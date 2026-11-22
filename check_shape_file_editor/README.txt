
mvn -f shape_editor/pom.xml dependency:copy-dependencies@copy-libs-for-eclipse


Quando Equinox dice che una class non e' presente MA e' presente il jar,
vuol dire che in qualche file MANIFEST.MF non e' stata fatta l'esportazione del
namespace

