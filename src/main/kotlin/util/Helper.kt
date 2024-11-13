package util

import java.util.concurrent.TimeUnit

class Helper {

    fun padding(value : Int){
        for(i in 1..value) println("")
    }

    fun headerFooter(){
        padding(2)
        for(i in 1..80) print("=")
        println("")
    }

    fun loadingEffect(){
        for(i in 1..4) {
            TimeUnit.SECONDS.sleep(1L)
            print(".")
        }
    }

    fun pressContinue(){
        val input = readln()
    }
}
