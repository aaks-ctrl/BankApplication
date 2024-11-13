package service

import dto.BankAccount
import dto.Transaction
import repository.*
import util.Helper
import util.constants
import java.util.UUID
import kotlin.system.exitProcess

class BankService {

    private val helper = Helper()
    private val constants = constants()

    private val bankRepo = BankAccountRepository()
    private val transactionRepo = TransactionRepository()

    private var bankAccount : BankAccount = BankAccount()
    fun setBankAccount(bankAccount: BankAccount?){
        if (bankAccount != null) {
            this.bankAccount = bankAccount
        }else{
            this.bankAccount = BankAccount()
        }
    }

    private var transaction : Transaction = Transaction()
    fun setTransaction(transaction : Transaction?){
        if(transaction != null){
            this.transaction = transaction
        }else{
            this.transaction = Transaction()
        }
    }

    private var transactions : ArrayList<Transaction> = arrayListOf()
    fun setTransactions(transactions : ArrayList<Transaction>?){
        if(transactions != null){
            this.transactions = transactions
        }else{
            this.transactions = arrayListOf()
        }
    }

    fun welcomeScreen(){
        helper.headerFooter()
        println(constants.welcomeMessage)
        helper.padding(3)
        print(constants.welcomeScreenOption)
        helper.padding(2)
        println(constants.chooseMessage)
        helper.headerFooter()
        helper.padding(2)
        try {
            var input = readLine()!!.toInt()
            if(input === 1){
                loginScreen()
            }else if(input === 2){
                signUpScreen()
            }else{
                throw NumberFormatException()
            }
        }catch(e : NumberFormatException){
            print(constants.wrongInput)
            helper.loadingEffect()
            welcomeScreen()
        }
    }

    private fun loginScreen(){
        helper.headerFooter()
        println(constants.bankHeader)
        helper.padding(3)
        print(constants.pleaseInput+constants.username+" : ")
        var input = readln()
        helper.padding(2)
        if(!bankRepo.getBankAccount(input.trim())){
            print(constants.noUserFound)
            helper.loadingEffect()
            helper.headerFooter()
            loginScreen()
        }else{
            print(constants.pleaseInput+constants.password+" : ")
            input = readln()
            helper.padding(1)
            try {
                bankAccount = bankRepo.checkAuth(input.trim())
                helper.headerFooter()
                accountMainScreen()
            }catch(e : Exception){
                print(e.message)
                helper.loadingEffect()
                loginScreen()
            }
        }
    }

    private fun signUpScreen(){
        var text : String
                helper.headerFooter()
        println(constants.welcomeMessage)
        helper.padding(3)
        print(constants.enterUserName)
        text = readln()
        if(!bankRepo.getBankAccount(text.trim())){
            bankAccount.setUserName(text.trim())
            print(constants.enterFullName)
            text = readln()
            bankAccount.setFullName(text.trim())
            print(constants.enterPassWord)
            text = readln()
            bankAccount.setPassword(text.trim())
            print(constants.enterPin)
            text= readln()
            bankAccount.setPin(text.toInt())
            helper.padding(1)
            bankAccount.setAccountNo(UUID.randomUUID().toString())
            bankRepo.createAccount(bankAccount)
            println("Bank account with account no. ${bankAccount.getAccountNo()} is created successfully")
            print(constants.pleaseLogin)
            helper.loadingEffect()
            bankAccount = BankAccount()
            welcomeScreen()
        }else{
            print(constants.accountExists)
            helper.loadingEffect()
            signUpScreen()
        }
    }

    private fun depositScreen(){
        helper.headerFooter()
        println(constants.bankHeader)
        helper.padding(2)
        print(constants.depositAmount)
        helper.headerFooter()
        helper.padding(1)
        val input = readln()
        if(input.toString() != input){
            print(constants.invalidInput)
            helper.loadingEffect()
            welcomeScreen()
        }else{
            bankAccount.setBalance(bankRepo.updateBalance(bankAccount.getUserName(), input.toDouble()))
            transaction = Transaction(bankAccount.getAccountNo(),0,"D",input.toDouble(),System.currentTimeMillis(),bankAccount.getBalance().minus(input.toDouble()))
            transactionRepo.putTransaction(transaction)
            println("$input deposited successfully into your account")
            balanceScreen()
        }
    }

    private fun withdrawScreen(){
        helper.headerFooter()
        println(constants.bankHeader)
        helper.padding(2)
        print(constants.withdrawAmount)
        helper.headerFooter()
        helper.padding(1)
        val input = readln()
        if(input.toString() != input){
            print(constants.invalidInput)
            helper.loadingEffect()
        }else{
            if(input.toDouble() > bankAccount.getBalance()){
                print(constants.insufficientBal)
                helper.loadingEffect()
            }else{
                print(constants.pin)
                helper.padding(1)
                val pin = readln()
                if(pin.toString()==pin && bankRepo.checkPin(bankAccount.getUserName(),pin.toInt())){
                    bankAccount.setBalance(bankRepo.updateBalance(bankAccount.getUserName(), -input.toDouble()))
                    transaction = Transaction(bankAccount.getAccountNo(),0,"W",input.toDouble(),System.currentTimeMillis(),bankAccount.getBalance().plus(input.toDouble()))
                    transactionRepo.putTransaction(transaction)
                    println("$input withdrawn successfully from your account")
                    balanceScreen()
                }else{
                    print(constants.incorrectPin)
                    helper.loadingEffect()
                }

            }
        }
        accountMainScreen()
    }

    private fun transactionHistoryScreen(){
        helper.headerFooter()
        println(constants.bankHeader)
        helper.padding(2)
        transactions = transactionRepo.getTransactions(bankAccount.getAccountNo())
        if(transactions.size!=0){
            println("Transaction ID  |  Type  |  Opening Balance  |  Amount  |  Closing Balance  |  Time")
            for(i in transactions) {
                val closingBalance = if(i.getTransactionType() == "D") i.getOpeningBalance()+i.getAmount() else i.getOpeningBalance()-i.getAmount()
                println("${i.getTransactionId()}               |  ${i.getTransactionType()}      |  ${i.getOpeningBalance()}          |  ${i.getAmount()}  |  $closingBalance  |  ${java.util.Date(i.getTimeStamp())}")
            }
            helper.headerFooter()
            println(constants.pressContinue)
            helper.pressContinue()
        }else{
        print(constants.noRecord)
            helper.loadingEffect()
        }
        accountMainScreen()
    }

    private fun balanceScreen(){
        helper.padding(2)
        println("Your account balance is : ${bankAccount.getBalance()}")
        helper.padding(2)
        println(constants.pressContinue+"...")
        helper.pressContinue()
        accountMainScreen()
    }

    private fun abort(){
        helper.padding(2)
        println(constants.abortMessage)
        exitProcess(1)
    }

    private fun accountMainScreen(){
        helper.headerFooter()
        println(constants.bankHeader)
        helper.padding(2)
        println("Hi! ${bankAccount.getFullName()}")
        helper.padding(2)
        for(i in constants.accountMainOption) println(i)
        helper.headerFooter()
        helper.padding(2)
        println(constants.chooseMessage)
        val input = readln().trim()
        when(input.uppercase()){
            "D" -> depositScreen()
            "W" -> withdrawScreen()
            "H" -> transactionHistoryScreen()
            "B" -> balanceScreen()
            "N" -> abort()
            else -> {
                print(constants.wrongInput)
                helper.loadingEffect()
                accountMainScreen()
            }
        }
    }
}
