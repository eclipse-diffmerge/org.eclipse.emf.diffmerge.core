pipeline {
  agent { label 'migration' }
  tools {
        maven 'apache-maven-latest'
        jdk 'openjdk-jdk17-latest'
  }
  environment {
    FROM_PR = "${BRANCH_NAME}".contains("PR-");
    
    BUILD_KEY = "${BRANCH_NAME}-${BUILD_ID}".replaceFirst(/^v/, "").replaceAll('/','-');
    SUB_BRANCH = "${FROM_PR}".replace("true", "${CHANGE_TARGET}").replace("false", "${BRANCH_NAME}");
    
    SSH_ACCOUNT = "genie.diffmerge@projects-storage.eclipse.org"
    BUILD_DIR = "/home/data/httpd/download.eclipse.org/diffmerge/nightly/${BUILD_KEY}"
    
    NIGHTLY_KEY = "${BRANCH_NAME}".replaceFirst(/^v/, "").replaceAll('/','-');
    NIGHTLY_DIR = "/home/data/httpd/download.eclipse.org/diffmerge/nightly/${NIGHTLY_KEY}"
  }
  stages {
    stage('Package') {
      steps {
      	sh 'env'
        wrap([$class: 'Xvnc', takeScreenshot: false, useXauthority: true]) {
		  sh 'mvn clean install -t ${WORKSPACE}/releng/org.eclipse.emf.diffmerge.configuration/toolchains-hipp.xml -Psign -Pstandalone -Pgui.test'
		  junit allowEmptyResults: true, testResults: '**/target/surefire-reports/TEST-*.xml'
        }
      }
    }
	stage('Publish artifacts') {
      steps {
        sshagent ( ['projects-storage.eclipse.org-bot-ssh']) {
            sh "ssh $SSH_ACCOUNT mkdir -p $BUILD_DIR/emf-diffmerge-site"
            sh "scp -rp $WORKSPACE/releng/org.eclipse.emf.diffmerge.update/target/repository/* $SSH_ACCOUNT:$BUILD_DIR/emf-diffmerge-site"
            
            sh "ssh $SSH_ACCOUNT mkdir -p $BUILD_DIR/standalone"
            sh "scp -rp $WORKSPACE/releng/org.eclipse.emf.diffmerge.update/target/standalone/*dependencies*.jar $SSH_ACCOUNT:$BUILD_DIR/standalone/"
        }
        script {		
		     currentBuild.description = "${BUILD_KEY} - <a href=\"https://download.eclipse.org/diffmerge/nightly/${BUILD_KEY}\">site</a>" 				
	   	}
	  }
    }
    stage('Publish nightly') {
    	when {
       		expression {
       			!"${BRANCH_NAME}".contains('PR-')
       		}
   		}
   		steps {
	        sshagent ( ['projects-storage.eclipse.org-bot-ssh']) {
	            sh "ssh ${SSH_ACCOUNT} mkdir -p ${NIGHTLY_DIR}"
	            sh "ssh ${SSH_ACCOUNT} rm -rf ${NIGHTLY_DIR}/emf-diffmerge-site"
	            sh "ssh ${SSH_ACCOUNT} cp -r ${BUILD_DIR}/emf-diffmerge-site ${NIGHTLY_DIR}"
	            sh "ssh ${SSH_ACCOUNT} rm -rf ${NIGHTLY_DIR}/standalone"
	            sh "ssh ${SSH_ACCOUNT} cp -r ${BUILD_DIR}/standalone ${NIGHTLY_DIR}"
	        }
		}
    }
    stage('Trigger childs') {
      steps {
        build job: "diffmerge-coevolution/${SUB_BRANCH}", parameters: [string(name: 'CORE_BRANCH', value:"${BUILD_KEY}")], wait: false
        build job: "diffmerge-patterns/${SUB_BRANCH}", parameters: [string(name: 'CORE_BRANCH', value:"${BUILD_KEY}")], wait: false
      }
    }
  }
  
  post {
    always {
      archiveArtifacts artifacts: '**/*.log, *.log, **/*.xml, *.exec', allowEmptyArchive: true
    }
  }
}