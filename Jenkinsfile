pipeline {
    agent any

    tools {
        maven 'maven'
    }

    environment {
        // Set a variable for the report path
        REPORT_PATH = "target/*.json"
    }

    stages {
        stage('Build') {
            steps {
                sh 'mvn clean compile'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn clean install -DskipTests=false'

                cucumber(
                    failedFeaturesNumber: -1,
                    failedScenariosNumber: -1,
                    failedStepsNumber: -1,
                    fileIncludePattern: REPORT_PATH, // Capture JSON reports
                    pendingStepsNumber: -1,
                    skippedStepsNumber: -1,
                    sortingMethod: 'ALPHABETICAL',
                    undefinedStepsNumber: -1
                )
            }
            post {
                always {
                    archiveArtifacts artifacts: 'target/*.json', allowEmptyArchive: true
                }
            }
        }

        stage('Archive') {
            steps {
                archiveArtifacts 'target/*.jar'
            }
        }
    }

    post {
        failure {
            echo 'Tests failed! Please check the Jenkins console output.'
        }
    }
}
