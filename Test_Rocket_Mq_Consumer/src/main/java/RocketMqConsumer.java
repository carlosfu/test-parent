import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import com.sohu.index.tv.mq.common.ConsumerExecutor;


public class RocketMqConsumer implements ConsumerExecutor{

    @Override
    public void execute(Map<String, Object> messageMap) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(sdf.format(new Date()) + ": ===================rocket mq topic: " + messageMap
                + "======================");
    }

}
