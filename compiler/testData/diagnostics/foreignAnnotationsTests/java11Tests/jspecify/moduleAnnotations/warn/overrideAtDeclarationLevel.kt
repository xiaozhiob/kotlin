// FIR_IDENTICAL
// JSPECIFY_STATE: warn
// ALLOW_KOTLIN_PACKAGE

// FILE: sandbox/module-info.java
import org.jspecify.annotations.NullMarked;

@NullMarked
module sandbox {
    requires java9_annotations;
    exports test;
}

// FILE: sandbox/test/Test.java
package test;

import org.jspecify.annotations.Nullable;

public class Test {
    public void foo(@Nullable Integer x) {}
}

// FILE: main.kt
import test.Test

fun main(x: Test) {
    x.foo(null)
}
