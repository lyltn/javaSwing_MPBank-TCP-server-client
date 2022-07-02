import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Customer extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Customer frame = new Customer();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Customer() {
		setTitle("Bank Account");
		Connect cn = new Connect();
	    Connection conn = cn.getConnection();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 823, 474);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(248, 248, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField.setBounds(259, 50, 301, 40);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Nap Tien");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Socket socket = new Socket("localhost", 5000);
					DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
					
					dos.writeUTF(1+"-"+textField.getText()+"-"+textField_1.getText());
					DataInputStream din = new DataInputStream(socket.getInputStream());
					String time = din.readUTF();
					if(time.length()>0)
						JOptionPane.showMessageDialog(null, "so du: "+time);
////					time = 1+"-"+time;
//					System.out.println(time);
				} catch (Exception e4) {
					// TODO: handle exception
					e4.printStackTrace();
				}
			}
		});
		btnNewButton.setBackground(new Color(240, 255, 255));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnNewButton.setBounds(82, 207, 160, 40);
		contentPane.add(btnNewButton);
		
		JButton btnRutTien = new JButton("Rut Tien");
		btnRutTien.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Socket socket = new Socket("localhost", 5000);
					DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
					
					dos.writeUTF(2+"-"+textField.getText()+"-"+textField_1.getText());
					DataInputStream din = new DataInputStream(socket.getInputStream());
					String time = din.readUTF();
					if(time.length()>0)
					JOptionPane.showMessageDialog(null, "so du: "+time);
				} catch (Exception e4) {
					// TODO: handle exception
					e4.printStackTrace();
				}

			}
		});
		btnRutTien.setBackground(new Color(240, 255, 255));
		btnRutTien.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnRutTien.setBounds(530, 207, 160, 40);
		contentPane.add(btnRutTien);
		
		JButton btnChuyenTien = new JButton("Chuyen Tien");
		btnChuyenTien.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String m = JOptionPane.showInputDialog("chuyen cho STK: ");
				String sql = "SELECT ten FROM taikhoan where stk ='"+m+"'";
				PreparedStatement stm;
				try {
					stm = conn.prepareStatement(sql);
					ResultSet rs = stm.executeQuery();
					while(rs.next()) {
						JOptionPane.showConfirmDialog(null, rs.getString(1));
					}
					try {
						Socket socket = new Socket("localhost", 5000);
						DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
						
						dos.writeUTF(3+"-"+textField.getText()+"-"+textField_1.getText()+"-"+m);
						DataInputStream din = new DataInputStream(socket.getInputStream());
						String time = din.readUTF();
						if(time.length()>0)
						JOptionPane.showMessageDialog(null, "so du: "+time);
					} catch (Exception e4) {
						// TODO: handle exception
						e4.printStackTrace();
					}
				} catch (SQLException e0) {
					// TODO Auto-generated catch block
					e0.printStackTrace();
				}
			}
		});
		btnChuyenTien.setBackground(new Color(240, 255, 255));
		btnChuyenTien.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnChuyenTien.setBounds(82, 322, 160, 40);
		contentPane.add(btnChuyenTien);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField_1.setColumns(10);
		textField_1.setBounds(259, 119, 301, 40);
		contentPane.add(textField_1);
		
		JLabel lblNewLabel = new JLabel("STK:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(166, 50, 93, 40);
		contentPane.add(lblNewLabel);
		
		JLabel lblSoTien = new JLabel("So Tien:");
		lblSoTien.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblSoTien.setBounds(166, 119, 93, 40);
		contentPane.add(lblSoTien);
		
		JButton btnKiemTra = new JButton("kiem Tra");
		btnKiemTra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String a ="";
				String sql = "SELECT ten FROM taikhoan where stk ='"+textField.getText()+"'";
				PreparedStatement stm;
				try {
					stm = conn.prepareStatement(sql);
					ResultSet rs = stm.executeQuery();
					while(rs.next()) {
						JOptionPane.showMessageDialog(null, rs.getString(1));
					}
					
				} catch (SQLException e0) {
					// TODO Auto-generated catch block
					e0.printStackTrace();
				}

			}
		});
		btnKiemTra.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnKiemTra.setBackground(new Color(240, 255, 255));
		btnKiemTra.setBounds(530, 322, 160, 40);
		contentPane.add(btnKiemTra);
		setResizable(false);
		setLocationRelativeTo(null);
	}
}
