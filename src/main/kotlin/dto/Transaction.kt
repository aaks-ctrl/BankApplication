package dto

import java.sql.Timestamp

class Transaction {
    private var accountNumber : String
    private var transactionId : Long
    private var transactionType : String
    private var amount : Double
    private var openingBalance : Double
    private var timestamp : Long

    constructor(){
        this.accountNumber = ""
        this.transactionId = 1L
        this.transactionType = ""
        this.amount = 0.0
        this.openingBalance = 0.0
        this.timestamp = 1L
    }

    constructor(accountNumber: String,transactionId : Long, transactionType : String, amount : Double, timestamp: Long, openingBalance : Double ){
        this.accountNumber = accountNumber
        this.transactionId = transactionId
        this.transactionType = transactionType
        this.amount = amount
        this.timestamp = timestamp
        this.openingBalance = openingBalance
    }

    fun getAccountNo() : String {
        return this.accountNumber
    }

    fun setAccountNo(accountNumber : String) {
        this.accountNumber = accountNumber
    }

    fun getTransactionId() : Long {
        return this.transactionId
    }

    fun setTransactionId(transactionId: Long) {
        this.transactionId = transactionId
    }

    fun getTransactionType() : String {
        return this.transactionType
    }

    fun setTransactionType(transactionType: String) {
        this.transactionType = transactionType
    }

    fun getAmount(): Double {
        return this.amount
    }

    fun setAmount(amount: Double){
        this.amount = amount
    }

    fun getTimeStamp() : Long {
        return this.timestamp
    }

    fun setTimeStamp(timestamp: Long){
        this.timestamp = timestamp
    }

    fun getOpeningBalance(): Double {
        return this.openingBalance
    }

    fun setOpeningBalance(openingBalance : Double) {
        this.openingBalance = openingBalance
    }
}
