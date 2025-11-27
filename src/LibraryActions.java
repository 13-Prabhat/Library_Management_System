public interface LibraryActions<T> {
    void add(T item) throws Exception;
    void update(T item) throws Exception;
    void delete(int id) throws Exception;
    T find(int id) throws Exception;
}
