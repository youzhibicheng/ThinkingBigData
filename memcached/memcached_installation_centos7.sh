#!/bin/bash

# libevent 是安装 memcached 的唯一前提条件。它是 memcached 所依赖的异步事件通知库
yum -y install libevent-devel


# install
# wget http://memcached.org/latest
# tar -zxvf memcached-1.x.x.tar.gz
# cd memcached-1.x.x
# ./configure && make && make test && sudo make install
yum -y install memcached
yum -y install memcached-devel
yum -y install python-memcached

# start
# 以守护程序的形式启动 memcached（-d），为其分配 2GB 内存（-m 2048），并指定监听 localhost，即端口 11211
# memcached -d -m 2048 -l 127.0.0.1 -p 11211
# 设置自动运行, 可以看出, 默认端口也是 11211
systemctl enable memcached.service
systemctl start memcached.service
netstat -tulnp | grep memcached

# 设置防火墙
# disable /etc/selinux/configure
systemctl stop firewalld.service
systemctl disable firewalld.service
# 或者
#firewall-cmd --zone=public --add-port=11211/tcp --permanent
#firewall-cmd --zone=public --add-port=11211/udp --permanent 
#firewall-cmd --reload

# 安装python环境
yum -y install python python-devel

# 安装java环境
yum -y install java-1.8.0-openjdk*

# telnet
telnet 127.0.0.1 11211