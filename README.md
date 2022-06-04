# tgbot
Simple CLI for [Telegram Bot API](https://core.telegram.org/bots/api)

## Example
```bash
tgbot -c your_channel_id -m "Hello from cli"
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
* Add location of `tgbot` to `$PATH`

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
