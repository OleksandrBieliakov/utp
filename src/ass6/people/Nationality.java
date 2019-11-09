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

    private static final Random RANDOM = new Random();
    private static final Nationality[] NATIONALITIES = Nationality.values();
    private static final int NATIONALITIES_LENGTH = NATIONALITIES.length;

    private final Locale locale;

    Nationality(Locale locale) {
        this.locale = locale;
    }

    public Locale getLocale() {
        return locale;
    }

    public static Nationality generateNationality() {
        return NATIONALITIES[RANDOM.nextInt(NATIONALITIES_LENGTH)];
    }

}
