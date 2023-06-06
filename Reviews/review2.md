
# Peer-Review 2: UML

Daniel Shala, Jurij Scandola, Jacopo Viganò, Carlo Zenesini

Gruppo 49

Valutazione del diagramma UML delle classi del gruppo 39.

## Lati positivi

- Tenere traccia dello stato in cui si trova l'handler è un'ottima trovata per gestire le possibili mosse dei giocatori e prevenire mosse invalide prima di aggiornare il server, e anche tenere tracci del tipo di connessione all'interno handler e clientHandler risulta utile a facilitare la comunicazione con il server.
- L'idea di separare, tramite la classe Lobby, le varie partite in corso sul server è ottima per poter gestire la comunicazione con i vari clientHandler connessi
- Stesso discorso anche per le enumerazioni dello stato e della connessione.

## Lati negativi

- La classe RemotePlayer potrebbe non essere necessaria come entità separata da player: piuttosto che contenere un'istanza, si può estendere la classe Player aggiungendo gli attributi e i metodi necessari.

## Confronto tra le architetture

- Nella nostra architettura, l'estensione RemotePlayer fa parte del modello lato clientHandler ed è una sottoclasse di Player. La vostra scelta di utilizzare un Handler esteso in base al tipo di connessione risulta essere un approccio migliore rispetto al nostro di avere un unico Handler contenente il tipo di connessione.

