addSbtPlugin("com.eed3si9n" % "sbt-appengine" % "0.6.2")

fullResolvers ~= {_.filterNot(_.name == "jcenter")}
