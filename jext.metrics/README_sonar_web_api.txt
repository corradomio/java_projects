SonarQube Web API

    ajs_anonymous_id=0c2f69c8-98de-45fe-a530-d7f880bb6362; Webstorm-675c8b73=0b32a744-2a19-4961-ace4-a2a3cdaced8d;
    XSRF-TOKEN=g9ugvaejtu7qq2lrm434kmbftk; JWT-SESSION=eyJhbGciOiJIUzI1NiJ9.eyJsYXN0UmVmcmVzaFRpbWUiOjE2NjM4MjE0OTk4ODQ
    sInhzcmZUb2tlbiI6Imc5dWd2YWVqdHU3cXEybHJtNDM0a21iZnRrIiwianRpIjoiQVlNN3Frdl94RnVsU1dGUXVzVGEiLCJzdWIiOiJBWU0yUnRNNz
    R3YTl6VFg1c1RCdCIsImlhdCI6MTY2MzE1MzM1OSwiZXhwIjoxNjY0MDgwNjk5fQ.-gEfHcF9dQ0e6dybgHi6M14EcIPvhyWRIEmIzPYO_Vs


User tokens
    sonaruser:sonaruser
        token: squ_ccad5f5b3a481e8bf3e592f32f79a23189af08e9

    admin:password

    curl -u THIS_IS_MY_TOKEN: https://sonarqube.com/api/user_tokens/search
    curl -u MY_LOGIN:MY_PASSWORD https://sonarqube.com/api/user_tokens/search

    curl -u sonaruser:sonaruser http://localhost:9000/api/user_tokens/search
    curl -u squ_ccad5f5b3a481e8bf3e592f32f79a23189af08e9: http://localhost:9000/api/user_tokens/search
        Note: token MUST TERMINATE with ':'


current user:

    using the browser

        http://localhost:9000/api -> http://localhost:9000/api/webservices/list

    using curl

        curl -u admin:password http://localhost:9000/api/webservices/list

    using wget

        wget --user=admin --password=password http://localhost:9000/api/webservices/list
        DOESN'T work!!!
        WHY???
        
        wget --auth-no-challenge --user=admin --password=password http://localhost:9000/api/webservices/list
        it works



api/authentication
api/languages
api/measures
api/metrics
api/project_analyses
api/projects


api/components      
api/hotspots        Read and update Security Hotspots.
api/issues          Read and update issues.
api/languages       Get the list of programming languages supported in this instance.
api/metrics         Get information on automatic metrics
api/measures        Get components or children with specified measures.

api/projects
api/project_analyses
api/project_badges

api/ce
api/project_dump
api/rules
api/server
api/settings
api/sources
api/system
api/user_groups
api/user_tokens
api/users
api/webservices


// --------------------------------------------------------------------------
// Possible object types
// --------------------------------------------------------------------------

qualifiers:
    TRK     project
    FIL
    DIR
    UTS     ???
    BRC     branch ???


curl -u sonaruser:sonaruser http://localhost:9000/api/components/search?qualifiers=TRK

// --------------------------------------------------------------------------
// list of projects
// --------------------------------------------------------------------------
//  Lucene
//  SPLServer
//  acme-beta4

http://localhost:9000/api/components/search?qualifiers=TRK
    {
        "paging": {
            "pageIndex": 1,
            "pageSize": 100,
            "total": 2
        },
        "components": [
            {
                "key": "Lucene",
                "name": "Lucene",
                "qualifier": "TRK",
                "project": "Lucene"
            },
            {
                "key": "SPLServer",
                "name": "splproject3.3",
                "qualifier": "TRK",
                "project": "SPLServer"
            }
        ]
    }

// --------------------------------------------------------------------------
// single object
// --------------------------------------------------------------------------
// TRK
// FIL
// DIR

http://localhost:9000/api/components/show?component=Lucene
http://localhost:9000/api/components/show?component=acme-beta4
    {
        "component": {
            "key": "Lucene",
            "name": "Lucene",
            "qualifier": "TRK",
            "analysisDate": "2022-09-13T16:08:03+0400",
            "tags": [],
            "visibility": "public",
            "version": "not provided",
            "needIssueSync": false
        },
        "ancestors": []
    }

// --------------------------------------------------------------------------
// HIERARCHY, single level
// --------------------------------------------------------------------------
// Note: the 'path' in a Source object is RELATIVE to the PROJECT_HOME
//       this is compatible with SonarQube and the component id to use.
//
//  example:    acme-beta4:src/main/java/com/acmeair
//

Note:
    http://localhost:9000/api/components/tree?component=acme-beta4&qualifiers=DIR

        return ONLY the list of directories containing some source file (java
        or other programming language)

    acme-beta4:src/main/java
    http://localhost:9000/api/components/tree?component=acme-beta4:src/main/java&qualifiers=DIR
        NOT FOUND

    acme-beta4:src/main/java/com/acmeair
    http://localhost:9000/api/components/tree?component=acme-beta4:src/main/java/com/acmeair&qualifiers=DIR
        OK


....


http://localhost:9000/api/components/tree?component=Lucene
http://localhost:9000/api/components/tree?component=acme-beta4
    -- navigate a single level

http://localhost:9000/api/components/tree?component=Lucene&qualifiers=FIL
http://localhost:9000/api/components/tree?component=Lucene&qualifiers=DIR
    -- WHY there are no children?
    -- because the list is FLAT


curl -u sonaruser:sonaruser "http://localhost:9000/api/components/tree?component=Lucene:dotnet/tools/Lucene.Net.Tests.Cli/Commands/Analysis&strategy=all"

http://localhost:9000/api/components/tree?component=Lucene:dotnet/tools/Lucene.Net.Tests.Cli/Commands/Analysis&strategy=all
    -- this is a directory DIR
    -- it returns the list of files/directories


curl -u sonaruser:sonaruser "http://localhost:9000/api/components/tree?component=Lucene:dotnet/tools/Lucene.Net.Tests.Cli/Commands/Analysis/AnalysisKuromojiBuildDictionaryCommandTest.cs&strategy=all"

http://localhost:9000/api/components/tree?component=Lucene:dotnet/tools/Lucene.Net.Tests.Cli/Commands/Analysis/AnalysisKuromojiBuildDictionaryCommandTest.cs&strategy=all


// --------------------------------------------------------------------------
// MEASURES
// --------------------------------------------------------------------------

http://localhost:9000/api/measures/component?component=Lucene&metricKeys=ncloc
    -- metrics at project level


curl -u sonaruser:sonaruser "http://localhost:9000/api/measures/component?component=Lucene:Lucene.Net.Grouping/AbstractAllGroupHeadsCollector.cs&metricKeys=ncloc"

http://localhost:9000/api/measures/component?component=Lucene:Lucene.Net.Grouping/AbstractAllGroupHeadsCollector.cs&metricKeys=ncloc
    -- metrics at file level
