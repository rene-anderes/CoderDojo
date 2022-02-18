package org.anderes.edu.xml.xpath;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.InputStream;

import javax.xml.xpath.XPathExpressionException;

import org.anderes.edu.xml.xpath.XPathEvaluator;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XPathQuery {
    
    private InputStream xmlInputStream;
    
    @Before
    public void setup() {
        xmlInputStream = XPathQuery.class.getResourceAsStream("/xpath/food.xml");
    }

    /**
     * Es sollen die Knoten "food" selektiert werden die im "name" den Begriff "Waffles" haben
     * 
     * @throws XPathExpressionException
     */
    @Test
    public void selectFoodWithDescriptionWaffles() throws XPathExpressionException {
        
        // when
        final NodeList list = XPathEvaluator.withFile(xmlInputStream ).getNodeList("//food[contains(name, \"Waffles\")]");
        
        // then
        assertThat(list.getLength(), is(3));
    }
    
    /**
     * Es sollen die Knoten "food" slektiert werden, die dessen "price" zwischen 5 und 8 liegen
     */
    @Test
    public void selectFoodWithPriceBetween() throws XPathExpressionException {
      
        // when
        final NodeList list = XPathEvaluator.withFile(xmlInputStream ).getNodeList("//food[price > 5 and price < 8]");
        
        // then
        assertThat(list.getLength(), is(3));
        
        System.out.println("----------------------------------------------------------------------------------------");
        System.out.println("Es sollen die Knoten 'food' slektiert werden, die dessen 'price' zwischen 5 und 8 liegen");
        System.out.println("----------------------------------------------------------------------------------------");
        for (int i = 0; i < list.getLength(); i++) {
            Node node = list.item(i);
            XPathEvaluator.dumpNode(node);
        }
        System.out.println("----------------------------------------------------------------------------------------");
    }
    
    /**
     * Es sollen die "description" Elemente selektiert werden deren Menüs ("food"), die weniger als 900 Kalorien ("calories")" haben.
     */
    @Test
    public void selectDescriptionByCaloriesLt900() throws XPathExpressionException {
        
        // when
        final NodeList list = XPathEvaluator.withFile(xmlInputStream ).getNodeList("//food[calories < 900]/description");
        
        // then
        assertThat(list.getLength(), is(2));
    }
}
