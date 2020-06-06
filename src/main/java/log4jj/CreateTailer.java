/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package log4jj;

import java.util.Properties;

/**
 *
 * @author ziedi
 */
public class CreateTailer {
    private static Properties p;
    private final static String BOOTSTRAP_SERVERS = "localhost:9092";
    private static int type=1; // 1:one logfile to one topic; 2:one logfile to multitopic
    private static String topic="logTest";
    private static String filename="/var/log/messages";

    public CreateTailer() {
        TailProducer tailer = new TailProducer(filename,BOOTSTRAP_SERVERS,this.topic ,
	Integer.valueOf(this.p.getProperty("runtime")),
	Long.valueOf(this.p.getProperty("interval")));
    }
}
