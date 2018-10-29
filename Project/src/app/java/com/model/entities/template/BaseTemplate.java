package app.java.com.model.entities.template;

import java.util.List;

public class BaseTemplate extends Template {

    private String templateName;
    private List<String> templateColumnNames;
    private List<String> columnIds;

    public BaseTemplate(String templateName, List<String> templateColumnNames,
                            List<String> columnIds) {
        this.templateName = templateName;
        this.templateColumnNames = templateColumnNames;
        this.columnIds = columnIds;
    }

    public List<String> getTemplateColumnNames() {

        return templateColumnNames;
    }

    public List<String> getColumnIds() {
        return columnIds;
    }

    public String toString() {
        return templateName;
    }
}