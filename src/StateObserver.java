import javax.swing.*;
import java.util.Observable;
import java.util.Observer;

final class StateObserver implements Observer {

    private final JTextArea textArea;

    StateObserver(JTextArea textArea) {                         //not a singleton - there can be many StateObservers for many textAreas
        this.textArea = textArea;
    }

    @Override
    public void update(Observable o, Object arg) {
        String state = ((ObservableOriginator) o).getState();
        textArea.setText(state);
    }

}