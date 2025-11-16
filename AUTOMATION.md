# E-Sports Application Automation Scripts

## Maven Commands

The project uses Maven for build automation. Here are the most common commands:

### Basic Maven Lifecycle Commands

1. Clean the project:
   ```bash
   mvn clean
   ```

2. Compile the source code:
   ```bash
   mvn compile
   ```

3. Run tests:
   ```bash
   mvn test
   ```

4. Package the application:
   ```bash
   mvn package
   ```

5. Install the package into local repository:
   ```bash
   mvn install
   ```

6. Clean and package in one command:
   ```bash
   mvn clean package
   ```

## Windows Batch Script

We've created a batch script `build.bat` that automates the build process:
- Cleans the project
- Compiles the code
- Packages the application into a WAR file

To run it, simply double-click on `build.bat` or execute from command prompt:
```cmd
build.bat
```

## PowerShell Script

We've also created a PowerShell script `run.ps1` with enhanced output formatting:
- Color-coded output messages
- Build status reporting
- File information display

To run it in PowerShell:
```powershell
.\run.ps1
```

## Deployment Instructions

After successful build, the WAR file will be located at `target/esports.war`. 
To deploy the application:
1. Copy `target/esports.war` to your Tomcat webapps directory
2. Start or restart Tomcat server
3. Access the application at: http://localhost:8080/esports/

## Prerequisites

Ensure you have the following installed:
- Java JDK 8 or higher
- Apache Maven 3.6 or higher
- Environment variables JAVA_HOME and MAVEN_HOME properly configured