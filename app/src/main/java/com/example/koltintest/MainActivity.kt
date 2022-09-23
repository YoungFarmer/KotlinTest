package com.example.koltintest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.widget.AppCompatDrawableManager.get
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import java.lang.reflect.Array.set
import kotlin.math.log
import kotlin.properties.Delegates
import kotlin.reflect.KProperty


class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}

fun main() {
//    println("hello kotlin")
//    println(test(10, sum(3, 5)));
//    println(test1(10) { num1: Int, num2: Int -> num1 + num2 })
//
//
//    println(resultByOpt(1, 2) { num1, num2 ->
//        num1 + num2
//    })
//
//    println(resultByOpt(3, 4) { num1, num2 ->
//        num1 - num2
//    })
//
//    println(resultByOpt(5, 6) { num1, num2 ->
//        num1 * num2
//    })
//
//    println(resultByOpt(6, 3) { num1, num2 ->
//        num1 / num2
//    })
//    println("---------------------")
//
//    val items = listOf(1, 2, 3, 4, 5)
//    val joinedToString = items.fold("Elements:", { acc, i -> acc + " " + i })
//    println(joinedToString)
//    val sum: (Int, Int) -> Int = { x: Int, y: Int -> x + y }
//    println(sum(1, 2))

//    corotinueTest()

    println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%")
//    runBlocking {
//        val deferreds: List<Deferred<Int>> = (1..3).map {
//            async {
//                delay(1000L * it)
//                println("Loading $it")
//                it
//            }
//        }
//        val sum =deferreds.awaitAll().sum()
//        println("$sum")
//    }


//    runBlocking<Unit>{
//        val channel = Channel<String>()
//        launch {
//            channel.send("A1")
//            channel.send("A2")
//            log("A done")
//        }
//        launch {
//            channel.send("B1")
//        }
//        launch {
//            repeat(3){
//                val x = channel.receive()
//                log(x)
//            }
//        }
//
//    }


    println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^")
//    runBlocking {
//        launch {
//            println("Thread.currentThread().name  "+Thread.currentThread().name)
//            doWorld()
//        }
//        println("Hello")
//    }


//    testHello1()

    println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&")
    runBlocking {
        launch {
            flowTest()
        }

    }

    val e = Example()
    println(e.p)
    e.p = "NEW"

    println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@")
//    println(lazyValue)
//    println(lazyValue)

//    val user = User()
//    user.name = "first"
//    user.name = "second"

    val myClass = MyClass()
    myClass.oldName = 42
    println(myClass.newName)

    val arr: ArrayList<String>? = null
    println("arr size is" + arr!!.size)


}

class MyClass {
    var newName: Int = 0
    @Deprecated("Use 'newName' instead", ReplaceWith("newName"))
    var oldName: Int by this::newName

    var abc: String = "abc"
        private set


}


class User {
    var name: String by Delegates.observable("<no name>") {
            prop, old, new ->
        println("$old -> $new")
    }

}

val lazyValue: String by lazy {
    println("World")
    "Hello"
}

class Example {
    var p: String by Delegate()
}

class Delegate {
    operator fun getValue(thisRef: Any?, property: KProperty<*>): String {
        return "$thisRef, thank you for delegating '${property.name}' to me!"
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        println("$value has been assigned to '${property.name}' in $thisRef.")
    }
}

suspend fun flowTest() {
    val flowOf = flowOf(1, 2, 3, 4)
    val map = flowOf.onEach {
        println("upFlow --- num in the flow is " + it)
        println("upFlow --- Thread.currentThread().name "+Thread.currentThread().name)
    }.flowOn(Dispatchers.Default)
        .onEach {
            println("downFLow ---num in the flow is " + it)
            println("downFLow ----Thread.currentThread().name "+Thread.currentThread().name)
        }
    map.collect()
}


fun lambdaFun1(postAction: () -> Unit){
    postAction()
}

inline fun lambdaFun(crossinline action: (() -> Unit)){
    Log.i("zyh", "testLambdaFun: 调用前")
    lambdaFun1{action()}
//    action()
    Log.i("zyh", "testLambdaFun: 调用后")
}

fun testHello1(){
    for (i in 1..10) {
        lambdaFun {
            Log.i("zyh", "testLambdaFun: 正在调用")
//            return
        }
    }

}


suspend fun doWorld() {
//    withContext(Dispatchers.Default) {
        println("Thread.currentThread().name  "+Thread.currentThread().name)
        delay(1000L)
        println("World!")
//    }

}
fun log(message: Any?) {
    println("[${Thread.currentThread().name}] $message")
}

fun corotinueTest() = runBlocking { // this: CoroutineScope
    launch { // launch a new coroutine and continue
        delay(1000L) // non-blocking delay for 1 second (default time unit is ms)
        println("World!") // print after delay
        println("Thread.currentThread().name  " + Thread.currentThread().name)
    }
    println("Hello") // main coroutine continues while a previous one is delayed
    println("Thread.currentThread().name   " + Thread.currentThread().name)
}

fun <T, R> Collection<T>.fold(
    initial: R,
    combine: (acc: R, nextElement: T) -> R
): R {
    var accumulator: R = initial
    for (element: T in this) {
        accumulator = combine(accumulator, element)
    }
    return accumulator
}

fun test(a: Int, b: Int): Int {
    return a+b;
}

fun sum(num1: Int, num2:Int): Int {
    return num1+num2;
}

fun test1(a: Int, b: (num1: Int, num2: Int) -> Int): Int {
    return a + b.invoke(3, 5);
}

private fun resultByOpt(num1: Int, num2: Int, result: (Int, Int) -> Int): Int{
    return result(num1, num2);
}


open class Shape {
    open fun draw(){}
    fun fill(){}

}

open class Circle() : Shape() {
    final override fun draw() {

    }
}

class Rectangle(val width: Int, val height: Int) {
    val area: Int = this.width * this.height
}