package Controllers;


import Controllers.Variations.Variation;

import java.io.*;
import java.util.Set;

public class ProgramController {

    public static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

    public static void run() throws IOException {
        System.out.print("Enter variation name (end for exit, help for variation names): ");
        String command = input.readLine().toLowerCase();
        while (!command.equals("end")) {
            if (command.equals("help")) help();
            else playVariation(command);
            System.out.print("Enter variation name (end for exit, help for variation names): ");
            command = input.readLine().toLowerCase();
        }
    }

    private static void help() {
        Set<Class<? extends Variation>> variations = Variation.getSubClasses();
        for (Class<? extends Variation> cls: variations)
            System.out.println(cls.getSimpleName());
    }

    private static void playVariation(String variation) {
        Set<Class<? extends Variation>> variations = Variation.getSubClasses();
        boolean find = false;
        for (Class<? extends Variation> cls: variations)
            if (cls.getSimpleName().equalsIgnoreCase(variation)) {
                try {
                    cls.newInstance().play();
                } catch (InstantiationException | IllegalAccessException | IOException e) {
                    System.out.println("There is no variation with this name!");
                }
                find = true;
                break;
            }
        if (!find) System.out.println("There is no variation with this name!");
    }
}
