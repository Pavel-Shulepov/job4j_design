package ru.job4j.concurrent;

public class Wget {
    public static void main(String[] args) {
        Thread wget = new Thread(
                () -> {
                    for (int i = 0; i <= 100; i++) {
                        try {
                            Thread.sleep(1000);
                            System.out.print("\rLoading : " + i + "%");
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                }
        );
        wget.start();
    }
}
