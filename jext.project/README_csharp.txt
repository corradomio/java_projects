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