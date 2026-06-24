# Online Quiz System

A desktop-based Online Quiz System developed using **Java Swing**, **JDBC**, and **MySQL**. The application allows administrators to manage quiz questions and students to take quizzes, calculate scores automatically, and store results in a database.

## Features

### Admin Module

* Secure Admin Login
* Add New Questions
* Delete Existing Questions
* View All Questions
* View Student Results
* Search Results by Student ID

### Student Module

* Student Login
* Attempt Quiz
* Navigate Questions (Next / Previous)
* Submit Quiz
* Automatic Score Calculation
* View Quiz Result

## Technologies Used

* Java
* Java Swing
* JDBC
* MySQL

## Database Tables

### questions

Stores all quiz questions and options.

### student_results

Stores student quiz results and performance details.

## Project Structure

```text
src/
├── Main.java
├── Homepage.java
├── AdminLogin.java
├── AdminPanel.java
├── AddQuestions.java
├── DeleteQuestion.java
├── ViewQuestions.java
├── StudentLogin.java
├── QuizForm.java
├── ResultForm.java
├── StudentResults.java
└── DBConnection.java
```

## How to Run

1. Create the required MySQL database and tables.
2. Update database credentials in `DBConnection.java`.
3. Add MySQL Connector/J to the project libraries.
4. Run `Main.java`.

## Future Enhancements

* Timer-based quizzes
* Multiple quiz categories
* Student registration system
* Leaderboard and ranking system
* Export results to Excel/PDF

## Author

**Surya Lakkimsetti**
B.Tech Information Technology
RVR & JC College of Engineering
