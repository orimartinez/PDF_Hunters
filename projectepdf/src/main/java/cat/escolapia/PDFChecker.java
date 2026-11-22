package cat.escolapia;

import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;

public class PDFChecker {
    
    static boolean comprovarPassword(String rutaPdf, String password) {
        File fitxer = new File(rutaPdf);

        try (PDDocument document = Loader.loadPDF(fitxer, password)) {
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
