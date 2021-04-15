package it.unibs.eliapitozzi.ro.fileoutput;

import gurobi.*;
import it.unibs.eliapitozzi.ro.defproblema.DatiProblema;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Scrive il file txt con le risposte ai quesiti
 *
 * @author Elia
 */
public class RisposteQuesiti {
    private static final String PATH_ANSWER_FILE = "GurobiElaborato/risposte_gruppo55.txt";
    private GRBModel model;
    private DatiProblema datiPb;

    public RisposteQuesiti(GRBModel model, DatiProblema datiProblema) {
        this.model = model;
        this.datiPb = datiProblema;
    }

    public void stampaFileRisposte() {
        try {
            PrintWriter writer = new PrintWriter(PATH_ANSWER_FILE);

            // Stampa intestazione
            writer.println(""); // Salto una riga
            writer.println("GRUPPO 55");
            writer.println("Componenti: Pitozzi");
            writer.println("\n");


            // Stampa risposta quesito 1
            writer.println("QUESITO I:");
            writer.printf("funzione obiettivo = %.04f\n", model.get(GRB.DoubleAttr.ObjVal));

            // Ciclo sui primi k elementi del vettore vars, ovvero le mie var originali
            writer.print("soluzione di base ottima: [");
            for (int i = 0; i < datiPb.getK() - 1; i++) {
                writer.printf("%.04f, ", model.getVar(i).get(GRB.DoubleAttr.X));
            }
            writer.printf("%.04f]\n", model.getVar(datiPb.getK() - 1).get(GRB.DoubleAttr.X));


            // Stampa risposta quesito 2
            writer.println("\nQUESITO II:");

            // Variabili original in base o meno
            writer.print("varibili in base: [");
            for (int i = 0; i < datiPb.getK() - 1; i++) {
                writer.print(model.getVarByName(String.format("x%d", i + 1))
                        .get(GRB.IntAttr.VBasis) == 0 ? "1, " : "0, ");
            }
            writer.print(model.getVarByName(String.format("x%d", datiPb.getK() - 1))
                    .get(GRB.IntAttr.VBasis) == 0 ? "1]\n" : "0]\n");

            // CCR
            writer.print("coefficienti di costo ridotto: [");
            for (int i = 0; i < datiPb.getK() - 1; i++) {
                writer.printf("%.04f, ", model.getVar(i).get(GRB.DoubleAttr.RC));
            }
            writer.printf("%.04f]\n", model.getVar(datiPb.getK() - 1).get(GRB.DoubleAttr.RC));

            // Soluzione ottima multipla
            writer.print("soluzione ottima multipla: ");
            // Controllo se c'è una var non in base con CCR nullo
            boolean multipla = false;
            // Ciclo su tutte le var, guardo tra quelle non in base
            // se loro CCR è nullo
            for (GRBVar var : model.getVars()) {
                if (var.get(GRB.IntAttr.VBasis) != 0) {
                    if (var.get(GRB.DoubleAttr.RC) == 0.) {
                        multipla = true;
                        break;
                    }
                }
            }
            writer.println(multipla ? "Sì" : "No");

            // Soluzione ottima degenere
            writer.print("soluzione ottima degenere: ");
            // Controllo se c'è una var in base nulla.
            boolean degenere = false;
            // Ciclo su tutte le var, guardo tra quelle in base se nulle
            for (GRBVar var : model.getVars()) {
                if (var.get(GRB.IntAttr.VBasis) == 0) {
                    if (var.get(GRB.DoubleAttr.X) == 0.) {
                        degenere = true;
                        break;
                    }
                }
            }
            writer.println(degenere ? "Sì" : "No");

            // Vincoli al vertice all'ottimo
            writer.print("vincoli vertice ottimo: ");
            // Se la var di slack del vincolo corrente è 0,
            // allora il vincolo identifica il vertice ottimo
            List<String> vincoliOttimo = new ArrayList<>();
            for (GRBConstr constr : model.getConstrs()) {
                if (constr.get(GRB.DoubleAttr.Slack) == 0.) {
                    vincoliOttimo.add(constr.get(GRB.StringAttr.ConstrName));
                }
            }
            writer.println(vincoliOttimo);

            // Stampa risposta quesito 3
            writer.println("\nQUESITO III:");


            // Chiudo writer
            writer.close();

        } catch (FileNotFoundException | GRBException e) {
            e.printStackTrace();
        }
    }

}
