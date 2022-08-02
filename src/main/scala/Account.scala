import java.time.Instant
import scala.collection.mutable.ArrayBuffer

class Account(val ledger: Ledger = new Ledger()) {

  def deposit(amount: Int, date: Instant): Unit = {
    val transaction = new Transaction(amount, date, "credit")
    ledger.history.append(transaction)
  }



}
