import java.time.Instant
import scala.collection.mutable.ArrayBuffer

class Transaction (
                  val amount: Int,
                  val date: Instant,
                  val transactionType: String
                )

class Ledger (val history: ArrayBuffer[Transaction] = ArrayBuffer() )

