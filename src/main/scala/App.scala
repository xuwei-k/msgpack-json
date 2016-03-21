package msgpack_json

import java.io._
import msgpack4z._
import unfiltered.request._
import unfiltered.response._
import scalaz.{-\/, \/-}

object App {

  private val codec = JawnMsgpack.jValueCodec(JawnUnpackOptions.default)

  def json2bytes(jsonString: String): Array[Byte] = {
    val json = jawn.Parser.parseUnsafe[jawn.ast.JValue](jsonString)
    codec.toBytes(json, new MsgpackJavaPacker)
  }

  def stream2bytes(in: InputStream): Array[Byte] = {
    val out = new ByteArrayOutputStream
    val ba = new Array[Byte](8192)
    @annotation.tailrec
    def loop(): Unit = {
      val len = in.read(ba)
      if (len > 0) out.write(ba, 0, len)
      if (len >= 0) loop()
    }
    loop()
    out.toByteArray
  }

  val main: unfiltered.filter.Plan.Intent = {
    case req @ POST(Path("/json2msgpack")) =>
      val str = scala.io.Source.fromInputStream(req.inputStream, "UTF-8").mkString
      Ok ~> ResponseBytes(App.json2bytes(str))
    case req @ POST(Path("/msgpack2json")) =>
     val unpacker = MsgpackJavaUnpacker.defaultUnpacker(stream2bytes(req.inputStream))
      App.codec.unpackAndClose(unpacker) match {
        case \/-(a) =>
          Ok ~> JsonContent ~> ResponseString(a.toString)
        case -\/(a) =>
          BadRequest ~> ResponseString(a.toString)
      }
  }
}

final class App extends unfiltered.filter.Plan {

  override def intent = {
    case request if App.main.isDefinedAt(request) =>
    try{
      App.main(request)
    }catch{
      case e: Throwable =>
        e.printStackTrace()
        System.err.println(e)
        System.err.println(e.getStackTrace.mkString("\n"))
        ResponseString(e.toString + "\n\n" + e.getStackTrace.mkString("\n")) ~> InternalServerError
    }
  }

}
