package com.scala.basic

/**
  * Scala中的基本控制结构有顺序、条件和循环三种方式，这个其它的JVM语言是一致的，但是Scala也有一些高级的流程控制结构
  * 类模式匹配；在这里我们主要if、for、while等三种控制结构及其企业级最佳实战，并且在最后用Spark源码说明
  *
  */
object ControlStructures {

  def main(args: Array[String]): Unit = {
     println("Scala")

    /**
      * if条件表达式会根据if括号里面的boolean值来决定整个if表达式的值
      *
      * 1，Scala中的if条件表达式是有值的！！！这个Java是不一样的！
      * 2，if条件表达式中可以进行类型推导，类型推导到一半过程就是更具变量的值的类型来推导确定变量的类型，这在很多复杂算法
      * 的实现的时候可以让我们省略掉变量的类型的书写，为复杂算法的实现提供了非常大的便利；
      * 3,如果if后面没有else部分，默认的实现是if(...)... else ()  这里省略else ()
      * 4,if表达式中如果有多条语句可以使用{}包裹起来，但是这里就有一个问题了，{}中的多条语句，哪一条是计算结果呢，前面已经说明，是{}中的最后一条
      * 5，补充说明，{.....}代表了一个语句块，语句块是有值的，值就是最后一条语句，类型就是最后一条语句值的类型
      * 6,if表达式可以用在for循环等其他控制结构中用于限制结果
      */
    var age = 10
    val result: String = if (age > 25) "Worker" else "Student"  //此时因为"Work" 和"student"都是字符串，所以result也是字符串类型
    println(result)
    val result2 = if (age > 18) "Adult" else 1 //此时因为if表达式中的else两侧的内容一个是字符串类型、另一个是整数类型，所以result的类型是两个类型的父类型
    val result3 = if (age > 18) "Adult" //如果if后面没有else部分，默认的实现是if(...)... else ()  这里省略else ()
    println(result2)

    var x,y = 0
    val result4 = if(age < 18) {
      x = x + 1;
      y = y + 1;
      x + y
    } else 0
    println(result4)

    for(i <- 0 to 5 if i%2 ==0 ) {
      println(i)
    }

    /**
      * for循环是不断的循环一个集合，然后for循环后面的{...}代码块部分会根据for循环(...)里面提取的集合的item来作为{...}的输入进程流程控制
      * 1,for循环中加入的if叫做条件守卫，用于限制for循环(优化for循环，去掉不必要的步骤，或者说用于跳出for循环)
      * 2,最后再次强调一下，在for循环中能够提取出什么内容取决于后面的集合的类型
      * 3，想跳出for循环的话，除了加入if守卫以外，还可以使用return关键字,Return 直接返回了，所以不执行下面的语句了
      */
    var flag = true
    var sum = 0
    for(i <- 0 to 5 if flag){
      sum = sum + i
      if (5 == i) flag = false  //return 返回的是方法级别的，这在实际开发中也非常常用
    }
    println("sum = " + sum)

    for(item <- "Spark Hello".split(" ")) println(item)

    /**
      * while循环，也是循环集合来作为{....}的输入，进而完成流程的控制的，while循环在实际Server和Framework开发中至关重要，例如让一个
      * 线程一直循环下去，一般都会使用while；
      */
    import scala.util.control.Breaks._
    flag = true
    breakable{
    while (flag){
      for (item <- "Spark"){
        println(item)
        if (item == 'r') {
          flag = false
          break //执行到这里会跳出for循环
        }
      }
    }
    }
    println("while finished")
  }
}
