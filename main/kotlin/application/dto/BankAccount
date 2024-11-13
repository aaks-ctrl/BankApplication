package dto

class BankAccount {

    private var userName:String
    private var fullName:String
    private var balance:Double
    private var password:String
    private var pin:Int
    private var accountNumber : String

    constructor(){
        this.userName = ""
        this.fullName = ""
        this.balance = 00.00
        this.password = ""
        this.pin = 1111
        this.accountNumber = ""
    }
    constructor(userName : String, fullName : String, balance : Double, password : String, pin : Int, accountNumber: String){
        this.userName = userName
        this.fullName = fullName
        this.balance = balance
        this.password = password
        this.pin = pin
        this.accountNumber = accountNumber
    }

    fun getUserName() : String {
        return this.userName
    }

    fun setUserName(userName : String) {
        this.userName = userName
    }

    fun getFullName() : String {
        return this.fullName
    }
    fun setFullName(name : String){
        this.fullName = name
    }

    fun getBalance() : Double {
        return this.balance
    }

    fun setBalance(balance : Double) {
        this.balance = balance
    }

    fun getPassword() : String {
        return this.password
    }

    fun setPassword(password : String){
        this.password = password
    }

    fun getPin() : Int {
        return this.pin
    }

    fun setPin(pin : Int){
        this.pin = pin
    }

    fun getAccountNo() : String {
        return this.accountNumber
    }

    fun setAccountNo(accountNumber : String) {
        this.accountNumber = accountNumber
    }

    override fun toString() : String {
        return this.getFullName() + " " + this.getBalance() + " " + this.getUserName() + " " + this.getAccountNo();
    }
}
