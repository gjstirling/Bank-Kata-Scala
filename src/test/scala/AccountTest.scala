import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import org.scalamock.scalatest.MockFactory

import java.time.{Clock, Instant, ZoneId}

class AccountTest extends AnyWordSpec with Matchers with MockFactory {

  "An Account" should {
    "Add deposit transactions" which {
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

    "Add withdraw transactions" which {
      "Can be found in the account transaction history" in {
        val account = new Account()
        val mockTime = "2022-07-28T14:35:00Z"
        val fixedClock = Clock.fixed(Instant.parse(mockTime), ZoneId.systemDefault())
        val date = Instant.now(fixedClock)
        account.deposit(100, date)
        account.withdraw(50, date)
        account.ledger.history.size shouldBe 2
        account.ledger.history(1).transactionType shouldBe "debit"
      }

      "Are refused when balance is too low" in {
        val account = new Account()
        val mockTime = "2022-07-28T14:35:00Z"
        val fixedClock = Clock.fixed(Instant.parse(mockTime), ZoneId.systemDefault())
        val date = Instant.now(fixedClock)
        an [IllegalArgumentException] should be thrownBy(account.withdraw(100, date))
      }
    }

    "Calculate the balance" which {
      "Can calculated from the ledger" in {
        val account = new Account()
        val mockTime = "2022-07-28T14:35:00Z"
        val fixedClock = Clock.fixed(Instant.parse(mockTime), ZoneId.systemDefault())
        val date = Instant.now(fixedClock)
        account.deposit(100, date)
        account.withdraw(25, date)
        account.balance shouldBe 75
      }
    }

    "Can return bank statements" which {
      "Returns a header" in {
        val account = new Account()
        account.printStatement() shouldBe "date || amount || balance\n"
      }


      "Prints a list of transactions" in {
        // TODO: Mock Statement static print method's return
//        val mockStatement = mock[StatementBase[String]]
//        val mockFormattedStatement = "date || amount || balance\n28/07/2022 || -25.00 || 75.00\n28/07/2022 || 100.00 || 100.00\n"
//        val mockLedger = new Ledger()
//        (Statement.print _).expects(mockLedger.history).returning(mockFormattedStatement)
//        val account = new Account(mockLedger, mockStatement)
        val account = new Account()

        val mockTime = "2022-07-28T14:35:00Z"
        val fixedClock = Clock.fixed(Instant.parse(mockTime), ZoneId.systemDefault())
        val date = Instant.now(fixedClock)

        account.deposit(100, date)
        account.withdraw(25, date)
        account.printStatement() shouldBe "date || amount || balance\n28/07/2022 || -25.00 || 75.00\n28/07/2022 || 100.00 || 100.00\n"
      }
    }
  }

}