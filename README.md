# <p align="center">Vet Management System</p>

## <p align="center">The application will be helper to management veterinary clinic services with recording datas about; animals, doctors, appointmens and much more you can learn more details about application by continue reading.</p>

### Details of application:
- User can record animals which are came to clinic with owner of animal record. Thus user can store which animal brought by whom and if something need to change, user can make that.
- User can add doctors to database and create appointments based on available dates of doctors. After that user can make changes on that records.
- User can create records about vaccines which are applied to animals and it prevent to apply the same vaccine to an animal via controlling expiring dates.

### The technologies which are using by the application:
- Java 21
- Spring Boot
- Spring Data JPA
- PostgreSQL / MySQL
- Swagger
- IntelliJ IDEA

## Installation

Before you begin, ensure you have the following installed on your machine:

- **Java Development Kit (JDK) 22**: [Download and Install JDK](https://www.oracle.com/java/technologies/javase-jdk21-downloads.html)
- **Maven**: [Download and Install Maven](https://maven.apache.org/install.html)
- **PostgreSQL**: [Download PostgreSQL](https://www.postgresql.org/download/)
- **Git**: [Download and Install Git](https://git-scm.com/book/en/v2/Getting-Started-Installing-Git)
- **IntelliJ IDEA** (or another IDE): [Download IntelliJ IDEA](https://www.jetbrains.com/idea/download/)


### Clone

1. Clone the repository: `git clone https://github.com/mhmtygmr0/Vet-Management-System`
2. Navigate to the project directory: `cd vet_management_system`
3. Open the project in your IDE.

### Database Setup

1. Create a database named `vet_management_system` in PostgreSQL.
2. Update the database configuration in `src/main/resources/application.properties` with your database details.
3. Import the `vet_management_system.sql` file to create the necessary tables and initial data.
4. Run the project.

## Database Design

The system uses a relational database with the following tables:

- `animals`: Stores animal information.
- `customers`: Stores customer information.
- `vaccines`: Stores vaccine information.
- `doctors`: Stores doctor information.
- `available_dates`: Stores doctor's available dates.
- `appointments`: Stores appointment details.


## UML Diagram
A UML diagram illustrating the system architecture and relationships between entities is included below.
![uml (1)](https://github.com/user-attachments/assets/787e8d26-3771-42c9-9037-e599245cad75)
