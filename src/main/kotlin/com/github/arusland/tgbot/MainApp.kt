package com.github.arusland.tgbot

object MainApp {
    @JvmStatic
    fun main(args: Array<String>) {
        // TODO: add picocli
        val service = TelegramService(BotConfig.load("application.properties"))

        service.sendMessage(args[0], args[1])
    }
}