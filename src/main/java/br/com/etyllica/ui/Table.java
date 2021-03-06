package br.com.etyllica.ui;

import java.util.List;

import br.com.etyllica.ui.base.BaseTable;
import br.com.etyllica.ui.base.UIView;
import br.com.etyllica.ui.listener.RowListener;
import br.com.etyllica.ui.table.Row;
import br.com.etyllica.ui.theme.ThemeManager;

/**
 * 
 * @author yuripourre
 *
 */

public class Table extends UIView {

	BaseTable table;

	public Table() {
		this(0,0,0,0);
	}

	public Table(int x, int y, int w, int h) {
		super(x,y,w,h);

		this.table = ThemeManager.getInstance().getTheme().createTable(x, y, w, h);
		delegateView(table);
	}

	public void rebuild() {
		BaseTable view = ThemeManager.getInstance().getTheme().createTable(x, y, w, h);
		view.copy(delegatedView);

		delegateView(view);
	}

	public Row addRow(String...values) {
		return table.addRow(values);
	}
	
	public void addHeader(String header) {
		table.addHeader(header);
	}
	
	public void setHeaders(List<String> headers) {
		table.setHeaders(headers);
	}
	
	public void setHeaders(String ... headers) {
		table.setHeaders(headers);
	}

	public void add(Row row) {
		table.add(row);
	}
	
	public void setRowListener(RowListener rowListener) {
		table.setRowListener(rowListener);
	}
	
	public RowListener getRowListener() {
		return table.getRowListener();
	}

	public void selectRow(int index) {
		table.selectRow(index);
	}
	
	public void selectRow(String column, String label) {
		table.selectRow(column, label);
	}
		
	public void selectRow(Row row) {
		table.selectRow(row);
	}
	
}
