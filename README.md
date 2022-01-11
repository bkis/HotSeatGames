# 🕹 Hot Seat Games

A simple [CLI](https://en.wikipedia.org/wiki/Command-line_interface) minigames engine for multiple players on the same computer. Created as a **Java learning project** for the students of my OOP programming class at the *[Institute for Digital Humanities](https://github.com/DH-Cologne)* ([University of Cologne](https://uni-koeln.de/)).

![screenshot](doc/screenshot.png)


## How to play

You have **three options** here:

1. Download the (*possibly outdated, probably up to date*) [current release](https://github.com/bkis/HotSeatGames/releases) and run it with `java -jar HotSeatGames.jar`, **OR**
2. Open the project in your favorite Java IDE and run it from there (*for development*), **OR**
3. Use your IDE (or your hacker skills and `javac` 🤓) to create a runnable `.jar` file from the [current project source](https://github.com/bkis/HotSeatGames/archive/main.zip), then run it with `java -jar HotSeatGames.jar`!


## Included games

At this point, the following games are included in *Hot Seat Games* (in order of contribution):

- **[Hyper Typer](https://github.com/bkis/HotSeatGames/tree/main/src/idh/hotseatgames/games/hypertyper)** (by [bkis](https://github.com/bkis))
- **[Black Jack](https://github.com/bkis/HotSeatGames/tree/main/src/idh/hotseatgames/games/blackjack)** (by [cthilo](https://github.com/cthilo))
- **[Rock-Paper-Scissors](https://github.com/bkis/HotSeatGames/tree/main/src/idh/hotseatgames/games/rockpaperscissors)** (by [esmith2smail](https://github.com/esmith2smail))
- **[Binory](https://github.com/bkis/HotSeatGames/tree/main/src/idh/hotseatgames/games/binory)** (by [bkis](https://github.com/bkis))
- **[One Plus One](https://github.com/bkis/HotSeatGames/tree/main/src/idh/hotseatgames/games/oneplusone)** (by [chrkell](https://github.com/chrkell))


## How to add new games

If you want to add new games, just follow these steps:

1. Create a package with your game's name under `idh.hotseatgames.games` (e.g. `idh.hotseatgames.games.mygame`).
2. Create a game class (preferably matching your packages name, e.g. `MyGame`) inside the package you just created. This game class must implement the `IGame` interface correctly to work with *Hot Seat Games*. Please **read the documentation** of the `IGame` interface's methods and stick to the conventions explained there! Furthermore, you are free to use the [very helpful utility classes](https://github.com/bkis/HotSeatGames/tree/main/src/idh/hotseatgames/utils) in `idh.hotseatgames.utils`. A final hint: Try to take inspiration from the existing games' implementations! Everything else is up to you.
3. Add your game package and class name to the static array `GAMES_SUB_PATHS` in the `GameManager` class to "register" your game with the *Hot Seat Games* Engine.


## Contributing

You are **very** welcome to contribute your games to this repository! If you don't know how to do that, you may read [this](https://docs.github.com/en/github/getting-started-with-github/quickstart) to learn how to set up Git and to understand the basics, then read [this](https://github.com/susam/gitpr) to learn about the ideal workflow for contributing to existing projects on GitHub.
