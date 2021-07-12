pipeline {
  agent { label 'migration' }
  tools {
        maven 'apache-maven-latest'
        jdk 'oracle-jdk8-latest'
  }
  environment {
    FROM_GITHUB = "${BRANCH_NAME}".contains("PR-");
    DM_BRANCH = "${FROM_GITHUB}".replace("true", "${CHANGE_BRANCH}").replace("false","${BRANCH_NAME}");
    
    BUILD_KEY = "${BRANCH_NAME}-${BUILD_ID}".replaceFirst(/^v/, "").replaceAll('/','-');
    SSH_ACCOUNT = "genie.diffmerge@projects-storage.eclipse.org"
    TARGET_DIR = "/home/data/httpd/download.eclipse.org/diffmerge/nightly/${BUILD_KEY}/emf-diffmerge-site"
    STANDALONE_DIR = "/home/data/httpd/download.eclipse.org/diffmerge/nightly/${BUILD_KEY}/standalone"
  }
  stages {
    stage('Package') {
      steps {
      	sh 'env'
        sh 'mvn clean install -t ${WORKSPACE}/releng/org.eclipse.emf.diffmerge.configuration/toolchains-hipp.xml -Psign -Pstandalone -Pgui.test'
      }
    }
    stage('Archive artifacts') {
      steps {
        archiveArtifacts artifacts:  "releng/org.eclipse.emf.diffmerge.update/target/repository/**, releng/org.eclipse.emf.diffmerge.update/target/org.eclipse.emf.diffmerge.update-*.zip, releng/org.eclipse.emf.diffmerge.update/target/standalone/**, releng/org.eclipse.emf.diffmerge.update/target/javadoc/**, tests/org.eclipse.emf.diffmerge.tests.ui/target/results/**"
      }
    }
	stage('Publish artifacts') {
      steps {
        sshagent ( ['projects-storage.eclipse.org-bot-ssh']) {
            sh "ssh $SSH_ACCOUNT mkdir -p $TARGET_DIR"
            sh "scp -rp $WORKSPACE/releng/org.eclipse.emf.diffmerge.update/target/repository/* $SSH_ACCOUNT:$TARGET_DIR"
        }
        script {		
		     currentBuild.description = "${BUILD_KEY} - <a href=\"https://download.eclipse.org/diffmerge/nightly/${BUILD_KEY}\">site</a>" 				
	   	}
	  }
    }
	stage('Publish standalone') {
      steps {
        sshagent ( ['projects-storage.eclipse.org-bot-ssh']) {
            sh "ssh $SSH_ACCOUNT mkdir -p $STANDALONE_DIR"
            sh "scp -rp $WORKSPACE/releng/org.eclipse.emf.diffmerge.update/target/standalone/*dependencies*.jar $SSH_ACCOUNT:$STANDALONE_DIR/"
        }
	  }
    }
    stage('Trigger childs') {
      steps {
        build job: 'diffmerge-coevolution', parameters: [string(name: 'GERRIT_REFSPEC', value:"${DM_BRANCH}"), string(name: 'DIFFMERGE_CORE', value:"https://download.eclipse.org/diffmerge/nightly/${BUILD_KEY}/emf-diffmerge-site"), string(name: 'CORE_BRANCH', value:"${BUILD_KEY}")], wait: false
        build job: 'diffmerge-patterns', parameters: [string(name: 'GERRIT_REFSPEC', value:"${DM_BRANCH}"), string(name: 'DIFFMERGE_CORE', value:"https://download.eclipse.org/diffmerge/nightly/${BUILD_KEY}/emf-diffmerge-site"), string(name: 'CORE_BRANCH', value:"${BUILD_KEY}")], wait: false
      }
    }
  }
}