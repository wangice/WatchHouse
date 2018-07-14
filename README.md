# WatchHouse
### 使用技术
  * 程序需要的运行环境：  
  mysql,zookeeper,kafka,elaticsearch
  
  * 程序使用的技术：
  SpringBoot,SpringCloud,Mybatis,kafka,elasticsearch,mysql
  
  
### 项目分析  
  * house-server  netflix-eureka-server用于服务发现
  * house-app  提供接口服务，netflix-eureka-client注册服务。  
    * house-app下的provider，用于提供数据库和service层服务提供  
    * house-app下的web,用于接口的提供
  * component组件，提供基础服务   
    * common包提供基础信息
    * common-misc提供公共类
    * es-client包提供elaticsearch客户端
    * mqconsumer包提供mq消息中间件消费消息
    * mqprovider包提供mq消息中间件发送消息
    
 
 
### 项目总结

* 包引用冲突  
  在项目过程中会遇到包的冲突，如何解决包的冲突，在idea中找到包依赖图,在其中去除掉那些红线的包，在项目启动过程
  中报类找到不到，但是明显可以搜索的到，那么就是包引用重复，如果解决，在maven project下查看依赖的jar包是否
  引用相似的包。
* 
 
 
  
    
   
  
  
  
  
     
  
   
 
 
