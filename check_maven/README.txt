<repository>
    dav:https://maven.forge.objectweb.org:8002/maven2/
    https://oss.sonatype.org/service/local/staging/deploy/maven2/
    scp://pixie.qos.ch/var/mvnrepo/
    ftp://www.atomikos.com
    s3://${amazonReleaseRepo}


Una 'relocated library' puo' essere vista ANCHE come una 'pom' library

Un MavenPom locale e' creato mediante il solo file
Un MavenPom repoto ha anche associato il downloader
