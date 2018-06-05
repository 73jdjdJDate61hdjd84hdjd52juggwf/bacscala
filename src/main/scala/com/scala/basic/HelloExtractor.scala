
@TQCode(name = "Scala",salary = 23232)case class Person2(name:String,age:Int){

}

class TQCode(val name:String,val salary:Int) extends annotation.Annotation
object TQCode{

  def apply( name: String, salary: Int): TQCode = {
    println("DTCoder apply methode invoked !!!")
    new TQCode(name, salary)
  }

  def unapply(arg: TQCode) = {
    Some((arg.name,arg.salary))
  }

}
object HelloExtractor extends App {

  Person2("Spark",7)
  val person = Person2.apply("Spark",7)  //调用apply工厂构造方法构造出类的实例对象
  val Person2(name,age) = person   //调用unapply方法把person实例中的name，age提取出来并赋值给Person2类成员
  println(name +":"+age)

  person match {
    case Person2(name,age) =>println("case 提取器"+name +":"+age)
  }

  val tqcoder = TQCode("hadoop",200000)
  val TQCode(tqname,salary) = tqcoder
}
