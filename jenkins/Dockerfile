FROM jenkins/jenkins:2.170
MAINTAINER manoharan rajesh

USER root

# Plugins
#COPY plugins.txt /usr/share/jenkins/plugins.txt
#RUN /usr/local/bin/plugins.sh /usr/share/jenkins/plugins.txt

COPY plugins.txt /usr/share/jenkins/ref/plugins.txt
RUN /usr/local/bin/install-plugins.sh < /usr/share/jenkins/ref/plugins.txt

RUN apt-get update && apt-get install --yes curl software-properties-common
RUN apt-get install --yes maven
RUN curl -sL https://deb.nodesource.com/setup_10.x | bash -
RUN apt-get update && apt-get install -y gcc g++ make nodejs


# Install jq, make, docker, docker-compose and doo
RUN apt  install -y  python-pip jq make && \
    \
     curl -sL https://download.docker.com/linux/static/edge/x86_64/docker-17.11.0-ce.tgz | tar zx && \
        mv /docker/* /bin/ && chmod +x /bin/docker* && \
    \
    pip install --upgrade pip==9.0.3 && \
    pip install docker-compose==1.18.0

# Init groovy scripts
COPY master-executors.groovy /usr/share/jenkins/ref/init.groovy.d/
