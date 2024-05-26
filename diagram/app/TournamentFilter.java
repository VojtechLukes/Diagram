package app;

import comparators.*;
import enums.*;
import java.util.ArrayList;

/**
 * Třída TournamentFilter poskytuje metody pro filtrování soutěžících podle
 * pohlaví a věkových kategorií a typu ELO systému.
 */
public class TournamentFilter {

    /**
     * Filtrování soutěžících podle pohlaví a věkových kategorií a typu ELO
     * systému.
     *
     * @param competitors Seznam soutěžících.
     * @param t Typ pohlaví a věkové kategorie.
     * @param e Typ ELO systému.
     * @return Seznam soutěžících po filtraci.
     * @throws IllegalArgumentException Pokud je zadaný neplatný vstupní soubor.
     */
    public static ArrayList<Competitor> filter(ArrayList<Competitor> competitors, TournamentGender t, EloSystem e) {
        ArrayList<Competitor> filteredList = new ArrayList<>();
        switch (t) {
            case U15 -> {
                for (Competitor competitor : competitors) {
                    if (competitor.getGenderAgeCategory().equals(CompetitorCategory.F15) || competitor.getGenderAgeCategory().equals(CompetitorCategory.M15)) {
                        filteredList.add(competitor);
                    }
                }
            }
            case U20 -> {
                for (Competitor competitor : competitors) {
                    if (competitor.getGenderAgeCategory().equals(CompetitorCategory.F20) || competitor.getGenderAgeCategory().equals(CompetitorCategory.M20)) {
                        filteredList.add(competitor);
                    }
                }
            }
            case UX -> {
                for (Competitor competitor : competitors) {
                    if (competitor.getGenderAgeCategory().equals(CompetitorCategory.FX) || competitor.getGenderAgeCategory().equals(CompetitorCategory.MX)) {
                        filteredList.add(competitor);
                    }
                }
            }
            case F15 -> {
                for (Competitor competitor : competitors) {
                    if (competitor.getGenderAgeCategory().equals(CompetitorCategory.F15)) {
                        filteredList.add(competitor);
                    }
                }
            }
            case F20 -> {
                for (Competitor competitor : competitors) {
                    if (competitor.getGenderAgeCategory().equals(CompetitorCategory.F20)) {
                        filteredList.add(competitor);
                    }
                }
            }
            case FX -> {
                for (Competitor competitor : competitors) {
                    if (competitor.getGenderAgeCategory().equals(CompetitorCategory.FX)) {
                        filteredList.add(competitor);
                    }
                }
            }
            case M15 -> {
                for (Competitor competitor : competitors) {
                    if (competitor.getGenderAgeCategory().equals(CompetitorCategory.M15)) {
                        filteredList.add(competitor);
                    }
                }
            }
            case M20 -> {
                for (Competitor competitor : competitors) {
                    if (competitor.getGenderAgeCategory().equals(CompetitorCategory.M20)) {
                        filteredList.add(competitor);
                    }
                }
            }
            case MX -> {
                for (Competitor competitor : competitors) {
                    if (competitor.getGenderAgeCategory().equals(CompetitorCategory.MX)) {
                        filteredList.add(competitor);
                    }
                }
            }
            default ->
                throw new IllegalArgumentException("Neplatný vstupní soubor");
        }
        switch (e) {
            case fideStdElo ->
                filteredList.sort(new StdComparator());
            case fideBlitzElo ->
                filteredList.sort(new BlitzComparator());
            case fideRapidElo ->
                filteredList.sort(new RapidComparator());
            case rapidStd ->
                filteredList.sort(new RapidStdComparator());
            case rapidBlitz ->
                filteredList.sort(new RapidBlitzComparator());
            case stdBlitz ->
                filteredList.sort(new StdBlitzComparator());
            case allElos ->
                filteredList.sort(new AllElosComparator());
        }
        return filteredList;
    }
}
