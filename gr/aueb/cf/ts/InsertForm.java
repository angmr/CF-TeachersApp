package gr.aueb.cf.ts;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JTable;

import java.awt.Toolkit;
import javax.swing.JButton;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class InsertForm extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textSname;
	private JTextField textFname;
	private JSeparator separator;
	private JButton btnInsert;
	private JButton btnClose;

	/**
	 * Create the frame.
	 */
	public InsertForm() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				textSname.setText("");
				textFname.setText("");
			}
		});
		setForeground(new Color(255, 255, 255));
		setIconImage(Toolkit.getDefaultToolkit().getImage(InsertForm.class.getResource("/resources/eduv2.png")));
		setTitle("Εισαγωγή Εκπαιδευτή");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(230, 230, 250));
		contentPane.setForeground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblSname = new JLabel("Επώνυμο");
		lblSname.setForeground(new Color(199, 21, 133));
		lblSname.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblSname.setBounds(64, 62, 85, 13);
		contentPane.add(lblSname);
		
		JLabel lblFname = new JLabel("Όνομα");
		lblFname.setForeground(new Color(199, 21, 133));
		lblFname.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblFname.setBounds(64, 120, 85, 13);
		contentPane.add(lblFname);
		
		textSname = new JTextField();
		textSname.setBounds(165, 59, 126, 19);
		contentPane.add(textSname);
		textSname.setColumns(10);
		
		textFname = new JTextField();
		textFname.setColumns(10);
		textFname.setBounds(165, 117, 126, 19);
		contentPane.add(textFname);
		
		separator = new JSeparator();
		separator.setBounds(10, 199, 416, 2);
		contentPane.add(separator);
		
		btnInsert = new JButton("Insert");
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String inputLastname = textSname.getText();
				String inputFirstname = textFname.getText();
				
				try {
					// SQL Injection -- "a' OR "'1' = '1"
					// PreparedStatement p = MainMenu.getConn().prepareStatement("INSERT INTO TEACHERS (FIRSTNAME, LASTNAME) VALUES ('" + inputFirstname + "', '" + inputLastname"'');
					
					PreparedStatement p = MainMenu.getConn().prepareStatement("INSERT INTO TEACHERS (FIRSTNAME, LASTNAME) VALUES (?, ?)");
					p.setString(1, inputFirstname);
					p.setString(2, inputLastname);
					
					int n = p.executeUpdate();
					
					JOptionPane.showMessageDialog(null, n + " records inserted", "INSERT", JOptionPane.PLAIN_MESSAGE);
					
					p.close();
					
				} catch (SQLException e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "Smth went wrong with insertion, please try again", "INSERT", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnInsert.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnInsert.setBounds(206, 213, 85, 40);
		contentPane.add(btnInsert);
		
		btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.getSearchForm().setEnabled(true);
				Main.getInsertForm().setVisible(false);
			}
		});
		btnClose.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnClose.setBounds(306, 213, 85, 40);
		contentPane.add(btnClose);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(230, 230, 250));
		panel.setBounds(37, 35, 354, 137);
		contentPane.add(panel);
		
		JTable table = new JTable();
		table.setModel(new DefaultTableModel(
				new Object[][] {},
				new String[] {
						"ID", "Επώνυμο", "Όνομα"
				}
		));
		table.setBounds(52, 232, 30, -26);
		contentPane.add(table);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(125, 204, 2, 2);
		contentPane.add(scrollPane);
	}
}
