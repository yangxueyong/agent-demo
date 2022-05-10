
### 前言
- 该工程旨在为哪些造数十分麻烦且会影响开发进度的伙伴提供一个临时的解决方案，为各种方法提供造数能力
- 工程基于javaagent开发（javassist）
- 操作台基于 https://gitee.com/zhang-zhihan/SpringBoot_v2.git 开源脚手架项目搭建

### 项目介绍
  基于springboot的一款纯净脚手架。努力打造一款免费开源、注释全、文档全适合新手学习、方便快速二次开发的框架。
   

##### 1. 没有基础版、没有vip版本、没有付费群、没有收费二维码
##### 2. 遵循开源真谛，一切免费才是真开源
##### 3. 不求回报，你使用快乐就是这个项目最大的快乐！



### 组织架构

```
agent-demo
├─sql  项目SQL语句以及文档
|
│─agent-core agent核心模块
|
|─agent-test agent测试类
|
├─agent-web 操作台
│  
└─pom.xml   maven.xml
```

### 部署流程
1. 导入sql文件夹里面的springbootv2.sql到数据库
2. 修改agent-web工程中application-dev.yml指向自己的数据库地址
3. 启动agent-web工程run SpringbootStart.java
4. 打包agent-core，获取agent-core-1.0-SNAPSHOT-jar-with-dependencies.jar的物理路径
5. 启动agent-test工程run AgentTestApplication

### 验证过程
1. 关闭agent-test工程，然后设定agent-test的vm参数
```
   -javaagent:/Users/yxy/work/java/agent/agent-demo/agent-core/target/agent-core-1.0-SNAPSHOT-jar-with-dependencies.jar={\"className\":\"com.yxy.agent.controller\",\"codeHref\":\"http://127.0.0.1:8080/CreateDataExternalController/findParam2\",\"systemCode\":\"pft\"}
```
2. 打开postmain，调用接口
```
POST http://127.0.0.1:8181/test/v2/getUser
{
  "body":{ 
    "idCardSuffix":"yyy",
    "phoneCode":"kkk",
    "openIdCode":"yy"
  },
  "header":{
    "apiVersion":"1.0",
    "msgSeq":"1112",
    "msgTime":"2021-08-19 19:50:23", 
    "txChnlNo":"000"
  }
}
```
3. 返回值应该是
```
{
    "code": 400,
    "success": false,
    "data": null,
    "msg": "非法的phoneCode"
}
```
4. 然后打开浏览器，输入地址 http://localhost:8080/admin/index，用户名密码为 create_data_user / create_data_user 
5. 

