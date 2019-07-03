package jaxb.example;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * @author zhwanwan
 * @create 2019-07-02 5:19 PM
 */
@XmlRootElement
public class Address {

    @XmlElement
    private String country;
    @XmlElement
    private String province;
    @XmlElement
    private String city;

    public Address() {
    }

    public Address(String country, String province, String city) {
        this.country = country;
        this.province = province;
        this.city = city;
    }
}
