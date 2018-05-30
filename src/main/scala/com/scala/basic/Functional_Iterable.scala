package com.scala.basic
import scala.collection.immutable
/**
  * 1,在Scala体系中Iterable是共同的Trait，Iterable要求继承者实现一些共同的方法，例如对元素的遍历等；
  * 2，Array是一个非常基础的数据结构，不从属于Scala集合体系；
  * 3,Scala的集合体系中集合分为可变集合和不可变集合之分；不可变集合在import scala.collection.immutable包中，可变
  * 的集合在import scala.collection.mutable；
  * 4，List是元素的列表集合，是不可变的；
  *  第一点：List中的head是指第一个元素，tail是指剩下的元素构成的List集合；
  *  第二点：使用::这个操作符来把List和其他元素进行组拼来构建新的List
  *  第三点：如果集合中没有元素的话，此时是Nil，例如说List中只有一个元素，那么head就是这个元素本身，tail操作就会返回Nil
  * 5，LinkedList是元素可变的list列表
  * 6,Set是元素不可重复的集合，且元素是无序的；HashSet中的元素不可变且不可重复且不能保证顺序
  * 7，LinkedHashSet会维护元素的插入顺序
  * 8,SortedSet会自动把插入的元素排序
  */
object Functional_Iterable {
  def main(args: Array[String]): Unit = {
    val range = 1 to 10

    val list = List(1,2,3,4,5)
    println(list.head)
    println(list.tail)//List(2, 3, 4, 5)
    val list1 = 0::list
    println(list1)  //List(0, 1, 2, 3, 4, 5)

    var linkedList = scala.collection.mutable.LinkedList(1,2,3,4,5)
    println(linkedList.elem)
    println(linkedList.tail)

    while (linkedList != Nil){
      println(linkedList.elem)
      linkedList = linkedList.tail
    }
    println(linkedList)  //LinkedList()

    val copied = linkedList.+:(9)
    println(copied)  //LinkedList(9)

    val set = Set(1,2,3,4,5)
    println(set)
    val setMore = set + 1  //添加元素后变成另一个Set，原来的Set保持不变
    println(setMore)

    val hashSet = scala.collection.mutable.HashSet(1,2,3)
    hashSet += 5
    hashSet += 50  // 添加元素
    println(hashSet)  //Set(1, 5, 2, 50, 3)

    val linkedHashSet = scala.collection.mutable.LinkedHashSet(1,2,3)
    linkedHashSet += 5
    linkedHashSet += 50
    println(linkedHashSet)  //Set(1, 2, 3, 5, 50)
    linkedHashSet += 4
    println(linkedHashSet) //Set(1, 2, 3, 5, 50, 4) //插入顺序不会变化

    val sortedSet = scala.collection.mutable.SortedSet(1, 2, 3, 5, 50, 4)
    println(sortedSet)  //TreeSet(1, 2, 3, 4, 5, 50)

    println(List("I am into spark too much","Scala is power and intelligent").flatMap(x =>x.split(" ")).map{x =>(x,1)}.map(x => x._2).reduce(_+_))//flatMap:List(I, am, into, spark, too, much, Scala, is, power, and, intelligent)
                              //map结果: List((I,1), (am,1), (into,1), (spark,1), (too,1), (much,1), (Scala,1), (is,1), (power,1), (and,1), (intelligent,1))
                              //reduce结果：11
    for(item <- List("I am into spark too much").flatMap(x =>x.split(" ")))  println(item)
    List("I am into spark too much","Scala is power and intelligent").flatMap(x =>x.split(" ")).map{x => (x,1)}
    List(1,2,3,4,5,6,7,8).foreach(x => println(x))
    List(1,2,3,4,5,6,7,8).foreach(println(_))
    List(1,2,3,4,5,6,7,8).foreach(println _)
    List(1,2,3,4,5,6,7,8).foreach(println)

  }
}
