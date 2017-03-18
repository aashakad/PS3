package pkgLibrary;

import java.util.ArrayList;

import javax.xml.bind.annotation.*;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)

public class Catalog {

	@XmlAttribute
	int id;

	@XmlElement(name = "book")
	ArrayList<Book> books;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ArrayList<Book> getBooks() {
		return books;
	}

	public void setBooks(ArrayList<Book> books) {
		this.books = books;
	}

	public Book GetBook(String searchId) {
		
		return Book.GetBook(this, searchId);

	}

	public void AddBook(int catId, Book bNew) throws BookException {
		int flag = 0;
		for (Book b : this.getBooks())
			if (b.equals(bNew))
				flag = 1;
		
		if (flag == 0) {
			ArrayList<Book> newList = this.getBooks();
			newList.add(bNew);
			this.setBooks(newList);
		} else
			throw new BookException(this, bNew);
	}
	

}
