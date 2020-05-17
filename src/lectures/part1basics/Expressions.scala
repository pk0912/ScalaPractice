package lectures.part1basics
/*
Lecture Pointers
 - Expressions
              - val x = 1 + 2 ; RHS here is an expression
              - Expressions are evaluated to a value and have a type
              - Math Expressions
                                - Math Operators
                                                => + - * /
                                                => Bitwise: & | ^ << >> >>> (Specific to Scala; Right shift with zero extension)
              - Relational Expressions
                                      - Example: val x = 2; 1 == x;
                                      - Relational Operators
                                                            => == != > >= < <=
              - Boolean Expressions
                                   - Boolean Operators
                                                      => logical operators
                                                      => ! (unary)
                                                      => && || (binary)
              - Shorthand Expressions
                                     - Operators for changing variables
                                     - Example: aVariable += 3
                                     - Shorthand Operators
                                                          => += -= *= /= etc.
                                                          => only works with variables not values
                                                          => These are all side effects
 - Instructions (Statements) vs Expressions (DO vs VALUE)
                                                         - Instruction is something we tell computer to do.
                                                         - Instructions are doing something.
                                                         - Expressions have values and/or types
                                                         - Instructions are executed (think Java); Expressions are evaluated (Scala)
                                                         - Example: IF Expression in the implementation part below
 - Loops
        - Discouraged to use
        - has side effects
        - while and looping is specific to imperative programming like Java, C, Python.
        - Example: While Loop in the implementation part below
 - Code Blocks
              - Special kind of expressions
              - Surrounded by curly braces
              - Value of the block is the value of the last expression
              - can have val/var definition inside
 * Everything in Scala is an Expression
 * Side effect of using a variable is that it can be reassigned and will return a unit type
 * Example of side effects (They are like instructions)
                                                       - println()
                                                       - whiles
                                                       - reassigning
 */
object Expressions extends App {

  val x = 1 + 2 // RHS is an EXPRESSION
  println(x)

  // IF expression
  val aCondition = true
  val aConditionedValue = if(aCondition) 5 else 3 // IF EXPRESSION
  println(aConditionedValue)
  println(if(aCondition) 5 else 3)

  // WHILE LOOP
  // NEVER WRITE THIS AGAIN
  var i = 0
  while (i < 4) {
    println(i)
    i += 1
  }

  // Side effect
  var aVariable: Int = 1
  val aWeirdValue: Unit = (aVariable = 3) // Unit === void
  println(aWeirdValue)

  // Code Blocks
  val aCodeBlock = {
    val y = 2
    val z = y + 1
    if (z > 2) "hello" else "goodbye"
  }
  println(aCodeBlock)

  val someValue = {
    2 < 3
  }
  println(someValue)
  val someOtherValue = {
    if (someValue) 9 else 42
    4
  }
  println(someOtherValue)
}
