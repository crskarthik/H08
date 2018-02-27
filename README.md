# H08 - Java Kafka & Install Spark

## Complete Java Kafka Exercise

Read the article here: https://www.javaworld.com/article/3060078/big-data/big-data-messaging-with-kafka-part-1.html (Links to an external site.)Links to an external site..

Create a new CustomProducer to auto-generate and send at least 10 messages to the topic. You can create a random word generator, cycle through a list sending one message every second, or use Facebook or Twitter APIs to access live content.  Experiment - the goal is to be comfortable adding external messaging to any Java app. 

Note: Use the binary version of Kafka (not the source), and make sure never to leave spaces in path and file names. Do not install them in your "My documents" folder. If you download and extract manually, I suggest C:\kafka and C:\zookeeper (with the current version appended). 

Create an h08 folder and set up your tools. (Use chocolatey or or your previous installations.)

## Set Up Tools

Run PowerShell as Admin and use Chocolatey to install zookeeper, kafka, and maven (verify by checking C:\ProgramData\chocolatey\lib and C:\tools).

```PowerShell
choco install apache-zookeeper -y
choco install kafka  -y
choco install maven -y
```

Configure environment variables for easy access. Create the following - use your path. 

```
ZOOKEEPER_HOME = C:\Tools\zookeeper-3.4.9
KAFKA_HOME = C:\ProgramData\chocolatey\lib\kafka\tools\kafka_2.11-1.0.0
```

Edit System “Path” to append

```
%ZOOKEEPER_HOME%\bin;
%KAFKA_HOME%\bin;
%KAFKA_HOME%\bin\windows;
```

Create the required zoo.cfg. In C:\Tools\zookeeper-3.4.9\conf, copy zoo_sample.cfg to zoo.cfg.

Start Zookeeper. In a new PowerShell Admin folder, run the following, then minimize, but don't close the window.
```PowerShell
zkServer
```

Start the Kafka service. In a new PowerShell Admin folder, run the following and then minimize (do not close) the window:

```PowerShell
kafka-server-start.bat C:\ProgramData\chocolatey\lib\kafka\tools\kafka_2.11-1.0.0\config\server.properties
```

## Execute Java Sample

1. Clone the Maven project from the article into h08. Explore the Maven pom file and the Java code for the Producer and Consumer.
1. In a Git Bash window, compile the code using Maven and create an executable jar file with  

```Bash 
mvn clean compile assembly:single
```

1. In this Git Bash window, start the Consumer app using topic test and group1 with 

```Bash
java -cp target/KafkaAPIClient-1.0-SNAPSHOT-jar-with-dependencies.jar com.spnotes.kafka.simple.Consumer test group1
```

1. In another Git Bash window, start the Producer app using topic test with 

```Bash
java -cp target/KafkaAPIClient-1.0-SNAPSHOT-jar-with-dependencies.jar com.spnotes.kafka.simple.Producer test
```

1. Type some messages for the Producer.
1. Verify the messages are output by the Consumer.

## Create Custom App

Plan and design a routine to create messages without the users typing. You may use an array of messages, generate them randomly, or access a public API.

Implement your Custom Producer app. 

Start your app.

Verify your messages are output by the Consumer.

Create a professional README.md file to explain how to setup and run your project.

Put your entire code solution in a repo and share a clickable link. 