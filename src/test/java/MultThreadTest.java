import me.alfod.ThreadUtils;
import org.junit.Test;

/**
 * @author Yang Dong
 * @createTime 2018/3/8  10:10
 * @lastUpdater Yang Dong
 * @lastUpdateTime 2018/3/8  10:10
 * @note
 */
public class MultThreadTest {
    @Test
    public void test() {
        ThreadUtils.run(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("test " + Thread.currentThread().getName());
            }
        }, 100, 0);
    }
}
