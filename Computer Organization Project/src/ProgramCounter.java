
public class ProgramCounter {
private short value;

public short incrementAndGet() {
	value+=2;
	return value;
}
public short getValue() {
	return value;
}

	public void setValue(short value) {
	this.value = value;
}
}
