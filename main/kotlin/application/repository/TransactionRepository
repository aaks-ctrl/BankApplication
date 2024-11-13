package repository

import dto.Transaction
import model.Transactions
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

class TransactionRepository {

    fun getTransactions(accountNumber: String) : ArrayList<Transaction>{
        var transactions: ArrayList<Transaction> = arrayListOf()
        transaction {
            Transactions.select{Transactions.accountNumber eq accountNumber}.forEach{
                var transaction : Transaction = Transaction()
                transaction.setTransactionId(it[Transactions.transactionId])
                transaction.setTransactionType(it[Transactions.transactionType])
                transaction.setAccountNo(it[Transactions.accountNumber])
                transaction.setAmount(it[Transactions.amount])
                transaction.setTimeStamp(it[Transactions.timestamp])
                transaction.setOpeningBalance(it[Transactions.openingBalance])
                transactions.add(transaction)
            }
        }
        return transactions
    }

    fun putTransaction(transaction: Transaction) : Boolean {
        var status = false
        transaction {
            Transactions.insert {
                it[accountNumber] = transaction.getAccountNo()
                it[transactionType] = transaction.getTransactionType()
                it[amount] = transaction.getAmount()
                it[openingBalance] = transaction.getOpeningBalance()
                it[timestamp] = transaction.getTimeStamp()
            }
            status = true
        }
        return status
    }
}
