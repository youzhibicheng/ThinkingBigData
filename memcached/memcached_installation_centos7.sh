#!/bin/bash

# libevent �ǰ�װ memcached ��Ψһǰ������������ memcached ���������첽�¼�֪ͨ��
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
# ���ػ��������ʽ���� memcached��-d����Ϊ����� 2GB �ڴ棨-m 2048������ָ������ localhost�����˿� 11211
# memcached -d -m 2048 -l 127.0.0.1 -p 11211
# �����Զ�����, ���Կ���, Ĭ�϶˿�Ҳ�� 11211
systemctl enable memcached.service
systemctl start memcached.service
netstat -tulnp | grep memcached

# ���÷���ǽ
# disable /etc/selinux/configure
systemctl stop firewalld.service
systemctl disable firewalld.service
# ����
#firewall-cmd --zone=public --add-port=11211/tcp --permanent
#firewall-cmd --zone=public --add-port=11211/udp --permanent 
#firewall-cmd --reload

# ��װpython����
yum -y install python python-devel

# ��װjava����
yum -y install java-1.8.0-openjdk*

# telnet
telnet 127.0.0.1 11211