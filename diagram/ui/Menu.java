package ui;

import app.*;
import java.io.IOException;
import java.util.ArrayList;
import dataStore.*;

/**
 * Třída obsahující metody pro vytváření textových menu.
 */
public class Menu {

    /**
     * Metoda pro vytvoření hlavního menu programu.
     *
     * @return Řetězec obsahující hlavní menu.
     */
    public static String mainMenu() {
        StringBuilder mainMenu = new StringBuilder();
        mainMenu.append("Vyberte zadáním příslušného čísla do konzole:\n");
        mainMenu.append("1 - Vytvořit novou soupisku na turnaj\n");
        mainMenu.append("2 - Přidat hráče do soupisky klubu\n");
        mainMenu.append("3 - Najít hráče podle ID\n");
        mainMenu.append("4 - Seřadit soupisku klubu\n");
        mainMenu.append("exit - Ukončit program\n");
        return mainMenu.toString();
    }

    /**
     * Metoda pro vytvoření menu výběru turnaje.
     *
     * @return Řetězec obsahující menu výběru turnaje.
     * @throws IOException Pokud dojde k chybě při čtení dat.
     */
    public static String tournamentMenu() throws IOException {
        DataReader d = new DataReader();
        ArrayList<Tournament> tournaments = d.tournamentsFromCsv(Configuration.getTOURNAMENT_DATA_PATH_CSV());

        StringBuilder tournamentMenu = new StringBuilder();
        tournamentMenu.append("Vyberte turnaj:\n");
        int i = 1;
        for (Tournament tournament : tournaments) {
            tournamentMenu.append(i).append(" - ").append(tournament.getName()).append("\n");
            i++;
        }
        tournamentMenu.append("exit - Vrátit se do hlavního menu\n");

        return tournamentMenu.toString();
    }

    /**
     * Metoda pro vytvoření menu pro výběr způsobu řazení soupisky hráčů.
     *
     * @return Řetězec obsahující menu pro výběr řazení.
     */
    public static String sortMenu() {
        StringBuilder sortMenu = new StringBuilder();
        sortMenu.append("1 - Seřadit podle věku (od nejmladšího)\n");
        sortMenu.append("2 - Seřadit podle Standart Ela\n");
        sortMenu.append("3 - Seřadit podle Rapid Ela\n");
        sortMenu.append("4 - Seřadit podle Blitz Ela\n");
        sortMenu.append("exit - Vrátit se zpět do main menu\n");
        return sortMenu.toString();
    }
}
