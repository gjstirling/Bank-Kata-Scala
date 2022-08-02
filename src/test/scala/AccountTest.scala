import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import org.scalamock.scalatest.MockFactory

class AccountTest extends AnyWordSpec with Matchers with MockFactory {

  "An Account" should {
    "Deposit transactions" which {
      "Can be found in the account transaction history" in {
        val account = new Account()
        account.deposit(100, "27/07/2022")
        account.transactions.length shouldBe 1
        account.transactions(0).balance shouldBe 100
        account.transactions(0).amount shouldBe 100
        account.transactions(0).transactionType shouldBe "credit"
      }
    }

    "Withdrawal transactions" which {
      "Can be found in the account transaction history" in {
        val account = new Account()
        account.deposit(100, "27/07/2022")
        account.withdraw(amount = 50, date="27/07/2022")
        account.transactions.length shouldBe 2
        account.transactions(0).balance shouldBe 50
        account.transactions(0).transactionType shouldBe "debit"
      }
    }
  }

}