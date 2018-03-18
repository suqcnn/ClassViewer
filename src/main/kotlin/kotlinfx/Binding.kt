@file:JvmName("Binding")

package kotlinfx

import java.util.concurrent.Callable

typealias Binding<T> = javafx.beans.binding.Binding<T>

typealias BooleanBinding = javafx.beans.binding.BooleanBinding
typealias FloatBinding = javafx.beans.binding.FloatBinding
typealias DoubleBinding = javafx.beans.binding.DoubleBinding
typealias IntegerBinding = javafx.beans.binding.IntegerBinding
typealias LongBinding = javafx.beans.binding.LongBinding
typealias NumberBinding = javafx.beans.binding.NumberBinding
typealias StringBinding = javafx.beans.binding.StringBinding
typealias ObjectBinding<T> = javafx.beans.binding.ObjectBinding<T>

typealias ListBinding<E> = javafx.beans.binding.ListBinding<E>
typealias SetBinding<E> = javafx.beans.binding.SetBinding<E>
typealias MapBinding<K, V> = javafx.beans.binding.MapBinding<K, V>


typealias BooleanExpression = javafx.beans.binding.BooleanExpression
typealias FloatExpression = javafx.beans.binding.FloatExpression
typealias DoubleExpression = javafx.beans.binding.DoubleExpression
typealias IntegerExpression = javafx.beans.binding.IntegerExpression
typealias LongExpression = javafx.beans.binding.LongExpression
typealias NumberExpression = javafx.beans.binding.NumberExpression
typealias StringExpression = javafx.beans.binding.StringExpression
typealias ObjectExpression<T> = javafx.beans.binding.ObjectExpression<T>

typealias ListExpression<E> = javafx.beans.binding.ListExpression<E>
typealias SetExpression<E> = javafx.beans.binding.SetExpression<E>
typealias MapExpression<K, V> = javafx.beans.binding.MapExpression<K, V>

object Bindings {
    @JvmStatic
    fun createBooleanBinding(vararg dependencies: Observable, func: () -> Boolean): BooleanBinding =
            javafx.beans.binding.Bindings.createBooleanBinding(Callable { func() }, *dependencies)

    @JvmStatic
    fun createFloatBinding(vararg dependencies: Observable, func: () -> Float): FloatBinding =
            javafx.beans.binding.Bindings.createFloatBinding(Callable { func() }, *dependencies)

    @JvmStatic
    fun createDoubleBinding(vararg dependencies: Observable, func: () -> Double): DoubleBinding =
            javafx.beans.binding.Bindings.createDoubleBinding(Callable { func() }, *dependencies)

    @JvmStatic
    fun createIntegerBinding(vararg dependencies: Observable, func: () -> Int): IntegerBinding =
            javafx.beans.binding.Bindings.createIntegerBinding(Callable { func() }, *dependencies)

    @JvmStatic
    fun createLongBinding(vararg dependencies: Observable, func: () -> Long): LongBinding =
            javafx.beans.binding.Bindings.createLongBinding(Callable { func() }, *dependencies)

    @JvmStatic
    fun createStringBinding(vararg dependencies: Observable, func: () -> String): StringBinding =
            javafx.beans.binding.Bindings.createStringBinding(Callable { func() }, *dependencies)

    @JvmStatic
    fun <T>createObjectBinding(vararg dependencies: Observable, func: () -> T): ObjectBinding<T> =
            javafx.beans.binding.Bindings.createObjectBinding(Callable { func() }, *dependencies)
}

class When(condition: ObservableBooleanValue) {
    class NumberConditionBuilder(private val impl: javafx.beans.binding.When.NumberConditionBuilder) {
        infix fun otherwise(otherwiseValue: ObservableNumberValue): NumberBinding = impl.otherwise(otherwiseValue)

        infix fun otherwise(otherwiseValue: Float): NumberBinding = impl.otherwise(otherwiseValue)

        infix fun otherwise(otherwiseValue: Double): DoubleBinding = impl.otherwise(otherwiseValue)

        infix fun otherwise(otherwiseValue: Int): NumberBinding = impl.otherwise(otherwiseValue)

        infix fun otherwise(otherwiseValue: Long): NumberBinding = impl.otherwise(otherwiseValue)
    }

    class BooleanConditionBuilder(private val impl: javafx.beans.binding.When.BooleanConditionBuilder) {
        infix fun otherwise(otherwiseValue: ObservableBooleanValue): BooleanBinding = impl.otherwise(otherwiseValue)

        infix fun otherwise(otherwiseValue: Boolean): BooleanBinding = impl.otherwise(otherwiseValue)
    }

    class StringConditionBuilder(private val impl: javafx.beans.binding.When.StringConditionBuilder) {
        infix fun otherwise(otherwiseValue: ObservableStringValue): StringBinding = impl.otherwise(otherwiseValue)

        infix fun otherwise(otherwiseValue: String): StringBinding = impl.otherwise(otherwiseValue)
    }

    class ObjectConditionBuilder<T>(private val impl: javafx.beans.binding.When.ObjectConditionBuilder<T>) {
        infix fun otherwise(otherwiseValue: ObservableObjectValue<T>): ObjectBinding<T> = impl.otherwise(otherwiseValue)

        infix fun otherwise(otherwiseValue: T): ObjectBinding<T> = impl.otherwise(otherwiseValue)
    }

    private val impl: javafx.beans.binding.When = javafx.beans.binding.When(condition)

    infix fun then(thenValue: ObservableNumberValue): NumberConditionBuilder = NumberConditionBuilder(impl.then(thenValue))

    infix fun then(thenValue: Float): NumberConditionBuilder = NumberConditionBuilder(impl.then(thenValue))

    infix fun then(thenValue: Double): NumberConditionBuilder = NumberConditionBuilder(impl.then(thenValue))

    infix fun then(thenValue: Int): NumberConditionBuilder = NumberConditionBuilder(impl.then(thenValue))

    infix fun then(thenValue: Long): NumberConditionBuilder = NumberConditionBuilder(impl.then(thenValue))


    infix fun then(thenValue: ObservableBooleanValue): BooleanConditionBuilder = BooleanConditionBuilder(impl.then(thenValue))

    infix fun then(thenValue: Boolean): BooleanConditionBuilder = BooleanConditionBuilder(impl.then(thenValue))


    infix fun then(thenValue: ObservableStringValue): StringConditionBuilder = StringConditionBuilder(impl.then(thenValue))

    infix fun then(thenValue: String): StringConditionBuilder = StringConditionBuilder(impl.then(thenValue))

    infix fun <T> then(thenValue: ObservableObjectValue<T>): ObjectConditionBuilder<T> = ObjectConditionBuilder(impl.then(thenValue))

    infix fun <T> then(thenValue: T): ObjectConditionBuilder<T> = ObjectConditionBuilder(impl.then(thenValue))
}