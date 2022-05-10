## 无侵入式造数

### 前言
- 该工程旨在为哪些造数十分麻烦且会影响开发进度的伙伴提供一个临时的解决方案，为各种java方法提供造数能力
- 工程基于javassist开发（javaagent技术）
- 操作台基于 https://gitee.com/zhang-zhihan/SpringBoot_v2.git 开源脚手架项目搭建

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
1. 关闭agent-test工程，然后设定agent-test的vm参数，再重新启动
```
   -javaagent:/Users/yxy/work/java/agent/agent-demo/agent-core/target/agent-core-1.0-SNAPSHOT-jar-with-dependencies.jar={\"className\":\"com.yxy.agent.controller\",\"codeHref\":\"http://127.0.0.1:8080/CreateDataExternalController/findParam2\",\"systemCode\":\"pft\"}
   其中 className 指向的地址表示agent-test工程中要造数的类路径，多个路径以英文逗号隔开
   codeHref 指向agent-web工程的地址
   systemCode 要与agent-web中菜单配置的systemCode保持一致（默认为pft）
```

2. 打开postman，调用接口
```
POST http://127.0.0.1:8181/test/v2/getUser2
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
   
5. 在页面中可以看到如下信息
![img1](https://raw.githubusercontent.com/yangxueyong/agent-demo/main/image/img.png "img1.jpg")
- 接口名称 显示的就是刚才 第二步中的接口

6. 然后点击右侧按钮"编辑"，将状态改为"启用"，然后点击"查看挡板"
![img1](https://raw.githubusercontent.com/yangxueyong/agent-demo/main/image/img_1.png "img1.jpg")
- 点击新增

7. 输入 入参和出参，点击"提交"
![img1](https://raw.githubusercontent.com/yangxueyong/agent-demo/main/image/img_2.png "img1.jpg")
```
表示当传入的参数是
"idCardSuffix":"yyy"

则返回值为
{
    "data": {
        "wxNickName": "王五",
        "wxOpenId": "yyyyyy",
        "phoneNum": "158000000000",
        "userName": "yyyyyy",
        "userId": "xxxxxxx",
        "wxHeadImg": "yyyyyy"
    },
    "code": 200,
    "success": true,
    "msg": "成功"
}
```
8. 然后重复第二步，再次调用postman
![img1](https://raw.githubusercontent.com/yangxueyong/agent-demo/main/image/img_3.png "img1.jpg")
- 可以看到返回值已经变成我们设定的返回值了
