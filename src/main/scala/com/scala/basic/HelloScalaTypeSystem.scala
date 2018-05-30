package com.scala.basic

/**
  * Scala类型系统编程实战：
  * 1，Scala的类和方法、函数都可以是泛型，在Spark源码中可以到处看到类和方法的泛型，在实际实例化的时候指定具体
  * 的类型，例如Spark最核心、最基础、最重要的抽象数据结构RDD里面关于RDD的类的定义是泛型的，RDD的几乎所有方法的
  * 定义也都是泛型的，之所以这么做，是因为RDD会派生很多子类，通过子类适配了各种不同的数据源以及业务逻辑操作；
  * 2，关于对类型边界的限定，分为上边界和下边界；
  *     上边界：表达了泛型的类型必须是某种类型或者某种类的子类，语法为<:,这里的一个新的现象是对类型进行限定：
  *     下边界：表达了泛型的类型必须是某种类型或者某种类的父类，语法为>:;
  * 3,View Bounds,可以进行某种神秘的转换，把你的类型可以在没有知觉的情况下转换成目标类型，其实可以认为View Bounds是
  * 上边界和下边界的加强补充版本，例如在SparkContext这个Spark的核心类中有T <% Writable 方式的代码，这个代码所表达的是
  * T必须是Writable类型的，但是T又没有直接继承自Writable接口，此时就需要通过“implicit”的方式来实现这个功能；
  *
  * 4，T:ClassTag，例如Spark源码中的RDD class RDD[T: ClassTag]这个其实也是一种类型转换系统，只是在编译的时候类型信息不够，
  * 需要借助于JVM的runtime来通过运行时信息获得完整的类型信息，这个在spark中是非常重要的，因为Spark的程序的编程和运行是区分了
  * Driver和Executor的，只有在运行的时候才知道完整的类型信息
  *
  * 5，逆变和协变 -T +T
  *
  * 6，Conext Bounds, T: Ordering这种语法必须能够变成Ordering[T]这种方式；
  */
class Animal[T](val species: T){
  def getAnimal(species: T):T = species
}

class Engineer
class Expert extends Engineer

class Meeting[-T]
class Meeting1[+T]
class Dog(val name: String)
class Person(val name: String){
  def talk(person: Person): Unit ={
    println(this.name + ":" +person.name)
  }
}
class Club1[T <% Person](p1: T, p2: T){
  def comunicate = p1.talk(p2)
}

class Worker(name:String) extends Person(name)

class Club[T <: Person](p1: T, p2: T){
  def comunicate = p1.talk(p2)
}

class Maximum[T: Ordering](val x: T,val y: T){
  def bigger(implicit ord: Ordering[T]) ={
    if (ord.compare(x,y)>0) x else y
  }
}
object HelloScalaTypeSystem {
  def main(args: Array[String]): Unit = {
//    implicit def dog2Person(dog:Dog) = new Person(dog.name)
//    val p = new Person("Scala")
//    val w = new Worker("Spark")
//    val dog = new Dog("dahuang")
//    new Club(p,w).comunicate
//    new Club1[Person](p,dog).comunicate  //必须要写[Person]来通知dog隐式转换为Person
//
//    val e = new Meeting1[Engineer]
//    participateMeeting1(e)
//    val expert = new Meeting1[Expert]
//    participateMeeting1(expert)
     val a: Int = new Maximum(3,5).bigger
    println(a)
  }

  def participateMeeting(meeting: Meeting1[Expert]): Unit ={//逆变表示：子类能参加的，父类也能参加
    println("Welcome")
  }

  def participateMeeting1(meeting: Meeting1[Engineer]): Unit ={//协变表示：父类能参加的，子类也能参加
    println("Welcome")
  }
}
