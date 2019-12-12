import json
import urllib.request
from pprint import pprint
import alpaca_trade_api as tradeapi
from socket import *


api = tradeapi.REST(key_id='PKT3FS8LGQM868OG1PIX', secret_key='JU7rvzZKZZNtVQwwipXpwFKdkGRF58AfdNYqEL0b')
endmsg = '\r\n.\r\n'


def main():
    comps = ['AAPL', 'IBM', 'INTC', 'MSFT']
    MSG= ''
    for i in range (0 ,4):
        MSG.join(comps[i] + " percent week change: " +(str)(setPC(comps[i])))
    websource = urllib.request.urlopen('http://localhost:8080/users/1')
    data = json.loads(websource.read().decode())
    users = data['email']
    EmailSender(users,MSG)


def EmailSender(user,MSG):
    # Choose a mail server (e.g. Google mail server) and call it mailserver
    mailserver = 'gmail-smtp-in.l.google.com'

    # Create socket called clientSocket and establish a TCP connection with mailserver
    clientSocket = socket(AF_INET, SOCK_STREAM)

    # Port number may change according to the mail server
    clientSocket.connect((mailserver, 25))

    recv = clientSocket.recv(1024).decode()
    print(recv)
    if recv[:3] != '220':
        print('220 reply not received from server.')

    # Send HELO command and print server response.
    heloCommand = 'HELO gmail.com\r\n'
    clientSocket.send(heloCommand.encode())
    recv1 = clientSocket.recv(1024).decode()
    print(recv1)
    if recv1[:3] != '250':
        print('250 reply not received from server.')
        
    # Send MAIL FROM command and print server response.
    mailfrom = 'MAIL FROM: <jtube3840@gmail.com>\r\n'
    clientSocket.send(mailfrom.encode())
    recv2 = clientSocket.recv(1024).decode()
    print(recv2)
    if recv2[:3] != '250':
        print('250 reply not received from server.')


    # Send RCPT TO command and print server response. 
    send = 'RCPT TO: <'+user+'>\r\n'
    rcptto = send
    clientSocket.send(rcptto.encode())
    recv3 = clientSocket.recv(1024).decode()
    print(recv3)
    if recv3[:3] != '250':
        print('250 reply not received from server.')

    # Send DATA command and print server response. 
    data = 'DATA\r\n'
    clientSocket.send(data.encode())
    recv4 = clientSocket.recv(1024).decode()
    print(recv4)
    if recv4[:3] != '354':
        print('354 reply not received from server.')

    # Send message data.
    clientSocket.send('FROM: <jtube3840@gmail.com>\r\n'.encode())
    clientSocket.send('TO: <'+user+'>\r\n'.encode())
    clientSocket.send('SUBJECT: Stock Info!\r\n'.encode())
    clientSocket.send(MSG+'\r\n'.encode())

    # Message ends with a single period.
    clientSocket.send(endmsg.encode())
    recv5 = clientSocket.recv(1024).decode()
    print(recv5)
    if recv5[:3] != '250':
        print('250 reply not received from server.')


def setPC(sym):
    # Get daily price data for AAPL over the last 5 trading days.
    barset = api.get_barset(sym, 'day', limit=5)
    sym_bars = barset[sym]

    # See how much AAPL moved in that timeframe.
    week_open = sym_bars[0].o
    week_close = sym_bars[-1].c
    percent_change = (week_close - week_open) / week_open * 100
    return percent_change



if __name__ == "__main__":
    main()