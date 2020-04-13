@file:Suppress("UNUSED_PARAMETER")

package lesson2.task1

import lesson1.task1.discriminant
import lesson1.task1.sqr
import kotlin.math.max
import kotlin.math.min
import kotlin.math.sqrt

/**
 * Пример
 *
 * Найти число корней квадратного уравнения ax^2 + bx + c = 0
 */
fun quadraticRootNumber(a: Double, b: Double, c: Double): Int {
    val discriminant = discriminant(a, b, c)
    return when {
        discriminant > 0.0 -> 2
        discriminant == 0.0 -> 1
        else -> 0
    }
}

/**
 * Пример
 *
 * Получить строковую нотацию для оценки по пятибалльной системе
 */
fun gradeNotation(grade: Int): String = when (grade) {
    5 -> "отлично"
    4 -> "хорошо"
    3 -> "удовлетворительно"
    2 -> "неудовлетворительно"
    else -> "несуществующая оценка $grade"
}

/**
 * Пример
 *
 * Найти наименьший корень биквадратного уравнения ax^4 + bx^2 + c = 0
 */
fun minBiRoot(a: Double, b: Double, c: Double): Double {
    // 1: в главной ветке if выполняется НЕСКОЛЬКО операторов
    if (a == 0.0) {
        if (b == 0.0) return Double.NaN // ... и ничего больше не делать
        val bc = -c / b
        if (bc < 0.0) return Double.NaN // ... и ничего больше не делать
        return -sqrt(bc)
        // Дальше функция при a == 0.0 не идёт
    }
    val d = discriminant(a, b, c)   // 2
    if (d < 0.0) return Double.NaN  // 3
    // 4
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    val y3 = max(y1, y2)       // 5
    if (y3 < 0.0) return Double.NaN // 6
    return -sqrt(y3)           // 7
}

/**
 * Простая
 *
 * Мой возраст. Для заданного 0 < n < 200, рассматриваемого как возраст человека,
 * вернуть строку вида: «21 год», «32 года», «12 лет».
 */


fun lastNumber(age: Int): Int {
    if (age < 0) return -1 //отправлю код ошибки "Меньше нуля. За рамками задачи"
    else if (age in 0..9) return age
    else if (age in 10..99) return age % 10
    else if (age in 100..200) return (age - age / 100 * 100) % 10
    else return -200 //отправлю код ошибки "Больше 200. За рамками задачи"
}

fun ageDescription(age: Int): String {
    /* В начале найдем последнюю цифру в числе */
    val lastNumber: Int = lastNumber(age)
    /* Вторым шагом вывод соответствующего литерала */
    if (age in 11..14 || age in 111..114) {
        return "$age лет"
    } else if (lastNumber == 1) {
        return "$age год"
    } else if (lastNumber in 2..4) {
        return "$age года"
    } else {
        return "$age лет"
    }
}

/**
 * Простая
 *
 * Путник двигался t1 часов со скоростью v1 км/час, затем t2 часов — со скоростью v2 км/час
 * и t3 часов — со скоростью v3 км/час.
 * Определить, за какое время он одолел первую половину пути?
 */
fun timeForHalfWay(t1: Double, v1: Double,
                   t2: Double, v2: Double,
                   t3: Double, v3: Double): Double {
    val s1 = t1 * v1
    val s2 = t2 * v2
    val s3 = t3 * v3
    val s = s1 + s2 + s3
    println("Всё расстояние: $s = $s1 + $s2 + $s3")
    val half_s = s / 2
    println("Половина расстояния: $half_s")

    if (half_s < s1) return half_s / v1
    else if (half_s < (s1 + s2)) {
        println("Т.к. половина расстояния меньше второго отрезка и больше первого отрезка: ")
        val part_s2 = half_s - s1
        val part_t2 = part_s2 / v2
        println("Кусочек второго отрезка: $part_s2 за $part_t2 времени")
        val half_t = part_t2 + t1
        println("Результат: $half_t")
        return half_t
    } else if (half_s < s) return (half_s - s1 - s2) / v3 + t2 + t1
    else return s3 / v3 + s2 / v2 + s1 / v1
}

/**
 * Простая
 *
 * Нa шахматной доске стоят черный король и две белые ладьи (ладья бьет по горизонтали и вертикали).
 * Определить, не находится ли король под боем, а если есть угроза, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от первой ладьи, 2, если только от второй ладьи,
 * и 3, если угроза от обеих ладей.
 * Считать, что ладьи не могут загораживать друг друга
 */
fun whichRookThreatens(kingX: Int, kingY: Int,
                       rookX1: Int, rookY1: Int,
                       rookX2: Int, rookY2: Int): Int {
    var result: Int = 0
    when (kingX) {
        rookX1 -> result += 1
        rookX2 -> result += 2
    }
    when (kingY) {
        rookY1 -> result += 1
        rookY2 -> result += 2
    }
    return result
}




fun diagonalChess(coordinates: Pair<Int, Int>, vector: Pair<Boolean, Boolean>): Array<Pair<Int, Int>> {
    var diagonal = emptyArray<Pair<Int, Int>>()
    var x1 = coordinates.first
    var y1 = coordinates.second
    if(vector.first==true && vector.second==true) while (x1 < 8 && y1 < 8) diagonal += ++x1 to ++y1
    if(vector.first==false && vector.second==false) while (x1 > 1 && y1 > 1) diagonal += --x1 to --y1
    if(vector.first==true && vector.second==false) while (x1 < 8 && y1 > 1) diagonal += ++x1 to --y1
    if(vector.first==false && vector.second==true) while (x1 > 1 && y1 < 8) diagonal += --x1 to ++y1
    return diagonal
}
fun allDiagonalChess(coordinates: Pair<Int, Int>): Array<Pair<Int, Int>> {
    val diagonalMax = diagonalChess(coordinates, (true to true))
    val diagonalMin = diagonalChess(coordinates, (false to false))
    val diagonalOnlyXMax = diagonalChess(coordinates, (true to false))
    val diagonalOnlyYMax = diagonalChess(coordinates, (false to true))
    val allDiagonal = diagonalMax + diagonalMin + diagonalOnlyXMax + diagonalOnlyYMax
    return allDiagonal
}
/**
 * Простая
 *
 * На шахматной доске стоят черный король и белые ладья и слон
 * (ладья бьет по горизонтали и вертикали, слон — по диагоналям).
 * Проверить, есть ли угроза королю и если есть, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от ладьи, 2, если только от слона,
 * и 3, если угроза есть и от ладьи и от слона.
 * Считать, что ладья и слон не могут загораживать друг друга.
 */

fun rookOrBishopThreatens(kingX: Int, kingY: Int,
                          rookX: Int, rookY: Int,
                          bishopX: Int, bishopY: Int): Int {
    var result = 0
    val kingCoordinates = kingX to kingY
    val bishopCoordinates = bishopX to bishopY
    val allDiagonal = allDiagonalChess(kingCoordinates)
    if(allDiagonal.contains(bishopCoordinates)) result += 2
    if(kingX == rookX || kingY == rookY) result += 1
    return result
}


fun main(args: Array<String>) {
    val a = 5.0
    val b = 4.0
    val c = 4.0

}
fun thCosAngle(max: Double, b: Double, c: Double): Double =
        (sqr(max) - sqr(b) - sqr(c)) / (-2 * b * c)

fun triangleKindCos(a: Double, b: Double, c: Double): Double {
    /* Сравним стороны */
    val m = max(max(a, b), c)
    when (m){
        a -> return thCosAngle(a, b, c)
        b -> return thCosAngle(b, a, c)
        c -> return thCosAngle(c, a, b)
        else -> return -1.0
    }
}
/**
 * Простая
 *
 * Треугольник задан длинами своих сторон a, b, c.
 * Проверить, является ли данный треугольник остроугольным (вернуть 0),
 * прямоугольным (вернуть 1) или тупоугольным (вернуть 2).
 * Если такой треугольник не существует, вернуть -1.
 */
fun triangleKind(a: Double, b: Double, c: Double): Int {
    val cos = triangleKindCos(a,b,c)
    when{
        (a>b+c || b>a+c || c>a+b) -> return -1
        cos > 0 -> return 0
        cos == 0.0 -> return 1
        else -> return 2
    }
    println(cos)
}

/**
 * Средняя
 *
 * Даны четыре точки на одной прямой: A, B, C и D.
 * Координаты точек a, b, c, d соответственно, b >= a, d >= c.
 * Найти длину пересечения отрезков AB и CD.
 * Если пересечения нет, вернуть -1.
 */
fun segmentLength(a: Int, b: Int, c: Int, d: Int): Int {
    val begin_first = min(a,c)
    val end_first = max(a,c)

    val end_last = max(b,d)
    val begin_last = min(b,d)
//    if (begin_first == a) if ()


    if (begin_first == end_last) return 1
    else return -1
}
