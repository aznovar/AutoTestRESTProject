package main.java.ru.kafka;

import org.apache.kafka.clients.producer.*;

import java.util.Properties;

public class KafkaProducer {

    private Properties props;
    private String topicName;

    public KafkaProducer(String topicName) {
        props = new Properties();
        props.put("some.bootstrap.server", "http://some_adress_of_server");
        props.put("acks", "all");
        props.put("client.id", "Autotests");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        this.topicName = topicName;
    }

    public void sendMessage(String message) {
        Producer<String, String> producer = new KafkaProducer<>(props);
        producer.send(new ProducerRecord<String, String>(topicName, message));
        producer.flush();
        producer.close();
    }
}

