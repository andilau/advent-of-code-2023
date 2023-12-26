package days

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

fun <E> List<E>.uniquePairs(): List<Pair<E, E>> =
    flatMapIndexed { leftIx: Int, left: E ->
        drop(leftIx + 1).map { right -> left to right }
    }