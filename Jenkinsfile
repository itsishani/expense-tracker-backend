pipeline {
    agent any

    tools {
        maven 'Maven 3.9.6'   // Name must match the Maven tool configured in Jenkins
        jdk 'Java 17'         // Optional if already available
    }

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/itsishani/expense-tracker-backend.git'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean install'
            }
        }

        stage('Unit Test') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Package') {
            steps {
                sh 'mvn package'
            }
        }

        stage('Docker Build') {
            steps {
                sh 'docker build -t expense-tracker-app .'
            }
        }

        stage('Docker Run') {
            steps {
                sh 'docker run -d -p 8080:8080 --name expense-app expense-tracker-app'
            }
        }
    }

    post {
        always {
            echo 'Pipeline finished.'
        }
    }
}
