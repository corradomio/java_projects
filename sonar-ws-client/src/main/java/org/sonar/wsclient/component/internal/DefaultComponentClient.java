package org.sonar.wsclient.component.internal;

import org.json.simple.JSONValue;
import org.sonar.wsclient.MapUtils;
import org.sonar.wsclient.component.Component;
import org.sonar.wsclient.component.ComponentClient;
import org.sonar.wsclient.internal.HttpRequestFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DefaultComponentClient implements ComponentClient {

    private static final String TREE_URL = "/api/components/tree";

    private final HttpRequestFactory requestFactory;


    public DefaultComponentClient(HttpRequestFactory requestFactory) {
        this.requestFactory = requestFactory;
    }

    @Override
    public List<Component> list(String id) {
        String json = requestFactory.get(TREE_URL,MapUtils.asMap(
                "component", id,
                "strategy", "all",
                "pageSize", 1000));
        return jsonToList(json);
    }

    /*
        {
            "paging": {
                "pageIndex": 1,
                "pageSize": 100,
                "total": 4
            },
            "baseComponent": {
                "key": "Lucene:dotnet/tools/Lucene.Net.Tests.Cli/Commands/Analysis",
                "name": "Analysis",
                "qualifier": "DIR",
                "path": "dotnet/tools/Lucene.Net.Tests.Cli/Commands/Analysis"
            },
            "components": [{
                    "key": "Lucene:dotnet/tools/Lucene.Net.Tests.Cli/Commands/Analysis/AnalysisCommandTest.cs",
                    "name": "AnalysisCommandTest.cs",
                    "qualifier": "UTS",
                    "path": "dotnet/tools/Lucene.Net.Tests.Cli/Commands/Analysis/AnalysisCommandTest.cs",
                    "language": "cs"
                }, {
                    "key": "Lucene:dotnet/tools/Lucene.Net.Tests.Cli/Commands/Analysis/AnalysisKuromojiBuildDictionaryCommandTest.cs",
                    "name": "AnalysisKuromojiBuildDictionaryCommandTest.cs",
                    "qualifier": "UTS",
                    "path": "dotnet/tools/Lucene.Net.Tests.Cli/Commands/Analysis/AnalysisKuromojiBuildDictionaryCommandTest.cs",
                    "language": "cs"
                }, {
                    "key": "Lucene:dotnet/tools/Lucene.Net.Tests.Cli/Commands/Analysis/AnalysisStempelCompileStemsCommandTest.cs",
                    "name": "AnalysisStempelCompileStemsCommandTest.cs",
                    "qualifier": "UTS",
                    "path": "dotnet/tools/Lucene.Net.Tests.Cli/Commands/Analysis/AnalysisStempelCompileStemsCommandTest.cs",
                    "language": "cs"
                }, {
                    "key": "Lucene:dotnet/tools/Lucene.Net.Tests.Cli/Commands/Analysis/AnalysisStempelPatchStemsCommandTest.cs",
                    "name": "AnalysisStempelPatchStemsCommandTest.cs",
                    "qualifier": "UTS",
                    "path": "dotnet/tools/Lucene.Net.Tests.Cli/Commands/Analysis/AnalysisStempelPatchStemsCommandTest.cs",
                    "language": "cs"
                }
            ]
        }

     */

    private List<Component> jsonToList(String json) {
        Map jsonRoot = (Map) JSONValue.parse(json);
        List<Map> components = (List<Map>) jsonRoot.get("components");
        List<Component> list = new ArrayList<>();
        for(Map jsonc : components)
            list.add(new Component(jsonc));
        return list;
    }

}
