public class Book {
    private int id;
    private String title;
    private String author;
    private boolean available = true;

    public Book(int id, String title, String author){
        this.id=id;
        this.title=title;
        this.author=author;
    }

    public int getId(){ return id; }
    public String getTitle(){ return title; }
    public String getAuthor(){ return author; }
    public boolean isAvailable(){ return available; }
    public void setAvailable(boolean available){ this.available = available; }

    @Override
    public String toString(){
        return id + " - " + title + " by " + author + (available ? " [Available]" : " [Issued]");
    }
}
