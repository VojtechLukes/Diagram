package dataStore;

/**
 * Třída obsahující konfigurační nastavení aplikace.
 */
public class Configuration {

    private static final String SEPARATOR = System.getProperty("file.separator");
    private static final String COMPETITOR_DATA_PATH_CSV = "data" + SEPARATOR + "CompetitorData.csv";
    private static final String TOURNAMENT_DATA_PATH_CSV = "data" + SEPARATOR + "TournamentData.csv";
    private static final String COMPETITOR_DATA_PATH_BINARY = "data" + SEPARATOR + "CompetitorData.dat";
    private static final String TOURNAMENT_DATA_PATH_BINARY = "data" + SEPARATOR + "TournamentData.dat";
    private static final String DATA_DIRECORY = "data" + SEPARATOR;

    /**
     * Vrátí cestu k CSV souboru pro ukládání dat o soutěžících.
     *
     * @return Cesta k CSV souboru pro ukládání dat o soutěžících.
     */
    public static String getCOMPETITOR_DATA_PATH_CSV() {
        return COMPETITOR_DATA_PATH_CSV;
    }

    /**
     * Vrátí cestu k CSV souboru pro ukládání dat o turnajích.
     *
     * @return Cesta k CSV souboru pro ukládání dat o turnajích.
     */
    public static String getTOURNAMENT_DATA_PATH_CSV() {
        return TOURNAMENT_DATA_PATH_CSV;
    }

    /**
     * Vrátí cestu k binárnímu souboru pro ukládání dat o soutěžících.
     *
     * @return Cesta k binárnímu souboru pro ukládání dat o soutěžících.
     */
    public static String getCOMPETITOR_DATA_PATH_BINARY() {
        return COMPETITOR_DATA_PATH_BINARY;
    }

    /**
     * Vrátí cestu k binárnímu souboru pro ukládání dat o turnajích.
     *
     * @return Cesta k binárnímu souboru pro ukládání dat o turnajích.
     */
    public static String getTOURNAMENT_DATA_PATH_BINARY() {
        return TOURNAMENT_DATA_PATH_BINARY;
    }

    /**
     * Vrátí adresář, ve kterém jsou ukládána data.
     *
     * @return Adresář, ve kterém jsou ukládána data.
     */
    public static String getDATA_DIRECORY() {
        return DATA_DIRECORY;
    }

}
