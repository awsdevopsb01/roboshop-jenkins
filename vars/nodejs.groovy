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
                    sh 'echo Unit Testcases'
//                    sh 'npm test'
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
            stage('Release') {
                when {
                    expression {
                        env.TAG_NAME==~".*"
                    }
                }
                steps {
                    sh 'echo Release'
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