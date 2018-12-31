import java.util.ArrayList;

final class Caretaker {

    private static final ArrayList<Memento> savedStates = new ArrayList<>();

    private Caretaker() {                                     //cannot instantiate: this class offers only class methods
    }

    static void addMemento(Memento memento) {
        savedStates.add(new Memento(memento));
    }           //defensive copy

    static Memento getMemento(int index) {
        return new Memento(savedStates.get(index));
    }         //defensive copy

    static void deleteMementoRange(int start, int end) {
        if (end > start)
            savedStates.subList(start, end).clear();
    }

}