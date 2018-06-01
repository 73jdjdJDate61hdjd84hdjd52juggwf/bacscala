package com.scala.basic

import java.io.IOException
object HelloExceptionAndLazyValue {

  def main(args: Array[String]): Unit = {
    try{
      1/0
    } catch {
      case ioException:IOException => println("IOException: "+ioException.toString)
      case illegalAccessException:IllegalAccessException => println("IllegalAccessException: "+illegalAccessException.toString)
      case arithmeticException:ArithmeticException => println("ArithmeticException: "+arithmeticException.toString)
    }

    lazy val scare = 90
    println("...........")
  }
}
