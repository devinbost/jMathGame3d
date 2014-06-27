/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mathgame;
import java.util.*;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
/**
 *
 * @author devinbost
 */
public class LogFileWriter {
     // The name of the file where the highscores will be saved
    private static final String LOG_FILE = "C:\\Users\\devinbost.BOSTINFORMATION\\Documents\\NetBeansProjects\\MathGame\\src\\mathgame\\log.txt";
    
    //Initialising an in and _outputStream for working with the file
    private static ObjectOutputStream _outputStream = null;
    private static ObjectInputStream _inputStream = null;
    
    private static LogFileWriter _logFileWriter = new LogFileWriter(); // can we use the "final" keyword here?
    // a private constructor prevents any other class from instantiating.
    private LogFileWriter(){
        // since the OutputObjectStream must be the same instance for appending to work, we need to construct it when this class is first constructed.
         try {
        _outputStream = new ObjectOutputStream(new FileOutputStream(LOG_FILE));
        } catch (FileNotFoundException e) {
            System.out.println("Error:\n FileNotFound when trying to update scores:  " + e.getMessage() + ",the program will try and make a new file");
        } catch (IOException e) {
            System.out.println("Error:\n IO Problem when trying to update scores: " + e.getMessage());
        } finally {
            try {
                if (_outputStream != null) {
                    _outputStream.flush();
                    _outputStream.close();
                }
            } catch (IOException e) {
                System.out.println("Error:\n IO Problem when trying to update scores: " + e.getMessage());
            }
        }
        
    }
    
    // static "instance" method.
    public static LogFileWriter getInstance(){
        return _logFileWriter;
    }
    private static LogFileEntry _logFileEntry = null;
    // log file writer will write the logfileentry to a log file.
    public class LogFileEntry implements Serializable {
        private static final long serialVersionUID = 1L;
        // timestamp will need to be set during construction
        // message will need to be also set during construction
        // Get the date today using Calendar object.
        private Date _timestamp = Calendar.getInstance().getTime();
        private String _messageToWrite = "";
        DateFormat _dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        public LogFileEntry(String message){
            _messageToWrite = _dateFormat.format(_timestamp);
        }
        
    }
    public void writeLogFileEntry(String message){
        _logFileEntry = new LogFileEntry(message);
        // write entry to log file
        this.updateLogFile();
    }
    
    public void updateLogFile() {
        try {
            if (_outputStream == null) {
                _outputStream = new ObjectOutputStream(new FileOutputStream(LOG_FILE, true));
            }
            
            _outputStream.writeObject(_logFileEntry);
        } catch (FileNotFoundException e) {
            System.out.println("Error:\n FileNotFound when trying to write to log:  " + e.getMessage() + ",the program will try and make a new file");
        } catch (IOException e) {
            System.out.println("Error:\n IO Problem when trying to write to log: " + e.getMessage());
        } finally {
            try {
                if (_outputStream != null) {
                    _outputStream.flush(); // flush forces the output stream to write.
                    _outputStream.close();
                }
            } catch (IOException e) {
                System.out.println("Error:\n IO Problem when trying to write to log: " + e.getMessage());
            }
        }
    }
        public void loadLogFile(){
        try{
            _inputStream = new ObjectInputStream(new FileInputStream(LOG_FILE));
            
        } catch (FileNotFoundException e) {
            System.out.println("Error:\n FileNotFound when trying to load log file: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error:\n IO Problem when trying to load log file: " + e.getMessage());
        } finally {
            try {
                if (_outputStream != null) {
                    _outputStream.flush();
                    _outputStream.close();
                }
            } catch (IOException e) {
                System.out.println("Error:\n IO Problem when trying to load log file: " + e.getMessage());
            }
        }
    }
}
