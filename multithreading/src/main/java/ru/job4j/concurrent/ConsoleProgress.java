package ru.job4j.concurrent;

public class ConsoleProgress implements Runnable {
    @Override
    public void run() {
        int process = 0;
        int chIndex = 0;
        String[] ch = new String[] { "\\", "|", "/" };
        while (!Thread.currentThread().isInterrupted()) {
            if (((process + 1) % 3) == 0) {
                chIndex = 0;
            } else {
                chIndex++;
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.print("\r load: " + process++ + ch[chIndex]);
        }
    }
}
