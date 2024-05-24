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
                    sh ' sonar-scanner -Dsonar.projectKey=${component} -Dsonar.host.url=http://172.31.37.59:9000 -Dsonar.login=admin -Dsonar.password=admin123 -Dsonar.qualitygate.wait=true'
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