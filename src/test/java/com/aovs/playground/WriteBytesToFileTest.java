package com.aovs.playground;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class WriteBytesToFileTest {
    @Test
    void testWriteToFile() throws IOException {
        String filename = "output.txt";
        WriteBytesToFile.writeFile(filename, "Hello!");
        Path filepath = Paths.get(filename);
        assertEquals(new String(Files.readAllBytes(filepath)).trim(), "100100011001011101100110110011011110100001");
        Files.deleteIfExists(filepath);
    }

    @Test
    void testWriteToFileFancy() throws IOException {
        String filename = "output.txt";
        WriteBytesToFile.writeFileFancy(filename, "Hello!");
        Path filepath = Paths.get(filename);
        assertEquals(new String(Files.readAllBytes(filepath)).trim(), "100100011001011101100110110011011110100001");
        Files.deleteIfExists(filepath);
    }

    @Test
    void testNullString() {
        assertThrows(IllegalArgumentException.class, () -> WriteBytesToFile.writeFile("whatever.txt", null));
        assertThrows(IllegalArgumentException.class, () -> WriteBytesToFile.writeFileFancy("whatever.txt", null));
    }

    @Test
    void testEmptyString() {
        assertThrows(IllegalArgumentException.class, () -> WriteBytesToFile.writeFile("whatever.txt", ""));
        assertThrows(IllegalArgumentException.class, () -> WriteBytesToFile.writeFileFancy("whatever.txt", ""));
    }
}
