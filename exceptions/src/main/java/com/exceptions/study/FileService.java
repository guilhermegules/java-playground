package main.java.com.exceptions.study;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileService {
    public void saveFile(String fileName, String content) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
          writer.write(content);
          System.out.println("Text operations");
        } 
    }
}
