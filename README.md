# Web-Based Chatbot - Spring Boot Application

A modern, AI-powered chatbot application built with Spring Boot and integrated with Google Gemini API.

## 🌟 Features

- **AI-Powered Conversations**: Integrated with Google Gemini Pro API
- **Modern UI**: Clean, responsive interface with dark/light theme support
- **Real-time Chat**: Instant responses from the AI assistant
- **Chat History**: Track your conversation history
- **User-Friendly**: Simple and intuitive design

## 🛠️ Technologies Used

- **Backend**: Spring Boot 3.2.0, Java 17
- **Frontend**: HTML, CSS, JavaScript, Thymeleaf
- **API**: Google Gemini Pro API
- **Build Tool**: Maven

## 📋 Prerequisites

Before running this project, make sure you have:

- Java 17 or higher installed
- Maven installed
- A Google Gemini API key ([Get one here](https://ai.google.dev/))

## 🚀 Getting Started

### 1. Clone the Repository

```bash
git clone <your-repository-url>
cd devops
```

### 2. Set Up Your API Key

You have **two options** to configure your Gemini API key:

#### **Option A: Environment Variable (Recommended)**

**Windows (PowerShell):**
```powershell
$env:GEMINI_API_KEY="your-api-key-here"
```

**Windows (Command Prompt):**
```cmd
set GEMINI_API_KEY=your-api-key-here
```

**Linux/Mac:**
```bash
export GEMINI_API_KEY="your-api-key-here"
```

#### **Option B: Direct Configuration**

Edit `src/main/resources/application.properties` and replace `YOUR_API_KEY_HERE` with your actual API key:

```properties
gemini.api.key=your-actual-api-key-here
```

⚠️ **Warning**: If you use Option B, make sure NOT to commit this file to GitHub!

### 3. Build and Run

#### **Using the Batch Script (Windows):**

Simply double-click `run.bat` or run:
```cmd
.\run.bat
```

#### **Using Maven Commands:**

```bash
# Clean and build
mvn clean package -DskipTests

# Run the application
java -jar target/chatbot-1.0.0.jar
```

#### **Using Maven Spring Boot Plugin:**

```bash
mvn spring-boot:run
```

### 4. Access the Application

Open your browser and navigate to:
```
http://localhost:8085
```

## 📁 Project Structure

```
devops/
├── src/
│   ├── main/
│   │   ├── java/com/example/chatbot/
│   │   │   ├── ChatbotApplication.java          # Main application class
│   │   │   ├── controller/
│   │   │   │   └── ChatController.java          # REST API endpoints
│   │   │   ├── model/
│   │   │   │   ├── ChatRequest.java             # Request model
│   │   │   │   └── ChatResponse.java            # Response model
│   │   │   └── service/
│   │   │       └── ChatService.java             # Business logic & API integration
│   │   └── resources/
│   │       ├── application.properties           # Configuration
│   │       ├── static/
│   │       │   └── style.css                    # Styling
│   │       └── templates/
│   │           └── index.html                   # Main UI
│   └── test/                                    # Unit tests
├── pom.xml                                      # Maven dependencies
├── run.bat                                      # Quick start script
└── .gitignore                                   # Git ignore rules
```

## 🔧 Configuration

The application can be configured via `src/main/resources/application.properties`:

```properties
# Server Configuration
server.port=8085
server.servlet.context-path=/

# Application Name
spring.application.name=chatbot

# Thymeleaf Configuration
spring.thymeleaf.prefix=classpath:/templates/
spring.thymeleaf.suffix=.html
spring.thymeleaf.mode=HTML
spring.thymeleaf.encoding=UTF-8
spring.thymeleaf.cache=false

# Gemini API Configuration
gemini.api.key=${GEMINI_API_KEY:YOUR_API_KEY_HERE}
gemini.api.url=https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash:generateContent
```

## 🧪 Running Tests

```bash
mvn test
```

## 📝 API Endpoints

### Chat Endpoint
- **URL**: `/api/chat`
- **Method**: `POST`
- **Content-Type**: `application/json`
- **Request Body**:
  ```json
  {
    "message": "Your question here"
  }
  ```
- **Response**:
  ```json
  {
    "response": "AI assistant's reply",
    "timestamp": "2025-12-25T16:40:00"
  }
  ```

## 🎨 Features Overview

- **Chat Interface**: Type messages and get AI-powered responses
- **Theme Toggle**: Switch between light and dark modes
- **Clear Chat**: Reset conversation history
- **User Settings**: Customize your experience
- **Responsive Design**: Works on desktop and mobile devices

## 🔒 Security Notes

- **Never commit your API key** to version control
- Use environment variables for sensitive data
- The `.gitignore` file is configured to exclude sensitive files
- Consider using Spring Boot profiles for different environments

## 🐛 Troubleshooting

### Application won't start
- Check if Java 17 is installed: `java -version`
- Verify Maven is installed: `mvn -version`
- Ensure port 8085 is not in use

### API not responding
- Verify your API key is correctly set
- Check your internet connection
- Ensure the Gemini API endpoint is accessible

### Build failures
- Run `mvn clean` to clear old builds
- Check Maven dependencies are downloaded
- Verify `pom.xml` is not corrupted

## 📄 License

This project is for educational purposes.

## 👤 Author

Built with ❤️ using Spring Boot and Google Gemini API

## 🙏 Acknowledgments

- Google Gemini API for AI capabilities
- Spring Boot team for the excellent framework
- Thymeleaf for templating support
