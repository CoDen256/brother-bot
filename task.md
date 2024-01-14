# Part I - Telegram Bot API + IO
Simplified architecture of some components:
![](./docs/task-min.drawio.png)

1. Brother Bot Process
   - defines logic, how the telegram bot would react to the given messages/commands
   - is responsible for sending PDF files for printing
   - polls periodically the Telegram Bot API for the updates/new messages and process them according to the defined logic
2. Telegram Bot API
   - returns the updates/received messages by the bot on request from the Brother Bot Process
3. LAN Machines/Servers:
   - rpi-beta: raspberry pi, hosting the brother telegram bot (rpi-alpha.local)
   - local dev PC, hosting the telegram bot during development


## Tasks
### Task 1
Clone repo (always add commits for each task)

### Task 2
1. Run the bot locally, edit the app.config. Ask me for the actual token value.
2. Open bot chat @brobrobotbotbot
3. send /start message in the chat
4. send a sticker
5. Observe the responses

### Task 3
Change /start message response to anything else -> run again -> verify

### Task 4
1. Add "/help" command to the bot, that sends a list with all commands (/start /help etc.)
2. Verify the command in the bot chat

### Task 5
1. Change the response message, when handling a sticker message to "Based".
2. Send response as two separate messages ("Based", "Good shit") or whatever you want as a reponse to a sticker message

### Task 6
1. Implement a handler to react on an image message. 
2. Send some funny response when user sends a picture 

### Task 7
1. Save locally each image that was sent
2. Check that it's working, by verifying the image 

### Task 8
Implement /image command that sends a message "THIS WILL BE AN IMAGE" (siehe task 2)

### Task 9
Change behavior /image command:
Load any local image and send as a response (e.g. load a previously saved image)


# Part II - Sending HTTP requests

Extended architecture of the main components:
![](./docs/task-reduced.drawio.png)

1. Printers
   - Brother MFCL2710DW (the actual printer)
   - PDF Printer (the virtual printer, that prints to the PDF file stored on the filesystem of rpi-beta.local)
2. Printer API Service
   - acts as a facade, simplified API for printing, without accessing printers directly
   - processes simplified incoming HTTP/REST requests and forwards them to the desired printers
3. Brother Bot Process
   - defines logic, how the telegram bot would react to the given messages/commands
   - primarily forwards the received PDF docs to the Printer API Service
   - polls periodically the Telegram Bot API for the updates/new messages and process them according to the defined logic
4. Telegram Bot API
   - returns the updates/received messages by the bot on request from the Brother Bot Process
5. LAN Machines/Servers:
   - Brother Printer: the actual printer (192.168.0.194)
   - rpi-alpha: raspberry pi, hosting printer api service + PDF Printer (rpi-beta.local)
   - rpi-beta: raspberry pi, hosting the brother telegram bot (rpi-alpha.local)
   - local dev PC, hosting the telegram bot during development

## Tasks
### Task 1
Get JSON list of printers via Postman (GET /printers) (siehe below Printer API Service)

### Task 2
Create a junit test PrinterServiceTest.mathWorks(), that checks whether the 2+2 is 4 and fails if its not,
using `assertEquals`

### Task 3
1. Remove previous `assertEquals` check
2. Create an http client
3. Let the http client execute GET www.google.com/
4. Let the http client execute the GET /printers endpoint of the Printer API Service instead

### Task 4
Extend the test with http client, expecting two printers !!! JSON PArser?

### Task 5
Add an implementation of printer service api that does nothing

### Task 6
Add an implementation of printer service api that get printers

### Task 7
Get particular printer via postman (GET /printers/{printer})

### Task 8
Add an endpoint on a printer service api



# Part III - Printing

The full version of the architecture
![](./docs/task-whole.drawio.png)
New components:

1. CUPS
    - used for managing printers, locations, groups, discovering, settings, adding and removing printers
    - Two services
        - The backend - the one, that is used by Printer API Service
        - The frontend - the web UI, can be accessed directly via port :631
2. SSH/SFTP Server
    - the SSH/SFTP server running on rpi-beta.local, allowing uploading files and running processes

## Tasks
1. SSH to rpi-beta
2. Upload a file to rpi-beta
3. Download a file from rpi-beta
4. Send Doc to print via postman, using PDF Printer
5. Send Doc to print via http client using PDF printer, test
6. Send doc endpoint in service
7. Save the doc locally on document and send it via postman
8. Save the doc locally and send it via http client
9. Create new bot, update token





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

# Telegram Bot
@brobrobotbotbot


# Brother BOT Capabilities

1. **/start** - Sends start message
2. **/help**  - Sends a help message with all commands
3. **Any Image** - Sends some funny response
4. **/printers** - Sends the list of available printers
5. **Any DOCUMENT** - Sends the document for printing and replies with a message
6. **/printers {printer}** - Sends info about specific printer
7. **Any Document + {number}** - Prints n copies of the

# The Big Picture
Whole architecture of all the main components:
![](./docs/task-flow.drawio.png)