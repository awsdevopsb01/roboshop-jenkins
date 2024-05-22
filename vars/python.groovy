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
            stage('code quality') {
                steps {
                    sh 'echo Code Quality'
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