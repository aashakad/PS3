package pkgLibrary;

public class BookException extends Exception{
	
		Catalog cat;
		Book b;
		String BookId;
		
	public BookException(Catalog c, String Id){
		this.cat = c;
		this.BookId = Id;
		this.b = null;
	}
	
	public BookException(Catalog c, Book bk){
		this.cat = c;
		this.b = bk;
		this.BookId = null;
	}

}
