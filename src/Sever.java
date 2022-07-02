
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.InterfaceAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Sever {
	public static Connect cn = new Connect();
	public static Connection conn = cn.getConnection();
	public static void up(String u, String y) {
		double a = 0;
		String sql = "SELECT tien FROM taikhoan where stk ='"+u+"'";
		PreparedStatement stm;
		try {
			stm = conn.prepareStatement(sql);
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				a = rs.getDouble(1);
			}
		} catch (SQLException e0) {
			// TODO Auto-generated catch block
			e0.printStackTrace();
		}
		String query = "update taikhoan set tien = ? where stk ='"+u+"'";
		PreparedStatement stm1;
		try {
			stm1 = conn.prepareStatement(query);
			stm1.setDouble(1, Integer.parseInt(y)+a);
			
			stm1.executeUpdate();

		} catch (SQLException e9) {
			// TODO Auto-generated catch block
			e9.printStackTrace();
		}
	}
	public static void tru(String u, String y) {
		double a = 0;
		String sql = "SELECT tien FROM taikhoan where stk ='"+u+"'";
		PreparedStatement stm;
		try {
			stm = conn.prepareStatement(sql);
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				a = rs.getDouble(1);
			}
		} catch (SQLException e0) {
			// TODO Auto-generated catch block
			e0.printStackTrace();
		}
		String query = "update taikhoan set tien = ? where stk ='"+u+"'";
		PreparedStatement stm1;
		try {
			stm1 = conn.prepareStatement(query);
			stm1.setDouble(1, a-Integer.parseInt(y));
			
			stm1.executeUpdate();

		} catch (SQLException e9) {
			// TODO Auto-generated catch block
			e9.printStackTrace();
		}

	}
	public static void main(String[] args) {
		try {
			ServerSocket server = new ServerSocket(5000);
			System.out.println("Server is started");
			while (true) {
				Socket socket = server.accept();
				System.out.println("okok");
//				InetSocketAddress t = (InetSocketAddress) socket.getRemoteSocketAddress();
//				System.out.println("iP: " + t.toString());
				DataInputStream din = new DataInputStream(socket.getInputStream());
				String time1 = din.readUTF();
				String[] i = time1.split("-");
				int k = Integer.parseInt(i[0]);	
//				System.out.println(k);
//				System.out.println(i[1]);
				if(k==1)
					up(i[1], i[2]);
				if(k==2)
					tru(i[1], i[2]);
				if(k==3) {
					tru(i[1], i[2]);
					up(i[3], i[2]);
				}
//				System.out.println(time1);
//				int i = Integer.parseInt(time1);
//				long u = 1;
//				for (int j = 1; j <= i; j++) {
//					u *= j;
//				}
				double u=0;
				String sql = "SELECT tien FROM taikhoan where stk ='"+i[1]+"'";
				PreparedStatement stm;
				try {
					stm = conn.prepareStatement(sql);
					ResultSet rs = stm.executeQuery();
					while (rs.next()) {
						u = rs.getDouble(1);
					}
				} catch (SQLException e0) {
					// TODO Auto-generated catch block
					e0.printStackTrace();
				}
				DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
				String time = String.valueOf(u);
				dos.writeUTF(time);
//				BufferedReader u = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//				DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
//				String ti;
//				while(true) {
//					ti = u.readLine();
//					System.out.println(ti);
//					 
//				}
				socket.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}
}


