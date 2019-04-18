#!groovy

properties([pipelineTriggers([pollSCM('H/10 * * * *')])])

node() {
    stage('Checkout'){
        checkout scm
    }
    stage ('build'){
     dir('service') {
       sh 'mvn clean install'
     }
     dir('ui') {
        sh 'npm install'
        sh 'npm run build --prod'
     }
    }

    stage ('build & push containers'){
        withDockerRegistry(credentialsId: 'dockerHub', url: 'https://index.docker.io/v1/') {
            dir('ui'){
                sh 'docker build -t manoharanrajesh/project-manager-ui .'
                sh 'docker push manoharanrajesh/project-manager-ui'
            }
            sh 'docker tag manoharanrajesh/project-manager-service:0.0.1-SNAPSHOT  manoharanrajesh/project-manager-service'
            sh 'docker push manoharanrajesh/project-manager-service'
        }

    }

}