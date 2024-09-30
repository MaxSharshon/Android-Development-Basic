package factories

import buttons.Button
import buttons.WindowsButton
import checkboxes.Checkbox
import checkboxes.WindowsCheckbox

class WindowsFactory : GUIFactory {
    override fun createButton(): Button {
        return WindowsButton()
    }

    override fun createCheckbox(): Checkbox {
        return WindowsCheckbox()
    }
}