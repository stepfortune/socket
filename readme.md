# Lab409 物联网接入平台

### 1. 构建方法: 

1. git clone https://github.com/XLab-Tongji/IoTAccessPlatform.git
2. 在 resource 目录下找到 sql 文件, 用mysql新建一个数据库, 用 sql 文件导入表 
3. 修改 application.properties 的数据库 username 和 password 和 数据库名
4. netty server 的默认端口是8090, springboot webserver的默认端口是8082, 请确保这两个端口没有占用
5. 用IDE运行这个项目
6. 命令行进入 front 文件夹, 运行命令 npm install安装依赖, npm run dev运行前端项目
7. 需安装mysql、rabbitmq以及redis数据库



### 2. 项目说明: 

1. 在首页进行创建 client group,  在列出的group 里选择一个(鼠标点击) , 进入到 client 的控制 和 观测界面
2. 在控制界面 根据 client 的类型分组控制 , 在观测界面 不对client进行分组显示
3. 控制界面无法修改client的数量, 只能控制client的state, 发送的消息, 发送的时间
4. 后端在收到前端传来的消息的时候默认没有将其消息插入远程的Cassendra数据库中, 需要关闭RemoteConfig.java 中的@Configuration 和 StorageReceiver.java 中的@Component注解  
