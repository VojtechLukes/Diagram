//package ui;
//
//import java.io.*;
//import java.util.ArrayList;
//import java.util.InputMismatchException;
//import java.util.Scanner;
//import app.*;
//import email.*;
//import dataStore.*;
//
//public class Ui {
//
//    static Scanner sc = new Scanner(System.in);
//    static CsvWriter c = new CsvWriter();
//    static BinaryWriter b = new BinaryWriter();
//
//    public static void main(String[] args) {
//        while (true) {
//            System.out.println(Menu.mainMenu());
//            String input = sc.nextLine();
//
//            if (input.equals("exit")) {
//                break;
//            }
//
//            String result = switch (input) {
//                case "1" ->
//                    buildRoster();
//                case "2" ->
//                    addCompetitor();
//                case "3" ->
//                    findCompetitor();
//                case "4" ->
//                    sortCompetitors();
//                default ->
//                    "Neplatný vstup\n";
//            };
//
//            System.out.println(result);
//        }
//    }
//
//    public static String buildRoster() {
//        StringBuilder result = new StringBuilder();
//
//        try {
//            ArrayList<Tournament> tournaments = new ArrayList<>();
//            ArrayList<Competitor> competitors = new ArrayList<>();
//            Tournament tournament;
//            int numberOfPlayers;
//            int tournamentNumber;
//
//            result.append("Z jakého souboru chcete načítat\nb -> binární\nc - > csv:\n");
//            System.out.print(result.toString());
//            result.setLength(0); // Clear the StringBuilder for new input
//
//            String input = sc.nextLine();
//
//            switch (input) {
//                case "b" -> {
//                    tournaments = DataReader.tournamentsFromBinary(Configuration.getTOURNAMENT_DATA_PATH_BINARY());
//                    competitors = DataReader.competitorsFromBinary(Configuration.getCOMPETITOR_DATA_PATH_BINARY());
//                }
//                case "c" -> {
//                    tournaments = DataReader.tournamentsFromCsv(Configuration.getTOURNAMENT_DATA_PATH_CSV());
//                    competitors = DataReader.competitorsFromCsv(Configuration.getCOMPETITOR_DATA_PATH_CSV());
//                }
//                default -> {
//                    return Menu.mainMenu();
//                }
//            }
//
//            result.append(Menu.tournamentMenu()).append("\n");
//            System.out.print(result.toString());
//            result.setLength(0); // Clear the StringBuilder for new input
//
//            input = sc.nextLine();
//
//            if (input.equals("exit")) {
//                return "";
//            }
//
//            tournamentNumber = Integer.parseInt(input);
//            tournament = tournaments.get(tournamentNumber - 1);
//            competitors = TournamentFilter.filter(competitors, tournament.getTournamentGender(), tournament.getEloSystem());
//
//            result.append("Zvolte kolik hráčů chcete nasadit na turnaj\n");
//            System.out.print(result.toString());
//            result.setLength(0); // Clear the StringBuilder for new input
//
//            numberOfPlayers = sc.nextInt();
//
//            while (numberOfPlayers > competitors.size()) {
//                result.append("Vybrali jste příliš mnoho hráčů, zadejte nový počet.\n");
//                System.out.print(result.toString());
//                result.setLength(0); // Clear the StringBuilder for new input
//                numberOfPlayers = sc.nextInt();
//            }
//
//            sc.nextLine(); // Clear the buffer
//            result.append("Zvolte typ exportovaného souboru\nc - > csv\nx - > csv + binární\n");
//            System.out.print(result.toString());
//            result.setLength(0); // Clear the StringBuilder for new input
//
//            input = sc.nextLine();
//            boolean saveSuccess = switch (input) {
//                case "c" ->
//                    c.save(tournament, numberOfPlayers, competitors, Configuration.getDATA_DIRECORY());
//                case "x" ->
//                    b.save(tournament, numberOfPlayers, competitors, Configuration.getDATA_DIRECORY())
//                    && c.save(tournament, numberOfPlayers, competitors, Configuration.getDATA_DIRECORY());
//                default ->
//                    false;
//            };
//            result.append(saveSuccess ? "Uložení proběhlo v pořádku\n" : "Soubory se neuložily správně\n");
//            result.append(sendEmail(tournament));
//
//        } catch (IOException | InputMismatchException | NumberFormatException ex) {
//            result.append("Neplatný vstup nebo chyba při čtení/zápisu.\n");
//        }
//
//        return result.toString();
//    }
//
//    public static String sendEmail(Tournament tournament) {
//        StringBuilder result = new StringBuilder();
//        result.append("Chcete soupisku zaslat emailem? a/n\n");
//        System.out.print(result.toString());
//        result.setLength(0); // Clear the StringBuilder for new input
//
//        String sendEmail = sc.nextLine();
//        if (sendEmail.equals("a")) {
//            result.append("Zadejte email na který chcete poslat soupisku.\n");
//            System.out.print(result.toString());
//            result.setLength(0); // Clear the StringBuilder for new input
//
//            String email = sc.nextLine();
//            File attachedFile = new File(Configuration.getDATA_DIRECORY() + tournament.getName() + ".csv");
//
//            boolean emailSuccess = SendEmail.sendEmail(attachedFile, email, tournament.getName());
//            result.append(emailSuccess ? "Email úspěšně odeslán\n" : "Odeslání emailu neprošlo v pořádku.\n");
//        }
//        return result.toString();
//    }
//
//    public static String addCompetitor() {
//        StringBuilder result = new StringBuilder();
//
//        try {
//            ArrayList<Competitor> competitors = DataReader.competitorsFromCsv(Configuration.getCOMPETITOR_DATA_PATH_CSV());
//
//            result.append("Zadávejte údaje:\n");
//            result.append("Jméno a příjmení:\n");
//            System.out.print(result.toString());
//            result.setLength(0); // Clear the StringBuilder for new input
//
//            String name = sc.nextLine();
//            result.append("Rok narození:\n");
//            System.out.print(result.toString());
//            result.setLength(0); // Clear the StringBuilder for new input
//
//            int birthYear = sc.nextInt();
//            result.append("Fide ID:\n");
//            System.out.print(result.toString());
//            result.setLength(0); // Clear the StringBuilder for new input
//
//            int fideId = sc.nextInt();
//            result.append("Fide Standard Elo:\n");
//            System.out.print(result.toString());
//            result.setLength(0); // Clear the StringBuilder for new input
//
//            int fideStdElo = sc.nextInt();
//            result.append("Fide Rapid Elo:\n");
//            System.out.print(result.toString());
//            result.setLength(0); // Clear the StringBuilder for new input
//
//            int fideRapidElo = sc.nextInt();
//            result.append("Fide Blitz Elo:\n");
//            System.out.print(result.toString());
//            result.setLength(0); // Clear the StringBuilder for new input
//
//            int fideBlitzElo = sc.nextInt();
//            result.append("Pohlaví:\n");
//            System.out.print(result.toString());
//            result.setLength(0); // Clear the StringBuilder for new input
//
//            sc.nextLine(); // Clear the buffer
//            String gender = sc.nextLine();
//
//            if (!gender.equals("M") && !gender.equals("F")) {
//                result.append("Zadejte správné pohlaví, F/M\n");
//            } else {
//                competitors.add(new Competitor(name, birthYear, fideId, fideStdElo, fideRapidElo, fideBlitzElo, gender));
//                boolean saveSuccess = b.save(competitors, Configuration.getCOMPETITOR_DATA_PATH_BINARY())
//                        && c.save(competitors, Configuration.getCOMPETITOR_DATA_PATH_CSV());
//                result.append(saveSuccess ? "Uložení proběhlo v pořádku\n" : "Soubory se neuložily správně\n");
//            }
//        } catch (InputMismatchException | IOException ex) {
//            result.append("Neplatný vstup nebo chyba při čtení/zápisu.\n");
//        }
//
//        return result.toString();
//    }
//
//    public static String findCompetitor() {
//        StringBuilder result = new StringBuilder();
//
//        try {
//            ArrayList<Competitor> competitors = DataReader.competitorsFromCsv(Configuration.getCOMPETITOR_DATA_PATH_CSV());
//
//            result.append("Zadejte ID hráče:\n");
//            System.out.print(result.toString());
//            result.setLength(0); // Clear the StringBuilder for new input
//
//            int fideId = sc.nextInt();
//            sc.nextLine(); // Clear the buffer after nextInt
//            boolean playerFound = false;
//
//            for (Competitor competitor : competitors) {
//                if (fideId == competitor.getFideId()) {
//                    result.append(String.format("Jméno: %s\nRok narození: %d\nStandard: %d\nRapid: %d\nBlitz: %d\nPohlaví: %s\n",
//                            competitor.getFullName(), competitor.getBirthYear(), competitor.getFideStdElo(), competitor.getFideRapidElo(),
//                            competitor.getFideBlitzElo(), competitor.getGender()));
//                    playerFound = true;
//                    break;
//                }
//            }
//
//            if (!playerFound) {
//                result.append("V soupisce není nikdo se zadaným ID.\n");
//            }
//        } catch (InputMismatchException | IOException ex) {
//            result.append("Neplatný vstup nebo chyba při čtení.\n");
//        }
//
//        return result.toString();
//    }
//
//    public static String sortCompetitors() {
//        StringBuilder result = new StringBuilder();
//
//        try {
//            ArrayList<Competitor> competitors = DataReader.competitorsFromCsv(Configuration.getCOMPETITOR_DATA_PATH_CSV());
//
//            result.append(Menu.sortMenu()).append("\n");
//            System.out.print(result.toString());
//            result.setLength(0); // Clear the StringBuilder for new input
//
//            String input = sc.nextLine();
//
//            if (input.equals("exit")) {
//                return Menu.mainMenu();
//            }
//
//            switch (input) {
//                case "1" ->
//                    competitors.sort(new comparators.AgeComparator());
//                case "2" ->
//                    competitors.sort(new comparators.StdComparator());
//                case "3" ->
//                    competitors.sort(new comparators.RapidComparator());
//                case "4" ->
//                    competitors.sort(new comparators.BlitzComparator());
//                default ->
//                    result.append("Neplatný vstup\n");
//            }
//
//            c.save(competitors, Configuration.getCOMPETITOR_DATA_PATH_CSV());
//            b.save(competitors, Configuration.getCOMPETITOR_DATA_PATH_BINARY());
//
//        } catch (IOException ex) {
//            result.append("Neplatný vstup nebo chyba při čtení/zápisu.\n");
//        }
//
//        return result.toString();
//    }
//}
