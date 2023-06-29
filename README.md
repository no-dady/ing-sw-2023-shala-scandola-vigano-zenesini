# Progetto ingegneria del software 2023

> Game: My Shelfie \
> Team: Shala, Scandola, Viganò, Zenesini \
> Prof: Gianpaolo Cugola

## Implemented Functionalities
| Functionality | Status |
|:-----------------------|:------------------------------------:|
| Regole Semplificate | :white_check_mark: |
| Regole Complete | :white_check_mark: |
| Socket |:white_check_mark: |
| RMI |:white_check_mark: |
| CLI | :white_check_mark: |
| GUI |:white_check_mark: |
| Partite Multiple | :white_check_mark: |
| Resilienza | :white_check_mark: |
| Chat | :negative_squared_cross_mark: |

## Usage

### Client

```bash
SYNTAX:
        java -jar CLIENT_GC49.jar [OPTION]
OPTIONS:
        --cli    Starts client in command line interface
        --gui    Starts client in gui mode
```

### Server

```bash
SYNTAX:
        java -jar SERVER_GC49.jar [--rmi | PORT] [--socket | PORT]
OPTIONS:
        --rmi   Specify rmi port, default is 1900
        --socket        Specify socket port, default is 1337
```
