import java.time.Instant

class Account(val ledger: Ledger = new Ledger(), val statementFactory: StatementBase[String] = Statement) {

  def deposit(amount: Int, date: Instant): Unit = {
    ledger.history.append(new Transaction(amount, date, "credit"))
  }

  def withdraw(amount: Int, date: Instant): Unit = {
    if (amount > balance){
      throw new IllegalArgumentException(
        "Balance too low, transaction cancelled"
      )
    }
    ledger.history.append(new Transaction(-amount, date, "debit"))
  }

  def printStatement(): String ={
    statementFactory.print(ledger.history)
  }

  def balance: Double = {
    ledger.history.foldLeft(0.0) { (balance, transaction) => balance + transaction.amount}
  }
}
