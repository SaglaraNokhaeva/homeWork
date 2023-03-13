package ui;
import presenter1.Presenter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Console implements View {
    private Scanner scanner;
    private Presenter presenter;
    private Menu menu;
    private boolean work;

    @Override
    public void start() throws NumberFormatException, IOException {
        scanner = new Scanner(System.in);
        menu = new Menu(this);
        work = true;
        while (work) {
            hello();
            String command = scanner.nextLine();
            if (checkInput(command)) {
                menu.execute(Integer.parseInt(command));
            } else {
                System.out.println("Wrong command number.");
            }
        }
    }

    private boolean checkInput(String text) {
        if (text.matches("[0-9]+")) {
            return Integer.parseInt(text) <= menu.getCommands().size();
        } else {
            return false;
        }
    }

    private void hello() {
        print("Choose menu option: ");
        System.out.println(menu.printMenu());
    }

    @Override
    public void print(String text) {
        System.out.println(text);
    }

    public void finish() {
        work = false;
    }

    public void addEntry() {
        System.out.println("Введите имя человека: ");
        String name = scanner.nextLine();

        System.out.println("пол: ");
        String gender = enterGender();

        System.out.println("Введите год рождения: ");
        Integer birthYear = Integer.parseInt(scanner.nextLine());

        System.out.println("Введите имя отца: ");
        String fatherName = scanner.nextLine();

        System.out.println("Введите имя матери: ");
        String motherName = scanner.nextLine();

        if (presenter.addEntry(name, gender, birthYear, fatherName, motherName)) {
            System.out.println("Человек добавлен");
        }
    }

    public void showEntry() {
        print("Enter name: ");
        presenter.showEntry(scanner.nextLine());
    }

    public void showAllEntries() {
        presenter.showAllEntries();

    }

    public String enterGender() {
        String gender = "";
        boolean validGender = false;
        while (!validGender) {
            System.out.println("Enter gender (m/f): ");
            gender = scanner.nextLine();
            validGender = gender.equalsIgnoreCase("m") ||
                    gender.equalsIgnoreCase("f");
        }
        return gender;
    }

    public void save() throws FileNotFoundException {
        presenter.save();
        print("Saved.");
    }

    public void load() throws FileNotFoundException, IOException {
        presenter.load();
        showAllEntries();
    }

    @Override
    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }
}