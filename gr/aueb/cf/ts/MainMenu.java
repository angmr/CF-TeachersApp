package gr.aueb.cf.ts;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MainMenu extends JFrame {
	
    private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static Connection conn;

	/**
	 * Create the frame.
	 */
	public MainMenu() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				String url = "jdbc:mysql://localhost:3306/tsdb?serverTimeZone=UTC";
				String username = "tsuser";
				String password = "Tsuser";
				
				try {
					conn = DriverManager.getConnection(url, username, password);
					System.out.println("Connection Established");
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		this.setTitle("AUEB Coding Factory");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(450, 300);
		this.setLocationRelativeTo(null);
		this.contentPane = new JPanel();
		contentPane.setBackground(new Color(230, 230, 250));
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitle = new JLabel("Ποιότητα στην Εκπαίδευση");
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblTitle.setForeground(new Color(102, 153, 255));
		lblTitle.setBounds(38, 26, 362, 36);
		contentPane.add(lblTitle);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(19, 72, 400, 2);
		contentPane.add(separator);
		
		JButton btnStudents = new JButton("");
		btnStudents.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.getMenu().setEnabled(false);
				Main.getSearchForm().setVisible(true);
			}
		});
		btnStudents.setBounds(61, 112, 40, 40);
		contentPane.add(btnStudents);
		
		JButton btnTeachers = new JButton("");
		btnTeachers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnTeachers.setBounds(61, 175, 40, 40);
		contentPane.add(btnTeachers);
		
		JLabel lblTeachers = new JLabel("Εκπαιδευτές");
		lblTeachers.setForeground(new Color(123, 104, 238));
		lblTeachers.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTeachers.setBounds(111, 118, 134, 29);
		contentPane.add(lblTeachers);
		
		JLabel lblStudents = new JLabel("Εκπαιδευόμενοι");
		lblStudents.setForeground(new Color(123, 104, 238));
		lblStudents.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblStudents.setBounds(111, 181, 134, 29);
		contentPane.add(lblStudents);
	}

	public static Connection getConn() {
		return conn;
	}
}
