// API_VERSION: 2.0
// ALLOW_DANGEROUS_LANGUAGE_VERSION_TESTING
// LANGUAGE_VERSION: 2.0
package test

import org.jetbrains.kotlin.plugin.sandbox.MyComposable

fun runComposable(block: @MyComposable () -> Unit) {}

