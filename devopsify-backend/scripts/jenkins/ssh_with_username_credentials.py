from api4jenkins import Jenkins
import sys
import time

argv = sys.argv
url = argv[1]
username = argv[2]
password = argv[3]

credentialsId = argv[4]
credentialsUsername = argv[5]
credentialsPassword = argv[6]

credentials = "<com.cloudbees.jenkins.plugins.sshcredentials.impl.BasicSSHUserPrivateKey plugin=\"ssh-credentials@277.v95c2fec1c047\"><scope>GLOBAL</scope><id>" + credentialsId + "</id><description>by devopsify</description><username>" + credentialsUsername + "</username><usernameSecret>false</usernameSecret><privateKeySource class=\"com.cloudbees.jenkins.plugins.sshcredentials.impl.BasicSSHUserPrivateKey$DirectEntryPrivateKeySource\"><privateKey>" + credentialsPassword + "</privateKey></privateKeySource></com.cloudbees.jenkins.plugins.sshcredentials.impl.BasicSSHUserPrivateKey>"

client = Jenkins(url, auth=(username, password))
if client.credentials.get(credentialsId) is not None:
    client.credentials.get(credentialsId).delete()
    time.sleep(1)
client.credentials.create(credentials)
