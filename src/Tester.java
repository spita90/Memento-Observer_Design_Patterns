import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

final class Tester extends JFrame {

    final static JButton undoButton = new JButton("Undo");
    final static JButton redoButton = new JButton("Redo");
    final static ObservableOriginator observableOriginator = ObservableOriginator.Instance();
    static Integer savedStates = 0;
    static Integer currentState = 0;
    private final static Scanner scanner = new Scanner(System.in);
    private static Boolean running = true;

    private Tester() {
        this.setSize(450, 250);
        this.setTitle("Memento/Observer Design Patterns");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final JPanel panel = new JPanel();
        final JTextArea textArea = new JTextArea(10, 40);
        panel.add(textArea);
        textArea.setEnabled(false);
        textArea.setDisabledTextColor(Color.BLACK);
        final ButtonListener undoListener = new ButtonListener();
        final ButtonListener redoListener = new ButtonListener();
        undoButton.addActionListener(undoListener);
        redoButton.addActionListener(redoListener);
        panel.add(undoButton);
        panel.add(redoButton);
        undoButton.setEnabled(false);
        redoButton.setEnabled(false);
        this.add(panel);
        this.setVisible(true);

        final StateObserver stateObserver = new StateObserver(textArea);
        observableOriginator.addObserver(stateObserver);

        while (running) {
            running = false;
            enableKeyboardListener();
        }
    }

    public static void main(String[] args) {
        new Tester();
    }


    private void enableKeyboardListener() {
        System.out.println("\nInsert text\n");
        final String text = scanner.nextLine();
        if (currentState < savedStates) {
            Caretaker.deleteMementoRange(currentState, savedStates);
            savedStates = savedStates - (savedStates - currentState);
        }
        savedStates++;
        currentState++;
        observableOriginator.setState(text);
        Caretaker.addMemento(observableOriginator.createMemento());
        if (currentState > 1)
            undoButton.setEnabled(true);
        redoButton.setEnabled(false);
        running = true;
    }

}