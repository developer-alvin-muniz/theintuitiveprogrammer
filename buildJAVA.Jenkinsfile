def dockerImage;

node('docker'){
	stage('SCM'){
		checkout([$class: 'GitSCM', branches: [[name: '*/master']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[url: 'https://github.com/alvin-muniz/spring-blog']]]);
	}
	stage('build'){
		dockerImage = docker.build('alvindevelopment/jenkindsdocker:v$BUILD_NUMBER');
		steps{
		    sh 'mvn clean'
		}

	}
	stage('push'){
		docker.withRegistry('https://index.docker.io/v1/', 'dockerhub-creds'){
			dockerImage.push();
		}
	}
}
