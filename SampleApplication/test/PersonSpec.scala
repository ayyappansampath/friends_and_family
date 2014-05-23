import model.Person;
import org.scalatest._

class PersonSpec extends FlatSpec with Matchers {

  "A Person" should "provide a firstName value" in {
    val person = new Person("Harry", "Hill", "harry@hill.com")
    person.firstName should be ("Harry")
  }

  it should "provide a lastName value" in {
    val person = new Person("Harry", "Hill", "harry@hill.com")
    person.lastName should be ("Hill")
  }

  it should "provide a email value" in {
    val person = new Person("Harry", "Hill", "harry@hill.com")
    person.email should be ("harry@hill.com")
  }

}
