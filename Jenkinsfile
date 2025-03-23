pipeline {
    agent any
    environment {
        DOCKERHUB_CREDENTIALS_ID = 'viettranni'
        // Docker Hub repository name
        DOCKERHUB_REPO = 'viettranni/localizedgreetingapp'
        // Docker image tag
        DOCKER_IMAGE_TAG = 'latest_v1'
    }
    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/Viettranni/LocalizedGreetingApp.git'
            }
        }
        stage('Build') {
            steps {
                sh 'mvn clean install'
            }
        }
        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }
        stage('Code Coverage') {
            steps {
                sh 'mvn jacoco:report'
            }
        }
        stage('Publish Test Results') {
            steps {
                junit '**/target/surefire-reports/*.xml'
            }
        }
        stage('Publish Coverage Report') {
            steps {
                jacoco()
            }
        }
        stage('Set up Docker Buildx') {
            steps {
                script {
                    sh 'docker buildx create --use || true'
                    sh 'docker buildx inspect --bootstrap'
                }
            }
        }
        stage('Build and Push Multi-Platform Docker Image') {
            steps {
                script {
                    sh """
                        docker login -u ${DOCKERHUB_CREDENTIALS_ID} -p \$(cat /run/secrets/dockerhub_password)
                        docker buildx build --platform linux/amd64,linux/arm64 \
                        -t ${DOCKERHUB_REPO}:${DOCKER_IMAGE_TAG} --push .
                    """
                }
            }
        }
    }
}
