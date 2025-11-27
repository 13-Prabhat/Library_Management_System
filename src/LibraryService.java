import java.time.LocalDate;
import java.util.List;

public class LibraryService {
    private BookDAO bookDAO = new BookDAO();
    private MemberDAO memberDAO = new MemberDAO();
    private TransactionDAO txnDAO = new TransactionDAO();

    // high-level operations that use DAOs
    public void addSampleData() {
        try {
            bookDAO.add(new Book(1, "Effective Java", "Joshua Bloch"));
            bookDAO.add(new Book(2, "Clean Code", "Robert C. Martin"));
            bookDAO.add(new Book(3, "Head First Java", "Kathy Sierra"));

            memberDAO.add(new Member(1, "Alice"));
            memberDAO.add(new Member(2, "Bob"));
        } catch (Exception e){
            System.out.println("Sample data may already exist: " + e.getMessage());
        }
    }

    public List<Book> listBooks() throws Exception { return bookDAO.all(); }
    public List<Member> listMembers() throws Exception { return memberDAO.all(); }
    public List<Transaction> listTransactions() throws Exception { return txnDAO.all(); }

    public void issueBook(int bookId, int memberId) throws Exception {
        Book b = bookDAO.find(bookId);
        if (b==null) throw new Exception("Book not found");
        if (!b.isAvailable()) throw new Exception("Book is currently issued");
        Transaction t = new Transaction(0, bookId, memberId, LocalDate.now(), LocalDate.now().plusDays(14));
        txnDAO.add(t);
    }

    public void returnBook(int txnId) throws Exception {
        Transaction t = txnDAO.find(txnId);
        if (t==null) throw new Exception("Transaction not found");
        t.setReturnDate(LocalDate.now());
        txnDAO.update(t);
    }
}
