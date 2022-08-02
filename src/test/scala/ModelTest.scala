import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import org.scalamock.scalatest.MockFactory

class ModelTest extends AnyWordSpec with Matchers with MockFactory {

  "A Model" should {
    "Have a method" which {
      "Return a truthy value" in {
        Model.truthy() shouldBe true
      }
    }

    "Have a method" which {
      "Which fails" in {
        Model.falsey() shouldBe true
      }
    }
  }

}