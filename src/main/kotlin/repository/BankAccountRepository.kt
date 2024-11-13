package repository

import dto.BankAccount
import model.BankAccounts
import org.jetbrains.exposed.sql.SqlExpressionBuilder
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update

class BankAccountRepository {

    private var bankAccount : BankAccount = BankAccount()

    fun createAccount(bankAccount: BankAccount) : Boolean
    {
        var status = false
        transaction {
            BankAccounts.insert {
                it[accountNumber] = bankAccount.getAccountNo()
                it[userName] = bankAccount.getUserName()
                it[fullName] = bankAccount.getFullName()
                it[balance] = bankAccount.getBalance()
                it[password] = bankAccount.getPassword()
                it[pin] = bankAccount.getPin()
            }
            status = true
        }
        return status
    }

    fun getBankAccount(username : String): Boolean{
        bankAccount = BankAccount()
        var status = false
        transaction {
            BankAccounts.select { BankAccounts.userName eq username }.forEach {
                bankAccount.setUserName(it[BankAccounts.userName])
                status = true
            }
        }
        return status
    }

    fun checkAuth(password : String) : BankAccount {
        if(getBankAccount(bankAccount.getUserName())) {
            transaction {
                BankAccounts.select { BankAccounts.userName eq bankAccount.getUserName() }.forEach {
                    if (it[BankAccounts.password] == password) {
                        bankAccount.setAccountNo(it[BankAccounts.accountNumber])
                        bankAccount.setBalance(it[BankAccounts.balance])
                        bankAccount.setFullName(it[BankAccounts.fullName])
                        bankAccount.setUserName(it[BankAccounts.userName])
                    } else {
                        bankAccount= BankAccount()
                        throw Exception("Please enter correct password")
                    }
                }
            }
        }
        return bankAccount
    }

    fun checkPin(username : String , pin : Int) : Boolean {
        var status = false
            transaction {
                BankAccounts.select { BankAccounts.userName eq username }.forEach {
                    if (it[BankAccounts.pin] == pin) {
                        status = true
                    }
                }
            }
        return status
    }

    fun updateBalance(username: String, amount:Double) : Double {
        val updatedBalance = bankAccount.getBalance()
        transaction {
            BankAccounts.update ({ BankAccounts.userName eq username }) {
                with(SqlExpressionBuilder) {
                    it.update(balance, balance+amount)
                    bankAccount.setBalance(updatedBalance+amount)
                }
            }
        }
        return bankAccount.getBalance()
    }
}
