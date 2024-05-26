package app;

import java.util.ArrayList;

/**
 * Abstraktní třída Writer definuje metody pro ukládání dat.
 */
public abstract class Writer {

    /**
     * Uloží data o turnaji do souboru.
     *
     * @param tournament Informace o turnaji.
     * @param numberOfPlayers Počet hráčů.
     * @param competitors Seznam soutěžících.
     * @param path Cesta k souboru.
     * @return True, pokud se podaří data úspěšně uložit, jinak false.
     */
    public abstract boolean save(Tournament tournament, int numberOfPlayers, ArrayList<Competitor> competitors, String path);

    /**
     * Uloží data o soutěžících do souboru.
     *
     * @param competitors Seznam soutěžících.
     * @param path Cesta k souboru.
     * @return True, pokud se podaří data úspěšně uložit, jinak false.
     */
    public abstract boolean save(ArrayList<Competitor> competitors, String path);
}
