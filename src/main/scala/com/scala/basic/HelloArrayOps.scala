package com.scala.basic

/**
  * 大数据技术是数据的集合以及对数据集合的操作技术的统称，具体来说：
  * 1，数据集合：会涉及数据的搜集，存储等，搜索会有很多技术，存储现在比较经典的是使用Hadoop，也有很多情况使用Kafka;
  * 2,对数据集合的操作技术；目前全球最火爆的是Spark；
  *
  * Spark的框架实现语言是Scala，首选的应用程序开发语言也是Scala，所以Scala对集合以及集合操作的支持就至关重要且必须异常强大；
  * 一个补充说明是：可能是巧合，Spark中很多数据操作的算子和Scala中对集合操作算子是一样一样的！也就是说，你掌握了Scala中
  * 的集合的操作，基本上就可以直接去开发Spark代码了，非常美妙！
  *
  * 关于Scala中数据的创建与操作：
  * 1,最原始的创建数据的方式是形如val array = new Array[Int](5),指定数组的类型是Int且其固定长度是5个元素；
  * 2，对数组元素访问的时候下标的范围在0到Length-1的长度，超过Length-1的话会出现Java.lang.ArrayIndexOutOfBoundsException
  * 3,最常用和经典的创建数组的方式是形如Array[Int](1,2,3,4,5)，直接通过Array类名并传入参数的方式来创建数组实例，在背后的实现是调用
  * Array的工厂方法模式apply来构建出数组及数组的内容；
  * 4,关于Array本身在底层的实现是借助了JVM平台上的Java语言的数组的实现，是不可变的！
  * 5，如果我们想使用可变数组的话，首先需要导入import scala.collection.mutable.ArrayBuffer,然后使用ArrayBuffer这个可变数组
  * 6, 关于ArrayBuffer增加元素默认情况下都是在ArrayBuffer末尾增加元素的，效率非常高！
  * 7，当需要多线程并发操作的时候，把ArrayBuffer转换成Array就非常重要，其实，即使是Array，其本身虽然不可变动(元素不可删减)，但是我们
  * 可以修改Array中的内容，还必须考虑并发写的问题；
  * 8,如果想在已经有的数组的基础上通过作用于每个元素来产生新的元素构成的新的数组，则可以通过yield语法来完成，这在大数据中意义重大；
  * 第一点：它是在不修改已经有的Array的内容的基础上完成的，非常适合于大数据的处理；
  * 第二点：在大数据处理中，例如Spark中业务操作的核心思想就类似于yield,来通过使用function对每个元素进行操作获得新元素构成的新集合，其实
  * 就是新的RDD，例如MapPartitionsRDD
  * 9，集合的操作往往可以通过丰富的操作算子，例如filter来过滤需要条件的元素，例如map来进行每个元素的加工；
  */
object HelloArrayOps {

  def main(args: Array[String]): Unit = {
    val array = Array(1,2,3,4,5)  //在这里可以去掉[Int]这个泛型类型，是因为Scala有类型推断的能力，而我们已经传进了当前数组Array的值，所以就根据值推断数组的类型
    //val array1 = Array.apply(1,2,3,4,5)
    array(0) = 10 //数据的索引下标是从0开始的，修改Array中的内容
    //array(5) = 1 //数组下标越界，会出现Java.lang.ArrayIndexOutOfBoundsException
    for (item <- array) println(item)

    val names = Array("Scala","Kafka","Spark")
    for(item <- names) println(item)

    import scala.collection.mutable.ArrayBuffer
    val arrayBuffer = ArrayBuffer[Int]()
    arrayBuffer += 1
    arrayBuffer += 2
    arrayBuffer += 3
    arrayBuffer += (4,5,6,7,8,9,10)
    arrayBuffer ++= Array(1,2,3)   //在可变数组末尾插入数组
    arrayBuffer.insert(arrayBuffer.length-1,100,1000)  //在指定位置插入元素，原来位置的元素后移
    arrayBuffer.remove(arrayBuffer.length-2)  //删除倒数第二个元素
    arrayBuffer.toArray  //当需要多线程并发操作的时候，把ArrayBuffer转换成Array就非常重要
    for(item <- arrayBuffer) println(item)
    for(i <- 0 until array.length) print(array(i)+" ")
    println()
    for(i <- 0 until (array.length,2)) print(array(i)+" ")
    println()
    for(i <- (0 until array.length).reverse) print(array(i)+" ")
    println()
    println("Sum = " + array.sum)
    println("Max + " + array.max)
    scala.util.Sorting.quickSort(array)//对数组进行升序排序，内容变成了2,3,4,5,10
    println("quickSort = " + array.mkString(","))
    println(array.mkString("*****",",","*****"))
    val arrayAddedOne: Array[Int] = for(item <- array) yield item+1
    println(arrayAddedOne.mkString(" "))
    val arrayOdd: Array[Int] = for(item <- array if item % 2 ==0) yield item
    println(arrayOdd.mkString(", "))
    println(array.filter{x => x%2 == 0}.mkString(","))
    println(array.filter{ _ % 2 == 0}.mkString(","))
    println(array.filter{ _ % 2 == 0}.map{_ * 10}.mkString(","))



  }
}
