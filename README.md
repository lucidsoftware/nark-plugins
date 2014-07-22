nark-plugins
============
Nark allows users to write plugins to take actions when certain events occur in the Nark system. This allows customization of Nark for various use cases.

### Alert Plugins

Alert plugins are called when an alert in the Nark system changes state (ie, error -> normal, warning -> error, etc.). These plugins can therefore take custom action when an alert is fired or goes back to a normal state.

To create an alert plugin, extend the AlertPlugin trait. This trait requires that the following instance variables be provided:

* `name: String` - a human readable name for the plugin
* `tags: Set[String]` - a set of tags to act upon. The plugin may not want to respond to ALL alerts in the system. Alerts or alerts created by a dynamic alert tagged with any of these strings will be sent to the plugin when their states change.
* `fallbackEmails: List[String]` - email addresses to notify when the plugin is unable to succesfully take action upon a state change

The class that extends the AlertPlugin trait must also provide definitions for the following methods:

* `init(): Unit` - a method that will initialize the plugin in anyway it needs
* `alert(alert: AlertEvent): Boolean` - take action when an alert changes state. The case class passed in contains necessary data about the alert and the state change. Return true if the action succeeds, false if it does not. If false is returned, the emails in `fallbackEmails` will be notified.

To use a plugin in Nark, include the dependency in Build.scala. Additionally, add the name of the plugin to a list specified by `plugins.names` in application.conf. Another configuration value will have to be defined for the name of the class that extends AlertPlugin. The key for this value will be "plugins." + pluginName + ".class". For example:

```
plugins.names = [
  "PagerDuty"
]
plugins.PagerDuty.class = "com.lucidchart.open.nark.pagerduty.PagerDuty"
```
