def isStringFoundInLastCommit(String s){
    return sh (
            script: "git log -1 --pretty=%B | grep ${s}",
            returnStatus: true
    ) == 0
}
def incrementPatchVersion(){
    echo "incrementing project patch version"
    sh 'mvn build-helper:parse-version versions:set -DnewVersion=\\\${parsedVersion.majorVersion}.\\\${parsedVersion.minorVersion}.\\\${parsedVersion.nextIncrementalVersion} versions:commit'
    version = readMavenPom().getVersion()
    env.IMAGE_TAG = "$version-$BUILD_NUMBER"
    echo "the new version is : ${version}"
}
def incrementMinorVersion(){
    echo "incrementing project minor version"
    sh 'mvn build-helper:parse-version versions:set -DnewVersion=\\\${parsedVersion.majorVersion}.\\\${parsedVersion.nextMinorVersion}.\\\${parsedVersion.incrementalVersion} versions:commit'
    version = readMavenPom().getVersion()
    env.IMAGE_TAG = "$version-$BUILD_NUMBER"
    echo "the new version is : ${version}"
}

def incrementMajorVersion(){
    echo "incrementing project major version"
    sh 'mvn build-helper:parse-version versions:set -DnewVersion=\\\${parsedVersion.nextMajorVersion}.\\\${parsedVersion.minorVersion}.\\\${parsedVersion.incrementalVersion} versions:commit'
    version = readMavenPom().getVersion()
    env.IMAGE_TAG = "$version-$BUILD_NUMBER"
    echo "the new version is : ${version}"
}