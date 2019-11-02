package com.lti;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/books")
public class BookService {
	
	@Path("/single")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public Book getBook()
	{
		Book b=new Book();
		b.setId(1);
		b.setName("Head First Java");
		b.setIsbn("ISBN-000-1111");
		b.setAuthor("Kathy Sierra");
		b.setPrice(2000.45f);
		
		return b;
	}
	
	@Path("/singleJson")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Book getBookJ()
	{
		Book b=new Book();
		b.setId(1);
		b.setName("Head First Java");
		b.setIsbn("ISBN-000-1111");
		b.setAuthor("Kathy Sierra");
		b.setPrice(2000.45f);
		
		return b;
	}
	
	@Path("/list")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public List<Book> getBookList()
	{
		ArrayList<Book> list=new ArrayList<>();
		
		Book b=new Book();
		b.setId(1);
		b.setName("Head First Java");
		b.setIsbn("ISBN-000-1111");
		b.setAuthor("Kathy Sierra");
		b.setPrice(2000.45f);
		
		list.add(b);
		
		
		Book b1=new Book();
		b1.setId(2);
		b1.setName("Head First C");
		b1.setIsbn("ISBN-000-1112");
		b1.setAuthor("Balaguruswami");
		b1.setPrice(1234.45f);
		
		list.add(b1);
		
		return list;
	}
	
	
	
}
