package com.mkyong.xml.jaxb.model;

import com.mkyong.xml.jaxb.model.Fruit;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;

import java.io.File;

public class JaxbExampleFruit2 {

    public static void main(String[] args) {

        JAXBContext jaxbContext = null;
        try {

            // Normal JAXB RI
            //jaxbContext = JAXBContext.newInstance(Fruit.class);

            // EclipseLink MOXy needs jaxb.properties at the same package with Fruit.class
            // Alternative, I prefer define this via eclipse JAXBContextFactory manually.
            jaxbContext = org.eclipse.persistence.jaxb.JAXBContextFactory
                .createContext(new Class[]{Fruit.class}, null);

            File file = new File("C:\\test\\fruit.xml");

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            Fruit o = (Fruit) jaxbUnmarshaller.unmarshal(file);

            System.out.println(o);

        } catch (JAXBException e) {
            e.printStackTrace();
        }

    }

}
