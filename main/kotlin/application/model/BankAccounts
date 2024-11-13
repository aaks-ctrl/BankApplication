package model

import org.jetbrains.exposed.sql.Table

object BankAccounts : Table("bank_accounts"){

    var accountNumber = varchar("account_number",36).uniqueIndex()
    val userName = varchar("user_name",10)
    val fullName = varchar("full_name",20)
    val balance = double("balance")
    val password = varchar("password", 16)
    val pin = integer("pin")
    override val primaryKey = PrimaryKey(accountNumber)
}
