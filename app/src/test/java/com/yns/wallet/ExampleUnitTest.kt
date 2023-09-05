package com.yns.wallet

import org.junit.Test

import org.junit.Assert.*
import kotlin.random.Random

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        val indexArray = ArrayList<Int>()
        indexArray.add(Random.nextInt(0, 3))
        indexArray.add(Random.nextInt(0, 3)+ 3)
        indexArray.add(Random.nextInt(0, 3)+ 6)
        indexArray.add(Random.nextInt(0, 3)+ 9)
        val removeIndex = Random.nextInt(0, 3)
        indexArray.removeAt(removeIndex)

        indexArray.forEach { i -> println(i.toString()) }
    }
}