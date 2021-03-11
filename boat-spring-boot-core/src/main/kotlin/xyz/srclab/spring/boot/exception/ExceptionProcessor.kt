package xyz.srclab.spring.boot.exception

import xyz.srclab.common.collect.sorted
import xyz.srclab.common.state.State

/**
 * Processor used to do with [Throwable] by a group of [ExceptionHandler]s.
 *
 * @see ExceptionHandler
 */
open class ExceptionProcessor<C, D, T : State<C, D, T>>(handlers: Iterable<ExceptionHandler<Throwable, C, D, T>>) {

    private val handlerMap: Map<Class<*>, ExceptionHandler<Throwable, C, D, T>>
    private val handlerArray: Array<ExceptionHandler<Throwable, C, D, T>>

    init {
        val handlerMap: MutableMap<Class<*>, ExceptionHandler<Throwable, C, D, T>> = HashMap()
        for (handler in handlers) {
            if (handlerMap.containsKey(handler.supportedExceptionType)) {
                throw IllegalArgumentException(
                    "More than one ExceptionHandler support same Exception: ${handler.supportedExceptionType}"
                )
            }
            handlerMap[handler.supportedExceptionType] = handler
        }
        this.handlerMap = handlerMap.toMap()
        this.handlerArray = handlerMap.values
            .sorted { it1, it2 ->
                val type1 = it1.supportedExceptionType
                val type2 = it2.supportedExceptionType
                if (type1.isAssignableFrom(type2)) -1 else if (type2.isAssignableFrom(type1)) 1 else 0
            }
            .toTypedArray()
    }

    /**
     * If [e] is not supported, thrown.
     */
    fun doException(e: Throwable): T {
        return doExceptionOrNull(e) ?: throw e
    }

    fun doExceptionOrNull(e: Throwable): T? {
        val mapHandler = handlerMap[e.javaClass]
        if (mapHandler !== null) {
            return mapHandler.handle(e)
        }
        for (arrayHandler in handlerArray) {
            if (arrayHandler.supportedExceptionType.isAssignableFrom(e.javaClass)) {
                return arrayHandler.handle(e)
            }
        }
        return null
    }
}