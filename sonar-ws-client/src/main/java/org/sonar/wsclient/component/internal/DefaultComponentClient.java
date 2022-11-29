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
    private static final String SHOW_URL = "/api/components/show";

    private final HttpRequestFactory requestFactory;


    public DefaultComponentClient(HttpRequestFactory requestFactory) {
        this.requestFactory = requestFactory;
    }

    @Override
    public Component get(String id) {
        String json = requestFactory.get(SHOW_URL, MapUtils.asMap(
                "component", id
        ));

        Map jsonRoot = (Map) JSONValue.parse(json);
        Map jsonc = (Map) jsonRoot.get("component");
        if (jsonc != null && !jsonc.isEmpty())
            return new Component(jsonc);
        else
            return null;
    }

    @Override
    public List<Component> list(String id, boolean recursive) {
        // 1) the result is paginated
        // 2) the maximum number of results is 500 (default 100)
        // 3) 'p' is used to specify the page (starting from 1)
        // 4) 'total' is the total number of elements, NOT the total number of pages

        List<Component> components = new ArrayList<>();
        int nPages = 1;
        for (int p=1; p <= nPages; ++p) {
            String json = requestFactory.get(TREE_URL, MapUtils.asMap(
                    "component", id,
                    "strategy", recursive ? "all" : "children",
                    "p", p));

            Map jsonRoot = (Map) JSONValue.parse(json);

            // 1) retrieve the current page and the page numbers
            int total = MapUtils.getInt(jsonRoot, "paging", "total");
            int pageSize = MapUtils.getInt(jsonRoot, "paging", "pageSize");
            nPages = (total + pageSize - 1)/pageSize;

            // 2) retrieve all components
            components.addAll(toList(jsonRoot));
        }
        // return jsonToList(json);
        return components;
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

    private List<Component> toList(Map jsonRoot) {
        List<Map> components = (List<Map>) jsonRoot.get("components");
        List<Component> list = new ArrayList<>();
        for(Map jsonc : components)
            list.add(new Component(jsonc));
        return list;
    }

}
