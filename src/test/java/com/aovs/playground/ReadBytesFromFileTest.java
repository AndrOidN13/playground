package com.aovs.playground;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ReadBytesFromFileTest {
    @Test
    void testReadFromFile() throws IOException {
        String filename = "readerTest.txt";
        Path filepath = Paths.get(filename);
        Files.write(Paths.get(filename), "100100011001011101100110110011011110100001".getBytes());
        assertEquals(ReadBytesFromFile.readFile(filename), "Hello!");
        Files.deleteIfExists(filepath);
    }

    @Test
    void testInvalidFilename() {
        String filename = null;
        assertThrows(IllegalArgumentException.class, () -> ReadBytesFromFile.readFile(filename));
    }

    @Test
    void testReadFromFileInvalid() throws IOException {
        String filename = "readerTestInvalid.txt";
        Path filepath = Paths.get(filename);
        Files.write(Paths.get(filename), "1001".getBytes());
        assertThrows(IllegalArgumentException.class, () -> ReadBytesFromFile.readFile(filename));
        Files.deleteIfExists(filepath);
    }
}
