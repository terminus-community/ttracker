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

## DB

### Models

#### Auth (`auth` table)

Let's stop on a simple email-based authentication, with a possible username.

**Columns**:

* `id` (`serial`, autoincremented integer type)
* `email` (text, discuss what limit we impose on its length)
* `username` (text, discuss what limit we imposse on its length)
* `salt` (autogenerated password salt for security reasons)
* `salt_num` (how many times did iterative hashing happen?)
* `password` (salted password checksum)

Scenario: user registers, a hash is concatenated with new password, random number `salt_num` is picked (2-9), password is hashed with salt `salt_num` times, so rainbow tables use on a leaked db is much less useful. (Yes, it's a typical modern approach.)

### Session

We should store all user sessions somewhere. The fact we'd store them in a DB is awkward, but this app is small.

**Columns**

* `id` (`serial`, autoincremented integer type)
* `user_id` (foreign key to `auth)
* `token` (text)
* `token_datetime` (datetime type for current DB implementation)

### Groups

We need groups, created by users, so the time tracking happens in groups.

* `id` (`serial`, autoincremented integer type)
* `name` (`text`, name of a group, may have a character length limit or not, up to discussion)

### User-group relations (`usergroup`)

* `id` (`serial`, autoincremented integer type)
* `user_id` (`int`) - a foreign key to user
* `group_id` (`int`) - a foreign key to a group
* `is_owner` (`bool`) - can add users to groups, remove a group, rename a group, view timings inside a group
* `is_mod` (`bool`) - can view a list of people in a group, view timings for other users

## API

### Routes

* `/api/auth/register/` - let's not implement email verification yet, accept email and password, check if there is no such email in use, create an `auth` table record.
* `/api/auth/login/` - verify a password by iterative hashing, return an error on failure and an auth token on success (JWT routes). Example request: `Content-Type: application/json` (header), `{"username": "Petya", "password":"IAm1st"}` or `{"email": "Peter@example.com", "password":"IAm1st"}` (body).
* `/api/auth/refresh/` - refresh a token (JWT). Example: `Content-Type: application/json` (header), `{"token":"<JWT>"}` (body).
* `/api/auth/verify` - we've closed an app and reopened it, we have the token, it did not expire, so we supply it to login instead. `Content-Type: application/json` (header), `{"token":"<EXISTING_TOKEN>"}` (body).
* `/api/tracking/<project_id>/start` - start tracking time for a user on `<project_id>`
* send_invite (add user to a group)
* remove a group
* rename a group
* list users in a group
* view timings inside a group
