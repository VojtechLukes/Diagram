package enums;

import java.time.LocalDate;

/**
 * Výčtový typ reprezentující kategorie soutěžících podle věku a pohlaví.
 */
public enum CompetitorCategory {
    M15("M", "M15", 0, 15),
    M20("M", "M20", 16, 20),
    MX("M", "MX", 21, Integer.MAX_VALUE),
    F15("F", "F15", 0, 15),
    F20("F", "F20", 16, 20),
    FX("F", "FX", 21, Integer.MAX_VALUE);

    /**
     * Vrátí pohlaví kategorie soutěžícího.
     *
     * @return Pohlaví kategorie soutěžícího.
     */
    public String getGender() {
        return gender;
    }

    /**
     * Vrátí název kategorie soutěžícího.
     *
     * @return Název kategorie soutěžícího.
     */
    public String getName() {
        return name;
    }

    /**
     * Vrátí minimální věk pro danou kategorii.
     *
     * @return Minimální věk pro danou kategorii.
     */
    public int getMinAge() {
        return minAge;
    }

    /**
     * Vrátí maximální věk pro danou kategorii.
     *
     * @return Maximální věk pro danou kategorii.
     */
    public int getMaxAge() {
        return maxAge;
    }

    private String name;
    private int minAge;
    private int maxAge;
    private String gender;

    private CompetitorCategory(String gender, String name, int minAge, int maxAge) {
        this.gender = gender;
        this.name = name;
        this.minAge = minAge;
        this.maxAge = maxAge;
    }

    /**
     * Vrátí kategorii soutěžícího na základě jeho roku narození a pohlaví.
     *
     * @param birthYear Rok narození soutěžícího.
     * @param gender Pohlaví soutěžícího.
     * @return Kategorie soutěžícího.
     * @throws IllegalArgumentException Pokud soutěžící nepatří do žádné
     * kategorie.
     */
    public static CompetitorCategory getCategory(int birthYear, String gender) {
        int age = LocalDate.now().getYear() - birthYear;
        CompetitorCategory[] categories = CompetitorCategory.values();
        for (CompetitorCategory category : categories) {
            if (age >= category.minAge && age <= category.maxAge && gender.equals(category.gender)) {
                return category;
            }
        }
        throw new IllegalArgumentException("Nepatří do žádné kategorie.");
    }
}
