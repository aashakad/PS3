package pkgEmpty;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import pkgLibrary.Book;
import pkgLibrary.BookException;
import pkgLibrary.Catalog;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
public class TestCatalog {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetBook() {
		Catalog cat = null;
		//Reads XML File onto Cat
		String basePath = new File("").getAbsolutePath();
		basePath = basePath + "\\src\\main\\resources\\XMLFiles\\Books.xml";
		File file = new File(basePath);

		System.out.println(file.getAbsolutePath());
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Catalog.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			cat = (Catalog) jaxbUnmarshaller.unmarshal(file);
			System.out.println(cat);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		//Searches for book with book id "bk101" which we know exists in cat
        Book bFound = cat.GetBook("bk101");
		
        //Checks if the id of the found book is the same as the id we were searching for
		assertEquals("bk101", bFound.getId());	
		
	}
	
	@Test
	public void testAddBook() {
		
		Catalog cat = null;
		//Reads XML File onto Cat
		String basePath = new File("").getAbsolutePath();
		basePath = basePath + "\\src\\main\\resources\\XMLFiles\\Books.xml";
		File file = new File(basePath);

		System.out.println(file.getAbsolutePath());
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Catalog.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			cat = (Catalog) jaxbUnmarshaller.unmarshal(file);
			System.out.println(cat);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		
		//Creates the book to be added
		Date date = new Date();
		Book bAdd= new Book("bk300", "JK ROWLING", "HP", "Fanatsy", 56.0, date, "A masterpiece", 44.8);
		
		//Adds book
		try{
			cat.AddBook(cat.getId(), bAdd);
		} catch (BookException b) {
			System.out.println("Book already exists/ADD FAILED");
		}
		
		//Finds the last book in the catalog and checks if it matches the one just added
		int indexLast =cat.getBooks().size() - 1;
		assertEquals(bAdd, cat.getBooks().get(indexLast));
	}
	

}
