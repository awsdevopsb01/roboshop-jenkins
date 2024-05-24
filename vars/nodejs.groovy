def call() {
    pipeline {

        agent {
            node {
                label 'workstation'
            }
        }

        options {
            ansiColor('xterm')
        }

        stages {
            stage('Code Quality') {
                steps {
                    sh ' sonar-scanner -Dsonar.projectKey=${component} -Dsonar.host.url=http://172.31.47.32:9000 -Dsonar.login=admin -Dsonar.password=admin123'
                }
            }

            stage('Unit Test Cases') {
                steps {
                    sh 'echo Unit Test Cases'
                }
            }
            stage('Checkmarx SAST Scan') {
                steps {
                    sh 'echo Unit Test Cases'
                }
            }

            stage('Checkmarx SCA Scan') {
                steps {
                    sh 'echo Unit Test Cases'
                }
            }
        }

        post {
            always {
                cleanWs()
            }
        }

    }
}