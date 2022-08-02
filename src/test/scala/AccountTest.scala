import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import org.scalamock.scalatest.MockFactory

class AccountTest extends AnyWordSpec with Matchers with MockFactory {

  "An Account" should {
    "Have a method" which {
      "Return a truthy value" in {
        var account = new Account()
        account.truthy() shouldBe true
      }
    }
  }

}