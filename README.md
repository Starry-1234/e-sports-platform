# E-Sports Data Platform 电竞数据平台

![Project Status](https://img.shields.io/badge/status-in%20development-orange)
![Java Version](https://img.shields.io/badge/java-8+-blue)
![License](https://img.shields.io/badge/license-MIT-green)

一个专业的电子竞技数据分析平台，用于管理和分析电竞比赛数据，包括战队信息、选手资料、比赛结果、英雄选择禁用(BP)策略以及比赛事件追踪等功能。

## 目录

- [功能特性](#功能特性)
- [技术栈](#技术栈)
- [项目结构](#项目结构)
- [快速开始](#快速开始)
- [构建与部署](#构建与部署)
- [数据库设计](#数据库设计)
- [API接口](#api接口)
- [贡献指南](#贡献指南)
- [许可证](#许可证)

## 功能特性

- 🏆 **战队管理**: 管理电竞战队基本信息
- 👤 **选手管理**: 维护选手个人资料和统计数据
- 🎮 **比赛数据**: 记录和展示比赛结果及详细数据
- 📊 **BP分析**: 英雄选择与禁用策略分析
- ⏱️ **事件追踪**: 比赛关键时刻和事件时间线追踪
- 📈 **数据统计**: 全面的数据概览和统计分析
- 🖥️ **可视化界面**: 基于Web的用户友好界面

## 技术栈

### 后端技术
- Java 8+
- Spring Framework 5.3.18
- Spring MVC
- MyBatis 3.5.9
- MySQL 8.0+

### 前端技术
- Thymeleaf 3.0.15 (模板引擎)
- HTML5/CSS3
- Bootstrap 5 (推测，根据前端样式)
- JavaScript/jQuery

### 构建工具
- Maven 3.6+
- Apache Tomcat (应用服务器)

### 依赖管理
- Druid 连接池
- Jackson JSON处理
- Logback 日志框架

## 项目结构

```
e-sports/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/niit/esports/
│   │   │       ├── controller/     # 控制层
│   │   │       ├── entity/         # 实体类
│   │   │       ├── mapper/         # MyBatis映射接口
│   │   │       ├── service/        # 服务层
│   │   │       └── util/           # 工具类
│   │   ├── resources/
│   │   │   ├── mybatis/            # MyBatis配置
│   │   │   ├── spring/             # Spring配置
│   │   │   ├── db.properties       # 数据库配置
│   │   │   └── logback.xml         # 日志配置
│   │   └── webapp/
│   │       ├── WEB-INF/
│   │       │   ├── views/          # 视图模板
│   │       │   └── web.xml         # Web配置
│   │       └── static/             # 静态资源
└── target/                         # 构建输出目录
```

## 快速开始

### 环境要求

- Java JDK 8 或更高版本
- Apache Maven 3.6 或更高版本
- MySQL 8.0 或更高版本
- Apache Tomcat 8.5 或更高版本

### 安装步骤

1. 克隆项目到本地：
```bash
git clone <repository-url>
cd e-sports
```

2. 配置数据库：
   - 在MySQL中创建数据库
   - 修改 `src/main/resources/db.properties` 文件中的数据库连接信息

3. 编译和打包项目：
```bash
mvn clean package
```

4. 部署WAR文件到Tomcat服务器：
   - 将 `target/esports.war` 复制到Tomcat的webapps目录下
   - 启动或重启Tomcat服务器

5. 访问应用程序：
   打开浏览器访问 `http://localhost:8080/esports/`

## 构建与部署

### 自动化构建脚本

项目提供了两个自动化构建脚本：

#### Windows批处理脚本
双击运行 `build.bat` 或在命令提示符中执行：
```cmd
build.bat
```

#### PowerShell脚本
在PowerShell中执行：
```powershell
.\run.ps1
```

### 手动构建命令

使用Maven进行手动构建：
```bash
# 清理项目
mvn clean

# 编译源代码
mvn compile

# 运行测试
mvn test

# 打包应用
mvn package

# 安装到本地仓库
mvn install

# 清理并打包
mvn clean package
```

### 部署说明

1. 构建完成后，WAR文件位于 `target/esports.war`
2. 将WAR文件复制到Tomcat的webapps目录
3. 启动或重启Tomcat服务器
4. 应用程序可通过 `http://服务器地址:端口/esports/` 访问

## 数据库设计

系统包含以下主要实体：

- **战队(Team)**: 存储战队基本信息
- **选手(Player)**: 选手个人资料
- **比赛(Match)**: 比赛基本信息
- **英雄(Hero)**: 游戏中可选英雄
- **比赛选手统计(MatchPlayerStats)**: 选手在具体比赛中的表现数据
- **比赛选人禁人(MatchPickBan)**: 比赛中的英雄选择和禁用记录
- **比赛事件(MatchEvent)**: 比赛过程中的关键事件

## API接口

### 主要页面路由

- `/` or `/index` - 首页
- `/teams` - 战队列表
- `/players` - 选手列表
- `/matches` - 比赛列表
- `/analysis/matchdata` - 比赛数据分析
- `/analysis/pickban` - BP分析
- `/analysis/events` - 比赛事件追踪

## 贡献指南

欢迎任何形式的贡献！如果您想参与项目开发，请遵循以下步骤：

1. Fork项目仓库
2. 创建您的功能分支 (`git checkout -b feature/AmazingFeature`)
3. 提交您的更改 (`git commit -m 'Add some AmazingFeature'`)
4. 推送到分支 (`git push origin feature/AmazingFeature`)
5. 开启一个Pull Request

### 开发规范

- 遵循现有的代码风格
- 为新功能添加适当的测试
- 更新相关文档
- 确保所有测试通过后再提交

## 许可证

本项目采用MIT许可证，详情请见[LICENSE](LICENSE)文件。

---

**项目状态**: 正在开发中

如有任何问题或建议，请联系项目维护者。