package ChatServer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.GregorianCalendar;

public class LogWriter {

    private File logFile;
    private GregorianCalendar calendar;

    public LogWriter() throws IOException {

        this.logFile = new File("C:\\Users\\Logan\\Desktop\\log.txt");
        this.logFile.createNewFile();
        this.calendar = new GregorianCalendar();
    }

    public void writeLog(String msg) throws IOException {

        if((msg.indexOf("connectionList")) < 0){
            FileWriter logFileWriter = new FileWriter(logFile, true);
            try {
                logFileWriter.write(calendar.getTime() + " "+ msg + "\n");
            } finally {
                logFileWriter.close();
            }
        }
    }
}
