package comparators;

import app.Competitor;
import java.util.Comparator;

/**
 * Komparátor pro porovnání soutěžících podle jejich kombinovaného ELO skóre v
 * rapid a blitz šachu.
 */
public class RapidBlitzComparator implements Comparator<Competitor> {

    /**
     * Porovnává dva soutěžící podle jejich kombinovaného ELO skóre v rapid a
     * blitz šachu.
     *
     * @param o1 První soutěžící.
     * @param o2 Druhý soutěžící.
     * @return Záporné číslo, pokud první soutěžící má nižší kombinované ELO
     * skóre v rapid a blitz šachu než druhý; kladné číslo, pokud má vyšší; 0,
     * pokud mají stejné kombinované ELO skóre v rapid a blitz šachu.
     */
    @Override
    public int compare(Competitor o1, Competitor o2) {
        return o2.getRapidBlitz() - o1.getRapidBlitz();
    }
}
