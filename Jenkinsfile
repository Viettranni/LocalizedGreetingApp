pipeline {
    agent any
    environment {
        // Docker Hub repository name
        DOCKERHUB_REPO = 'viettranni/localizedgreetingapp'
        // Docker image tag
        DOCKER_IMAGE_TAG = 'latest_v3'
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
                    withCredentials([usernamePassword(credentialsId: 'dockerhub_credentials', usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
                        sh """
                            echo "$DOCKER_PASS" | docker login -u "$DOCKER_USER" --password-stdin
                            docker buildx build --platform linux/amd64,linux/arm64 \
                            -t ${DOCKERHUB_REPO}:${DOCKER_IMAGE_TAG} --push .
                        """
                    }
                }
            }
        }
    }
}
