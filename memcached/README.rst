memcachedȱ����֤�Լ���ȫ���ƣ������Ӧ�ý�memcached�����������ڷ���ǽ��
memcached��APIʹ����ʮ��λԪ��ѭ������У�飨CRC-32�������ֵ�󣬽����Ϸ�ɢ�ڲ�ͬ�Ļ����ϡ�
����������Ժ󣬽��������������ϻ���LRU�����滻����
����memcachedͨ��ֻ�ǵ�����ȡϵͳʹ�ã�����ʹ��memcached��Ӧ�ó�ʽ��д�ؽ�����ϵͳʱ�����Ǻ�˵����ݿ⣩��Ҫ����ĳ�ʽ�����memcached�ڵ�����

memcachedû�����÷ֲ�ʽ���ܣ��޷�ʵ��ʹ�ö�̨Memcache���������洢��ͬ�����ݣ����̶ȵ�ʹ����ͬ����Դ���޷�ͬ�����ݣ�������ɵ�����ϡ���memagent����ʵ�ּ�Ⱥ��
�� Memcached�п��Ա����item��������û�����Ƶģ�ֻҪ�ڴ��㹻 ��
Memcached���������ʹ���ڴ�Ϊ2G��Ҫʹ�ø����ڴ棬���Էֶ���˿ڿ������Memcached���� 

memcached�Ǽ�ֵһһ��Ӧ��keyĬ������ܳ���128���� �ڣ�valueĬ�ϴ�С��1M��Ҳ����һ��slabs��
���Ҫ��2M��ֵ�������ģ�������������slabs����Ϊ����slabs���������ģ��޷����ڴ��� �洢������Ҫ�޸�slabs�Ĵ�С��
���key��value���д洢ʱ����ʹ���slabsû�������꣬��ôҲ�����ű�����ݡ�

Memcached�������������׶ι�ϣ��two-stage hash��

���ֿͻ�����memcached�����ݵĴ洢��ʽ�ǲ�ͬ�ģ�perl Storable, php serialize, java hibernate, JSON�ȣ���һЩ�ͻ���ʵ�ֵĹ�ϣ�㷨Ҳ��һ�������ǣ�memcached�������˵���Ϊ����һ�µġ�
Memcached���ĺô������������˼��ѵ�ˮƽ����չ�ԣ��ر�����һ���޴��ϵͳ�С����ڿͻ����Լ�����һ�ι�ϣ����ô���Ǻ��������Ӵ���memcached����Ⱥ�С�
memcached֮��û���໥ͨ�ţ���˲������� memcached�ĸ��أ�
û�жಥЭ�飬��������ͨ������ը��implode����
memcached�ļ�Ⱥ�ܺ��á�
�ڴ治���ˣ����Ӽ�̨memcached�ɣ�
CPU�������ˣ������Ӽ�̨�ɣ�
�ж�����ڴ棿�����Ӽ�̨�ɣ���Ҫ�˷��ˡ�

Memcached��Ҫ��cache������LRU����������ã��㷨+��ʱʧЧ
���memcached���ڴ治�����ˣ����ڵ�slabs�����ȱ��滻�����ž��ֵ����ϵ�δ��ʹ�õ�slabs��


memcached��centos7�°�װ
memcached_installation_centos7.sh



memcached�Ļ�������
memcached_commands.sh



memcached+magentʵ��memcached��Ⱥ
magent  �������˵����
1.-h this message   
2.-u uid   
3.-g gid   
4.-p port, default is 11211. (0 to disable tcp support)   
5.-s ip:port, set memcached server ip and port   
6.-b ip:port, set backup memcached server ip and port   
7.-l ip, local bind ip address, default is 0.0.0.0  
8.-n number, set max connections, default is 4096  
9.-D do not go to background   
10.-k use ketama key allocation algorithm   
11.-f file, unix socket path to listen on. default is off   
12.-i number, max keep alive connections for one memcached server, default is 20  
13.-v verbose 
����magent֮��, ��س����Ƿ���Ҫ�䶯???
	Ӧ�ò���Ҫ��magent��ʵҲ�൱��һ��memcached server��ֻ��ip�Ͷ˿ںŵ��������



memcached java
�Ƽ�ʹ�ã�xmemcached



memcached python




������DB��ͬ��
�Ƚϱ��յ������ǣ���ѯ��ʱ��ӻ�����ȡ��add��updae��delete��ʱ��ͬʱ����������DB��
��Ȼ��Ҳ���Զ�ʱͬ��������DB�����ݣ�������Ϊ��ͬ��ҵ��Ӧ���в�ͬ��ѡ��
����ʵ�ʵ�Ӧ������ͬʱʹ�������ַ�ʽ�������û�������Ϣ֮������ݣ����ö�ʱͬ���ķ�ʽ��



# �Ƚ� MySQL��query cache



# �Ƚ� local cache



