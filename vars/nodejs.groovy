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
                    sh 'echo Code Quality'
//                    sh ' sonar-scanner -Dsonar.projectKey=${component} -Dsonar.host.url=http://172.31.37.59:9000 -Dsonar.login=admin -Dsonar.password=admin123 -Dsonar.qualitygate.wait=true'
                    }
            }
            stage('Unit Test Cases') {
                steps {
                    npm test
                }
            }
            stage('Checkmarx SAST Scan') {
                steps {
                    sh 'echo SAST Scan'
                }
            }

            stage('Checkmarx SCA Scan') {
                steps {
                    sh 'echo SCA Scan'
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