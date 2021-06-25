package ru.netology.game.data;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class SaveGameManager {

    private List<File> saveGames = new ArrayList<>();


    public List<File> getSaveGames() {
        return saveGames;
    }


    public void saveGame(String absolutePath, GameProgress player) {


        String fileName = (absolutePath + "savegames//" + player.getName() + ".dat");
        File savedGame = new File(absolutePath + "savegames//", player.getName() + ".dat");

        try (FileOutputStream fos = new FileOutputStream(fileName);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {

            oos.writeObject(player);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        saveGames.add(savedGame);

    }

    public void zipFiles(String absolutePath) {

        String zipAbsolutePath = (absolutePath + "savegames//zip.zip");

        try (ZipOutputStream zout = new ZipOutputStream(new
                FileOutputStream(zipAbsolutePath))) {

            for (File saveGame : saveGames) {

                String path = saveGame.getAbsolutePath();

                try (FileInputStream fis = new FileInputStream(path)) {

                    ZipEntry entry = new ZipEntry(saveGame.getName());
                    zout.putNextEntry(entry);

                    byte[] buffer = new byte[fis.available()];
                    fis.read(buffer);

                    zout.write(buffer);
                    zout.closeEntry();

                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteSaveGames() {

        StringBuilder sb = new StringBuilder();
        for (File f: saveGames){

            sb.append("Файл " + f.getAbsolutePath() + " удален");
            f.delete();
            sb.append(System.getProperty("line.separator"));

        }

        System.out.println(sb);

    }
}
