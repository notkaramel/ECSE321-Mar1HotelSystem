# Marwaan's Hotel System - MHS

Welcome to Fall 2023 ECSE 321's Group 05 repository!

## Getting started
```bash
# Clone the repository using HTTPS
git clone https://github.com/McGill-ECSE321-Fall2023/project-group-05.git

# Clone the repository using SSH
git clone git@github.com:McGill-ECSE321-Fall2023/project-group-05.git
```

- In the `backend/src/main/resources/` folder, add a `.env` file with the following fields:
```env
USERNAME=
PORT=
HOST=
DATABASE=
PASSWORD=
```
- For example, the default setting from the tutorial:
```env
USERNAME=postgres
PORT=5432
HOST=localhost
DATABASE=event_registration # it should be your hotel system database
PASSWORD= # your postgresql password
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
│       │   ├── java/ca/mcgill/ecse321/MarwaanHotelSystem
│       │   │   └── MarwaanHotelSystemApplication.java # Main app
│       │   └── resources
│       │       ├── application.properties # App configuration 
│       │       ├── static
│       │       └── templates
│       └── test/java/ca/mcgill/ecse321/MarwaanHotelSystem
│           └── MarwaanHotelSystemApplicationTests.java # Test file
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
