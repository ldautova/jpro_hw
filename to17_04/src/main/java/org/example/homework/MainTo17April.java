package org.example.homework;

/**
 * Задание 3 дедлайн 17.04
 * Попробуйте реализовать собственный пул потоков. В качестве аргументов конструктора пулу передается его емкость (количество рабочих потоков).
 * Как только пул создан, он сразу инициализирует и запускает потоки. Внутри пула очередь задач на исполнение организуется через LinkedList<Runnable>.
 * При выполнении у пула потоков метода execute(Runnabler), указанная задача должна попасть в очередь исполнения, и как только появится свободный
 * поток – должна быть выполнена.
 * Также необходимо реализовать метод shutdown(), после выполнения которого новые задачи больше не принимаются пулом
 * (при попытке добавить задачу можно бросать IllegalStateException), и все потоки для которых больше нет задач завершают свою работу.
 * Дополнительно можно добавить метод awaitTermination() без таймаута, работающий аналогично стандартным пулам потоков
 *
 * @author Lina_Dautova
 */
public class MainTo17April {

    public static void main(String[] args) {
        System.out.println("MainTo17April started");
        CustomThreadPool customThreadPool = new CustomThreadPool(4);
        for (int i = 0; i < 20; i++) {
            int finalI = i;
            customThreadPool.execute(() -> {
                System.out.println("Task " + finalI + " on " + Thread.currentThread().getName() + " was started");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                System.out.println("Task " + finalI + " on " + Thread.currentThread().getName() + " was ended");
            });
        }

        customThreadPool.shutdown();

        customThreadPool.awaitTermination();

        System.out.println("MainTo17April finished");
    }
}

