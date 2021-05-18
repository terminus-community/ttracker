# TTracker

A time-tracker born out of the <cite>"we don't know Clojure yet"</cite> dialog.
Idea: have a web server backend so *teamleads* and *users* themselves can view time spent on tasks, have a desktop app to do the tracking.

**Currently the repo is private, let's discuss things first, make MVP, make it public.**

## Concept

Essentially, what we'd want is a simple project to **learn Clojure**.

It'll need:

* routes to log in and out
* basic admin panel
* ability to create and delete projects
* ability to start tracking time on a project

## Server-side

* Probably microframework-based, and [Luminus](https://luminusweb.com/docs/guestbook.html) seems nice on the first sight: what I like is [REPL-driven development](https://luminusweb.com/docs/repl.html) idea, what I want to know later is modularity of Luminus (replacing a template engine, for instance)
* JWT auth routes (People in [this thread](https://www.reddit.com/r/Clojure/comments/9bj8pj/cemerickfriend_or_something_else/) recommend [buddy](https://github.com/funcool/buddy))
* Admin panel to add and delete users (yes, manually, we can improve it later)
* Route to start and stop tracking time

## Client-side: a GUI app

The application will be browser-based.

Also, it would be sehr cool, if we will be able to implement client-side in ClojureScript, transpilable or in other way compatible with one of popular JS frameworks: Vue.js, React or Angular.

Login:

* ask user for the server URL, login, password
* log on a server using supplied login and password
* open the base view

Main view:

* show a list of the projects, a button to create one and delete one
* when a project is picked, enable "start" button
* track when user has no activity, show a pause notification, pause tracking in 1 minute
