#!/bin/bash

#format:http://qlogo2.store.qq.com/qzone/983516937/983516937/100|||»¨»¨½´

. ~/.bash_profile
export LANG=zh_CN.GB18030
JAVA_HOME=${corner_javahome}
OUTPUT_HOME=${corner_output}

cd `dirname $0`/..
BASE=`pwd`
echo "BASE=$BASE"

#sh $BASE/bin/env.sh

LOCALCLASSPATH=`echo $BASE/WORLDS-INF/lib/*.jar | tr ' ' ':'`
export CLASSPATH=$LOCALCLASSPATH:$BASE/WORLDS-INF/classes
nohup $JAVA_HOME/bin/java  -Dapplication.codeset=GBK com.doucome.corner.task.zhe.spider.AddUser $1>> $OUTPUT_HOME/logs/sys/addUser_stdout.log 2>&1 &