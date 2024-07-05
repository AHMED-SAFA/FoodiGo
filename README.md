# FoodiGo - Food Delivery and Ordering System

![Screenshot (174)](https://github.com/AHMED-SAFA/FoodieGo/assets/111047763/f2e20a57-f8cd-48ca-bde8-b30ba85429cf)

![Screenshot (175)](https://github.com/AHMED-SAFA/FoodieGo/assets/111047763/92f92e77-0f02-4575-9a16-1f96db4027fd)


## ➡️Overview

This is a native Android application for a food delivery and ordering system. The project is built using Java and Firebase, leveraging Firebase Realtime Database, Firebase Cloud Storage, and Firebase Authentication. The project incorporates modern software design patterns such as Adapter, Singleton, and MVC. It also includes comprehensive testing with unit tests, UI tests, and continuous integration (CI) testing.

## ➡️Features

- **User Authentication:** Sign up, login, and logout functionality using Firebase Authentication.

![Screenshot (176)](https://github.com/AHMED-SAFA/FoodieGo/assets/111047763/a3c47330-560e-4046-a422-bf8f557ab877)

- **Browse Restaurants:** View a list of available restaurants from Firebase Realtime Database.
- **Order Food:** Select food items from restaurant menus and place orders, stored in Firebase Realtime Database.

  ![Screenshot (177)](https://github.com/AHMED-SAFA/FoodieGo/assets/111047763/c3e33cba-2bbe-4ee3-b53d-f4712ba7c662)

  ![Screenshot (179)](https://github.com/AHMED-SAFA/FoodieGo/assets/111047763/109abeeb-6663-4e27-ba91-ac3e4829a785)
  
![Screenshot (178)](https://github.com/AHMED-SAFA/FoodieGo/assets/111047763/7e5253f3-913e-4e7b-bcc9-b4bceca8088d)


- **Order History:** View past orders fetched from the Firebase Realtime Database.

  
![Screenshot (180)](https://github.com/AHMED-SAFA/FoodieGo/assets/111047763/e57aafd5-ff98-4337-8bc3-64718cdbf50e)

  
- **Real-time Updates:** Receive real-time updates on order status via Firebase Realtime Database.
- **Image Uploads:** Upload and view images for menu items and restaurant profiles using Firebase Cloud Storage.

## ➡️Design Patterns

### 1. Adapter Design Pattern
Used to manage the communication between different parts of the application, such as converting data from Firebase into a format that can be displayed in the UI.

### 2. Singleton Design Pattern
Ensures that certain classes, like the Firebase database instance, are created only once throughout the application lifecycle.

### 3. MVC Pattern (Model-View-Controller)
Separates the application into three interconnected components:
- **Model:** Manages the data and business logic.
- **View:** Handles the UI and displays data to the user.
- **Controller:** Acts as an intermediary between Model and View, processing user input and updating the Model.

## ➡️Testing

### Unit Testing
Unit tests are written to verify the functionality of individual components and methods within the application.

![Screenshot (184)](https://github.com/AHMED-SAFA/FoodieGo/assets/111047763/3ccad846-28fe-4c0e-a42f-22631cbbe437)


### UI Testing
UI tests ensure that the user interface operates correctly and provides a smooth user experience.

![Screenshot (183)](https://github.com/AHMED-SAFA/FoodieGo/assets/111047763/abcaf286-6b6e-4eab-b689-2ca7abcbbcb9)


### CI Testing
Continuous Integration (CI) testing is set up to automatically run tests on new commits to ensure code quality and functionality.

![Screenshot (181)](https://github.com/AHMED-SAFA/FoodieGo/assets/111047763/e5b55389-96c3-4a1c-8761-a50d5a94e933)


## ➡️Technologies Used

- **Java:** Core programming language for Android development.
- **Firebase:** Backend-as-a-Service for real-time database, authentication, and cloud functions.
  - **Firebase Realtime Database:** For storing and syncing data in real time.
  - **Firebase Cloud Storage:** For storing images and other files.
  - **Firebase Authentication:** For user authentication and security.
- **JUnit:** Framework for unit testing.
- **Espresso:** Framework for UI testing.
- **Travis CI/GitHub Actions:** Tools for continuous integration.

## ➡️Installation

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
  
<h1>➡️Contributors</h1>
<ul>
  <li><h3><a href="https://github.com/souravdebnath109">Sourav Debnath</a></h2></li>
  <li><h3><a href="https://github.com/AHMED-SAFA">Ahmed Nur E Safa</a></h2> </li>
  <li><h3><a href="https://github.com/DsDipto7">Dipto Saha</a></h2></li>
</ul>

