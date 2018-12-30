import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

final class ButtonListener implements ActionListener {

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == Tester.undoButton) {
            Tester.currentState--;
            Tester.observableOriginator.restoreFromMemento(Caretaker.getMemento(Tester.currentState - 1));
            Tester.redoButton.setEnabled(true);
            if (Tester.currentState <= 1)
                Tester.undoButton.setEnabled(false);
        } else if (e.getSource() == Tester.redoButton) {
            Tester.currentState++;
            Tester.observableOriginator.restoreFromMemento(Caretaker.getMemento(Tester.currentState - 1));
            Tester.undoButton.setEnabled(true);
            if ((Tester.savedStates - 1) < Tester.currentState) {
                Tester.redoButton.setEnabled(false);
            }
        }
    }

}