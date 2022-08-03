import java.time.format.DateTimeFormatter
import java.time.{Instant, ZoneId}
import scala.collection.mutable.ArrayBuffer

object Statement {

  def print(transactions: ArrayBuffer[Transaction]): String ={
    var balance = 0
    val transactionStrings = transactions.map(transaction => {
      balance += transaction.amount
      s"${formatInstant(transaction.date)} || ${transaction.amount}.00 || ${balance}.00\n"
    }).reverse.mkString("")
    "date || amount || balance\n" + transactionStrings
  }

  val formatInstant = (instant: Instant) => {
    val dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy").withZone(ZoneId.systemDefault())
    dateTimeFormatter.format(instant)
  }



}
