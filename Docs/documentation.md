# Documentazione

## Model

### GameState

Semplice classe che salva lo stato corrente del gioco, ovvero lo stato dei giocatori, il turno, il sacchetto e la tavola di gioco.

### Pocket

Rappresenta il saccheto: è costituito da un array di Tile da cui si possono solo estrarre e non inserire dopo la creazione.

### Tile

Rappresenta la tesserina di gioco, le cui proprietà sono: Id, pickable(se può essere presa dal giocatore) e TileType(tipo).

### TileType

Enumerazione che rappresenta colore:tipo contente anche un identificativo per le immagini associate.

### Board

Rappresenta la tavola di gioco, è costituita da un array bidimensionale di Tile e 2 CommonGoalCard.

### CommonGoalCard

Enumerazione per le carte obiettivo comune a cui viene associata la carta più il punteggio ottenibile.

### CommonGoalCardCondition

Interfaccia che rappresenta la Carta obiettivo comune generica.

### CgcXXXX

Implementazione della carta obiettivo comune specifica XXXX (nomi sono ancora in via di definizione).

### Player

Classe che rappresenta il giocatore, contiene il punteggio corrente (ottenuto solo tramite CommonGoalCard), l'id, la propria PersonalGoalCard, il nome utente, una coppia di boolean per verificare se ha già ottenuto una PersonalGoalCard.

### Bookshelf

Rappresenta la shelf del giocatore, contiene un array bidimensionale di Tile. Le tile possono essere inserite solo per colonna.

### PersonalGoalCard

Record che associa una Coordinates(oggetto coordinate x,y) ad un colore per ogni PersonalGoalCard le quali vengono caricate tramite deserializzazione di file JSON.

## Controller

### GameController

Il controller del gameplay, inizializza e inizia la partita.

### TurnController (deprecated)

Controller del turno che si occupa di aggiornare la view in base alla situazione di gioco. Per ora utilizza il pattern deprecato dell'it.polimi.observer in java.it.polimi.util
