# Mar-1 Hotel System - MHS

Welcome to Fall 2023 ECSE 321's Group 05 repository!

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

# for UNIX-based OS (Linux, macOS, etc.)
chmod +x gradlew 
./gradlew build 
./gradlew build -xtest # Build without testing
./gradlew test  # Run the tests

# for Windows
gradlew.bat build # Build + test 
gradlew.bat build -xtest # Build the project without testing
gradlew.bat test # Run the tests
```

- Our database is open to the public and pre-configured, so you don't need to configure anything to run the project. However, if you want to run the project locally, you can change the database configuration in `backend/src/main/resources/application.properties`: (replace `YOUR_DATABASE` and `PASSWORD` with your own database name and password)
```properties
server.port = ${PORT:8080}

spring.datasource.driver-class-name=org.postgresql.Driver
spring.jpa.hibernate.ddl-auto=update

spring.datasource.url=jdbc:postgresql://localhost:5432/YOUR_DATABASE
spring.datasource.username=postgres
spring.datasource.password=PASSWORD
```

### Frontend (To Be Implemented)
- To start
```bash
cd frontend
npm install
```

## The developer team
| Name | Program - Year | GitHub Profile |
| ---- | ------- | ----- |
| Adam Corbier | Computer Eng. - U2 | [Ad2Am2](https://github.com/Ad2Am2) |
| Antoine Phan | Electrical Eng. - U2 | [notkaramel](https://github.com/notkaramel) | 
| Bilar Mohktari | Software Eng. - U2 | [bmokhtari](https://github.com/bmokhtari) |
| Emma Friesen | Chemical Eng. - U4 | [emma-friesen](https://github.com/emma-friesen) |
| Lucas Pacicco | Eletrical Eng. - U3 | [Lucaspac5](https://github.com/Lucaspac5) |
| Zi Xu Liu | Computer Eng. - U2 | [ARandomPi](https://github.com/ARandomPi) |

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
