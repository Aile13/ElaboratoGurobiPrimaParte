package it.unibs.eliapitozzi.ro;

import gurobi.*;
import gurobi.GRB.*;
import it.unibs.eliapitozzi.ro.defproblema.DatiProblema;
import it.unibs.eliapitozzi.ro.fileoutput.RisposteQuesiti;

public class Main {

    private static final String PROBLEM_FILE_LP =
            "GurobiElaborato/src/it/unibs/eliapitozzi/ro/defproblema/problema.lp";

    public static void main(String[] args) {
        try {
            // Leggo dati da file istanza, costruisco file lp.
            //DatiProblema datiProblema = new DatiProblema();

            // Creo ambiente e setto impostazioni
            GRBEnv env = new GRBEnv("elaboratoGurobi.log");

            env.set(IntParam.Presolve, 0);
            env.set(IntParam.Method, 0);
            env.set(DoubleParam.Heuristics, 0.);
            env.set(DoubleParam.TimeLimit, 180); // max 3 min di attesa


            // Creo model problema da file lp
            GRBModel model = new GRBModel(env, PROBLEM_FILE_LP);

            // Ottimizza il modello
            model.optimize();

            // status esecuzione
            System.out.println(
            		"Esito esecuzione: " +
							(model.get(IntAttr.Status) == Status.OPTIMAL ?
                            "Soluzione ottimale trovata.\n" :
                            "Soluzione ottimale non trovata.\n")
            );
            // Estraggo varibili e mostro
            GRBVar[] vars = model.getVars();

            System.out.println("\nVariabili di modello e loro valore:");

            for (GRBVar var : vars) {
                System.out.printf("%s = %.04f\n",
                        var.get(StringAttr.VarName), var.get(DoubleAttr.X));
            }


            // Estraggo funz obiettivo e mostro
            double objVal = model.get(DoubleAttr.ObjVal);
            System.out.printf("\nValore funzione obiettivo: %.04f\n", objVal);

            // Libera le risorse associate a modello ed env
            model.dispose();
            env.dispose();

            // Stampa il file di output risposte ai quesiti
            RisposteQuesiti risposteQuesiti = new RisposteQuesiti(
                    objVal,
                    vars
                    );

            risposteQuesiti.stampaFileRisposte();


			/*// Creazione delle variabili
			GRBVar x = model.addVar(0.0, GRB.INFINITY, 0.0, GRB.CONTINUOUS, "x");
			GRBVar y = model.addVar(0.0, GRB.INFINITY, 0.0, GRB.CONTINUOUS, "y");
			GRBVar z = model.addVar(0.0, GRB.INFINITY, 0.0, GRB.CONTINUOUS, "z");
			
			// Aggiunta della funzione obiettivo: max x + y + 2 z
			GRBLinExpr expr = new GRBLinExpr();
			expr.addTerm(1.0, x);
			expr.addTerm(1.0, y);
			expr.addTerm(2.0, z);
			model.setObjective(expr, GRB.MAXIMIZE);
			
			// Aggiunta del vincolo: x + 2 y + 3 z <= 4
			expr = new GRBLinExpr();
			expr.addTerm(1.0, x);
			expr.addTerm(2.0, y);
			expr.addTerm(3.0, z);
			model.addConstr(expr, GRB.LESS_EQUAL, 4.0, "c0");
			
			// Aggiunta del vincolo: x + y >= 1
			expr = new GRBLinExpr();
			expr.addTerm(1.0, x);
			expr.addTerm(1.0, y);
			model.addConstr(expr, GRB.LESS_EQUAL, 1.0, "c1");
			*/


			/*
			System.out.println(x.get(GRB.StringAttr.VarName) + " " + x.get(GRB.DoubleAttr.X));
			System.out.println(y.get(GRB.StringAttr.VarName) + " " + y.get(GRB.DoubleAttr.X));
			System.out.println(z.get(GRB.StringAttr.VarName) + " " + z.get(GRB.DoubleAttr.X));
			System.out.println("Obj: " + model.get(GRB.DoubleAttr.ObjVal));
			
			
			
			System.out.println(model.getVarByName("x").get(GRB.StringAttr.VarName) + " "
					+ model.getVarByName("x").get(GRB.DoubleAttr.X) + " RC: " + model.getVarByName("x").get(GRB.DoubleAttr.RC));
			System.out.println(model.getVarByName("y").get(GRB.StringAttr.VarName) + " "
					+ model.getVarByName("y").get(GRB.DoubleAttr.X) + " RC: " + model.getVarByName("y").get(GRB.DoubleAttr.RC));
			System.out.println(model.getVarByName("z").get(GRB.StringAttr.VarName) + " " 
					+ model.getVarByName("z").get(GRB.DoubleAttr.X) + " RC: " + model.getVarByName("z").get(GRB.DoubleAttr.RC));
			System.out.println("Obj: " + model.get(GRB.DoubleAttr.ObjVal));
			*/

        } catch (GRBException e) {
            System.out.println("Error code: " + e.getErrorCode() + ". " + e.getMessage());
        }
    }
}
