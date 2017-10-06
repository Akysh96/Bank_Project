package gui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;

import bank.Account;
import bank.Person;

public class GetTable {

	@SuppressWarnings({ "rawtypes", "unchecked" })

	public JTable getTable(HashMap<Integer, Person> person) {

		JComboBox[] box = new JComboBox[person.size()];
		DefaultCellEditor[] def = new DefaultCellEditor[person.size()];
		List<TableCellEditor> editors = new ArrayList<TableCellEditor>(3);
		String[] col = { "Id", "Name", "Surname", "CNP", "type" };
		DefaultTableModel defT = new DefaultTableModel(col, 0);
		JTable table = new JTable(defT);
		int i = 0;
		for (Person person1 : person.values()) {
			box[i] = new JComboBox<String>();
			for (Account account : person1.getAccounts()) {
				box[i].addItem(account.getType());
			}
			Object[] rows = { person1.getId(), person1.getName(), person1.getSurName(), person1.getCnp(),
					box[i].getSelectedItem() };
			defT.addRow(rows);
			def[i] = new DefaultCellEditor(box[i]);
			editors.add(def[i]);
			table = new JTable(defT) {
				private static final long serialVersionUID = 1L;

				public TableCellEditor getCellEditor(int row, int column) {
					int modelColumn = convertColumnIndexToModel(column);
					if (modelColumn == 4)
						return editors.get(row);
					else
						return super.getCellEditor(row, column);
				}
			};
			i++;
		}
		table.setFillsViewportHeight(true);
		return table;
	}

}
