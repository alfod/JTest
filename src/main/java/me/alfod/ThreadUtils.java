package me.alfod;

import java.util.concurrent.CountDownLatch;

/**
 * @author Yang Dong
 * @createTime 2018/3/7  16:19
 * @lastUpdater Yang Dong
 * @lastUpdateTime 2018/3/7  16:19
 * @note
 */
public class ThreadUtils {
    private final Integer count = 10_000;

    public static void run(Runnable runnable, int count, double interval) {
        if (runnable == null) {
            throw new RuntimeException("runnable is null");
        }
        if (count < 1) {
            count = 1;
        }

        Thread[] threads = new Thread[count];
        InnerRunnable innerRunnable;
        CountDownLatch remainThread = new CountDownLatch(count);
        CountDownLatch successNumber = new CountDownLatch(count);
        for (int i = 0; i < count; i++) {
            innerRunnable = new InnerRunnable(remainThread, successNumber, runnable);
            threads[i] = new Thread(innerRunnable);
        }
        for (int i = 0; i < count; i++) {
            print("thread  " + i + " run ");
            threads[i].start();
        }
        try {
            remainThread.await();
        } catch (Exception e) {
            e.printStackTrace();
        }

        long failed = (count - successNumber.getCount());
        long success = successNumber.getCount();

        print("failed: " + failed);
        print("success: " + success);
        print("rate: " + (double) success / count);

    }

    private static void print(Object o) {
        System.out.println(o);
    }
}
