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
            stage('code compile') {
                steps {
                    sh 'mvn compile'
                }
            }

            stage('Code Quality') {
                steps {
                    sh 'echo Code Quality'
//                    sh ' sonar-scanner -Dsonar.projectKey=${component} -Dsonar.host.url=http://172.31.37.59:9000 -Dsonar.login=admin -Dsonar.password=admin123 -Dsonar.qualitygate.wait=true -Dsonar.java.binaries=./target'
                }
            }

            stage('Unit Test Cases') {
                steps {
                    sh 'mvn test'
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