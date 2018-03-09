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
                if (Math.random() > 0.4555) {
                    throw new RuntimeException("failed");
                }
                System.out.println("test " + Math.random());
            }
        }, 100, 0);
    }
}
