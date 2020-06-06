/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package log4jj;

/**
 *
 * @author ziedi
 */
public interface LogFileTailerListener {
    /**
     * A new line has been added to the tailed log file
     * 
     * @param line The new line that has been added to the tailed log file
     */
    public void newLogFileLine(String line);
    public void close();
}
