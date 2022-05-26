package com.github.arusland.tgbot

import com.pengrad.telegrambot.TelegramBot
import com.pengrad.telegrambot.request.SendMessage

class TelegramService(private val config: BotConfig) {

    fun sendMessage(chatId: String, message: String) {
        val api = TelegramBot(config.botToken)
        val request = SendMessage(chatId, message)
        val sendResponse = api.execute(request)

        if (!sendResponse.isOk) {
            throw Exception(sendResponse.description())
        }
    }
}
