package ru.job4j.concurrent.io;

import java.io.*;

public class SaveFile {
    private final File file;

    public SaveFile(File file) {
        this.file = file;
    }

    public void saveContent(String content) throws IOException {
        BufferedOutputStream bufferedOutputStream;
        try (OutputStream out = new FileOutputStream(file)) {
            bufferedOutputStream = new BufferedOutputStream(out);
            for (int i = 0; i < content.length(); i += 1) {
                bufferedOutputStream.write(content.charAt(i));
            }
        }
    }
}
