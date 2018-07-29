package secondarySort;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.WritableComparable;

public class IntPair implements WritableComparable<IntPair> {
	private int firstInt;
	private int secondInt;

	public IntPair() {
	};

	public IntPair(int first, int second) {
		set(first, second);
	}

	private void set(int first, int second) {
		this.firstInt = first;
		this.secondInt = second;

	}

	public int getFirstInt() {
		return firstInt;
	}

	public void setFirstInt(int firstInt) {
		this.firstInt = firstInt;
	}

	public int getSecondInt() {
		return secondInt;
	}

	public void setSecondInt(int secondInt) {
		this.secondInt = secondInt;
	}

	public void readFields(DataInput in) throws IOException {
		firstInt = in.readInt();
		secondInt = in.readInt();

	}

	public void write(DataOutput out) throws IOException {
		out.writeInt(firstInt);
		out.writeInt(secondInt);

	}

	@Override
	public int hashCode() {
		return firstInt * 163 + secondInt;
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof IntPair) {
			IntPair ip = (IntPair) o;
			return firstInt == ip.firstInt && secondInt == ip.secondInt;
		}
		return false;
	}

	public int compareTo(IntPair ip) {
		int firstComp = compare(firstInt, ip.firstInt);
		if (firstComp != 0) {
			return firstComp;
		} else {
			return compare(secondInt, ip.secondInt);
		}

	}

	public static int compare(int a, int b) {
		return (a < b ? -1 : (a == b ? 0 : 1));
	}

	@Override
	public String toString() {
		return firstInt + "\t" + secondInt;
	}
}
