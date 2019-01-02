import java.util.Observable;

final class ObservableOriginator extends Observable {

    private static ObservableOriginator instance = null;
    private String state;

    private ObservableOriginator() {
    }

    static ObservableOriginator Instance() {                //Singleton
        if (instance == null) {
            instance = new ObservableOriginator();
        }
        return instance;
    }

    String getState() {
        return new String(state);                           //defensive copy
    }

    void setState(String newState) {
        state = new String(newState);                       //defensive copy
        setChanged();
        notifyObservers();
    }

    Memento createMemento() {
        return new Memento(new String(state));              //defensive copy
    }

    void restoreFromMemento(Memento memento) {
        state = memento.getState();
        setChanged();
        notifyObservers();
    }

}