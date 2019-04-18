import jenkins.*
import hudson.*
import com.cloudbees.plugins.credentials.*
import com.cloudbees.plugins.credentials.common.*
import com.cloudbees.plugins.credentials.domains.*
import com.cloudbees.jenkins.plugins.sshcredentials.impl.*
import hudson.plugins.sshslaves.*;
import hudson.model.*
import jenkins.model.*
import hudson.security.*

println "--> disabling master executors"
Jenkins.instance.setNumExecutors(2)

/**
 * Below code will create a user - jenkins ,  admin with default password.
 */

def hudsonRealm = new HudsonPrivateSecurityRealm(false)
def adminUsername = System.getenv('JENKINS_ADMIN_USERNAME') ?: 'admin'
def adminPassword = System.getenv('JENKINS_ADMIN_PASSWORD') ?: 'password'
hudsonRealm.createAccount(adminUsername, adminPassword)
hudsonRealm.createAccount("jenkins", "jenkins")

def instance = Jenkins.getInstance()
instance.setSecurityRealm(hudsonRealm)
instance.save()


//def strategy = new GlobalMatrixAuthorizationStrategy()

//  Slave Permissions
//strategy.add(hudson.model.Computer.BUILD,'jenkinsuser')
//strategy.add(hudson.model.Computer.CONFIGURE,'jenkinsuser')
//strategy.add(hudson.model.Computer.CONNECT,'jenkinsuser')
//strategy.add(hudson.model.Computer.CREATE,'jenkinsuser')
//strategy.add(hudson.model.Computer.DELETE,'jenkinsuser')
//strategy.add(hudson.model.Computer.DISCONNECT,'jenkinsuser')

//  Credential Permissions
//strategy.add(com.cloudbees.plugins.credentials.CredentialsProvider.CREATE,'jenkinsuser')
//strategy.add(com.cloudbees.plugins.credentials.CredentialsProvider.DELETE,'jenkinsuser')
//strategy.add(com.cloudbees.plugins.credentials.CredentialsProvider.MANAGE_DOMAINS,'jenkinsuser')
//strategy.add(com.cloudbees.plugins.credentials.CredentialsProvider.UPDATE,'jenkinsuser')
//strategy.add(com.cloudbees.plugins.credentials.CredentialsProvider.VIEW,'jenkinsuser')

//  Overall Permissions
//strategy.add(hudson.model.Hudson.ADMINISTER,'jenkinsuser')
//strategy.add(hudson.PluginManager.CONFIGURE_UPDATECENTER,'jenkinsuser')
//strategy.add(hudson.model.Hudson.READ,'jenkinsuser')
//strategy.add(hudson.model.Hudson.RUN_SCRIPTS,'jenkinsuser')
//strategy.add(hudson.PluginManager.UPLOAD_PLUGINS,'jenkinsuser')

//  Job Permissions
//strategy.add(hudson.model.Item.BUILD,'jenkinsuser')
//strategy.add(hudson.model.Item.CANCEL,'jenkinsuser')
//strategy.add(hudson.model.Item.CONFIGURE,'jenkinsuser')
//strategy.add(hudson.model.Item.CREATE,'jenkinsuser')
//strategy.add(hudson.model.Item.DELETE,'jenkinsuser')
//strategy.add(hudson.model.Item.DISCOVER,'jenkinsuser')
//strategy.add(hudson.model.Item.READ,'jenkinsuser')
//strategy.add(hudson.model.Item.WORKSPACE,'jenkinsuser')

//  Run Permissions
//strategy.add(hudson.model.Run.DELETE,'jenkinsuser')
//strategy.add(hudson.model.Run.UPDATE,'jenkinsuser')

//  View Permissions
//strategy.add(hudson.model.View.CONFIGURE,'jenkinsuser')
//strategy.add(hudson.model.View.CREATE,'jenkinsuser')
//strategy.add(hudson.model.View.DELETE,'jenkinsuser')
//strategy.add(hudson.model.View.READ,'jenkinsuser')

//  Setting Anonymous Permissions
//strategy.add(hudson.model.Hudson.READ,'anonymous')
//strategy.add(hudson.model.Item.BUILD,'anonymous')
//strategy.add(hudson.model.Item.CANCEL,'anonymous')
//strategy.add(hudson.model.Item.DISCOVER,'anonymous')
//strategy.add(hudson.model.Item.READ,'anonymous')

// Setting Admin Permissions
//strategy.add(Jenkins.ADMINISTER, "admin")

// Setting easy settings for local builds
//def local = System.getenv("BUILD").toString()
//if(local == "local") {
//  Overall Permissions
//  strategy.add(hudson.model.Hudson.ADMINISTER,'anonymous')
// strategy.add(hudson.PluginManager.CONFIGURE_UPDATECENTER,'anonymous')
//strategy.add(hudson.model.Hudson.READ,'anonymous')
// strategy.add(hudson.model.Hudson.RUN_SCRIPTS,'anonymous')
//strategy.add(hudson.PluginManager.UPLOAD_PLUGINS,'anonymous')
//}

//instance.setAuthorizationStrategy(strategy)
// instance.save()