package com.github.arusland.tgbot

import org.slf4j.LoggerFactory
import java.io.File
import java.io.FileInputStream
import java.util.*

/**
 * Simple bot configuration
 */
class BotConfig private constructor(prop: Properties) {
    private val log = LoggerFactory.getLogger(BotConfig::class.java)
    private val props: Properties = prop

    val botToken: String
        get() = getProperty("bot.token")

    private fun getProperty(key: String): String =
        props.getProperty(key) ?: throw IllegalStateException("Configuration not found for key: $key")

    private fun getProperty(key: String, defValue: String): String =
        props.getProperty(key) ?: defValue

    companion object {
        fun load(fileName: File, throwOnError: Boolean = true): BotConfig {
            val props = Properties()

            try {
                val file = fileName.canonicalFile
                FileInputStream(file).use { input -> props.load(input) }
            } catch (e: Exception) {
                if (throwOnError) {
                    throw RuntimeException(e)
                }
            }

            return BotConfig(props)
        }
    }
}
