/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab4;
import lab4.Interfaces.IState;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 *
 * @author Яна
 */
public abstract class Component implements IState {
    public String name;
    public int id;
    public String time;
    public String timeon;
    public String timeoff;
    public boolean state;

    public void printMes() {
    }

    public Component() {
        name = "";
        id = 0;
        time = "";
        timeon = "";
        timeoff = "";
        state = false;
    }
public void timer() {
        Pattern p = Pattern.compile("^[0-2][0-3]:[0-5][0-9]$");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.printf("Введите вреня хх:хх\n");
            time = scanner.nextLine();
            if ((p.matcher(time)).matches()) {
                break;
            }
        }
        if (getState()) {
            timeon = time;
        } else {
            timeoff = time;
        }
    }

    public void print() {
        System.out.printf("Id-" + id + " имя-" + name + " состояние-" + (state ? "on" : "off") +
                " включить в " + (timeon == "" ? "xx:xx" : timeon) +
                " выключить в " + (timeoff == "" ? "xx:xx" : timeoff) + "\n");
    }

}
