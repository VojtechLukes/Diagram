package app;

import java.time.LocalDate;
import enums.*;

/**
 * Třída Competitor představuje soutěžícího v turnaji.
 */
public class Competitor {

    /**
     * Vrátí celé jméno soutěžícího.
     *
     * @return Celé jméno soutěžícího.
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * Vrátí věk soutěžícího.
     *
     * @return Věk soutěžícího.
     */
    public int getAge() {
        return age;
    }

    /**
     * Vrátí FIDE ID soutěžícího.
     *
     * @return FIDE ID soutěžícího.
     */
    public int getFideId() {
        return fideId;
    }

    /**
     * Vrátí standardní ELO rating soutěžícího.
     *
     * @return Standardní ELO rating soutěžícího.
     */
    public int getFideStdElo() {
        return fideStdElo;
    }

    /**
     * Vrátí rychlý ELO rating soutěžícího.
     *
     * @return Rychlý ELO rating soutěžícího.
     */
    public int getFideRapidElo() {
        return fideRapidElo;
    }

    /**
     * Vrátí ELO rating v blitzu soutěžícího.
     *
     * @return ELO rating v blitzu soutěžícího.
     */
    public int getFideBlitzElo() {
        return fideBlitzElo;
    }

    /**
     * Vrátí součet standardního a rychlého ELO ratingu soutěžícího.
     *
     * @return Součet standardního a rychlého ELO ratingu soutěžícího.
     */
    public int getStdRapid() {
        return stdRapid;
    }

    /**
     * Vrátí součet rychlého a blitz ELO ratingu soutěžícího.
     *
     * @return Součet rychlého a blitz ELO ratingu soutěžícího.
     */
    public int getRapidBlitz() {
        return rapidBlitz;
    }

    /**
     * Vrátí součet standardního a blitz ELO ratingu soutěžícího.
     *
     * @return Součet standardního a blitz ELO ratingu soutěžícího.
     */
    public int getStdBlitz() {
        return stdBlitz;
    }

    /**
     * Vrátí rok narození soutěžícího.
     *
     * @return Rok narození soutěžícího.
     */
    public int getBirthYear() {
        return birthYear;
    }

    /**
     * Vrátí součet všech ELO ratingů soutěžícího.
     *
     * @return Součet všech ELO ratingů soutěžícího.
     */
    public int getAllElos() {
        return allElos;
    }

    /**
     * Vrátí kategorii soutěžícího podle pohlaví a věku.
     *
     * @return Kategorie soutěžícího.
     */
    public CompetitorCategory getGenderAgeCategory() {
        return genderAgeCategory;
    }

    /**
     * Vrátí pohlaví soutěžícího.
     *
     * @return Pohlaví soutěžícího.
     */
    public String getGender() {
        return gender;
    }

    private int birthYear;
    private int age;
    private int fideId;
    private int fideStdElo;
    private int fideRapidElo;
    private int fideBlitzElo;
    private int stdRapid;
    private int rapidBlitz;
    private int stdBlitz;
    private int allElos;
    private CompetitorCategory genderAgeCategory;
    private String fullName;
    String gender;

    /**
     * Konstruktor třídy Competitor.
     *
     * @param fullName Celé jméno soutěžícího.
     * @param birthYear Rok narození soutěžícího.
     * @param fideId FIDE ID soutěžícího.
     * @param fideStdElo Standardní ELO rating soutěžícího.
     * @param fideRapidElo Rychlý ELO rating soutěžícího.
     * @param fideBlitzElo ELO rating v blitzu soutěžícího.
     * @param gender Pohlaví soutěžícího.
     */
    public Competitor(String fullName, int birthYear, int fideId, int fideStdElo, int fideRapidElo, int fideBlitzElo, String gender) {
        this.fullName = fullName;
        this.gender = gender;
        this.birthYear = birthYear;
        this.age = LocalDate.now().getYear() - this.birthYear; //použití timeAPI;
        this.fideId = fideId;
        this.fideStdElo = fideStdElo;
        this.fideBlitzElo = fideBlitzElo;
        this.fideRapidElo = fideRapidElo;
        this.stdRapid = fideRapidElo + fideStdElo;
        this.stdBlitz = fideStdElo + fideBlitzElo;
        this.rapidBlitz = fideRapidElo + fideBlitzElo;
        this.allElos = fideBlitzElo + fideRapidElo + fideStdElo;
        this.genderAgeCategory = CompetitorCategory.getCategory(birthYear, gender);
    }
}
