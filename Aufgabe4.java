import java.util.Scanner;

public class Aufgabe4 {
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

            // Binäre Suche
            int li = 0, re = words.length - 1, gefundenBei = -1;
            while (li <= re) {
                int mi = (li + re) / 2; // Mitte berechnen
                if (words[mi].equals(suchwort)) {
                    gefundenBei = mi; // Wort gefunden
                    break;
                } else if (words[mi].compareTo(suchwort) < 0) {
                    li = mi + 1; // Nach rechts weitersuchen
                } else {
                    re = mi - 1; // Nach links weitersuchen
                }
            }

            // Ergebnis prüfen und Position im ursprünglichen Array finden
            if (gefundenBei != -1) {
                // Wort gefunden: Ursprüngliche Position suchen
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
}
