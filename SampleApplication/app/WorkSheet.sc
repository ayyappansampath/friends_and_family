
class Rational(x: Int , y : Int){
  val numerator = x
  val denominator = y
}

object rationals {
  val obj = new Rational(4,2)
  println(obj.numerator)

}