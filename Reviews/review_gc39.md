# Peer-Review 1: UML

Daniel Shala, Jurij Scandola, Jacopo Viganò, Carlo Zenesini

Gruppo 49

Valutazione del diagramma UML delle classi del gruppo 39.

## Lati positivi

La scelta di utilizzare la classe Board come memoria dello stato corrente è stata una valida scelta, i dati risultano così centralizzati e di conseguenza serializzazione e salvataggio su file risulteranno più semplici.
Common goal Card e Goal factory permettono la scalabilità e l'espandibilità del gioco in caso di espansione.

## Lati negativi

In Board, un set per le common goal card è eccessivo considerato il fatto che sono soltanto due in tutta la partita.
Personal Goal Card  data la loro condizione predefinita, risulta inefficiente e poco pratico costruirle settando ogni qual volta un Array di Pgtype.

## Confronto tra le architetture

Nella nostra architettura la common goal card non tiene conto dei giocatori che l'hanno completata, ma ciò viene delegato al giocatore. In questo modo il punteggio viene assegnato direttamente al conseguimento dell'obiettivo.
Utilizzare una lista per i giocatori che hanno completato una common  goal card ci permetterebe di risparmiare chiamate nel caso in cui tutti i giocatori abbiano conseguito l'obiettivo e manterrebbe il punteggio e la logica relativa all'assegnamento all'interno della stessa classe.


