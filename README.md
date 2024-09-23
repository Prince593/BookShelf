# Multi-Module Android Application

## Overview

This Android application is developed by **Prince Kumar** using **MVVM architecture** and **Jetpack Compose**. The project follows a **multi-module** architecture to improve code organization, build times, and scalability. The app leverages the power of modern Android development practices to deliver a high-quality user experience.

## Features

- **MVVM Architecture**: The application follows the MVVM (Model-View-ViewModel) pattern, ensuring separation of concerns, testability, and maintainability.
- **Jetpack Compose**: Fully implemented using Jetpack Compose for building native UI, making the UI development faster and more intuitive.
- **Multi-Module Structure**: The project is divided into multiple modules to enforce modularity, reusability, and faster build times.

## Technologies Used

- **Kotlin**: For writing concise and clear code.
- **MVVM**: For separating UI from business logic and data handling.
- **Jetpack Compose**: For declarative UI development.
- **Coroutines and Flows**: For managing asynchronous tasks efficiently.
- **Hilt**: For dependency injection across the application.
- **Room**: For local data persistence.
- **Retrofit**: For network calls and API handling.

## Project Structure

The project is divided into the following modules:

1. **app**: Contains the main Android application logic.
2. **utils**: Holds common logic shared across different modules
3. **themes**: Holds common theme shared across different modules
4. **database**: Holds database related items
5. **network**: Holds network items
6. **featureX**: Example of a feature-specific module that contains the logic for a specific feature in the app.
7. **data**: Handles the data layer of the app, including repository and data sources.

## Getting Started

### Prerequisites

- Android Studio JellyFish or later.
- Gradle 8.6 or later.
- Android SDK 21 or later.

### Installation

1. Clone the repository:

   ```bash
   git clone https://github.com/Prince593/BookShelf.git
