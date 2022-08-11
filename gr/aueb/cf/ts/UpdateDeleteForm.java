package gr.aueb.cf.ts;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.ImageIcon;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UpdateDeleteForm extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtId;
	private JTextField txtSname;
	private JTextField txtFname;
	private ResultSet rs;
	private String lastname;
	
	/**
	 * Create the frame.
	 */
	public UpdateDeleteForm() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				try {
					PreparedStatement p;
					String sql = "SELECT ID, FIRSTNAME, LASTNAME FROM TEACHERS WERE LASTNAME LIKE ?";
					p = MainMenu.getConn().prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
					p.setString(1, getLastname() + '%');
					
					rs = p.executeQuery();
					
					if (rs.next()) {
						txtId.setText(rs.getString("ID"));
						txtSname.setText(rs.getString("LASTNAME"));
						txtFname.setText(rs.getString("FIRSTNAME"));
					}else {
						return;
					}
				}catch(SQLException e1){
					e1.printStackTrace();
				}
			}
			
			@Override
			public void windowDeactivated(WindowEvent e) {
				txtId.setText("");
				txtSname.setText("");
				txtFname.setText("");
			}
		});
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(UpdateDeleteForm.class.getResource("/resources/eduv2.png")));
		setTitle("Ενημέρωση / Διαγραφή Εκπαιδευτή");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 370);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbl_Id = new JLabel("ID");
		lbl_Id.setFont(new Font("Tahoma", Font.BOLD, 18));
		lbl_Id.setBounds(44, 24, 80, 29);
		contentPane.add(lbl_Id);
		
		JLabel lblSname = new JLabel("Επώνυμο");
		lblSname.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblSname.setBounds(44, 84, 89, 29);
		contentPane.add(lblSname);
		
		JLabel lblFname = new JLabel("Όνομα");
		lblFname.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblFname.setBounds(44, 139, 80, 29);
		contentPane.add(lblFname);
		
		txtId = new JTextField();
		txtId.setEditable(false);
		txtId.setColumns(10);
		txtId.setBounds(158, 23, 96, 37);
		contentPane.add(txtId);
		
		txtSname = new JTextField();
		txtSname.setColumns(10);
		txtSname.setBounds(158, 80, 188, 37);
		contentPane.add(txtSname);
		
		txtFname = new JTextField();
		txtFname.setColumns(10);
		txtFname.setBounds(158, 135, 188, 37);
		contentPane.add(txtFname);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query = "DELETE FROM TEACHERS WHERE ID = ?";
					PreparedStatement p = MainMenu.getConn().prepareStatement(query);
					p.setInt(1, Integer.parseInt(txtId.getText()));
					
					int answer = JOptionPane.showConfirmDialog(null, "Είστε σίγουρος;", "Warning", JOptionPane.YES_NO_OPTION);
					if (answer == JOptionPane.YES_OPTION) {
						int numberOfRowsAffected = p.executeUpdate();
						JOptionPane.showMessageDialog(null, numberOfRowsAffected + " rows deleted successfully", "DELETE", JOptionPane.INFORMATION_MESSAGE);
					}
				}catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnDelete.setBounds(17, 274, 122, 37);
		contentPane.add(btnDelete);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query = "UPDATE TEACHERS set FIRSTNAME = ?, LASTNAME = ? WHERE ID = ?";
					PreparedStatement p = MainMenu.getConn().prepareStatement(query);
					p.setString(1, txtFname.getText());
					p.setString(2,  txtSname.getText());
					p.setInt(3, Integer.parseInt(txtId.getText()));
						
					int numberOfRowsAffected = p.executeUpdate();
					JOptionPane.showMessageDialog(null, numberOfRowsAffected + " rows deleted successfully", "DELETE", JOptionPane.INFORMATION_MESSAGE);
					
					p.close();
					
				}catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnUpdate.setBounds(156, 274, 122, 37);
		contentPane.add(btnUpdate);
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.getSearchForm().setEnabled(true);
				Main.getUpdateDeleteForm().setVisible(false);
			}
		});
		btnClose.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnClose.setBounds(295, 274, 122, 37);
		contentPane.add(btnClose);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 195, 416, 2);
		contentPane.add(separator);
		
		JButton btnFirst = new JButton("");
		btnFirst.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (rs.first()) {
						txtId.setText(rs.getString("ID"));
						txtSname.setText(rs.getString("LASTNAME"));
						txtFname.setText(rs.getString("FIRSTNAME"));
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnFirst.setIcon(new ImageIcon(UpdateDeleteForm.class.getResource("/resources/First record.png")));
		btnFirst.setBounds(95, 207, 40, 30);
		contentPane.add(btnFirst);
		
		JButton btnPrevious = new JButton("");
		btnPrevious.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (rs.previous()) {
						txtId.setText(rs.getString("ID"));
						txtSname.setText(rs.getString("LASTNAME"));
						txtFname.setText(rs.getString("FIRSTNAME"));
					} else {
						rs.first();
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnPrevious.setIcon(new ImageIcon(UpdateDeleteForm.class.getResource("/resources/Previous_record.png")));
		btnPrevious.setBounds(155, 207, 40, 30);
		contentPane.add(btnPrevious);
		
		JButton btnNext = new JButton("");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (rs.next()) {
						txtId.setText(rs.getString("ID"));
						txtSname.setText(rs.getString("LASTNAME"));
						txtFname.setText(rs.getString("FIRSTNAME"));
					} else {
						rs.last();
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNext.setIcon(new ImageIcon(UpdateDeleteForm.class.getResource("/resources/Next_track.png")));
		btnNext.setBounds(215, 207, 40, 30);
		contentPane.add(btnNext);
		
		JButton btnLast = new JButton("");
		btnLast.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (rs.last()) {
						txtId.setText(rs.getString("ID"));
						txtSname.setText(rs.getString("LASTNAME"));
						txtFname.setText(rs.getString("FIRSTNAME"));
					}
				}catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnLast.setIcon(new ImageIcon(UpdateDeleteForm.class.getResource("/resources/Last_Record.png")));
		btnLast.setBounds(275, 207, 40, 30);
		contentPane.add(btnLast);
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
}
