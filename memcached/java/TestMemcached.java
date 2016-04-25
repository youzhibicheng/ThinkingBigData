public class TestMemCached {   
    @Before
    public void init() {
        /*��ʼ��SockIOPool������memcached�����ӳ�*/  
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
        /*����MemcachedClientʵ��*/  
        MemCachedClient memCachedClient = new MemCachedClient();  
        for (int i = 0; i < 3; i++) {  
            /*��������뵽memcached����*/  
            boolean success = memCachedClient.set("" + i, "Hello!" + i);  
            /*��memcached�����а�keyֵȡ����*/  
            String result = (String) memCachedClient.get("" + i);  
            System.out.println("��" + (i + 1) + "��" + (success ? "����ɹ���" : "����ʧ�ܣ�"));  
            System.out.println("��ȡ��ֵ�ǣ�" + result);  
        }  
    }
    
    @Test
    public void testSet() {
        MemCachedClient memCachedClient = new MemCachedClient();
        System.out.println(memCachedClient.set("test", "heheda!", new Date(new Date().getTime() + 10000))); // ����10�볬ʱ 
    }
    
    @Test
    public void testGet() {
        MemCachedClient memCachedClient = new MemCachedClient();
        System.out.println(memCachedClient.get("test"));
    }

}