package cat.escolapia;

import java.util.Scanner;
import java.io.File;

public class Main {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("====== Selecciona una opció ======");
        System.out.println("1 - Missió 1: PIN BREAKER");
        System.out.println("2 - Missió 2: ALPHANUMERIC HUNTER");
        System.out.println("3 - Missió 3: DICTIONARY ATTACK");
        System.out.println("4 - Missió 4: FINAL BOSS");
        System.out.println("5 - ESTADÍSTIQUES FINALS");
        System.out.println("===================================");
        int opcioMenu = llegirEnter();

        switch (opcioMenu) {
            case 1:
                BruteForce.ex1();
                break;
            case 2:
                BruteForce.ex2();
                break;
            case 3:
                DictionaryAttack.ex3();
                break;
            case 4:
                PasswordVariants.ex4();
                break;
            case 5:
                System.out.println("Les estadístiques finals estan a ");
                break;

            default:
                System.out.println("Error, torna-ho a intentar!");
                break;
        }

    }

    public static int llegirEnter() {
        int numero = 0;
        try {
            numero = sc.nextInt();
            sc.nextLine(); 
        } catch (Exception e) {
            System.out.println("Error, torna-ho a intentar!");
            sc.nextLine();
        }

        return numero;
    }
    
}
