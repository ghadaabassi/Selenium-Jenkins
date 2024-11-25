pipeline {
    agent any

    tools {
        maven 'maven'  // Ensure the correct Maven tool is installed in Jenkins
    }

    environment {
        // Set a variable for the report path
        REPORT_PATH = "target/*.json"
    }

    stages {
        stage('Build') {
            steps {
                // Clean and compile the project
                sh 'mvn clean compile'
            }
        }

        stage('Test') {
            steps {
                // Run tests and generate Cucumber JSON reports
                sh 'mvn clean install -DskipTests=false'

                // Execute Cucumber step for capturing test results
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
                    // Archive test results (e.g., Surefire reports and Cucumber reports)
                    archiveArtifacts artifacts: 'target/*.json', allowEmptyArchive: true
                }
            }
        }

        stage('Archive') {
            steps {
                // Archive any relevant artifacts like jars, reports, etc.
                archiveArtifacts 'target/*.jar'
            }
        }
    }

    post {
        // Clean up or handle failure steps if needed
        failure {
            // Optionally, send failure notifications or perform other actions
            echo 'Tests failed! Please check the Jenkins console output.'
        }
    }
}
