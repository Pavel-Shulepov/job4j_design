package io;

import java.io.FileOutputStream;

public class MultiplicationTable {

    public static void saveToFile(String fileName) {

        try (FileOutputStream out = new FileOutputStream(fileName)) {
            for(int i = 1; i < 10; i++){
                for(int k = 1; k < 10; k++){
                    out.write((k * i + "\t").getBytes());
                }
                out.write("\n".getBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
