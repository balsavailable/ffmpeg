
public class GrandParent<T extends GrandParent<T>> {

	public T grandParent() {

		return (T)this;
	}

}
