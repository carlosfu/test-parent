import java.util.concurrent.TimeUnit;

import com.carlosfu.util.TestUtil;

/**
 * 测试引入同包 同类名 同方法名(方法内容不同)的实现(根据pom的引入顺序来决定)
 * 
 * @author leifu
 * @Time 2014年9月29日
 */
public class TestJarDuplicate {
    public static void main(String[] args) throws InterruptedException {
        while (true) {
            TestUtil.hello();
            TimeUnit.SECONDS.sleep(1);
        }
    }
}
