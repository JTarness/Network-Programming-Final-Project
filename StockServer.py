#!/usr/bin/env
# Server for multithreaded chat application
# Usage: Open terminal/cmd and run "python ChatServer.py"
from socket import AF_INET, socket, SOCK_STREAM
from threading import Thread

# Set up chat server
# Store client sockets
clients = {}

HOST = ''
PORT = input('Enter server port: ')
if not PORT:
    PORT = 1234
else:
    PORT = int(PORT)
    
BUFSIZ = 1024
ADDR = (HOST, PORT)

# Create a TCP server socket
SERVER = socket(AF_INET, SOCK_STREAM)
SERVER.bind(ADDR)

# Handles incomming connections
def accept_incoming_connections():
    while True:
        # Set up a new connection from the chat client
        client, client_address = SERVER.accept()
        # Start client thread to handle the new connection
        Thread(target=handle_client, args=(client,)).start()

# Handles a single client connection, taking client socket as argument
def handle_client(client):
    broadcast(msg.encode("utf8"))
    # Add new pair client socket, name to the clients pool
    clients[client] = name
    print(msg)

    while True:
        # Receive message from client
        msg = client.recv(BUFSIZ)

# Broadcasts a message to all the clients, using prefix for name identification
def broadcast(msg, prefix=""):
    for sock in clients:
        sock.send(prefix.encode("utf8")+msg)
    
def main():
    # Start listening to client connections
    SERVER.listen(5)
    print("Waiting for connection...")
    # Start the accepting connections thread
    ACCEPT_THREAD = Thread(target=accept_incoming_connections)
    ACCEPT_THREAD.start()
    # Wait for the accepting connections thread to stop
    ACCEPT_THREAD.join()
    
    # Close the server socket
    SERVER.close()  

if __name__ == "__main__":
    main()
