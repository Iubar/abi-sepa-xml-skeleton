pipeline {
    agent {    
    	docker {    		
    		image 'iubar-maven-alpine'
    		label 'docker'
    		args '-e MAVEN_CONFIG=/home/jenkins/.m2 -v $HOME/.m2:/home/jenkins/.m2:rw,z'
    	} 
    }
    stages {
        stage ('Build') {
            steps {
            	echo 'Building...'
                sh 'mvn -B -DskipTests=true clean package'
            }
        }
		stage('Test') {
            steps {
            	echo 'Testing...'
                sh 'mvn -B test -Djava.io.tmpdir=${WORKSPACE}@tmp'
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml' // show junit log in Jenkins 
                }
            }
        }                      
        stage('Analyze') {
            steps {
                sh 'sonar-scanner'
            }
        }
    }
	post {
        always {
            echo 'Cleaning the workspace...'
            deleteDir()
			dir("${env.WORKSPACE}@tmp") {
			  echo 'Cleaning ${env.WORKSPACE}@tmp ...'
			  deleteDir()
			}
        }
    }    
}