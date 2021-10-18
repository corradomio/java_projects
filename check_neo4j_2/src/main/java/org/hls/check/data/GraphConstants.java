package org.hls.check.data;

public interface GraphConstants {

    // ----------------------------------------------------------------------
    // Dependency Graph
    // ----------------------------------------------------------------------

    String UNKNOWN = "UNKNOWN";
    String EMPTY = "";

    String REPOSITORY = "repository";
    String PROJECT = "project";
    String   MODULE = "module";
    String     LIBRARY = "library";
    String       LIBRARY_TYPE = "libraryType";
    String       FILE = "file";
    String       FILES = "files";
    String     SOURCE = "source";
    String     RESOURCE = "resource";
    String LIBREPO = "librepo";

    String SPL_ID = "splId";

    String SPLPROJECT = "splproject";
    String   DESCRIPTION = "description";
    String   JPARAMETERS = "jparameters";
    String   PROJECT_URL = "url";
    String SOURCE_MODEL = "msource";
    String   SOURCE_HOME = "sourceHome";
    String   REF_ID = "refId";
    String   MODULE_REF_ID = "moduleRefId";
    String DEPENDENCY_MODEL = "project";

    String COMPONENT_MODEL = "mcomponent";
    String FEATURE_MODEL = "mfeature";
    String CLUSTER_MODEL = "cluster";
    String RUNTIME_MODEL = "runtime";
    String DEPENDENCY = "dependency";

    String NAMESPACE = "namespace";
    String   TYPE = "type";
    String     FIELD = "field";
    String     METHOD = "method";
    String       TYPE_PARAMS = "typeParams";
    String       NUM_PARAMS = "nParams";
    String       SIGNATURE = "signature";
    String       DECLARATION = "declaration";
    String       RETURN = "return";
    String       PARAMETER = "parameter";
    String       CONTENT = "content";
    String     REFTYPE = "reftype";

    String    MODIFIERS = "modifiers";
    String      STRUCTURE = "structure";
    String        CONSTRUCTOR = "constructor";
    String        DESTRUCTOR = "destructor";
    String        STATIC = "static";
    String      VISIBILITY = "visibility";
    String        PUBLIC = "public";
    String        PROTECTED = "protected";
    String        PRIVATE = "private";
    String        PACKAGE = "package";

    String ID = "id";
    String IDS = "ids";
    String PROJECT_ID  = "projectId";
    String   MODULE_ID = "moduleId";
    String     TYPE_ID = "typeId";
    String      OWNER_TYPE_ID = "ownerTypeId";
    String       PARAMETERS = "parameters";
    String         OWNER_METHOD_ID = "ownerMethodId";
    String       RETURN_TYPE = "returnType";
    String       MODULE_HOME = "moduleHome";
    String     LIBRARY_ID = "libraryId";
    String     SOURCE_ID = "sourceId";
    String     RESOURCE_ID = "resourceId";
    String     NAMESPACE_ID = "namespaceId";
    String     METHOD_ID = "methodId";
    String     FIELD_ID = "fieldId";

    String COMMENT = "comment";
    String    DOCUMENTATION = "documentation";
    String    COMMENTS = "comments";
    String    ALL_COMMENTS = "allComments";
    String    AUTHORS = "authors";
    String WITH_COMMENT_ID = "withCommentId";

    String NAME = "name";
    String    FULLNAME = "fullname";
    // String      TYPENAME = "typename";
    // String      METHODNAME = "methodname";
    String PATH = "path";
    String DIGEST = "digest";
    String NO_DIGEST = "0";
    String TIMESTAMP = "timestamp";
    String STATUS = "status";
    String REASON = "reason";
    String LANGUAGE = "language";
    String PROJECT_TYPE = "projectType";

    // Aliases (NOT IN DATABASE!)
    String FULL_NAME = "fullName";
    // String METHOD_NAME = "methodName";
    // String TYPE_NAME = "typeName";
    // End aliases

    String ROLE = "role";
    String CONTEXT = "context";

    String EDGE_TYPE = "type";          // property used to specialize an edge
    // ing USES_TYPE = "uses";          // property used to specialize an 'uses' edge. It contains ALSO 'type'

    String MEMBER_OF = "memberOf";
    String USES = "uses";
    String DECLARES = "declares";
    String EXTENDS = "extends";
    String IMPLEMENTS = "implements";
    String DEPENDS_ON = "dependsOn";
    String OF_TYPE = "ofType";
    String FOLLOWS = "follows";
    String IN_NAMESPACE = "inNamespace";

    String METHOD_CALL = "call";

    String TOKEN = "token";
    String PERCENTAGE = "percentage";
    String ISINTERFACE = "isInterface";
    String ISEXTENDED = "isExtended";

    // String STATEMENT = "statement";
    String INDEX = "index";

    String UNKNOWN_NODE_ID = "-1";
    String ALL_NODES = null;

    // ----------------------------------------------------------------------
    // EntryPoint (type/component/feature)
    // ----------------------------------------------------------------------

    String RUNTIME = "runtime";
    String ENTRY_POINT = "entryPoint";
    String COUNT_ENTRY_POINTS = "countEntryPoints";
    String COUNT_METHODS = "countMethods";
    // String ENTRY_POINT_SCORE = "entryPointScore";

    // ----------------------------------------------------------------------
    // Type roles
    // ----------------------------------------------------------------------

    String TROLE_INTERFACE = "INTERFACE";
    String TROLE_CLASS = "CLASS";
    String TROLE_ENUM = "ENUM";
    String TROLE_ANNOTATION = "ANNOTATION";
    String TROLE_REFTYPE = "REFTYPE";

    // String TROLE_PRIMITIVE = "PRIMITIVE";
    // String TROLE_TYPE_PARAMETER = "TYPE_PARAMETER";

    // ----------------------------------------------------------------------
    // Component Graph
    // ----------------------------------------------------------------------

    String COMPONENT = "component";
    String COMPONENTS = "components";
    String COMPONENT_ID = "componentId";

    String CONTAINS = "contains";
    String DEPTH = "depth";
    String ALL = "all";
    String COUNT = "count";
    String TYPES = "types";
    String COUNT_TYPES = "countTypes";
    String TYPES_COUNT = "typesCount";

    // String CLASSES = "classes";

    // ----------------------------------------------------------------------
    // Type types
    // ----------------------------------------------------------------------

    // String TYPE_UNKNOWN = "unknown";
    // String TYPE_REFTYPE = "reftype";

    // ----------------------------------------------------------------------
    // Component/Feature roles
    // ----------------------------------------------------------------------

    String ROLE_TYPE      = "TYPE";
    String ROLE_COMPONENT = "COMPONENT";
    String ROLE_DAGROOT   = "DAGROOT";

    String ROLE_PROJECT = "PROJECT";
    String ROLE_FEATURE = "FEATURE";

    // ----------------------------------------------------------------------
    // Feature Graph
    // ----------------------------------------------------------------------

    String FEATURE = "feature";
    String FEATURES = "features";
    String FEATURE_ID = "featureId";

    String COMPLEXITY = "complexity";

    // ----------------------------------------------------------------------
    // Core Graph
    // ----------------------------------------------------------------------

    String SCORE = "score";
    String CORE = "core";
    String NODES = "nodes";
    String ALPHA = "alpha";

    // ----------------------------------------------------------------------
    // Clustering Graph
    // ----------------------------------------------------------------------

    String KEYWORDS = "keywords";
    String CLUSTER = "cluster";

    // ----------------------------------------------------------------------
    // Statistics Graph
    // ----------------------------------------------------------------------

    String ELEMENTS = "elements";

    // ----------------------------------------------------------------------
    // RestWrapper Graph
    // ----------------------------------------------------------------------

    String SPEC = "spec";


    // ----------------------------------------------------------------------
    // Language Specific
    // ----------------------------------------------------------------------
    // Java

    String JAVA_LANG = "java";
    String JAVA_LANG_OBJECT = "java.lang.Object";

    // ----------------------------------------------------------------------
    // Database Graph elements
    // ----------------------------------------------------------------------

    String GRAPH_NODE_ID = "$id";
    String GRAPH_NODE_TYPE = "$type";
    String GRAPH_NODE_LABELS = "$labels";
    String GRAPH_EDGE_TYPE = "$type";

    // ----------------------------------------------------------------------
    // JSON Graph elements
    // ----------------------------------------------------------------------

    String LABEL = "label";
    String LABELS = "labels";
    String METADATA = "metadata";
    String DIRECTED = "directed";

    String GRAPH = "graph";
    String GRAPH_DIRECTED = DIRECTED;
    String GRAPH_TYPE = "type";
    String GRAPH_NODES = "nodes";
    String GRAPH_EDGES = "edges";

    String EDGE_SOURCE = "source";
    String EDGE_TARGET = "target";
    String EDGE_FROM = "idfrom";
    String EDGE_TO = "idto";
    String EDGE_RELATION = "relation";
    String EDGE_DIRECTED = DIRECTED;

    // ----------------------------------------------------------------------
    // Model Status
    // ----------------------------------------------------------------------

    // String MODEL_STATUS = "modelStatus";
    // String TASK_ID = "taskId";
    // String TASK_TYPE = "taskType";
    // String STATUS_MESSAGE = "message";

    // Model status
    String STATUS_NOT_EXISTENT = "NOT_EXISTENT";
    String STATUS_TASK_RUNNING = "TASK_RUNNING";
    String STATUS_VALID = "VALID";
    String STATUS_INVALID = "INVALID";

    String REASON_INIT = "Initializing ...";
    String REASON_CREATING = "Creating ...";
    String REASON_TASK_RUNNING = "Task runnint ...";
    String REASON_ABORTED = "Aborted";
    String REASON_FAILED = "Failed for exception";
    String REASON_DELETING = "Deleting ...";
    String REASON_LINKED_PROJECT = "Linked project";
    String REASON_AUTOMATIC_IMPORT = "Automatic import";
    String REASON_DOWNLOADED = "Downloaded";
    String REASON_DELETED = "Deleted";
    String REASON_VALID = "";
    String REASON_NOT_EXISTENT = "";
    String REASON_CHECKOUT = "Checkout";
    String REASON_UPDATE = "Update";

    // ----------------------------------------------------------------------
    // Model Status
    // ----------------------------------------------------------------------

    // String COUNT_N_FEATURES = "features";
    // String COUNT_N_TYPES = "types";
    // String COUNT_N_COMPONENTS = "components";

    // ----------------------------------------------------------------------
    // Fulltext indices
    // ----------------------------------------------------------------------

    String FULLTEXT_INDEX_NAME = "spl_fulltext_name_index";

    // ----------------------------------------------------------------------
    // Library versions
    // ----------------------------------------------------------------------
    String VERSION = "version";
    String USED_VERSION = "usedVersion";
    String DECLARED_VERSION = "declaredVersion";
    String LATEST_VERSION = "latestVersion";
    String URL = "url";
    String REPOSITORY_TYPE = "repositoryType";

    // ----------------------------------------------------------------------
    // SPL Versioning
    // ----------------------------------------------------------------------
    // revision -> current revision
    //      Used ONLY in 'msource', 'project', 'mcomponent', ... nodes
    // revisions -> [rev0, rev1,...]
    // inRevision -> [true,false,...]

    String REVISIONS = "revisions";     // [0,1,3,...]
    String IN_REVISION = "inRevision";  // [false,true,...] for CONSECUTIVE revisions starting from 0
    String REVISION = "revision";
    String REVISION_ID = "revisionId";
}
