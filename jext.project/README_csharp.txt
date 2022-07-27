External library dependencies
-----------------------------

2 mechanism:
    1) inside ".csproj" in tag <PackageReference>
       https://docs.microsoft.com/en-us/nuget/consume-packages/package-references-in-project-files
       Used in .NET Core

    2) in external file 'packages.config'
       Used in .NET Framework

-----------------------------

    <ItemGroup>
        <!-- ... -->
        <PackageReference Include="Contoso.Utility.UsefulStuff" Version="3.6.0" />
        <!-- ... -->
        <PackageReference Include="Microsoft.Data.Sqlite">
            <Version>3.1.3</Version>
        </PackageReference>
        <!-- ... -->
    </ItemGroup>

-----------------------------

    <packages>
        <package id="CNTK.CPUOnly" version="2.7.0" targetFramework="net45" />
        <package id="CNTK.Deps.MKL" version="2.7.0" targetFramework="net45" />
        <package id="CNTK.Deps.OpenCV.Zip" version="2.7.0" targetFramework="net45" />
        <package id="Microsoft.AspNet.WebApi" version="5.2.3" targetFramework="net45" />
        <package id="Microsoft.AspNet.WebApi.Client" version="5.2.3" targetFramework="net45" />
        <package id="Microsoft.AspNet.WebApi.Core" version="5.2.3" targetFramework="net45" />
        <package id="Microsoft.AspNet.WebApi.WebHost" version="5.2.3" targetFramework="net45" />
        <package id="Microsoft.Web.Infrastructure" version="1.0.0.0" targetFramework="net45" />
        <package id="Newtonsoft.Json" version="6.0.8" targetFramework="net45" />
        <package id="Swashbuckle" version="5.3.1" targetFramework="net45" />
        <package id="Swashbuckle.Core" version="5.3.1" targetFramework="net45" />
        <package id="System.IdentityModel.Tokens.Jwt" version="4.0.0" targetFramework="net45" />
        <package id="WebActivatorEx" version="2.0.6" targetFramework="net45" />
    </packages>

-----------------------------

namespace

    Namespace_or_type_name          -

    Namespace_declaration
        NAMESPACE qi=qualified_identifier namespace_body ';'?

    Namespace_body                  -
        OPEN_BRACE extern_alias_directives? using_directives? namespace_member_declarations? CLOSE_BRACE

    Namespace_member_declarations   -
    Namespace_member_declaration    -

using

    UsingStatement                  -
        USING OPEN_PARENS resource_acquisition CLOSE_PARENS embedded_statement       #usingStatement

    Using_directives                -

    Using_directive
        USING identifier '=' namespace_or_type_name ';'            #usingAliasDirective
        USING namespace_or_type_name ';'                           #usingNamespaceDirective
        USING STATIC namespace_or_type_name ';'                    #usingStaticDirective

    UsingAliasDirective             -
    UsingNamespaceDirective         -
    UsingStaticDirective            -