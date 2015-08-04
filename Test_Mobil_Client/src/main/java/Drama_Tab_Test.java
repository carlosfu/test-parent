import java.util.List;
import java.util.concurrent.CountDownLatch;

import com.alibaba.dubbo.remoting.TimeoutException;
import com.sohu.tv.mobil.api.DramaTabService;
import com.sohu.tv.mobil.api.DramaTabServiceFactory;
import com.sohu.tv.mobil.entity.BlogVideo;
import com.sohu.tv.mobil.entity.RequestParam;
import com.sohu.tv.mobil.exception.MobilRemoteException;


public class Drama_Tab_Test {
 
    private static CountDownLatch countDownLatch = new CountDownLatch(1);

    
    private static DramaTabService dramaTabService = DramaTabServiceFactory.getDramaTabService();
    
    public static void main(String[] args) throws TimeoutException, MobilRemoteException, InterruptedException {
        RequestParam rule = new RequestParam();
//        rule.setP("carlosfu@163.com");
//        rule.setY("14055679392943406453");
        rule.setPid("5475907");
//        5475907:1335781,5919520,112,10;1333523,5475908,101,10;1261358,5475908,101,10;1258452,5475908,101,10;1253050,5475908,101,10;
//        BlogVideo{vid='1333523', catecode='101', pid='5475908', tvid='null', type='vrs', rcAlgorithm='10', infoType='1'}
//        BlogVideo{vid='1261358', catecode='101', pid='5475908', tvid='null', type='vrs', rcAlgorithm='10', infoType='1'}
//        BlogVideo{vid='1253050', catecode='101', pid='5475908', tvid='null', type='vrs', rcAlgorithm='10', infoType='1'}
//        DramaTabValue:size=5,list=[
//        BlogVideo{vid='1335781', catecode='112', pid='5919520', tvid='null', type='vms', rcAlgorithm='10', infoType='0'},
//        BlogVideo{vid='1333523', catecode='101', pid='5475908', tvid='null', type='vrs', rcAlgorithm='10', infoType='1'},
//        BlogVideo{vid='1261358', catecode='101', pid='5475908', tvid='null', type='vrs', rcAlgorithm='10', infoType='1'}, 
//        BlogVideo{vid='1258452', catecode='101', pid='5475908', tvid='null', type='vrs', rcAlgorithm='10', infoType='1'},
//        BlogVideo{vid='1253050', catecode='101', pid='5475908', tvid='null', type='vrs', rcAlgorithm='10', infoType='1'}]
        
        List<BlogVideo> blogVideos = dramaTabService.recommend(rule);
        System.out.println(blogVideos.size());
        for(BlogVideo bv : blogVideos){
           System.out.println(bv); 
        }
//        BlogVideo{vid='1335781', catecode='112', pid='5919520', tvid='null', type='vms', rcAlgorithm='10', infoType='0'}
//        BlogVideo{vid='1333523', catecode='101', pid='5475908', tvid='null', type='vrs', rcAlgorithm='10', infoType='1'}
//        BlogVideo{vid='1333523', catecode='101', pid='5475908', tvid='null', type='vrs', rcAlgorithm='10', infoType='1'}
//        BlogVideo{vid='1333523', catecode='101', pid='5475908', tvid='null', type='vrs', rcAlgorithm='10', infoType='1'}
//        BlogVideo{vid='1333523', catecode='101', pid='5475908', tvid='null', type='vrs', rcAlgorithm='10', infoType='1'}
        System.out.println("=========finish=============");
        
        // 锁住，
        // 可以在http://10.10.52.132:8080/consumers.html?service=com.sohu.tv.mobil.api.HomeTvNewService观察是否成为消费者
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
