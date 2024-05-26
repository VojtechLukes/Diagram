package comparators;

import app.Competitor;
import java.util.Comparator;

/**
 * Komparátor pro porovnání soutěžících podle jejich ELO skóre v blitz šachu.
 */
public class BlitzComparator implements Comparator<Competitor> {

    /**
     * Porovnává dva soutěžící podle jejich ELO skóre v blitz šachu.
     *
     * @param o1 První soutěžící.
     * @param o2 Druhý soutěžící.
     * @return Záporné číslo, pokud první soutěžící má nižší ELO skóre v blitz
     * šachu než druhý; kladné číslo, pokud má vyšší; 0, pokud mají stejné ELO
     * skóre v blitz šachu.
     */
    @Override
    public int compare(Competitor o1, Competitor o2) {
        return o2.getFideBlitzElo() - o1.getFideBlitzElo();
    }
}
