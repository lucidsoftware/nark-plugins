package com.lucidchart.open.nark.plugins

/**
 * Trait for plugins that handle changes in alert state
 */
trait AlertPlugin {

	/**
	 * The human readable name for Nark to use when showing the plugin
	 */
	def name: String

	/**
	 * The Set of tags this AlertPlugin will take action upon
	 */
	def tags: Set[String]

	/**
	 * The email address to notify when an alert is not handled properly
	 */
	def fallbackEmails: List[String]
	
	/**
	 * Load this plugin. Calling this method allows the plugin to do any necessary initialization
	 */
	def init(): Unit

	/**
	 * Handle a change in alert state for a particular target
	 * @param alert the AlertEvent for the Alert that changed
	 * @return whether the alert was successfully handled
	 */
	def alert(alert: AlertEvent): Boolean
}