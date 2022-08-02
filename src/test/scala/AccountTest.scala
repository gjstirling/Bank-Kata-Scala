import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import org.scalamock.scalatest.MockFactory

import java.time.{Clock, Instant, ZoneId}

class AccountTest extends AnyWordSpec with Matchers with MockFactory {

  "An Account" should {
    "Deposit transactions" which {
      "Can be found in the account transaction history" in {
        val account = new Account()
        val mockTime = "2022-07-28T14:35:00Z"
        val fixedClock = Clock.fixed(Instant.parse(mockTime), ZoneId.systemDefault())
        val date = Instant.now(fixedClock)
        account.deposit(100, date)
        account.ledger.history.size shouldBe 1
        account.ledger.history(0).amount shouldBe 100
        account.ledger.history(0).date shouldBe date
        account.ledger.history(0).transactionType shouldBe "credit"
      }
    }
  }

}