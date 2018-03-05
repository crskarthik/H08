# H08 - Java Kafka & Install Spark

## Complete Java Kafka Exercise

Read the article here: https://www.javaworld.com/article/3060078/big-data/big-data-messaging-with-kafka-part-1.html

Create a new CustomProducer to auto-generate and send at least 10 messages to the topic. You can create a random word generator, cycle through a list sending one message every second, or use Facebook or Twitter APIs to access live content.  Experiment - the goal is to be comfortable adding external messaging to any Java app.

Note: Use the binary version of Kafka (not the source), and make sure never to leave spaces in path and file names. Do not install them in your "My documents" folder. If you download and extract manually, I suggest C:\kafka and C:\zookeeper (with the current version appended).

## Design and Implement a Custom Producer App

Plan and design a routine to create messages without the users typing. You may use an array of messages, generate them randomly, or access a public API.

If you want to test the CaseKafka example that accesses Twitter data, you'll need to follow the Twitter guides below and create your custom 
src/main/resources/twitter4j.properties file. 

Implement your Custom Producer app.

Compile and build a new executable jar with maven using the mvn clean compile assembly:single command. 

Start your consumer using a custom java -cp command (short for 'classpath').

Verify your messages are output by the Consumer.

Create a professional README.md file to explain how to setup and run your project.

Put your entire code solution in a repo and share a clickable link.

## Set Up Environment

Clone this h08 repo and set up your tools. (Use chocolatey or or your previous installations.)

Run PowerShell as Admin and use Chocolatey to install zookeeper, kafka, and maven (verify by checking C:\ProgramData\chocolatey\lib and C:\tools).

```PowerShell
choco install apache-zookeeper -y
choco install kafka -y
choco install maven -y
```

Configure environment variables for easy access. Create the following - use your path. 
-ZOOKEEPER_HOME = C:\Tools\zookeeper-3.4.9
- KAFKA_HOME = C:\ProgramData\chocolatey\lib\kafka\tools\kafka_2.11-1.0.0

```PowerShell
[Environment]::SetEnvironmentVariable("ZOOKEEPER_HOME", "C:\Tools\zookeeper-3.4.9", "Machine")
[Environment]::SetEnvironmentVariable("KAFKA_HOME", "C:\ProgramData\chocolatey\lib\kafka\tools\kafka_2.11-1.0.0", "Machine")
```

Edit System “Path” to append

```
%ZOOKEEPER_HOME%\bin;
%KAFKA_HOME%\bin;
%KAFKA_HOME%\bin\windows;
```

## Configure Zookeeper

Create the required zoo.cfg. In C:\Tools\zookeeper-3.4.9\conf, copy zoo_sample.cfg to zoo.cfg.

## Start Zookeeper Service

Start Zookeeper. In a new PowerShell Admin folder, run the following, then minimize, but don't close the window.
```PowerShell
zkServer
```

## Start Kafka Service

Start the Kafka service. In a new PowerShell Admin folder, run the following to clean up old log files and start the service. Then minimize (but do not close) the window.

```PowerShell
Remove-Item â€“path C:\tmp\kafka-logs â€“recurse
kafka-server-start C:\ProgramData\chocolatey\lib\kafka\tools\kafka_2.11-1.0.0\config\server.properties
```

## Create the KafkaAPIClient Executable Jar

1. Clone the Maven project from the article into h08. Explore the Maven pom file and the Java code for the Producer and Consumer.
1. Open a PowerShell window in the *KafkaAPIClient* folder, compile the code using Maven and create an executable jar file. Generated artificacts can be found in the new 'target' folder.

```PowerShell
mvn clean compile assembly:single
```

## Create the KafkaChinta Executable Jar

1. Open a PowerShell window in the *KafkaChinta* folder, compile the code using Maven and create an executable jar file. Generated artificacts can be found in the new 'target' folder.

```PowerShell
mvn clean compile assembly:single
```

## Start the Default Consumer App

Open a PowerShell window in the *KafkaAPIClient* folder, start the original consumer app using topic test and group1 with:

```PowerShell
java -cp target/KafkaAPIClient-1.0-SNAPSHOT-jar-with-dependencies.jar com.spnotes.kafka.simple.Consumer test group1
```

## Start the Default Producer App

Open another PowerShell window in the *KafkaAPIClient* folder, start the Producer app using topic test:

```PowerShell
java -cp target/KafkaAPIClient-1.0-SNAPSHOT-jar-with-dependencies.jar com.spnotes.kafka.simple.Producer test
```

1. Type some messages for the Producer.
1. Verify the messages are output by the Consumer.

## Start The Kafka Chinta Producer app

Open another PowerShell window in the *KafkaChinta* folder, and run:

```PowerShell
java -cp target/KafkaChinta-1.0-SNAPSHOT-jar-with-dependencies.jar edu.nwmissouri.Chinta.kafka.CustomProducer
```

OR for convenience, there's a batch file to run this CustomProducer.
Open a PowerShell window here and run:

```PowerShell
.\runp
```

## Start Your Custom Producer app

Using your own custom package, create your custom producer. Compile and build the updated jar. Using the java -cp command, start your producer as well.

Monitor the results in the consumer app.

## Accessing Twitter with Code

See http://www.baeldung.com/twitter4j

For help registering your Twitter API see this Tweepy article: http://www.compjour.org/tutorials/getting-started-with-tweepy/

Register app: 
https://apps.twitter.com

Developer Agreement: 
https://developer.twitter.com/en/developer-terms/agreement-and-policy

Twitter App Attributes Example:

- Name: KafkaCaseCustomProducer
- Description: Sample app to illustrate working with the Twitter API and using Kafka from a Java application
- Website: https://bitbucket.org/professorcase/h08
- Callback URL: (blank)

## Working with Kafka

To list topics, open PowerShell as admin and run:

```PowerShell
kafka-topics.bat --list --zookeeper localhost:2181
```

To delete a topic (e.g. test), open PowerShell as admin and run:

```PowerShell
kafka-topics.bat --zookeeper localhost:2181 --delete --topic test

To describe a topic (e.g. test), open PowerShell as admin and run:

```PowerShell
kafka-topics.bat --describe --zookeeper localhost:2181 --topic test
```

To view the messages on a topic (e.g. test), open PowerShell as admin and run:

```PowerShell
kafka-console-consumer.bat --zookeeper localhost:2181 --topic test --from-beginning
```

To list the groups, open PowerShell as admin and run:

```PowerShell
kafka-consumer-groups.bat --zookeeper localhost:9092 --list
```

## See also

http://cloudurable.com/blog/kafka-tutorial-kafka-producer/index.html