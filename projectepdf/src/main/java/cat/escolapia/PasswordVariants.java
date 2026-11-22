package cat.escolapia;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class PasswordVariants {

    public static void ex4() {

        String rutaPdf = "projectepdf/documents/04_arxiu_ultrasecret.pdf";
        String rutaDiccionari = "projectepdf/src/main/resources/diccionari.txt";
        ArrayList<String> paraules = new ArrayList<>();

        // Llegim el fitxer, igual que a la missió 3
        try (Scanner lector = new Scanner(new File(rutaDiccionari))) {
            // Llegeixo el fitxer línia per línia
            while (lector.hasNextLine()) {
                // Trec els espais sobrants del principi i del final
                String paraula = lector.nextLine().trim();

                // Guardo la paraula només si la línia no està buida
                if (!paraula.isEmpty()) {
                    paraules.add(paraula);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("No s'ha trobat el fitxer del diccionari.");
            return;
        }

        // Comprovo que el diccionari no estigui buit
        if (paraules.isEmpty()) {
            System.out.println("El diccionari és buit.");
            return;
        }

        int variantsGenerades = 0;
        int variantsComprovades = 0;
        String passwordTrobada = "";
        long inici = System.nanoTime();

        // Recorro l'array de paraules
        for (int j = 0; j < paraules.size(); j++) {
            // Agafo la paraula de la posició actual
            String paraula = paraules.get(j);

            // Creo una versió de la paraula amb la inicial en majúscula
            String paraulaAmbInicialMajuscula = Character.toUpperCase(paraula.charAt(0)) + paraula.substring(1);

            // Genero les combinacions de dos dígits, des de 00 fins a 99
            for (int i = 0; i <= 99; i++) {
                // Converteixo el número en un text de dos dígits
                String dosDigits = String.format("%02d", i);

                // Creo la variant afegint els dos dígits al final
                String intentContrasenya = paraulaAmbInicialMajuscula + dosDigits;

                variantsGenerades++;
                variantsComprovades++;

                // Comprovo si la variant obre el PDF
                if (PDFChecker.comprovarPassword(rutaPdf, intentContrasenya)) {
                    // Guardo la contrasenya correcta i aturo el bucle dels dígits
                    passwordTrobada = intentContrasenya;
                    break;
                }
            }

            // Paro també el bucle de les paraules si ja he trobat la contrasenya
            if (!passwordTrobada.isEmpty()) {
                break;
            }
        }

        // Calculo el temps que ha durat la missió (en segons)
        double temps = (System.nanoTime() - inici) / 1_000_000_000.0;

        // Mostro les paraules llegides i les variants generades i comprovades
        System.out.println("Paraules al diccionari: " + paraules.size());
        System.out.println("Variants generades: " + variantsGenerades);
        System.out.println("Variants comprovades: " + variantsComprovades);

        // Mostro el temps que ha durat la missió
        System.out.println("Temps: " + temps + " segons");

        // Indico si he trobat la contrasenya
        if (!passwordTrobada.isEmpty()) {
            System.out.println("Contrasenya trobada: " + passwordTrobada);
        } else {
            System.out.println("No s'ha trobat la contrasenya.");
        }
    }
}