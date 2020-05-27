package walker;

import java.io.*;
import java.nio.charset.StandardCharsets;


public class Walk {
    private static String inputFile;
    private static String outputFile;
    private static String currentline;
    private static BufferedReader reader;
    private static OutputStreamWriter fileWriter;

    public static void main(String[] args) {
        try {
            walk(args[0], args[1]);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static void openInputFile() throws IOException {
        reader = new BufferedReader(new InputStreamReader(new FileInputStream(inputFile), StandardCharsets.UTF_8));
    }

    private static void openOutputFile() {
        File outFile = new File(outputFile);
        try {
            if (!outFile.exists()) {
                if (outFile.getParentFile() != null) {
                    if (outFile.getParentFile().mkdirs()) {
                        throw new IOException();
                    }
                }

                if (outFile.createNewFile()) {
                    throw new IOException();
                }
            }

            fileWriter = new OutputStreamWriter(new FileOutputStream(outputFile), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static void getHash() throws IOException {
        while ((currentline = reader.readLine()) != null) {

            fileWriter.write(String.format("%08x ", HashCalculator.hash32(currentline)) + currentline + System.lineSeparator());

        }
    }

    public static void walk(String input, String output) throws IOException {
        inputFile = input;
        outputFile = output;
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


}



