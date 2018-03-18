@file:JvmName("Effect")

package kotlinfx

typealias Effect = javafx.scene.effect.Effect

//effects

typealias Blend = javafx.scene.effect.Blend

typealias Bloom = javafx.scene.effect.Bloom

typealias BoxBlur = javafx.scene.effect.BoxBlur

typealias ColorAdjust = javafx.scene.effect.ColorAdjust

typealias ColorInput = javafx.scene.effect.ColorInput

typealias DisplacementMap = javafx.scene.effect.DisplacementMap

typealias DropShadow = javafx.scene.effect.DropShadow

typealias GaussianBlur = javafx.scene.effect.GaussianBlur

typealias Glow = javafx.scene.effect.Glow

typealias ImageInput = javafx.scene.effect.ImageInput

typealias InnerShadow = javafx.scene.effect.InnerShadow

typealias Light = javafx.scene.effect.Light

typealias DistantLight = javafx.scene.effect.Light.Distant

typealias PointLight = javafx.scene.effect.Light.Point

typealias SpotLight = javafx.scene.effect.Light.Spot

typealias Lighting = javafx.scene.effect.Lighting

typealias MotionBlur = javafx.scene.effect.MotionBlur

typealias PerspectiveTransform = javafx.scene.effect.PerspectiveTransform

typealias Reflection = javafx.scene.effect.Reflection

typealias SepiaTone = javafx.scene.effect.SepiaTone

typealias Shadow = javafx.scene.effect.Shadow

// enums

typealias BlendMode = javafx.scene.effect.BlendMode

typealias BlurType = javafx.scene.effect.BlurType

// other

typealias FloatMap = javafx.scene.effect.FloatMap

// ...
inline fun blend(
        mode: BlendMode = BlendMode.SRC_OVER,
        initializer: Blend.() -> Unit = {}): Blend {
    return Blend(mode).apply(initializer)
}

inline fun bloom(initializer: Bloom.() -> Unit = {}): Bloom {
    return Bloom().apply(initializer)
}

inline fun boxBlur(initializer: BoxBlur.() -> Unit = {}): BoxBlur {
    return BoxBlur().apply(initializer)
}

inline fun colorAdjust(initializer: ColorAdjust.() -> Unit = {}): ColorAdjust {
    return ColorAdjust().apply(initializer)
}

inline fun colorInput(initializer: ColorInput.() -> Unit = {}): ColorInput {
    return ColorInput().apply(initializer)
}

inline fun displacementMap(initializer: DisplacementMap.() -> Unit = {}): DisplacementMap {
    return DisplacementMap().apply(initializer)
}

inline fun dropShadow(initializer: DropShadow.() -> Unit = {}): DropShadow {
    return DropShadow().apply(initializer)
}

inline fun gaussianBlur(initializer: GaussianBlur.() -> Unit = {}): GaussianBlur {
    return GaussianBlur().apply(initializer)
}

inline fun glow(initializer: Glow.() -> Unit = {}): Glow {
    return Glow().apply(initializer)
}

inline fun imageInput(initializer: ImageInput.() -> Unit = {}): ImageInput {
    return ImageInput().apply(initializer)
}

inline fun innerShadow(initializer: InnerShadow.() -> Unit = {}): InnerShadow {
    return InnerShadow().apply(initializer)
}

inline fun distantLight(initializer: DistantLight.() -> Unit = {}): DistantLight {
    return DistantLight().apply(initializer)
}

inline fun pointLight(initializer: PointLight.() -> Unit = {}): PointLight {
    return PointLight().apply(initializer)
}

inline fun spotLight(initializer: SpotLight.() -> Unit = {}): SpotLight {
    return SpotLight().apply(initializer)
}

inline fun lighting(initializer: Lighting.() -> Unit = {}): Lighting {
    return Lighting().apply(initializer)
}

inline fun motionBlur(initializer: MotionBlur.() -> Unit = {}): MotionBlur {
    return MotionBlur().apply(initializer)
}

inline fun perspectiveTransform(initializer: PerspectiveTransform.() -> Unit = {}): PerspectiveTransform {
    return PerspectiveTransform().apply(initializer)
}

inline fun reflection(initializer: Reflection.() -> Unit = {}): Reflection {
    return Reflection().apply(initializer)
}

inline fun sepiaTone(initializer: SepiaTone.() -> Unit = {}): SepiaTone {
    return SepiaTone().apply(initializer)

}inline fun shadow(initializer: Shadow.() -> Unit = {}): Shadow {
    return Shadow().apply(initializer)
}