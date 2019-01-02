final class Memento {

    private final String state;

    Memento(String stateToSet) {
        state = new String(stateToSet);                 //defensive copy
    }

    Memento(Memento memento) {
        state = new String(memento.getState());         //defensive copy
    }

    String getState() {
        return new String(state);                       //defensive copy
    }

}