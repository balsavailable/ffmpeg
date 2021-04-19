import java.util.Optional;

import com.djax.ffmpeg.builder.AdBuilder;
import com.djax.ffmpeg.builder.CreativeBuilder;

public class Maine {

	public static void main(String[] args) {

		Optional<Child> child = Optional.ofNullable(null);
		if (child.isPresent())
			child.get().grandParent();
		System.out.println("hi");
		

	}
}
