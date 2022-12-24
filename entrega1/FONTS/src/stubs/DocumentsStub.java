package FONTS.src.stubs;

import java.util.HashSet;

/**
 * Stub de Document.
 */
public class DocumentsStub {

    /* Con el proposito de este stub nos imaginamos que existen actualmente 5 documentos, con los siguientes paths y contenidos:

        Documento 1: path: d1, contenido: "a b c d e f g h i"
        Documento 2: path: d2, contenido: "a! b! c!"
        Documento 3: path: d3, contenido: "d? e?"
        Documento 4: path: d4, contenido: "f, g. h, i."
        Documento 5: path: d5, contenido: "palabra palabra2 palabra3 palabra4?"

     */

    /**
     * Obté tots els documents.
     * @return HashSet: HashSet amb tots els documents.
     */
    public  HashSet<String> getAllDocuments() {
        HashSet<String> tmpSet = new HashSet<>();
        tmpSet.add("d1");
        tmpSet.add("d2");
        tmpSet.add("d3");
        tmpSet.add("d4");
        tmpSet.add("d5");

        return tmpSet;
    }

    public HashSet<String> getDocumentsParaula(String p) {
        HashSet<String> tmpSet = new HashSet<>();
        switch (p) {
            case "a":
            case "b":
                tmpSet.add("d1");
                tmpSet.add("d2");
                break;
            case "c":
                tmpSet.add( "d1");
                tmpSet.add("d2");
            case "d":
                tmpSet.add("d1");
                tmpSet.add("d3");
                break;
            case "g":
            case "i":
                tmpSet.add("d1");
                tmpSet.add("d4");
                break;
            case "!":
            case "palabra2":
            case "palabra1":
            case ".":
            case ",":
                tmpSet.add("d2");
                break;
        }
        return tmpSet;
    }

    /**
     * Obté tots els documents que inclouen la frase f.
     * @param f Frase a buscar.
     * @return HashSet: HashShet amb els documents que inclouen la frase f.
     */
    public  HashSet<String> getDocumentsFrase(String f) {

        HashSet<String> tmpSet = new HashSet<>();

        switch (f) {
            case "a b c":
                tmpSet.add("d1");
                break;
            case "a!":
                tmpSet.add("d2");
                break;
            case "paraula1 paraula2":
                tmpSet.add("d5");
                break;
            case "g. h,":
                tmpSet.add("d4");
                break;
        }
        return tmpSet;
    }

}
