object HelloHierachyAndTrait {

  def main(args: Array[String]): Unit = {
    val succ = (x: Int) => x + 1
    val anonfun1 = new Function1[Int, Int] {
      def apply(x: Int): Int = x + 1
    }
    assert(succ(0) == anonfun1(0))
  }
}
