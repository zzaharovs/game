package ru.netology.game.install;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InstallGame {

    private List<File> directory = new ArrayList<>();
    private List<File> files = new ArrayList<>();
    private String absolutePath;
    private StringBuilder sb = new StringBuilder();

    public InstallGame (String absolutePath) {
        this.absolutePath = absolutePath;
        sb.append("Запущена установка программы");
        sb.append(System.getProperty("line.separator"));
        createFiles();
        install();
        logging();

    }


    private void createFiles() {

        File src = new File(absolutePath + "src");
        File dirMain = new File(src, "main");
        File fileMain = new File(dirMain, "Main.java");
        File util = new File(dirMain, "Utils.java");
        File test = new File(absolutePath + "test");
        File res = new File(absolutePath + "res");
        File drawables = new File(res, "drawables");
        File vectors = new File(res, "vectors");
        File icons = new File(res, "icons");
        File savegames = new File(absolutePath + "savegames");
        File temp = new File(absolutePath + "temp");
        File fileTemp = new File(absolutePath + "temp", "temp.txt");

        directory.add(src);
        directory.add(dirMain);
        directory.add(test);
        directory.add(res);
        directory.add(drawables);
        directory.add(vectors);
        directory.add(icons);
        directory.add(savegames);
        directory.add(temp);

        files.add(fileMain);
        files.add(util);
        files.add(fileTemp);

    }

    private void install() {

        for (File f : directory) {

            if (f.mkdir()) {
                sb.append("Каталог " + f.getAbsolutePath() + " создан");
                sb.append(System.getProperty("line.separator"));
            }
        }

        for (File f : files) {

            try {
                if (f.createNewFile()) {
                    sb.append("Файл " + f.getAbsolutePath() + " создан \n");
                    sb.append(System.getProperty("line.separator"));
                }
            } catch (IOException ex) {
                ex.getMessage();
            }

        }

    }

    private void logging() {

        try (FileWriter writer = new FileWriter(absolutePath + "//temp//temp.txt")) {

            writer.write(String.valueOf(sb));
            writer.flush();

        } catch (IOException ex) {

            System.out.println(ex.getMessage());

        }
    }
}
