package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import javax.swing.JComponent;
import javax.swing.JOptionPane;

public class Myboard extends JComponent {

	static String name1 = JOptionPane.showInputDialog(null, "What's your name?");
	static String name = "'" + name1 + "'";

	static String sql1 = "insert into user(username) values(?)";
	static String sql2 = "insert into sb values (?,?,?)";
	static String sql3 = "insert into myscores values (?,?,?)";

	static PreparedStatement pst = null;
	static Connection conn = null;
	static ResultSet ret = null;

	private static final String USER_NAME = "root";
	private static final String PASSWORD = "123";
	public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://localhost:3306/zuma?characterEncoding=utf8&useUnicode=true&useSSL=false&serverTimezone=UTC";

	private static final long serialVersionUID = 1L;
	private int x = 20;
	private int y = 100;

	public void paint(Graphics g) {
		try {
			Class.forName(DRIVER);
			Connection conn = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);

//				�����ҵ�����������δ��������Ϸ
			String string = "select username from user";
			pst = conn.prepareStatement(string);
			ret = pst.executeQuery();
			int count1 = 0;
			while (ret.next()) {
				String username = ret.getString("username");
				if (name1.equals(username)) {
					count1++;
				}
			}
			ret.close();
			if (count1 == 0) {
				pst = conn.prepareStatement(sql1);
				pst.setString(1, name1);
				pst.executeUpdate();
			}

			String ha = "select userid from user where username =" + name;
			pst = conn.prepareStatement(ha);
			ret = pst.executeQuery();
			int i = 0;
			while (ret.next()) {
				i = ret.getInt("userid");
			}
			ret.close();

//				ÿ�β�����ҵķ���
			pst = conn.prepareStatement(sql3);
			pst.setInt(1, 20000);// ��ҵķ���
			pst.setInt(2, i);
			// ��õ�ǰ����������date
			java.sql.Date date = new java.sql.Date(System.currentTimeMillis());
			pst.setDate(3, date);
			pst.executeUpdate();

////			//���������߷��������а�
			// �Ƴ��Ѿ����������а��ϵ���ҷ�����¼
			String drop = "delete from sb where userid=" + i;

			pst = conn.prepareStatement(drop);
			pst.executeUpdate();

			String insert = "select max(score) from myscores where userid=" + i;
			pst = conn.prepareStatement(insert);
			ret = pst.executeQuery();
			int k = 0;
			while (ret.next()) {
				k = ret.getInt("max(score)");
			}
			ret.close();

			pst = conn.prepareStatement(sql2);
			pst.setString(1, name1);
			pst.setInt(2, k);
			pst.setInt(3, i);
			pst.executeUpdate();

			String my = "select * from myscores where userid=" + i;
			// ִ����䣬�õ������
			pst = conn.prepareStatement(my);
			ret = pst.executeQuery();

			g.setColor(Color.GREEN);
			g.setFont(new Font("Arial", Font.CENTER_BASELINE, 30));
			g.drawString("My Score Board", x + 160, y);
			g.drawString("id", x + 100, y + 50);
			g.drawString("score", x + 200, y + 50);
			g.drawString("playdate", x + 400, y + 50);

//	            ArrayList<ResultSet> records=new ArrayList<ResultSet>();
			int extra = 0;
			while (ret.next()) {
				int count = ret.getInt("score");
//					String name = ret.getString("username");  
				int id = ret.getInt("userid");
//		            Date date2=ret.getDate("date");
//					 System.out.println(id + "\t" + name + "\t" + count);��ʾ���� 
				String s1 = Integer.toString(id);
				String s2 = Integer.toString(count);

//					int score= ret.getInt("score");
//					int id=ret.getInt("userid");
				java.sql.Date date2 = ret.getDate("date");
				SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
//					 
//					String s1 = Integer.toString(id);
//				    String s2 = Integer.toString(score);
				String s3 = sdf2.format(date2);
				g.setFont(new Font("Arial", Font.BOLD, 20));
				g.drawString(s1, x + 100, y + 100 + extra);
				g.drawString(s2, x + 200, y + 100 + extra);
				g.drawString(s3, x + 400, y + 100 + extra);

				extra = extra + 30;
			}
			conn.close();
			ret.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e1) {
			System.out.println("Sorry,can't find the Driver!");
			e1.printStackTrace();
		}
	}
}
