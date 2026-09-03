import java.util.*;

public class LibrarySystem {
    Scanner sc= new Scanner(System.in);
    int BookId, avlBook,TotalBook;
    String BookName="",BookAuthor="",BookCat="";
    
    
    

    void addBook(){
        System.out.println("Add Book Details");

        System.out.println("Enter your Book id");
        BookId=sc.nextInt();

        if(BookId > 0){
            System.out.println("Enter your Book Name");
            sc.nextLine();
            BookName=sc.nextLine();
            System.out.println("Enter your Book Author");
            BookAuthor=sc.nextLine();

            System.out.println("Enter your Book category");
            BookCat=sc.nextLine();
            System.out.println("Enter your Book Total");
            TotalBook=sc.nextInt();
            avlBook=TotalBook;
            System.out.println("Book added successfully");
        }
        else{
            System.out.println("Invalid Book ID");
        }
    }

    void mainMenu(){
        System.out.println("Library management System");
       
        System.out.println("1.Add Book");
         System.out.println("2.View Book");
          System.out.println("3.Search Book");
           System.out.println("4.Issue Book");
            System.out.println("5.Return Book");
             System.out.println("6.Delete book");
              System.out.println("7.Library Report");
               System.out.println("8.Exit");
        
    }
    public static void main(String[]args){
        LibrarySystem obj=new LibrarySystem();
        int choice;
        do{
            obj.mainMenu();
            System.out.println("Enter Choice");
            choice=obj.sc.nextInt();
            switch(choice){
                case 1:
                    obj.addBook();
                    break;
                    case 8:
                        System.out.println("Exit");
                        break;
                        default:
                            System.out.println("Invalid Choice");
            }
        }
        while(choice != 8);

    }
}