http://www.ehcache.org/

ehcache是一个非常轻量级的缓存实现，而且从1.2之后就支持了集群，而且是hibernate默认的缓存provider。
EhCache 是一个纯Java的进程内缓存框架，具有快速、精干等特点，是Hibernate中默认的CacheProvider

EhCache一般做一级缓存, Memcached, redis一般用作二级缓存
一级缓存和二级缓存之间如何交互？？？

与memcached集成

Ehcache可以对页面、对象、数据进行缓存，同时支持集群/分布式缓存
EHCache支持内存和磁盘的缓存，支持LRU、LFU和FIFO多种淘汰算法

页面缓存
	主要用Filter过滤器对请求的url进行过滤，如果该url在缓存中出现。那么页面数据就从缓存对象中获取，并以gzip压缩后返回。
	其速度是没有压缩缓存时速度的3-5倍，效率相当之高！其中页面缓存的过滤器有CachingFilter，一般要扩展filter或是自定义Filter都继承该CachingFilter。

对象缓存
	

LRU(Least Recently Used)
	最近最少使用页面置换算法
	首先淘汰最长时间未被使用的页面
LFU(Least Frequently Used)
	最近最不常用页面置换算法
	淘汰一定时期内被访问次数最少的页
	
