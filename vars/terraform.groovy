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
        parameters {
            choice(name: 'env', choices: ['dev','prod'], description: 'Pick your choice of Environment')
            choice(name: 'action', choices: ['apply','destroy'], description: 'parameter to apply or destroy infra')
        }

        stages {

            stage('Terraform init') {
                steps {
                    sh 'terraform init -backend-config=env-${env}/state.tfvars'
                }
            }

            stage ('Terraform Apply') {
                steps {
                    sh 'terraform ${action} -auto-approve -var-file=env-${env}/main.tfvars'
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