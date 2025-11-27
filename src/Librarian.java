public class Librarian extends User {
    private String shift;

    public Librarian(int id, String name, String shift){
        super(id, name);
        this.shift = shift;
    }

    public String getShift(){ return shift; }

    public void openLibrary(){
        System.out.println(getName() + " opened the library for shift " + shift);
    }
}
