package IOOuterActive;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Language {
    private Map<String, String> output = new HashMap<>();

    public void readFile() throws IOException {
        String src = "src/Languages/danish.txt";
        File langFile = new File(src);
        InputStream fis = new FileInputStream(langFile);
        Scanner read = new Scanner(fis);
        read.useDelimiter("\n");

        while (read.hasNext()){
            output.put(read.next().trim(), read.next().trim());
        }

        fis.close();
    }

    public Map<String, String> getOutput() {
        return output;
    }

    public String getOutputMessage(String key) {
        return output.get(key);
    }

}

