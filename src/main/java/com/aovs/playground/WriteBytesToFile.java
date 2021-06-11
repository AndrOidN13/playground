package com.aovs.playground;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class WriteBytesToFile {
    // Plain vanilla solution
    public static void writeFile(String filename, String text) {
        if(null == text || text.isEmpty()) throw new IllegalArgumentException("String must not be null or empty");

        StringBuilder builder = new StringBuilder();
        for (byte b : text.getBytes()) builder.append(String.format("%7s", Integer.toBinaryString(b)).replace(' ', '0'));
        try {
            Files.write(Paths.get(filename), builder.toString().getBytes());
        } catch (IOException ioe) {
            System.out.printf("Error writing text: %s to file: %s%n", text, filename);
        }
    }

    // Solution using Java 8 fancy FP features
    public static void writeFileFancy(String filename, String text) {
        if(null == text || text.isEmpty()) throw new IllegalArgumentException("String must not be null or empty");

        byte[] bytes = text.getBytes();
        try {
            Files.write(
                    Paths.get(filename),
                    IntStream
                            .range(0, bytes.length)
                            .mapToObj(i -> bytes[i])
                            .map(b -> String.format("%7s", Integer.toBinaryString(b)).replace(' ', '0'))
                            .collect(Collectors.joining())
                            .getBytes()
            );
        } catch (IOException ioe) {
            System.out.printf("Error writing text: %s to file: %s%n", text, filename);
        }
    }
}
