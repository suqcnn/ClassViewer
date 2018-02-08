@file:JvmName("AccessFlags")
@file:Suppress("NOTHING_TO_INLINE")

package org.glavo.viewer.classfile.jvm

const val ACC_PUBLIC        = 0x0001
const val ACC_PRIVATE       = 0x0002
const val ACC_PROTECTED     = 0x0004
const val ACC_STATIC        = 0x0008
const val ACC_FINAL         = 0x0010
const val ACC_SUPER         = 0x0020
const val ACC_TRANSITIVE    = 0x0020
const val ACC_SYNCHRONIZED  = 0x0020
const val ACC_VOLATILE      = 0x0040
const val ACC_BRIDGE        = 0x0040
const val ACC_STATIC_PHASE  = 0x0040
const val ACC_TRANSIENT     = 0x0080
const val ACC_VARARGS       = 0x0080
const val ACC_NATIVE        = 0x0100
const val ACC_INTERFACE     = 0x0200
const val ACC_ABSTRACT      = 0x0400
const val ACC_STRICT        = 0x0800
const val ACC_SYNTHETIC     = 0x1000
const val ACC_ANNOTATION    = 0x2000
const val ACC_ENUM          = 0x4000
const val ACC_MODULE        = 0x8000
const val ACC_MANDATED      = 0x8000

infix inline fun Int.isAcc(acc: Int): Boolean {
    return (this and acc) != 0
}

val Int.isPublic: Boolean
    inline get() = this isAcc ACC_PUBLIC

val Int.isPrivate: Boolean
    inline get() = this isAcc ACC_PRIVATE

val Int.isProtected: Boolean
    inline get() = this isAcc ACC_PROTECTED

val Int.isStatic: Boolean
    inline get() = this isAcc ACC_STATIC

val Int.isFinal: Boolean
    inline get() = this isAcc ACC_FINAL

val Int.isSuper: Boolean
    inline get() = this isAcc ACC_SUPER

val Int.isTransitive: Boolean
    get() = this isAcc ACC_TRANSITIVE

val Int.isSynchronized: Boolean
    get() = this isAcc ACC_SYNCHRONIZED

val Int.isVolatile: Boolean
    get() = this isAcc ACC_VOLATILE

val Int.isBridge: Boolean
    get() = this isAcc ACC_BRIDGE

val Int.isStaticPhase: Boolean
    get() = this isAcc ACC_STATIC_PHASE

val Int.isTransient: Boolean
    get() = this isAcc ACC_TRANSIENT

val Int.isVarargs: Boolean
    get() = this isAcc ACC_VARARGS

val Int.isNative: Boolean
    get() = this isAcc ACC_NATIVE

val Int.isInterface: Boolean
    get() = this isAcc ACC_INTERFACE

val Int.isAbstract: Boolean
    get() = this isAcc ACC_ABSTRACT

val Int.isStrict: Boolean
    get() = this isAcc ACC_STRICT

val Int.isSynthetic: Boolean
    get() = this isAcc ACC_SYNTHETIC

val Int.isAnnotation: Boolean
    get() = this isAcc ACC_ANNOTATION

val Int.isEnum: Boolean
    get() = this isAcc ACC_ENUM

val Int.isModule: Boolean
    get() = this isAcc ACC_MODULE

val Int.isMandated: Boolean
    get() = this isAcc ACC_MANDATED