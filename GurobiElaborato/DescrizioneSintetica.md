# Descrizione Elaborato Gurobi di Elia Pitozzi

Dopo la lettura del testo, si è passati alla definizione del corrispondente modello matematico.
Il modello da me scritto e usato è il seguente:

```text
    Min w
    w >= xi  per i = 1,...,n
    xi >= omega  per i = 1,...,n
    xi <= teta  per i = 1,...,n
    xi * 100 * ( 1000 * h * g ) * ( 1 / alfai + 1 / betai) <= tau  per i = 1,...,n 
    x1 + x2 + ... + xn = 100
    0 <= xi <= 100  per i = 1,...,n
```
_Nota: le var decisionali xi sono in percentuale sul totale di dati da elaborare._

Il programma Java che ho realizzato risolve il problema eseguendo questi punti principali:
* Legge i dati del pb dal file _istanza_55_singolo.txt_.
* Successivamente scrive in maniera automatica il file _problema.lp_ con i dati ricavati, e struttura la funzione obbiettivo, i vincoli e gli estremi come il modello sopra riportato.
* Quest'ultimo file viene usato come input per l'esecuzione del solver **Gurobi**.
* Infine, ricavata la soluzione del modello, si procede alla determinazione delle risposte e al loro output formattato nel file _risposte_gruppo55.txt_.

### Specifiche per l'esecuzione del file _gruppo55.jar_:
* Il file jar per funzionare ha bisogno del file _istanza_55_singolo.txt_ come input. Pertanto questo file deve essere presente nella stessa cartella dove si trova il jar.
* Durante l'esecuzione del jar, viene creato il file _problema.lp_, questo serve all'evoluzione del programma, in quanto il file è l'input di partenza per la risoluzione del modello da parte del solver Gurobi.
* Durante l'avanzamento del solver viene prodotto un relativo file di log chiamato _elaboratoGurobi.txt_.
* In seguito alla risoluzione del modello e dell'esecuzione del jar, viene creato un file _risposte_gruppo55.txt_, contenente le risposte ai quesiti secondo il formato richiesto.