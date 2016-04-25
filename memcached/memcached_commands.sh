#!/bin/bash

####################################
####      basic command         ####
####################################
# set
# command <key> <flags> <expiration time> <bytes> 
# <value>
# 注意，value 存储的值（始终位于第二行）
# # 无论如何都存储的set
set userId 0 0 5
12345
# STORED

# add
# command <key> <flags> <expiration time> <bytes>
# <value>
# 仅当缓存中不存在键时，add 命令才会向缓存中添加一个键值对。如果缓存中已经存在键，则之前的值将仍然保持相同，并且您将获得响应 NOT_STORED
# 只有数据不存在时进行添加的add
# 请注意这里的bytes字段
add userId 0 0 5
55555
# NOT_STORED
add companyId 0 0 3
564
# STORED

# replace
# command <key> <flags> <expiration time> <bytes>
# <value>
# 仅当键已经存在时，replace 命令才会替换缓存中的键。如果缓存中不存在键，那么您将从 memcached 服务器接受到一条 NOT_STORED 响应
# 只有数据存在时进行替换的replace
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
# 使用一个键来调用 get，如果这个键存在于缓存中，则返回相应的值。如果不存在，则不返回任何内容。
get userId
# VALUE userId 0 5
# 12345
# END
get bob
# END

# delete
# command <key>
# delete 命令用于删除 memcached 中的任何现有值。您将使用一个键调用 delete，如果该键存在于缓存中，则删除该值。如果不存在，则返回一条 NOT_FOUND 消息。
delete bob
# NOT_FOUND
delete userId
# DELETED
get userId
# END

# append
# 在现有的缓存数据后添加缓存数据，如现有缓存的key不存在服务器响应为NOT_STORED
# 这个value(GraceLi)之前一定要有一个空格，否则报错
append userName 0 0 8
 GraceLi
# STORED

# prepend
# 和append非常类似，但它的作用是在现有的缓存数据前添加缓存数据。
# 这个value(MintsZou)之前一定不能有空格，奇怪
prepend userName 0 0 8
MintsZou

# incr
# decr


####################################
###       expert command         ###
####################################
# gets
# gets 命令的功能类似于基本的 get 命令。两个命令之间的差异在于，gets 返回的信息稍微多一些：64 位的整型值非常像名称/值对的 “版本” 标识符。
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
# gets 命令将返回一个额外的值 — 在本例中是整型值 4，用于标识名称/值对。如果对此名称/值对执行另一个 set 命令，则 gets 返回的额外值将会发生更改，以表明名称/值对已经被更新。
# gets命令比普通的get命令多返回了一个数字。这个数字可以检查数据是否发生改变。当key对应的数据改变时，这个多返回的数字也会改变。

set userId 0 0 5
33333
# STORED

gets userId
VALUE userId 0 5 5
33333
# END


# cas
# cas（check and set）是一个非常便捷的 memcached 命令，用于设置名称/值对的值（如果该名称/值对在您上次执行 gets 后没有更新过）。
# 它使用与 set 命令相类似的语法，但包括一个额外的值：gets 返回的额外值。
# 从本质上说，同时使用 gets 和 cas 命令可以防止您使用自上次读取后经过更新的名称/值对。
# cas即checked and set的意思，只有当最后一个参数和gets所获取的参数匹配时才能存储，否则返回“EXISTS”。
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
# stats 和 flush_all 命令。
# 执行 stats 命令显示了关于当前 memcached 实例的信息
stats
# 执行stats items，可以看到STAT items行，如果memcached存储内容很多，那么这里也会列出很多的STAT items行。
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
# 这里slab_id为1，是由tats items返回的结果（STAT items后面的数字）决定的；
# limit_num看起来好像是返回多少条记录，猜的一点不错， 不过0表示显示出所有记录，而n(n>0)就表示显示n条记录，如果n超过该slab下的所有记录，则结果和0返回的结果一致。
stats cachedump 1 0
# ITEM userName [8 b; 1450070639 s]
# ITEM accountId [5 b; 1450070639 s]
# END
# 通过stats items、stats cachedump slab_id limit_num配合get命令可以遍历memcached的记录
stats slabs
stats sizes
stats reset

# flush all
# 这个最简单的命令仅用于清理缓存中的所有名称/值对。如果您需要将缓存重置到干净的状态，则 flush_all 能提供很大的用处。

# stats指标
# get_hits		找到名称/值对的次数（get_hits）
# get_misses	未找到名称/值对的次数（get_misses）
# cmd_gets		get命令执行的次数
# 缓存命中率表示执行 get 的次数与错过 get 的次数的百分比 git_hits/cmd_gets


