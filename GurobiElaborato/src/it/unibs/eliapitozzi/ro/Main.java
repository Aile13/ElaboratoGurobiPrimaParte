package it.unibs.eliapitozzi.ro;

import gurobi.*;
import it.unibs.eliapitozzi.ro.defproblema.DatiProblema;
import it.unibs.eliapitozzi.ro.fileoutput.RisposteQuesiti;

/**
 * Classe con entry-point del programma.
 * Usa classi ausilarie e Gurobi per risoluzione pb.
 *
 * @author Elia Pitozzi
 */
public class Main {

    private static final String PROBLEM_FILE_LP = "problema.lp";

    public static void main(String[] args) {
        try {
            // Leggo dati da file istanza, costruisco file lp.
            DatiProblema datiProblema = new DatiProblema();

            // Creo ambiente e setto impostazioni
            GRBEnv env = new GRBEnv("elaboratoGurobi.log");

            env.set(GRB.IntParam.Presolve, 0);
            env.set(GRB.IntParam.Method, 0);
            env.set(GRB.DoubleParam.Heuristics, 0.);
            env.set(GRB.DoubleParam.TimeLimit, 180); // max 3 min di attesa


            // Creo model problema da file lp
            GRBModel model = new GRBModel(env, PROBLEM_FILE_LP);

            // Imposto nome modello
            model.set(GRB.StringAttr.ModelName, " pc bank balancer ");

            // Ottimizza il modello
            model.optimize();

            // Status esecuzione
            System.out.println(
                    "\nEsito esecuzione: " +
                            (model.get(GRB.IntAttr.Status) == GRB.Status.OPTIMAL ?
                                    "Soluzione ottimale trovata." :
                                    "Soluzione ottimale non trovata.")
            );

            // Estraggo varibili e mostro
            GRBVar[] vars = model.getVars();
            System.out.println("\nVariabili di modello e loro valore:");

            for (GRBVar var : vars) {
                System.out.printf("%s = %.04f in base: %d CCr: %.02f, in model: %d\n",
                        var.get(GRB.StringAttr.VarName), var.get(GRB.DoubleAttr.X), var.get(GRB.IntAttr.VBasis), var.get(GRB.DoubleAttr.RC), var.index());
            }


            // Estraggo funz obiettivo e mostro
            double objVal = model.get(GRB.DoubleAttr.ObjVal);
            System.out.printf("\nValore funzione obiettivo: %.04f\n", objVal);

            System.out.println(model.getJSONSolution());


            // Stampa il file di output, risposte ai quesiti
            RisposteQuesiti risposteQuesiti = new RisposteQuesiti(model, datiProblema);
            risposteQuesiti.stampaFileRisposte();

            // Libera le risorse associate a modello ed env
            model.dispose();
            env.dispose();

        } catch (GRBException e) {
            System.out.println("Error code: " + e.getErrorCode() + ". " + e.getMessage());
        }
    }
}
