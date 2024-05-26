package app;

import enums.*;

/**
 * Třída Tournament definuje turnaj.
 */
public class Tournament {

    /**
     * Vrátí pohlaví a věkovou kategorii turnaje.
     *
     * @return Pohlaví a věková kategorie turnaje.
     */
    public TournamentGender getTournamentGender() {
        return tournamentGender;
    }

    /**
     * Vrátí název turnaje.
     *
     * @return Název turnaje.
     */
    public String getName() {
        return name;
    }

    /**
     * Vrátí typ ELO systému použitého v turnaji.
     *
     * @return Typ ELO systému použitého v turnaji.
     */
    public EloSystem getEloSystem() {
        return eloSystem;
    }

    /**
     * Vrátí pohlaví a věkovou kategorii turnaje ve formě textového řetězce.
     *
     * @return Pohlaví a věková kategorie turnaje.
     */
    public String getGenderAgeCategory() {
        return genderAgeCategory;
    }

    /**
     * Vrátí kategorii ELO použitou v turnaji ve formě textového řetězce.
     *
     * @return Kategorie ELO použitá v turnaji.
     */
    public String getEloCategory() {
        return eloCategory;
    }

    private TournamentGender tournamentGender;
    private String name;
    private EloSystem eloSystem;
    String genderAgeCategory;
    String eloCategory;

    /**
     * Konstruktor třídy Tournament.
     *
     * @param name Název turnaje.
     * @param genderAgeCategory Pohlaví a věková kategorie turnaje.
     * @param eloCategory Kategorie ELO použitá v turnaji.
     */
    public Tournament(String name, String genderAgeCategory, String eloCategory) {
        this.name = name;
        this.genderAgeCategory = genderAgeCategory;
        this.eloCategory = eloCategory;
        switch (genderAgeCategory) {
            case "U15" -> {
                this.tournamentGender = TournamentGender.U15;
                break;
            }
            case "U20" -> {
                this.tournamentGender = TournamentGender.U20;
                break;
            }
            case "UX" -> {
                this.tournamentGender = TournamentGender.UX;
                break;
            }
            case "F15" -> {
                this.tournamentGender = TournamentGender.F15;
                break;
            }
            case "F20" -> {
                this.tournamentGender = TournamentGender.F20;
                break;
            }
            case "FX" -> {
                this.tournamentGender = TournamentGender.FX;
                break;
            }
            case "M15" -> {
                this.tournamentGender = TournamentGender.M15;
                break;
            }
            case "M20" -> {
                this.tournamentGender = TournamentGender.M20;
                break;
            }
            case "MX" -> {
                this.tournamentGender = TournamentGender.MX;
                break;
            }
            default -> {
                new IllegalArgumentException("Neplatný vstup pro turnaj.");
                break;
            }
        }
        switch (eloCategory) {
            case "fideBlitzElo" -> {
                this.eloSystem = EloSystem.fideBlitzElo;
            }
            case "fideStdElo" -> {
                this.eloSystem = EloSystem.fideStdElo;
            }
            case "fideRapidElo" -> {
                this.eloSystem = EloSystem.fideRapidElo;
            }
            case "stdBlitz" -> {
                this.eloSystem = EloSystem.stdBlitz;
            }
            case "rapidStd" -> {
                this.eloSystem = EloSystem.rapidStd;
            }
            case "rapidBlitz" -> {
                this.eloSystem = EloSystem.rapidBlitz;
            }
            case "allElos" -> {
                this.eloSystem = EloSystem.allElos;
            }
            default -> {
                new IllegalArgumentException("Neplatný vstup pro turnaj.");
                break;
            }
        }
    }
}
