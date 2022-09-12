// Generated from CSharpParser.g4 by ANTLR 4.7.2
package jext.antlr.v4.csharp;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class CSharpParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		BYTE_ORDER_MARK=1, SINGLE_LINE_DOC_COMMENT=2, EMPTY_DELIMITED_DOC_COMMENT=3, 
		DELIMITED_DOC_COMMENT=4, SINGLE_LINE_COMMENT=5, DELIMITED_COMMENT=6, PRAGMA_COMMENT=7, 
		WHITESPACES=8, ABSTRACT=9, ADD=10, ALIAS=11, ARGLIST=12, AS=13, ASCENDING=14, 
		ASYNC=15, AWAIT=16, BASE=17, BOOL=18, BREAK=19, BY=20, BYTE=21, CASE=22, 
		CATCH=23, CHAR=24, CHECKED=25, CLASS=26, CONST=27, CONTINUE=28, DECIMAL=29, 
		DEFAULT=30, DELEGATE=31, DESCENDING=32, DO=33, DOUBLE=34, DYNAMIC=35, 
		ELSE=36, ENUM=37, EQUALS=38, EVENT=39, EXPLICIT=40, EXTERN=41, FALSE=42, 
		FINAL=43, FINALLY=44, FIXED=45, FLOAT=46, FOR=47, FOREACH=48, FROM=49, 
		GET=50, GOTO=51, GROUP=52, IF=53, IMPLICIT=54, IN=55, INT=56, INTERFACE=57, 
		INTERNAL=58, INTO=59, IS=60, JOIN=61, LET=62, LOCK=63, LONG=64, NAMEOF=65, 
		NAMESPACE=66, NATIVE=67, NEW=68, NOTNULL=69, NULL_=70, OBJECT=71, ON=72, 
		OPERATOR=73, ORDERBY=74, OUT=75, OVERRIDE=76, PARAMS=77, PARTIAL=78, PRIVATE=79, 
		PROTECTED=80, PUBLIC=81, READONLY=82, REF=83, REMOVE=84, RETURN=85, SBYTE=86, 
		SEALED=87, SELECT=88, SET=89, SHORT=90, SIZEOF=91, STACKALLOC=92, STATIC=93, 
		STRING=94, STRUCT=95, SWITCH=96, THIS=97, THROW=98, THROWS=99, TRUE=100, 
		TRY=101, TYPEOF=102, UINT=103, ULONG=104, UNCHECKED=105, UNMANAGED=106, 
		UNSAFE=107, USHORT=108, USING=109, VAR=110, VIRTUAL=111, VOID=112, VOLATILE=113, 
		WHEN=114, WHERE=115, WHILE=116, YIELD=117, IDENTIFIER=118, LITERAL_ACCESS=119, 
		INTEGER_LITERAL=120, HEX_INTEGER_LITERAL=121, BIN_INTEGER_LITERAL=122, 
		REAL_LITERAL=123, CHARACTER_LITERAL=124, REGULAR_STRING=125, VERBATIUM_STRING=126, 
		INTERPOLATED_REGULAR_STRING_START=127, INTERPOLATED_VERBATIUM_STRING_START=128, 
		OPEN_BRACE=129, CLOSE_BRACE=130, OPEN_BRACKET=131, CLOSE_BRACKET=132, 
		OPEN_PARENS=133, CLOSE_PARENS=134, DOT=135, COMMA=136, COLON=137, SEMICOLON=138, 
		PLUS=139, MINUS=140, STAR=141, DIV=142, PERCENT=143, AMP=144, BITWISE_OR=145, 
		CARET=146, BANG=147, TILDE=148, ASSIGNMENT=149, LT=150, GT=151, INTERR=152, 
		DOUBLE_COLON=153, OP_COALESCING=154, OP_INC=155, OP_DEC=156, OP_AND=157, 
		OP_OR=158, OP_PTR=159, OP_EQ=160, OP_NE=161, OP_LE=162, OP_GE=163, OP_ADD_ASSIGNMENT=164, 
		OP_SUB_ASSIGNMENT=165, OP_MULT_ASSIGNMENT=166, OP_DIV_ASSIGNMENT=167, 
		OP_MOD_ASSIGNMENT=168, OP_AND_ASSIGNMENT=169, OP_OR_ASSIGNMENT=170, OP_XOR_ASSIGNMENT=171, 
		OP_LEFT_SHIFT=172, OP_LEFT_SHIFT_ASSIGNMENT=173, OP_COALESCING_ASSIGNMENT=174, 
		OP_RANGE=175, DOUBLE_CURLY_INSIDE=176, OPEN_BRACE_INSIDE=177, REGULAR_CHAR_INSIDE=178, 
		VERBATIUM_DOUBLE_QUOTE_INSIDE=179, DOUBLE_QUOTE_INSIDE=180, REGULAR_STRING_INSIDE=181, 
		VERBATIUM_INSIDE_STRING=182, CLOSE_BRACE_INSIDE=183, FORMAT_STRING=184, 
		DIRECTIVE_WHITESPACES=185, DIGITS=186, DEFINE=187, UNDEF=188, ELIF=189, 
		ENDIF=190, LINE=191, ERROR=192, WARNING=193, REGION=194, ENDREGION=195, 
		PRAGMA=196, NULLABLE=197, DIRECTIVE_HIDDEN=198, CONDITIONAL_SYMBOL=199, 
		DIRECTIVE_NEW_LINE=200, TEXT=201, DOUBLE_CURLY_CLOSE_INSIDE=202;
	public static final int
		RULE_compilation_unit = 0, RULE_namespace_or_type_name = 1, RULE_type_ = 2, 
		RULE_base_type = 3, RULE_tuple_type = 4, RULE_tuple_element = 5, RULE_simple_type = 6, 
		RULE_numeric_type = 7, RULE_integral_type = 8, RULE_floating_point_type = 9, 
		RULE_class_type = 10, RULE_type_argument_list = 11, RULE_argument_list = 12, 
		RULE_argument = 13, RULE_expression = 14, RULE_non_assignment_expression = 15, 
		RULE_assignment = 16, RULE_assignment_operator = 17, RULE_conditional_expression = 18, 
		RULE_null_coalescing_expression = 19, RULE_conditional_or_expression = 20, 
		RULE_conditional_and_expression = 21, RULE_inclusive_or_expression = 22, 
		RULE_exclusive_or_expression = 23, RULE_and_expression = 24, RULE_equality_expression = 25, 
		RULE_relational_expression = 26, RULE_shift_expression = 27, RULE_additive_expression = 28, 
		RULE_multiplicative_expression = 29, RULE_switch_expression = 30, RULE_switch_expression_arms = 31, 
		RULE_switch_expression_arm = 32, RULE_range_expression = 33, RULE_unary_expression = 34, 
		RULE_primary_expression = 35, RULE_primary_expression_start = 36, RULE_throwable_expression = 37, 
		RULE_throw_expression = 38, RULE_member_access = 39, RULE_bracket_expression = 40, 
		RULE_indexer_argument = 41, RULE_predefined_type = 42, RULE_expression_list = 43, 
		RULE_object_or_collection_initializer = 44, RULE_object_initializer = 45, 
		RULE_member_initializer_list = 46, RULE_member_initializer = 47, RULE_initializer_value = 48, 
		RULE_collection_initializer = 49, RULE_element_initializer = 50, RULE_anonymous_object_initializer = 51, 
		RULE_member_declarator_list = 52, RULE_member_declarator = 53, RULE_unbound_type_name = 54, 
		RULE_generic_dimension_specifier = 55, RULE_isType = 56, RULE_isTypePatternArms = 57, 
		RULE_isTypePatternArm = 58, RULE_lambda_expression = 59, RULE_anonymous_function_signature = 60, 
		RULE_explicit_anonymous_function_parameter_list = 61, RULE_explicit_anonymous_function_parameter = 62, 
		RULE_implicit_anonymous_function_parameter_list = 63, RULE_anonymous_function_body = 64, 
		RULE_query_expression = 65, RULE_from_clause = 66, RULE_query_body = 67, 
		RULE_query_body_clause = 68, RULE_let_clause = 69, RULE_where_clause = 70, 
		RULE_combined_join_clause = 71, RULE_orderby_clause = 72, RULE_ordering = 73, 
		RULE_select_or_group_clause = 74, RULE_query_continuation = 75, RULE_statement = 76, 
		RULE_declarationStatement = 77, RULE_local_function_declaration = 78, 
		RULE_local_function_header = 79, RULE_local_function_modifiers = 80, RULE_local_function_body = 81, 
		RULE_labeled_Statement = 82, RULE_embedded_statement = 83, RULE_simple_embedded_statement = 84, 
		RULE_block = 85, RULE_local_variable_declaration = 86, RULE_local_variable_type = 87, 
		RULE_local_variable_declarator = 88, RULE_local_variable_initializer = 89, 
		RULE_local_constant_declaration = 90, RULE_if_body = 91, RULE_switch_section = 92, 
		RULE_switch_label = 93, RULE_case_guard = 94, RULE_statement_list = 95, 
		RULE_for_initializer = 96, RULE_for_iterator = 97, RULE_catch_clauses = 98, 
		RULE_specific_catch_clause = 99, RULE_general_catch_clause = 100, RULE_exception_filter = 101, 
		RULE_finally_clause = 102, RULE_resource_acquisition = 103, RULE_namespace_declaration = 104, 
		RULE_qualified_identifier = 105, RULE_namespace_body = 106, RULE_extern_alias_directives = 107, 
		RULE_extern_alias_directive = 108, RULE_using_directives = 109, RULE_using_directive = 110, 
		RULE_namespace_member_declarations = 111, RULE_namespace_member_declaration = 112, 
		RULE_type_declaration = 113, RULE_qualified_alias_member = 114, RULE_type_parameter_list = 115, 
		RULE_type_parameter = 116, RULE_class_base = 117, RULE_interface_type_list = 118, 
		RULE_type_parameter_constraints_clauses = 119, RULE_type_parameter_constraints_clause = 120, 
		RULE_type_parameter_constraints = 121, RULE_primary_constraint = 122, 
		RULE_secondary_constraints = 123, RULE_constructor_constraint = 124, RULE_class_body = 125, 
		RULE_class_member_declarations = 126, RULE_class_member_declaration = 127, 
		RULE_all_member_modifiers = 128, RULE_all_member_modifier = 129, RULE_common_member_declaration = 130, 
		RULE_typed_member_declaration = 131, RULE_constant_declarators = 132, 
		RULE_constant_declarator = 133, RULE_variable_declarators = 134, RULE_variable_declarator = 135, 
		RULE_variable_initializer = 136, RULE_return_type = 137, RULE_member_name = 138, 
		RULE_method_body = 139, RULE_formal_parameter_list = 140, RULE_fixed_parameters = 141, 
		RULE_fixed_parameter = 142, RULE_parameter_modifier = 143, RULE_parameter_array = 144, 
		RULE_accessor_declarations = 145, RULE_get_accessor_declaration = 146, 
		RULE_set_accessor_declaration = 147, RULE_accessor_modifier = 148, RULE_accessor_body = 149, 
		RULE_event_accessor_declarations = 150, RULE_add_accessor_declaration = 151, 
		RULE_remove_accessor_declaration = 152, RULE_overloadable_operator = 153, 
		RULE_conversion_operator_declarator = 154, RULE_constructor_initializer = 155, 
		RULE_body = 156, RULE_struct_interfaces = 157, RULE_struct_body = 158, 
		RULE_struct_member_declaration = 159, RULE_array_type = 160, RULE_rank_specifier = 161, 
		RULE_array_initializer = 162, RULE_variant_type_parameter_list = 163, 
		RULE_variant_type_parameter = 164, RULE_variance_annotation = 165, RULE_interface_base = 166, 
		RULE_interface_body = 167, RULE_interface_member_declaration = 168, RULE_interface_accessors = 169, 
		RULE_enum_base = 170, RULE_enum_body = 171, RULE_enum_member_declaration = 172, 
		RULE_global_attribute_section = 173, RULE_global_attribute_target = 174, 
		RULE_attributes = 175, RULE_attribute_section = 176, RULE_attribute_target = 177, 
		RULE_attribute_list = 178, RULE_attribute = 179, RULE_attribute_argument = 180, 
		RULE_pointer_type = 181, RULE_fixed_pointer_declarators = 182, RULE_fixed_pointer_declarator = 183, 
		RULE_fixed_pointer_initializer = 184, RULE_fixed_size_buffer_declarator = 185, 
		RULE_stackalloc_initializer = 186, RULE_right_arrow = 187, RULE_right_shift = 188, 
		RULE_right_shift_assignment = 189, RULE_literal = 190, RULE_boolean_literal = 191, 
		RULE_string_literal = 192, RULE_interpolated_regular_string = 193, RULE_interpolated_verbatium_string = 194, 
		RULE_interpolated_regular_string_part = 195, RULE_interpolated_verbatium_string_part = 196, 
		RULE_interpolated_string_expression = 197, RULE_keyword = 198, RULE_class_definition = 199, 
		RULE_struct_definition = 200, RULE_interface_definition = 201, RULE_enum_definition = 202, 
		RULE_delegate_definition = 203, RULE_event_declaration = 204, RULE_field_declaration = 205, 
		RULE_property_declaration = 206, RULE_constant_declaration = 207, RULE_indexer_declaration = 208, 
		RULE_destructor_definition = 209, RULE_constructor_declaration = 210, 
		RULE_method_declaration = 211, RULE_method_member_name = 212, RULE_operator_declaration = 213, 
		RULE_arg_declaration = 214, RULE_method_invocation = 215, RULE_object_creation_expression = 216, 
		RULE_identifier = 217;
	private static String[] makeRuleNames() {
		return new String[] {
			"compilation_unit", "namespace_or_type_name", "type_", "base_type", "tuple_type", 
			"tuple_element", "simple_type", "numeric_type", "integral_type", "floating_point_type", 
			"class_type", "type_argument_list", "argument_list", "argument", "expression", 
			"non_assignment_expression", "assignment", "assignment_operator", "conditional_expression", 
			"null_coalescing_expression", "conditional_or_expression", "conditional_and_expression", 
			"inclusive_or_expression", "exclusive_or_expression", "and_expression", 
			"equality_expression", "relational_expression", "shift_expression", "additive_expression", 
			"multiplicative_expression", "switch_expression", "switch_expression_arms", 
			"switch_expression_arm", "range_expression", "unary_expression", "primary_expression", 
			"primary_expression_start", "throwable_expression", "throw_expression", 
			"member_access", "bracket_expression", "indexer_argument", "predefined_type", 
			"expression_list", "object_or_collection_initializer", "object_initializer", 
			"member_initializer_list", "member_initializer", "initializer_value", 
			"collection_initializer", "element_initializer", "anonymous_object_initializer", 
			"member_declarator_list", "member_declarator", "unbound_type_name", "generic_dimension_specifier", 
			"isType", "isTypePatternArms", "isTypePatternArm", "lambda_expression", 
			"anonymous_function_signature", "explicit_anonymous_function_parameter_list", 
			"explicit_anonymous_function_parameter", "implicit_anonymous_function_parameter_list", 
			"anonymous_function_body", "query_expression", "from_clause", "query_body", 
			"query_body_clause", "let_clause", "where_clause", "combined_join_clause", 
			"orderby_clause", "ordering", "select_or_group_clause", "query_continuation", 
			"statement", "declarationStatement", "local_function_declaration", "local_function_header", 
			"local_function_modifiers", "local_function_body", "labeled_Statement", 
			"embedded_statement", "simple_embedded_statement", "block", "local_variable_declaration", 
			"local_variable_type", "local_variable_declarator", "local_variable_initializer", 
			"local_constant_declaration", "if_body", "switch_section", "switch_label", 
			"case_guard", "statement_list", "for_initializer", "for_iterator", "catch_clauses", 
			"specific_catch_clause", "general_catch_clause", "exception_filter", 
			"finally_clause", "resource_acquisition", "namespace_declaration", "qualified_identifier", 
			"namespace_body", "extern_alias_directives", "extern_alias_directive", 
			"using_directives", "using_directive", "namespace_member_declarations", 
			"namespace_member_declaration", "type_declaration", "qualified_alias_member", 
			"type_parameter_list", "type_parameter", "class_base", "interface_type_list", 
			"type_parameter_constraints_clauses", "type_parameter_constraints_clause", 
			"type_parameter_constraints", "primary_constraint", "secondary_constraints", 
			"constructor_constraint", "class_body", "class_member_declarations", 
			"class_member_declaration", "all_member_modifiers", "all_member_modifier", 
			"common_member_declaration", "typed_member_declaration", "constant_declarators", 
			"constant_declarator", "variable_declarators", "variable_declarator", 
			"variable_initializer", "return_type", "member_name", "method_body", 
			"formal_parameter_list", "fixed_parameters", "fixed_parameter", "parameter_modifier", 
			"parameter_array", "accessor_declarations", "get_accessor_declaration", 
			"set_accessor_declaration", "accessor_modifier", "accessor_body", "event_accessor_declarations", 
			"add_accessor_declaration", "remove_accessor_declaration", "overloadable_operator", 
			"conversion_operator_declarator", "constructor_initializer", "body", 
			"struct_interfaces", "struct_body", "struct_member_declaration", "array_type", 
			"rank_specifier", "array_initializer", "variant_type_parameter_list", 
			"variant_type_parameter", "variance_annotation", "interface_base", "interface_body", 
			"interface_member_declaration", "interface_accessors", "enum_base", "enum_body", 
			"enum_member_declaration", "global_attribute_section", "global_attribute_target", 
			"attributes", "attribute_section", "attribute_target", "attribute_list", 
			"attribute", "attribute_argument", "pointer_type", "fixed_pointer_declarators", 
			"fixed_pointer_declarator", "fixed_pointer_initializer", "fixed_size_buffer_declarator", 
			"stackalloc_initializer", "right_arrow", "right_shift", "right_shift_assignment", 
			"literal", "boolean_literal", "string_literal", "interpolated_regular_string", 
			"interpolated_verbatium_string", "interpolated_regular_string_part", 
			"interpolated_verbatium_string_part", "interpolated_string_expression", 
			"keyword", "class_definition", "struct_definition", "interface_definition", 
			"enum_definition", "delegate_definition", "event_declaration", "field_declaration", 
			"property_declaration", "constant_declaration", "indexer_declaration", 
			"destructor_definition", "constructor_declaration", "method_declaration", 
			"method_member_name", "operator_declaration", "arg_declaration", "method_invocation", 
			"object_creation_expression", "identifier"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'\u00EF\u00BB\u00BF'", null, "'/***/'", null, null, null, null, 
			null, "'abstract'", "'add'", "'alias'", "'__arglist'", "'as'", "'ascending'", 
			"'async'", "'await'", "'base'", "'bool'", "'break'", "'by'", "'byte'", 
			"'case'", "'catch'", "'char'", "'checked'", "'class'", "'const'", "'continue'", 
			"'decimal'", "'default'", "'delegate'", "'descending'", "'do'", "'double'", 
			"'dynamic'", "'else'", "'enum'", "'equals'", "'event'", "'explicit'", 
			"'extern'", "'false'", "'final'", "'finally'", "'fixed'", "'float'", 
			"'for'", "'foreach'", "'from'", "'get'", "'goto'", "'group'", "'if'", 
			"'implicit'", "'in'", "'int'", "'interface'", "'internal'", "'into'", 
			"'is'", "'join'", "'let'", "'lock'", "'long'", "'nameof'", "'namespace'", 
			"'native'", "'new'", "'notnull'", "'null'", "'object'", "'on'", "'operator'", 
			"'orderby'", "'out'", "'override'", "'params'", "'partial'", "'private'", 
			"'protected'", "'public'", "'readonly'", "'ref'", "'remove'", "'return'", 
			"'sbyte'", "'sealed'", "'select'", "'set'", "'short'", "'sizeof'", "'stackalloc'", 
			"'static'", "'string'", "'struct'", "'switch'", "'this'", "'throw'", 
			"'throws'", "'true'", "'try'", "'typeof'", "'uint'", "'ulong'", "'unchecked'", 
			"'unmanaged'", "'unsafe'", "'ushort'", "'using'", "'var'", "'virtual'", 
			"'void'", "'volatile'", "'when'", "'where'", "'while'", "'yield'", null, 
			null, null, null, null, null, null, null, null, null, null, "'{'", "'}'", 
			"'['", "']'", "'('", "')'", "'.'", "','", "':'", "';'", "'+'", "'-'", 
			"'*'", "'/'", "'%'", "'&'", "'|'", "'^'", "'!'", "'~'", "'='", "'<'", 
			"'>'", "'?'", "'::'", "'??'", "'++'", "'--'", "'&&'", "'||'", "'->'", 
			"'=='", "'!='", "'<='", "'>='", "'+='", "'-='", "'*='", "'/='", "'%='", 
			"'&='", "'|='", "'^='", "'<<'", "'<<='", "'??='", "'..'", "'{{'", null, 
			null, null, null, null, null, null, null, null, null, "'define'", "'undef'", 
			"'elif'", "'endif'", "'line'", null, null, null, null, null, null, "'hidden'", 
			null, null, null, "'}}'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "BYTE_ORDER_MARK", "SINGLE_LINE_DOC_COMMENT", "EMPTY_DELIMITED_DOC_COMMENT", 
			"DELIMITED_DOC_COMMENT", "SINGLE_LINE_COMMENT", "DELIMITED_COMMENT", 
			"PRAGMA_COMMENT", "WHITESPACES", "ABSTRACT", "ADD", "ALIAS", "ARGLIST", 
			"AS", "ASCENDING", "ASYNC", "AWAIT", "BASE", "BOOL", "BREAK", "BY", "BYTE", 
			"CASE", "CATCH", "CHAR", "CHECKED", "CLASS", "CONST", "CONTINUE", "DECIMAL", 
			"DEFAULT", "DELEGATE", "DESCENDING", "DO", "DOUBLE", "DYNAMIC", "ELSE", 
			"ENUM", "EQUALS", "EVENT", "EXPLICIT", "EXTERN", "FALSE", "FINAL", "FINALLY", 
			"FIXED", "FLOAT", "FOR", "FOREACH", "FROM", "GET", "GOTO", "GROUP", "IF", 
			"IMPLICIT", "IN", "INT", "INTERFACE", "INTERNAL", "INTO", "IS", "JOIN", 
			"LET", "LOCK", "LONG", "NAMEOF", "NAMESPACE", "NATIVE", "NEW", "NOTNULL", 
			"NULL_", "OBJECT", "ON", "OPERATOR", "ORDERBY", "OUT", "OVERRIDE", "PARAMS", 
			"PARTIAL", "PRIVATE", "PROTECTED", "PUBLIC", "READONLY", "REF", "REMOVE", 
			"RETURN", "SBYTE", "SEALED", "SELECT", "SET", "SHORT", "SIZEOF", "STACKALLOC", 
			"STATIC", "STRING", "STRUCT", "SWITCH", "THIS", "THROW", "THROWS", "TRUE", 
			"TRY", "TYPEOF", "UINT", "ULONG", "UNCHECKED", "UNMANAGED", "UNSAFE", 
			"USHORT", "USING", "VAR", "VIRTUAL", "VOID", "VOLATILE", "WHEN", "WHERE", 
			"WHILE", "YIELD", "IDENTIFIER", "LITERAL_ACCESS", "INTEGER_LITERAL", 
			"HEX_INTEGER_LITERAL", "BIN_INTEGER_LITERAL", "REAL_LITERAL", "CHARACTER_LITERAL", 
			"REGULAR_STRING", "VERBATIUM_STRING", "INTERPOLATED_REGULAR_STRING_START", 
			"INTERPOLATED_VERBATIUM_STRING_START", "OPEN_BRACE", "CLOSE_BRACE", "OPEN_BRACKET", 
			"CLOSE_BRACKET", "OPEN_PARENS", "CLOSE_PARENS", "DOT", "COMMA", "COLON", 
			"SEMICOLON", "PLUS", "MINUS", "STAR", "DIV", "PERCENT", "AMP", "BITWISE_OR", 
			"CARET", "BANG", "TILDE", "ASSIGNMENT", "LT", "GT", "INTERR", "DOUBLE_COLON", 
			"OP_COALESCING", "OP_INC", "OP_DEC", "OP_AND", "OP_OR", "OP_PTR", "OP_EQ", 
			"OP_NE", "OP_LE", "OP_GE", "OP_ADD_ASSIGNMENT", "OP_SUB_ASSIGNMENT", 
			"OP_MULT_ASSIGNMENT", "OP_DIV_ASSIGNMENT", "OP_MOD_ASSIGNMENT", "OP_AND_ASSIGNMENT", 
			"OP_OR_ASSIGNMENT", "OP_XOR_ASSIGNMENT", "OP_LEFT_SHIFT", "OP_LEFT_SHIFT_ASSIGNMENT", 
			"OP_COALESCING_ASSIGNMENT", "OP_RANGE", "DOUBLE_CURLY_INSIDE", "OPEN_BRACE_INSIDE", 
			"REGULAR_CHAR_INSIDE", "VERBATIUM_DOUBLE_QUOTE_INSIDE", "DOUBLE_QUOTE_INSIDE", 
			"REGULAR_STRING_INSIDE", "VERBATIUM_INSIDE_STRING", "CLOSE_BRACE_INSIDE", 
			"FORMAT_STRING", "DIRECTIVE_WHITESPACES", "DIGITS", "DEFINE", "UNDEF", 
			"ELIF", "ENDIF", "LINE", "ERROR", "WARNING", "REGION", "ENDREGION", "PRAGMA", 
			"NULLABLE", "DIRECTIVE_HIDDEN", "CONDITIONAL_SYMBOL", "DIRECTIVE_NEW_LINE", 
			"TEXT", "DOUBLE_CURLY_CLOSE_INSIDE"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "CSharpParser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public CSharpParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class Compilation_unitContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(CSharpParser.EOF, 0); }
		public TerminalNode BYTE_ORDER_MARK() { return getToken(CSharpParser.BYTE_ORDER_MARK, 0); }
		public Extern_alias_directivesContext extern_alias_directives() {
			return getRuleContext(Extern_alias_directivesContext.class,0);
		}
		public Using_directivesContext using_directives() {
			return getRuleContext(Using_directivesContext.class,0);
		}
		public List<Global_attribute_sectionContext> global_attribute_section() {
			return getRuleContexts(Global_attribute_sectionContext.class);
		}
		public Global_attribute_sectionContext global_attribute_section(int i) {
			return getRuleContext(Global_attribute_sectionContext.class,i);
		}
		public Namespace_member_declarationsContext namespace_member_declarations() {
			return getRuleContext(Namespace_member_declarationsContext.class,0);
		}
		public Compilation_unitContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_compilation_unit; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterCompilation_unit(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitCompilation_unit(this);
		}
	}

	public final Compilation_unitContext compilation_unit() throws RecognitionException {
		Compilation_unitContext _localctx = new Compilation_unitContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_compilation_unit);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(437);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==BYTE_ORDER_MARK) {
				{
				setState(436);
				match(BYTE_ORDER_MARK);
				}
			}

			setState(440);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				{
				setState(439);
				extern_alias_directives();
				}
				break;
			}
			setState(443);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==USING) {
				{
				setState(442);
				using_directives();
				}
			}

			setState(448);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(445);
					global_attribute_section();
					}
					} 
				}
				setState(450);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
			}
			setState(452);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (((((_la - 9)) & ~0x3f) == 0 && ((1L << (_la - 9)) & ((1L << (ABSTRACT - 9)) | (1L << (ASYNC - 9)) | (1L << (CLASS - 9)) | (1L << (DELEGATE - 9)) | (1L << (ENUM - 9)) | (1L << (EXTERN - 9)) | (1L << (FINAL - 9)) | (1L << (INTERFACE - 9)) | (1L << (INTERNAL - 9)) | (1L << (NAMESPACE - 9)) | (1L << (NATIVE - 9)) | (1L << (NEW - 9)))) != 0) || ((((_la - 76)) & ~0x3f) == 0 && ((1L << (_la - 76)) & ((1L << (OVERRIDE - 76)) | (1L << (PARTIAL - 76)) | (1L << (PRIVATE - 76)) | (1L << (PROTECTED - 76)) | (1L << (PUBLIC - 76)) | (1L << (READONLY - 76)) | (1L << (REF - 76)) | (1L << (SEALED - 76)) | (1L << (STATIC - 76)) | (1L << (STRUCT - 76)) | (1L << (UNSAFE - 76)) | (1L << (VIRTUAL - 76)) | (1L << (VOLATILE - 76)) | (1L << (OPEN_BRACKET - 76)))) != 0)) {
				{
				setState(451);
				namespace_member_declarations();
				}
			}

			setState(454);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Namespace_or_type_nameContext extends ParserRuleContext {
		public List<IdentifierContext> identifier() {
			return getRuleContexts(IdentifierContext.class);
		}
		public IdentifierContext identifier(int i) {
			return getRuleContext(IdentifierContext.class,i);
		}
		public Qualified_alias_memberContext qualified_alias_member() {
			return getRuleContext(Qualified_alias_memberContext.class,0);
		}
		public List<TerminalNode> DOT() { return getTokens(CSharpParser.DOT); }
		public TerminalNode DOT(int i) {
			return getToken(CSharpParser.DOT, i);
		}
		public List<Type_argument_listContext> type_argument_list() {
			return getRuleContexts(Type_argument_listContext.class);
		}
		public Type_argument_listContext type_argument_list(int i) {
			return getRuleContext(Type_argument_listContext.class,i);
		}
		public Namespace_or_type_nameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_namespace_or_type_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterNamespace_or_type_name(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitNamespace_or_type_name(this);
		}
	}

	public final Namespace_or_type_nameContext namespace_or_type_name() throws RecognitionException {
		Namespace_or_type_nameContext _localctx = new Namespace_or_type_nameContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_namespace_or_type_name);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(461);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				{
				setState(456);
				identifier();
				setState(458);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
				case 1:
					{
					setState(457);
					type_argument_list();
					}
					break;
				}
				}
				break;
			case 2:
				{
				setState(460);
				qualified_alias_member();
				}
				break;
			}
			setState(470);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(463);
					match(DOT);
					setState(464);
					identifier();
					setState(466);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
					case 1:
						{
						setState(465);
						type_argument_list();
						}
						break;
					}
					}
					} 
				}
				setState(472);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Type_Context extends ParserRuleContext {
		public Base_typeContext base_type() {
			return getRuleContext(Base_typeContext.class,0);
		}
		public List<TerminalNode> INTERR() { return getTokens(CSharpParser.INTERR); }
		public TerminalNode INTERR(int i) {
			return getToken(CSharpParser.INTERR, i);
		}
		public List<Rank_specifierContext> rank_specifier() {
			return getRuleContexts(Rank_specifierContext.class);
		}
		public Rank_specifierContext rank_specifier(int i) {
			return getRuleContext(Rank_specifierContext.class,i);
		}
		public List<TerminalNode> STAR() { return getTokens(CSharpParser.STAR); }
		public TerminalNode STAR(int i) {
			return getToken(CSharpParser.STAR, i);
		}
		public Type_Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type_; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterType_(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitType_(this);
		}
	}

	public final Type_Context type_() throws RecognitionException {
		Type_Context _localctx = new Type_Context(_ctx, getState());
		enterRule(_localctx, 4, RULE_type_);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(473);
			base_type();
			setState(479);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					setState(477);
					_errHandler.sync(this);
					switch (_input.LA(1)) {
					case INTERR:
						{
						setState(474);
						match(INTERR);
						}
						break;
					case OPEN_BRACKET:
						{
						setState(475);
						rank_specifier();
						}
						break;
					case STAR:
						{
						setState(476);
						match(STAR);
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					} 
				}
				setState(481);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Base_typeContext extends ParserRuleContext {
		public Simple_typeContext simple_type() {
			return getRuleContext(Simple_typeContext.class,0);
		}
		public Class_typeContext class_type() {
			return getRuleContext(Class_typeContext.class,0);
		}
		public TerminalNode VOID() { return getToken(CSharpParser.VOID, 0); }
		public TerminalNode STAR() { return getToken(CSharpParser.STAR, 0); }
		public Tuple_typeContext tuple_type() {
			return getRuleContext(Tuple_typeContext.class,0);
		}
		public Base_typeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_base_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterBase_type(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitBase_type(this);
		}
	}

	public final Base_typeContext base_type() throws RecognitionException {
		Base_typeContext _localctx = new Base_typeContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_base_type);
		try {
			setState(487);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case BOOL:
			case BYTE:
			case CHAR:
			case DECIMAL:
			case DOUBLE:
			case FLOAT:
			case INT:
			case LONG:
			case SBYTE:
			case SHORT:
			case UINT:
			case ULONG:
			case USHORT:
				enterOuterAlt(_localctx, 1);
				{
				setState(482);
				simple_type();
				}
				break;
			case ADD:
			case ALIAS:
			case ARGLIST:
			case ASCENDING:
			case ASYNC:
			case AWAIT:
			case BY:
			case DESCENDING:
			case DYNAMIC:
			case EQUALS:
			case FROM:
			case GET:
			case GROUP:
			case INTO:
			case JOIN:
			case LET:
			case NAMEOF:
			case OBJECT:
			case ON:
			case ORDERBY:
			case PARTIAL:
			case REMOVE:
			case SELECT:
			case SET:
			case STRING:
			case UNMANAGED:
			case VAR:
			case WHEN:
			case WHERE:
			case YIELD:
			case IDENTIFIER:
				enterOuterAlt(_localctx, 2);
				{
				setState(483);
				class_type();
				}
				break;
			case VOID:
				enterOuterAlt(_localctx, 3);
				{
				setState(484);
				match(VOID);
				setState(485);
				match(STAR);
				}
				break;
			case OPEN_PARENS:
				enterOuterAlt(_localctx, 4);
				{
				setState(486);
				tuple_type();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Tuple_typeContext extends ParserRuleContext {
		public TerminalNode OPEN_PARENS() { return getToken(CSharpParser.OPEN_PARENS, 0); }
		public List<Tuple_elementContext> tuple_element() {
			return getRuleContexts(Tuple_elementContext.class);
		}
		public Tuple_elementContext tuple_element(int i) {
			return getRuleContext(Tuple_elementContext.class,i);
		}
		public TerminalNode CLOSE_PARENS() { return getToken(CSharpParser.CLOSE_PARENS, 0); }
		public List<TerminalNode> COMMA() { return getTokens(CSharpParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(CSharpParser.COMMA, i);
		}
		public Tuple_typeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tuple_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterTuple_type(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitTuple_type(this);
		}
	}

	public final Tuple_typeContext tuple_type() throws RecognitionException {
		Tuple_typeContext _localctx = new Tuple_typeContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_tuple_type);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(489);
			match(OPEN_PARENS);
			setState(490);
			tuple_element();
			setState(493); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(491);
				match(COMMA);
				setState(492);
				tuple_element();
				}
				}
				setState(495); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==COMMA );
			setState(497);
			match(CLOSE_PARENS);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Tuple_elementContext extends ParserRuleContext {
		public Type_Context type_() {
			return getRuleContext(Type_Context.class,0);
		}
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public Tuple_elementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tuple_element; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterTuple_element(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitTuple_element(this);
		}
	}

	public final Tuple_elementContext tuple_element() throws RecognitionException {
		Tuple_elementContext _localctx = new Tuple_elementContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_tuple_element);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(499);
			type_();
			setState(501);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ADD) | (1L << ALIAS) | (1L << ARGLIST) | (1L << ASCENDING) | (1L << ASYNC) | (1L << AWAIT) | (1L << BY) | (1L << DESCENDING) | (1L << DYNAMIC) | (1L << EQUALS) | (1L << FROM) | (1L << GET) | (1L << GROUP) | (1L << INTO) | (1L << JOIN) | (1L << LET))) != 0) || ((((_la - 65)) & ~0x3f) == 0 && ((1L << (_la - 65)) & ((1L << (NAMEOF - 65)) | (1L << (ON - 65)) | (1L << (ORDERBY - 65)) | (1L << (PARTIAL - 65)) | (1L << (REMOVE - 65)) | (1L << (SELECT - 65)) | (1L << (SET - 65)) | (1L << (UNMANAGED - 65)) | (1L << (VAR - 65)) | (1L << (WHEN - 65)) | (1L << (WHERE - 65)) | (1L << (YIELD - 65)) | (1L << (IDENTIFIER - 65)))) != 0)) {
				{
				setState(500);
				identifier();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Simple_typeContext extends ParserRuleContext {
		public Numeric_typeContext numeric_type() {
			return getRuleContext(Numeric_typeContext.class,0);
		}
		public TerminalNode BOOL() { return getToken(CSharpParser.BOOL, 0); }
		public Simple_typeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_simple_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterSimple_type(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitSimple_type(this);
		}
	}

	public final Simple_typeContext simple_type() throws RecognitionException {
		Simple_typeContext _localctx = new Simple_typeContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_simple_type);
		try {
			setState(505);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case BYTE:
			case CHAR:
			case DECIMAL:
			case DOUBLE:
			case FLOAT:
			case INT:
			case LONG:
			case SBYTE:
			case SHORT:
			case UINT:
			case ULONG:
			case USHORT:
				enterOuterAlt(_localctx, 1);
				{
				setState(503);
				numeric_type();
				}
				break;
			case BOOL:
				enterOuterAlt(_localctx, 2);
				{
				setState(504);
				match(BOOL);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Numeric_typeContext extends ParserRuleContext {
		public Integral_typeContext integral_type() {
			return getRuleContext(Integral_typeContext.class,0);
		}
		public Floating_point_typeContext floating_point_type() {
			return getRuleContext(Floating_point_typeContext.class,0);
		}
		public TerminalNode DECIMAL() { return getToken(CSharpParser.DECIMAL, 0); }
		public Numeric_typeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_numeric_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterNumeric_type(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitNumeric_type(this);
		}
	}

	public final Numeric_typeContext numeric_type() throws RecognitionException {
		Numeric_typeContext _localctx = new Numeric_typeContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_numeric_type);
		try {
			setState(510);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case BYTE:
			case CHAR:
			case INT:
			case LONG:
			case SBYTE:
			case SHORT:
			case UINT:
			case ULONG:
			case USHORT:
				enterOuterAlt(_localctx, 1);
				{
				setState(507);
				integral_type();
				}
				break;
			case DOUBLE:
			case FLOAT:
				enterOuterAlt(_localctx, 2);
				{
				setState(508);
				floating_point_type();
				}
				break;
			case DECIMAL:
				enterOuterAlt(_localctx, 3);
				{
				setState(509);
				match(DECIMAL);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Integral_typeContext extends ParserRuleContext {
		public TerminalNode SBYTE() { return getToken(CSharpParser.SBYTE, 0); }
		public TerminalNode BYTE() { return getToken(CSharpParser.BYTE, 0); }
		public TerminalNode SHORT() { return getToken(CSharpParser.SHORT, 0); }
		public TerminalNode USHORT() { return getToken(CSharpParser.USHORT, 0); }
		public TerminalNode INT() { return getToken(CSharpParser.INT, 0); }
		public TerminalNode UINT() { return getToken(CSharpParser.UINT, 0); }
		public TerminalNode LONG() { return getToken(CSharpParser.LONG, 0); }
		public TerminalNode ULONG() { return getToken(CSharpParser.ULONG, 0); }
		public TerminalNode CHAR() { return getToken(CSharpParser.CHAR, 0); }
		public Integral_typeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_integral_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterIntegral_type(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitIntegral_type(this);
		}
	}

	public final Integral_typeContext integral_type() throws RecognitionException {
		Integral_typeContext _localctx = new Integral_typeContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_integral_type);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(512);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BYTE) | (1L << CHAR) | (1L << INT))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (LONG - 64)) | (1L << (SBYTE - 64)) | (1L << (SHORT - 64)) | (1L << (UINT - 64)) | (1L << (ULONG - 64)) | (1L << (USHORT - 64)))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Floating_point_typeContext extends ParserRuleContext {
		public TerminalNode FLOAT() { return getToken(CSharpParser.FLOAT, 0); }
		public TerminalNode DOUBLE() { return getToken(CSharpParser.DOUBLE, 0); }
		public Floating_point_typeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_floating_point_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterFloating_point_type(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitFloating_point_type(this);
		}
	}

	public final Floating_point_typeContext floating_point_type() throws RecognitionException {
		Floating_point_typeContext _localctx = new Floating_point_typeContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_floating_point_type);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(514);
			_la = _input.LA(1);
			if ( !(_la==DOUBLE || _la==FLOAT) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Class_typeContext extends ParserRuleContext {
		public Namespace_or_type_nameContext namespace_or_type_name() {
			return getRuleContext(Namespace_or_type_nameContext.class,0);
		}
		public TerminalNode OBJECT() { return getToken(CSharpParser.OBJECT, 0); }
		public TerminalNode DYNAMIC() { return getToken(CSharpParser.DYNAMIC, 0); }
		public TerminalNode STRING() { return getToken(CSharpParser.STRING, 0); }
		public Class_typeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_class_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterClass_type(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitClass_type(this);
		}
	}

	public final Class_typeContext class_type() throws RecognitionException {
		Class_typeContext _localctx = new Class_typeContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_class_type);
		try {
			setState(520);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(516);
				namespace_or_type_name();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(517);
				match(OBJECT);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(518);
				match(DYNAMIC);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(519);
				match(STRING);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Type_argument_listContext extends ParserRuleContext {
		public TerminalNode LT() { return getToken(CSharpParser.LT, 0); }
		public List<Type_Context> type_() {
			return getRuleContexts(Type_Context.class);
		}
		public Type_Context type_(int i) {
			return getRuleContext(Type_Context.class,i);
		}
		public TerminalNode GT() { return getToken(CSharpParser.GT, 0); }
		public List<TerminalNode> COMMA() { return getTokens(CSharpParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(CSharpParser.COMMA, i);
		}
		public Type_argument_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type_argument_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterType_argument_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitType_argument_list(this);
		}
	}

	public final Type_argument_listContext type_argument_list() throws RecognitionException {
		Type_argument_listContext _localctx = new Type_argument_listContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_type_argument_list);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(522);
			match(LT);
			setState(523);
			type_();
			setState(528);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(524);
				match(COMMA);
				setState(525);
				type_();
				}
				}
				setState(530);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(531);
			match(GT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Argument_listContext extends ParserRuleContext {
		public List<ArgumentContext> argument() {
			return getRuleContexts(ArgumentContext.class);
		}
		public ArgumentContext argument(int i) {
			return getRuleContext(ArgumentContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(CSharpParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(CSharpParser.COMMA, i);
		}
		public Argument_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_argument_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterArgument_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitArgument_list(this);
		}
	}

	public final Argument_listContext argument_list() throws RecognitionException {
		Argument_listContext _localctx = new Argument_listContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_argument_list);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(533);
			argument();
			setState(538);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(534);
				match(COMMA);
				setState(535);
				argument();
				}
				}
				setState(540);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArgumentContext extends ParserRuleContext {
		public Token refout;
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TerminalNode COLON() { return getToken(CSharpParser.COLON, 0); }
		public TerminalNode VAR() { return getToken(CSharpParser.VAR, 0); }
		public Type_Context type_() {
			return getRuleContext(Type_Context.class,0);
		}
		public TerminalNode REF() { return getToken(CSharpParser.REF, 0); }
		public TerminalNode OUT() { return getToken(CSharpParser.OUT, 0); }
		public TerminalNode IN() { return getToken(CSharpParser.IN, 0); }
		public ArgumentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_argument; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterArgument(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitArgument(this);
		}
	}

	public final ArgumentContext argument() throws RecognitionException {
		ArgumentContext _localctx = new ArgumentContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_argument);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(544);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
			case 1:
				{
				setState(541);
				identifier();
				setState(542);
				match(COLON);
				}
				break;
			}
			setState(547);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,20,_ctx) ) {
			case 1:
				{
				setState(546);
				((ArgumentContext)_localctx).refout = _input.LT(1);
				_la = _input.LA(1);
				if ( !(((((_la - 55)) & ~0x3f) == 0 && ((1L << (_la - 55)) & ((1L << (IN - 55)) | (1L << (OUT - 55)) | (1L << (REF - 55)))) != 0)) ) {
					((ArgumentContext)_localctx).refout = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				break;
			}
			setState(551);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,21,_ctx) ) {
			case 1:
				{
				setState(549);
				match(VAR);
				}
				break;
			case 2:
				{
				setState(550);
				type_();
				}
				break;
			}
			setState(553);
			expression();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionContext extends ParserRuleContext {
		public AssignmentContext assignment() {
			return getRuleContext(AssignmentContext.class,0);
		}
		public Non_assignment_expressionContext non_assignment_expression() {
			return getRuleContext(Non_assignment_expressionContext.class,0);
		}
		public TerminalNode REF() { return getToken(CSharpParser.REF, 0); }
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitExpression(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_expression);
		try {
			setState(559);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,22,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(555);
				assignment();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(556);
				non_assignment_expression();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(557);
				match(REF);
				setState(558);
				non_assignment_expression();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Non_assignment_expressionContext extends ParserRuleContext {
		public Lambda_expressionContext lambda_expression() {
			return getRuleContext(Lambda_expressionContext.class,0);
		}
		public Query_expressionContext query_expression() {
			return getRuleContext(Query_expressionContext.class,0);
		}
		public Conditional_expressionContext conditional_expression() {
			return getRuleContext(Conditional_expressionContext.class,0);
		}
		public Non_assignment_expressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_non_assignment_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterNon_assignment_expression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitNon_assignment_expression(this);
		}
	}

	public final Non_assignment_expressionContext non_assignment_expression() throws RecognitionException {
		Non_assignment_expressionContext _localctx = new Non_assignment_expressionContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_non_assignment_expression);
		try {
			setState(564);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,23,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(561);
				lambda_expression();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(562);
				query_expression();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(563);
				conditional_expression();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AssignmentContext extends ParserRuleContext {
		public Unary_expressionContext unary_expression() {
			return getRuleContext(Unary_expressionContext.class,0);
		}
		public Assignment_operatorContext assignment_operator() {
			return getRuleContext(Assignment_operatorContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode OP_COALESCING_ASSIGNMENT() { return getToken(CSharpParser.OP_COALESCING_ASSIGNMENT, 0); }
		public Throwable_expressionContext throwable_expression() {
			return getRuleContext(Throwable_expressionContext.class,0);
		}
		public AssignmentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignment; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterAssignment(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitAssignment(this);
		}
	}

	public final AssignmentContext assignment() throws RecognitionException {
		AssignmentContext _localctx = new AssignmentContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_assignment);
		try {
			setState(574);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,24,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(566);
				unary_expression();
				setState(567);
				assignment_operator();
				setState(568);
				expression();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(570);
				unary_expression();
				setState(571);
				match(OP_COALESCING_ASSIGNMENT);
				setState(572);
				throwable_expression();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Assignment_operatorContext extends ParserRuleContext {
		public TerminalNode ASSIGNMENT() { return getToken(CSharpParser.ASSIGNMENT, 0); }
		public TerminalNode OP_ADD_ASSIGNMENT() { return getToken(CSharpParser.OP_ADD_ASSIGNMENT, 0); }
		public TerminalNode OP_SUB_ASSIGNMENT() { return getToken(CSharpParser.OP_SUB_ASSIGNMENT, 0); }
		public TerminalNode OP_MULT_ASSIGNMENT() { return getToken(CSharpParser.OP_MULT_ASSIGNMENT, 0); }
		public TerminalNode OP_DIV_ASSIGNMENT() { return getToken(CSharpParser.OP_DIV_ASSIGNMENT, 0); }
		public TerminalNode OP_MOD_ASSIGNMENT() { return getToken(CSharpParser.OP_MOD_ASSIGNMENT, 0); }
		public TerminalNode OP_AND_ASSIGNMENT() { return getToken(CSharpParser.OP_AND_ASSIGNMENT, 0); }
		public TerminalNode OP_OR_ASSIGNMENT() { return getToken(CSharpParser.OP_OR_ASSIGNMENT, 0); }
		public TerminalNode OP_XOR_ASSIGNMENT() { return getToken(CSharpParser.OP_XOR_ASSIGNMENT, 0); }
		public TerminalNode OP_LEFT_SHIFT_ASSIGNMENT() { return getToken(CSharpParser.OP_LEFT_SHIFT_ASSIGNMENT, 0); }
		public Right_shift_assignmentContext right_shift_assignment() {
			return getRuleContext(Right_shift_assignmentContext.class,0);
		}
		public Assignment_operatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignment_operator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterAssignment_operator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitAssignment_operator(this);
		}
	}

	public final Assignment_operatorContext assignment_operator() throws RecognitionException {
		Assignment_operatorContext _localctx = new Assignment_operatorContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_assignment_operator);
		try {
			setState(587);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ASSIGNMENT:
				enterOuterAlt(_localctx, 1);
				{
				setState(576);
				match(ASSIGNMENT);
				}
				break;
			case OP_ADD_ASSIGNMENT:
				enterOuterAlt(_localctx, 2);
				{
				setState(577);
				match(OP_ADD_ASSIGNMENT);
				}
				break;
			case OP_SUB_ASSIGNMENT:
				enterOuterAlt(_localctx, 3);
				{
				setState(578);
				match(OP_SUB_ASSIGNMENT);
				}
				break;
			case OP_MULT_ASSIGNMENT:
				enterOuterAlt(_localctx, 4);
				{
				setState(579);
				match(OP_MULT_ASSIGNMENT);
				}
				break;
			case OP_DIV_ASSIGNMENT:
				enterOuterAlt(_localctx, 5);
				{
				setState(580);
				match(OP_DIV_ASSIGNMENT);
				}
				break;
			case OP_MOD_ASSIGNMENT:
				enterOuterAlt(_localctx, 6);
				{
				setState(581);
				match(OP_MOD_ASSIGNMENT);
				}
				break;
			case OP_AND_ASSIGNMENT:
				enterOuterAlt(_localctx, 7);
				{
				setState(582);
				match(OP_AND_ASSIGNMENT);
				}
				break;
			case OP_OR_ASSIGNMENT:
				enterOuterAlt(_localctx, 8);
				{
				setState(583);
				match(OP_OR_ASSIGNMENT);
				}
				break;
			case OP_XOR_ASSIGNMENT:
				enterOuterAlt(_localctx, 9);
				{
				setState(584);
				match(OP_XOR_ASSIGNMENT);
				}
				break;
			case OP_LEFT_SHIFT_ASSIGNMENT:
				enterOuterAlt(_localctx, 10);
				{
				setState(585);
				match(OP_LEFT_SHIFT_ASSIGNMENT);
				}
				break;
			case GT:
				enterOuterAlt(_localctx, 11);
				{
				setState(586);
				right_shift_assignment();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Conditional_expressionContext extends ParserRuleContext {
		public Null_coalescing_expressionContext null_coalescing_expression() {
			return getRuleContext(Null_coalescing_expressionContext.class,0);
		}
		public TerminalNode INTERR() { return getToken(CSharpParser.INTERR, 0); }
		public List<Throwable_expressionContext> throwable_expression() {
			return getRuleContexts(Throwable_expressionContext.class);
		}
		public Throwable_expressionContext throwable_expression(int i) {
			return getRuleContext(Throwable_expressionContext.class,i);
		}
		public TerminalNode COLON() { return getToken(CSharpParser.COLON, 0); }
		public Conditional_expressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_conditional_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterConditional_expression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitConditional_expression(this);
		}
	}

	public final Conditional_expressionContext conditional_expression() throws RecognitionException {
		Conditional_expressionContext _localctx = new Conditional_expressionContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_conditional_expression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(589);
			null_coalescing_expression();
			setState(595);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,26,_ctx) ) {
			case 1:
				{
				setState(590);
				match(INTERR);
				setState(591);
				throwable_expression();
				setState(592);
				match(COLON);
				setState(593);
				throwable_expression();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Null_coalescing_expressionContext extends ParserRuleContext {
		public Conditional_or_expressionContext conditional_or_expression() {
			return getRuleContext(Conditional_or_expressionContext.class,0);
		}
		public TerminalNode OP_COALESCING() { return getToken(CSharpParser.OP_COALESCING, 0); }
		public Null_coalescing_expressionContext null_coalescing_expression() {
			return getRuleContext(Null_coalescing_expressionContext.class,0);
		}
		public Throw_expressionContext throw_expression() {
			return getRuleContext(Throw_expressionContext.class,0);
		}
		public Null_coalescing_expressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_null_coalescing_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterNull_coalescing_expression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitNull_coalescing_expression(this);
		}
	}

	public final Null_coalescing_expressionContext null_coalescing_expression() throws RecognitionException {
		Null_coalescing_expressionContext _localctx = new Null_coalescing_expressionContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_null_coalescing_expression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(597);
			conditional_or_expression();
			setState(603);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==OP_COALESCING) {
				{
				setState(598);
				match(OP_COALESCING);
				setState(601);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case ADD:
				case ALIAS:
				case ARGLIST:
				case ASCENDING:
				case ASYNC:
				case AWAIT:
				case BASE:
				case BOOL:
				case BY:
				case BYTE:
				case CHAR:
				case CHECKED:
				case DECIMAL:
				case DEFAULT:
				case DELEGATE:
				case DESCENDING:
				case DOUBLE:
				case DYNAMIC:
				case EQUALS:
				case FALSE:
				case FLOAT:
				case FROM:
				case GET:
				case GROUP:
				case INT:
				case INTO:
				case JOIN:
				case LET:
				case LONG:
				case NAMEOF:
				case NEW:
				case NULL_:
				case OBJECT:
				case ON:
				case ORDERBY:
				case PARTIAL:
				case REMOVE:
				case SBYTE:
				case SELECT:
				case SET:
				case SHORT:
				case SIZEOF:
				case STRING:
				case THIS:
				case TRUE:
				case TYPEOF:
				case UINT:
				case ULONG:
				case UNCHECKED:
				case UNMANAGED:
				case USHORT:
				case VAR:
				case WHEN:
				case WHERE:
				case YIELD:
				case IDENTIFIER:
				case LITERAL_ACCESS:
				case INTEGER_LITERAL:
				case HEX_INTEGER_LITERAL:
				case BIN_INTEGER_LITERAL:
				case REAL_LITERAL:
				case CHARACTER_LITERAL:
				case REGULAR_STRING:
				case VERBATIUM_STRING:
				case INTERPOLATED_REGULAR_STRING_START:
				case INTERPOLATED_VERBATIUM_STRING_START:
				case OPEN_PARENS:
				case PLUS:
				case MINUS:
				case STAR:
				case AMP:
				case CARET:
				case BANG:
				case TILDE:
				case OP_INC:
				case OP_DEC:
				case OP_RANGE:
					{
					setState(599);
					null_coalescing_expression();
					}
					break;
				case THROW:
					{
					setState(600);
					throw_expression();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Conditional_or_expressionContext extends ParserRuleContext {
		public List<Conditional_and_expressionContext> conditional_and_expression() {
			return getRuleContexts(Conditional_and_expressionContext.class);
		}
		public Conditional_and_expressionContext conditional_and_expression(int i) {
			return getRuleContext(Conditional_and_expressionContext.class,i);
		}
		public List<TerminalNode> OP_OR() { return getTokens(CSharpParser.OP_OR); }
		public TerminalNode OP_OR(int i) {
			return getToken(CSharpParser.OP_OR, i);
		}
		public Conditional_or_expressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_conditional_or_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterConditional_or_expression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitConditional_or_expression(this);
		}
	}

	public final Conditional_or_expressionContext conditional_or_expression() throws RecognitionException {
		Conditional_or_expressionContext _localctx = new Conditional_or_expressionContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_conditional_or_expression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(605);
			conditional_and_expression();
			setState(610);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OP_OR) {
				{
				{
				setState(606);
				match(OP_OR);
				setState(607);
				conditional_and_expression();
				}
				}
				setState(612);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Conditional_and_expressionContext extends ParserRuleContext {
		public List<Inclusive_or_expressionContext> inclusive_or_expression() {
			return getRuleContexts(Inclusive_or_expressionContext.class);
		}
		public Inclusive_or_expressionContext inclusive_or_expression(int i) {
			return getRuleContext(Inclusive_or_expressionContext.class,i);
		}
		public List<TerminalNode> OP_AND() { return getTokens(CSharpParser.OP_AND); }
		public TerminalNode OP_AND(int i) {
			return getToken(CSharpParser.OP_AND, i);
		}
		public Conditional_and_expressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_conditional_and_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterConditional_and_expression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitConditional_and_expression(this);
		}
	}

	public final Conditional_and_expressionContext conditional_and_expression() throws RecognitionException {
		Conditional_and_expressionContext _localctx = new Conditional_and_expressionContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_conditional_and_expression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(613);
			inclusive_or_expression();
			setState(618);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OP_AND) {
				{
				{
				setState(614);
				match(OP_AND);
				setState(615);
				inclusive_or_expression();
				}
				}
				setState(620);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Inclusive_or_expressionContext extends ParserRuleContext {
		public List<Exclusive_or_expressionContext> exclusive_or_expression() {
			return getRuleContexts(Exclusive_or_expressionContext.class);
		}
		public Exclusive_or_expressionContext exclusive_or_expression(int i) {
			return getRuleContext(Exclusive_or_expressionContext.class,i);
		}
		public List<TerminalNode> BITWISE_OR() { return getTokens(CSharpParser.BITWISE_OR); }
		public TerminalNode BITWISE_OR(int i) {
			return getToken(CSharpParser.BITWISE_OR, i);
		}
		public Inclusive_or_expressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_inclusive_or_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterInclusive_or_expression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitInclusive_or_expression(this);
		}
	}

	public final Inclusive_or_expressionContext inclusive_or_expression() throws RecognitionException {
		Inclusive_or_expressionContext _localctx = new Inclusive_or_expressionContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_inclusive_or_expression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(621);
			exclusive_or_expression();
			setState(626);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==BITWISE_OR) {
				{
				{
				setState(622);
				match(BITWISE_OR);
				setState(623);
				exclusive_or_expression();
				}
				}
				setState(628);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Exclusive_or_expressionContext extends ParserRuleContext {
		public List<And_expressionContext> and_expression() {
			return getRuleContexts(And_expressionContext.class);
		}
		public And_expressionContext and_expression(int i) {
			return getRuleContext(And_expressionContext.class,i);
		}
		public List<TerminalNode> CARET() { return getTokens(CSharpParser.CARET); }
		public TerminalNode CARET(int i) {
			return getToken(CSharpParser.CARET, i);
		}
		public Exclusive_or_expressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exclusive_or_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterExclusive_or_expression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitExclusive_or_expression(this);
		}
	}

	public final Exclusive_or_expressionContext exclusive_or_expression() throws RecognitionException {
		Exclusive_or_expressionContext _localctx = new Exclusive_or_expressionContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_exclusive_or_expression);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(629);
			and_expression();
			setState(634);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,32,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(630);
					match(CARET);
					setState(631);
					and_expression();
					}
					} 
				}
				setState(636);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,32,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class And_expressionContext extends ParserRuleContext {
		public List<Equality_expressionContext> equality_expression() {
			return getRuleContexts(Equality_expressionContext.class);
		}
		public Equality_expressionContext equality_expression(int i) {
			return getRuleContext(Equality_expressionContext.class,i);
		}
		public List<TerminalNode> AMP() { return getTokens(CSharpParser.AMP); }
		public TerminalNode AMP(int i) {
			return getToken(CSharpParser.AMP, i);
		}
		public And_expressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_and_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterAnd_expression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitAnd_expression(this);
		}
	}

	public final And_expressionContext and_expression() throws RecognitionException {
		And_expressionContext _localctx = new And_expressionContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_and_expression);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(637);
			equality_expression();
			setState(642);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,33,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(638);
					match(AMP);
					setState(639);
					equality_expression();
					}
					} 
				}
				setState(644);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,33,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Equality_expressionContext extends ParserRuleContext {
		public List<Relational_expressionContext> relational_expression() {
			return getRuleContexts(Relational_expressionContext.class);
		}
		public Relational_expressionContext relational_expression(int i) {
			return getRuleContext(Relational_expressionContext.class,i);
		}
		public List<TerminalNode> OP_EQ() { return getTokens(CSharpParser.OP_EQ); }
		public TerminalNode OP_EQ(int i) {
			return getToken(CSharpParser.OP_EQ, i);
		}
		public List<TerminalNode> OP_NE() { return getTokens(CSharpParser.OP_NE); }
		public TerminalNode OP_NE(int i) {
			return getToken(CSharpParser.OP_NE, i);
		}
		public Equality_expressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_equality_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterEquality_expression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitEquality_expression(this);
		}
	}

	public final Equality_expressionContext equality_expression() throws RecognitionException {
		Equality_expressionContext _localctx = new Equality_expressionContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_equality_expression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(645);
			relational_expression();
			setState(650);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OP_EQ || _la==OP_NE) {
				{
				{
				setState(646);
				_la = _input.LA(1);
				if ( !(_la==OP_EQ || _la==OP_NE) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(647);
				relational_expression();
				}
				}
				setState(652);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Relational_expressionContext extends ParserRuleContext {
		public List<Shift_expressionContext> shift_expression() {
			return getRuleContexts(Shift_expressionContext.class);
		}
		public Shift_expressionContext shift_expression(int i) {
			return getRuleContext(Shift_expressionContext.class,i);
		}
		public List<TerminalNode> IS() { return getTokens(CSharpParser.IS); }
		public TerminalNode IS(int i) {
			return getToken(CSharpParser.IS, i);
		}
		public List<IsTypeContext> isType() {
			return getRuleContexts(IsTypeContext.class);
		}
		public IsTypeContext isType(int i) {
			return getRuleContext(IsTypeContext.class,i);
		}
		public List<TerminalNode> AS() { return getTokens(CSharpParser.AS); }
		public TerminalNode AS(int i) {
			return getToken(CSharpParser.AS, i);
		}
		public List<Type_Context> type_() {
			return getRuleContexts(Type_Context.class);
		}
		public Type_Context type_(int i) {
			return getRuleContext(Type_Context.class,i);
		}
		public List<TerminalNode> LT() { return getTokens(CSharpParser.LT); }
		public TerminalNode LT(int i) {
			return getToken(CSharpParser.LT, i);
		}
		public List<TerminalNode> GT() { return getTokens(CSharpParser.GT); }
		public TerminalNode GT(int i) {
			return getToken(CSharpParser.GT, i);
		}
		public List<TerminalNode> OP_LE() { return getTokens(CSharpParser.OP_LE); }
		public TerminalNode OP_LE(int i) {
			return getToken(CSharpParser.OP_LE, i);
		}
		public List<TerminalNode> OP_GE() { return getTokens(CSharpParser.OP_GE); }
		public TerminalNode OP_GE(int i) {
			return getToken(CSharpParser.OP_GE, i);
		}
		public Relational_expressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_relational_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterRelational_expression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitRelational_expression(this);
		}
	}

	public final Relational_expressionContext relational_expression() throws RecognitionException {
		Relational_expressionContext _localctx = new Relational_expressionContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_relational_expression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(653);
			shift_expression();
			setState(662);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==AS || _la==IS || ((((_la - 150)) & ~0x3f) == 0 && ((1L << (_la - 150)) & ((1L << (LT - 150)) | (1L << (GT - 150)) | (1L << (OP_LE - 150)) | (1L << (OP_GE - 150)))) != 0)) {
				{
				setState(660);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case LT:
				case GT:
				case OP_LE:
				case OP_GE:
					{
					setState(654);
					_la = _input.LA(1);
					if ( !(((((_la - 150)) & ~0x3f) == 0 && ((1L << (_la - 150)) & ((1L << (LT - 150)) | (1L << (GT - 150)) | (1L << (OP_LE - 150)) | (1L << (OP_GE - 150)))) != 0)) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					setState(655);
					shift_expression();
					}
					break;
				case IS:
					{
					setState(656);
					match(IS);
					setState(657);
					isType();
					}
					break;
				case AS:
					{
					setState(658);
					match(AS);
					setState(659);
					type_();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(664);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Shift_expressionContext extends ParserRuleContext {
		public List<Additive_expressionContext> additive_expression() {
			return getRuleContexts(Additive_expressionContext.class);
		}
		public Additive_expressionContext additive_expression(int i) {
			return getRuleContext(Additive_expressionContext.class,i);
		}
		public List<TerminalNode> OP_LEFT_SHIFT() { return getTokens(CSharpParser.OP_LEFT_SHIFT); }
		public TerminalNode OP_LEFT_SHIFT(int i) {
			return getToken(CSharpParser.OP_LEFT_SHIFT, i);
		}
		public List<Right_shiftContext> right_shift() {
			return getRuleContexts(Right_shiftContext.class);
		}
		public Right_shiftContext right_shift(int i) {
			return getRuleContext(Right_shiftContext.class,i);
		}
		public Shift_expressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_shift_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterShift_expression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitShift_expression(this);
		}
	}

	public final Shift_expressionContext shift_expression() throws RecognitionException {
		Shift_expressionContext _localctx = new Shift_expressionContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_shift_expression);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(665);
			additive_expression();
			setState(673);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,38,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(668);
					_errHandler.sync(this);
					switch (_input.LA(1)) {
					case OP_LEFT_SHIFT:
						{
						setState(666);
						match(OP_LEFT_SHIFT);
						}
						break;
					case GT:
						{
						setState(667);
						right_shift();
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(670);
					additive_expression();
					}
					} 
				}
				setState(675);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,38,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Additive_expressionContext extends ParserRuleContext {
		public List<Multiplicative_expressionContext> multiplicative_expression() {
			return getRuleContexts(Multiplicative_expressionContext.class);
		}
		public Multiplicative_expressionContext multiplicative_expression(int i) {
			return getRuleContext(Multiplicative_expressionContext.class,i);
		}
		public List<TerminalNode> PLUS() { return getTokens(CSharpParser.PLUS); }
		public TerminalNode PLUS(int i) {
			return getToken(CSharpParser.PLUS, i);
		}
		public List<TerminalNode> MINUS() { return getTokens(CSharpParser.MINUS); }
		public TerminalNode MINUS(int i) {
			return getToken(CSharpParser.MINUS, i);
		}
		public Additive_expressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_additive_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterAdditive_expression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitAdditive_expression(this);
		}
	}

	public final Additive_expressionContext additive_expression() throws RecognitionException {
		Additive_expressionContext _localctx = new Additive_expressionContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_additive_expression);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(676);
			multiplicative_expression();
			setState(681);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,39,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(677);
					_la = _input.LA(1);
					if ( !(_la==PLUS || _la==MINUS) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					setState(678);
					multiplicative_expression();
					}
					} 
				}
				setState(683);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,39,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Multiplicative_expressionContext extends ParserRuleContext {
		public List<Switch_expressionContext> switch_expression() {
			return getRuleContexts(Switch_expressionContext.class);
		}
		public Switch_expressionContext switch_expression(int i) {
			return getRuleContext(Switch_expressionContext.class,i);
		}
		public List<TerminalNode> STAR() { return getTokens(CSharpParser.STAR); }
		public TerminalNode STAR(int i) {
			return getToken(CSharpParser.STAR, i);
		}
		public List<TerminalNode> DIV() { return getTokens(CSharpParser.DIV); }
		public TerminalNode DIV(int i) {
			return getToken(CSharpParser.DIV, i);
		}
		public List<TerminalNode> PERCENT() { return getTokens(CSharpParser.PERCENT); }
		public TerminalNode PERCENT(int i) {
			return getToken(CSharpParser.PERCENT, i);
		}
		public Multiplicative_expressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_multiplicative_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterMultiplicative_expression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitMultiplicative_expression(this);
		}
	}

	public final Multiplicative_expressionContext multiplicative_expression() throws RecognitionException {
		Multiplicative_expressionContext _localctx = new Multiplicative_expressionContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_multiplicative_expression);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(684);
			switch_expression();
			setState(689);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,40,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(685);
					_la = _input.LA(1);
					if ( !(((((_la - 141)) & ~0x3f) == 0 && ((1L << (_la - 141)) & ((1L << (STAR - 141)) | (1L << (DIV - 141)) | (1L << (PERCENT - 141)))) != 0)) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					setState(686);
					switch_expression();
					}
					} 
				}
				setState(691);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,40,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Switch_expressionContext extends ParserRuleContext {
		public Range_expressionContext range_expression() {
			return getRuleContext(Range_expressionContext.class,0);
		}
		public TerminalNode SWITCH() { return getToken(CSharpParser.SWITCH, 0); }
		public TerminalNode OPEN_BRACE() { return getToken(CSharpParser.OPEN_BRACE, 0); }
		public TerminalNode CLOSE_BRACE() { return getToken(CSharpParser.CLOSE_BRACE, 0); }
		public Switch_expression_armsContext switch_expression_arms() {
			return getRuleContext(Switch_expression_armsContext.class,0);
		}
		public TerminalNode COMMA() { return getToken(CSharpParser.COMMA, 0); }
		public Switch_expressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_switch_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterSwitch_expression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitSwitch_expression(this);
		}
	}

	public final Switch_expressionContext switch_expression() throws RecognitionException {
		Switch_expressionContext _localctx = new Switch_expressionContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_switch_expression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(692);
			range_expression();
			setState(702);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SWITCH) {
				{
				setState(693);
				match(SWITCH);
				setState(694);
				match(OPEN_BRACE);
				setState(699);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ADD) | (1L << ALIAS) | (1L << ARGLIST) | (1L << ASCENDING) | (1L << ASYNC) | (1L << AWAIT) | (1L << BASE) | (1L << BOOL) | (1L << BY) | (1L << BYTE) | (1L << CHAR) | (1L << CHECKED) | (1L << DECIMAL) | (1L << DEFAULT) | (1L << DELEGATE) | (1L << DESCENDING) | (1L << DOUBLE) | (1L << DYNAMIC) | (1L << EQUALS) | (1L << FALSE) | (1L << FLOAT) | (1L << FROM) | (1L << GET) | (1L << GROUP) | (1L << INT) | (1L << INTO) | (1L << JOIN) | (1L << LET))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (LONG - 64)) | (1L << (NAMEOF - 64)) | (1L << (NEW - 64)) | (1L << (NULL_ - 64)) | (1L << (OBJECT - 64)) | (1L << (ON - 64)) | (1L << (ORDERBY - 64)) | (1L << (PARTIAL - 64)) | (1L << (REF - 64)) | (1L << (REMOVE - 64)) | (1L << (SBYTE - 64)) | (1L << (SELECT - 64)) | (1L << (SET - 64)) | (1L << (SHORT - 64)) | (1L << (SIZEOF - 64)) | (1L << (STRING - 64)) | (1L << (THIS - 64)) | (1L << (TRUE - 64)) | (1L << (TYPEOF - 64)) | (1L << (UINT - 64)) | (1L << (ULONG - 64)) | (1L << (UNCHECKED - 64)) | (1L << (UNMANAGED - 64)) | (1L << (USHORT - 64)) | (1L << (VAR - 64)) | (1L << (WHEN - 64)) | (1L << (WHERE - 64)) | (1L << (YIELD - 64)) | (1L << (IDENTIFIER - 64)) | (1L << (LITERAL_ACCESS - 64)) | (1L << (INTEGER_LITERAL - 64)) | (1L << (HEX_INTEGER_LITERAL - 64)) | (1L << (BIN_INTEGER_LITERAL - 64)) | (1L << (REAL_LITERAL - 64)) | (1L << (CHARACTER_LITERAL - 64)) | (1L << (REGULAR_STRING - 64)) | (1L << (VERBATIUM_STRING - 64)) | (1L << (INTERPOLATED_REGULAR_STRING_START - 64)))) != 0) || ((((_la - 128)) & ~0x3f) == 0 && ((1L << (_la - 128)) & ((1L << (INTERPOLATED_VERBATIUM_STRING_START - 128)) | (1L << (OPEN_PARENS - 128)) | (1L << (PLUS - 128)) | (1L << (MINUS - 128)) | (1L << (STAR - 128)) | (1L << (AMP - 128)) | (1L << (CARET - 128)) | (1L << (BANG - 128)) | (1L << (TILDE - 128)) | (1L << (OP_INC - 128)) | (1L << (OP_DEC - 128)) | (1L << (OP_RANGE - 128)))) != 0)) {
					{
					setState(695);
					switch_expression_arms();
					setState(697);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==COMMA) {
						{
						setState(696);
						match(COMMA);
						}
					}

					}
				}

				setState(701);
				match(CLOSE_BRACE);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Switch_expression_armsContext extends ParserRuleContext {
		public List<Switch_expression_armContext> switch_expression_arm() {
			return getRuleContexts(Switch_expression_armContext.class);
		}
		public Switch_expression_armContext switch_expression_arm(int i) {
			return getRuleContext(Switch_expression_armContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(CSharpParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(CSharpParser.COMMA, i);
		}
		public Switch_expression_armsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_switch_expression_arms; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterSwitch_expression_arms(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitSwitch_expression_arms(this);
		}
	}

	public final Switch_expression_armsContext switch_expression_arms() throws RecognitionException {
		Switch_expression_armsContext _localctx = new Switch_expression_armsContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_switch_expression_arms);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(704);
			switch_expression_arm();
			setState(709);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,44,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(705);
					match(COMMA);
					setState(706);
					switch_expression_arm();
					}
					} 
				}
				setState(711);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,44,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Switch_expression_armContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public Right_arrowContext right_arrow() {
			return getRuleContext(Right_arrowContext.class,0);
		}
		public Throwable_expressionContext throwable_expression() {
			return getRuleContext(Throwable_expressionContext.class,0);
		}
		public Case_guardContext case_guard() {
			return getRuleContext(Case_guardContext.class,0);
		}
		public Switch_expression_armContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_switch_expression_arm; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterSwitch_expression_arm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitSwitch_expression_arm(this);
		}
	}

	public final Switch_expression_armContext switch_expression_arm() throws RecognitionException {
		Switch_expression_armContext _localctx = new Switch_expression_armContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_switch_expression_arm);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(712);
			expression();
			setState(714);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==WHEN) {
				{
				setState(713);
				case_guard();
				}
			}

			setState(716);
			right_arrow();
			setState(717);
			throwable_expression();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Range_expressionContext extends ParserRuleContext {
		public List<Unary_expressionContext> unary_expression() {
			return getRuleContexts(Unary_expressionContext.class);
		}
		public Unary_expressionContext unary_expression(int i) {
			return getRuleContext(Unary_expressionContext.class,i);
		}
		public TerminalNode OP_RANGE() { return getToken(CSharpParser.OP_RANGE, 0); }
		public Range_expressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_range_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterRange_expression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitRange_expression(this);
		}
	}

	public final Range_expressionContext range_expression() throws RecognitionException {
		Range_expressionContext _localctx = new Range_expressionContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_range_expression);
		int _la;
		try {
			setState(727);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,48,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(719);
				unary_expression();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(721);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ADD) | (1L << ALIAS) | (1L << ARGLIST) | (1L << ASCENDING) | (1L << ASYNC) | (1L << AWAIT) | (1L << BASE) | (1L << BOOL) | (1L << BY) | (1L << BYTE) | (1L << CHAR) | (1L << CHECKED) | (1L << DECIMAL) | (1L << DEFAULT) | (1L << DELEGATE) | (1L << DESCENDING) | (1L << DOUBLE) | (1L << DYNAMIC) | (1L << EQUALS) | (1L << FALSE) | (1L << FLOAT) | (1L << FROM) | (1L << GET) | (1L << GROUP) | (1L << INT) | (1L << INTO) | (1L << JOIN) | (1L << LET))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (LONG - 64)) | (1L << (NAMEOF - 64)) | (1L << (NEW - 64)) | (1L << (NULL_ - 64)) | (1L << (OBJECT - 64)) | (1L << (ON - 64)) | (1L << (ORDERBY - 64)) | (1L << (PARTIAL - 64)) | (1L << (REMOVE - 64)) | (1L << (SBYTE - 64)) | (1L << (SELECT - 64)) | (1L << (SET - 64)) | (1L << (SHORT - 64)) | (1L << (SIZEOF - 64)) | (1L << (STRING - 64)) | (1L << (THIS - 64)) | (1L << (TRUE - 64)) | (1L << (TYPEOF - 64)) | (1L << (UINT - 64)) | (1L << (ULONG - 64)) | (1L << (UNCHECKED - 64)) | (1L << (UNMANAGED - 64)) | (1L << (USHORT - 64)) | (1L << (VAR - 64)) | (1L << (WHEN - 64)) | (1L << (WHERE - 64)) | (1L << (YIELD - 64)) | (1L << (IDENTIFIER - 64)) | (1L << (LITERAL_ACCESS - 64)) | (1L << (INTEGER_LITERAL - 64)) | (1L << (HEX_INTEGER_LITERAL - 64)) | (1L << (BIN_INTEGER_LITERAL - 64)) | (1L << (REAL_LITERAL - 64)) | (1L << (CHARACTER_LITERAL - 64)) | (1L << (REGULAR_STRING - 64)) | (1L << (VERBATIUM_STRING - 64)) | (1L << (INTERPOLATED_REGULAR_STRING_START - 64)))) != 0) || ((((_la - 128)) & ~0x3f) == 0 && ((1L << (_la - 128)) & ((1L << (INTERPOLATED_VERBATIUM_STRING_START - 128)) | (1L << (OPEN_PARENS - 128)) | (1L << (PLUS - 128)) | (1L << (MINUS - 128)) | (1L << (STAR - 128)) | (1L << (AMP - 128)) | (1L << (CARET - 128)) | (1L << (BANG - 128)) | (1L << (TILDE - 128)) | (1L << (OP_INC - 128)) | (1L << (OP_DEC - 128)))) != 0)) {
					{
					setState(720);
					unary_expression();
					}
				}

				setState(723);
				match(OP_RANGE);
				setState(725);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,47,_ctx) ) {
				case 1:
					{
					setState(724);
					unary_expression();
					}
					break;
				}
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Unary_expressionContext extends ParserRuleContext {
		public Primary_expressionContext primary_expression() {
			return getRuleContext(Primary_expressionContext.class,0);
		}
		public TerminalNode PLUS() { return getToken(CSharpParser.PLUS, 0); }
		public Unary_expressionContext unary_expression() {
			return getRuleContext(Unary_expressionContext.class,0);
		}
		public TerminalNode MINUS() { return getToken(CSharpParser.MINUS, 0); }
		public TerminalNode BANG() { return getToken(CSharpParser.BANG, 0); }
		public TerminalNode TILDE() { return getToken(CSharpParser.TILDE, 0); }
		public TerminalNode OP_INC() { return getToken(CSharpParser.OP_INC, 0); }
		public TerminalNode OP_DEC() { return getToken(CSharpParser.OP_DEC, 0); }
		public TerminalNode OPEN_PARENS() { return getToken(CSharpParser.OPEN_PARENS, 0); }
		public Type_Context type_() {
			return getRuleContext(Type_Context.class,0);
		}
		public TerminalNode CLOSE_PARENS() { return getToken(CSharpParser.CLOSE_PARENS, 0); }
		public TerminalNode AWAIT() { return getToken(CSharpParser.AWAIT, 0); }
		public TerminalNode AMP() { return getToken(CSharpParser.AMP, 0); }
		public TerminalNode STAR() { return getToken(CSharpParser.STAR, 0); }
		public TerminalNode CARET() { return getToken(CSharpParser.CARET, 0); }
		public Unary_expressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unary_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterUnary_expression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitUnary_expression(this);
		}
	}

	public final Unary_expressionContext unary_expression() throws RecognitionException {
		Unary_expressionContext _localctx = new Unary_expressionContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_unary_expression);
		try {
			setState(755);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,49,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(729);
				primary_expression();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(730);
				match(PLUS);
				setState(731);
				unary_expression();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(732);
				match(MINUS);
				setState(733);
				unary_expression();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(734);
				match(BANG);
				setState(735);
				unary_expression();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(736);
				match(TILDE);
				setState(737);
				unary_expression();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(738);
				match(OP_INC);
				setState(739);
				unary_expression();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(740);
				match(OP_DEC);
				setState(741);
				unary_expression();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(742);
				match(OPEN_PARENS);
				setState(743);
				type_();
				setState(744);
				match(CLOSE_PARENS);
				setState(745);
				unary_expression();
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(747);
				match(AWAIT);
				setState(748);
				unary_expression();
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(749);
				match(AMP);
				setState(750);
				unary_expression();
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(751);
				match(STAR);
				setState(752);
				unary_expression();
				}
				break;
			case 12:
				enterOuterAlt(_localctx, 12);
				{
				setState(753);
				match(CARET);
				setState(754);
				unary_expression();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Primary_expressionContext extends ParserRuleContext {
		public Primary_expression_startContext pe;
		public Primary_expression_startContext primary_expression_start() {
			return getRuleContext(Primary_expression_startContext.class,0);
		}
		public List<TerminalNode> BANG() { return getTokens(CSharpParser.BANG); }
		public TerminalNode BANG(int i) {
			return getToken(CSharpParser.BANG, i);
		}
		public List<Bracket_expressionContext> bracket_expression() {
			return getRuleContexts(Bracket_expressionContext.class);
		}
		public Bracket_expressionContext bracket_expression(int i) {
			return getRuleContext(Bracket_expressionContext.class,i);
		}
		public List<Member_accessContext> member_access() {
			return getRuleContexts(Member_accessContext.class);
		}
		public Member_accessContext member_access(int i) {
			return getRuleContext(Member_accessContext.class,i);
		}
		public List<Method_invocationContext> method_invocation() {
			return getRuleContexts(Method_invocationContext.class);
		}
		public Method_invocationContext method_invocation(int i) {
			return getRuleContext(Method_invocationContext.class,i);
		}
		public List<TerminalNode> OP_INC() { return getTokens(CSharpParser.OP_INC); }
		public TerminalNode OP_INC(int i) {
			return getToken(CSharpParser.OP_INC, i);
		}
		public List<TerminalNode> OP_DEC() { return getTokens(CSharpParser.OP_DEC); }
		public TerminalNode OP_DEC(int i) {
			return getToken(CSharpParser.OP_DEC, i);
		}
		public List<TerminalNode> OP_PTR() { return getTokens(CSharpParser.OP_PTR); }
		public TerminalNode OP_PTR(int i) {
			return getToken(CSharpParser.OP_PTR, i);
		}
		public List<IdentifierContext> identifier() {
			return getRuleContexts(IdentifierContext.class);
		}
		public IdentifierContext identifier(int i) {
			return getRuleContext(IdentifierContext.class,i);
		}
		public Primary_expressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_primary_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterPrimary_expression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitPrimary_expression(this);
		}
	}

	public final Primary_expressionContext primary_expression() throws RecognitionException {
		Primary_expressionContext _localctx = new Primary_expressionContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_primary_expression);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(757);
			((Primary_expressionContext)_localctx).pe = primary_expression_start();
			setState(759);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,50,_ctx) ) {
			case 1:
				{
				setState(758);
				match(BANG);
				}
				break;
			}
			setState(764);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,51,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(761);
					bracket_expression();
					}
					} 
				}
				setState(766);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,51,_ctx);
			}
			setState(768);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,52,_ctx) ) {
			case 1:
				{
				setState(767);
				match(BANG);
				}
				break;
			}
			setState(792);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,57,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					{
					setState(776);
					_errHandler.sync(this);
					switch (_input.LA(1)) {
					case DOT:
					case INTERR:
						{
						setState(770);
						member_access();
						}
						break;
					case OPEN_PARENS:
						{
						setState(771);
						method_invocation();
						}
						break;
					case OP_INC:
						{
						setState(772);
						match(OP_INC);
						}
						break;
					case OP_DEC:
						{
						setState(773);
						match(OP_DEC);
						}
						break;
					case OP_PTR:
						{
						setState(774);
						match(OP_PTR);
						setState(775);
						identifier();
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(779);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,54,_ctx) ) {
					case 1:
						{
						setState(778);
						match(BANG);
						}
						break;
					}
					}
					setState(784);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,55,_ctx);
					while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
						if ( _alt==1 ) {
							{
							{
							setState(781);
							bracket_expression();
							}
							} 
						}
						setState(786);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,55,_ctx);
					}
					setState(788);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,56,_ctx) ) {
					case 1:
						{
						setState(787);
						match(BANG);
						}
						break;
					}
					}
					} 
				}
				setState(794);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,57,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Primary_expression_startContext extends ParserRuleContext {
		public Primary_expression_startContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_primary_expression_start; }
	 
		public Primary_expression_startContext() { }
		public void copyFrom(Primary_expression_startContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class LiteralAccessExpressionContext extends Primary_expression_startContext {
		public TerminalNode LITERAL_ACCESS() { return getToken(CSharpParser.LITERAL_ACCESS, 0); }
		public LiteralAccessExpressionContext(Primary_expression_startContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterLiteralAccessExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitLiteralAccessExpression(this);
		}
	}
	public static class DefaultValueExpressionContext extends Primary_expression_startContext {
		public TerminalNode DEFAULT() { return getToken(CSharpParser.DEFAULT, 0); }
		public TerminalNode OPEN_PARENS() { return getToken(CSharpParser.OPEN_PARENS, 0); }
		public Type_Context type_() {
			return getRuleContext(Type_Context.class,0);
		}
		public TerminalNode CLOSE_PARENS() { return getToken(CSharpParser.CLOSE_PARENS, 0); }
		public DefaultValueExpressionContext(Primary_expression_startContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterDefaultValueExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitDefaultValueExpression(this);
		}
	}
	public static class BaseAccessExpressionContext extends Primary_expression_startContext {
		public TerminalNode BASE() { return getToken(CSharpParser.BASE, 0); }
		public TerminalNode DOT() { return getToken(CSharpParser.DOT, 0); }
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TerminalNode OPEN_BRACKET() { return getToken(CSharpParser.OPEN_BRACKET, 0); }
		public Expression_listContext expression_list() {
			return getRuleContext(Expression_listContext.class,0);
		}
		public TerminalNode CLOSE_BRACKET() { return getToken(CSharpParser.CLOSE_BRACKET, 0); }
		public Type_argument_listContext type_argument_list() {
			return getRuleContext(Type_argument_listContext.class,0);
		}
		public BaseAccessExpressionContext(Primary_expression_startContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterBaseAccessExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitBaseAccessExpression(this);
		}
	}
	public static class SizeofExpressionContext extends Primary_expression_startContext {
		public TerminalNode SIZEOF() { return getToken(CSharpParser.SIZEOF, 0); }
		public TerminalNode OPEN_PARENS() { return getToken(CSharpParser.OPEN_PARENS, 0); }
		public Type_Context type_() {
			return getRuleContext(Type_Context.class,0);
		}
		public TerminalNode CLOSE_PARENS() { return getToken(CSharpParser.CLOSE_PARENS, 0); }
		public SizeofExpressionContext(Primary_expression_startContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterSizeofExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitSizeofExpression(this);
		}
	}
	public static class ParenthesisExpressionsContext extends Primary_expression_startContext {
		public TerminalNode OPEN_PARENS() { return getToken(CSharpParser.OPEN_PARENS, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode CLOSE_PARENS() { return getToken(CSharpParser.CLOSE_PARENS, 0); }
		public ParenthesisExpressionsContext(Primary_expression_startContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterParenthesisExpressions(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitParenthesisExpressions(this);
		}
	}
	public static class ThisReferenceExpressionContext extends Primary_expression_startContext {
		public TerminalNode THIS() { return getToken(CSharpParser.THIS, 0); }
		public ThisReferenceExpressionContext(Primary_expression_startContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterThisReferenceExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitThisReferenceExpression(this);
		}
	}
	public static class ObjectCreationExpressionContext extends Primary_expression_startContext {
		public TerminalNode NEW() { return getToken(CSharpParser.NEW, 0); }
		public Type_Context type_() {
			return getRuleContext(Type_Context.class,0);
		}
		public Anonymous_object_initializerContext anonymous_object_initializer() {
			return getRuleContext(Anonymous_object_initializerContext.class,0);
		}
		public List<Rank_specifierContext> rank_specifier() {
			return getRuleContexts(Rank_specifierContext.class);
		}
		public Rank_specifierContext rank_specifier(int i) {
			return getRuleContext(Rank_specifierContext.class,i);
		}
		public Array_initializerContext array_initializer() {
			return getRuleContext(Array_initializerContext.class,0);
		}
		public Object_creation_expressionContext object_creation_expression() {
			return getRuleContext(Object_creation_expressionContext.class,0);
		}
		public Object_or_collection_initializerContext object_or_collection_initializer() {
			return getRuleContext(Object_or_collection_initializerContext.class,0);
		}
		public TerminalNode OPEN_BRACKET() { return getToken(CSharpParser.OPEN_BRACKET, 0); }
		public Expression_listContext expression_list() {
			return getRuleContext(Expression_listContext.class,0);
		}
		public TerminalNode CLOSE_BRACKET() { return getToken(CSharpParser.CLOSE_BRACKET, 0); }
		public TerminalNode OPEN_PARENS() { return getToken(CSharpParser.OPEN_PARENS, 0); }
		public TerminalNode CLOSE_PARENS() { return getToken(CSharpParser.CLOSE_PARENS, 0); }
		public Argument_listContext argument_list() {
			return getRuleContext(Argument_listContext.class,0);
		}
		public ObjectCreationExpressionContext(Primary_expression_startContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterObjectCreationExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitObjectCreationExpression(this);
		}
	}
	public static class AnonymousMethodExpressionContext extends Primary_expression_startContext {
		public TerminalNode DELEGATE() { return getToken(CSharpParser.DELEGATE, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public TerminalNode ASYNC() { return getToken(CSharpParser.ASYNC, 0); }
		public TerminalNode OPEN_PARENS() { return getToken(CSharpParser.OPEN_PARENS, 0); }
		public TerminalNode CLOSE_PARENS() { return getToken(CSharpParser.CLOSE_PARENS, 0); }
		public Explicit_anonymous_function_parameter_listContext explicit_anonymous_function_parameter_list() {
			return getRuleContext(Explicit_anonymous_function_parameter_listContext.class,0);
		}
		public AnonymousMethodExpressionContext(Primary_expression_startContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterAnonymousMethodExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitAnonymousMethodExpression(this);
		}
	}
	public static class TypeofExpressionContext extends Primary_expression_startContext {
		public TerminalNode TYPEOF() { return getToken(CSharpParser.TYPEOF, 0); }
		public TerminalNode OPEN_PARENS() { return getToken(CSharpParser.OPEN_PARENS, 0); }
		public TerminalNode CLOSE_PARENS() { return getToken(CSharpParser.CLOSE_PARENS, 0); }
		public Unbound_type_nameContext unbound_type_name() {
			return getRuleContext(Unbound_type_nameContext.class,0);
		}
		public Type_Context type_() {
			return getRuleContext(Type_Context.class,0);
		}
		public TerminalNode VOID() { return getToken(CSharpParser.VOID, 0); }
		public TypeofExpressionContext(Primary_expression_startContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterTypeofExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitTypeofExpression(this);
		}
	}
	public static class TupleExpressionContext extends Primary_expression_startContext {
		public TerminalNode OPEN_PARENS() { return getToken(CSharpParser.OPEN_PARENS, 0); }
		public List<ArgumentContext> argument() {
			return getRuleContexts(ArgumentContext.class);
		}
		public ArgumentContext argument(int i) {
			return getRuleContext(ArgumentContext.class,i);
		}
		public TerminalNode CLOSE_PARENS() { return getToken(CSharpParser.CLOSE_PARENS, 0); }
		public List<TerminalNode> COMMA() { return getTokens(CSharpParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(CSharpParser.COMMA, i);
		}
		public TupleExpressionContext(Primary_expression_startContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterTupleExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitTupleExpression(this);
		}
	}
	public static class UncheckedExpressionContext extends Primary_expression_startContext {
		public TerminalNode UNCHECKED() { return getToken(CSharpParser.UNCHECKED, 0); }
		public TerminalNode OPEN_PARENS() { return getToken(CSharpParser.OPEN_PARENS, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode CLOSE_PARENS() { return getToken(CSharpParser.CLOSE_PARENS, 0); }
		public UncheckedExpressionContext(Primary_expression_startContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterUncheckedExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitUncheckedExpression(this);
		}
	}
	public static class SimpleNameExpressionContext extends Primary_expression_startContext {
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public Type_argument_listContext type_argument_list() {
			return getRuleContext(Type_argument_listContext.class,0);
		}
		public SimpleNameExpressionContext(Primary_expression_startContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterSimpleNameExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitSimpleNameExpression(this);
		}
	}
	public static class MemberAccessExpressionContext extends Primary_expression_startContext {
		public Predefined_typeContext predefined_type() {
			return getRuleContext(Predefined_typeContext.class,0);
		}
		public Qualified_alias_memberContext qualified_alias_member() {
			return getRuleContext(Qualified_alias_memberContext.class,0);
		}
		public MemberAccessExpressionContext(Primary_expression_startContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterMemberAccessExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitMemberAccessExpression(this);
		}
	}
	public static class CheckedExpressionContext extends Primary_expression_startContext {
		public TerminalNode CHECKED() { return getToken(CSharpParser.CHECKED, 0); }
		public TerminalNode OPEN_PARENS() { return getToken(CSharpParser.OPEN_PARENS, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode CLOSE_PARENS() { return getToken(CSharpParser.CLOSE_PARENS, 0); }
		public CheckedExpressionContext(Primary_expression_startContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterCheckedExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitCheckedExpression(this);
		}
	}
	public static class LiteralExpressionContext extends Primary_expression_startContext {
		public LiteralContext literal() {
			return getRuleContext(LiteralContext.class,0);
		}
		public LiteralExpressionContext(Primary_expression_startContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterLiteralExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitLiteralExpression(this);
		}
	}
	public static class NameofExpressionContext extends Primary_expression_startContext {
		public TerminalNode NAMEOF() { return getToken(CSharpParser.NAMEOF, 0); }
		public TerminalNode OPEN_PARENS() { return getToken(CSharpParser.OPEN_PARENS, 0); }
		public List<IdentifierContext> identifier() {
			return getRuleContexts(IdentifierContext.class);
		}
		public IdentifierContext identifier(int i) {
			return getRuleContext(IdentifierContext.class,i);
		}
		public TerminalNode CLOSE_PARENS() { return getToken(CSharpParser.CLOSE_PARENS, 0); }
		public List<TerminalNode> DOT() { return getTokens(CSharpParser.DOT); }
		public TerminalNode DOT(int i) {
			return getToken(CSharpParser.DOT, i);
		}
		public NameofExpressionContext(Primary_expression_startContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterNameofExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitNameofExpression(this);
		}
	}

	public final Primary_expression_startContext primary_expression_start() throws RecognitionException {
		Primary_expression_startContext _localctx = new Primary_expression_startContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_primary_expression_start);
		int _la;
		try {
			int _alt;
			setState(922);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,74,_ctx) ) {
			case 1:
				_localctx = new LiteralExpressionContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(795);
				literal();
				}
				break;
			case 2:
				_localctx = new SimpleNameExpressionContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(796);
				identifier();
				setState(798);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,58,_ctx) ) {
				case 1:
					{
					setState(797);
					type_argument_list();
					}
					break;
				}
				}
				break;
			case 3:
				_localctx = new ParenthesisExpressionsContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(800);
				match(OPEN_PARENS);
				setState(801);
				expression();
				setState(802);
				match(CLOSE_PARENS);
				}
				break;
			case 4:
				_localctx = new MemberAccessExpressionContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(804);
				predefined_type();
				}
				break;
			case 5:
				_localctx = new MemberAccessExpressionContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(805);
				qualified_alias_member();
				}
				break;
			case 6:
				_localctx = new LiteralAccessExpressionContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(806);
				match(LITERAL_ACCESS);
				}
				break;
			case 7:
				_localctx = new ThisReferenceExpressionContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(807);
				match(THIS);
				}
				break;
			case 8:
				_localctx = new BaseAccessExpressionContext(_localctx);
				enterOuterAlt(_localctx, 8);
				{
				setState(808);
				match(BASE);
				setState(818);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case DOT:
					{
					setState(809);
					match(DOT);
					setState(810);
					identifier();
					setState(812);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,59,_ctx) ) {
					case 1:
						{
						setState(811);
						type_argument_list();
						}
						break;
					}
					}
					break;
				case OPEN_BRACKET:
					{
					setState(814);
					match(OPEN_BRACKET);
					setState(815);
					expression_list();
					setState(816);
					match(CLOSE_BRACKET);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				break;
			case 9:
				_localctx = new ObjectCreationExpressionContext(_localctx);
				enterOuterAlt(_localctx, 9);
				{
				setState(820);
				match(NEW);
				setState(849);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case ADD:
				case ALIAS:
				case ARGLIST:
				case ASCENDING:
				case ASYNC:
				case AWAIT:
				case BOOL:
				case BY:
				case BYTE:
				case CHAR:
				case DECIMAL:
				case DESCENDING:
				case DOUBLE:
				case DYNAMIC:
				case EQUALS:
				case FLOAT:
				case FROM:
				case GET:
				case GROUP:
				case INT:
				case INTO:
				case JOIN:
				case LET:
				case LONG:
				case NAMEOF:
				case OBJECT:
				case ON:
				case ORDERBY:
				case PARTIAL:
				case REMOVE:
				case SBYTE:
				case SELECT:
				case SET:
				case SHORT:
				case STRING:
				case UINT:
				case ULONG:
				case UNMANAGED:
				case USHORT:
				case VAR:
				case VOID:
				case WHEN:
				case WHERE:
				case YIELD:
				case IDENTIFIER:
				case OPEN_PARENS:
					{
					setState(821);
					type_();
					setState(843);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,64,_ctx) ) {
					case 1:
						{
						setState(822);
						object_creation_expression();
						}
						break;
					case 2:
						{
						setState(823);
						object_or_collection_initializer();
						}
						break;
					case 3:
						{
						setState(824);
						match(OPEN_BRACKET);
						setState(825);
						expression_list();
						setState(826);
						match(CLOSE_BRACKET);
						setState(830);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,61,_ctx);
						while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
							if ( _alt==1 ) {
								{
								{
								setState(827);
								rank_specifier();
								}
								} 
							}
							setState(832);
							_errHandler.sync(this);
							_alt = getInterpreter().adaptivePredict(_input,61,_ctx);
						}
						setState(834);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if (_la==OPEN_BRACE) {
							{
							setState(833);
							array_initializer();
							}
						}

						}
						break;
					case 4:
						{
						setState(837); 
						_errHandler.sync(this);
						_la = _input.LA(1);
						do {
							{
							{
							setState(836);
							rank_specifier();
							}
							}
							setState(839); 
							_errHandler.sync(this);
							_la = _input.LA(1);
						} while ( _la==OPEN_BRACKET );
						setState(841);
						array_initializer();
						}
						break;
					}
					}
					break;
				case OPEN_BRACE:
					{
					setState(845);
					anonymous_object_initializer();
					}
					break;
				case OPEN_BRACKET:
					{
					setState(846);
					rank_specifier();
					setState(847);
					array_initializer();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				break;
			case 10:
				_localctx = new ObjectCreationExpressionContext(_localctx);
				enterOuterAlt(_localctx, 10);
				{
				setState(851);
				match(NEW);
				setState(852);
				match(OPEN_PARENS);
				setState(854);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ADD) | (1L << ALIAS) | (1L << ARGLIST) | (1L << ASCENDING) | (1L << ASYNC) | (1L << AWAIT) | (1L << BASE) | (1L << BOOL) | (1L << BY) | (1L << BYTE) | (1L << CHAR) | (1L << CHECKED) | (1L << DECIMAL) | (1L << DEFAULT) | (1L << DELEGATE) | (1L << DESCENDING) | (1L << DOUBLE) | (1L << DYNAMIC) | (1L << EQUALS) | (1L << FALSE) | (1L << FLOAT) | (1L << FROM) | (1L << GET) | (1L << GROUP) | (1L << IN) | (1L << INT) | (1L << INTO) | (1L << JOIN) | (1L << LET))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (LONG - 64)) | (1L << (NAMEOF - 64)) | (1L << (NEW - 64)) | (1L << (NULL_ - 64)) | (1L << (OBJECT - 64)) | (1L << (ON - 64)) | (1L << (ORDERBY - 64)) | (1L << (OUT - 64)) | (1L << (PARTIAL - 64)) | (1L << (REF - 64)) | (1L << (REMOVE - 64)) | (1L << (SBYTE - 64)) | (1L << (SELECT - 64)) | (1L << (SET - 64)) | (1L << (SHORT - 64)) | (1L << (SIZEOF - 64)) | (1L << (STRING - 64)) | (1L << (THIS - 64)) | (1L << (TRUE - 64)) | (1L << (TYPEOF - 64)) | (1L << (UINT - 64)) | (1L << (ULONG - 64)) | (1L << (UNCHECKED - 64)) | (1L << (UNMANAGED - 64)) | (1L << (USHORT - 64)) | (1L << (VAR - 64)) | (1L << (VOID - 64)) | (1L << (WHEN - 64)) | (1L << (WHERE - 64)) | (1L << (YIELD - 64)) | (1L << (IDENTIFIER - 64)) | (1L << (LITERAL_ACCESS - 64)) | (1L << (INTEGER_LITERAL - 64)) | (1L << (HEX_INTEGER_LITERAL - 64)) | (1L << (BIN_INTEGER_LITERAL - 64)) | (1L << (REAL_LITERAL - 64)) | (1L << (CHARACTER_LITERAL - 64)) | (1L << (REGULAR_STRING - 64)) | (1L << (VERBATIUM_STRING - 64)) | (1L << (INTERPOLATED_REGULAR_STRING_START - 64)))) != 0) || ((((_la - 128)) & ~0x3f) == 0 && ((1L << (_la - 128)) & ((1L << (INTERPOLATED_VERBATIUM_STRING_START - 128)) | (1L << (OPEN_PARENS - 128)) | (1L << (PLUS - 128)) | (1L << (MINUS - 128)) | (1L << (STAR - 128)) | (1L << (AMP - 128)) | (1L << (CARET - 128)) | (1L << (BANG - 128)) | (1L << (TILDE - 128)) | (1L << (OP_INC - 128)) | (1L << (OP_DEC - 128)) | (1L << (OP_RANGE - 128)))) != 0)) {
					{
					setState(853);
					argument_list();
					}
				}

				setState(856);
				match(CLOSE_PARENS);
				}
				break;
			case 11:
				_localctx = new TupleExpressionContext(_localctx);
				enterOuterAlt(_localctx, 11);
				{
				setState(857);
				match(OPEN_PARENS);
				setState(858);
				argument();
				setState(861); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(859);
					match(COMMA);
					setState(860);
					argument();
					}
					}
					setState(863); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==COMMA );
				setState(865);
				match(CLOSE_PARENS);
				}
				break;
			case 12:
				_localctx = new TypeofExpressionContext(_localctx);
				enterOuterAlt(_localctx, 12);
				{
				setState(867);
				match(TYPEOF);
				setState(868);
				match(OPEN_PARENS);
				setState(872);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,68,_ctx) ) {
				case 1:
					{
					setState(869);
					unbound_type_name();
					}
					break;
				case 2:
					{
					setState(870);
					type_();
					}
					break;
				case 3:
					{
					setState(871);
					match(VOID);
					}
					break;
				}
				setState(874);
				match(CLOSE_PARENS);
				}
				break;
			case 13:
				_localctx = new CheckedExpressionContext(_localctx);
				enterOuterAlt(_localctx, 13);
				{
				setState(875);
				match(CHECKED);
				setState(876);
				match(OPEN_PARENS);
				setState(877);
				expression();
				setState(878);
				match(CLOSE_PARENS);
				}
				break;
			case 14:
				_localctx = new UncheckedExpressionContext(_localctx);
				enterOuterAlt(_localctx, 14);
				{
				setState(880);
				match(UNCHECKED);
				setState(881);
				match(OPEN_PARENS);
				setState(882);
				expression();
				setState(883);
				match(CLOSE_PARENS);
				}
				break;
			case 15:
				_localctx = new DefaultValueExpressionContext(_localctx);
				enterOuterAlt(_localctx, 15);
				{
				setState(885);
				match(DEFAULT);
				setState(890);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,69,_ctx) ) {
				case 1:
					{
					setState(886);
					match(OPEN_PARENS);
					setState(887);
					type_();
					setState(888);
					match(CLOSE_PARENS);
					}
					break;
				}
				}
				break;
			case 16:
				_localctx = new AnonymousMethodExpressionContext(_localctx);
				enterOuterAlt(_localctx, 16);
				{
				setState(893);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ASYNC) {
					{
					setState(892);
					match(ASYNC);
					}
				}

				setState(895);
				match(DELEGATE);
				setState(901);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==OPEN_PARENS) {
					{
					setState(896);
					match(OPEN_PARENS);
					setState(898);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (((((_la - 10)) & ~0x3f) == 0 && ((1L << (_la - 10)) & ((1L << (ADD - 10)) | (1L << (ALIAS - 10)) | (1L << (ARGLIST - 10)) | (1L << (ASCENDING - 10)) | (1L << (ASYNC - 10)) | (1L << (AWAIT - 10)) | (1L << (BOOL - 10)) | (1L << (BY - 10)) | (1L << (BYTE - 10)) | (1L << (CHAR - 10)) | (1L << (DECIMAL - 10)) | (1L << (DESCENDING - 10)) | (1L << (DOUBLE - 10)) | (1L << (DYNAMIC - 10)) | (1L << (EQUALS - 10)) | (1L << (FLOAT - 10)) | (1L << (FROM - 10)) | (1L << (GET - 10)) | (1L << (GROUP - 10)) | (1L << (IN - 10)) | (1L << (INT - 10)) | (1L << (INTO - 10)) | (1L << (JOIN - 10)) | (1L << (LET - 10)) | (1L << (LONG - 10)) | (1L << (NAMEOF - 10)) | (1L << (OBJECT - 10)) | (1L << (ON - 10)))) != 0) || ((((_la - 74)) & ~0x3f) == 0 && ((1L << (_la - 74)) & ((1L << (ORDERBY - 74)) | (1L << (OUT - 74)) | (1L << (PARTIAL - 74)) | (1L << (REF - 74)) | (1L << (REMOVE - 74)) | (1L << (SBYTE - 74)) | (1L << (SELECT - 74)) | (1L << (SET - 74)) | (1L << (SHORT - 74)) | (1L << (STRING - 74)) | (1L << (UINT - 74)) | (1L << (ULONG - 74)) | (1L << (UNMANAGED - 74)) | (1L << (USHORT - 74)) | (1L << (VAR - 74)) | (1L << (VOID - 74)) | (1L << (WHEN - 74)) | (1L << (WHERE - 74)) | (1L << (YIELD - 74)) | (1L << (IDENTIFIER - 74)) | (1L << (OPEN_PARENS - 74)))) != 0)) {
						{
						setState(897);
						explicit_anonymous_function_parameter_list();
						}
					}

					setState(900);
					match(CLOSE_PARENS);
					}
				}

				setState(903);
				block();
				}
				break;
			case 17:
				_localctx = new SizeofExpressionContext(_localctx);
				enterOuterAlt(_localctx, 17);
				{
				setState(904);
				match(SIZEOF);
				setState(905);
				match(OPEN_PARENS);
				setState(906);
				type_();
				setState(907);
				match(CLOSE_PARENS);
				}
				break;
			case 18:
				_localctx = new NameofExpressionContext(_localctx);
				enterOuterAlt(_localctx, 18);
				{
				setState(909);
				match(NAMEOF);
				setState(910);
				match(OPEN_PARENS);
				setState(916);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,73,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(911);
						identifier();
						setState(912);
						match(DOT);
						}
						} 
					}
					setState(918);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,73,_ctx);
				}
				setState(919);
				identifier();
				setState(920);
				match(CLOSE_PARENS);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Throwable_expressionContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public Throw_expressionContext throw_expression() {
			return getRuleContext(Throw_expressionContext.class,0);
		}
		public Throwable_expressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_throwable_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterThrowable_expression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitThrowable_expression(this);
		}
	}

	public final Throwable_expressionContext throwable_expression() throws RecognitionException {
		Throwable_expressionContext _localctx = new Throwable_expressionContext(_ctx, getState());
		enterRule(_localctx, 74, RULE_throwable_expression);
		try {
			setState(926);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ADD:
			case ALIAS:
			case ARGLIST:
			case ASCENDING:
			case ASYNC:
			case AWAIT:
			case BASE:
			case BOOL:
			case BY:
			case BYTE:
			case CHAR:
			case CHECKED:
			case DECIMAL:
			case DEFAULT:
			case DELEGATE:
			case DESCENDING:
			case DOUBLE:
			case DYNAMIC:
			case EQUALS:
			case FALSE:
			case FLOAT:
			case FROM:
			case GET:
			case GROUP:
			case INT:
			case INTO:
			case JOIN:
			case LET:
			case LONG:
			case NAMEOF:
			case NEW:
			case NULL_:
			case OBJECT:
			case ON:
			case ORDERBY:
			case PARTIAL:
			case REF:
			case REMOVE:
			case SBYTE:
			case SELECT:
			case SET:
			case SHORT:
			case SIZEOF:
			case STRING:
			case THIS:
			case TRUE:
			case TYPEOF:
			case UINT:
			case ULONG:
			case UNCHECKED:
			case UNMANAGED:
			case USHORT:
			case VAR:
			case WHEN:
			case WHERE:
			case YIELD:
			case IDENTIFIER:
			case LITERAL_ACCESS:
			case INTEGER_LITERAL:
			case HEX_INTEGER_LITERAL:
			case BIN_INTEGER_LITERAL:
			case REAL_LITERAL:
			case CHARACTER_LITERAL:
			case REGULAR_STRING:
			case VERBATIUM_STRING:
			case INTERPOLATED_REGULAR_STRING_START:
			case INTERPOLATED_VERBATIUM_STRING_START:
			case OPEN_PARENS:
			case PLUS:
			case MINUS:
			case STAR:
			case AMP:
			case CARET:
			case BANG:
			case TILDE:
			case OP_INC:
			case OP_DEC:
			case OP_RANGE:
				enterOuterAlt(_localctx, 1);
				{
				setState(924);
				expression();
				}
				break;
			case THROW:
				enterOuterAlt(_localctx, 2);
				{
				setState(925);
				throw_expression();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Throw_expressionContext extends ParserRuleContext {
		public TerminalNode THROW() { return getToken(CSharpParser.THROW, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public Throw_expressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_throw_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterThrow_expression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitThrow_expression(this);
		}
	}

	public final Throw_expressionContext throw_expression() throws RecognitionException {
		Throw_expressionContext _localctx = new Throw_expressionContext(_ctx, getState());
		enterRule(_localctx, 76, RULE_throw_expression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(928);
			match(THROW);
			setState(929);
			expression();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Member_accessContext extends ParserRuleContext {
		public TerminalNode DOT() { return getToken(CSharpParser.DOT, 0); }
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TerminalNode INTERR() { return getToken(CSharpParser.INTERR, 0); }
		public Type_argument_listContext type_argument_list() {
			return getRuleContext(Type_argument_listContext.class,0);
		}
		public Member_accessContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_member_access; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterMember_access(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitMember_access(this);
		}
	}

	public final Member_accessContext member_access() throws RecognitionException {
		Member_accessContext _localctx = new Member_accessContext(_ctx, getState());
		enterRule(_localctx, 78, RULE_member_access);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(932);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==INTERR) {
				{
				setState(931);
				match(INTERR);
				}
			}

			setState(934);
			match(DOT);
			setState(935);
			identifier();
			setState(937);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,77,_ctx) ) {
			case 1:
				{
				setState(936);
				type_argument_list();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Bracket_expressionContext extends ParserRuleContext {
		public TerminalNode OPEN_BRACKET() { return getToken(CSharpParser.OPEN_BRACKET, 0); }
		public List<Indexer_argumentContext> indexer_argument() {
			return getRuleContexts(Indexer_argumentContext.class);
		}
		public Indexer_argumentContext indexer_argument(int i) {
			return getRuleContext(Indexer_argumentContext.class,i);
		}
		public TerminalNode CLOSE_BRACKET() { return getToken(CSharpParser.CLOSE_BRACKET, 0); }
		public TerminalNode INTERR() { return getToken(CSharpParser.INTERR, 0); }
		public List<TerminalNode> COMMA() { return getTokens(CSharpParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(CSharpParser.COMMA, i);
		}
		public Bracket_expressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bracket_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterBracket_expression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitBracket_expression(this);
		}
	}

	public final Bracket_expressionContext bracket_expression() throws RecognitionException {
		Bracket_expressionContext _localctx = new Bracket_expressionContext(_ctx, getState());
		enterRule(_localctx, 80, RULE_bracket_expression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(940);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==INTERR) {
				{
				setState(939);
				match(INTERR);
				}
			}

			setState(942);
			match(OPEN_BRACKET);
			setState(943);
			indexer_argument();
			setState(948);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(944);
				match(COMMA);
				setState(945);
				indexer_argument();
				}
				}
				setState(950);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(951);
			match(CLOSE_BRACKET);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Indexer_argumentContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TerminalNode COLON() { return getToken(CSharpParser.COLON, 0); }
		public Indexer_argumentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_indexer_argument; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterIndexer_argument(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitIndexer_argument(this);
		}
	}

	public final Indexer_argumentContext indexer_argument() throws RecognitionException {
		Indexer_argumentContext _localctx = new Indexer_argumentContext(_ctx, getState());
		enterRule(_localctx, 82, RULE_indexer_argument);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(956);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,80,_ctx) ) {
			case 1:
				{
				setState(953);
				identifier();
				setState(954);
				match(COLON);
				}
				break;
			}
			setState(958);
			expression();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Predefined_typeContext extends ParserRuleContext {
		public TerminalNode BOOL() { return getToken(CSharpParser.BOOL, 0); }
		public TerminalNode BYTE() { return getToken(CSharpParser.BYTE, 0); }
		public TerminalNode CHAR() { return getToken(CSharpParser.CHAR, 0); }
		public TerminalNode DECIMAL() { return getToken(CSharpParser.DECIMAL, 0); }
		public TerminalNode DOUBLE() { return getToken(CSharpParser.DOUBLE, 0); }
		public TerminalNode FLOAT() { return getToken(CSharpParser.FLOAT, 0); }
		public TerminalNode INT() { return getToken(CSharpParser.INT, 0); }
		public TerminalNode LONG() { return getToken(CSharpParser.LONG, 0); }
		public TerminalNode OBJECT() { return getToken(CSharpParser.OBJECT, 0); }
		public TerminalNode SBYTE() { return getToken(CSharpParser.SBYTE, 0); }
		public TerminalNode SHORT() { return getToken(CSharpParser.SHORT, 0); }
		public TerminalNode STRING() { return getToken(CSharpParser.STRING, 0); }
		public TerminalNode UINT() { return getToken(CSharpParser.UINT, 0); }
		public TerminalNode ULONG() { return getToken(CSharpParser.ULONG, 0); }
		public TerminalNode USHORT() { return getToken(CSharpParser.USHORT, 0); }
		public Predefined_typeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_predefined_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterPredefined_type(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitPredefined_type(this);
		}
	}

	public final Predefined_typeContext predefined_type() throws RecognitionException {
		Predefined_typeContext _localctx = new Predefined_typeContext(_ctx, getState());
		enterRule(_localctx, 84, RULE_predefined_type);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(960);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BOOL) | (1L << BYTE) | (1L << CHAR) | (1L << DECIMAL) | (1L << DOUBLE) | (1L << FLOAT) | (1L << INT))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (LONG - 64)) | (1L << (OBJECT - 64)) | (1L << (SBYTE - 64)) | (1L << (SHORT - 64)) | (1L << (STRING - 64)) | (1L << (UINT - 64)) | (1L << (ULONG - 64)) | (1L << (USHORT - 64)))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Expression_listContext extends ParserRuleContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(CSharpParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(CSharpParser.COMMA, i);
		}
		public Expression_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterExpression_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitExpression_list(this);
		}
	}

	public final Expression_listContext expression_list() throws RecognitionException {
		Expression_listContext _localctx = new Expression_listContext(_ctx, getState());
		enterRule(_localctx, 86, RULE_expression_list);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(962);
			expression();
			setState(967);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(963);
				match(COMMA);
				setState(964);
				expression();
				}
				}
				setState(969);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Object_or_collection_initializerContext extends ParserRuleContext {
		public Object_initializerContext object_initializer() {
			return getRuleContext(Object_initializerContext.class,0);
		}
		public Collection_initializerContext collection_initializer() {
			return getRuleContext(Collection_initializerContext.class,0);
		}
		public Object_or_collection_initializerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_object_or_collection_initializer; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterObject_or_collection_initializer(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitObject_or_collection_initializer(this);
		}
	}

	public final Object_or_collection_initializerContext object_or_collection_initializer() throws RecognitionException {
		Object_or_collection_initializerContext _localctx = new Object_or_collection_initializerContext(_ctx, getState());
		enterRule(_localctx, 88, RULE_object_or_collection_initializer);
		try {
			setState(972);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,82,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(970);
				object_initializer();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(971);
				collection_initializer();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Object_initializerContext extends ParserRuleContext {
		public TerminalNode OPEN_BRACE() { return getToken(CSharpParser.OPEN_BRACE, 0); }
		public TerminalNode CLOSE_BRACE() { return getToken(CSharpParser.CLOSE_BRACE, 0); }
		public Member_initializer_listContext member_initializer_list() {
			return getRuleContext(Member_initializer_listContext.class,0);
		}
		public TerminalNode COMMA() { return getToken(CSharpParser.COMMA, 0); }
		public Object_initializerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_object_initializer; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterObject_initializer(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitObject_initializer(this);
		}
	}

	public final Object_initializerContext object_initializer() throws RecognitionException {
		Object_initializerContext _localctx = new Object_initializerContext(_ctx, getState());
		enterRule(_localctx, 90, RULE_object_initializer);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(974);
			match(OPEN_BRACE);
			setState(979);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (((((_la - 10)) & ~0x3f) == 0 && ((1L << (_la - 10)) & ((1L << (ADD - 10)) | (1L << (ALIAS - 10)) | (1L << (ARGLIST - 10)) | (1L << (ASCENDING - 10)) | (1L << (ASYNC - 10)) | (1L << (AWAIT - 10)) | (1L << (BY - 10)) | (1L << (DESCENDING - 10)) | (1L << (DYNAMIC - 10)) | (1L << (EQUALS - 10)) | (1L << (FROM - 10)) | (1L << (GET - 10)) | (1L << (GROUP - 10)) | (1L << (INTO - 10)) | (1L << (JOIN - 10)) | (1L << (LET - 10)) | (1L << (NAMEOF - 10)) | (1L << (ON - 10)))) != 0) || ((((_la - 74)) & ~0x3f) == 0 && ((1L << (_la - 74)) & ((1L << (ORDERBY - 74)) | (1L << (PARTIAL - 74)) | (1L << (REMOVE - 74)) | (1L << (SELECT - 74)) | (1L << (SET - 74)) | (1L << (UNMANAGED - 74)) | (1L << (VAR - 74)) | (1L << (WHEN - 74)) | (1L << (WHERE - 74)) | (1L << (YIELD - 74)) | (1L << (IDENTIFIER - 74)) | (1L << (OPEN_BRACKET - 74)))) != 0)) {
				{
				setState(975);
				member_initializer_list();
				setState(977);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==COMMA) {
					{
					setState(976);
					match(COMMA);
					}
				}

				}
			}

			setState(981);
			match(CLOSE_BRACE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Member_initializer_listContext extends ParserRuleContext {
		public List<Member_initializerContext> member_initializer() {
			return getRuleContexts(Member_initializerContext.class);
		}
		public Member_initializerContext member_initializer(int i) {
			return getRuleContext(Member_initializerContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(CSharpParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(CSharpParser.COMMA, i);
		}
		public Member_initializer_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_member_initializer_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterMember_initializer_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitMember_initializer_list(this);
		}
	}

	public final Member_initializer_listContext member_initializer_list() throws RecognitionException {
		Member_initializer_listContext _localctx = new Member_initializer_listContext(_ctx, getState());
		enterRule(_localctx, 92, RULE_member_initializer_list);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(983);
			member_initializer();
			setState(988);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,85,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(984);
					match(COMMA);
					setState(985);
					member_initializer();
					}
					} 
				}
				setState(990);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,85,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Member_initializerContext extends ParserRuleContext {
		public TerminalNode ASSIGNMENT() { return getToken(CSharpParser.ASSIGNMENT, 0); }
		public Initializer_valueContext initializer_value() {
			return getRuleContext(Initializer_valueContext.class,0);
		}
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TerminalNode OPEN_BRACKET() { return getToken(CSharpParser.OPEN_BRACKET, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode CLOSE_BRACKET() { return getToken(CSharpParser.CLOSE_BRACKET, 0); }
		public Member_initializerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_member_initializer; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterMember_initializer(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitMember_initializer(this);
		}
	}

	public final Member_initializerContext member_initializer() throws RecognitionException {
		Member_initializerContext _localctx = new Member_initializerContext(_ctx, getState());
		enterRule(_localctx, 94, RULE_member_initializer);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(996);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ADD:
			case ALIAS:
			case ARGLIST:
			case ASCENDING:
			case ASYNC:
			case AWAIT:
			case BY:
			case DESCENDING:
			case DYNAMIC:
			case EQUALS:
			case FROM:
			case GET:
			case GROUP:
			case INTO:
			case JOIN:
			case LET:
			case NAMEOF:
			case ON:
			case ORDERBY:
			case PARTIAL:
			case REMOVE:
			case SELECT:
			case SET:
			case UNMANAGED:
			case VAR:
			case WHEN:
			case WHERE:
			case YIELD:
			case IDENTIFIER:
				{
				setState(991);
				identifier();
				}
				break;
			case OPEN_BRACKET:
				{
				setState(992);
				match(OPEN_BRACKET);
				setState(993);
				expression();
				setState(994);
				match(CLOSE_BRACKET);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(998);
			match(ASSIGNMENT);
			setState(999);
			initializer_value();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Initializer_valueContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public Object_or_collection_initializerContext object_or_collection_initializer() {
			return getRuleContext(Object_or_collection_initializerContext.class,0);
		}
		public Initializer_valueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_initializer_value; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterInitializer_value(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitInitializer_value(this);
		}
	}

	public final Initializer_valueContext initializer_value() throws RecognitionException {
		Initializer_valueContext _localctx = new Initializer_valueContext(_ctx, getState());
		enterRule(_localctx, 96, RULE_initializer_value);
		try {
			setState(1003);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ADD:
			case ALIAS:
			case ARGLIST:
			case ASCENDING:
			case ASYNC:
			case AWAIT:
			case BASE:
			case BOOL:
			case BY:
			case BYTE:
			case CHAR:
			case CHECKED:
			case DECIMAL:
			case DEFAULT:
			case DELEGATE:
			case DESCENDING:
			case DOUBLE:
			case DYNAMIC:
			case EQUALS:
			case FALSE:
			case FLOAT:
			case FROM:
			case GET:
			case GROUP:
			case INT:
			case INTO:
			case JOIN:
			case LET:
			case LONG:
			case NAMEOF:
			case NEW:
			case NULL_:
			case OBJECT:
			case ON:
			case ORDERBY:
			case PARTIAL:
			case REF:
			case REMOVE:
			case SBYTE:
			case SELECT:
			case SET:
			case SHORT:
			case SIZEOF:
			case STRING:
			case THIS:
			case TRUE:
			case TYPEOF:
			case UINT:
			case ULONG:
			case UNCHECKED:
			case UNMANAGED:
			case USHORT:
			case VAR:
			case WHEN:
			case WHERE:
			case YIELD:
			case IDENTIFIER:
			case LITERAL_ACCESS:
			case INTEGER_LITERAL:
			case HEX_INTEGER_LITERAL:
			case BIN_INTEGER_LITERAL:
			case REAL_LITERAL:
			case CHARACTER_LITERAL:
			case REGULAR_STRING:
			case VERBATIUM_STRING:
			case INTERPOLATED_REGULAR_STRING_START:
			case INTERPOLATED_VERBATIUM_STRING_START:
			case OPEN_PARENS:
			case PLUS:
			case MINUS:
			case STAR:
			case AMP:
			case CARET:
			case BANG:
			case TILDE:
			case OP_INC:
			case OP_DEC:
			case OP_RANGE:
				enterOuterAlt(_localctx, 1);
				{
				setState(1001);
				expression();
				}
				break;
			case OPEN_BRACE:
				enterOuterAlt(_localctx, 2);
				{
				setState(1002);
				object_or_collection_initializer();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Collection_initializerContext extends ParserRuleContext {
		public TerminalNode OPEN_BRACE() { return getToken(CSharpParser.OPEN_BRACE, 0); }
		public List<Element_initializerContext> element_initializer() {
			return getRuleContexts(Element_initializerContext.class);
		}
		public Element_initializerContext element_initializer(int i) {
			return getRuleContext(Element_initializerContext.class,i);
		}
		public TerminalNode CLOSE_BRACE() { return getToken(CSharpParser.CLOSE_BRACE, 0); }
		public List<TerminalNode> COMMA() { return getTokens(CSharpParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(CSharpParser.COMMA, i);
		}
		public Collection_initializerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_collection_initializer; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterCollection_initializer(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitCollection_initializer(this);
		}
	}

	public final Collection_initializerContext collection_initializer() throws RecognitionException {
		Collection_initializerContext _localctx = new Collection_initializerContext(_ctx, getState());
		enterRule(_localctx, 98, RULE_collection_initializer);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(1005);
			match(OPEN_BRACE);
			setState(1006);
			element_initializer();
			setState(1011);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,88,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(1007);
					match(COMMA);
					setState(1008);
					element_initializer();
					}
					} 
				}
				setState(1013);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,88,_ctx);
			}
			setState(1015);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==COMMA) {
				{
				setState(1014);
				match(COMMA);
				}
			}

			setState(1017);
			match(CLOSE_BRACE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Element_initializerContext extends ParserRuleContext {
		public Non_assignment_expressionContext non_assignment_expression() {
			return getRuleContext(Non_assignment_expressionContext.class,0);
		}
		public TerminalNode OPEN_BRACE() { return getToken(CSharpParser.OPEN_BRACE, 0); }
		public Expression_listContext expression_list() {
			return getRuleContext(Expression_listContext.class,0);
		}
		public TerminalNode CLOSE_BRACE() { return getToken(CSharpParser.CLOSE_BRACE, 0); }
		public Element_initializerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_element_initializer; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterElement_initializer(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitElement_initializer(this);
		}
	}

	public final Element_initializerContext element_initializer() throws RecognitionException {
		Element_initializerContext _localctx = new Element_initializerContext(_ctx, getState());
		enterRule(_localctx, 100, RULE_element_initializer);
		try {
			setState(1024);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ADD:
			case ALIAS:
			case ARGLIST:
			case ASCENDING:
			case ASYNC:
			case AWAIT:
			case BASE:
			case BOOL:
			case BY:
			case BYTE:
			case CHAR:
			case CHECKED:
			case DECIMAL:
			case DEFAULT:
			case DELEGATE:
			case DESCENDING:
			case DOUBLE:
			case DYNAMIC:
			case EQUALS:
			case FALSE:
			case FLOAT:
			case FROM:
			case GET:
			case GROUP:
			case INT:
			case INTO:
			case JOIN:
			case LET:
			case LONG:
			case NAMEOF:
			case NEW:
			case NULL_:
			case OBJECT:
			case ON:
			case ORDERBY:
			case PARTIAL:
			case REMOVE:
			case SBYTE:
			case SELECT:
			case SET:
			case SHORT:
			case SIZEOF:
			case STRING:
			case THIS:
			case TRUE:
			case TYPEOF:
			case UINT:
			case ULONG:
			case UNCHECKED:
			case UNMANAGED:
			case USHORT:
			case VAR:
			case WHEN:
			case WHERE:
			case YIELD:
			case IDENTIFIER:
			case LITERAL_ACCESS:
			case INTEGER_LITERAL:
			case HEX_INTEGER_LITERAL:
			case BIN_INTEGER_LITERAL:
			case REAL_LITERAL:
			case CHARACTER_LITERAL:
			case REGULAR_STRING:
			case VERBATIUM_STRING:
			case INTERPOLATED_REGULAR_STRING_START:
			case INTERPOLATED_VERBATIUM_STRING_START:
			case OPEN_PARENS:
			case PLUS:
			case MINUS:
			case STAR:
			case AMP:
			case CARET:
			case BANG:
			case TILDE:
			case OP_INC:
			case OP_DEC:
			case OP_RANGE:
				enterOuterAlt(_localctx, 1);
				{
				setState(1019);
				non_assignment_expression();
				}
				break;
			case OPEN_BRACE:
				enterOuterAlt(_localctx, 2);
				{
				setState(1020);
				match(OPEN_BRACE);
				setState(1021);
				expression_list();
				setState(1022);
				match(CLOSE_BRACE);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Anonymous_object_initializerContext extends ParserRuleContext {
		public TerminalNode OPEN_BRACE() { return getToken(CSharpParser.OPEN_BRACE, 0); }
		public TerminalNode CLOSE_BRACE() { return getToken(CSharpParser.CLOSE_BRACE, 0); }
		public Member_declarator_listContext member_declarator_list() {
			return getRuleContext(Member_declarator_listContext.class,0);
		}
		public TerminalNode COMMA() { return getToken(CSharpParser.COMMA, 0); }
		public Anonymous_object_initializerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_anonymous_object_initializer; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterAnonymous_object_initializer(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitAnonymous_object_initializer(this);
		}
	}

	public final Anonymous_object_initializerContext anonymous_object_initializer() throws RecognitionException {
		Anonymous_object_initializerContext _localctx = new Anonymous_object_initializerContext(_ctx, getState());
		enterRule(_localctx, 102, RULE_anonymous_object_initializer);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1026);
			match(OPEN_BRACE);
			setState(1031);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (((((_la - 10)) & ~0x3f) == 0 && ((1L << (_la - 10)) & ((1L << (ADD - 10)) | (1L << (ALIAS - 10)) | (1L << (ARGLIST - 10)) | (1L << (ASCENDING - 10)) | (1L << (ASYNC - 10)) | (1L << (AWAIT - 10)) | (1L << (BASE - 10)) | (1L << (BOOL - 10)) | (1L << (BY - 10)) | (1L << (BYTE - 10)) | (1L << (CHAR - 10)) | (1L << (CHECKED - 10)) | (1L << (DECIMAL - 10)) | (1L << (DEFAULT - 10)) | (1L << (DELEGATE - 10)) | (1L << (DESCENDING - 10)) | (1L << (DOUBLE - 10)) | (1L << (DYNAMIC - 10)) | (1L << (EQUALS - 10)) | (1L << (FALSE - 10)) | (1L << (FLOAT - 10)) | (1L << (FROM - 10)) | (1L << (GET - 10)) | (1L << (GROUP - 10)) | (1L << (INT - 10)) | (1L << (INTO - 10)) | (1L << (JOIN - 10)) | (1L << (LET - 10)) | (1L << (LONG - 10)) | (1L << (NAMEOF - 10)) | (1L << (NEW - 10)) | (1L << (NULL_ - 10)) | (1L << (OBJECT - 10)) | (1L << (ON - 10)))) != 0) || ((((_la - 74)) & ~0x3f) == 0 && ((1L << (_la - 74)) & ((1L << (ORDERBY - 74)) | (1L << (PARTIAL - 74)) | (1L << (REMOVE - 74)) | (1L << (SBYTE - 74)) | (1L << (SELECT - 74)) | (1L << (SET - 74)) | (1L << (SHORT - 74)) | (1L << (SIZEOF - 74)) | (1L << (STRING - 74)) | (1L << (THIS - 74)) | (1L << (TRUE - 74)) | (1L << (TYPEOF - 74)) | (1L << (UINT - 74)) | (1L << (ULONG - 74)) | (1L << (UNCHECKED - 74)) | (1L << (UNMANAGED - 74)) | (1L << (USHORT - 74)) | (1L << (VAR - 74)) | (1L << (WHEN - 74)) | (1L << (WHERE - 74)) | (1L << (YIELD - 74)) | (1L << (IDENTIFIER - 74)) | (1L << (LITERAL_ACCESS - 74)) | (1L << (INTEGER_LITERAL - 74)) | (1L << (HEX_INTEGER_LITERAL - 74)) | (1L << (BIN_INTEGER_LITERAL - 74)) | (1L << (REAL_LITERAL - 74)) | (1L << (CHARACTER_LITERAL - 74)) | (1L << (REGULAR_STRING - 74)) | (1L << (VERBATIUM_STRING - 74)) | (1L << (INTERPOLATED_REGULAR_STRING_START - 74)) | (1L << (INTERPOLATED_VERBATIUM_STRING_START - 74)) | (1L << (OPEN_PARENS - 74)))) != 0)) {
				{
				setState(1027);
				member_declarator_list();
				setState(1029);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==COMMA) {
					{
					setState(1028);
					match(COMMA);
					}
				}

				}
			}

			setState(1033);
			match(CLOSE_BRACE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Member_declarator_listContext extends ParserRuleContext {
		public List<Member_declaratorContext> member_declarator() {
			return getRuleContexts(Member_declaratorContext.class);
		}
		public Member_declaratorContext member_declarator(int i) {
			return getRuleContext(Member_declaratorContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(CSharpParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(CSharpParser.COMMA, i);
		}
		public Member_declarator_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_member_declarator_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterMember_declarator_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitMember_declarator_list(this);
		}
	}

	public final Member_declarator_listContext member_declarator_list() throws RecognitionException {
		Member_declarator_listContext _localctx = new Member_declarator_listContext(_ctx, getState());
		enterRule(_localctx, 104, RULE_member_declarator_list);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(1035);
			member_declarator();
			setState(1040);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,93,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(1036);
					match(COMMA);
					setState(1037);
					member_declarator();
					}
					} 
				}
				setState(1042);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,93,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Member_declaratorContext extends ParserRuleContext {
		public Primary_expressionContext primary_expression() {
			return getRuleContext(Primary_expressionContext.class,0);
		}
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TerminalNode ASSIGNMENT() { return getToken(CSharpParser.ASSIGNMENT, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public Member_declaratorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_member_declarator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterMember_declarator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitMember_declarator(this);
		}
	}

	public final Member_declaratorContext member_declarator() throws RecognitionException {
		Member_declaratorContext _localctx = new Member_declaratorContext(_ctx, getState());
		enterRule(_localctx, 106, RULE_member_declarator);
		try {
			setState(1048);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,94,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1043);
				primary_expression();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1044);
				identifier();
				setState(1045);
				match(ASSIGNMENT);
				setState(1046);
				expression();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Unbound_type_nameContext extends ParserRuleContext {
		public List<IdentifierContext> identifier() {
			return getRuleContexts(IdentifierContext.class);
		}
		public IdentifierContext identifier(int i) {
			return getRuleContext(IdentifierContext.class,i);
		}
		public TerminalNode DOUBLE_COLON() { return getToken(CSharpParser.DOUBLE_COLON, 0); }
		public List<TerminalNode> DOT() { return getTokens(CSharpParser.DOT); }
		public TerminalNode DOT(int i) {
			return getToken(CSharpParser.DOT, i);
		}
		public List<Generic_dimension_specifierContext> generic_dimension_specifier() {
			return getRuleContexts(Generic_dimension_specifierContext.class);
		}
		public Generic_dimension_specifierContext generic_dimension_specifier(int i) {
			return getRuleContext(Generic_dimension_specifierContext.class,i);
		}
		public Unbound_type_nameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unbound_type_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterUnbound_type_name(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitUnbound_type_name(this);
		}
	}

	public final Unbound_type_nameContext unbound_type_name() throws RecognitionException {
		Unbound_type_nameContext _localctx = new Unbound_type_nameContext(_ctx, getState());
		enterRule(_localctx, 108, RULE_unbound_type_name);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1050);
			identifier();
			setState(1059);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case CLOSE_PARENS:
			case DOT:
			case LT:
				{
				setState(1052);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==LT) {
					{
					setState(1051);
					generic_dimension_specifier();
					}
				}

				}
				break;
			case DOUBLE_COLON:
				{
				setState(1054);
				match(DOUBLE_COLON);
				setState(1055);
				identifier();
				setState(1057);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==LT) {
					{
					setState(1056);
					generic_dimension_specifier();
					}
				}

				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(1068);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==DOT) {
				{
				{
				setState(1061);
				match(DOT);
				setState(1062);
				identifier();
				setState(1064);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==LT) {
					{
					setState(1063);
					generic_dimension_specifier();
					}
				}

				}
				}
				setState(1070);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Generic_dimension_specifierContext extends ParserRuleContext {
		public TerminalNode LT() { return getToken(CSharpParser.LT, 0); }
		public TerminalNode GT() { return getToken(CSharpParser.GT, 0); }
		public List<TerminalNode> COMMA() { return getTokens(CSharpParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(CSharpParser.COMMA, i);
		}
		public Generic_dimension_specifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_generic_dimension_specifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterGeneric_dimension_specifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitGeneric_dimension_specifier(this);
		}
	}

	public final Generic_dimension_specifierContext generic_dimension_specifier() throws RecognitionException {
		Generic_dimension_specifierContext _localctx = new Generic_dimension_specifierContext(_ctx, getState());
		enterRule(_localctx, 110, RULE_generic_dimension_specifier);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1071);
			match(LT);
			setState(1075);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(1072);
				match(COMMA);
				}
				}
				setState(1077);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(1078);
			match(GT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IsTypeContext extends ParserRuleContext {
		public Base_typeContext base_type() {
			return getRuleContext(Base_typeContext.class,0);
		}
		public List<Rank_specifierContext> rank_specifier() {
			return getRuleContexts(Rank_specifierContext.class);
		}
		public Rank_specifierContext rank_specifier(int i) {
			return getRuleContext(Rank_specifierContext.class,i);
		}
		public List<TerminalNode> STAR() { return getTokens(CSharpParser.STAR); }
		public TerminalNode STAR(int i) {
			return getToken(CSharpParser.STAR, i);
		}
		public TerminalNode INTERR() { return getToken(CSharpParser.INTERR, 0); }
		public IsTypePatternArmsContext isTypePatternArms() {
			return getRuleContext(IsTypePatternArmsContext.class,0);
		}
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TerminalNode NULL_() { return getToken(CSharpParser.NULL_, 0); }
		public IsTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_isType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterIsType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitIsType(this);
		}
	}

	public final IsTypeContext isType() throws RecognitionException {
		IsTypeContext _localctx = new IsTypeContext(_ctx, getState());
		enterRule(_localctx, 112, RULE_isType);
		int _la;
		try {
			int _alt;
			setState(1098);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ADD:
			case ALIAS:
			case ARGLIST:
			case ASCENDING:
			case ASYNC:
			case AWAIT:
			case BOOL:
			case BY:
			case BYTE:
			case CHAR:
			case DECIMAL:
			case DESCENDING:
			case DOUBLE:
			case DYNAMIC:
			case EQUALS:
			case FLOAT:
			case FROM:
			case GET:
			case GROUP:
			case INT:
			case INTO:
			case JOIN:
			case LET:
			case LONG:
			case NAMEOF:
			case OBJECT:
			case ON:
			case ORDERBY:
			case PARTIAL:
			case REMOVE:
			case SBYTE:
			case SELECT:
			case SET:
			case SHORT:
			case STRING:
			case UINT:
			case ULONG:
			case UNMANAGED:
			case USHORT:
			case VAR:
			case VOID:
			case WHEN:
			case WHERE:
			case YIELD:
			case IDENTIFIER:
			case OPEN_PARENS:
				enterOuterAlt(_localctx, 1);
				{
				setState(1080);
				base_type();
				setState(1085);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,102,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						setState(1083);
						_errHandler.sync(this);
						switch (_input.LA(1)) {
						case OPEN_BRACKET:
							{
							setState(1081);
							rank_specifier();
							}
							break;
						case STAR:
							{
							setState(1082);
							match(STAR);
							}
							break;
						default:
							throw new NoViableAltException(this);
						}
						} 
					}
					setState(1087);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,102,_ctx);
				}
				setState(1089);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,103,_ctx) ) {
				case 1:
					{
					setState(1088);
					match(INTERR);
					}
					break;
				}
				setState(1092);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==OPEN_BRACE) {
					{
					setState(1091);
					isTypePatternArms();
					}
				}

				setState(1095);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,105,_ctx) ) {
				case 1:
					{
					setState(1094);
					identifier();
					}
					break;
				}
				}
				break;
			case NULL_:
				enterOuterAlt(_localctx, 2);
				{
				setState(1097);
				match(NULL_);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IsTypePatternArmsContext extends ParserRuleContext {
		public TerminalNode OPEN_BRACE() { return getToken(CSharpParser.OPEN_BRACE, 0); }
		public List<IsTypePatternArmContext> isTypePatternArm() {
			return getRuleContexts(IsTypePatternArmContext.class);
		}
		public IsTypePatternArmContext isTypePatternArm(int i) {
			return getRuleContext(IsTypePatternArmContext.class,i);
		}
		public TerminalNode CLOSE_BRACE() { return getToken(CSharpParser.CLOSE_BRACE, 0); }
		public List<TerminalNode> COMMA() { return getTokens(CSharpParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(CSharpParser.COMMA, i);
		}
		public IsTypePatternArmsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_isTypePatternArms; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterIsTypePatternArms(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitIsTypePatternArms(this);
		}
	}

	public final IsTypePatternArmsContext isTypePatternArms() throws RecognitionException {
		IsTypePatternArmsContext _localctx = new IsTypePatternArmsContext(_ctx, getState());
		enterRule(_localctx, 114, RULE_isTypePatternArms);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1100);
			match(OPEN_BRACE);
			setState(1101);
			isTypePatternArm();
			setState(1106);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(1102);
				match(COMMA);
				setState(1103);
				isTypePatternArm();
				}
				}
				setState(1108);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(1109);
			match(CLOSE_BRACE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IsTypePatternArmContext extends ParserRuleContext {
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TerminalNode COLON() { return getToken(CSharpParser.COLON, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public IsTypePatternArmContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_isTypePatternArm; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterIsTypePatternArm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitIsTypePatternArm(this);
		}
	}

	public final IsTypePatternArmContext isTypePatternArm() throws RecognitionException {
		IsTypePatternArmContext _localctx = new IsTypePatternArmContext(_ctx, getState());
		enterRule(_localctx, 116, RULE_isTypePatternArm);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1111);
			identifier();
			setState(1112);
			match(COLON);
			setState(1113);
			expression();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Lambda_expressionContext extends ParserRuleContext {
		public Anonymous_function_signatureContext anonymous_function_signature() {
			return getRuleContext(Anonymous_function_signatureContext.class,0);
		}
		public Right_arrowContext right_arrow() {
			return getRuleContext(Right_arrowContext.class,0);
		}
		public Anonymous_function_bodyContext anonymous_function_body() {
			return getRuleContext(Anonymous_function_bodyContext.class,0);
		}
		public TerminalNode ASYNC() { return getToken(CSharpParser.ASYNC, 0); }
		public Lambda_expressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lambda_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterLambda_expression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitLambda_expression(this);
		}
	}

	public final Lambda_expressionContext lambda_expression() throws RecognitionException {
		Lambda_expressionContext _localctx = new Lambda_expressionContext(_ctx, getState());
		enterRule(_localctx, 118, RULE_lambda_expression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1116);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,108,_ctx) ) {
			case 1:
				{
				setState(1115);
				match(ASYNC);
				}
				break;
			}
			setState(1118);
			anonymous_function_signature();
			setState(1119);
			right_arrow();
			setState(1120);
			anonymous_function_body();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Anonymous_function_signatureContext extends ParserRuleContext {
		public TerminalNode OPEN_PARENS() { return getToken(CSharpParser.OPEN_PARENS, 0); }
		public TerminalNode CLOSE_PARENS() { return getToken(CSharpParser.CLOSE_PARENS, 0); }
		public Explicit_anonymous_function_parameter_listContext explicit_anonymous_function_parameter_list() {
			return getRuleContext(Explicit_anonymous_function_parameter_listContext.class,0);
		}
		public Implicit_anonymous_function_parameter_listContext implicit_anonymous_function_parameter_list() {
			return getRuleContext(Implicit_anonymous_function_parameter_listContext.class,0);
		}
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public Anonymous_function_signatureContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_anonymous_function_signature; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterAnonymous_function_signature(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitAnonymous_function_signature(this);
		}
	}

	public final Anonymous_function_signatureContext anonymous_function_signature() throws RecognitionException {
		Anonymous_function_signatureContext _localctx = new Anonymous_function_signatureContext(_ctx, getState());
		enterRule(_localctx, 120, RULE_anonymous_function_signature);
		try {
			setState(1133);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,109,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1122);
				match(OPEN_PARENS);
				setState(1123);
				match(CLOSE_PARENS);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1124);
				match(OPEN_PARENS);
				setState(1125);
				explicit_anonymous_function_parameter_list();
				setState(1126);
				match(CLOSE_PARENS);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(1128);
				match(OPEN_PARENS);
				setState(1129);
				implicit_anonymous_function_parameter_list();
				setState(1130);
				match(CLOSE_PARENS);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(1132);
				identifier();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Explicit_anonymous_function_parameter_listContext extends ParserRuleContext {
		public List<Explicit_anonymous_function_parameterContext> explicit_anonymous_function_parameter() {
			return getRuleContexts(Explicit_anonymous_function_parameterContext.class);
		}
		public Explicit_anonymous_function_parameterContext explicit_anonymous_function_parameter(int i) {
			return getRuleContext(Explicit_anonymous_function_parameterContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(CSharpParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(CSharpParser.COMMA, i);
		}
		public Explicit_anonymous_function_parameter_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_explicit_anonymous_function_parameter_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterExplicit_anonymous_function_parameter_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitExplicit_anonymous_function_parameter_list(this);
		}
	}

	public final Explicit_anonymous_function_parameter_listContext explicit_anonymous_function_parameter_list() throws RecognitionException {
		Explicit_anonymous_function_parameter_listContext _localctx = new Explicit_anonymous_function_parameter_listContext(_ctx, getState());
		enterRule(_localctx, 122, RULE_explicit_anonymous_function_parameter_list);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1135);
			explicit_anonymous_function_parameter();
			setState(1140);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(1136);
				match(COMMA);
				setState(1137);
				explicit_anonymous_function_parameter();
				}
				}
				setState(1142);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Explicit_anonymous_function_parameterContext extends ParserRuleContext {
		public Token refout;
		public Type_Context type_() {
			return getRuleContext(Type_Context.class,0);
		}
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TerminalNode REF() { return getToken(CSharpParser.REF, 0); }
		public TerminalNode OUT() { return getToken(CSharpParser.OUT, 0); }
		public TerminalNode IN() { return getToken(CSharpParser.IN, 0); }
		public Explicit_anonymous_function_parameterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_explicit_anonymous_function_parameter; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterExplicit_anonymous_function_parameter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitExplicit_anonymous_function_parameter(this);
		}
	}

	public final Explicit_anonymous_function_parameterContext explicit_anonymous_function_parameter() throws RecognitionException {
		Explicit_anonymous_function_parameterContext _localctx = new Explicit_anonymous_function_parameterContext(_ctx, getState());
		enterRule(_localctx, 124, RULE_explicit_anonymous_function_parameter);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1144);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (((((_la - 55)) & ~0x3f) == 0 && ((1L << (_la - 55)) & ((1L << (IN - 55)) | (1L << (OUT - 55)) | (1L << (REF - 55)))) != 0)) {
				{
				setState(1143);
				((Explicit_anonymous_function_parameterContext)_localctx).refout = _input.LT(1);
				_la = _input.LA(1);
				if ( !(((((_la - 55)) & ~0x3f) == 0 && ((1L << (_la - 55)) & ((1L << (IN - 55)) | (1L << (OUT - 55)) | (1L << (REF - 55)))) != 0)) ) {
					((Explicit_anonymous_function_parameterContext)_localctx).refout = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
			}

			setState(1146);
			type_();
			setState(1147);
			identifier();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Implicit_anonymous_function_parameter_listContext extends ParserRuleContext {
		public List<IdentifierContext> identifier() {
			return getRuleContexts(IdentifierContext.class);
		}
		public IdentifierContext identifier(int i) {
			return getRuleContext(IdentifierContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(CSharpParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(CSharpParser.COMMA, i);
		}
		public Implicit_anonymous_function_parameter_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_implicit_anonymous_function_parameter_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterImplicit_anonymous_function_parameter_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitImplicit_anonymous_function_parameter_list(this);
		}
	}

	public final Implicit_anonymous_function_parameter_listContext implicit_anonymous_function_parameter_list() throws RecognitionException {
		Implicit_anonymous_function_parameter_listContext _localctx = new Implicit_anonymous_function_parameter_listContext(_ctx, getState());
		enterRule(_localctx, 126, RULE_implicit_anonymous_function_parameter_list);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1149);
			identifier();
			setState(1154);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(1150);
				match(COMMA);
				setState(1151);
				identifier();
				}
				}
				setState(1156);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Anonymous_function_bodyContext extends ParserRuleContext {
		public Throwable_expressionContext throwable_expression() {
			return getRuleContext(Throwable_expressionContext.class,0);
		}
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public Anonymous_function_bodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_anonymous_function_body; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterAnonymous_function_body(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitAnonymous_function_body(this);
		}
	}

	public final Anonymous_function_bodyContext anonymous_function_body() throws RecognitionException {
		Anonymous_function_bodyContext _localctx = new Anonymous_function_bodyContext(_ctx, getState());
		enterRule(_localctx, 128, RULE_anonymous_function_body);
		try {
			setState(1159);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ADD:
			case ALIAS:
			case ARGLIST:
			case ASCENDING:
			case ASYNC:
			case AWAIT:
			case BASE:
			case BOOL:
			case BY:
			case BYTE:
			case CHAR:
			case CHECKED:
			case DECIMAL:
			case DEFAULT:
			case DELEGATE:
			case DESCENDING:
			case DOUBLE:
			case DYNAMIC:
			case EQUALS:
			case FALSE:
			case FLOAT:
			case FROM:
			case GET:
			case GROUP:
			case INT:
			case INTO:
			case JOIN:
			case LET:
			case LONG:
			case NAMEOF:
			case NEW:
			case NULL_:
			case OBJECT:
			case ON:
			case ORDERBY:
			case PARTIAL:
			case REF:
			case REMOVE:
			case SBYTE:
			case SELECT:
			case SET:
			case SHORT:
			case SIZEOF:
			case STRING:
			case THIS:
			case THROW:
			case TRUE:
			case TYPEOF:
			case UINT:
			case ULONG:
			case UNCHECKED:
			case UNMANAGED:
			case USHORT:
			case VAR:
			case WHEN:
			case WHERE:
			case YIELD:
			case IDENTIFIER:
			case LITERAL_ACCESS:
			case INTEGER_LITERAL:
			case HEX_INTEGER_LITERAL:
			case BIN_INTEGER_LITERAL:
			case REAL_LITERAL:
			case CHARACTER_LITERAL:
			case REGULAR_STRING:
			case VERBATIUM_STRING:
			case INTERPOLATED_REGULAR_STRING_START:
			case INTERPOLATED_VERBATIUM_STRING_START:
			case OPEN_PARENS:
			case PLUS:
			case MINUS:
			case STAR:
			case AMP:
			case CARET:
			case BANG:
			case TILDE:
			case OP_INC:
			case OP_DEC:
			case OP_RANGE:
				enterOuterAlt(_localctx, 1);
				{
				setState(1157);
				throwable_expression();
				}
				break;
			case OPEN_BRACE:
				enterOuterAlt(_localctx, 2);
				{
				setState(1158);
				block();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Query_expressionContext extends ParserRuleContext {
		public From_clauseContext from_clause() {
			return getRuleContext(From_clauseContext.class,0);
		}
		public Query_bodyContext query_body() {
			return getRuleContext(Query_bodyContext.class,0);
		}
		public Query_expressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_query_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterQuery_expression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitQuery_expression(this);
		}
	}

	public final Query_expressionContext query_expression() throws RecognitionException {
		Query_expressionContext _localctx = new Query_expressionContext(_ctx, getState());
		enterRule(_localctx, 130, RULE_query_expression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1161);
			from_clause();
			setState(1162);
			query_body();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class From_clauseContext extends ParserRuleContext {
		public TerminalNode FROM() { return getToken(CSharpParser.FROM, 0); }
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TerminalNode IN() { return getToken(CSharpParser.IN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public Type_Context type_() {
			return getRuleContext(Type_Context.class,0);
		}
		public From_clauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_from_clause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterFrom_clause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitFrom_clause(this);
		}
	}

	public final From_clauseContext from_clause() throws RecognitionException {
		From_clauseContext _localctx = new From_clauseContext(_ctx, getState());
		enterRule(_localctx, 132, RULE_from_clause);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1164);
			match(FROM);
			setState(1166);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,114,_ctx) ) {
			case 1:
				{
				setState(1165);
				type_();
				}
				break;
			}
			setState(1168);
			identifier();
			setState(1169);
			match(IN);
			setState(1170);
			expression();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Query_bodyContext extends ParserRuleContext {
		public Select_or_group_clauseContext select_or_group_clause() {
			return getRuleContext(Select_or_group_clauseContext.class,0);
		}
		public List<Query_body_clauseContext> query_body_clause() {
			return getRuleContexts(Query_body_clauseContext.class);
		}
		public Query_body_clauseContext query_body_clause(int i) {
			return getRuleContext(Query_body_clauseContext.class,i);
		}
		public Query_continuationContext query_continuation() {
			return getRuleContext(Query_continuationContext.class,0);
		}
		public Query_bodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_query_body; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterQuery_body(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitQuery_body(this);
		}
	}

	public final Query_bodyContext query_body() throws RecognitionException {
		Query_bodyContext _localctx = new Query_bodyContext(_ctx, getState());
		enterRule(_localctx, 134, RULE_query_body);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1175);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << FROM) | (1L << JOIN) | (1L << LET))) != 0) || _la==ORDERBY || _la==WHERE) {
				{
				{
				setState(1172);
				query_body_clause();
				}
				}
				setState(1177);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(1178);
			select_or_group_clause();
			setState(1180);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,116,_ctx) ) {
			case 1:
				{
				setState(1179);
				query_continuation();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Query_body_clauseContext extends ParserRuleContext {
		public From_clauseContext from_clause() {
			return getRuleContext(From_clauseContext.class,0);
		}
		public Let_clauseContext let_clause() {
			return getRuleContext(Let_clauseContext.class,0);
		}
		public Where_clauseContext where_clause() {
			return getRuleContext(Where_clauseContext.class,0);
		}
		public Combined_join_clauseContext combined_join_clause() {
			return getRuleContext(Combined_join_clauseContext.class,0);
		}
		public Orderby_clauseContext orderby_clause() {
			return getRuleContext(Orderby_clauseContext.class,0);
		}
		public Query_body_clauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_query_body_clause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterQuery_body_clause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitQuery_body_clause(this);
		}
	}

	public final Query_body_clauseContext query_body_clause() throws RecognitionException {
		Query_body_clauseContext _localctx = new Query_body_clauseContext(_ctx, getState());
		enterRule(_localctx, 136, RULE_query_body_clause);
		try {
			setState(1187);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case FROM:
				enterOuterAlt(_localctx, 1);
				{
				setState(1182);
				from_clause();
				}
				break;
			case LET:
				enterOuterAlt(_localctx, 2);
				{
				setState(1183);
				let_clause();
				}
				break;
			case WHERE:
				enterOuterAlt(_localctx, 3);
				{
				setState(1184);
				where_clause();
				}
				break;
			case JOIN:
				enterOuterAlt(_localctx, 4);
				{
				setState(1185);
				combined_join_clause();
				}
				break;
			case ORDERBY:
				enterOuterAlt(_localctx, 5);
				{
				setState(1186);
				orderby_clause();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Let_clauseContext extends ParserRuleContext {
		public TerminalNode LET() { return getToken(CSharpParser.LET, 0); }
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TerminalNode ASSIGNMENT() { return getToken(CSharpParser.ASSIGNMENT, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public Let_clauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_let_clause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterLet_clause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitLet_clause(this);
		}
	}

	public final Let_clauseContext let_clause() throws RecognitionException {
		Let_clauseContext _localctx = new Let_clauseContext(_ctx, getState());
		enterRule(_localctx, 138, RULE_let_clause);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1189);
			match(LET);
			setState(1190);
			identifier();
			setState(1191);
			match(ASSIGNMENT);
			setState(1192);
			expression();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Where_clauseContext extends ParserRuleContext {
		public TerminalNode WHERE() { return getToken(CSharpParser.WHERE, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode NOTNULL() { return getToken(CSharpParser.NOTNULL, 0); }
		public Where_clauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_where_clause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterWhere_clause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitWhere_clause(this);
		}
	}

	public final Where_clauseContext where_clause() throws RecognitionException {
		Where_clauseContext _localctx = new Where_clauseContext(_ctx, getState());
		enterRule(_localctx, 140, RULE_where_clause);
		try {
			setState(1198);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,118,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1194);
				match(WHERE);
				setState(1195);
				expression();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1196);
				match(WHERE);
				setState(1197);
				match(NOTNULL);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Combined_join_clauseContext extends ParserRuleContext {
		public TerminalNode JOIN() { return getToken(CSharpParser.JOIN, 0); }
		public List<IdentifierContext> identifier() {
			return getRuleContexts(IdentifierContext.class);
		}
		public IdentifierContext identifier(int i) {
			return getRuleContext(IdentifierContext.class,i);
		}
		public TerminalNode IN() { return getToken(CSharpParser.IN, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode ON() { return getToken(CSharpParser.ON, 0); }
		public TerminalNode EQUALS() { return getToken(CSharpParser.EQUALS, 0); }
		public Type_Context type_() {
			return getRuleContext(Type_Context.class,0);
		}
		public TerminalNode INTO() { return getToken(CSharpParser.INTO, 0); }
		public Combined_join_clauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_combined_join_clause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterCombined_join_clause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitCombined_join_clause(this);
		}
	}

	public final Combined_join_clauseContext combined_join_clause() throws RecognitionException {
		Combined_join_clauseContext _localctx = new Combined_join_clauseContext(_ctx, getState());
		enterRule(_localctx, 142, RULE_combined_join_clause);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1200);
			match(JOIN);
			setState(1202);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,119,_ctx) ) {
			case 1:
				{
				setState(1201);
				type_();
				}
				break;
			}
			setState(1204);
			identifier();
			setState(1205);
			match(IN);
			setState(1206);
			expression();
			setState(1207);
			match(ON);
			setState(1208);
			expression();
			setState(1209);
			match(EQUALS);
			setState(1210);
			expression();
			setState(1213);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==INTO) {
				{
				setState(1211);
				match(INTO);
				setState(1212);
				identifier();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Orderby_clauseContext extends ParserRuleContext {
		public TerminalNode ORDERBY() { return getToken(CSharpParser.ORDERBY, 0); }
		public List<OrderingContext> ordering() {
			return getRuleContexts(OrderingContext.class);
		}
		public OrderingContext ordering(int i) {
			return getRuleContext(OrderingContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(CSharpParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(CSharpParser.COMMA, i);
		}
		public Orderby_clauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_orderby_clause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterOrderby_clause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitOrderby_clause(this);
		}
	}

	public final Orderby_clauseContext orderby_clause() throws RecognitionException {
		Orderby_clauseContext _localctx = new Orderby_clauseContext(_ctx, getState());
		enterRule(_localctx, 144, RULE_orderby_clause);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1215);
			match(ORDERBY);
			setState(1216);
			ordering();
			setState(1221);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(1217);
				match(COMMA);
				setState(1218);
				ordering();
				}
				}
				setState(1223);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OrderingContext extends ParserRuleContext {
		public Token dir;
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode ASCENDING() { return getToken(CSharpParser.ASCENDING, 0); }
		public TerminalNode DESCENDING() { return getToken(CSharpParser.DESCENDING, 0); }
		public OrderingContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ordering; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterOrdering(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitOrdering(this);
		}
	}

	public final OrderingContext ordering() throws RecognitionException {
		OrderingContext _localctx = new OrderingContext(_ctx, getState());
		enterRule(_localctx, 146, RULE_ordering);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1224);
			expression();
			setState(1226);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ASCENDING || _la==DESCENDING) {
				{
				setState(1225);
				((OrderingContext)_localctx).dir = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==ASCENDING || _la==DESCENDING) ) {
					((OrderingContext)_localctx).dir = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Select_or_group_clauseContext extends ParserRuleContext {
		public TerminalNode SELECT() { return getToken(CSharpParser.SELECT, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode GROUP() { return getToken(CSharpParser.GROUP, 0); }
		public TerminalNode BY() { return getToken(CSharpParser.BY, 0); }
		public Select_or_group_clauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_select_or_group_clause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterSelect_or_group_clause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitSelect_or_group_clause(this);
		}
	}

	public final Select_or_group_clauseContext select_or_group_clause() throws RecognitionException {
		Select_or_group_clauseContext _localctx = new Select_or_group_clauseContext(_ctx, getState());
		enterRule(_localctx, 148, RULE_select_or_group_clause);
		try {
			setState(1235);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case SELECT:
				enterOuterAlt(_localctx, 1);
				{
				setState(1228);
				match(SELECT);
				setState(1229);
				expression();
				}
				break;
			case GROUP:
				enterOuterAlt(_localctx, 2);
				{
				setState(1230);
				match(GROUP);
				setState(1231);
				expression();
				setState(1232);
				match(BY);
				setState(1233);
				expression();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Query_continuationContext extends ParserRuleContext {
		public TerminalNode INTO() { return getToken(CSharpParser.INTO, 0); }
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public Query_bodyContext query_body() {
			return getRuleContext(Query_bodyContext.class,0);
		}
		public Query_continuationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_query_continuation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterQuery_continuation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitQuery_continuation(this);
		}
	}

	public final Query_continuationContext query_continuation() throws RecognitionException {
		Query_continuationContext _localctx = new Query_continuationContext(_ctx, getState());
		enterRule(_localctx, 150, RULE_query_continuation);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1237);
			match(INTO);
			setState(1238);
			identifier();
			setState(1239);
			query_body();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StatementContext extends ParserRuleContext {
		public Labeled_StatementContext labeled_Statement() {
			return getRuleContext(Labeled_StatementContext.class,0);
		}
		public DeclarationStatementContext declarationStatement() {
			return getRuleContext(DeclarationStatementContext.class,0);
		}
		public Embedded_statementContext embedded_statement() {
			return getRuleContext(Embedded_statementContext.class,0);
		}
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitStatement(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 152, RULE_statement);
		try {
			setState(1244);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,124,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1241);
				labeled_Statement();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1242);
				declarationStatement();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(1243);
				embedded_statement();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DeclarationStatementContext extends ParserRuleContext {
		public Local_variable_declarationContext local_variable_declaration() {
			return getRuleContext(Local_variable_declarationContext.class,0);
		}
		public TerminalNode SEMICOLON() { return getToken(CSharpParser.SEMICOLON, 0); }
		public Local_constant_declarationContext local_constant_declaration() {
			return getRuleContext(Local_constant_declarationContext.class,0);
		}
		public Local_function_declarationContext local_function_declaration() {
			return getRuleContext(Local_function_declarationContext.class,0);
		}
		public DeclarationStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declarationStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterDeclarationStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitDeclarationStatement(this);
		}
	}

	public final DeclarationStatementContext declarationStatement() throws RecognitionException {
		DeclarationStatementContext _localctx = new DeclarationStatementContext(_ctx, getState());
		enterRule(_localctx, 154, RULE_declarationStatement);
		try {
			setState(1253);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,125,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1246);
				local_variable_declaration();
				setState(1247);
				match(SEMICOLON);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1249);
				local_constant_declaration();
				setState(1250);
				match(SEMICOLON);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(1252);
				local_function_declaration();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Local_function_declarationContext extends ParserRuleContext {
		public Local_function_headerContext local_function_header() {
			return getRuleContext(Local_function_headerContext.class,0);
		}
		public Local_function_bodyContext local_function_body() {
			return getRuleContext(Local_function_bodyContext.class,0);
		}
		public Local_function_declarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_local_function_declaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterLocal_function_declaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitLocal_function_declaration(this);
		}
	}

	public final Local_function_declarationContext local_function_declaration() throws RecognitionException {
		Local_function_declarationContext _localctx = new Local_function_declarationContext(_ctx, getState());
		enterRule(_localctx, 156, RULE_local_function_declaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1255);
			local_function_header();
			setState(1256);
			local_function_body();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Local_function_headerContext extends ParserRuleContext {
		public Return_typeContext return_type() {
			return getRuleContext(Return_typeContext.class,0);
		}
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TerminalNode OPEN_PARENS() { return getToken(CSharpParser.OPEN_PARENS, 0); }
		public TerminalNode CLOSE_PARENS() { return getToken(CSharpParser.CLOSE_PARENS, 0); }
		public Local_function_modifiersContext local_function_modifiers() {
			return getRuleContext(Local_function_modifiersContext.class,0);
		}
		public Type_parameter_listContext type_parameter_list() {
			return getRuleContext(Type_parameter_listContext.class,0);
		}
		public Formal_parameter_listContext formal_parameter_list() {
			return getRuleContext(Formal_parameter_listContext.class,0);
		}
		public Type_parameter_constraints_clausesContext type_parameter_constraints_clauses() {
			return getRuleContext(Type_parameter_constraints_clausesContext.class,0);
		}
		public Local_function_headerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_local_function_header; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterLocal_function_header(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitLocal_function_header(this);
		}
	}

	public final Local_function_headerContext local_function_header() throws RecognitionException {
		Local_function_headerContext _localctx = new Local_function_headerContext(_ctx, getState());
		enterRule(_localctx, 158, RULE_local_function_header);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1259);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,126,_ctx) ) {
			case 1:
				{
				setState(1258);
				local_function_modifiers();
				}
				break;
			}
			setState(1261);
			return_type();
			setState(1262);
			identifier();
			setState(1264);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LT) {
				{
				setState(1263);
				type_parameter_list();
				}
			}

			setState(1266);
			match(OPEN_PARENS);
			setState(1268);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (((((_la - 10)) & ~0x3f) == 0 && ((1L << (_la - 10)) & ((1L << (ADD - 10)) | (1L << (ALIAS - 10)) | (1L << (ARGLIST - 10)) | (1L << (ASCENDING - 10)) | (1L << (ASYNC - 10)) | (1L << (AWAIT - 10)) | (1L << (BOOL - 10)) | (1L << (BY - 10)) | (1L << (BYTE - 10)) | (1L << (CHAR - 10)) | (1L << (DECIMAL - 10)) | (1L << (DESCENDING - 10)) | (1L << (DOUBLE - 10)) | (1L << (DYNAMIC - 10)) | (1L << (EQUALS - 10)) | (1L << (FLOAT - 10)) | (1L << (FROM - 10)) | (1L << (GET - 10)) | (1L << (GROUP - 10)) | (1L << (IN - 10)) | (1L << (INT - 10)) | (1L << (INTO - 10)) | (1L << (JOIN - 10)) | (1L << (LET - 10)) | (1L << (LONG - 10)) | (1L << (NAMEOF - 10)) | (1L << (OBJECT - 10)) | (1L << (ON - 10)))) != 0) || ((((_la - 74)) & ~0x3f) == 0 && ((1L << (_la - 74)) & ((1L << (ORDERBY - 74)) | (1L << (OUT - 74)) | (1L << (PARAMS - 74)) | (1L << (PARTIAL - 74)) | (1L << (REF - 74)) | (1L << (REMOVE - 74)) | (1L << (SBYTE - 74)) | (1L << (SELECT - 74)) | (1L << (SET - 74)) | (1L << (SHORT - 74)) | (1L << (STRING - 74)) | (1L << (THIS - 74)) | (1L << (UINT - 74)) | (1L << (ULONG - 74)) | (1L << (UNMANAGED - 74)) | (1L << (USHORT - 74)) | (1L << (VAR - 74)) | (1L << (VOID - 74)) | (1L << (WHEN - 74)) | (1L << (WHERE - 74)) | (1L << (YIELD - 74)) | (1L << (IDENTIFIER - 74)) | (1L << (OPEN_BRACKET - 74)) | (1L << (OPEN_PARENS - 74)))) != 0)) {
				{
				setState(1267);
				formal_parameter_list();
				}
			}

			setState(1270);
			match(CLOSE_PARENS);
			setState(1272);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==WHERE) {
				{
				setState(1271);
				type_parameter_constraints_clauses();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Local_function_modifiersContext extends ParserRuleContext {
		public TerminalNode ASYNC() { return getToken(CSharpParser.ASYNC, 0); }
		public TerminalNode UNSAFE() { return getToken(CSharpParser.UNSAFE, 0); }
		public TerminalNode STATIC() { return getToken(CSharpParser.STATIC, 0); }
		public Local_function_modifiersContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_local_function_modifiers; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterLocal_function_modifiers(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitLocal_function_modifiers(this);
		}
	}

	public final Local_function_modifiersContext local_function_modifiers() throws RecognitionException {
		Local_function_modifiersContext _localctx = new Local_function_modifiersContext(_ctx, getState());
		enterRule(_localctx, 160, RULE_local_function_modifiers);
		int _la;
		try {
			setState(1282);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ASYNC:
			case UNSAFE:
				enterOuterAlt(_localctx, 1);
				{
				setState(1274);
				_la = _input.LA(1);
				if ( !(_la==ASYNC || _la==UNSAFE) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(1276);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==STATIC) {
					{
					setState(1275);
					match(STATIC);
					}
				}

				}
				break;
			case STATIC:
				enterOuterAlt(_localctx, 2);
				{
				setState(1278);
				match(STATIC);
				setState(1280);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,131,_ctx) ) {
				case 1:
					{
					setState(1279);
					_la = _input.LA(1);
					if ( !(_la==ASYNC || _la==UNSAFE) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					}
					break;
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Local_function_bodyContext extends ParserRuleContext {
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public Right_arrowContext right_arrow() {
			return getRuleContext(Right_arrowContext.class,0);
		}
		public Throwable_expressionContext throwable_expression() {
			return getRuleContext(Throwable_expressionContext.class,0);
		}
		public TerminalNode SEMICOLON() { return getToken(CSharpParser.SEMICOLON, 0); }
		public Local_function_bodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_local_function_body; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterLocal_function_body(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitLocal_function_body(this);
		}
	}

	public final Local_function_bodyContext local_function_body() throws RecognitionException {
		Local_function_bodyContext _localctx = new Local_function_bodyContext(_ctx, getState());
		enterRule(_localctx, 162, RULE_local_function_body);
		try {
			setState(1289);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case OPEN_BRACE:
				enterOuterAlt(_localctx, 1);
				{
				setState(1284);
				block();
				}
				break;
			case ASSIGNMENT:
				enterOuterAlt(_localctx, 2);
				{
				setState(1285);
				right_arrow();
				setState(1286);
				throwable_expression();
				setState(1287);
				match(SEMICOLON);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Labeled_StatementContext extends ParserRuleContext {
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TerminalNode COLON() { return getToken(CSharpParser.COLON, 0); }
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public Labeled_StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_labeled_Statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterLabeled_Statement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitLabeled_Statement(this);
		}
	}

	public final Labeled_StatementContext labeled_Statement() throws RecognitionException {
		Labeled_StatementContext _localctx = new Labeled_StatementContext(_ctx, getState());
		enterRule(_localctx, 164, RULE_labeled_Statement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1291);
			identifier();
			setState(1292);
			match(COLON);
			setState(1293);
			statement();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Embedded_statementContext extends ParserRuleContext {
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public Simple_embedded_statementContext simple_embedded_statement() {
			return getRuleContext(Simple_embedded_statementContext.class,0);
		}
		public Embedded_statementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_embedded_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterEmbedded_statement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitEmbedded_statement(this);
		}
	}

	public final Embedded_statementContext embedded_statement() throws RecognitionException {
		Embedded_statementContext _localctx = new Embedded_statementContext(_ctx, getState());
		enterRule(_localctx, 166, RULE_embedded_statement);
		try {
			setState(1297);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case OPEN_BRACE:
				enterOuterAlt(_localctx, 1);
				{
				setState(1295);
				block();
				}
				break;
			case ADD:
			case ALIAS:
			case ARGLIST:
			case ASCENDING:
			case ASYNC:
			case AWAIT:
			case BASE:
			case BOOL:
			case BREAK:
			case BY:
			case BYTE:
			case CHAR:
			case CHECKED:
			case CONTINUE:
			case DECIMAL:
			case DEFAULT:
			case DELEGATE:
			case DESCENDING:
			case DO:
			case DOUBLE:
			case DYNAMIC:
			case EQUALS:
			case FALSE:
			case FIXED:
			case FLOAT:
			case FOR:
			case FOREACH:
			case FROM:
			case GET:
			case GOTO:
			case GROUP:
			case IF:
			case INT:
			case INTO:
			case JOIN:
			case LET:
			case LOCK:
			case LONG:
			case NAMEOF:
			case NEW:
			case NULL_:
			case OBJECT:
			case ON:
			case ORDERBY:
			case PARTIAL:
			case REF:
			case REMOVE:
			case RETURN:
			case SBYTE:
			case SELECT:
			case SET:
			case SHORT:
			case SIZEOF:
			case STRING:
			case SWITCH:
			case THIS:
			case THROW:
			case TRUE:
			case TRY:
			case TYPEOF:
			case UINT:
			case ULONG:
			case UNCHECKED:
			case UNMANAGED:
			case UNSAFE:
			case USHORT:
			case USING:
			case VAR:
			case WHEN:
			case WHERE:
			case WHILE:
			case YIELD:
			case IDENTIFIER:
			case LITERAL_ACCESS:
			case INTEGER_LITERAL:
			case HEX_INTEGER_LITERAL:
			case BIN_INTEGER_LITERAL:
			case REAL_LITERAL:
			case CHARACTER_LITERAL:
			case REGULAR_STRING:
			case VERBATIUM_STRING:
			case INTERPOLATED_REGULAR_STRING_START:
			case INTERPOLATED_VERBATIUM_STRING_START:
			case OPEN_PARENS:
			case SEMICOLON:
			case PLUS:
			case MINUS:
			case STAR:
			case AMP:
			case CARET:
			case BANG:
			case TILDE:
			case OP_INC:
			case OP_DEC:
			case OP_RANGE:
				enterOuterAlt(_localctx, 2);
				{
				setState(1296);
				simple_embedded_statement();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Simple_embedded_statementContext extends ParserRuleContext {
		public Simple_embedded_statementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_simple_embedded_statement; }
	 
		public Simple_embedded_statementContext() { }
		public void copyFrom(Simple_embedded_statementContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class TryStatementContext extends Simple_embedded_statementContext {
		public TerminalNode TRY() { return getToken(CSharpParser.TRY, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public Catch_clausesContext catch_clauses() {
			return getRuleContext(Catch_clausesContext.class,0);
		}
		public Finally_clauseContext finally_clause() {
			return getRuleContext(Finally_clauseContext.class,0);
		}
		public TryStatementContext(Simple_embedded_statementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterTryStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitTryStatement(this);
		}
	}
	public static class CheckedStatementContext extends Simple_embedded_statementContext {
		public TerminalNode CHECKED() { return getToken(CSharpParser.CHECKED, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public CheckedStatementContext(Simple_embedded_statementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterCheckedStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitCheckedStatement(this);
		}
	}
	public static class ThrowStatementContext extends Simple_embedded_statementContext {
		public TerminalNode THROW() { return getToken(CSharpParser.THROW, 0); }
		public TerminalNode SEMICOLON() { return getToken(CSharpParser.SEMICOLON, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ThrowStatementContext(Simple_embedded_statementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterThrowStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitThrowStatement(this);
		}
	}
	public static class TheEmptyStatementContext extends Simple_embedded_statementContext {
		public TerminalNode SEMICOLON() { return getToken(CSharpParser.SEMICOLON, 0); }
		public TheEmptyStatementContext(Simple_embedded_statementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterTheEmptyStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitTheEmptyStatement(this);
		}
	}
	public static class UnsafeStatementContext extends Simple_embedded_statementContext {
		public TerminalNode UNSAFE() { return getToken(CSharpParser.UNSAFE, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public UnsafeStatementContext(Simple_embedded_statementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterUnsafeStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitUnsafeStatement(this);
		}
	}
	public static class ForStatementContext extends Simple_embedded_statementContext {
		public TerminalNode FOR() { return getToken(CSharpParser.FOR, 0); }
		public TerminalNode OPEN_PARENS() { return getToken(CSharpParser.OPEN_PARENS, 0); }
		public List<TerminalNode> SEMICOLON() { return getTokens(CSharpParser.SEMICOLON); }
		public TerminalNode SEMICOLON(int i) {
			return getToken(CSharpParser.SEMICOLON, i);
		}
		public TerminalNode CLOSE_PARENS() { return getToken(CSharpParser.CLOSE_PARENS, 0); }
		public Embedded_statementContext embedded_statement() {
			return getRuleContext(Embedded_statementContext.class,0);
		}
		public For_initializerContext for_initializer() {
			return getRuleContext(For_initializerContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public For_iteratorContext for_iterator() {
			return getRuleContext(For_iteratorContext.class,0);
		}
		public ForStatementContext(Simple_embedded_statementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterForStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitForStatement(this);
		}
	}
	public static class BreakStatementContext extends Simple_embedded_statementContext {
		public TerminalNode BREAK() { return getToken(CSharpParser.BREAK, 0); }
		public TerminalNode SEMICOLON() { return getToken(CSharpParser.SEMICOLON, 0); }
		public BreakStatementContext(Simple_embedded_statementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterBreakStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitBreakStatement(this);
		}
	}
	public static class IfStatementContext extends Simple_embedded_statementContext {
		public TerminalNode IF() { return getToken(CSharpParser.IF, 0); }
		public TerminalNode OPEN_PARENS() { return getToken(CSharpParser.OPEN_PARENS, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode CLOSE_PARENS() { return getToken(CSharpParser.CLOSE_PARENS, 0); }
		public List<If_bodyContext> if_body() {
			return getRuleContexts(If_bodyContext.class);
		}
		public If_bodyContext if_body(int i) {
			return getRuleContext(If_bodyContext.class,i);
		}
		public TerminalNode ELSE() { return getToken(CSharpParser.ELSE, 0); }
		public IfStatementContext(Simple_embedded_statementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterIfStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitIfStatement(this);
		}
	}
	public static class ReturnStatementContext extends Simple_embedded_statementContext {
		public TerminalNode RETURN() { return getToken(CSharpParser.RETURN, 0); }
		public TerminalNode SEMICOLON() { return getToken(CSharpParser.SEMICOLON, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ReturnStatementContext(Simple_embedded_statementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterReturnStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitReturnStatement(this);
		}
	}
	public static class GotoStatementContext extends Simple_embedded_statementContext {
		public TerminalNode GOTO() { return getToken(CSharpParser.GOTO, 0); }
		public TerminalNode SEMICOLON() { return getToken(CSharpParser.SEMICOLON, 0); }
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TerminalNode CASE() { return getToken(CSharpParser.CASE, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode DEFAULT() { return getToken(CSharpParser.DEFAULT, 0); }
		public GotoStatementContext(Simple_embedded_statementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterGotoStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitGotoStatement(this);
		}
	}
	public static class SwitchStatementContext extends Simple_embedded_statementContext {
		public TerminalNode SWITCH() { return getToken(CSharpParser.SWITCH, 0); }
		public TerminalNode OPEN_PARENS() { return getToken(CSharpParser.OPEN_PARENS, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode CLOSE_PARENS() { return getToken(CSharpParser.CLOSE_PARENS, 0); }
		public TerminalNode OPEN_BRACE() { return getToken(CSharpParser.OPEN_BRACE, 0); }
		public TerminalNode CLOSE_BRACE() { return getToken(CSharpParser.CLOSE_BRACE, 0); }
		public List<Switch_sectionContext> switch_section() {
			return getRuleContexts(Switch_sectionContext.class);
		}
		public Switch_sectionContext switch_section(int i) {
			return getRuleContext(Switch_sectionContext.class,i);
		}
		public SwitchStatementContext(Simple_embedded_statementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterSwitchStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitSwitchStatement(this);
		}
	}
	public static class FixedStatementContext extends Simple_embedded_statementContext {
		public TerminalNode FIXED() { return getToken(CSharpParser.FIXED, 0); }
		public TerminalNode OPEN_PARENS() { return getToken(CSharpParser.OPEN_PARENS, 0); }
		public Pointer_typeContext pointer_type() {
			return getRuleContext(Pointer_typeContext.class,0);
		}
		public Fixed_pointer_declaratorsContext fixed_pointer_declarators() {
			return getRuleContext(Fixed_pointer_declaratorsContext.class,0);
		}
		public TerminalNode CLOSE_PARENS() { return getToken(CSharpParser.CLOSE_PARENS, 0); }
		public Embedded_statementContext embedded_statement() {
			return getRuleContext(Embedded_statementContext.class,0);
		}
		public FixedStatementContext(Simple_embedded_statementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterFixedStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitFixedStatement(this);
		}
	}
	public static class WhileStatementContext extends Simple_embedded_statementContext {
		public TerminalNode WHILE() { return getToken(CSharpParser.WHILE, 0); }
		public TerminalNode OPEN_PARENS() { return getToken(CSharpParser.OPEN_PARENS, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode CLOSE_PARENS() { return getToken(CSharpParser.CLOSE_PARENS, 0); }
		public Embedded_statementContext embedded_statement() {
			return getRuleContext(Embedded_statementContext.class,0);
		}
		public WhileStatementContext(Simple_embedded_statementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterWhileStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitWhileStatement(this);
		}
	}
	public static class DoStatementContext extends Simple_embedded_statementContext {
		public TerminalNode DO() { return getToken(CSharpParser.DO, 0); }
		public Embedded_statementContext embedded_statement() {
			return getRuleContext(Embedded_statementContext.class,0);
		}
		public TerminalNode WHILE() { return getToken(CSharpParser.WHILE, 0); }
		public TerminalNode OPEN_PARENS() { return getToken(CSharpParser.OPEN_PARENS, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode CLOSE_PARENS() { return getToken(CSharpParser.CLOSE_PARENS, 0); }
		public TerminalNode SEMICOLON() { return getToken(CSharpParser.SEMICOLON, 0); }
		public DoStatementContext(Simple_embedded_statementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterDoStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitDoStatement(this);
		}
	}
	public static class ForeachStatementContext extends Simple_embedded_statementContext {
		public TerminalNode FOREACH() { return getToken(CSharpParser.FOREACH, 0); }
		public TerminalNode OPEN_PARENS() { return getToken(CSharpParser.OPEN_PARENS, 0); }
		public Local_variable_typeContext local_variable_type() {
			return getRuleContext(Local_variable_typeContext.class,0);
		}
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TerminalNode IN() { return getToken(CSharpParser.IN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode CLOSE_PARENS() { return getToken(CSharpParser.CLOSE_PARENS, 0); }
		public Embedded_statementContext embedded_statement() {
			return getRuleContext(Embedded_statementContext.class,0);
		}
		public TerminalNode AWAIT() { return getToken(CSharpParser.AWAIT, 0); }
		public ForeachStatementContext(Simple_embedded_statementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterForeachStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitForeachStatement(this);
		}
	}
	public static class UncheckedStatementContext extends Simple_embedded_statementContext {
		public TerminalNode UNCHECKED() { return getToken(CSharpParser.UNCHECKED, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public UncheckedStatementContext(Simple_embedded_statementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterUncheckedStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitUncheckedStatement(this);
		}
	}
	public static class ExpressionStatementContext extends Simple_embedded_statementContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode SEMICOLON() { return getToken(CSharpParser.SEMICOLON, 0); }
		public ExpressionStatementContext(Simple_embedded_statementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterExpressionStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitExpressionStatement(this);
		}
	}
	public static class ContinueStatementContext extends Simple_embedded_statementContext {
		public TerminalNode CONTINUE() { return getToken(CSharpParser.CONTINUE, 0); }
		public TerminalNode SEMICOLON() { return getToken(CSharpParser.SEMICOLON, 0); }
		public ContinueStatementContext(Simple_embedded_statementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterContinueStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitContinueStatement(this);
		}
	}
	public static class UsingStatementContext extends Simple_embedded_statementContext {
		public TerminalNode USING() { return getToken(CSharpParser.USING, 0); }
		public TerminalNode OPEN_PARENS() { return getToken(CSharpParser.OPEN_PARENS, 0); }
		public Resource_acquisitionContext resource_acquisition() {
			return getRuleContext(Resource_acquisitionContext.class,0);
		}
		public TerminalNode CLOSE_PARENS() { return getToken(CSharpParser.CLOSE_PARENS, 0); }
		public Embedded_statementContext embedded_statement() {
			return getRuleContext(Embedded_statementContext.class,0);
		}
		public UsingStatementContext(Simple_embedded_statementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterUsingStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitUsingStatement(this);
		}
	}
	public static class LockStatementContext extends Simple_embedded_statementContext {
		public TerminalNode LOCK() { return getToken(CSharpParser.LOCK, 0); }
		public TerminalNode OPEN_PARENS() { return getToken(CSharpParser.OPEN_PARENS, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode CLOSE_PARENS() { return getToken(CSharpParser.CLOSE_PARENS, 0); }
		public Embedded_statementContext embedded_statement() {
			return getRuleContext(Embedded_statementContext.class,0);
		}
		public LockStatementContext(Simple_embedded_statementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterLockStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitLockStatement(this);
		}
	}
	public static class YieldStatementContext extends Simple_embedded_statementContext {
		public TerminalNode YIELD() { return getToken(CSharpParser.YIELD, 0); }
		public TerminalNode SEMICOLON() { return getToken(CSharpParser.SEMICOLON, 0); }
		public TerminalNode RETURN() { return getToken(CSharpParser.RETURN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode BREAK() { return getToken(CSharpParser.BREAK, 0); }
		public YieldStatementContext(Simple_embedded_statementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterYieldStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitYieldStatement(this);
		}
	}

	public final Simple_embedded_statementContext simple_embedded_statement() throws RecognitionException {
		Simple_embedded_statementContext _localctx = new Simple_embedded_statementContext(_ctx, getState());
		enterRule(_localctx, 168, RULE_simple_embedded_statement);
		int _la;
		try {
			setState(1429);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,147,_ctx) ) {
			case 1:
				_localctx = new TheEmptyStatementContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(1299);
				match(SEMICOLON);
				}
				break;
			case 2:
				_localctx = new ExpressionStatementContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(1300);
				expression();
				setState(1301);
				match(SEMICOLON);
				}
				break;
			case 3:
				_localctx = new IfStatementContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(1303);
				match(IF);
				setState(1304);
				match(OPEN_PARENS);
				setState(1305);
				expression();
				setState(1306);
				match(CLOSE_PARENS);
				setState(1307);
				if_body();
				setState(1310);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,135,_ctx) ) {
				case 1:
					{
					setState(1308);
					match(ELSE);
					setState(1309);
					if_body();
					}
					break;
				}
				}
				break;
			case 4:
				_localctx = new SwitchStatementContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(1312);
				match(SWITCH);
				setState(1313);
				match(OPEN_PARENS);
				setState(1314);
				expression();
				setState(1315);
				match(CLOSE_PARENS);
				setState(1316);
				match(OPEN_BRACE);
				setState(1320);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==CASE || _la==DEFAULT) {
					{
					{
					setState(1317);
					switch_section();
					}
					}
					setState(1322);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(1323);
				match(CLOSE_BRACE);
				}
				break;
			case 5:
				_localctx = new WhileStatementContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(1325);
				match(WHILE);
				setState(1326);
				match(OPEN_PARENS);
				setState(1327);
				expression();
				setState(1328);
				match(CLOSE_PARENS);
				setState(1329);
				embedded_statement();
				}
				break;
			case 6:
				_localctx = new DoStatementContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(1331);
				match(DO);
				setState(1332);
				embedded_statement();
				setState(1333);
				match(WHILE);
				setState(1334);
				match(OPEN_PARENS);
				setState(1335);
				expression();
				setState(1336);
				match(CLOSE_PARENS);
				setState(1337);
				match(SEMICOLON);
				}
				break;
			case 7:
				_localctx = new ForStatementContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(1339);
				match(FOR);
				setState(1340);
				match(OPEN_PARENS);
				setState(1342);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ADD) | (1L << ALIAS) | (1L << ARGLIST) | (1L << ASCENDING) | (1L << ASYNC) | (1L << AWAIT) | (1L << BASE) | (1L << BOOL) | (1L << BY) | (1L << BYTE) | (1L << CHAR) | (1L << CHECKED) | (1L << DECIMAL) | (1L << DEFAULT) | (1L << DELEGATE) | (1L << DESCENDING) | (1L << DOUBLE) | (1L << DYNAMIC) | (1L << EQUALS) | (1L << FALSE) | (1L << FIXED) | (1L << FLOAT) | (1L << FROM) | (1L << GET) | (1L << GROUP) | (1L << INT) | (1L << INTO) | (1L << JOIN) | (1L << LET))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (LONG - 64)) | (1L << (NAMEOF - 64)) | (1L << (NEW - 64)) | (1L << (NULL_ - 64)) | (1L << (OBJECT - 64)) | (1L << (ON - 64)) | (1L << (ORDERBY - 64)) | (1L << (PARTIAL - 64)) | (1L << (REF - 64)) | (1L << (REMOVE - 64)) | (1L << (SBYTE - 64)) | (1L << (SELECT - 64)) | (1L << (SET - 64)) | (1L << (SHORT - 64)) | (1L << (SIZEOF - 64)) | (1L << (STRING - 64)) | (1L << (THIS - 64)) | (1L << (TRUE - 64)) | (1L << (TYPEOF - 64)) | (1L << (UINT - 64)) | (1L << (ULONG - 64)) | (1L << (UNCHECKED - 64)) | (1L << (UNMANAGED - 64)) | (1L << (USHORT - 64)) | (1L << (USING - 64)) | (1L << (VAR - 64)) | (1L << (VOID - 64)) | (1L << (WHEN - 64)) | (1L << (WHERE - 64)) | (1L << (YIELD - 64)) | (1L << (IDENTIFIER - 64)) | (1L << (LITERAL_ACCESS - 64)) | (1L << (INTEGER_LITERAL - 64)) | (1L << (HEX_INTEGER_LITERAL - 64)) | (1L << (BIN_INTEGER_LITERAL - 64)) | (1L << (REAL_LITERAL - 64)) | (1L << (CHARACTER_LITERAL - 64)) | (1L << (REGULAR_STRING - 64)) | (1L << (VERBATIUM_STRING - 64)) | (1L << (INTERPOLATED_REGULAR_STRING_START - 64)))) != 0) || ((((_la - 128)) & ~0x3f) == 0 && ((1L << (_la - 128)) & ((1L << (INTERPOLATED_VERBATIUM_STRING_START - 128)) | (1L << (OPEN_PARENS - 128)) | (1L << (PLUS - 128)) | (1L << (MINUS - 128)) | (1L << (STAR - 128)) | (1L << (AMP - 128)) | (1L << (CARET - 128)) | (1L << (BANG - 128)) | (1L << (TILDE - 128)) | (1L << (OP_INC - 128)) | (1L << (OP_DEC - 128)) | (1L << (OP_RANGE - 128)))) != 0)) {
					{
					setState(1341);
					for_initializer();
					}
				}

				setState(1344);
				match(SEMICOLON);
				setState(1346);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ADD) | (1L << ALIAS) | (1L << ARGLIST) | (1L << ASCENDING) | (1L << ASYNC) | (1L << AWAIT) | (1L << BASE) | (1L << BOOL) | (1L << BY) | (1L << BYTE) | (1L << CHAR) | (1L << CHECKED) | (1L << DECIMAL) | (1L << DEFAULT) | (1L << DELEGATE) | (1L << DESCENDING) | (1L << DOUBLE) | (1L << DYNAMIC) | (1L << EQUALS) | (1L << FALSE) | (1L << FLOAT) | (1L << FROM) | (1L << GET) | (1L << GROUP) | (1L << INT) | (1L << INTO) | (1L << JOIN) | (1L << LET))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (LONG - 64)) | (1L << (NAMEOF - 64)) | (1L << (NEW - 64)) | (1L << (NULL_ - 64)) | (1L << (OBJECT - 64)) | (1L << (ON - 64)) | (1L << (ORDERBY - 64)) | (1L << (PARTIAL - 64)) | (1L << (REF - 64)) | (1L << (REMOVE - 64)) | (1L << (SBYTE - 64)) | (1L << (SELECT - 64)) | (1L << (SET - 64)) | (1L << (SHORT - 64)) | (1L << (SIZEOF - 64)) | (1L << (STRING - 64)) | (1L << (THIS - 64)) | (1L << (TRUE - 64)) | (1L << (TYPEOF - 64)) | (1L << (UINT - 64)) | (1L << (ULONG - 64)) | (1L << (UNCHECKED - 64)) | (1L << (UNMANAGED - 64)) | (1L << (USHORT - 64)) | (1L << (VAR - 64)) | (1L << (WHEN - 64)) | (1L << (WHERE - 64)) | (1L << (YIELD - 64)) | (1L << (IDENTIFIER - 64)) | (1L << (LITERAL_ACCESS - 64)) | (1L << (INTEGER_LITERAL - 64)) | (1L << (HEX_INTEGER_LITERAL - 64)) | (1L << (BIN_INTEGER_LITERAL - 64)) | (1L << (REAL_LITERAL - 64)) | (1L << (CHARACTER_LITERAL - 64)) | (1L << (REGULAR_STRING - 64)) | (1L << (VERBATIUM_STRING - 64)) | (1L << (INTERPOLATED_REGULAR_STRING_START - 64)))) != 0) || ((((_la - 128)) & ~0x3f) == 0 && ((1L << (_la - 128)) & ((1L << (INTERPOLATED_VERBATIUM_STRING_START - 128)) | (1L << (OPEN_PARENS - 128)) | (1L << (PLUS - 128)) | (1L << (MINUS - 128)) | (1L << (STAR - 128)) | (1L << (AMP - 128)) | (1L << (CARET - 128)) | (1L << (BANG - 128)) | (1L << (TILDE - 128)) | (1L << (OP_INC - 128)) | (1L << (OP_DEC - 128)) | (1L << (OP_RANGE - 128)))) != 0)) {
					{
					setState(1345);
					expression();
					}
				}

				setState(1348);
				match(SEMICOLON);
				setState(1350);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ADD) | (1L << ALIAS) | (1L << ARGLIST) | (1L << ASCENDING) | (1L << ASYNC) | (1L << AWAIT) | (1L << BASE) | (1L << BOOL) | (1L << BY) | (1L << BYTE) | (1L << CHAR) | (1L << CHECKED) | (1L << DECIMAL) | (1L << DEFAULT) | (1L << DELEGATE) | (1L << DESCENDING) | (1L << DOUBLE) | (1L << DYNAMIC) | (1L << EQUALS) | (1L << FALSE) | (1L << FLOAT) | (1L << FROM) | (1L << GET) | (1L << GROUP) | (1L << INT) | (1L << INTO) | (1L << JOIN) | (1L << LET))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (LONG - 64)) | (1L << (NAMEOF - 64)) | (1L << (NEW - 64)) | (1L << (NULL_ - 64)) | (1L << (OBJECT - 64)) | (1L << (ON - 64)) | (1L << (ORDERBY - 64)) | (1L << (PARTIAL - 64)) | (1L << (REF - 64)) | (1L << (REMOVE - 64)) | (1L << (SBYTE - 64)) | (1L << (SELECT - 64)) | (1L << (SET - 64)) | (1L << (SHORT - 64)) | (1L << (SIZEOF - 64)) | (1L << (STRING - 64)) | (1L << (THIS - 64)) | (1L << (TRUE - 64)) | (1L << (TYPEOF - 64)) | (1L << (UINT - 64)) | (1L << (ULONG - 64)) | (1L << (UNCHECKED - 64)) | (1L << (UNMANAGED - 64)) | (1L << (USHORT - 64)) | (1L << (VAR - 64)) | (1L << (WHEN - 64)) | (1L << (WHERE - 64)) | (1L << (YIELD - 64)) | (1L << (IDENTIFIER - 64)) | (1L << (LITERAL_ACCESS - 64)) | (1L << (INTEGER_LITERAL - 64)) | (1L << (HEX_INTEGER_LITERAL - 64)) | (1L << (BIN_INTEGER_LITERAL - 64)) | (1L << (REAL_LITERAL - 64)) | (1L << (CHARACTER_LITERAL - 64)) | (1L << (REGULAR_STRING - 64)) | (1L << (VERBATIUM_STRING - 64)) | (1L << (INTERPOLATED_REGULAR_STRING_START - 64)))) != 0) || ((((_la - 128)) & ~0x3f) == 0 && ((1L << (_la - 128)) & ((1L << (INTERPOLATED_VERBATIUM_STRING_START - 128)) | (1L << (OPEN_PARENS - 128)) | (1L << (PLUS - 128)) | (1L << (MINUS - 128)) | (1L << (STAR - 128)) | (1L << (AMP - 128)) | (1L << (CARET - 128)) | (1L << (BANG - 128)) | (1L << (TILDE - 128)) | (1L << (OP_INC - 128)) | (1L << (OP_DEC - 128)) | (1L << (OP_RANGE - 128)))) != 0)) {
					{
					setState(1349);
					for_iterator();
					}
				}

				setState(1352);
				match(CLOSE_PARENS);
				setState(1353);
				embedded_statement();
				}
				break;
			case 8:
				_localctx = new ForeachStatementContext(_localctx);
				enterOuterAlt(_localctx, 8);
				{
				setState(1355);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==AWAIT) {
					{
					setState(1354);
					match(AWAIT);
					}
				}

				setState(1357);
				match(FOREACH);
				setState(1358);
				match(OPEN_PARENS);
				setState(1359);
				local_variable_type();
				setState(1360);
				identifier();
				setState(1361);
				match(IN);
				setState(1362);
				expression();
				setState(1363);
				match(CLOSE_PARENS);
				setState(1364);
				embedded_statement();
				}
				break;
			case 9:
				_localctx = new BreakStatementContext(_localctx);
				enterOuterAlt(_localctx, 9);
				{
				setState(1366);
				match(BREAK);
				setState(1367);
				match(SEMICOLON);
				}
				break;
			case 10:
				_localctx = new ContinueStatementContext(_localctx);
				enterOuterAlt(_localctx, 10);
				{
				setState(1368);
				match(CONTINUE);
				setState(1369);
				match(SEMICOLON);
				}
				break;
			case 11:
				_localctx = new GotoStatementContext(_localctx);
				enterOuterAlt(_localctx, 11);
				{
				setState(1370);
				match(GOTO);
				setState(1375);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case ADD:
				case ALIAS:
				case ARGLIST:
				case ASCENDING:
				case ASYNC:
				case AWAIT:
				case BY:
				case DESCENDING:
				case DYNAMIC:
				case EQUALS:
				case FROM:
				case GET:
				case GROUP:
				case INTO:
				case JOIN:
				case LET:
				case NAMEOF:
				case ON:
				case ORDERBY:
				case PARTIAL:
				case REMOVE:
				case SELECT:
				case SET:
				case UNMANAGED:
				case VAR:
				case WHEN:
				case WHERE:
				case YIELD:
				case IDENTIFIER:
					{
					setState(1371);
					identifier();
					}
					break;
				case CASE:
					{
					setState(1372);
					match(CASE);
					setState(1373);
					expression();
					}
					break;
				case DEFAULT:
					{
					setState(1374);
					match(DEFAULT);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(1377);
				match(SEMICOLON);
				}
				break;
			case 12:
				_localctx = new ReturnStatementContext(_localctx);
				enterOuterAlt(_localctx, 12);
				{
				setState(1378);
				match(RETURN);
				setState(1380);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ADD) | (1L << ALIAS) | (1L << ARGLIST) | (1L << ASCENDING) | (1L << ASYNC) | (1L << AWAIT) | (1L << BASE) | (1L << BOOL) | (1L << BY) | (1L << BYTE) | (1L << CHAR) | (1L << CHECKED) | (1L << DECIMAL) | (1L << DEFAULT) | (1L << DELEGATE) | (1L << DESCENDING) | (1L << DOUBLE) | (1L << DYNAMIC) | (1L << EQUALS) | (1L << FALSE) | (1L << FLOAT) | (1L << FROM) | (1L << GET) | (1L << GROUP) | (1L << INT) | (1L << INTO) | (1L << JOIN) | (1L << LET))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (LONG - 64)) | (1L << (NAMEOF - 64)) | (1L << (NEW - 64)) | (1L << (NULL_ - 64)) | (1L << (OBJECT - 64)) | (1L << (ON - 64)) | (1L << (ORDERBY - 64)) | (1L << (PARTIAL - 64)) | (1L << (REF - 64)) | (1L << (REMOVE - 64)) | (1L << (SBYTE - 64)) | (1L << (SELECT - 64)) | (1L << (SET - 64)) | (1L << (SHORT - 64)) | (1L << (SIZEOF - 64)) | (1L << (STRING - 64)) | (1L << (THIS - 64)) | (1L << (TRUE - 64)) | (1L << (TYPEOF - 64)) | (1L << (UINT - 64)) | (1L << (ULONG - 64)) | (1L << (UNCHECKED - 64)) | (1L << (UNMANAGED - 64)) | (1L << (USHORT - 64)) | (1L << (VAR - 64)) | (1L << (WHEN - 64)) | (1L << (WHERE - 64)) | (1L << (YIELD - 64)) | (1L << (IDENTIFIER - 64)) | (1L << (LITERAL_ACCESS - 64)) | (1L << (INTEGER_LITERAL - 64)) | (1L << (HEX_INTEGER_LITERAL - 64)) | (1L << (BIN_INTEGER_LITERAL - 64)) | (1L << (REAL_LITERAL - 64)) | (1L << (CHARACTER_LITERAL - 64)) | (1L << (REGULAR_STRING - 64)) | (1L << (VERBATIUM_STRING - 64)) | (1L << (INTERPOLATED_REGULAR_STRING_START - 64)))) != 0) || ((((_la - 128)) & ~0x3f) == 0 && ((1L << (_la - 128)) & ((1L << (INTERPOLATED_VERBATIUM_STRING_START - 128)) | (1L << (OPEN_PARENS - 128)) | (1L << (PLUS - 128)) | (1L << (MINUS - 128)) | (1L << (STAR - 128)) | (1L << (AMP - 128)) | (1L << (CARET - 128)) | (1L << (BANG - 128)) | (1L << (TILDE - 128)) | (1L << (OP_INC - 128)) | (1L << (OP_DEC - 128)) | (1L << (OP_RANGE - 128)))) != 0)) {
					{
					setState(1379);
					expression();
					}
				}

				setState(1382);
				match(SEMICOLON);
				}
				break;
			case 13:
				_localctx = new ThrowStatementContext(_localctx);
				enterOuterAlt(_localctx, 13);
				{
				setState(1383);
				match(THROW);
				setState(1385);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ADD) | (1L << ALIAS) | (1L << ARGLIST) | (1L << ASCENDING) | (1L << ASYNC) | (1L << AWAIT) | (1L << BASE) | (1L << BOOL) | (1L << BY) | (1L << BYTE) | (1L << CHAR) | (1L << CHECKED) | (1L << DECIMAL) | (1L << DEFAULT) | (1L << DELEGATE) | (1L << DESCENDING) | (1L << DOUBLE) | (1L << DYNAMIC) | (1L << EQUALS) | (1L << FALSE) | (1L << FLOAT) | (1L << FROM) | (1L << GET) | (1L << GROUP) | (1L << INT) | (1L << INTO) | (1L << JOIN) | (1L << LET))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (LONG - 64)) | (1L << (NAMEOF - 64)) | (1L << (NEW - 64)) | (1L << (NULL_ - 64)) | (1L << (OBJECT - 64)) | (1L << (ON - 64)) | (1L << (ORDERBY - 64)) | (1L << (PARTIAL - 64)) | (1L << (REF - 64)) | (1L << (REMOVE - 64)) | (1L << (SBYTE - 64)) | (1L << (SELECT - 64)) | (1L << (SET - 64)) | (1L << (SHORT - 64)) | (1L << (SIZEOF - 64)) | (1L << (STRING - 64)) | (1L << (THIS - 64)) | (1L << (TRUE - 64)) | (1L << (TYPEOF - 64)) | (1L << (UINT - 64)) | (1L << (ULONG - 64)) | (1L << (UNCHECKED - 64)) | (1L << (UNMANAGED - 64)) | (1L << (USHORT - 64)) | (1L << (VAR - 64)) | (1L << (WHEN - 64)) | (1L << (WHERE - 64)) | (1L << (YIELD - 64)) | (1L << (IDENTIFIER - 64)) | (1L << (LITERAL_ACCESS - 64)) | (1L << (INTEGER_LITERAL - 64)) | (1L << (HEX_INTEGER_LITERAL - 64)) | (1L << (BIN_INTEGER_LITERAL - 64)) | (1L << (REAL_LITERAL - 64)) | (1L << (CHARACTER_LITERAL - 64)) | (1L << (REGULAR_STRING - 64)) | (1L << (VERBATIUM_STRING - 64)) | (1L << (INTERPOLATED_REGULAR_STRING_START - 64)))) != 0) || ((((_la - 128)) & ~0x3f) == 0 && ((1L << (_la - 128)) & ((1L << (INTERPOLATED_VERBATIUM_STRING_START - 128)) | (1L << (OPEN_PARENS - 128)) | (1L << (PLUS - 128)) | (1L << (MINUS - 128)) | (1L << (STAR - 128)) | (1L << (AMP - 128)) | (1L << (CARET - 128)) | (1L << (BANG - 128)) | (1L << (TILDE - 128)) | (1L << (OP_INC - 128)) | (1L << (OP_DEC - 128)) | (1L << (OP_RANGE - 128)))) != 0)) {
					{
					setState(1384);
					expression();
					}
				}

				setState(1387);
				match(SEMICOLON);
				}
				break;
			case 14:
				_localctx = new TryStatementContext(_localctx);
				enterOuterAlt(_localctx, 14);
				{
				setState(1388);
				match(TRY);
				setState(1389);
				block();
				setState(1395);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case CATCH:
					{
					setState(1390);
					catch_clauses();
					setState(1392);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==FINALLY) {
						{
						setState(1391);
						finally_clause();
						}
					}

					}
					break;
				case FINALLY:
					{
					setState(1394);
					finally_clause();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				break;
			case 15:
				_localctx = new CheckedStatementContext(_localctx);
				enterOuterAlt(_localctx, 15);
				{
				setState(1397);
				match(CHECKED);
				setState(1398);
				block();
				}
				break;
			case 16:
				_localctx = new UncheckedStatementContext(_localctx);
				enterOuterAlt(_localctx, 16);
				{
				setState(1399);
				match(UNCHECKED);
				setState(1400);
				block();
				}
				break;
			case 17:
				_localctx = new LockStatementContext(_localctx);
				enterOuterAlt(_localctx, 17);
				{
				setState(1401);
				match(LOCK);
				setState(1402);
				match(OPEN_PARENS);
				setState(1403);
				expression();
				setState(1404);
				match(CLOSE_PARENS);
				setState(1405);
				embedded_statement();
				}
				break;
			case 18:
				_localctx = new UsingStatementContext(_localctx);
				enterOuterAlt(_localctx, 18);
				{
				setState(1407);
				match(USING);
				setState(1408);
				match(OPEN_PARENS);
				setState(1409);
				resource_acquisition();
				setState(1410);
				match(CLOSE_PARENS);
				setState(1411);
				embedded_statement();
				}
				break;
			case 19:
				_localctx = new YieldStatementContext(_localctx);
				enterOuterAlt(_localctx, 19);
				{
				setState(1413);
				match(YIELD);
				setState(1417);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case RETURN:
					{
					setState(1414);
					match(RETURN);
					setState(1415);
					expression();
					}
					break;
				case BREAK:
					{
					setState(1416);
					match(BREAK);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(1419);
				match(SEMICOLON);
				}
				break;
			case 20:
				_localctx = new UnsafeStatementContext(_localctx);
				enterOuterAlt(_localctx, 20);
				{
				setState(1420);
				match(UNSAFE);
				setState(1421);
				block();
				}
				break;
			case 21:
				_localctx = new FixedStatementContext(_localctx);
				enterOuterAlt(_localctx, 21);
				{
				setState(1422);
				match(FIXED);
				setState(1423);
				match(OPEN_PARENS);
				setState(1424);
				pointer_type();
				setState(1425);
				fixed_pointer_declarators();
				setState(1426);
				match(CLOSE_PARENS);
				setState(1427);
				embedded_statement();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BlockContext extends ParserRuleContext {
		public TerminalNode OPEN_BRACE() { return getToken(CSharpParser.OPEN_BRACE, 0); }
		public TerminalNode CLOSE_BRACE() { return getToken(CSharpParser.CLOSE_BRACE, 0); }
		public Statement_listContext statement_list() {
			return getRuleContext(Statement_listContext.class,0);
		}
		public BlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_block; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitBlock(this);
		}
	}

	public final BlockContext block() throws RecognitionException {
		BlockContext _localctx = new BlockContext(_ctx, getState());
		enterRule(_localctx, 170, RULE_block);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1431);
			match(OPEN_BRACE);
			setState(1433);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ADD) | (1L << ALIAS) | (1L << ARGLIST) | (1L << ASCENDING) | (1L << ASYNC) | (1L << AWAIT) | (1L << BASE) | (1L << BOOL) | (1L << BREAK) | (1L << BY) | (1L << BYTE) | (1L << CHAR) | (1L << CHECKED) | (1L << CONST) | (1L << CONTINUE) | (1L << DECIMAL) | (1L << DEFAULT) | (1L << DELEGATE) | (1L << DESCENDING) | (1L << DO) | (1L << DOUBLE) | (1L << DYNAMIC) | (1L << EQUALS) | (1L << FALSE) | (1L << FIXED) | (1L << FLOAT) | (1L << FOR) | (1L << FOREACH) | (1L << FROM) | (1L << GET) | (1L << GOTO) | (1L << GROUP) | (1L << IF) | (1L << INT) | (1L << INTO) | (1L << JOIN) | (1L << LET) | (1L << LOCK))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (LONG - 64)) | (1L << (NAMEOF - 64)) | (1L << (NEW - 64)) | (1L << (NULL_ - 64)) | (1L << (OBJECT - 64)) | (1L << (ON - 64)) | (1L << (ORDERBY - 64)) | (1L << (PARTIAL - 64)) | (1L << (REF - 64)) | (1L << (REMOVE - 64)) | (1L << (RETURN - 64)) | (1L << (SBYTE - 64)) | (1L << (SELECT - 64)) | (1L << (SET - 64)) | (1L << (SHORT - 64)) | (1L << (SIZEOF - 64)) | (1L << (STATIC - 64)) | (1L << (STRING - 64)) | (1L << (SWITCH - 64)) | (1L << (THIS - 64)) | (1L << (THROW - 64)) | (1L << (TRUE - 64)) | (1L << (TRY - 64)) | (1L << (TYPEOF - 64)) | (1L << (UINT - 64)) | (1L << (ULONG - 64)) | (1L << (UNCHECKED - 64)) | (1L << (UNMANAGED - 64)) | (1L << (UNSAFE - 64)) | (1L << (USHORT - 64)) | (1L << (USING - 64)) | (1L << (VAR - 64)) | (1L << (VOID - 64)) | (1L << (WHEN - 64)) | (1L << (WHERE - 64)) | (1L << (WHILE - 64)) | (1L << (YIELD - 64)) | (1L << (IDENTIFIER - 64)) | (1L << (LITERAL_ACCESS - 64)) | (1L << (INTEGER_LITERAL - 64)) | (1L << (HEX_INTEGER_LITERAL - 64)) | (1L << (BIN_INTEGER_LITERAL - 64)) | (1L << (REAL_LITERAL - 64)) | (1L << (CHARACTER_LITERAL - 64)) | (1L << (REGULAR_STRING - 64)) | (1L << (VERBATIUM_STRING - 64)) | (1L << (INTERPOLATED_REGULAR_STRING_START - 64)))) != 0) || ((((_la - 128)) & ~0x3f) == 0 && ((1L << (_la - 128)) & ((1L << (INTERPOLATED_VERBATIUM_STRING_START - 128)) | (1L << (OPEN_BRACE - 128)) | (1L << (OPEN_PARENS - 128)) | (1L << (SEMICOLON - 128)) | (1L << (PLUS - 128)) | (1L << (MINUS - 128)) | (1L << (STAR - 128)) | (1L << (AMP - 128)) | (1L << (CARET - 128)) | (1L << (BANG - 128)) | (1L << (TILDE - 128)) | (1L << (OP_INC - 128)) | (1L << (OP_DEC - 128)) | (1L << (OP_RANGE - 128)))) != 0)) {
				{
				setState(1432);
				statement_list();
				}
			}

			setState(1435);
			match(CLOSE_BRACE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Local_variable_declarationContext extends ParserRuleContext {
		public Local_variable_typeContext local_variable_type() {
			return getRuleContext(Local_variable_typeContext.class,0);
		}
		public List<Local_variable_declaratorContext> local_variable_declarator() {
			return getRuleContexts(Local_variable_declaratorContext.class);
		}
		public Local_variable_declaratorContext local_variable_declarator(int i) {
			return getRuleContext(Local_variable_declaratorContext.class,i);
		}
		public TerminalNode USING() { return getToken(CSharpParser.USING, 0); }
		public TerminalNode REF() { return getToken(CSharpParser.REF, 0); }
		public TerminalNode READONLY() { return getToken(CSharpParser.READONLY, 0); }
		public List<TerminalNode> COMMA() { return getTokens(CSharpParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(CSharpParser.COMMA, i);
		}
		public TerminalNode FIXED() { return getToken(CSharpParser.FIXED, 0); }
		public Pointer_typeContext pointer_type() {
			return getRuleContext(Pointer_typeContext.class,0);
		}
		public Fixed_pointer_declaratorsContext fixed_pointer_declarators() {
			return getRuleContext(Fixed_pointer_declaratorsContext.class,0);
		}
		public Local_variable_declarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_local_variable_declaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterLocal_variable_declaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitLocal_variable_declaration(this);
		}
	}

	public final Local_variable_declarationContext local_variable_declaration() throws RecognitionException {
		Local_variable_declarationContext _localctx = new Local_variable_declarationContext(_ctx, getState());
		enterRule(_localctx, 172, RULE_local_variable_declaration);
		int _la;
		try {
			setState(1456);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ADD:
			case ALIAS:
			case ARGLIST:
			case ASCENDING:
			case ASYNC:
			case AWAIT:
			case BOOL:
			case BY:
			case BYTE:
			case CHAR:
			case DECIMAL:
			case DESCENDING:
			case DOUBLE:
			case DYNAMIC:
			case EQUALS:
			case FLOAT:
			case FROM:
			case GET:
			case GROUP:
			case INT:
			case INTO:
			case JOIN:
			case LET:
			case LONG:
			case NAMEOF:
			case OBJECT:
			case ON:
			case ORDERBY:
			case PARTIAL:
			case REF:
			case REMOVE:
			case SBYTE:
			case SELECT:
			case SET:
			case SHORT:
			case STRING:
			case UINT:
			case ULONG:
			case UNMANAGED:
			case USHORT:
			case USING:
			case VAR:
			case VOID:
			case WHEN:
			case WHERE:
			case YIELD:
			case IDENTIFIER:
			case OPEN_PARENS:
				enterOuterAlt(_localctx, 1);
				{
				setState(1441);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,149,_ctx) ) {
				case 1:
					{
					setState(1437);
					match(USING);
					}
					break;
				case 2:
					{
					setState(1438);
					match(REF);
					}
					break;
				case 3:
					{
					setState(1439);
					match(REF);
					setState(1440);
					match(READONLY);
					}
					break;
				}
				setState(1443);
				local_variable_type();
				setState(1444);
				local_variable_declarator();
				setState(1449);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(1445);
					match(COMMA);
					setState(1446);
					local_variable_declarator();
					}
					}
					setState(1451);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case FIXED:
				enterOuterAlt(_localctx, 2);
				{
				setState(1452);
				match(FIXED);
				setState(1453);
				pointer_type();
				setState(1454);
				fixed_pointer_declarators();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Local_variable_typeContext extends ParserRuleContext {
		public TerminalNode VAR() { return getToken(CSharpParser.VAR, 0); }
		public Type_Context type_() {
			return getRuleContext(Type_Context.class,0);
		}
		public Local_variable_typeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_local_variable_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterLocal_variable_type(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitLocal_variable_type(this);
		}
	}

	public final Local_variable_typeContext local_variable_type() throws RecognitionException {
		Local_variable_typeContext _localctx = new Local_variable_typeContext(_ctx, getState());
		enterRule(_localctx, 174, RULE_local_variable_type);
		try {
			setState(1460);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,152,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1458);
				match(VAR);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1459);
				type_();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Local_variable_declaratorContext extends ParserRuleContext {
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TerminalNode ASSIGNMENT() { return getToken(CSharpParser.ASSIGNMENT, 0); }
		public Local_variable_initializerContext local_variable_initializer() {
			return getRuleContext(Local_variable_initializerContext.class,0);
		}
		public TerminalNode REF() { return getToken(CSharpParser.REF, 0); }
		public Local_variable_declaratorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_local_variable_declarator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterLocal_variable_declarator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitLocal_variable_declarator(this);
		}
	}

	public final Local_variable_declaratorContext local_variable_declarator() throws RecognitionException {
		Local_variable_declaratorContext _localctx = new Local_variable_declaratorContext(_ctx, getState());
		enterRule(_localctx, 176, RULE_local_variable_declarator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1462);
			identifier();
			setState(1468);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ASSIGNMENT) {
				{
				setState(1463);
				match(ASSIGNMENT);
				setState(1465);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,153,_ctx) ) {
				case 1:
					{
					setState(1464);
					match(REF);
					}
					break;
				}
				setState(1467);
				local_variable_initializer();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Local_variable_initializerContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public Array_initializerContext array_initializer() {
			return getRuleContext(Array_initializerContext.class,0);
		}
		public Stackalloc_initializerContext stackalloc_initializer() {
			return getRuleContext(Stackalloc_initializerContext.class,0);
		}
		public Local_variable_initializerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_local_variable_initializer; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterLocal_variable_initializer(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitLocal_variable_initializer(this);
		}
	}

	public final Local_variable_initializerContext local_variable_initializer() throws RecognitionException {
		Local_variable_initializerContext _localctx = new Local_variable_initializerContext(_ctx, getState());
		enterRule(_localctx, 178, RULE_local_variable_initializer);
		try {
			setState(1473);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ADD:
			case ALIAS:
			case ARGLIST:
			case ASCENDING:
			case ASYNC:
			case AWAIT:
			case BASE:
			case BOOL:
			case BY:
			case BYTE:
			case CHAR:
			case CHECKED:
			case DECIMAL:
			case DEFAULT:
			case DELEGATE:
			case DESCENDING:
			case DOUBLE:
			case DYNAMIC:
			case EQUALS:
			case FALSE:
			case FLOAT:
			case FROM:
			case GET:
			case GROUP:
			case INT:
			case INTO:
			case JOIN:
			case LET:
			case LONG:
			case NAMEOF:
			case NEW:
			case NULL_:
			case OBJECT:
			case ON:
			case ORDERBY:
			case PARTIAL:
			case REF:
			case REMOVE:
			case SBYTE:
			case SELECT:
			case SET:
			case SHORT:
			case SIZEOF:
			case STRING:
			case THIS:
			case TRUE:
			case TYPEOF:
			case UINT:
			case ULONG:
			case UNCHECKED:
			case UNMANAGED:
			case USHORT:
			case VAR:
			case WHEN:
			case WHERE:
			case YIELD:
			case IDENTIFIER:
			case LITERAL_ACCESS:
			case INTEGER_LITERAL:
			case HEX_INTEGER_LITERAL:
			case BIN_INTEGER_LITERAL:
			case REAL_LITERAL:
			case CHARACTER_LITERAL:
			case REGULAR_STRING:
			case VERBATIUM_STRING:
			case INTERPOLATED_REGULAR_STRING_START:
			case INTERPOLATED_VERBATIUM_STRING_START:
			case OPEN_PARENS:
			case PLUS:
			case MINUS:
			case STAR:
			case AMP:
			case CARET:
			case BANG:
			case TILDE:
			case OP_INC:
			case OP_DEC:
			case OP_RANGE:
				enterOuterAlt(_localctx, 1);
				{
				setState(1470);
				expression();
				}
				break;
			case OPEN_BRACE:
				enterOuterAlt(_localctx, 2);
				{
				setState(1471);
				array_initializer();
				}
				break;
			case STACKALLOC:
				enterOuterAlt(_localctx, 3);
				{
				setState(1472);
				stackalloc_initializer();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Local_constant_declarationContext extends ParserRuleContext {
		public TerminalNode CONST() { return getToken(CSharpParser.CONST, 0); }
		public Type_Context type_() {
			return getRuleContext(Type_Context.class,0);
		}
		public Constant_declaratorsContext constant_declarators() {
			return getRuleContext(Constant_declaratorsContext.class,0);
		}
		public Local_constant_declarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_local_constant_declaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterLocal_constant_declaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitLocal_constant_declaration(this);
		}
	}

	public final Local_constant_declarationContext local_constant_declaration() throws RecognitionException {
		Local_constant_declarationContext _localctx = new Local_constant_declarationContext(_ctx, getState());
		enterRule(_localctx, 180, RULE_local_constant_declaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1475);
			match(CONST);
			setState(1476);
			type_();
			setState(1477);
			constant_declarators();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class If_bodyContext extends ParserRuleContext {
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public Simple_embedded_statementContext simple_embedded_statement() {
			return getRuleContext(Simple_embedded_statementContext.class,0);
		}
		public If_bodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_if_body; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterIf_body(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitIf_body(this);
		}
	}

	public final If_bodyContext if_body() throws RecognitionException {
		If_bodyContext _localctx = new If_bodyContext(_ctx, getState());
		enterRule(_localctx, 182, RULE_if_body);
		try {
			setState(1481);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case OPEN_BRACE:
				enterOuterAlt(_localctx, 1);
				{
				setState(1479);
				block();
				}
				break;
			case ADD:
			case ALIAS:
			case ARGLIST:
			case ASCENDING:
			case ASYNC:
			case AWAIT:
			case BASE:
			case BOOL:
			case BREAK:
			case BY:
			case BYTE:
			case CHAR:
			case CHECKED:
			case CONTINUE:
			case DECIMAL:
			case DEFAULT:
			case DELEGATE:
			case DESCENDING:
			case DO:
			case DOUBLE:
			case DYNAMIC:
			case EQUALS:
			case FALSE:
			case FIXED:
			case FLOAT:
			case FOR:
			case FOREACH:
			case FROM:
			case GET:
			case GOTO:
			case GROUP:
			case IF:
			case INT:
			case INTO:
			case JOIN:
			case LET:
			case LOCK:
			case LONG:
			case NAMEOF:
			case NEW:
			case NULL_:
			case OBJECT:
			case ON:
			case ORDERBY:
			case PARTIAL:
			case REF:
			case REMOVE:
			case RETURN:
			case SBYTE:
			case SELECT:
			case SET:
			case SHORT:
			case SIZEOF:
			case STRING:
			case SWITCH:
			case THIS:
			case THROW:
			case TRUE:
			case TRY:
			case TYPEOF:
			case UINT:
			case ULONG:
			case UNCHECKED:
			case UNMANAGED:
			case UNSAFE:
			case USHORT:
			case USING:
			case VAR:
			case WHEN:
			case WHERE:
			case WHILE:
			case YIELD:
			case IDENTIFIER:
			case LITERAL_ACCESS:
			case INTEGER_LITERAL:
			case HEX_INTEGER_LITERAL:
			case BIN_INTEGER_LITERAL:
			case REAL_LITERAL:
			case CHARACTER_LITERAL:
			case REGULAR_STRING:
			case VERBATIUM_STRING:
			case INTERPOLATED_REGULAR_STRING_START:
			case INTERPOLATED_VERBATIUM_STRING_START:
			case OPEN_PARENS:
			case SEMICOLON:
			case PLUS:
			case MINUS:
			case STAR:
			case AMP:
			case CARET:
			case BANG:
			case TILDE:
			case OP_INC:
			case OP_DEC:
			case OP_RANGE:
				enterOuterAlt(_localctx, 2);
				{
				setState(1480);
				simple_embedded_statement();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Switch_sectionContext extends ParserRuleContext {
		public Statement_listContext statement_list() {
			return getRuleContext(Statement_listContext.class,0);
		}
		public List<Switch_labelContext> switch_label() {
			return getRuleContexts(Switch_labelContext.class);
		}
		public Switch_labelContext switch_label(int i) {
			return getRuleContext(Switch_labelContext.class,i);
		}
		public Switch_sectionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_switch_section; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterSwitch_section(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitSwitch_section(this);
		}
	}

	public final Switch_sectionContext switch_section() throws RecognitionException {
		Switch_sectionContext _localctx = new Switch_sectionContext(_ctx, getState());
		enterRule(_localctx, 184, RULE_switch_section);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(1484); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(1483);
					switch_label();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(1486); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,157,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			setState(1488);
			statement_list();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Switch_labelContext extends ParserRuleContext {
		public TerminalNode CASE() { return getToken(CSharpParser.CASE, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode COLON() { return getToken(CSharpParser.COLON, 0); }
		public Case_guardContext case_guard() {
			return getRuleContext(Case_guardContext.class,0);
		}
		public TerminalNode DEFAULT() { return getToken(CSharpParser.DEFAULT, 0); }
		public Switch_labelContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_switch_label; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterSwitch_label(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitSwitch_label(this);
		}
	}

	public final Switch_labelContext switch_label() throws RecognitionException {
		Switch_labelContext _localctx = new Switch_labelContext(_ctx, getState());
		enterRule(_localctx, 186, RULE_switch_label);
		int _la;
		try {
			setState(1499);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case CASE:
				enterOuterAlt(_localctx, 1);
				{
				setState(1490);
				match(CASE);
				setState(1491);
				expression();
				setState(1493);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==WHEN) {
					{
					setState(1492);
					case_guard();
					}
				}

				setState(1495);
				match(COLON);
				}
				break;
			case DEFAULT:
				enterOuterAlt(_localctx, 2);
				{
				setState(1497);
				match(DEFAULT);
				setState(1498);
				match(COLON);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Case_guardContext extends ParserRuleContext {
		public TerminalNode WHEN() { return getToken(CSharpParser.WHEN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public Case_guardContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_case_guard; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterCase_guard(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitCase_guard(this);
		}
	}

	public final Case_guardContext case_guard() throws RecognitionException {
		Case_guardContext _localctx = new Case_guardContext(_ctx, getState());
		enterRule(_localctx, 188, RULE_case_guard);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1501);
			match(WHEN);
			setState(1502);
			expression();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Statement_listContext extends ParserRuleContext {
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public Statement_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterStatement_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitStatement_list(this);
		}
	}

	public final Statement_listContext statement_list() throws RecognitionException {
		Statement_listContext _localctx = new Statement_listContext(_ctx, getState());
		enterRule(_localctx, 190, RULE_statement_list);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(1505); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(1504);
					statement();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(1507); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,160,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class For_initializerContext extends ParserRuleContext {
		public Local_variable_declarationContext local_variable_declaration() {
			return getRuleContext(Local_variable_declarationContext.class,0);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(CSharpParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(CSharpParser.COMMA, i);
		}
		public For_initializerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_for_initializer; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterFor_initializer(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitFor_initializer(this);
		}
	}

	public final For_initializerContext for_initializer() throws RecognitionException {
		For_initializerContext _localctx = new For_initializerContext(_ctx, getState());
		enterRule(_localctx, 192, RULE_for_initializer);
		int _la;
		try {
			setState(1518);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,162,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1509);
				local_variable_declaration();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1510);
				expression();
				setState(1515);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(1511);
					match(COMMA);
					setState(1512);
					expression();
					}
					}
					setState(1517);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class For_iteratorContext extends ParserRuleContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(CSharpParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(CSharpParser.COMMA, i);
		}
		public For_iteratorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_for_iterator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterFor_iterator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitFor_iterator(this);
		}
	}

	public final For_iteratorContext for_iterator() throws RecognitionException {
		For_iteratorContext _localctx = new For_iteratorContext(_ctx, getState());
		enterRule(_localctx, 194, RULE_for_iterator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1520);
			expression();
			setState(1525);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(1521);
				match(COMMA);
				setState(1522);
				expression();
				}
				}
				setState(1527);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Catch_clausesContext extends ParserRuleContext {
		public List<Specific_catch_clauseContext> specific_catch_clause() {
			return getRuleContexts(Specific_catch_clauseContext.class);
		}
		public Specific_catch_clauseContext specific_catch_clause(int i) {
			return getRuleContext(Specific_catch_clauseContext.class,i);
		}
		public General_catch_clauseContext general_catch_clause() {
			return getRuleContext(General_catch_clauseContext.class,0);
		}
		public Catch_clausesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_catch_clauses; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterCatch_clauses(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitCatch_clauses(this);
		}
	}

	public final Catch_clausesContext catch_clauses() throws RecognitionException {
		Catch_clausesContext _localctx = new Catch_clausesContext(_ctx, getState());
		enterRule(_localctx, 196, RULE_catch_clauses);
		int _la;
		try {
			int _alt;
			setState(1539);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,166,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1528);
				specific_catch_clause();
				setState(1532);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,164,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(1529);
						specific_catch_clause();
						}
						} 
					}
					setState(1534);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,164,_ctx);
				}
				setState(1536);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==CATCH) {
					{
					setState(1535);
					general_catch_clause();
					}
				}

				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1538);
				general_catch_clause();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Specific_catch_clauseContext extends ParserRuleContext {
		public TerminalNode CATCH() { return getToken(CSharpParser.CATCH, 0); }
		public TerminalNode OPEN_PARENS() { return getToken(CSharpParser.OPEN_PARENS, 0); }
		public Class_typeContext class_type() {
			return getRuleContext(Class_typeContext.class,0);
		}
		public TerminalNode CLOSE_PARENS() { return getToken(CSharpParser.CLOSE_PARENS, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public Exception_filterContext exception_filter() {
			return getRuleContext(Exception_filterContext.class,0);
		}
		public Specific_catch_clauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_specific_catch_clause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterSpecific_catch_clause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitSpecific_catch_clause(this);
		}
	}

	public final Specific_catch_clauseContext specific_catch_clause() throws RecognitionException {
		Specific_catch_clauseContext _localctx = new Specific_catch_clauseContext(_ctx, getState());
		enterRule(_localctx, 198, RULE_specific_catch_clause);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1541);
			match(CATCH);
			setState(1542);
			match(OPEN_PARENS);
			setState(1543);
			class_type();
			setState(1545);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ADD) | (1L << ALIAS) | (1L << ARGLIST) | (1L << ASCENDING) | (1L << ASYNC) | (1L << AWAIT) | (1L << BY) | (1L << DESCENDING) | (1L << DYNAMIC) | (1L << EQUALS) | (1L << FROM) | (1L << GET) | (1L << GROUP) | (1L << INTO) | (1L << JOIN) | (1L << LET))) != 0) || ((((_la - 65)) & ~0x3f) == 0 && ((1L << (_la - 65)) & ((1L << (NAMEOF - 65)) | (1L << (ON - 65)) | (1L << (ORDERBY - 65)) | (1L << (PARTIAL - 65)) | (1L << (REMOVE - 65)) | (1L << (SELECT - 65)) | (1L << (SET - 65)) | (1L << (UNMANAGED - 65)) | (1L << (VAR - 65)) | (1L << (WHEN - 65)) | (1L << (WHERE - 65)) | (1L << (YIELD - 65)) | (1L << (IDENTIFIER - 65)))) != 0)) {
				{
				setState(1544);
				identifier();
				}
			}

			setState(1547);
			match(CLOSE_PARENS);
			setState(1549);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==WHEN) {
				{
				setState(1548);
				exception_filter();
				}
			}

			setState(1551);
			block();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class General_catch_clauseContext extends ParserRuleContext {
		public TerminalNode CATCH() { return getToken(CSharpParser.CATCH, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public Exception_filterContext exception_filter() {
			return getRuleContext(Exception_filterContext.class,0);
		}
		public General_catch_clauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_general_catch_clause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterGeneral_catch_clause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitGeneral_catch_clause(this);
		}
	}

	public final General_catch_clauseContext general_catch_clause() throws RecognitionException {
		General_catch_clauseContext _localctx = new General_catch_clauseContext(_ctx, getState());
		enterRule(_localctx, 200, RULE_general_catch_clause);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1553);
			match(CATCH);
			setState(1555);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==WHEN) {
				{
				setState(1554);
				exception_filter();
				}
			}

			setState(1557);
			block();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Exception_filterContext extends ParserRuleContext {
		public TerminalNode WHEN() { return getToken(CSharpParser.WHEN, 0); }
		public TerminalNode OPEN_PARENS() { return getToken(CSharpParser.OPEN_PARENS, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode CLOSE_PARENS() { return getToken(CSharpParser.CLOSE_PARENS, 0); }
		public Exception_filterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exception_filter; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterException_filter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitException_filter(this);
		}
	}

	public final Exception_filterContext exception_filter() throws RecognitionException {
		Exception_filterContext _localctx = new Exception_filterContext(_ctx, getState());
		enterRule(_localctx, 202, RULE_exception_filter);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1559);
			match(WHEN);
			setState(1560);
			match(OPEN_PARENS);
			setState(1561);
			expression();
			setState(1562);
			match(CLOSE_PARENS);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Finally_clauseContext extends ParserRuleContext {
		public TerminalNode FINALLY() { return getToken(CSharpParser.FINALLY, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public Finally_clauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_finally_clause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterFinally_clause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitFinally_clause(this);
		}
	}

	public final Finally_clauseContext finally_clause() throws RecognitionException {
		Finally_clauseContext _localctx = new Finally_clauseContext(_ctx, getState());
		enterRule(_localctx, 204, RULE_finally_clause);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1564);
			match(FINALLY);
			setState(1565);
			block();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Resource_acquisitionContext extends ParserRuleContext {
		public Local_variable_declarationContext local_variable_declaration() {
			return getRuleContext(Local_variable_declarationContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public Resource_acquisitionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_resource_acquisition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterResource_acquisition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitResource_acquisition(this);
		}
	}

	public final Resource_acquisitionContext resource_acquisition() throws RecognitionException {
		Resource_acquisitionContext _localctx = new Resource_acquisitionContext(_ctx, getState());
		enterRule(_localctx, 206, RULE_resource_acquisition);
		try {
			setState(1569);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,170,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1567);
				local_variable_declaration();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1568);
				expression();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Namespace_declarationContext extends ParserRuleContext {
		public Qualified_identifierContext qi;
		public TerminalNode NAMESPACE() { return getToken(CSharpParser.NAMESPACE, 0); }
		public TerminalNode SEMICOLON() { return getToken(CSharpParser.SEMICOLON, 0); }
		public Qualified_identifierContext qualified_identifier() {
			return getRuleContext(Qualified_identifierContext.class,0);
		}
		public Namespace_bodyContext namespace_body() {
			return getRuleContext(Namespace_bodyContext.class,0);
		}
		public Namespace_declarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_namespace_declaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterNamespace_declaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitNamespace_declaration(this);
		}
	}

	public final Namespace_declarationContext namespace_declaration() throws RecognitionException {
		Namespace_declarationContext _localctx = new Namespace_declarationContext(_ctx, getState());
		enterRule(_localctx, 208, RULE_namespace_declaration);
		int _la;
		try {
			setState(1581);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,172,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1571);
				match(NAMESPACE);
				setState(1572);
				((Namespace_declarationContext)_localctx).qi = qualified_identifier();
				setState(1573);
				match(SEMICOLON);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1575);
				match(NAMESPACE);
				setState(1576);
				((Namespace_declarationContext)_localctx).qi = qualified_identifier();
				setState(1577);
				namespace_body();
				setState(1579);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==SEMICOLON) {
					{
					setState(1578);
					match(SEMICOLON);
					}
				}

				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Qualified_identifierContext extends ParserRuleContext {
		public List<IdentifierContext> identifier() {
			return getRuleContexts(IdentifierContext.class);
		}
		public IdentifierContext identifier(int i) {
			return getRuleContext(IdentifierContext.class,i);
		}
		public List<TerminalNode> DOT() { return getTokens(CSharpParser.DOT); }
		public TerminalNode DOT(int i) {
			return getToken(CSharpParser.DOT, i);
		}
		public Qualified_identifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_qualified_identifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterQualified_identifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitQualified_identifier(this);
		}
	}

	public final Qualified_identifierContext qualified_identifier() throws RecognitionException {
		Qualified_identifierContext _localctx = new Qualified_identifierContext(_ctx, getState());
		enterRule(_localctx, 210, RULE_qualified_identifier);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1583);
			identifier();
			setState(1588);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==DOT) {
				{
				{
				setState(1584);
				match(DOT);
				setState(1585);
				identifier();
				}
				}
				setState(1590);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Namespace_bodyContext extends ParserRuleContext {
		public TerminalNode OPEN_BRACE() { return getToken(CSharpParser.OPEN_BRACE, 0); }
		public TerminalNode CLOSE_BRACE() { return getToken(CSharpParser.CLOSE_BRACE, 0); }
		public Extern_alias_directivesContext extern_alias_directives() {
			return getRuleContext(Extern_alias_directivesContext.class,0);
		}
		public Using_directivesContext using_directives() {
			return getRuleContext(Using_directivesContext.class,0);
		}
		public Namespace_member_declarationsContext namespace_member_declarations() {
			return getRuleContext(Namespace_member_declarationsContext.class,0);
		}
		public Namespace_bodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_namespace_body; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterNamespace_body(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitNamespace_body(this);
		}
	}

	public final Namespace_bodyContext namespace_body() throws RecognitionException {
		Namespace_bodyContext _localctx = new Namespace_bodyContext(_ctx, getState());
		enterRule(_localctx, 212, RULE_namespace_body);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1591);
			match(OPEN_BRACE);
			setState(1593);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,174,_ctx) ) {
			case 1:
				{
				setState(1592);
				extern_alias_directives();
				}
				break;
			}
			setState(1596);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==USING) {
				{
				setState(1595);
				using_directives();
				}
			}

			setState(1599);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (((((_la - 9)) & ~0x3f) == 0 && ((1L << (_la - 9)) & ((1L << (ABSTRACT - 9)) | (1L << (ASYNC - 9)) | (1L << (CLASS - 9)) | (1L << (DELEGATE - 9)) | (1L << (ENUM - 9)) | (1L << (EXTERN - 9)) | (1L << (FINAL - 9)) | (1L << (INTERFACE - 9)) | (1L << (INTERNAL - 9)) | (1L << (NAMESPACE - 9)) | (1L << (NATIVE - 9)) | (1L << (NEW - 9)))) != 0) || ((((_la - 76)) & ~0x3f) == 0 && ((1L << (_la - 76)) & ((1L << (OVERRIDE - 76)) | (1L << (PARTIAL - 76)) | (1L << (PRIVATE - 76)) | (1L << (PROTECTED - 76)) | (1L << (PUBLIC - 76)) | (1L << (READONLY - 76)) | (1L << (REF - 76)) | (1L << (SEALED - 76)) | (1L << (STATIC - 76)) | (1L << (STRUCT - 76)) | (1L << (UNSAFE - 76)) | (1L << (VIRTUAL - 76)) | (1L << (VOLATILE - 76)) | (1L << (OPEN_BRACKET - 76)))) != 0)) {
				{
				setState(1598);
				namespace_member_declarations();
				}
			}

			setState(1601);
			match(CLOSE_BRACE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Extern_alias_directivesContext extends ParserRuleContext {
		public List<Extern_alias_directiveContext> extern_alias_directive() {
			return getRuleContexts(Extern_alias_directiveContext.class);
		}
		public Extern_alias_directiveContext extern_alias_directive(int i) {
			return getRuleContext(Extern_alias_directiveContext.class,i);
		}
		public Extern_alias_directivesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_extern_alias_directives; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterExtern_alias_directives(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitExtern_alias_directives(this);
		}
	}

	public final Extern_alias_directivesContext extern_alias_directives() throws RecognitionException {
		Extern_alias_directivesContext _localctx = new Extern_alias_directivesContext(_ctx, getState());
		enterRule(_localctx, 214, RULE_extern_alias_directives);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(1604); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(1603);
					extern_alias_directive();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(1606); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,177,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Extern_alias_directiveContext extends ParserRuleContext {
		public TerminalNode EXTERN() { return getToken(CSharpParser.EXTERN, 0); }
		public TerminalNode ALIAS() { return getToken(CSharpParser.ALIAS, 0); }
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TerminalNode SEMICOLON() { return getToken(CSharpParser.SEMICOLON, 0); }
		public Extern_alias_directiveContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_extern_alias_directive; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterExtern_alias_directive(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitExtern_alias_directive(this);
		}
	}

	public final Extern_alias_directiveContext extern_alias_directive() throws RecognitionException {
		Extern_alias_directiveContext _localctx = new Extern_alias_directiveContext(_ctx, getState());
		enterRule(_localctx, 216, RULE_extern_alias_directive);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1608);
			match(EXTERN);
			setState(1609);
			match(ALIAS);
			setState(1610);
			identifier();
			setState(1611);
			match(SEMICOLON);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Using_directivesContext extends ParserRuleContext {
		public List<Using_directiveContext> using_directive() {
			return getRuleContexts(Using_directiveContext.class);
		}
		public Using_directiveContext using_directive(int i) {
			return getRuleContext(Using_directiveContext.class,i);
		}
		public Using_directivesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_using_directives; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterUsing_directives(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitUsing_directives(this);
		}
	}

	public final Using_directivesContext using_directives() throws RecognitionException {
		Using_directivesContext _localctx = new Using_directivesContext(_ctx, getState());
		enterRule(_localctx, 218, RULE_using_directives);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1614); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(1613);
				using_directive();
				}
				}
				setState(1616); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==USING );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Using_directiveContext extends ParserRuleContext {
		public Using_directiveContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_using_directive; }
	 
		public Using_directiveContext() { }
		public void copyFrom(Using_directiveContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class UsingAliasDirectiveContext extends Using_directiveContext {
		public TerminalNode USING() { return getToken(CSharpParser.USING, 0); }
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TerminalNode ASSIGNMENT() { return getToken(CSharpParser.ASSIGNMENT, 0); }
		public Namespace_or_type_nameContext namespace_or_type_name() {
			return getRuleContext(Namespace_or_type_nameContext.class,0);
		}
		public TerminalNode SEMICOLON() { return getToken(CSharpParser.SEMICOLON, 0); }
		public UsingAliasDirectiveContext(Using_directiveContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterUsingAliasDirective(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitUsingAliasDirective(this);
		}
	}
	public static class UsingNamespaceDirectiveContext extends Using_directiveContext {
		public TerminalNode USING() { return getToken(CSharpParser.USING, 0); }
		public Namespace_or_type_nameContext namespace_or_type_name() {
			return getRuleContext(Namespace_or_type_nameContext.class,0);
		}
		public TerminalNode SEMICOLON() { return getToken(CSharpParser.SEMICOLON, 0); }
		public UsingNamespaceDirectiveContext(Using_directiveContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterUsingNamespaceDirective(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitUsingNamespaceDirective(this);
		}
	}
	public static class UsingStaticDirectiveContext extends Using_directiveContext {
		public TerminalNode USING() { return getToken(CSharpParser.USING, 0); }
		public TerminalNode STATIC() { return getToken(CSharpParser.STATIC, 0); }
		public Namespace_or_type_nameContext namespace_or_type_name() {
			return getRuleContext(Namespace_or_type_nameContext.class,0);
		}
		public TerminalNode SEMICOLON() { return getToken(CSharpParser.SEMICOLON, 0); }
		public UsingStaticDirectiveContext(Using_directiveContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterUsingStaticDirective(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitUsingStaticDirective(this);
		}
	}

	public final Using_directiveContext using_directive() throws RecognitionException {
		Using_directiveContext _localctx = new Using_directiveContext(_ctx, getState());
		enterRule(_localctx, 220, RULE_using_directive);
		try {
			setState(1633);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,179,_ctx) ) {
			case 1:
				_localctx = new UsingAliasDirectiveContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(1618);
				match(USING);
				setState(1619);
				identifier();
				setState(1620);
				match(ASSIGNMENT);
				setState(1621);
				namespace_or_type_name();
				setState(1622);
				match(SEMICOLON);
				}
				break;
			case 2:
				_localctx = new UsingNamespaceDirectiveContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(1624);
				match(USING);
				setState(1625);
				namespace_or_type_name();
				setState(1626);
				match(SEMICOLON);
				}
				break;
			case 3:
				_localctx = new UsingStaticDirectiveContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(1628);
				match(USING);
				setState(1629);
				match(STATIC);
				setState(1630);
				namespace_or_type_name();
				setState(1631);
				match(SEMICOLON);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Namespace_member_declarationsContext extends ParserRuleContext {
		public List<Namespace_member_declarationContext> namespace_member_declaration() {
			return getRuleContexts(Namespace_member_declarationContext.class);
		}
		public Namespace_member_declarationContext namespace_member_declaration(int i) {
			return getRuleContext(Namespace_member_declarationContext.class,i);
		}
		public Namespace_member_declarationsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_namespace_member_declarations; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterNamespace_member_declarations(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitNamespace_member_declarations(this);
		}
	}

	public final Namespace_member_declarationsContext namespace_member_declarations() throws RecognitionException {
		Namespace_member_declarationsContext _localctx = new Namespace_member_declarationsContext(_ctx, getState());
		enterRule(_localctx, 222, RULE_namespace_member_declarations);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1636); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(1635);
				namespace_member_declaration();
				}
				}
				setState(1638); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( ((((_la - 9)) & ~0x3f) == 0 && ((1L << (_la - 9)) & ((1L << (ABSTRACT - 9)) | (1L << (ASYNC - 9)) | (1L << (CLASS - 9)) | (1L << (DELEGATE - 9)) | (1L << (ENUM - 9)) | (1L << (EXTERN - 9)) | (1L << (FINAL - 9)) | (1L << (INTERFACE - 9)) | (1L << (INTERNAL - 9)) | (1L << (NAMESPACE - 9)) | (1L << (NATIVE - 9)) | (1L << (NEW - 9)))) != 0) || ((((_la - 76)) & ~0x3f) == 0 && ((1L << (_la - 76)) & ((1L << (OVERRIDE - 76)) | (1L << (PARTIAL - 76)) | (1L << (PRIVATE - 76)) | (1L << (PROTECTED - 76)) | (1L << (PUBLIC - 76)) | (1L << (READONLY - 76)) | (1L << (REF - 76)) | (1L << (SEALED - 76)) | (1L << (STATIC - 76)) | (1L << (STRUCT - 76)) | (1L << (UNSAFE - 76)) | (1L << (VIRTUAL - 76)) | (1L << (VOLATILE - 76)) | (1L << (OPEN_BRACKET - 76)))) != 0) );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Namespace_member_declarationContext extends ParserRuleContext {
		public Namespace_declarationContext namespace_declaration() {
			return getRuleContext(Namespace_declarationContext.class,0);
		}
		public Type_declarationContext type_declaration() {
			return getRuleContext(Type_declarationContext.class,0);
		}
		public Namespace_member_declarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_namespace_member_declaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterNamespace_member_declaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitNamespace_member_declaration(this);
		}
	}

	public final Namespace_member_declarationContext namespace_member_declaration() throws RecognitionException {
		Namespace_member_declarationContext _localctx = new Namespace_member_declarationContext(_ctx, getState());
		enterRule(_localctx, 224, RULE_namespace_member_declaration);
		try {
			setState(1642);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NAMESPACE:
				enterOuterAlt(_localctx, 1);
				{
				setState(1640);
				namespace_declaration();
				}
				break;
			case ABSTRACT:
			case ASYNC:
			case CLASS:
			case DELEGATE:
			case ENUM:
			case EXTERN:
			case FINAL:
			case INTERFACE:
			case INTERNAL:
			case NATIVE:
			case NEW:
			case OVERRIDE:
			case PARTIAL:
			case PRIVATE:
			case PROTECTED:
			case PUBLIC:
			case READONLY:
			case REF:
			case SEALED:
			case STATIC:
			case STRUCT:
			case UNSAFE:
			case VIRTUAL:
			case VOLATILE:
			case OPEN_BRACKET:
				enterOuterAlt(_localctx, 2);
				{
				setState(1641);
				type_declaration();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Type_declarationContext extends ParserRuleContext {
		public Class_definitionContext class_definition() {
			return getRuleContext(Class_definitionContext.class,0);
		}
		public Struct_definitionContext struct_definition() {
			return getRuleContext(Struct_definitionContext.class,0);
		}
		public Interface_definitionContext interface_definition() {
			return getRuleContext(Interface_definitionContext.class,0);
		}
		public Enum_definitionContext enum_definition() {
			return getRuleContext(Enum_definitionContext.class,0);
		}
		public Delegate_definitionContext delegate_definition() {
			return getRuleContext(Delegate_definitionContext.class,0);
		}
		public AttributesContext attributes() {
			return getRuleContext(AttributesContext.class,0);
		}
		public All_member_modifiersContext all_member_modifiers() {
			return getRuleContext(All_member_modifiersContext.class,0);
		}
		public Type_declarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type_declaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterType_declaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitType_declaration(this);
		}
	}

	public final Type_declarationContext type_declaration() throws RecognitionException {
		Type_declarationContext _localctx = new Type_declarationContext(_ctx, getState());
		enterRule(_localctx, 226, RULE_type_declaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1645);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==OPEN_BRACKET) {
				{
				setState(1644);
				attributes();
				}
			}

			setState(1648);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,183,_ctx) ) {
			case 1:
				{
				setState(1647);
				all_member_modifiers();
				}
				break;
			}
			setState(1655);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case CLASS:
				{
				setState(1650);
				class_definition();
				}
				break;
			case READONLY:
			case REF:
			case STRUCT:
				{
				setState(1651);
				struct_definition();
				}
				break;
			case INTERFACE:
				{
				setState(1652);
				interface_definition();
				}
				break;
			case ENUM:
				{
				setState(1653);
				enum_definition();
				}
				break;
			case DELEGATE:
				{
				setState(1654);
				delegate_definition();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Qualified_alias_memberContext extends ParserRuleContext {
		public List<IdentifierContext> identifier() {
			return getRuleContexts(IdentifierContext.class);
		}
		public IdentifierContext identifier(int i) {
			return getRuleContext(IdentifierContext.class,i);
		}
		public TerminalNode DOUBLE_COLON() { return getToken(CSharpParser.DOUBLE_COLON, 0); }
		public Type_argument_listContext type_argument_list() {
			return getRuleContext(Type_argument_listContext.class,0);
		}
		public Qualified_alias_memberContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_qualified_alias_member; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterQualified_alias_member(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitQualified_alias_member(this);
		}
	}

	public final Qualified_alias_memberContext qualified_alias_member() throws RecognitionException {
		Qualified_alias_memberContext _localctx = new Qualified_alias_memberContext(_ctx, getState());
		enterRule(_localctx, 228, RULE_qualified_alias_member);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1657);
			identifier();
			setState(1658);
			match(DOUBLE_COLON);
			setState(1659);
			identifier();
			setState(1661);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,185,_ctx) ) {
			case 1:
				{
				setState(1660);
				type_argument_list();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Type_parameter_listContext extends ParserRuleContext {
		public TerminalNode LT() { return getToken(CSharpParser.LT, 0); }
		public List<Type_parameterContext> type_parameter() {
			return getRuleContexts(Type_parameterContext.class);
		}
		public Type_parameterContext type_parameter(int i) {
			return getRuleContext(Type_parameterContext.class,i);
		}
		public TerminalNode GT() { return getToken(CSharpParser.GT, 0); }
		public List<TerminalNode> COMMA() { return getTokens(CSharpParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(CSharpParser.COMMA, i);
		}
		public Type_parameter_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type_parameter_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterType_parameter_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitType_parameter_list(this);
		}
	}

	public final Type_parameter_listContext type_parameter_list() throws RecognitionException {
		Type_parameter_listContext _localctx = new Type_parameter_listContext(_ctx, getState());
		enterRule(_localctx, 230, RULE_type_parameter_list);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1663);
			match(LT);
			setState(1664);
			type_parameter();
			setState(1669);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(1665);
				match(COMMA);
				setState(1666);
				type_parameter();
				}
				}
				setState(1671);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(1672);
			match(GT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Type_parameterContext extends ParserRuleContext {
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public AttributesContext attributes() {
			return getRuleContext(AttributesContext.class,0);
		}
		public Type_parameterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type_parameter; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterType_parameter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitType_parameter(this);
		}
	}

	public final Type_parameterContext type_parameter() throws RecognitionException {
		Type_parameterContext _localctx = new Type_parameterContext(_ctx, getState());
		enterRule(_localctx, 232, RULE_type_parameter);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1675);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==OPEN_BRACKET) {
				{
				setState(1674);
				attributes();
				}
			}

			setState(1677);
			identifier();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Class_baseContext extends ParserRuleContext {
		public TerminalNode COLON() { return getToken(CSharpParser.COLON, 0); }
		public Class_typeContext class_type() {
			return getRuleContext(Class_typeContext.class,0);
		}
		public List<TerminalNode> COMMA() { return getTokens(CSharpParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(CSharpParser.COMMA, i);
		}
		public List<Namespace_or_type_nameContext> namespace_or_type_name() {
			return getRuleContexts(Namespace_or_type_nameContext.class);
		}
		public Namespace_or_type_nameContext namespace_or_type_name(int i) {
			return getRuleContext(Namespace_or_type_nameContext.class,i);
		}
		public Class_baseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_class_base; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterClass_base(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitClass_base(this);
		}
	}

	public final Class_baseContext class_base() throws RecognitionException {
		Class_baseContext _localctx = new Class_baseContext(_ctx, getState());
		enterRule(_localctx, 234, RULE_class_base);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1679);
			match(COLON);
			setState(1680);
			class_type();
			setState(1685);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(1681);
				match(COMMA);
				setState(1682);
				namespace_or_type_name();
				}
				}
				setState(1687);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Interface_type_listContext extends ParserRuleContext {
		public List<Namespace_or_type_nameContext> namespace_or_type_name() {
			return getRuleContexts(Namespace_or_type_nameContext.class);
		}
		public Namespace_or_type_nameContext namespace_or_type_name(int i) {
			return getRuleContext(Namespace_or_type_nameContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(CSharpParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(CSharpParser.COMMA, i);
		}
		public Interface_type_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_interface_type_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterInterface_type_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitInterface_type_list(this);
		}
	}

	public final Interface_type_listContext interface_type_list() throws RecognitionException {
		Interface_type_listContext _localctx = new Interface_type_listContext(_ctx, getState());
		enterRule(_localctx, 236, RULE_interface_type_list);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1688);
			namespace_or_type_name();
			setState(1693);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(1689);
				match(COMMA);
				setState(1690);
				namespace_or_type_name();
				}
				}
				setState(1695);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Type_parameter_constraints_clausesContext extends ParserRuleContext {
		public List<Type_parameter_constraints_clauseContext> type_parameter_constraints_clause() {
			return getRuleContexts(Type_parameter_constraints_clauseContext.class);
		}
		public Type_parameter_constraints_clauseContext type_parameter_constraints_clause(int i) {
			return getRuleContext(Type_parameter_constraints_clauseContext.class,i);
		}
		public Type_parameter_constraints_clausesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type_parameter_constraints_clauses; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterType_parameter_constraints_clauses(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitType_parameter_constraints_clauses(this);
		}
	}

	public final Type_parameter_constraints_clausesContext type_parameter_constraints_clauses() throws RecognitionException {
		Type_parameter_constraints_clausesContext _localctx = new Type_parameter_constraints_clausesContext(_ctx, getState());
		enterRule(_localctx, 238, RULE_type_parameter_constraints_clauses);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1697); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(1696);
				type_parameter_constraints_clause();
				}
				}
				setState(1699); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==WHERE );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Type_parameter_constraints_clauseContext extends ParserRuleContext {
		public TerminalNode WHERE() { return getToken(CSharpParser.WHERE, 0); }
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TerminalNode COLON() { return getToken(CSharpParser.COLON, 0); }
		public Type_parameter_constraintsContext type_parameter_constraints() {
			return getRuleContext(Type_parameter_constraintsContext.class,0);
		}
		public Type_parameter_constraints_clauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type_parameter_constraints_clause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterType_parameter_constraints_clause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitType_parameter_constraints_clause(this);
		}
	}

	public final Type_parameter_constraints_clauseContext type_parameter_constraints_clause() throws RecognitionException {
		Type_parameter_constraints_clauseContext _localctx = new Type_parameter_constraints_clauseContext(_ctx, getState());
		enterRule(_localctx, 240, RULE_type_parameter_constraints_clause);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1701);
			match(WHERE);
			setState(1702);
			identifier();
			setState(1703);
			match(COLON);
			setState(1704);
			type_parameter_constraints();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Type_parameter_constraintsContext extends ParserRuleContext {
		public Constructor_constraintContext constructor_constraint() {
			return getRuleContext(Constructor_constraintContext.class,0);
		}
		public Primary_constraintContext primary_constraint() {
			return getRuleContext(Primary_constraintContext.class,0);
		}
		public List<TerminalNode> COMMA() { return getTokens(CSharpParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(CSharpParser.COMMA, i);
		}
		public Secondary_constraintsContext secondary_constraints() {
			return getRuleContext(Secondary_constraintsContext.class,0);
		}
		public Type_parameter_constraintsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type_parameter_constraints; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterType_parameter_constraints(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitType_parameter_constraints(this);
		}
	}

	public final Type_parameter_constraintsContext type_parameter_constraints() throws RecognitionException {
		Type_parameter_constraintsContext _localctx = new Type_parameter_constraintsContext(_ctx, getState());
		enterRule(_localctx, 242, RULE_type_parameter_constraints);
		int _la;
		try {
			setState(1716);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NEW:
				enterOuterAlt(_localctx, 1);
				{
				setState(1706);
				constructor_constraint();
				}
				break;
			case ADD:
			case ALIAS:
			case ARGLIST:
			case ASCENDING:
			case ASYNC:
			case AWAIT:
			case BY:
			case CLASS:
			case DESCENDING:
			case DYNAMIC:
			case EQUALS:
			case FROM:
			case GET:
			case GROUP:
			case INTO:
			case JOIN:
			case LET:
			case NAMEOF:
			case OBJECT:
			case ON:
			case ORDERBY:
			case PARTIAL:
			case REMOVE:
			case SELECT:
			case SET:
			case STRING:
			case STRUCT:
			case UNMANAGED:
			case VAR:
			case WHEN:
			case WHERE:
			case YIELD:
			case IDENTIFIER:
				enterOuterAlt(_localctx, 2);
				{
				setState(1707);
				primary_constraint();
				setState(1710);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,191,_ctx) ) {
				case 1:
					{
					setState(1708);
					match(COMMA);
					setState(1709);
					secondary_constraints();
					}
					break;
				}
				setState(1714);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==COMMA) {
					{
					setState(1712);
					match(COMMA);
					setState(1713);
					constructor_constraint();
					}
				}

				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Primary_constraintContext extends ParserRuleContext {
		public Class_typeContext class_type() {
			return getRuleContext(Class_typeContext.class,0);
		}
		public TerminalNode CLASS() { return getToken(CSharpParser.CLASS, 0); }
		public TerminalNode INTERR() { return getToken(CSharpParser.INTERR, 0); }
		public TerminalNode STRUCT() { return getToken(CSharpParser.STRUCT, 0); }
		public TerminalNode UNMANAGED() { return getToken(CSharpParser.UNMANAGED, 0); }
		public Primary_constraintContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_primary_constraint; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterPrimary_constraint(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitPrimary_constraint(this);
		}
	}

	public final Primary_constraintContext primary_constraint() throws RecognitionException {
		Primary_constraintContext _localctx = new Primary_constraintContext(_ctx, getState());
		enterRule(_localctx, 244, RULE_primary_constraint);
		int _la;
		try {
			setState(1725);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,195,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1718);
				class_type();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1719);
				match(CLASS);
				setState(1721);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==INTERR) {
					{
					setState(1720);
					match(INTERR);
					}
				}

				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(1723);
				match(STRUCT);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(1724);
				match(UNMANAGED);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Secondary_constraintsContext extends ParserRuleContext {
		public List<Namespace_or_type_nameContext> namespace_or_type_name() {
			return getRuleContexts(Namespace_or_type_nameContext.class);
		}
		public Namespace_or_type_nameContext namespace_or_type_name(int i) {
			return getRuleContext(Namespace_or_type_nameContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(CSharpParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(CSharpParser.COMMA, i);
		}
		public Secondary_constraintsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_secondary_constraints; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterSecondary_constraints(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitSecondary_constraints(this);
		}
	}

	public final Secondary_constraintsContext secondary_constraints() throws RecognitionException {
		Secondary_constraintsContext _localctx = new Secondary_constraintsContext(_ctx, getState());
		enterRule(_localctx, 246, RULE_secondary_constraints);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(1727);
			namespace_or_type_name();
			setState(1732);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,196,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(1728);
					match(COMMA);
					setState(1729);
					namespace_or_type_name();
					}
					} 
				}
				setState(1734);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,196,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Constructor_constraintContext extends ParserRuleContext {
		public TerminalNode NEW() { return getToken(CSharpParser.NEW, 0); }
		public TerminalNode OPEN_PARENS() { return getToken(CSharpParser.OPEN_PARENS, 0); }
		public TerminalNode CLOSE_PARENS() { return getToken(CSharpParser.CLOSE_PARENS, 0); }
		public Constructor_constraintContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constructor_constraint; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterConstructor_constraint(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitConstructor_constraint(this);
		}
	}

	public final Constructor_constraintContext constructor_constraint() throws RecognitionException {
		Constructor_constraintContext _localctx = new Constructor_constraintContext(_ctx, getState());
		enterRule(_localctx, 248, RULE_constructor_constraint);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1735);
			match(NEW);
			setState(1736);
			match(OPEN_PARENS);
			setState(1737);
			match(CLOSE_PARENS);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Class_bodyContext extends ParserRuleContext {
		public TerminalNode OPEN_BRACE() { return getToken(CSharpParser.OPEN_BRACE, 0); }
		public TerminalNode CLOSE_BRACE() { return getToken(CSharpParser.CLOSE_BRACE, 0); }
		public Class_member_declarationsContext class_member_declarations() {
			return getRuleContext(Class_member_declarationsContext.class,0);
		}
		public Class_bodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_class_body; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterClass_body(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitClass_body(this);
		}
	}

	public final Class_bodyContext class_body() throws RecognitionException {
		Class_bodyContext _localctx = new Class_bodyContext(_ctx, getState());
		enterRule(_localctx, 250, RULE_class_body);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1739);
			match(OPEN_BRACE);
			setState(1741);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ABSTRACT) | (1L << ADD) | (1L << ALIAS) | (1L << ARGLIST) | (1L << ASCENDING) | (1L << ASYNC) | (1L << AWAIT) | (1L << BOOL) | (1L << BY) | (1L << BYTE) | (1L << CHAR) | (1L << CLASS) | (1L << CONST) | (1L << DECIMAL) | (1L << DELEGATE) | (1L << DESCENDING) | (1L << DOUBLE) | (1L << DYNAMIC) | (1L << ENUM) | (1L << EQUALS) | (1L << EVENT) | (1L << EXPLICIT) | (1L << EXTERN) | (1L << FINAL) | (1L << FLOAT) | (1L << FROM) | (1L << GET) | (1L << GROUP) | (1L << IMPLICIT) | (1L << INT) | (1L << INTERFACE) | (1L << INTERNAL) | (1L << INTO) | (1L << JOIN) | (1L << LET))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (LONG - 64)) | (1L << (NAMEOF - 64)) | (1L << (NATIVE - 64)) | (1L << (NEW - 64)) | (1L << (OBJECT - 64)) | (1L << (ON - 64)) | (1L << (ORDERBY - 64)) | (1L << (OVERRIDE - 64)) | (1L << (PARTIAL - 64)) | (1L << (PRIVATE - 64)) | (1L << (PROTECTED - 64)) | (1L << (PUBLIC - 64)) | (1L << (READONLY - 64)) | (1L << (REF - 64)) | (1L << (REMOVE - 64)) | (1L << (SBYTE - 64)) | (1L << (SEALED - 64)) | (1L << (SELECT - 64)) | (1L << (SET - 64)) | (1L << (SHORT - 64)) | (1L << (STATIC - 64)) | (1L << (STRING - 64)) | (1L << (STRUCT - 64)) | (1L << (UINT - 64)) | (1L << (ULONG - 64)) | (1L << (UNMANAGED - 64)) | (1L << (UNSAFE - 64)) | (1L << (USHORT - 64)) | (1L << (VAR - 64)) | (1L << (VIRTUAL - 64)) | (1L << (VOID - 64)) | (1L << (VOLATILE - 64)) | (1L << (WHEN - 64)) | (1L << (WHERE - 64)) | (1L << (YIELD - 64)) | (1L << (IDENTIFIER - 64)))) != 0) || ((((_la - 131)) & ~0x3f) == 0 && ((1L << (_la - 131)) & ((1L << (OPEN_BRACKET - 131)) | (1L << (OPEN_PARENS - 131)) | (1L << (TILDE - 131)))) != 0)) {
				{
				setState(1740);
				class_member_declarations();
				}
			}

			setState(1743);
			match(CLOSE_BRACE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Class_member_declarationsContext extends ParserRuleContext {
		public List<Class_member_declarationContext> class_member_declaration() {
			return getRuleContexts(Class_member_declarationContext.class);
		}
		public Class_member_declarationContext class_member_declaration(int i) {
			return getRuleContext(Class_member_declarationContext.class,i);
		}
		public Class_member_declarationsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_class_member_declarations; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterClass_member_declarations(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitClass_member_declarations(this);
		}
	}

	public final Class_member_declarationsContext class_member_declarations() throws RecognitionException {
		Class_member_declarationsContext _localctx = new Class_member_declarationsContext(_ctx, getState());
		enterRule(_localctx, 252, RULE_class_member_declarations);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1746); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(1745);
				class_member_declaration();
				}
				}
				setState(1748); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ABSTRACT) | (1L << ADD) | (1L << ALIAS) | (1L << ARGLIST) | (1L << ASCENDING) | (1L << ASYNC) | (1L << AWAIT) | (1L << BOOL) | (1L << BY) | (1L << BYTE) | (1L << CHAR) | (1L << CLASS) | (1L << CONST) | (1L << DECIMAL) | (1L << DELEGATE) | (1L << DESCENDING) | (1L << DOUBLE) | (1L << DYNAMIC) | (1L << ENUM) | (1L << EQUALS) | (1L << EVENT) | (1L << EXPLICIT) | (1L << EXTERN) | (1L << FINAL) | (1L << FLOAT) | (1L << FROM) | (1L << GET) | (1L << GROUP) | (1L << IMPLICIT) | (1L << INT) | (1L << INTERFACE) | (1L << INTERNAL) | (1L << INTO) | (1L << JOIN) | (1L << LET))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (LONG - 64)) | (1L << (NAMEOF - 64)) | (1L << (NATIVE - 64)) | (1L << (NEW - 64)) | (1L << (OBJECT - 64)) | (1L << (ON - 64)) | (1L << (ORDERBY - 64)) | (1L << (OVERRIDE - 64)) | (1L << (PARTIAL - 64)) | (1L << (PRIVATE - 64)) | (1L << (PROTECTED - 64)) | (1L << (PUBLIC - 64)) | (1L << (READONLY - 64)) | (1L << (REF - 64)) | (1L << (REMOVE - 64)) | (1L << (SBYTE - 64)) | (1L << (SEALED - 64)) | (1L << (SELECT - 64)) | (1L << (SET - 64)) | (1L << (SHORT - 64)) | (1L << (STATIC - 64)) | (1L << (STRING - 64)) | (1L << (STRUCT - 64)) | (1L << (UINT - 64)) | (1L << (ULONG - 64)) | (1L << (UNMANAGED - 64)) | (1L << (UNSAFE - 64)) | (1L << (USHORT - 64)) | (1L << (VAR - 64)) | (1L << (VIRTUAL - 64)) | (1L << (VOID - 64)) | (1L << (VOLATILE - 64)) | (1L << (WHEN - 64)) | (1L << (WHERE - 64)) | (1L << (YIELD - 64)) | (1L << (IDENTIFIER - 64)))) != 0) || ((((_la - 131)) & ~0x3f) == 0 && ((1L << (_la - 131)) & ((1L << (OPEN_BRACKET - 131)) | (1L << (OPEN_PARENS - 131)) | (1L << (TILDE - 131)))) != 0) );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Class_member_declarationContext extends ParserRuleContext {
		public Common_member_declarationContext common_member_declaration() {
			return getRuleContext(Common_member_declarationContext.class,0);
		}
		public Destructor_definitionContext destructor_definition() {
			return getRuleContext(Destructor_definitionContext.class,0);
		}
		public AttributesContext attributes() {
			return getRuleContext(AttributesContext.class,0);
		}
		public All_member_modifiersContext all_member_modifiers() {
			return getRuleContext(All_member_modifiersContext.class,0);
		}
		public Class_member_declarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_class_member_declaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterClass_member_declaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitClass_member_declaration(this);
		}
	}

	public final Class_member_declarationContext class_member_declaration() throws RecognitionException {
		Class_member_declarationContext _localctx = new Class_member_declarationContext(_ctx, getState());
		enterRule(_localctx, 254, RULE_class_member_declaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1751);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==OPEN_BRACKET) {
				{
				setState(1750);
				attributes();
				}
			}

			setState(1754);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,200,_ctx) ) {
			case 1:
				{
				setState(1753);
				all_member_modifiers();
				}
				break;
			}
			setState(1758);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ADD:
			case ALIAS:
			case ARGLIST:
			case ASCENDING:
			case ASYNC:
			case AWAIT:
			case BOOL:
			case BY:
			case BYTE:
			case CHAR:
			case CLASS:
			case CONST:
			case DECIMAL:
			case DELEGATE:
			case DESCENDING:
			case DOUBLE:
			case DYNAMIC:
			case ENUM:
			case EQUALS:
			case EVENT:
			case EXPLICIT:
			case FLOAT:
			case FROM:
			case GET:
			case GROUP:
			case IMPLICIT:
			case INT:
			case INTERFACE:
			case INTO:
			case JOIN:
			case LET:
			case LONG:
			case NAMEOF:
			case OBJECT:
			case ON:
			case ORDERBY:
			case PARTIAL:
			case READONLY:
			case REF:
			case REMOVE:
			case SBYTE:
			case SELECT:
			case SET:
			case SHORT:
			case STRING:
			case STRUCT:
			case UINT:
			case ULONG:
			case UNMANAGED:
			case USHORT:
			case VAR:
			case VOID:
			case WHEN:
			case WHERE:
			case YIELD:
			case IDENTIFIER:
			case OPEN_PARENS:
				{
				setState(1756);
				common_member_declaration();
				}
				break;
			case TILDE:
				{
				setState(1757);
				destructor_definition();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class All_member_modifiersContext extends ParserRuleContext {
		public List<All_member_modifierContext> all_member_modifier() {
			return getRuleContexts(All_member_modifierContext.class);
		}
		public All_member_modifierContext all_member_modifier(int i) {
			return getRuleContext(All_member_modifierContext.class,i);
		}
		public All_member_modifiersContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_all_member_modifiers; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterAll_member_modifiers(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitAll_member_modifiers(this);
		}
	}

	public final All_member_modifiersContext all_member_modifiers() throws RecognitionException {
		All_member_modifiersContext _localctx = new All_member_modifiersContext(_ctx, getState());
		enterRule(_localctx, 256, RULE_all_member_modifiers);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(1761); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(1760);
					all_member_modifier();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(1763); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,202,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class All_member_modifierContext extends ParserRuleContext {
		public TerminalNode NEW() { return getToken(CSharpParser.NEW, 0); }
		public TerminalNode PUBLIC() { return getToken(CSharpParser.PUBLIC, 0); }
		public TerminalNode PROTECTED() { return getToken(CSharpParser.PROTECTED, 0); }
		public TerminalNode INTERNAL() { return getToken(CSharpParser.INTERNAL, 0); }
		public TerminalNode PRIVATE() { return getToken(CSharpParser.PRIVATE, 0); }
		public TerminalNode READONLY() { return getToken(CSharpParser.READONLY, 0); }
		public TerminalNode FINAL() { return getToken(CSharpParser.FINAL, 0); }
		public TerminalNode VOLATILE() { return getToken(CSharpParser.VOLATILE, 0); }
		public TerminalNode VIRTUAL() { return getToken(CSharpParser.VIRTUAL, 0); }
		public TerminalNode SEALED() { return getToken(CSharpParser.SEALED, 0); }
		public TerminalNode OVERRIDE() { return getToken(CSharpParser.OVERRIDE, 0); }
		public TerminalNode ABSTRACT() { return getToken(CSharpParser.ABSTRACT, 0); }
		public TerminalNode STATIC() { return getToken(CSharpParser.STATIC, 0); }
		public TerminalNode UNSAFE() { return getToken(CSharpParser.UNSAFE, 0); }
		public TerminalNode EXTERN() { return getToken(CSharpParser.EXTERN, 0); }
		public TerminalNode NATIVE() { return getToken(CSharpParser.NATIVE, 0); }
		public TerminalNode PARTIAL() { return getToken(CSharpParser.PARTIAL, 0); }
		public TerminalNode ASYNC() { return getToken(CSharpParser.ASYNC, 0); }
		public All_member_modifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_all_member_modifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterAll_member_modifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitAll_member_modifier(this);
		}
	}

	public final All_member_modifierContext all_member_modifier() throws RecognitionException {
		All_member_modifierContext _localctx = new All_member_modifierContext(_ctx, getState());
		enterRule(_localctx, 258, RULE_all_member_modifier);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1765);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ABSTRACT) | (1L << ASYNC) | (1L << EXTERN) | (1L << FINAL) | (1L << INTERNAL))) != 0) || ((((_la - 67)) & ~0x3f) == 0 && ((1L << (_la - 67)) & ((1L << (NATIVE - 67)) | (1L << (NEW - 67)) | (1L << (OVERRIDE - 67)) | (1L << (PARTIAL - 67)) | (1L << (PRIVATE - 67)) | (1L << (PROTECTED - 67)) | (1L << (PUBLIC - 67)) | (1L << (READONLY - 67)) | (1L << (SEALED - 67)) | (1L << (STATIC - 67)) | (1L << (UNSAFE - 67)) | (1L << (VIRTUAL - 67)) | (1L << (VOLATILE - 67)))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Common_member_declarationContext extends ParserRuleContext {
		public Constant_declarationContext constant_declaration() {
			return getRuleContext(Constant_declarationContext.class,0);
		}
		public Typed_member_declarationContext typed_member_declaration() {
			return getRuleContext(Typed_member_declarationContext.class,0);
		}
		public Event_declarationContext event_declaration() {
			return getRuleContext(Event_declarationContext.class,0);
		}
		public Conversion_operator_declaratorContext conversion_operator_declarator() {
			return getRuleContext(Conversion_operator_declaratorContext.class,0);
		}
		public BodyContext body() {
			return getRuleContext(BodyContext.class,0);
		}
		public Right_arrowContext right_arrow() {
			return getRuleContext(Right_arrowContext.class,0);
		}
		public Throwable_expressionContext throwable_expression() {
			return getRuleContext(Throwable_expressionContext.class,0);
		}
		public TerminalNode SEMICOLON() { return getToken(CSharpParser.SEMICOLON, 0); }
		public Constructor_declarationContext constructor_declaration() {
			return getRuleContext(Constructor_declarationContext.class,0);
		}
		public TerminalNode VOID() { return getToken(CSharpParser.VOID, 0); }
		public Method_declarationContext method_declaration() {
			return getRuleContext(Method_declarationContext.class,0);
		}
		public Class_definitionContext class_definition() {
			return getRuleContext(Class_definitionContext.class,0);
		}
		public Struct_definitionContext struct_definition() {
			return getRuleContext(Struct_definitionContext.class,0);
		}
		public Interface_definitionContext interface_definition() {
			return getRuleContext(Interface_definitionContext.class,0);
		}
		public Enum_definitionContext enum_definition() {
			return getRuleContext(Enum_definitionContext.class,0);
		}
		public Delegate_definitionContext delegate_definition() {
			return getRuleContext(Delegate_definitionContext.class,0);
		}
		public Common_member_declarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_common_member_declaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterCommon_member_declaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitCommon_member_declaration(this);
		}
	}

	public final Common_member_declarationContext common_member_declaration() throws RecognitionException {
		Common_member_declarationContext _localctx = new Common_member_declarationContext(_ctx, getState());
		enterRule(_localctx, 260, RULE_common_member_declaration);
		try {
			setState(1786);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,204,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1767);
				constant_declaration();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1768);
				typed_member_declaration();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(1769);
				event_declaration();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(1770);
				conversion_operator_declarator();
				setState(1776);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case OPEN_BRACE:
				case SEMICOLON:
					{
					setState(1771);
					body();
					}
					break;
				case ASSIGNMENT:
					{
					setState(1772);
					right_arrow();
					setState(1773);
					throwable_expression();
					setState(1774);
					match(SEMICOLON);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(1778);
				constructor_declaration();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(1779);
				match(VOID);
				setState(1780);
				method_declaration();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(1781);
				class_definition();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(1782);
				struct_definition();
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(1783);
				interface_definition();
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(1784);
				enum_definition();
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(1785);
				delegate_definition();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Typed_member_declarationContext extends ParserRuleContext {
		public Type_Context type_() {
			return getRuleContext(Type_Context.class,0);
		}
		public Namespace_or_type_nameContext namespace_or_type_name() {
			return getRuleContext(Namespace_or_type_nameContext.class,0);
		}
		public TerminalNode DOT() { return getToken(CSharpParser.DOT, 0); }
		public Indexer_declarationContext indexer_declaration() {
			return getRuleContext(Indexer_declarationContext.class,0);
		}
		public Method_declarationContext method_declaration() {
			return getRuleContext(Method_declarationContext.class,0);
		}
		public Property_declarationContext property_declaration() {
			return getRuleContext(Property_declarationContext.class,0);
		}
		public Operator_declarationContext operator_declaration() {
			return getRuleContext(Operator_declarationContext.class,0);
		}
		public Field_declarationContext field_declaration() {
			return getRuleContext(Field_declarationContext.class,0);
		}
		public TerminalNode REF() { return getToken(CSharpParser.REF, 0); }
		public TerminalNode READONLY() { return getToken(CSharpParser.READONLY, 0); }
		public Typed_member_declarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typed_member_declaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterTyped_member_declaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitTyped_member_declaration(this);
		}
	}

	public final Typed_member_declarationContext typed_member_declaration() throws RecognitionException {
		Typed_member_declarationContext _localctx = new Typed_member_declarationContext(_ctx, getState());
		enterRule(_localctx, 262, RULE_typed_member_declaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1793);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,205,_ctx) ) {
			case 1:
				{
				setState(1788);
				match(REF);
				}
				break;
			case 2:
				{
				setState(1789);
				match(READONLY);
				setState(1790);
				match(REF);
				}
				break;
			case 3:
				{
				setState(1791);
				match(REF);
				setState(1792);
				match(READONLY);
				}
				break;
			}
			setState(1795);
			type_();
			setState(1805);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,206,_ctx) ) {
			case 1:
				{
				setState(1796);
				namespace_or_type_name();
				setState(1797);
				match(DOT);
				setState(1798);
				indexer_declaration();
				}
				break;
			case 2:
				{
				setState(1800);
				method_declaration();
				}
				break;
			case 3:
				{
				setState(1801);
				property_declaration();
				}
				break;
			case 4:
				{
				setState(1802);
				indexer_declaration();
				}
				break;
			case 5:
				{
				setState(1803);
				operator_declaration();
				}
				break;
			case 6:
				{
				setState(1804);
				field_declaration();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Constant_declaratorsContext extends ParserRuleContext {
		public List<Constant_declaratorContext> constant_declarator() {
			return getRuleContexts(Constant_declaratorContext.class);
		}
		public Constant_declaratorContext constant_declarator(int i) {
			return getRuleContext(Constant_declaratorContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(CSharpParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(CSharpParser.COMMA, i);
		}
		public Constant_declaratorsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constant_declarators; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterConstant_declarators(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitConstant_declarators(this);
		}
	}

	public final Constant_declaratorsContext constant_declarators() throws RecognitionException {
		Constant_declaratorsContext _localctx = new Constant_declaratorsContext(_ctx, getState());
		enterRule(_localctx, 264, RULE_constant_declarators);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1807);
			constant_declarator();
			setState(1812);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(1808);
				match(COMMA);
				setState(1809);
				constant_declarator();
				}
				}
				setState(1814);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Constant_declaratorContext extends ParserRuleContext {
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TerminalNode ASSIGNMENT() { return getToken(CSharpParser.ASSIGNMENT, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public Constant_declaratorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constant_declarator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterConstant_declarator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitConstant_declarator(this);
		}
	}

	public final Constant_declaratorContext constant_declarator() throws RecognitionException {
		Constant_declaratorContext _localctx = new Constant_declaratorContext(_ctx, getState());
		enterRule(_localctx, 266, RULE_constant_declarator);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1815);
			identifier();
			setState(1816);
			match(ASSIGNMENT);
			setState(1817);
			expression();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Variable_declaratorsContext extends ParserRuleContext {
		public List<Variable_declaratorContext> variable_declarator() {
			return getRuleContexts(Variable_declaratorContext.class);
		}
		public Variable_declaratorContext variable_declarator(int i) {
			return getRuleContext(Variable_declaratorContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(CSharpParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(CSharpParser.COMMA, i);
		}
		public Variable_declaratorsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variable_declarators; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterVariable_declarators(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitVariable_declarators(this);
		}
	}

	public final Variable_declaratorsContext variable_declarators() throws RecognitionException {
		Variable_declaratorsContext _localctx = new Variable_declaratorsContext(_ctx, getState());
		enterRule(_localctx, 268, RULE_variable_declarators);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1819);
			variable_declarator();
			setState(1824);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(1820);
				match(COMMA);
				setState(1821);
				variable_declarator();
				}
				}
				setState(1826);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Variable_declaratorContext extends ParserRuleContext {
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TerminalNode ASSIGNMENT() { return getToken(CSharpParser.ASSIGNMENT, 0); }
		public Variable_initializerContext variable_initializer() {
			return getRuleContext(Variable_initializerContext.class,0);
		}
		public Variable_declaratorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variable_declarator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterVariable_declarator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitVariable_declarator(this);
		}
	}

	public final Variable_declaratorContext variable_declarator() throws RecognitionException {
		Variable_declaratorContext _localctx = new Variable_declaratorContext(_ctx, getState());
		enterRule(_localctx, 270, RULE_variable_declarator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1827);
			identifier();
			setState(1830);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ASSIGNMENT) {
				{
				setState(1828);
				match(ASSIGNMENT);
				setState(1829);
				variable_initializer();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Variable_initializerContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public Array_initializerContext array_initializer() {
			return getRuleContext(Array_initializerContext.class,0);
		}
		public Variable_initializerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variable_initializer; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterVariable_initializer(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitVariable_initializer(this);
		}
	}

	public final Variable_initializerContext variable_initializer() throws RecognitionException {
		Variable_initializerContext _localctx = new Variable_initializerContext(_ctx, getState());
		enterRule(_localctx, 272, RULE_variable_initializer);
		try {
			setState(1834);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ADD:
			case ALIAS:
			case ARGLIST:
			case ASCENDING:
			case ASYNC:
			case AWAIT:
			case BASE:
			case BOOL:
			case BY:
			case BYTE:
			case CHAR:
			case CHECKED:
			case DECIMAL:
			case DEFAULT:
			case DELEGATE:
			case DESCENDING:
			case DOUBLE:
			case DYNAMIC:
			case EQUALS:
			case FALSE:
			case FLOAT:
			case FROM:
			case GET:
			case GROUP:
			case INT:
			case INTO:
			case JOIN:
			case LET:
			case LONG:
			case NAMEOF:
			case NEW:
			case NULL_:
			case OBJECT:
			case ON:
			case ORDERBY:
			case PARTIAL:
			case REF:
			case REMOVE:
			case SBYTE:
			case SELECT:
			case SET:
			case SHORT:
			case SIZEOF:
			case STRING:
			case THIS:
			case TRUE:
			case TYPEOF:
			case UINT:
			case ULONG:
			case UNCHECKED:
			case UNMANAGED:
			case USHORT:
			case VAR:
			case WHEN:
			case WHERE:
			case YIELD:
			case IDENTIFIER:
			case LITERAL_ACCESS:
			case INTEGER_LITERAL:
			case HEX_INTEGER_LITERAL:
			case BIN_INTEGER_LITERAL:
			case REAL_LITERAL:
			case CHARACTER_LITERAL:
			case REGULAR_STRING:
			case VERBATIUM_STRING:
			case INTERPOLATED_REGULAR_STRING_START:
			case INTERPOLATED_VERBATIUM_STRING_START:
			case OPEN_PARENS:
			case PLUS:
			case MINUS:
			case STAR:
			case AMP:
			case CARET:
			case BANG:
			case TILDE:
			case OP_INC:
			case OP_DEC:
			case OP_RANGE:
				enterOuterAlt(_localctx, 1);
				{
				setState(1832);
				expression();
				}
				break;
			case OPEN_BRACE:
				enterOuterAlt(_localctx, 2);
				{
				setState(1833);
				array_initializer();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Return_typeContext extends ParserRuleContext {
		public Type_Context type_() {
			return getRuleContext(Type_Context.class,0);
		}
		public TerminalNode VOID() { return getToken(CSharpParser.VOID, 0); }
		public Return_typeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_return_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterReturn_type(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitReturn_type(this);
		}
	}

	public final Return_typeContext return_type() throws RecognitionException {
		Return_typeContext _localctx = new Return_typeContext(_ctx, getState());
		enterRule(_localctx, 274, RULE_return_type);
		try {
			setState(1838);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,211,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1836);
				type_();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1837);
				match(VOID);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Member_nameContext extends ParserRuleContext {
		public Namespace_or_type_nameContext namespace_or_type_name() {
			return getRuleContext(Namespace_or_type_nameContext.class,0);
		}
		public Member_nameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_member_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterMember_name(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitMember_name(this);
		}
	}

	public final Member_nameContext member_name() throws RecognitionException {
		Member_nameContext _localctx = new Member_nameContext(_ctx, getState());
		enterRule(_localctx, 276, RULE_member_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1840);
			namespace_or_type_name();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Method_bodyContext extends ParserRuleContext {
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public TerminalNode SEMICOLON() { return getToken(CSharpParser.SEMICOLON, 0); }
		public Method_bodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_method_body; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterMethod_body(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitMethod_body(this);
		}
	}

	public final Method_bodyContext method_body() throws RecognitionException {
		Method_bodyContext _localctx = new Method_bodyContext(_ctx, getState());
		enterRule(_localctx, 278, RULE_method_body);
		try {
			setState(1844);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case OPEN_BRACE:
				enterOuterAlt(_localctx, 1);
				{
				setState(1842);
				block();
				}
				break;
			case SEMICOLON:
				enterOuterAlt(_localctx, 2);
				{
				setState(1843);
				match(SEMICOLON);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Formal_parameter_listContext extends ParserRuleContext {
		public Parameter_arrayContext parameter_array() {
			return getRuleContext(Parameter_arrayContext.class,0);
		}
		public Fixed_parametersContext fixed_parameters() {
			return getRuleContext(Fixed_parametersContext.class,0);
		}
		public TerminalNode COMMA() { return getToken(CSharpParser.COMMA, 0); }
		public Formal_parameter_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_formal_parameter_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterFormal_parameter_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitFormal_parameter_list(this);
		}
	}

	public final Formal_parameter_listContext formal_parameter_list() throws RecognitionException {
		Formal_parameter_listContext _localctx = new Formal_parameter_listContext(_ctx, getState());
		enterRule(_localctx, 280, RULE_formal_parameter_list);
		int _la;
		try {
			setState(1852);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,214,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1846);
				parameter_array();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1847);
				fixed_parameters();
				setState(1850);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==COMMA) {
					{
					setState(1848);
					match(COMMA);
					setState(1849);
					parameter_array();
					}
				}

				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Fixed_parametersContext extends ParserRuleContext {
		public List<Fixed_parameterContext> fixed_parameter() {
			return getRuleContexts(Fixed_parameterContext.class);
		}
		public Fixed_parameterContext fixed_parameter(int i) {
			return getRuleContext(Fixed_parameterContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(CSharpParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(CSharpParser.COMMA, i);
		}
		public Fixed_parametersContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fixed_parameters; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterFixed_parameters(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitFixed_parameters(this);
		}
	}

	public final Fixed_parametersContext fixed_parameters() throws RecognitionException {
		Fixed_parametersContext _localctx = new Fixed_parametersContext(_ctx, getState());
		enterRule(_localctx, 282, RULE_fixed_parameters);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(1854);
			fixed_parameter();
			setState(1859);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,215,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(1855);
					match(COMMA);
					setState(1856);
					fixed_parameter();
					}
					} 
				}
				setState(1861);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,215,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Fixed_parameterContext extends ParserRuleContext {
		public Arg_declarationContext arg_declaration() {
			return getRuleContext(Arg_declarationContext.class,0);
		}
		public AttributesContext attributes() {
			return getRuleContext(AttributesContext.class,0);
		}
		public Parameter_modifierContext parameter_modifier() {
			return getRuleContext(Parameter_modifierContext.class,0);
		}
		public TerminalNode ARGLIST() { return getToken(CSharpParser.ARGLIST, 0); }
		public Fixed_parameterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fixed_parameter; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterFixed_parameter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitFixed_parameter(this);
		}
	}

	public final Fixed_parameterContext fixed_parameter() throws RecognitionException {
		Fixed_parameterContext _localctx = new Fixed_parameterContext(_ctx, getState());
		enterRule(_localctx, 284, RULE_fixed_parameter);
		int _la;
		try {
			setState(1870);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,218,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1863);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==OPEN_BRACKET) {
					{
					setState(1862);
					attributes();
					}
				}

				setState(1866);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (((((_la - 55)) & ~0x3f) == 0 && ((1L << (_la - 55)) & ((1L << (IN - 55)) | (1L << (OUT - 55)) | (1L << (REF - 55)) | (1L << (THIS - 55)))) != 0)) {
					{
					setState(1865);
					parameter_modifier();
					}
				}

				setState(1868);
				arg_declaration();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1869);
				match(ARGLIST);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Parameter_modifierContext extends ParserRuleContext {
		public TerminalNode REF() { return getToken(CSharpParser.REF, 0); }
		public TerminalNode OUT() { return getToken(CSharpParser.OUT, 0); }
		public TerminalNode IN() { return getToken(CSharpParser.IN, 0); }
		public TerminalNode THIS() { return getToken(CSharpParser.THIS, 0); }
		public Parameter_modifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parameter_modifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterParameter_modifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitParameter_modifier(this);
		}
	}

	public final Parameter_modifierContext parameter_modifier() throws RecognitionException {
		Parameter_modifierContext _localctx = new Parameter_modifierContext(_ctx, getState());
		enterRule(_localctx, 286, RULE_parameter_modifier);
		try {
			setState(1880);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,219,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1872);
				match(REF);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1873);
				match(OUT);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(1874);
				match(IN);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(1875);
				match(REF);
				setState(1876);
				match(THIS);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(1877);
				match(IN);
				setState(1878);
				match(THIS);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(1879);
				match(THIS);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Parameter_arrayContext extends ParserRuleContext {
		public TerminalNode PARAMS() { return getToken(CSharpParser.PARAMS, 0); }
		public Array_typeContext array_type() {
			return getRuleContext(Array_typeContext.class,0);
		}
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public AttributesContext attributes() {
			return getRuleContext(AttributesContext.class,0);
		}
		public Parameter_arrayContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parameter_array; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterParameter_array(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitParameter_array(this);
		}
	}

	public final Parameter_arrayContext parameter_array() throws RecognitionException {
		Parameter_arrayContext _localctx = new Parameter_arrayContext(_ctx, getState());
		enterRule(_localctx, 288, RULE_parameter_array);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1883);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==OPEN_BRACKET) {
				{
				setState(1882);
				attributes();
				}
			}

			setState(1885);
			match(PARAMS);
			setState(1886);
			array_type();
			setState(1887);
			identifier();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Accessor_declarationsContext extends ParserRuleContext {
		public AttributesContext attrs;
		public Accessor_modifierContext mods;
		public TerminalNode GET() { return getToken(CSharpParser.GET, 0); }
		public Accessor_bodyContext accessor_body() {
			return getRuleContext(Accessor_bodyContext.class,0);
		}
		public TerminalNode SET() { return getToken(CSharpParser.SET, 0); }
		public AttributesContext attributes() {
			return getRuleContext(AttributesContext.class,0);
		}
		public Accessor_modifierContext accessor_modifier() {
			return getRuleContext(Accessor_modifierContext.class,0);
		}
		public Set_accessor_declarationContext set_accessor_declaration() {
			return getRuleContext(Set_accessor_declarationContext.class,0);
		}
		public Get_accessor_declarationContext get_accessor_declaration() {
			return getRuleContext(Get_accessor_declarationContext.class,0);
		}
		public Accessor_declarationsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_accessor_declarations; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterAccessor_declarations(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitAccessor_declarations(this);
		}
	}

	public final Accessor_declarationsContext accessor_declarations() throws RecognitionException {
		Accessor_declarationsContext _localctx = new Accessor_declarationsContext(_ctx, getState());
		enterRule(_localctx, 290, RULE_accessor_declarations);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1890);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==OPEN_BRACKET) {
				{
				setState(1889);
				((Accessor_declarationsContext)_localctx).attrs = attributes();
				}
			}

			setState(1893);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (((((_la - 58)) & ~0x3f) == 0 && ((1L << (_la - 58)) & ((1L << (INTERNAL - 58)) | (1L << (PRIVATE - 58)) | (1L << (PROTECTED - 58)))) != 0)) {
				{
				setState(1892);
				((Accessor_declarationsContext)_localctx).mods = accessor_modifier();
				}
			}

			setState(1905);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case GET:
				{
				setState(1895);
				match(GET);
				setState(1896);
				accessor_body();
				setState(1898);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==INTERNAL || ((((_la - 79)) & ~0x3f) == 0 && ((1L << (_la - 79)) & ((1L << (PRIVATE - 79)) | (1L << (PROTECTED - 79)) | (1L << (SET - 79)) | (1L << (OPEN_BRACKET - 79)))) != 0)) {
					{
					setState(1897);
					set_accessor_declaration();
					}
				}

				}
				break;
			case SET:
				{
				setState(1900);
				match(SET);
				setState(1901);
				accessor_body();
				setState(1903);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==GET || _la==INTERNAL || ((((_la - 79)) & ~0x3f) == 0 && ((1L << (_la - 79)) & ((1L << (PRIVATE - 79)) | (1L << (PROTECTED - 79)) | (1L << (OPEN_BRACKET - 79)))) != 0)) {
					{
					setState(1902);
					get_accessor_declaration();
					}
				}

				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Get_accessor_declarationContext extends ParserRuleContext {
		public TerminalNode GET() { return getToken(CSharpParser.GET, 0); }
		public Accessor_bodyContext accessor_body() {
			return getRuleContext(Accessor_bodyContext.class,0);
		}
		public AttributesContext attributes() {
			return getRuleContext(AttributesContext.class,0);
		}
		public Accessor_modifierContext accessor_modifier() {
			return getRuleContext(Accessor_modifierContext.class,0);
		}
		public Get_accessor_declarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_get_accessor_declaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterGet_accessor_declaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitGet_accessor_declaration(this);
		}
	}

	public final Get_accessor_declarationContext get_accessor_declaration() throws RecognitionException {
		Get_accessor_declarationContext _localctx = new Get_accessor_declarationContext(_ctx, getState());
		enterRule(_localctx, 292, RULE_get_accessor_declaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1908);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==OPEN_BRACKET) {
				{
				setState(1907);
				attributes();
				}
			}

			setState(1911);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (((((_la - 58)) & ~0x3f) == 0 && ((1L << (_la - 58)) & ((1L << (INTERNAL - 58)) | (1L << (PRIVATE - 58)) | (1L << (PROTECTED - 58)))) != 0)) {
				{
				setState(1910);
				accessor_modifier();
				}
			}

			setState(1913);
			match(GET);
			setState(1914);
			accessor_body();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Set_accessor_declarationContext extends ParserRuleContext {
		public TerminalNode SET() { return getToken(CSharpParser.SET, 0); }
		public Accessor_bodyContext accessor_body() {
			return getRuleContext(Accessor_bodyContext.class,0);
		}
		public AttributesContext attributes() {
			return getRuleContext(AttributesContext.class,0);
		}
		public Accessor_modifierContext accessor_modifier() {
			return getRuleContext(Accessor_modifierContext.class,0);
		}
		public Set_accessor_declarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_set_accessor_declaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterSet_accessor_declaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitSet_accessor_declaration(this);
		}
	}

	public final Set_accessor_declarationContext set_accessor_declaration() throws RecognitionException {
		Set_accessor_declarationContext _localctx = new Set_accessor_declarationContext(_ctx, getState());
		enterRule(_localctx, 294, RULE_set_accessor_declaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1917);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==OPEN_BRACKET) {
				{
				setState(1916);
				attributes();
				}
			}

			setState(1920);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (((((_la - 58)) & ~0x3f) == 0 && ((1L << (_la - 58)) & ((1L << (INTERNAL - 58)) | (1L << (PRIVATE - 58)) | (1L << (PROTECTED - 58)))) != 0)) {
				{
				setState(1919);
				accessor_modifier();
				}
			}

			setState(1922);
			match(SET);
			setState(1923);
			accessor_body();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Accessor_modifierContext extends ParserRuleContext {
		public TerminalNode PROTECTED() { return getToken(CSharpParser.PROTECTED, 0); }
		public TerminalNode INTERNAL() { return getToken(CSharpParser.INTERNAL, 0); }
		public TerminalNode PRIVATE() { return getToken(CSharpParser.PRIVATE, 0); }
		public Accessor_modifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_accessor_modifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterAccessor_modifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitAccessor_modifier(this);
		}
	}

	public final Accessor_modifierContext accessor_modifier() throws RecognitionException {
		Accessor_modifierContext _localctx = new Accessor_modifierContext(_ctx, getState());
		enterRule(_localctx, 296, RULE_accessor_modifier);
		try {
			setState(1932);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,230,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1925);
				match(PROTECTED);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1926);
				match(INTERNAL);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(1927);
				match(PRIVATE);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(1928);
				match(PROTECTED);
				setState(1929);
				match(INTERNAL);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(1930);
				match(INTERNAL);
				setState(1931);
				match(PROTECTED);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Accessor_bodyContext extends ParserRuleContext {
		public Right_arrowContext right_arrow() {
			return getRuleContext(Right_arrowContext.class,0);
		}
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public TerminalNode SEMICOLON() { return getToken(CSharpParser.SEMICOLON, 0); }
		public Accessor_bodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_accessor_body; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterAccessor_body(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitAccessor_body(this);
		}
	}

	public final Accessor_bodyContext accessor_body() throws RecognitionException {
		Accessor_bodyContext _localctx = new Accessor_bodyContext(_ctx, getState());
		enterRule(_localctx, 298, RULE_accessor_body);
		try {
			setState(1939);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ASSIGNMENT:
				enterOuterAlt(_localctx, 1);
				{
				setState(1934);
				right_arrow();
				setState(1935);
				statement();
				}
				break;
			case OPEN_BRACE:
				enterOuterAlt(_localctx, 2);
				{
				setState(1937);
				block();
				}
				break;
			case SEMICOLON:
				enterOuterAlt(_localctx, 3);
				{
				setState(1938);
				match(SEMICOLON);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Event_accessor_declarationsContext extends ParserRuleContext {
		public TerminalNode ADD() { return getToken(CSharpParser.ADD, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public Remove_accessor_declarationContext remove_accessor_declaration() {
			return getRuleContext(Remove_accessor_declarationContext.class,0);
		}
		public TerminalNode REMOVE() { return getToken(CSharpParser.REMOVE, 0); }
		public Add_accessor_declarationContext add_accessor_declaration() {
			return getRuleContext(Add_accessor_declarationContext.class,0);
		}
		public AttributesContext attributes() {
			return getRuleContext(AttributesContext.class,0);
		}
		public Event_accessor_declarationsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_event_accessor_declarations; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterEvent_accessor_declarations(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitEvent_accessor_declarations(this);
		}
	}

	public final Event_accessor_declarationsContext event_accessor_declarations() throws RecognitionException {
		Event_accessor_declarationsContext _localctx = new Event_accessor_declarationsContext(_ctx, getState());
		enterRule(_localctx, 300, RULE_event_accessor_declarations);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1942);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==OPEN_BRACKET) {
				{
				setState(1941);
				attributes();
				}
			}

			setState(1952);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ADD:
				{
				setState(1944);
				match(ADD);
				setState(1945);
				block();
				setState(1946);
				remove_accessor_declaration();
				}
				break;
			case REMOVE:
				{
				setState(1948);
				match(REMOVE);
				setState(1949);
				block();
				setState(1950);
				add_accessor_declaration();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Add_accessor_declarationContext extends ParserRuleContext {
		public TerminalNode ADD() { return getToken(CSharpParser.ADD, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public AttributesContext attributes() {
			return getRuleContext(AttributesContext.class,0);
		}
		public Add_accessor_declarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_add_accessor_declaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterAdd_accessor_declaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitAdd_accessor_declaration(this);
		}
	}

	public final Add_accessor_declarationContext add_accessor_declaration() throws RecognitionException {
		Add_accessor_declarationContext _localctx = new Add_accessor_declarationContext(_ctx, getState());
		enterRule(_localctx, 302, RULE_add_accessor_declaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1955);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==OPEN_BRACKET) {
				{
				setState(1954);
				attributes();
				}
			}

			setState(1957);
			match(ADD);
			setState(1958);
			block();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Remove_accessor_declarationContext extends ParserRuleContext {
		public TerminalNode REMOVE() { return getToken(CSharpParser.REMOVE, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public AttributesContext attributes() {
			return getRuleContext(AttributesContext.class,0);
		}
		public Remove_accessor_declarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_remove_accessor_declaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterRemove_accessor_declaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitRemove_accessor_declaration(this);
		}
	}

	public final Remove_accessor_declarationContext remove_accessor_declaration() throws RecognitionException {
		Remove_accessor_declarationContext _localctx = new Remove_accessor_declarationContext(_ctx, getState());
		enterRule(_localctx, 304, RULE_remove_accessor_declaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1961);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==OPEN_BRACKET) {
				{
				setState(1960);
				attributes();
				}
			}

			setState(1963);
			match(REMOVE);
			setState(1964);
			block();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Overloadable_operatorContext extends ParserRuleContext {
		public TerminalNode PLUS() { return getToken(CSharpParser.PLUS, 0); }
		public TerminalNode MINUS() { return getToken(CSharpParser.MINUS, 0); }
		public TerminalNode BANG() { return getToken(CSharpParser.BANG, 0); }
		public TerminalNode TILDE() { return getToken(CSharpParser.TILDE, 0); }
		public TerminalNode OP_INC() { return getToken(CSharpParser.OP_INC, 0); }
		public TerminalNode OP_DEC() { return getToken(CSharpParser.OP_DEC, 0); }
		public TerminalNode TRUE() { return getToken(CSharpParser.TRUE, 0); }
		public TerminalNode FALSE() { return getToken(CSharpParser.FALSE, 0); }
		public TerminalNode STAR() { return getToken(CSharpParser.STAR, 0); }
		public TerminalNode DIV() { return getToken(CSharpParser.DIV, 0); }
		public TerminalNode PERCENT() { return getToken(CSharpParser.PERCENT, 0); }
		public TerminalNode AMP() { return getToken(CSharpParser.AMP, 0); }
		public TerminalNode BITWISE_OR() { return getToken(CSharpParser.BITWISE_OR, 0); }
		public TerminalNode CARET() { return getToken(CSharpParser.CARET, 0); }
		public TerminalNode OP_LEFT_SHIFT() { return getToken(CSharpParser.OP_LEFT_SHIFT, 0); }
		public Right_shiftContext right_shift() {
			return getRuleContext(Right_shiftContext.class,0);
		}
		public TerminalNode OP_EQ() { return getToken(CSharpParser.OP_EQ, 0); }
		public TerminalNode OP_NE() { return getToken(CSharpParser.OP_NE, 0); }
		public TerminalNode GT() { return getToken(CSharpParser.GT, 0); }
		public TerminalNode LT() { return getToken(CSharpParser.LT, 0); }
		public TerminalNode OP_GE() { return getToken(CSharpParser.OP_GE, 0); }
		public TerminalNode OP_LE() { return getToken(CSharpParser.OP_LE, 0); }
		public Overloadable_operatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_overloadable_operator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterOverloadable_operator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitOverloadable_operator(this);
		}
	}

	public final Overloadable_operatorContext overloadable_operator() throws RecognitionException {
		Overloadable_operatorContext _localctx = new Overloadable_operatorContext(_ctx, getState());
		enterRule(_localctx, 306, RULE_overloadable_operator);
		try {
			setState(1988);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,236,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1966);
				match(PLUS);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1967);
				match(MINUS);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(1968);
				match(BANG);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(1969);
				match(TILDE);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(1970);
				match(OP_INC);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(1971);
				match(OP_DEC);
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(1972);
				match(TRUE);
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(1973);
				match(FALSE);
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(1974);
				match(STAR);
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(1975);
				match(DIV);
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(1976);
				match(PERCENT);
				}
				break;
			case 12:
				enterOuterAlt(_localctx, 12);
				{
				setState(1977);
				match(AMP);
				}
				break;
			case 13:
				enterOuterAlt(_localctx, 13);
				{
				setState(1978);
				match(BITWISE_OR);
				}
				break;
			case 14:
				enterOuterAlt(_localctx, 14);
				{
				setState(1979);
				match(CARET);
				}
				break;
			case 15:
				enterOuterAlt(_localctx, 15);
				{
				setState(1980);
				match(OP_LEFT_SHIFT);
				}
				break;
			case 16:
				enterOuterAlt(_localctx, 16);
				{
				setState(1981);
				right_shift();
				}
				break;
			case 17:
				enterOuterAlt(_localctx, 17);
				{
				setState(1982);
				match(OP_EQ);
				}
				break;
			case 18:
				enterOuterAlt(_localctx, 18);
				{
				setState(1983);
				match(OP_NE);
				}
				break;
			case 19:
				enterOuterAlt(_localctx, 19);
				{
				setState(1984);
				match(GT);
				}
				break;
			case 20:
				enterOuterAlt(_localctx, 20);
				{
				setState(1985);
				match(LT);
				}
				break;
			case 21:
				enterOuterAlt(_localctx, 21);
				{
				setState(1986);
				match(OP_GE);
				}
				break;
			case 22:
				enterOuterAlt(_localctx, 22);
				{
				setState(1987);
				match(OP_LE);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Conversion_operator_declaratorContext extends ParserRuleContext {
		public TerminalNode OPERATOR() { return getToken(CSharpParser.OPERATOR, 0); }
		public Type_Context type_() {
			return getRuleContext(Type_Context.class,0);
		}
		public TerminalNode OPEN_PARENS() { return getToken(CSharpParser.OPEN_PARENS, 0); }
		public Arg_declarationContext arg_declaration() {
			return getRuleContext(Arg_declarationContext.class,0);
		}
		public TerminalNode CLOSE_PARENS() { return getToken(CSharpParser.CLOSE_PARENS, 0); }
		public TerminalNode IMPLICIT() { return getToken(CSharpParser.IMPLICIT, 0); }
		public TerminalNode EXPLICIT() { return getToken(CSharpParser.EXPLICIT, 0); }
		public Conversion_operator_declaratorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_conversion_operator_declarator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterConversion_operator_declarator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitConversion_operator_declarator(this);
		}
	}

	public final Conversion_operator_declaratorContext conversion_operator_declarator() throws RecognitionException {
		Conversion_operator_declaratorContext _localctx = new Conversion_operator_declaratorContext(_ctx, getState());
		enterRule(_localctx, 308, RULE_conversion_operator_declarator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1990);
			_la = _input.LA(1);
			if ( !(_la==EXPLICIT || _la==IMPLICIT) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(1991);
			match(OPERATOR);
			setState(1992);
			type_();
			setState(1993);
			match(OPEN_PARENS);
			setState(1994);
			arg_declaration();
			setState(1995);
			match(CLOSE_PARENS);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Constructor_initializerContext extends ParserRuleContext {
		public TerminalNode COLON() { return getToken(CSharpParser.COLON, 0); }
		public TerminalNode OPEN_PARENS() { return getToken(CSharpParser.OPEN_PARENS, 0); }
		public TerminalNode CLOSE_PARENS() { return getToken(CSharpParser.CLOSE_PARENS, 0); }
		public TerminalNode BASE() { return getToken(CSharpParser.BASE, 0); }
		public TerminalNode THIS() { return getToken(CSharpParser.THIS, 0); }
		public Argument_listContext argument_list() {
			return getRuleContext(Argument_listContext.class,0);
		}
		public Constructor_initializerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constructor_initializer; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterConstructor_initializer(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitConstructor_initializer(this);
		}
	}

	public final Constructor_initializerContext constructor_initializer() throws RecognitionException {
		Constructor_initializerContext _localctx = new Constructor_initializerContext(_ctx, getState());
		enterRule(_localctx, 310, RULE_constructor_initializer);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1997);
			match(COLON);
			setState(1998);
			_la = _input.LA(1);
			if ( !(_la==BASE || _la==THIS) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(1999);
			match(OPEN_PARENS);
			setState(2001);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ADD) | (1L << ALIAS) | (1L << ARGLIST) | (1L << ASCENDING) | (1L << ASYNC) | (1L << AWAIT) | (1L << BASE) | (1L << BOOL) | (1L << BY) | (1L << BYTE) | (1L << CHAR) | (1L << CHECKED) | (1L << DECIMAL) | (1L << DEFAULT) | (1L << DELEGATE) | (1L << DESCENDING) | (1L << DOUBLE) | (1L << DYNAMIC) | (1L << EQUALS) | (1L << FALSE) | (1L << FLOAT) | (1L << FROM) | (1L << GET) | (1L << GROUP) | (1L << IN) | (1L << INT) | (1L << INTO) | (1L << JOIN) | (1L << LET))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (LONG - 64)) | (1L << (NAMEOF - 64)) | (1L << (NEW - 64)) | (1L << (NULL_ - 64)) | (1L << (OBJECT - 64)) | (1L << (ON - 64)) | (1L << (ORDERBY - 64)) | (1L << (OUT - 64)) | (1L << (PARTIAL - 64)) | (1L << (REF - 64)) | (1L << (REMOVE - 64)) | (1L << (SBYTE - 64)) | (1L << (SELECT - 64)) | (1L << (SET - 64)) | (1L << (SHORT - 64)) | (1L << (SIZEOF - 64)) | (1L << (STRING - 64)) | (1L << (THIS - 64)) | (1L << (TRUE - 64)) | (1L << (TYPEOF - 64)) | (1L << (UINT - 64)) | (1L << (ULONG - 64)) | (1L << (UNCHECKED - 64)) | (1L << (UNMANAGED - 64)) | (1L << (USHORT - 64)) | (1L << (VAR - 64)) | (1L << (VOID - 64)) | (1L << (WHEN - 64)) | (1L << (WHERE - 64)) | (1L << (YIELD - 64)) | (1L << (IDENTIFIER - 64)) | (1L << (LITERAL_ACCESS - 64)) | (1L << (INTEGER_LITERAL - 64)) | (1L << (HEX_INTEGER_LITERAL - 64)) | (1L << (BIN_INTEGER_LITERAL - 64)) | (1L << (REAL_LITERAL - 64)) | (1L << (CHARACTER_LITERAL - 64)) | (1L << (REGULAR_STRING - 64)) | (1L << (VERBATIUM_STRING - 64)) | (1L << (INTERPOLATED_REGULAR_STRING_START - 64)))) != 0) || ((((_la - 128)) & ~0x3f) == 0 && ((1L << (_la - 128)) & ((1L << (INTERPOLATED_VERBATIUM_STRING_START - 128)) | (1L << (OPEN_PARENS - 128)) | (1L << (PLUS - 128)) | (1L << (MINUS - 128)) | (1L << (STAR - 128)) | (1L << (AMP - 128)) | (1L << (CARET - 128)) | (1L << (BANG - 128)) | (1L << (TILDE - 128)) | (1L << (OP_INC - 128)) | (1L << (OP_DEC - 128)) | (1L << (OP_RANGE - 128)))) != 0)) {
				{
				setState(2000);
				argument_list();
				}
			}

			setState(2003);
			match(CLOSE_PARENS);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BodyContext extends ParserRuleContext {
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public TerminalNode SEMICOLON() { return getToken(CSharpParser.SEMICOLON, 0); }
		public BodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_body; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterBody(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitBody(this);
		}
	}

	public final BodyContext body() throws RecognitionException {
		BodyContext _localctx = new BodyContext(_ctx, getState());
		enterRule(_localctx, 312, RULE_body);
		try {
			setState(2007);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case OPEN_BRACE:
				enterOuterAlt(_localctx, 1);
				{
				setState(2005);
				block();
				}
				break;
			case SEMICOLON:
				enterOuterAlt(_localctx, 2);
				{
				setState(2006);
				match(SEMICOLON);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Struct_interfacesContext extends ParserRuleContext {
		public TerminalNode COLON() { return getToken(CSharpParser.COLON, 0); }
		public Interface_type_listContext interface_type_list() {
			return getRuleContext(Interface_type_listContext.class,0);
		}
		public Struct_interfacesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_struct_interfaces; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterStruct_interfaces(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitStruct_interfaces(this);
		}
	}

	public final Struct_interfacesContext struct_interfaces() throws RecognitionException {
		Struct_interfacesContext _localctx = new Struct_interfacesContext(_ctx, getState());
		enterRule(_localctx, 314, RULE_struct_interfaces);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2009);
			match(COLON);
			setState(2010);
			interface_type_list();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Struct_bodyContext extends ParserRuleContext {
		public TerminalNode OPEN_BRACE() { return getToken(CSharpParser.OPEN_BRACE, 0); }
		public TerminalNode CLOSE_BRACE() { return getToken(CSharpParser.CLOSE_BRACE, 0); }
		public List<Struct_member_declarationContext> struct_member_declaration() {
			return getRuleContexts(Struct_member_declarationContext.class);
		}
		public Struct_member_declarationContext struct_member_declaration(int i) {
			return getRuleContext(Struct_member_declarationContext.class,i);
		}
		public Struct_bodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_struct_body; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterStruct_body(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitStruct_body(this);
		}
	}

	public final Struct_bodyContext struct_body() throws RecognitionException {
		Struct_bodyContext _localctx = new Struct_bodyContext(_ctx, getState());
		enterRule(_localctx, 316, RULE_struct_body);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2012);
			match(OPEN_BRACE);
			setState(2016);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (((((_la - 9)) & ~0x3f) == 0 && ((1L << (_la - 9)) & ((1L << (ABSTRACT - 9)) | (1L << (ADD - 9)) | (1L << (ALIAS - 9)) | (1L << (ARGLIST - 9)) | (1L << (ASCENDING - 9)) | (1L << (ASYNC - 9)) | (1L << (AWAIT - 9)) | (1L << (BOOL - 9)) | (1L << (BY - 9)) | (1L << (BYTE - 9)) | (1L << (CHAR - 9)) | (1L << (CLASS - 9)) | (1L << (CONST - 9)) | (1L << (DECIMAL - 9)) | (1L << (DELEGATE - 9)) | (1L << (DESCENDING - 9)) | (1L << (DOUBLE - 9)) | (1L << (DYNAMIC - 9)) | (1L << (ENUM - 9)) | (1L << (EQUALS - 9)) | (1L << (EVENT - 9)) | (1L << (EXPLICIT - 9)) | (1L << (EXTERN - 9)) | (1L << (FINAL - 9)) | (1L << (FIXED - 9)) | (1L << (FLOAT - 9)) | (1L << (FROM - 9)) | (1L << (GET - 9)) | (1L << (GROUP - 9)) | (1L << (IMPLICIT - 9)) | (1L << (INT - 9)) | (1L << (INTERFACE - 9)) | (1L << (INTERNAL - 9)) | (1L << (INTO - 9)) | (1L << (JOIN - 9)) | (1L << (LET - 9)) | (1L << (LONG - 9)) | (1L << (NAMEOF - 9)) | (1L << (NATIVE - 9)) | (1L << (NEW - 9)) | (1L << (OBJECT - 9)) | (1L << (ON - 9)))) != 0) || ((((_la - 74)) & ~0x3f) == 0 && ((1L << (_la - 74)) & ((1L << (ORDERBY - 74)) | (1L << (OVERRIDE - 74)) | (1L << (PARTIAL - 74)) | (1L << (PRIVATE - 74)) | (1L << (PROTECTED - 74)) | (1L << (PUBLIC - 74)) | (1L << (READONLY - 74)) | (1L << (REF - 74)) | (1L << (REMOVE - 74)) | (1L << (SBYTE - 74)) | (1L << (SEALED - 74)) | (1L << (SELECT - 74)) | (1L << (SET - 74)) | (1L << (SHORT - 74)) | (1L << (STATIC - 74)) | (1L << (STRING - 74)) | (1L << (STRUCT - 74)) | (1L << (UINT - 74)) | (1L << (ULONG - 74)) | (1L << (UNMANAGED - 74)) | (1L << (UNSAFE - 74)) | (1L << (USHORT - 74)) | (1L << (VAR - 74)) | (1L << (VIRTUAL - 74)) | (1L << (VOID - 74)) | (1L << (VOLATILE - 74)) | (1L << (WHEN - 74)) | (1L << (WHERE - 74)) | (1L << (YIELD - 74)) | (1L << (IDENTIFIER - 74)) | (1L << (OPEN_BRACKET - 74)) | (1L << (OPEN_PARENS - 74)))) != 0)) {
				{
				{
				setState(2013);
				struct_member_declaration();
				}
				}
				setState(2018);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(2019);
			match(CLOSE_BRACE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Struct_member_declarationContext extends ParserRuleContext {
		public Common_member_declarationContext common_member_declaration() {
			return getRuleContext(Common_member_declarationContext.class,0);
		}
		public TerminalNode FIXED() { return getToken(CSharpParser.FIXED, 0); }
		public Type_Context type_() {
			return getRuleContext(Type_Context.class,0);
		}
		public TerminalNode SEMICOLON() { return getToken(CSharpParser.SEMICOLON, 0); }
		public AttributesContext attributes() {
			return getRuleContext(AttributesContext.class,0);
		}
		public All_member_modifiersContext all_member_modifiers() {
			return getRuleContext(All_member_modifiersContext.class,0);
		}
		public List<Fixed_size_buffer_declaratorContext> fixed_size_buffer_declarator() {
			return getRuleContexts(Fixed_size_buffer_declaratorContext.class);
		}
		public Fixed_size_buffer_declaratorContext fixed_size_buffer_declarator(int i) {
			return getRuleContext(Fixed_size_buffer_declaratorContext.class,i);
		}
		public Struct_member_declarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_struct_member_declaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterStruct_member_declaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitStruct_member_declaration(this);
		}
	}

	public final Struct_member_declarationContext struct_member_declaration() throws RecognitionException {
		Struct_member_declarationContext _localctx = new Struct_member_declarationContext(_ctx, getState());
		enterRule(_localctx, 318, RULE_struct_member_declaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2022);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==OPEN_BRACKET) {
				{
				setState(2021);
				attributes();
				}
			}

			setState(2025);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,241,_ctx) ) {
			case 1:
				{
				setState(2024);
				all_member_modifiers();
				}
				break;
			}
			setState(2037);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ADD:
			case ALIAS:
			case ARGLIST:
			case ASCENDING:
			case ASYNC:
			case AWAIT:
			case BOOL:
			case BY:
			case BYTE:
			case CHAR:
			case CLASS:
			case CONST:
			case DECIMAL:
			case DELEGATE:
			case DESCENDING:
			case DOUBLE:
			case DYNAMIC:
			case ENUM:
			case EQUALS:
			case EVENT:
			case EXPLICIT:
			case FLOAT:
			case FROM:
			case GET:
			case GROUP:
			case IMPLICIT:
			case INT:
			case INTERFACE:
			case INTO:
			case JOIN:
			case LET:
			case LONG:
			case NAMEOF:
			case OBJECT:
			case ON:
			case ORDERBY:
			case PARTIAL:
			case READONLY:
			case REF:
			case REMOVE:
			case SBYTE:
			case SELECT:
			case SET:
			case SHORT:
			case STRING:
			case STRUCT:
			case UINT:
			case ULONG:
			case UNMANAGED:
			case USHORT:
			case VAR:
			case VOID:
			case WHEN:
			case WHERE:
			case YIELD:
			case IDENTIFIER:
			case OPEN_PARENS:
				{
				setState(2027);
				common_member_declaration();
				}
				break;
			case FIXED:
				{
				setState(2028);
				match(FIXED);
				setState(2029);
				type_();
				setState(2031); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(2030);
					fixed_size_buffer_declarator();
					}
					}
					setState(2033); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ADD) | (1L << ALIAS) | (1L << ARGLIST) | (1L << ASCENDING) | (1L << ASYNC) | (1L << AWAIT) | (1L << BY) | (1L << DESCENDING) | (1L << DYNAMIC) | (1L << EQUALS) | (1L << FROM) | (1L << GET) | (1L << GROUP) | (1L << INTO) | (1L << JOIN) | (1L << LET))) != 0) || ((((_la - 65)) & ~0x3f) == 0 && ((1L << (_la - 65)) & ((1L << (NAMEOF - 65)) | (1L << (ON - 65)) | (1L << (ORDERBY - 65)) | (1L << (PARTIAL - 65)) | (1L << (REMOVE - 65)) | (1L << (SELECT - 65)) | (1L << (SET - 65)) | (1L << (UNMANAGED - 65)) | (1L << (VAR - 65)) | (1L << (WHEN - 65)) | (1L << (WHERE - 65)) | (1L << (YIELD - 65)) | (1L << (IDENTIFIER - 65)))) != 0) );
				setState(2035);
				match(SEMICOLON);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Array_typeContext extends ParserRuleContext {
		public Base_typeContext base_type() {
			return getRuleContext(Base_typeContext.class,0);
		}
		public List<Rank_specifierContext> rank_specifier() {
			return getRuleContexts(Rank_specifierContext.class);
		}
		public Rank_specifierContext rank_specifier(int i) {
			return getRuleContext(Rank_specifierContext.class,i);
		}
		public List<TerminalNode> STAR() { return getTokens(CSharpParser.STAR); }
		public TerminalNode STAR(int i) {
			return getToken(CSharpParser.STAR, i);
		}
		public List<TerminalNode> INTERR() { return getTokens(CSharpParser.INTERR); }
		public TerminalNode INTERR(int i) {
			return getToken(CSharpParser.INTERR, i);
		}
		public Array_typeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_array_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterArray_type(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitArray_type(this);
		}
	}

	public final Array_typeContext array_type() throws RecognitionException {
		Array_typeContext _localctx = new Array_typeContext(_ctx, getState());
		enterRule(_localctx, 320, RULE_array_type);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2039);
			base_type();
			setState(2047); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(2043);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==STAR || _la==INTERR) {
					{
					{
					setState(2040);
					_la = _input.LA(1);
					if ( !(_la==STAR || _la==INTERR) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					}
					}
					setState(2045);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(2046);
				rank_specifier();
				}
				}
				setState(2049); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( ((((_la - 131)) & ~0x3f) == 0 && ((1L << (_la - 131)) & ((1L << (OPEN_BRACKET - 131)) | (1L << (STAR - 131)) | (1L << (INTERR - 131)))) != 0) );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Rank_specifierContext extends ParserRuleContext {
		public TerminalNode OPEN_BRACKET() { return getToken(CSharpParser.OPEN_BRACKET, 0); }
		public TerminalNode CLOSE_BRACKET() { return getToken(CSharpParser.CLOSE_BRACKET, 0); }
		public List<TerminalNode> COMMA() { return getTokens(CSharpParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(CSharpParser.COMMA, i);
		}
		public Rank_specifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rank_specifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterRank_specifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitRank_specifier(this);
		}
	}

	public final Rank_specifierContext rank_specifier() throws RecognitionException {
		Rank_specifierContext _localctx = new Rank_specifierContext(_ctx, getState());
		enterRule(_localctx, 322, RULE_rank_specifier);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2051);
			match(OPEN_BRACKET);
			setState(2055);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(2052);
				match(COMMA);
				}
				}
				setState(2057);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(2058);
			match(CLOSE_BRACKET);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Array_initializerContext extends ParserRuleContext {
		public TerminalNode OPEN_BRACE() { return getToken(CSharpParser.OPEN_BRACE, 0); }
		public TerminalNode CLOSE_BRACE() { return getToken(CSharpParser.CLOSE_BRACE, 0); }
		public List<Variable_initializerContext> variable_initializer() {
			return getRuleContexts(Variable_initializerContext.class);
		}
		public Variable_initializerContext variable_initializer(int i) {
			return getRuleContext(Variable_initializerContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(CSharpParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(CSharpParser.COMMA, i);
		}
		public Array_initializerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_array_initializer; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterArray_initializer(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitArray_initializer(this);
		}
	}

	public final Array_initializerContext array_initializer() throws RecognitionException {
		Array_initializerContext _localctx = new Array_initializerContext(_ctx, getState());
		enterRule(_localctx, 324, RULE_array_initializer);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(2060);
			match(OPEN_BRACE);
			setState(2072);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ADD) | (1L << ALIAS) | (1L << ARGLIST) | (1L << ASCENDING) | (1L << ASYNC) | (1L << AWAIT) | (1L << BASE) | (1L << BOOL) | (1L << BY) | (1L << BYTE) | (1L << CHAR) | (1L << CHECKED) | (1L << DECIMAL) | (1L << DEFAULT) | (1L << DELEGATE) | (1L << DESCENDING) | (1L << DOUBLE) | (1L << DYNAMIC) | (1L << EQUALS) | (1L << FALSE) | (1L << FLOAT) | (1L << FROM) | (1L << GET) | (1L << GROUP) | (1L << INT) | (1L << INTO) | (1L << JOIN) | (1L << LET))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (LONG - 64)) | (1L << (NAMEOF - 64)) | (1L << (NEW - 64)) | (1L << (NULL_ - 64)) | (1L << (OBJECT - 64)) | (1L << (ON - 64)) | (1L << (ORDERBY - 64)) | (1L << (PARTIAL - 64)) | (1L << (REF - 64)) | (1L << (REMOVE - 64)) | (1L << (SBYTE - 64)) | (1L << (SELECT - 64)) | (1L << (SET - 64)) | (1L << (SHORT - 64)) | (1L << (SIZEOF - 64)) | (1L << (STRING - 64)) | (1L << (THIS - 64)) | (1L << (TRUE - 64)) | (1L << (TYPEOF - 64)) | (1L << (UINT - 64)) | (1L << (ULONG - 64)) | (1L << (UNCHECKED - 64)) | (1L << (UNMANAGED - 64)) | (1L << (USHORT - 64)) | (1L << (VAR - 64)) | (1L << (WHEN - 64)) | (1L << (WHERE - 64)) | (1L << (YIELD - 64)) | (1L << (IDENTIFIER - 64)) | (1L << (LITERAL_ACCESS - 64)) | (1L << (INTEGER_LITERAL - 64)) | (1L << (HEX_INTEGER_LITERAL - 64)) | (1L << (BIN_INTEGER_LITERAL - 64)) | (1L << (REAL_LITERAL - 64)) | (1L << (CHARACTER_LITERAL - 64)) | (1L << (REGULAR_STRING - 64)) | (1L << (VERBATIUM_STRING - 64)) | (1L << (INTERPOLATED_REGULAR_STRING_START - 64)))) != 0) || ((((_la - 128)) & ~0x3f) == 0 && ((1L << (_la - 128)) & ((1L << (INTERPOLATED_VERBATIUM_STRING_START - 128)) | (1L << (OPEN_BRACE - 128)) | (1L << (OPEN_PARENS - 128)) | (1L << (PLUS - 128)) | (1L << (MINUS - 128)) | (1L << (STAR - 128)) | (1L << (AMP - 128)) | (1L << (CARET - 128)) | (1L << (BANG - 128)) | (1L << (TILDE - 128)) | (1L << (OP_INC - 128)) | (1L << (OP_DEC - 128)) | (1L << (OP_RANGE - 128)))) != 0)) {
				{
				setState(2061);
				variable_initializer();
				setState(2066);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,247,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(2062);
						match(COMMA);
						setState(2063);
						variable_initializer();
						}
						} 
					}
					setState(2068);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,247,_ctx);
				}
				setState(2070);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==COMMA) {
					{
					setState(2069);
					match(COMMA);
					}
				}

				}
			}

			setState(2074);
			match(CLOSE_BRACE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Variant_type_parameter_listContext extends ParserRuleContext {
		public TerminalNode LT() { return getToken(CSharpParser.LT, 0); }
		public List<Variant_type_parameterContext> variant_type_parameter() {
			return getRuleContexts(Variant_type_parameterContext.class);
		}
		public Variant_type_parameterContext variant_type_parameter(int i) {
			return getRuleContext(Variant_type_parameterContext.class,i);
		}
		public TerminalNode GT() { return getToken(CSharpParser.GT, 0); }
		public List<TerminalNode> COMMA() { return getTokens(CSharpParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(CSharpParser.COMMA, i);
		}
		public Variant_type_parameter_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variant_type_parameter_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterVariant_type_parameter_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitVariant_type_parameter_list(this);
		}
	}

	public final Variant_type_parameter_listContext variant_type_parameter_list() throws RecognitionException {
		Variant_type_parameter_listContext _localctx = new Variant_type_parameter_listContext(_ctx, getState());
		enterRule(_localctx, 326, RULE_variant_type_parameter_list);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2076);
			match(LT);
			setState(2077);
			variant_type_parameter();
			setState(2082);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(2078);
				match(COMMA);
				setState(2079);
				variant_type_parameter();
				}
				}
				setState(2084);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(2085);
			match(GT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Variant_type_parameterContext extends ParserRuleContext {
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public AttributesContext attributes() {
			return getRuleContext(AttributesContext.class,0);
		}
		public Variance_annotationContext variance_annotation() {
			return getRuleContext(Variance_annotationContext.class,0);
		}
		public Variant_type_parameterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variant_type_parameter; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterVariant_type_parameter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitVariant_type_parameter(this);
		}
	}

	public final Variant_type_parameterContext variant_type_parameter() throws RecognitionException {
		Variant_type_parameterContext _localctx = new Variant_type_parameterContext(_ctx, getState());
		enterRule(_localctx, 328, RULE_variant_type_parameter);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2088);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==OPEN_BRACKET) {
				{
				setState(2087);
				attributes();
				}
			}

			setState(2091);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==IN || _la==OUT) {
				{
				setState(2090);
				variance_annotation();
				}
			}

			setState(2093);
			identifier();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Variance_annotationContext extends ParserRuleContext {
		public TerminalNode IN() { return getToken(CSharpParser.IN, 0); }
		public TerminalNode OUT() { return getToken(CSharpParser.OUT, 0); }
		public Variance_annotationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variance_annotation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterVariance_annotation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitVariance_annotation(this);
		}
	}

	public final Variance_annotationContext variance_annotation() throws RecognitionException {
		Variance_annotationContext _localctx = new Variance_annotationContext(_ctx, getState());
		enterRule(_localctx, 330, RULE_variance_annotation);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2095);
			_la = _input.LA(1);
			if ( !(_la==IN || _la==OUT) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Interface_baseContext extends ParserRuleContext {
		public TerminalNode COLON() { return getToken(CSharpParser.COLON, 0); }
		public Interface_type_listContext interface_type_list() {
			return getRuleContext(Interface_type_listContext.class,0);
		}
		public Interface_baseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_interface_base; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterInterface_base(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitInterface_base(this);
		}
	}

	public final Interface_baseContext interface_base() throws RecognitionException {
		Interface_baseContext _localctx = new Interface_baseContext(_ctx, getState());
		enterRule(_localctx, 332, RULE_interface_base);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2097);
			match(COLON);
			setState(2098);
			interface_type_list();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Interface_bodyContext extends ParserRuleContext {
		public TerminalNode OPEN_BRACE() { return getToken(CSharpParser.OPEN_BRACE, 0); }
		public TerminalNode CLOSE_BRACE() { return getToken(CSharpParser.CLOSE_BRACE, 0); }
		public List<Interface_member_declarationContext> interface_member_declaration() {
			return getRuleContexts(Interface_member_declarationContext.class);
		}
		public Interface_member_declarationContext interface_member_declaration(int i) {
			return getRuleContext(Interface_member_declarationContext.class,i);
		}
		public Interface_bodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_interface_body; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterInterface_body(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitInterface_body(this);
		}
	}

	public final Interface_bodyContext interface_body() throws RecognitionException {
		Interface_bodyContext _localctx = new Interface_bodyContext(_ctx, getState());
		enterRule(_localctx, 334, RULE_interface_body);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2100);
			match(OPEN_BRACE);
			setState(2104);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (((((_la - 10)) & ~0x3f) == 0 && ((1L << (_la - 10)) & ((1L << (ADD - 10)) | (1L << (ALIAS - 10)) | (1L << (ARGLIST - 10)) | (1L << (ASCENDING - 10)) | (1L << (ASYNC - 10)) | (1L << (AWAIT - 10)) | (1L << (BOOL - 10)) | (1L << (BY - 10)) | (1L << (BYTE - 10)) | (1L << (CHAR - 10)) | (1L << (DECIMAL - 10)) | (1L << (DESCENDING - 10)) | (1L << (DOUBLE - 10)) | (1L << (DYNAMIC - 10)) | (1L << (EQUALS - 10)) | (1L << (EVENT - 10)) | (1L << (FLOAT - 10)) | (1L << (FROM - 10)) | (1L << (GET - 10)) | (1L << (GROUP - 10)) | (1L << (INT - 10)) | (1L << (INTO - 10)) | (1L << (JOIN - 10)) | (1L << (LET - 10)) | (1L << (LONG - 10)) | (1L << (NAMEOF - 10)) | (1L << (NEW - 10)) | (1L << (OBJECT - 10)) | (1L << (ON - 10)))) != 0) || ((((_la - 74)) & ~0x3f) == 0 && ((1L << (_la - 74)) & ((1L << (ORDERBY - 74)) | (1L << (PARTIAL - 74)) | (1L << (READONLY - 74)) | (1L << (REF - 74)) | (1L << (REMOVE - 74)) | (1L << (SBYTE - 74)) | (1L << (SELECT - 74)) | (1L << (SET - 74)) | (1L << (SHORT - 74)) | (1L << (STRING - 74)) | (1L << (UINT - 74)) | (1L << (ULONG - 74)) | (1L << (UNMANAGED - 74)) | (1L << (UNSAFE - 74)) | (1L << (USHORT - 74)) | (1L << (VAR - 74)) | (1L << (VOID - 74)) | (1L << (WHEN - 74)) | (1L << (WHERE - 74)) | (1L << (YIELD - 74)) | (1L << (IDENTIFIER - 74)) | (1L << (OPEN_BRACKET - 74)) | (1L << (OPEN_PARENS - 74)))) != 0)) {
				{
				{
				setState(2101);
				interface_member_declaration();
				}
				}
				setState(2106);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(2107);
			match(CLOSE_BRACE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Interface_member_declarationContext extends ParserRuleContext {
		public Type_Context type_() {
			return getRuleContext(Type_Context.class,0);
		}
		public TerminalNode VOID() { return getToken(CSharpParser.VOID, 0); }
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TerminalNode OPEN_PARENS() { return getToken(CSharpParser.OPEN_PARENS, 0); }
		public TerminalNode CLOSE_PARENS() { return getToken(CSharpParser.CLOSE_PARENS, 0); }
		public TerminalNode SEMICOLON() { return getToken(CSharpParser.SEMICOLON, 0); }
		public TerminalNode EVENT() { return getToken(CSharpParser.EVENT, 0); }
		public AttributesContext attributes() {
			return getRuleContext(AttributesContext.class,0);
		}
		public TerminalNode NEW() { return getToken(CSharpParser.NEW, 0); }
		public TerminalNode OPEN_BRACE() { return getToken(CSharpParser.OPEN_BRACE, 0); }
		public Interface_accessorsContext interface_accessors() {
			return getRuleContext(Interface_accessorsContext.class,0);
		}
		public TerminalNode CLOSE_BRACE() { return getToken(CSharpParser.CLOSE_BRACE, 0); }
		public TerminalNode THIS() { return getToken(CSharpParser.THIS, 0); }
		public TerminalNode OPEN_BRACKET() { return getToken(CSharpParser.OPEN_BRACKET, 0); }
		public Formal_parameter_listContext formal_parameter_list() {
			return getRuleContext(Formal_parameter_listContext.class,0);
		}
		public TerminalNode CLOSE_BRACKET() { return getToken(CSharpParser.CLOSE_BRACKET, 0); }
		public TerminalNode UNSAFE() { return getToken(CSharpParser.UNSAFE, 0); }
		public TerminalNode REF() { return getToken(CSharpParser.REF, 0); }
		public TerminalNode READONLY() { return getToken(CSharpParser.READONLY, 0); }
		public Type_parameter_listContext type_parameter_list() {
			return getRuleContext(Type_parameter_listContext.class,0);
		}
		public Type_parameter_constraints_clausesContext type_parameter_constraints_clauses() {
			return getRuleContext(Type_parameter_constraints_clausesContext.class,0);
		}
		public Interface_member_declarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_interface_member_declaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterInterface_member_declaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitInterface_member_declaration(this);
		}
	}

	public final Interface_member_declarationContext interface_member_declaration() throws RecognitionException {
		Interface_member_declarationContext _localctx = new Interface_member_declarationContext(_ctx, getState());
		enterRule(_localctx, 336, RULE_interface_member_declaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2110);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==OPEN_BRACKET) {
				{
				setState(2109);
				attributes();
				}
			}

			setState(2113);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==NEW) {
				{
				setState(2112);
				match(NEW);
				}
			}

			setState(2178);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,266,_ctx) ) {
			case 1:
				{
				setState(2116);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==UNSAFE) {
					{
					setState(2115);
					match(UNSAFE);
					}
				}

				setState(2123);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,257,_ctx) ) {
				case 1:
					{
					setState(2118);
					match(REF);
					}
					break;
				case 2:
					{
					setState(2119);
					match(REF);
					setState(2120);
					match(READONLY);
					}
					break;
				case 3:
					{
					setState(2121);
					match(READONLY);
					setState(2122);
					match(REF);
					}
					break;
				}
				setState(2125);
				type_();
				setState(2153);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,261,_ctx) ) {
				case 1:
					{
					setState(2126);
					identifier();
					setState(2128);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==LT) {
						{
						setState(2127);
						type_parameter_list();
						}
					}

					setState(2130);
					match(OPEN_PARENS);
					setState(2132);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (((((_la - 10)) & ~0x3f) == 0 && ((1L << (_la - 10)) & ((1L << (ADD - 10)) | (1L << (ALIAS - 10)) | (1L << (ARGLIST - 10)) | (1L << (ASCENDING - 10)) | (1L << (ASYNC - 10)) | (1L << (AWAIT - 10)) | (1L << (BOOL - 10)) | (1L << (BY - 10)) | (1L << (BYTE - 10)) | (1L << (CHAR - 10)) | (1L << (DECIMAL - 10)) | (1L << (DESCENDING - 10)) | (1L << (DOUBLE - 10)) | (1L << (DYNAMIC - 10)) | (1L << (EQUALS - 10)) | (1L << (FLOAT - 10)) | (1L << (FROM - 10)) | (1L << (GET - 10)) | (1L << (GROUP - 10)) | (1L << (IN - 10)) | (1L << (INT - 10)) | (1L << (INTO - 10)) | (1L << (JOIN - 10)) | (1L << (LET - 10)) | (1L << (LONG - 10)) | (1L << (NAMEOF - 10)) | (1L << (OBJECT - 10)) | (1L << (ON - 10)))) != 0) || ((((_la - 74)) & ~0x3f) == 0 && ((1L << (_la - 74)) & ((1L << (ORDERBY - 74)) | (1L << (OUT - 74)) | (1L << (PARAMS - 74)) | (1L << (PARTIAL - 74)) | (1L << (REF - 74)) | (1L << (REMOVE - 74)) | (1L << (SBYTE - 74)) | (1L << (SELECT - 74)) | (1L << (SET - 74)) | (1L << (SHORT - 74)) | (1L << (STRING - 74)) | (1L << (THIS - 74)) | (1L << (UINT - 74)) | (1L << (ULONG - 74)) | (1L << (UNMANAGED - 74)) | (1L << (USHORT - 74)) | (1L << (VAR - 74)) | (1L << (VOID - 74)) | (1L << (WHEN - 74)) | (1L << (WHERE - 74)) | (1L << (YIELD - 74)) | (1L << (IDENTIFIER - 74)) | (1L << (OPEN_BRACKET - 74)) | (1L << (OPEN_PARENS - 74)))) != 0)) {
						{
						setState(2131);
						formal_parameter_list();
						}
					}

					setState(2134);
					match(CLOSE_PARENS);
					setState(2136);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==WHERE) {
						{
						setState(2135);
						type_parameter_constraints_clauses();
						}
					}

					setState(2138);
					match(SEMICOLON);
					}
					break;
				case 2:
					{
					setState(2140);
					identifier();
					setState(2141);
					match(OPEN_BRACE);
					setState(2142);
					interface_accessors();
					setState(2143);
					match(CLOSE_BRACE);
					}
					break;
				case 3:
					{
					setState(2145);
					match(THIS);
					setState(2146);
					match(OPEN_BRACKET);
					setState(2147);
					formal_parameter_list();
					setState(2148);
					match(CLOSE_BRACKET);
					setState(2149);
					match(OPEN_BRACE);
					setState(2150);
					interface_accessors();
					setState(2151);
					match(CLOSE_BRACE);
					}
					break;
				}
				}
				break;
			case 2:
				{
				setState(2156);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==UNSAFE) {
					{
					setState(2155);
					match(UNSAFE);
					}
				}

				setState(2158);
				match(VOID);
				setState(2159);
				identifier();
				setState(2161);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==LT) {
					{
					setState(2160);
					type_parameter_list();
					}
				}

				setState(2163);
				match(OPEN_PARENS);
				setState(2165);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (((((_la - 10)) & ~0x3f) == 0 && ((1L << (_la - 10)) & ((1L << (ADD - 10)) | (1L << (ALIAS - 10)) | (1L << (ARGLIST - 10)) | (1L << (ASCENDING - 10)) | (1L << (ASYNC - 10)) | (1L << (AWAIT - 10)) | (1L << (BOOL - 10)) | (1L << (BY - 10)) | (1L << (BYTE - 10)) | (1L << (CHAR - 10)) | (1L << (DECIMAL - 10)) | (1L << (DESCENDING - 10)) | (1L << (DOUBLE - 10)) | (1L << (DYNAMIC - 10)) | (1L << (EQUALS - 10)) | (1L << (FLOAT - 10)) | (1L << (FROM - 10)) | (1L << (GET - 10)) | (1L << (GROUP - 10)) | (1L << (IN - 10)) | (1L << (INT - 10)) | (1L << (INTO - 10)) | (1L << (JOIN - 10)) | (1L << (LET - 10)) | (1L << (LONG - 10)) | (1L << (NAMEOF - 10)) | (1L << (OBJECT - 10)) | (1L << (ON - 10)))) != 0) || ((((_la - 74)) & ~0x3f) == 0 && ((1L << (_la - 74)) & ((1L << (ORDERBY - 74)) | (1L << (OUT - 74)) | (1L << (PARAMS - 74)) | (1L << (PARTIAL - 74)) | (1L << (REF - 74)) | (1L << (REMOVE - 74)) | (1L << (SBYTE - 74)) | (1L << (SELECT - 74)) | (1L << (SET - 74)) | (1L << (SHORT - 74)) | (1L << (STRING - 74)) | (1L << (THIS - 74)) | (1L << (UINT - 74)) | (1L << (ULONG - 74)) | (1L << (UNMANAGED - 74)) | (1L << (USHORT - 74)) | (1L << (VAR - 74)) | (1L << (VOID - 74)) | (1L << (WHEN - 74)) | (1L << (WHERE - 74)) | (1L << (YIELD - 74)) | (1L << (IDENTIFIER - 74)) | (1L << (OPEN_BRACKET - 74)) | (1L << (OPEN_PARENS - 74)))) != 0)) {
					{
					setState(2164);
					formal_parameter_list();
					}
				}

				setState(2167);
				match(CLOSE_PARENS);
				setState(2169);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==WHERE) {
					{
					setState(2168);
					type_parameter_constraints_clauses();
					}
				}

				setState(2171);
				match(SEMICOLON);
				}
				break;
			case 3:
				{
				setState(2173);
				match(EVENT);
				setState(2174);
				type_();
				setState(2175);
				identifier();
				setState(2176);
				match(SEMICOLON);
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Interface_accessorsContext extends ParserRuleContext {
		public TerminalNode GET() { return getToken(CSharpParser.GET, 0); }
		public List<TerminalNode> SEMICOLON() { return getTokens(CSharpParser.SEMICOLON); }
		public TerminalNode SEMICOLON(int i) {
			return getToken(CSharpParser.SEMICOLON, i);
		}
		public TerminalNode SET() { return getToken(CSharpParser.SET, 0); }
		public List<AttributesContext> attributes() {
			return getRuleContexts(AttributesContext.class);
		}
		public AttributesContext attributes(int i) {
			return getRuleContext(AttributesContext.class,i);
		}
		public Interface_accessorsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_interface_accessors; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterInterface_accessors(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitInterface_accessors(this);
		}
	}

	public final Interface_accessorsContext interface_accessors() throws RecognitionException {
		Interface_accessorsContext _localctx = new Interface_accessorsContext(_ctx, getState());
		enterRule(_localctx, 338, RULE_interface_accessors);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2181);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==OPEN_BRACKET) {
				{
				setState(2180);
				attributes();
				}
			}

			setState(2201);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case GET:
				{
				setState(2183);
				match(GET);
				setState(2184);
				match(SEMICOLON);
				setState(2190);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==SET || _la==OPEN_BRACKET) {
					{
					setState(2186);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==OPEN_BRACKET) {
						{
						setState(2185);
						attributes();
						}
					}

					setState(2188);
					match(SET);
					setState(2189);
					match(SEMICOLON);
					}
				}

				}
				break;
			case SET:
				{
				setState(2192);
				match(SET);
				setState(2193);
				match(SEMICOLON);
				setState(2199);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==GET || _la==OPEN_BRACKET) {
					{
					setState(2195);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==OPEN_BRACKET) {
						{
						setState(2194);
						attributes();
						}
					}

					setState(2197);
					match(GET);
					setState(2198);
					match(SEMICOLON);
					}
				}

				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Enum_baseContext extends ParserRuleContext {
		public TerminalNode COLON() { return getToken(CSharpParser.COLON, 0); }
		public Type_Context type_() {
			return getRuleContext(Type_Context.class,0);
		}
		public Enum_baseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_enum_base; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterEnum_base(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitEnum_base(this);
		}
	}

	public final Enum_baseContext enum_base() throws RecognitionException {
		Enum_baseContext _localctx = new Enum_baseContext(_ctx, getState());
		enterRule(_localctx, 340, RULE_enum_base);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2203);
			match(COLON);
			setState(2204);
			type_();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Enum_bodyContext extends ParserRuleContext {
		public TerminalNode OPEN_BRACE() { return getToken(CSharpParser.OPEN_BRACE, 0); }
		public TerminalNode CLOSE_BRACE() { return getToken(CSharpParser.CLOSE_BRACE, 0); }
		public List<Enum_member_declarationContext> enum_member_declaration() {
			return getRuleContexts(Enum_member_declarationContext.class);
		}
		public Enum_member_declarationContext enum_member_declaration(int i) {
			return getRuleContext(Enum_member_declarationContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(CSharpParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(CSharpParser.COMMA, i);
		}
		public Enum_bodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_enum_body; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterEnum_body(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitEnum_body(this);
		}
	}

	public final Enum_bodyContext enum_body() throws RecognitionException {
		Enum_bodyContext _localctx = new Enum_bodyContext(_ctx, getState());
		enterRule(_localctx, 342, RULE_enum_body);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(2206);
			match(OPEN_BRACE);
			setState(2218);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (((((_la - 10)) & ~0x3f) == 0 && ((1L << (_la - 10)) & ((1L << (ADD - 10)) | (1L << (ALIAS - 10)) | (1L << (ARGLIST - 10)) | (1L << (ASCENDING - 10)) | (1L << (ASYNC - 10)) | (1L << (AWAIT - 10)) | (1L << (BY - 10)) | (1L << (DESCENDING - 10)) | (1L << (DYNAMIC - 10)) | (1L << (EQUALS - 10)) | (1L << (FROM - 10)) | (1L << (GET - 10)) | (1L << (GROUP - 10)) | (1L << (INTO - 10)) | (1L << (JOIN - 10)) | (1L << (LET - 10)) | (1L << (NAMEOF - 10)) | (1L << (ON - 10)))) != 0) || ((((_la - 74)) & ~0x3f) == 0 && ((1L << (_la - 74)) & ((1L << (ORDERBY - 74)) | (1L << (PARTIAL - 74)) | (1L << (REMOVE - 74)) | (1L << (SELECT - 74)) | (1L << (SET - 74)) | (1L << (UNMANAGED - 74)) | (1L << (VAR - 74)) | (1L << (WHEN - 74)) | (1L << (WHERE - 74)) | (1L << (YIELD - 74)) | (1L << (IDENTIFIER - 74)) | (1L << (OPEN_BRACKET - 74)))) != 0)) {
				{
				setState(2207);
				enum_member_declaration();
				setState(2212);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,273,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(2208);
						match(COMMA);
						setState(2209);
						enum_member_declaration();
						}
						} 
					}
					setState(2214);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,273,_ctx);
				}
				setState(2216);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==COMMA) {
					{
					setState(2215);
					match(COMMA);
					}
				}

				}
			}

			setState(2220);
			match(CLOSE_BRACE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Enum_member_declarationContext extends ParserRuleContext {
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public AttributesContext attributes() {
			return getRuleContext(AttributesContext.class,0);
		}
		public TerminalNode ASSIGNMENT() { return getToken(CSharpParser.ASSIGNMENT, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public Enum_member_declarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_enum_member_declaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterEnum_member_declaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitEnum_member_declaration(this);
		}
	}

	public final Enum_member_declarationContext enum_member_declaration() throws RecognitionException {
		Enum_member_declarationContext _localctx = new Enum_member_declarationContext(_ctx, getState());
		enterRule(_localctx, 344, RULE_enum_member_declaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2223);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==OPEN_BRACKET) {
				{
				setState(2222);
				attributes();
				}
			}

			setState(2225);
			identifier();
			setState(2228);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ASSIGNMENT) {
				{
				setState(2226);
				match(ASSIGNMENT);
				setState(2227);
				expression();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Global_attribute_sectionContext extends ParserRuleContext {
		public TerminalNode OPEN_BRACKET() { return getToken(CSharpParser.OPEN_BRACKET, 0); }
		public Global_attribute_targetContext global_attribute_target() {
			return getRuleContext(Global_attribute_targetContext.class,0);
		}
		public TerminalNode COLON() { return getToken(CSharpParser.COLON, 0); }
		public Attribute_listContext attribute_list() {
			return getRuleContext(Attribute_listContext.class,0);
		}
		public TerminalNode CLOSE_BRACKET() { return getToken(CSharpParser.CLOSE_BRACKET, 0); }
		public TerminalNode COMMA() { return getToken(CSharpParser.COMMA, 0); }
		public Global_attribute_sectionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_global_attribute_section; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterGlobal_attribute_section(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitGlobal_attribute_section(this);
		}
	}

	public final Global_attribute_sectionContext global_attribute_section() throws RecognitionException {
		Global_attribute_sectionContext _localctx = new Global_attribute_sectionContext(_ctx, getState());
		enterRule(_localctx, 346, RULE_global_attribute_section);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2230);
			match(OPEN_BRACKET);
			setState(2231);
			global_attribute_target();
			setState(2232);
			match(COLON);
			setState(2233);
			attribute_list();
			setState(2235);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==COMMA) {
				{
				setState(2234);
				match(COMMA);
				}
			}

			setState(2237);
			match(CLOSE_BRACKET);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Global_attribute_targetContext extends ParserRuleContext {
		public KeywordContext keyword() {
			return getRuleContext(KeywordContext.class,0);
		}
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public Global_attribute_targetContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_global_attribute_target; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterGlobal_attribute_target(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitGlobal_attribute_target(this);
		}
	}

	public final Global_attribute_targetContext global_attribute_target() throws RecognitionException {
		Global_attribute_targetContext _localctx = new Global_attribute_targetContext(_ctx, getState());
		enterRule(_localctx, 348, RULE_global_attribute_target);
		try {
			setState(2241);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,279,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(2239);
				keyword();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(2240);
				identifier();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AttributesContext extends ParserRuleContext {
		public List<Attribute_sectionContext> attribute_section() {
			return getRuleContexts(Attribute_sectionContext.class);
		}
		public Attribute_sectionContext attribute_section(int i) {
			return getRuleContext(Attribute_sectionContext.class,i);
		}
		public AttributesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_attributes; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterAttributes(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitAttributes(this);
		}
	}

	public final AttributesContext attributes() throws RecognitionException {
		AttributesContext _localctx = new AttributesContext(_ctx, getState());
		enterRule(_localctx, 350, RULE_attributes);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2244); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(2243);
				attribute_section();
				}
				}
				setState(2246); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==OPEN_BRACKET );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Attribute_sectionContext extends ParserRuleContext {
		public TerminalNode OPEN_BRACKET() { return getToken(CSharpParser.OPEN_BRACKET, 0); }
		public Attribute_listContext attribute_list() {
			return getRuleContext(Attribute_listContext.class,0);
		}
		public TerminalNode CLOSE_BRACKET() { return getToken(CSharpParser.CLOSE_BRACKET, 0); }
		public Attribute_targetContext attribute_target() {
			return getRuleContext(Attribute_targetContext.class,0);
		}
		public TerminalNode COLON() { return getToken(CSharpParser.COLON, 0); }
		public TerminalNode COMMA() { return getToken(CSharpParser.COMMA, 0); }
		public Attribute_sectionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_attribute_section; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterAttribute_section(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitAttribute_section(this);
		}
	}

	public final Attribute_sectionContext attribute_section() throws RecognitionException {
		Attribute_sectionContext _localctx = new Attribute_sectionContext(_ctx, getState());
		enterRule(_localctx, 352, RULE_attribute_section);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2248);
			match(OPEN_BRACKET);
			setState(2252);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,281,_ctx) ) {
			case 1:
				{
				setState(2249);
				attribute_target();
				setState(2250);
				match(COLON);
				}
				break;
			}
			setState(2254);
			attribute_list();
			setState(2256);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==COMMA) {
				{
				setState(2255);
				match(COMMA);
				}
			}

			setState(2258);
			match(CLOSE_BRACKET);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Attribute_targetContext extends ParserRuleContext {
		public KeywordContext keyword() {
			return getRuleContext(KeywordContext.class,0);
		}
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public Attribute_targetContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_attribute_target; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterAttribute_target(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitAttribute_target(this);
		}
	}

	public final Attribute_targetContext attribute_target() throws RecognitionException {
		Attribute_targetContext _localctx = new Attribute_targetContext(_ctx, getState());
		enterRule(_localctx, 354, RULE_attribute_target);
		try {
			setState(2262);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,283,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(2260);
				keyword();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(2261);
				identifier();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Attribute_listContext extends ParserRuleContext {
		public List<AttributeContext> attribute() {
			return getRuleContexts(AttributeContext.class);
		}
		public AttributeContext attribute(int i) {
			return getRuleContext(AttributeContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(CSharpParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(CSharpParser.COMMA, i);
		}
		public Attribute_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_attribute_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterAttribute_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitAttribute_list(this);
		}
	}

	public final Attribute_listContext attribute_list() throws RecognitionException {
		Attribute_listContext _localctx = new Attribute_listContext(_ctx, getState());
		enterRule(_localctx, 356, RULE_attribute_list);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(2264);
			attribute();
			setState(2269);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,284,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(2265);
					match(COMMA);
					setState(2266);
					attribute();
					}
					} 
				}
				setState(2271);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,284,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AttributeContext extends ParserRuleContext {
		public Namespace_or_type_nameContext namespace_or_type_name() {
			return getRuleContext(Namespace_or_type_nameContext.class,0);
		}
		public TerminalNode OPEN_PARENS() { return getToken(CSharpParser.OPEN_PARENS, 0); }
		public TerminalNode CLOSE_PARENS() { return getToken(CSharpParser.CLOSE_PARENS, 0); }
		public List<Attribute_argumentContext> attribute_argument() {
			return getRuleContexts(Attribute_argumentContext.class);
		}
		public Attribute_argumentContext attribute_argument(int i) {
			return getRuleContext(Attribute_argumentContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(CSharpParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(CSharpParser.COMMA, i);
		}
		public AttributeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_attribute; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterAttribute(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitAttribute(this);
		}
	}

	public final AttributeContext attribute() throws RecognitionException {
		AttributeContext _localctx = new AttributeContext(_ctx, getState());
		enterRule(_localctx, 358, RULE_attribute);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2272);
			namespace_or_type_name();
			setState(2285);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==OPEN_PARENS) {
				{
				setState(2273);
				match(OPEN_PARENS);
				setState(2282);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ADD) | (1L << ALIAS) | (1L << ARGLIST) | (1L << ASCENDING) | (1L << ASYNC) | (1L << AWAIT) | (1L << BASE) | (1L << BOOL) | (1L << BY) | (1L << BYTE) | (1L << CHAR) | (1L << CHECKED) | (1L << DECIMAL) | (1L << DEFAULT) | (1L << DELEGATE) | (1L << DESCENDING) | (1L << DOUBLE) | (1L << DYNAMIC) | (1L << EQUALS) | (1L << FALSE) | (1L << FLOAT) | (1L << FROM) | (1L << GET) | (1L << GROUP) | (1L << INT) | (1L << INTO) | (1L << JOIN) | (1L << LET))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (LONG - 64)) | (1L << (NAMEOF - 64)) | (1L << (NEW - 64)) | (1L << (NULL_ - 64)) | (1L << (OBJECT - 64)) | (1L << (ON - 64)) | (1L << (ORDERBY - 64)) | (1L << (PARTIAL - 64)) | (1L << (REF - 64)) | (1L << (REMOVE - 64)) | (1L << (SBYTE - 64)) | (1L << (SELECT - 64)) | (1L << (SET - 64)) | (1L << (SHORT - 64)) | (1L << (SIZEOF - 64)) | (1L << (STRING - 64)) | (1L << (THIS - 64)) | (1L << (TRUE - 64)) | (1L << (TYPEOF - 64)) | (1L << (UINT - 64)) | (1L << (ULONG - 64)) | (1L << (UNCHECKED - 64)) | (1L << (UNMANAGED - 64)) | (1L << (USHORT - 64)) | (1L << (VAR - 64)) | (1L << (WHEN - 64)) | (1L << (WHERE - 64)) | (1L << (YIELD - 64)) | (1L << (IDENTIFIER - 64)) | (1L << (LITERAL_ACCESS - 64)) | (1L << (INTEGER_LITERAL - 64)) | (1L << (HEX_INTEGER_LITERAL - 64)) | (1L << (BIN_INTEGER_LITERAL - 64)) | (1L << (REAL_LITERAL - 64)) | (1L << (CHARACTER_LITERAL - 64)) | (1L << (REGULAR_STRING - 64)) | (1L << (VERBATIUM_STRING - 64)) | (1L << (INTERPOLATED_REGULAR_STRING_START - 64)))) != 0) || ((((_la - 128)) & ~0x3f) == 0 && ((1L << (_la - 128)) & ((1L << (INTERPOLATED_VERBATIUM_STRING_START - 128)) | (1L << (OPEN_PARENS - 128)) | (1L << (PLUS - 128)) | (1L << (MINUS - 128)) | (1L << (STAR - 128)) | (1L << (AMP - 128)) | (1L << (CARET - 128)) | (1L << (BANG - 128)) | (1L << (TILDE - 128)) | (1L << (OP_INC - 128)) | (1L << (OP_DEC - 128)) | (1L << (OP_RANGE - 128)))) != 0)) {
					{
					setState(2274);
					attribute_argument();
					setState(2279);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==COMMA) {
						{
						{
						setState(2275);
						match(COMMA);
						setState(2276);
						attribute_argument();
						}
						}
						setState(2281);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(2284);
				match(CLOSE_PARENS);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Attribute_argumentContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TerminalNode COLON() { return getToken(CSharpParser.COLON, 0); }
		public Attribute_argumentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_attribute_argument; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterAttribute_argument(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitAttribute_argument(this);
		}
	}

	public final Attribute_argumentContext attribute_argument() throws RecognitionException {
		Attribute_argumentContext _localctx = new Attribute_argumentContext(_ctx, getState());
		enterRule(_localctx, 360, RULE_attribute_argument);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2290);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,288,_ctx) ) {
			case 1:
				{
				setState(2287);
				identifier();
				setState(2288);
				match(COLON);
				}
				break;
			}
			setState(2292);
			expression();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Pointer_typeContext extends ParserRuleContext {
		public TerminalNode STAR() { return getToken(CSharpParser.STAR, 0); }
		public Simple_typeContext simple_type() {
			return getRuleContext(Simple_typeContext.class,0);
		}
		public Class_typeContext class_type() {
			return getRuleContext(Class_typeContext.class,0);
		}
		public List<Rank_specifierContext> rank_specifier() {
			return getRuleContexts(Rank_specifierContext.class);
		}
		public Rank_specifierContext rank_specifier(int i) {
			return getRuleContext(Rank_specifierContext.class,i);
		}
		public List<TerminalNode> INTERR() { return getTokens(CSharpParser.INTERR); }
		public TerminalNode INTERR(int i) {
			return getToken(CSharpParser.INTERR, i);
		}
		public TerminalNode VOID() { return getToken(CSharpParser.VOID, 0); }
		public Pointer_typeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pointer_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterPointer_type(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitPointer_type(this);
		}
	}

	public final Pointer_typeContext pointer_type() throws RecognitionException {
		Pointer_typeContext _localctx = new Pointer_typeContext(_ctx, getState());
		enterRule(_localctx, 362, RULE_pointer_type);
		int _la;
		try {
			setState(2309);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ADD:
			case ALIAS:
			case ARGLIST:
			case ASCENDING:
			case ASYNC:
			case AWAIT:
			case BOOL:
			case BY:
			case BYTE:
			case CHAR:
			case DECIMAL:
			case DESCENDING:
			case DOUBLE:
			case DYNAMIC:
			case EQUALS:
			case FLOAT:
			case FROM:
			case GET:
			case GROUP:
			case INT:
			case INTO:
			case JOIN:
			case LET:
			case LONG:
			case NAMEOF:
			case OBJECT:
			case ON:
			case ORDERBY:
			case PARTIAL:
			case REMOVE:
			case SBYTE:
			case SELECT:
			case SET:
			case SHORT:
			case STRING:
			case UINT:
			case ULONG:
			case UNMANAGED:
			case USHORT:
			case VAR:
			case WHEN:
			case WHERE:
			case YIELD:
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(2296);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case BOOL:
				case BYTE:
				case CHAR:
				case DECIMAL:
				case DOUBLE:
				case FLOAT:
				case INT:
				case LONG:
				case SBYTE:
				case SHORT:
				case UINT:
				case ULONG:
				case USHORT:
					{
					setState(2294);
					simple_type();
					}
					break;
				case ADD:
				case ALIAS:
				case ARGLIST:
				case ASCENDING:
				case ASYNC:
				case AWAIT:
				case BY:
				case DESCENDING:
				case DYNAMIC:
				case EQUALS:
				case FROM:
				case GET:
				case GROUP:
				case INTO:
				case JOIN:
				case LET:
				case NAMEOF:
				case OBJECT:
				case ON:
				case ORDERBY:
				case PARTIAL:
				case REMOVE:
				case SELECT:
				case SET:
				case STRING:
				case UNMANAGED:
				case VAR:
				case WHEN:
				case WHERE:
				case YIELD:
				case IDENTIFIER:
					{
					setState(2295);
					class_type();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(2302);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==OPEN_BRACKET || _la==INTERR) {
					{
					setState(2300);
					_errHandler.sync(this);
					switch (_input.LA(1)) {
					case OPEN_BRACKET:
						{
						setState(2298);
						rank_specifier();
						}
						break;
					case INTERR:
						{
						setState(2299);
						match(INTERR);
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					}
					setState(2304);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(2305);
				match(STAR);
				}
				break;
			case VOID:
				enterOuterAlt(_localctx, 2);
				{
				setState(2307);
				match(VOID);
				setState(2308);
				match(STAR);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Fixed_pointer_declaratorsContext extends ParserRuleContext {
		public List<Fixed_pointer_declaratorContext> fixed_pointer_declarator() {
			return getRuleContexts(Fixed_pointer_declaratorContext.class);
		}
		public Fixed_pointer_declaratorContext fixed_pointer_declarator(int i) {
			return getRuleContext(Fixed_pointer_declaratorContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(CSharpParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(CSharpParser.COMMA, i);
		}
		public Fixed_pointer_declaratorsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fixed_pointer_declarators; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterFixed_pointer_declarators(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitFixed_pointer_declarators(this);
		}
	}

	public final Fixed_pointer_declaratorsContext fixed_pointer_declarators() throws RecognitionException {
		Fixed_pointer_declaratorsContext _localctx = new Fixed_pointer_declaratorsContext(_ctx, getState());
		enterRule(_localctx, 364, RULE_fixed_pointer_declarators);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2311);
			fixed_pointer_declarator();
			setState(2316);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(2312);
				match(COMMA);
				setState(2313);
				fixed_pointer_declarator();
				}
				}
				setState(2318);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Fixed_pointer_declaratorContext extends ParserRuleContext {
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TerminalNode ASSIGNMENT() { return getToken(CSharpParser.ASSIGNMENT, 0); }
		public Fixed_pointer_initializerContext fixed_pointer_initializer() {
			return getRuleContext(Fixed_pointer_initializerContext.class,0);
		}
		public Fixed_pointer_declaratorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fixed_pointer_declarator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterFixed_pointer_declarator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitFixed_pointer_declarator(this);
		}
	}

	public final Fixed_pointer_declaratorContext fixed_pointer_declarator() throws RecognitionException {
		Fixed_pointer_declaratorContext _localctx = new Fixed_pointer_declaratorContext(_ctx, getState());
		enterRule(_localctx, 366, RULE_fixed_pointer_declarator);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2319);
			identifier();
			setState(2320);
			match(ASSIGNMENT);
			setState(2321);
			fixed_pointer_initializer();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Fixed_pointer_initializerContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode AMP() { return getToken(CSharpParser.AMP, 0); }
		public Stackalloc_initializerContext stackalloc_initializer() {
			return getRuleContext(Stackalloc_initializerContext.class,0);
		}
		public Fixed_pointer_initializerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fixed_pointer_initializer; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterFixed_pointer_initializer(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitFixed_pointer_initializer(this);
		}
	}

	public final Fixed_pointer_initializerContext fixed_pointer_initializer() throws RecognitionException {
		Fixed_pointer_initializerContext _localctx = new Fixed_pointer_initializerContext(_ctx, getState());
		enterRule(_localctx, 368, RULE_fixed_pointer_initializer);
		try {
			setState(2328);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ADD:
			case ALIAS:
			case ARGLIST:
			case ASCENDING:
			case ASYNC:
			case AWAIT:
			case BASE:
			case BOOL:
			case BY:
			case BYTE:
			case CHAR:
			case CHECKED:
			case DECIMAL:
			case DEFAULT:
			case DELEGATE:
			case DESCENDING:
			case DOUBLE:
			case DYNAMIC:
			case EQUALS:
			case FALSE:
			case FLOAT:
			case FROM:
			case GET:
			case GROUP:
			case INT:
			case INTO:
			case JOIN:
			case LET:
			case LONG:
			case NAMEOF:
			case NEW:
			case NULL_:
			case OBJECT:
			case ON:
			case ORDERBY:
			case PARTIAL:
			case REF:
			case REMOVE:
			case SBYTE:
			case SELECT:
			case SET:
			case SHORT:
			case SIZEOF:
			case STRING:
			case THIS:
			case TRUE:
			case TYPEOF:
			case UINT:
			case ULONG:
			case UNCHECKED:
			case UNMANAGED:
			case USHORT:
			case VAR:
			case WHEN:
			case WHERE:
			case YIELD:
			case IDENTIFIER:
			case LITERAL_ACCESS:
			case INTEGER_LITERAL:
			case HEX_INTEGER_LITERAL:
			case BIN_INTEGER_LITERAL:
			case REAL_LITERAL:
			case CHARACTER_LITERAL:
			case REGULAR_STRING:
			case VERBATIUM_STRING:
			case INTERPOLATED_REGULAR_STRING_START:
			case INTERPOLATED_VERBATIUM_STRING_START:
			case OPEN_PARENS:
			case PLUS:
			case MINUS:
			case STAR:
			case AMP:
			case CARET:
			case BANG:
			case TILDE:
			case OP_INC:
			case OP_DEC:
			case OP_RANGE:
				enterOuterAlt(_localctx, 1);
				{
				setState(2324);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,294,_ctx) ) {
				case 1:
					{
					setState(2323);
					match(AMP);
					}
					break;
				}
				setState(2326);
				expression();
				}
				break;
			case STACKALLOC:
				enterOuterAlt(_localctx, 2);
				{
				setState(2327);
				stackalloc_initializer();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Fixed_size_buffer_declaratorContext extends ParserRuleContext {
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TerminalNode OPEN_BRACKET() { return getToken(CSharpParser.OPEN_BRACKET, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode CLOSE_BRACKET() { return getToken(CSharpParser.CLOSE_BRACKET, 0); }
		public Fixed_size_buffer_declaratorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fixed_size_buffer_declarator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterFixed_size_buffer_declarator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitFixed_size_buffer_declarator(this);
		}
	}

	public final Fixed_size_buffer_declaratorContext fixed_size_buffer_declarator() throws RecognitionException {
		Fixed_size_buffer_declaratorContext _localctx = new Fixed_size_buffer_declaratorContext(_ctx, getState());
		enterRule(_localctx, 370, RULE_fixed_size_buffer_declarator);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2330);
			identifier();
			setState(2331);
			match(OPEN_BRACKET);
			setState(2332);
			expression();
			setState(2333);
			match(CLOSE_BRACKET);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Stackalloc_initializerContext extends ParserRuleContext {
		public TerminalNode STACKALLOC() { return getToken(CSharpParser.STACKALLOC, 0); }
		public Type_Context type_() {
			return getRuleContext(Type_Context.class,0);
		}
		public TerminalNode OPEN_BRACKET() { return getToken(CSharpParser.OPEN_BRACKET, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode CLOSE_BRACKET() { return getToken(CSharpParser.CLOSE_BRACKET, 0); }
		public TerminalNode OPEN_BRACE() { return getToken(CSharpParser.OPEN_BRACE, 0); }
		public TerminalNode CLOSE_BRACE() { return getToken(CSharpParser.CLOSE_BRACE, 0); }
		public List<TerminalNode> COMMA() { return getTokens(CSharpParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(CSharpParser.COMMA, i);
		}
		public Stackalloc_initializerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stackalloc_initializer; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterStackalloc_initializer(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitStackalloc_initializer(this);
		}
	}

	public final Stackalloc_initializerContext stackalloc_initializer() throws RecognitionException {
		Stackalloc_initializerContext _localctx = new Stackalloc_initializerContext(_ctx, getState());
		enterRule(_localctx, 372, RULE_stackalloc_initializer);
		int _la;
		try {
			int _alt;
			setState(2364);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,300,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(2335);
				match(STACKALLOC);
				setState(2336);
				type_();
				setState(2337);
				match(OPEN_BRACKET);
				setState(2338);
				expression();
				setState(2339);
				match(CLOSE_BRACKET);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(2341);
				match(STACKALLOC);
				setState(2343);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (((((_la - 10)) & ~0x3f) == 0 && ((1L << (_la - 10)) & ((1L << (ADD - 10)) | (1L << (ALIAS - 10)) | (1L << (ARGLIST - 10)) | (1L << (ASCENDING - 10)) | (1L << (ASYNC - 10)) | (1L << (AWAIT - 10)) | (1L << (BOOL - 10)) | (1L << (BY - 10)) | (1L << (BYTE - 10)) | (1L << (CHAR - 10)) | (1L << (DECIMAL - 10)) | (1L << (DESCENDING - 10)) | (1L << (DOUBLE - 10)) | (1L << (DYNAMIC - 10)) | (1L << (EQUALS - 10)) | (1L << (FLOAT - 10)) | (1L << (FROM - 10)) | (1L << (GET - 10)) | (1L << (GROUP - 10)) | (1L << (INT - 10)) | (1L << (INTO - 10)) | (1L << (JOIN - 10)) | (1L << (LET - 10)) | (1L << (LONG - 10)) | (1L << (NAMEOF - 10)) | (1L << (OBJECT - 10)) | (1L << (ON - 10)))) != 0) || ((((_la - 74)) & ~0x3f) == 0 && ((1L << (_la - 74)) & ((1L << (ORDERBY - 74)) | (1L << (PARTIAL - 74)) | (1L << (REMOVE - 74)) | (1L << (SBYTE - 74)) | (1L << (SELECT - 74)) | (1L << (SET - 74)) | (1L << (SHORT - 74)) | (1L << (STRING - 74)) | (1L << (UINT - 74)) | (1L << (ULONG - 74)) | (1L << (UNMANAGED - 74)) | (1L << (USHORT - 74)) | (1L << (VAR - 74)) | (1L << (VOID - 74)) | (1L << (WHEN - 74)) | (1L << (WHERE - 74)) | (1L << (YIELD - 74)) | (1L << (IDENTIFIER - 74)) | (1L << (OPEN_PARENS - 74)))) != 0)) {
					{
					setState(2342);
					type_();
					}
				}

				setState(2345);
				match(OPEN_BRACKET);
				setState(2347);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ADD) | (1L << ALIAS) | (1L << ARGLIST) | (1L << ASCENDING) | (1L << ASYNC) | (1L << AWAIT) | (1L << BASE) | (1L << BOOL) | (1L << BY) | (1L << BYTE) | (1L << CHAR) | (1L << CHECKED) | (1L << DECIMAL) | (1L << DEFAULT) | (1L << DELEGATE) | (1L << DESCENDING) | (1L << DOUBLE) | (1L << DYNAMIC) | (1L << EQUALS) | (1L << FALSE) | (1L << FLOAT) | (1L << FROM) | (1L << GET) | (1L << GROUP) | (1L << INT) | (1L << INTO) | (1L << JOIN) | (1L << LET))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (LONG - 64)) | (1L << (NAMEOF - 64)) | (1L << (NEW - 64)) | (1L << (NULL_ - 64)) | (1L << (OBJECT - 64)) | (1L << (ON - 64)) | (1L << (ORDERBY - 64)) | (1L << (PARTIAL - 64)) | (1L << (REF - 64)) | (1L << (REMOVE - 64)) | (1L << (SBYTE - 64)) | (1L << (SELECT - 64)) | (1L << (SET - 64)) | (1L << (SHORT - 64)) | (1L << (SIZEOF - 64)) | (1L << (STRING - 64)) | (1L << (THIS - 64)) | (1L << (TRUE - 64)) | (1L << (TYPEOF - 64)) | (1L << (UINT - 64)) | (1L << (ULONG - 64)) | (1L << (UNCHECKED - 64)) | (1L << (UNMANAGED - 64)) | (1L << (USHORT - 64)) | (1L << (VAR - 64)) | (1L << (WHEN - 64)) | (1L << (WHERE - 64)) | (1L << (YIELD - 64)) | (1L << (IDENTIFIER - 64)) | (1L << (LITERAL_ACCESS - 64)) | (1L << (INTEGER_LITERAL - 64)) | (1L << (HEX_INTEGER_LITERAL - 64)) | (1L << (BIN_INTEGER_LITERAL - 64)) | (1L << (REAL_LITERAL - 64)) | (1L << (CHARACTER_LITERAL - 64)) | (1L << (REGULAR_STRING - 64)) | (1L << (VERBATIUM_STRING - 64)) | (1L << (INTERPOLATED_REGULAR_STRING_START - 64)))) != 0) || ((((_la - 128)) & ~0x3f) == 0 && ((1L << (_la - 128)) & ((1L << (INTERPOLATED_VERBATIUM_STRING_START - 128)) | (1L << (OPEN_PARENS - 128)) | (1L << (PLUS - 128)) | (1L << (MINUS - 128)) | (1L << (STAR - 128)) | (1L << (AMP - 128)) | (1L << (CARET - 128)) | (1L << (BANG - 128)) | (1L << (TILDE - 128)) | (1L << (OP_INC - 128)) | (1L << (OP_DEC - 128)) | (1L << (OP_RANGE - 128)))) != 0)) {
					{
					setState(2346);
					expression();
					}
				}

				setState(2349);
				match(CLOSE_BRACKET);
				setState(2350);
				match(OPEN_BRACE);
				setState(2351);
				expression();
				setState(2356);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,298,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(2352);
						match(COMMA);
						setState(2353);
						expression();
						}
						} 
					}
					setState(2358);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,298,_ctx);
				}
				setState(2360);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==COMMA) {
					{
					setState(2359);
					match(COMMA);
					}
				}

				setState(2362);
				match(CLOSE_BRACE);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Right_arrowContext extends ParserRuleContext {
		public Token first;
		public Token second;
		public TerminalNode ASSIGNMENT() { return getToken(CSharpParser.ASSIGNMENT, 0); }
		public TerminalNode GT() { return getToken(CSharpParser.GT, 0); }
		public Right_arrowContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_right_arrow; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterRight_arrow(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitRight_arrow(this);
		}
	}

	public final Right_arrowContext right_arrow() throws RecognitionException {
		Right_arrowContext _localctx = new Right_arrowContext(_ctx, getState());
		enterRule(_localctx, 374, RULE_right_arrow);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2366);
			((Right_arrowContext)_localctx).first = match(ASSIGNMENT);
			setState(2367);
			((Right_arrowContext)_localctx).second = match(GT);
			setState(2368);
			if (!((((Right_arrowContext)_localctx).first!=null?((Right_arrowContext)_localctx).first.getTokenIndex():0) + 1 == (((Right_arrowContext)_localctx).second!=null?((Right_arrowContext)_localctx).second.getTokenIndex():0))) throw new FailedPredicateException(this, "$first.index + 1 == $second.index");
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Right_shiftContext extends ParserRuleContext {
		public Token first;
		public Token second;
		public List<TerminalNode> GT() { return getTokens(CSharpParser.GT); }
		public TerminalNode GT(int i) {
			return getToken(CSharpParser.GT, i);
		}
		public Right_shiftContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_right_shift; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterRight_shift(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitRight_shift(this);
		}
	}

	public final Right_shiftContext right_shift() throws RecognitionException {
		Right_shiftContext _localctx = new Right_shiftContext(_ctx, getState());
		enterRule(_localctx, 376, RULE_right_shift);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2370);
			((Right_shiftContext)_localctx).first = match(GT);
			setState(2371);
			((Right_shiftContext)_localctx).second = match(GT);
			setState(2372);
			if (!((((Right_shiftContext)_localctx).first!=null?((Right_shiftContext)_localctx).first.getTokenIndex():0) + 1 == (((Right_shiftContext)_localctx).second!=null?((Right_shiftContext)_localctx).second.getTokenIndex():0))) throw new FailedPredicateException(this, "$first.index + 1 == $second.index");
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Right_shift_assignmentContext extends ParserRuleContext {
		public Token first;
		public Token second;
		public TerminalNode GT() { return getToken(CSharpParser.GT, 0); }
		public TerminalNode OP_GE() { return getToken(CSharpParser.OP_GE, 0); }
		public Right_shift_assignmentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_right_shift_assignment; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterRight_shift_assignment(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitRight_shift_assignment(this);
		}
	}

	public final Right_shift_assignmentContext right_shift_assignment() throws RecognitionException {
		Right_shift_assignmentContext _localctx = new Right_shift_assignmentContext(_ctx, getState());
		enterRule(_localctx, 378, RULE_right_shift_assignment);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2374);
			((Right_shift_assignmentContext)_localctx).first = match(GT);
			setState(2375);
			((Right_shift_assignmentContext)_localctx).second = match(OP_GE);
			setState(2376);
			if (!((((Right_shift_assignmentContext)_localctx).first!=null?((Right_shift_assignmentContext)_localctx).first.getTokenIndex():0) + 1 == (((Right_shift_assignmentContext)_localctx).second!=null?((Right_shift_assignmentContext)_localctx).second.getTokenIndex():0))) throw new FailedPredicateException(this, "$first.index + 1 == $second.index");
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LiteralContext extends ParserRuleContext {
		public Boolean_literalContext boolean_literal() {
			return getRuleContext(Boolean_literalContext.class,0);
		}
		public String_literalContext string_literal() {
			return getRuleContext(String_literalContext.class,0);
		}
		public TerminalNode INTEGER_LITERAL() { return getToken(CSharpParser.INTEGER_LITERAL, 0); }
		public TerminalNode HEX_INTEGER_LITERAL() { return getToken(CSharpParser.HEX_INTEGER_LITERAL, 0); }
		public TerminalNode BIN_INTEGER_LITERAL() { return getToken(CSharpParser.BIN_INTEGER_LITERAL, 0); }
		public TerminalNode REAL_LITERAL() { return getToken(CSharpParser.REAL_LITERAL, 0); }
		public TerminalNode CHARACTER_LITERAL() { return getToken(CSharpParser.CHARACTER_LITERAL, 0); }
		public TerminalNode NULL_() { return getToken(CSharpParser.NULL_, 0); }
		public LiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitLiteral(this);
		}
	}

	public final LiteralContext literal() throws RecognitionException {
		LiteralContext _localctx = new LiteralContext(_ctx, getState());
		enterRule(_localctx, 380, RULE_literal);
		try {
			setState(2386);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case FALSE:
			case TRUE:
				enterOuterAlt(_localctx, 1);
				{
				setState(2378);
				boolean_literal();
				}
				break;
			case REGULAR_STRING:
			case VERBATIUM_STRING:
			case INTERPOLATED_REGULAR_STRING_START:
			case INTERPOLATED_VERBATIUM_STRING_START:
				enterOuterAlt(_localctx, 2);
				{
				setState(2379);
				string_literal();
				}
				break;
			case INTEGER_LITERAL:
				enterOuterAlt(_localctx, 3);
				{
				setState(2380);
				match(INTEGER_LITERAL);
				}
				break;
			case HEX_INTEGER_LITERAL:
				enterOuterAlt(_localctx, 4);
				{
				setState(2381);
				match(HEX_INTEGER_LITERAL);
				}
				break;
			case BIN_INTEGER_LITERAL:
				enterOuterAlt(_localctx, 5);
				{
				setState(2382);
				match(BIN_INTEGER_LITERAL);
				}
				break;
			case REAL_LITERAL:
				enterOuterAlt(_localctx, 6);
				{
				setState(2383);
				match(REAL_LITERAL);
				}
				break;
			case CHARACTER_LITERAL:
				enterOuterAlt(_localctx, 7);
				{
				setState(2384);
				match(CHARACTER_LITERAL);
				}
				break;
			case NULL_:
				enterOuterAlt(_localctx, 8);
				{
				setState(2385);
				match(NULL_);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Boolean_literalContext extends ParserRuleContext {
		public TerminalNode TRUE() { return getToken(CSharpParser.TRUE, 0); }
		public TerminalNode FALSE() { return getToken(CSharpParser.FALSE, 0); }
		public Boolean_literalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_boolean_literal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterBoolean_literal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitBoolean_literal(this);
		}
	}

	public final Boolean_literalContext boolean_literal() throws RecognitionException {
		Boolean_literalContext _localctx = new Boolean_literalContext(_ctx, getState());
		enterRule(_localctx, 382, RULE_boolean_literal);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2388);
			_la = _input.LA(1);
			if ( !(_la==FALSE || _la==TRUE) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class String_literalContext extends ParserRuleContext {
		public Interpolated_regular_stringContext interpolated_regular_string() {
			return getRuleContext(Interpolated_regular_stringContext.class,0);
		}
		public Interpolated_verbatium_stringContext interpolated_verbatium_string() {
			return getRuleContext(Interpolated_verbatium_stringContext.class,0);
		}
		public TerminalNode REGULAR_STRING() { return getToken(CSharpParser.REGULAR_STRING, 0); }
		public TerminalNode VERBATIUM_STRING() { return getToken(CSharpParser.VERBATIUM_STRING, 0); }
		public String_literalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_string_literal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterString_literal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitString_literal(this);
		}
	}

	public final String_literalContext string_literal() throws RecognitionException {
		String_literalContext _localctx = new String_literalContext(_ctx, getState());
		enterRule(_localctx, 384, RULE_string_literal);
		try {
			setState(2394);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INTERPOLATED_REGULAR_STRING_START:
				enterOuterAlt(_localctx, 1);
				{
				setState(2390);
				interpolated_regular_string();
				}
				break;
			case INTERPOLATED_VERBATIUM_STRING_START:
				enterOuterAlt(_localctx, 2);
				{
				setState(2391);
				interpolated_verbatium_string();
				}
				break;
			case REGULAR_STRING:
				enterOuterAlt(_localctx, 3);
				{
				setState(2392);
				match(REGULAR_STRING);
				}
				break;
			case VERBATIUM_STRING:
				enterOuterAlt(_localctx, 4);
				{
				setState(2393);
				match(VERBATIUM_STRING);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Interpolated_regular_stringContext extends ParserRuleContext {
		public TerminalNode INTERPOLATED_REGULAR_STRING_START() { return getToken(CSharpParser.INTERPOLATED_REGULAR_STRING_START, 0); }
		public TerminalNode DOUBLE_QUOTE_INSIDE() { return getToken(CSharpParser.DOUBLE_QUOTE_INSIDE, 0); }
		public List<Interpolated_regular_string_partContext> interpolated_regular_string_part() {
			return getRuleContexts(Interpolated_regular_string_partContext.class);
		}
		public Interpolated_regular_string_partContext interpolated_regular_string_part(int i) {
			return getRuleContext(Interpolated_regular_string_partContext.class,i);
		}
		public Interpolated_regular_stringContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_interpolated_regular_string; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterInterpolated_regular_string(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitInterpolated_regular_string(this);
		}
	}

	public final Interpolated_regular_stringContext interpolated_regular_string() throws RecognitionException {
		Interpolated_regular_stringContext _localctx = new Interpolated_regular_stringContext(_ctx, getState());
		enterRule(_localctx, 386, RULE_interpolated_regular_string);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2396);
			match(INTERPOLATED_REGULAR_STRING_START);
			setState(2400);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ADD) | (1L << ALIAS) | (1L << ARGLIST) | (1L << ASCENDING) | (1L << ASYNC) | (1L << AWAIT) | (1L << BASE) | (1L << BOOL) | (1L << BY) | (1L << BYTE) | (1L << CHAR) | (1L << CHECKED) | (1L << DECIMAL) | (1L << DEFAULT) | (1L << DELEGATE) | (1L << DESCENDING) | (1L << DOUBLE) | (1L << DYNAMIC) | (1L << EQUALS) | (1L << FALSE) | (1L << FLOAT) | (1L << FROM) | (1L << GET) | (1L << GROUP) | (1L << INT) | (1L << INTO) | (1L << JOIN) | (1L << LET))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (LONG - 64)) | (1L << (NAMEOF - 64)) | (1L << (NEW - 64)) | (1L << (NULL_ - 64)) | (1L << (OBJECT - 64)) | (1L << (ON - 64)) | (1L << (ORDERBY - 64)) | (1L << (PARTIAL - 64)) | (1L << (REF - 64)) | (1L << (REMOVE - 64)) | (1L << (SBYTE - 64)) | (1L << (SELECT - 64)) | (1L << (SET - 64)) | (1L << (SHORT - 64)) | (1L << (SIZEOF - 64)) | (1L << (STRING - 64)) | (1L << (THIS - 64)) | (1L << (TRUE - 64)) | (1L << (TYPEOF - 64)) | (1L << (UINT - 64)) | (1L << (ULONG - 64)) | (1L << (UNCHECKED - 64)) | (1L << (UNMANAGED - 64)) | (1L << (USHORT - 64)) | (1L << (VAR - 64)) | (1L << (WHEN - 64)) | (1L << (WHERE - 64)) | (1L << (YIELD - 64)) | (1L << (IDENTIFIER - 64)) | (1L << (LITERAL_ACCESS - 64)) | (1L << (INTEGER_LITERAL - 64)) | (1L << (HEX_INTEGER_LITERAL - 64)) | (1L << (BIN_INTEGER_LITERAL - 64)) | (1L << (REAL_LITERAL - 64)) | (1L << (CHARACTER_LITERAL - 64)) | (1L << (REGULAR_STRING - 64)) | (1L << (VERBATIUM_STRING - 64)) | (1L << (INTERPOLATED_REGULAR_STRING_START - 64)))) != 0) || ((((_la - 128)) & ~0x3f) == 0 && ((1L << (_la - 128)) & ((1L << (INTERPOLATED_VERBATIUM_STRING_START - 128)) | (1L << (OPEN_PARENS - 128)) | (1L << (PLUS - 128)) | (1L << (MINUS - 128)) | (1L << (STAR - 128)) | (1L << (AMP - 128)) | (1L << (CARET - 128)) | (1L << (BANG - 128)) | (1L << (TILDE - 128)) | (1L << (OP_INC - 128)) | (1L << (OP_DEC - 128)) | (1L << (OP_RANGE - 128)) | (1L << (DOUBLE_CURLY_INSIDE - 128)) | (1L << (REGULAR_CHAR_INSIDE - 128)) | (1L << (REGULAR_STRING_INSIDE - 128)))) != 0)) {
				{
				{
				setState(2397);
				interpolated_regular_string_part();
				}
				}
				setState(2402);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(2403);
			match(DOUBLE_QUOTE_INSIDE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Interpolated_verbatium_stringContext extends ParserRuleContext {
		public TerminalNode INTERPOLATED_VERBATIUM_STRING_START() { return getToken(CSharpParser.INTERPOLATED_VERBATIUM_STRING_START, 0); }
		public TerminalNode DOUBLE_QUOTE_INSIDE() { return getToken(CSharpParser.DOUBLE_QUOTE_INSIDE, 0); }
		public List<Interpolated_verbatium_string_partContext> interpolated_verbatium_string_part() {
			return getRuleContexts(Interpolated_verbatium_string_partContext.class);
		}
		public Interpolated_verbatium_string_partContext interpolated_verbatium_string_part(int i) {
			return getRuleContext(Interpolated_verbatium_string_partContext.class,i);
		}
		public Interpolated_verbatium_stringContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_interpolated_verbatium_string; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterInterpolated_verbatium_string(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitInterpolated_verbatium_string(this);
		}
	}

	public final Interpolated_verbatium_stringContext interpolated_verbatium_string() throws RecognitionException {
		Interpolated_verbatium_stringContext _localctx = new Interpolated_verbatium_stringContext(_ctx, getState());
		enterRule(_localctx, 388, RULE_interpolated_verbatium_string);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2405);
			match(INTERPOLATED_VERBATIUM_STRING_START);
			setState(2409);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ADD) | (1L << ALIAS) | (1L << ARGLIST) | (1L << ASCENDING) | (1L << ASYNC) | (1L << AWAIT) | (1L << BASE) | (1L << BOOL) | (1L << BY) | (1L << BYTE) | (1L << CHAR) | (1L << CHECKED) | (1L << DECIMAL) | (1L << DEFAULT) | (1L << DELEGATE) | (1L << DESCENDING) | (1L << DOUBLE) | (1L << DYNAMIC) | (1L << EQUALS) | (1L << FALSE) | (1L << FLOAT) | (1L << FROM) | (1L << GET) | (1L << GROUP) | (1L << INT) | (1L << INTO) | (1L << JOIN) | (1L << LET))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (LONG - 64)) | (1L << (NAMEOF - 64)) | (1L << (NEW - 64)) | (1L << (NULL_ - 64)) | (1L << (OBJECT - 64)) | (1L << (ON - 64)) | (1L << (ORDERBY - 64)) | (1L << (PARTIAL - 64)) | (1L << (REF - 64)) | (1L << (REMOVE - 64)) | (1L << (SBYTE - 64)) | (1L << (SELECT - 64)) | (1L << (SET - 64)) | (1L << (SHORT - 64)) | (1L << (SIZEOF - 64)) | (1L << (STRING - 64)) | (1L << (THIS - 64)) | (1L << (TRUE - 64)) | (1L << (TYPEOF - 64)) | (1L << (UINT - 64)) | (1L << (ULONG - 64)) | (1L << (UNCHECKED - 64)) | (1L << (UNMANAGED - 64)) | (1L << (USHORT - 64)) | (1L << (VAR - 64)) | (1L << (WHEN - 64)) | (1L << (WHERE - 64)) | (1L << (YIELD - 64)) | (1L << (IDENTIFIER - 64)) | (1L << (LITERAL_ACCESS - 64)) | (1L << (INTEGER_LITERAL - 64)) | (1L << (HEX_INTEGER_LITERAL - 64)) | (1L << (BIN_INTEGER_LITERAL - 64)) | (1L << (REAL_LITERAL - 64)) | (1L << (CHARACTER_LITERAL - 64)) | (1L << (REGULAR_STRING - 64)) | (1L << (VERBATIUM_STRING - 64)) | (1L << (INTERPOLATED_REGULAR_STRING_START - 64)))) != 0) || ((((_la - 128)) & ~0x3f) == 0 && ((1L << (_la - 128)) & ((1L << (INTERPOLATED_VERBATIUM_STRING_START - 128)) | (1L << (OPEN_PARENS - 128)) | (1L << (PLUS - 128)) | (1L << (MINUS - 128)) | (1L << (STAR - 128)) | (1L << (AMP - 128)) | (1L << (CARET - 128)) | (1L << (BANG - 128)) | (1L << (TILDE - 128)) | (1L << (OP_INC - 128)) | (1L << (OP_DEC - 128)) | (1L << (OP_RANGE - 128)) | (1L << (DOUBLE_CURLY_INSIDE - 128)) | (1L << (VERBATIUM_DOUBLE_QUOTE_INSIDE - 128)) | (1L << (VERBATIUM_INSIDE_STRING - 128)))) != 0)) {
				{
				{
				setState(2406);
				interpolated_verbatium_string_part();
				}
				}
				setState(2411);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(2412);
			match(DOUBLE_QUOTE_INSIDE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Interpolated_regular_string_partContext extends ParserRuleContext {
		public Interpolated_string_expressionContext interpolated_string_expression() {
			return getRuleContext(Interpolated_string_expressionContext.class,0);
		}
		public TerminalNode DOUBLE_CURLY_INSIDE() { return getToken(CSharpParser.DOUBLE_CURLY_INSIDE, 0); }
		public TerminalNode REGULAR_CHAR_INSIDE() { return getToken(CSharpParser.REGULAR_CHAR_INSIDE, 0); }
		public TerminalNode REGULAR_STRING_INSIDE() { return getToken(CSharpParser.REGULAR_STRING_INSIDE, 0); }
		public Interpolated_regular_string_partContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_interpolated_regular_string_part; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterInterpolated_regular_string_part(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitInterpolated_regular_string_part(this);
		}
	}

	public final Interpolated_regular_string_partContext interpolated_regular_string_part() throws RecognitionException {
		Interpolated_regular_string_partContext _localctx = new Interpolated_regular_string_partContext(_ctx, getState());
		enterRule(_localctx, 390, RULE_interpolated_regular_string_part);
		try {
			setState(2418);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ADD:
			case ALIAS:
			case ARGLIST:
			case ASCENDING:
			case ASYNC:
			case AWAIT:
			case BASE:
			case BOOL:
			case BY:
			case BYTE:
			case CHAR:
			case CHECKED:
			case DECIMAL:
			case DEFAULT:
			case DELEGATE:
			case DESCENDING:
			case DOUBLE:
			case DYNAMIC:
			case EQUALS:
			case FALSE:
			case FLOAT:
			case FROM:
			case GET:
			case GROUP:
			case INT:
			case INTO:
			case JOIN:
			case LET:
			case LONG:
			case NAMEOF:
			case NEW:
			case NULL_:
			case OBJECT:
			case ON:
			case ORDERBY:
			case PARTIAL:
			case REF:
			case REMOVE:
			case SBYTE:
			case SELECT:
			case SET:
			case SHORT:
			case SIZEOF:
			case STRING:
			case THIS:
			case TRUE:
			case TYPEOF:
			case UINT:
			case ULONG:
			case UNCHECKED:
			case UNMANAGED:
			case USHORT:
			case VAR:
			case WHEN:
			case WHERE:
			case YIELD:
			case IDENTIFIER:
			case LITERAL_ACCESS:
			case INTEGER_LITERAL:
			case HEX_INTEGER_LITERAL:
			case BIN_INTEGER_LITERAL:
			case REAL_LITERAL:
			case CHARACTER_LITERAL:
			case REGULAR_STRING:
			case VERBATIUM_STRING:
			case INTERPOLATED_REGULAR_STRING_START:
			case INTERPOLATED_VERBATIUM_STRING_START:
			case OPEN_PARENS:
			case PLUS:
			case MINUS:
			case STAR:
			case AMP:
			case CARET:
			case BANG:
			case TILDE:
			case OP_INC:
			case OP_DEC:
			case OP_RANGE:
				enterOuterAlt(_localctx, 1);
				{
				setState(2414);
				interpolated_string_expression();
				}
				break;
			case DOUBLE_CURLY_INSIDE:
				enterOuterAlt(_localctx, 2);
				{
				setState(2415);
				match(DOUBLE_CURLY_INSIDE);
				}
				break;
			case REGULAR_CHAR_INSIDE:
				enterOuterAlt(_localctx, 3);
				{
				setState(2416);
				match(REGULAR_CHAR_INSIDE);
				}
				break;
			case REGULAR_STRING_INSIDE:
				enterOuterAlt(_localctx, 4);
				{
				setState(2417);
				match(REGULAR_STRING_INSIDE);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Interpolated_verbatium_string_partContext extends ParserRuleContext {
		public Interpolated_string_expressionContext interpolated_string_expression() {
			return getRuleContext(Interpolated_string_expressionContext.class,0);
		}
		public TerminalNode DOUBLE_CURLY_INSIDE() { return getToken(CSharpParser.DOUBLE_CURLY_INSIDE, 0); }
		public TerminalNode VERBATIUM_DOUBLE_QUOTE_INSIDE() { return getToken(CSharpParser.VERBATIUM_DOUBLE_QUOTE_INSIDE, 0); }
		public TerminalNode VERBATIUM_INSIDE_STRING() { return getToken(CSharpParser.VERBATIUM_INSIDE_STRING, 0); }
		public Interpolated_verbatium_string_partContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_interpolated_verbatium_string_part; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterInterpolated_verbatium_string_part(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitInterpolated_verbatium_string_part(this);
		}
	}

	public final Interpolated_verbatium_string_partContext interpolated_verbatium_string_part() throws RecognitionException {
		Interpolated_verbatium_string_partContext _localctx = new Interpolated_verbatium_string_partContext(_ctx, getState());
		enterRule(_localctx, 392, RULE_interpolated_verbatium_string_part);
		try {
			setState(2424);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ADD:
			case ALIAS:
			case ARGLIST:
			case ASCENDING:
			case ASYNC:
			case AWAIT:
			case BASE:
			case BOOL:
			case BY:
			case BYTE:
			case CHAR:
			case CHECKED:
			case DECIMAL:
			case DEFAULT:
			case DELEGATE:
			case DESCENDING:
			case DOUBLE:
			case DYNAMIC:
			case EQUALS:
			case FALSE:
			case FLOAT:
			case FROM:
			case GET:
			case GROUP:
			case INT:
			case INTO:
			case JOIN:
			case LET:
			case LONG:
			case NAMEOF:
			case NEW:
			case NULL_:
			case OBJECT:
			case ON:
			case ORDERBY:
			case PARTIAL:
			case REF:
			case REMOVE:
			case SBYTE:
			case SELECT:
			case SET:
			case SHORT:
			case SIZEOF:
			case STRING:
			case THIS:
			case TRUE:
			case TYPEOF:
			case UINT:
			case ULONG:
			case UNCHECKED:
			case UNMANAGED:
			case USHORT:
			case VAR:
			case WHEN:
			case WHERE:
			case YIELD:
			case IDENTIFIER:
			case LITERAL_ACCESS:
			case INTEGER_LITERAL:
			case HEX_INTEGER_LITERAL:
			case BIN_INTEGER_LITERAL:
			case REAL_LITERAL:
			case CHARACTER_LITERAL:
			case REGULAR_STRING:
			case VERBATIUM_STRING:
			case INTERPOLATED_REGULAR_STRING_START:
			case INTERPOLATED_VERBATIUM_STRING_START:
			case OPEN_PARENS:
			case PLUS:
			case MINUS:
			case STAR:
			case AMP:
			case CARET:
			case BANG:
			case TILDE:
			case OP_INC:
			case OP_DEC:
			case OP_RANGE:
				enterOuterAlt(_localctx, 1);
				{
				setState(2420);
				interpolated_string_expression();
				}
				break;
			case DOUBLE_CURLY_INSIDE:
				enterOuterAlt(_localctx, 2);
				{
				setState(2421);
				match(DOUBLE_CURLY_INSIDE);
				}
				break;
			case VERBATIUM_DOUBLE_QUOTE_INSIDE:
				enterOuterAlt(_localctx, 3);
				{
				setState(2422);
				match(VERBATIUM_DOUBLE_QUOTE_INSIDE);
				}
				break;
			case VERBATIUM_INSIDE_STRING:
				enterOuterAlt(_localctx, 4);
				{
				setState(2423);
				match(VERBATIUM_INSIDE_STRING);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Interpolated_string_expressionContext extends ParserRuleContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(CSharpParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(CSharpParser.COMMA, i);
		}
		public TerminalNode COLON() { return getToken(CSharpParser.COLON, 0); }
		public List<TerminalNode> FORMAT_STRING() { return getTokens(CSharpParser.FORMAT_STRING); }
		public TerminalNode FORMAT_STRING(int i) {
			return getToken(CSharpParser.FORMAT_STRING, i);
		}
		public Interpolated_string_expressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_interpolated_string_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterInterpolated_string_expression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitInterpolated_string_expression(this);
		}
	}

	public final Interpolated_string_expressionContext interpolated_string_expression() throws RecognitionException {
		Interpolated_string_expressionContext _localctx = new Interpolated_string_expressionContext(_ctx, getState());
		enterRule(_localctx, 394, RULE_interpolated_string_expression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2426);
			expression();
			setState(2431);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(2427);
				match(COMMA);
				setState(2428);
				expression();
				}
				}
				setState(2433);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(2440);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==COLON) {
				{
				setState(2434);
				match(COLON);
				setState(2436); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(2435);
					match(FORMAT_STRING);
					}
					}
					setState(2438); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==FORMAT_STRING );
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class KeywordContext extends ParserRuleContext {
		public TerminalNode ABSTRACT() { return getToken(CSharpParser.ABSTRACT, 0); }
		public TerminalNode AS() { return getToken(CSharpParser.AS, 0); }
		public TerminalNode BASE() { return getToken(CSharpParser.BASE, 0); }
		public TerminalNode BOOL() { return getToken(CSharpParser.BOOL, 0); }
		public TerminalNode BREAK() { return getToken(CSharpParser.BREAK, 0); }
		public TerminalNode BYTE() { return getToken(CSharpParser.BYTE, 0); }
		public TerminalNode CASE() { return getToken(CSharpParser.CASE, 0); }
		public TerminalNode CATCH() { return getToken(CSharpParser.CATCH, 0); }
		public TerminalNode CHAR() { return getToken(CSharpParser.CHAR, 0); }
		public TerminalNode CHECKED() { return getToken(CSharpParser.CHECKED, 0); }
		public TerminalNode CLASS() { return getToken(CSharpParser.CLASS, 0); }
		public TerminalNode CONST() { return getToken(CSharpParser.CONST, 0); }
		public TerminalNode CONTINUE() { return getToken(CSharpParser.CONTINUE, 0); }
		public TerminalNode DECIMAL() { return getToken(CSharpParser.DECIMAL, 0); }
		public TerminalNode DEFAULT() { return getToken(CSharpParser.DEFAULT, 0); }
		public TerminalNode DELEGATE() { return getToken(CSharpParser.DELEGATE, 0); }
		public TerminalNode DO() { return getToken(CSharpParser.DO, 0); }
		public TerminalNode DOUBLE() { return getToken(CSharpParser.DOUBLE, 0); }
		public TerminalNode ELSE() { return getToken(CSharpParser.ELSE, 0); }
		public TerminalNode ENUM() { return getToken(CSharpParser.ENUM, 0); }
		public TerminalNode EVENT() { return getToken(CSharpParser.EVENT, 0); }
		public TerminalNode EXPLICIT() { return getToken(CSharpParser.EXPLICIT, 0); }
		public TerminalNode EXTERN() { return getToken(CSharpParser.EXTERN, 0); }
		public TerminalNode FALSE() { return getToken(CSharpParser.FALSE, 0); }
		public TerminalNode FINAL() { return getToken(CSharpParser.FINAL, 0); }
		public TerminalNode FINALLY() { return getToken(CSharpParser.FINALLY, 0); }
		public TerminalNode FIXED() { return getToken(CSharpParser.FIXED, 0); }
		public TerminalNode FLOAT() { return getToken(CSharpParser.FLOAT, 0); }
		public TerminalNode FOR() { return getToken(CSharpParser.FOR, 0); }
		public TerminalNode FOREACH() { return getToken(CSharpParser.FOREACH, 0); }
		public TerminalNode GOTO() { return getToken(CSharpParser.GOTO, 0); }
		public TerminalNode IF() { return getToken(CSharpParser.IF, 0); }
		public TerminalNode IMPLICIT() { return getToken(CSharpParser.IMPLICIT, 0); }
		public TerminalNode IN() { return getToken(CSharpParser.IN, 0); }
		public TerminalNode INT() { return getToken(CSharpParser.INT, 0); }
		public TerminalNode INTERFACE() { return getToken(CSharpParser.INTERFACE, 0); }
		public TerminalNode INTERNAL() { return getToken(CSharpParser.INTERNAL, 0); }
		public TerminalNode IS() { return getToken(CSharpParser.IS, 0); }
		public TerminalNode LOCK() { return getToken(CSharpParser.LOCK, 0); }
		public TerminalNode LONG() { return getToken(CSharpParser.LONG, 0); }
		public TerminalNode NAMESPACE() { return getToken(CSharpParser.NAMESPACE, 0); }
		public TerminalNode NATIVE() { return getToken(CSharpParser.NATIVE, 0); }
		public TerminalNode NEW() { return getToken(CSharpParser.NEW, 0); }
		public TerminalNode NULL_() { return getToken(CSharpParser.NULL_, 0); }
		public TerminalNode OBJECT() { return getToken(CSharpParser.OBJECT, 0); }
		public TerminalNode OPERATOR() { return getToken(CSharpParser.OPERATOR, 0); }
		public TerminalNode OUT() { return getToken(CSharpParser.OUT, 0); }
		public TerminalNode OVERRIDE() { return getToken(CSharpParser.OVERRIDE, 0); }
		public TerminalNode PARAMS() { return getToken(CSharpParser.PARAMS, 0); }
		public TerminalNode PRIVATE() { return getToken(CSharpParser.PRIVATE, 0); }
		public TerminalNode PROTECTED() { return getToken(CSharpParser.PROTECTED, 0); }
		public TerminalNode PUBLIC() { return getToken(CSharpParser.PUBLIC, 0); }
		public TerminalNode READONLY() { return getToken(CSharpParser.READONLY, 0); }
		public TerminalNode REF() { return getToken(CSharpParser.REF, 0); }
		public TerminalNode RETURN() { return getToken(CSharpParser.RETURN, 0); }
		public TerminalNode SBYTE() { return getToken(CSharpParser.SBYTE, 0); }
		public TerminalNode SEALED() { return getToken(CSharpParser.SEALED, 0); }
		public TerminalNode SHORT() { return getToken(CSharpParser.SHORT, 0); }
		public TerminalNode SIZEOF() { return getToken(CSharpParser.SIZEOF, 0); }
		public TerminalNode STACKALLOC() { return getToken(CSharpParser.STACKALLOC, 0); }
		public TerminalNode STATIC() { return getToken(CSharpParser.STATIC, 0); }
		public TerminalNode STRING() { return getToken(CSharpParser.STRING, 0); }
		public TerminalNode STRUCT() { return getToken(CSharpParser.STRUCT, 0); }
		public TerminalNode SWITCH() { return getToken(CSharpParser.SWITCH, 0); }
		public TerminalNode THIS() { return getToken(CSharpParser.THIS, 0); }
		public TerminalNode THROW() { return getToken(CSharpParser.THROW, 0); }
		public TerminalNode TRUE() { return getToken(CSharpParser.TRUE, 0); }
		public TerminalNode TRY() { return getToken(CSharpParser.TRY, 0); }
		public TerminalNode TYPEOF() { return getToken(CSharpParser.TYPEOF, 0); }
		public TerminalNode UINT() { return getToken(CSharpParser.UINT, 0); }
		public TerminalNode ULONG() { return getToken(CSharpParser.ULONG, 0); }
		public TerminalNode UNCHECKED() { return getToken(CSharpParser.UNCHECKED, 0); }
		public TerminalNode UNMANAGED() { return getToken(CSharpParser.UNMANAGED, 0); }
		public TerminalNode UNSAFE() { return getToken(CSharpParser.UNSAFE, 0); }
		public TerminalNode USHORT() { return getToken(CSharpParser.USHORT, 0); }
		public TerminalNode USING() { return getToken(CSharpParser.USING, 0); }
		public TerminalNode VIRTUAL() { return getToken(CSharpParser.VIRTUAL, 0); }
		public TerminalNode VOID() { return getToken(CSharpParser.VOID, 0); }
		public TerminalNode VOLATILE() { return getToken(CSharpParser.VOLATILE, 0); }
		public TerminalNode WHILE() { return getToken(CSharpParser.WHILE, 0); }
		public KeywordContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_keyword; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterKeyword(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitKeyword(this);
		}
	}

	public final KeywordContext keyword() throws RecognitionException {
		KeywordContext _localctx = new KeywordContext(_ctx, getState());
		enterRule(_localctx, 396, RULE_keyword);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2442);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ABSTRACT) | (1L << AS) | (1L << BASE) | (1L << BOOL) | (1L << BREAK) | (1L << BYTE) | (1L << CASE) | (1L << CATCH) | (1L << CHAR) | (1L << CHECKED) | (1L << CLASS) | (1L << CONST) | (1L << CONTINUE) | (1L << DECIMAL) | (1L << DEFAULT) | (1L << DELEGATE) | (1L << DO) | (1L << DOUBLE) | (1L << ELSE) | (1L << ENUM) | (1L << EVENT) | (1L << EXPLICIT) | (1L << EXTERN) | (1L << FALSE) | (1L << FINAL) | (1L << FINALLY) | (1L << FIXED) | (1L << FLOAT) | (1L << FOR) | (1L << FOREACH) | (1L << GOTO) | (1L << IF) | (1L << IMPLICIT) | (1L << IN) | (1L << INT) | (1L << INTERFACE) | (1L << INTERNAL) | (1L << IS) | (1L << LOCK))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (LONG - 64)) | (1L << (NAMESPACE - 64)) | (1L << (NATIVE - 64)) | (1L << (NEW - 64)) | (1L << (NULL_ - 64)) | (1L << (OBJECT - 64)) | (1L << (OPERATOR - 64)) | (1L << (OUT - 64)) | (1L << (OVERRIDE - 64)) | (1L << (PARAMS - 64)) | (1L << (PRIVATE - 64)) | (1L << (PROTECTED - 64)) | (1L << (PUBLIC - 64)) | (1L << (READONLY - 64)) | (1L << (REF - 64)) | (1L << (RETURN - 64)) | (1L << (SBYTE - 64)) | (1L << (SEALED - 64)) | (1L << (SHORT - 64)) | (1L << (SIZEOF - 64)) | (1L << (STACKALLOC - 64)) | (1L << (STATIC - 64)) | (1L << (STRING - 64)) | (1L << (STRUCT - 64)) | (1L << (SWITCH - 64)) | (1L << (THIS - 64)) | (1L << (THROW - 64)) | (1L << (TRUE - 64)) | (1L << (TRY - 64)) | (1L << (TYPEOF - 64)) | (1L << (UINT - 64)) | (1L << (ULONG - 64)) | (1L << (UNCHECKED - 64)) | (1L << (UNMANAGED - 64)) | (1L << (UNSAFE - 64)) | (1L << (USHORT - 64)) | (1L << (USING - 64)) | (1L << (VIRTUAL - 64)) | (1L << (VOID - 64)) | (1L << (VOLATILE - 64)) | (1L << (WHILE - 64)))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Class_definitionContext extends ParserRuleContext {
		public TerminalNode CLASS() { return getToken(CSharpParser.CLASS, 0); }
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public Class_bodyContext class_body() {
			return getRuleContext(Class_bodyContext.class,0);
		}
		public Type_parameter_listContext type_parameter_list() {
			return getRuleContext(Type_parameter_listContext.class,0);
		}
		public Class_baseContext class_base() {
			return getRuleContext(Class_baseContext.class,0);
		}
		public Type_parameter_constraints_clausesContext type_parameter_constraints_clauses() {
			return getRuleContext(Type_parameter_constraints_clausesContext.class,0);
		}
		public TerminalNode SEMICOLON() { return getToken(CSharpParser.SEMICOLON, 0); }
		public Class_definitionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_class_definition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterClass_definition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitClass_definition(this);
		}
	}

	public final Class_definitionContext class_definition() throws RecognitionException {
		Class_definitionContext _localctx = new Class_definitionContext(_ctx, getState());
		enterRule(_localctx, 398, RULE_class_definition);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2444);
			match(CLASS);
			setState(2445);
			identifier();
			setState(2447);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LT) {
				{
				setState(2446);
				type_parameter_list();
				}
			}

			setState(2450);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==COLON) {
				{
				setState(2449);
				class_base();
				}
			}

			setState(2453);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==WHERE) {
				{
				setState(2452);
				type_parameter_constraints_clauses();
				}
			}

			setState(2455);
			class_body();
			setState(2457);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SEMICOLON) {
				{
				setState(2456);
				match(SEMICOLON);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Struct_definitionContext extends ParserRuleContext {
		public TerminalNode STRUCT() { return getToken(CSharpParser.STRUCT, 0); }
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public Struct_bodyContext struct_body() {
			return getRuleContext(Struct_bodyContext.class,0);
		}
		public Type_parameter_listContext type_parameter_list() {
			return getRuleContext(Type_parameter_listContext.class,0);
		}
		public Struct_interfacesContext struct_interfaces() {
			return getRuleContext(Struct_interfacesContext.class,0);
		}
		public Type_parameter_constraints_clausesContext type_parameter_constraints_clauses() {
			return getRuleContext(Type_parameter_constraints_clausesContext.class,0);
		}
		public TerminalNode SEMICOLON() { return getToken(CSharpParser.SEMICOLON, 0); }
		public TerminalNode READONLY() { return getToken(CSharpParser.READONLY, 0); }
		public TerminalNode REF() { return getToken(CSharpParser.REF, 0); }
		public Struct_definitionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_struct_definition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterStruct_definition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitStruct_definition(this);
		}
	}

	public final Struct_definitionContext struct_definition() throws RecognitionException {
		Struct_definitionContext _localctx = new Struct_definitionContext(_ctx, getState());
		enterRule(_localctx, 400, RULE_struct_definition);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2460);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==READONLY || _la==REF) {
				{
				setState(2459);
				_la = _input.LA(1);
				if ( !(_la==READONLY || _la==REF) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
			}

			setState(2462);
			match(STRUCT);
			setState(2463);
			identifier();
			setState(2465);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LT) {
				{
				setState(2464);
				type_parameter_list();
				}
			}

			setState(2468);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==COLON) {
				{
				setState(2467);
				struct_interfaces();
				}
			}

			setState(2471);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==WHERE) {
				{
				setState(2470);
				type_parameter_constraints_clauses();
				}
			}

			setState(2473);
			struct_body();
			setState(2475);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SEMICOLON) {
				{
				setState(2474);
				match(SEMICOLON);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Interface_definitionContext extends ParserRuleContext {
		public TerminalNode INTERFACE() { return getToken(CSharpParser.INTERFACE, 0); }
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public Class_bodyContext class_body() {
			return getRuleContext(Class_bodyContext.class,0);
		}
		public Variant_type_parameter_listContext variant_type_parameter_list() {
			return getRuleContext(Variant_type_parameter_listContext.class,0);
		}
		public Interface_baseContext interface_base() {
			return getRuleContext(Interface_baseContext.class,0);
		}
		public Type_parameter_constraints_clausesContext type_parameter_constraints_clauses() {
			return getRuleContext(Type_parameter_constraints_clausesContext.class,0);
		}
		public TerminalNode SEMICOLON() { return getToken(CSharpParser.SEMICOLON, 0); }
		public Interface_definitionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_interface_definition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterInterface_definition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitInterface_definition(this);
		}
	}

	public final Interface_definitionContext interface_definition() throws RecognitionException {
		Interface_definitionContext _localctx = new Interface_definitionContext(_ctx, getState());
		enterRule(_localctx, 402, RULE_interface_definition);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2477);
			match(INTERFACE);
			setState(2478);
			identifier();
			setState(2480);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LT) {
				{
				setState(2479);
				variant_type_parameter_list();
				}
			}

			setState(2483);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==COLON) {
				{
				setState(2482);
				interface_base();
				}
			}

			setState(2486);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==WHERE) {
				{
				setState(2485);
				type_parameter_constraints_clauses();
				}
			}

			setState(2488);
			class_body();
			setState(2490);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SEMICOLON) {
				{
				setState(2489);
				match(SEMICOLON);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Enum_definitionContext extends ParserRuleContext {
		public TerminalNode ENUM() { return getToken(CSharpParser.ENUM, 0); }
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public Enum_bodyContext enum_body() {
			return getRuleContext(Enum_bodyContext.class,0);
		}
		public Enum_baseContext enum_base() {
			return getRuleContext(Enum_baseContext.class,0);
		}
		public TerminalNode SEMICOLON() { return getToken(CSharpParser.SEMICOLON, 0); }
		public Enum_definitionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_enum_definition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterEnum_definition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitEnum_definition(this);
		}
	}

	public final Enum_definitionContext enum_definition() throws RecognitionException {
		Enum_definitionContext _localctx = new Enum_definitionContext(_ctx, getState());
		enterRule(_localctx, 404, RULE_enum_definition);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2492);
			match(ENUM);
			setState(2493);
			identifier();
			setState(2495);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==COLON) {
				{
				setState(2494);
				enum_base();
				}
			}

			setState(2497);
			enum_body();
			setState(2499);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SEMICOLON) {
				{
				setState(2498);
				match(SEMICOLON);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Delegate_definitionContext extends ParserRuleContext {
		public TerminalNode DELEGATE() { return getToken(CSharpParser.DELEGATE, 0); }
		public Return_typeContext return_type() {
			return getRuleContext(Return_typeContext.class,0);
		}
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TerminalNode OPEN_PARENS() { return getToken(CSharpParser.OPEN_PARENS, 0); }
		public TerminalNode CLOSE_PARENS() { return getToken(CSharpParser.CLOSE_PARENS, 0); }
		public TerminalNode SEMICOLON() { return getToken(CSharpParser.SEMICOLON, 0); }
		public Variant_type_parameter_listContext variant_type_parameter_list() {
			return getRuleContext(Variant_type_parameter_listContext.class,0);
		}
		public Formal_parameter_listContext formal_parameter_list() {
			return getRuleContext(Formal_parameter_listContext.class,0);
		}
		public Type_parameter_constraints_clausesContext type_parameter_constraints_clauses() {
			return getRuleContext(Type_parameter_constraints_clausesContext.class,0);
		}
		public Delegate_definitionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_delegate_definition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterDelegate_definition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitDelegate_definition(this);
		}
	}

	public final Delegate_definitionContext delegate_definition() throws RecognitionException {
		Delegate_definitionContext _localctx = new Delegate_definitionContext(_ctx, getState());
		enterRule(_localctx, 406, RULE_delegate_definition);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2501);
			match(DELEGATE);
			setState(2502);
			return_type();
			setState(2503);
			identifier();
			setState(2505);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LT) {
				{
				setState(2504);
				variant_type_parameter_list();
				}
			}

			setState(2507);
			match(OPEN_PARENS);
			setState(2509);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (((((_la - 10)) & ~0x3f) == 0 && ((1L << (_la - 10)) & ((1L << (ADD - 10)) | (1L << (ALIAS - 10)) | (1L << (ARGLIST - 10)) | (1L << (ASCENDING - 10)) | (1L << (ASYNC - 10)) | (1L << (AWAIT - 10)) | (1L << (BOOL - 10)) | (1L << (BY - 10)) | (1L << (BYTE - 10)) | (1L << (CHAR - 10)) | (1L << (DECIMAL - 10)) | (1L << (DESCENDING - 10)) | (1L << (DOUBLE - 10)) | (1L << (DYNAMIC - 10)) | (1L << (EQUALS - 10)) | (1L << (FLOAT - 10)) | (1L << (FROM - 10)) | (1L << (GET - 10)) | (1L << (GROUP - 10)) | (1L << (IN - 10)) | (1L << (INT - 10)) | (1L << (INTO - 10)) | (1L << (JOIN - 10)) | (1L << (LET - 10)) | (1L << (LONG - 10)) | (1L << (NAMEOF - 10)) | (1L << (OBJECT - 10)) | (1L << (ON - 10)))) != 0) || ((((_la - 74)) & ~0x3f) == 0 && ((1L << (_la - 74)) & ((1L << (ORDERBY - 74)) | (1L << (OUT - 74)) | (1L << (PARAMS - 74)) | (1L << (PARTIAL - 74)) | (1L << (REF - 74)) | (1L << (REMOVE - 74)) | (1L << (SBYTE - 74)) | (1L << (SELECT - 74)) | (1L << (SET - 74)) | (1L << (SHORT - 74)) | (1L << (STRING - 74)) | (1L << (THIS - 74)) | (1L << (UINT - 74)) | (1L << (ULONG - 74)) | (1L << (UNMANAGED - 74)) | (1L << (USHORT - 74)) | (1L << (VAR - 74)) | (1L << (VOID - 74)) | (1L << (WHEN - 74)) | (1L << (WHERE - 74)) | (1L << (YIELD - 74)) | (1L << (IDENTIFIER - 74)) | (1L << (OPEN_BRACKET - 74)) | (1L << (OPEN_PARENS - 74)))) != 0)) {
				{
				setState(2508);
				formal_parameter_list();
				}
			}

			setState(2511);
			match(CLOSE_PARENS);
			setState(2513);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==WHERE) {
				{
				setState(2512);
				type_parameter_constraints_clauses();
				}
			}

			setState(2515);
			match(SEMICOLON);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Event_declarationContext extends ParserRuleContext {
		public TerminalNode EVENT() { return getToken(CSharpParser.EVENT, 0); }
		public Type_Context type_() {
			return getRuleContext(Type_Context.class,0);
		}
		public Variable_declaratorsContext variable_declarators() {
			return getRuleContext(Variable_declaratorsContext.class,0);
		}
		public TerminalNode SEMICOLON() { return getToken(CSharpParser.SEMICOLON, 0); }
		public Member_nameContext member_name() {
			return getRuleContext(Member_nameContext.class,0);
		}
		public TerminalNode OPEN_BRACE() { return getToken(CSharpParser.OPEN_BRACE, 0); }
		public Event_accessor_declarationsContext event_accessor_declarations() {
			return getRuleContext(Event_accessor_declarationsContext.class,0);
		}
		public TerminalNode CLOSE_BRACE() { return getToken(CSharpParser.CLOSE_BRACE, 0); }
		public Event_declarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_event_declaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterEvent_declaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitEvent_declaration(this);
		}
	}

	public final Event_declarationContext event_declaration() throws RecognitionException {
		Event_declarationContext _localctx = new Event_declarationContext(_ctx, getState());
		enterRule(_localctx, 408, RULE_event_declaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2517);
			match(EVENT);
			setState(2518);
			type_();
			setState(2527);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,328,_ctx) ) {
			case 1:
				{
				setState(2519);
				variable_declarators();
				setState(2520);
				match(SEMICOLON);
				}
				break;
			case 2:
				{
				setState(2522);
				member_name();
				setState(2523);
				match(OPEN_BRACE);
				setState(2524);
				event_accessor_declarations();
				setState(2525);
				match(CLOSE_BRACE);
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Field_declarationContext extends ParserRuleContext {
		public Variable_declaratorsContext variable_declarators() {
			return getRuleContext(Variable_declaratorsContext.class,0);
		}
		public TerminalNode SEMICOLON() { return getToken(CSharpParser.SEMICOLON, 0); }
		public Field_declarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_field_declaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterField_declaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitField_declaration(this);
		}
	}

	public final Field_declarationContext field_declaration() throws RecognitionException {
		Field_declarationContext _localctx = new Field_declarationContext(_ctx, getState());
		enterRule(_localctx, 410, RULE_field_declaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2529);
			variable_declarators();
			setState(2530);
			match(SEMICOLON);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Property_declarationContext extends ParserRuleContext {
		public Member_nameContext member_name() {
			return getRuleContext(Member_nameContext.class,0);
		}
		public TerminalNode OPEN_BRACE() { return getToken(CSharpParser.OPEN_BRACE, 0); }
		public Accessor_declarationsContext accessor_declarations() {
			return getRuleContext(Accessor_declarationsContext.class,0);
		}
		public TerminalNode CLOSE_BRACE() { return getToken(CSharpParser.CLOSE_BRACE, 0); }
		public Right_arrowContext right_arrow() {
			return getRuleContext(Right_arrowContext.class,0);
		}
		public Throwable_expressionContext throwable_expression() {
			return getRuleContext(Throwable_expressionContext.class,0);
		}
		public TerminalNode SEMICOLON() { return getToken(CSharpParser.SEMICOLON, 0); }
		public TerminalNode ASSIGNMENT() { return getToken(CSharpParser.ASSIGNMENT, 0); }
		public Variable_initializerContext variable_initializer() {
			return getRuleContext(Variable_initializerContext.class,0);
		}
		public Property_declarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_property_declaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterProperty_declaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitProperty_declaration(this);
		}
	}

	public final Property_declarationContext property_declaration() throws RecognitionException {
		Property_declarationContext _localctx = new Property_declarationContext(_ctx, getState());
		enterRule(_localctx, 412, RULE_property_declaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2532);
			member_name();
			setState(2546);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case OPEN_BRACE:
				{
				setState(2533);
				match(OPEN_BRACE);
				setState(2534);
				accessor_declarations();
				setState(2535);
				match(CLOSE_BRACE);
				setState(2540);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ASSIGNMENT) {
					{
					setState(2536);
					match(ASSIGNMENT);
					setState(2537);
					variable_initializer();
					setState(2538);
					match(SEMICOLON);
					}
				}

				}
				break;
			case ASSIGNMENT:
				{
				setState(2542);
				right_arrow();
				setState(2543);
				throwable_expression();
				setState(2544);
				match(SEMICOLON);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Constant_declarationContext extends ParserRuleContext {
		public TerminalNode CONST() { return getToken(CSharpParser.CONST, 0); }
		public Type_Context type_() {
			return getRuleContext(Type_Context.class,0);
		}
		public Constant_declaratorsContext constant_declarators() {
			return getRuleContext(Constant_declaratorsContext.class,0);
		}
		public TerminalNode SEMICOLON() { return getToken(CSharpParser.SEMICOLON, 0); }
		public Constant_declarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constant_declaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterConstant_declaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitConstant_declaration(this);
		}
	}

	public final Constant_declarationContext constant_declaration() throws RecognitionException {
		Constant_declarationContext _localctx = new Constant_declarationContext(_ctx, getState());
		enterRule(_localctx, 414, RULE_constant_declaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2548);
			match(CONST);
			setState(2549);
			type_();
			setState(2550);
			constant_declarators();
			setState(2551);
			match(SEMICOLON);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Indexer_declarationContext extends ParserRuleContext {
		public TerminalNode THIS() { return getToken(CSharpParser.THIS, 0); }
		public TerminalNode OPEN_BRACKET() { return getToken(CSharpParser.OPEN_BRACKET, 0); }
		public Formal_parameter_listContext formal_parameter_list() {
			return getRuleContext(Formal_parameter_listContext.class,0);
		}
		public TerminalNode CLOSE_BRACKET() { return getToken(CSharpParser.CLOSE_BRACKET, 0); }
		public TerminalNode OPEN_BRACE() { return getToken(CSharpParser.OPEN_BRACE, 0); }
		public Accessor_declarationsContext accessor_declarations() {
			return getRuleContext(Accessor_declarationsContext.class,0);
		}
		public TerminalNode CLOSE_BRACE() { return getToken(CSharpParser.CLOSE_BRACE, 0); }
		public Right_arrowContext right_arrow() {
			return getRuleContext(Right_arrowContext.class,0);
		}
		public Throwable_expressionContext throwable_expression() {
			return getRuleContext(Throwable_expressionContext.class,0);
		}
		public TerminalNode SEMICOLON() { return getToken(CSharpParser.SEMICOLON, 0); }
		public Indexer_declarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_indexer_declaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterIndexer_declaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitIndexer_declaration(this);
		}
	}

	public final Indexer_declarationContext indexer_declaration() throws RecognitionException {
		Indexer_declarationContext _localctx = new Indexer_declarationContext(_ctx, getState());
		enterRule(_localctx, 416, RULE_indexer_declaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2553);
			match(THIS);
			setState(2554);
			match(OPEN_BRACKET);
			setState(2555);
			formal_parameter_list();
			setState(2556);
			match(CLOSE_BRACKET);
			setState(2565);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case OPEN_BRACE:
				{
				setState(2557);
				match(OPEN_BRACE);
				setState(2558);
				accessor_declarations();
				setState(2559);
				match(CLOSE_BRACE);
				}
				break;
			case ASSIGNMENT:
				{
				setState(2561);
				right_arrow();
				setState(2562);
				throwable_expression();
				setState(2563);
				match(SEMICOLON);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Destructor_definitionContext extends ParserRuleContext {
		public TerminalNode TILDE() { return getToken(CSharpParser.TILDE, 0); }
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TerminalNode OPEN_PARENS() { return getToken(CSharpParser.OPEN_PARENS, 0); }
		public TerminalNode CLOSE_PARENS() { return getToken(CSharpParser.CLOSE_PARENS, 0); }
		public BodyContext body() {
			return getRuleContext(BodyContext.class,0);
		}
		public Destructor_definitionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_destructor_definition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterDestructor_definition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitDestructor_definition(this);
		}
	}

	public final Destructor_definitionContext destructor_definition() throws RecognitionException {
		Destructor_definitionContext _localctx = new Destructor_definitionContext(_ctx, getState());
		enterRule(_localctx, 418, RULE_destructor_definition);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2567);
			match(TILDE);
			setState(2568);
			identifier();
			setState(2569);
			match(OPEN_PARENS);
			setState(2570);
			match(CLOSE_PARENS);
			setState(2571);
			body();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Constructor_declarationContext extends ParserRuleContext {
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TerminalNode OPEN_PARENS() { return getToken(CSharpParser.OPEN_PARENS, 0); }
		public TerminalNode CLOSE_PARENS() { return getToken(CSharpParser.CLOSE_PARENS, 0); }
		public BodyContext body() {
			return getRuleContext(BodyContext.class,0);
		}
		public Formal_parameter_listContext formal_parameter_list() {
			return getRuleContext(Formal_parameter_listContext.class,0);
		}
		public Constructor_initializerContext constructor_initializer() {
			return getRuleContext(Constructor_initializerContext.class,0);
		}
		public Constructor_declarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constructor_declaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterConstructor_declaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitConstructor_declaration(this);
		}
	}

	public final Constructor_declarationContext constructor_declaration() throws RecognitionException {
		Constructor_declarationContext _localctx = new Constructor_declarationContext(_ctx, getState());
		enterRule(_localctx, 420, RULE_constructor_declaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2573);
			identifier();
			setState(2574);
			match(OPEN_PARENS);
			setState(2576);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (((((_la - 10)) & ~0x3f) == 0 && ((1L << (_la - 10)) & ((1L << (ADD - 10)) | (1L << (ALIAS - 10)) | (1L << (ARGLIST - 10)) | (1L << (ASCENDING - 10)) | (1L << (ASYNC - 10)) | (1L << (AWAIT - 10)) | (1L << (BOOL - 10)) | (1L << (BY - 10)) | (1L << (BYTE - 10)) | (1L << (CHAR - 10)) | (1L << (DECIMAL - 10)) | (1L << (DESCENDING - 10)) | (1L << (DOUBLE - 10)) | (1L << (DYNAMIC - 10)) | (1L << (EQUALS - 10)) | (1L << (FLOAT - 10)) | (1L << (FROM - 10)) | (1L << (GET - 10)) | (1L << (GROUP - 10)) | (1L << (IN - 10)) | (1L << (INT - 10)) | (1L << (INTO - 10)) | (1L << (JOIN - 10)) | (1L << (LET - 10)) | (1L << (LONG - 10)) | (1L << (NAMEOF - 10)) | (1L << (OBJECT - 10)) | (1L << (ON - 10)))) != 0) || ((((_la - 74)) & ~0x3f) == 0 && ((1L << (_la - 74)) & ((1L << (ORDERBY - 74)) | (1L << (OUT - 74)) | (1L << (PARAMS - 74)) | (1L << (PARTIAL - 74)) | (1L << (REF - 74)) | (1L << (REMOVE - 74)) | (1L << (SBYTE - 74)) | (1L << (SELECT - 74)) | (1L << (SET - 74)) | (1L << (SHORT - 74)) | (1L << (STRING - 74)) | (1L << (THIS - 74)) | (1L << (UINT - 74)) | (1L << (ULONG - 74)) | (1L << (UNMANAGED - 74)) | (1L << (USHORT - 74)) | (1L << (VAR - 74)) | (1L << (VOID - 74)) | (1L << (WHEN - 74)) | (1L << (WHERE - 74)) | (1L << (YIELD - 74)) | (1L << (IDENTIFIER - 74)) | (1L << (OPEN_BRACKET - 74)) | (1L << (OPEN_PARENS - 74)))) != 0)) {
				{
				setState(2575);
				formal_parameter_list();
				}
			}

			setState(2578);
			match(CLOSE_PARENS);
			setState(2580);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==COLON) {
				{
				setState(2579);
				constructor_initializer();
				}
			}

			setState(2582);
			body();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Method_declarationContext extends ParserRuleContext {
		public Method_member_nameContext method_member_name() {
			return getRuleContext(Method_member_nameContext.class,0);
		}
		public TerminalNode OPEN_PARENS() { return getToken(CSharpParser.OPEN_PARENS, 0); }
		public TerminalNode CLOSE_PARENS() { return getToken(CSharpParser.CLOSE_PARENS, 0); }
		public Method_bodyContext method_body() {
			return getRuleContext(Method_bodyContext.class,0);
		}
		public Right_arrowContext right_arrow() {
			return getRuleContext(Right_arrowContext.class,0);
		}
		public Throwable_expressionContext throwable_expression() {
			return getRuleContext(Throwable_expressionContext.class,0);
		}
		public TerminalNode SEMICOLON() { return getToken(CSharpParser.SEMICOLON, 0); }
		public Type_parameter_listContext type_parameter_list() {
			return getRuleContext(Type_parameter_listContext.class,0);
		}
		public Formal_parameter_listContext formal_parameter_list() {
			return getRuleContext(Formal_parameter_listContext.class,0);
		}
		public Type_parameter_constraints_clausesContext type_parameter_constraints_clauses() {
			return getRuleContext(Type_parameter_constraints_clausesContext.class,0);
		}
		public Method_declarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_method_declaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterMethod_declaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitMethod_declaration(this);
		}
	}

	public final Method_declarationContext method_declaration() throws RecognitionException {
		Method_declarationContext _localctx = new Method_declarationContext(_ctx, getState());
		enterRule(_localctx, 422, RULE_method_declaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2584);
			method_member_name();
			setState(2586);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LT) {
				{
				setState(2585);
				type_parameter_list();
				}
			}

			setState(2588);
			match(OPEN_PARENS);
			setState(2590);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (((((_la - 10)) & ~0x3f) == 0 && ((1L << (_la - 10)) & ((1L << (ADD - 10)) | (1L << (ALIAS - 10)) | (1L << (ARGLIST - 10)) | (1L << (ASCENDING - 10)) | (1L << (ASYNC - 10)) | (1L << (AWAIT - 10)) | (1L << (BOOL - 10)) | (1L << (BY - 10)) | (1L << (BYTE - 10)) | (1L << (CHAR - 10)) | (1L << (DECIMAL - 10)) | (1L << (DESCENDING - 10)) | (1L << (DOUBLE - 10)) | (1L << (DYNAMIC - 10)) | (1L << (EQUALS - 10)) | (1L << (FLOAT - 10)) | (1L << (FROM - 10)) | (1L << (GET - 10)) | (1L << (GROUP - 10)) | (1L << (IN - 10)) | (1L << (INT - 10)) | (1L << (INTO - 10)) | (1L << (JOIN - 10)) | (1L << (LET - 10)) | (1L << (LONG - 10)) | (1L << (NAMEOF - 10)) | (1L << (OBJECT - 10)) | (1L << (ON - 10)))) != 0) || ((((_la - 74)) & ~0x3f) == 0 && ((1L << (_la - 74)) & ((1L << (ORDERBY - 74)) | (1L << (OUT - 74)) | (1L << (PARAMS - 74)) | (1L << (PARTIAL - 74)) | (1L << (REF - 74)) | (1L << (REMOVE - 74)) | (1L << (SBYTE - 74)) | (1L << (SELECT - 74)) | (1L << (SET - 74)) | (1L << (SHORT - 74)) | (1L << (STRING - 74)) | (1L << (THIS - 74)) | (1L << (UINT - 74)) | (1L << (ULONG - 74)) | (1L << (UNMANAGED - 74)) | (1L << (USHORT - 74)) | (1L << (VAR - 74)) | (1L << (VOID - 74)) | (1L << (WHEN - 74)) | (1L << (WHERE - 74)) | (1L << (YIELD - 74)) | (1L << (IDENTIFIER - 74)) | (1L << (OPEN_BRACKET - 74)) | (1L << (OPEN_PARENS - 74)))) != 0)) {
				{
				setState(2589);
				formal_parameter_list();
				}
			}

			setState(2592);
			match(CLOSE_PARENS);
			setState(2594);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==WHERE) {
				{
				setState(2593);
				type_parameter_constraints_clauses();
				}
			}

			setState(2601);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case OPEN_BRACE:
			case SEMICOLON:
				{
				setState(2596);
				method_body();
				}
				break;
			case ASSIGNMENT:
				{
				setState(2597);
				right_arrow();
				setState(2598);
				throwable_expression();
				setState(2599);
				match(SEMICOLON);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Method_member_nameContext extends ParserRuleContext {
		public List<IdentifierContext> identifier() {
			return getRuleContexts(IdentifierContext.class);
		}
		public IdentifierContext identifier(int i) {
			return getRuleContext(IdentifierContext.class,i);
		}
		public TerminalNode DOUBLE_COLON() { return getToken(CSharpParser.DOUBLE_COLON, 0); }
		public List<TerminalNode> DOT() { return getTokens(CSharpParser.DOT); }
		public TerminalNode DOT(int i) {
			return getToken(CSharpParser.DOT, i);
		}
		public List<Type_argument_listContext> type_argument_list() {
			return getRuleContexts(Type_argument_listContext.class);
		}
		public Type_argument_listContext type_argument_list(int i) {
			return getRuleContext(Type_argument_listContext.class,i);
		}
		public Method_member_nameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_method_member_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterMethod_member_name(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitMethod_member_name(this);
		}
	}

	public final Method_member_nameContext method_member_name() throws RecognitionException {
		Method_member_nameContext _localctx = new Method_member_nameContext(_ctx, getState());
		enterRule(_localctx, 424, RULE_method_member_name);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(2608);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,338,_ctx) ) {
			case 1:
				{
				setState(2603);
				identifier();
				}
				break;
			case 2:
				{
				setState(2604);
				identifier();
				setState(2605);
				match(DOUBLE_COLON);
				setState(2606);
				identifier();
				}
				break;
			}
			setState(2617);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,340,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(2611);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==LT) {
						{
						setState(2610);
						type_argument_list();
						}
					}

					setState(2613);
					match(DOT);
					setState(2614);
					identifier();
					}
					} 
				}
				setState(2619);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,340,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Operator_declarationContext extends ParserRuleContext {
		public TerminalNode OPERATOR() { return getToken(CSharpParser.OPERATOR, 0); }
		public Overloadable_operatorContext overloadable_operator() {
			return getRuleContext(Overloadable_operatorContext.class,0);
		}
		public TerminalNode OPEN_PARENS() { return getToken(CSharpParser.OPEN_PARENS, 0); }
		public List<Arg_declarationContext> arg_declaration() {
			return getRuleContexts(Arg_declarationContext.class);
		}
		public Arg_declarationContext arg_declaration(int i) {
			return getRuleContext(Arg_declarationContext.class,i);
		}
		public TerminalNode CLOSE_PARENS() { return getToken(CSharpParser.CLOSE_PARENS, 0); }
		public BodyContext body() {
			return getRuleContext(BodyContext.class,0);
		}
		public Right_arrowContext right_arrow() {
			return getRuleContext(Right_arrowContext.class,0);
		}
		public Throwable_expressionContext throwable_expression() {
			return getRuleContext(Throwable_expressionContext.class,0);
		}
		public TerminalNode SEMICOLON() { return getToken(CSharpParser.SEMICOLON, 0); }
		public List<TerminalNode> IN() { return getTokens(CSharpParser.IN); }
		public TerminalNode IN(int i) {
			return getToken(CSharpParser.IN, i);
		}
		public TerminalNode COMMA() { return getToken(CSharpParser.COMMA, 0); }
		public Operator_declarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_operator_declaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterOperator_declaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitOperator_declaration(this);
		}
	}

	public final Operator_declarationContext operator_declaration() throws RecognitionException {
		Operator_declarationContext _localctx = new Operator_declarationContext(_ctx, getState());
		enterRule(_localctx, 426, RULE_operator_declaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2620);
			match(OPERATOR);
			setState(2621);
			overloadable_operator();
			setState(2622);
			match(OPEN_PARENS);
			setState(2624);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==IN) {
				{
				setState(2623);
				match(IN);
				}
			}

			setState(2626);
			arg_declaration();
			setState(2632);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==COMMA) {
				{
				setState(2627);
				match(COMMA);
				setState(2629);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==IN) {
					{
					setState(2628);
					match(IN);
					}
				}

				setState(2631);
				arg_declaration();
				}
			}

			setState(2634);
			match(CLOSE_PARENS);
			setState(2640);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case OPEN_BRACE:
			case SEMICOLON:
				{
				setState(2635);
				body();
				}
				break;
			case ASSIGNMENT:
				{
				setState(2636);
				right_arrow();
				setState(2637);
				throwable_expression();
				setState(2638);
				match(SEMICOLON);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Arg_declarationContext extends ParserRuleContext {
		public Type_Context type_() {
			return getRuleContext(Type_Context.class,0);
		}
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TerminalNode ASSIGNMENT() { return getToken(CSharpParser.ASSIGNMENT, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public Arg_declarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arg_declaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterArg_declaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitArg_declaration(this);
		}
	}

	public final Arg_declarationContext arg_declaration() throws RecognitionException {
		Arg_declarationContext _localctx = new Arg_declarationContext(_ctx, getState());
		enterRule(_localctx, 428, RULE_arg_declaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2642);
			type_();
			setState(2643);
			identifier();
			setState(2646);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ASSIGNMENT) {
				{
				setState(2644);
				match(ASSIGNMENT);
				setState(2645);
				expression();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Method_invocationContext extends ParserRuleContext {
		public TerminalNode OPEN_PARENS() { return getToken(CSharpParser.OPEN_PARENS, 0); }
		public TerminalNode CLOSE_PARENS() { return getToken(CSharpParser.CLOSE_PARENS, 0); }
		public Argument_listContext argument_list() {
			return getRuleContext(Argument_listContext.class,0);
		}
		public Method_invocationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_method_invocation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterMethod_invocation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitMethod_invocation(this);
		}
	}

	public final Method_invocationContext method_invocation() throws RecognitionException {
		Method_invocationContext _localctx = new Method_invocationContext(_ctx, getState());
		enterRule(_localctx, 430, RULE_method_invocation);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2648);
			match(OPEN_PARENS);
			setState(2650);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ADD) | (1L << ALIAS) | (1L << ARGLIST) | (1L << ASCENDING) | (1L << ASYNC) | (1L << AWAIT) | (1L << BASE) | (1L << BOOL) | (1L << BY) | (1L << BYTE) | (1L << CHAR) | (1L << CHECKED) | (1L << DECIMAL) | (1L << DEFAULT) | (1L << DELEGATE) | (1L << DESCENDING) | (1L << DOUBLE) | (1L << DYNAMIC) | (1L << EQUALS) | (1L << FALSE) | (1L << FLOAT) | (1L << FROM) | (1L << GET) | (1L << GROUP) | (1L << IN) | (1L << INT) | (1L << INTO) | (1L << JOIN) | (1L << LET))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (LONG - 64)) | (1L << (NAMEOF - 64)) | (1L << (NEW - 64)) | (1L << (NULL_ - 64)) | (1L << (OBJECT - 64)) | (1L << (ON - 64)) | (1L << (ORDERBY - 64)) | (1L << (OUT - 64)) | (1L << (PARTIAL - 64)) | (1L << (REF - 64)) | (1L << (REMOVE - 64)) | (1L << (SBYTE - 64)) | (1L << (SELECT - 64)) | (1L << (SET - 64)) | (1L << (SHORT - 64)) | (1L << (SIZEOF - 64)) | (1L << (STRING - 64)) | (1L << (THIS - 64)) | (1L << (TRUE - 64)) | (1L << (TYPEOF - 64)) | (1L << (UINT - 64)) | (1L << (ULONG - 64)) | (1L << (UNCHECKED - 64)) | (1L << (UNMANAGED - 64)) | (1L << (USHORT - 64)) | (1L << (VAR - 64)) | (1L << (VOID - 64)) | (1L << (WHEN - 64)) | (1L << (WHERE - 64)) | (1L << (YIELD - 64)) | (1L << (IDENTIFIER - 64)) | (1L << (LITERAL_ACCESS - 64)) | (1L << (INTEGER_LITERAL - 64)) | (1L << (HEX_INTEGER_LITERAL - 64)) | (1L << (BIN_INTEGER_LITERAL - 64)) | (1L << (REAL_LITERAL - 64)) | (1L << (CHARACTER_LITERAL - 64)) | (1L << (REGULAR_STRING - 64)) | (1L << (VERBATIUM_STRING - 64)) | (1L << (INTERPOLATED_REGULAR_STRING_START - 64)))) != 0) || ((((_la - 128)) & ~0x3f) == 0 && ((1L << (_la - 128)) & ((1L << (INTERPOLATED_VERBATIUM_STRING_START - 128)) | (1L << (OPEN_PARENS - 128)) | (1L << (PLUS - 128)) | (1L << (MINUS - 128)) | (1L << (STAR - 128)) | (1L << (AMP - 128)) | (1L << (CARET - 128)) | (1L << (BANG - 128)) | (1L << (TILDE - 128)) | (1L << (OP_INC - 128)) | (1L << (OP_DEC - 128)) | (1L << (OP_RANGE - 128)))) != 0)) {
				{
				setState(2649);
				argument_list();
				}
			}

			setState(2652);
			match(CLOSE_PARENS);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Object_creation_expressionContext extends ParserRuleContext {
		public TerminalNode OPEN_PARENS() { return getToken(CSharpParser.OPEN_PARENS, 0); }
		public TerminalNode CLOSE_PARENS() { return getToken(CSharpParser.CLOSE_PARENS, 0); }
		public Argument_listContext argument_list() {
			return getRuleContext(Argument_listContext.class,0);
		}
		public Object_or_collection_initializerContext object_or_collection_initializer() {
			return getRuleContext(Object_or_collection_initializerContext.class,0);
		}
		public Object_creation_expressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_object_creation_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterObject_creation_expression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitObject_creation_expression(this);
		}
	}

	public final Object_creation_expressionContext object_creation_expression() throws RecognitionException {
		Object_creation_expressionContext _localctx = new Object_creation_expressionContext(_ctx, getState());
		enterRule(_localctx, 432, RULE_object_creation_expression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2654);
			match(OPEN_PARENS);
			setState(2656);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ADD) | (1L << ALIAS) | (1L << ARGLIST) | (1L << ASCENDING) | (1L << ASYNC) | (1L << AWAIT) | (1L << BASE) | (1L << BOOL) | (1L << BY) | (1L << BYTE) | (1L << CHAR) | (1L << CHECKED) | (1L << DECIMAL) | (1L << DEFAULT) | (1L << DELEGATE) | (1L << DESCENDING) | (1L << DOUBLE) | (1L << DYNAMIC) | (1L << EQUALS) | (1L << FALSE) | (1L << FLOAT) | (1L << FROM) | (1L << GET) | (1L << GROUP) | (1L << IN) | (1L << INT) | (1L << INTO) | (1L << JOIN) | (1L << LET))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (LONG - 64)) | (1L << (NAMEOF - 64)) | (1L << (NEW - 64)) | (1L << (NULL_ - 64)) | (1L << (OBJECT - 64)) | (1L << (ON - 64)) | (1L << (ORDERBY - 64)) | (1L << (OUT - 64)) | (1L << (PARTIAL - 64)) | (1L << (REF - 64)) | (1L << (REMOVE - 64)) | (1L << (SBYTE - 64)) | (1L << (SELECT - 64)) | (1L << (SET - 64)) | (1L << (SHORT - 64)) | (1L << (SIZEOF - 64)) | (1L << (STRING - 64)) | (1L << (THIS - 64)) | (1L << (TRUE - 64)) | (1L << (TYPEOF - 64)) | (1L << (UINT - 64)) | (1L << (ULONG - 64)) | (1L << (UNCHECKED - 64)) | (1L << (UNMANAGED - 64)) | (1L << (USHORT - 64)) | (1L << (VAR - 64)) | (1L << (VOID - 64)) | (1L << (WHEN - 64)) | (1L << (WHERE - 64)) | (1L << (YIELD - 64)) | (1L << (IDENTIFIER - 64)) | (1L << (LITERAL_ACCESS - 64)) | (1L << (INTEGER_LITERAL - 64)) | (1L << (HEX_INTEGER_LITERAL - 64)) | (1L << (BIN_INTEGER_LITERAL - 64)) | (1L << (REAL_LITERAL - 64)) | (1L << (CHARACTER_LITERAL - 64)) | (1L << (REGULAR_STRING - 64)) | (1L << (VERBATIUM_STRING - 64)) | (1L << (INTERPOLATED_REGULAR_STRING_START - 64)))) != 0) || ((((_la - 128)) & ~0x3f) == 0 && ((1L << (_la - 128)) & ((1L << (INTERPOLATED_VERBATIUM_STRING_START - 128)) | (1L << (OPEN_PARENS - 128)) | (1L << (PLUS - 128)) | (1L << (MINUS - 128)) | (1L << (STAR - 128)) | (1L << (AMP - 128)) | (1L << (CARET - 128)) | (1L << (BANG - 128)) | (1L << (TILDE - 128)) | (1L << (OP_INC - 128)) | (1L << (OP_DEC - 128)) | (1L << (OP_RANGE - 128)))) != 0)) {
				{
				setState(2655);
				argument_list();
				}
			}

			setState(2658);
			match(CLOSE_PARENS);
			setState(2660);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==OPEN_BRACE) {
				{
				setState(2659);
				object_or_collection_initializer();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IdentifierContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(CSharpParser.IDENTIFIER, 0); }
		public TerminalNode ADD() { return getToken(CSharpParser.ADD, 0); }
		public TerminalNode ALIAS() { return getToken(CSharpParser.ALIAS, 0); }
		public TerminalNode ARGLIST() { return getToken(CSharpParser.ARGLIST, 0); }
		public TerminalNode ASCENDING() { return getToken(CSharpParser.ASCENDING, 0); }
		public TerminalNode ASYNC() { return getToken(CSharpParser.ASYNC, 0); }
		public TerminalNode AWAIT() { return getToken(CSharpParser.AWAIT, 0); }
		public TerminalNode BY() { return getToken(CSharpParser.BY, 0); }
		public TerminalNode DESCENDING() { return getToken(CSharpParser.DESCENDING, 0); }
		public TerminalNode DYNAMIC() { return getToken(CSharpParser.DYNAMIC, 0); }
		public TerminalNode EQUALS() { return getToken(CSharpParser.EQUALS, 0); }
		public TerminalNode FROM() { return getToken(CSharpParser.FROM, 0); }
		public TerminalNode GET() { return getToken(CSharpParser.GET, 0); }
		public TerminalNode GROUP() { return getToken(CSharpParser.GROUP, 0); }
		public TerminalNode INTO() { return getToken(CSharpParser.INTO, 0); }
		public TerminalNode JOIN() { return getToken(CSharpParser.JOIN, 0); }
		public TerminalNode LET() { return getToken(CSharpParser.LET, 0); }
		public TerminalNode NAMEOF() { return getToken(CSharpParser.NAMEOF, 0); }
		public TerminalNode ON() { return getToken(CSharpParser.ON, 0); }
		public TerminalNode ORDERBY() { return getToken(CSharpParser.ORDERBY, 0); }
		public TerminalNode PARTIAL() { return getToken(CSharpParser.PARTIAL, 0); }
		public TerminalNode REMOVE() { return getToken(CSharpParser.REMOVE, 0); }
		public TerminalNode SELECT() { return getToken(CSharpParser.SELECT, 0); }
		public TerminalNode SET() { return getToken(CSharpParser.SET, 0); }
		public TerminalNode UNMANAGED() { return getToken(CSharpParser.UNMANAGED, 0); }
		public TerminalNode VAR() { return getToken(CSharpParser.VAR, 0); }
		public TerminalNode WHEN() { return getToken(CSharpParser.WHEN, 0); }
		public TerminalNode WHERE() { return getToken(CSharpParser.WHERE, 0); }
		public TerminalNode YIELD() { return getToken(CSharpParser.YIELD, 0); }
		public IdentifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_identifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).enterIdentifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CSharpParserListener ) ((CSharpParserListener)listener).exitIdentifier(this);
		}
	}

	public final IdentifierContext identifier() throws RecognitionException {
		IdentifierContext _localctx = new IdentifierContext(_ctx, getState());
		enterRule(_localctx, 434, RULE_identifier);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2662);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ADD) | (1L << ALIAS) | (1L << ARGLIST) | (1L << ASCENDING) | (1L << ASYNC) | (1L << AWAIT) | (1L << BY) | (1L << DESCENDING) | (1L << DYNAMIC) | (1L << EQUALS) | (1L << FROM) | (1L << GET) | (1L << GROUP) | (1L << INTO) | (1L << JOIN) | (1L << LET))) != 0) || ((((_la - 65)) & ~0x3f) == 0 && ((1L << (_la - 65)) & ((1L << (NAMEOF - 65)) | (1L << (ON - 65)) | (1L << (ORDERBY - 65)) | (1L << (PARTIAL - 65)) | (1L << (REMOVE - 65)) | (1L << (SELECT - 65)) | (1L << (SET - 65)) | (1L << (UNMANAGED - 65)) | (1L << (VAR - 65)) | (1L << (WHEN - 65)) | (1L << (WHERE - 65)) | (1L << (YIELD - 65)) | (1L << (IDENTIFIER - 65)))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 187:
			return right_arrow_sempred((Right_arrowContext)_localctx, predIndex);
		case 188:
			return right_shift_sempred((Right_shiftContext)_localctx, predIndex);
		case 189:
			return right_shift_assignment_sempred((Right_shift_assignmentContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean right_arrow_sempred(Right_arrowContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return (((Right_arrowContext)_localctx).first!=null?((Right_arrowContext)_localctx).first.getTokenIndex():0) + 1 == (((Right_arrowContext)_localctx).second!=null?((Right_arrowContext)_localctx).second.getTokenIndex():0);
		}
		return true;
	}
	private boolean right_shift_sempred(Right_shiftContext _localctx, int predIndex) {
		switch (predIndex) {
		case 1:
			return (((Right_shiftContext)_localctx).first!=null?((Right_shiftContext)_localctx).first.getTokenIndex():0) + 1 == (((Right_shiftContext)_localctx).second!=null?((Right_shiftContext)_localctx).second.getTokenIndex():0);
		}
		return true;
	}
	private boolean right_shift_assignment_sempred(Right_shift_assignmentContext _localctx, int predIndex) {
		switch (predIndex) {
		case 2:
			return (((Right_shift_assignmentContext)_localctx).first!=null?((Right_shift_assignmentContext)_localctx).first.getTokenIndex():0) + 1 == (((Right_shift_assignmentContext)_localctx).second!=null?((Right_shift_assignmentContext)_localctx).second.getTokenIndex():0);
		}
		return true;
	}

	private static final int _serializedATNSegments = 2;
	private static final String _serializedATNSegment0 =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\u00cc\u0a6b\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t="+
		"\4>\t>\4?\t?\4@\t@\4A\tA\4B\tB\4C\tC\4D\tD\4E\tE\4F\tF\4G\tG\4H\tH\4I"+
		"\tI\4J\tJ\4K\tK\4L\tL\4M\tM\4N\tN\4O\tO\4P\tP\4Q\tQ\4R\tR\4S\tS\4T\tT"+
		"\4U\tU\4V\tV\4W\tW\4X\tX\4Y\tY\4Z\tZ\4[\t[\4\\\t\\\4]\t]\4^\t^\4_\t_\4"+
		"`\t`\4a\ta\4b\tb\4c\tc\4d\td\4e\te\4f\tf\4g\tg\4h\th\4i\ti\4j\tj\4k\t"+
		"k\4l\tl\4m\tm\4n\tn\4o\to\4p\tp\4q\tq\4r\tr\4s\ts\4t\tt\4u\tu\4v\tv\4"+
		"w\tw\4x\tx\4y\ty\4z\tz\4{\t{\4|\t|\4}\t}\4~\t~\4\177\t\177\4\u0080\t\u0080"+
		"\4\u0081\t\u0081\4\u0082\t\u0082\4\u0083\t\u0083\4\u0084\t\u0084\4\u0085"+
		"\t\u0085\4\u0086\t\u0086\4\u0087\t\u0087\4\u0088\t\u0088\4\u0089\t\u0089"+
		"\4\u008a\t\u008a\4\u008b\t\u008b\4\u008c\t\u008c\4\u008d\t\u008d\4\u008e"+
		"\t\u008e\4\u008f\t\u008f\4\u0090\t\u0090\4\u0091\t\u0091\4\u0092\t\u0092"+
		"\4\u0093\t\u0093\4\u0094\t\u0094\4\u0095\t\u0095\4\u0096\t\u0096\4\u0097"+
		"\t\u0097\4\u0098\t\u0098\4\u0099\t\u0099\4\u009a\t\u009a\4\u009b\t\u009b"+
		"\4\u009c\t\u009c\4\u009d\t\u009d\4\u009e\t\u009e\4\u009f\t\u009f\4\u00a0"+
		"\t\u00a0\4\u00a1\t\u00a1\4\u00a2\t\u00a2\4\u00a3\t\u00a3\4\u00a4\t\u00a4"+
		"\4\u00a5\t\u00a5\4\u00a6\t\u00a6\4\u00a7\t\u00a7\4\u00a8\t\u00a8\4\u00a9"+
		"\t\u00a9\4\u00aa\t\u00aa\4\u00ab\t\u00ab\4\u00ac\t\u00ac\4\u00ad\t\u00ad"+
		"\4\u00ae\t\u00ae\4\u00af\t\u00af\4\u00b0\t\u00b0\4\u00b1\t\u00b1\4\u00b2"+
		"\t\u00b2\4\u00b3\t\u00b3\4\u00b4\t\u00b4\4\u00b5\t\u00b5\4\u00b6\t\u00b6"+
		"\4\u00b7\t\u00b7\4\u00b8\t\u00b8\4\u00b9\t\u00b9\4\u00ba\t\u00ba\4\u00bb"+
		"\t\u00bb\4\u00bc\t\u00bc\4\u00bd\t\u00bd\4\u00be\t\u00be\4\u00bf\t\u00bf"+
		"\4\u00c0\t\u00c0\4\u00c1\t\u00c1\4\u00c2\t\u00c2\4\u00c3\t\u00c3\4\u00c4"+
		"\t\u00c4\4\u00c5\t\u00c5\4\u00c6\t\u00c6\4\u00c7\t\u00c7\4\u00c8\t\u00c8"+
		"\4\u00c9\t\u00c9\4\u00ca\t\u00ca\4\u00cb\t\u00cb\4\u00cc\t\u00cc\4\u00cd"+
		"\t\u00cd\4\u00ce\t\u00ce\4\u00cf\t\u00cf\4\u00d0\t\u00d0\4\u00d1\t\u00d1"+
		"\4\u00d2\t\u00d2\4\u00d3\t\u00d3\4\u00d4\t\u00d4\4\u00d5\t\u00d5\4\u00d6"+
		"\t\u00d6\4\u00d7\t\u00d7\4\u00d8\t\u00d8\4\u00d9\t\u00d9\4\u00da\t\u00da"+
		"\4\u00db\t\u00db\3\2\5\2\u01b8\n\2\3\2\5\2\u01bb\n\2\3\2\5\2\u01be\n\2"+
		"\3\2\7\2\u01c1\n\2\f\2\16\2\u01c4\13\2\3\2\5\2\u01c7\n\2\3\2\3\2\3\3\3"+
		"\3\5\3\u01cd\n\3\3\3\5\3\u01d0\n\3\3\3\3\3\3\3\5\3\u01d5\n\3\7\3\u01d7"+
		"\n\3\f\3\16\3\u01da\13\3\3\4\3\4\3\4\3\4\7\4\u01e0\n\4\f\4\16\4\u01e3"+
		"\13\4\3\5\3\5\3\5\3\5\3\5\5\5\u01ea\n\5\3\6\3\6\3\6\3\6\6\6\u01f0\n\6"+
		"\r\6\16\6\u01f1\3\6\3\6\3\7\3\7\5\7\u01f8\n\7\3\b\3\b\5\b\u01fc\n\b\3"+
		"\t\3\t\3\t\5\t\u0201\n\t\3\n\3\n\3\13\3\13\3\f\3\f\3\f\3\f\5\f\u020b\n"+
		"\f\3\r\3\r\3\r\3\r\7\r\u0211\n\r\f\r\16\r\u0214\13\r\3\r\3\r\3\16\3\16"+
		"\3\16\7\16\u021b\n\16\f\16\16\16\u021e\13\16\3\17\3\17\3\17\5\17\u0223"+
		"\n\17\3\17\5\17\u0226\n\17\3\17\3\17\5\17\u022a\n\17\3\17\3\17\3\20\3"+
		"\20\3\20\3\20\5\20\u0232\n\20\3\21\3\21\3\21\5\21\u0237\n\21\3\22\3\22"+
		"\3\22\3\22\3\22\3\22\3\22\3\22\5\22\u0241\n\22\3\23\3\23\3\23\3\23\3\23"+
		"\3\23\3\23\3\23\3\23\3\23\3\23\5\23\u024e\n\23\3\24\3\24\3\24\3\24\3\24"+
		"\3\24\5\24\u0256\n\24\3\25\3\25\3\25\3\25\5\25\u025c\n\25\5\25\u025e\n"+
		"\25\3\26\3\26\3\26\7\26\u0263\n\26\f\26\16\26\u0266\13\26\3\27\3\27\3"+
		"\27\7\27\u026b\n\27\f\27\16\27\u026e\13\27\3\30\3\30\3\30\7\30\u0273\n"+
		"\30\f\30\16\30\u0276\13\30\3\31\3\31\3\31\7\31\u027b\n\31\f\31\16\31\u027e"+
		"\13\31\3\32\3\32\3\32\7\32\u0283\n\32\f\32\16\32\u0286\13\32\3\33\3\33"+
		"\3\33\7\33\u028b\n\33\f\33\16\33\u028e\13\33\3\34\3\34\3\34\3\34\3\34"+
		"\3\34\3\34\7\34\u0297\n\34\f\34\16\34\u029a\13\34\3\35\3\35\3\35\5\35"+
		"\u029f\n\35\3\35\7\35\u02a2\n\35\f\35\16\35\u02a5\13\35\3\36\3\36\3\36"+
		"\7\36\u02aa\n\36\f\36\16\36\u02ad\13\36\3\37\3\37\3\37\7\37\u02b2\n\37"+
		"\f\37\16\37\u02b5\13\37\3 \3 \3 \3 \3 \5 \u02bc\n \5 \u02be\n \3 \5 \u02c1"+
		"\n \3!\3!\3!\7!\u02c6\n!\f!\16!\u02c9\13!\3\"\3\"\5\"\u02cd\n\"\3\"\3"+
		"\"\3\"\3#\3#\5#\u02d4\n#\3#\3#\5#\u02d8\n#\5#\u02da\n#\3$\3$\3$\3$\3$"+
		"\3$\3$\3$\3$\3$\3$\3$\3$\3$\3$\3$\3$\3$\3$\3$\3$\3$\3$\3$\3$\3$\5$\u02f6"+
		"\n$\3%\3%\5%\u02fa\n%\3%\7%\u02fd\n%\f%\16%\u0300\13%\3%\5%\u0303\n%\3"+
		"%\3%\3%\3%\3%\3%\5%\u030b\n%\3%\5%\u030e\n%\3%\7%\u0311\n%\f%\16%\u0314"+
		"\13%\3%\5%\u0317\n%\7%\u0319\n%\f%\16%\u031c\13%\3&\3&\3&\5&\u0321\n&"+
		"\3&\3&\3&\3&\3&\3&\3&\3&\3&\3&\3&\3&\5&\u032f\n&\3&\3&\3&\3&\5&\u0335"+
		"\n&\3&\3&\3&\3&\3&\3&\3&\3&\7&\u033f\n&\f&\16&\u0342\13&\3&\5&\u0345\n"+
		"&\3&\6&\u0348\n&\r&\16&\u0349\3&\3&\5&\u034e\n&\3&\3&\3&\3&\5&\u0354\n"+
		"&\3&\3&\3&\5&\u0359\n&\3&\3&\3&\3&\3&\6&\u0360\n&\r&\16&\u0361\3&\3&\3"+
		"&\3&\3&\3&\3&\5&\u036b\n&\3&\3&\3&\3&\3&\3&\3&\3&\3&\3&\3&\3&\3&\3&\3"+
		"&\3&\5&\u037d\n&\3&\5&\u0380\n&\3&\3&\3&\5&\u0385\n&\3&\5&\u0388\n&\3"+
		"&\3&\3&\3&\3&\3&\3&\3&\3&\3&\3&\7&\u0395\n&\f&\16&\u0398\13&\3&\3&\3&"+
		"\5&\u039d\n&\3\'\3\'\5\'\u03a1\n\'\3(\3(\3(\3)\5)\u03a7\n)\3)\3)\3)\5"+
		")\u03ac\n)\3*\5*\u03af\n*\3*\3*\3*\3*\7*\u03b5\n*\f*\16*\u03b8\13*\3*"+
		"\3*\3+\3+\3+\5+\u03bf\n+\3+\3+\3,\3,\3-\3-\3-\7-\u03c8\n-\f-\16-\u03cb"+
		"\13-\3.\3.\5.\u03cf\n.\3/\3/\3/\5/\u03d4\n/\5/\u03d6\n/\3/\3/\3\60\3\60"+
		"\3\60\7\60\u03dd\n\60\f\60\16\60\u03e0\13\60\3\61\3\61\3\61\3\61\3\61"+
		"\5\61\u03e7\n\61\3\61\3\61\3\61\3\62\3\62\5\62\u03ee\n\62\3\63\3\63\3"+
		"\63\3\63\7\63\u03f4\n\63\f\63\16\63\u03f7\13\63\3\63\5\63\u03fa\n\63\3"+
		"\63\3\63\3\64\3\64\3\64\3\64\3\64\5\64\u0403\n\64\3\65\3\65\3\65\5\65"+
		"\u0408\n\65\5\65\u040a\n\65\3\65\3\65\3\66\3\66\3\66\7\66\u0411\n\66\f"+
		"\66\16\66\u0414\13\66\3\67\3\67\3\67\3\67\3\67\5\67\u041b\n\67\38\38\5"+
		"8\u041f\n8\38\38\38\58\u0424\n8\58\u0426\n8\38\38\38\58\u042b\n8\78\u042d"+
		"\n8\f8\168\u0430\138\39\39\79\u0434\n9\f9\169\u0437\139\39\39\3:\3:\3"+
		":\7:\u043e\n:\f:\16:\u0441\13:\3:\5:\u0444\n:\3:\5:\u0447\n:\3:\5:\u044a"+
		"\n:\3:\5:\u044d\n:\3;\3;\3;\3;\7;\u0453\n;\f;\16;\u0456\13;\3;\3;\3<\3"+
		"<\3<\3<\3=\5=\u045f\n=\3=\3=\3=\3=\3>\3>\3>\3>\3>\3>\3>\3>\3>\3>\3>\5"+
		">\u0470\n>\3?\3?\3?\7?\u0475\n?\f?\16?\u0478\13?\3@\5@\u047b\n@\3@\3@"+
		"\3@\3A\3A\3A\7A\u0483\nA\fA\16A\u0486\13A\3B\3B\5B\u048a\nB\3C\3C\3C\3"+
		"D\3D\5D\u0491\nD\3D\3D\3D\3D\3E\7E\u0498\nE\fE\16E\u049b\13E\3E\3E\5E"+
		"\u049f\nE\3F\3F\3F\3F\3F\5F\u04a6\nF\3G\3G\3G\3G\3G\3H\3H\3H\3H\5H\u04b1"+
		"\nH\3I\3I\5I\u04b5\nI\3I\3I\3I\3I\3I\3I\3I\3I\3I\5I\u04c0\nI\3J\3J\3J"+
		"\3J\7J\u04c6\nJ\fJ\16J\u04c9\13J\3K\3K\5K\u04cd\nK\3L\3L\3L\3L\3L\3L\3"+
		"L\5L\u04d6\nL\3M\3M\3M\3M\3N\3N\3N\5N\u04df\nN\3O\3O\3O\3O\3O\3O\3O\5"+
		"O\u04e8\nO\3P\3P\3P\3Q\5Q\u04ee\nQ\3Q\3Q\3Q\5Q\u04f3\nQ\3Q\3Q\5Q\u04f7"+
		"\nQ\3Q\3Q\5Q\u04fb\nQ\3R\3R\5R\u04ff\nR\3R\3R\5R\u0503\nR\5R\u0505\nR"+
		"\3S\3S\3S\3S\3S\5S\u050c\nS\3T\3T\3T\3T\3U\3U\5U\u0514\nU\3V\3V\3V\3V"+
		"\3V\3V\3V\3V\3V\3V\3V\5V\u0521\nV\3V\3V\3V\3V\3V\3V\7V\u0529\nV\fV\16"+
		"V\u052c\13V\3V\3V\3V\3V\3V\3V\3V\3V\3V\3V\3V\3V\3V\3V\3V\3V\3V\3V\3V\5"+
		"V\u0541\nV\3V\3V\5V\u0545\nV\3V\3V\5V\u0549\nV\3V\3V\3V\5V\u054e\nV\3"+
		"V\3V\3V\3V\3V\3V\3V\3V\3V\3V\3V\3V\3V\3V\3V\3V\3V\3V\5V\u0562\nV\3V\3"+
		"V\3V\5V\u0567\nV\3V\3V\3V\5V\u056c\nV\3V\3V\3V\3V\3V\5V\u0573\nV\3V\5"+
		"V\u0576\nV\3V\3V\3V\3V\3V\3V\3V\3V\3V\3V\3V\3V\3V\3V\3V\3V\3V\3V\3V\3"+
		"V\5V\u058c\nV\3V\3V\3V\3V\3V\3V\3V\3V\3V\3V\5V\u0598\nV\3W\3W\5W\u059c"+
		"\nW\3W\3W\3X\3X\3X\3X\5X\u05a4\nX\3X\3X\3X\3X\7X\u05aa\nX\fX\16X\u05ad"+
		"\13X\3X\3X\3X\3X\5X\u05b3\nX\3Y\3Y\5Y\u05b7\nY\3Z\3Z\3Z\5Z\u05bc\nZ\3"+
		"Z\5Z\u05bf\nZ\3[\3[\3[\5[\u05c4\n[\3\\\3\\\3\\\3\\\3]\3]\5]\u05cc\n]\3"+
		"^\6^\u05cf\n^\r^\16^\u05d0\3^\3^\3_\3_\3_\5_\u05d8\n_\3_\3_\3_\3_\5_\u05de"+
		"\n_\3`\3`\3`\3a\6a\u05e4\na\ra\16a\u05e5\3b\3b\3b\3b\7b\u05ec\nb\fb\16"+
		"b\u05ef\13b\5b\u05f1\nb\3c\3c\3c\7c\u05f6\nc\fc\16c\u05f9\13c\3d\3d\7"+
		"d\u05fd\nd\fd\16d\u0600\13d\3d\5d\u0603\nd\3d\5d\u0606\nd\3e\3e\3e\3e"+
		"\5e\u060c\ne\3e\3e\5e\u0610\ne\3e\3e\3f\3f\5f\u0616\nf\3f\3f\3g\3g\3g"+
		"\3g\3g\3h\3h\3h\3i\3i\5i\u0624\ni\3j\3j\3j\3j\3j\3j\3j\3j\5j\u062e\nj"+
		"\5j\u0630\nj\3k\3k\3k\7k\u0635\nk\fk\16k\u0638\13k\3l\3l\5l\u063c\nl\3"+
		"l\5l\u063f\nl\3l\5l\u0642\nl\3l\3l\3m\6m\u0647\nm\rm\16m\u0648\3n\3n\3"+
		"n\3n\3n\3o\6o\u0651\no\ro\16o\u0652\3p\3p\3p\3p\3p\3p\3p\3p\3p\3p\3p\3"+
		"p\3p\3p\3p\5p\u0664\np\3q\6q\u0667\nq\rq\16q\u0668\3r\3r\5r\u066d\nr\3"+
		"s\5s\u0670\ns\3s\5s\u0673\ns\3s\3s\3s\3s\3s\5s\u067a\ns\3t\3t\3t\3t\5"+
		"t\u0680\nt\3u\3u\3u\3u\7u\u0686\nu\fu\16u\u0689\13u\3u\3u\3v\5v\u068e"+
		"\nv\3v\3v\3w\3w\3w\3w\7w\u0696\nw\fw\16w\u0699\13w\3x\3x\3x\7x\u069e\n"+
		"x\fx\16x\u06a1\13x\3y\6y\u06a4\ny\ry\16y\u06a5\3z\3z\3z\3z\3z\3{\3{\3"+
		"{\3{\5{\u06b1\n{\3{\3{\5{\u06b5\n{\5{\u06b7\n{\3|\3|\3|\5|\u06bc\n|\3"+
		"|\3|\5|\u06c0\n|\3}\3}\3}\7}\u06c5\n}\f}\16}\u06c8\13}\3~\3~\3~\3~\3\177"+
		"\3\177\5\177\u06d0\n\177\3\177\3\177\3\u0080\6\u0080\u06d5\n\u0080\r\u0080"+
		"\16\u0080\u06d6\3\u0081\5\u0081\u06da\n\u0081\3\u0081\5\u0081\u06dd\n"+
		"\u0081\3\u0081\3\u0081\5\u0081\u06e1\n\u0081\3\u0082\6\u0082\u06e4\n\u0082"+
		"\r\u0082\16\u0082\u06e5\3\u0083\3\u0083\3\u0084\3\u0084\3\u0084\3\u0084"+
		"\3\u0084\3\u0084\3\u0084\3\u0084\3\u0084\5\u0084\u06f3\n\u0084\3\u0084"+
		"\3\u0084\3\u0084\3\u0084\3\u0084\3\u0084\3\u0084\3\u0084\5\u0084\u06fd"+
		"\n\u0084\3\u0085\3\u0085\3\u0085\3\u0085\3\u0085\5\u0085\u0704\n\u0085"+
		"\3\u0085\3\u0085\3\u0085\3\u0085\3\u0085\3\u0085\3\u0085\3\u0085\3\u0085"+
		"\3\u0085\5\u0085\u0710\n\u0085\3\u0086\3\u0086\3\u0086\7\u0086\u0715\n"+
		"\u0086\f\u0086\16\u0086\u0718\13\u0086\3\u0087\3\u0087\3\u0087\3\u0087"+
		"\3\u0088\3\u0088\3\u0088\7\u0088\u0721\n\u0088\f\u0088\16\u0088\u0724"+
		"\13\u0088\3\u0089\3\u0089\3\u0089\5\u0089\u0729\n\u0089\3\u008a\3\u008a"+
		"\5\u008a\u072d\n\u008a\3\u008b\3\u008b\5\u008b\u0731\n\u008b\3\u008c\3"+
		"\u008c\3\u008d\3\u008d\5\u008d\u0737\n\u008d\3\u008e\3\u008e\3\u008e\3"+
		"\u008e\5\u008e\u073d\n\u008e\5\u008e\u073f\n\u008e\3\u008f\3\u008f\3\u008f"+
		"\7\u008f\u0744\n\u008f\f\u008f\16\u008f\u0747\13\u008f\3\u0090\5\u0090"+
		"\u074a\n\u0090\3\u0090\5\u0090\u074d\n\u0090\3\u0090\3\u0090\5\u0090\u0751"+
		"\n\u0090\3\u0091\3\u0091\3\u0091\3\u0091\3\u0091\3\u0091\3\u0091\3\u0091"+
		"\5\u0091\u075b\n\u0091\3\u0092\5\u0092\u075e\n\u0092\3\u0092\3\u0092\3"+
		"\u0092\3\u0092\3\u0093\5\u0093\u0765\n\u0093\3\u0093\5\u0093\u0768\n\u0093"+
		"\3\u0093\3\u0093\3\u0093\5\u0093\u076d\n\u0093\3\u0093\3\u0093\3\u0093"+
		"\5\u0093\u0772\n\u0093\5\u0093\u0774\n\u0093\3\u0094\5\u0094\u0777\n\u0094"+
		"\3\u0094\5\u0094\u077a\n\u0094\3\u0094\3\u0094\3\u0094\3\u0095\5\u0095"+
		"\u0780\n\u0095\3\u0095\5\u0095\u0783\n\u0095\3\u0095\3\u0095\3\u0095\3"+
		"\u0096\3\u0096\3\u0096\3\u0096\3\u0096\3\u0096\3\u0096\5\u0096\u078f\n"+
		"\u0096\3\u0097\3\u0097\3\u0097\3\u0097\3\u0097\5\u0097\u0796\n\u0097\3"+
		"\u0098\5\u0098\u0799\n\u0098\3\u0098\3\u0098\3\u0098\3\u0098\3\u0098\3"+
		"\u0098\3\u0098\3\u0098\5\u0098\u07a3\n\u0098\3\u0099\5\u0099\u07a6\n\u0099"+
		"\3\u0099\3\u0099\3\u0099\3\u009a\5\u009a\u07ac\n\u009a\3\u009a\3\u009a"+
		"\3\u009a\3\u009b\3\u009b\3\u009b\3\u009b\3\u009b\3\u009b\3\u009b\3\u009b"+
		"\3\u009b\3\u009b\3\u009b\3\u009b\3\u009b\3\u009b\3\u009b\3\u009b\3\u009b"+
		"\3\u009b\3\u009b\3\u009b\3\u009b\3\u009b\5\u009b\u07c7\n\u009b\3\u009c"+
		"\3\u009c\3\u009c\3\u009c\3\u009c\3\u009c\3\u009c\3\u009d\3\u009d\3\u009d"+
		"\3\u009d\5\u009d\u07d4\n\u009d\3\u009d\3\u009d\3\u009e\3\u009e\5\u009e"+
		"\u07da\n\u009e\3\u009f\3\u009f\3\u009f\3\u00a0\3\u00a0\7\u00a0\u07e1\n"+
		"\u00a0\f\u00a0\16\u00a0\u07e4\13\u00a0\3\u00a0\3\u00a0\3\u00a1\5\u00a1"+
		"\u07e9\n\u00a1\3\u00a1\5\u00a1\u07ec\n\u00a1\3\u00a1\3\u00a1\3\u00a1\3"+
		"\u00a1\6\u00a1\u07f2\n\u00a1\r\u00a1\16\u00a1\u07f3\3\u00a1\3\u00a1\5"+
		"\u00a1\u07f8\n\u00a1\3\u00a2\3\u00a2\7\u00a2\u07fc\n\u00a2\f\u00a2\16"+
		"\u00a2\u07ff\13\u00a2\3\u00a2\6\u00a2\u0802\n\u00a2\r\u00a2\16\u00a2\u0803"+
		"\3\u00a3\3\u00a3\7\u00a3\u0808\n\u00a3\f\u00a3\16\u00a3\u080b\13\u00a3"+
		"\3\u00a3\3\u00a3\3\u00a4\3\u00a4\3\u00a4\3\u00a4\7\u00a4\u0813\n\u00a4"+
		"\f\u00a4\16\u00a4\u0816\13\u00a4\3\u00a4\5\u00a4\u0819\n\u00a4\5\u00a4"+
		"\u081b\n\u00a4\3\u00a4\3\u00a4\3\u00a5\3\u00a5\3\u00a5\3\u00a5\7\u00a5"+
		"\u0823\n\u00a5\f\u00a5\16\u00a5\u0826\13\u00a5\3\u00a5\3\u00a5\3\u00a6"+
		"\5\u00a6\u082b\n\u00a6\3\u00a6\5\u00a6\u082e\n\u00a6\3\u00a6\3\u00a6\3"+
		"\u00a7\3\u00a7\3\u00a8\3\u00a8\3\u00a8\3\u00a9\3\u00a9\7\u00a9\u0839\n"+
		"\u00a9\f\u00a9\16\u00a9\u083c\13\u00a9\3\u00a9\3\u00a9\3\u00aa\5\u00aa"+
		"\u0841\n\u00aa\3\u00aa\5\u00aa\u0844\n\u00aa\3\u00aa\5\u00aa\u0847\n\u00aa"+
		"\3\u00aa\3\u00aa\3\u00aa\3\u00aa\3\u00aa\5\u00aa\u084e\n\u00aa\3\u00aa"+
		"\3\u00aa\3\u00aa\5\u00aa\u0853\n\u00aa\3\u00aa\3\u00aa\5\u00aa\u0857\n"+
		"\u00aa\3\u00aa\3\u00aa\5\u00aa\u085b\n\u00aa\3\u00aa\3\u00aa\3\u00aa\3"+
		"\u00aa\3\u00aa\3\u00aa\3\u00aa\3\u00aa\3\u00aa\3\u00aa\3\u00aa\3\u00aa"+
		"\3\u00aa\3\u00aa\3\u00aa\5\u00aa\u086c\n\u00aa\3\u00aa\5\u00aa\u086f\n"+
		"\u00aa\3\u00aa\3\u00aa\3\u00aa\5\u00aa\u0874\n\u00aa\3\u00aa\3\u00aa\5"+
		"\u00aa\u0878\n\u00aa\3\u00aa\3\u00aa\5\u00aa\u087c\n\u00aa\3\u00aa\3\u00aa"+
		"\3\u00aa\3\u00aa\3\u00aa\3\u00aa\3\u00aa\5\u00aa\u0885\n\u00aa\3\u00ab"+
		"\5\u00ab\u0888\n\u00ab\3\u00ab\3\u00ab\3\u00ab\5\u00ab\u088d\n\u00ab\3"+
		"\u00ab\3\u00ab\5\u00ab\u0891\n\u00ab\3\u00ab\3\u00ab\3\u00ab\5\u00ab\u0896"+
		"\n\u00ab\3\u00ab\3\u00ab\5\u00ab\u089a\n\u00ab\5\u00ab\u089c\n\u00ab\3"+
		"\u00ac\3\u00ac\3\u00ac\3\u00ad\3\u00ad\3\u00ad\3\u00ad\7\u00ad\u08a5\n"+
		"\u00ad\f\u00ad\16\u00ad\u08a8\13\u00ad\3\u00ad\5\u00ad\u08ab\n\u00ad\5"+
		"\u00ad\u08ad\n\u00ad\3\u00ad\3\u00ad\3\u00ae\5\u00ae\u08b2\n\u00ae\3\u00ae"+
		"\3\u00ae\3\u00ae\5\u00ae\u08b7\n\u00ae\3\u00af\3\u00af\3\u00af\3\u00af"+
		"\3\u00af\5\u00af\u08be\n\u00af\3\u00af\3\u00af\3\u00b0\3\u00b0\5\u00b0"+
		"\u08c4\n\u00b0\3\u00b1\6\u00b1\u08c7\n\u00b1\r\u00b1\16\u00b1\u08c8\3"+
		"\u00b2\3\u00b2\3\u00b2\3\u00b2\5\u00b2\u08cf\n\u00b2\3\u00b2\3\u00b2\5"+
		"\u00b2\u08d3\n\u00b2\3\u00b2\3\u00b2\3\u00b3\3\u00b3\5\u00b3\u08d9\n\u00b3"+
		"\3\u00b4\3\u00b4\3\u00b4\7\u00b4\u08de\n\u00b4\f\u00b4\16\u00b4\u08e1"+
		"\13\u00b4\3\u00b5\3\u00b5\3\u00b5\3\u00b5\3\u00b5\7\u00b5\u08e8\n\u00b5"+
		"\f\u00b5\16\u00b5\u08eb\13\u00b5\5\u00b5\u08ed\n\u00b5\3\u00b5\5\u00b5"+
		"\u08f0\n\u00b5\3\u00b6\3\u00b6\3\u00b6\5\u00b6\u08f5\n\u00b6\3\u00b6\3"+
		"\u00b6\3\u00b7\3\u00b7\5\u00b7\u08fb\n\u00b7\3\u00b7\3\u00b7\7\u00b7\u08ff"+
		"\n\u00b7\f\u00b7\16\u00b7\u0902\13\u00b7\3\u00b7\3\u00b7\3\u00b7\3\u00b7"+
		"\5\u00b7\u0908\n\u00b7\3\u00b8\3\u00b8\3\u00b8\7\u00b8\u090d\n\u00b8\f"+
		"\u00b8\16\u00b8\u0910\13\u00b8\3\u00b9\3\u00b9\3\u00b9\3\u00b9\3\u00ba"+
		"\5\u00ba\u0917\n\u00ba\3\u00ba\3\u00ba\5\u00ba\u091b\n\u00ba\3\u00bb\3"+
		"\u00bb\3\u00bb\3\u00bb\3\u00bb\3\u00bc\3\u00bc\3\u00bc\3\u00bc\3\u00bc"+
		"\3\u00bc\3\u00bc\3\u00bc\5\u00bc\u092a\n\u00bc\3\u00bc\3\u00bc\5\u00bc"+
		"\u092e\n\u00bc\3\u00bc\3\u00bc\3\u00bc\3\u00bc\3\u00bc\7\u00bc\u0935\n"+
		"\u00bc\f\u00bc\16\u00bc\u0938\13\u00bc\3\u00bc\5\u00bc\u093b\n\u00bc\3"+
		"\u00bc\3\u00bc\5\u00bc\u093f\n\u00bc\3\u00bd\3\u00bd\3\u00bd\3\u00bd\3"+
		"\u00be\3\u00be\3\u00be\3\u00be\3\u00bf\3\u00bf\3\u00bf\3\u00bf\3\u00c0"+
		"\3\u00c0\3\u00c0\3\u00c0\3\u00c0\3\u00c0\3\u00c0\3\u00c0\5\u00c0\u0955"+
		"\n\u00c0\3\u00c1\3\u00c1\3\u00c2\3\u00c2\3\u00c2\3\u00c2\5\u00c2\u095d"+
		"\n\u00c2\3\u00c3\3\u00c3\7\u00c3\u0961\n\u00c3\f\u00c3\16\u00c3\u0964"+
		"\13\u00c3\3\u00c3\3\u00c3\3\u00c4\3\u00c4\7\u00c4\u096a\n\u00c4\f\u00c4"+
		"\16\u00c4\u096d\13\u00c4\3\u00c4\3\u00c4\3\u00c5\3\u00c5\3\u00c5\3\u00c5"+
		"\5\u00c5\u0975\n\u00c5\3\u00c6\3\u00c6\3\u00c6\3\u00c6\5\u00c6\u097b\n"+
		"\u00c6\3\u00c7\3\u00c7\3\u00c7\7\u00c7\u0980\n\u00c7\f\u00c7\16\u00c7"+
		"\u0983\13\u00c7\3\u00c7\3\u00c7\6\u00c7\u0987\n\u00c7\r\u00c7\16\u00c7"+
		"\u0988\5\u00c7\u098b\n\u00c7\3\u00c8\3\u00c8\3\u00c9\3\u00c9\3\u00c9\5"+
		"\u00c9\u0992\n\u00c9\3\u00c9\5\u00c9\u0995\n\u00c9\3\u00c9\5\u00c9\u0998"+
		"\n\u00c9\3\u00c9\3\u00c9\5\u00c9\u099c\n\u00c9\3\u00ca\5\u00ca\u099f\n"+
		"\u00ca\3\u00ca\3\u00ca\3\u00ca\5\u00ca\u09a4\n\u00ca\3\u00ca\5\u00ca\u09a7"+
		"\n\u00ca\3\u00ca\5\u00ca\u09aa\n\u00ca\3\u00ca\3\u00ca\5\u00ca\u09ae\n"+
		"\u00ca\3\u00cb\3\u00cb\3\u00cb\5\u00cb\u09b3\n\u00cb\3\u00cb\5\u00cb\u09b6"+
		"\n\u00cb\3\u00cb\5\u00cb\u09b9\n\u00cb\3\u00cb\3\u00cb\5\u00cb\u09bd\n"+
		"\u00cb\3\u00cc\3\u00cc\3\u00cc\5\u00cc\u09c2\n\u00cc\3\u00cc\3\u00cc\5"+
		"\u00cc\u09c6\n\u00cc\3\u00cd\3\u00cd\3\u00cd\3\u00cd\5\u00cd\u09cc\n\u00cd"+
		"\3\u00cd\3\u00cd\5\u00cd\u09d0\n\u00cd\3\u00cd\3\u00cd\5\u00cd\u09d4\n"+
		"\u00cd\3\u00cd\3\u00cd\3\u00ce\3\u00ce\3\u00ce\3\u00ce\3\u00ce\3\u00ce"+
		"\3\u00ce\3\u00ce\3\u00ce\3\u00ce\5\u00ce\u09e2\n\u00ce\3\u00cf\3\u00cf"+
		"\3\u00cf\3\u00d0\3\u00d0\3\u00d0\3\u00d0\3\u00d0\3\u00d0\3\u00d0\3\u00d0"+
		"\5\u00d0\u09ef\n\u00d0\3\u00d0\3\u00d0\3\u00d0\3\u00d0\5\u00d0\u09f5\n"+
		"\u00d0\3\u00d1\3\u00d1\3\u00d1\3\u00d1\3\u00d1\3\u00d2\3\u00d2\3\u00d2"+
		"\3\u00d2\3\u00d2\3\u00d2\3\u00d2\3\u00d2\3\u00d2\3\u00d2\3\u00d2\3\u00d2"+
		"\5\u00d2\u0a08\n\u00d2\3\u00d3\3\u00d3\3\u00d3\3\u00d3\3\u00d3\3\u00d3"+
		"\3\u00d4\3\u00d4\3\u00d4\5\u00d4\u0a13\n\u00d4\3\u00d4\3\u00d4\5\u00d4"+
		"\u0a17\n\u00d4\3\u00d4\3\u00d4\3\u00d5\3\u00d5\5\u00d5\u0a1d\n\u00d5\3"+
		"\u00d5\3\u00d5\5\u00d5\u0a21\n\u00d5\3\u00d5\3\u00d5\5\u00d5\u0a25\n\u00d5"+
		"\3\u00d5\3\u00d5\3\u00d5\3\u00d5\3\u00d5\5\u00d5\u0a2c\n\u00d5\3\u00d6"+
		"\3\u00d6\3\u00d6\3\u00d6\3\u00d6\5\u00d6\u0a33\n\u00d6\3\u00d6\5\u00d6"+
		"\u0a36\n\u00d6\3\u00d6\3\u00d6\7\u00d6\u0a3a\n\u00d6\f\u00d6\16\u00d6"+
		"\u0a3d\13\u00d6\3\u00d7\3\u00d7\3\u00d7\3\u00d7\5\u00d7\u0a43\n\u00d7"+
		"\3\u00d7\3\u00d7\3\u00d7\5\u00d7\u0a48\n\u00d7\3\u00d7\5\u00d7\u0a4b\n"+
		"\u00d7\3\u00d7\3\u00d7\3\u00d7\3\u00d7\3\u00d7\3\u00d7\5\u00d7\u0a53\n"+
		"\u00d7\3\u00d8\3\u00d8\3\u00d8\3\u00d8\5\u00d8\u0a59\n\u00d8\3\u00d9\3"+
		"\u00d9\5\u00d9\u0a5d\n\u00d9\3\u00d9\3\u00d9\3\u00da\3\u00da\5\u00da\u0a63"+
		"\n\u00da\3\u00da\3\u00da\5\u00da\u0a67\n\u00da\3\u00db\3\u00db\3\u00db"+
		"\2\2\u00dc\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\64\66"+
		"8:<>@BDFHJLNPRTVXZ\\^`bdfhjlnprtvxz|~\u0080\u0082\u0084\u0086\u0088\u008a"+
		"\u008c\u008e\u0090\u0092\u0094\u0096\u0098\u009a\u009c\u009e\u00a0\u00a2"+
		"\u00a4\u00a6\u00a8\u00aa\u00ac\u00ae\u00b0\u00b2\u00b4\u00b6\u00b8\u00ba"+
		"\u00bc\u00be\u00c0\u00c2\u00c4\u00c6\u00c8\u00ca\u00cc\u00ce\u00d0\u00d2"+
		"\u00d4\u00d6\u00d8\u00da\u00dc\u00de\u00e0\u00e2\u00e4\u00e6\u00e8\u00ea"+
		"\u00ec\u00ee\u00f0\u00f2\u00f4\u00f6\u00f8\u00fa\u00fc\u00fe\u0100\u0102"+
		"\u0104\u0106\u0108\u010a\u010c\u010e\u0110\u0112\u0114\u0116\u0118\u011a"+
		"\u011c\u011e\u0120\u0122\u0124\u0126\u0128\u012a\u012c\u012e\u0130\u0132"+
		"\u0134\u0136\u0138\u013a\u013c\u013e\u0140\u0142\u0144\u0146\u0148\u014a"+
		"\u014c\u014e\u0150\u0152\u0154\u0156\u0158\u015a\u015c\u015e\u0160\u0162"+
		"\u0164\u0166\u0168\u016a\u016c\u016e\u0170\u0172\u0174\u0176\u0178\u017a"+
		"\u017c\u017e\u0180\u0182\u0184\u0186\u0188\u018a\u018c\u018e\u0190\u0192"+
		"\u0194\u0196\u0198\u019a\u019c\u019e\u01a0\u01a2\u01a4\u01a6\u01a8\u01aa"+
		"\u01ac\u01ae\u01b0\u01b2\u01b4\2\25\n\2\27\27\32\32::BBXX\\\\ijnn\4\2"+
		"$$\60\60\5\299MMUU\3\2\u00a2\u00a3\4\2\u0098\u0099\u00a4\u00a5\3\2\u008d"+
		"\u008e\3\2\u008f\u0091\20\2\24\24\27\27\32\32\37\37$$\60\60::BBIIXX\\"+
		"\\``ijnn\4\2\20\20\"\"\4\2\21\21mm\17\2\13\13\21\21++--<<EFNNPTYY__mm"+
		"qqss\4\2**88\4\2\23\23cc\4\2\u008f\u008f\u009a\u009a\4\299MM\4\2,,ff\27"+
		"\2\13\13\17\17\23\25\27!#$&\')\62\65\65\67<>>ABDFHIKKMOQUWY\\dfoqsvv\3"+
		"\2TU\26\2\f\16\20\22\26\26\"\"%%((\63\64\66\66==?@CCJJLLPPVVZ[llpptuw"+
		"x\2\u0b80\2\u01b7\3\2\2\2\4\u01cf\3\2\2\2\6\u01db\3\2\2\2\b\u01e9\3\2"+
		"\2\2\n\u01eb\3\2\2\2\f\u01f5\3\2\2\2\16\u01fb\3\2\2\2\20\u0200\3\2\2\2"+
		"\22\u0202\3\2\2\2\24\u0204\3\2\2\2\26\u020a\3\2\2\2\30\u020c\3\2\2\2\32"+
		"\u0217\3\2\2\2\34\u0222\3\2\2\2\36\u0231\3\2\2\2 \u0236\3\2\2\2\"\u0240"+
		"\3\2\2\2$\u024d\3\2\2\2&\u024f\3\2\2\2(\u0257\3\2\2\2*\u025f\3\2\2\2,"+
		"\u0267\3\2\2\2.\u026f\3\2\2\2\60\u0277\3\2\2\2\62\u027f\3\2\2\2\64\u0287"+
		"\3\2\2\2\66\u028f\3\2\2\28\u029b\3\2\2\2:\u02a6\3\2\2\2<\u02ae\3\2\2\2"+
		">\u02b6\3\2\2\2@\u02c2\3\2\2\2B\u02ca\3\2\2\2D\u02d9\3\2\2\2F\u02f5\3"+
		"\2\2\2H\u02f7\3\2\2\2J\u039c\3\2\2\2L\u03a0\3\2\2\2N\u03a2\3\2\2\2P\u03a6"+
		"\3\2\2\2R\u03ae\3\2\2\2T\u03be\3\2\2\2V\u03c2\3\2\2\2X\u03c4\3\2\2\2Z"+
		"\u03ce\3\2\2\2\\\u03d0\3\2\2\2^\u03d9\3\2\2\2`\u03e6\3\2\2\2b\u03ed\3"+
		"\2\2\2d\u03ef\3\2\2\2f\u0402\3\2\2\2h\u0404\3\2\2\2j\u040d\3\2\2\2l\u041a"+
		"\3\2\2\2n\u041c\3\2\2\2p\u0431\3\2\2\2r\u044c\3\2\2\2t\u044e\3\2\2\2v"+
		"\u0459\3\2\2\2x\u045e\3\2\2\2z\u046f\3\2\2\2|\u0471\3\2\2\2~\u047a\3\2"+
		"\2\2\u0080\u047f\3\2\2\2\u0082\u0489\3\2\2\2\u0084\u048b\3\2\2\2\u0086"+
		"\u048e\3\2\2\2\u0088\u0499\3\2\2\2\u008a\u04a5\3\2\2\2\u008c\u04a7\3\2"+
		"\2\2\u008e\u04b0\3\2\2\2\u0090\u04b2\3\2\2\2\u0092\u04c1\3\2\2\2\u0094"+
		"\u04ca\3\2\2\2\u0096\u04d5\3\2\2\2\u0098\u04d7\3\2\2\2\u009a\u04de\3\2"+
		"\2\2\u009c\u04e7\3\2\2\2\u009e\u04e9\3\2\2\2\u00a0\u04ed\3\2\2\2\u00a2"+
		"\u0504\3\2\2\2\u00a4\u050b\3\2\2\2\u00a6\u050d\3\2\2\2\u00a8\u0513\3\2"+
		"\2\2\u00aa\u0597\3\2\2\2\u00ac\u0599\3\2\2\2\u00ae\u05b2\3\2\2\2\u00b0"+
		"\u05b6\3\2\2\2\u00b2\u05b8\3\2\2\2\u00b4\u05c3\3\2\2\2\u00b6\u05c5\3\2"+
		"\2\2\u00b8\u05cb\3\2\2\2\u00ba\u05ce\3\2\2\2\u00bc\u05dd\3\2\2\2\u00be"+
		"\u05df\3\2\2\2\u00c0\u05e3\3\2\2\2\u00c2\u05f0\3\2\2\2\u00c4\u05f2\3\2"+
		"\2\2\u00c6\u0605\3\2\2\2\u00c8\u0607\3\2\2\2\u00ca\u0613\3\2\2\2\u00cc"+
		"\u0619\3\2\2\2\u00ce\u061e\3\2\2\2\u00d0\u0623\3\2\2\2\u00d2\u062f\3\2"+
		"\2\2\u00d4\u0631\3\2\2\2\u00d6\u0639\3\2\2\2\u00d8\u0646\3\2\2\2\u00da"+
		"\u064a\3\2\2\2\u00dc\u0650\3\2\2\2\u00de\u0663\3\2\2\2\u00e0\u0666\3\2"+
		"\2\2\u00e2\u066c\3\2\2\2\u00e4\u066f\3\2\2\2\u00e6\u067b\3\2\2\2\u00e8"+
		"\u0681\3\2\2\2\u00ea\u068d\3\2\2\2\u00ec\u0691\3\2\2\2\u00ee\u069a\3\2"+
		"\2\2\u00f0\u06a3\3\2\2\2\u00f2\u06a7\3\2\2\2\u00f4\u06b6\3\2\2\2\u00f6"+
		"\u06bf\3\2\2\2\u00f8\u06c1\3\2\2\2\u00fa\u06c9\3\2\2\2\u00fc\u06cd\3\2"+
		"\2\2\u00fe\u06d4\3\2\2\2\u0100\u06d9\3\2\2\2\u0102\u06e3\3\2\2\2\u0104"+
		"\u06e7\3\2\2\2\u0106\u06fc\3\2\2\2\u0108\u0703\3\2\2\2\u010a\u0711\3\2"+
		"\2\2\u010c\u0719\3\2\2\2\u010e\u071d\3\2\2\2\u0110\u0725\3\2\2\2\u0112"+
		"\u072c\3\2\2\2\u0114\u0730\3\2\2\2\u0116\u0732\3\2\2\2\u0118\u0736\3\2"+
		"\2\2\u011a\u073e\3\2\2\2\u011c\u0740\3\2\2\2\u011e\u0750\3\2\2\2\u0120"+
		"\u075a\3\2\2\2\u0122\u075d\3\2\2\2\u0124\u0764\3\2\2\2\u0126\u0776\3\2"+
		"\2\2\u0128\u077f\3\2\2\2\u012a\u078e\3\2\2\2\u012c\u0795\3\2\2\2\u012e"+
		"\u0798\3\2\2\2\u0130\u07a5\3\2\2\2\u0132\u07ab\3\2\2\2\u0134\u07c6\3\2"+
		"\2\2\u0136\u07c8\3\2\2\2\u0138\u07cf\3\2\2\2\u013a\u07d9\3\2\2\2\u013c"+
		"\u07db\3\2\2\2\u013e\u07de\3\2\2\2\u0140\u07e8\3\2\2\2\u0142\u07f9\3\2"+
		"\2\2\u0144\u0805\3\2\2\2\u0146\u080e\3\2\2\2\u0148\u081e\3\2\2\2\u014a"+
		"\u082a\3\2\2\2\u014c\u0831\3\2\2\2\u014e\u0833\3\2\2\2\u0150\u0836\3\2"+
		"\2\2\u0152\u0840\3\2\2\2\u0154\u0887\3\2\2\2\u0156\u089d\3\2\2\2\u0158"+
		"\u08a0\3\2\2\2\u015a\u08b1\3\2\2\2\u015c\u08b8\3\2\2\2\u015e\u08c3\3\2"+
		"\2\2\u0160\u08c6\3\2\2\2\u0162\u08ca\3\2\2\2\u0164\u08d8\3\2\2\2\u0166"+
		"\u08da\3\2\2\2\u0168\u08e2\3\2\2\2\u016a\u08f4\3\2\2\2\u016c\u0907\3\2"+
		"\2\2\u016e\u0909\3\2\2\2\u0170\u0911\3\2\2\2\u0172\u091a\3\2\2\2\u0174"+
		"\u091c\3\2\2\2\u0176\u093e\3\2\2\2\u0178\u0940\3\2\2\2\u017a\u0944\3\2"+
		"\2\2\u017c\u0948\3\2\2\2\u017e\u0954\3\2\2\2\u0180\u0956\3\2\2\2\u0182"+
		"\u095c\3\2\2\2\u0184\u095e\3\2\2\2\u0186\u0967\3\2\2\2\u0188\u0974\3\2"+
		"\2\2\u018a\u097a\3\2\2\2\u018c\u097c\3\2\2\2\u018e\u098c\3\2\2\2\u0190"+
		"\u098e\3\2\2\2\u0192\u099e\3\2\2\2\u0194\u09af\3\2\2\2\u0196\u09be\3\2"+
		"\2\2\u0198\u09c7\3\2\2\2\u019a\u09d7\3\2\2\2\u019c\u09e3\3\2\2\2\u019e"+
		"\u09e6\3\2\2\2\u01a0\u09f6\3\2\2\2\u01a2\u09fb\3\2\2\2\u01a4\u0a09\3\2"+
		"\2\2\u01a6\u0a0f\3\2\2\2\u01a8\u0a1a\3\2\2\2\u01aa\u0a32\3\2\2\2\u01ac"+
		"\u0a3e\3\2\2\2\u01ae\u0a54\3\2\2\2\u01b0\u0a5a\3\2\2\2\u01b2\u0a60\3\2"+
		"\2\2\u01b4\u0a68\3\2\2\2\u01b6\u01b8\7\3\2\2\u01b7\u01b6\3\2\2\2\u01b7"+
		"\u01b8\3\2\2\2\u01b8\u01ba\3\2\2\2\u01b9\u01bb\5\u00d8m\2\u01ba\u01b9"+
		"\3\2\2\2\u01ba\u01bb\3\2\2\2\u01bb\u01bd\3\2\2\2\u01bc\u01be\5\u00dco"+
		"\2\u01bd\u01bc\3\2\2\2\u01bd\u01be\3\2\2\2\u01be\u01c2\3\2\2\2\u01bf\u01c1"+
		"\5\u015c\u00af\2\u01c0\u01bf\3\2\2\2\u01c1\u01c4\3\2\2\2\u01c2\u01c0\3"+
		"\2\2\2\u01c2\u01c3\3\2\2\2\u01c3\u01c6\3\2\2\2\u01c4\u01c2\3\2\2\2\u01c5"+
		"\u01c7\5\u00e0q\2\u01c6\u01c5\3\2\2\2\u01c6\u01c7\3\2\2\2\u01c7\u01c8"+
		"\3\2\2\2\u01c8\u01c9\7\2\2\3\u01c9\3\3\2\2\2\u01ca\u01cc\5\u01b4\u00db"+
		"\2\u01cb\u01cd\5\30\r\2\u01cc\u01cb\3\2\2\2\u01cc\u01cd\3\2\2\2\u01cd"+
		"\u01d0\3\2\2\2\u01ce\u01d0\5\u00e6t\2\u01cf\u01ca\3\2\2\2\u01cf\u01ce"+
		"\3\2\2\2\u01d0\u01d8\3\2\2\2\u01d1\u01d2\7\u0089\2\2\u01d2\u01d4\5\u01b4"+
		"\u00db\2\u01d3\u01d5\5\30\r\2\u01d4\u01d3\3\2\2\2\u01d4\u01d5\3\2\2\2"+
		"\u01d5\u01d7\3\2\2\2\u01d6\u01d1\3\2\2\2\u01d7\u01da\3\2\2\2\u01d8\u01d6"+
		"\3\2\2\2\u01d8\u01d9\3\2\2\2\u01d9\5\3\2\2\2\u01da\u01d8\3\2\2\2\u01db"+
		"\u01e1\5\b\5\2\u01dc\u01e0\7\u009a\2\2\u01dd\u01e0\5\u0144\u00a3\2\u01de"+
		"\u01e0\7\u008f\2\2\u01df\u01dc\3\2\2\2\u01df\u01dd\3\2\2\2\u01df\u01de"+
		"\3\2\2\2\u01e0\u01e3\3\2\2\2\u01e1\u01df\3\2\2\2\u01e1\u01e2\3\2\2\2\u01e2"+
		"\7\3\2\2\2\u01e3\u01e1\3\2\2\2\u01e4\u01ea\5\16\b\2\u01e5\u01ea\5\26\f"+
		"\2\u01e6\u01e7\7r\2\2\u01e7\u01ea\7\u008f\2\2\u01e8\u01ea\5\n\6\2\u01e9"+
		"\u01e4\3\2\2\2\u01e9\u01e5\3\2\2\2\u01e9\u01e6\3\2\2\2\u01e9\u01e8\3\2"+
		"\2\2\u01ea\t\3\2\2\2\u01eb\u01ec\7\u0087\2\2\u01ec\u01ef\5\f\7\2\u01ed"+
		"\u01ee\7\u008a\2\2\u01ee\u01f0\5\f\7\2\u01ef\u01ed\3\2\2\2\u01f0\u01f1"+
		"\3\2\2\2\u01f1\u01ef\3\2\2\2\u01f1\u01f2\3\2\2\2\u01f2\u01f3\3\2\2\2\u01f3"+
		"\u01f4\7\u0088\2\2\u01f4\13\3\2\2\2\u01f5\u01f7\5\6\4\2\u01f6\u01f8\5"+
		"\u01b4\u00db\2\u01f7\u01f6\3\2\2\2\u01f7\u01f8\3\2\2\2\u01f8\r\3\2\2\2"+
		"\u01f9\u01fc\5\20\t\2\u01fa\u01fc\7\24\2\2\u01fb\u01f9\3\2\2\2\u01fb\u01fa"+
		"\3\2\2\2\u01fc\17\3\2\2\2\u01fd\u0201\5\22\n\2\u01fe\u0201\5\24\13\2\u01ff"+
		"\u0201\7\37\2\2\u0200\u01fd\3\2\2\2\u0200\u01fe\3\2\2\2\u0200\u01ff\3"+
		"\2\2\2\u0201\21\3\2\2\2\u0202\u0203\t\2\2\2\u0203\23\3\2\2\2\u0204\u0205"+
		"\t\3\2\2\u0205\25\3\2\2\2\u0206\u020b\5\4\3\2\u0207\u020b\7I\2\2\u0208"+
		"\u020b\7%\2\2\u0209\u020b\7`\2\2\u020a\u0206\3\2\2\2\u020a\u0207\3\2\2"+
		"\2\u020a\u0208\3\2\2\2\u020a\u0209\3\2\2\2\u020b\27\3\2\2\2\u020c\u020d"+
		"\7\u0098\2\2\u020d\u0212\5\6\4\2\u020e\u020f\7\u008a\2\2\u020f\u0211\5"+
		"\6\4\2\u0210\u020e\3\2\2\2\u0211\u0214\3\2\2\2\u0212\u0210\3\2\2\2\u0212"+
		"\u0213\3\2\2\2\u0213\u0215\3\2\2\2\u0214\u0212\3\2\2\2\u0215\u0216\7\u0099"+
		"\2\2\u0216\31\3\2\2\2\u0217\u021c\5\34\17\2\u0218\u0219\7\u008a\2\2\u0219"+
		"\u021b\5\34\17\2\u021a\u0218\3\2\2\2\u021b\u021e\3\2\2\2\u021c\u021a\3"+
		"\2\2\2\u021c\u021d\3\2\2\2\u021d\33\3\2\2\2\u021e\u021c\3\2\2\2\u021f"+
		"\u0220\5\u01b4\u00db\2\u0220\u0221\7\u008b\2\2\u0221\u0223\3\2\2\2\u0222"+
		"\u021f\3\2\2\2\u0222\u0223\3\2\2\2\u0223\u0225\3\2\2\2\u0224\u0226\t\4"+
		"\2\2\u0225\u0224\3\2\2\2\u0225\u0226\3\2\2\2\u0226\u0229\3\2\2\2\u0227"+
		"\u022a\7p\2\2\u0228\u022a\5\6\4\2\u0229\u0227\3\2\2\2\u0229\u0228\3\2"+
		"\2\2\u0229\u022a\3\2\2\2\u022a\u022b\3\2\2\2\u022b\u022c\5\36\20\2\u022c"+
		"\35\3\2\2\2\u022d\u0232\5\"\22\2\u022e\u0232\5 \21\2\u022f\u0230\7U\2"+
		"\2\u0230\u0232\5 \21\2\u0231\u022d\3\2\2\2\u0231\u022e\3\2\2\2\u0231\u022f"+
		"\3\2\2\2\u0232\37\3\2\2\2\u0233\u0237\5x=\2\u0234\u0237\5\u0084C\2\u0235"+
		"\u0237\5&\24\2\u0236\u0233\3\2\2\2\u0236\u0234\3\2\2\2\u0236\u0235\3\2"+
		"\2\2\u0237!\3\2\2\2\u0238\u0239\5F$\2\u0239\u023a\5$\23\2\u023a\u023b"+
		"\5\36\20\2\u023b\u0241\3\2\2\2\u023c\u023d\5F$\2\u023d\u023e\7\u00b0\2"+
		"\2\u023e\u023f\5L\'\2\u023f\u0241\3\2\2\2\u0240\u0238\3\2\2\2\u0240\u023c"+
		"\3\2\2\2\u0241#\3\2\2\2\u0242\u024e\7\u0097\2\2\u0243\u024e\7\u00a6\2"+
		"\2\u0244\u024e\7\u00a7\2\2\u0245\u024e\7\u00a8\2\2\u0246\u024e\7\u00a9"+
		"\2\2\u0247\u024e\7\u00aa\2\2\u0248\u024e\7\u00ab\2\2\u0249\u024e\7\u00ac"+
		"\2\2\u024a\u024e\7\u00ad\2\2\u024b\u024e\7\u00af\2\2\u024c\u024e\5\u017c"+
		"\u00bf\2\u024d\u0242\3\2\2\2\u024d\u0243\3\2\2\2\u024d\u0244\3\2\2\2\u024d"+
		"\u0245\3\2\2\2\u024d\u0246\3\2\2\2\u024d\u0247\3\2\2\2\u024d\u0248\3\2"+
		"\2\2\u024d\u0249\3\2\2\2\u024d\u024a\3\2\2\2\u024d\u024b\3\2\2\2\u024d"+
		"\u024c\3\2\2\2\u024e%\3\2\2\2\u024f\u0255\5(\25\2\u0250\u0251\7\u009a"+
		"\2\2\u0251\u0252\5L\'\2\u0252\u0253\7\u008b\2\2\u0253\u0254\5L\'\2\u0254"+
		"\u0256\3\2\2\2\u0255\u0250\3\2\2\2\u0255\u0256\3\2\2\2\u0256\'\3\2\2\2"+
		"\u0257\u025d\5*\26\2\u0258\u025b\7\u009c\2\2\u0259\u025c\5(\25\2\u025a"+
		"\u025c\5N(\2\u025b\u0259\3\2\2\2\u025b\u025a\3\2\2\2\u025c\u025e\3\2\2"+
		"\2\u025d\u0258\3\2\2\2\u025d\u025e\3\2\2\2\u025e)\3\2\2\2\u025f\u0264"+
		"\5,\27\2\u0260\u0261\7\u00a0\2\2\u0261\u0263\5,\27\2\u0262\u0260\3\2\2"+
		"\2\u0263\u0266\3\2\2\2\u0264\u0262\3\2\2\2\u0264\u0265\3\2\2\2\u0265+"+
		"\3\2\2\2\u0266\u0264\3\2\2\2\u0267\u026c\5.\30\2\u0268\u0269\7\u009f\2"+
		"\2\u0269\u026b\5.\30\2\u026a\u0268\3\2\2\2\u026b\u026e\3\2\2\2\u026c\u026a"+
		"\3\2\2\2\u026c\u026d\3\2\2\2\u026d-\3\2\2\2\u026e\u026c\3\2\2\2\u026f"+
		"\u0274\5\60\31\2\u0270\u0271\7\u0093\2\2\u0271\u0273\5\60\31\2\u0272\u0270"+
		"\3\2\2\2\u0273\u0276\3\2\2\2\u0274\u0272\3\2\2\2\u0274\u0275\3\2\2\2\u0275"+
		"/\3\2\2\2\u0276\u0274\3\2\2\2\u0277\u027c\5\62\32\2\u0278\u0279\7\u0094"+
		"\2\2\u0279\u027b\5\62\32\2\u027a\u0278\3\2\2\2\u027b\u027e\3\2\2\2\u027c"+
		"\u027a\3\2\2\2\u027c\u027d\3\2\2\2\u027d\61\3\2\2\2\u027e\u027c\3\2\2"+
		"\2\u027f\u0284\5\64\33\2\u0280\u0281\7\u0092\2\2\u0281\u0283\5\64\33\2"+
		"\u0282\u0280\3\2\2\2\u0283\u0286\3\2\2\2\u0284\u0282\3\2\2\2\u0284\u0285"+
		"\3\2\2\2\u0285\63\3\2\2\2\u0286\u0284\3\2\2\2\u0287\u028c\5\66\34\2\u0288"+
		"\u0289\t\5\2\2\u0289\u028b\5\66\34\2\u028a\u0288\3\2\2\2\u028b\u028e\3"+
		"\2\2\2\u028c\u028a\3\2\2\2\u028c\u028d\3\2\2\2\u028d\65\3\2\2\2\u028e"+
		"\u028c\3\2\2\2\u028f\u0298\58\35\2\u0290\u0291\t\6\2\2\u0291\u0297\58"+
		"\35\2\u0292\u0293\7>\2\2\u0293\u0297\5r:\2\u0294\u0295\7\17\2\2\u0295"+
		"\u0297\5\6\4\2\u0296\u0290\3\2\2\2\u0296\u0292\3\2\2\2\u0296\u0294\3\2"+
		"\2\2\u0297\u029a\3\2\2\2\u0298\u0296\3\2\2\2\u0298\u0299\3\2\2\2\u0299"+
		"\67\3\2\2\2\u029a\u0298\3\2\2\2\u029b\u02a3\5:\36\2\u029c\u029f\7\u00ae"+
		"\2\2\u029d\u029f\5\u017a\u00be\2\u029e\u029c\3\2\2\2\u029e\u029d\3\2\2"+
		"\2\u029f\u02a0\3\2\2\2\u02a0\u02a2\5:\36\2\u02a1\u029e\3\2\2\2\u02a2\u02a5"+
		"\3\2\2\2\u02a3\u02a1\3\2\2\2\u02a3\u02a4\3\2\2\2\u02a49\3\2\2\2\u02a5"+
		"\u02a3\3\2\2\2\u02a6\u02ab\5<\37\2\u02a7\u02a8\t\7\2\2\u02a8\u02aa\5<"+
		"\37\2\u02a9\u02a7\3\2\2\2\u02aa\u02ad\3\2\2\2\u02ab\u02a9\3\2\2\2\u02ab"+
		"\u02ac\3\2\2\2\u02ac;\3\2\2\2\u02ad\u02ab\3\2\2\2\u02ae\u02b3\5> \2\u02af"+
		"\u02b0\t\b\2\2\u02b0\u02b2\5> \2\u02b1\u02af\3\2\2\2\u02b2\u02b5\3\2\2"+
		"\2\u02b3\u02b1\3\2\2\2\u02b3\u02b4\3\2\2\2\u02b4=\3\2\2\2\u02b5\u02b3"+
		"\3\2\2\2\u02b6\u02c0\5D#\2\u02b7\u02b8\7b\2\2\u02b8\u02bd\7\u0083\2\2"+
		"\u02b9\u02bb\5@!\2\u02ba\u02bc\7\u008a\2\2\u02bb\u02ba\3\2\2\2\u02bb\u02bc"+
		"\3\2\2\2\u02bc\u02be\3\2\2\2\u02bd\u02b9\3\2\2\2\u02bd\u02be\3\2\2\2\u02be"+
		"\u02bf\3\2\2\2\u02bf\u02c1\7\u0084\2\2\u02c0\u02b7\3\2\2\2\u02c0\u02c1"+
		"\3\2\2\2\u02c1?\3\2\2\2\u02c2\u02c7\5B\"\2\u02c3\u02c4\7\u008a\2\2\u02c4"+
		"\u02c6\5B\"\2\u02c5\u02c3\3\2\2\2\u02c6\u02c9\3\2\2\2\u02c7\u02c5\3\2"+
		"\2\2\u02c7\u02c8\3\2\2\2\u02c8A\3\2\2\2\u02c9\u02c7\3\2\2\2\u02ca\u02cc"+
		"\5\36\20\2\u02cb\u02cd\5\u00be`\2\u02cc\u02cb\3\2\2\2\u02cc\u02cd\3\2"+
		"\2\2\u02cd\u02ce\3\2\2\2\u02ce\u02cf\5\u0178\u00bd\2\u02cf\u02d0\5L\'"+
		"\2\u02d0C\3\2\2\2\u02d1\u02da\5F$\2\u02d2\u02d4\5F$\2\u02d3\u02d2\3\2"+
		"\2\2\u02d3\u02d4\3\2\2\2\u02d4\u02d5\3\2\2\2\u02d5\u02d7\7\u00b1\2\2\u02d6"+
		"\u02d8\5F$\2\u02d7\u02d6\3\2\2\2\u02d7\u02d8\3\2\2\2\u02d8\u02da\3\2\2"+
		"\2\u02d9\u02d1\3\2\2\2\u02d9\u02d3\3\2\2\2\u02daE\3\2\2\2\u02db\u02f6"+
		"\5H%\2\u02dc\u02dd\7\u008d\2\2\u02dd\u02f6\5F$\2\u02de\u02df\7\u008e\2"+
		"\2\u02df\u02f6\5F$\2\u02e0\u02e1\7\u0095\2\2\u02e1\u02f6\5F$\2\u02e2\u02e3"+
		"\7\u0096\2\2\u02e3\u02f6\5F$\2\u02e4\u02e5\7\u009d\2\2\u02e5\u02f6\5F"+
		"$\2\u02e6\u02e7\7\u009e\2\2\u02e7\u02f6\5F$\2\u02e8\u02e9\7\u0087\2\2"+
		"\u02e9\u02ea\5\6\4\2\u02ea\u02eb\7\u0088\2\2\u02eb\u02ec\5F$\2\u02ec\u02f6"+
		"\3\2\2\2\u02ed\u02ee\7\22\2\2\u02ee\u02f6\5F$\2\u02ef\u02f0\7\u0092\2"+
		"\2\u02f0\u02f6\5F$\2\u02f1\u02f2\7\u008f\2\2\u02f2\u02f6\5F$\2\u02f3\u02f4"+
		"\7\u0094\2\2\u02f4\u02f6\5F$\2\u02f5\u02db\3\2\2\2\u02f5\u02dc\3\2\2\2"+
		"\u02f5\u02de\3\2\2\2\u02f5\u02e0\3\2\2\2\u02f5\u02e2\3\2\2\2\u02f5\u02e4"+
		"\3\2\2\2\u02f5\u02e6\3\2\2\2\u02f5\u02e8\3\2\2\2\u02f5\u02ed\3\2\2\2\u02f5"+
		"\u02ef\3\2\2\2\u02f5\u02f1\3\2\2\2\u02f5\u02f3\3\2\2\2\u02f6G\3\2\2\2"+
		"\u02f7\u02f9\5J&\2\u02f8\u02fa\7\u0095\2\2\u02f9\u02f8\3\2\2\2\u02f9\u02fa"+
		"\3\2\2\2\u02fa\u02fe\3\2\2\2\u02fb\u02fd\5R*\2\u02fc\u02fb\3\2\2\2\u02fd"+
		"\u0300\3\2\2\2\u02fe\u02fc\3\2\2\2\u02fe\u02ff\3\2\2\2\u02ff\u0302\3\2"+
		"\2\2\u0300\u02fe\3\2\2\2\u0301\u0303\7\u0095\2\2\u0302\u0301\3\2\2\2\u0302"+
		"\u0303\3\2\2\2\u0303\u031a\3\2\2\2\u0304\u030b\5P)\2\u0305\u030b\5\u01b0"+
		"\u00d9\2\u0306\u030b\7\u009d\2\2\u0307\u030b\7\u009e\2\2\u0308\u0309\7"+
		"\u00a1\2\2\u0309\u030b\5\u01b4\u00db\2\u030a\u0304\3\2\2\2\u030a\u0305"+
		"\3\2\2\2\u030a\u0306\3\2\2\2\u030a\u0307\3\2\2\2\u030a\u0308\3\2\2\2\u030b"+
		"\u030d\3\2\2\2\u030c\u030e\7\u0095\2\2\u030d\u030c\3\2\2\2\u030d\u030e"+
		"\3\2\2\2\u030e\u0312\3\2\2\2\u030f\u0311\5R*\2\u0310\u030f\3\2\2\2\u0311"+
		"\u0314\3\2\2\2\u0312\u0310\3\2\2\2\u0312\u0313\3\2\2\2\u0313\u0316\3\2"+
		"\2\2\u0314\u0312\3\2\2\2\u0315\u0317\7\u0095\2\2\u0316\u0315\3\2\2\2\u0316"+
		"\u0317\3\2\2\2\u0317\u0319\3\2\2\2\u0318\u030a\3\2\2\2\u0319\u031c\3\2"+
		"\2\2\u031a\u0318\3\2\2\2\u031a\u031b\3\2\2\2\u031bI\3\2\2\2\u031c\u031a"+
		"\3\2\2\2\u031d\u039d\5\u017e\u00c0\2\u031e\u0320\5\u01b4\u00db\2\u031f"+
		"\u0321\5\30\r\2\u0320\u031f\3\2\2\2\u0320\u0321\3\2\2\2\u0321\u039d\3"+
		"\2\2\2\u0322\u0323\7\u0087\2\2\u0323\u0324\5\36\20\2\u0324\u0325\7\u0088"+
		"\2\2\u0325\u039d\3\2\2\2\u0326\u039d\5V,\2\u0327\u039d\5\u00e6t\2\u0328"+
		"\u039d\7y\2\2\u0329\u039d\7c\2\2\u032a\u0334\7\23\2\2\u032b\u032c\7\u0089"+
		"\2\2\u032c\u032e\5\u01b4\u00db\2\u032d\u032f\5\30\r\2\u032e\u032d\3\2"+
		"\2\2\u032e\u032f\3\2\2\2\u032f\u0335\3\2\2\2\u0330\u0331\7\u0085\2\2\u0331"+
		"\u0332\5X-\2\u0332\u0333\7\u0086\2\2\u0333\u0335\3\2\2\2\u0334\u032b\3"+
		"\2\2\2\u0334\u0330\3\2\2\2\u0335\u039d\3\2\2\2\u0336\u0353\7F\2\2\u0337"+
		"\u034d\5\6\4\2\u0338\u034e\5\u01b2\u00da\2\u0339\u034e\5Z.\2\u033a\u033b"+
		"\7\u0085\2\2\u033b\u033c\5X-\2\u033c\u0340\7\u0086\2\2\u033d\u033f\5\u0144"+
		"\u00a3\2\u033e\u033d\3\2\2\2\u033f\u0342\3\2\2\2\u0340\u033e\3\2\2\2\u0340"+
		"\u0341\3\2\2\2\u0341\u0344\3\2\2\2\u0342\u0340\3\2\2\2\u0343\u0345\5\u0146"+
		"\u00a4\2\u0344\u0343\3\2\2\2\u0344\u0345\3\2\2\2\u0345\u034e\3\2\2\2\u0346"+
		"\u0348\5\u0144\u00a3\2\u0347\u0346\3\2\2\2\u0348\u0349\3\2\2\2\u0349\u0347"+
		"\3\2\2\2\u0349\u034a\3\2\2\2\u034a\u034b\3\2\2\2\u034b\u034c\5\u0146\u00a4"+
		"\2\u034c\u034e\3\2\2\2\u034d\u0338\3\2\2\2\u034d\u0339\3\2\2\2\u034d\u033a"+
		"\3\2\2\2\u034d\u0347\3\2\2\2\u034e\u0354\3\2\2\2\u034f\u0354\5h\65\2\u0350"+
		"\u0351\5\u0144\u00a3\2\u0351\u0352\5\u0146\u00a4\2\u0352\u0354\3\2\2\2"+
		"\u0353\u0337\3\2\2\2\u0353\u034f\3\2\2\2\u0353\u0350\3\2\2\2\u0354\u039d"+
		"\3\2\2\2\u0355\u0356\7F\2\2\u0356\u0358\7\u0087\2\2\u0357\u0359\5\32\16"+
		"\2\u0358\u0357\3\2\2\2\u0358\u0359\3\2\2\2\u0359\u035a\3\2\2\2\u035a\u039d"+
		"\7\u0088\2\2\u035b\u035c\7\u0087\2\2\u035c\u035f\5\34\17\2\u035d\u035e"+
		"\7\u008a\2\2\u035e\u0360\5\34\17\2\u035f\u035d\3\2\2\2\u0360\u0361\3\2"+
		"\2\2\u0361\u035f\3\2\2\2\u0361\u0362\3\2\2\2\u0362\u0363\3\2\2\2\u0363"+
		"\u0364\7\u0088\2\2\u0364\u039d\3\2\2\2\u0365\u0366\7h\2\2\u0366\u036a"+
		"\7\u0087\2\2\u0367\u036b\5n8\2\u0368\u036b\5\6\4\2\u0369\u036b\7r\2\2"+
		"\u036a\u0367\3\2\2\2\u036a\u0368\3\2\2\2\u036a\u0369\3\2\2\2\u036b\u036c"+
		"\3\2\2\2\u036c\u039d\7\u0088\2\2\u036d\u036e\7\33\2\2\u036e\u036f\7\u0087"+
		"\2\2\u036f\u0370\5\36\20\2\u0370\u0371\7\u0088\2\2\u0371\u039d\3\2\2\2"+
		"\u0372\u0373\7k\2\2\u0373\u0374\7\u0087\2\2\u0374\u0375\5\36\20\2\u0375"+
		"\u0376\7\u0088\2\2\u0376\u039d\3\2\2\2\u0377\u037c\7 \2\2\u0378\u0379"+
		"\7\u0087\2\2\u0379\u037a\5\6\4\2\u037a\u037b\7\u0088\2\2\u037b\u037d\3"+
		"\2\2\2\u037c\u0378\3\2\2\2\u037c\u037d\3\2\2\2\u037d\u039d\3\2\2\2\u037e"+
		"\u0380\7\21\2\2\u037f\u037e\3\2\2\2\u037f\u0380\3\2\2\2\u0380\u0381\3"+
		"\2\2\2\u0381\u0387\7!\2\2\u0382\u0384\7\u0087\2\2\u0383\u0385\5|?\2\u0384"+
		"\u0383\3\2\2\2\u0384\u0385\3\2\2\2\u0385\u0386\3\2\2\2\u0386\u0388\7\u0088"+
		"\2\2\u0387\u0382\3\2\2\2\u0387\u0388\3\2\2\2\u0388\u0389\3\2\2\2\u0389"+
		"\u039d\5\u00acW\2\u038a\u038b\7]\2\2\u038b\u038c\7\u0087\2\2\u038c\u038d"+
		"\5\6\4\2\u038d\u038e\7\u0088\2\2\u038e\u039d\3\2\2\2\u038f\u0390\7C\2"+
		"\2\u0390\u0396\7\u0087\2\2\u0391\u0392\5\u01b4\u00db\2\u0392\u0393\7\u0089"+
		"\2\2\u0393\u0395\3\2\2\2\u0394\u0391\3\2\2\2\u0395\u0398\3\2\2\2\u0396"+
		"\u0394\3\2\2\2\u0396\u0397\3\2\2\2\u0397\u0399\3\2\2\2\u0398\u0396\3\2"+
		"\2\2\u0399\u039a\5\u01b4\u00db\2\u039a\u039b\7\u0088\2\2\u039b\u039d\3"+
		"\2\2\2\u039c\u031d\3\2\2\2\u039c\u031e\3\2\2\2\u039c\u0322\3\2\2\2\u039c"+
		"\u0326\3\2\2\2\u039c\u0327\3\2\2\2\u039c\u0328\3\2\2\2\u039c\u0329\3\2"+
		"\2\2\u039c\u032a\3\2\2\2\u039c\u0336\3\2\2\2\u039c\u0355\3\2\2\2\u039c"+
		"\u035b\3\2\2\2\u039c\u0365\3\2\2\2\u039c\u036d\3\2\2\2\u039c\u0372\3\2"+
		"\2\2\u039c\u0377\3\2\2\2\u039c\u037f\3\2\2\2\u039c\u038a\3\2\2\2\u039c"+
		"\u038f\3\2\2\2\u039dK\3\2\2\2\u039e\u03a1\5\36\20\2\u039f\u03a1\5N(\2"+
		"\u03a0\u039e\3\2\2\2\u03a0\u039f\3\2\2\2\u03a1M\3\2\2\2\u03a2\u03a3\7"+
		"d\2\2\u03a3\u03a4\5\36\20\2\u03a4O\3\2\2\2\u03a5\u03a7\7\u009a\2\2\u03a6"+
		"\u03a5\3\2\2\2\u03a6\u03a7\3\2\2\2\u03a7\u03a8\3\2\2\2\u03a8\u03a9\7\u0089"+
		"\2\2\u03a9\u03ab\5\u01b4\u00db\2\u03aa\u03ac\5\30\r\2\u03ab\u03aa\3\2"+
		"\2\2\u03ab\u03ac\3\2\2\2\u03acQ\3\2\2\2\u03ad\u03af\7\u009a\2\2\u03ae"+
		"\u03ad\3\2\2\2\u03ae\u03af\3\2\2\2\u03af\u03b0\3\2\2\2\u03b0\u03b1\7\u0085"+
		"\2\2\u03b1\u03b6\5T+\2\u03b2\u03b3\7\u008a\2\2\u03b3\u03b5\5T+\2\u03b4"+
		"\u03b2\3\2\2\2\u03b5\u03b8\3\2\2\2\u03b6\u03b4\3\2\2\2\u03b6\u03b7\3\2"+
		"\2\2\u03b7\u03b9\3\2\2\2\u03b8\u03b6\3\2\2\2\u03b9\u03ba\7\u0086\2\2\u03ba"+
		"S\3\2\2\2\u03bb\u03bc\5\u01b4\u00db\2\u03bc\u03bd\7\u008b\2\2\u03bd\u03bf"+
		"\3\2\2\2\u03be\u03bb\3\2\2\2\u03be\u03bf\3\2\2\2\u03bf\u03c0\3\2\2\2\u03c0"+
		"\u03c1\5\36\20\2\u03c1U\3\2\2\2\u03c2\u03c3\t\t\2\2\u03c3W\3\2\2\2\u03c4"+
		"\u03c9\5\36\20\2\u03c5\u03c6\7\u008a\2\2\u03c6\u03c8\5\36\20\2\u03c7\u03c5"+
		"\3\2\2\2\u03c8\u03cb\3\2\2\2\u03c9\u03c7\3\2\2\2\u03c9\u03ca\3\2\2\2\u03ca"+
		"Y\3\2\2\2\u03cb\u03c9\3\2\2\2\u03cc\u03cf\5\\/\2\u03cd\u03cf\5d\63\2\u03ce"+
		"\u03cc\3\2\2\2\u03ce\u03cd\3\2\2\2\u03cf[\3\2\2\2\u03d0\u03d5\7\u0083"+
		"\2\2\u03d1\u03d3\5^\60\2\u03d2\u03d4\7\u008a\2\2\u03d3\u03d2\3\2\2\2\u03d3"+
		"\u03d4\3\2\2\2\u03d4\u03d6\3\2\2\2\u03d5\u03d1\3\2\2\2\u03d5\u03d6\3\2"+
		"\2\2\u03d6\u03d7\3\2\2\2\u03d7\u03d8\7\u0084\2\2\u03d8]\3\2\2\2\u03d9"+
		"\u03de\5`\61\2\u03da\u03db\7\u008a\2\2\u03db\u03dd\5`\61\2\u03dc\u03da"+
		"\3\2\2\2\u03dd\u03e0\3\2\2\2\u03de\u03dc\3\2\2\2\u03de\u03df\3\2\2\2\u03df"+
		"_\3\2\2\2\u03e0\u03de\3\2\2\2\u03e1\u03e7\5\u01b4\u00db\2\u03e2\u03e3"+
		"\7\u0085\2\2\u03e3\u03e4\5\36\20\2\u03e4\u03e5\7\u0086\2\2\u03e5\u03e7"+
		"\3\2\2\2\u03e6\u03e1\3\2\2\2\u03e6\u03e2\3\2\2\2\u03e7\u03e8\3\2\2\2\u03e8"+
		"\u03e9\7\u0097\2\2\u03e9\u03ea\5b\62\2\u03eaa\3\2\2\2\u03eb\u03ee\5\36"+
		"\20\2\u03ec\u03ee\5Z.\2\u03ed\u03eb\3\2\2\2\u03ed\u03ec\3\2\2\2\u03ee"+
		"c\3\2\2\2\u03ef\u03f0\7\u0083\2\2\u03f0\u03f5\5f\64\2\u03f1\u03f2\7\u008a"+
		"\2\2\u03f2\u03f4\5f\64\2\u03f3\u03f1\3\2\2\2\u03f4\u03f7\3\2\2\2\u03f5"+
		"\u03f3\3\2\2\2\u03f5\u03f6\3\2\2\2\u03f6\u03f9\3\2\2\2\u03f7\u03f5\3\2"+
		"\2\2\u03f8\u03fa\7\u008a\2\2\u03f9\u03f8\3\2\2\2\u03f9\u03fa\3\2\2\2\u03fa"+
		"\u03fb\3\2\2\2\u03fb\u03fc\7\u0084\2\2\u03fce\3\2\2\2\u03fd\u0403\5 \21"+
		"\2\u03fe\u03ff\7\u0083\2\2\u03ff\u0400\5X-\2\u0400\u0401\7\u0084\2\2\u0401"+
		"\u0403\3\2\2\2\u0402\u03fd\3\2\2\2\u0402\u03fe\3\2\2\2\u0403g\3\2\2\2"+
		"\u0404\u0409\7\u0083\2\2\u0405\u0407\5j\66\2\u0406\u0408\7\u008a\2\2\u0407"+
		"\u0406\3\2\2\2\u0407\u0408\3\2\2\2\u0408\u040a\3\2\2\2\u0409\u0405\3\2"+
		"\2\2\u0409\u040a\3\2\2\2\u040a\u040b\3\2\2\2\u040b\u040c\7\u0084\2\2\u040c"+
		"i\3\2\2\2\u040d\u0412\5l\67\2\u040e\u040f\7\u008a\2\2\u040f\u0411\5l\67"+
		"\2\u0410\u040e\3\2\2\2\u0411\u0414\3\2\2\2\u0412\u0410\3\2\2\2\u0412\u0413"+
		"\3\2\2\2\u0413k\3\2\2\2\u0414\u0412\3\2\2\2\u0415\u041b\5H%\2\u0416\u0417"+
		"\5\u01b4\u00db\2\u0417\u0418\7\u0097\2\2\u0418\u0419\5\36\20\2\u0419\u041b"+
		"\3\2\2\2\u041a\u0415\3\2\2\2\u041a\u0416\3\2\2\2\u041bm\3\2\2\2\u041c"+
		"\u0425\5\u01b4\u00db\2\u041d\u041f\5p9\2\u041e\u041d\3\2\2\2\u041e\u041f"+
		"\3\2\2\2\u041f\u0426\3\2\2\2\u0420\u0421\7\u009b\2\2\u0421\u0423\5\u01b4"+
		"\u00db\2\u0422\u0424\5p9\2\u0423\u0422\3\2\2\2\u0423\u0424\3\2\2\2\u0424"+
		"\u0426\3\2\2\2\u0425\u041e\3\2\2\2\u0425\u0420\3\2\2\2\u0426\u042e\3\2"+
		"\2\2\u0427\u0428\7\u0089\2\2\u0428\u042a\5\u01b4\u00db\2\u0429\u042b\5"+
		"p9\2\u042a\u0429\3\2\2\2\u042a\u042b\3\2\2\2\u042b\u042d\3\2\2\2\u042c"+
		"\u0427\3\2\2\2\u042d\u0430\3\2\2\2\u042e\u042c\3\2\2\2\u042e\u042f\3\2"+
		"\2\2\u042fo\3\2\2\2\u0430\u042e\3\2\2\2\u0431\u0435\7\u0098\2\2\u0432"+
		"\u0434\7\u008a\2\2\u0433\u0432\3\2\2\2\u0434\u0437\3\2\2\2\u0435\u0433"+
		"\3\2\2\2\u0435\u0436\3\2\2\2\u0436\u0438\3\2\2\2\u0437\u0435\3\2\2\2\u0438"+
		"\u0439\7\u0099\2\2\u0439q\3\2\2\2\u043a\u043f\5\b\5\2\u043b\u043e\5\u0144"+
		"\u00a3\2\u043c\u043e\7\u008f\2\2\u043d\u043b\3\2\2\2\u043d\u043c\3\2\2"+
		"\2\u043e\u0441\3\2\2\2\u043f\u043d\3\2\2\2\u043f\u0440\3\2\2\2\u0440\u0443"+
		"\3\2\2\2\u0441\u043f\3\2\2\2\u0442\u0444\7\u009a\2\2\u0443\u0442\3\2\2"+
		"\2\u0443\u0444\3\2\2\2\u0444\u0446\3\2\2\2\u0445\u0447\5t;\2\u0446\u0445"+
		"\3\2\2\2\u0446\u0447\3\2\2\2\u0447\u0449\3\2\2\2\u0448\u044a\5\u01b4\u00db"+
		"\2\u0449\u0448\3\2\2\2\u0449\u044a\3\2\2\2\u044a\u044d\3\2\2\2\u044b\u044d"+
		"\7H\2\2\u044c\u043a\3\2\2\2\u044c\u044b\3\2\2\2\u044ds\3\2\2\2\u044e\u044f"+
		"\7\u0083\2\2\u044f\u0454\5v<\2\u0450\u0451\7\u008a\2\2\u0451\u0453\5v"+
		"<\2\u0452\u0450\3\2\2\2\u0453\u0456\3\2\2\2\u0454\u0452\3\2\2\2\u0454"+
		"\u0455\3\2\2\2\u0455\u0457\3\2\2\2\u0456\u0454\3\2\2\2\u0457\u0458\7\u0084"+
		"\2\2\u0458u\3\2\2\2\u0459\u045a\5\u01b4\u00db\2\u045a\u045b\7\u008b\2"+
		"\2\u045b\u045c\5\36\20\2\u045cw\3\2\2\2\u045d\u045f\7\21\2\2\u045e\u045d"+
		"\3\2\2\2\u045e\u045f\3\2\2\2\u045f\u0460\3\2\2\2\u0460\u0461\5z>\2\u0461"+
		"\u0462\5\u0178\u00bd\2\u0462\u0463\5\u0082B\2\u0463y\3\2\2\2\u0464\u0465"+
		"\7\u0087\2\2\u0465\u0470\7\u0088\2\2\u0466\u0467\7\u0087\2\2\u0467\u0468"+
		"\5|?\2\u0468\u0469\7\u0088\2\2\u0469\u0470\3\2\2\2\u046a\u046b\7\u0087"+
		"\2\2\u046b\u046c\5\u0080A\2\u046c\u046d\7\u0088\2\2\u046d\u0470\3\2\2"+
		"\2\u046e\u0470\5\u01b4\u00db\2\u046f\u0464\3\2\2\2\u046f\u0466\3\2\2\2"+
		"\u046f\u046a\3\2\2\2\u046f\u046e\3\2\2\2\u0470{\3\2\2\2\u0471\u0476\5"+
		"~@\2\u0472\u0473\7\u008a\2\2\u0473\u0475\5~@\2\u0474\u0472\3\2\2\2\u0475"+
		"\u0478\3\2\2\2\u0476\u0474\3\2\2\2\u0476\u0477\3\2\2\2\u0477}\3\2\2\2"+
		"\u0478\u0476\3\2\2\2\u0479\u047b\t\4\2\2\u047a\u0479\3\2\2\2\u047a\u047b"+
		"\3\2\2\2\u047b\u047c\3\2\2\2\u047c\u047d\5\6\4\2\u047d\u047e\5\u01b4\u00db"+
		"\2\u047e\177\3\2\2\2\u047f\u0484\5\u01b4\u00db\2\u0480\u0481\7\u008a\2"+
		"\2\u0481\u0483\5\u01b4\u00db\2\u0482\u0480\3\2\2\2\u0483\u0486\3\2\2\2"+
		"\u0484\u0482\3\2\2\2\u0484\u0485\3\2\2\2\u0485\u0081\3\2\2\2\u0486\u0484"+
		"\3\2\2\2\u0487\u048a\5L\'\2\u0488\u048a\5\u00acW\2\u0489\u0487\3\2\2\2"+
		"\u0489\u0488\3\2\2\2\u048a\u0083\3\2\2\2\u048b\u048c\5\u0086D\2\u048c"+
		"\u048d\5\u0088E\2\u048d\u0085\3\2\2\2\u048e\u0490\7\63\2\2\u048f\u0491"+
		"\5\6\4\2\u0490\u048f\3\2\2\2\u0490\u0491\3\2\2\2\u0491\u0492\3\2\2\2\u0492"+
		"\u0493\5\u01b4\u00db\2\u0493\u0494\79\2\2\u0494\u0495\5\36\20\2\u0495"+
		"\u0087\3\2\2\2\u0496\u0498\5\u008aF\2\u0497\u0496\3\2\2\2\u0498\u049b"+
		"\3\2\2\2\u0499\u0497\3\2\2\2\u0499\u049a\3\2\2\2\u049a\u049c\3\2\2\2\u049b"+
		"\u0499\3\2\2\2\u049c\u049e\5\u0096L\2\u049d\u049f\5\u0098M\2\u049e\u049d"+
		"\3\2\2\2\u049e\u049f\3\2\2\2\u049f\u0089\3\2\2\2\u04a0\u04a6\5\u0086D"+
		"\2\u04a1\u04a6\5\u008cG\2\u04a2\u04a6\5\u008eH\2\u04a3\u04a6\5\u0090I"+
		"\2\u04a4\u04a6\5\u0092J\2\u04a5\u04a0\3\2\2\2\u04a5\u04a1\3\2\2\2\u04a5"+
		"\u04a2\3\2\2\2\u04a5\u04a3\3\2\2\2\u04a5\u04a4\3\2\2\2\u04a6\u008b\3\2"+
		"\2\2\u04a7\u04a8\7@\2\2\u04a8\u04a9\5\u01b4\u00db\2\u04a9\u04aa\7\u0097"+
		"\2\2\u04aa\u04ab\5\36\20\2\u04ab\u008d\3\2\2\2\u04ac\u04ad\7u\2\2\u04ad"+
		"\u04b1\5\36\20\2\u04ae\u04af\7u\2\2\u04af\u04b1\7G\2\2\u04b0\u04ac\3\2"+
		"\2\2\u04b0\u04ae\3\2\2\2\u04b1\u008f\3\2\2\2\u04b2\u04b4\7?\2\2\u04b3"+
		"\u04b5\5\6\4\2\u04b4\u04b3\3\2\2\2\u04b4\u04b5\3\2\2\2\u04b5\u04b6\3\2"+
		"\2\2\u04b6\u04b7\5\u01b4\u00db\2\u04b7\u04b8\79\2\2\u04b8\u04b9\5\36\20"+
		"\2\u04b9\u04ba\7J\2\2\u04ba\u04bb\5\36\20\2\u04bb\u04bc\7(\2\2\u04bc\u04bf"+
		"\5\36\20\2\u04bd\u04be\7=\2\2\u04be\u04c0\5\u01b4\u00db\2\u04bf\u04bd"+
		"\3\2\2\2\u04bf\u04c0\3\2\2\2\u04c0\u0091\3\2\2\2\u04c1\u04c2\7L\2\2\u04c2"+
		"\u04c7\5\u0094K\2\u04c3\u04c4\7\u008a\2\2\u04c4\u04c6\5\u0094K\2\u04c5"+
		"\u04c3\3\2\2\2\u04c6\u04c9\3\2\2\2\u04c7\u04c5\3\2\2\2\u04c7\u04c8\3\2"+
		"\2\2\u04c8\u0093\3\2\2\2\u04c9\u04c7\3\2\2\2\u04ca\u04cc\5\36\20\2\u04cb"+
		"\u04cd\t\n\2\2\u04cc\u04cb\3\2\2\2\u04cc\u04cd\3\2\2\2\u04cd\u0095\3\2"+
		"\2\2\u04ce\u04cf\7Z\2\2\u04cf\u04d6\5\36\20\2\u04d0\u04d1\7\66\2\2\u04d1"+
		"\u04d2\5\36\20\2\u04d2\u04d3\7\26\2\2\u04d3\u04d4\5\36\20\2\u04d4\u04d6"+
		"\3\2\2\2\u04d5\u04ce\3\2\2\2\u04d5\u04d0\3\2\2\2\u04d6\u0097\3\2\2\2\u04d7"+
		"\u04d8\7=\2\2\u04d8\u04d9\5\u01b4\u00db\2\u04d9\u04da\5\u0088E\2\u04da"+
		"\u0099\3\2\2\2\u04db\u04df\5\u00a6T\2\u04dc\u04df\5\u009cO\2\u04dd\u04df"+
		"\5\u00a8U\2\u04de\u04db\3\2\2\2\u04de\u04dc\3\2\2\2\u04de\u04dd\3\2\2"+
		"\2\u04df\u009b\3\2\2\2\u04e0\u04e1\5\u00aeX\2\u04e1\u04e2\7\u008c\2\2"+
		"\u04e2\u04e8\3\2\2\2\u04e3\u04e4\5\u00b6\\\2\u04e4\u04e5\7\u008c\2\2\u04e5"+
		"\u04e8\3\2\2\2\u04e6\u04e8\5\u009eP\2\u04e7\u04e0\3\2\2\2\u04e7\u04e3"+
		"\3\2\2\2\u04e7\u04e6\3\2\2\2\u04e8\u009d\3\2\2\2\u04e9\u04ea\5\u00a0Q"+
		"\2\u04ea\u04eb\5\u00a4S\2\u04eb\u009f\3\2\2\2\u04ec\u04ee\5\u00a2R\2\u04ed"+
		"\u04ec\3\2\2\2\u04ed\u04ee\3\2\2\2\u04ee\u04ef\3\2\2\2\u04ef\u04f0\5\u0114"+
		"\u008b\2\u04f0\u04f2\5\u01b4\u00db\2\u04f1\u04f3\5\u00e8u\2\u04f2\u04f1"+
		"\3\2\2\2\u04f2\u04f3\3\2\2\2\u04f3\u04f4\3\2\2\2\u04f4\u04f6\7\u0087\2"+
		"\2\u04f5\u04f7\5\u011a\u008e\2\u04f6\u04f5\3\2\2\2\u04f6\u04f7\3\2\2\2"+
		"\u04f7\u04f8\3\2\2\2\u04f8\u04fa\7\u0088\2\2\u04f9\u04fb\5\u00f0y\2\u04fa"+
		"\u04f9\3\2\2\2\u04fa\u04fb\3\2\2\2\u04fb\u00a1\3\2\2\2\u04fc\u04fe\t\13"+
		"\2\2\u04fd\u04ff\7_\2\2\u04fe\u04fd\3\2\2\2\u04fe\u04ff\3\2\2\2\u04ff"+
		"\u0505\3\2\2\2\u0500\u0502\7_\2\2\u0501\u0503\t\13\2\2\u0502\u0501\3\2"+
		"\2\2\u0502\u0503\3\2\2\2\u0503\u0505\3\2\2\2\u0504\u04fc\3\2\2\2\u0504"+
		"\u0500\3\2\2\2\u0505\u00a3\3\2\2\2\u0506\u050c\5\u00acW\2\u0507\u0508"+
		"\5\u0178\u00bd\2\u0508\u0509\5L\'\2\u0509\u050a\7\u008c\2\2\u050a\u050c"+
		"\3\2\2\2\u050b\u0506\3\2\2\2\u050b\u0507\3\2\2\2\u050c\u00a5\3\2\2\2\u050d"+
		"\u050e\5\u01b4\u00db\2\u050e\u050f\7\u008b\2\2\u050f\u0510\5\u009aN\2"+
		"\u0510\u00a7\3\2\2\2\u0511\u0514\5\u00acW\2\u0512\u0514\5\u00aaV\2\u0513"+
		"\u0511\3\2\2\2\u0513\u0512\3\2\2\2\u0514\u00a9\3\2\2\2\u0515\u0598\7\u008c"+
		"\2\2\u0516\u0517\5\36\20\2\u0517\u0518\7\u008c\2\2\u0518\u0598\3\2\2\2"+
		"\u0519\u051a\7\67\2\2\u051a\u051b\7\u0087\2\2\u051b\u051c\5\36\20\2\u051c"+
		"\u051d\7\u0088\2\2\u051d\u0520\5\u00b8]\2\u051e\u051f\7&\2\2\u051f\u0521"+
		"\5\u00b8]\2\u0520\u051e\3\2\2\2\u0520\u0521\3\2\2\2\u0521\u0598\3\2\2"+
		"\2\u0522\u0523\7b\2\2\u0523\u0524\7\u0087\2\2\u0524\u0525\5\36\20\2\u0525"+
		"\u0526\7\u0088\2\2\u0526\u052a\7\u0083\2\2\u0527\u0529\5\u00ba^\2\u0528"+
		"\u0527\3\2\2\2\u0529\u052c\3\2\2\2\u052a\u0528\3\2\2\2\u052a\u052b\3\2"+
		"\2\2\u052b\u052d\3\2\2\2\u052c\u052a\3\2\2\2\u052d\u052e\7\u0084\2\2\u052e"+
		"\u0598\3\2\2\2\u052f\u0530\7v\2\2\u0530\u0531\7\u0087\2\2\u0531\u0532"+
		"\5\36\20\2\u0532\u0533\7\u0088\2\2\u0533\u0534\5\u00a8U\2\u0534\u0598"+
		"\3\2\2\2\u0535\u0536\7#\2\2\u0536\u0537\5\u00a8U\2\u0537\u0538\7v\2\2"+
		"\u0538\u0539\7\u0087\2\2\u0539\u053a\5\36\20\2\u053a\u053b\7\u0088\2\2"+
		"\u053b\u053c\7\u008c\2\2\u053c\u0598\3\2\2\2\u053d\u053e\7\61\2\2\u053e"+
		"\u0540\7\u0087\2\2\u053f\u0541\5\u00c2b\2\u0540\u053f\3\2\2\2\u0540\u0541"+
		"\3\2\2\2\u0541\u0542\3\2\2\2\u0542\u0544\7\u008c\2\2\u0543\u0545\5\36"+
		"\20\2\u0544\u0543\3\2\2\2\u0544\u0545\3\2\2\2\u0545\u0546\3\2\2\2\u0546"+
		"\u0548\7\u008c\2\2\u0547\u0549\5\u00c4c\2\u0548\u0547\3\2\2\2\u0548\u0549"+
		"\3\2\2\2\u0549\u054a\3\2\2\2\u054a\u054b\7\u0088\2\2\u054b\u0598\5\u00a8"+
		"U\2\u054c\u054e\7\22\2\2\u054d\u054c\3\2\2\2\u054d\u054e\3\2\2\2\u054e"+
		"\u054f\3\2\2\2\u054f\u0550\7\62\2\2\u0550\u0551\7\u0087\2\2\u0551\u0552"+
		"\5\u00b0Y\2\u0552\u0553\5\u01b4\u00db\2\u0553\u0554\79\2\2\u0554\u0555"+
		"\5\36\20\2\u0555\u0556\7\u0088\2\2\u0556\u0557\5\u00a8U\2\u0557\u0598"+
		"\3\2\2\2\u0558\u0559\7\25\2\2\u0559\u0598\7\u008c\2\2\u055a\u055b\7\36"+
		"\2\2\u055b\u0598\7\u008c\2\2\u055c\u0561\7\65\2\2\u055d\u0562\5\u01b4"+
		"\u00db\2\u055e\u055f\7\30\2\2\u055f\u0562\5\36\20\2\u0560\u0562\7 \2\2"+
		"\u0561\u055d\3\2\2\2\u0561\u055e\3\2\2\2\u0561\u0560\3\2\2\2\u0562\u0563"+
		"\3\2\2\2\u0563\u0598\7\u008c\2\2\u0564\u0566\7W\2\2\u0565\u0567\5\36\20"+
		"\2\u0566\u0565\3\2\2\2\u0566\u0567\3\2\2\2\u0567\u0568\3\2\2\2\u0568\u0598"+
		"\7\u008c\2\2\u0569\u056b\7d\2\2\u056a\u056c\5\36\20\2\u056b\u056a\3\2"+
		"\2\2\u056b\u056c\3\2\2\2\u056c\u056d\3\2\2\2\u056d\u0598\7\u008c\2\2\u056e"+
		"\u056f\7g\2\2\u056f\u0575\5\u00acW\2\u0570\u0572\5\u00c6d\2\u0571\u0573"+
		"\5\u00ceh\2\u0572\u0571\3\2\2\2\u0572\u0573\3\2\2\2\u0573\u0576\3\2\2"+
		"\2\u0574\u0576\5\u00ceh\2\u0575\u0570\3\2\2\2\u0575\u0574\3\2\2\2\u0576"+
		"\u0598\3\2\2\2\u0577\u0578\7\33\2\2\u0578\u0598\5\u00acW\2\u0579\u057a"+
		"\7k\2\2\u057a\u0598\5\u00acW\2\u057b\u057c\7A\2\2\u057c\u057d\7\u0087"+
		"\2\2\u057d\u057e\5\36\20\2\u057e\u057f\7\u0088\2\2\u057f\u0580\5\u00a8"+
		"U\2\u0580\u0598\3\2\2\2\u0581\u0582\7o\2\2\u0582\u0583\7\u0087\2\2\u0583"+
		"\u0584\5\u00d0i\2\u0584\u0585\7\u0088\2\2\u0585\u0586\5\u00a8U\2\u0586"+
		"\u0598\3\2\2\2\u0587\u058b\7w\2\2\u0588\u0589\7W\2\2\u0589\u058c\5\36"+
		"\20\2\u058a\u058c\7\25\2\2\u058b\u0588\3\2\2\2\u058b\u058a\3\2\2\2\u058c"+
		"\u058d\3\2\2\2\u058d\u0598\7\u008c\2\2\u058e\u058f\7m\2\2\u058f\u0598"+
		"\5\u00acW\2\u0590\u0591\7/\2\2\u0591\u0592\7\u0087\2\2\u0592\u0593\5\u016c"+
		"\u00b7\2\u0593\u0594\5\u016e\u00b8\2\u0594\u0595\7\u0088\2\2\u0595\u0596"+
		"\5\u00a8U\2\u0596\u0598\3\2\2\2\u0597\u0515\3\2\2\2\u0597\u0516\3\2\2"+
		"\2\u0597\u0519\3\2\2\2\u0597\u0522\3\2\2\2\u0597\u052f\3\2\2\2\u0597\u0535"+
		"\3\2\2\2\u0597\u053d\3\2\2\2\u0597\u054d\3\2\2\2\u0597\u0558\3\2\2\2\u0597"+
		"\u055a\3\2\2\2\u0597\u055c\3\2\2\2\u0597\u0564\3\2\2\2\u0597\u0569\3\2"+
		"\2\2\u0597\u056e\3\2\2\2\u0597\u0577\3\2\2\2\u0597\u0579\3\2\2\2\u0597"+
		"\u057b\3\2\2\2\u0597\u0581\3\2\2\2\u0597\u0587\3\2\2\2\u0597\u058e\3\2"+
		"\2\2\u0597\u0590\3\2\2\2\u0598\u00ab\3\2\2\2\u0599\u059b\7\u0083\2\2\u059a"+
		"\u059c\5\u00c0a\2\u059b\u059a\3\2\2\2\u059b\u059c\3\2\2\2\u059c\u059d"+
		"\3\2\2\2\u059d\u059e\7\u0084\2\2\u059e\u00ad\3\2\2\2\u059f\u05a4\7o\2"+
		"\2\u05a0\u05a4\7U\2\2\u05a1\u05a2\7U\2\2\u05a2\u05a4\7T\2\2\u05a3\u059f"+
		"\3\2\2\2\u05a3\u05a0\3\2\2\2\u05a3\u05a1\3\2\2\2\u05a3\u05a4\3\2\2\2\u05a4"+
		"\u05a5\3\2\2\2\u05a5\u05a6\5\u00b0Y\2\u05a6\u05ab\5\u00b2Z\2\u05a7\u05a8"+
		"\7\u008a\2\2\u05a8\u05aa\5\u00b2Z\2\u05a9\u05a7\3\2\2\2\u05aa\u05ad\3"+
		"\2\2\2\u05ab\u05a9\3\2\2\2\u05ab\u05ac\3\2\2\2\u05ac\u05b3\3\2\2\2\u05ad"+
		"\u05ab\3\2\2\2\u05ae\u05af\7/\2\2\u05af\u05b0\5\u016c\u00b7\2\u05b0\u05b1"+
		"\5\u016e\u00b8\2\u05b1\u05b3\3\2\2\2\u05b2\u05a3\3\2\2\2\u05b2\u05ae\3"+
		"\2\2\2\u05b3\u00af\3\2\2\2\u05b4\u05b7\7p\2\2\u05b5\u05b7\5\6\4\2\u05b6"+
		"\u05b4\3\2\2\2\u05b6\u05b5\3\2\2\2\u05b7\u00b1\3\2\2\2\u05b8\u05be\5\u01b4"+
		"\u00db\2\u05b9\u05bb\7\u0097\2\2\u05ba\u05bc\7U\2\2\u05bb\u05ba\3\2\2"+
		"\2\u05bb\u05bc\3\2\2\2\u05bc\u05bd\3\2\2\2\u05bd\u05bf\5\u00b4[\2\u05be"+
		"\u05b9\3\2\2\2\u05be\u05bf\3\2\2\2\u05bf\u00b3\3\2\2\2\u05c0\u05c4\5\36"+
		"\20\2\u05c1\u05c4\5\u0146\u00a4\2\u05c2\u05c4\5\u0176\u00bc\2\u05c3\u05c0"+
		"\3\2\2\2\u05c3\u05c1\3\2\2\2\u05c3\u05c2\3\2\2\2\u05c4\u00b5\3\2\2\2\u05c5"+
		"\u05c6\7\35\2\2\u05c6\u05c7\5\6\4\2\u05c7\u05c8\5\u010a\u0086\2\u05c8"+
		"\u00b7\3\2\2\2\u05c9\u05cc\5\u00acW\2\u05ca\u05cc\5\u00aaV\2\u05cb\u05c9"+
		"\3\2\2\2\u05cb\u05ca\3\2\2\2\u05cc\u00b9\3\2\2\2\u05cd\u05cf\5\u00bc_"+
		"\2\u05ce\u05cd\3\2\2\2\u05cf\u05d0\3\2\2\2\u05d0\u05ce\3\2\2\2\u05d0\u05d1"+
		"\3\2\2\2\u05d1\u05d2\3\2\2\2\u05d2\u05d3\5\u00c0a\2\u05d3\u00bb\3\2\2"+
		"\2\u05d4\u05d5\7\30\2\2\u05d5\u05d7\5\36\20\2\u05d6\u05d8\5\u00be`\2\u05d7"+
		"\u05d6\3\2\2\2\u05d7\u05d8\3\2\2\2\u05d8\u05d9\3\2\2\2\u05d9\u05da\7\u008b"+
		"\2\2\u05da\u05de\3\2\2\2\u05db\u05dc\7 \2\2\u05dc\u05de\7\u008b\2\2\u05dd"+
		"\u05d4\3\2\2\2\u05dd\u05db\3\2\2\2\u05de\u00bd\3\2\2\2\u05df\u05e0\7t"+
		"\2\2\u05e0\u05e1\5\36\20\2\u05e1\u00bf\3\2\2\2\u05e2\u05e4\5\u009aN\2"+
		"\u05e3\u05e2\3\2\2\2\u05e4\u05e5\3\2\2\2\u05e5\u05e3\3\2\2\2\u05e5\u05e6"+
		"\3\2\2\2\u05e6\u00c1\3\2\2\2\u05e7\u05f1\5\u00aeX\2\u05e8\u05ed\5\36\20"+
		"\2\u05e9\u05ea\7\u008a\2\2\u05ea\u05ec\5\36\20\2\u05eb\u05e9\3\2\2\2\u05ec"+
		"\u05ef\3\2\2\2\u05ed\u05eb\3\2\2\2\u05ed\u05ee\3\2\2\2\u05ee\u05f1\3\2"+
		"\2\2\u05ef\u05ed\3\2\2\2\u05f0\u05e7\3\2\2\2\u05f0\u05e8\3\2\2\2\u05f1"+
		"\u00c3\3\2\2\2\u05f2\u05f7\5\36\20\2\u05f3\u05f4\7\u008a\2\2\u05f4\u05f6"+
		"\5\36\20\2\u05f5\u05f3\3\2\2\2\u05f6\u05f9\3\2\2\2\u05f7\u05f5\3\2\2\2"+
		"\u05f7\u05f8\3\2\2\2\u05f8\u00c5\3\2\2\2\u05f9\u05f7\3\2\2\2\u05fa\u05fe"+
		"\5\u00c8e\2\u05fb\u05fd\5\u00c8e\2\u05fc\u05fb\3\2\2\2\u05fd\u0600\3\2"+
		"\2\2\u05fe\u05fc\3\2\2\2\u05fe\u05ff\3\2\2\2\u05ff\u0602\3\2\2\2\u0600"+
		"\u05fe\3\2\2\2\u0601\u0603\5\u00caf\2\u0602\u0601\3\2\2\2\u0602\u0603"+
		"\3\2\2\2\u0603\u0606\3\2\2\2\u0604\u0606\5\u00caf\2\u0605\u05fa\3\2\2"+
		"\2\u0605\u0604\3\2\2\2\u0606\u00c7\3\2\2\2\u0607\u0608\7\31\2\2\u0608"+
		"\u0609\7\u0087\2\2\u0609\u060b\5\26\f\2\u060a\u060c\5\u01b4\u00db\2\u060b"+
		"\u060a\3\2\2\2\u060b\u060c\3\2\2\2\u060c\u060d\3\2\2\2\u060d\u060f\7\u0088"+
		"\2\2\u060e\u0610\5\u00ccg\2\u060f\u060e\3\2\2\2\u060f\u0610\3\2\2\2\u0610"+
		"\u0611\3\2\2\2\u0611\u0612\5\u00acW\2\u0612\u00c9\3\2\2\2\u0613\u0615"+
		"\7\31\2\2\u0614\u0616\5\u00ccg\2\u0615\u0614\3\2\2\2\u0615\u0616\3\2\2"+
		"\2\u0616\u0617\3\2\2\2\u0617\u0618\5\u00acW\2\u0618\u00cb\3\2\2\2\u0619"+
		"\u061a\7t\2\2\u061a\u061b\7\u0087\2\2\u061b\u061c\5\36\20\2\u061c\u061d"+
		"\7\u0088\2\2\u061d\u00cd\3\2\2\2\u061e\u061f\7.\2\2\u061f\u0620\5\u00ac"+
		"W\2\u0620\u00cf\3\2\2\2\u0621\u0624\5\u00aeX\2\u0622\u0624\5\36\20\2\u0623"+
		"\u0621\3\2\2\2\u0623\u0622\3\2\2\2\u0624\u00d1\3\2\2\2\u0625\u0626\7D"+
		"\2\2\u0626\u0627\5\u00d4k\2\u0627\u0628\7\u008c\2\2\u0628\u0630\3\2\2"+
		"\2\u0629\u062a\7D\2\2\u062a\u062b\5\u00d4k\2\u062b\u062d\5\u00d6l\2\u062c"+
		"\u062e\7\u008c\2\2\u062d\u062c\3\2\2\2\u062d\u062e\3\2\2\2\u062e\u0630"+
		"\3\2\2\2\u062f\u0625\3\2\2\2\u062f\u0629\3\2\2\2\u0630\u00d3\3\2\2\2\u0631"+
		"\u0636\5\u01b4\u00db\2\u0632\u0633\7\u0089\2\2\u0633\u0635\5\u01b4\u00db"+
		"\2\u0634\u0632\3\2\2\2\u0635\u0638\3\2\2\2\u0636\u0634\3\2\2\2\u0636\u0637"+
		"\3\2\2\2\u0637\u00d5\3\2\2\2\u0638\u0636\3\2\2\2\u0639\u063b\7\u0083\2"+
		"\2\u063a\u063c\5\u00d8m\2\u063b\u063a\3\2\2\2\u063b\u063c\3\2\2\2\u063c"+
		"\u063e\3\2\2\2\u063d\u063f\5\u00dco\2\u063e\u063d\3\2\2\2\u063e\u063f"+
		"\3\2\2\2\u063f\u0641\3\2\2\2\u0640\u0642\5\u00e0q\2\u0641\u0640\3\2\2"+
		"\2\u0641\u0642\3\2\2\2\u0642\u0643\3\2\2\2\u0643\u0644\7\u0084\2\2\u0644"+
		"\u00d7\3\2\2\2\u0645\u0647\5\u00dan\2\u0646\u0645\3\2\2\2\u0647\u0648"+
		"\3\2\2\2\u0648\u0646\3\2\2\2\u0648\u0649\3\2\2\2\u0649\u00d9\3\2\2\2\u064a"+
		"\u064b\7+\2\2\u064b\u064c\7\r\2\2\u064c\u064d\5\u01b4\u00db\2\u064d\u064e"+
		"\7\u008c\2\2\u064e\u00db\3\2\2\2\u064f\u0651\5\u00dep\2\u0650\u064f\3"+
		"\2\2\2\u0651\u0652\3\2\2\2\u0652\u0650\3\2\2\2\u0652\u0653\3\2\2\2\u0653"+
		"\u00dd\3\2\2\2\u0654\u0655\7o\2\2\u0655\u0656\5\u01b4\u00db\2\u0656\u0657"+
		"\7\u0097\2\2\u0657\u0658\5\4\3\2\u0658\u0659\7\u008c\2\2\u0659\u0664\3"+
		"\2\2\2\u065a\u065b\7o\2\2\u065b\u065c\5\4\3\2\u065c\u065d\7\u008c\2\2"+
		"\u065d\u0664\3\2\2\2\u065e\u065f\7o\2\2\u065f\u0660\7_\2\2\u0660\u0661"+
		"\5\4\3\2\u0661\u0662\7\u008c\2\2\u0662\u0664\3\2\2\2\u0663\u0654\3\2\2"+
		"\2\u0663\u065a\3\2\2\2\u0663\u065e\3\2\2\2\u0664\u00df\3\2\2\2\u0665\u0667"+
		"\5\u00e2r\2\u0666\u0665\3\2\2\2\u0667\u0668\3\2\2\2\u0668\u0666\3\2\2"+
		"\2\u0668\u0669\3\2\2\2\u0669\u00e1\3\2\2\2\u066a\u066d\5\u00d2j\2\u066b"+
		"\u066d\5\u00e4s\2\u066c\u066a\3\2\2\2\u066c\u066b\3\2\2\2\u066d\u00e3"+
		"\3\2\2\2\u066e\u0670\5\u0160\u00b1\2\u066f\u066e\3\2\2\2\u066f\u0670\3"+
		"\2\2\2\u0670\u0672\3\2\2\2\u0671\u0673\5\u0102\u0082\2\u0672\u0671\3\2"+
		"\2\2\u0672\u0673\3\2\2\2\u0673\u0679\3\2\2\2\u0674\u067a\5\u0190\u00c9"+
		"\2\u0675\u067a\5\u0192\u00ca\2\u0676\u067a\5\u0194\u00cb\2\u0677\u067a"+
		"\5\u0196\u00cc\2\u0678\u067a\5\u0198\u00cd\2\u0679\u0674\3\2\2\2\u0679"+
		"\u0675\3\2\2\2\u0679\u0676\3\2\2\2\u0679\u0677\3\2\2\2\u0679\u0678\3\2"+
		"\2\2\u067a\u00e5\3\2\2\2\u067b\u067c\5\u01b4\u00db\2\u067c\u067d\7\u009b"+
		"\2\2\u067d\u067f\5\u01b4\u00db\2\u067e\u0680\5\30\r\2\u067f\u067e\3\2"+
		"\2\2\u067f\u0680\3\2\2\2\u0680\u00e7\3\2\2\2\u0681\u0682\7\u0098\2\2\u0682"+
		"\u0687\5\u00eav\2\u0683\u0684\7\u008a\2\2\u0684\u0686\5\u00eav\2\u0685"+
		"\u0683\3\2\2\2\u0686\u0689\3\2\2\2\u0687\u0685\3\2\2\2\u0687\u0688\3\2"+
		"\2\2\u0688\u068a\3\2\2\2\u0689\u0687\3\2\2\2\u068a\u068b\7\u0099\2\2\u068b"+
		"\u00e9\3\2\2\2\u068c\u068e\5\u0160\u00b1\2\u068d\u068c\3\2\2\2\u068d\u068e"+
		"\3\2\2\2\u068e\u068f\3\2\2\2\u068f\u0690\5\u01b4\u00db\2\u0690\u00eb\3"+
		"\2\2\2\u0691\u0692\7\u008b\2\2\u0692\u0697\5\26\f\2\u0693\u0694\7\u008a"+
		"\2\2\u0694\u0696\5\4\3\2\u0695\u0693\3\2\2\2\u0696\u0699\3\2\2\2\u0697"+
		"\u0695\3\2\2\2\u0697\u0698\3\2\2\2\u0698\u00ed\3\2\2\2\u0699\u0697\3\2"+
		"\2\2\u069a\u069f\5\4\3\2\u069b\u069c\7\u008a\2\2\u069c\u069e\5\4\3\2\u069d"+
		"\u069b\3\2\2\2\u069e\u06a1\3\2\2\2\u069f\u069d\3\2\2\2\u069f\u06a0\3\2"+
		"\2\2\u06a0\u00ef\3\2\2\2\u06a1\u069f\3\2\2\2\u06a2\u06a4\5\u00f2z\2\u06a3"+
		"\u06a2\3\2\2\2\u06a4\u06a5\3\2\2\2\u06a5\u06a3\3\2\2\2\u06a5\u06a6\3\2"+
		"\2\2\u06a6\u00f1\3\2\2\2\u06a7\u06a8\7u\2\2\u06a8\u06a9\5\u01b4\u00db"+
		"\2\u06a9\u06aa\7\u008b\2\2\u06aa\u06ab\5\u00f4{\2\u06ab\u00f3\3\2\2\2"+
		"\u06ac\u06b7\5\u00fa~\2\u06ad\u06b0\5\u00f6|\2\u06ae\u06af\7\u008a\2\2"+
		"\u06af\u06b1\5\u00f8}\2\u06b0\u06ae\3\2\2\2\u06b0\u06b1\3\2\2\2\u06b1"+
		"\u06b4\3\2\2\2\u06b2\u06b3\7\u008a\2\2\u06b3\u06b5\5\u00fa~\2\u06b4\u06b2"+
		"\3\2\2\2\u06b4\u06b5\3\2\2\2\u06b5\u06b7\3\2\2\2\u06b6\u06ac\3\2\2\2\u06b6"+
		"\u06ad\3\2\2\2\u06b7\u00f5\3\2\2\2\u06b8\u06c0\5\26\f\2\u06b9\u06bb\7"+
		"\34\2\2\u06ba\u06bc\7\u009a\2\2\u06bb\u06ba\3\2\2\2\u06bb\u06bc\3\2\2"+
		"\2\u06bc\u06c0\3\2\2\2\u06bd\u06c0\7a\2\2\u06be\u06c0\7l\2\2\u06bf\u06b8"+
		"\3\2\2\2\u06bf\u06b9\3\2\2\2\u06bf\u06bd\3\2\2\2\u06bf\u06be\3\2\2\2\u06c0"+
		"\u00f7\3\2\2\2\u06c1\u06c6\5\4\3\2\u06c2\u06c3\7\u008a\2\2\u06c3\u06c5"+
		"\5\4\3\2\u06c4\u06c2\3\2\2\2\u06c5\u06c8\3\2\2\2\u06c6\u06c4\3\2\2\2\u06c6"+
		"\u06c7\3\2\2\2\u06c7\u00f9\3\2\2\2\u06c8\u06c6\3\2\2\2\u06c9\u06ca\7F"+
		"\2\2\u06ca\u06cb\7\u0087\2\2\u06cb\u06cc\7\u0088\2\2\u06cc\u00fb\3\2\2"+
		"\2\u06cd\u06cf\7\u0083\2\2\u06ce\u06d0\5\u00fe\u0080\2\u06cf\u06ce\3\2"+
		"\2\2\u06cf\u06d0\3\2\2\2\u06d0\u06d1\3\2\2\2\u06d1\u06d2\7\u0084\2\2\u06d2"+
		"\u00fd\3\2\2\2\u06d3\u06d5\5\u0100\u0081\2\u06d4\u06d3\3\2\2\2\u06d5\u06d6"+
		"\3\2\2\2\u06d6\u06d4\3\2\2\2\u06d6\u06d7\3\2\2\2\u06d7\u00ff\3\2\2\2\u06d8"+
		"\u06da\5\u0160\u00b1\2\u06d9\u06d8\3\2\2\2\u06d9\u06da\3\2\2\2\u06da\u06dc"+
		"\3\2\2\2\u06db\u06dd\5\u0102\u0082\2\u06dc\u06db\3\2\2\2\u06dc\u06dd\3"+
		"\2\2\2\u06dd\u06e0\3\2\2\2\u06de\u06e1\5\u0106\u0084\2\u06df\u06e1\5\u01a4"+
		"\u00d3\2\u06e0\u06de\3\2\2\2\u06e0\u06df\3\2\2\2\u06e1\u0101\3\2\2\2\u06e2"+
		"\u06e4\5\u0104\u0083\2\u06e3\u06e2\3\2\2\2\u06e4\u06e5\3\2\2\2\u06e5\u06e3"+
		"\3\2\2\2\u06e5\u06e6\3\2\2\2\u06e6\u0103\3\2\2\2\u06e7\u06e8\t\f\2\2\u06e8"+
		"\u0105\3\2\2\2\u06e9\u06fd\5\u01a0\u00d1\2\u06ea\u06fd\5\u0108\u0085\2"+
		"\u06eb\u06fd\5\u019a\u00ce\2\u06ec\u06f2\5\u0136\u009c\2\u06ed\u06f3\5"+
		"\u013a\u009e\2\u06ee\u06ef\5\u0178\u00bd\2\u06ef\u06f0\5L\'\2\u06f0\u06f1"+
		"\7\u008c\2\2\u06f1\u06f3\3\2\2\2\u06f2\u06ed\3\2\2\2\u06f2\u06ee\3\2\2"+
		"\2\u06f3\u06fd\3\2\2\2\u06f4\u06fd\5\u01a6\u00d4\2\u06f5\u06f6\7r\2\2"+
		"\u06f6\u06fd\5\u01a8\u00d5\2\u06f7\u06fd\5\u0190\u00c9\2\u06f8\u06fd\5"+
		"\u0192\u00ca\2\u06f9\u06fd\5\u0194\u00cb\2\u06fa\u06fd\5\u0196\u00cc\2"+
		"\u06fb\u06fd\5\u0198\u00cd\2\u06fc\u06e9\3\2\2\2\u06fc\u06ea\3\2\2\2\u06fc"+
		"\u06eb\3\2\2\2\u06fc\u06ec\3\2\2\2\u06fc\u06f4\3\2\2\2\u06fc\u06f5\3\2"+
		"\2\2\u06fc\u06f7\3\2\2\2\u06fc\u06f8\3\2\2\2\u06fc\u06f9\3\2\2\2\u06fc"+
		"\u06fa\3\2\2\2\u06fc\u06fb\3\2\2\2\u06fd\u0107\3\2\2\2\u06fe\u0704\7U"+
		"\2\2\u06ff\u0700\7T\2\2\u0700\u0704\7U\2\2\u0701\u0702\7U\2\2\u0702\u0704"+
		"\7T\2\2\u0703\u06fe\3\2\2\2\u0703\u06ff\3\2\2\2\u0703\u0701\3\2\2\2\u0703"+
		"\u0704\3\2\2\2\u0704\u0705\3\2\2\2\u0705\u070f\5\6\4\2\u0706\u0707\5\4"+
		"\3\2\u0707\u0708\7\u0089\2\2\u0708\u0709\5\u01a2\u00d2\2\u0709\u0710\3"+
		"\2\2\2\u070a\u0710\5\u01a8\u00d5\2\u070b\u0710\5\u019e\u00d0\2\u070c\u0710"+
		"\5\u01a2\u00d2\2\u070d\u0710\5\u01ac\u00d7\2\u070e\u0710\5\u019c\u00cf"+
		"\2\u070f\u0706\3\2\2\2\u070f\u070a\3\2\2\2\u070f\u070b\3\2\2\2\u070f\u070c"+
		"\3\2\2\2\u070f\u070d\3\2\2\2\u070f\u070e\3\2\2\2\u0710\u0109\3\2\2\2\u0711"+
		"\u0716\5\u010c\u0087\2\u0712\u0713\7\u008a\2\2\u0713\u0715\5\u010c\u0087"+
		"\2\u0714\u0712\3\2\2\2\u0715\u0718\3\2\2\2\u0716\u0714\3\2\2\2\u0716\u0717"+
		"\3\2\2\2\u0717\u010b\3\2\2\2\u0718\u0716\3\2\2\2\u0719\u071a\5\u01b4\u00db"+
		"\2\u071a\u071b\7\u0097\2\2\u071b\u071c\5\36\20\2\u071c\u010d\3\2\2\2\u071d"+
		"\u0722\5\u0110\u0089\2\u071e\u071f\7\u008a\2\2\u071f\u0721\5\u0110\u0089"+
		"\2\u0720\u071e\3\2\2\2\u0721\u0724\3\2\2\2\u0722\u0720\3\2\2\2\u0722\u0723"+
		"\3\2\2\2\u0723\u010f\3\2\2\2\u0724\u0722\3\2\2\2\u0725\u0728\5\u01b4\u00db"+
		"\2\u0726\u0727\7\u0097\2\2\u0727\u0729\5\u0112\u008a\2\u0728\u0726\3\2"+
		"\2\2\u0728\u0729\3\2\2\2\u0729\u0111\3\2\2\2\u072a\u072d\5\36\20\2\u072b"+
		"\u072d\5\u0146\u00a4\2\u072c\u072a\3\2\2\2\u072c\u072b\3\2\2\2\u072d\u0113"+
		"\3\2\2\2\u072e\u0731\5\6\4\2\u072f\u0731\7r\2\2\u0730\u072e\3\2\2\2\u0730"+
		"\u072f\3\2\2\2\u0731\u0115\3\2\2\2\u0732\u0733\5\4\3\2\u0733\u0117\3\2"+
		"\2\2\u0734\u0737\5\u00acW\2\u0735\u0737\7\u008c\2\2\u0736\u0734\3\2\2"+
		"\2\u0736\u0735\3\2\2\2\u0737\u0119\3\2\2\2\u0738\u073f\5\u0122\u0092\2"+
		"\u0739\u073c\5\u011c\u008f\2\u073a\u073b\7\u008a\2\2\u073b\u073d\5\u0122"+
		"\u0092\2\u073c\u073a\3\2\2\2\u073c\u073d\3\2\2\2\u073d\u073f\3\2\2\2\u073e"+
		"\u0738\3\2\2\2\u073e\u0739\3\2\2\2\u073f\u011b\3\2\2\2\u0740\u0745\5\u011e"+
		"\u0090\2\u0741\u0742\7\u008a\2\2\u0742\u0744\5\u011e\u0090\2\u0743\u0741"+
		"\3\2\2\2\u0744\u0747\3\2\2\2\u0745\u0743\3\2\2\2\u0745\u0746\3\2\2\2\u0746"+
		"\u011d\3\2\2\2\u0747\u0745\3\2\2\2\u0748\u074a\5\u0160\u00b1\2\u0749\u0748"+
		"\3\2\2\2\u0749\u074a\3\2\2\2\u074a\u074c\3\2\2\2\u074b\u074d\5\u0120\u0091"+
		"\2\u074c\u074b\3\2\2\2\u074c\u074d\3\2\2\2\u074d\u074e\3\2\2\2\u074e\u0751"+
		"\5\u01ae\u00d8\2\u074f\u0751\7\16\2\2\u0750\u0749\3\2\2\2\u0750\u074f"+
		"\3\2\2\2\u0751\u011f\3\2\2\2\u0752\u075b\7U\2\2\u0753\u075b\7M\2\2\u0754"+
		"\u075b\79\2\2\u0755\u0756\7U\2\2\u0756\u075b\7c\2\2\u0757\u0758\79\2\2"+
		"\u0758\u075b\7c\2\2\u0759\u075b\7c\2\2\u075a\u0752\3\2\2\2\u075a\u0753"+
		"\3\2\2\2\u075a\u0754\3\2\2\2\u075a\u0755\3\2\2\2\u075a\u0757\3\2\2\2\u075a"+
		"\u0759\3\2\2\2\u075b\u0121\3\2\2\2\u075c\u075e\5\u0160\u00b1\2\u075d\u075c"+
		"\3\2\2\2\u075d\u075e\3\2\2\2\u075e\u075f\3\2\2\2\u075f\u0760\7O\2\2\u0760"+
		"\u0761\5\u0142\u00a2\2\u0761\u0762\5\u01b4\u00db\2\u0762\u0123\3\2\2\2"+
		"\u0763\u0765\5\u0160\u00b1\2\u0764\u0763\3\2\2\2\u0764\u0765\3\2\2\2\u0765"+
		"\u0767\3\2\2\2\u0766\u0768\5\u012a\u0096\2\u0767\u0766\3\2\2\2\u0767\u0768"+
		"\3\2\2\2\u0768\u0773\3\2\2\2\u0769\u076a\7\64\2\2\u076a\u076c\5\u012c"+
		"\u0097\2\u076b\u076d\5\u0128\u0095\2\u076c\u076b\3\2\2\2\u076c\u076d\3"+
		"\2\2\2\u076d\u0774\3\2\2\2\u076e\u076f\7[\2\2\u076f\u0771\5\u012c\u0097"+
		"\2\u0770\u0772\5\u0126\u0094\2\u0771\u0770\3\2\2\2\u0771\u0772\3\2\2\2"+
		"\u0772\u0774\3\2\2\2\u0773\u0769\3\2\2\2\u0773\u076e\3\2\2\2\u0774\u0125"+
		"\3\2\2\2\u0775\u0777\5\u0160\u00b1\2\u0776\u0775\3\2\2\2\u0776\u0777\3"+
		"\2\2\2\u0777\u0779\3\2\2\2\u0778\u077a\5\u012a\u0096\2\u0779\u0778\3\2"+
		"\2\2\u0779\u077a\3\2\2\2\u077a\u077b\3\2\2\2\u077b\u077c\7\64\2\2\u077c"+
		"\u077d\5\u012c\u0097\2\u077d\u0127\3\2\2\2\u077e\u0780\5\u0160\u00b1\2"+
		"\u077f\u077e\3\2\2\2\u077f\u0780\3\2\2\2\u0780\u0782\3\2\2\2\u0781\u0783"+
		"\5\u012a\u0096\2\u0782\u0781\3\2\2\2\u0782\u0783\3\2\2\2\u0783\u0784\3"+
		"\2\2\2\u0784\u0785\7[\2\2\u0785\u0786\5\u012c\u0097\2\u0786\u0129\3\2"+
		"\2\2\u0787\u078f\7R\2\2\u0788\u078f\7<\2\2\u0789\u078f\7Q\2\2\u078a\u078b"+
		"\7R\2\2\u078b\u078f\7<\2\2\u078c\u078d\7<\2\2\u078d\u078f\7R\2\2\u078e"+
		"\u0787\3\2\2\2\u078e\u0788\3\2\2\2\u078e\u0789\3\2\2\2\u078e\u078a\3\2"+
		"\2\2\u078e\u078c\3\2\2\2\u078f\u012b\3\2\2\2\u0790\u0791\5\u0178\u00bd"+
		"\2\u0791\u0792\5\u009aN\2\u0792\u0796\3\2\2\2\u0793\u0796\5\u00acW\2\u0794"+
		"\u0796\7\u008c\2\2\u0795\u0790\3\2\2\2\u0795\u0793\3\2\2\2\u0795\u0794"+
		"\3\2\2\2\u0796\u012d\3\2\2\2\u0797\u0799\5\u0160\u00b1\2\u0798\u0797\3"+
		"\2\2\2\u0798\u0799\3\2\2\2\u0799\u07a2\3\2\2\2\u079a\u079b\7\f\2\2\u079b"+
		"\u079c\5\u00acW\2\u079c\u079d\5\u0132\u009a\2\u079d\u07a3\3\2\2\2\u079e"+
		"\u079f\7V\2\2\u079f\u07a0\5\u00acW\2\u07a0\u07a1\5\u0130\u0099\2\u07a1"+
		"\u07a3\3\2\2\2\u07a2\u079a\3\2\2\2\u07a2\u079e\3\2\2\2\u07a3\u012f\3\2"+
		"\2\2\u07a4\u07a6\5\u0160\u00b1\2\u07a5\u07a4\3\2\2\2\u07a5\u07a6\3\2\2"+
		"\2\u07a6\u07a7\3\2\2\2\u07a7\u07a8\7\f\2\2\u07a8\u07a9\5\u00acW\2\u07a9"+
		"\u0131\3\2\2\2\u07aa\u07ac\5\u0160\u00b1\2\u07ab\u07aa\3\2\2\2\u07ab\u07ac"+
		"\3\2\2\2\u07ac\u07ad\3\2\2\2\u07ad\u07ae\7V\2\2\u07ae\u07af\5\u00acW\2"+
		"\u07af\u0133\3\2\2\2\u07b0\u07c7\7\u008d\2\2\u07b1\u07c7\7\u008e\2\2\u07b2"+
		"\u07c7\7\u0095\2\2\u07b3\u07c7\7\u0096\2\2\u07b4\u07c7\7\u009d\2\2\u07b5"+
		"\u07c7\7\u009e\2\2\u07b6\u07c7\7f\2\2\u07b7\u07c7\7,\2\2\u07b8\u07c7\7"+
		"\u008f\2\2\u07b9\u07c7\7\u0090\2\2\u07ba\u07c7\7\u0091\2\2\u07bb\u07c7"+
		"\7\u0092\2\2\u07bc\u07c7\7\u0093\2\2\u07bd\u07c7\7\u0094\2\2\u07be\u07c7"+
		"\7\u00ae\2\2\u07bf\u07c7\5\u017a\u00be\2\u07c0\u07c7\7\u00a2\2\2\u07c1"+
		"\u07c7\7\u00a3\2\2\u07c2\u07c7\7\u0099\2\2\u07c3\u07c7\7\u0098\2\2\u07c4"+
		"\u07c7\7\u00a5\2\2\u07c5\u07c7\7\u00a4\2\2\u07c6\u07b0\3\2\2\2\u07c6\u07b1"+
		"\3\2\2\2\u07c6\u07b2\3\2\2\2\u07c6\u07b3\3\2\2\2\u07c6\u07b4\3\2\2\2\u07c6"+
		"\u07b5\3\2\2\2\u07c6\u07b6\3\2\2\2\u07c6\u07b7\3\2\2\2\u07c6\u07b8\3\2"+
		"\2\2\u07c6\u07b9\3\2\2\2\u07c6\u07ba\3\2\2\2\u07c6\u07bb\3\2\2\2\u07c6"+
		"\u07bc\3\2\2\2\u07c6\u07bd\3\2\2\2\u07c6\u07be\3\2\2\2\u07c6\u07bf\3\2"+
		"\2\2\u07c6\u07c0\3\2\2\2\u07c6\u07c1\3\2\2\2\u07c6\u07c2\3\2\2\2\u07c6"+
		"\u07c3\3\2\2\2\u07c6\u07c4\3\2\2\2\u07c6\u07c5\3\2\2\2\u07c7\u0135\3\2"+
		"\2\2\u07c8\u07c9\t\r\2\2\u07c9\u07ca\7K\2\2\u07ca\u07cb\5\6\4\2\u07cb"+
		"\u07cc\7\u0087\2\2\u07cc\u07cd\5\u01ae\u00d8\2\u07cd\u07ce\7\u0088\2\2"+
		"\u07ce\u0137\3\2\2\2\u07cf\u07d0\7\u008b\2\2\u07d0\u07d1\t\16\2\2\u07d1"+
		"\u07d3\7\u0087\2\2\u07d2\u07d4\5\32\16\2\u07d3\u07d2\3\2\2\2\u07d3\u07d4"+
		"\3\2\2\2\u07d4\u07d5\3\2\2\2\u07d5\u07d6\7\u0088\2\2\u07d6\u0139\3\2\2"+
		"\2\u07d7\u07da\5\u00acW\2\u07d8\u07da\7\u008c\2\2\u07d9\u07d7\3\2\2\2"+
		"\u07d9\u07d8\3\2\2\2\u07da\u013b\3\2\2\2\u07db\u07dc\7\u008b\2\2\u07dc"+
		"\u07dd\5\u00eex\2\u07dd\u013d\3\2\2\2\u07de\u07e2\7\u0083\2\2\u07df\u07e1"+
		"\5\u0140\u00a1\2\u07e0\u07df\3\2\2\2\u07e1\u07e4\3\2\2\2\u07e2\u07e0\3"+
		"\2\2\2\u07e2\u07e3\3\2\2\2\u07e3\u07e5\3\2\2\2\u07e4\u07e2\3\2\2\2\u07e5"+
		"\u07e6\7\u0084\2\2\u07e6\u013f\3\2\2\2\u07e7\u07e9\5\u0160\u00b1\2\u07e8"+
		"\u07e7\3\2\2\2\u07e8\u07e9\3\2\2\2\u07e9\u07eb\3\2\2\2\u07ea\u07ec\5\u0102"+
		"\u0082\2\u07eb\u07ea\3\2\2\2\u07eb\u07ec\3\2\2\2\u07ec\u07f7\3\2\2\2\u07ed"+
		"\u07f8\5\u0106\u0084\2\u07ee\u07ef\7/\2\2\u07ef\u07f1\5\6\4\2\u07f0\u07f2"+
		"\5\u0174\u00bb\2\u07f1\u07f0\3\2\2\2\u07f2\u07f3\3\2\2\2\u07f3\u07f1\3"+
		"\2\2\2\u07f3\u07f4\3\2\2\2\u07f4\u07f5\3\2\2\2\u07f5\u07f6\7\u008c\2\2"+
		"\u07f6\u07f8\3\2\2\2\u07f7\u07ed\3\2\2\2\u07f7\u07ee\3\2\2\2\u07f8\u0141"+
		"\3\2\2\2\u07f9\u0801\5\b\5\2\u07fa\u07fc\t\17\2\2\u07fb\u07fa\3\2\2\2"+
		"\u07fc\u07ff\3\2\2\2\u07fd\u07fb\3\2\2\2\u07fd\u07fe\3\2\2\2\u07fe\u0800"+
		"\3\2\2\2\u07ff\u07fd\3\2\2\2\u0800\u0802\5\u0144\u00a3\2\u0801\u07fd\3"+
		"\2\2\2\u0802\u0803\3\2\2\2\u0803\u0801\3\2\2\2\u0803\u0804\3\2\2\2\u0804"+
		"\u0143\3\2\2\2\u0805\u0809\7\u0085\2\2\u0806\u0808\7\u008a\2\2\u0807\u0806"+
		"\3\2\2\2\u0808\u080b\3\2\2\2\u0809\u0807\3\2\2\2\u0809\u080a\3\2\2\2\u080a"+
		"\u080c\3\2\2\2\u080b\u0809\3\2\2\2\u080c\u080d\7\u0086\2\2\u080d\u0145"+
		"\3\2\2\2\u080e\u081a\7\u0083\2\2\u080f\u0814\5\u0112\u008a\2\u0810\u0811"+
		"\7\u008a\2\2\u0811\u0813\5\u0112\u008a\2\u0812\u0810\3\2\2\2\u0813\u0816"+
		"\3\2\2\2\u0814\u0812\3\2\2\2\u0814\u0815\3\2\2\2\u0815\u0818\3\2\2\2\u0816"+
		"\u0814\3\2\2\2\u0817\u0819\7\u008a\2\2\u0818\u0817\3\2\2\2\u0818\u0819"+
		"\3\2\2\2\u0819\u081b\3\2\2\2\u081a\u080f\3\2\2\2\u081a\u081b\3\2\2\2\u081b"+
		"\u081c\3\2\2\2\u081c\u081d\7\u0084\2\2\u081d\u0147\3\2\2\2\u081e\u081f"+
		"\7\u0098\2\2\u081f\u0824\5\u014a\u00a6\2\u0820\u0821\7\u008a\2\2\u0821"+
		"\u0823\5\u014a\u00a6\2\u0822\u0820\3\2\2\2\u0823\u0826\3\2\2\2\u0824\u0822"+
		"\3\2\2\2\u0824\u0825\3\2\2\2\u0825\u0827\3\2\2\2\u0826\u0824\3\2\2\2\u0827"+
		"\u0828\7\u0099\2\2\u0828\u0149\3\2\2\2\u0829\u082b\5\u0160\u00b1\2\u082a"+
		"\u0829\3\2\2\2\u082a\u082b\3\2\2\2\u082b\u082d\3\2\2\2\u082c\u082e\5\u014c"+
		"\u00a7\2\u082d\u082c\3\2\2\2\u082d\u082e\3\2\2\2\u082e\u082f\3\2\2\2\u082f"+
		"\u0830\5\u01b4\u00db\2\u0830\u014b\3\2\2\2\u0831\u0832\t\20\2\2\u0832"+
		"\u014d\3\2\2\2\u0833\u0834\7\u008b\2\2\u0834\u0835\5\u00eex\2\u0835\u014f"+
		"\3\2\2\2\u0836\u083a\7\u0083\2\2\u0837\u0839\5\u0152\u00aa\2\u0838\u0837"+
		"\3\2\2\2\u0839\u083c\3\2\2\2\u083a\u0838\3\2\2\2\u083a\u083b\3\2\2\2\u083b"+
		"\u083d\3\2\2\2\u083c\u083a\3\2\2\2\u083d\u083e\7\u0084\2\2\u083e\u0151"+
		"\3\2\2\2\u083f\u0841\5\u0160\u00b1\2\u0840\u083f\3\2\2\2\u0840\u0841\3"+
		"\2\2\2\u0841\u0843\3\2\2\2\u0842\u0844\7F\2\2\u0843\u0842\3\2\2\2\u0843"+
		"\u0844\3\2\2\2\u0844\u0884\3\2\2\2\u0845\u0847\7m\2\2\u0846\u0845\3\2"+
		"\2\2\u0846\u0847\3\2\2\2\u0847\u084d\3\2\2\2\u0848\u084e\7U\2\2\u0849"+
		"\u084a\7U\2\2\u084a\u084e\7T\2\2\u084b\u084c\7T\2\2\u084c\u084e\7U\2\2"+
		"\u084d\u0848\3\2\2\2\u084d\u0849\3\2\2\2\u084d\u084b\3\2\2\2\u084d\u084e"+
		"\3\2\2\2\u084e\u084f\3\2\2\2\u084f\u086b\5\6\4\2\u0850\u0852\5\u01b4\u00db"+
		"\2\u0851\u0853\5\u00e8u\2\u0852\u0851\3\2\2\2\u0852\u0853\3\2\2\2\u0853"+
		"\u0854\3\2\2\2\u0854\u0856\7\u0087\2\2\u0855\u0857\5\u011a\u008e\2\u0856"+
		"\u0855\3\2\2\2\u0856\u0857\3\2\2\2\u0857\u0858\3\2\2\2\u0858\u085a\7\u0088"+
		"\2\2\u0859\u085b\5\u00f0y\2\u085a\u0859\3\2\2\2\u085a\u085b\3\2\2\2\u085b"+
		"\u085c\3\2\2\2\u085c\u085d\7\u008c\2\2\u085d\u086c\3\2\2\2\u085e\u085f"+
		"\5\u01b4\u00db\2\u085f\u0860\7\u0083\2\2\u0860\u0861\5\u0154\u00ab\2\u0861"+
		"\u0862\7\u0084\2\2\u0862\u086c\3\2\2\2\u0863\u0864\7c\2\2\u0864\u0865"+
		"\7\u0085\2\2\u0865\u0866\5\u011a\u008e\2\u0866\u0867\7\u0086\2\2\u0867"+
		"\u0868\7\u0083\2\2\u0868\u0869\5\u0154\u00ab\2\u0869\u086a\7\u0084\2\2"+
		"\u086a\u086c\3\2\2\2\u086b\u0850\3\2\2\2\u086b\u085e\3\2\2\2\u086b\u0863"+
		"\3\2\2\2\u086c\u0885\3\2\2\2\u086d\u086f\7m\2\2\u086e\u086d\3\2\2\2\u086e"+
		"\u086f\3\2\2\2\u086f\u0870\3\2\2\2\u0870\u0871\7r\2\2\u0871\u0873\5\u01b4"+
		"\u00db\2\u0872\u0874\5\u00e8u\2\u0873\u0872\3\2\2\2\u0873\u0874\3\2\2"+
		"\2\u0874\u0875\3\2\2\2\u0875\u0877\7\u0087\2\2\u0876\u0878\5\u011a\u008e"+
		"\2\u0877\u0876\3\2\2\2\u0877\u0878\3\2\2\2\u0878\u0879\3\2\2\2\u0879\u087b"+
		"\7\u0088\2\2\u087a\u087c\5\u00f0y\2\u087b\u087a\3\2\2\2\u087b\u087c\3"+
		"\2\2\2\u087c\u087d\3\2\2\2\u087d\u087e\7\u008c\2\2\u087e\u0885\3\2\2\2"+
		"\u087f\u0880\7)\2\2\u0880\u0881\5\6\4\2\u0881\u0882\5\u01b4\u00db\2\u0882"+
		"\u0883\7\u008c\2\2\u0883\u0885\3\2\2\2\u0884\u0846\3\2\2\2\u0884\u086e"+
		"\3\2\2\2\u0884\u087f\3\2\2\2\u0885\u0153\3\2\2\2\u0886\u0888\5\u0160\u00b1"+
		"\2\u0887\u0886\3\2\2\2\u0887\u0888\3\2\2\2\u0888\u089b\3\2\2\2\u0889\u088a"+
		"\7\64\2\2\u088a\u0890\7\u008c\2\2\u088b\u088d\5\u0160\u00b1\2\u088c\u088b"+
		"\3\2\2\2\u088c\u088d\3\2\2\2\u088d\u088e\3\2\2\2\u088e\u088f\7[\2\2\u088f"+
		"\u0891\7\u008c\2\2\u0890\u088c\3\2\2\2\u0890\u0891\3\2\2\2\u0891\u089c"+
		"\3\2\2\2\u0892\u0893\7[\2\2\u0893\u0899\7\u008c\2\2\u0894\u0896\5\u0160"+
		"\u00b1\2\u0895\u0894\3\2\2\2\u0895\u0896\3\2\2\2\u0896\u0897\3\2\2\2\u0897"+
		"\u0898\7\64\2\2\u0898\u089a\7\u008c\2\2\u0899\u0895\3\2\2\2\u0899\u089a"+
		"\3\2\2\2\u089a\u089c\3\2\2\2\u089b\u0889\3\2\2\2\u089b\u0892\3\2\2\2\u089c"+
		"\u0155\3\2\2\2\u089d\u089e\7\u008b\2\2\u089e\u089f\5\6\4\2\u089f\u0157"+
		"\3\2\2\2\u08a0\u08ac\7\u0083\2\2\u08a1\u08a6\5\u015a\u00ae\2\u08a2\u08a3"+
		"\7\u008a\2\2\u08a3\u08a5\5\u015a\u00ae\2\u08a4\u08a2\3\2\2\2\u08a5\u08a8"+
		"\3\2\2\2\u08a6\u08a4\3\2\2\2\u08a6\u08a7\3\2\2\2\u08a7\u08aa\3\2\2\2\u08a8"+
		"\u08a6\3\2\2\2\u08a9\u08ab\7\u008a\2\2\u08aa\u08a9\3\2\2\2\u08aa\u08ab"+
		"\3\2\2\2\u08ab\u08ad\3\2\2\2\u08ac\u08a1\3\2\2\2\u08ac\u08ad\3\2\2\2\u08ad"+
		"\u08ae\3\2\2\2\u08ae\u08af\7\u0084\2\2\u08af\u0159\3\2\2\2\u08b0\u08b2"+
		"\5\u0160\u00b1\2\u08b1\u08b0\3\2\2\2\u08b1\u08b2\3\2\2\2\u08b2\u08b3\3"+
		"\2\2\2\u08b3\u08b6\5\u01b4\u00db\2\u08b4\u08b5\7\u0097\2\2\u08b5\u08b7"+
		"\5\36\20\2\u08b6\u08b4\3\2\2\2\u08b6\u08b7\3\2\2\2\u08b7\u015b\3\2\2\2"+
		"\u08b8\u08b9\7\u0085\2\2\u08b9\u08ba\5\u015e\u00b0\2\u08ba\u08bb\7\u008b"+
		"\2\2\u08bb\u08bd\5\u0166\u00b4\2\u08bc\u08be\7\u008a\2\2\u08bd\u08bc\3"+
		"\2\2\2\u08bd\u08be\3\2\2\2\u08be\u08bf\3\2\2\2\u08bf\u08c0\7\u0086\2\2"+
		"\u08c0\u015d\3\2\2\2\u08c1\u08c4\5\u018e\u00c8\2\u08c2\u08c4\5\u01b4\u00db"+
		"\2\u08c3\u08c1\3\2\2\2\u08c3\u08c2\3\2\2\2\u08c4\u015f\3\2\2\2\u08c5\u08c7"+
		"\5\u0162\u00b2\2\u08c6\u08c5\3\2\2\2\u08c7\u08c8\3\2\2\2\u08c8\u08c6\3"+
		"\2\2\2\u08c8\u08c9\3\2\2\2\u08c9\u0161\3\2\2\2\u08ca\u08ce\7\u0085\2\2"+
		"\u08cb\u08cc\5\u0164\u00b3\2\u08cc\u08cd\7\u008b\2\2\u08cd\u08cf\3\2\2"+
		"\2\u08ce\u08cb\3\2\2\2\u08ce\u08cf\3\2\2\2\u08cf\u08d0\3\2\2\2\u08d0\u08d2"+
		"\5\u0166\u00b4\2\u08d1\u08d3\7\u008a\2\2\u08d2\u08d1\3\2\2\2\u08d2\u08d3"+
		"\3\2\2\2\u08d3\u08d4\3\2\2\2\u08d4\u08d5\7\u0086\2\2\u08d5\u0163\3\2\2"+
		"\2\u08d6\u08d9\5\u018e\u00c8\2\u08d7\u08d9\5\u01b4\u00db\2\u08d8\u08d6"+
		"\3\2\2\2\u08d8\u08d7\3\2\2\2\u08d9\u0165\3\2\2\2\u08da\u08df\5\u0168\u00b5"+
		"\2\u08db\u08dc\7\u008a\2\2\u08dc\u08de\5\u0168\u00b5\2\u08dd\u08db\3\2"+
		"\2\2\u08de\u08e1\3\2\2\2\u08df\u08dd\3\2\2\2\u08df\u08e0\3\2\2\2\u08e0"+
		"\u0167\3\2\2\2\u08e1\u08df\3\2\2\2\u08e2\u08ef\5\4\3\2\u08e3\u08ec\7\u0087"+
		"\2\2\u08e4\u08e9\5\u016a\u00b6\2\u08e5\u08e6\7\u008a\2\2\u08e6\u08e8\5"+
		"\u016a\u00b6\2\u08e7\u08e5\3\2\2\2\u08e8\u08eb\3\2\2\2\u08e9\u08e7\3\2"+
		"\2\2\u08e9\u08ea\3\2\2\2\u08ea\u08ed\3\2\2\2\u08eb\u08e9\3\2\2\2\u08ec"+
		"\u08e4\3\2\2\2\u08ec\u08ed\3\2\2\2\u08ed\u08ee\3\2\2\2\u08ee\u08f0\7\u0088"+
		"\2\2\u08ef\u08e3\3\2\2\2\u08ef\u08f0\3\2\2\2\u08f0\u0169\3\2\2\2\u08f1"+
		"\u08f2\5\u01b4\u00db\2\u08f2\u08f3\7\u008b\2\2\u08f3\u08f5\3\2\2\2\u08f4"+
		"\u08f1\3\2\2\2\u08f4\u08f5\3\2\2\2\u08f5\u08f6\3\2\2\2\u08f6\u08f7\5\36"+
		"\20\2\u08f7\u016b\3\2\2\2\u08f8\u08fb\5\16\b\2\u08f9\u08fb\5\26\f\2\u08fa"+
		"\u08f8\3\2\2\2\u08fa\u08f9\3\2\2\2\u08fb\u0900\3\2\2\2\u08fc\u08ff\5\u0144"+
		"\u00a3\2\u08fd\u08ff\7\u009a\2\2\u08fe\u08fc\3\2\2\2\u08fe\u08fd\3\2\2"+
		"\2\u08ff\u0902\3\2\2\2\u0900\u08fe\3\2\2\2\u0900\u0901\3\2\2\2\u0901\u0903"+
		"\3\2\2\2\u0902\u0900\3\2\2\2\u0903\u0904\7\u008f\2\2\u0904\u0908\3\2\2"+
		"\2\u0905\u0906\7r\2\2\u0906\u0908\7\u008f\2\2\u0907\u08fa\3\2\2\2\u0907"+
		"\u0905\3\2\2\2\u0908\u016d\3\2\2\2\u0909\u090e\5\u0170\u00b9\2\u090a\u090b"+
		"\7\u008a\2\2\u090b\u090d\5\u0170\u00b9\2\u090c\u090a\3\2\2\2\u090d\u0910"+
		"\3\2\2\2\u090e\u090c\3\2\2\2\u090e\u090f\3\2\2\2\u090f\u016f\3\2\2\2\u0910"+
		"\u090e\3\2\2\2\u0911\u0912\5\u01b4\u00db\2\u0912\u0913\7\u0097\2\2\u0913"+
		"\u0914\5\u0172\u00ba\2\u0914\u0171\3\2\2\2\u0915\u0917\7\u0092\2\2\u0916"+
		"\u0915\3\2\2\2\u0916\u0917\3\2\2\2\u0917\u0918\3\2\2\2\u0918\u091b\5\36"+
		"\20\2\u0919\u091b\5\u0176\u00bc\2\u091a\u0916\3\2\2\2\u091a\u0919\3\2"+
		"\2\2\u091b\u0173\3\2\2\2\u091c\u091d\5\u01b4\u00db\2\u091d\u091e\7\u0085"+
		"\2\2\u091e\u091f\5\36\20\2\u091f\u0920\7\u0086\2\2\u0920\u0175\3\2\2\2"+
		"\u0921\u0922\7^\2\2\u0922\u0923\5\6\4\2\u0923\u0924\7\u0085\2\2\u0924"+
		"\u0925\5\36\20\2\u0925\u0926\7\u0086\2\2\u0926\u093f\3\2\2\2\u0927\u0929"+
		"\7^\2\2\u0928\u092a\5\6\4\2\u0929\u0928\3\2\2\2\u0929\u092a\3\2\2\2\u092a"+
		"\u092b\3\2\2\2\u092b\u092d\7\u0085\2\2\u092c\u092e\5\36\20\2\u092d\u092c"+
		"\3\2\2\2\u092d\u092e\3\2\2\2\u092e\u092f\3\2\2\2\u092f\u0930\7\u0086\2"+
		"\2\u0930\u0931\7\u0083\2\2\u0931\u0936\5\36\20\2\u0932\u0933\7\u008a\2"+
		"\2\u0933\u0935\5\36\20\2\u0934\u0932\3\2\2\2\u0935\u0938\3\2\2\2\u0936"+
		"\u0934\3\2\2\2\u0936\u0937\3\2\2\2\u0937\u093a\3\2\2\2\u0938\u0936\3\2"+
		"\2\2\u0939\u093b\7\u008a\2\2\u093a\u0939\3\2\2\2\u093a\u093b\3\2\2\2\u093b"+
		"\u093c\3\2\2\2\u093c\u093d\7\u0084\2\2\u093d\u093f\3\2\2\2\u093e\u0921"+
		"\3\2\2\2\u093e\u0927\3\2\2\2\u093f\u0177\3\2\2\2\u0940\u0941\7\u0097\2"+
		"\2\u0941\u0942\7\u0099\2\2\u0942\u0943\6\u00bd\2\3\u0943\u0179\3\2\2\2"+
		"\u0944\u0945\7\u0099\2\2\u0945\u0946\7\u0099\2\2\u0946\u0947\6\u00be\3"+
		"\3\u0947\u017b\3\2\2\2\u0948\u0949\7\u0099\2\2\u0949\u094a\7\u00a5\2\2"+
		"\u094a\u094b\6\u00bf\4\3\u094b\u017d\3\2\2\2\u094c\u0955\5\u0180\u00c1"+
		"\2\u094d\u0955\5\u0182\u00c2\2\u094e\u0955\7z\2\2\u094f\u0955\7{\2\2\u0950"+
		"\u0955\7|\2\2\u0951\u0955\7}\2\2\u0952\u0955\7";
	private static final String _serializedATNSegment1 =
		"~\2\2\u0953\u0955\7H\2\2\u0954\u094c\3\2\2\2\u0954\u094d\3\2\2\2\u0954"+
		"\u094e\3\2\2\2\u0954\u094f\3\2\2\2\u0954\u0950\3\2\2\2\u0954\u0951\3\2"+
		"\2\2\u0954\u0952\3\2\2\2\u0954\u0953\3\2\2\2\u0955\u017f\3\2\2\2\u0956"+
		"\u0957\t\21\2\2\u0957\u0181\3\2\2\2\u0958\u095d\5\u0184\u00c3\2\u0959"+
		"\u095d\5\u0186\u00c4\2\u095a\u095d\7\177\2\2\u095b\u095d\7\u0080\2\2\u095c"+
		"\u0958\3\2\2\2\u095c\u0959\3\2\2\2\u095c\u095a\3\2\2\2\u095c\u095b\3\2"+
		"\2\2\u095d\u0183\3\2\2\2\u095e\u0962\7\u0081\2\2\u095f\u0961\5\u0188\u00c5"+
		"\2\u0960\u095f\3\2\2\2\u0961\u0964\3\2\2\2\u0962\u0960\3\2\2\2\u0962\u0963"+
		"\3\2\2\2\u0963\u0965\3\2\2\2\u0964\u0962\3\2\2\2\u0965\u0966\7\u00b6\2"+
		"\2\u0966\u0185\3\2\2\2\u0967\u096b\7\u0082\2\2\u0968\u096a\5\u018a\u00c6"+
		"\2\u0969\u0968\3\2\2\2\u096a\u096d\3\2\2\2\u096b\u0969\3\2\2\2\u096b\u096c"+
		"\3\2\2\2\u096c\u096e\3\2\2\2\u096d\u096b\3\2\2\2\u096e\u096f\7\u00b6\2"+
		"\2\u096f\u0187\3\2\2\2\u0970\u0975\5\u018c\u00c7\2\u0971\u0975\7\u00b2"+
		"\2\2\u0972\u0975\7\u00b4\2\2\u0973\u0975\7\u00b7\2\2\u0974\u0970\3\2\2"+
		"\2\u0974\u0971\3\2\2\2\u0974\u0972\3\2\2\2\u0974\u0973\3\2\2\2\u0975\u0189"+
		"\3\2\2\2\u0976\u097b\5\u018c\u00c7\2\u0977\u097b\7\u00b2\2\2\u0978\u097b"+
		"\7\u00b5\2\2\u0979\u097b\7\u00b8\2\2\u097a\u0976\3\2\2\2\u097a\u0977\3"+
		"\2\2\2\u097a\u0978\3\2\2\2\u097a\u0979\3\2\2\2\u097b\u018b\3\2\2\2\u097c"+
		"\u0981\5\36\20\2\u097d\u097e\7\u008a\2\2\u097e\u0980\5\36\20\2\u097f\u097d"+
		"\3\2\2\2\u0980\u0983\3\2\2\2\u0981\u097f\3\2\2\2\u0981\u0982\3\2\2\2\u0982"+
		"\u098a\3\2\2\2\u0983\u0981\3\2\2\2\u0984\u0986\7\u008b\2\2\u0985\u0987"+
		"\7\u00ba\2\2\u0986\u0985\3\2\2\2\u0987\u0988\3\2\2\2\u0988\u0986\3\2\2"+
		"\2\u0988\u0989\3\2\2\2\u0989\u098b\3\2\2\2\u098a\u0984\3\2\2\2\u098a\u098b"+
		"\3\2\2\2\u098b\u018d\3\2\2\2\u098c\u098d\t\22\2\2\u098d\u018f\3\2\2\2"+
		"\u098e\u098f\7\34\2\2\u098f\u0991\5\u01b4\u00db\2\u0990\u0992\5\u00e8"+
		"u\2\u0991\u0990\3\2\2\2\u0991\u0992\3\2\2\2\u0992\u0994\3\2\2\2\u0993"+
		"\u0995\5\u00ecw\2\u0994\u0993\3\2\2\2\u0994\u0995\3\2\2\2\u0995\u0997"+
		"\3\2\2\2\u0996\u0998\5\u00f0y\2\u0997\u0996\3\2\2\2\u0997\u0998\3\2\2"+
		"\2\u0998\u0999\3\2\2\2\u0999\u099b\5\u00fc\177\2\u099a\u099c\7\u008c\2"+
		"\2\u099b\u099a\3\2\2\2\u099b\u099c\3\2\2\2\u099c\u0191\3\2\2\2\u099d\u099f"+
		"\t\23\2\2\u099e\u099d\3\2\2\2\u099e\u099f\3\2\2\2\u099f\u09a0\3\2\2\2"+
		"\u09a0\u09a1\7a\2\2\u09a1\u09a3\5\u01b4\u00db\2\u09a2\u09a4\5\u00e8u\2"+
		"\u09a3\u09a2\3\2\2\2\u09a3\u09a4\3\2\2\2\u09a4\u09a6\3\2\2\2\u09a5\u09a7"+
		"\5\u013c\u009f\2\u09a6\u09a5\3\2\2\2\u09a6\u09a7\3\2\2\2\u09a7\u09a9\3"+
		"\2\2\2\u09a8\u09aa\5\u00f0y\2\u09a9\u09a8\3\2\2\2\u09a9\u09aa\3\2\2\2"+
		"\u09aa\u09ab\3\2\2\2\u09ab\u09ad\5\u013e\u00a0\2\u09ac\u09ae\7\u008c\2"+
		"\2\u09ad\u09ac\3\2\2\2\u09ad\u09ae\3\2\2\2\u09ae\u0193\3\2\2\2\u09af\u09b0"+
		"\7;\2\2\u09b0\u09b2\5\u01b4\u00db\2\u09b1\u09b3\5\u0148\u00a5\2\u09b2"+
		"\u09b1\3\2\2\2\u09b2\u09b3\3\2\2\2\u09b3\u09b5\3\2\2\2\u09b4\u09b6\5\u014e"+
		"\u00a8\2\u09b5\u09b4\3\2\2\2\u09b5\u09b6\3\2\2\2\u09b6\u09b8\3\2\2\2\u09b7"+
		"\u09b9\5\u00f0y\2\u09b8\u09b7\3\2\2\2\u09b8\u09b9\3\2\2\2\u09b9\u09ba"+
		"\3\2\2\2\u09ba\u09bc\5\u00fc\177\2\u09bb\u09bd\7\u008c\2\2\u09bc\u09bb"+
		"\3\2\2\2\u09bc\u09bd\3\2\2\2\u09bd\u0195\3\2\2\2\u09be\u09bf\7\'\2\2\u09bf"+
		"\u09c1\5\u01b4\u00db\2\u09c0\u09c2\5\u0156\u00ac\2\u09c1\u09c0\3\2\2\2"+
		"\u09c1\u09c2\3\2\2\2\u09c2\u09c3\3\2\2\2\u09c3\u09c5\5\u0158\u00ad\2\u09c4"+
		"\u09c6\7\u008c\2\2\u09c5\u09c4\3\2\2\2\u09c5\u09c6\3\2\2\2\u09c6\u0197"+
		"\3\2\2\2\u09c7\u09c8\7!\2\2\u09c8\u09c9\5\u0114\u008b\2\u09c9\u09cb\5"+
		"\u01b4\u00db\2\u09ca\u09cc\5\u0148\u00a5\2\u09cb\u09ca\3\2\2\2\u09cb\u09cc"+
		"\3\2\2\2\u09cc\u09cd\3\2\2\2\u09cd\u09cf\7\u0087\2\2\u09ce\u09d0\5\u011a"+
		"\u008e\2\u09cf\u09ce\3\2\2\2\u09cf\u09d0\3\2\2\2\u09d0\u09d1\3\2\2\2\u09d1"+
		"\u09d3\7\u0088\2\2\u09d2\u09d4\5\u00f0y\2\u09d3\u09d2\3\2\2\2\u09d3\u09d4"+
		"\3\2\2\2\u09d4\u09d5\3\2\2\2\u09d5\u09d6\7\u008c\2\2\u09d6\u0199\3\2\2"+
		"\2\u09d7\u09d8\7)\2\2\u09d8\u09e1\5\6\4\2\u09d9\u09da\5\u010e\u0088\2"+
		"\u09da\u09db\7\u008c\2\2\u09db\u09e2\3\2\2\2\u09dc\u09dd\5\u0116\u008c"+
		"\2\u09dd\u09de\7\u0083\2\2\u09de\u09df\5\u012e\u0098\2\u09df\u09e0\7\u0084"+
		"\2\2\u09e0\u09e2\3\2\2\2\u09e1\u09d9\3\2\2\2\u09e1\u09dc\3\2\2\2\u09e2"+
		"\u019b\3\2\2\2\u09e3\u09e4\5\u010e\u0088\2\u09e4\u09e5\7\u008c\2\2\u09e5"+
		"\u019d\3\2\2\2\u09e6\u09f4\5\u0116\u008c\2\u09e7\u09e8\7\u0083\2\2\u09e8"+
		"\u09e9\5\u0124\u0093\2\u09e9\u09ee\7\u0084\2\2\u09ea\u09eb\7\u0097\2\2"+
		"\u09eb\u09ec\5\u0112\u008a\2\u09ec\u09ed\7\u008c\2\2\u09ed\u09ef\3\2\2"+
		"\2\u09ee\u09ea\3\2\2\2\u09ee\u09ef\3\2\2\2\u09ef\u09f5\3\2\2\2\u09f0\u09f1"+
		"\5\u0178\u00bd\2\u09f1\u09f2\5L\'\2\u09f2\u09f3\7\u008c\2\2\u09f3\u09f5"+
		"\3\2\2\2\u09f4\u09e7\3\2\2\2\u09f4\u09f0\3\2\2\2\u09f5\u019f\3\2\2\2\u09f6"+
		"\u09f7\7\35\2\2\u09f7\u09f8\5\6\4\2\u09f8\u09f9\5\u010a\u0086\2\u09f9"+
		"\u09fa\7\u008c\2\2\u09fa\u01a1\3\2\2\2\u09fb\u09fc\7c\2\2\u09fc\u09fd"+
		"\7\u0085\2\2\u09fd\u09fe\5\u011a\u008e\2\u09fe\u0a07\7\u0086\2\2\u09ff"+
		"\u0a00\7\u0083\2\2\u0a00\u0a01\5\u0124\u0093\2\u0a01\u0a02\7\u0084\2\2"+
		"\u0a02\u0a08\3\2\2\2\u0a03\u0a04\5\u0178\u00bd\2\u0a04\u0a05\5L\'\2\u0a05"+
		"\u0a06\7\u008c\2\2\u0a06\u0a08\3\2\2\2\u0a07\u09ff\3\2\2\2\u0a07\u0a03"+
		"\3\2\2\2\u0a08\u01a3\3\2\2\2\u0a09\u0a0a\7\u0096\2\2\u0a0a\u0a0b\5\u01b4"+
		"\u00db\2\u0a0b\u0a0c\7\u0087\2\2\u0a0c\u0a0d\7\u0088\2\2\u0a0d\u0a0e\5"+
		"\u013a\u009e\2\u0a0e\u01a5\3\2\2\2\u0a0f\u0a10\5\u01b4\u00db\2\u0a10\u0a12"+
		"\7\u0087\2\2\u0a11\u0a13\5\u011a\u008e\2\u0a12\u0a11\3\2\2\2\u0a12\u0a13"+
		"\3\2\2\2\u0a13\u0a14\3\2\2\2\u0a14\u0a16\7\u0088\2\2\u0a15\u0a17\5\u0138"+
		"\u009d\2\u0a16\u0a15\3\2\2\2\u0a16\u0a17\3\2\2\2\u0a17\u0a18\3\2\2\2\u0a18"+
		"\u0a19\5\u013a\u009e\2\u0a19\u01a7\3\2\2\2\u0a1a\u0a1c\5\u01aa\u00d6\2"+
		"\u0a1b\u0a1d\5\u00e8u\2\u0a1c\u0a1b\3\2\2\2\u0a1c\u0a1d\3\2\2\2\u0a1d"+
		"\u0a1e\3\2\2\2\u0a1e\u0a20\7\u0087\2\2\u0a1f\u0a21\5\u011a\u008e\2\u0a20"+
		"\u0a1f\3\2\2\2\u0a20\u0a21\3\2\2\2\u0a21\u0a22\3\2\2\2\u0a22\u0a24\7\u0088"+
		"\2\2\u0a23\u0a25\5\u00f0y\2\u0a24\u0a23\3\2\2\2\u0a24\u0a25\3\2\2\2\u0a25"+
		"\u0a2b\3\2\2\2\u0a26\u0a2c\5\u0118\u008d\2\u0a27\u0a28\5\u0178\u00bd\2"+
		"\u0a28\u0a29\5L\'\2\u0a29\u0a2a\7\u008c\2\2\u0a2a\u0a2c\3\2\2\2\u0a2b"+
		"\u0a26\3\2\2\2\u0a2b\u0a27\3\2\2\2\u0a2c\u01a9\3\2\2\2\u0a2d\u0a33\5\u01b4"+
		"\u00db\2\u0a2e\u0a2f\5\u01b4\u00db\2\u0a2f\u0a30\7\u009b\2\2\u0a30\u0a31"+
		"\5\u01b4\u00db\2\u0a31\u0a33\3\2\2\2\u0a32\u0a2d\3\2\2\2\u0a32\u0a2e\3"+
		"\2\2\2\u0a33\u0a3b\3\2\2\2\u0a34\u0a36\5\30\r\2\u0a35\u0a34\3\2\2\2\u0a35"+
		"\u0a36\3\2\2\2\u0a36\u0a37\3\2\2\2\u0a37\u0a38\7\u0089\2\2\u0a38\u0a3a"+
		"\5\u01b4\u00db\2\u0a39\u0a35\3\2\2\2\u0a3a\u0a3d\3\2\2\2\u0a3b\u0a39\3"+
		"\2\2\2\u0a3b\u0a3c\3\2\2\2\u0a3c\u01ab\3\2\2\2\u0a3d\u0a3b\3\2\2\2\u0a3e"+
		"\u0a3f\7K\2\2\u0a3f\u0a40\5\u0134\u009b\2\u0a40\u0a42\7\u0087\2\2\u0a41"+
		"\u0a43\79\2\2\u0a42\u0a41\3\2\2\2\u0a42\u0a43\3\2\2\2\u0a43\u0a44\3\2"+
		"\2\2\u0a44\u0a4a\5\u01ae\u00d8\2\u0a45\u0a47\7\u008a\2\2\u0a46\u0a48\7"+
		"9\2\2\u0a47\u0a46\3\2\2\2\u0a47\u0a48\3\2\2\2\u0a48\u0a49\3\2\2\2\u0a49"+
		"\u0a4b\5\u01ae\u00d8\2\u0a4a\u0a45\3\2\2\2\u0a4a\u0a4b\3\2\2\2\u0a4b\u0a4c"+
		"\3\2\2\2\u0a4c\u0a52\7\u0088\2\2\u0a4d\u0a53\5\u013a\u009e\2\u0a4e\u0a4f"+
		"\5\u0178\u00bd\2\u0a4f\u0a50\5L\'\2\u0a50\u0a51\7\u008c\2\2\u0a51\u0a53"+
		"\3\2\2\2\u0a52\u0a4d\3\2\2\2\u0a52\u0a4e\3\2\2\2\u0a53\u01ad\3\2\2\2\u0a54"+
		"\u0a55\5\6\4\2\u0a55\u0a58\5\u01b4\u00db\2\u0a56\u0a57\7\u0097\2\2\u0a57"+
		"\u0a59\5\36\20\2\u0a58\u0a56\3\2\2\2\u0a58\u0a59\3\2\2\2\u0a59\u01af\3"+
		"\2\2\2\u0a5a\u0a5c\7\u0087\2\2\u0a5b\u0a5d\5\32\16\2\u0a5c\u0a5b\3\2\2"+
		"\2\u0a5c\u0a5d\3\2\2\2\u0a5d\u0a5e\3\2\2\2\u0a5e\u0a5f\7\u0088\2\2\u0a5f"+
		"\u01b1\3\2\2\2\u0a60\u0a62\7\u0087\2\2\u0a61\u0a63\5\32\16\2\u0a62\u0a61"+
		"\3\2\2\2\u0a62\u0a63\3\2\2\2\u0a63\u0a64\3\2\2\2\u0a64\u0a66\7\u0088\2"+
		"\2\u0a65\u0a67\5Z.\2\u0a66\u0a65\3\2\2\2\u0a66\u0a67\3\2\2\2\u0a67\u01b3"+
		"\3\2\2\2\u0a68\u0a69\t\24\2\2\u0a69\u01b5\3\2\2\2\u015f\u01b7\u01ba\u01bd"+
		"\u01c2\u01c6\u01cc\u01cf\u01d4\u01d8\u01df\u01e1\u01e9\u01f1\u01f7\u01fb"+
		"\u0200\u020a\u0212\u021c\u0222\u0225\u0229\u0231\u0236\u0240\u024d\u0255"+
		"\u025b\u025d\u0264\u026c\u0274\u027c\u0284\u028c\u0296\u0298\u029e\u02a3"+
		"\u02ab\u02b3\u02bb\u02bd\u02c0\u02c7\u02cc\u02d3\u02d7\u02d9\u02f5\u02f9"+
		"\u02fe\u0302\u030a\u030d\u0312\u0316\u031a\u0320\u032e\u0334\u0340\u0344"+
		"\u0349\u034d\u0353\u0358\u0361\u036a\u037c\u037f\u0384\u0387\u0396\u039c"+
		"\u03a0\u03a6\u03ab\u03ae\u03b6\u03be\u03c9\u03ce\u03d3\u03d5\u03de\u03e6"+
		"\u03ed\u03f5\u03f9\u0402\u0407\u0409\u0412\u041a\u041e\u0423\u0425\u042a"+
		"\u042e\u0435\u043d\u043f\u0443\u0446\u0449\u044c\u0454\u045e\u046f\u0476"+
		"\u047a\u0484\u0489\u0490\u0499\u049e\u04a5\u04b0\u04b4\u04bf\u04c7\u04cc"+
		"\u04d5\u04de\u04e7\u04ed\u04f2\u04f6\u04fa\u04fe\u0502\u0504\u050b\u0513"+
		"\u0520\u052a\u0540\u0544\u0548\u054d\u0561\u0566\u056b\u0572\u0575\u058b"+
		"\u0597\u059b\u05a3\u05ab\u05b2\u05b6\u05bb\u05be\u05c3\u05cb\u05d0\u05d7"+
		"\u05dd\u05e5\u05ed\u05f0\u05f7\u05fe\u0602\u0605\u060b\u060f\u0615\u0623"+
		"\u062d\u062f\u0636\u063b\u063e\u0641\u0648\u0652\u0663\u0668\u066c\u066f"+
		"\u0672\u0679\u067f\u0687\u068d\u0697\u069f\u06a5\u06b0\u06b4\u06b6\u06bb"+
		"\u06bf\u06c6\u06cf\u06d6\u06d9\u06dc\u06e0\u06e5\u06f2\u06fc\u0703\u070f"+
		"\u0716\u0722\u0728\u072c\u0730\u0736\u073c\u073e\u0745\u0749\u074c\u0750"+
		"\u075a\u075d\u0764\u0767\u076c\u0771\u0773\u0776\u0779\u077f\u0782\u078e"+
		"\u0795\u0798\u07a2\u07a5\u07ab\u07c6\u07d3\u07d9\u07e2\u07e8\u07eb\u07f3"+
		"\u07f7\u07fd\u0803\u0809\u0814\u0818\u081a\u0824\u082a\u082d\u083a\u0840"+
		"\u0843\u0846\u084d\u0852\u0856\u085a\u086b\u086e\u0873\u0877\u087b\u0884"+
		"\u0887\u088c\u0890\u0895\u0899\u089b\u08a6\u08aa\u08ac\u08b1\u08b6\u08bd"+
		"\u08c3\u08c8\u08ce\u08d2\u08d8\u08df\u08e9\u08ec\u08ef\u08f4\u08fa\u08fe"+
		"\u0900\u0907\u090e\u0916\u091a\u0929\u092d\u0936\u093a\u093e\u0954\u095c"+
		"\u0962\u096b\u0974\u097a\u0981\u0988\u098a\u0991\u0994\u0997\u099b\u099e"+
		"\u09a3\u09a6\u09a9\u09ad\u09b2\u09b5\u09b8\u09bc\u09c1\u09c5\u09cb\u09cf"+
		"\u09d3\u09e1\u09ee\u09f4\u0a07\u0a12\u0a16\u0a1c\u0a20\u0a24\u0a2b\u0a32"+
		"\u0a35\u0a3b\u0a42\u0a47\u0a4a\u0a52\u0a58\u0a5c\u0a62\u0a66";
	public static final String _serializedATN = Utils.join(
		new String[] {
			_serializedATNSegment0,
			_serializedATNSegment1
		},
		""
	);
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}