# Food Delivery and Ordering System

![Food Delivery](.jpg)

## Overview

This is a native Android application for a food delivery and ordering system. The project is built using Java and Firebase, leveraging Firebase Realtime Database, Firebase Cloud Storage, and Firebase Authentication. The project incorporates modern software design patterns such as Adapter, Singleton, and MVC. It also includes comprehensive testing with unit tests, UI tests, and continuous integration (CI) testing.

## Features

- **User Authentication:** Sign up, login, and logout functionality using Firebase Authentication.
- **Browse Restaurants:** View a list of available restaurants from Firebase Realtime Database.
- **Order Food:** Select food items from restaurant menus and place orders, stored in Firebase Realtime Database.
- **Order History:** View past orders fetched from Firebase Realtime Database.
- **Real-time Updates:** Receive real-time updates on order status via Firebase Realtime Database.
- **Image Uploads:** Upload and view images for menu items and restaurant profiles using Firebase Cloud Storage.

## Design Patterns

### 1. Adapter Design Pattern
Used to manage the communication between different parts of the application, such as converting data from Firebase into a format that can be displayed in the UI.

### 2. Singleton Design Pattern
Ensures that certain classes, like the Firebase database instance, are created only once throughout the application lifecycle.

### 3. MVC Pattern (Model-View-Controller)
Separates the application into three interconnected components:
- **Model:** Manages the data and business logic.
- **View:** Handles the UI and displays data to the user.
- **Controller:** Acts as an intermediary between Model and View, processing user input and updating the Model.

## Testing

### Unit Testing
Unit tests are written to verify the functionality of individual components and methods within the application.

### UI Testing
UI tests ensure that the user interface operates correctly and provides a smooth user experience.

### CI Testing
Continuous Integration (CI) testing is set up to automatically run tests on new commits to ensure code quality and functionality.

## Technologies Used

- **Java:** Core programming language for Android development.
- **Firebase:** Backend-as-a-Service for real-time database, authentication, and cloud functions.
  - **Firebase Realtime Database:** For storing and syncing data in real time.
  - **Firebase Cloud Storage:** For storing images and other files.
  - **Firebase Authentication:** For user authentication and security.
- **JUnit:** Framework for unit testing.
- **Espresso:** Framework for UI testing.
- **Travis CI/GitHub Actions:** Tools for continuous integration.

## Installation

1. **Clone the repository:**
   ```sh
   git clone https://github.com/AHMED-SAFA/FoodieGo.git

2. **Open the project in Android Studio:**
   - File > Open > Select the project directory.

3. **Set up Firebase:**
    - Add your google-services.json file to the app directory.
    - Configure Firebase in your project by following the Firebase setup guide.
4. **Configure Firebase Database and Storage:**
    - Set up Firebase Realtime Database rules in the Firebase console.
    - Set up Firebase Cloud Storage rules in the Firebase console.
5. **Build the project:**
    - Build > Rebuild Project
6. **Run the application:**
    - Run > Run 'app'
  
<h1>#Contributors</h1>
<h3>-><a href="https://github.com/souravdebnath109">Sourav Debnath</a></h2>  
<h3>-><a href="https://github.com/AHMED-SAFA">Ahmed Nur E Safa</a></h2>  
<h3>-><a href="https://github.com/DsDipto7">Dipto Saha</a></h2>
