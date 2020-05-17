package lectures.part1basics
/*
Lecture Pointers:
 - Values
         - val keyword
         - constants
         - immutable (can't be reassigned)
 - Types
        - Unit - Corresponds to no value; only value it can hold in scala is ()
        - Null - null or empty reference
        - Nothing - The subtype of every other type; includes no values
        - Any - The supertype of any type; any object is of type Any
        - AnyRef - The supertype of any reference type
        - String - use double quote
        - Char - use single quote
               - 16-bit unsigned Unicode Character
        - Boolean - true/false
        - Byte - 8-bit signed value
        - Short - 16-bit signed value
        - Int - 32-bit signed value
        - Long - 64-bit signed value
               - Put 'L' in the end of the number
        - Float - 32-bit IEEE 754 single-precision float
                - Put 'f' in the end to distinguish it from double
        - Double - 64-bit IEEE 754 double-precision float
 - Variables
            - var keyword
            - mutable
            - side effects
 * Types of val/var are optional; Compiler can infer type from the assigned value
 * ';' is optional; compulsory if multiple expressions in the same line
 * Prefer val over var (because of side effect)
 */
object ValuesVariablesTypes extends App {

  val aValue: Int = 4
  println(aValue)

  val y = 14
  println(y)

  val aString: String = "String variable"
  println(aString)
  val aChar: Char = 'c'
  println(aChar)
  val aShort: Short = 12345
  println(aShort)
  val anInt: Int = 1234567890
  println(anInt)
  val aLong: Long = 1234567890123456789L
  println(aLong)
  val aBoolean: Boolean = true
  println(aBoolean)
  val aFloat: Float = 3.1415926535897932384626433832795028841971693993751058209749445923078164062862089986280348253421170679f
  println(aFloat) // It will print 3.1415927
  val aDouble: Double = 3.1415926535897932384626433832795028841971693993751058209749445923078164062862089986280348253421170679
  println(aDouble) // It will print 3.141592653589793

  var aVariable: Int = 0
  println(aVariable)

  aVariable = 9 // var can be reassigned; side-effect
  println(aVariable)
}
