package jaxb.example;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Arrays;

/**
 * @author zhwanwan
 * @create 2019-07-02 5:06 PM
 */
public class JAXBExample {
    public static void main(String[] args) {

        Address address1 = new Address("China", "Jiangsu", "Suzhou");
        Address address2 = new Address("China", "Jiangsu", "Nanjing");

        Customer customer = new Customer("华为研发", 30, 20, Arrays.asList(address1, address2));

        /*customer.setId(100);
        customer.setName("oracle");
        customer.setAge(29);*/

        try (StringWriter sw = new StringWriter()) {

            sw.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");

//            File file = new File("C:\\file.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(Customer.class);
            Marshaller marshaller = jaxbContext.createMarshaller();

            // output pretty printed
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
            /*marshaller.setProperty("com.sun.xml.bind.xmlHeaders",
                    "<?xml version=\"1.0\" encoding=\"UTF-8\"?>");*/

            //jaxbMarshaller.marshal(customer, file);
            //marshaller.marshal(customer, System.out);
            //System.out.println("--------------------------");
            marshaller.marshal(customer, sw);
            String xmlString = sw.toString();
            System.out.println(xmlString);

        } catch (JAXBException | IOException e) {
            e.printStackTrace();
        }

    }
}
