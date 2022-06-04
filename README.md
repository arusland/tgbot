# tgbot
Simple CLI for [Telegram Bot API](https://core.telegram.org/bots/api)

## Examples
```bash
tgbot -c your_channel_id -m "Hello from cli"

tgbot -c your_channel_id -f html -m "Simple <b>html</b> content"

tgbot -c your_channel_id -f markdown -m "Simple *markdown* content"
```

## Requirements
* Java 8 and higher
* Maven 3.6 and higher

## Build
```
git clone https://github.com/arusland/tgbot.git
cd tgbot
mvn clean package
```
## Install
* Copy files from `distr` into permanent application place
* Add location of executable `tgbot` to `$PATH`

## Configuration
Create configuration file `~/.tgbot/tgbot.properties` with [Telegram Bot Token](https://core.telegram.org/bots#6-botfather)

```properties
bot.token=your_telegram_bot_token
```

## Usage
```text
Usage: tgbot [-sV] [-np] [-cfg=CONFIG_FILE] [-f=FORMAT] -c=CHAT_ID -m=MESSAGE
Simple CLI for Telegram Bot API
  -c, --chat=CHAT_ID      Chat id/Channel id
      -cfg=CONFIG_FILE    Custom config file
  -f, --format=FORMAT     Message format: html, markdown, markdown2
  -m, --message=MESSAGE   Message to send
      -np, --nopreview    Disable web preview
  -s, --silent            Disable notification
  -V, --version           Print version info
```
