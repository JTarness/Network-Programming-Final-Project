#!/usr/bin/env
from socket import AF_INET, socket, SOCK_STREAM
from threading import Thread
import sys

# Set up chat client
HOST = input('Enter server host: ')
if not HOST:
    HOST = "localhost"

PORT = input('Enter server port: ')
if not PORT:
    PORT = 1234
else:
    PORT = int(PORT)

BUFSIZ = 1024
ADDR = (HOST, PORT)
NAME = ''

# Create a TCP client socket
client_socket = socket(AF_INET, SOCK_STREAM)
# Connect to the chat server
client_socket.connect(ADDR)

# Handles receiving of messages
def receive():
    while True:
        try:
            msg = client_socket.recv(BUFSIZ).decode("utf8")
            if msg == "{quit}":
                # Close the client socket after {quit} received by chat server
                client_socket.close()
            else:
                # Print out the chat messages from other chat client 
                if (NAME + ':') not in msg:
                    print(msg)
        except OSError:
            # Possibly client has left the chat.
            break

# Handles sending of messages
def send():  
    while True:
        try:
            # Send message to chat server
            msg = input('')
            client_socket.send(msg.encode("utf8"))
        except OSError:
            # Possibly client has left the chat.
            break

def main():
    # Handshake steps:
    # 1. Client receives greeting message from chat server, asking for a name
    msg = client_socket.recv(BUFSIZ).decode("utf8")
    print(msg)
    # 2. Client enters name and sends it to chat server
    NAME = input('')
    client_socket.send(NAME.encode("utf8"))
    # 3. Server acks with a welcome message
    msg = client_socket.recv(BUFSIZ).decode("utf8")
    print(msg)

    # Start the receiving thread
    receive_thread = Thread(target=receive)
    receive_thread.start()

    # Start the sending thread
    send_thread = Thread(target=send)
    send_thread.start()

    # Wait for child threads to stop
    receive_thread.join()
    send_thread.join()

if __name__ == "__main__":
    main()
