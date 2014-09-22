import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.CountDownLatch;

import com.sohu.rank.vrs.dubbo.UgcVideoServiceFactory;
import com.sohu.rank.vrs.dubbo.VrsVideoServiceFactory;
import com.sohu.rank.vrs.model.DataObject;
import com.sohu.rank.vrs.model.DataStatusEnum;
import com.sohu.rank.vrs.service.IUgcVideoService;
import com.sohu.rank.vrs.service.IVrsVideoService;

public class VideoService_Ugc_Test {

    private static Integer ugcVid = 73080319;

    private static CountDownLatch countDownLatch = new CountDownLatch(1);

    private static IUgcVideoService ugcService = UgcVideoServiceFactory.getUgcVideoService();

    public static void main(String[] args) {
        // 获取专辑信息
        DataObject<HashMap<String, Object>> dataObject = ugcService.getUgcVideoByVid(ugcVid);
        System.out.println("dataObject status: " + dataObject.getStatus());
        // dataObject.getStatus()也变成枚举比较好
        if (dataObject != null && DataStatusEnum.OK.getValue() == dataObject.getStatus()) {
            // 结果存在一个map里面, 例如专辑名、图片等等
            Map<String, Object> map = (Map<String, Object>) dataObject.getData();
            for (Entry<String, Object> entry : map.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
        }

        // 锁住，
        // 可以在http://10.10.52.132:8080/consumers.html?service=com.sohu.rank.vrs.service.IVrsVideoService观察是否成为消费者
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
