# Weekly Tasks Manager

![](https://img.shields.io/github/tag/Fabricio-Tomas/Task-manager-notif.svg)
![](https://img.shields.io/github/release/Fabricio-Tomas/Task-manager-notif.svg)
![](https://img.shields.io/github/issues/Fabricio-Tomas/Task-manager-notif.svg)

*Weekly Tasks Manager* is a software that lets you organize every task in a simple and comfortable table. It also sends notifications at time on desktop or via email if the options is chosen. (may be an update to receive notifications via WhatsApp)

Remember to organize your time and have a relaxed weekend, knowing that every task was completed.

------------

### Features
- Organize your week in a seven-column table.
- Diferent themes and languajes.
     - White and Dark Theme.
     - English and Spanish.
- Receive notifications in desktop or email by entering an address.
- Automaticaly sort tasks to show the most recent on top.
- Store user's configurations in the `config.dat` file.
- Store daily tasks in the `tasks` folder with seven files.
- More features in the future

------------

### Download
![](https://img.shields.io/github/release/Fabricio-Tomas/Task-manager-notif.svg)

- The download of the most recent release can be found [here](https://github.com/Fabricio-Tomas/Task-manager-notif/releases/tag/v1.0)
- Also check the release [history](https://github.com/Fabricio-Tomas/Task-manager-notif/releases)

- The source code can be compiled using the following commands: (keep in mind that the `res` folder and the `javax.mail.jar` library must be in the same directory where the src folder is stored)

```
javac -encoding utf8 -d bin -cp src;javax.mail.jar src\main\App.java
```
```
java -cp bin;res;javax.mail.jar main.App
```

------------

### Licences

This software is under `MIT LICENCE` which can be found in this repository.

Some icons and fonts are not owned by Fabricio-Tomas. Links, names and licencies are stored in this repository and writed bellow.

- Noto Sans Font
	- *Designed by Google*
	- [Download](https://fonts.google.com/noto/specimen/Noto+Sans?selection.family=Noto+Sans)

- Oswald Font
	- *Designed by Vernon Adams (2012)*
	- [Download](https://www.fontsquirrel.com/fonts/oswald)

- Icons designed by <a href="https://www.flaticon.es/autores/najmunnahar" title="NajmunNahar">NajmunNahar</a> from <a href="https://www.flaticon.es/" title="Flaticon">www.flaticon.es</a>

- javax.mail.jar
	- Owned by Oracle
	- [Download](https://www.oracle.com/java/technologies/javamail-api.html)
