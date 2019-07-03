package mytest;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author zhwanwan
 */
@XmlRootElement
public class FilterCriteriaRow {
    @XmlElement
    private FilterCriteriaItem filterCriteriaItem;
    @XmlElement
    private String conjunction;

    public FilterCriteriaRow() {
    }

    public FilterCriteriaRow(FilterCriteriaItem filterCriteriaItem, String conjunction) {
        this.filterCriteriaItem = filterCriteriaItem;
        this.conjunction = conjunction;
    }
}
