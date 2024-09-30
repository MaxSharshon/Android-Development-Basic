package factories

import buttons.Button
import buttons.MacOSButton
import checkboxes.Checkbox
import checkboxes.MacOSCheckbox

class MacOSFactory : GUIFactory {
    override fun createButton(): Button {
        return MacOSButton()
    }

    override fun createCheckbox(): Checkbox {
        return MacOSCheckbox()
    }
}

