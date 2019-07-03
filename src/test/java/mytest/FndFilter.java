package mytest;

import javax.xml.XMLConstants;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author zhwanwan
 */
@XmlRootElement(name = "FndFilter")
public class FndFilter {

    @XmlElement(name = "KeyFlexFilter")
    private KeyFlexFilter keyFlexFilter;

    @XmlAttribute(name = "xmlns:xsi")
    private static final String xmlns = XMLConstants.W3C_XML_SCHEMA_INSTANCE_NS_URI;
    @XmlAttribute(name = "xsi:noNamespaceSchemaLocation")
    private static final String nsLocation = "http://www.xxx.com/apps/fnd/applcore/filter/ABFilter.xsd";

    public FndFilter() {
    }

    public FndFilter(KeyFlexFilter keyFlexFilter) {
        this.keyFlexFilter = keyFlexFilter;
    }
}
