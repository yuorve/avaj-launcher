package simulator;

public abstract class Tower {
    private java.util.List<Flyable> observers = new java.util.ArrayList<>();

    public void register(Flyable flyable) {
        observers.add(flyable);
        System.out.println("Tower says: " + getAircraftInfo(flyable) + " registered to weather tower.");
    }

    public void unregister(Flyable flyable) {
        observers.remove(flyable);
        System.out.println("Tower says: " + getAircraftInfo(flyable) + " unregistered from weather tower.");
    }

    protected void conditionChanged() {
        // Create a copy to avoid ConcurrentModificationException
        java.util.List<Flyable> observersCopy = new java.util.ArrayList<>(observers);
        for (Flyable flyable : observersCopy) {
            flyable.updateConditions();
        }
    }

    private String getAircraftInfo(Flyable flyable) {
        // This will be overridden by aircraft toString()
        return flyable.toString();
    }
}
