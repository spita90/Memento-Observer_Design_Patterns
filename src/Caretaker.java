import java.util.ArrayList;

final class Caretaker {

    private static final ArrayList<Memento> savedStates = new ArrayList<>();

    private Caretaker() {
    }

    static void addMemento(Memento memento) {
        savedStates.add(memento);
    }

    static Memento getMemento(int index) {
        return savedStates.get(index);
    }

    static void deleteMementoRange(int start, int end) {
        if (end > start)
            savedStates.subList(start, end).clear();
    }

}