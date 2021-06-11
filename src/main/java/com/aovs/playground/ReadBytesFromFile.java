package com.aovs.playground;


public class ReadBytesFromFile {
    public static String readFile(String filename) throws java.io.IOException {
        if(null == filename || filename.isEmpty()) throw new IllegalArgumentException("Filename must not be null or empty");

        String fileContents = new String(java.nio.file.Files.readAllBytes(java.nio.file.Paths.get(filename))).trim();

        if(fileContents.length() < 7 || fileContents.length() % 7 != 0) throw new IllegalArgumentException("File contents is invalid");

        byte[] bytes = new byte[fileContents.length() / 7];

        for(int i = 0, j = 7, index = 0; j <= fileContents.length(); i += 7, j += 7, index++) {
            bytes[index] = (byte) Integer.parseUnsignedInt(fileContents.substring(i, j), 2);
        }

        return new String(bytes);
    }
}
