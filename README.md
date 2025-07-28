# 📧 Email Validator API

A lightweight REST API built with **Spring Boot** that validates email addresses for proper syntax and checks for real domain existence via **MX record lookup**. Ideal for form validation, registration flows, or standalone email verification microservices.

---

## 🚀 Features

- ✅ Validate email structure using **regular expressions**
- ✅ Check if the domain has valid **MX DNS records**
- ✅ RESTful API interface – simple and fast to integrate
- ✅ JSON response with status and message
- ✅ Built with clean, layered architecture (Controller → Service → Utils)
- ✅ Easily extendable for further features (e.g. SMTP check)

---

## 🛠 Technologies Used

- **Java 17**
- **Spring Boot**
- **Spring Web**
- **Java Mail API**
- **Regex**
- **Maven**

---

## 🔌 API Endpoint

`POST /api/v1/validate`

### Request Body (JSON)
```json
{
  "email": "test@example.com"
}

#### 📤 Sample Request:
GET /api/v1/validate?email=test@example.com


#### 📥 Sample Response:
```
{
  "email": "test@example.com",
  "user": "test",
  "domain": "example.com",
  "validSyntax": true,
  "hasMxRecord": true,
  "temporary": false
}

#### Deployed on RapidAPI.com -> https://rapidapi.com/slawi/api/email-validator-api9

#### If you want to start it localy just remove the rapid api proxy secret from the controller.
