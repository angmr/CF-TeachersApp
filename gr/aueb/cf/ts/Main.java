package gr.aueb.cf.ts;

import java.awt.EventQueue;

public class Main {
	private static MainMenu menu;
	private static InsertForm insertForm;
	private static SearchForm searchForm;
	private static UpdateDeleteForm updateDeleteForm;

	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					menu = new MainMenu();
					menu.setVisible(true);
					
					insertForm = new InsertForm();
					insertForm.setVisible(false);
					
					searchForm = new SearchForm();
					searchForm.setVisible(false);
					
				} catch (Exception e){
					e.printStackTrace();
				}
			}
		});
	}

	public static MainMenu getMenu() {
		return menu;
	}

	public static InsertForm getInsertForm() {
		return insertForm;
	}
	
	public static SearchForm getSearchForm() {
		return searchForm;
	}

	public static UpdateDeleteForm getUpdateDeleteForm() {
		return updateDeleteForm;
	}
	
}
