Here's an updated `README.md` that includes instructions for running the frontend:

```markdown
# E-Commerce Platform Backend & Frontend

This is a full-stack e-commerce subscription platform. The backend is developed in Java, while the frontend consists of HTML, CSS, and JavaScript.

## Project Structure

- **src/**: Contains the Java source code.
- **frontend/**: Contains the frontend files:
  - **HTML/**: HTML files for the web pages.
  - **CSS/**: Stylesheets for the web pages.
  - **JS/**: JavaScript files for client-side logic.
- **codeToExecuteSQLFiles.java**: A Java program to initialize the database by executing the required SQL code.
- **README.md**: Project documentation.

## Prerequisites

- **Java Development Kit (JDK)**: Ensure you have JDK 8 or later installed.
- **MySQL**: The project uses MySQL as the database management system.
- **Web Browser**: Any modern web browser to view the frontend.

## Setup Instructions

### Step 1: Clone the Repository

Clone the repository to your local machine:

```bash
https://github.com/satyamrawat625/code-fury.git
cd code-fury
```

### Step 2: Set Up the Database

Before running the backend code, you need to set up the database.

1. **MySQL Configuration**:
    - Make sure MySQL is installed and running on your machine.
    - Create a database for the project (you can name it `bigcart` or any name you prefer).

2. **Edit Database Configuration**:
    - In your Java code, ensure that the database connection details (username, password, database name) are correctly configured.

3. **Initialize the Database**:
    - The file `codeToExecuteSQLFiles.java` is provided to initialize the database.
    - This file will execute the required SQL commands to create the necessary tables and the database itself.

### Step 3: Running the Initialization Script

To create the database and tables, follow these steps:

1. Open a terminal/command prompt.
2. Navigate to the directory where `codeToExecuteSQLFiles.java` is located.
3. Compile and run the Java file:

```bash
javac codeToExecuteSQLFiles.java
java codeToExecuteSQLFiles
```

This will execute the SQL code required to set up the database schema.

### Step 4: Run the Backend

After the database has been initialized, you can compile and run the rest of the backend code. The backend will interact with the database using JDBC.

```bash
javac -d bin src/**/*.java
java -cp bin com.ecommerce.App
```

### Step 5: Run the Frontend

To view the frontend of the application:

1. Navigate to the `frontend` directory in your project.
2. Open the `index.html` file in any modern web browser.

```bash
cd frontend
open index.html
```

Alternatively, you can open the `index.html` file directly from your file explorer by double-clicking it.


## Troubleshooting

- **MySQL Connection Issues**: Make sure your MySQL server is running and that your connection details in the Java code are correct.
- **SQL Errors**: If the initialization fails, ensure that the SQL syntax in the file `codeToExecuteSQLFiles.java` is correct and matches your MySQL version.
- **Frontend Issues**: If the frontend doesn't load correctly, ensure your browser is up to date and check the console for any JavaScript errors.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
```

This `README.md` now includes instructions for both the backend setup and how to run the frontend by opening the `index.html` file in a browser.