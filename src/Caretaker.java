import java.util.ArrayList;

final class Caretaker {

    private ArrayList<Memento> savedStates = new ArrayList<>();
    private static Caretaker instance = null;

    private Caretaker() {
    }

    static Caretaker Instance() {                //Singleton
        if (instance == null) {
            instance = new Caretaker();
        }
        return instance;
    }

    void addMemento(Memento memento) {
        savedStates.add(memento);
    }

    Memento getMemento(int index) {
        return savedStates.get(index);
    }

    void deleteMementoRange(int start, int end) {
        if (end > start)
            savedStates.subList(start, end).clear();
    }

}