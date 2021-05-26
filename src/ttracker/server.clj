(ns ttracker.server
  (:require
    [immutant.web :as web]
    [clojure.string :as str]))

(defn app [request]
  (let [{:keys [uri server-name]} request]
    {:status 200
     :headers {"Content-Type" "text/plain"}
     :body (format "uri: %s, port: %s" uri server-name)}))

(defn -main [& args]
  (web/run #'app {:port 8080}))

(-main)

;; Hot reloading in Atom doesn't work. When loading changed file with chlorine's'ctrl-; f' it gives an error: 'java.io.FileNotFoundException "Could not locate immutant/web__init.class, immutant/web.clj or immutant/web.cljc on classpath."'

;; How I try to run this server:
; 1) launch socket REPL with time-tracker> clj -J'-Dclojure.server.repl={:port,5555,:accept,clojure.core.server/repl}'
; 2) connect to this REPL with chlorine: 'ctrl-; y'
; 3) launch server on localhost: time-tracker>lein run -m ttracker.server (now it works, for example if type localhost:8080/foobar in the browser)
; 4) change :body in the 'app' function so that there will be slightly different server response (browser output)
; 5) re-evaluate the app function by chlorine:evaluate-top-block command ('alt-enter' within the function's code). Inline result shows the name of the function, that means function has been recompiled successfully.
; 6) refresh browser. But the browser still shows the same output.
; 7) try to reload whole namespace (file) with chlorine:load-file  (ctrl-; f). And gives the aforementioned error.
