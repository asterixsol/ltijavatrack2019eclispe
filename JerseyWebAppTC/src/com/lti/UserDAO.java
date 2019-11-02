package com.lti;

import java.sql.*;


import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/users")
public class UserDAO 
{
	DatabaseConnector db=new DatabaseConnector();
	
	
	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public User authenticateUser(User u)
	{
		User user=null;
		String email=u.getEmail();
		String emailD="";
		String password=u.getPassword();
		String passwordD="";
		
		try
		{
			String query="Select email,password from zuser where email='"+email+"'";
			ResultSet rs=db.getResultSet(query);
			
			if(rs.next())
			{
				emailD=rs.getString("email");
				passwordD=rs.getString("password");
				
			}
			
			if(email.equals(emailD) && password.equals(passwordD))
			{
				user=getUserByEmail(emailD);
			}
			else
			{
				user=null;
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			db.closeConnection();
		}
		
		return user;
	}
	
	
	@POST
	@Path("/addUserC")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addUser(User u)
	{
		String result="";
		
		try
		{
			String query="Insert into zuser values (?,?,?,?)";
			PreparedStatement pstmt=db.getPreparedStatement(query);
			pstmt.setInt(1,u.getId());
			pstmt.setString(2, u.getName());
			pstmt.setString(3,u.getEmail());
			pstmt.setString(4, u.getPassword());
			
			int i=pstmt.executeUpdate();
			
			if(i==1)
			{
				result=Results.SUCCESS;
			}
			else
			{
				result=Results.FAILURE;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			result=Results.PROBLEM;
		}
		finally
		{
			db.closeConnection();
		}
		return Response
	            .status(200)
	           // .header("Access-Control-Allow-Origin", "http://localhost:4200")
	            .header("Access-Control-Allow-Headers", "Origin, Content-Type, X-Auth-Token")
	            .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
	          
	            .entity(result)
	            .build();
		
	}
	
	
	@POST
	@Path("/updateUser")
	@Consumes(MediaType.APPLICATION_JSON)
	public String updateUser(User u)
	{
		System.out.println(u);
		String result="";
		
		try
		{
			String query="update zuser set name=?,email=?,password=? where id=?";;
			PreparedStatement pstmt=db.getPreparedStatement(query);
			pstmt.setInt(4,u.getId());
			pstmt.setString(1, u.getName());
			pstmt.setString(2,u.getEmail());
			pstmt.setString(3, u.getPassword());
			
			int i=pstmt.executeUpdate();
			System.out.println(i);
			if(i==1)
			{
				result=Results.SUCCESS;
			}
			else
			{
				result=Results.FAILURE;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			result=Results.PROBLEM;
		}
		finally
		{
			db.closeConnection();
		}
		
		return result;
	}
	
	public String deleteUser(User u)
	{
		String result="";
		
		try
		{
			String query="delete from zuser where id=?";;
			PreparedStatement pstmt=db.getPreparedStatement(query);
			pstmt.setInt(1,u.getId());
			
			int i=pstmt.executeUpdate();
			
			if(i==1)
			{
				result=Results.SUCCESS;
			}
			else
			{
				result=Results.FAILURE;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			result=Results.PROBLEM;
		}
		finally
		{
			db.closeConnection();
		}
		
		return result;
	}
	
	public User getUserByEmail(String email)
	{
		User u=null;
		
		try
		{
			String query="Select * from zuser where email='"+email+"'";
			ResultSet rs=db.getResultSet(query);
			if(rs.next())
			{
				u=new User();
				u.setId(rs.getInt("id"));
				u.setName(rs.getString("name"));
				u.setEmail(rs.getString("email"));
				u.setPassword(rs.getString("password"));
			}
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		
		}
		finally
		{
			db.closeConnection();
		}
		
		return  u;
	}
	
	public User getUserById(int id)
	{
		User u=null;
		
		try
		{
			String query="Select * from zuser where id="+id;
			ResultSet rs=db.getResultSet(query);
			if(rs.next())
			{
				u=new User();
				u.setId(rs.getInt("id"));
				u.setName(rs.getString("name"));
				u.setEmail(rs.getString("email"));
				u.setPassword(rs.getString("password"));
			}
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		
		}
		finally
		{
			db.closeConnection();
		}
		
		return  u;
	}
	
	@Path("/showAllUser")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<User> getAllUsers()
	{
		ArrayList<User> list=new ArrayList<>();
		
		
		try
		{
			String query="Select * from zuser";
			ResultSet rs=db.getResultSet(query);
			while(rs.next())
			{
				User u=new User();
				u.setId(rs.getInt("id"));
				u.setName(rs.getString("name"));
				u.setEmail(rs.getString("email"));
				u.setPassword(rs.getString("password"));
				
				list.add(u);
			}
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		
		}
		finally
		{
			db.closeConnection();
		}
		
		return  list;
	}
	
	
	@Path("/showAllUserC")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllUsersC()
	{
		ArrayList<User> list=new ArrayList<>();
		
		
		try
		{
			String query="Select * from zuser";
			ResultSet rs=db.getResultSet(query);
			while(rs.next())
			{
				User u=new User();
				u.setId(rs.getInt("id"));
				u.setName(rs.getString("name"));
				u.setEmail(rs.getString("email"));
				u.setPassword(rs.getString("password"));
				
				list.add(u);
			}
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		
		}
		finally
		{
			db.closeConnection();
		}
		return Response
	            .status(200)
	           // .header("Access-Control-Allow-Origin", "*")
	            .entity(list)
	            .build();
		
	}





}
