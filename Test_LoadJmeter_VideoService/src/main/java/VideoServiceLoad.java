import java.util.HashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;

import com.sohu.rank.vrs.dubbo.VrsVideoServiceFactory;
import com.sohu.rank.vrs.model.DataObject;
import com.sohu.rank.vrs.service.IVrsVideoService;

/**
 * videoService服务简单压力测试
 * 
 * @author leifu
 * @Time 2014年8月12日
 */
public class VideoServiceLoad extends AbstractJavaSamplerClient {
    /**
     * 服务：直接用工厂的模式调用
     */
    private IVrsVideoService videoService = VrsVideoServiceFactory.getVrsVideoService();

    /**
     * 定义label名称，显示在jmeter的结果窗口
     */
    private String label = "testVideoService";

    /**
     * 记错数，由于是多线程
     */
    private AtomicLong errorTimes = new AtomicLong();

    /**
     * 参数
     */
    public Arguments getDefaultParameters() {
        // 参数定义，显示在前台，也可以不定义
        Arguments params = new Arguments();
        params.addArgument("vid", "0");
        return params;
    }

    /**
     * 运行
     */
    public SampleResult runTest(JavaSamplerContext javaSamplerContext) {
        SampleResult sampleResult = new SampleResult();
        // 标签
        sampleResult.setSampleLabel(label);
        // 开始
        sampleResult.sampleStart();
        try {
            // 业务逻辑
            String vid = javaSamplerContext.getParameter("vid");
            System.out.println("=============vid: " + vid + "===========");
            DataObject<HashMap<String, Object>> videoInfo = videoService.getVrsVideoByVid(Long.parseLong(vid));
            if (videoInfo != null) {
                sampleResult.setSuccessful(true);
            } else {
                sampleResult.setSuccessful(false);
            }
//             System.out.println("videoInfo: " + videoInfo.getData());
        } catch (Exception e) {
            errorTimes.addAndGet(1);
            getLogger().info(e.getMessage(), e);
            e.printStackTrace();
        } finally {
            // 结束
            sampleResult.sampleEnd();
        }
        return sampleResult;
    }

    /**
     * 本地测试一次，看一下runTest是否通
     * 
     * @param args
     */
    public static void main(String[] args) {
        // 把测试接口类new一个对象
        VideoServiceLoad test = new VideoServiceLoad();
        Arguments params = new Arguments();
        params.addArgument("vid", "1452502");// 设置参数，并赋予默认值0
        JavaSamplerContext context = new JavaSamplerContext(params);
        // 初始值执行
        test.setupTest(context);
        // 执行部分调用
        test.runTest(context);
        // 执行结束处理
        test.teardownTest(context);
    }

    /**
     * 测试结束清理方法,全局调用一次
     */
    @Override
    public void teardownTest(JavaSamplerContext context) {
    }
}
