package me.alfod;

/**
 * @author Yang Dong
 * @createTime 2018/3/9  15:13
 * @lastUpdater Yang Dong
 * @lastUpdateTime 2018/3/9  15:13
 * @note
 */
public class Threader {
    private Integer times;
    private Runnable runnable;

    public Threader(Integer times, Runnable runnable) {
        this.times = times;
        this.runnable = runnable;
    }

    public Threader(Runnable runnable) {
        this.runnable = runnable;
    }

    public Threader(Integer times) {
        this.times = times;
    }

    public void run() {
        ThreadUtils.run(runnable, times, 0);
    }

    public Integer getTimes() {
        return times;
    }

    public void setTimes(Integer times) {
        this.times = times;
    }

    public Runnable getRunnable() {
        return runnable;
    }

    public void setRunnable(Runnable runnable) {
        this.runnable = runnable;
    }
}
