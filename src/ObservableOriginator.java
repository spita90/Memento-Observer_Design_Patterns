import java.util.Observable;

final class ObservableOriginator extends Observable {

    private String state;
    private static ObservableOriginator instance = null;

    private ObservableOriginator() {
    }

    static ObservableOriginator Instance() {                //Singleton
        if (instance == null) {
            instance = new ObservableOriginator();
        }
        return instance;
    }

    void setState(String newState) {
        state = newState;
        setChanged();
        notifyObservers();
    }

    String getState() {
        return state;
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