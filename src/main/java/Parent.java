
public class Parent<T extends Parent<T>> extends GrandParent<T> {
	public T parent() {
		return null;
	}

}
