package com.github.arusland.tgbot

import org.slf4j.LoggerFactory
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
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

    private fun setLongList(propName: String, list: List<Long>) {
        props.setProperty(propName, list.joinToString(","))
    }

    private fun getLongList(propName: String): List<Long> {
        return getProperty(propName, "")
            .split(",".toRegex())
            .filter { it.isNotBlank() }
            .map { it.trim().toLong() }
            .toList()
    }

    fun save(fileName: String) {
        FileOutputStream(fileName).use { output -> props.store(output, "Wikipeda Pod") }
    }

    companion object {
        fun load(fileName: String, throwOnError: Boolean = true): BotConfig {
            val props = Properties()

            try {
                val file = File(fileName).canonicalFile
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
