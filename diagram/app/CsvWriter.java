package app;

import java.io.*;
import java.util.ArrayList;

/**
 * Třída CsvWriter slouží k ukládání dat do CSV souborů.
 */
public class CsvWriter extends Writer {

    /**
     * Uloží informace o turnaji do CSV souboru.
     *
     * @param tournament Objekt reprezentující turnaj.
     * @param numberOfPlayers Počet hráčů v turnaji.
     * @param competitors Seznam soutěžících v turnaji.
     * @param path Cesta k výstupnímu souboru.
     * @return True, pokud se podařilo uložit data, jinak false.
     */
    @Override
    public boolean save(Tournament tournament, int numberOfPlayers, ArrayList<Competitor> competitors, String path) {
        File file = new File(path + tournament.getName() + ".csv");
        try ( FileWriter csv = new FileWriter(file)) {
            csv.append("fullName,birthYear,fideId,fideStdElo,fideRapidElo,fideBlitzElo,gender\n");
            for (int i = 0; i < numberOfPlayers; i++) {
                csv.append(competitors.get(i).getFullName() + ",");
                csv.append(competitors.get(i).getBirthYear() + ",");
                csv.append(competitors.get(i).getFideId() + ",");
                csv.append(competitors.get(i).getFideStdElo() + ",");
                csv.append(competitors.get(i).getFideRapidElo() + ",");
                csv.append(competitors.get(i).getFideBlitzElo() + ",");
                csv.append(competitors.get(i).getGender() + "\n");
            }
        } catch (IOException ex) {
            return false;
        }
        return true;
    }

    /**
     * Uloží informace o soutěžících do CSV souboru.
     *
     * @param competitors Seznam soutěžících.
     * @param path Cesta k výstupnímu souboru.
     * @return True, pokud se podařilo uložit data, jinak false.
     */
    @Override
    public boolean save(ArrayList<Competitor> competitors, String path) {
        try ( FileWriter csv = new FileWriter(path)) {
            csv.append("fullName,birthYear,fideId,fideStdElo,fideRapidElo,fideBlitzElo,gender\n");
            if (!competitors.isEmpty()) {
                for (Competitor competitor : competitors) {
                    csv.append(competitor.getFullName() + ",");
                    csv.append(competitor.getBirthYear() + ",");
                    csv.append(competitor.getFideId() + ",");
                    csv.append(competitor.getFideStdElo() + ",");
                    csv.append(competitor.getFideRapidElo() + ",");
                    csv.append(competitor.getFideBlitzElo() + ",");
                    csv.append(competitor.getGender() + "\n");
                }
                return true;
            } else {
                return false;
            }
        } catch (IOException ex) {
            return false;
        }
    }
}
