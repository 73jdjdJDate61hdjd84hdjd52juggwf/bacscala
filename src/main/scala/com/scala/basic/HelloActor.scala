import scala.actors.Actor
object HelloActor {

  def main(args: Array[String]): Unit = {

    val helloActor = new HelloActor
    helloActor.start()
    val helloBackActor = new HelloBackActor(helloActor)
    helloBackActor.start()
//    var counter = 0
//    while (true){
//      helloActor ! "hehe,zhenxing"+ counter
//      counter += 1
//      Thread.sleep(3000)
//    }
//    helloActor ! Hello("Spark","Spark is hot")
  }
}

case class Hello(name: String,content:String,sender:Actor)
case class HelloBack(name: String,content:String,sender:Actor)
class HelloActor extends Actor {
  var counter = 0
  override def act(): Unit = {
    while (true){
      receive{
        case content:String => println("Message:"+content)
        case Hello(name,content,sender) => {
          println("HelloActor:"+"name: " + name+" content:"+content+counter)
          counter += 1
          Thread.sleep(1000)
          sender ! HelloBack(name,content+counter,this)
        }
      }
    }
  }
}
class HelloBackActor(helloActor: HelloActor) extends Actor {
  var counter = 0
  override def act(): Unit = {
    helloActor ! Hello("Spark","Love is here",this)
    while (true){
      receive{
        case content:String => println("Message:"+content)
        case HelloBack(name,content,sender) => {
          println("HelloBackActor:"+"name: " + name+" content:"+content+counter)
          counter +=1
          Thread.sleep(1000)
          sender ! Hello(name,content+counter,this)
        }
      }

    }
  }
}
