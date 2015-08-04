import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.io.FileUtils;

import com.sohu.rank.vrs.dubbo.UgcVideoServiceFactory;
import com.sohu.rank.vrs.model.DataObject;
import com.sohu.rank.vrs.model.DataStatusEnum;
import com.sohu.rank.vrs.service.IUgcVideoService;

public class WeMedia_Hot_170_Test {

    private static IUgcVideoService ugcService = UgcVideoServiceFactory.getUgcVideoService();

    public static void main(String[] args) throws IOException {
        List<String> lst = FileUtils.readLines(new File("hotwemedia.txt"));
        for(String vid : lst){
            showVideoInfo(Long.parseLong(vid));
        }
    }

    private static void showVideoInfo(long vid) {
     // 获取专辑信息
        DataObject<HashMap<String, Object>> dataObject = ugcService.getUgcVideoByVid(vid);
        System.out.println("dataObject status: " + dataObject.getStatus());
        // dataObject.getStatus()也变成枚举比较好
        if (dataObject != null && DataStatusEnum.OK.getValue() == dataObject.getStatus()) {
            // 结果存在一个map里面, 例如专辑名、图片等等
            Map<String, Object> map = (Map<String, Object>) dataObject.getData();
            for (Entry<String, Object> entry : map.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
            System.out.println("======================================================");
        }
    }
}
