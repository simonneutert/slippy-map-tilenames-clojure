(ns slippy.core
  (:require [clojure.math :as math]))

(defn longitude2tile [lon zoom]
  (int
   (*
    (math/pow 2M zoom)
    (/ (+ (float lon) 180) 360))))

(defn latitude2tile [lat zoom]
  (int
   (*
    (math/pow 2M zoom)
    (/ (- 1 (/ (Math/log (+ (Math/tan (/ (* (float lat) Math/PI) 180)) (/ 1 (Math/cos (/ (* lat Math/PI) 180))))) Math/PI)) 2))))

(defn tile2longitude [tilex zoom]
  (float
   (- (/ (* tilex 360) (math/pow 2M zoom)) 180)))

(defn tile2latitude [tiley zoom]
  (/
   (*
    180
    (Math/atan (Math/sinh (* Math/PI (- 1 (* 2 (/ tiley (math/pow 2M zoom))))))))
   Math/PI))

(defn tile2latlng [tilex tiley zoom]
  (let [lat (tile2latitude tiley zoom)
        lng (tile2longitude tilex zoom)]
    {:lat lat :lng lng :zoom zoom}))

(defn latlng2tile [lat lng zoom]
  (let [tilex (longitude2tile lng zoom)
        tiley (latitude2tile lat zoom)]
    {:tilex tilex :tiley tiley :zoom zoom}))