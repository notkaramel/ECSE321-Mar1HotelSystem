# Mar-1 Hotel System - MHS

Welcome to Fall 2023 ECSE 321's Group 05 repository! Please check our [wiki](https://github.com/notkaramel/ECSE321-Mar1HotelSystem/wiki) for more information about our project.

## Getting started
- Clone the project
```bash
# Clone the repository using HTTPS
git clone https://github.com/McGill-ECSE321-Fall2023/project-group-05.git

# Clone the repository using SSH
git clone git@github.com:McGill-ECSE321-Fall2023/project-group-05.git
```
- Change directory to the project
```bash
cd project-group-05
```

### Backend
- To build & test our project:
```bash
cd backend

# for UNIX-like OS (Linux, macOS, etc.)
chmod +x gradlew 
./gradlew build 

# for Windows
gradlew.bat build # Build + test 
```

- Our database is [open to the public and pre-configured](https://github.com/McGill-ECSE321-Fall2023/project-group-05/wiki/0.-Developer-Guide#database-privacy-concerns), so you don't need to configure anything to run the project. However, if you want to run the project locally, you can change the database configuration in `backend/src/main/resources/application.properties`: (replace `YOUR_DATABASE` and `PASSWORD` with your own database name and password)
```properties
server.port = ${PORT:8080}

spring.datasource.driver-class-name=org.postgresql.Driver
spring.jpa.hibernate.ddl-auto=update

spring.datasource.url=jdbc:postgresql://localhost:5432/YOUR_DATABASE
spring.datasource.username=postgres
spring.datasource.password=PASSWORD
```

### Frontend
- All the frontend files are in the `frontend` folder.
- To start
```bash
cd frontend
npm install # to install dependencies
```
- We use [Vite](https://vitejs.dev/) to develop and build our frontend. Our project requires an `.env` file to be able to connect to the backend. You can create a `.env` file in the `frontend` folder and add the following line to it:
```
VITE_BACKEND = "http://localhost:8080" 
```
> Note that localhost:8080 is the default backend URL. Please change the URL if you are using a different backend URL. 

- To start the development server, run:
```bash
npm run dev # to start the development server
npm run build # to build the project for production
```

## Project structure
```bash
.
├── backend # The backend of the project
│   ├── build.gradle
│   ├── gradle
│   │   └── wrapper
│   │       ├── gradle-wrapper.jar
│   │       └── gradle-wrapper.properties
│   ├── gradlew
│   ├── gradlew.bat
│   ├── HELP.md
│   ├── settings.gradle
│   └── src
│       ├── main
│       │   ├── java/ca/mcgill/ecse321/Mar1HotelSystem
│       │   │   ├── model # The domain model of the project
│       │   │   ├── dao # The data access objects (CRUD) 
│       │   │   └── Mar1HotelSystemApplication.java # Main app
│       │   └── resources
│       │       ├── application.properties # App configuration 
│       │       ├── static
│       │       └── templates
│       └── test/java/ca/mcgill/ecse321/Mar1HotelSystem
│           ├── tests # CRUD - Database test files
│           └── Mar1HotelSystemApplicationTests.java # Test file
├── documentation # Documentation: UML diagrams, Umple code, etc.
│   ├── README.md
│   ├── UMLCodes
│   │   ├── # PlantUML, Umple code
│   │   ├── UseCaseDiagram.plantuml
│   │   ├── DomainModel.ump
│   │   └── ...
│   └── UMLDiagrams
│       ├── # Figures (PNG, JPG, SVG format)
│       ├── UseCaseDiagram.png
│       └── ...
├── frontend # The frontend of the project
│   ├── index.html
│   ├── package.json
│   ├── package-lock.json
│   └── README.md
├── LICENSE
└── README.md # This document
```
