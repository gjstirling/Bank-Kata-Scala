import scala.collection.mutable.ArrayBuffer

class Account {
  var transactions: ArrayBuffer[Transaction] = ArrayBuffer()

  def deposit(amount: Int, date: String): Unit = {
    transactions += new Transaction(amount, date, "credit", getBalanceFromTransactions())
  }

  def withdraw(amount: Int, date: String): Unit = {
    transactions += new Transaction(amount, date, "debit", getBalanceFromTransactions())
  }

  def getBalanceFromTransactions(): Int ={
    100
  }
}
