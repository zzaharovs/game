package ru.netology.game;

import ru.netology.game.data.GameProgress;
import ru.netology.game.data.SaveGameManager;
import ru.netology.game.install.InstallGame;

public class Main {

    public static void main(String[] args) {


        String absolutePath = ("D://Distrib//Games//");

        System.out.println("Игра будет установлена в каталоге по умолчанию: " + absolutePath);

        InstallGame installer = new InstallGame(absolutePath);
        SaveGameManager session = new SaveGameManager();

        System.out.println("Игра установлена!");
        System.out.println();

        System.out.println("Обнаружено несколько персонажей");
        System.out.println();

        GameProgress [] playersArray = new GameProgress[]{
                new GameProgress("Narg", 100, 2, 10, 3500),
                new GameProgress("Chitsa", 150, 5, 15, 6000),
                new GameProgress("Minigan", 990, 10, 99, 100500)
        };

        for (GameProgress player : playersArray) {

            System.out.println(player);
            session.saveGame(absolutePath, player);
            System.out.println("Персонаж " + player.getName() + " сохранен");
            System.out.println();

        }

        System.out.println("Готовим персонажей к импорту");
        session.zipFiles(absolutePath);
        System.out.println("Персонажи готовы к переносу на другой локальный компьютер");
        System.out.println();

        System.out.println("Удаляем файлы, оставшиеся после архивации");
        System.out.println();
        session.deleteSaveGames();

    }

}
