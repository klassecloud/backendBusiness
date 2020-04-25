pipeline {

    agent any

    tools {
        jdk 'jdk-14'
        gradle 'gradle-6.3'
    }

    environment {
        GRADLE_USER_HOME = "$WORKSPACE/.build"
    }

    stages {
        stage('Supply credentials') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'artifactory-as-andreas', passwordVariable: 'ARTIFACTORY_PASSWORD', usernameVariable: 'ARTIFACTORY_USER')]) {
                    sh "mkdir $GRADLE_USER_HOME || true"
                    sh "printf \"\nartifactory_user=$ARTIFACTORY_USER\nartifactory_password=$ARTIFACTORY_PASSWORD\" >> $GRADLE_USER_HOME/gradle.properties"
                }
            }
        }

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

        stage('Publish on artifactory') {
            when { branch 'master' }
            steps {
                sh "gradle publish"
            }
        }

    }

}
