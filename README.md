# DevOpsify

Description
------------
An assistance platform made using Spring framework that analyses your code, and helps you either to start a devops
project, or to turn an existing project into a devops project using open source software (Git, Docker, Jenkins..)

The main aim of this project is to develop a server application that automizes some parts of
the DevOps cycle, does a DevOps analyze for projects and gives some feedback to developers
on thing to improve.

UseCases
------------
So far, the only use case we have is creating a new project and generating jenkinsfiles, dockerfiles while creating a
new pipeline in jenkins
![eza](https://svgshare.com/i/iPw.svg)

##### TODO:

add import project

Javadocs
------------
javadocs are to be found here : [DevOpsify Javadocs](https://oubaydos.github.io/DevOpsify)


Contributing
------------
This is a new project, so we welcome all contributions, feel free to open a pull request

Project Structure
--------

    .
    ├── ...
    ├── devopsify-backend                       # the backend
        ├── ...    
            ├── Service                         # logic of the application
                ├── ProjectService              # service for creating and managing projects
                ├── UserService                 # service for managing users
                ├── technologies                # chosen devops technologies
                    ├── docker                  # docker & dockerfile service 
                    ├── git                     # git operations 
                    ├── github                  # github service 
                    ├── jenkins                 # jenkins & jenkinsfile service 
                    ├── maven                   # maven service 
                    ├── nexus                   # todo : nexus service 
    ├── devopsify-frontend                      # the frontend
        ├── ...      
            ├── components
            ├── api                             # service layer
            ├── utils                           # utilities layer
    ├── README.md, LICENSE...   # other files

To build sources locally follow these instructions.

### Tests

we just started working on unit tests.

integration tests are not yet started.

you are welcome to help on that matter.

Technologies
--------

* Java
* Python
* Groovy
* Spring boot
* ReactJs
* Mui (Material ui)
* Figma
* [github api](https://github.com/hub4j/github-api)
* [jenkins api](https://github.com/cdancy/jenkins-rest)
* [jenkins api for python](https://github.com/joelee2012/api4jenkins)
* [JGit](https://www.eclipse.org/jgit/)
* [maven plugin API](https://maven.apache.org/ref/3.8.6/maven-plugin-api/)
* PostgreSQL
* Hibernate
* Maven
* JUNIT 5
* Docker
* Jenkins

Commands
--------
to run the backend use the command

```bash
cd devopsify-backend
mvn spring-boot:run
```

to run the frontend use the command:

first install dependencies

```bash
cd devopsify-frontend
npm i # to install dependencies
```

then run the project

```bash
cd devopsify-frontend
npm start
```

Project Status
-------
this project is _in progress_, many things are not yet started

License
-------

Copyright (c) 2021 obaydah bouifadene & hamza ben yazid

Licensed under
the [![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

Contact
-------
Reach us here:
* b.obaydah@gmail.com
* hamzabyazid@gmail.com
* [LinkedIn 1](https://www.linkedin.com/in/oubaydos)
* [LinkedIn 2](https://www.linkedin.com/in/hamza-benyazid)
