pipeline {

    agent any

    tools {
        jdk 'jdk-14'
        gradle 'gradle-6.3'
    }

    stages {

        stage('Compile and run tests') {
            steps {
                sh "gradle clean check"
            }
            post {
                always {
                    junit 'build/test-results/test/*.xml'
                }
            }
        }

        stage('Build fat jar') {
            steps {
                sh "gradle bootJar"
            }
        }

        stage('Static code analysis') {
            when { branch 'master' }
            steps {
                withCredentials([string(credentialsId: 'sonarqube-token', variable: 'SONAR_LOGIN_TOKEN')]) {
                    sh "gradle sonarqube -Dsonar.login=$SONAR_LOGIN_TOKEN"
                }
            }
        }

    }

}
