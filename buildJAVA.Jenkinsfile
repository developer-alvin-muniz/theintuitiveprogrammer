def dockerImage;

node('docker'){
	stage('SCM'){
		checkout([$class: 'GitSCM', branches: [[name: '*/master']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[url: 'https://github.com/alvin-muniz/spring-blog']]]);
	}
	stage('build'){
// 		dockerImage = docker.build('alvindevelopment/jenkindsdocker:v$BUILD_NUMBER');
        sh 'mvn -v'
		sh 'mvn clean install'
		dockerImage = docker.build('alvindevelopment/blog:v$BUILD_NUMBER','-f springBootApp.Dockerfile .');
	}
	stage('push'){
		docker.withRegistry('https://index.docker.io/v1/', 'dockerhub-creds'){
			dockerImage.push();
		}
	}
}
