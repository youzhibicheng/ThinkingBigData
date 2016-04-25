#!/bin/bash

####################################
####      basic command         ####
####################################
# set
# command <key> <flags> <expiration time> <bytes> 
# <value>
# ע�⣬value �洢��ֵ��ʼ��λ�ڵڶ��У�
# # ������ζ��洢��set
set userId 0 0 5
12345
# STORED

# add
# command <key> <flags> <expiration time> <bytes>
# <value>
# ���������в����ڼ�ʱ��add ����Ż��򻺴������һ����ֵ�ԡ�����������Ѿ����ڼ�����֮ǰ��ֵ����Ȼ������ͬ���������������Ӧ NOT_STORED
# ֻ�����ݲ�����ʱ������ӵ�add
# ��ע�������bytes�ֶ�
add userId 0 0 5
55555
# NOT_STORED
add companyId 0 0 3
564
# STORED

# replace
# command <key> <flags> <expiration time> <bytes>
# <value>
# �������Ѿ�����ʱ��replace ����Ż��滻�����еļ�����������в����ڼ�����ô������ memcached ���������ܵ�һ�� NOT_STORED ��Ӧ
# ֻ�����ݴ���ʱ�����滻��replace
replace accountId 0 0 5
67890
# NOT_STORED
set accountId 0 0 5
67890
# STORED
replace accountId 0 0 5
55555
# STORED

# get
# command <key>
# ʹ��һ���������� get���������������ڻ����У��򷵻���Ӧ��ֵ����������ڣ��򲻷����κ����ݡ�
get userId
# VALUE userId 0 5
# 12345
# END
get bob
# END

# delete
# command <key>
# delete ��������ɾ�� memcached �е��κ�����ֵ������ʹ��һ�������� delete������ü������ڻ����У���ɾ����ֵ����������ڣ��򷵻�һ�� NOT_FOUND ��Ϣ��
delete bob
# NOT_FOUND
delete userId
# DELETED
get userId
# END

# append
# �����еĻ������ݺ���ӻ������ݣ������л����key�����ڷ�������ӦΪNOT_STORED
# ���value(GraceLi)֮ǰһ��Ҫ��һ���ո񣬷��򱨴�
append userName 0 0 8
 GraceLi
# STORED

# prepend
# ��append�ǳ����ƣ������������������еĻ�������ǰ��ӻ������ݡ�
# ���value(MintsZou)֮ǰһ�������пո����
prepend userName 0 0 8
MintsZou

# incr
# decr


####################################
###       expert command         ###
####################################
# gets
# gets ����Ĺ��������ڻ����� get �����������֮��Ĳ������ڣ�gets ���ص���Ϣ��΢��һЩ��64 λ������ֵ�ǳ�������/ֵ�Ե� ���汾�� ��ʶ����
set userId 0 0 5
12345
# STORED

get userId
# VALUE userId 0 5
# 12345
# END

gets userId
# VALUE userId 0 5 4
# 12345
# END
# gets �������һ�������ֵ �� �ڱ�����������ֵ 4�����ڱ�ʶ����/ֵ�ԡ�����Դ�����/ֵ��ִ����һ�� set ����� gets ���صĶ���ֵ���ᷢ�����ģ��Ա�������/ֵ���Ѿ������¡�
# gets�������ͨ��get����෵����һ�����֡�������ֿ��Լ�������Ƿ����ı䡣��key��Ӧ�����ݸı�ʱ������෵�ص�����Ҳ��ı䡣

set userId 0 0 5
33333
# STORED

gets userId
VALUE userId 0 5 5
33333
# END


# cas
# cas��check and set����һ���ǳ���ݵ� memcached ���������������/ֵ�Ե�ֵ�����������/ֵ�������ϴ�ִ�� gets ��û�и��¹�����
# ��ʹ���� set ���������Ƶ��﷨��������һ�������ֵ��gets ���صĶ���ֵ��
# �ӱ�����˵��ͬʱʹ�� gets �� cas ������Է�ֹ��ʹ�����ϴζ�ȡ�󾭹����µ�����/ֵ�ԡ�
# cas��checked and set����˼��ֻ�е����һ��������gets����ȡ�Ĳ���ƥ��ʱ���ܴ洢�����򷵻ء�EXISTS����
set userId 0 0 5
55555
# STORED

gets userId
# VALUE userId 0 5 6
# 55555
# END

cas userId 0 0 5 5
33333
# EXIST

cas userId 0 0 5 6
33333
# STORED


####################################
###       manage command         ###
####################################
# stats �� flush_all ���
# ִ�� stats ������ʾ�˹��ڵ�ǰ memcached ʵ������Ϣ
stats
# ִ��stats items�����Կ���STAT items�У����memcached�洢���ݺܶ࣬��ô����Ҳ���г��ܶ��STAT items�С�
stats items
# STAT items:1:number 2
# STAT items:1:age 2173
# STAT items:1:evicted 0
# STAT items:1:evicted_nonzero 0
# STAT items:1:evicted_time 0
# STAT items:1:outofmemory 0
# STAT items:1:tailrepairs 0
# STAT items:1:reclaimed 1
# STAT items:1:expired_unfetched 1
# STAT items:1:evicted_unfetched 0
END
# stats cachedump slab_id limit_num
# ����slab_idΪ1������tats items���صĽ����STAT items��������֣������ģ�
# limit_num�����������Ƿ��ض�������¼���µ�һ�㲻�� ����0��ʾ��ʾ�����м�¼����n(n>0)�ͱ�ʾ��ʾn����¼�����n������slab�µ����м�¼��������0���صĽ��һ�¡�
stats cachedump 1 0
# ITEM userName [8 b; 1450070639 s]
# ITEM accountId [5 b; 1450070639 s]
# END
# ͨ��stats items��stats cachedump slab_id limit_num���get������Ա���memcached�ļ�¼
stats slabs
stats sizes
stats reset

# flush all
# �����򵥵�����������������е���������/ֵ�ԡ��������Ҫ���������õ��ɾ���״̬���� flush_all ���ṩ�ܴ���ô���

# statsָ��
# get_hits		�ҵ�����/ֵ�ԵĴ�����get_hits��
# get_misses	δ�ҵ�����/ֵ�ԵĴ�����get_misses��
# cmd_gets		get����ִ�еĴ���
# ���������ʱ�ʾִ�� get �Ĵ������� get �Ĵ����İٷֱ� git_hits/cmd_gets


