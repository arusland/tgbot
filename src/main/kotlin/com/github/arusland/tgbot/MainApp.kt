package com.github.arusland.tgbot

import com.pengrad.telegrambot.model.request.ParseMode
import picocli.CommandLine
import picocli.CommandLine.Command
import picocli.CommandLine.Option
import java.io.File
import java.util.concurrent.Callable
import kotlin.system.exitProcess

/**
 * Telegram bot CLI
 */
@Command(
    name = "tgbot",
    description = ["Simple CLI for Telegram Bot API"],
    versionProvider = ManifestVersionProvider::class
)
class MainApp : Callable<Int> {
    @Option(
        names = ["-c", "--chat"],
        paramLabel = "CHAT_ID",
        description = ["Chat id/Channel id"],
        required = true
    )
    private var chatId: String = ""

    @Option(
        names = ["-m", "--message"],
        paramLabel = "MESSAGE",
        description = ["Message to send"],
        required = true
    )
    private var message: String = ""

    @Option(
        names = ["-f", "--format"],
        paramLabel = "FORMAT",
        description = ["Message format: html, markdown, markdown2"]
    )
    private var format: String = ""

    @Option(
        names = ["-s", "--silent"],
        paramLabel = "true|false",
        description = ["Disable notification"]
    )
    private var disableNotification: Boolean = false

    @Option(
        names = ["-np", "--nopreview"],
        paramLabel = "true|false",
        description = ["Disable web preview"]
    )
    private var disablePreview: Boolean = false

    @Option(
        names = ["-cfg"],
        paramLabel = "CONFIG_FILE",
        description = ["Custom config file"]
    )
    private var config: String = ""

    @Option(
        names = ["-V", "--version"],
        versionHelp = true,
        description = ["Print version info"]
    )
    private var versionRequested: Boolean = false

    private val service: Lazy<TelegramService> = lazy { createTelegramService() }

    override fun call(): Int {
        service.value.sendMessage(
            chatId, message, mode = parseMode(format),
            disableNotification = disableNotification,
            disablePreview = disablePreview
        )
        return 0
    }

    private fun createTelegramService(): TelegramService {
        val configFile = if (config.isNotBlank()) File(config) else getDefaultConfig()

        if (!configFile.exists()) {
            println("Minimal config file content:\n\tbot.token=place_talegram_bot_token")
            throw IllegalArgumentException("Config file not found: $configFile")
        }

        return TelegramService(BotConfig.load(configFile))
    }

    private fun getDefaultConfig(): File {
        val configFolder = File(File(System.getProperty("user.home")), ".tgbot")

        if (!configFolder.exists()) {
            configFolder.mkdirs()
        }
        return File(configFolder, "tgbot.properties")
    }

    companion object {
        private fun parseMode(format: String): ParseMode? =
            when (format) {
                "html" -> ParseMode.HTML
                "markdown" -> ParseMode.Markdown
                "markdown2" -> ParseMode.MarkdownV2
                "" -> null
                else -> throw IllegalArgumentException("Unsupported format: $format")
            }

        @JvmStatic
        fun main(args: Array<String>) {
            val exitCode: Int = CommandLine(MainApp()).execute(*args)
            exitProcess(exitCode)
        }
    }
}
