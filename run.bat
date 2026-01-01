@echo off
REM ========================================
REM Web-Based Chatbot - Project Runner
REM ========================================

cls
echo.
echo ╔════════════════════════════════════════════════════════════════╗
echo ║     Web-Based Chatbot - Spring Boot Application (DevOps)      ║
echo ╚════════════════════════════════════════════════════════════════╝
echo.

REM Move to project directory
set PROJECT_DIR=%~dp0
cd /d "%PROJECT_DIR%"

echo [INFO] Project Directory:
echo %PROJECT_DIR%
echo.

REM Step 1: Build project
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

REM Step 2: Open browser
echo [INFO] Opening browser at http://localhost:8085
start http://localhost:8085
echo.

REM Step 3: Run application
echo [RUN] Starting Spring Boot application...
echo ----------------------------------------
echo Press Ctrl+C to stop the application
echo.
java -jar "%PROJECT_DIR%target\chatbot-1.0.0.jar"

pause
