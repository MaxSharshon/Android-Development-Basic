import Demo.configureApplication
import app.Application
import factories.GUIFactory
import factories.MacOSFactory
import factories.WindowsFactory
import java.lang.System.getProperty
import java.util.*

object Demo{
    fun configureApplication(): Application {
        val factory: GUIFactory = if (getProperty("os.name").lowercase(Locale.getDefault()).contains("mac")) {
            MacOSFactory()
        } else {
            WindowsFactory()
        }
        return Application(factory)
    }
}

fun main() {
    val app = configureApplication()
    app.paint()
}