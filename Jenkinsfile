pipeline {

    agent any

    tools {
        jdk 'jdk-14'
        gradle 'gradle-6.3'
    }

    stages {

        stage('Compile and test') {
            steps {
                sh "gradle bootJar"
            }
        }

        stage('Static code analysis') {
            steps {
                withCredentials([string(credentialsId: 'sonarqube-token', variable: 'SONAR_LOGIN_TOKEN')]) {
                    sh "gradle sonarqube -Dsonar.login=$SONAR_LOGIN_TOKEN"
                }
            }
            post {
                always {
                    junit 'build/test-results/test/*.xml'
                }
            }
        }

    }

}
