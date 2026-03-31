package com.vincematolka.invoicestreams;
import java.util.Comparator;
import java.util.List;

public class FunctionalProgramming {

    public static void main(String[] args) {
        // TODO code application logic here
        // create the ArrayList of Invoices
        List<Invoice> invoices = List.of(
        new Invoice(83,"Electric sander", 7, 57.98),
        new Invoice(24,"Power saw", 18, 99.99),
        new Invoice(7,"Sledge hammer", 11, 21.50),
        new Invoice(77,"Hammer", 76, 11.99),
        new Invoice(39,"Lawn mower", 3, 79.50),
        new Invoice(68,"Screw driver", 106, 6.99),
        new Invoice(56,"Jig saw", 21, 11.00),
        new Invoice(3,"Wrench", 34, 7.50));
        
        //Display the table of invoices using Invoice toString().
        //Print table header.
        System.out.println("Part number\tPart description\tQuantity\tPrice per item\tValue");
        invoices.stream()
                .forEach(System.out::print);
        
        //a)Use streams to sort Invoice object by partDecsription, then display the results.
        System.out.println("\n\nInvoices Sorted by Part Description:\n");
        System.out.println("Part number\tPart description\tQuantityPrice per item\tValue"); 
        invoices.stream()
                        .sorted(Comparator.comparing(Invoice::getPartDescription))
                        .forEach(System.out::print);
        
        //b)Use streams to sort Invoice object by price, then display the results.
        System.out.println("\n\nInvoices Sorted by Price:\n");
        System.out.println("Part number\tPart description\tQuantity\tPrice per item\tVatue");
        invoices.stream()
                        .sorted(Comparator.comparing(Invoice::getPricePerItem))
                        .forEach(System.out::print);
        
        //c)Use streams to map each Invoice to its partDescription and quantity, 
        //  then display the results.
        System.out.println("\n\nPart Description and Quantity for each Invoice:\n");
        System.out.println("Part description\tQuantity");
        invoices.stream()
                        .sorted(Comparator.comparing(Invoice::getQuantity))
                        .map(x -> String.format("%-16s\t%8d\n", x.getPartDescription(),x.getQuantity()))
                        .forEach(System.out::print);
    }
}
