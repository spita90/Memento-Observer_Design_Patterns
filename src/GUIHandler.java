import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;
import javax.swing.*;

public final class GUIHandler extends JFrame {

    public static void main(String[] args) {
        new GUIHandler();
    }

    private JButton undoButton, redoButton;
    private Caretaker caretaker = Caretaker.Instance();
    private ObservableOriginator observableOriginator = ObservableOriginator.Instance();
    private int savedStates = 0;
    private int currentState = 0;
    private Scanner scanner = new Scanner(System.in);
    private boolean running = true;

    private GUIHandler() {
        this.setSize(450, 250);
        this.setTitle("Memento/Observer Design Patterns");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        JTextArea textArea = new JTextArea(10, 40);
        panel.add(textArea);
        textArea.setEnabled(false);
        textArea.setDisabledTextColor(Color.BLACK);
        ButtonListener undoListener = new ButtonListener();
        ButtonListener redoListener = new ButtonListener();
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
        StateObserver stateObserver = new StateObserver(textArea);
        observableOriginator.addObserver(stateObserver);
        while (running) {
            running = false;
            KeyboardInputListener kil = new KeyboardInputListener();
            kil.enableKeyboardListener();
        }
    }

    final class KeyboardInputListener {

        void enableKeyboardListener() {
            System.out.println("\nInsert text\n");
            String text = scanner.nextLine();
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