import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

final class Tester extends JFrame {

    private final JButton undoButton, redoButton;
    private final Caretaker caretaker = Caretaker.Instance();
    private final ObservableOriginator observableOriginator = ObservableOriginator.Instance();
    private final Scanner scanner = new Scanner(System.in);
    private int savedStates = 0;
    private int currentState = 0;
    private boolean running = true;

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
        undoButton = new JButton("Undo");
        undoButton.addActionListener(undoListener);
        redoButton = new JButton("Redo");
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
            final KeyboardInputListener kil = new KeyboardInputListener();
            kil.enableKeyboardListener();
        }
    }

    public static void main(String[] args) {
        new Tester();
    }

    final class KeyboardInputListener {

        void enableKeyboardListener() {
            System.out.println("\nInsert text\n");
            final String text = scanner.nextLine();
            if (currentState < savedStates) {
                caretaker.deleteMementoRange(currentState, savedStates);
                savedStates = savedStates - (savedStates - currentState);
            }
            savedStates++;
            currentState++;
            observableOriginator.setState(text);
            caretaker.addMemento(observableOriginator.createMemento());
            if (currentState > 1)
                undoButton.setEnabled(true);
            redoButton.setEnabled(false);
            running = true;
        }

    }

    final class ButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == undoButton) {
                currentState--;
                observableOriginator.restoreFromMemento(caretaker.getMemento(currentState - 1));
                redoButton.setEnabled(true);
                if (currentState <= 1)
                    undoButton.setEnabled(false);
            } else if (e.getSource() == redoButton) {
                currentState++;
                observableOriginator.restoreFromMemento(caretaker.getMemento(currentState - 1));
                undoButton.setEnabled(true);
                if ((savedStates - 1) < currentState) {
                    redoButton.setEnabled(false);
                }
            }
        }

    }

}