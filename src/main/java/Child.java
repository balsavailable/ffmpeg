
public class Child<T extends Child<T>> extends Parent<T>{
	
	public T child()
	{
		System.out.println("hi");
		return null;
	}


}
