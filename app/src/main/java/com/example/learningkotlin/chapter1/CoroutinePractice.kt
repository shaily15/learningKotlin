package com.example.learningkotlin.chapter1

import kotlinx.coroutines.*
import kotlin.concurrent.thread

class CoroutinePractice {}

fun main()  = runBlocking {
    println("Main program starts in: ${Thread.currentThread().name}")

    // Background thread
    thread {
        println("Background or Worker thread starts in: ${Thread.currentThread().name}")
        Thread.sleep(1000)
        println("Background or Worker thread ends in: ${Thread.currentThread().name}")
    }

    // Implementing Coroutine Global Scope (Not recommended)
    GlobalScope.launch {
        println("Coroutine thread starts in: ${Thread.currentThread().name}")
        mySuspendFunc(1000)
        println("Coroutine thread ends in: ${Thread.currentThread().name}")
    }

    // Local scope takes the thread on which the parent is running
    val job: Job = launch {
        println("Coroutine thread starts in: ${Thread.currentThread().name}")
        mySuspendFunc(1000)
        println("Coroutine thread ends in: ${Thread.currentThread().name}")
    }

    // Thread.sleep(2000)

//    runBlocking {
//        delay(2000)
//    }

//    mySuspendFunc(2000)
    job.cancel(" Canceled ${Thread.currentThread().name}")
    println("Main program ends in: ${Thread.currentThread().name}")
}

suspend fun mySuspendFunc(time: Long) {
    delay(time)
}
