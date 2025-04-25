package org.example;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Напишите код, в котором создаются и запускаются на выполнение потоки S и W.
 * <p>
 * Поток S выполняет переключение с задержкой 1000 миллисекунд из состояния true в состояние false и наоборот.
 * <p>
 * Поток W ожидает состояния true потока S, выводит на консоль обратный отсчет от 30 с задержкой
 * 100 миллисекунд и приостанавливает свое действие, как только поток S переключен в состояние false.
 * Условием завершения работы потоков является достижение отсчета нулевой отметки.
 *
 * @author Lina_Dautova
 */
public class Main {
    private static final Object monitor = new Object();
    private static final AtomicBoolean down = new AtomicBoolean(false);
    private static final AtomicInteger count = new AtomicInteger(0);
    private static volatile boolean flag = false;

    public static void main(String[] args) {
        System.out.println("started");
        new Thread(new S_Thread()).start();
        new Thread(new W_Thread()).start();
        System.out.println("finished");
    }

    private static class S_Thread implements Runnable {
        @Override
        public void run() {
            while (!down.get()) {
                synchronized (monitor) {
                    flag = !flag;
                    System.out.println(LocalDateTime.now() + "::: flag =" + flag);
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            Thread.currentThread().interrupt();
            System.out.println("S_Thread: interrupt");
        }
    }

    private static class W_Thread implements Runnable {

        @Override
        public void run() {
            while (count.get() != 30) {
                synchronized (monitor) {
                    if (flag) {
                        count.getAndIncrement();
                        System.out.println(LocalDateTime.now() + "::: Меняем count = " + count.get());
                    } else {
                        System.out.println(LocalDateTime.now() + "::: ждем flag = true");
                    }
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            Thread.currentThread().interrupt();
            System.out.println("W_Thread: interrupt");
            down.set(true);
        }
    }
}