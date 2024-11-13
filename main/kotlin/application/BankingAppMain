package application

import model.BankAccounts
import model.Transactions
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import repository.DatabaseDriver
import service.BankService

class BankingAppMain {

    private val connection = DatabaseDriver()
    private val bankService = BankService()

    fun main(){
        val status = connection.connectToDatabase()
        if(status!=null){
            initiateDatabase()
            bankService.welcomeScreen()
        }else{
            println("Something went wrong, please restart the application")
        }
    }

    private fun initiateDatabase() {
        transaction {
            SchemaUtils.create(BankAccounts,Transactions)
        }
    }
}
