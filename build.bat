@echo off
echo ================================
echo E-Sports Application Build Script
echo ================================

REM 设置Java环境变量(如果需要)
REM set JAVA_HOME=C:\Program Files\Java\jdk1.8.0_231

REM 清理、编译、测试和打包项目
call mvn clean package

if %errorlevel% == 0 (
    echo.
    echo Build Success!
    echo War file location: target/esports.war
) else (
    echo.
    echo Build Failed!
    exit /b %errorlevel%
)

pause