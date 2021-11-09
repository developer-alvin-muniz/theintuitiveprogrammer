FROM jenkins/jenkins:lts
USER root
RUN apt-get update -qq
RUN apt-get install -qqy apt-transport-https ca-certificates curl gnupg2 software-properties-common
RUN curl -fsSL https://download.docker.com/linux/ubuntu/gpg | apt-key add -
RUN apt-key fingerprint 0EBFCD88


RUN   add-apt-repository "deb [arch=amd64] https://download.docker.com/linux/ubuntu eoan stable\
  $(lsb_release -cs) \
  stable"
RUN apt-get update
RUN apt-get install docker-ce -y

RUN usermod -aG docker jenkins

RUN apt-get update
RUN apt-get install maven -y
RUN echo "maven installed"
RUN systemctl start docker
RUN echo "docker started"
