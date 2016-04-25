memcached缺乏认证以及安全管制，这代表应该将memcached服务器放置在防火墙后。
memcached的API使用三十二位元的循环冗余校验（CRC-32）计算键值后，将资料分散在不同的机器上。
当表格满了以后，接下来新增的资料会以LRU机制替换掉。
由于memcached通常只是当作快取系统使用，所以使用memcached的应用程式在写回较慢的系统时（像是后端的数据库）需要额外的程式码更新memcached内的资料

memcached没有内置分布式功能，无法实现使用多台Memcache服务器来存储不同的数据，最大程度的使用相同的资源；无法同步数据，容易造成单点故障。（memagent代理实现集群）
在 Memcached中可以保存的item数据量是没有限制的，只要内存足够 。
Memcached单进程最大使用内存为2G，要使用更多内存，可以分多个端口开启多个Memcached进程 

memcached是键值一一对应，key默认最大不能超过128个字 节，value默认大小是1M，也就是一个slabs，
如果要存2M的值（连续的），不能用两个slabs，因为两个slabs不是连续的，无法在内存中 存储，故需要修改slabs的大小，
多个key和value进行存储时，即使这个slabs没有利用完，那么也不会存放别的数据。

Memcached的神奇来自两阶段哈希（two-stage hash）

各种客户端在memcached中数据的存储形式是不同的（perl Storable, php serialize, java hibernate, JSON等）。一些客户端实现的哈希算法也不一样。但是，memcached服务器端的行为总是一致的。
Memcached最大的好处就是它带来了极佳的水平可扩展性，特别是在一个巨大的系统中。由于客户端自己做了一次哈希，那么我们很容易增加大量memcached到集群中。
memcached之间没有相互通信，因此不会增加 memcached的负载；
没有多播协议，不会网络通信量爆炸（implode）。
memcached的集群很好用。
内存不够了？增加几台memcached吧；
CPU不够用了？再增加几台吧；
有多余的内存？在增加几台吧，不要浪费了。

Memcached主要的cache机制是LRU（最近最少用）算法+超时失效
如果memcached的内存不够用了，过期的slabs会优先被替换，接着就轮到最老的未被使用的slabs。


memcached在centos7下安装
memcached_installation_centos7.sh



memcached的基本命令
memcached_commands.sh



memcached+magent实现memcached集群
magent  命令参数说明：
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
引入magent之后, 相关程序是否需要变动???
	应该不需要，magent其实也相当于一个memcached server，只是ip和端口号的区别而已



memcached java
推荐使用：xmemcached



memcached python




缓存与DB的同步
比较保险的做法是：查询的时候从缓存中取，add、updae、delete的时候同时操作缓存与DB。
当然你也可以定时同步缓存与DB的数据，个人认为不同的业务应该有不同的选择！
我在实际的应用中是同时使用这两种方式，比如用户个人信息之类的内容，就用定时同步的方式。



# 比较 MySQL的query cache



# 比较 local cache



