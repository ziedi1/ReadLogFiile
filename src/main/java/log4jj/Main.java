/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package log4jj;

import org.apache.log4j.PropertyConfigurator;

/**
 *
 * @author ziedi
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String log4jConfPath = "/home/ziedi/NetBeansProjects/Kafka/kafkaHelloWord/testLog4j/src/main/recources/log4j.properties";
      PropertyConfigurator.configure(log4jConfPath);
        TailProducer tailer = new TailProducer("/var/log/messages","localhost:9092","logTest" ,
	31536000,500);
    }
    
}
