import java.util.Scanner;

public class Aufgabe5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int arraySize;

        // Sicherstellen, dass mindestens 6 Wörter eingegeben werden
        do {
            System.out.print("Wie viele Wörter sollen eingegeben werden (mindestens 6): ");
            arraySize = scanner.nextInt();
        } while (arraySize < 6);

        scanner.nextLine(); // Eingabepuffer leeren

        // Array für die Wörter erstellen
        String[] words = new String[arraySize];
        String[] originalWords = new String[arraySize]; // Ursprüngliche Reihenfolge speichern

        // Wörter einlesen
        System.out.println("Geben Sie die Wörter ein:");
        for (int i = 0; i < arraySize; i++) {
            System.out.print("Wort " + (i + 1) + ": ");
            String word = scanner.nextLine();
            words[i] = word;
            originalWords[i] = word; // Ursprüngliche Reihenfolge speichern
        }

        // Wörter sortieren (für die binäre Suche notwendig)
        java.util.Arrays.sort(words);

        boolean weitereSuche;
        do {
            // Suchwort eingeben
            System.out.print("Welches Wort soll gesucht werden: ");
            String suchwort = scanner.nextLine();

            // Aufruf der Binärsuchmethode
            int gefundenBei = binarySearch(words, suchwort, 0, words.length - 1);

            // Ergebnis prüfen und Position im ursprünglichen Array finden
            if (gefundenBei != -1) {
                // Ursprüngliche Position suchen
                int originalIndex = -1;
                for (int i = 0; i < originalWords.length; i++) {
                    if (originalWords[i].equals(suchwort)) {
                        originalIndex = i;
                        break; // Sobald gefunden, Schleife verlassen
                    }
                }

                System.out.println("Wort \"" + suchwort + "\" gefunden an Position (im ursprünglichen Array): " + originalIndex);
            } else {
                System.out.println("Wort \"" + suchwort + "\" nicht gefunden.");
            }

            // Fragen, ob eine weitere Suche durchgeführt werden soll
            System.out.print("Möchten Sie ein weiteres Wort suchen? (ja/nein): ");
            weitereSuche = scanner.nextLine().equalsIgnoreCase("ja");
        } while (weitereSuche);

        scanner.close();
    }

    /**
     * Methode zur Durchführung der binären Suche.
     *
     * @param words  Das sortierte Array.
     * @param target Das zu suchende Wort.
     * @param li     Der linke Index (Startpunkt).
     * @param re     Der rechte Index (Endpunkt).
     * @return Die Position des Wortes im sortierten Array oder -1, wenn nicht gefunden.
     */
    public static int binarySearch(String[] words, String target, int li, int re) {
        while (li <= re) {
            int mi = (li + re) / 2; // Mitte berechnen
            if (words[mi].equals(target)) {
                return mi; // Wort gefunden
            } else if (words[mi].compareTo(target) < 0) {
                li = mi + 1; // Nach rechts weitersuchen
            } else {
                re = mi - 1; // Nach links weitersuchen
            }
        }
        return -1; // Wort nicht gefunden
    }
}
