package comparators;

import app.Competitor;
import java.util.Comparator;

/**
 * Komparátor pro porovnání soutěžících podle jejich ELO skóre ve standardním
 * šachu.
 */
public class StdComparator implements Comparator<Competitor> {

    /**
     * Porovnává dva soutěžící podle jejich ELO skóre ve standardním šachu.
     *
     * @param o1 První soutěžící.
     * @param o2 Druhý soutěžící.
     * @return Záporné číslo, pokud první soutěžící má nižší ELO skóre ve
     * standardním šachu než druhý; kladné číslo, pokud má vyšší; 0, pokud mají
     * stejné ELO skóre ve standardním šachu.
     */
    @Override
    public int compare(Competitor o1, Competitor o2) {
        return o2.getFideStdElo() - o1.getFideStdElo();
    }
}
