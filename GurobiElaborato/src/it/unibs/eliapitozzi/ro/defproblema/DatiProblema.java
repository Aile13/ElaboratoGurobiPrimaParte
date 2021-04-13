package it.unibs.eliapitozzi.ro.defproblema;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Legge file fornito e costruisce il modello in formato lp.
 *
 * @author Elia
 */
public class DatiProblema {

    private static final String DATA_FILE_PATH = "GurobiElaborato/fileForniti/istanza_singolo55.txt";
    private static final String MODEL_FILE_PATH = "GurobiElaborato/src/it/unibs/eliapitozzi/ro/defproblema/problema.lp";

    List<ComputerModel> reteComputer = new ArrayList<>();
    private int h;
    private int n;
    private double g;
    private double omega;
    private double teta;
    private int tau;

    /**
     * Costruisce l'istanza dalla lettura del file fornito.
     * E elabora il corrispondente problema in formato lp.
     */
    public DatiProblema() {
        leggiEEstraiDati();
        elaboraFileModelloLP();
    }

    public int getN() {
        return n;
    }

    private void elaboraFileModelloLP() {

        PrintWriter w;
        try {
            w = new PrintWriter(MODEL_FILE_PATH);

            // Stampa descrizione file
            w.println("\\ LP file per definizione problema ottimizzazione di elaborato");
            w.println("\\ Variabili tutte continue");


            // Stampa def problema in formato lp

            // Stampa funzione obiettivo
            w.println("\nMinimize w ");

            // Aggiungo vincoli
            w.println("Subject To");

            // Vincolo w >= xi
            for (int i = 0; i < n; i++) {
                w.printf("  w - x%d >= 0\n", i + 1);
            }

            // Vincolo estremi percentuali forniti per ogni xi, entro omega e teta
            for (int i = 0; i < n; i++) {
                w.printf("  x%d - %.02f >= 0\n", i + 1, omega);
                w.printf("  x%d <= %.02f\n", i + 1, teta);
            }

            // Vincolo per percentuale totale, tutte le xi insieme danno 100%
            w.print(" "); // Salta uno spazio.
            for (int i = 0; i < n - 1; i++) {
                w.printf(" x%d +", i + 1);
            }
            w.printf(" x%d = 100,0\n", n);

            // Vincolo temporale, tempo di esecuzione massimo ammesso
            double totDatiInGB = h * g * 1000;

            // Calcolo coefficiente per ogni xi
            // (xi * 100 * totDati / alfa)  +  (xi * 100 * totDati / beta)
            // ho raccolto 100 e totDati
            for (int i = 0; i < n ; i++) {
                double coef = totDatiInGB / 100 * (1. / reteComputer.get(i).getAlfa() + 1. / reteComputer.get(i).getBeta());
                w.printf("  %.02f x%d <= %d\n", coef, i + 1, tau);
            }

            // Aggiungo intervallo di validita variabili xi, tra 0 e 100, perchè percentuale
            w.println("Bounds");
            for (int i = 0; i < n; i++) {
                w.printf("  0,0 <= x%d <= 100,0\n", i + 1);
            }

            // Concludo file LP
            w.println("\nEnd");

            w.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void leggiEEstraiDati() {
        try {
            BufferedReader in = new BufferedReader(new FileReader(DATA_FILE_PATH));

            // Legge e estrae parametri "singoli"
            h = Integer.parseInt(parseIntsAndFloats(in.readLine()).get(0));
            n = Integer.parseInt(parseIntsAndFloats(in.readLine()).get(0));
            g = Double.parseDouble(parseIntsAndFloats(in.readLine()).get(0));
            omega = Double.parseDouble(parseIntsAndFloats(in.readLine()).get(0));
            teta = Double.parseDouble(parseIntsAndFloats(in.readLine()).get(0));
            tau = Integer.parseInt(parseIntsAndFloats(in.readLine()).get(0));

            // salta la riga vuota e titolo
            in.readLine();
            in.readLine();

            // Legge e estrae dati computer
            for (int i = 0; i < n; i++) {

                ArrayList<String> valoriPC = parseIntsAndFloats(in.readLine());

                double alfa = Double.parseDouble(valoriPC.get(1));
                double beta = Double.parseDouble(valoriPC.get(2));

                reteComputer.add(new ComputerModel(alfa, beta));
            }

            in.close();

        } catch (FileNotFoundException e) {
            System.err.println("File non trovato in percorso: " + DATA_FILE_PATH);
        } catch (IOException e) {
            System.err.println("File non leggibile in percorso: " + DATA_FILE_PATH);
        }
    }

    private ArrayList<String> parseIntsAndFloats(String raw) {

        ArrayList<String> listBuffer = new ArrayList<>();

        Pattern p = Pattern.compile("[0-9]*\\.?[0-9]+");

        Matcher m = p.matcher(raw);

        while (m.find()) {
            listBuffer.add(m.group());
        }

        return listBuffer;
    }

    @Override
    public String toString() {
        return "DatiProblema{" +
                "reteComputer=" + reteComputer +
                ", h=" + h +
                ", n=" + n +
                ", g=" + g +
                ", omega=" + omega +
                ", teta=" + teta +
                ", tau=" + tau +
                '}';
    }
}
