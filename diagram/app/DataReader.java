package app;

import java.io.*;
import java.util.ArrayList;
import utils.*;

/**
 * Třída DataReader poskytuje metody pro čtení dat z různých typů souborů.
 */
public class DataReader {

    /**
     * Načte seznam soutěžících z CSV souboru.
     *
     * @param path Cesta k CSV souboru.
     * @return Seznam soutěžících.
     * @throws IOException Pokud nastane chyba při čtení souboru.
     * @throws InputFileDoesNotExistException Pokud zadaný soubor neexistuje.
     */
    public static ArrayList<Competitor> competitorsFromCsv(String path) throws IOException, InputFileDoesNotExistException {
        checkFileExists(path);
        String name;
        int birthYear;
        int ID;
        int std;
        int rapid;
        int blitz;
        String gender;
        ArrayList<Competitor> competitors = new ArrayList<>();
        try ( BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            reader.readLine(); // Přeskočení prvního řádku
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                name = values[0];
                birthYear = Integer.parseInt(values[1]);
                ID = Integer.parseInt(values[2]);
                std = Integer.parseInt(values[3]);
                rapid = Integer.parseInt(values[4]);
                blitz = Integer.parseInt(values[5]);
                gender = values[6];
                competitors.add(new Competitor(name, birthYear, ID, std, rapid, blitz, gender));
            }
        }
        return competitors;
    }

    /**
     * Načte seznam turnajů z CSV souboru.
     *
     * @param path Cesta k CSV souboru.
     * @return Seznam turnajů.
     * @throws IOException Pokud nastane chyba při čtení souboru.
     * @throws InputFileDoesNotExistException Pokud zadaný soubor neexistuje.
     */
    public static ArrayList<Tournament> tournamentsFromCsv(String path) throws IOException, InputFileDoesNotExistException {
        checkFileExists(path);
        ArrayList<Tournament> tournaments = new ArrayList<>();
        try ( BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            reader.readLine(); // Přeskočení prvního řádku
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                tournaments.add(new Tournament(values[0], values[1], values[2]));
            }
        }
        return tournaments;
    }

    /**
     * Načte seznam turnajů z binárního souboru.
     *
     * @param path Cesta k binárnímu souboru.
     * @return Seznam turnajů.
     * @throws IOException Pokud nastane chyba při čtení souboru.
     * @throws InputFileDoesNotExistException Pokud zadaný soubor neexistuje.
     */
    public static ArrayList<Tournament> tournamentsFromBinary(String path) throws IOException, InputFileDoesNotExistException {
        checkFileExists(path);
        String name;
        String genderAgeCategory;
        String eloCategory;
        ArrayList<Tournament> tournaments = new ArrayList<>();
        try ( DataInputStream binary = new DataInputStream(new FileInputStream(path))) {
            while (binary.available() > 0) {
                name = binary.readUTF();
                genderAgeCategory = binary.readUTF();
                eloCategory = binary.readUTF();
                tournaments.add(new Tournament(name, genderAgeCategory, eloCategory));
            }
        }
        return tournaments;
    }

    /**
     * Načte seznam soutěžících z binárního souboru.
     *
     * @param path Cesta k binárnímu souboru.
     * @return Seznam soutěžících.
     * @throws IOException Pokud nastane chyba při čtení souboru.
     * @throws InputFileDoesNotExistException Pokud zadaný soubor neexistuje.
     */
    public static ArrayList<Competitor> competitorsFromBinary(String path) throws IOException, InputFileDoesNotExistException {
        checkFileExists(path);
        String name;
        int birthYear;
        int ID;
        int std;
        int rapid;
        int blitz;
        String gender;
        ArrayList<Competitor> competitors = new ArrayList<>();
        try ( DataInputStream binary = new DataInputStream(new FileInputStream(path))) {
            while (binary.available() > 0) {
                name = binary.readUTF();
                birthYear = binary.readInt();
                ID = binary.readInt();
                std = binary.readInt();
                rapid = binary.readInt();
                blitz = binary.readInt();
                gender = binary.readUTF();
                competitors.add(new Competitor(name, birthYear, ID, std, rapid, blitz, gender));
            }
        }
        return competitors;
    }

    /**
     * Kontroluje existenci a platnost souboru.
     *
     * @param path Cesta k souboru.
     * @throws InputFileDoesNotExistException Pokud zadaný soubor neexistuje.
     */
    private static void checkFileExists(String path) throws InputFileDoesNotExistException {
        File file = new File(path);
        if (!file.exists() || !file.isFile()) {
            throw new InputFileDoesNotExistException("Soubor nenalezen: " + path);
        }
    }
}
