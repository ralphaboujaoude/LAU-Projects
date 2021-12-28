
public class Multiplexer {

	private short input0;
	private short input1;
	

    public Multiplexer(short input0, short input1) {
		this.input0 = input0;
		this.input1 = input1;
	}

    public short pickInput(boolean source) {
	   return source ? input1 : input0;
    }
}
