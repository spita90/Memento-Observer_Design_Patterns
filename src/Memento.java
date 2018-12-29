final class Memento {

    private final String state;

    Memento(String stateToSet) {
        state = stateToSet;
    }

    String getState() {
        return state;
    }

}