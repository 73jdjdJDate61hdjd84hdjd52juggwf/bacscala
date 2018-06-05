/**
  * private[spark]表示org.apache.spark私有的包可以访问，要想在应用程序中访问，必须要定义和org.apache.spark包名
  * 相同的包
  */
abstract class Person1{
  val counter: Int           //成员不被实例化，类就是抽象的,和Java不一样
  val counterArray = new Array[Int](counter)
}

class Sporter extends Person1 {
  override val counter: Int = 5
}   //继承父类，覆盖父类会失败,输出counterArray的length时还是0

//class Sporter extends {
//  override val counter: Int = 5
//} with Person1              //提前定义语法，意思是覆盖父类的成员

class Programmer(val name:String,val salary: Double){
  final override def equals(other: scala.Any): Boolean ={
  val that = other.asInstanceOf[Programmer]
    if (that == null) false
    else name == that.name && salary == that.salary
  }

  final override def hashCode: Int = {
    name.hashCode()*17 + salary.hashCode()*9
  }
}


object OverrideField extends App {

  val s = new Sporter
  println(s.counterArray.length)
}
