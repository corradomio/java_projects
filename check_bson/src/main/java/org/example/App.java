package org.example;

import jext.util.Arrays;
import jext.util.Maps;
import org.yuanheng.cookjson.CookJsonProvider;

import javax.json.JsonObjectBuilder;
import javax.json.spi.JsonProvider;
import java.util.Map;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Map<String, Object> map = Maps.asMap(
            "int", 1,
            "float", 2.0,
            "string", "s",
            "int[]", Arrays.asArray(1,2,3,4),
            "float[]", Arrays.asArray(1.,2.,3.,4.),
            "string[]", Arrays.asArray("I", "II", "III", "IV")
        );

        JsonProvider p = new CookJsonProvider();
        JsonObjectBuilder job = p.createObjectBuilder();

        System.out.println( "Hello World!" );
    }
}
