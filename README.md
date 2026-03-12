# RV Auto Seller

A full-stack car marketplace web application built with **Spring Boot** and **Vue**.

RV Auto Seller is a client-server platform designed to simplify the process of publishing, browsing, and managing car listings in one place. Users can explore active listings, search and filter vehicles, view full car details, schedule appointments, leave seller feedback, and manage listings or users depending on their role.

---

## Overview

The platform supports three user roles:

- **Client**
- **Seller**
- **Administrator**

It combines a **Spring Boot REST API** backend with a **Vue + Vite** frontend and uses **JWT-based authentication** to secure protected operations.

---

## Main Features

### Public features
- Browse active car listings
- View full listing details
- Search listings by title
- Filter listings by multiple criteria:
  - brand
  - model
  - min/max price
  - year
  - fuel type
  - mileage
  - location
  - gearbox
- View seller reviews and feedback

### Authenticated client features
- Register and log in
- Contact sellers directly from the platform
- Schedule a viewing / test-drive appointment
- View purchased cars
- Leave feedback for sellers

### Seller features
- All authenticated client features
- Add a new car listing
- Upload between **1 and 10 images** for each listing
- Delete own listings
- Manage appointment requests
- Confirm or reject reservations

### Admin features
- Manage users and listings
- Perform CRUD operations on accounts and listings
- View platform statistics
- Manage user access rights
- Block accounts / reset passwords

---

## Tech Stack

### Backend
- Java
- Spring Boot
- Spring Web (REST)
- Spring Security
- JWT Bearer Authentication
- Spring Data JPA
- Hibernate
- Jackson
- Lombok

### Frontend
- Vue.js
- Vite
- TypeScript
- Axios / fetch

### Persistence & Storage
- Relational database
- Server-side image storage
- Multipart file upload

### Tools
- Postman
- Git
- GitHub
- IntelliJ IDEA
- WebStorm
- draw.io

---

## Architecture

The application follows a **client-server architecture**:

- The **frontend** is responsible for the UI, forms, dashboards, search/filter flows, and user interaction
- The **backend** exposes REST endpoints for authentication, listings, appointments, feedback, and administration
- Protected actions are secured using **JWT tokens**
- Uploaded images are stored on the server and their URLs are persisted in the database

---

## Core Modules

### Authentication & Security
- User registration
- User login
- JWT-based authentication
- Bearer token authorization
- Protected routes and secured backend endpoints
- Current-user identification for role-based actions

### Listings
- Create listing
- View listings
- View listing details
- Update listing
- Delete listing
- Search and filter listings
- Upload car images

### Appointments
- Request a viewing / test-drive
- Seller approval / rejection flow
- Reservation management
- Email notifications for appointment events

### Feedback
- Leave feedback after purchase
- Display seller reviews for future buyers

### Administration
- Manage users
- Manage listings
- View statistics and charts
- Handle account restrictions and access rights

---

## Data Model

The backend uses entities such as:

- `User`
- `Listing`
- `Image`
- `Appointment`
- `Feedback`

These model the main business flows of the application and are persisted through **JPA / Hibernate**.

---

## Image Upload Handling

Listings support multiple car images.

- Images are uploaded using **multipart/form-data**
- Files are stored on the server in an `/uploads` directory
- The corresponding file URLs are saved in the database

---

## Example User Flow

1. A user browses active listings
2. The user filters cars by desired criteria
3. The user opens a listing details page
4. After authentication, the user schedules a viewing
5. The seller confirms or rejects the appointment
6. After purchase, the buyer can leave feedback

---

## UI Pages

The project includes dedicated screens for:

- Home page with active listings
- Filters & search
- Listing details
- Login / Register
- Seller dashboard
- Appointments page
- Admin dashboard with statistics

---

## Project Highlights

What this project demonstrates from a software engineering perspective:

- Full-stack development
- REST API design
- Authentication and authorization with JWT
- Role-based access control
- Database modeling with JPA / Hibernate
- CRUD operations for real business entities
- File upload handling
- Frontend-backend integration
- Multi-role workflows
- Admin management features

---
