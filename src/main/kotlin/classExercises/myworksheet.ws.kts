package classExercises


for (i in 1 until 10) {
    println("pig")
}
val test = 2
if (test < 0) println("pig") else if (test == 1) println ("house") else println("mouse")

fun incrementWithTwo(value : Int, default: Int = value+2): Int {
    return default
}

5+incrementWithTwo(3)
5+incrementWithTwo(value = 3, default = 55)
5+incrementWithTwo(default = 5, value = 1)

fun calcArea(x: Double, y: Double): Double {
    return x * y
}
fun calcCircumference(x: Double, y: Double):Double {
    return (x*2)+(y*2)
}

calcArea(1.2,2.0)
calcCircumference(2.0,2.0)
val name = "Andreas"
println("Hej $name")