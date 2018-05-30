package com.scala.basic

import scala.collection.mutable

/**
  * 1,默认情况下Map构造的是不可变的集合，里面的内容不可修改，一旦修改就变成新的Map，原来的Map内容保持不变；
  * 2，Map的实例是调用工厂方法模式apply来构造Map实例，而需要主要的是Map是接口，在apply中使用了具体的实现；
  * 3，如果想直接new出Map实例，则需要使用HashMap等具体的Map子类；
  * 4，查询一个Map中的值一定是采用getorelse的语法，一方面key不存在的情况下不报告异常，另外还有一个神奇的作用就是提供默认值
  * 而关于默认值的提供在实际开发中至关重要，在Spark中很多默认的配置都是通过getorelse的方式来实现的；
  * 5，使用SortedMap可以得到排序的Map集合；
  * 6，LinkedHashMap可以记住插入的数据的顺序，这在实际开发中非常有用
  *
  * 7，
  */
object HelloMapTuple {

  def main(args: Array[String]): Unit = {
    val bigDatas = Map("Spark"->6, "Hadoop"->10)   //调用工厂方法模式apply来构造Map实例，而需要主要的是Map是接口，在apply中调用实例化它的实现
    val persons = Map(("luzhenxing",30),("Spark",19))
    val persons1 = scala.collection.immutable.SortedMap(("lu",2),("ab",54),("zhen",3))//输出后是排序后的结果
    val programmingLanguage = scala.collection.mutable.Map("Scala"->15,"Java"->20)
    programmingLanguage("Scala") = 13
    for ((name,age)<-programmingLanguage) println(name + ":" + age)
    println(programmingLanguage.getOrElse("Python","luzhenxing"))
    val personsInformation = new mutable.HashMap[String,Int]()
    personsInformation += ("Scala"->15,"Java"->23)
    personsInformation -=("Java")
    for ((name,age)<-personsInformation) println(name + ":" + age)
    for(key <- personsInformation.keySet) println(key)
    for (value<- personsInformation.values) println(value)
    val result: mutable.Map[Int, String] = for((name,age) <- personsInformation) yield (age,name)  //yield相当于map操作，底层是map操作
    for ((age,name) <- result) println(age +":"+name)
    for((name,age)<-persons) println(name+":"+age)
    for((name,age)<-persons1) println(name+":"+age)

    val information = ("luzhenxing",30,Array("kdfj",34,"kdjf"),mutable.Map(("zhenxing",23)))
    for(item<- information._4) println(item._2)
  }
}
