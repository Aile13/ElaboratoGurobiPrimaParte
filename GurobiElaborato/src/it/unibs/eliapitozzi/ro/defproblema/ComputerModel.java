package it.unibs.eliapitozzi.ro.defproblema;

/**
 * @author Elia
 */
public class ComputerModel {
    private double alfa;
    private double beta;

    public double getAlfa() {
        return alfa;
    }

    public double getBeta() {
        return beta;
    }

    public ComputerModel(double alfa, double beta) {
        this.alfa = alfa;
        this.beta = beta;
    }

    @Override
    public String toString() {
        return "ComputerModel{" +
                "alfa=" + alfa +
                ", beta=" + beta +
                '}';
    }
}
