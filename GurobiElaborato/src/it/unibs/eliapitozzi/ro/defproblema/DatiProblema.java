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

    /**
     * h, n, g, omega, teta, tau e la lista computer sono forniti dal testo.
     * k è numero di var originali del pb.
     */
    List<ComputerModel> reteComputer = new ArrayList<>();
    private int h;
    private int n;
    private double g;
    private double omega;
    private double teta;
    private int tau;
    private int k;

    /**
     * Costruisce l'istanza dalla lettura del file fornito.
     * E elabora il corrispondente problema in formato lp.
     */
    public DatiProblema() {
        leggiEEstraiDati();
        // k è il numero di variabili originali del problema
        // Calcolato sommando al numero di var decisionali associato ai pc la var w
        k = reteComputer.size() + 1;

        elaboraFileModelloLP();
    }

    public int getN() {
        return n;
    }

    public int getK() {
        return k;
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
            w.println("\nMinimize w\n");

            // Aggiungo vincoli
            w.println("Subject To");

            // Vincolo w >= xi
            for (int i = 0; i < n; i++) {
                String label = String.format("  c_di_w_e_x%d: ", i + 1);
                w.printf(label + "w - x%d >= 0\n", i + 1);
            }

            // Salto riga tra un tipo di vincolo e un altro
            w.println();

            // Vincolo estremi percentuali forniti per ogni xi, entro omega e teta
            for (int i = 0; i < n; i++) {
                String labelEstrMin = String.format("  c_di_x%d_e_omega: ", i + 1);
                String labelEstrMax = String.format("  c_di_x%d_e_teta: ", i + 1);

                w.printf(labelEstrMin + "x%d - %.02f >= 0\n", i + 1, omega);
                w.printf(labelEstrMax + "x%d <= %.02f\n", i + 1, teta);
            }

            // Salto riga tra un tipo di vincolo e un altro
            w.println();

            // Vincolo per percentuale totale, tutte le xi insieme danno 100%
            w.print("  c_delle_xi_somma_delle_percentuali:");
            for (int i = 0; i < n - 1; i++) {
                w.printf(" x%d +", i + 1);
            }
            w.printf(" x%d = 100,0\n", n);

            // Salto riga tra un tipo di vincolo e un altro
            w.println();

            // Vincolo temporale, tempo di esecuzione massimo ammesso
            double totDatiInGB = h * g * 1000;

            // Calcolo coefficiente per ogni xi
            // (xi * 100 * totDati / alfa)  +  (xi * 100 * totDati / beta)
            // Ho raccolto 100 e totDati, poi calcolato la somma dei reciproci di alfa e beta
            for (int i = 0; i < n; i++) {

                double coef = totDatiInGB / 100 *
                        (1. / reteComputer.get(i).getAlfa() + 1. / reteComputer.get(i).getBeta());

                String label = String.format("  c_di_x%d_tempo_max_esec:  ", i + 1);

                w.printf(label + "%.02f x%d <= %d\n", coef, i + 1, tau);
            }

            // Salto riga tra un tipo di vincolo e un altro
            w.println();

            // Aggiungo intervallo di validita variabili xi, tra 0 e 100, perchè percentuale
            w.println("Bounds");
            for (int i = 0; i < n; i++) {
                w.printf("  0,0 <= x%d <= 100,0\n", i + 1);
            }

            // Salto riga
            w.println();

            // Concludo file LP
            w.println("End");

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
