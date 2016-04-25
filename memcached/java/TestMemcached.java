public class TestMemCached {   
    @Before
    public void init() {
        /*初始化SockIOPool，管理memcached的连接池*/  
        String[] servers = { "192.168.80.11:11211" };  
        SockIOPool pool = SockIOPool.getInstance();  
        pool.setServers(servers);  
        pool.setFailover(true);  
        pool.setInitConn(10);  
        pool.setMinConn(5);  
        pool.setMaxConn(250);  
        pool.setMaintSleep(30);  
        pool.setNagle(false);  
        pool.setSocketTO(3000);  
        pool.setAliveCheck(true);  
        pool.initialize();
    }

    @Test
    public void test() {
        /*建立MemcachedClient实例*/  
        MemCachedClient memCachedClient = new MemCachedClient();  
        for (int i = 0; i < 3; i++) {  
            /*将对象加入到memcached缓存*/  
            boolean success = memCachedClient.set("" + i, "Hello!" + i);  
            /*从memcached缓存中按key值取对象*/  
            String result = (String) memCachedClient.get("" + i);  
            System.out.println("第" + (i + 1) + "条" + (success ? "插入成功！" : "插入失败！"));  
            System.out.println("获取的值是：" + result);  
        }  
    }
    
    @Test
    public void testSet() {
        MemCachedClient memCachedClient = new MemCachedClient();
        System.out.println(memCachedClient.set("test", "heheda!", new Date(new Date().getTime() + 10000))); // 设置10秒超时 
    }
    
    @Test
    public void testGet() {
        MemCachedClient memCachedClient = new MemCachedClient();
        System.out.println(memCachedClient.get("test"));
    }

}