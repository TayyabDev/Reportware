package app.java.com.model.usecase;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import app.java.com.model.Exceptions.SelectException;
import app.java.com.model.database.api.SelectCommand;
import app.java.com.presenter.interfaces.FetchAttributeNamesResultInterface;

public class FetchAttributeNamesUseCase extends UseCase{

    private FetchAttributeNamesResultInterface resultInterface;
    public FetchAttributeNamesUseCase(FetchAttributeNamesResultInterface resultInterface){
        this.resultInterface = resultInterface;
    }


    @Override
    public void run() {
        try {
            List<String> attrs = fetchAttributeNames();
            resultInterface.onSuccessFetchingAttributes(attrs);
        } catch (SelectException e) {
           resultInterface.onErrorFetchingAttributes(e.getMessage());
        }

    }


    /*
     * get the list of template table name in the database
     */
    public static List<String> fetchTemplateTableName() throws SelectException {
        List<String> target = new ArrayList<String>();

        target.add("tableName");
        SelectCommand fetchTemplateTableNameComm = new SelectCommand(target, "Template");
        List<String> templateTableNames = fetchTemplateTableNameComm.selectHandleSingle();
        return templateTableNames;
    }

    /*
     * get the attributes names for each of the template table in the database
     */
    public static List<String> fetchAttributeNames(List<String> tableNames) throws SelectException {
        List<String> allAttributeNames = new ArrayList<String>();
        for (String tableName : tableNames) {
            SelectCommand getAttributeComm = new SelectCommand(tableName);
            List<String> attrNameForOneTable = getAttributeComm.getColumnIds();
            // remove the first three columns from each table
            // (id, clientDataFormId, processing_detail)
            for (int i = 0; i < 3; i++) {
                attrNameForOneTable.remove(0);
            }
            allAttributeNames.addAll(attrNameForOneTable);
        }
        List<String> formulatedRealNames = new ArrayList<>();
        // need to get the real name for the user not the database ids
        for (String id : allAttributeNames) {
            List<String> target = new ArrayList<>();
            target.add("realName");
            List<String> constraint = new ArrayList<>();
            constraint.add("`variableName` = \'`" + id+ "`\'");
            SelectCommand getRealNameComm = new SelectCommand(target, "VariableName", constraint);
            List<String> realNames = getRealNameComm.selectHandleSingle();
            formulatedRealNames.addAll(realNames);
        }
        return formulatedRealNames;
    }

    public static List<String> fetchAttributeNames() throws SelectException {
        List<String> target = new ArrayList<>();
        target.add("templateName");
        target.add("realName");
        SelectCommand fetchAttrComm = new SelectCommand(target, "VariableName");
        List<List<String>> res = fetchAttrComm.selectHandle();
        List<String> formulatedRes = new ArrayList<String>();
        for (List<String> row : res) {
            if (!row.isEmpty()) {
                String templateName = row.get(0);
                String attrName = row.get(1);
                formulatedRes.add(templateName + " -- " + attrName);
            }
        }
        return formulatedRes;
    }


    /*public static HashMap<String, List<String>> fetchAttributeNames() throws SelectException {
        List<String> target = new ArrayList<>();
        target.add("templateName");
        target.add("realName");
        SelectCommand fetchAttrComm = new SelectCommand(target, "VariableName");
        List<List<String>> res = fetchAttrComm.selectHandle();

        HashMap<String, List<String>> formulatedRes = new HashMap<>();
        for (List<String> row : res) {
            if (!row.isEmpty()) {
                String templateName = row.get(0);
                String attrName = row.get(1);
                if(formulatedRes.containsKey(templateName)){
                    formulatedRes.get(templateName).add(attrName);
                } else {
                    formulatedRes.put(templateName, new ArrayList<String>(Arrays.asList(attrName)));
                }
            }
        }
        return formulatedRes;
    }*/



    public static void main(String[] agv) throws SelectException {
//		List<String> tableNames = fetchTemplateTableName();
        List<String> attrNames = fetchAttributeNames();

        System.out.println(attrNames);
    }
}