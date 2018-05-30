package com.scala.basic

/**
  * 1,在Scala中，定义类是用class关键字；
  * 2，可以使用new ClassName的方法构建出类的对象；
  * 3，如果名称相同，则object中的内容都是class的静态内容，也就是说object中的内容class都可以在没有实例的时候
  * 直接去调用；正是因为可以在没有类的实例的时候去调用object中的一切内容，所以可以使用object中的特定方法来
  * 创建类的实例，而这个特定方法就是apply方法；
  * 4，object中的apply方式是class对象生成的工厂方法，用于控制对象的生成；
  * 5，很多框架的代码一般直接调用抽象类的object的apply方法去生成类实例对象；
  *    第一：其秘籍在于apply具有类对象生成的一切生杀大权，抽象类是不可以直接实例化的，在apply方法中可以实例化
  *    抽象类的子类，以Spark中的图计算为例，Graph是抽象的class，在object Graph中apply方法实际上是调用了Graph的子类GraphImpl
  *    来创建Graph类型的对象实例的，当然从spark图计算的源代码可以看出，GraphImpl的构造也是使用了object GraphImpl的apply方法；
  *    第二：这种方式神奇的效应在于更加能够应对代表版本迭代或者修改的变化，这是更高意义的面向接口编程；
  *
  *  6,object HelloOOP是class HelloOOP的半生对象，class HelloOOP可以直接访问object HelloOOP中的一切内容，而class HelloOOP
  *  是object HelloOOP的半生类，object HelloOOP可以直接访问class HelloOOP的一切内容，一个特例是用private[this]修饰的成员，我们会在
  *  后面讲解。
  *  7，在定义Scala的class的时候可以直接在类名后面（）里加入类的构造参数，此时在apply方法中也必须有这些参数；
  *  8，Scala中可以在object中构造很多apply方法，
  *  9,Scala中的很多集合都是使用apply的方法构造的，例如Array：
  *  def apply[T: ClassTag](xs: T*): Array[T] = {
    val array = new Array[T](xs.length)
    var i = 0
    for (x <- xs.iterator) { array(i) = x; i += 1 }
    array
  }
  */
class HelloOOP(age: Int) { //加上构造函数就需要在实例化时都添加

  var name = "Spark"
  def sayHello = println("Hi,My name is "+name)
}
object HelloOOP{
  var number = 0

  def main(args: Array[String]): Unit = {
    println("Hello Scala OOP!!!")
    val helloOOP = HelloOOP(30)
    helloOOP.sayHello
    HelloOOP(31)
    HelloOOP(31)
    HelloOOP(31)
    HelloOOP(31)
    HelloOOP(31)
    HelloOOP(31)

  }

  def apply(age:Int): HelloOOP = {
    println("My number is : "+number)
    number = number + 1
    new HelloOOP(age)
  }
}
