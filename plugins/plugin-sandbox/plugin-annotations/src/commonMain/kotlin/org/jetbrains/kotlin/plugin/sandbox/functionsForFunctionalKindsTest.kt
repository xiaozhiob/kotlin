/*
 * Copyright 2010-2023 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.plugin.sandbox

class Box<T>(val value: T)

fun produceComposableFunction(): @MyComposable () -> Unit = null!!
fun consumeComposableFunction(@Suppress("UNUSED_PARAMETER") block: @MyComposable () -> Unit) {}

fun produceBoxedComposableFunction(): Box<@MyComposable () -> Unit> = Box(produceComposableFunction())
