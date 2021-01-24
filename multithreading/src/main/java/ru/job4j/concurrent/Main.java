package ru.job4j.concurrent;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Thread progress = new Thread(new ConsoleProgress());
        progress.start();
        Thread.sleep(1);
        progress.interrupt();
    }

}
