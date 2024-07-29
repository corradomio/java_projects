package com.example;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println("Hello world!");

        Reader in = new FileReader("generatedInvoices_v2.csv");
        Iterable<CSVRecord> records = CSVFormat.RFC4180.builder()
            .setHeader()
            .setSkipHeaderRecord(true)
            .build()
            .parse(in);
        for (CSVRecord record : records) {
            String supplierName = record.get(3);
            String invoiceId = record.get(4);
            System.out.printf("%s: %s\n", supplierName, invoiceId);
        }

    }


    // public static void main(String[] args) throws IOException {
    //     System.out.println("Hello world!");
    //
    //     CsvConfiguration config = new CsvConfiguration();
    //     config.setLineBreak("\n");
    //     config.setFieldDelimiter(',');
    //     Deserializer deserializer = CsvIOFactory.createFactory(config, Invoice.class).createDeserializer();
    //     FileReader reader = new FileReader(new File("generatedInvoices_v2.csv"));
    //     deserializer.open(reader);
    //     while (deserializer.hasNext()) {
    //         Invoice i = deserializer.next();
    //         // do something useful with it
    //         System.out.println(i.getInvoiceId());
    //     }
    //     deserializer.close(true);
    // }
}