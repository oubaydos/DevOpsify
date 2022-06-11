from api4jenkins import Jenkins
import sys
argv = sys.argv
url = argv[1]
username = argv[2]
password = argv[3]

credentialsId = argv[4]
credentialsUsername = argv[5]
credentialsPassword = argv[6]

credentials = "<com.cloudbees.plugins.credentials.impl.UsernamePasswordCredentialsImpl><scope>GLOBAL</scope><id>" + credentialsId + "</id><username>" + credentialsUsername + "</username><password>" + credentialsPassword + "</password><description></description></com.cloudbees.plugins.credentials.impl.UsernamePasswordCredentialsImpl>";


client = Jenkins(url, auth=(username, password))
client.credentials.create(credentials)
