package it.unibs.eliapitozzi.ro.fileoutput;

import gurobi.GRB;
import gurobi.GRBException;
import gurobi.GRBModel;
import it.unibs.eliapitozzi.ro.defproblema.DatiProblema;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * Scrive il file txt con le risposte ai quesiti
 *
 * @author Elia
 */
public class RisposteQuesiti {
    private GRBModel model;
    private DatiProblema datiProblema;

    public RisposteQuesiti(GRBModel model, DatiProblema datiProblema) {
        this.model = model;
        this.datiProblema = datiProblema;
    }

    public void stampaFileRisposte() {
        try {
            PrintWriter writer = new PrintWriter("GurobiElaborato/risposte_gruppo55.txt");

            // Stampa intestazione
            writer.println("GRUPPO 55");
            writer.println("Componenti: Elia Pitozzi");
            writer.println("\n");

            // Stampa risposta quesito 1
            writer.println("QUESITO I:");
            writer.printf("funzione obiettivo = %.04f\n", model.get(GRB.DoubleAttr.ObjVal));

            writer.print("soluzione di base ottima: [");
            for (int i = 0; i < datiProblema.getN() - 1; i++) {
                writer.printf("%.04f, ", model.getVarByName(String.format("x%d", i + 1)).get(GRB.DoubleAttr.X));
            }
            writer.printf("%.04f]\n",
                    model.getVarByName(String.format("x%d", datiProblema.getN())).get(GRB.DoubleAttr.X));


            // Stampa risposta quesito 2
            /*writer.print("varibili di base: [");
            for (int i = 0; i < datiProblema.getN() - 1; i++) {
                writer.print(model.getVarByName(String.format("x%d", i + 1))
                        .get(GRB.IntAttr.VBasis) == 0 ? "1 " : "0 ");
            }
            writer.print(model.getVarByName(String.format("x%d", datiProblema.getN()))
                    .get(GRB.IntAttr.VBasis) == 0 ? "1]" : "0]");
*/

            writer.close();

        } catch (FileNotFoundException | GRBException e) {
            e.printStackTrace();
        }
    }

}
