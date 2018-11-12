package app.java.com.model.usecase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import app.java.com.model.Exceptions.SelectException;
import app.java.com.model.database.api.SelectCommand;
import app.java.com.presenter.interfaces.FetchUserDataResultInterface;

public class FetchUserDataUseCase extends UseCase {

	private String table = null;
	private List<String> target = null;
	private List<String> constraints = null;
	private FetchUserDataResultInterface resultInterface;
	
	public FetchUserDataUseCase (FetchUserDataResultInterface resultInterface, List<String> target, String table, List<String> constraint) {
		this.resultInterface = resultInterface;
		this.target = target;
		this.table = '`' + table.replace(' ', '_') + '`';
		this.constraints = constraint;
	}
	
	@Override
	public void run() {
		List<String> columns = new ArrayList<String>();
		List<List<String>> data = new ArrayList<List<String>>();
		SelectCommand selectCommand = new SelectCommand(target, table, constraints);
		
		try {
			columns.addAll(selectCommand.getColumnIds());
			data.addAll(selectCommand.selectHandle());
        } catch (SelectException e) {
            resultInterface.onErrorSelectTable("failed when select " + e.getMessage());
        }
		
		if (data.isEmpty()) {
			resultInterface.onErrorSelectTable("No Data Returned");
		}
		else {
			resultInterface.onSuccessSelectTable(columns, data);
		}
	}
}