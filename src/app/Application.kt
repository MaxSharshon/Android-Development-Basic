package app

import factories.GUIFactory

class Application(factory:GUIFactory) {
    private val button = factory.createButton()
    private val checkbox = factory.createCheckbox()

    fun paint(){
        button.paint()
        checkbox.paint()
    }
}