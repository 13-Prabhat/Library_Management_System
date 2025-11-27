import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class MainFrame extends JFrame {
    private LibraryService service = new LibraryService();
    private DefaultListModel<String> bookModel = new DefaultListModel<>();
    private JList<String> bookList = new JList<>(bookModel);

    public MainFrame(){
        setTitle("Library Management System");
        setSize(800,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton btnLoad = new JButton("Load Sample Data");
        JButton btnRefresh = new JButton("Refresh");
        JButton btnIssue = new JButton("Issue Book (ID)");
        JButton btnReturn = new JButton("Return (Txn ID)");
        JTextField tf = new JTextField(5);
        JTextField tfTxn = new JTextField(5);

        top.add(btnLoad); top.add(btnRefresh); top.add(new JLabel("Book ID:")); top.add(tf);
        top.add(btnIssue); top.add(new JLabel("Txn ID:")); top.add(tfTxn); top.add(btnReturn);

        add(top, BorderLayout.NORTH);
        add(new JScrollPane(bookList), BorderLayout.CENTER);

        btnLoad.addActionListener(e -> {
            service.addSampleData();
            refreshBooks();
        });

        btnRefresh.addActionListener(e -> refreshBooks());

        btnIssue.addActionListener(e -> {
            try {
                int id = Integer.parseInt(tf.getText().trim());
                service.issueBook(id, 1); // sample member id 1
                JOptionPane.showMessageDialog(this, "Issued book " + id);
                refreshBooks();
            } catch (Exception ex){
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        });

        btnReturn.addActionListener(e -> {
            try {
                int txn = Integer.parseInt(tfTxn.getText().trim());
                service.returnBook(txn);
                JOptionPane.showMessageDialog(this, "Returned txn " + txn);
                refreshBooks();
            } catch (Exception ex){
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        });

        // start background backup thread
        new Thread(() -> {
            while (true){
                try {
                    Thread.sleep(60_000); // every 60 seconds
                    DatabaseConnection.backupDatabase();
                } catch (InterruptedException ie) { break; }
            }
        }, "DB-Backup-Thread").start();
    }

    private void refreshBooks(){
        bookModel.clear();
        try {
            java.util.List<Book> books = service.listBooks();
            for (Book b : books){
                bookModel.addElement(b.toString());
            }
        } catch (Exception e){
            JOptionPane.showMessageDialog(this, "Failed to load books: " + e.getMessage());
        }
    }

    public static void showUI(){
        SwingUtilities.invokeLater(() -> {
            MainFrame f = new MainFrame();
            f.setVisible(true);
            f.refreshBooks();
        });
    }
}
