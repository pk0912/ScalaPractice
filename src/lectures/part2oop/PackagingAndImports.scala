/*
Lecture Pointers
 - Packaging and Imports
                        - package mirror the file structure
                        - package members are accessible by their simple name
                        - packages are ordered hierarchically
                        - package object
                                        - holds standalone methods/constants
                                        - file name is package.scala
                                        - can only be one per package
                                        - can only contain one package object
                                        - name is same as the package in which it resides
                                        - members of a package object are visible throughout the package
                        - use '._' after package name to import all members of that package
                        - use '{}' after package name to import multiple members of the package
                        - aliasing can be done using '=>' operator
                        - default imports
                                         - java.lang => String, Object, Exception
                                         - scala => Int, Nothing, Function
                                         - scala.Predef => println, ???
 */


package lectures.part2oop

import java.sql

import exercises.Writer
//import playground._
import playground.{Cinderella => Princess, PrinceCharming}

import java.util.Date
import java.sql.{Date => SqlDate}

object PackagingAndImports extends App {

  // package members are accessible by their name
  val writer = new Writer("Daniel", "RockTheJVM", 2018)

  val scalaPlayground = new Princess // fully qualified name

  // package object
  sayHello
  println(SPEED_OF_LIGHT)

  // imports
  val prince = new PrinceCharming

  val date = new Date()
//  val sqlDate = new sql.Date(2020, 5, 28)
  val sqlDate = new SqlDate(2020, 5, 28)
}
