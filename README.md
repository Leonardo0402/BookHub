# BookHub 学习项目

BookHub 是一个面向学习的 Java 桌面应用示例项目，目标是帮助你循序渐进地掌握面向对象编程（OOP）与常见企业级开发技术。仓库中包含清晰的分层包结构、类注释以及示例代码片段，覆盖继承、多态、接口、异常、集合、泛型、多线程、网络通信、JDBC、Swing UI 等核心知识点。

## 快速开始

```bash
mvn clean package
java -jar target/bookhub-0.1.0-SNAPSHOT.jar
```

首次运行会启动带有日志的命令行引导模式，同时也准备了 Swing 图形界面的骨架。

## 项目结构

```
com.bookhub
 ├─ app/            // 应用入口与模块装配
 ├─ config/         // 配置常量、环境自定义
 ├─ domain/         // 领域模型（实体、值对象、策略接口）
 ├─ dao/            // 数据访问层（JDBC、RowMapper、Repository）
 ├─ service/        // 业务逻辑（借阅、搜索、下载、索引）
 ├─ net/            // TCP/UDP/RMI/HTTP 通信
 ├─ ui/             // Swing MVC 视图模型
 ├─ plugin/         // 插件系统与自定义注解
 └─ util/           // 工具类、异常体系、线程工具
```

`docs/` 目录可放置 UML 类图、时序图、数据库脚本等学习资料。

## 下一步

- 根据 `docs/` 内的学习计划逐章实现功能。
- 编写 JUnit 测试巩固每个模块的知识点。
- 利用 README 作为日志，记录每一次学习成果与思考。

祝学习顺利！
