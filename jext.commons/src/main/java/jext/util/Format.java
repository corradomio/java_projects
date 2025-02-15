package jext.util;

import jext.util.logging.Logger;

import java.util.Map;

public class Format {

    private static final Logger logger = Logger.getLogger(Format.class) ;

    // ----------------------------------------------------------------------
    // Replace
    // ----------------------------------------------------------------------
    //  ${name}
    //  %[argument_index$][flags][width][.precision]conversion
    //  %[argument_name$][flags][width][.precision]conversion
    //
    //  conversion ::= s, d, f, tX
    //  'b', 'B'    general         If the argument arg is null, then the result is "false". If arg is a boolean or Boolean, then the result is the string returned by String.valueOf(arg). Otherwise, the result is "true".
    // 'h', 'H'     general         If the argument arg is null, then the result is "null". Otherwise, the result is obtained by invoking Integer.toHexString(arg.hashCode()).
    // 's', 'S'     general         If the argument arg is null, then the result is "null". If arg implements Formattable, then arg.formatTo is invoked. Otherwise, the result is obtained by invoking arg.toString().
    // 'c', 'C'     character       The result is a Unicode character
    // 'd'          integral        The result is formatted as a decimal integer
    // 'o'          integral        The result is formatted as an octal integer
    // 'x', 'X'     integral        The result is formatted as a hexadecimal integer
    // 'e', 'E'     floating point  The result is formatted as a decimal number in computerized scientific notation
    // 'f'          floating point  The result is formatted as a decimal number
    // 'g', 'G'     floating point  The result is formatted using computerized scientific notation or decimal format, depending on the precision and the value after rounding.
    // 'a', 'A'     floating point  The result is formatted as a hexadecimal floating-point number with a significand and an exponent. This conversion is not supported for the BigDecimal type despite the latter's being in the floating point argument category.
    // 't', 'T'     date/time       Prefix for date and time conversion characters. See Date/Time Conversions.
    // '%'          percent         The result is a literal '%' ('\u0025')
    // 'n'          line separator     The result is the platform-specific line separator

    /**
     * Inside the string 'template', replace the variables '${varname}' with the value
     * presents in 'params'.
     *
     * If 'varname' is not present in 'params', it will not replaces
     *
     * @param template template string
     * @param params parameters
     * @return
     */
    public static String replace(String template, Map<String, Object> params) {
        String t = template;
        int pos = t.indexOf("${");
        while (pos != -1) {
            int end = t.indexOf('}', pos);
            if (end == -1) {
                logger.errorf(String.format("Missing a '}' in '%s'", template));
                break;
            }

            String pname = t.substring(pos+2, end);
            if (params.containsKey(pname)) {
                Object v = params.get(pname);
                String value = v != null ? v.toString() : "<null>";

                t = t.substring(0, pos) + value + t.substring(end+1);
            }
            else {
                pos = end;
                logger.errorf(String.format("Parameter ${%s} not resolved in '%s'", pname, template));
            }

            pos = t.indexOf("${", pos);
        }

        return t;
    }

    // ----------------------------------------------------------------------
    // Parametric format
    // ----------------------------------------------------------------------
    // Replace "${name}" with the value in the dictionary

    public static String format(String template, Map<String, Object> params) {
        return replace(template, params);
    }

}
