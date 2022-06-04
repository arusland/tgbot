package com.github.arusland.tgbot

import com.pengrad.telegrambot.TelegramBot
import com.pengrad.telegrambot.model.request.ParseMode
import com.pengrad.telegrambot.request.SendMessage

class TelegramService(private val config: BotConfig) {
    private val api = TelegramBot(config.botToken)

    fun sendMessage(
        chatId: String,
        message: String,
        mode: ParseMode? = null,
        disableNotification: Boolean = false,
        disablePreview: Boolean = false
    ) {
        if (message.isBlank()) throw IllegalArgumentException("Message cannot be empty")

        val request = SendMessage(chatId, message)
        if (mode != null) {
            request.parseMode(mode)
        }

        request.disableNotification(disableNotification)
        request.disableWebPagePreview(disablePreview)
        val sendResponse = api.execute(request)

        if (!sendResponse.isOk) {
            throw Exception(sendResponse.description())
        }
    }
}
