import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import net.sf.ehcache.config.CacheConfiguration;
import net.sf.ehcache.config.Configuration;
import net.sf.ehcache.config.MemoryUnit;


public class OnlyTest {
	public static void main(String[] args) {
		Configuration managerConfiguration = new Configuration();
		managerConfiguration.updateCheck(true)
		    .monitoring(Configuration.Monitoring.AUTODETECT)
		    .name("carlosfu_service_config")
		    .cache(new CacheConfiguration()
		        .name("carlosfuServiceOffHeapCache")
		        .maxBytesLocalHeap(16, MemoryUnit.MEGABYTES)
		        .maxBytesLocalOffHeap(32, MemoryUnit.MEGABYTES)
		    );

		CacheManager manager = CacheManager.create(managerConfiguration);
		Cache bigMemory = manager.getCache("carlosfuServiceOffHeapCache");
		
		Element element = new Element("a", "b");
		bigMemory.put(element);
		
		System.out.println(bigMemory.get("a"));
	}
}
