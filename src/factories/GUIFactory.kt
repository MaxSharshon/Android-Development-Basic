package factories

import buttons.Button
import checkboxes.Checkbox

interface GUIFactory {
    fun createButton(): Button
    fun createCheckbox(): Checkbox
}

