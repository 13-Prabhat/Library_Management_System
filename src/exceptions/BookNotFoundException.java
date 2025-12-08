package exceptions;

/**
 * Exception thrown when a book is not found in the library database.
 * This exception provides specific error handling for book lookup operations.
 * 
 * @author Library Management System Team
 * @version 1.0
 */
public class BookNotFoundException extends Exception {
    
    private int bookId;
    
    /**
     * Constructs a BookNotFoundException with a book ID.
     * 
     * @param bookId the ID of the book that was not found
     */
    public BookNotFoundException(int bookId) {
        super("Book with ID " + bookId + " not found in the library");
        this.bookId = bookId;
    }
    
    /**
     * Constructs a BookNotFoundException with a custom message.
     * 
     * @param message the detail message
     */
    public BookNotFoundException(String message) {
        super(message);
    }
    
    /**
     * Gets the book ID that caused this exception.
     * 
     * @return the book ID
     */
    public int getBookId() {
        return bookId;
    }
}
