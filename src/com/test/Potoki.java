package com.test;

public class Potoki implements Runnable{

    @Override
    public void run() {
        Thread thr = Thread.currentThread();
        for (int i = 0; i <= 5; i++) {
            System.out.println(thr.getName() + ": " + i);
            try {
                Thread.sleep(2500);
            } catch (InterruptedException e) {
                System.out.println("Прерывание дочерного потока");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thr1 = new Thread(new Potoki(), "Поток 1");
        Thread thr2 = new Thread(new Potoki(), "Поток 2");
        Thread thr3 = new Thread(new Potoki(), "Поток 3");

        thr1.start();
        thr2.start();
        thr3.start();

        for (int i = 0; i <= 5; i++) {
            System.out.println("Главный поток: " + i);
            Thread.sleep(1500);
        }
        if (thr1.isAlive() || thr2.isAlive() || thr3.isAlive()) {
            System.out.println("Ждем завершение дочерних потоков");
            thr1.join();
            thr2.join();
            thr3.join();
        }
        System.out.println("Все процессы завершены");
    }
}
