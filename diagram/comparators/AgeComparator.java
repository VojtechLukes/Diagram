package comparators;

import app.Competitor;
import java.util.Comparator;

/**
 * Komparátor pro porovnání soutěžících podle jejich věku.
 */
public class AgeComparator implements Comparator<Competitor> {

    /**
     * Porovnává dva soutěžící podle jejich věku (roku narození).
     *
     * @param o1 První soutěžící.
     * @param o2 Druhý soutěžící.
     * @return Záporné číslo, pokud první soutěžící je starší než druhý; kladné
     * číslo, pokud je mladší; 0, pokud mají stejný věk.
     */
    @Override
    public int compare(Competitor o1, Competitor o2) {
        return o2.getBirthYear() - o1.getBirthYear();
    }
}
