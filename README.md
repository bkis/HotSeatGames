# 🕹 Hot Seat Games

A simple [CLI](https://en.wikipedia.org/wiki/Command-line_interface) minigames engine for multiple players on the same computer. Created as a **Java learning project** for the students of my OOP programming class at the [_Institute for Digital Humanities_](https://github.com/DH-Cologne) ([University of Cologne](https://uni-koeln.de/)).

![screenshot](doc/screenshot.png)


## How to play

You have **three options** here:

1. Download the (_possibly outdated, probably up to date_) [current release](https://github.com/bkis/HotSeatGames/releases) and run it with `java -jar HotSeatGames.jar`, **OR**
2. Open the project in your favorite Java IDE and run it from there (_for development_), **OR**
3. Use your IDE (or your hacker skills and `javac` 🤓) to create a runnable `.jar` file from the [current project source](https://github.com/bkis/HotSeatGames/archive/main.zip), then run it with `java -jar HotSeatGames.jar`!


## Included games

At this point, the following games are included in _Hot Seat Games_ (in order of contribution):

- Hyper Typer (by [bkis](https://github.com/bkis))
- Black Jack (by [cthilo](https://github.com/cthilo))
- Rock-Paper-Scissors (by [esmith2smail](https://github.com/esmith2smail))
- Binory (by [bkis](https://github.com/bkis))
- One Plus One (by [chrkell](https://github.com/chrkell))


## How to add new games

If you want to add new games, just follow these steps:

1. Create a package with your game's name under `idh.hotseatgames.games` (e.g. `idh.hotseatgames.games.mygame`).
2. Create a game class (preferrably matching your packages name, e.g. `MyGame`) inside this package. This game class must implement the `IGame` interface correctly to work with _Hot Seat Games_. You are free to use the very helpful utility classes in `idh.hotseatgames.utils` ([have a look](https://github.com/bkis/HotSeatGames/tree/main/src/idh/hotseatgames/utils)!). Try to take inspiration from the existing games implementations! Everything else is up to you.
3. Add your game package and class name to the static array `GAMES_SUB_PATHS` in the `GameManager` class to "register" your game with _Hot Seat Games_.

**✨ You are welcome to contribute your games to this repository!** If you don't know how to do that, you may read [this](https://docs.github.com/en/github/getting-started-with-github/quickstart) to learn how to set up git and to understand the basics, then read [this](https://github.com/susam/gitpr) to learn about the ideal workflow for contributing.