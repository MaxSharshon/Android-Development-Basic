import SupportedOS.WINDOWS_OS
import SupportedOS.MAC_OS
import app.Application
import factories.MacOSFactory
import factories.WindowsFactory
import java.lang.System.getProperty
import java.util.*

object Demo{
    fun configureApplication(): Application {
        val osName = getProperty(SystemProperties.OS_NAME).lowercase(Locale.getDefault())

        val factory = when {
            MAC_OS in osName -> {
                MacOSFactory()
            }

            WINDOWS_OS in osName -> {
                WindowsFactory()
            }

            else -> throw UnsupportedOSException(osName)
        }
        return Application(factory)
    }
}