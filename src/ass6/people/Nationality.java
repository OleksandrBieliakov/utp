package ass6.people;

import java.util.Locale;
import java.util.Random;

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

    private Locale locale;

    Nationality(Locale locale) {
        this.locale = locale;
    }

    public Locale getLocale() {
        return locale;
    }

    public static Nationality generateNationality() {
        Nationality[] nationalities = Nationality.values();
        return nationalities[new Random().nextInt(nationalities.length)];
    }

}
