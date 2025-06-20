pipeline {
    agent any

    tools {
        maven 'Maven 3.9.6'  // Make sure this exact name matches your Jenkins Maven tool config
        jdk 'Java 17'        // Optional if you're already using Java 17 globally
    }

    environment {
        IMAGE_NAME = 'expense-tracker-app'
        CONTAINER_NAME = 'expense-app'
    }

    stages {

        stage('Checkout') {
            steps {
                git url: 'https://github.com/itsishani/expense-tracker-backend.git', branch: 'master'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean install -DskipTests'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn test'
               // junit 'target/surefire-reports/*.xml'
            }
        }

        stage('Package') {
            steps {
                sh 'mvn package -DskipTests'
            }
        }

        stage('Docker Build') {
            steps {
                sh 'docker build -t $IMAGE_NAME .'
            }
        }

        stage('Docker Cleanup (if running)') {
            steps {
                sh '''
                    if [ $(docker ps -aq -f name=$CONTAINER_NAME) ]; then
                        docker rm -f $CONTAINER_NAME
                    fi
                '''
            }
        }

        stage('Docker Run') {
            steps {
                sh 'docker run -d -p 8080:8080 --name $CONTAINER_NAME $IMAGE_NAME'
            }
        }
    }

    post {
        always {
            echo '🔁 CI pipeline completed.'
        }
        success {
            echo '✅ Build succeeded!'
        }
        failure {
            echo '❌ Build failed!'
        }
    }
}
