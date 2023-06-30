# Documentazione Protocollo Comunicazioni

La comunicazione tra il client e il server è gestita attraverso l'implementazione di un protocollo basato su 4 oggetti che ereditano 2 interfacce. Le interfacce coinvolte sono la ServerInterface e la ClientInterface.

## ServerInterface 

La ServerInterface definisce le funzioni che il client può chiamare, tra cui:
- **Register**: chiamata dal client durante la prima connessione per notificare al server la corretta connessione e avviare la procedura di creazione o join di una lobby. Nel caso in cui ci sia già una registrazione in corso, il client viene messo in coda in attesa del suo turno.
- **SendMessage**: utilizzata dal client per inviare al server un oggetto Action, convertito in una stringa.
- **SendSetupFirst**: utilizzata dal client per inviare al server l'oggetto setupFirst, convertito in una stringa. Questo oggetto consente al server di creare una lobby con il client che ha inviato l'oggetto.
- **SendSetupAll**: utilizzata dal client per inviare al server l'oggetto SetupAll, convertito in una stringa. L'oggetto SetupAll consente al server di far entrare il client nella lobby disponibile.

## ClientInterface

La ClientInterface, invece, definisce le funzioni che il server può chiamare sul client, tra cui:
-  **Send**: utilizzata dal server per inviare al client un oggetto Message, convertito in una stringa. Il client eseguirà quindi le azioni richieste dal server, come passare informazioni sulla partita o impostare gli stati del client.
- **Close**: chiude la connessione del client, sia che si tratti di RMI o di Socket.

## Utilizzo 

La ServerInterface viene implementata dalle classi Server (che ospita le comunicazioni) e ClientSocketMiddleware (il middleware utilizzato dal client per comunicare con il server tramite Socket), mentre la ClientInterface viene implementata dalle classi Client (l'oggetto principale utilizzato dal client in RMI e Socket) e ClientSkeleton (il middleware utilizzato dal server per comunicare con il client tramite Socket).

I middleware per la comunicazione tramite Socket rimangono in ascolto sul DataInputStream su un thread dedicato. Quando ricevono un'informazione, iniziano leggendo un intero che identifica la funzione chiamata dal lato opposto. Successivamente, ricevono l'informazione convertita in una stringa, se la funzione chiamata necessita una argomento. Utilizzando l'intero letto inizialmente, il middleware richiama la funzione corrispondente nell'oggetto appropriato (ClientSkeleton richiama le funzioni del Server, mentre ClientSocketMiddleware richiama le funzioni del Client), garantendo un comportamento coerente sia per la comunicazione RMI che per quella tramite Socket.

All'avvio di ServerApp, vengono creati due thread separati in cui il Server funziona sia in modalità RMI che in modalità Socket. Entrambi gli oggetti utilizzano un attributo comune, una lista di lobby, che consente loro di comunicare con tutte le partite in corso lato server e di crearne di nuove.

All'avvio di ClientApp invece viene chiesto dalla UI scelta quale tipo di comunicazione si preferisce utilizzare, inizializzando cosí Client con il costruttore corretto, poiché Client dispone di due costruttori seperati, uno per le comunicazioni RMI e uno per le comunicazioni Socket.

Quando viene chiamata la funzione di chiusura della comunicazione lato client, se viene utilizzata una comunicazione RMI, il Client si rimuove dal registro tramite UnicastRemoteObject.unexportObject. Se viene utilizzata una comunicazione tramite Socket, il ciclo while che legge dal DataInputStream dentro ClientSocketMiddleware viene interrotto, quindi vengono chiusi lo stream di input, lo stream di output e il socket utilizzati per la connessione, nonché il thread in cui ClientSocketMiddleware è in esecuzione