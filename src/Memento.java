final class Memento {

    private final String state;

    Memento(String stateToSet) {
        state = stateToSet;                 //String is immutable: no defensive copy
    }

    Memento(Memento memento) {
        state = memento.getState();         //String is immutable: no defensive copy
    }

    String getState() {
        return state;                       //String is immutable: no defensive copy
    }

}