# Work Log Assistant

Work Log Assistant is a simple, efficient tool to help you track and document your daily tasks. It integrates with the Gemini API to generate context-aware work log entries and supports session-based saving, allowing users to view or continue editing their logs on another page within the same browser session.

## Features

- 📝 **Task-based Work Log Creation**  
  Add and manage work logs by entering tasks you've worked on throughout the day.

- 🤖 **Gemini API Integration**  
  Automatically generate descriptive work log content using Gemini's AI capabilities.

- 💾 **Session-based Save Functionality**  
  Your logs are preserved during the browser session and can be accessed or edited from another page.

## Getting Started

### Prerequisites

- Node.js (if running locally)
- An API key for Google Gemini (or the relevant setup for calling Gemini API)
- Modern web browser (Chrome, Firefox, etc.)

### Installation

1. Clone the repository:

   ```bash
   git clone https://github.com/yourusername/work-log-assistant.git
   cd work-log-assistant
Flow:
   work-log-assistant/
├── public/
├── src/
│   ├── components/
│   │   ├── TaskInput.jsx
│   │   ├── WorkLogGenerator.jsx
│   │   └── SavedLogs.jsx
│   ├── pages/
│   │   ├── Home.jsx
│   │   └── Logs.jsx
│   ├── services/
│   │   └── geminiAPI.js
│   └── App.jsx
├── package.json
└── README.md
