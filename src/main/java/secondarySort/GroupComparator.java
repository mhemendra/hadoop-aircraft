package secondarySort;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class GroupComparator extends WritableComparator {
	protected GroupComparator() {
		super(IntPair.class, true);
	}

	@Override
	public int compare(WritableComparable w1, WritableComparable w2) {
		IntPair int1 = (IntPair) w1;
		IntPair int2 = (IntPair) w2;
		return IntPair.compare(int1.getFirstInt(), int2.getFirstInt());
	}
}
