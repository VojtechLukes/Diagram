package utils;

import app.Competitor;
import app.DataReader;
import app.Tournament;
import java.io.IOException;
import java.util.ArrayList;
import dataStore.*;

/**
 * Třída {@code TestBinary} poskytuje metodu pro kontrolu správného načtení dat
 * z binárních souborů, a to jména všech hráčů a turnajů.
 */
public class TestBinary {

    /**
     * Metoda vypíše jména všech hráčů a turnajů zapsaných v souborech pro
     * kontrolu správného načtení z binárních souborů.
     *
     * @param args argumenty příkazového řádku (nejsou použity)
     * @throws IOException pokud dojde k chybě při čtení ze souborů
     */
    public static void main(String[] args) throws IOException {
        DataReader d = new DataReader();
        StringBuilder sb = new StringBuilder();

        // Načtení turnajů z binárního souboru a výpis jejich jmen
        ArrayList<Tournament> tournaments = d.tournamentsFromBinary(Configuration.getTOURNAMENT_DATA_PATH_BINARY());
        for (Tournament tournament : tournaments) {
            sb.append(tournament.getName()).append("\n");
        }

        // Načtení hráčů z binárního souboru a výpis jejich jmen
        ArrayList<Competitor> competitors = d.competitorsFromBinary(Configuration.getCOMPETITOR_DATA_PATH_BINARY());
        for (Competitor competitor : competitors) {
            sb.append(competitor.getFullName()).append("\n");
        }

        // Kontrola, zda byly načteny nějaké turnaje a hráči
        if (!tournaments.isEmpty() && !competitors.isEmpty()) {
            sb.append("Testování načtení vstupních binárních souborů prošlo v pořádku.");
            System.out.println(sb.toString());
        } else {
            System.out.println("Testování načtení dat z binárnách souborů neproběhlo v pořádku, nebo jsou soubory prázdné");
        }
    }
}
