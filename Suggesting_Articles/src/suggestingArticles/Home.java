package suggestingArticles;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class Home extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8757971442865889030L;
	private JPanel contentPane;
	private JTable allPostTable;
	private JTable suggestedTable;
	JLabel sgstlbl;
	Connection con = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home frame = new Home();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */

	// list all posts
	public void listPosts() {
		ResultSet rs = null;
		PreparedStatement st = null;

		try {
			con = DriverManager.getConnection("jdbc:h2:~/article", "article", "");
			String h = "SELECT NAME FROM TEST";
			st = con.prepareStatement(h);
			rs = st.executeQuery();

			// while(rs.next()){
			allPostTable.setModel(net.proteanit.sql.DbUtils.resultSetToTableModel(rs));
			// }
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}

	// list posts according to users taste
	public void suggestedPosts() {
		ResultSet rs = null;
		PreparedStatement st = null;

		try {
			con = DriverManager.getConnection("jdbc:h2:~/article", "article", "");
			String h = "SELECT LANGUAGE FROM USERS  where name LIKE '%" + User.loggedInUser
					+ "%' ORDER BY VIEWS DESC LIMIT 1";
			st = con.prepareStatement(h);
			rs = st.executeQuery();

			while (rs.next()) {
				sgstlbl.setText(rs.getString("LANGUAGE"));
			}
		} catch (SQLException e) {
			 
			e.printStackTrace();
		}

		if (sgstlbl.getText() != null) {
			try {
				con = DriverManager.getConnection("jdbc:h2:~/article", "article", "");
				String h = "SELECT NAME FROM TEST where TAGS LIKE '%" + sgstlbl.getText() + "%'";
				st = con.prepareStatement(h);
				rs = st.executeQuery();

				suggestedTable.setModel(net.proteanit.sql.DbUtils.resultSetToTableModel(rs));

			} catch (SQLException f) {
				 
				f.printStackTrace();
			}
		} else {

		}

	}

	public void back() {
		User.main(null);
		this.dispose();
	}

	public String retrieveLang() {
		DefaultTableModel mt = (DefaultTableModel) allPostTable.getModel();
		String postName = (String) mt.getValueAt(allPostTable.getSelectedRow(), 0);

		ResultSet rs = null;
		PreparedStatement st = null;
		String getLang = null;

		try {
			con = DriverManager.getConnection("jdbc:h2:~/article", "article", "");
			String sql = "SELECT LANGUAGE FROM TEST WHERE NAME = '" + postName + "' ";
			st = con.prepareStatement(sql);
			rs = st.executeQuery();

			while (rs.next()) {
				getLang = rs.getString("LANGUAGE");
			}

		} catch (SQLException e) {
			 
			e.printStackTrace();
		}
		return getLang;
	}

	public void updateCount() {
		retrieveLang();
		PreparedStatement st = null;

		try {
			con = DriverManager.getConnection("jdbc:h2:~/article", "article", "");
			String sql = "UPDATE USERS SET VIEWS = VIEWS + 1 WHERE NAME = '"+User.loggedInUser+"' and language = '"+retrieveLang().toString()+"' ";
			st = con.prepareStatement(sql);
			st.executeUpdate();
			System.out.println("updated");
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		suggestedPosts();
		
	}

	public Home() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 760, 509);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 72, 487, 358);
		contentPane.add(scrollPane);

		allPostTable = new JTable();
		allPostTable.setFont(new Font("SansSerif", Font.PLAIN, 16));
		scrollPane.setColumnHeaderView(allPostTable);
		allPostTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				updateCount();

			}
		});

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(540, 32, 175, 398);
		contentPane.add(scrollPane_1);

		suggestedTable = new JTable();
		scrollPane_1.setColumnHeaderView(suggestedTable);

		JLabel lblNewLabel = new JLabel("All Posts");
		lblNewLabel.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblNewLabel.setBounds(10, 47, 92, 14);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Suggested Post");
		lblNewLabel_1.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(540, 0, 175, 27);
		contentPane.add(lblNewLabel_1);

		JLabel welcome = new JLabel("New label");
		welcome.setFont(new Font("SansSerif", Font.PLAIN, 18));
		welcome.setBounds(10, 8, 209, 28);
		contentPane.add(welcome);

		welcome.setText("Welcome " + User.loggedInUser);

		sgstlbl = new JLabel("New label");
		sgstlbl.setBounds(705, 0, 29, 1);
		contentPane.add(sgstlbl);

		JButton btnNewButton = new JButton("Back");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				back();
			}
		});
		btnNewButton.setBounds(10, 441, 55, 23);
		contentPane.add(btnNewButton);
		listPosts();
		suggestedPosts();

	}
}
