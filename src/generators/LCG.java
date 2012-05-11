package generators;

public class LCG {

	long a;
	long b;
	long m;
	long seed;

	long last;

	public LCG(long a, long b, long m, long seed) {
		this.a = a;
		this.b = b;
		this.m = m;
		this.seed = seed;
		this.last = seed;
	}

	public long getA() {
		return a;
	}

	public void setA(long a) {
		this.a = a;
	}

	public long getB() {
		return b;
	}

	public void setB(long b) {
		this.b = b;
	}

	public long getM() {
		return m;
	}

	public void setM(long m) {
		this.m = m;
	}

	public long getSeed() {
		return seed;
	}

	public void setSeed(long seed) {
		this.seed = seed;
	}

	public long getNextRandom() {
		long x_n_1 = last;
		long x_n = -1;
		if (b == 0) {	// Multiplicative LCG
			x_n = g(x_n_1) + m * h(x_n_1);
		} else {	// Mixed LCG
			x_n = (a * x_n_1 + b) % m;
		}
		last = x_n;
		return x_n;
	}

	private long g(long x) {
		long q = m / a;
		long r = m % a;
		return (a * (x % q)) - (r * (x / q));
	}

	private long h(long x) {
		long q = m / a;
		return (x / q) - ((a * x) / m);
	}

	public static LCG getPrimaryLCG(long seed){
		return new LCG(16807, 0, 2147483647, seed);
	}
	
	public static void main(String[] args) {
		LCG rnd = LCG.getPrimaryLCG(1);
		for (int i = 0; i < 20; i++) {
			System.out.println(rnd.getNextRandom());
		}
	}

}
