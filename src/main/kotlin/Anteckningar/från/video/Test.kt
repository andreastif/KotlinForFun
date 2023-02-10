import kotlin.system.exitProcess

fun main() {

    println("Hello World")

    //måste ange returparameter genom --> : Int
    fun giveMeFour(): Int {
        return 4;
    }

    //Kotlin infererar själv returtypen om vi har en "oneliner" dvs utan måsvingar
    fun giveMeFive() = 5;

    println("give me four: " + giveMeFour())
    println("give me five: " + giveMeFive())

    //funktioner med inparametrar
    fun addtwo(x: Int) = x + 2 //en inparameter, ej angiven returtyp (inferens)
    fun addTwoNums(x: Int, y: Int) = x + y //två inparametrar, ej anggiven retyrtyp (inferens)
    fun addThreeNums(x: Int, y: Int, z: Int): Double =
        (x + y + z).toDouble() //tre inparametrar, explicit returtyp med cast

    //the old "java way"
    fun addIt(s: String, s2: String): String { //returtyp är String
        return s + s2
    }

    //using $ sign, returning void (explicit, behövs dock ej)
    fun sayHi(x: String, y: String): Unit = println("Hi $x and $y, nice to meet you!")
    sayHi("gris", "slafs")


    //if sats i kotlin
    val myNum = -11
    if (myNum > 0)
        println("robot")
    else
        println("machine")

    //vi kan använda if/else i kotlin rakt i en sträng, ser ut som en ternary operator
    val result = if (myNum > 0) "robot" else "washing machine"
    println(result)

    //en if/elseif/else i en funktion

    fun withinBounds(lowerLimit: Double, upperLimit: Double, value: Double): String {
        return if (value < lowerLimit) "to low"
        else if (value > upperLimit) "To high"
        else "Value is within bounds"
    }

    val boundsResult = withinBounds(5.0, 83.5, 22.1)
    println(boundsResult)

    //Funktioner kan ha andra funktioner i sig
    fun eval(x: Int) = if (x > 100) "wow" else "boo"
    println(eval(5))


    //while loopar (precis som java)
    var i = 0
    while (i < 100) {
        print(".")
        i += 10
    }

    //for loopar, ser ut som javas for each
    //loopar HELA listan
    val myList: ArrayList<String> = arrayListOf(
        "Janne",
        "banne",
        "hanne",
        "ramme"
    ) //OBS!! List<?> är READ ONLY. Använd arraylist så har du add/remove
    for (entries in myList) {
        println(entries)
    }
    myList.add("jens")
    for (entries in myList) {
        println(entries)
    }

    //loopar med tillgång till index
    for ((i, item) in myList.withIndex()) {
        println("$item is at index $i")
    }

    //for loopar och ranges
    for (i in 1..5) { //så länge som i är mindre än 5, printa hey. Börjar på 1
        println("Hey $i")
    }

    for (i in 0..myList.size - 1) { //en traditionel for loop fast i kotlin
        println(myList.get(i))
    }

    for (i in 0 until myList.size) { // en variant på ovanstående for loop med kotlin syntax (until, until = mylist.size-1)
        println(myList.get(i))
    }

    //man kan räkna baklänges också
    for (i in 3 downTo 1) {
        println("Hey $i")
    }

    for (i in myList.size - 1 downTo 0) {
        println(myList.get(i))
    }

    //vi kan iterera flera steg i taget också
    for (i in 1..6 step 2) { //ökar i med 2 i taget
        println("Hey $i")
    }

    //IntRange
    //random
    var randomNumber = (1..10).random() // ger en random siffra mellan 1-10

    //vi kan loopa över chars också eftersom chars är numeriska värden, går från a-z i alfabetisk ordning
    for (c in 'a'..'z') {
        print(c)
    }
    println()

    //vi kan iterera över strängar också, och eftersom vi plockar ut en char kan vi lägga till en siffra så hoppar vi
    //ett steg fram i alfabetet över vad som faktiskt printas ut (printar ej ut abcd, utan bcdf)
    val mystring = "abcd"
    for (i in 0..mystring.length - 1) {
        println(mystring[i] + 1) //hoppar fram ett steg i alfabetet
    }

    //kan iterera utan att ange ett index också
    for (ch in "blabla") {
        print(ch + 1)
    }
    println()
    //sist men inte minst kan vi också bara ange nyckelordet repeat(x) och uppge hur många ggr vi vill loopa någonting
    repeat(5) {
        println("i am being printed 5 times")
    }

    //nu loopar vi så vi kan köra felhantering

//    fun loop() {
//        try {
//            while (true) {
//                //läsa från konsollen med Kotlin'
//                var userInput = readLine() //så enkelt!
//                println("userinput $userInput");
//
//                //Om vi vill casta om värdet till en int?
//                //userInput.toInt() //FEL - kan ge ifrån sig null! måste dock passa med Java, därför finns 2 operatorer i kotlin
//                //där det ev kan bli null: !! eller ?
//
//                //nullhantering i kotlin:
//                //!! (not-null) betyder att vi hindrar att vi får null i programmet, kontrollerar att strängen som läses in INTE är null
//                var myInput = readLine()!!.toInt() //RÄTT kontrollerar att input inte är null
//                println("myInput $myInput");
//                //? (elvis-operator) berättar att om vår sträng INTE är null skall vi exekvera det som står bakom,
//                // annars låt vara (som en optional)
//                var yourInput = readLine()?.toInt()
//                println("yourInput $yourInput");
//
//                if (myInput == 0 || yourInput == 0) {
//                    exitProcess(0) //ersätter System.exit(0) från Java
//                }
//            }
//
//        } catch (nfe: NumberFormatException) {
//            println("Felaktig input, skall vara en siffra!")
//            loop()
//        }
//    }
//    loop()

    //rekursion i kotlin
    //rekursion betyder att man skriver kod som anropar sig själv.

    //rekursion -> klassisk
    fun faculty(x : Long) : Long {
        if (x <= 1) return 1
        return x * faculty(x-1)
    }
    println(faculty(3)) // 3! = 3*2*1 = 6
    //Vad är det som händer när vi anropar faculty?
    //vi stoppar in 3, och kotlin kan inte räkna ut vad 3 fac 2 är ännu för den har fortfarande inte träffat condition som returnerar 1
    //kör samma funktion IGEN dvs faculty(2) ... tills 1an hittas.
    // faculty(3) => return 3 * faculty(2) => return * (2 * faculty(1)) => return 3 * (2 * 1) => 6


    //rekursion -> med ackumulator
    fun factAcc(n : Long) : Long { //vi skickar in det talet vi vill använda fakultet på

        tailrec fun factInnerAcc(acc: Long, n: Long) : Long { //här är funktionen som ansvarar för att "loopa"
            if (n==1L)
                return acc //om vi når 1 på n, betyder det att vi har multiplicerat färdigt
            else
                return factInnerAcc(n*acc, n-1) //här multiplicerar vi om vi nte får 1 på n
        }
        return factInnerAcc(1, n)
    }

    println(factAcc(3))
    //det som händer i factAcc är följande
    //vi anropar factAcc(3) med 3 => den inre funktionen tar in en ackumulator och storlek på n
    // => ackumulator börjar på 1 när n är 3
    // => i else return kommer vi att få 1*3, vilket är 3
    // => i nästa anrop skickar vi in en ackumulator på 3 (alltså 3*1) och vi tar 3-1 och får 2
    // => eftersom n fortfarande inte är 1, blir ackumulatorn 3*2 eller 6
    // => nu skickar vi in 6 och 1, därmed upphör rekursionen då vi returnerar ut "acc" då if kriteriet är uppfyllt.

    //rekursion -> svansrekursion
    //tailrec används för att berätta för kotlin att optimera rekursionen
    //tillåts bara när det sista anropet står ensamt i sin sats (????)
    //och är för funktioner d¨det rekursiva anropet sker sist i funktionen (???)

    fun factAcc2(n : Long) : Long { //vi skickar in det talet vi vill använda fakultet på

        tailrec fun factInnerAcc(acc: Long, n: Long) : Long { //här är funktionen som ansvarar för att "loopa"
            if (n==1L)
                return acc //om vi når 1 på n, betyder det att vi har multiplicerat färdigt
            else
                return factInnerAcc(n*acc, n-1) //här multiplicerar vi om vi nte får 1 på n
        }
        return factInnerAcc(1, n)
    }

}