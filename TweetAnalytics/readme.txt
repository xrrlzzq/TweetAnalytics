In order to run this application, you need to use "cd" command in cmd to work in the project file and enter then "sbt run" in cmd.
And the open browser, enter "localhost:9000" to run the apllication.
technical notes

  (1) install sbt-jacoco:
      add following line to project\plugins.sbt:
      
      addSbtPlugin("com.github.sbt" % "sbt-jacoco" % "3.0.3")

  (2) install twitter4j:

      add following lines to build.sbt:
      
      // https://mvnrepository.com/artifact/org.twitter4j/twitter4j-core
      libraryDependencies += "org.twitter4j" % "twitter4j-core" % "4.0.6"

      // https://mvnrepository.com/artifact/org.twitter4j/twitter4j-stream
      libraryDependencies += "org.twitter4j" % "twitter4j-stream" % "4.0.6"


  (3) install mockito:

      add following lines to build.sbt:

      // https://mvnrepository.com/artifact/org.mockito/mockito-core
      libraryDependencies += "org.mockito" % "mockito-core" % "2.15.0" % Test

      // https://mvnrepository.com/artifact/net.bytebuddy/byte-buddy
      libraryDependencies += "net.bytebuddy" % "byte-buddy" % "1.7.9"

      // https://mvnrepository.com/artifact/net.bytebuddy/byte-buddy-agent
      libraryDependencies += "net.bytebuddy" % "byte-buddy-agent" % "1.7.9" % Test

      // https://mvnrepository.com/artifact/org.objenesis/objenesis
      libraryDependencies += "org.objenesis" % "objenesis" % "2.6" % Test

   (4)install CoffeeScript plugin: 
      add following line to project\plugins.sbt:

      addSbtPlugin("com.typesafe.sbt" % "sbt-coffeescript" % "1.0.2")

  £¨5£©enable javascript at client side in play framework:
       add add following lines to application.conf:

       play.filters.headers {
            contentSecurityPolicy = "default-src 'self' 'unsafe-inline' 'unsafe-eval' *.jquery.com; connect-src 'self' ws:"
       }

  £¨6)install Akka Testkit for actor test:
      add following lines to build.sbt:

      // https://mvnrepository.com/artifact/com.typesafe.akka/akka-testkit
      libraryDependencies += "com.typesafe.akka" %% "akka-testkit" % "2.5.11" % Test


    (7)install WS Client library for WebSocket test:
       add following lines to build.sbt:
       
       libraryDependencies += ws

    (8)install Awaitility testing libraries for dealing with CompletionStage:
       add following lines to build.sbt:

       libraryDependencies += "org.awaitility" % "awaitility" % "3.0.0" % Test

