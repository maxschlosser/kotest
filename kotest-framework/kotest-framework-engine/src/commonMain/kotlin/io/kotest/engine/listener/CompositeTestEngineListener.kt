package io.kotest.engine.listener

import io.kotest.core.spec.Spec
import io.kotest.core.test.TestCase
import io.kotest.core.test.TestResult
import io.kotest.engine.interceptors.EngineContext
import kotlin.reflect.KClass

/**
 * A [TestEngineListener] that wraps one or more other test engine listeners,
 * forwarding calls to all listeners.
 */
class CompositeTestEngineListener(private val listeners: List<TestEngineListener>) : TestEngineListener {

   init {
      require(listeners.isNotEmpty())
   }

   override suspend fun engineStarted() {
      listeners.forEach { it.engineStarted() }
   }

   override suspend fun engineInitialized(context: EngineContext) {
      listeners.forEach { it.engineInitialized(context) }
   }

   override suspend fun engineFinished(t: List<Throwable>) {
      listeners.forEach { it.engineFinished(t) }
   }

   override suspend fun specStarted(kclass: KClass<*>) {
      listeners.forEach { it.specStarted(kclass) }
   }

   override suspend fun testStarted(testCase: TestCase) {
      listeners.forEach { it.testStarted(testCase) }
   }

   override suspend fun testIgnored(testCase: TestCase, reason: String?) {
      listeners.forEach { it.testIgnored(testCase, reason) }
   }

   override suspend fun testFinished(testCase: TestCase, result: TestResult) {
      listeners.forEach { it.testFinished(testCase, result) }
   }

   override suspend fun specFinished(kclass: KClass<*>, t: Throwable?) {
      listeners.forEach { it.specFinished(kclass, t) }
   }

   override suspend fun specIgnored(kclass: KClass<*>) {
      listeners.forEach { it.specIgnored(kclass) }
   }

   override suspend fun specEnter(kclass: KClass<*>) {
      listeners.forEach { it.specEnter(kclass) }
   }

   override suspend fun specInactive(kclass: KClass<*>, results: Map<TestCase, TestResult>) {
      listeners.forEach { it.specInactive(kclass, results) }
   }
}
