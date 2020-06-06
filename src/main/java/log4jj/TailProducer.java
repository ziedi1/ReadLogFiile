/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package log4jj;

import java.io.File;
import java.util.Properties;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

/**
 *
 * @author ziedi
 */
public class TailProducer implements LogFileTailerListener{
 private LogFileTailer tailer;
    private Producer<String, String> producer;
    private String topic;
    private Thread t;

	/**
	 * Creates a new Tail instance to follow the specified file
	 */
	public TailProducer(String filename, String zkconnect, String topic,
			int runtime, long interval) {
            
		int curtime = (int) (System.currentTimeMillis() / 1000);
		Properties props = new Properties();
		props.put("bootstrap.servers", zkconnect);
                props.put("acks", "all");
                props.put("compression.type", "snappy");
                props.put("retries", 1);
                props.put("batch.size", 16384);
                props.put("linger.ms", 5);
                props.put("buffer.memory", 33554432);
                props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
                props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		this.producer = new KafkaProducer<String, String>(props);
		this.topic = topic;
                System.out.println("Curtime"+curtime+"  Runtime"+runtime);
		tailer = new LogFileTailer(new File(filename), interval, true, curtime
				+ runtime);
                
		tailer.addLogFileTailerListener(this);
		this.t = new Thread(tailer);
		this.t.start();
		// tailer.start();
	}

	/**
	 * A new line has been added to the tailed log file
	 * 
	 * @param line
	 *            The new line that has been added to the tailed log file
	 */
	public void newLogFileLine(String line) {
            System.out.println(line);
		ProducerRecord<String, String> data = new ProducerRecord<String, String>(
				this.topic, line);
		this.producer.send(data);
	}

	public boolean getState() {
		return this.t.isAlive();
	}

	@Override
	public void close() {
		this.producer.close();
}


    
       
}
