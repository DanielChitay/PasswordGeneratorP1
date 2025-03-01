package mipatito;

import java.util.Scanner;

public class PasswordGenerator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("=== Menú de Generador de Contraseñas ===");
            System.out.println("1. Versión 1 (Default)");
            System.out.println("2. Versión 2 (Configurable)");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    ejecutarVersion1();
                    break;
                case 2:
                    ejecutarVersion2();
                    break;
                case 3:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        } while (opcion != 3);

        scanner.close();
    }

    public static void ejecutarVersion1() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese la longitud de la contraseña: ");
        int n = scanner.nextInt();

        if (n < 8) {
            System.out.println("La contraseña debe tener un mínimo de 8 caracteres.");
        } else {
            String contraseña = PasswordGeneratorV1.generatePassword(n);
            System.out.println("Contraseña generada: " + contraseña);
        }
    }

    public static void ejecutarVersion2() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("¿Restringir mínimo de dígitos? (true/false): ");
        boolean restrictMinDigits = scanner.nextBoolean();
        int minDigits = 0;
        if (restrictMinDigits) {
            System.out.print("Ingrese la cantidad mínima de dígitos: ");
            minDigits = scanner.nextInt();
        }

        System.out.print("¿Restringir mínimo de letras mayúsculas? (true/false): ");
        boolean restrictMinUpperCaseLetters = scanner.nextBoolean();
        int minUpperCaseLetters = 0;
        if (restrictMinUpperCaseLetters) {
            System.out.print("Ingrese la cantidad mínima de letras mayúsculas: ");
            minUpperCaseLetters = scanner.nextInt();
        }

        System.out.print("¿Restringir mínimo de letras minúsculas? (true/false): ");
        boolean restrictMinLowerCaseLetters = scanner.nextBoolean();
        int minLowerCaseLetters = 0;
        if (restrictMinLowerCaseLetters) {
            System.out.print("Ingrese la cantidad mínima de letras minúsculas: ");
            minLowerCaseLetters = scanner.nextInt();
        }

        System.out.print("¿Restringir mínimo de caracteres no alfanuméricos? (true/false): ");
        boolean restrictMinNonAlphanumericCharacters = scanner.nextBoolean();
        int minNonAlphanumericCharacters = 0;
        if (restrictMinNonAlphanumericCharacters) {
            System.out.print("Ingrese la cantidad mínima de caracteres no alfanuméricos: ");
            minNonAlphanumericCharacters = scanner.nextInt();
        }

        System.out.print("Ingrese la longitud mínima de la contraseña: ");
        int minLength = scanner.nextInt();


        String contraseña = PasswordGeneratorV2.generatePassword(minLength, restrictMinDigits, minDigits,
                restrictMinUpperCaseLetters, minUpperCaseLetters,
                restrictMinLowerCaseLetters, minLowerCaseLetters,
                restrictMinNonAlphanumericCharacters, minNonAlphanumericCharacters);

        System.out.println("Contraseña generada: " + contraseña);
    }
}

class PasswordGeneratorV1 {

    public static String generatePassword(int longitud) {
        String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()";
        StringBuilder password;
        boolean esValida;

        do {
            password = new StringBuilder();
            for (int i = 0; i < longitud; i++) {
                int indice = (int) (Math.random() * caracteres.length());
                password.append(caracteres.charAt(indice));
            }
            esValida = esFuerte(password.toString());
        } while (!esValida);

        return password.toString();
    }

    public static boolean esFuerte(String contraseña) {
        int mayusculas = 0;
        int minusculas = 0;
        int numeros = 0;
        int especiales = 0;

        for (int i = 0; i < contraseña.length(); i++) {
            char c = contraseña.charAt(i);
            if (Character.isUpperCase(c)) {
                mayusculas++;
            } else if (Character.isLowerCase(c)) {
                minusculas++;
            } else if (Character.isDigit(c)) {
                numeros++;
            } else if (!Character.isLetterOrDigit(c)) {
                especiales++;
            }
        }

        return (mayusculas > 0 && minusculas > 0 && numeros > 0 && especiales > 0);
    }
}

class PasswordGeneratorV2 {

    public static String generatePassword(int minLength, boolean restrictMinDigits, int minDigits,
                                          boolean restrictMinUpperCaseLetters, int minUpperCaseLetters,
                                          boolean restrictMinLowerCaseLetters, int minLowerCaseLetters,
                                          boolean restrictMinNonAlphanumericCharacters, int minNonAlphanumericCharacters) {
        String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()";
        StringBuilder password;
        boolean esValida;

        do {
            password = new StringBuilder();
            for (int i = 0; i < minLength; i++) {
                int indice = (int) (Math.random() * caracteres.length());
                password.append(caracteres.charAt(indice));
            }
            esValida = esFuerte(password.toString(), restrictMinDigits, minDigits,
                    restrictMinUpperCaseLetters, minUpperCaseLetters,
                    restrictMinLowerCaseLetters, minLowerCaseLetters,
                    restrictMinNonAlphanumericCharacters, minNonAlphanumericCharacters);
        } while (!esValida);

        return password.toString();
    }

    public static boolean esFuerte(String contraseña, boolean restrictMinDigits, int minDigits,
                                   boolean restrictMinUpperCaseLetters, int minUpperCaseLetters,
                                   boolean restrictMinLowerCaseLetters, int minLowerCaseLetters,
                                   boolean restrictMinNonAlphanumericCharacters, int minNonAlphanumericCharacters) {
        int mayusculas = 0;
        int minusculas = 0;
        int numeros = 0;
        int especiales = 0;

        for (int i = 0; i < contraseña.length(); i++) {
            char c = contraseña.charAt(i);
            if (Character.isUpperCase(c)) {
                mayusculas++;
            } else if (Character.isLowerCase(c)) {
                minusculas++;
            } else if (Character.isDigit(c)) {
                numeros++;
            } else if (!Character.isLetterOrDigit(c)) {
                especiales++;
            }
        }

      
        boolean cumpleRestricciones = true;

        if (restrictMinDigits && numeros < minDigits) {
            cumpleRestricciones = false;
        }
        if (restrictMinUpperCaseLetters && mayusculas < minUpperCaseLetters) {
            cumpleRestricciones = false;
        }
        if (restrictMinLowerCaseLetters && minusculas < minLowerCaseLetters) {
            cumpleRestricciones = false;
        }
        if (restrictMinNonAlphanumericCharacters && especiales < minNonAlphanumericCharacters) {
            cumpleRestricciones = false;
        }

        return cumpleRestricciones;
    }
}