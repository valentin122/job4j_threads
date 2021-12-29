package ru.job4j.concurrent;

import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
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
        //String file = "https://raw.githubusercontent.com/peterarsentev/course_test/master/pom.xml";
        try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream("pom_tmp.xml")) {
            byte[] dataBuffer = new byte[speed];
            int bytesRead;
            Date startDate = new Date();
            System.out.println("Start downloading: " + startDate.toString());
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                long startTime = new Date().getTime();
                fileOutputStream.write(dataBuffer, 0, bytesRead);
                long timeLimit = 1000 - (new Date().getTime() - startTime);
                System.out.println("timeLimit: " + timeLimit);
                Thread.sleep(timeLimit);
            }
            System.out.println("End downloading: " + new Date().toString());
            System.out.println("Time expend: " + (new Date().getTime() - startDate.getTime()) + "ms");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        String url = "https://raw.githubusercontent.com/peterarsentev/course_test/master/pom.xml"; //args[0];
        int speed = 1024; //Integer.parseInt(args[1]);
        Thread wget = new Thread(new Wget(url, speed));
        wget.start();
        wget.join();
    }
}
