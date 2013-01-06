(ns sencha.ext.msg
  "A ClojureScript convenience library for Sencha Touch 2.1. Ext.Msg module"
  )

(defn alert 
  "Displays a standard read-only message box with an OK button (comparable to the basic JavaScript alert prompt). If a callback function is passed it will be called after the user clicks the button, and the itemId of the button that was clicked will be passed as the only parameter to the callback."
  ([title msg]
    (js/Ext.Msg.alert (str title) (str msg)))
  ([title msg a-fn]
    (js/Ext.Msg.alert (str title) (str msg) a-fn))
  ([title msg a-fn a-scope]
    (js/Ext.Msg.alert (str title) (str msg) a-fn a-scope)))

(defn confirm 
  "Displays a confirmation message box with Yes and No buttons (comparable to JavaScript's confirm). If a callback function is passed it will be called after the user clicks either button, and the id of the button that was clicked will be passed as the only parameter to the callback (could also be the top-right close button)"
  ([title msg]
    (js/Ext.Msg.alert (str title) (str msg)))
  ([title msg a-fn]
    (js/Ext.Msg.alert (str title) (str msg) a-fn))
  ([title msg a-fn a-scope]
    (js/Ext.Msg.alert (str title) (str msg) a-fn a-scope)))

(defn prompt 
  "Displays a message box with OK and Cancel buttons prompting the user to enter some text (comparable to JavaScript's prompt). The prompt can be a single-line or multi-line textbox. If a callback function is passed it will be called after the user clicks either button, and the id of the button that was clicked (could also be the top-right close button) and the text that was entered will be passed as the two parameters to the callback."
  ([title msg a-fn a-scope]
    (js/Ext.Msg.alert (str title) (str msg) a-fn a-scope))
  ([title msg a-fn a-scope multi-line?]
    (js/Ext.Msg.alert (str title) (str msg) a-fn a-scope multi-line?))
  ([title msg a-fn a-scope multi-line? a-value]
    (js/Ext.Msg.alert (str title) (str msg) a-fn a-scope multi-line? a-value))
  ([title msg a-fn a-scope multi-line? a-value prompt-cfg]
    (js/Ext.Msg.alert (str title) (str msg) a-fn a-scope multi-line? a-value (clj->js prompt-cfg))))

(defn show 
  "Displays the Ext.MessageBox with a specified configuration. All display functions (e.g. prompt, alert, confirm) on MessageBox call this function internally, although those calls are basic shortcuts and do not support all of the config options allowed here."
  ([cfg]
    (js/Ext.Msg.show (clj->js cfg))))

