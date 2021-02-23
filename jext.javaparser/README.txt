Soot:
    http://soot-oss.github.io/soot/



    BodyDeclaration(Node)
        AnnotationMemberDeclaration
        CallableDeclaration
            ConstructorDeclaration
            MethodDeclaration
        EnumConstantDeclaration
        FieldDeclaration
        InitializerDeclaration
        TypeDeclaration
            AnnotationDeclaration
            ClassOrInterfaceDeclaration
            EnumDeclaration

    ResolvedDeclaration
        ResolvedMethodLikeDeclaration
            ResolvedConstructorDeclaration
            ResolvedMethodDeclaration
        ResolvedTypeDeclaration
            ResolvedReferenceTypeDeclaration
                ResolvedAnnotationDeclaration
                ResolvedClassDeclaration
                ResolvedInterfaceDeclaration
                ResolvedEnumDeclaration
            ResolvedTypeParameterDeclaration
        ResolvedValueDeclaration
            ResolvedAnnotationMemberDeclaration
            ResolvedEnumConstantDeclaration
            ResolvedParameterDeclaration
            ResolvedPatternDeclaration

    Type(Node)
        PrimitiveType
        ReferenceType
            ArrayType
            ClassOrInterfaceType
            TypeParameter
        UnionType
        IntersectionType
        WildcardType
        UnknownType
        VarType
        VoidType

    ResolvedType
        ?   InferenceVariable
        ?   InferenceVariableType
        ?   LambdaArgumentTypePlaceholder
        ?   LazyType
        NullType
        ResolvedArrayType
        ResolvedIntersectionType
        ResolvedLambdaConstraintType
        ResolvedPrimitiveType
        ResolvedReferenceType
            ?   ReferenceTypeImpl
        ResolvedTypeVariable
        ResolvedUnionType
        ResolvedVoidType
        ResolvedWildcard

    Resolvable
        EnumConstantDeclaration
        MethodCallExpr
        Parameter
        AnnotationExpr
            MarkerAnnontationExpr
            SingleMemberAnnotationExpr
            NormalAnnotationExpr
        EnumDeclaration
        ThisExpr
        NameExpr
        AnnotationMemberDeclaration
        ExplicitConstructorInvocationStmt
        MethodreferenceExpr
        VariableDeclarator
        ConstructorDeclaration
        FieldDeclaration
        Type
            VarType
            ReferenceType
                ClassOrInterfaceType
                ArrayType
                TypeParameter
            UnionType
            UnknownType
            WildcardType
            PrimitiveType
            VoidType
            IntersectionType
        MethodDeclaration
        ObjectCreationExpr
        AnnotationDeclaration
        FieldAccessExpr
        ClassOrInterfaceDeclaration
