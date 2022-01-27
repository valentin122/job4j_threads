package ru.job4j.concurrent.io;

import java.io.*;
import java.util.function.Predicate;

public class ParseFile {
    private final File file;

    public ParseFile(File file) {
        this.file = file;
    }

    public synchronized File getFile() {
        return file;
    }

    public String getContent(Predicate<Character> filter) throws IOException {
        BufferedInputStream in;
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(file))) {
            in = inputStream;
            int data;
            while ((data = in.read()) > 0) {
                if (filter.test((char) data)) {
                    stringBuilder.append((char) data);
                }
            }
        }
        return stringBuilder.toString();
    }
}
