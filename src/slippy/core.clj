(ns slippy.core
  (:require [clojure.math.numeric-tower :as math]))

(defn longitude2tile [lon zoom]
  (int
   (*
    (math/expt 2 zoom)
    (/ (+ (float lon) 180) 360))))

(defn latitude2tile [lat zoom]
  (int
   (*
    (math/expt 2 zoom)
    (/ (- 1 (/ (Math/log (+ (Math/tan (/ (* (float lat) Math/PI) 180)) (/ 1 (Math/cos (/ (* lat Math/PI) 180))))) Math/PI)) 2))))

(defn tile2longitude [x zoom]
  (float
   (- (/ (* x 360) (math/expt 2 zoom)) 180)))

(defn tile2latitude [y zoom]
  (/
   (*
    180
    (Math/atan (Math/sinh (* Math/PI (- 1 (* 2 (/ y (math/expt 2 zoom))))))))
   Math/PI))

(defn tile2latlng [x y zoom]
  (let [lat (tile2latitude y zoom)
        lng (tile2longitude x zoom)]
    {:lat lat :lng lng :zoom zoom}))

(defn latlng2tile [lat lng zoom]
  (let [tilex (longitude2tile lng zoom)
        tiley (latitude2tile lat zoom)]
    {:tilex tilex :tiley tiley :zoom zoom}))