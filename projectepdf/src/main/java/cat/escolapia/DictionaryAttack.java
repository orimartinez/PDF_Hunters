package cat.escolapia;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class DictionaryAttack {

    public static void ex3() {

        String rutaPdf = "projectepdf/documents/03_contrasenya_director.pdf";
        String rutaDiccionari = "projectepdf/src/main/resources/diccionari.txt";

        // Creo una llista on guardaré les paraules del diccionari
        ArrayList<String> paraules = new ArrayList<>();

        // Llegeixo el diccionari línia per línia
        try (Scanner lector = new Scanner(new File(rutaDiccionari))) {
            while (lector.hasNextLine()) {
                // Llegeixo una línia i trec els espais
                String paraula = lector.nextLine().trim();

                // Només guardo les línies que no estan buides
                if (!paraula.isEmpty()) {
                    paraules.add(paraula);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("No s'ha trobat el fitxer del diccionari.");
            return;
        }

        // Comprovem que el diccionari no estigui buit
        if (paraules.isEmpty()) {
            System.out.println("El diccionari és buit.");
            return;
        }

        // Comencem amb la comprovació de la contrasenya

        int intents = 0;
        String passwordTrobada = "";
        long inici = System.nanoTime();

        // Recorro les paraules del diccionari una per una
        for (String intentContrasenya : paraules) {

            intents++;

            // Comprovo si la paraula permet obrir el PDF
            if (PDFChecker.comprovarPassword(rutaPdf, intentContrasenya)) {
                // Guardo la contrasenya trobada i paro el bucle
                passwordTrobada = intentContrasenya;
                break;
            }
        }

        // Calculo el temps que ha durat la missió (en segons)
        double temps = (System.nanoTime() - inici) / 1_000_000_000.0;

        // Mostro quantes paraules conté el diccionari i quants intents he fet
        System.out.println("Paraules al diccionari: " + paraules.size());
        System.out.println("Intents: " + intents);

        // Mostro el temps total
        System.out.println("Temps: " + temps + " segons");

        // Indico si he trobat la contrasenya
        if (!passwordTrobada.isEmpty()) {
            System.out.println("Contrasenya trobada: " + passwordTrobada);
        } else {
            System.out.println("No s'ha trobat la contrasenya.");
        }
    }
}