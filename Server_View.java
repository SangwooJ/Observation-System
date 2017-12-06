import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Server_View extends JFrame{
	Server_Controller sc;
	JTextArea textArea;
	private ArrayList<Node_View> node_view_list;
	
	public Server_View(Server_Controller sc){
		this.sc = sc;
		node_view_list = new ArrayList<Node_View>();
		
		init();
	}
	public void closeNodeView(int id){
		Node_View clsNV = null;
		for(Node_View nv : node_view_list){
			if(nv.id == id){
				clsNV = nv;
			}
		}
		sc.deleteNode(clsNV.id);
		node_view_list.remove(clsNV);
		
	}
	public Node_View getNodeView(int id){
		Node_View rtnNV = null;
		for(Node_View nv : node_view_list){
			if(nv.id == id){
				rtnNV = nv;
			}
		}
		
		return rtnNV;
	}
	public void addNodeView(int id){
		Node_View  nv = new Node_View(this,id);
		node_view_list.add(nv);
	}
	private void init() { //메인화면
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 280, 400);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout());

		
		JScrollPane js = new JScrollPane();
				
		textArea = new JTextArea();
		textArea.setColumns(20);
		textArea.setRows(5);
		
		js.setPreferredSize(new Dimension(280,200));
		js.setViewportView(textArea);
		
		contentPane.add(js,BorderLayout.CENTER);
		

		JTextField textField = new JTextField();
		
		textField.setColumns(10);


		JButton Start = new JButton("전체메세지");

		Start.addActionListener(new ActionListener() { 
			@Override
			public void actionPerformed(ActionEvent e) {
				textArea.append("전체전송 :" + textField.getText() + "\n");
				sc.inputCmd("ALERT",textField.getText(), sc.MAX);
				textField.setText("");
			}
		}); // 클릭 이벤트 익명클래스 끝

		
		JPanel panel =new JPanel();
		panel.setLayout(new BorderLayout());
		
		panel.add(textField,BorderLayout.CENTER);
		panel.add(Start,BorderLayout.SOUTH);
		
		contentPane.add(panel,BorderLayout.SOUTH);
		
		textArea.setEditable(false); // textArea를 사용자가 수정 못하게끔 막는다.
		setVisible(true);
	}

}
