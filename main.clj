(defprotocol Vector
  (length_squared [v])
  (length [v])
  (normalize [v])
  (add [a b])
  (sub [a b])
  (mul [a b])
  (div [a b]))

(defmacro make-vec
  {:clj-kondo/lint-as 'clojure.core/defrecord} ;; for LSP
  [record-name fields]
  (let [a (gensym "a")
        b (gensym "b")]
    `(do (defrecord ~record-name ~fields
           Vector
           ;; Lengths
           (length_squared [_] (+ ~@(map (fn [field] `(* ~field ~field)) fields)))
           (length [v#] (Math/sqrt (length_squared v#)))
           (normalize [v#]
             (let [len# (length v#)]
               (if (== len# 0)
                 (new ~record-name ~@(repeat (count fields) 0))
                 (div v# len#))))
           ;; Math
           (add [~a ~b]
             (new ~record-name ~@(map (fn [field] `(+ (~(keyword field) ~a) (~(keyword field) ~b))) fields)))
           (sub [~a ~b]
             (new ~record-name ~@(map (fn [field] `(- (~(keyword field) ~a) (~(keyword field) ~b))) fields)))
           (mul [~a ~b]
             (new ~record-name ~@(map (fn [field] `(* (~(keyword field) ~a) ~b)) fields)))
           (div [~a ~b]
             (new ~record-name ~@(map (fn [field] `(/ (~(keyword field) ~a) ~b)) fields))))
         (defn ~(symbol (str "splat" (count fields))) [~a]
           (new ~record-name ~@(repeat (count fields) a)))
         ;; Constants
         (def ~(symbol (str "ZERO" (count fields)))
           (new ~record-name ~@(repeat (count fields) 0)))
         ;; Unit vectors
         ~@(map (fn [field]
                  `(def ~(symbol (str record-name field))
                     (new ~record-name
                          ~@(map (fn [field2]
                                   (if (= field field2) 1 0))
                                 fields))))
                fields))))

(make-vec Size [width height])
(make-vec Vec2 [x y])
(make-vec Vec3 [x y z])
(make-vec Vec4 [x y z w])

#_{:clj-kondo/ignore [:unresolved-symbol]}
(do (println (->Size 1920 1080))
    (println (length (->Vec2 3 4)))
    (println (length (->Vec3 3 4 12)))
    (println (length (->Vec4 3 4 12 84)))
    (println (splat3 5))
    (println (add (->Vec2 1 2) (->Vec2 3 4)))
    (println (normalize (->Vec2 3 4)))
    (println (add Vec2x (mul Vec2y 2))))

;; (println (macroexpand-1 '(make-vec Vec2 [x y])))
