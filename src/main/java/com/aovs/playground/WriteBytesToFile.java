package com.aovs.playground;


public class WriteBytesToFile {
    // Plain vanilla solution
    public static void writeFile(String filename, String text) {
        if(null == text || text.isEmpty()) throw new IllegalArgumentException("String must not be null or empty");

        StringBuilder builder = new StringBuilder();
        for (byte b : text.getBytes()) builder.append(String.format("%7s", Integer.toBinaryString(b)).replace(' ', '0'));
        try {
            java.nio.file.Files.write(java.nio.file.Paths.get(filename), builder.toString().getBytes());
        } catch (java.io.IOException ioe) {
            System.out.printf("Error writing text: %s to file: %s", text, filename);
        }
    }

    // Solution using Java 8 fancy FP features
    public static void writeFileFancy(String filename, String text) {
        if(null == text || text.isEmpty()) throw new IllegalArgumentException("String must not be null or empty");

        byte[] bytes = text.getBytes();
        try {
            java.nio.file.Files.write(
                    java.nio.file.Paths.get(filename),
                    java.util.stream.IntStream
                            .range(0, bytes.length)
                            .mapToObj(i -> bytes[i])
                            .map(b -> String.format("%7s", Integer.toBinaryString(b)).replace(' ', '0'))
                            .collect(java.util.stream.Collectors.joining())
                            .getBytes()
            );
        } catch (java.io.IOException ioe) {
            System.out.printf("Error writing text: %s to file: %s", text, filename);
        }
    }
}
