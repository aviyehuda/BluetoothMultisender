package ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class MyDialog extends JDialog implements ActionListener{
	
	private JTextArea jTextArea;
	private JList jList;
	private DialogListener listener;
	private Object[] items;
	private JLabel statusLabel;


	public MyDialog(String title, Object [] items , DialogListener listener ) {
		super.setTitle(title);
		
		this.listener = listener;
		this.setSize(500,300);
		
		jList = new JList();
		
		if(items==null){
			items = new Object[0];
		}
		jList.setListData(items);
		
		this.items = items;
		
		JScrollPane jScrollPane = new JScrollPane(jList);
		
		jScrollPane.setSize(255,5);
		
		this.getContentPane().setLayout(new BorderLayout());
		this.getContentPane().add(jScrollPane,BorderLayout.WEST);
		
		jTextArea = new JTextArea("Enter message here");
		this.getContentPane().add(jTextArea,BorderLayout.CENTER);
		
		
		JButton jButton = new JButton("Send");
		jButton.addActionListener(this);
		this.getContentPane().add(jButton,BorderLayout.SOUTH);
		
		
		statusLabel = new JLabel("Started");
		
		
		this.getContentPane().add(statusLabel,BorderLayout.NORTH);
		
		setLocationRelativeTo(null);
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		this.show();
	}
	
	public void addItem(Object item){
		
		Object [] newitems = new Object[items.length+1];
		
		for (int i = 0; i < items.length; i++) {
			newitems[i] = items[i];
		}
		newitems[newitems.length-1] =item;
		
		items = newitems;
		jList.setListData(newitems);
	}
	

	public void setStatus(String text){
		statusLabel.setText(text);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Send")){
			Object [] selectedItems = jList.getSelectedValues();
			String message = jTextArea.getText();
			
			listener.sendPressed(selectedItems, message,this);
		}
	}
	
	public interface DialogListener{
		public void sendPressed(Object [] selectedItems, String message,MyDialog dialog);
	}
	
	
	

}
