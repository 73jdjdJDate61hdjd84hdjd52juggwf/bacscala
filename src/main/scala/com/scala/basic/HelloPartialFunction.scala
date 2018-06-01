package com.scala.basic

object HelloPartialFunction {

  def main(args: Array[String]): Unit = {
    val sample = 1 to 10
    val isEven: PartialFunction[Int, String] = {
      case x if x % 2 ==0 => x+" is even"
    }

    val isOdd: PartialFunction[Int, String] = {
          case x if x % 2 == 1 => x+" is odd"
    }
    //isEven(4)
    val evenNumbers = sample collect isEven

    val numbers = sample map (isEven orElse isOdd)

    //evenNumbers.foreach(println)

    numbers.foreach(println)
  }
}
