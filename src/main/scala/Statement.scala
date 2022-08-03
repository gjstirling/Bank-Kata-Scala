import java.time.format.DateTimeFormatter
import java.time.{Instant, ZoneId}
import scala.collection.mutable.ArrayBuffer

object Statement {

  def print(transactions: ArrayBuffer[Transaction]): String ={
    val transactionsArray = transactions.map(transaction => {
      s"${formatInstant(transaction.date)} || ${transaction.amount}.00 || 100.00\n"
    }).mkString("")
    "date || amount || balance\n" + transactionsArray
  }

  val formatInstant = (instant: Instant) => {
    val dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy").withZone(ZoneId.systemDefault())
    dateTimeFormatter.format(instant)
  }



}
