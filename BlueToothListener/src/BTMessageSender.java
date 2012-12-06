import java.io.OutputStream;

import javax.microedition.io.Connector;
import javax.obex.ClientSession;
import javax.obex.HeaderSet;
import javax.obex.Operation;
import javax.obex.ResponseCodes;
import javax.swing.JDialog;

import ui.MyDialog;

public class BTMessageSender {

	public static boolean  sendMessage(String deviceBTURL, String message, MyDialog dialog){
		
		// we assume that the given device already has the service
		
		try{
			 System.out.println("Connecting to " + deviceBTURL);
			 dialog.setStatus("Connecting to " + deviceBTURL);
			
			 //1. open session 
			 ClientSession clientSession = 
				 (ClientSession) Connector.open(deviceBTURL);
			 
			 //2. connect + get response
			 HeaderSet hsConnectReply = clientSession.connect(null);
			 
			 // check response
			 if (hsConnectReply.getResponseCode() != ResponseCodes.OBEX_HTTP_OK) {
		            System.out.println("Failed to connect");
		            dialog.setStatus("Failed to connect");
		            return false;
		      }
			 
			 // 3. create the PUT operation
			 HeaderSet hsOperation = clientSession.createHeaderSet();
		     hsOperation.setHeader(HeaderSet.NAME, "Hello.txt");
		     hsOperation.setHeader(HeaderSet.TYPE, "text");
		     Operation putOperation = clientSession.put(hsOperation);
		
		     
		     // 4. send the data to the device
	         byte data[] = message.getBytes("iso-8859-1");
	         OutputStream os = putOperation.openOutputStream();
	         os.write(data);
	         os.close();
	
	         putOperation.close();
	         clientSession.disconnect(null);
	         clientSession.close();
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
		
	}
	
	
}
