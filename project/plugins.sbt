addSbtPlugin("com.eed3si9n" % "sbt-appengine" % "0.8.0")

fullResolvers ~= {_.filterNot(_.name == "jcenter")}
