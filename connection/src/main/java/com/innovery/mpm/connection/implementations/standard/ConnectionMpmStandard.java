package com.innovery.mpm.connection.implementations.standard;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketTimeoutException;

import org.apache.log4j.Logger;

import com.innovery.mpm.connection.implementations.util.ErrorMessages;
import com.innovery.mpm.connection.interfaces.standard.ConnectionManagmentStandardInterface;
import com.innovery.mpm.connection.interfaces.standard.ConnectionMpmStandardInterface;
import com.innovery.mpm.connection.interfaces.standard.session.SessionStandardInterface;


/**
 * 
 * @author Valerio Boschi, Daniela R. Auricchio
 *
 */
public class ConnectionMpmStandard implements ConnectionMpmStandardInterface{
	private final String LOCAL_NOT_LOGGED_ERROR = "RESP:3007;";
	private final String LOCAL_MSISDN_NOT_FOUND_ERROR_CH1 = ":17051;";
	private final String LOCAL_MSISDN_NOT_FOUND_ERROR_CH2 = ":17001;";
	private final String LOCAL_MSISDN_NOT_FOUND_BSO = ":50030;";
	
	private Socket _clientSocketStandard;
	private BufferedReader _inputStream;
	private PrintWriter _outputStream;
	private static final boolean AUTOFLUSH = true;
	private final Logger log = ConnectionManagmentStandard._LOG;
	
	private ConnectionManagmentStandardInterface connection_managment;
	private SessionStandardInterface session;
	
	private ErrorMessages message;
	
	public ConnectionMpmStandard(ConnectionManagmentStandard connection_managment){
		this.connection_managment = connection_managment;
		this.session = this.connection_managment.getSession();
		this.message = new ErrorMessages();
	}

	public void connectStandard(String ip_address, int port) {
		@SuppressWarnings("unused")
		String message = null;
		try {
			_clientSocketStandard = new Socket(ip_address, port);
			log.info("CREATING NEW CONNECTION. SOCKET ID: " + _clientSocketStandard.toString());
			new ConnectionResponse("Connected, handshaking", ConnectionResponse.TYPE_CONNECTED_START_HANDSHAKE);
			_outputStream = new PrintWriter(_clientSocketStandard.getOutputStream(), AUTOFLUSH);
			_outputStream.flush();
			_inputStream = new BufferedReader(new InputStreamReader(_clientSocketStandard.getInputStream()));

			StringBuffer handshake = new StringBuffer();
			String line = "";
			char c;
			while (!handshake.toString().toLowerCase().endsWith("enter command:")) {
				c = (char) _inputStream.read();
				handshake.append(c);
			}
			line = handshake.toString();
			new ConnectionResponse(line, ConnectionResponse.TYPE_ECHO);
			new ConnectionResponse("CONNECTED, HANDSHAKE COMPLETE.",	ConnectionResponse.TYPE_HANDSHAKE_COMPLETE);

		} catch (Exception e) {
			String problem = e.getMessage();
			if (problem == null) {
				problem = "UNKNOWN ERROR IN COMMUNICATION TO THE SERVER.";
			}
			new ConnectionResponse(problem, ConnectionResponse.TYPE_ERROR);
			log.error(problem);
		}
	}

	public void disconnectStandard() throws Exception {		
		log.info("DISCONNECTIONG SOCKET.");
		if(_inputStream!=null){
			_inputStream.close();
		}
		if(_outputStream!=null){
			_outputStream.close();
		}
		if(_clientSocketStandard!=null){
			_clientSocketStandard.close();
		}
		log.info("DISCONNECTED.");
		new ConnectionResponse("DISCONNECTED.",	ConnectionResponse.TYPE_DISCONNECTED);

	}

	public boolean isConnectedStandard() {
		if (_clientSocketStandard == null) {
			log.info("NOT CONNECTED");
			return false;
		}
		log.info("CONNECTED");
		return (_clientSocketStandard.isConnected() && !_clientSocketStandard.isClosed() && _clientSocketStandard.isBound());
	}

	private void readCompleteBuffer() throws IOException {
		StringBuffer line;
		int token = 0;
		char c;
		line = new StringBuffer();
		while (!line.toString().toLowerCase().equals("enter command:")) {
			token = _inputStream.read();
			c = (char) token;
			if (token == -1) {
				_clientSocketStandard = null;
				new ConnectionResponse("Error: TIME OUT to the Server\n",
						ConnectionResponse.TYPE_TIME_OUT);
				break;
			}
			line.append(c);
		}
	}

	public String send(String message) {
		String lineToSend = message;
		if(!message.contains("LOGIN:")){
			log.info("COMMAND SENT: "+lineToSend);
		}
		String lineReceived = new String();
		try {
			_outputStream.println(lineToSend);
			_outputStream.flush();
			lineReceived = _inputStream.readLine();
			readCompleteBuffer();
		} catch (SocketTimeoutException e) {
			_clientSocketStandard = null;
		} catch (Exception e) {

		}
		log.info("MPM RESPONSE: " + lineReceived);
		return getStatus(lineReceived);
	}
	
	private String getStatus(String response_mpm){
		

		if(session.getConnection()!=null){
			log.info("CONNECTED.");
			log.info("MPM RESPONSE ON CONNECTION STATUS: "+response_mpm);
			if(response_mpm.contains(LOCAL_NOT_LOGGED_ERROR) || response_mpm.trim().equals("")){
				log.info("NOT LOGGED.");
				response_mpm = message.get_NOT_LOGGED_WARN();
			}
			else if(response_mpm.endsWith(LOCAL_MSISDN_NOT_FOUND_ERROR_CH1) || response_mpm.endsWith(LOCAL_MSISDN_NOT_FOUND_ERROR_CH2) || response_mpm.endsWith(LOCAL_MSISDN_NOT_FOUND_BSO)){
				log.info("LOGGED AND MSISDN NOT FOUND.");
				response_mpm = message.get_USER_NOT_FOUND();
			}
		}
		else{
			log.info("NOT CONNECTED.");
			response_mpm = message.get_MPM_CONNECTION_WARN();
		}
		return response_mpm;
	}
}