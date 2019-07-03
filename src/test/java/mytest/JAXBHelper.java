package mytest;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Arrays;

/**
 * @author zhwanwan
 * @create 2019-07-02 7:35 PM
 */
public class JAXBHelper {


    public static String buildFndFilter(FndFilter fndFilter) {
        String result = "";
        try (StringWriter sw = new StringWriter()) {
            sw.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
            JAXBContext jaxbContext = JAXBContext.newInstance(FndFilter.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
            marshaller.marshal(fndFilter, sw);
            result = sw.toString();
        } catch (JAXBException | IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) {
        FilterCriteriaItem item1 = new FilterCriteriaItem("1", "2", "3", "4", "5", "6");
        FilterCriteriaItem item2 = new FilterCriteriaItem("1", "2", "3", "4", "5", "6");
        FilterCriteriaRow row1 = new FilterCriteriaRow(item1, "AND");
        FilterCriteriaRow row2 = new FilterCriteriaRow(item2, "OR");
        KeyFlexFilter key = new KeyFlexFilter("1", "2", "3", Arrays.asList(row1, row2));
        FndFilter fndFilter = new FndFilter(key);
        System.out.println(buildFndFilter(fndFilter));

    }

}
