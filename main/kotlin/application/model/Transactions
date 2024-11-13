package model

import org.jetbrains.exposed.sql.Table

object Transactions : Table("transactions"){

    val transactionId = long("transaction_id").autoIncrement().uniqueIndex()
    val accountNumber = varchar("account_number",36).references(BankAccounts.accountNumber)
    val transactionType = char("type",1)
    val amount = double("amount")
    val openingBalance = double("opening_balance")
    val timestamp = long("time_stamp")
    override val primaryKey = PrimaryKey(transactionId)
}
