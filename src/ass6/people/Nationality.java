package ass6.people;

import java.util.Locale;

public enum Nationality {

    POLISH(new Locale("pl-PL")),
    UKRAINIAN(new Locale("uk-UA")),
    BELARUSSIAN(new Locale("be-BY")),
    SLOVAK(new Locale("sk-SK")),
    LITHUANIAN(new Locale("lt-LT")),
    LATVIAN(new Locale("lv-LV")),
    BRITISH(Locale.UK),
    INDIAN(new Locale("hi-IN")),
    CHINESE(Locale.CHINA),
    VIETNAMESE(new Locale("vi-VN"));

    private final Locale locale;

    Nationality(Locale locale) {
        this.locale = locale;
    }

    public Locale getLocale() {
        return locale;
    }

}
