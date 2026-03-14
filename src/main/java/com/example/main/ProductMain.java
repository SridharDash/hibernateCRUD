package com.example.main;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

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
        

        System.out.println("CRUD operations completed");
         tx = session.beginTransaction();

        session.persist(new Product("Tablet","Electronics",400,15));
        session.persist(new Product("Keyboard","Accessories",50,30));
        session.persist(new Product("Mouse","Accessories",25,50));
        session.persist(new Product("Monitor","Electronics",300,10));
        session.persist(new Product("Headphones","Accessories",80,20));

        tx.commit();
        
        List<Product> list = session
        		.createQuery("FROM Product ORDER BY price ASC", Product.class)
        		.list();

        		System.out.println("Price Ascending:");
        		list.forEach(p -> System.out.println(p.getName()+" "+p.getPrice()));
        		
        		
        		List<Product> list2 = session
        				.createQuery("FROM Product ORDER BY price DESC", Product.class)
        				.list();

        				System.out.println("Price Descending:");
        				list2.forEach(p -> System.out.println(p.getName()+" "+p.getPrice()));
        				
        				List<Product> list3 = session
        						.createQuery("FROM Product ORDER BY quantity DESC", Product.class)
        						.list();

        						System.out.println("Highest Quantity:");
        						list3.forEach(p -> System.out.println(p.getName()+" "+p.getQuantity()));
        						
        						Query<Product> query = session.createQuery("FROM Product", Product.class);

        						query.setFirstResult(0);
        						query.setMaxResults(3);

        						List<Product> firstThree = query.list();

        						System.out.println("First 3 Products:");
        						firstThree.forEach(p -> System.out.println(p.getName()));
        						
        						query = session.createQuery("FROM Product", Product.class);

        						query.setFirstResult(0);
        						query.setMaxResults(3);

        						 firstThree = query.list();

        						System.out.println("First 3 Products:");
        						firstThree.forEach(p -> System.out.println(p.getName()));
        						
        						Query<Product> query2 = session.createQuery("FROM Product", Product.class);

        						query2.setFirstResult(3);
        						query2.setMaxResults(3);

        						List<Product> nextThree = query2.list();

        						System.out.println("Next 3 Products:");
        						nextThree.forEach(p -> System.out.println(p.getName()));
        						
        						Long count = session
        								.createQuery("SELECT COUNT(*) FROM Product", Long.class)
        								.uniqueResult();

        								System.out.println("Total Products: "+count);
        						
        					
        								Long count2 = session
        										.createQuery("SELECT COUNT(*) FROM Product WHERE quantity > 0", Long.class)
        										.uniqueResult();

        										System.out.println("Products with stock: "+count2);
        										
        										session.close();
        						
        										
    }
}
        														