package research

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import org.junit.Assert.assertEquals
import org.junit.Test
import kotlin.system.measureTimeMillis

class ConcurrentTest {

    private val n = 10  // number of jobs to launch
    private val t = 100L // time to wait in each coroutine

    private val theMap = mutableMapOf<Int, Int>()
    private val mutex = Mutex()
    private var counter = 0

    private suspend fun action() {
        val x = counter
        delay(t)
        counter = x + 1
    }

    private fun massiveCoroutines(dispatcher: CoroutineDispatcher, action: suspend () -> Unit) {
        val time = measureTimeMillis {
            runBlocking(dispatcher) {
                repeat(n) {
                    launch {
                        action()
                    }
                }
            }
        }
        println("----------> Completed actions in $n coroutines in $time ms")
    }

    private fun massiveThreads(action: () -> Unit) {
        val jobs = mutableListOf<Thread>()
        val time = measureTimeMillis {
            repeat(n) { index ->
                jobs.add(
                    Thread {
                        action()
                    }.also { it.start() }
                )
            }
            jobs.forEach { it.join() }
        }
        println("----------> Completed actions in $n threads in $time ms")
    }

    @Test
    fun test_coroutine_withLock() {
        counter = 0
        massiveCoroutines(Dispatchers.Default) {
            mutex.withLock {
                action()
            }
        }
        assertEquals(n, counter)
    }

    @Test
    fun test_coroutine_io_withLock() {
        counter = 0
        massiveCoroutines(Dispatchers.IO) {
            mutex.withLock {
                action()
            }
        }
        assertEquals(n, counter)
    }

    @Test
    fun test_thread_withLock() {
        counter = 0
        massiveThreads {
            mutex.withLock {
                action()
            }
        }
        assertEquals(n, counter)
    }

    @Test
    fun test_thread_synchronized() {
        counter = 0
        massiveThreads {
            synchronized(this) {
                action()
            }
        }
        assertEquals(n, counter)
    }

    @Test
    fun test_coroutine_synchronized() {
        counter = 0
        massiveCoroutines(Dispatchers.Default) {
            synchronized(this) {
                action()
            }
        }
        assertEquals(n, counter)
    }

    @Test
    fun test_coroutine_io_synchronized() {
        counter = 0
        massiveCoroutines(Dispatchers.IO) {
            synchronized(this) {
                action()
            }
        }
        assertEquals(n, counter)
    }

}
