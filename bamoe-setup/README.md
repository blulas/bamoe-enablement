# Installing IBM Business Automation Manager Open Editions on a Local Workstation

This repository sets up the required infrastructure services in order to run IBM Business Automation Manager Open Editions (BAMOE) using `docker-compose`, allowing for a customized setup based on subscription entitlements and use case requirements, including:

- BAMOE Maven Repository (standalone)
- BAMOE Canvas (standalone) - 
- BAMOE (Maven, Canvas, Keycloak, Management & Task Consoles, no PostgreSQL) - 

## Requirements for Local Machine Setup
The following instructions are helpful in setting up your local environment in order to do BAMOE development.  All functions of BAMOE are available from the developer workstation, including web-based tools and consoles.

- **JDK 17** (https://developer.ibm.com/languages/java/semeru-runtimes/downloads/), prefer the IBM Semeru release of JDK, but any OpenJDK will do
- **GIT Command Line Interface** (https://git-scm.com/downloads), plus you are free to install any GIT related extensions or simply use the command line tools
- **Maven Command Line Interface** (https://maven.apache.org/install.html), used for builds and deployments of BAMOE libraries, plus you are free to install any Maven related extensions or simply use command line tools.
- **VS Code IDE** (https://code.visualstudio.com/download), and install the following extensions from the VS Code Marketplace:
BAMOE Developer Tools, this is the set of editors for DMN, BPMN, and PMML that developers use to create their visual models in the IDE Drools (by Jim Moody), this is a third-party editor which does simple syntax highlighting of the Drools Rule Language (DRL) files

## Running Container Images Locally
There are several pre-built container images which assist the developer.  These images require a container management system, such as **Docker**, **PodMan**, or **Rancher Desktop**.  Most BAMOE technologists use **Rancher Desktop**, which can be run in `docker` mode, and we can supply a startup repository that installs Canvas and other images into your Rancher installation.  If you plan to install the container images on your laptop, we will also guide you through this, but here are the instructions if you want to get ahead.  

You need `Docker Compose` to run the BAMOE images locally. Since Docker Desktop is no longer supported by IBM, the easiest way is to use Rancher Desktop.  Click [here](https://docs.rancherdesktop.io/getting-started/installation/) in order to get instructions on how to install Rancher Desktop in Docker mode.  

Running the `docker-compose.yaml` as is will build the system as a sample environment pulling in the requisite containers. To do this you can follow these steps.

1. Clone this repository

    ~~~shell
    git clone https://github.com/blulas/bamoe-setup.git
    ~~~

2. Bring up the services through a docker compose up command

    ~~~shell
    docker compose -f docker-compose/$CUSTOM_DOCKER_COMPOSE.YML up
    ~~~

Choices are:

- **BAMOE Maven Repository** `docker compose -f docker-compose/bamoe-maven.yml up`
- **BAMOE Canvas** `docker compose -f docker-compose/bamoe-canvas.yml up`
- **BAMOE All (Maven, Canvas, Keycloak, Management & Task Consoles)** `docker compose -f docker-compose/bamoe-all.yml up`

## Installed Services
Services installed during this process can be accessed at:

- For IBM BAMOE Canvas, you can access this by default at [IBM BAMOE Canvas](http://localhost:8000), which is configured in the compose yaml with various settings. These can be further customized, for documentation on it refer to the [Quay Image Repository](https://quay.io/repository/bamoe/canvas) and the [Product Documentation](https://www.ibm.com/docs/en/ibamoe/9.1.1).

- For IBM BAMOE Management Console, you can access this by default at [IBM BAMOE Management Console](http://localhost:8280).  For documentation on it refer to the [Product Documentation](https://www.ibm.com/docs/en/ibamoe/9.1.1).

- For IBM BAMOE Task Console, you can access this by default at [IBM BAMOE Task Console](http://localhost:8380).  For documentation on it refer to the [Product Documentation](https://www.ibm.com/docs/en/ibamoe/9.1.1).

## Maven Repository

For more information on how to properly setup Maven for local development, visit [Setting Up Maven](./maven/README.md).
