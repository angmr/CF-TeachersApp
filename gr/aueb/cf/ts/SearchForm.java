package gr.aueb.cf.ts;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SearchForm extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtSname;
	private JButton btnInsert;
	private JPanel panel;
	private JPanel panel_1;
	private static String inputLastname;

	/**
	 * Create the frame.
	 */
	public SearchForm() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(SearchForm.class.getResource("/resources/eduv2.png")));
		setTitle("Εισαγωγή / Αναζήτηση Εκπαιδευτών");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(443, 369);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblSname = new JLabel("Επώνυμο");
		lblSname.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSname.setBounds(178, 36, 69, 20);
		contentPane.add(lblSname);
		
		txtSname = new JTextField();
		txtSname.setBounds(80, 66, 279, 19);
		contentPane.add(txtSname);
		txtSname.setColumns(10);
		
		JButton btnSearch = new JButton("Αναζήτηση");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inputLastname = txtSname.getText();
				Main.getUpdateDeleteForm().setLastname(inputLastname);
				Main.getUpdateDeleteForm().setVisible(true);
			}
		});
		btnSearch.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnSearch.setBounds(163, 95, 109, 34);
		contentPane.add(btnSearch);
		
		btnInsert = new JButton("Εισαγωγή");
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.getSearchForm().setEnabled(false);
				Main.getInsertForm().setVisible(true);
			}
		});
		btnInsert.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnInsert.setBounds(163, 185, 109, 34);
		contentPane.add(btnInsert);
		
		panel = new JPanel();
		panel.setBounds(35, 23, 355, 129);
		contentPane.add(panel);
		
		panel_1 = new JPanel();
		panel_1.setBounds(35, 162, 355, 78);
		contentPane.add(panel_1);
		
		JButton btnNewButton = new JButton("Close");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.getMenu().setEnabled(true);
				Main.getSearchForm().setVisible(false);
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton.setBounds(293, 269, 97, 34);
		contentPane.add(btnNewButton);
	}

	public static String getInputLastname() {
		return inputLastname;
	}
}
