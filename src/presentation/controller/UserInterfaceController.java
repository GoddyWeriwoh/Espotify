package presentation.controller;

import business.entity.Song;

import java.util.ArrayList;
import java.util.Scanner;

public class UserInterfaceController {
    public int showMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1. Sign up\n2. Login\n3. See song list\n4. Exit");
        return scanner.nextInt();
    }

    public String askEmail() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the email:");
        String email = scanner.nextLine();
        return email;
    }

    public String askName() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the username:");
        String username = scanner.nextLine();
        return username;
    }

    public String askPassword() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the password:");
        return scanner.nextLine();
    }

    public void showUserExists() {
        System.out.println("User already exists!\n");
    }

    public void showIncorrectPass() {
        System.out.println("Incorrect password!\n");
    }

    public void showUserNoExist() {
        System.out.println("User doesnt exist!\n");
    }

    public void loggedInshow() {
        System.out.println("You are logged in!\n");
    }

    public void showSingUpSuccess() {
        System.out.println("You've been registered!\n");
    }

    public void showSongListMenu(ArrayList<Song> songs) {
        for (int i = 0; i < songs.size(); i++) {
            System.out.println((i+1) + ". " + songs.get(i).getTitle() +"\n");
        }
        System.out.println("Choose what you want to add to playlist\n");
    }

    public void mustLogIn() {
        System.out.println("You must be logged in to manage a playlist\n");
    }

    public String chooseSongs() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose songs you want to add, seperated by commas\n");
        return scanner.nextLine();
    }

    public String getPlayListName() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Give new Playlist a Name\n");
        return scanner.nextLine();
    }
}
