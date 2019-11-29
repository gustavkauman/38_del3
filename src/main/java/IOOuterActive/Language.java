package IOOuterActive;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Language {
    private Map<String, String> output = new HashMap<>();

    public void readFile() throws IOException {
        String src = "danish.txt";
        InputStream fis = Main.class.getClassLoader().getResourceAsStream(src);
        Scanner read = new Scanner(fis, "UTF-8");
        read.useDelimiter("\n");

        while (read.hasNext()){
            output.put(read.next().trim(), read.next().trim());
        }

        fis.close();
    }

    public String getOutputMessage(String key) {
        return output.get(key);
    }

}

