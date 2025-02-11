package jext.math.exception;

import jext.math.exception.util.Localizable;

import java.util.Locale;

public enum LocalizedFormats2 implements Localizable {
    OFFSPRING_RATE("offspring rate ({0})"),
    ;

    private final String sourceFormat;

    LocalizedFormats2(final String sourceFormat) {
        this.sourceFormat = sourceFormat;
    }


    @Override
    public String getSourceString() {
        return sourceFormat;
    }

    @Override
    public String getLocalizedString(Locale locale) {
        return sourceFormat;
    }
}
