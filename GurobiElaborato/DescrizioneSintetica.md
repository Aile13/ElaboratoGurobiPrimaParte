# Descrizione Elaborato Gurobi di Elia Pitozzi

Dopo la lettura del testo, si è passati alla definizione del corrispondente modello matematico.
Il modello da me scritto e usato è il seguente:


Il programma Java che ho realizzato risolve il problema eseguendo questi punti principali:
* Legge i dati del pb dal file _istanza_55_singolo.txt_.
* Successivamente scrive in maniera automatica il file _problema.lp_ con i dati ricavati, e struttura la funzione obbiettivo, i vincoli e gli estremi come il modello sopra riportato.
* Quest'ultimo file viene usato come input per l'esecuzione del solver **Gurobi**.
* Infine, ricavata la soluzione del modello, si procede alla determinazione delle risposte e al loro output formattato nel file _risposte_gruppo55.txt_.  