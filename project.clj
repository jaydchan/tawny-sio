(defproject ncl.sio "1.0.0-SNAPSHOT"
  :description "Recasting of SIO using Tawny-OWL"
  :dependencies [[uk.org.russet/tawny-owl "1.1.0-SNAPSHOT"]]
  :scm {:url "https://github.com/jaydchan/tawny-sio.git"
        :name "git"}
  :license {:name "LGPL"
            :url "http://www.gnu.org/licenses/lgpl-3.0.txt"
            :distribution :repo}
  :main ncl.sio.core
  :aot [ncl.sio.core]
)
