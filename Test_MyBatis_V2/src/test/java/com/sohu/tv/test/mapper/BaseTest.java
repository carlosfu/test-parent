package com.sohu.tv.test.mapper;
import java.io.IOException;
import java.io.Reader;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.BeforeClass;
/**
 * mybatis测试基类
 * 
 * @author leifu
 * @Date 2015年7月28日
 * @Time 上午10:59:03
 */
public class BaseTest {
    protected static SqlSessionFactory sessionFactory;
    /**
     * mybatis基础配置
     */
    protected final static String MYBATIS_CONF = "mybatis-base.xml";
    @BeforeClass
    public static void setUp() throws IOException {
        Reader reader = Resources.getResourceAsReader(MYBATIS_CONF);
        sessionFactory = new SqlSessionFactoryBuilder().build(reader);
    }
}