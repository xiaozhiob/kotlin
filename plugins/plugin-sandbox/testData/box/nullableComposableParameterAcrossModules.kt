// ISSUE: KT-64994

// MODULE: a
// FILE: a.kt
import org.jetbrains.kotlin.plugin.sandbox.MyComposable

fun RMenuItem(
    onClick: (() -> Unit)? = null,
    trailing: (@MyComposable () -> Unit)? = null,
) {}

// MODULE: b(a)
// FILE: b.kt
import org.jetbrains.kotlin.plugin.sandbox.MyComposable

fun bar(onClick: (() -> Unit)? = null) {
    RMenuItem(
        onClick = onClick,
        trailing = onClick?.let {
            { }
        }
    )
}

fun box() = "OK"
