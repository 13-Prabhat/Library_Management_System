import java.time.LocalDate;

public class Transaction {
    private int id;
    private int bookId;
    private int memberId;
    private LocalDate issueDate;
    private LocalDate dueDate;
    private LocalDate returnDate;

    public Transaction(int id, int bookId, int memberId, LocalDate issueDate, LocalDate dueDate){
        this.id = id;
        this.bookId = bookId;
        this.memberId = memberId;
        this.issueDate = issueDate;
        this.dueDate = dueDate;
    }

    public int getId(){ return id; }
    public int getBookId(){ return bookId; }
    public int getMemberId(){ return memberId; }
    public LocalDate getIssueDate(){ return issueDate; }
    public LocalDate getDueDate(){ return dueDate; }
    public LocalDate getReturnDate(){ return returnDate; }
    public void setReturnDate(LocalDate rd){ this.returnDate = rd; }

    @Override
    public String toString(){
        return "Txn " + id + ": Book " + bookId + " -> Member " + memberId + " issued " + issueDate + " due " + dueDate + (returnDate!=null? " returned "+returnDate : "");
    }
}
