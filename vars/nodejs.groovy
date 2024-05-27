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
//        environment {
//            NEXUS = credentials('NEXUS')
//        }
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
                    sh 'npm install'
                    sh 'echo $TAG_NAME >VERSION'
                    sh 'zip -r ${component}-${TAG_NAME}.zip node_modules server.js VERSION'
                    sh 'curl -v -u admin:admin123 --upload-file ${component}-${TAG_NAME}.zip http://172.31.34.184:8081/repository/${component}/${component}-${TAG_NAME}.zip'
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