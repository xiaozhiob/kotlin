// FIR_IDENTICAL

fun result(): Result<Int> = TODO()
val resultP: Result<Int> = result()

fun f(r1: Result<Int>?) {
    r1 ?: 0
}
