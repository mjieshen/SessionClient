# SessionClient
**项目设计**
1.	核心功能介绍:  
1）SessionManager类包含一个map和一个线程池，map用于保存所有的Session，线程池用于异步提交Session。   
2）DefaultSessionProcessor类用于具体处理，包括start请求，以及按照sessionExpireTime执行的stop请求。
2.	类图  
![uml](https://github.com/mjieshen/SessionClient/tree/master/src/main/resources/uml-class.jpg)
3.	流程图

**项目部署**
1.	代码:  
客户端：SessionManager.java  
服务端：src/main/python/LTEServer.py

2.	运行:  
1）通过LTEServer.py启动服务端；  
2）调用SessionManager类的createSession()方法请求开启会话。

**测试用例**
1.	代码覆盖率：70%。
