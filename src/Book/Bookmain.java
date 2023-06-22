package Book;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

public class Bookmain {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		 int bid,year;
		 String Title,author,status="Available";
		 int num;
		 boolean value=true;
		 ArrayList<book> b = new ArrayList<>();
		 ArrayList<book> b1 = new ArrayList<>();
		try {
		
		String url = "jdbc:mysql://localhost:3306/spec",
				user = "root", password = "root";
		String sql;
		int res;
		
		Connection conn = DriverManager.getConnection(url, user, password);
		//Checking if connection was successful
		if(!conn.isClosed()) {
			System.out.println("DB Connection Success");
		}
		
		//Creating a Statement to execute Queries
		Statement stmt = conn.createStatement();
		
		sql = "SELECT * FROM book_data";
			ResultSet bookData = stmt.executeQuery(sql);
			int j = 0;
		
		
		while(bookData.next()) {
			bid = bookData.getInt("bid");
			author = bookData.getString("author");
			Title = bookData.getString("Title");
			year = bookData.getInt("year");
			status = bookData.getString("status");
			b.add(new book(bid,author,Title,year,status));
			b1.add(new book(bid,author,Title,year,status));

			
		}
		
		 
		
//			 book[] b=new book[num];
			 


		 
		// TODO Auto-generated method stub
			System.out.println("Welcome to book management system ");
			do {
				value=true;
				 boolean borrow=true,Return=true;

				System.out.println("1.Add Book");
				System.out.println("2.Show all Books");
				System.out.println("3.Show Available Books");
				System.out.println("4.Borrow Book");
				System.out.println("5.Return Book");
				System.out.println("6.Exit\n");
				System.out.println("Enter your choice");
				 int choice=sc.nextInt();
				 
				 
				 switch(choice) {
				 
				 case 1:
			
						System.out.println("Enter Book id: ");
						bid=sc.nextInt();
						System.out.println("Enter author: ");
						author=sc.next();
						System.out.println("Enter Title: ");
						Title=sc.next();
						System.out.println("ENter publish Year of the book: ");
						year=sc.nextInt();
						
						sql = "INSERT INTO book_data (bid,author,Title,year,status) VALUES ('" + bid + "','" + author + "','" + Title+"','"+ year+"','"+ status+ "')";
						res = stmt.executeUpdate(sql);
						
						b.add(new book(bid,author,Title,year,status));
						b1.add(new book(bid,author,Title,year,status));
//						book j =new book(bid,author,Title,year);
				

						System.out.println("Book Added Succesfully !!!");
//						b[i].showallbooks();
					 

					 break;
				 case 2:
					 System.out.println("ID \tauthor \tTitle \tPublish year \tStatus ");
					 for(int i = 0; i < b.size(); i++) {
						 b.get(i).showallbooks();	
						}
					 break;
				 case 3:
					 
					 System.out.println("ID \tauthor \tTitle \tPublish year \tStatus ");
					 for(int i = 0; i < b1.size(); i++) {
						 
						 if(b.get(i).status.equalsIgnoreCase("Available")) {
						 b1.get(i).showallbooks();	
						 }
						  
						}
					 
					 
					 break;
				 case 4:
					 System.out.println("Enter Book id: ");
						bid=sc.nextInt();
					
					 for(int i = 0; i < b1.size(); i++) {
						if( b1.get(i).getbid()==bid)	{
							System.out.println("Book Borrowed Succesfully !!!");

							 System.out.println("ID \tauthor \tTitle \tPublish year \tStatus ");
							 b1.get(i).showallbooks();
							 b1.remove(i);
							 status="not Available";
							 b.get(i).status(status);
							borrow=false;
							
							sql = "UPDATE book_data SET status = 'not Available' WHERE bid = '" + bid + "'";
							res = stmt.executeUpdate(sql);
						}
						}
					 if(borrow) {
						 System.out.println("Book not available ");

					 }
					 break;
				 case 5:
					 System.out.println("Enter Book id: ");
						bid=sc.nextInt();
					
						
					 for(int i = 0; i < b.size(); i++) {
						
						if( b.get(i).getbid()==bid)	{
							 if(b.get(i).status.equalsIgnoreCase("not Available")) {
								 sql = "UPDATE book_data SET status ='Available' WHERE bid = '" + bid + "'";
									res = stmt.executeUpdate(sql);
								 
							 System.out.println("Book Returned Successfully ");

							 System.out.println("ID \tauthor \tTitle \tPublish year \tStatus ");
							 b1.add(b.get(i));
							b.get(i).showallbooks();
							Return=false;
							status="Available";
							 b.get(i).status(status);
							 }
							 else {
									System.out.println("Book already returnde ");
								}
							 
						}
					 
						
							
							
						
						}
					 if(Return) {
						 System.out.println("Book not form our library available ");

					 }
					 break;
				 case 6:
						System.out.println("Thankyou for using application !!!");
						value=false;
					 break;
				 }

			}while(value);
		}
		catch(Exception e) {
			System.out.println("ERROR!! \nMessage:\n" + e.getMessage()) ;
		}

	}

}
