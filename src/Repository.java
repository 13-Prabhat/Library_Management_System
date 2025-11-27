import java.util.ArrayList;
import java.util.List;

public class Repository<T> {
    protected List<T> items = new ArrayList<>();

    public void add(T t){ items.add(t); }
    public List<T> all(){ return items; }
    public int size(){ return items.size(); }
}
