import java.util.List;
import java.util.concurrent.CountDownLatch;

import com.alibaba.dubbo.remoting.TimeoutException;
import com.sohu.tv.mobil.api.HomeTvNewService;
import com.sohu.tv.mobil.api.HomeTvNewServiceFactory;
import com.sohu.tv.mobil.entity.BlogVideo;
import com.sohu.tv.mobil.entity.RequestParam;
import com.sohu.tv.mobil.exception.MobilRemoteException;


public class Mobil_HomeNewTv_Test {

    private static CountDownLatch countDownLatch = new CountDownLatch(1);

    private static HomeTvNewService homeTvNewService = HomeTvNewServiceFactory.getHomeTvNewService();
    
//    private static WeMediaHomeService weMediaHomeService = WeMediaIndexServiceFactory.getWeMediaIndexService();

    
    public static void main(String[] args) throws TimeoutException, MobilRemoteException, InterruptedException {
        RequestParam rule = new RequestParam();
        rule.setP("carlosfu@163.com");
        rule.setY("14055679392943406453");
        
        List<BlogVideo> blogVideos = homeTvNewService.recommend(rule);
//        List<BlogVideo> blogVideos = weMediaHomeService.recommend(rule);
        for(BlogVideo bv : blogVideos){
           System.out.println(bv); 
        }
        
        // 锁住，
        // 可以在http://10.10.52.132:8080/consumers.html?service=com.sohu.tv.mobil.api.HomeTvNewService观察是否成为消费者
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
