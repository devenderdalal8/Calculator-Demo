package com.example.calculator.util

class Extension {

    companion object {

        private fun operation(a: Double, b: Double, ops: String): Number {
            return when (ops) {
                "+" -> a + b
                "-" -> a - b
                "*" -> a * b;
                "/" -> {
                    if (b == 0.0) {
                        0
                    } else {
                        a / b
                    }
                }
                else -> 0
            }

        }

        private fun containAdd(result: MutableList<String>): MutableList<String> {
            while (true) {
                if (result.contains("+")) {
                    if (result.contains("/"))
                        containDiv(result)
                    else if (result.contains("*")) {
                        containMul(result)
                    } else {
                        val pos = result.indexOf("+")
                        val firstPos = pos - 1
                        val secPos = pos + 1
                        val res =
                            operation(result[firstPos].toDouble(), result[secPos].toDouble(), result[pos])
                        result[firstPos] = res.toString();
                        result.removeAt(pos)
                        result.removeAt(pos)
                    }
                } else {
                    if (result.contains("/")) {
                        containDiv(result)
                    } else if (result.contains("*")) {
                        containMul(result)
                    } else
                        return result
                }
            }
        }

        private fun containMul(result: MutableList<String>): MutableList<String> {
            while (true) {
                if (result.contains("*")) {
                    if (result.contains("/")) {
                        containDiv(result)
                    } else {
                        val pos = result.indexOf("*")
                        val firstPos = pos - 1
                        val secPos = pos + 1
                        val res =
                            operation(result[firstPos].toDouble(), result[secPos].toDouble(), result[pos])
                        result[firstPos] = res.toString();
                        result.removeAt(pos)
                        result.removeAt(pos)
                    }
                } else {
                    if (result.contains("/")) {
                        containDiv(result)
                    } else
                        return result
                }
            }
        }

        private fun containSub(result: MutableList<String>): MutableList<String> {
            while (true) {
                if (result.contains("-")) {
                    if (result.contains("/")) {
                        containDiv(result)
                    } else if (result.contains("*")) {
                        containMul(result)
                    } else if (result.contains("+")) {
                        containAdd(result)
                    } else {
                        val pos = result.indexOf("-")
                        val firstPos = pos - 1
                        val secPos = pos + 1
                        val res =
                            operation(result[firstPos].toDouble(), result[secPos].toDouble(), result[pos])
                        result[firstPos] = res.toString();
                        result.removeAt(pos)
                        result.removeAt(pos)
                    }
                } else {
                    if (result.contains("/")) {
                        containDiv(result)
                    } else if (result.contains("*")) {
                        containMul(result)
                    } else if (result.contains("+")) {
                        containAdd(result)
                    } else return result
                }
            }
        }

        fun containDiv(result: MutableList<String>): MutableList<String> {
            while (true) {
                if (result.contains("/")) {
                    val pos = result.indexOf("/")
                    val firstPos = pos - 1
                    val secPos = pos + 1
                    val res =
                        operation(result[firstPos].toDouble(), result[secPos].toDouble(), result[pos])
                    result[firstPos] = res.toString();
                    result.removeAt(pos)
                    result.removeAt(pos)
                } else {
                    return result
                }
            }
        }

        fun getResult(number: String):String {
            val result =
                number.split(regex = Regex("(?<=\\D)(?=\\d)|(?<=\\d)(?=\\D)")).toMutableList()
            return containSub(result)[0]

        }
    }
}