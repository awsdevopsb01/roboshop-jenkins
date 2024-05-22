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
                    sh ' sonar-scanner -Dsonar.projectKey=${component} -Dsonar.host.url=http://172.31.43.16:9000'
                }
            }
        }
        stages {
            stage('Unit Test Cases') {
                steps {
                    sh 'echo Unit Test Cases'
                }
            }
        }
        stages {
            stage('Checkmarx SAST Scan') {
                steps {
                    sh 'echo Unit Test Cases'
                }
            }
        }
        stages {
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