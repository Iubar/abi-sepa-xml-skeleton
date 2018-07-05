<<<<<<< HEAD
=======
// iubar-auto-update
>>>>>>> 59ffa94a8d1698d285f67b62b9b4077779c57707
pipeline {
    agent {    
    	docker {   	
    		image 'iubar-maven-alpine'
    		label 'docker'
<<<<<<< HEAD
    		args '-v $HOME/.m2:/home/jenkins/.m2:rw,z'
    	} 
    }
    stages {
		stage ('Upstream build') {
			steps {
				build job: 'iubar-gcalendar'
			}
		}
        stage ('Build') {
            steps {
=======
    		args '-e MAVEN_CONFIG=/home/jenkins/.m2 -v $HOME/.m2:/home/jenkins/.m2:rw,z'
    	} 
    }
    stages {
        stage ('Build') {
            steps {
            	echo 'Building...'
>>>>>>> 59ffa94a8d1698d285f67b62b9b4077779c57707
                sh 'mvn -B -DskipTests=true clean package'
            }
        }
		stage('Test') {
            steps {
<<<<<<< HEAD
=======
            	echo 'Testing...'
>>>>>>> 59ffa94a8d1698d285f67b62b9b4077779c57707
                sh 'mvn -B -Djava.io.tmpdir=${WORKSPACE}@tmp -Djava.awt.headless=true test'
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml' // show junit log in Jenkins 
                }
            }
        }
<<<<<<< HEAD
        stage('Analyze') {
            steps {
				script {
					try {
						sh 'sonar-scanner'
					} catch (err){
						echo "SonarQube: analyze failed !!!"
					}
				}
            }
        }
        stage('Quality gate') {
            environment { 
				SONAR_PROJECTKEY = 'java:abi-sepa-hello'
            }		
            steps {
				sh '''
				    QUALITYGATE=$(curl http://192.168.0.117:9000/api/qualitygates/project_status?projectKey=$SONAR_PROJECTKEY | jq '.projectStatus.status')
				    QUALITYGATE=$(echo "$QUALITYGATE" | sed -e 's/^"//' -e 's/"$//')
				    echo "QUALITYGATE: ${QUALITYGATE}"
                    if [ $QUALITYGATE = OK ]; then
                       echo "High five !"
                    else
                       echo "Poor quality !"
					   echo "( see http://192.168.0.117:9000/dashboard?id=$SONAR_PROJECTKEY)"
                       exit 1
                    fi				    
				'''
            }
        }
		stage ('Deploy') {
            steps {
            	echo 'Deploying...'
                sh 'mvn -B -DskipTests=true deploy'
            }
        }		
=======
		stage ('Deploy') {
            steps {
            	echo 'Deploying...'
                sh 'mvn -B -DskipTests=true jar:jar deploy:deploy'
            }
        }
        stage('Analyze') {
            steps {
                sh 'sonar-scanner'
            }
        }
>>>>>>> 59ffa94a8d1698d285f67b62b9b4077779c57707
    }
	post {
        changed {
        	echo "CURRENT STATUS: ${currentBuild.currentResult}"
            sh "curl -H 'JENKINS: Pipeline Hook Iubar' -i -X GET -G ${env.IUBAR_WEBHOOK_URL} -d status=${currentBuild.currentResult} -d project_name=${JOB_NAME}"
        }
		cleanup {
			cleanWs()
			dir("${env.WORKSPACE}@tmp") {				
				deleteDir()
			}
        }
    }    
}