@echo off
cls
echo.
echo ╔════════════════════════════════════════════════════════════════╗
echo ║     Web-Based Chatbot - Spring Boot Application (DevOps)      ║
echo ╚════════════════════════════════════════════════════════════════╝
echo.

set PROJECT_DIR=%~dp0
cd /d "%PROJECT_DIR%"

echo [INFO] Project Directory:
echo %PROJECT_DIR%
echo.

echo [BUILD] Running Maven build...
echo ----------------------------------------
call mvn clean package -DskipTests
if %errorlevel% neq 0 (
    echo.
    echo [ERROR] Maven build failed!
    echo Make sure Maven is installed and added to PATH.
    pause
    exit /b 1
)
echo [SUCCESS] Build completed!
echo.

echo [INFO] Opening browser at http://localhost:8085
start http://localhost:8085
echo.

echo [RUN] Starting Spring Boot application...
echo ----------------------------------------
echo Press Ctrl+C to stop the application
echo.
java -jar "%PROJECT_DIR%target\chatbot-1.0.0.jar"

pause
