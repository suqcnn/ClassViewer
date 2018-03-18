package kotlinfx

object App {
    private lateinit var app: Helper

    lateinit var stage: Stage

    var onInit: () -> Unit = {}
    var onStop: () -> Unit = {}
    var stageInitializer: Stage.() -> Unit = {}

    var autoShow: Boolean = true

    class Helper : Application() {
        var onStop: () -> Unit = {}

        override fun init() {
            app = this
            onInit()
        }

        override fun start(primaryStage: Stage) {
            App.stage = primaryStage
            primaryStage.stageInitializer()
            if (autoShow) {
                stage.show()
            }
        }

        override fun stop() {
            onStop()
        }
    }

    fun run(
            args: Array<String>,
            autoShow: Boolean = true,
            onInit: () -> Unit = {},
            onStop: () -> Unit = {},
            stageInitializer: Stage.() -> Unit) {
        this.onInit = onInit
        this.onStop = onStop
        this.stageInitializer = stageInitializer
        this.autoShow = autoShow

        launch<Helper>(args)
    }

}