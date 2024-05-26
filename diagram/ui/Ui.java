package ui;

import utils.SendEmail;
import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import app.*;
import dataStore.*;
import utils.*;

/**
 * Třída Ui je zodpovědná za správu uživatelského rozhraní a zpracování
 * uživatelských vstupů.
 */
public class Ui {

    static Scanner sc = new Scanner(System.in);
    static CsvWriter c = new CsvWriter();
    static BinaryWriter b = new BinaryWriter();

    /**
     * Hlavní metoda třídy Ui. Zobrazuje hlavní menu a zpracovává uživatelský
     * vstup.
     *
     * @param args argumenty příkazového řádku
     */
    public static void main(String[] args) {
        String input;
        do {
            try {
                System.out.println(Menu.mainMenu());
                input = sc.nextLine();
                handleMainMenuInput(input);
            } catch (Exception e) {
                System.out.println("Došlo k neočekávané chybě: " + e.getMessage());
                input = "exit";
            }
        } while (!input.equals("exit"));
    }

    /**
     * Zpracovává vstup uživatele z hlavního menu.
     *
     * @param input vstup uživatele
     */
    private static void handleMainMenuInput(String input) {
        try {
            switch (input) {
                case "1" ->
                    buildRoster();
                case "2" ->
                    addCompetitor();
                case "3" ->
                    findCompetitor();
                case "4" ->
                    sortCompetitors();
                default ->
                    System.out.println("Neplatný vstup");
            }
        } catch (Exception e) {
            System.out.println("Chyba při zpracování vstupu: " + e.getMessage());
        }
    }

    /**
     * Vytváří soupisku turnaje na základě uživatelského vstupu.
     */
    private static void buildRoster() {
        try {
            ArrayList<Tournament> tournaments = new ArrayList<>();
            ArrayList<Competitor> competitors = new ArrayList<>();
            String input = getRosterInput();

            if (input.equals("b")) {
                loadFromBinary(tournaments, competitors);
            } else if (input.equals("c")) {
                loadFromCsv(tournaments, competitors);
            } else {
                System.out.println(Menu.mainMenu());
                return;
            }

            Tournament tournament = selectTournament(tournaments);
            if (tournament == null) {
                return;
            }

            competitors = TournamentFilter.filter(competitors, tournament.getTournamentGender(), tournament.getEloSystem());
            int numberOfPlayers = getNumberOfPlayers(competitors.size());
            saveRoster(tournament, numberOfPlayers, competitors);
            sendEmail(tournament);

        } catch (InputFileDoesNotExistException e) {
            System.out.println("Vstupní soubor nenalezen: " + e.getMessage());
        } catch (IOException | InputMismatchException | NumberFormatException ex) {
            System.out.println("Neplatný vstup nebo chyba při čtení/zápisu: " + ex.getMessage());
        }
    }

    /**
     * Získává vstup uživatele pro načtení soupisky.
     *
     * @return vstup uživatele
     */
    private static String getRosterInput() {
        System.out.println("Z jakého souboru chcete načítat\nb -> binární\nc - > csv:");
        return sc.nextLine();
    }

    /**
     * Načítá data z binárního souboru.
     *
     * @param tournaments seznam turnajů
     * @param competitors seznam soutěžících
     * @throws IOException pokud dojde k chybě při čtení souboru
     */
    private static void loadFromBinary(ArrayList<Tournament> tournaments, ArrayList<Competitor> competitors) throws IOException {
        tournaments.addAll(DataReader.tournamentsFromBinary(Configuration.getTOURNAMENT_DATA_PATH_BINARY()));
        competitors.addAll(DataReader.competitorsFromBinary(Configuration.getCOMPETITOR_DATA_PATH_BINARY()));
    }

    /**
     * Načítá data z CSV souboru.
     *
     * @param tournaments seznam turnajů
     * @param competitors seznam soutěžících
     * @throws IOException pokud dojde k chybě při čtení souboru
     */
    private static void loadFromCsv(ArrayList<Tournament> tournaments, ArrayList<Competitor> competitors) throws IOException {
        tournaments.addAll(DataReader.tournamentsFromCsv(Configuration.getTOURNAMENT_DATA_PATH_CSV()));
        competitors.addAll(DataReader.competitorsFromCsv(Configuration.getCOMPETITOR_DATA_PATH_CSV()));
    }

    /**
     * Vybere turnaj z poskytnutého seznamu turnajů.
     *
     * @param tournaments seznam turnajů
     * @return vybraný turnaj nebo null, pokud je vstup neplatný
     */
    private static Tournament selectTournament(ArrayList<Tournament> tournaments) {
        try {
            System.out.println(Menu.tournamentMenu());
            String input = sc.nextLine();
            if (input.equals("exit")) {
                return null;
            }
            int tournamentNumber = Integer.parseInt(input);
            return tournaments.get(tournamentNumber - 1);
        } catch (IOException e) {
            System.out.println("Chyba při čtení vstupu: " + e.getMessage());
            return null;
        } catch (NumberFormatException e) {
            System.out.println("Neplatný formát čísla: " + e.getMessage());
            return null;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Neplatné číslo turnaje: " + e.getMessage());
            return null;
        } catch (Exception e) {
            System.out.println("Došlo k neočekávané chybě: " + e.getMessage());
            return null;
        }
    }

    /**
     * Získá počet hráčů pro turnaj.
     *
     * @param maxPlayers maximální počet hráčů
     * @return zvolený počet hráčů
     */
    private static int getNumberOfPlayers(int maxPlayers) {
        try {
            System.out.println("Zvolte kolik hráčů chcete nasadit na turnaj");
            int numberOfPlayers = sc.nextInt();
            while (numberOfPlayers > maxPlayers) {
                System.out.println("Vybrali jste příliš mnoho hráčů, zadejte nový počet.");
                numberOfPlayers = sc.nextInt();
            }
            sc.nextLine(); // Vyčistí buffer
            return numberOfPlayers;
        } catch (InputMismatchException e) {
            System.out.println("Neplatný vstup: " + e.getMessage());
            sc.nextLine(); // Vyčistí buffer
            return getNumberOfPlayers(maxPlayers);
        } catch (Exception e) {
            System.out.println("Došlo k neočekávané chybě: " + e.getMessage());
            return 0;
        }
    }

    /**
     * Uloží soupisku turnaje do souboru.
     *
     * @param tournament turnaj
     * @param numberOfPlayers počet hráčů
     * @param competitors seznam soutěžících
     * @throws IOException pokud dojde k chybě při zápisu do souboru
     */
    private static void saveRoster(Tournament tournament, int numberOfPlayers, ArrayList<Competitor> competitors) throws IOException {
        try {
            System.out.println("Zvolte typ exportovaného souboru\nc - > csv\nx - > csv + binární");
            String input = sc.nextLine();
            boolean success = switch (input) {
                case "c" ->
                    c.save(tournament, numberOfPlayers, competitors, Configuration.getDATA_DIRECORY());
                case "x" ->
                    b.save(tournament, numberOfPlayers, competitors, Configuration.getDATA_DIRECORY())
                    && c.save(tournament, numberOfPlayers, competitors, Configuration.getDATA_DIRECORY());
                default ->
                    false;
            };
            if (success) {
                System.out.println("Uložení proběhlo v pořádku");
            } else {
                System.out.println("Soubory se neuložily správně");
            }
        } catch (Exception e) {
            System.out.println("Došlo k neočekávané chybě: " + e.getMessage());
        }
    }

    /**
     * Odešle soupisku turnaje emailem.
     *
     * @param tournament turnaj
     */
    private static void sendEmail(Tournament tournament) {
        try {
            System.out.println("Chcete soupisku zaslat emailem? a/n");
            String sendEmail = sc.nextLine();
            if (sendEmail.equals("a")) {
                System.out.println("Zadejte email na který chcete poslat soupisku.");
                String email = sc.nextLine();
                File attachedFile = new File(Configuration.getDATA_DIRECORY() + tournament.getName() + ".csv");
                if (SendEmail.sendEmail(attachedFile, email, tournament.getName())) {
                    System.out.println("Email úspěšně odeslán");
                } else {
                    System.out.println("Odeslání emailu neprošlo v pořádku.");
                }
            }
        } catch (Exception e) {
            System.out.println("Došlo k neočekávané chybě při odesílání emailu: " + e.getMessage());
        }
    }

    /**
     * Přidá soutěžícího do seznamu.
     */
    private static void addCompetitor() {
        try {
            ArrayList<Competitor> competitors = DataReader.competitorsFromCsv(Configuration.getCOMPETITOR_DATA_PATH_CSV());
            Competitor competitor = getCompetitorDetails();
            if (competitor != null) {
                competitors.add(competitor);
                if (saveCompetitors(competitors)) {
                    System.out.println("Uložení proběhlo v pořádku");
                } else {
                    System.out.println("Soubory se neuložily správně");
                }
            }
        } catch (InputFileDoesNotExistException e) {
            System.out.println("Vstupní soubor nenalezen: " + e.getMessage());
        } catch (InputMismatchException | IOException ex) {
            System.out.println("Neplatný vstup nebo chyba při čtení/zápisu: " + ex.getMessage());
        }
    }

    /**
     * Získá detaily soutěžícího od uživatele.
     *
     * @return vytvořený soutěžící
     */
    private static Competitor getCompetitorDetails() {
        try {
            System.out.println("Zadávejte údaje:");
            System.out.println("Jméno a příjmení:");
            String name = sc.nextLine();
            System.out.println("Rok narození:");
            int birthYear = sc.nextInt();
            System.out.println("Fide ID:");
            int fideId = sc.nextInt();
            System.out.println("Fide Standard Elo:");
            int fideStdElo = sc.nextInt();
            System.out.println("Fide Rapid Elo:");
            int fideRapidElo = sc.nextInt();
            System.out.println("Fide Blitz Elo:");
            int fideBlitzElo = sc.nextInt();
            System.out.println("Pohlaví:");
            sc.nextLine(); // Vyčistí buffer
            String gender = sc.nextLine();

            if (!gender.equals("M") && !gender.equals("F")) {
                System.out.println("Zadejte správné pohlaví, F/M");
                return null;
            }
            return new Competitor(name, birthYear, fideId, fideStdElo, fideRapidElo, fideBlitzElo, gender);
        } catch (InputMismatchException e) {
            System.out.println("Neplatný vstup: " + e.getMessage());
            sc.nextLine(); // Vyčistí buffer
            return null;
        } catch (Exception e) {
            System.out.println("Došlo k neočekávané chybě: " + e.getMessage());
            return null;
        }
    }

    /**
     * Uloží seznam soutěžících do souboru.
     *
     * @param competitors seznam soutěžících
     * @return true pokud bylo uložení úspěšné, jinak false
     * @throws IOException pokud dojde k chybě při zápisu do souboru
     */
    private static boolean saveCompetitors(ArrayList<Competitor> competitors) throws IOException {
        try {
            return b.save(competitors, Configuration.getCOMPETITOR_DATA_PATH_BINARY())
                    && c.save(competitors, Configuration.getCOMPETITOR_DATA_PATH_CSV());
        } catch (Exception e) {
            System.out.println("Došlo k neočekávané chybě: " + e.getMessage());
            return false;
        }
    }

    /**
     * Vyhledá soutěžícího podle Fide ID.
     */
    private static void findCompetitor() {
        try {
            ArrayList<Competitor> competitors = DataReader.competitorsFromCsv(Configuration.getCOMPETITOR_DATA_PATH_CSV());
            System.out.println("Zadejte ID hráče:");
            int fideId = sc.nextInt();
            sc.nextLine(); // Vyčistí buffer
            displayCompetitorInfo(competitors, fideId);
        } catch (InputFileDoesNotExistException e) {
            System.out.println("Vstupní soubor nenalezen: " + e.getMessage());
        } catch (InputMismatchException | IOException ex) {
            System.out.println("Neplatný vstup nebo chyba při čtení: " + ex.getMessage());
        }
    }

    /**
     * Zobrazí informace o soutěžícím.
     *
     * @param competitors seznam soutěžících
     * @param fideId Fide ID soutěžícího
     */
    private static void displayCompetitorInfo(ArrayList<Competitor> competitors, int fideId) {
        try {
            boolean playerFound = false;
            for (Competitor competitor : competitors) {
                if (fideId == competitor.getFideId()) {
                    System.out.printf("Jméno: %s\nRok narození: %d\nStandard: %d\nRapid: %d\nBlitz: %d\nPohlaví: %s\n",
                            competitor.getFullName(), competitor.getBirthYear(), competitor.getFideStdElo(), competitor.getFideRapidElo(),
                            competitor.getFideBlitzElo(), competitor.getGender());
                    playerFound = true;
                    break;
                }
            }
            if (!playerFound) {
                System.out.println("V soupisce není nikdo se zadaným ID.");
            }
        } catch (Exception e) {
            System.out.println("Došlo k neočekávané chybě: " + e.getMessage());
        }
    }

    /**
     * Seřadí soutěžící podle uživatelského vstupu.
     */
    private static void sortCompetitors() {
        try {
            ArrayList<Competitor> competitors = DataReader.competitorsFromCsv(Configuration.getCOMPETITOR_DATA_PATH_CSV());
            sortCompetitorsMenu(competitors);
            saveCompetitors(competitors);
        } catch (InputFileDoesNotExistException e) {
            System.out.println("Vstupní soubor nenalezen: " + e.getMessage());
        } catch (IOException ex) {
            System.out.println("Neplatný vstup nebo chyba při čtení/zápisu: " + ex.getMessage());
        } catch (Exception e) {
            System.out.println("Došlo k neočekávané chybě: " + e.getMessage());
        }
    }

    /**
     * Zobrazí menu pro řazení soutěžících a zpracuje uživatelský vstup.
     *
     * @param competitors seznam soutěžících
     */
    private static void sortCompetitorsMenu(ArrayList<Competitor> competitors) {
        try {
            System.out.println(Menu.sortMenu());
            String input = sc.nextLine();
            if (input.equals("exit")) {
                System.out.println(Menu.mainMenu());
                return;
            }
            switch (input) {
                case "1" ->
                    competitors.sort(new comparators.AgeComparator());
                case "2" ->
                    competitors.sort(new comparators.StdComparator());
                case "3" ->
                    competitors.sort(new comparators.RapidComparator());
                case "4" ->
                    competitors.sort(new comparators.BlitzComparator());
                default ->
                    System.out.println("Neplatný vstup");
            }
        } catch (Exception e) {
            System.out.println("Došlo k neočekávané chybě: " + e.getMessage());
        }
    }
}
