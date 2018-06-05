object ScalaAdvanced {

  def main(args: Array[String]): Unit = {
    def process(code1: Int):String ={
      var result:String ="0"
      code1 match {
        case 0 => result ="1"
        case 1 => result ="2"
        case _ => result ="1000"
      }
      return result
    }
//面向值的编程，没有变量的赋值，有数据的不可变性
    def processBetter(code1: Int):String = code1 match {
        case 0 => "1"
        case 1 => "2"
        case _ => "1000"
      }
    }
  }

}
