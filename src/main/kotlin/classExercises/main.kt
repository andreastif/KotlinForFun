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
                mut[target - 1]
            else {
                mut.add(mut[mut.size - 2] + mut[mut.size - 1])
                fibonnacciLoop(mut, target)
            }
        }
        return fibonnacciLoop(mut, target)
    }

    println(findFibonnacciNum(21))

    //Övningsuppgift 4-a

    //glöm ej val/var i constructor till klassen, annars går det ej att använda/anropa i funktionerna
    open class Animal(var type: String, val numOfLegs: Int) {
        fun printType() = println(type)
        fun printLegs() = println(numOfLegs)

        //run låter oss omdefiniera en funktion eller variabel innanför scopet av den funktion som omsluter dessa.
        fun allInfo() = run {
            fun printType() =
                println("Gris") //omdefinierar till att skriva ut "gris" (har ingenting med >val type< att göra)

            fun printLegs() = println("HOWDY NEIGHBOR") //omdefinierar till att skriva ut "howdy neighbor"
            printType() //Anropar den LOKALA funktionen annars hör vi ingenting. Det räcker inte med att anropa allInfo. Vi måste anropa internt efter omdefinition.
            printLegs() //anropar den igen
        }

        fun allInfo2() {
            printType()
            printLegs()
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other !is Animal) return false

            if (type != other.type) return false
            if (numOfLegs != other.numOfLegs) return false

            return true
        }

        override fun hashCode(): Int {
            var result = type.hashCode()
            result = 31 * result + numOfLegs
            return result
        }

        override fun toString(): String {
            return "Animal(type='$type', numOfLegs=$numOfLegs)"
        }


    }

    val myTurtle = Animal("turtle", 4)
    val mySnail = Animal("Snail", 0)
    val myMonkey = Animal("Monkey", 2)
    myTurtle.allInfo()
    myTurtle.allInfo2()
    mySnail.allInfo2()
    myMonkey.allInfo2()

    //Övningsuppgift 4-b
    val myListOfAnimals = mutableListOf(myTurtle, mySnail, myMonkey)
    for (element in myListOfAnimals) {
        println("type: ${element.type}, no of legs: ${element.numOfLegs}")
    }

    //Övningsuppgift 4-c
    val mySet = mutableSetOf(myTurtle, myTurtle, mySnail, mySnail, myMonkey, myMonkey)
    val mySet2 = mutableSetOf(myMonkey, myTurtle, mySnail)
    mySet.forEach{e -> println("set1:$e")}
    mySet2.forEach{e -> println("set2:$e")}
    println(mySet == mySet2) //true

    //Övningsuppgift 4-d
//    I ditt djur-program, skapa upp en map över dina djur
//    • Låt djurets namn vara nyckel (du hittar på namn till dem)
//    • Låt själva djurobjektet vara värdet i mappen
//    • Skriv ut alla djur i din mapp
    val myMap = mutableMapOf("Ahmed" to myMonkey, "Lisa" to mySnail, "Giorgios" to myTurtle)
    myMap.forEach{e-> println(e)}

    //Övningsuppgift 4-e
    println("skriv turtle snail eller monkey")
    when (readLine()) {
        "turtle" -> println("blub blub")
        "snail" -> println("blib blib")
        "monkey" -> println("ooh ooh aah aah")
    }

    //Övningsuppgift 5-a
    data class Rectangle(val width: Int, val height: Int)

    //två olika sätt att skriva extension functions på
    fun Rectangle.Area(): Int {return this.width * this.height}
    fun Rectangle.Circumference(): Int = (this.width*2) + (this.height*2)

    val myRec1 = Rectangle(55,11)
    val myRec2 = Rectangle(55,11)

    //användning av extension function
    println(myRec1.Area())
    println(myRec1.Circumference())

    //test av data class equals
    println(myRec1 == myRec2) //true

    //Övningsuppgift 5-b
    fun String.DoubleUp() = println("$this $this")
    "hej".DoubleUp()

    //Övningsuppgift 5-c
    fun String.printRepeat(index: Int) =  repeat(index) {println("$this")}
    "hej".printRepeat(5)

    //Övningsuppgift 6-a
    //olika sätt att skriva en lambda på
    myMap.forEach{e-> println(e)}
    myMap.forEach{println({"${it.value}"})}
    myMap.forEach({e-> println(e.value)})
    myMap.forEach{e-> println(e)}

    //Övningsuppgift 6-b
    fun changeAnimal(mutableList: MutableList<Animal>) = mutableList.iterator().forEach { e -> e.type = "frog" }

    //Övningsuppgift 6-c
    changeAnimal(myListOfAnimals)
    myListOfAnimals.forEach{ println("$it") }
    val makeSpider = {animal: Animal -> if (animal.type == "frog" && animal.numOfLegs > 2) animal.type = "spider"}
    val removeFrog = {animal: Animal ->  animal.type != "frog" }
    myListOfAnimals.forEach(makeSpider)
    myListOfAnimals.filter(removeFrog).forEach{ println("$it") }

    //Övningsuppgift 6-d
    val findCat = {animal: Animal -> if (animal.type == "cat") println("cat is here") else println("cat is not here")}
    val findSpider = {animal: Animal -> if (animal.type == "spider") println("spider is here") else println("spider is not here")}
    myListOfAnimals.forEach(findCat)
    myListOfAnimals.forEach(findSpider)

    //Övningsuppgift 6-e
    fun getLegs(animal: Animal) : Int = animal.numOfLegs




}