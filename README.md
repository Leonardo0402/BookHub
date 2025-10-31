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

## 使用 Maven Wrapper（推荐）

本仓库已内置 Maven Wrapper（`mvnw` / `mvnw.cmd`），无需安装 Maven 即可构建与运行。

- Windows:
  - 运行测试：`mvnw.cmd -q -DskipTests=false test`
  - 启动应用（推荐）：`mvnw.cmd -q org.codehaus.mojo:exec-maven-plugin:3.1.0:java -Dexec.mainClass=com.bookhub.app.BookHubApplication`
- macOS/Linux:
  - 运行测试：`./mvnw -q -DskipTests=false test`
  - 启动应用（推荐）：`./mvnw -q org.codehaus.mojo:exec-maven-plugin:3.1.0:java -Dexec.mainClass=com.bookhub.app.BookHubApplication`

说明：使用 `exec-maven-plugin` 启动会自动把依赖加入类路径，避免 `java -jar` 方式遗漏依赖的问题。

## 常用 Maven 命令示例

- 清理与编译：`mvnw[.cmd] -q clean compile`
- 运行全部测试：`mvnw[.cmd] -q test`
- 仅运行单个测试类：`mvnw[.cmd] -q -Dtest=SearchServiceTest test`
- 仅运行某个测试方法：`mvnw[.cmd] -q -Dtest=SearchServiceTest#searchByKeywordFindsMatchingItems test`
- 打包 JAR：`mvnw[.cmd] -q -DskipTests package`
- 使用 exec 插件运行主类：
  - `mvnw[.cmd] -q org.codehaus.mojo:exec-maven-plugin:3.1.0:java -Dexec.mainClass=com.bookhub.app.BookHubApplication`

可选：如需通过 `java -jar` 运行，请先打包，然后确保依赖在类路径（或改用 Shade/Assembly 打包可执行 JAR）。推荐仍使用上面的 exec 方式。

## 运行与配置

- 启动 UI：执行“使用 Maven Wrapper”中的启动命令后会弹出 Swing 主窗口。
- 日志等级（slf4j-simple）：在命令行追加 JVM 选项控制日志，例如：
  - `-Dorg.slf4j.simpleLogger.defaultLogLevel=info`
  - `-Dorg.slf4j.simpleLogger.showThreadName=true`
  - `-Dorg.slf4j.simpleLogger.showDateTime=true`
- 编码设置：Windows 终端如出现中文乱码，可：
  - 切换终端到 UTF-8：`chcp 65001`
  - 或在命令中追加：`-Dfile.encoding=UTF-8`

示例（Windows，UTF-8 日志、信息级别）：

```
mvnw.cmd -q org.codehaus.mojo:exec-maven-plugin:3.1.0:java ^
  -Dexec.mainClass=com.bookhub.app.BookHubApplication ^
  -Dorg.slf4j.simpleLogger.defaultLogLevel=info ^
  -Dfile.encoding=UTF-8
```

祝学习顺利！
