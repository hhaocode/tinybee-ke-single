#!/bin/sh
# java env
# app config
APP=tinybee-ke-admin
APP_DIR=/usr/local/app/server/tinybee-ke-admin/
APP_NAME=$APP
JAR_FILE_NAME=$APP_NAME\.jar
PID=$APP_NAME\.pid
LOG_DIR=$APP_DIR/logs
LOG_FILE=$LOG_DIR/$APP\.log
# REPOSITORY=
# 创建app的目录
if [ ! -d $APP_DIR ]; then
     mkdir -p $APP_DIR
fi
# 创建日志目录
if [ ! -d $LOG_DIR ]; then
     mkdir -p $LOG_DIR
fi
cd $APP_DIR
# 杀掉进程
kill `cat $APP_DIR/$PID`
rm -rf $APP_DIR/$PID
echo "======= stop $APP_NAME ========"
sleep 5
P_ID=`ps -ef | grep -w "$APP_NAME" | grep -v "grep" | awk '{print $2}'`
if [ "$P_ID" == "" ]; then
  echo "=== $APP_NAME process not exists or stop success"
else
  echo "=== $APP_NAME process pid is:$P_ID"
  echo "=== begin kill $APP_NAME process, pid is:$P_ID"
  kill -9 $P_ID
fi
nohup java -jar -Dspring.profiles.active=prod $JAR_FILE_NAME >$LOG_FILE 2>&1 &
echo $! > $APP_DIR/$PID
echo "============================ start $APP_NAME=== 开始启动========================"
tail -f $LOG_FILE
exit 0

