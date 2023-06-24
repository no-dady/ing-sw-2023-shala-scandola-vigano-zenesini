# Rete

## ServerSock
classe che implementa interfaccia Server, utilizza metodologia socket per connessione e gestisce i client connessi attraverso socket.

## ClientSock
classe che implementa interfaccia Client, utilizza metodologia socket per connessione e manda messaggi al serverInterface socket.

## ServerRMI
classe che implementa interfaccia Server, utilizza metodologia RMI per connessione e gestisce i client connessi attraverso RMI.

## ClientRMI
classe che implementa interfaccia Client, utilizza metodologia RMI per connessione e manda messaggi al serverInterface  RMI.

## ConnectionType
Enumerazione che definisce I tipi di connessioni possibili, passata all’handler gli dice quale serverInterface gestisce quale client.

## Client
Interfaccia che rappresenta un client e che offre metodi di base per la comunicazione.

## Server
Interfaccia che rappresenta un serverInterface e che offre metodi di base per la comunicazione.

## Hub
Una vera e propria partita del gioco si svolge su un hub, client connessi con tipi di connessione diversi possono stare nello stesso hub grazie all’handler che gestirà appropriatamente  attraverso il nickname e il parametro connection type le comunicazioni con i serverInterface.

## Handler
Classe che rappresenta il punto di connessione tra hub, client e di conseguenza al serverInterface, permettendo a questi di comunicare correttamente.
forse c'è qualcosa che non va nella logica
