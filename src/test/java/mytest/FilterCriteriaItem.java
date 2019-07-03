package mytest;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author zhwanwan
 */
@XmlRootElement
public class FilterCriteriaItem {

    public FilterCriteriaItem() {
    }

    public FilterCriteriaItem(String attributeName, String columnName,
                              String operator, String conjunction, String valueDataType, String value) {
        this.attributeName = attributeName;
        this.columnName = columnName;
        this.operator = operator;
        this.conjunction = conjunction;
        this.valueDataType = valueDataType;
        this.value = value;
    }

    @XmlElement
    private String attributeName;
    @XmlElement
    private String columnName;
    @XmlElement
    private String operator;
    @XmlElement
    private String conjunction;
    @XmlElement
    private String valueDataType;
    @XmlElement
    private String value;

}
