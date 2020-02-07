pipeline {
    agent any
    stages {
        stage('Clone repo and clean') {
            steps {
              sh "mvn clean -f test-pipeline"
            }
        }
        stage('Test'){
	         steps{
		          sh "mvn test"
	           }
        }
        stage('Deploy'){
	        steps{
		         sh "mvn package"
	       }
        }
    }
}
