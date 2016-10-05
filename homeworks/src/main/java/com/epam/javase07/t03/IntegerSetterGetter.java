package com.epam.javase07.t03;


import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Перепишите код приложения для темы Wait, notify так, чтобы ситуация, когда все потоки хотят
 *прочитать из очереди, не могла возникнуть.
 *
 */
class IntegerSetterGetter extends Thread {

    private SharedResource resource;

    private boolean run;

    private static final Lock readLock=new ReentrantLock();
    private Random rand = new Random();

    public IntegerSetterGetter(String name, SharedResource resource) {

        super(name);

        this.resource = resource;

        run = true;

    }

    public void stopThread() {

        run = false;

    }

    public void run() {

        int action;
        try {

            while (run) {

                action = rand.nextInt(1000);

                if (action % 2 == 0 ) {
                    if(readLock.tryLock()) {
                        try {
                            getIntegersFromResource();
                        } finally {
                            readLock.unlock();
                        }
                    }
                    else{

                        System.out.println("Поток " + getName() + " не может совершить чтение. ");
                        continue;
                    }

                } else {

                    setIntegersIntoResource();

                }

            }

            System.out.println("Поток " + getName() + " завершил работу.");

        } catch (InterruptedException e) {
            e.printStackTrace();

        }

    }

    private void getIntegersFromResource() throws InterruptedException {

        Integer number;

        synchronized (resource) {

            System.out.println("Поток " + getName() + " хочет извлечь число.");

            number = resource.getElement();

            while (number == null) {

                System.out.println("Поток " + getName() + " ждет пока очередь заполнится.");

                resource.wait();

                System.out.println("Поток " + getName() + " возобновил работу.");

                number = resource.getElement();

            }

            System.out.println("Поток " + getName() + " извлек число " + number);

        }
    }

    private void setIntegersIntoResource() throws InterruptedException {

        Integer number = rand.nextInt(500);

        synchronized (resource) {

            resource.setElement(number);

            System.out.println("Поток " + getName() + " записал число " + number);

            resource.notify();

        }

    }
}
