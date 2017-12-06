import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Node_View extends JFrame {
	Server_View sv;
	int id;

	JTextArea textArea;
	JScrollPane js;
	
	public Node_View(Server_View sv , int id){
		this.sv = sv;
		this.id = id;
		setup_node_panel();
	}
	
	public void printProcessList(List<String> pl){
		int position = js.getVerticalScrollBar().getValue();
		js.remove(textArea);
		textArea = new JTextArea();
		textArea.setColumns(20);
		textArea.setRows(5);
		
		for( String s : pl ){
			textArea.append(s+"\n");
		}
		
		js.setViewportView(textArea);
		
		js.getVerticalScrollBar().setValue(position);
		setVisible(true);
		
	}
	
	public void printChart(){
		
	}
	
	//�������̵�
	public void dispose(){
		sv.textArea.append("Ŭ�� ����\n");
		sv.sc.inputCmd("PROCESS_END", " ", id);
		sv.closeNodeView(id);
		super.dispose();
	}
	
	public void setup_node_panel(){
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 280, 400);
		
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout());
		
		js = new JScrollPane();
				
		textArea = new JTextArea();
		textArea.setColumns(20);
		textArea.setRows(5);
		
		js.setPreferredSize(new Dimension(280,300));
		js.setViewportView(textArea);
		
		contentPane.add(js,BorderLayout.CENTER);
		

		JTextField textField = new JTextField();
		
		textField.setColumns(10);

		JButton Start = new JButton("���޼���");

		Start.addActionListener(new ActionListener() { 
			@Override
			public void actionPerformed(ActionEvent e) {
				sv.textArea.append(id+"���� ���� :" + textField.getText() + "\n");
				sv.sc.inputCmd("ALERT",textField.getText(), id);
				textField.setText("");
			}
		}); // Ŭ�� �̺�Ʈ �͸�Ŭ���� ��

		
		JPanel panel =new JPanel();
		panel.setLayout(new BorderLayout());
		
		panel.add(textField,BorderLayout.CENTER);
		panel.add(Start,BorderLayout.SOUTH);
		
		contentPane.add(panel,BorderLayout.SOUTH);
		
		textArea.setEditable(false); // textArea�� ����ڰ� ���� ���ϰԲ� ���´�.
		setVisible(true);
	}
}
