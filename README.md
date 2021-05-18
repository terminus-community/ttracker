# TTracker

A time-tracker born out of the "we don't know Closure yet" dialog.

**Currently the repo is private, let's discuss things first, make MVP, make it public.**

## Concept

Essentially, what we'd want is a simple project to **learn closure**.

It'll need:

* routes to log in and out
* basic admin panel
* ability to create and delete projects
* ability to start tracking time on a project

## Server-side

* JWT auth routes
* Admin panel to add and delete users (yes, manually, we can improve it later)

## Client-side: a GUI app

Login:

* ask user for the server URL, login, password
* log on a server using supplied login and password
* open the base view

Main view:

* show a list of the projects, a button to create one and delete one
* when a project is picked, enable "start" button
* track when user has no activity, show a pause notification, pause tracking in 1 minute
