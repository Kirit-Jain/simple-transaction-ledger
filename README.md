# Simple Transaction Ledger API

### Project Overview

This is a backend-only RESTful API that simulates a basic financial transaction ledger. This project was built to deepen my understanding of backend systems design, data persistence with Spring Data JPA, and the core business logic behind financial services.

The API allows users to:

  * Post new transactions between different accounts.

  * Retrieve a list of all transactions for a specific account.

  * Calculate and retrieve the real-time balance for a specific account.

### Tech Stack

  * **Backend:** Spring Boot (Java)

  * **Database:** MySQL

  * **ORM (Object-Relational Mapping):** Spring Data JPA

  * **Build Tool:** Maven

### API Endpoints

The application runs on `http://localhost:8080`.

1.  **Create a New Transaction**

    Posts a new transaction. The system will debit the `senderAccountId` and credit the `receiverAccountId`.

      * **Endpoint:** `POST /transactions`

      * **Request Body (JSON):**

        ```json
        {
          "senderAccountId": "acct_alice",
          "receiverAccountId": "acct_bob",
          "amount": 150.75
        }
        ```

      * **Success Response (JSON):**

        ```json
        {
          "id": 1,
          "senderAccountId": "acct_alice",
          "receiverAccountId": "acct_bob",
          "amount": 150.75,
          "timestamp": "2025-11-14T10:30:00.000+00:00"
        }
        ```

2.  **Get Account Balance**

    Calculates and returns the real-time balance for a single account by processing all its debit and credit transactions.

      * **Endpoint:** `GET /accounts/{accountId}/balance`

      * **Example URL (for `acct_alice`):** `http://localhost:8080/accounts/acct_alice/balance`

      * **Success Response (JSON):**

        ```json
        {
          "accountId": "acct_alice",
          "balance": -150.75
        }
        ```

3.  **Get All Transactions for an Account**

    Returns a list of all transactions (both sent and received) for a single account.

      * **Endpoint:** `GET /accounts/{accountId}/transactions`

      * **Example URL (for `acct_alice`):** `http://localhost:8080/accounts/acct_alice/transactions`

      * **Success Response (JSON):**

        ```json
        [
          {
            "id": 1,
            "senderAccountId": "acct_alice",
            "receiverAccountId": "acct_bob",
            "amount": 150.75,
            "timestamp": "2025-11-14T10:30:00.000+00:00"
          }
        ]
        ```

### How to Run Locally

1.  **Clone the repository:**

    ```bash
    git clone https://github.com/your-username/your-repo-name.git
    cd your-repo-name
    ```

    *(Remember to replace `your-username` and `your-repo-name`)*

2.  **Set up your MySQL Database:**

      * Log in to your local MySQL instance and run:
        ```sql
        CREATE DATABASE transaction_ledger;
        ```

3.  **Configure your credentials:**

      * Open `src/main/resources/application.properties`.
      * Change the `spring.datasource.username` and `spring.datasource.password` properties to match your local MySQL `root` user or another user you have created.

4.  **Build and Run the application:**

      * The easiest way is to use the built-in Maven wrapper.
      * **On macOS/Linux:**
        ```bash
        ./mvnw spring-boot:run
        ```
      * **On Windows (PowerShell):**
        ```bash
        ./mvnw.cmd spring-boot:run
        ```

5.  The application will start and be available at `http://localhost:8080`.
