package jext.sourcecode.project.lfm.csharp;

/*
    NuGet Server API
        https://docs.microsoft.com/en-us/nuget/api/overview

    Service Index:
        https://api.nuget.org/v3/index.json.

    Resources and schema
        Resource name               Required    Description
        PackageBaseAddress          yes         Get package content (.nupkg).
        PackageDetailsUriTemplate   no          Construct a URL to access a package details web page.
        RegistrationsBaseUrl        yes         Get package metadata.
        SearchQueryService          yes         Filter and search for packages by keyword.


    Semantic Version for .NET
        https://www.nuget.org/packages/semver/
        https://semver.org/
 */
/*
    ServiceIndex
    ------------

    {
      "version": "3.0.0",
      "resources": [
        ...
        {
          "@id": "https://api.nuget.org/v3/registration5-semver1/",
          "@type": "RegistrationsBaseUrl",
          "comment": "Base URL of Azure storage where NuGet package registration info is stored"
        },
        {
          "@id": "https://api.nuget.org/v3-flatcontainer/",
          "@type": "PackageBaseAddress/3.0.0",
          "comment": "Base URL of where NuGet packages are stored, in the format https://api.nuget.org/v3-flatcontainer/{id-lower}/{version-lower}/{id-lower}.{version-lower}.nupkg"
        },
        ...
      ],
      "@context": {
        "@vocab": "http://schema.nuget.org/services#",
        "comment": "http://www.w3.org/2000/01/rdf-schema#comment"
      }
    }

 */

import jext.sourcecode.project.util.BaseLibraryRepository;

public class NuGetRepository extends BaseLibraryRepository {

}
