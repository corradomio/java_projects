package jext.sourcecode.project.csharp;

import jext.antlr.v4.CSharpParsing;
import jext.antlr.v4.ParseResult;
import jext.antlr.v4.csharp.CSharpParser;
import jext.antlr.v4.csharp.CSharpParserBaseListener;
import jext.name.Name;
import jext.name.ObjectName;
import jext.sourcecode.project.Module;
import jext.sourcecode.project.ProjectException;
import jext.sourcecode.project.RefType;
import jext.sourcecode.project.Source;
import jext.sourcecode.project.util.SourceCode;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.File;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class CSharpSourceCode extends SourceCode {

    private Set<RefType> definedTypes;
    private Set<RefType> usedTypes;

    // ----------------------------------------------------------------------
    //
    // ----------------------------------------------------------------------

    public static Source newSource(File sourceFile, Module module) {
        String name = sourceFile.getName();

        if (name.endsWith(CSharpConstants.CSHARP_EXT))
            return new CSharpSourceCode(sourceFile, module);

        throw new ProjectException("Unsupported source file " + sourceFile);
    }

    private CSharpSourceCode(File file, Module module) {
        super(file, module);
    }

    @Override
    public Optional<String> getSourceRoot() {
        return Optional.empty();
    }

    @Override
    public Set<RefType> getTypes() {
        if (definedTypes == null)
            populateTypes();
        return definedTypes;

    }

    @Override
    public Set<RefType> getUsedTypes() {
        if (usedTypes == null)
            populateTypes();
        return usedTypes;
    }

    private void populateTypes(){
        definedTypes = new HashSet<>();
        usedTypes = new HashSet<>();

        ParseResult<CSharpParser.Compilation_unitContext>
            result =  CSharpParsing.parse(file);

        result.ifSuccessful(tree -> {
            ParseTreeWalker ptw = new ParseTreeWalker();
            ptw.walk(new CSharpParserBaseListener() {

                // Namespace_declaration
                //         NAMESPACE qi=qualified_identifier namespace_body ';'?
                @Override
                public void enterNamespace_declaration(CSharpParser.Namespace_declarationContext ctx) {
                    String qi = ctx.getChild(1).getText();
                    // logger.printf("    namespace %s", qi);
                    definedTypes.add(Namespace.of(qi));
                }

                // USING identifier '=' namespace_or_type_name ';'            #usingAliasDirective
                @Override
                public void enterUsingAliasDirective(CSharpParser.UsingAliasDirectiveContext ctx) {
                    String identifier = ctx.getChild(1).getText();
                    String namespace_or_type_name = ctx.getChild(3).getText();
                    //logger.printf("    using %s = %s", identifier, namespace_or_type_name);
                    usedTypes.add(Namespace.of(namespace_or_type_name));
                }

                // USING namespace_or_type_name ';'                           #usingNamespaceDirective
                @Override
                public void enterUsingNamespaceDirective(CSharpParser.UsingNamespaceDirectiveContext ctx) {
                    String namespace_or_type_name = ctx.getChild(1).getText();
                    //logger.printf("    using %s", namespace_or_type_name);
                    usedTypes.add(Namespace.of(namespace_or_type_name));
                }

                // USING STATIC namespace_or_type_name ';'                    #usingStaticDirective
                @Override
                public void enterUsingStaticDirective(CSharpParser.UsingStaticDirectiveContext ctx) {
                    String namespace_or_type_name = ctx.getChild(2).getText();
                    //logger.printf("    using static %s", namespace_or_type_name);
                    Name name = ObjectName.of(namespace_or_type_name).getParent();
                    usedTypes.add(Namespace.of(name));
                }

            }, tree);
        });

    }
}
