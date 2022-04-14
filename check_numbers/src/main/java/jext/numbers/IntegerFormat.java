package jext.numbers;

public class IntegerFormat {

    private static final String ZERO = "0";
    private static final String ONE = "1";

    private static final String[][] ROMAN = {
        {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"},   // 1
        {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"},   // 10
        {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"},   // 100
        {"", "M", "MM", "MMM",       "|MV|", "|V|", "|VM|", "|VMM|", "|VMMM|", "|MI|"}, // 1000
        {"", "|I|", "|II|", "|III|", "|IV|", "|V|", "|VI|", "|VII|", "|VIII|", "|IX|"}, // 10_000
        {"", "|X|", "|XX|", "|XXX|", "|XL|", "|L|", "|LX|", "|LXX|", "|LXXX|", "|XC|"}, // 100_000
        {"", "|C|", "|CC|", "|CCC|", "|CD|", "|D|", "|DC|", "|DCC|", "|DCCC|", "|CM|"}, // 1000_000
        {"", "|M|", "|MM|", "|MMM|", "[MV]", "[V]", "[VM]", "[VMM]", "[VMMM]", "[MI]"}, // 10_000_000
        {"", "[I]", "[II]", "[III]", "[IV]", "[V]", "[VI]", "[VII]", "[VIII]", "[IX]"}, // 100_000_000
        {"", "[X]", "[XX]", "[XXX]", "[XL]", "[L]", "[LX]", "[LXX]", "[LXXX]", "[XC]"}, // 1000_000_000
        {"", "[C]", "[CC]", "[CCC]", "[CD]", "[D]", "[DC]", "[DCC]", "[DCCC]", "[CM]"}, // 10_000_000_000
        {"", "[M]", "[MM]", "[MMM]", "{MV}", "{V}", "{VM}", "{VMM}", "{VMMM}", "{MI}"}, // 100_000_000_000
        {"", "{I}", "{II}", "{III}", "{IV}", "{V}", "{VI}", "{VII}", "{VIII}", "{IX}"}, // 1000_000_000_000
        {"", "{X}", "{XX}", "{XXX}", "{XL}", "{L}", "{LX}", "{LXX}", "{LXXX}", "{XC}"}, // 10_000_000_000_000
        {"", "{C}", "{CC}", "{CCC}", "{CD}", "{D}", "{DC}", "{DCC}", "{DCCC}", "{CM}"}, // 100_000_000_000_000
        {"", "{M}", "{MM}", "{MMM}", "(MV)", "(V)", "(VM)", "(VMM)", "(VMMM)", "(MX)"}, // 1000_000_000_000_000
    };

    private static final String[][] ITALIAN = {
            {"","uno","due","tre","quattro","cinque","sei", "sette","otto","nove",
             "dieci","undici","dodici","tredici","quattordici","quindici","sedici","diciasette","diciotto","dicianove"},
            {"", "", "vent", "trent","quarant","cinquant","sessant","settant","ottant","novant"},
            {"", "", "venti", "trenta","quaranta","cinquanta","sessanta","settanta","ottanta","novanta"},
            { "cento", "cento"},
            { "mille", "mila"},
            {"milione", "milioni"},
            {"miliardo","miliardi"}
    };

    public static String roman(int i) {
        String r = "";
        if (i == 0)
            return ZERO;

        int f = 0;
        while (i > 0) {
            int d = i%10;
            r = ROMAN[f][d] + r;
            i = i/10;
            f += 1;
        }

        return r;
    }


    public static String italian(int i){
        if (i == 0)
            return "zero";
        else
            return _ita(i);
    }

    private static String _ita(int i) {
        if (i == 0)
            return "";

        if (i<20)
            return ITALIAN[0][i];
        if (i < 100) {
            int d0 = i%10;
            int d1 = i/10;
            if (d0 == 1)
                return ITALIAN[1][d1]+ITALIAN[0][d0];
            else
                return ITALIAN[2][d1]+ITALIAN[0][d0];
        }
        if (i<1000) {
            int d10 = i%100;
            int d2 = i/100;

            if (d2 == 1)
                return ITALIAN[3][0] + _ita(d10);
            else
                return ITALIAN[0][d2] + ITALIAN[3][0] + _ita(d10);
        }

        return "boh!";
    }

}
