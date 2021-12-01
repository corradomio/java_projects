Soot:
    http://soot-oss.github.io/soot/

-----------------------------------------------------------------------------

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
            MarkerAnnotationExpr
            SingleMemberAnnotationExpr
            NormalAnnotationExpr
        EnumDeclaration
        ThisExpr
        NameExpr
        AnnotationMemberDeclaration
        ExplicitConstructorInvocationStmt
        MethodReferenceExpr
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

-----------------------------------------------------------------------------

Node (com.github.javaparser.ast)
    ArrayCreationLevel (com.github.javaparser.ast)
    BodyDeclaration (com.github.javaparser.ast.body)
        AnnotationMemberDeclaration (com.github.javaparser.ast.body)
        CallableDeclaration (com.github.javaparser.ast.body)
            ConstructorDeclaration (com.github.javaparser.ast.body)
            MethodDeclaration (com.github.javaparser.ast.body)
        CompactConstructorDeclaration (com.github.javaparser.ast.body)
        EnumConstantDeclaration (com.github.javaparser.ast.body)
        FieldDeclaration (com.github.javaparser.ast.body)
        InitializerDeclaration (com.github.javaparser.ast.body)
        TypeDeclaration (com.github.javaparser.ast.body)
            AnnotationDeclaration (com.github.javaparser.ast.body)
            ClassOrInterfaceDeclaration (com.github.javaparser.ast.body)
            EnumDeclaration (com.github.javaparser.ast.body)
            RecordDeclaration (com.github.javaparser.ast.body)
    CatchClause (com.github.javaparser.ast.stmt)
    Comment (com.github.javaparser.ast.comments)
        BlockComment (com.github.javaparser.ast.comments)
        JavadocComment (com.github.javaparser.ast.comments)
        LineComment (com.github.javaparser.ast.comments)
    CompilationUnit (com.github.javaparser.ast)
    Expression (com.github.javaparser.ast.expr)
        AnnotationExpr (com.github.javaparser.ast.expr)
            MarkerAnnotationExpr (com.github.javaparser.ast.expr)
            NormalAnnotationExpr (com.github.javaparser.ast.expr)
            SingleMemberAnnotationExpr (com.github.javaparser.ast.expr)
        ArrayAccessExpr (com.github.javaparser.ast.expr)
        ArrayCreationExpr (com.github.javaparser.ast.expr)
        ArrayInitializerExpr (com.github.javaparser.ast.expr)
        AssignExpr (com.github.javaparser.ast.expr)
        BinaryExpr (com.github.javaparser.ast.expr)
        CastExpr (com.github.javaparser.ast.expr)
        ClassExpr (com.github.javaparser.ast.expr)
        ConditionalExpr (com.github.javaparser.ast.expr)
        EnclosedExpr (com.github.javaparser.ast.expr)
        FieldAccessExpr (com.github.javaparser.ast.expr)
        InstanceOfExpr (com.github.javaparser.ast.expr)
        LambdaExpr (com.github.javaparser.ast.expr)
        LiteralExpr (com.github.javaparser.ast.expr)
            BooleanLiteralExpr (com.github.javaparser.ast.expr)
            LiteralStringValueExpr (com.github.javaparser.ast.expr)
                CharLiteralExpr (com.github.javaparser.ast.expr)
                DoubleLiteralExpr (com.github.javaparser.ast.expr)
                IntegerLiteralExpr (com.github.javaparser.ast.expr)
                LongLiteralExpr (com.github.javaparser.ast.expr)
                StringLiteralExpr (com.github.javaparser.ast.expr)
                TextBlockLiteralExpr (com.github.javaparser.ast.expr)
            NullLiteralExpr (com.github.javaparser.ast.expr)
        MethodCallExpr (com.github.javaparser.ast.expr)
        MethodReferenceExpr (com.github.javaparser.ast.expr)
        NameExpr (com.github.javaparser.ast.expr)
        ObjectCreationExpr (com.github.javaparser.ast.expr)
        PatternExpr (com.github.javaparser.ast.expr)
        SuperExpr (com.github.javaparser.ast.expr)
        SwitchExpr (com.github.javaparser.ast.expr)
        ThisExpr (com.github.javaparser.ast.expr)
        TypeExpr (com.github.javaparser.ast.expr)
        UnaryExpr (com.github.javaparser.ast.expr)
        VariableDeclarationExpr (com.github.javaparser.ast.expr)
    ImportDeclaration (com.github.javaparser.ast)
    MemberValuePair (com.github.javaparser.ast.expr)
    Modifier (com.github.javaparser.ast)
    ModuleDeclaration (com.github.javaparser.ast.modules)
    ModuleDirective (com.github.javaparser.ast.modules)
        ModuleExportsDirective (com.github.javaparser.ast.modules)
        ModuleOpensDirective (com.github.javaparser.ast.modules)
        ModuleProvidesDirective (com.github.javaparser.ast.modules)
        ModuleRequiresDirective (com.github.javaparser.ast.modules)
        ModuleUsesDirective (com.github.javaparser.ast.modules)
    Name (com.github.javaparser.ast.expr)
    PackageDeclaration (com.github.javaparser.ast)
    Parameter (com.github.javaparser.ast.body)
    ReceiverParameter (com.github.javaparser.ast.body)
    SimpleName (com.github.javaparser.ast.expr)
    Statement (com.github.javaparser.ast.stmt)
        AssertStmt (com.github.javaparser.ast.stmt)
        BlockStmt (com.github.javaparser.ast.stmt)
        BreakStmt (com.github.javaparser.ast.stmt)
        ContinueStmt (com.github.javaparser.ast.stmt)
        DoStmt (com.github.javaparser.ast.stmt)
        EmptyStmt (com.github.javaparser.ast.stmt)
        ExplicitConstructorInvocationStmt (com.github.javaparser.ast.stmt)
        ExpressionStmt (com.github.javaparser.ast.stmt)
        ForEachStmt (com.github.javaparser.ast.stmt)
        ForStmt (com.github.javaparser.ast.stmt)
        IfStmt (com.github.javaparser.ast.stmt)
        LabeledStmt (com.github.javaparser.ast.stmt)
        LocalClassDeclarationStmt (com.github.javaparser.ast.stmt)
        LocalRecordDeclarationStmt (com.github.javaparser.ast.stmt)
        ReturnStmt (com.github.javaparser.ast.stmt)
        SwitchStmt (com.github.javaparser.ast.stmt)
        SynchronizedStmt (com.github.javaparser.ast.stmt)
        ThrowStmt (com.github.javaparser.ast.stmt)
        TryStmt (com.github.javaparser.ast.stmt)
        UnparsableStmt (com.github.javaparser.ast.stmt)
        WhileStmt (com.github.javaparser.ast.stmt)
        YieldStmt (com.github.javaparser.ast.stmt)
    SwitchEntry (com.github.javaparser.ast.stmt)
    Type (com.github.javaparser.ast.type)
        IntersectionType (com.github.javaparser.ast.type)
        PrimitiveType (com.github.javaparser.ast.type)
        ReferenceType (com.github.javaparser.ast.type)
            ArrayType (com.github.javaparser.ast.type)
            ClassOrInterfaceType (com.github.javaparser.ast.type)
            TypeParameter (com.github.javaparser.ast.type)
        UnionType (com.github.javaparser.ast.type)
        UnknownType (com.github.javaparser.ast.type)
        VarType (com.github.javaparser.ast.type)
        VoidType (com.github.javaparser.ast.type)
        WildcardType (com.github.javaparser.ast.type)
    VariableDeclarator (com.github.javaparser.ast.body)
