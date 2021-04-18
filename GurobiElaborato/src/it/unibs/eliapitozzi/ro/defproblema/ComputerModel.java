package it.unibs.eliapitozzi.ro.defproblema;

/**
 * Model di un singolo pc.
 *
 * @author Elia
 */
public class ComputerModel {
    private final double alfa;
    private final double beta;

    public ComputerModel(double alfa, double beta) {
        this.alfa = alfa;
        this.beta = beta;
    }

    public double getAlfa() {
        return alfa;
    }

    public double getBeta() {
        return beta;
    }

    @Override
    public String toString() {
        return "ComputerModel{" +
                "alfa=" + alfa +
                ", beta=" + beta +
                '}';
    }
}
