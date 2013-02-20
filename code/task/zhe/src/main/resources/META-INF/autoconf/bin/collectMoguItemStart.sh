#!/bin/bash
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
nohup $JAVA_HOME/bin/java  -Dapplication.codeset=GBK com.doucome.corner.task.zhe.spider.CollectMogu $1>> $OUTPUT_HOME/logs/sys/collectMogu_stdout.log 2>&1 &