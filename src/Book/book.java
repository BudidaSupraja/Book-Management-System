package Book;

public class book {
	int bid,year;
	String Title,author,status;
	
	public book(int bid,String author,String Title,int year,String status) {
		this.bid=bid;
		this.author=author;
		this.Title=Title;
		this.year=year;
		this.status=status;
		
		
		}
	public void showallbooks(){
		
		System.out.println(this.bid+"\t"+this.author+"\t"+this.Title+"\t"+this.year+"\t"+this.status);
//		System.out.print("vinay");
	}
	public int getbid() {
		return this.bid;
	}
	public void status(String status) {
		this.status=status;
	}
}
