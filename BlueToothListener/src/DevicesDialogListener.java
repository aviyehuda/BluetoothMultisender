import ui.MyDialog;
import ui.MyDialog.DialogListener;



public class DevicesDialogListener implements DialogListener {

	@Override
	public void sendPressed(Object[] selectedItems, String message,MyDialog dialog) {
		System.out.println(""+message);
		
		for (Object item : selectedItems) {
			BTMessageSender.sendMessage(((BTDevice)item).getBTURL(),message, dialog);
		}

	}

}
