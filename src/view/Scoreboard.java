package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Scoreboard extends JComponent {

	// user name
	static String name1 = JOptionPane.showInputDialog(null, "What's your name?");
	static String name = "'" + name1 + "'";

	static String sql = "select * from sb order by count DESC LIMIT 10";
	static String sqlsql = "select * from sb where username=" + name;

	static String ha = "select userid from user where username='Jenny'";
	static String haha = "select userid,username,max(score) from myscores " + "join user using(userid) group by userid";

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

			java.sql.Date date = new java.sql.Date(System.currentTimeMillis());

			pst = conn.prepareStatement(haha);
			ret = pst.executeQuery();
			int i = 0;
			while (ret.next()) {
				i = ret.getInt("max(score)");
			}
			ret.close();

//				//������
//				pst=conn.prepareStatement(sql1);
//				pst.setString(1, name);

			// ÿ�β�����ҵķ���
//				pst=conn.prepareStatement(sql3);
//				pst.setInt(1, x);
//				pst.setInt(2, i);
//				pst.setDate(3, date);
//				pst.executeUpdate();

////			//������������������а�
//				pst=conn.prepareStatement(sql2);
//				pst.setString(1, "Jenny");
//				pst.setInt(2, i);
//				pst.setInt(3, 1);
//				pst.executeUpdate();

			// ִ����䣬�õ������
			pst = conn.prepareStatement(sql);
			ret = pst.executeQuery();

			g.setColor(Color.GREEN);
			g.setFont(new Font("Arial", Font.CENTER_BASELINE, 30));
			g.drawString("Zuma Score Board", x + 160, y);
			g.drawString("id", x + 100, y + 50);
			g.drawString("username", x + 200, y + 50);
			g.drawString("score", x + 400, y + 50);

//	            ArrayList<ResultSet> records=new ArrayList<ResultSet>();
			int extra = 0;
			while (ret.next()) {

				String name = ret.getString("username");
				int count = ret.getInt("count");
				int id = ret.getInt("userid");
//					 System.out.println(id + "\t" + name + "\t" + count);��ʾ���� 
				String s1 = Integer.toString(id);
				String s2 = Integer.toString(count);

//					int score= ret.getInt("score");
//					int id=ret.getInt("userid");
//					java.sql.Date date2=ret.getDate("date");
//					SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd"); 
//					 
//					String s1 = Integer.toString(id);
//				    String s2 = Integer.toString(score);
//				    String s3 = sdf2.format(date2);
				g.setFont(new Font("Arial", Font.BOLD, 20));
				g.drawString(s1, x + 100, y + 100 + extra);
				g.drawString(name, x + 200, y + 100 + extra);
				g.drawString(s2, x + 400, y + 100 + extra);

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
