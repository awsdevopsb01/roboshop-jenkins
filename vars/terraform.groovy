def call() {
    pipeline {
        agent {
            node {
                label 'workstation'
            }
        }

        parameters {
            choice(name: 'env', choices ['dev','prod'], description: 'Pick your choice of Environment')
        }

        stages {
            stage('Terraform init')
            steps {
                sh 'terraform init -backend-config=env-${env}/state.tfvars'
            }
            stage ('Terraform Apply')
            steps {
                sh 'terraform apply -auto-approve -var-file=env-${env}/main.tfvars'
            }
        }

        post {
            always {
                cleanWs()
            }
        }

    }
}