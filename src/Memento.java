final class Memento {

    private final String state;

    Memento(String stateToSet) {
        state = stateToSet;
    }

    Memento(Memento memento) {
        state = memento.getState();
    }

    String getState() {
        return state;
    }

}