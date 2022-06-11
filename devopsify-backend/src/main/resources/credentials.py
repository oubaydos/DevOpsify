import argparse
from api4jenkins import Jenkins

parser = argparse.ArgumentParser()
parser.add_argument('--credentials-username', help='credentials-username to create')
parser.add_argument('--credentials-password', help='credentials-password to create')
parser.add_argument('--credentials-id', help='credentials-id to create')
parser.add_argument('--jenkins_url', help='jenkins_url')
parser.add_argument('--jenkins_username', help='jenkins_username')
parser.add_argument('--jenkins_password', help='jenkins_password')

args = parser.parse_args()
url = args.__getattribute__("jenkins_url")
username = args.__getattribute__("jenkins_username")
password = args.__getattribute__("jenkins_password")

credentialsId = args.__getattribute__("credentials-id")
credentialsUsername = args.__getattribute__("credentials-username")
credentialsPassword = args.__getattribute__("credentials-password")

credentials = "<com.cloudbees.plugins.credentials.impl.UsernamePasswordCredentialsImpl><scope>GLOBAL</scope><id>" + credentialsId + "</id><username>" + credentialsUsername + "</username><password>" + credentialsPassword + "</password><description></description></com.cloudbees.plugins.credentials.impl.UsernamePasswordCredentialsImpl>";


client = Jenkins(url, auth=(username, password))
client.credentials.create(credentials)
