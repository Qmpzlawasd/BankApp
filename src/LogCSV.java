import java.io.*;
import java.time.LocalDate;

public class LogCSV {
    private static final LogCSV instance = new LogCSV();
    private final String filePath = "/home/stefan/IdeaProjects/BankApp/log.csv";

    private LogCSV() {
    }

    public static LogCSV getInstance() {
        return instance;
    }

    public void log(String log) {

        FileWriter fr = null;
        try {
            fr = new FileWriter(filePath, true);
            fr.write(log + "," + LocalDate.now() + '\n');
            fr.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    public String getFilePath() {
        return filePath;
    }

}
