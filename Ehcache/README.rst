http://www.ehcache.org/

ehcache��һ���ǳ��������Ļ���ʵ�֣����Ҵ�1.2֮���֧���˼�Ⱥ��������hibernateĬ�ϵĻ���provider��
EhCache ��һ����Java�Ľ����ڻ����ܣ����п��١����ɵ��ص㣬��Hibernate��Ĭ�ϵ�CacheProvider

EhCacheһ����һ������, Memcached, redisһ��������������
һ������Ͷ�������֮����ν���������

��memcached����

Ehcache���Զ�ҳ�桢�������ݽ��л��棬ͬʱ֧�ּ�Ⱥ/�ֲ�ʽ����
EHCache֧���ڴ�ʹ��̵Ļ��棬֧��LRU��LFU��FIFO������̭�㷨

ҳ�滺��
	��Ҫ��Filter�������������url���й��ˣ������url�ڻ����г��֡���ôҳ�����ݾʹӻ�������л�ȡ������gzipѹ���󷵻ء�
	���ٶ���û��ѹ������ʱ�ٶȵ�3-5����Ч���൱֮�ߣ�����ҳ�滺��Ĺ�������CachingFilter��һ��Ҫ��չfilter�����Զ���Filter���̳и�CachingFilter��

���󻺴�
	

LRU(Least Recently Used)
	�������ʹ��ҳ���û��㷨
	������̭�ʱ��δ��ʹ�õ�ҳ��
LFU(Least Frequently Used)
	��������ҳ���û��㷨
	��̭һ��ʱ���ڱ����ʴ������ٵ�ҳ
	
