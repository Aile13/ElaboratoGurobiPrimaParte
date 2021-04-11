package it.unibs.eliapitozzi.ro.fileoutput;

import gurobi.GRB;
import gurobi.GRBException;
import gurobi.GRBVar;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * Scrive il file txt con le risposte ai quesiti
 *
 * @author Elia
 */
public class RisposteQuesiti {
    double valoreFunzObbiettivo;
    GRBVar[] soluzDiBaseOttima;

    public RisposteQuesiti(double valoreFunzObbiettivo, GRBVar[] soluzioneDiBaseOttima) {
        this.valoreFunzObbiettivo = valoreFunzObbiettivo;
        this.soluzDiBaseOttima = soluzioneDiBaseOttima;
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
            writer.printf("funzione obiettivo = %.04f\n", valoreFunzObbiettivo);

            writer.println("soluzione di base ottima: [");
            /*for (int i = 0; i < soluzDiBaseOttima.length - 1; i++) {
                writer.printf("%.04f, ", soluzDiBaseOttima[i].get(GRB.DoubleAttr.X));
            }
            writer.printf("%.04f]\n",
                    soluzDiBaseOttima[soluzDiBaseOttima.length - 1].get(GRB.DoubleAttr.X));
*/
            writer.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
