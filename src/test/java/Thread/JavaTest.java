package Thread;

import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Yang Dong
 * @createTime 2018/3/14  10:31
 * @lastUpdater Yang Dong
 * @lastUpdateTime 2018/3/14  10:31
 * @note
 */
public class JavaTest {

    @Test
    public void LinkedHashMapTest() {
        LinkedHashMap<String, Integer> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("dfd", 1);
        linkedHashMap.put("dfds", 2);
        linkedHashMap.put("sasdf", 3);
        for (Map.Entry entry : linkedHashMap.entrySet()) {
            System.out.println(entry.getValue());
        }
    }
}
