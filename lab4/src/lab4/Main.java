/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab4;
import lab4.Component;
import lab4.ComponentsArray;
//import lab4.Patterns.Factory;
//import lab4.SmartHomeComponents.*;
import lab4.Terrarium;

import java.lang.reflect.InvocationTargetException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Яна
 */
public class Main {
    private static Factory factory;
    private static ComponentsArray componentsArray;
    private static Scanner scanner;
    private static String name;
    private static int id;
    private static int key;
    private static Component n;

    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        factory = new Factory();
        componentsArray = new ComponentsArray();
        int key;
        scanner = new Scanner(System.in);
        System.out.printf("Создание террариума: \n");
        terrarium();
        while (true) {
            printMenu();
            key = keyIn();
            switch (key) {
                case 1:
                    printComp();
                    key = keyInComp();
                    switch (key) {
                        case 1:
                            fridge();
                            break;
                        case 2:
                            lamp();
                            break;
                        case 3:
                            coffeMachine();
                            break;
                        case 4:
                            signaling();
                            break;
                        case 5:
                            heating();
                            break;
                        default:
                            System.out.printf("Error");
                            break;
                    }
                    break;
                case 2:
                    key = outPrint();
                    on(key);
                    break;
                case 3:
                    key = outPrint();
                    off(key);
                    break;
                case 4:
                    deleteComp();
                    break;
                case 5:
                    break;
                case 6:
                    key = outPrint();
                    onTime(key);
                    break;
                case 7:
                    key = outPrint();
                    offTime(key);
                    break;
                case 8:
                    terrariumCreat();
                    break;
                default:
                    System.out.printf("Error");
                    break;
            }
            getComps();
        }
    }

    private static int keyInComp() {
        int key;
        while (true) {
            key = keyErr();
            if (key >= 1 && key <= 5) {
                break;
            }
        }
        return key;
    }

    public static boolean idVal(Component n) {
        return n != null;

    }

    public static void terrariumCreat() {
        Component n = componentsArray.searchComp(1);
        n.printMes();
    }

    public static void off(int id) {
        Component n = componentsArray.searchComp(id);
        n.switchOff();
    }

    public static void on(int id) {
        n = componentsArray.searchComp(id);
        if (idVal(n)) {
            n.turnOn();
        } else {
            System.out.printf("Такого Id нет\n");
        }
    }

    public static void offTime(int id) {
        Component n = componentsArray.searchComp(id);
        n.timer();
    }

    public static void onTime(int id) {
        Component n = componentsArray.searchComp(id);
        n.timer();
    }

    public static int outPrint() {
        System.out.printf("Введите Id=");
        while (true) {
            key = keyErr();
            if (key > 0) {
                break;
            }
        }
        return key;
    }

    public static void terrarium() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        input();
        Terrarium terrarium = factory.create(Terrarium.class)
                .withName(name)
                .done();
        componentsArray.addComp(terrarium);
        terrarium.timeNow();

    }

    public static void fridge() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        input();
        Fridge fridge = factory.create(Fridge.class)
                .withName(name)
                .done();
        componentsArray.addComp(fridge);

    }

    public static void coffeMachine() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        input();
        CoffeeMachine coffeeMachine = factory.create(CoffeeMachine.class)
                .withName(name)
                .done();
        componentsArray.addComp(coffeeMachine);

    }

    public static void heating() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        input();
        Heating heating = factory.create(Heating.class)
                .withName(name)
                .done();
        componentsArray.addComp(heating);

    }

    public static void lamp() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        input();
        Lamp lamp = factory.create(Lamp.class)
                .withName(name)
                .done();
        componentsArray.addComp(lamp);

    }

    public static void signaling() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        input();
        Signaling signaling = factory.create(Signaling.class)
                .withName(name)
                .done();
        componentsArray.addComp(signaling);

    }

    public static void getComps() {
        componentsArray.getComps();
    }

    public static void deleteComp() {
        System.out.printf("Введите Id:");
        while (id <= 1) {
            id = keyErr();
        }
        n = componentsArray.searchComp(id);
        if (idVal(n)) {
            componentsArray.deleteComp(id);
        } else {
            System.out.printf("Такого Id нет\n");
        }
        id=0;

    }

    public static void printMenu() {
        System.out.printf("Команды:\n" +
                "1 - Создать компонент\n" +
                "2 - Включить компонент\n" +
                "3 - Выключить компонент\n" +
                "4 - Удалить компонент\n" +
                "5 - Отобразить компоненты\n" +
                "6 - Включить компонент в хх:хх\n" +
                "7 - Выключить компонент в хх:хх\n" +
                "8 - Даные о террариуме\n");
    }

    public static void printComp() {
        System.out.printf("Компонент:\n" +
                "1 - Холодильник\n" +
                "2 - Светильник\n" +
                "3 - Кофе машина\n" +
                "4 - Сигнализация\n" +
                "5 - Обогрев\n");
    }

    public static void input() {
        System.out.printf("Введите Name:");
        name = scanner.next();
    }

    public static int keyIn() {
        int key;
        while (true) {
            key = keyErr();
            if (key >= 1 && key <= 8) {
                break;
            }
        }
        return key;
    }

    public static int keyErr() {
        Scanner scanner1 = new Scanner(System.in);
        int playerNum;
        try {
            playerNum = scanner1.nextInt();
        } catch (InputMismatchException e) {
            playerNum = -1;
        }
        return playerNum;
    }
}

