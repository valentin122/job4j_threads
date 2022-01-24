package ru.job4j.concurrent;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Date;

public class Wget implements Runnable {
    private final String url;
    private final int speed;

    public Wget(String url, int speed) {
        this.url = url;
        this.speed = speed;
    }

    @Override
    public void run() {
        String fileName = url.substring(url.lastIndexOf("/") + 1);
        try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
            FileOutputStream fileOutputStream = new FileOutputStream(fileName)) {
            byte[] dataBuffer = new byte[1024];
            int bytesRead;
            long bytesWrited = 0;
            Date referencePointDate = new Date();
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
                bytesWrited += 1024;
                if (bytesWrited >= speed) {
                    long deltaTime = new Date().getTime() - referencePointDate.getTime();
                    if (deltaTime < 1000) {
                        Thread.sleep(1000 - deltaTime);
                        System.out.println("sleeping: " + (1000 - deltaTime));
                    }
                    referencePointDate = new Date();
                    bytesWrited = 0;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void validateParams(String[] args) {
        if (args.length != 2) {
            System.out.println("Illegal numbers of args. Please input url and speed.");
            throw new IllegalArgumentException();

        }
    }

    public static void main(String[] args) throws InterruptedException {
        validateParams(args);
        String url = args[0];
        int speed = Integer.parseInt(args[1]);
        Thread wget = new Thread(new Wget(url, speed));
        wget.start();
        wget.join();
    }
}