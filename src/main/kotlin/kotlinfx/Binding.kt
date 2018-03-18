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
    fun <T> createObjectBinding(vararg dependencies: Observable, func: () -> T): ObjectBinding<T> =
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

operator fun NumberExpression.unaryMinus(): NumberBinding = this.negate()

operator fun NumberExpression.plus(other: ObservableNumberValue): NumberBinding = this.add(other)

operator fun NumberExpression.plus(other: Double): NumberBinding = this.add(other)

operator fun NumberExpression.plus(other: Float): NumberBinding = this.add(other)

operator fun NumberExpression.plus(other: Long): NumberBinding = this.add(other)

operator fun NumberExpression.plus(other: Int): NumberBinding = this.add(other)

operator fun NumberExpression.minus(other: ObservableNumberValue): NumberBinding = this.subtract(other)

operator fun NumberExpression.minus(other: Double): NumberBinding = this.subtract(other)

operator fun NumberExpression.minus(other: Float): NumberBinding = this.subtract(other)

operator fun NumberExpression.minus(other: Long): NumberBinding = this.subtract(other)

operator fun NumberExpression.minus(other: Int): NumberBinding = this.subtract(other)

operator fun NumberExpression.times(other: ObservableNumberValue): NumberBinding = this.multiply(other)

operator fun NumberExpression.times(other: Double): NumberBinding = this.multiply(other)

operator fun NumberExpression.times(other: Float): NumberBinding = this.multiply(other)

operator fun NumberExpression.times(other: Long): NumberBinding = this.multiply(other)

operator fun NumberExpression.times(other: Int): NumberBinding = this.multiply(other)

operator fun NumberExpression.div(other: ObservableNumberValue): NumberBinding = this.divide(other)

operator fun NumberExpression.div(other: Double): NumberBinding = this.divide(other)

operator fun NumberExpression.div(other: Float): NumberBinding = this.divide(other)

operator fun NumberExpression.div(other: Long): NumberBinding = this.divide(other)

operator fun NumberExpression.div(other: Int): NumberBinding = this.divide(other)


operator fun FloatExpression.unaryMinus(): FloatBinding = this.negate()

operator fun FloatExpression.plus(other: Double): DoubleBinding = this.add(other)

operator fun FloatExpression.plus(other: Float): FloatBinding = this.add(other)

operator fun FloatExpression.plus(other: Long): FloatBinding = this.add(other)

operator fun FloatExpression.plus(other: Int): FloatBinding = this.add(other)

operator fun FloatExpression.minus(other: Double): DoubleBinding = this.subtract(other)

operator fun FloatExpression.minus(other: Float): FloatBinding = this.subtract(other)

operator fun FloatExpression.minus(other: Long): FloatBinding = this.subtract(other)

operator fun FloatExpression.minus(other: Int): FloatBinding = this.subtract(other)

operator fun FloatExpression.times(other: Double): DoubleBinding = this.multiply(other)

operator fun FloatExpression.times(other: Float): FloatBinding = this.multiply(other)

operator fun FloatExpression.times(other: Long): FloatBinding = this.multiply(other)

operator fun FloatExpression.times(other: Int): FloatBinding = this.multiply(other)

operator fun FloatExpression.div(other: Double): DoubleBinding = this.divide(other)

operator fun FloatExpression.div(other: Float): FloatBinding = this.divide(other)

operator fun FloatExpression.div(other: Long): FloatBinding = this.divide(other)

operator fun FloatExpression.div(other: Int): FloatBinding = this.divide(other)


operator fun DoubleExpression.unaryMinus(): DoubleBinding = this.negate()

operator fun DoubleExpression.plus(other: ObservableNumberValue): DoubleBinding = this.add(other)

operator fun DoubleExpression.plus(other: Double): DoubleBinding = this.add(other)

operator fun DoubleExpression.plus(other: Float): DoubleBinding = this.add(other)

operator fun DoubleExpression.plus(other: Long): DoubleBinding = this.add(other)

operator fun DoubleExpression.plus(other: Int): DoubleBinding = this.add(other)

operator fun DoubleExpression.minus(other: ObservableNumberValue): DoubleBinding = this.subtract(other)

operator fun DoubleExpression.minus(other: Double): DoubleBinding = this.subtract(other)

operator fun DoubleExpression.minus(other: Float): DoubleBinding = this.subtract(other)

operator fun DoubleExpression.minus(other: Long): DoubleBinding = this.subtract(other)

operator fun DoubleExpression.minus(other: Int): DoubleBinding = this.subtract(other)

operator fun DoubleExpression.times(other: ObservableNumberValue): DoubleBinding = this.multiply(other)

operator fun DoubleExpression.times(other: Double): DoubleBinding = this.multiply(other)

operator fun DoubleExpression.times(other: Float): DoubleBinding = this.multiply(other)

operator fun DoubleExpression.times(other: Long): DoubleBinding = this.multiply(other)

operator fun DoubleExpression.times(other: Int): DoubleBinding = this.multiply(other)

operator fun DoubleExpression.div(other: ObservableNumberValue): DoubleBinding = this.divide(other)

operator fun DoubleExpression.div(other: Double): DoubleBinding = this.divide(other)

operator fun DoubleExpression.div(other: Float): DoubleBinding = this.divide(other)

operator fun DoubleExpression.div(other: Long): DoubleBinding = this.divide(other)

operator fun DoubleExpression.div(other: Int): DoubleBinding = this.divide(other)


operator fun IntegerExpression.unaryMinus(): IntegerBinding = this.negate()

operator fun IntegerExpression.plus(other: Double): DoubleBinding = this.add(other)

operator fun IntegerExpression.plus(other: Float): FloatBinding = this.add(other)

operator fun IntegerExpression.plus(other: Long): LongBinding = this.add(other)

operator fun IntegerExpression.plus(other: Int): IntegerBinding = this.add(other)

operator fun IntegerExpression.minus(other: Double): DoubleBinding = this.subtract(other)

operator fun IntegerExpression.minus(other: Float): FloatBinding = this.subtract(other)

operator fun IntegerExpression.minus(other: Long): LongBinding = this.subtract(other)

operator fun IntegerExpression.minus(other: Int): IntegerBinding = this.subtract(other)

operator fun IntegerExpression.times(other: Double): DoubleBinding = this.multiply(other)

operator fun IntegerExpression.times(other: Float): FloatBinding = this.multiply(other)

operator fun IntegerExpression.times(other: Long): LongBinding = this.multiply(other)

operator fun IntegerExpression.times(other: Int): IntegerBinding = this.multiply(other)

operator fun IntegerExpression.div(other: Double): DoubleBinding = this.divide(other)

operator fun IntegerExpression.div(other: Float): FloatBinding = this.divide(other)

operator fun IntegerExpression.div(other: Long): LongBinding = this.divide(other)

operator fun IntegerExpression.div(other: Int): IntegerBinding = this.divide(other)


operator fun LongExpression.unaryMinus(): LongBinding = this.negate()

operator fun LongExpression.plus(other: Double): DoubleBinding = this.add(other)

operator fun LongExpression.plus(other: Float): FloatBinding = this.add(other)

operator fun LongExpression.plus(other: Long): LongBinding = this.add(other)

operator fun LongExpression.plus(other: Int): LongBinding = this.add(other)

operator fun LongExpression.minus(other: Double): DoubleBinding = this.subtract(other)

operator fun LongExpression.minus(other: Float): FloatBinding = this.subtract(other)

operator fun LongExpression.minus(other: Long): LongBinding = this.subtract(other)

operator fun LongExpression.minus(other: Int): LongBinding = this.subtract(other)

operator fun LongExpression.times(other: Double): DoubleBinding = this.multiply(other)

operator fun LongExpression.times(other: Float): FloatBinding = this.multiply(other)

operator fun LongExpression.times(other: Long): LongBinding = this.multiply(other)

operator fun LongExpression.times(other: Int): LongBinding = this.multiply(other)

operator fun LongExpression.div(other: Double): DoubleBinding = this.divide(other)

operator fun LongExpression.div(other: Float): FloatBinding = this.divide(other)

operator fun LongExpression.div(other: Long): LongBinding = this.divide(other)

operator fun LongExpression.div(other: Int): LongBinding = this.divide(other)