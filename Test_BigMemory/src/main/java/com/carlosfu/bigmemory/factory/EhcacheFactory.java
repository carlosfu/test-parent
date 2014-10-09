package com.carlosfu.bigmemory.factory;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.management.ManagementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.management.MBeanServer;

import java.io.InputStream;
import java.lang.management.ManagementFactory;

/**
 * ehcache工厂类
 * 
 * @author leifu(original: mobil)
 * @Time 2014-10-8
 */
public class EhcacheFactory {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * 配置路径
	 */
	private String ehcacheXmlPath;

	/**
	 * 本地堆外缓存名
	 */
	private String offheapCacheName;

	/**
	 * 本地堆缓存名
	 */
	private String heapCacheName;

	/**
	 * cache管理，真正的工厂
	 */
	private CacheManager cacheManager;

	public void init() {
		InputStream inputStream;
		inputStream = EhcacheFactory.class.getClassLoader()
				.getResourceAsStream(ehcacheXmlPath);
		cacheManager = CacheManager.newInstance(inputStream);
		MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
		ManagementService.registerMBeans(cacheManager, mBeanServer, false,
				true, true, true);
	}

	/**
	 * 获取本地堆缓存
	 * 
	 * @return
	 */
	public Ehcache getHeapEhcache() {
		return cacheManager.getEhcache(heapCacheName);
	}

	/**
	 * 获取本地堆外缓存
	 * 
	 * @return
	 */
	public Ehcache getOffHeapEhcache() {
		return cacheManager.getEhcache(offheapCacheName);
	}

	public void setEhcacheXmlPath(String ehcacheXmlPath) {
		this.ehcacheXmlPath = ehcacheXmlPath;
	}

	public void setHeapCacheName(String heapCacheName) {
		this.heapCacheName = heapCacheName;
	}

	public void setOffheapCacheName(String offheapCacheName) {
		this.offheapCacheName = offheapCacheName;
	}

}
