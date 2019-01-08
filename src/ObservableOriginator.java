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
        return state;                           //String is immutable: no defensive copy
    }

    void setState(String newState) {
        state = newState;                       //String is immutable: no defensive copy
        setChanged();
        notifyObservers();
    }

    Memento createMemento() {
        return new Memento(state);
    }

    void restoreFromMemento(Memento memento) {
        state = memento.getState();
        setChanged();
        notifyObservers();
    }

}