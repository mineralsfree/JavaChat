package men.brakh;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class Logger {

    private FileWriter fw;
    private String fileName;
    public Logger(String filename) throws IOException {
        this.fileName = filename;
        File file = new File(fileName);
        file.createNewFile();
        fw = new FileWriter(file);


    }
    public void log(String msg){
        LocalDateTime a =  LocalDateTime.now();
        String Output = "time {"+ a+"}" + msg;
        try {
            fw.write(Output+ "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(Output);


    }
    public void log(Exception e){

    }
}
