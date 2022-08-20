(ns slippy.core-test
  (:require [clojure.test :refer :all]
            [slippy.core :refer :all]))

(deftest slippy-test
  (testing "Slippy Tile for zoom 14"
    (is
     (=
      (latlng2tile 50 8 14)
      {:zoom 14 :tilex 8556 :tiley 5556}))))

(deftest slippy-test-doubles
  (testing "Slippy Tile for zoom 14 with doubles lat lng"
    (is
     (=
      (latlng2tile 50.1234 8.1234 14)
      {:zoom 14 :tilex 8561 :tiley 5547}))))

(deftest slippy-test-doubles-16
  (testing "Slippy Tile for zoom 14 with doubles lat lng"
    (is
     (=
      (latlng2tile 50.1234 8.1234 16)
      {:zoom 16 :tilex 34246 :tiley 22191}))))

(deftest slippy-test-reverse
  (testing "Lat Lng from slippy zoom 14"
    (is
     (and
      (=
       (int (:lat (tile2latlng 8556 5556 14)))
       50)
      (=
       (int (:lng (tile2latlng 8556 5556 14)))
       7)))))