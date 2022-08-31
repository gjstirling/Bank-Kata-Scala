import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import org.scalamock.scalatest.MockFactory

import java.time.{Clock, Instant, ZoneId}

class StatementTestTest extends AnyWordSpec with Matchers with MockFactory {

  "A Statement" should {
    "Return statement string" which {
      "Which has a header" in {
        val ledger = new Ledger()
        Statement.print(ledger.history) shouldBe "date || amount || balance\n"
      }

      "Which has a transaction" in {
        val mockTime = "2022-07-28T14:35:00Z"
        val fixedClock = Clock.fixed(Instant.parse(mockTime), ZoneId.systemDefault())
        val date = Instant.now(fixedClock)
        val ledger = new Ledger()
        ledger.history.append(new Transaction(100, date, "credit"))
        Statement.print(ledger.history) shouldBe "date || amount || balance\n28/07/2022 || 100.00 || 100.00\n"
      }

      "Which has multiple transactions in order of most recent" in {
        val mockTime = "2022-07-28T14:35:00Z"
        val fixedClock = Clock.fixed(Instant.parse(mockTime), ZoneId.systemDefault())
        val date = Instant.now(fixedClock)
        val ledger = new Ledger()
        ledger.history.append(new Transaction(100, date, "credit"))
        ledger.history.append(new Transaction(50, date, "credit"))
        ledger.history.append(new Transaction(-100, date, "debit"))
        Statement.print(ledger.history) shouldBe "date || amount || balance\n28/07/2022 || -100.00 || 50.00\n28/07/2022 || 50.00 || 150.00\n28/07/2022 || 100.00 || 100.00\n"
      }
    }
  }
}
