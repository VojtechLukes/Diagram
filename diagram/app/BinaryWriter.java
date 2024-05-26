package app;

import java.io.*;
import java.util.ArrayList;

/**
 * Třída BinaryWriter slouží k zápisu dat do binárních souborů.
 */
public class BinaryWriter extends Writer {

    /**
     * Uloží informace o turnaji do binárního souboru.
     *
     * @param tournament Objekt reprezentující turnaj.
     * @param numberOfPlayers Počet hráčů v turnaji.
     * @param competitors Seznam soutěžících v turnaji.
     * @param path Cesta k výstupnímu souboru.
     * @return True, pokud se podařilo uložit data, jinak false.
     */
    @Override
    public boolean save(Tournament tournament, int numberOfPlayers, ArrayList<Competitor> competitors, String path) {
        File file = new File(path + tournament.getName() + ".dat");
        try ( DataOutputStream binary = new DataOutputStream(new FileOutputStream(file))) {
            for (int i = 0; i < numberOfPlayers; i++) {
                binary.writeUTF(competitors.get(i).getFullName());
                binary.writeInt(competitors.get(i).getBirthYear());
                binary.writeInt(competitors.get(i).getFideId());
                binary.writeInt(competitors.get(i).getFideStdElo());
                binary.writeInt(competitors.get(i).getFideRapidElo());
                binary.writeInt(competitors.get(i).getFideBlitzElo());
                binary.writeUTF(competitors.get(i).getGender());
            }
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    /**
     * Uloží informace o soutěžících do binárního souboru.
     *
     * @param competitors Seznam soutěžících.
     * @param path Cesta k výstupnímu souboru.
     * @return True, pokud se podařilo uložit data, jinak false.
     */
    @Override
    public boolean save(ArrayList<Competitor> competitors, String path) {
        try ( DataOutputStream binary = new DataOutputStream(new FileOutputStream(path))) {
            for (Competitor competitor : competitors) {
                binary.writeUTF(competitor.getFullName());
                binary.writeInt(competitor.getBirthYear());
                binary.writeInt(competitor.getFideId());
                binary.writeInt(competitor.getFideStdElo());
                binary.writeInt(competitor.getFideRapidElo());
                binary.writeInt(competitor.getFideBlitzElo());
                binary.writeUTF(competitor.getGender());
            }
        } catch (IOException e) {
            return false;
        }
        return true;
    }
}
