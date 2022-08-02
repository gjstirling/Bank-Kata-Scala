import java.time.Instant
import scala.collection.mutable.ArrayBuffer

class Account(val ledger: Ledger = new Ledger()) {

  def deposit(amount: Int, date: Instant): Unit = {
    ledger.history.append(new Transaction(amount, date, "credit"))
  }

  def withdraw(amount: Int, date: Instant): Unit = {
    val checkBalance = balance
    if (amount > checkBalance){
      throw new IllegalArgumentException(
        "Balance too low, transaction cancelled"
      )
    }
    ledger.history.append(new Transaction(-amount, date, "debit"))
  }

  def balance: Double = {
    ledger.history.foldLeft(0.0) { (balance, transactionHistory) => balance + transactionHistory.amount}
  }



}
