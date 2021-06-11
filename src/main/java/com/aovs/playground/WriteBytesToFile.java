package com.aovs.playground;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class WriteBytesToFile {
    public static void writeFile(String filename, String text) {
        StringBuilder builder = new StringBuilder();
        for (byte b : text.getBytes()) builder.append(byteToBinaryString(b));
        try {
            Files.write(Paths.get(filename), builder.toString().getBytes());
        } catch (IOException ioe) {
            System.out.printf("Error writing text: %s to file: %s%n", text, filename);
        }
    }

    public static void writeFileFancy(String filename, String text) {
        byte[] bytes = text.getBytes();
        try {
            Files.write(
                    Paths.get(filename),
                    IntStream
                            .range(0, bytes.length)
                            .mapToObj(i -> bytes[i])
                            .map(WriteBytesToFile::byteToBinaryString)
                            .collect(Collectors.joining())
                            .getBytes()
            );
        } catch (IOException ioe) {
            System.out.printf("Error writing text: %s to file: %s%n", text, filename);
        }
    }

    private static String byteToBinaryString(byte b) {
        return String.format("%7s", Integer.toBinaryString(b)).replace(' ', '0');
    }
}
