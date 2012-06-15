#!/bin/bash


function deploy_template() {
  echo "run app-deploy.sh deploy_template target = $TARGET  and  webapp_home=$WEBAPP_HOME"
  deploy  $TARGET/web-deploy.jar $WEBAPP_HOME
  chmod 755 $WEBAPP_HOME/bin/*
}

function deploy_task() {
  echo "run app-deploy.sh  deploy_task  target = $TARGET and task_home=$TASK_HOME"
  mkdir -p $TASK_HOME 
  deploy $TARGET/corner.task.zhe-uber.jar $TASK_HOME
  chmod 755 $TASK_HOME/bin/*
}

function deploy_war() {
	
  # echo "run app-deploy.sh deploy_war target = $TARGET  and  webapp_home=$WEBAPP_HOME"
  
  if [ $PRODUCTION == true ]; then
	echo "COPY  $TARGET/corner.bundle.war-1.0-SNAPSHOT.war to $WEBAPP_HOME/web.war "
    mkdir -p $OUTPUT_HOME
    cp  $TARGET/corner.bundle.war-1.0-SNAPSHOT.war $WEBAPP_HOME/web.war
  else
	echo "COPY  $TARGET/corner.bundle.war-1.0-SNAPSHOT.war to $WEBAPP_HOME/.. "
    mkdir -p $WEBAPP_HOME
    cp  $TARGET/corner.bundle.war-1.0-SNAPSHOT.war $WEBAPP_HOME/..
  fi
}
