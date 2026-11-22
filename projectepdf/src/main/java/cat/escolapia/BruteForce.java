package cat.escolapia;

public class BruteForce {

    public static void ex1() {

        String rutaPdf = "projectepdf/documents/01_caixa_forta.pdf";
        int intents = 0;
        String passwordTrobada = "";

        long inici = System.nanoTime();

        // Provo tots els números possibles, des de 0000 fins a 9999
        for (int i = 0; i <= 9999; i++) {
            // Converteixo cada número en un text de quatre xifres
            String intentContrasenya = String.format("%04d", i);
            intents++;

            // Comprovo si la contrasenya deixa obrir el PDF
            if (PDFChecker.comprovarPassword(rutaPdf, intentContrasenya)) {
                // Guardo la contrasenya correcta i paro el bucle amb el break
                passwordTrobada = intentContrasenya;
                break;
            }
        }

        // Calculo el temps que ha durat la missió, (en segons)
        double temps = (System.nanoTime() - inici) / 1_000_000_000.0;

        // Mostro els resultats de la primera missió
        System.out.println("Intents: " + intents);
        System.out.println("Temps: " + temps + " segons");
        System.out.println("Contrasenya trobada: " + passwordTrobada);
    }

    public static void ex2() {
        String rutaPdf = "projectepdf/documents/02_projecte_secret.pdf";

        char primeraLletra = 'c';
        String lletresPossibles = "aeioutr";

        int intents = 0;
        String passwordTrobada = "";

        long inici = System.nanoTime();

        // Provo cada lletra possible a la segona posició
        for (int j = 0; j < lletresPossibles.length(); j++) {
            char segonaLletra = lletresPossibles.charAt(j);

            // Provo totes les combinacions de quatre números
            for (int i = 0; i <= 9999; i++) {
                String quatreDigits = String.format("%04d", i);

                // Creo la contrasenya amb el patró: primera lletra, segona lletra i quatre números
                String intentContrasenya = "" + primeraLletra + segonaLletra + quatreDigits;

                intents++;

                // Comprovo si aquesta combinació deixa obrir el PDF
                if (PDFChecker.comprovarPassword(rutaPdf, intentContrasenya)) {
                    // Guardo la contrasenya correcta i paro el bucle dels números
                    passwordTrobada = intentContrasenya;
                    break;
                }
            }

            // Si ja he trobat la contrasenya, també paro el bucle de lletres
            if (!passwordTrobada.isEmpty()) {
                break;
            }
        }

        // Calculo les estadístiques de la segona missió
        double temps = (System.nanoTime() - inici) / 1_000_000_000.0;
        int combinacionsPossibles = lletresPossibles.length() * 10000;
        double intentsPerSegon = intents / temps;

        // Mostro les combinacions, els intents, el temps i la velocitat
        System.out.println("Combinacions possibles: " + combinacionsPossibles);
        System.out.println("Intents: " + intents);
        System.out.println("Temps: " + temps + " segons");
        System.out.println("Velocitat: " + intentsPerSegon + " intents/segon");

        // Indico si he trobat la contrasenya
        if (!passwordTrobada.isEmpty()) {
            System.out.println("Contrasenya trobada: " + passwordTrobada);
        } else {
            System.out.println("No s'ha trobat la contrasenya.");
        }
    }
}