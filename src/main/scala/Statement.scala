import java.time.format.DateTimeFormatter
import java.time.{Instant, ZoneId}
import scala.collection.mutable.ArrayBuffer

object Statement {

  def print(transactions: ArrayBuffer[Transaction]): String ={
    var balance = 0
    val transactionsArray = transactions.map(transaction => {
      balance += transaction.amount
      println("the balance is:", balance)
      s"${formatInstant(transaction.date)} || ${transaction.amount}.00 || ${balance}.00\n"
    }).reverse.mkString("")
    "date || amount || balance\n" + transactionsArray
  }

  val formatInstant = (instant: Instant) => {
    val dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy").withZone(ZoneId.systemDefault())
    dateTimeFormatter.format(instant)
  }



}
