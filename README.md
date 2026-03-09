# FFPE - Fuel Fraud Pattern Engine

## Project Overview
FFPE (Fuel Fraud Pattern Engine) is a fleet monitoring system that detects fuel theft by comparing tank fuel level, ECU fuel consumption, and expected fuel values.

If the fuel mismatch exceeds a threshold value, the system detects fraud and sends an alert.

## Technologies Used
Frontend
- HTML
- CSS
- JavaScript

Backend
- Python Flask

Alerts
- Telegram Bot API

DSA Demonstration
- Java implementation of fraud detection algorithm

## System Workflow
1. Fuel data is received from vehicle sensors
2. Backend compares tank fuel and ECU values
3. If mismatch exceeds threshold (5 liters)
4. System detects fraud
5. Dashboard updates and Telegram alert is sent

## Features
- Live fuel monitoring dashboard
- Automated fraud detection
- Telegram alert system
- API documentation page
- Java implementation of fraud detection algorithm

## Future Improvements
- Integration with IoT fuel sensors
- GPS vehicle tracking
- Data analytics for fleet management

Note:
The dashboard requires the Flask backend (app.py) to be running locally.
If the backend is not running, the dashboard will display "Backend Offline".