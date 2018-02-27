package edu.nwmissouri.isl.professorcase.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;
import java.io.File;
import java.io.FileInputStream;
import java.util.Scanner;

/**
 * Custom Producer using Kafka for messaging.
 */
public class CustomProducer {
  private static Scanner in;
  private static FileInputStream istream = null;
  private static Properties props = new Properties();

  public static void main(String[] argv) throws Exception {
    in = new Scanner(System.in);
    System.out.println("Enter message(type exit to quit)");

    // Create an input stream for the properties
    istream = new FileInputStream(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
        + File.separator + "resources" + File.separator + "server.properties");

    // Load properties and display
    props.load(istream);
    System.out.println(props.getProperty("BOOTSTRAP_SERVERS_CONFIG"));
    System.out.println(props.getProperty("KEY_SERIALIZER_CLASS_CONFIG"));
    System.out.println(props.getProperty("VALUE_SERIALIZER_CLASS_CONFIG"));
    System.out.println(props.getProperty("TOPIC"));

    //Configure the Producer
    Properties configProperties = new Properties();
    configProperties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, props.getProperty("BOOTSTRAP_SERVERS_CONFIG"));
    configProperties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, props.getProperty("KEY_SERIALIZER_CLASS_CONFIG"));
    configProperties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
        props.getProperty("VALUE_SERIALIZER_CLASS_CONFIG"));

    String topicName = props.getProperty("topic");

    org.apache.kafka.clients.producer.Producer producer = new KafkaProducer(configProperties);
    String line = in.nextLine();
    while (!line.equals("exit")) {
      //TODO: Make sure to use the ProducerRecord constructor that does not take parition Id
      ProducerRecord<String, String> rec = new ProducerRecord<String, String>(topicName, line);
      producer.send(rec);
      line = in.nextLine();
    }
    in.close();
    producer.close();
  }
}
