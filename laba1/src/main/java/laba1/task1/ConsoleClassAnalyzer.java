package laba1.task1;

import java.util.Scanner;

public class ConsoleClassAnalyzer {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the name of the class to analyze:");
        String className = scanner.nextLine();

        String classDescription = ClassAnalyzer.getClassDescription(className);
        System.out.println("\nClass Description:");
        System.out.println(classDescription);

        scanner.close();
    }
}
