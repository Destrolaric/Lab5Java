package walker;
import java.io.*;
import java.nio.charset.StandardCharsets;


public class Walk {
    public static void main(String[] args) {
        try {
            walk(args[0], args[1]);
        } catch (Exception e) {

        }
    }

    private static String inputFile;
    private static String outputFile;
    private static String currentline;
    static private BufferedReader reader;
    static private OutputStreamWriter fileWriter;


    private static void openInputFile() throws IOException {
        reader = new BufferedReader(new InputStreamReader(new FileInputStream(inputFile),StandardCharsets.UTF_8));
    }

    private static void openOutputFile() throws IOException {
        File outFile = new File(outputFile);
        if (!outFile.exists()) {
            if (outFile.getParentFile() != null) {
                outFile.getParentFile().mkdirs();
            }
            outFile.createNewFile();
        }
        fileWriter=new OutputStreamWriter(new FileOutputStream(outputFile), StandardCharsets.UTF_8);
    }

    public static void walk(String input,String output) throws IOException {
        inputFile=input;
        outputFile=output;
        try {
            openInputFile();
            openOutputFile();
            getHash();
        } finally {
            if (reader != null) {
                reader.close();
            }

            if (fileWriter != null) {
                fileWriter.close();
            }
        }
    }


    private static void getHash() throws IOException {
        while ((currentline = reader.readLine()) != null) {

            fileWriter.write(String.format("%08x ", HashCalculator.hash32(currentline)) + currentline + System.lineSeparator());

        }
    }
}


