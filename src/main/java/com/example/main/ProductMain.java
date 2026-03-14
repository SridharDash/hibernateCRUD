package com.example.main;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.example.entity.Product;
import com.example.util.HibernateUtil;

public class ProductMain {

    public static void main(String[] args) {

        Session session = HibernateUtil
                .getSessionFactory()
                .openSession();

        Transaction tx = session.beginTransaction();

        
        Product p1 = new Product("Laptop","Gaming Laptop",1200,5);
        Product p2 = new Product("Phone","Android Phone",600,10);

        session.persist(p1);
        session.persist(p2);

      
        Product product = session.get(Product.class,1);
        System.out.println(product.getName());

        
        product.setPrice(1300);
        session.merge(product);

        
     
        Product deleteProduct = session.get(Product.class, 2);

        if (deleteProduct != null) {
            session.remove(deleteProduct);
            System.out.println("Product deleted successfully");
        } else {
            System.out.println("Product not found");
        }

        tx.commit();
        session.close();

        System.out.println("CRUD operations completed");
    }
}