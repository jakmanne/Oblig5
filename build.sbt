name := "eksempel2"
 
version := "1.0" 
      
lazy val `eksempel2` = (project in file(".")).enablePlugins(PlayJava)

resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"
      
scalaVersion := "2.11.11"

libraryDependencies ++= Seq( javaJdbc , cache , ws, javaJdbc, javaWs, javaJpa,"org.hibernate" % "hibernate-entitymanager" % "5.2.10.Final", "org.postgresql" % "postgresql" % "9.3-1100-jdbc4",  "org.hibernate.javax.persistence" % "hibernate-jpa-2.1-api" % "1.0.0.Final",
  javaJpa.exclude("org.hibernate.javax.persistence", "hibernate-jpa-2.0-api"))

unmanagedResourceDirectories in Test <+=  baseDirectory ( _ /"target/web/public/test" )  

      