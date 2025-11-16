# E-Sports Application PowerShell Script

Write-Host "================================" -ForegroundColor Green
Write-Host "E-Sports Application Runner" -ForegroundColor Green
Write-Host "================================" -ForegroundColor Green

# 构建项目
Write-Host "Building project..." -ForegroundColor Yellow
mvn clean package

if ($LASTEXITCODE -eq 0) {
    Write-Host "Build successful!" -ForegroundColor Green
    
    # 可以在这里添加部署到Tomcat服务器的命令示例
    Write-Host "To deploy the application:" -ForegroundColor Cyan
    Write-Host "1. Copy target/esports.war to your Tomcat webapps directory" -ForegroundColor Cyan
    Write-Host "2. Start/restart Tomcat server" -ForegroundColor Cyan
    
    # 显示生成的war文件位置
    Write-Host "`nWar file location:" -ForegroundColor Yellow
    Get-ChildItem "target/esports.war" | Format-Table Name, Length, LastWriteTime
} else {
    Write-Host "Build failed!" -ForegroundColor Red
    exit $LASTEXITCODE
}