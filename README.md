# DolFin Technology

## Description
This project aims to perform web scraping of stock market data using an Alpaca Trade API to in turn use that data to send email messages to users about stock market tips and insights within a network program. 

An overarching goal is to provide up to date, reliable, and consistent stock information to both experienced and prospective investors. All users of the program should find use in the information provided.

Prior experience with the stock market is not necessary as all users will be provided with information on the percent change of a variety of stocks in each subsequent email update.

## Getting Started
### Registration

1. The client will access the application that is running on a virtual machine through a web browser which will have access to both API’s.
2. The web server directly communicates with the Alpaca Trade API that holds stock information and eventually formats emails to the user
3. An additional Spring API also holds all client profile information
4. In order to initially subscribe to the application, the client uses a browser to connect to a registration/sign-in page
After entering profile information, the user will then begin receiving emails with stock updates and tips
5. In the future, the application will also ask each user at registration if they are interested in being placed in an investing group based on investing preferences 

### Running the Application
1. Open the APO folder and run APO application (this starts the API)
2. Open the registration page file and add credentials
3. Run PythonServer.py through terminal or direcly in an IDE supporting the file
4. Check all folders of email regularly for updated stock insight alerts 

## Features 
1. Directly email users information about stock tips and insights 
2. Users are provided with the best platforms to invest in with the least cost 
3. Create investing groups based on investing preferences 
4. Users provide their email and phone number to subscribe to the market updates
5. Users log into a full website as a place to gain more details from the information sent to them

## Demo Video
https://youtu.be/s91rMoWCjlI
## References
Making API- https://www.youtube.com/watch?v=Of1IcjpGNNg

JSON-https://www.w3schools.com/python/python_json.asp
## Team members

* Andrew Hogan, Client-server and socket programming
* J Tarness, Webscraping and building databases for information
* Duoduo Xu, Front end and GUI developer

