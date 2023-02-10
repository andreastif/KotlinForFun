package classExercises


//import kotlin.system.exitProcess


fun main() {

    //Exercise 1a (videotape)
    fun showTotalTime(startingHour: Int, startingMinute: Int, endingHour: Int, endingMinute: Int): Int {
        return (((endingHour - startingHour) * 60) - startingMinute) + endingMinute
    }

    fun isThereEnoughVideoTape(totalTapeInMin: Int, usedUpTapeInMin: Int, showTotalTime: Int): Boolean {
        return ((totalTapeInMin - usedUpTapeInMin) - showTotalTime >= 0)
    }

    val result = showTotalTime(20, 15, 21, 0)
    val result2 = showTotalTime(21, 5, 23, 5)
    println(isThereEnoughVideoTape(60, 0, result))
    println(isThereEnoughVideoTape(60, 0, result2))

    //Exercise 1b (Mindre läsbart imo)
    fun showTotalTimeWithShortNotation(
        startingHour: Int, startingMinute: Int, endingHour: Int, endingMinute: Int
    ): Int = (((endingHour - startingHour) * 60) - startingMinute) + endingMinute

    fun isThereEnoughVideoTapeWithShortNotation(
        totalTapeInMin: Int, usedUpTapeInMin: Int, showTotalTime: Int
    ): Boolean = ((totalTapeInMin - usedUpTapeInMin) - showTotalTime >= 0)

    val result3 = showTotalTimeWithShortNotation(20, 15, 21, 0)
    val result4 = showTotalTimeWithShortNotation(21, 5, 23, 5)
    println(isThereEnoughVideoTapeWithShortNotation(60, 0, result3))
    println(isThereEnoughVideoTapeWithShortNotation(60, 0, result4))


    //Övningsuppgift 2-a
    for (i in 20 downTo 1) if (i % 2 == 0) println(i)

    //Övningsuppgift 2b
    fun multiplikationstabell(target: Int, lowest: Int, highest: Int) {
        for (i in lowest..highest) {
            println("Multiplikation av $target gånger $i = ${target * i}")
        }
    }
    multiplikationstabell(5, 0, 9)

    //Övningsuppgift 2-c
    fun isPrime(num: Int): Boolean {
        var myBool = true
        for (i in 2 until num) {
            if (num % i == 0) {
                myBool = false
                break
            }
        }
        return myBool
    }
    println(isPrime(4))

    //Övningsuppgift 3-a

//    fun guessNumGame() {
//        val randomNumber = (1..10).random()
//
//        while (true) {
//            println("Gissa på en siffra")
//            try {
//                val guess = readLine()!!.toInt()
//                fun guessNum(num: Int, random: Int) {
//                    println(random)
//                    if (num == random) {
//                        println("You won! Spela igen? ja/nej?")
//                        if (readLine().equals("ja")) guessNumGame() else exitProcess(0)
//                    } else if (num < random) {
//                        println("Higher!")
//                    } else {
//                        println("Lower!")
//                    }
//                }
//                guessNum(guess, randomNumber)
//            } catch (e: IllegalArgumentException) {
//                println("You must enter a number")
//            }
//        }
//    }
//    guessNumGame()

    //Övningsuppgift 7-a

    fun compoundInterest(value: Double, numOfYears: Long, rate: Double): Double {
        val myVal: Double = value * rate
        if (numOfYears > 1) {
            return compoundInterest(myVal, numOfYears - 1, rate)
        }
        return myVal
    }

    println(compoundInterest(100.0, 12, 1.03))

    //Övningsuppgift 7-b
    fun sumList(listOfIntegers: ArrayList<Int>): Int {
        fun ack(index: Int, value: Int): Int {
            if (index > -1) {
                val lastVal = listOfIntegers[index]
                return ack(index - 1, (value + lastVal))
            }
            return value
        }
        return ack(listOfIntegers.size - 1, 0)
    }


    val myList = arrayListOf(100, 2, 6, 4, 8, 99)
    println("sum: ${sumList(myList)}")
    println("reduce: ${myList.reduce { sum, part -> sum + part }}")

    //Övningsuppgift 7-c
    fun maxValInList(listOfIntegers: ArrayList<Int>): Int {
        tailrec fun ack(index: Int, value: Int): Int {
            if (index > -1) {
                return ack(index - 1, if (listOfIntegers[index] > value) listOfIntegers[index] else value)
            }
            return value
        }
        return ack(listOfIntegers.size - 1, 0)
    }
    println(maxValInList(myList))

    //Övningsuppgift 7-d
    fun getStringBackwards(word: String): String {
        fun iterateBackwards(index: Int, word: String, backwardsWord: String): String {
            if (index > -1) {
                return iterateBackwards(index - 1, word, backwardsWord + word[index])
            }
            return backwardsWord
        }
        return iterateBackwards(word.length - 1, word, "")
    }
    println(getStringBackwards("gubbe"))

    //Övningsuppgift 7-e

    fun balancedOrNot(word: String): Boolean {

        fun increment(map: MutableMap<String, Int>, key: String): MutableMap<String, Int> {
            //Anrop av map med Key, kollar om det finns. Kommer lagra value i count. OM ej finns = null
            //och vi skapar vår första mappning mot parantesen
            if (key == ")" || key == "(") {
                when (val count = map[key]) {
                    null -> map[key] = 1
                    else -> map[key] = count + 1
                }
            }
            return map
        }


        fun findParentheses(index: Int, word: String, mutMap: MutableMap<String, Int>): MutableMap<String, Int> {
            if (index > -1) {
                return findParentheses(index - 1, word, increment(mutMap, word[index].toString()))
            }
            return mutMap
        }

        fun isBalanced(mutMap: MutableMap<String, Int>): Boolean {
            val obj1 = mutMap[")"]
            val obj2 = mutMap["("]
            return obj1 == obj2
        }

        return isBalanced(findParentheses(word.length - 1, word, mutableMapOf()))
    }

    println(balancedOrNot(")!)((")) //true

    //Övningsuppgift 7-f

    fun findFibonnacciNum(target: Int): Int {

        //Ta in ett index-tal (target)
        //Börja från 1..2 och summera talen
        //Spara summan i en lista
        //För varje iteration, minska index med 1 (index används som en counter) obs! Tänk på index börjar från 0 jmfr m inskickat index.
        //Exekvera fram tills att man har förbrukat index
        //Returnera den siffra som finns på target index

        val mut: MutableList<Int> = mutableListOf(1, 2)

        fun fibonnacciLoop(mut: MutableList<Int>, target: Int): Int {
            return if (mut.size == target)
                mut[target-1]
            else {
                mut.add(mut[mut.size-2] + mut[mut.size-1])
                fibonnacciLoop(mut, target)
            }
        }
        return fibonnacciLoop(mut, target)
    }

    println(findFibonnacciNum(21))

}