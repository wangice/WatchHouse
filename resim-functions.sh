
app_User=ctf-app
App_Group=app

if [ -n "$2" ]; then
     PROFILE=$2
     echo "Set spring.profiles.active ${PROFILE} "
fi

#初始化Jar_Pid变量（全局）
Jar_Pid=0
#检测是否运行
checkPid() {
    pid=`ps -ef|grep ${App_Service_Name} |grep java|awk '{print $2}'`
   if [ -n "$pid" ]; then
      Jar_Pid=${pid}
   else
      Jar_Pid=0
   fi
}

#部署JAR
install(){
    #创建目录
    sudo mkdir -p ${App_Home} ${Log_Home}
    #赋予log目录权限
    sudo chmod o+w ${Log_Home}
    #复制App程序到App目录
    sudo cp ${JAR_File_Name} ${App_Home}/${JAR_File_Name}
    #加入系统服务
    echo "Add to System Service named ${App_Service_Name}"
    #卸载原有服务
    sudo chkconfig ${App_Service_Name} off
    sudo chkconfig --del ${App_Service_Name}
    sudo rm -rf /etc/rc.d/init.d/${App_Service_Name}

    #增加新服务
    echo "current dir:" `pwd`
    df | grep /opt
    if [  $? -eq 0 ]
    then
        echo opt 单独分区,自启动脚本拷贝到/etc/rc.d/init.d/${App_Service_Name}
        sudo cp ${Dir}/${JAR_Shell} /etc/rc.d/init.d/${App_Service_Name}
        sudo ln -s /etc/rc.d/init.d/${App_Service_Name}   ${App_Home}/${JAR_Shell}
    else
        echo opt 非单独分区,自启动脚本链接到/etc/rc.d/init.d/{App_Service_Name}
        sudo  cp ${Dir}/${JAR_Shell} ${App_Home}/${JAR_Shell}
        sudo ln -s ${App_Home}/${JAR_Shell} /etc/rc.d/init.d/${App_Service_Name}
    fi

    #设置开机启动
    echo "Set Boot Up Service when UbuntuServer System start up"
    sudo chkconfig ${App_Service_Name} on


	#create group if not exists
	egrep -w "^$App_Group" /etc/group >& /dev/null
	if [ $? -ne 0 ]
	then
		echo "Creating Application Group:"${App_Group}
		sudo groupadd -f ${App_Group}
	fi
	#create user if not exists
	egrep -w "^$app_User" /etc/passwd >& /dev/null
	if [ $? -ne 0 ]
	then
		echo "Creating Application User:"${app_User}
		sudo useradd -g ${App_Group} -d /home/${app_User} -m -s /bin/false -r ${app_User}
		#sudo -S passwd $app_User << EOF
#test
#test
#EOF
	fi

	#设置目录权限
	echo "Set The Permission For User: "${app_User}
	sudo chown -R ${app_User}:${App_Group} ${App_Base_Home}
	sudo chmod -R o+w ${App_Base_Home}

    echo "Install ${App_Service_Name} Success!"
}
#卸载，删除所有文件
uninstall(){
    echo "Uninstall Service ${App_Service_Name}"
    sudo chkconfig ${App_Service_Name} off
    sudo chkconfig --del ${App_Service_Name}
    sudo rm -rf /etc/rc.d/init.d/${App_Service_Name}
    echo "Remove app files and log files ... "
    sudo rm -rf ${App_Home} ${Log_Home}
    echo "Uninstall ${App_Service_Name} Complete!"
}
#启动
start() {
    checkPid
    if [[ ${Jar_Pid} -ne 0 ]]; then
      echo "================================================================"
      echo "INFO: ${App_Service_Name} already started!  pid=$Jar_Pid "
      echo "================================================================"
   else
      echo "|  Use spring.profiles.active ${Profile} "
      echo  "Starting ${App_Service_Name} with JVM arguments: ${JvmArgs}..."
      #su -l ${app_User} --shell=/bin/bash -c "nohup /opt/jdk1.8.0_144/jre/bin/java -Dspring.profiles.active=${Profile} -Dapp.log.home=${Log_Home} ${JvmArgs} -jar ${App_Home}/${JAR_File_Name} >${Log_Home}/start_console.log &"
      su -l ${app_User} --shell=/bin/bash -c "nohup /opt/jdk1.8.0_144/jre/bin/java -Dspring.profiles.active=${Profile} -Dapp.log.home=${Log_Home} ${JvmArgs} -jar ${App_Home}/${JAR_File_Name} > ${Log_Home}/start_console.log  >/dev/null 2>&1 &"
      #休眠等待,查看是否启动成功
      for((i=1;i<=${SleepTime};i++));
          do
            #休眠等待2s
            sleep 2s
            checkPid
            if [[ ${Jar_Pid} -ne 0 ]]; then
               echo "${App_Service_Name} Started Successfully in $[i*2]s, pid=$Jar_Pid"
               break
            else
               echo "Starting ${App_Service_Name} ..."
            fi
          done
   fi
}
#停止服务
stop() {
   checkPid
   if [[ ${Jar_Pid} -ne 0 ]]; then
      echo  "Stopping ${App_Service_Name}  ...   pid=${Jar_Pid}  "
      su -l ${app_User} --shell=/bin/bash -c "kill -15 ${Jar_Pid}"
      for((i=1;i<=${SleepTime};i++));
      do
	      #休眠等待2s
	      sleep 2s
	      checkPid
	      if [[ ${Jar_Pid} == 0 ]]; then
	         echo "${App_Service_Name} Stopped Successfully in $[i*2]s"
	         break
	      else
	         echo "Stopping ${App_Service_Name} ..."
	      fi
      done
   else
      echo "================================================================"
      echo "INFO: ${App_Service_Name} is not running"
      echo "================================================================"
   fi
}
restart(){
    echo  $"Restarting ${App_Service_Name}: "
    stop
    start
}
status() {
   checkPid
   if [[ ${Jar_Pid} -ne 0 ]];  then
      echo "${App_Service_Name} is running!  pid=${Jar_Pid} "
   else
      echo "${App_Service_Name} is not running"
   fi
}
#jar备份
backup(){
    if [ ! -d "$Backup_Home" ]; then
        sudo mkdir -p ${Backup_Home}
        sudo chown ${app_User}:${App_Group} ${Backup_Home}
        sudo chmod o+w ${Backup_Home}
    fi
    #利用时间戳,备份原文件
    timeMark=`date +%Y%m%d%H%M%S`
    BackUp_Dir=${Backup_Home}/${timeMark}
    #根据名称排序，取最新版本日期的jar包和sh文件.
    BackUp_JAR=`ls -r ${App_Home}/${App_File_Name}*.jar|sed -n '1p'`
    BackUp_Shell=`ls -r ${App_Home}/${App_File_Name}*.sh|sed -n '1p'`
    if [ ! -d "BackUp_Dir" ]; then
        sudo mkdir -p ${BackUp_Dir}
        sudo chown ${app_User}:${App_Group} ${BackUp_Dir}
        sudo chmod o+w ${BackUp_Dir}
        backJarName=${BackUp_Dir}/${JAR_File_Name}_${timeMark}
        backShellName=${BackUp_Dir}/${JAR_Shell}_${timeMark}
        echo "BackUp old file ${BackUp_JAR} to ${backJarName}"
        echo "BackUp old file ${BackUp_Shell} to ${backShellName}"
        su -l ${app_User} --shell=/bin/bash -c "mv ${BackUp_JAR}  ${backJarName}"
        su -l ${app_User} --shell=/bin/bash -c "mv ${BackUp_Shell}  ${backShellName}"
    else
        echo "The files has been backed up!"
    fi
}

#jar发布更新
publish(){
    stop
	backup
	uninstall
	install
	start
    echo "Publish Done !"
}

####################################################################################################################

startDocker() {
    echo -e "当前用户:" `whoami`
    echo -e "当前目录:"  `pwd`
    echo "startDocker..."
    sudo cp ${JAR_File_Name} ${App_Home}/${JAR_File_Name}
    #Base_Image_Src_File=${Dir}/docker_ubuntu16.04lts_jre8.tar
    #Base_Image=docker_ubuntu16.04lts_jre8:base
    App_Image=docker_ubuntu16.04lts_jre8:${App_Service_Name}
    App_Container=${App_Service_Name}

    # 停止容器并删除
    sudo docker rm  ${App_Container} -f

    sudo ps -ef | grep java | grep ${App_Service_Name} | awk '{system("sudo kill -9 " $2)}'

    # 删除镜像
    sudo docker rmi ${App_Image}  -f

    backup2

    # 创建镜像,各主机需要手动加载基础镜像：docker_ubuntu16.04lts_jre8:base
    docker build -t ${App_Image} .

    docker run  -idt --net=host  --name ${App_Container} --env=DUBBO_SERVER_PORT=${DUBBO_SERVER_PORT} --env=PROFILE=${PROFILE}  --env=Log_Home=${Log_Home} -v ${App_Base_Home}:${App_Base_Home} -v ${App_Home}:${App_Home} -v ${Log_Home}:${Log_Home} -v ${Backup_Home}:${Backup_Home}  ${App_Image} bash

    docker ps -a
    docker images
}

####################################################################################################################

case "$1" in
    start|sa)start ;;
    stop|sp)stop ;;
    restart|r)restart ;;
    status)status;;
    install|i)install;;
    publish|p)publish;;
    uninstall|u)uninstall;;
    backup|b)backup;;
    startDocker|sD)startDocker;;
    *)echo $"Usage: $0 {start|stop|restart|status|install|uninstall|publish|backup|startDocker}";;
esac


