package com.lucidchart.open.nark.plugins

import java.util.{Date, UUID}

object AlertState extends Enumeration {
	val normal = Value(0, "normal")
	val error = Value(1, "error")
	val warn = Value(2, "warn")
}

object Comparisons extends Enumeration {
	val < = Value(0, "<")
	val <= = Value(1, "<=")
	val == = Value(2, "==")
	val >= = Value(3, ">=")
	val > = Value(4, ">")
	val != = Value(5, "!=")
	val isNull = Value(6, "Is NULL")
	val isNotNull = Value(7, "Is Not NULL")

	val nullables = ValueSet(
		isNull,
		isNotNull
	)
}

case class AlertEvent (
	id: UUID,
	name: String,
	target: String,
	server: String,
	comparison: Comparisons.Value,
	warnThreshold: BigDecimal,
	errorThreshold: BigDecimal,
	lastValue: BigDecimal,
	previous: AlertState.Value,
	current: AlertState.Value
)