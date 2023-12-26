package days

import kotlin.math.absoluteValue
import kotlin.math.sign

fun lcm(x: Long, y: Long, vararg ints: Long): Long =
    ints.fold(x * y / gcd(x, y)) { acc, z -> lcm(acc, z) }

fun lcm(x: Long, y: Long, ints: List<Long>): Long =
    ints.fold(x * y / gcd(x, y)) { acc, z -> lcm(acc, z) }

fun gcd(a: Long, b: Long): Long {
    if (b == 0L) return a
    return gcd(b, a % b)
}

fun pairs(intRange: IntRange): List<Pair<Int, Int>> =
    intRange.flatMap { dy ->
        intRange.map { dx ->
            Pair(dx, dy)
        }
    }

fun <T> Collection<T>.permutations(): Set<List<T>> {
    if (isEmpty()) return setOf(emptyList())
    val result: MutableSet<List<T>> = mutableSetOf()
    for (element in this) {
        (this - element).permutations().forEach { item ->
            result.add(item + element)
        }
    }
    return result
}

fun <T> Collection<T>.arrangements(): Set<List<T>> {
    if (size <= 3) return setOf(this.toList())
    val result: MutableSet<List<T>> = mutableSetOf()
    val first = first()
    this.drop(1).arrangements().forEach { list ->
        for (index in list.indices) {
            val new = list.toMutableList()
            new.add(index, first)
            result.add(new)
        }
    }
    return result
}

data class Point(val x: Int, val y: Int) {

    val north: Point get() = this.copy(y = y - 1)
    val northwest: Point get() = this + Point(-1, -1)
    val northeast: Point get() = this + Point(1, -1)
    val south: Point get() = this + Point(0, 1)
    val southwest: Point get() = this + Point(-1, 1)
    val southeast: Point get() = this + Point(1, 1)
    val west: Point get() = this + Point(-1, 0)
    val east: Point get() = this + Point(1, 0)

    fun neighboursAndSelf(): Set<Point> = setOf(
        copy(x = x + 1),
        copy(y = y + 1),
        copy(x = x - 1),
        copy(y = y - 1),
        copy(),
    )

    fun neighbours() = setOf(
        copy(x = x + 1),
        copy(y = y + 1),
        copy(x = x - 1),
        copy(y = y - 1),
    )

    fun neighboursAll() = setOf(
        copy(x = x + 1),
        copy(x = x - 1),
        copy(x = x + 1, y = y - 1),
        copy(x = x + 1, y = y + 1),
        copy(y = y + 1),
        copy(y = y - 1),
        copy(x = x - 1, y = y - 1),
        copy(x = x - 1, y = y + 1),
    )

    fun lineto(to: Point): Sequence<Point> {
        val dx = (to.x - x).sign
        val dy = (to.y - y).sign

        return generateSequence(this) {
            if (it == to) null
            else it + Point(dx, dy)
        }
    }

    operator fun plus(other: Point) = Point(x + other.x, y + other.y)
    operator fun minus(other: Point) = Point(x - other.x, y - other.y)
    operator fun times(value: Int) = Point(x * value, y * value)

    public fun distance(other: Point): Int {
        return (other.x - x).absoluteValue + (other.y - y).absoluteValue
    }

    override fun toString(): String {
        return "P($x,$y)"
    }

    companion object {
        val WEST = Point(-1, 0)
        val EAST = Point(1, 0)
        val NORTH = Point(0, -1)
        val SOUTH = Point(0, 1)

        fun from(line: String) = line
            .split(",")
            .map(String::toInt)
            .let { (x, y) -> Point(x, y) }
    }
}

operator fun Int.times(vector: Point) = vector.times(this)

fun List<String>.extract(char: Char): Set<Point> {
    return this.flatMapIndexed() { y, row -> row.mapIndexedNotNull { x, c -> if (c == char) Point(x, y) else null } }
        .toSet()
}

fun Iterable<Point>.draw() {
    (minOf { it.y }..maxOf { it.y }).forEach { y ->
        (minOf { it.x }..maxOf { it.x }).map { x -> if (Point(x, y) in this) '#' else '.' }.joinToString("")
            .also { println(it) }
    }
}

fun <T> Map<Point, T>.mapAsString(default: T, mapping: (T) -> Char) =
    buildString {
        val map = this@mapAsString
        val yRange = keys.minOf(Point::y)..keys.maxOf(Point::y)
        val xRange = (keys.minOf(Point::x)..keys.maxOf(Point::x))
        for (y in yRange) {
            val line = xRange
                .map { x -> map.getOrDefault(Point(x, y), default) }
                .map { mapping(it) }
                .joinToString("")
            appendLine(line)
        }
    }