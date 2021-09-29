package bookMS;
import java.io.*;
import java.util.*;

class Book{
	String bookName;
	String author;
	String category;
	String borrower;
	float price;
	boolean checked;
	public Book(String bookname,String author,String category,float price)
	{
		this.bookName = bookname;
		this.author = author;
		this.category = category;
		this.borrower = null;
		this.price = price;
		this.checked = true;
	}
	public Book(String bookname,float price)
	{
		this.bookName = bookname;
		this.author = "Anonymous author";
		this.category = "Others";
		this.borrower = null;
		this.price = price;
		this.checked = true;
	}
	public Book()
	{
		this.bookName = null;
		this.author = null;
		this.category = null;
		this.borrower = null;
		this.price = 0;
		this.checked = true;
	}
	public boolean lend(String borrower)
	{
		if(this.borrower==null)
		{
			this.borrower = borrower;
			return true;
		}
		else
		{
			return false;
		}
	}
	public void returnBook()
	{
		this.borrower = null;
	}
}
class Borrower
{
	String account;
	String name;
	String password;
	int recordTotal;
	int recordNow;
	int recordCredit;
	Vector<String> bookRecord = new Vector<String>();
	public Borrower(String account,String name,String password)
	{
		this.account = account;
		this.name = name;
		this.password = password;
		this.recordTotal = 0;
		this.recordNow = 0;
		this.recordCredit =0;
	}
	public Borrower()
	{
		this.account = null;
		this.name = null;
		this.password = null;
		this.recordTotal = 0;
		this.recordNow = 0;
		this.recordCredit =0;
	}
	public void setRcT(int num)
	{
		this.recordTotal = num;
	}
	public void setRcN(int num)
	{
		this.recordNow = num;
	}
	public void setRcC(int num)
	{
		this.recordCredit = num;
	}
	public void addRcB(String bookRC)
	{
		this.bookRecord.add(bookRC);
	}
}
public class BMS {
	
	public static void main(String[] args) 
	{
		int startNum,optionNum,LogIndex=-1;
		
		Scanner key = new Scanner(System.in);
		LinkedList<Book>  Lib = new LinkedList<Book>();
		LinkedList<Borrower> acList = new LinkedList<Borrower>();
		
		Lib.add(new Book("Gone with the wind","Margaret","Novel",10));
		Lib.add(new Book("The Adventures of Tom Sawyer","Mark Twain","Novel",20));
		Lib.add(new Book("Time vol.2000","Nancy Gibbs","Magzine",30));
		Lib.add(new Book("Attack on Titan vol.9","Isayama","Manga",10));
		Lib.add(new Book("Absolute Java","Dr.Pearson","Text Book",30));
		
		acList.add(new Borrower("Admin","Administrator","admin0000"));
		do
		{
			do
			{
				System.out.println("===============================================================");
				System.out.println("Welcome to use Book Management System v1.0 !");
				System.out.println("[1] Register an account.");
				System.out.println("[2] Just Log in.");
				System.out.println("[3] Exit.");
				System.out.println("===============================================================");
				System.out.println("Press Number for your choice: ");
				startNum = key.nextInt();key.nextLine();
				System.out.println("===============================================================");
				if(startNum==1)
				{
					int checkAc;
					Borrower newOne = new Borrower();
					
					
					System.out.println("Before You Start to use, you need to register an account.");
					System.out.println("Please input your Account: ");
					newOne.account = key.nextLine();
					
					do
					{
						checkAc=1;
						for(int i=0;i<acList.size();i++)
						{
							
							if(newOne.account.equals(acList.get(i).account))
							{
								checkAc=0;
							}
							
						}
						if(checkAc==0)
						{
							System.out.println("The Account is existed,please set your Account again: ");
							newOne.account = key.nextLine();
						}
					}while(checkAc==0);
					
					System.out.println("Please input your Name: ");
					newOne.name = key.nextLine();
					System.out.println("Please input your Password: ");
					newOne.password = key.nextLine();
					acList.add(newOne);
					System.out.println("Registering is Succeed !");
				}
				else if(startNum==2)
				{
					int checkLog;
					String temp_AC,temp_PW;
					System.out.println("Please input your Account: ");
					temp_AC = key.nextLine();
					System.out.println("Please input your Password: ");
					temp_PW = key.nextLine();
					do
					{
						checkLog=0;
						for(int i=0;i<acList.size();i++)
						{
							
							if(temp_AC.equals(acList.get(i).account)&&temp_PW.equals(acList.get(i).password))
							{
								checkLog=1;
								LogIndex=i;
							}
							
							
						}
						if(checkLog==0)
						{
							System.out.println("The Account or Password is incorrect,please input again.");
							System.out.println("Please input your Account: ");
							temp_AC = key.nextLine();
							System.out.println("Please input your Password: ");
							temp_PW = key.nextLine();
						}
					}while(checkLog==0);
					System.out.println("Log-in Now !");
				}
			}while(startNum==1);
			if(startNum!=3)
			{
				do
				{
					System.out.println("===============================================================");
					System.out.println("Please choose one of follow function of Book Management System :");
					System.out.println("===============================================================");
					System.out.println("[1] Search the information of Book.");
					System.out.println("[2] Just quick checkout the Book List.");
					System.out.println("[3] Add a new Book.                 (***Admin Only !***)");
					System.out.println("[4] Delete a Book.                  (***Admin Only !***)");
					System.out.println("[5] Modify information of a Book.   (***Admin Only !***)");
					System.out.println("[6] Borrow a Book.");
					System.out.println("[7] Return a Book.");
					System.out.println("[8] View My Account.");
					System.out.println("[0] Log Out.");
					System.out.println("===============================================================");
					System.out.println("Press Number for your choice: ");
					optionNum = key.nextInt();
					key.nextLine();
					System.out.println("===============================================================");
					if(optionNum==1)
					{
						String temp_Name;
						boolean exist = false;
						
						System.out.println("[1] Search the information of Book.");
						System.out.println("");
						System.out.println("Please input name of book:");
						temp_Name = key.nextLine();
						
						for(int i=0;i<Lib.size();i++)
						{
							if(temp_Name.equals(Lib.get(i).bookName))
							{
								exist = true;
								System.out.println("<-Information-> ");
								System.out.println("Name: "+Lib.get(i).bookName);
								System.out.println("Author: "+Lib.get(i).author);
								System.out.println("Category: "+Lib.get(i).category);
								System.out.println("Price: "+Lib.get(i).price);
								System.out.println("Borrower: "+Lib.get(i).borrower);
							}
						}
						if(exist==false)
						{
							System.out.println("BOOK IS NOT FOUND !");
						}
					}
					else if(optionNum==2)
					{
						int inLib=Lib.size(),outLib=0;
						
						System.out.println("[2] Just quick checkout the Books.");
						System.out.println("");
						System.out.println("The list of all Book. ");
						System.out.println("===============================================================");
						System.out.println("<Name>                <Category>                 <Borrower>");
						
						for(int i=0;i<Lib.size();i++)
						{
							System.out.println("<"+Lib.get(i).bookName+">_____<"+Lib.get(i).category+">_____<"+Lib.get(i).borrower+">");
							System.out.println("");
							if(Lib.get(i).borrower!=null)
							{
								inLib--;
								outLib++;
							}
						}
						System.out.println("===============================================================");
						System.out.println("Number of total Books: "+Lib.size());
						System.out.println("Number of Books in Library: "+inLib);
						System.out.println("Number of Books were borrowed: "+outLib);
					}
					else if(optionNum==3)
					{
						Book aBook = new Book();
						if(LogIndex!=0)
						{
							System.out.println("ERROR : You are not administrator.");
						}
						else
						{
							System.out.println("[3] Add a new book.");
							System.out.println("");
							System.out.println("Please input Information of book.");
							System.out.println("Name: ");
							aBook.bookName = key.nextLine();
							System.out.println("Author: ");
							aBook.author = key.nextLine();
							System.out.println("Category: ");
							aBook.category = key.nextLine();
							System.out.println("Price: ");
							aBook.price = key.nextInt();
							Lib.add(aBook);
							key.nextLine();
						}
					}
					else if(optionNum==4)
					{
						String temp_Name;
						boolean exist = false;
						if(LogIndex!=0)
						{
							System.out.println("ERROR : You are not administrator.");
						}
						else
						{
							System.out.println("[4] Delete a Book.");
							System.out.println("");
							System.out.println("Please input name of book you want to remove.");
							System.out.println("Name: ");
							temp_Name = key.nextLine();
							
							for(int i = 0;i<Lib.size();i++)
							{
								if(temp_Name.equals(Lib.get(i).bookName))
								{
									exist = true;
									System.out.println("The book <"+temp_Name+"> has been removed.");
									Lib.remove(i);
								}
							}
							if(exist==false)
							{
								System.out.println("BOOK IS NOT FOUND !");
							}
						}
					}
					else if(optionNum==5)
					{
						int checkMod = 0;
						String temp_Name;
						Book temp_Book = new Book();
						boolean exist = false;
						if(LogIndex!=0)
						{
							System.out.println("ERROR : You are not administrator.");
						}
						else
						{
							System.out.println("[5] Modify information of a Book.");
							System.out.println("");
							System.out.println("Please input the name of book you want to modify: ");
							temp_Name = key.nextLine();
							
							for(int i = 0;i<Lib.size();i++)
							{
								if(temp_Name.equals(Lib.get(i).bookName))
								{
									exist = true;
									System.out.println("Current Name: "+Lib.get(i).bookName+".");
									System.out.println("New Name: ");
									temp_Book.bookName = key.nextLine();
									
									System.out.println("Current Author: "+Lib.get(i).author+".");
									System.out.println("New Author: ");
									temp_Book.author = key.nextLine();
									System.out.println("Current Category: "+Lib.get(i).category+".");
									System.out.println("New Category: ");
									temp_Book.category = key.nextLine();
									System.out.println("Current Price: "+Lib.get(i).price+".");
									System.out.println("New Price: ");
									temp_Book.price = key.nextInt();key.nextLine();
									
									
									System.out.println("Are you sure to change the data above ? (Yes: 1 / No: 0)");
									checkMod = key.nextInt();key.nextLine();
									if(checkMod==1)
									{
										Lib.set(i, temp_Book);
									}
								}
							}
							if(exist==false)
							{
								System.out.println("BOOK IS NOT FOUND !");
							}
						}
					}
					else if(optionNum==6)
					{
						
						String temp_Name,temp_Borrower = acList.get(LogIndex).name;
						boolean exist = false;
						
						System.out.println("[6] Borrow a Book.");
						System.out.println("");
						System.out.println("Please input the name of book you want to borrow: ");
						temp_Name = key.nextLine();
						
						
						for(int i = 0;i<Lib.size();i++)
						{
							if(temp_Name.equals(Lib.get(i).bookName))
							{
								exist = true;
								if(Lib.get(i).borrower==null)
								{
									Lib.get(i).lend(temp_Borrower);
									acList.get(LogIndex).setRcT(acList.get(LogIndex).recordTotal+1);
									acList.get(LogIndex).setRcN(acList.get(LogIndex).recordNow+1);
									acList.get(LogIndex).addRcB(temp_Name);
									System.out.println("Borrowing is Succeed ! Enjoy your Reading !");
									//Lib.set(i,new Book(Lib.get(i).bookName,Lib.get(i).author,Lib.get(i).category,Lib.get(i).price).lend(temp_Borrower));
								}
								else
								{
									System.out.println("Borrowing isn't Succeed ! The book has been borrowed by other.");
									System.out.println("Current Borrower: "+Lib.get(i).borrower);
								}
							}
						}
						if(exist==false)
						{
							System.out.println("BOOK IS NOT FOUND !");
						}
					}
					else if(optionNum==7)
					{
						String temp_Name;
						boolean exist = false;
						
						System.out.println("[7] Return a Book.");
						System.out.println("");
						System.out.println("Please input the name of book you want to return: ");
						temp_Name = key.nextLine();
						
						for(int i = 0;i<Lib.size();i++)
						{
							if(temp_Name.equals(Lib.get(i).bookName))
							{
								exist = true;
								
								Lib.get(i).returnBook();
								acList.get(LogIndex).setRcN(acList.get(LogIndex).recordNow-1);
								acList.get(LogIndex).setRcC(acList.get(LogIndex).recordCredit+1);
								System.out.println("Book returning is Succeed ! Thanks your Reading !");
							}
						}
						if(exist==false)
						{
							System.out.println("BOOK IS NOT FOUND !");
						}
					}
					else if(optionNum==8)
					{
						System.out.println("[8] View My Account.");
						System.out.println("");
						System.out.println("===============================================================");
						System.out.println("Account: "+acList.get(LogIndex).account);
						System.out.println("Name: "+acList.get(LogIndex).name);
						System.out.println("Password: "+acList.get(LogIndex).password);
						System.out.println("Total times of Borrow Books : "+acList.get(LogIndex).recordTotal);
						System.out.println("Total Books on hand: "+acList.get(LogIndex).recordNow);
						System.out.println("Total Books Credit Point: "+acList.get(LogIndex).recordCredit);
						System.out.println("===============================================================");
						System.out.println("Borrow Record: ");
						System.out.println("===============================================================");
						System.out.println("<Name>");
						for(int i = 0;i<acList.get(LogIndex).bookRecord.size();i++)
						{
							System.out.println("<"+acList.get(LogIndex).bookRecord.get(i)+">");
						}
					}
					else
					{
						startNum=-1;
					}
					
				}while(optionNum!=0);
			}
		}while(startNum!=3);
		
		key.close();
	}

}
