# MyTaskNotesApp

* Description
  MyTaskNotesApp is a simple and secure Android application designed to help users manage their personal tasks and notes efficiently. The app allows users to create, view, edit, and delete tasks, as well as toggle their completion status. 

  The project follows a modern Android architecture using the Repository pattern with Room for local data persistence, ensuring that all user data remains safe even after the app is restarted.

## Core Features
* Task Management: Create new tasks with a title and description, and edit existing ones.
* Task Tracking: Mark tasks as completed with a visual strikethrough effect or delete them when they are no longer needed.
* Local Persistence: All data is stored locally using a Room SQLite database.
* Responsive UI: The app uses a RecyclerView for smooth list scrolling and a ViewModel to preserve state during screen rotations[cite: 17, 38, 40].

## Design Choices
* Architecture: I implemented the **MVVM (Model-View-ViewModel)** architecture. This separates the UI logic from the data layer, making the app easier to maintain and test.
* Data Persistence: I chose **Room Database** over SharedPreferences because it provides a more robust solution for structured data and allows for complex queries in the future if the app expands.
* UI/UX: The interface follows **Material Design 3** guidelines, utilizing `MaterialCardView` for task items and a `FloatingActionButton` for primary actions to ensure a consistent Android feel.

## Secure Coding Practices
This application incorporates several secure coding practices as required by the assignment constraints:
1. Input Validation: The app validates user input in `AddEditTaskActivity` to ensure titles are not empty before saving to the database, preventing null or corrupted entries.
2. Data Sanitization: Input length is restricted for both titles and descriptions to prevent excessive data storage and ensure memory stability.
3. No Hard-coded Data: No sensitive information or configuration strings are hard-coded within the source code.

## Screenshots
1. **Main Screen**: ![Main Screen](Screenshots/MainScreen.png)
2. **Add Task**: `![Add Task](Screenshots/AddTask.png)
3. **Edit/Complete**: ![Edit/Complete](Screenshots/Edit.png)

## Technical Specifications
* Language: Kotlin
* Minimum SDK: API 26
* Database: Room (SQLite)
* IDE: Android Studio
