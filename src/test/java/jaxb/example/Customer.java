package jaxb.example;

import javax.xml.XMLConstants;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * @author zhwanwan
 * @create 2019-07-02 5:05 PM
 */
//namespace = XMLConstants.W3C_XML_SCHEMA_INSTANCE_NS_URI
@XmlRootElement(name = "Customer")
public class Customer {

    @XmlElement(name = "Name")
    private String name;
    @XmlElement(name = "Age")
    private int age;
    //    @XmlAttribute(name = "ID")
    private int id;

    @XmlAttribute(name = "xmlns:xsi")
    private static final String xmlns = XMLConstants.W3C_XML_SCHEMA_INSTANCE_NS_URI;
    @XmlAttribute(name = "xsi:noNamespaceSchemaLocation")
    private static final String nsLocation = "http://www.oracle.com/apps/fnd/applcore/filter/FndFilter.xsd";

    @XmlElement(name = "Address")
    private List<Address> addresses;

    public Customer() {
    }

    public Customer(String name, int age, int id, List<Address> addresses) {
        this.name = name;
        this.age = age;
        this.id = id;
        this.addresses = addresses;
    }

    /*public String getName() {
        return name;
    }

    @XmlElement(name = "Name")
    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    @XmlElement(name = "Age")
    public void setAge(int age) {
        this.age = age;
    }

    public int getId() {
        return id;
    }

    @XmlAttribute(name = "ID")
    public void setId(int id) {
        this.id = id;
    }*/

}