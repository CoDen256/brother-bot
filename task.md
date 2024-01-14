# Telegram Bot 
@brobrobotbotbot

# Tasks
![](docs\task-reduced.drawio.png)


## Task 1

1. Run the bot, send /start message in the chat, send sticker
2. Change /start message response
3. Implement /help message, that sends a list with all commands (/start /help)
4. Change response message on a sticker
5. Implement response to any image. Let it send some funny response
6. Save locally each image that was sent, check locally that it's working
7. Implement /image command that sends "THIS WILL BE AN IMAGE"
8. Load any local image and send it on /image command (load a previously saved image)
9. Get printers via postman (GET /printers)
10. Create Test assertTrue(true)
11. Create Test with http client executing (GET /printers)
12. Extend the test with http client, expecting two printers !!! JSON PArser?
13. Add an implementation of printer service api that does nothing
14. Add an implementation of printer service api that get printers
15. Get particular printer via postman (GET /printers/{printer})
16. Add an endpoint on a printer service api
17. SSH to rpi-beta
18. Upload a file to rpi-beta
19. Download a file from rpi-beta
20. Send Doc to print via postman, using PDF Printer
21. Send Doc to print via http client using PDF printer, test
22. Send doc endpoint in service
23. Save the doc locally on document and send it via postman
24. Save the doc locally and send it via http client
25. Create new bot, update token




## Task 2





The full version
![](docs\task-whole.drawio.png)







# Printer API Service
REST API (host: http://rpi-beta.local:8080)<br>
List of all available printers

    GET /printers

Get printer info by name

    GET /printers/{printer}

Get jobs of the printer

    GET /printers/{printer}/jobs

Post a job with document in the body

    POST /printers/{printer}/jobs?copies=1

Get the job info by name

    GET /printers/{printer}/jobs/{job}

# Telegram Bot library
https://github.com/rubenlagus/TelegramBots/wiki/Getting-Started


# Brother BOT Capabilities

1. **/start** - Sends start message
2. **/help**  - Sends a help message with all commands
3. **Any Image** - Sends some funny response
4. **/printers** - Sends the list of available printers
5. **Any DOCUMENT** - Sends the document for printing and replies with a message
6. **/printers {printer}** - Sends info about specific printer
7. **Any Document + {number}** - Prints n copies of the

# The Big Picture
![](docs\task-flow.drawio.png)