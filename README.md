Copy code
# Repository Setup and Application Deployment Guide

This guide provides a series of steps to help you set up and deploy the "invygo" application locally. Follow these instructions to successfully clone the repository, build a Docker container, and import associated APIs using Postman.

## Step 1: Clone the Repository

1. Open your terminal.
2. Navigate to the directory where you want to clone the repository.
3. Run the following command to clone the repository:

   ```bash
   git clone <repository_url>
## Step 2: Navigate to the Project Directory
1. Change your working directory to the "invygo" project:

   ```bash
   cd invygo
## Step 3: Build the Docker Image
1. Ensure that Docker is installed and running on your machine.

2. Run the following command to build the Docker image for the "invygo-app" application:

   ```bash
   docker build -t invygo-app .
## Step 4: Run the Docker Container
1. Execute the following command to start a Docker container from the built image. This will forward port 8080 from the container to your host machine:

   ```bash
   docker run -p 8080:8080 invygo-app

This will start the "invygo-app" container, making the application accessible at http://localhost:8080.

## Step 5: Import APIs in Postman
1. Ensure that you have Postman installed on your machine. If not, you can download it from the official website.
Open Postman.
2. Click on the "Import" button located at the top left corner of the Postman window.
3. Select the "Import From File" option.
4. Navigate to the base location of the cloned repository and choose the "api.yaml" file.
5. Postman will import the API definitions from the YAML file.
6. Congratulations! You have successfully set up and deployed the "invygo" application locally using Docker and imported the APIs into Postman. You are now able to explore the application and test its APIs using Postman.

Please note that this guide assumes you have the necessary prerequisites (Git, Docker, and Postman) installed on your machine. If you encounter any issues during the setup process, refer to the documentation for each tool or platform for troubleshooting assistance.
