#!/bin/bash
. ~/.bash_profile
JAVA_HOME=${corner_javahome}
OUTPUT_HOME=${corner_output}

cd `dirname $0`/..
BASE=`pwd`
echo "BASE=$BASE"

#sh $BASE/bin/env.sh

LOCALCLASSPATH=`echo $BASE/WORLDS-INF/lib/*.jar | tr ' ' ':'`
export CLASSPATH=$LOCALCLASSPATH:$BASE/WORLDS-INF/classes
nohup $JAVA_HOME/bin/java  -Dapplication.codeset=GBK com.doucome.corner.task.zhe.starter.TaokeReportSyncTaskStarter $1>> $OUTPUT_HOME/logs/sys/taokeReportSync_stdout.log 2>&1 &