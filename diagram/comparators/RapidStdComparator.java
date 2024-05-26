package comparators;

import app.Competitor;
import java.util.Comparator;

/**
 * Komparátor pro porovnání soutěžících podle jejich kombinovaného ELO skóre v
 * rapid a standardním šachu.
 */
public class RapidStdComparator implements Comparator<Competitor> {

    /**
     * Porovnává dva soutěžící podle jejich kombinovaného ELO skóre v rapid a
     * standardním šachu.
     *
     * @param o1 První soutěžící.
     * @param o2 Druhý soutěžící.
     * @return Záporné číslo, pokud první soutěžící má nižší kombinované ELO
     * skóre v rapid a standardním šachu než druhý; kladné číslo, pokud má
     * vyšší; 0, pokud mají stejné kombinované ELO skóre v rapid a standardním
     * šachu.
     */
    @Override
    public int compare(Competitor o1, Competitor o2) {
        return o2.getStdRapid() - o1.getStdRapid();
    }
}
