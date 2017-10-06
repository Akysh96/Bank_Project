package bank;

public interface Observable {

	public void addObserver(Observer obs);

	public void notifyObserver(String changes);
}
