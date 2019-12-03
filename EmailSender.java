import java.io.*;
import java.net.*;

public class EmailSender {
	public static void main(String[] args) throws Exception {
		// Establish a TCP connection with the Gmail server.
		Socket socket = new Socket("" + "gmail-smtp-in.l.google.com", 25);

		// Create a BufferedReader to read a line at a time.
		InputStream is = socket.getInputStream();
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);

		// Read greeting from the server.
		String response = br.readLine();
		System.out.println(response);
		if (!response.startsWith("220")) {
			throw new Exception("220 reply not received from server.");
		}

		// Get a reference to the socket's output stream.
		OutputStream os = socket.getOutputStream();

		// Send HELO command and get server response.
		String command = "HELO gmail\r\n";
		System.out.print(command);
		os.write(command.getBytes("US-ASCII"));
		response = br.readLine();
		System.out.println(response);
		
		if (!response.startsWith("250")) {
			throw new Exception("250 reply not received from server.");
		}

		// Send MAIL FROM command.
		String fromCommand = "MAIL FROM: <ahogan1452@gmail.com>\r\n";
		System.out.print(fromCommand);
		os.write(fromCommand.getBytes("US-ASCII"));
		response = br.readLine();
		System.out.println(response);
		
		if(!response.startsWith("250")) {
			throw new Exception("250 reply not received from server.");
		}
		// Send RCPT TO command.
		String rcptCommand = "RCPT TO: <drpham.wit@gmail.com>\r\n";
		System.out.print(rcptCommand);
		os.write(rcptCommand.getBytes("US-ASCII"));
		response = br.readLine();
		System.out.println(response);
		
		if(!response.startsWith("250")) {
			throw new Exception("250 reply not received from server.");
		}
		// Send DATA command.
		String dataCommand = "DATA\r\n";
		System.out.print(dataCommand);
		os.write(dataCommand.getBytes("US-ASCII"));
		response = br.readLine();
		System.out.println(response);
		
		if(!response.startsWith("354")) {
			throw new Exception("354 reply not received from server.");
		}

		// Send message data. 
		// Note: DO NOT forget to include header lines, e.g., From: To: Subject:
		StringBuilder message = new StringBuilder();
		message.append("FROM: <ahogan1452@gmail.com>\r\n");
		message.append("TO: <drpham.wit@gmail.com>\r\n");
		message.append("SUBJECT: HoganLab2\r\n");
		message.append("Prof.Pham,\r\n");
		message.append("Lab 2 EmailSender test\r\n");
		message.append("Thanks, \r\n");
		message.append("Andrew\r\n");
		message.append(".\r\n");
		String dataMessage = message.toString();
		System.out.print(dataMessage);
		os.write(dataMessage.getBytes("US-ASCII"));
		response = br.readLine();
		System.out.println(response);

		if(!response.startsWith("250")) {
			throw new Exception("250 reply not received from server.");
		}

		// Send QUIT command.
		String quitCommand = "QUIT\r\n";
		System.out.print(quitCommand);
		os.write(quitCommand.getBytes("US-ASCII"));
		response = br.readLine();
		System.out.println(response);
		
		if(!response.startsWith("221")) {
			throw new Exception("221 reply not received from server.");
		}
	}

}

