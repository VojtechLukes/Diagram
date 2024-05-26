package comparators;

import app.Competitor;
import java.util.Comparator;

/**
 * Komparátor pro porovnání soutěžících podle jejich celkového ELO skóre.
 */
public class AllElosComparator implements Comparator<Competitor> {

    /**
     * Porovnává dva soutěžící podle jejich celkového ELO skóre.
     *
     * @param o1 První soutěžící.
     * @param o2 Druhý soutěžící.
     * @return Záporné číslo, pokud první soutěžící má nižší celkové ELO skóre
     * než druhý; kladné číslo, pokud má vyšší; 0, pokud mají stejné celkové ELO
     * skóre.
     */
    @Override
    public int compare(Competitor o1, Competitor o2) {
        return o2.getAllElos() - o1.getAllElos();
    }
}
