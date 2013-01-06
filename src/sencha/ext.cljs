(ns sencha.ext
  "Collection of cljs functions to facilitate coding with sencha.
  http://docs.sencha.com/touch/2-0/#!/api/Ext
  Ext is the global namespace for the whole Sencha Touch framework. 
  Every class, function and configuration for the whole framework exists 
  under this single global variable. The Ext singleton itself contains a set
  of useful helper functions (like apply, min and others), but most of the 
  framework that you use day to day exists in specialized classes 
  (for example Ext.Panel, Ext.Carousel and others).
  Interesting properties: emptyFn, version"
  )

(defn sencha-init 
  "Initializes the sencha framework by checking for the existence of singleton Ext.Viewport, and calling Ext.setup when not.
  This has to be invoked before any sencha framework calls are made.
  (too bad that it is not checked and automatically called by the framework...)"
  ([] (sencha-init (fn [])))
  ([onready-fn]
  (when-not js/Ext.Viewport (js/Ext.setup (clj->js {:onReady onready-fn})))))


(defn sencha-reset 
  "Convenience function to start fresh with a clean screen.
  Removes and destroys the complete widget/component hierarchy."
  []
  (.removeAll js/Ext.Viewport true true))


(defn console-log 
  [msg]
  (js/console.log msg))


(defn application
  "Syntactic sugar for Ext.application.
  \"application\" defines the set of Models, Controllers, Profiles, Stores and Views
  that an application consists of. It automatically loads all of those dependencies and 
  can optionally specify a launch function that will be called when everthing is ready.
  \"kvs\" are the key-value pairs that are wrapped-up in a config-object when passed-thru."
  [& kvs]
  (let [kv-map (apply hash-map kvs)]
    (js/Ext.application 
      (clj->js
        (merge {:name "SenchaApp"} kv-map)))))


(defn setup
  "Syntactic sugar for Ext.setup.
  \"setup\" is the entry-point function to initialize a Sencha Touch application. 
  Note that if your application makes use of MVC architecture, use application instead.
  \"kvs\" are the key-value pairs that are wrapped-up in a config-object when passed-thru.
  Relevant keys are:
  onReady : Function
  A function to be called when the application is ready.
  viewport : Object
  A custom config object to be used when creating the global Ext.Viewport instance."
  [& kvs]
  (let [kv-map (apply hash-map kvs)]
    (js/Ext.setup 
      (clj->js
        (merge {:onReady  (fn [] (msg-alert "Sencha Touch", "Ready to go"))} kv-map)))))


(defn create
  "Syntactic sugar for Ext.create.
  Instantiate a class by either full name, alias or alternate name.
  \"class-name\" identifies the class, and will be string'ified before passed-thru.
  \"kvs\" are the key-value pairs of properties to apply to this instance,
  which will be wrapped-up in a map and js/Object'ified. 
  The new instance/object is returned."
  [class-name & kvs]
  (let [kv-map (apply hash-map kvs)]
    (js/Ext.create 
      (str class-name), 
      (clj->js kv-map))))


(defn define 
  "Syntactic sugar for Ext.define.
  \"class-name\" identifies the new class, and will be string'ified before passed-thru.
  \"kvs\" are the key-value pairs of properties to apply to this class, 
  which will be wrapped-up in a map and js/Object'ified. 
  Note that keys should be string'ifyable.
  \"created-fn\" is the optional callback to execute after the class (or override) is created. 
  The execution scope (this) will be the newly created class itself. 
  \"created-fn\" should be last param if included."
  [class-name & kvs]
  (let [created-fn? (odd? (count kvs))
        created-fn (and created-fn? (last kvs))
        kvs (if created-fn? (butlast kvs) kvs)
        kv-map (apply hash-map kvs)]
    (if created-fn?
      (js/Ext.create (str class-name), (clj->js kv-map), created-fn)
      (js/Ext.create (str class-name), (clj->js kv-map)))))


(defn get-class-name 
  "Syntactic sugar for Ext.getClassName.
  Returns the name (string) of the class by its reference or its instance.
  Empty string is returned when class cannot be found."
  [o]
  (js/Ext.getClassName o))


(defn destroy 
  "Attempts to destroy any objects passed to it by removing all event listeners, 
  removing them from the DOM (if applicable) and calling their destroy functions
  (if available). This method is primarily intended for arguments of type 
  Ext.Element and Ext.Component. Any number of elements and/or components can be
  passed into this function in a single call as separate arguments."
  [& args]
  (apply js/Ext.destroy args))


(defn id 
  "Generates unique ids. If the element already has an id, it is unchanged."
  ([an-el]
    (js/Ext.id an-el))
  ([an-el a-prefix]
    (js/Ext.id an-el a-prefix)))


(defn get-cmp 
  "This is shorthand reference to Ext.ComponentMgr.get. 
  Looks up an existing Component by id"
  [an-id]
  (js/Ext.getCmp an-id))


(defn show-branch [a-component]
  (if (and (.hasParent a-component)
           (= (.getParent a-component) js/Ext.Viewport))
    (.show a-component)
    (show-branch (.getParent a-component))))

