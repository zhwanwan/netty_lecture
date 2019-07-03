package mytest;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * @author zhwanwan
 */
@XmlRootElement
public class KeyFlexFilter {

    @XmlElement
    private String keyFlexfieldcode;
    @XmlElement
    private String structureInstanceCode;
    @XmlElement
    private String applicationShortName;
    @XmlElement(name = "filterCriteriaRow")
    private List<FilterCriteriaRow> filterCriteriaRows;

    public KeyFlexFilter() {
    }

    public KeyFlexFilter(String keyFlexfieldcode, String structureInstanceCode,
                         String applicationShortName, List<FilterCriteriaRow> filterCriteriaRows) {
        this.keyFlexfieldcode = keyFlexfieldcode;
        this.structureInstanceCode = structureInstanceCode;
        this.applicationShortName = applicationShortName;
        this.filterCriteriaRows = filterCriteriaRows;
    }
}
