package simulation;

import java.util.ArrayList;
import java.util.List;

public class LCG {
	long a;
	long b;
	long m;
	long seed;
	ArrayList<Long> randomNumbersPool;
	

	public LCG(long a, long b, long m , long seed) {
		this.a = a;
		this.b = b;
		this.m = m;
		this.seed = seed;
		randomNumbersPool = new ArrayList<Long>();
		randomNumbersPool.add(seed);
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
	
	public long getNextRandom()
	{
		long x_n_1 = randomNumbersPool.get(randomNumbersPool.size()-1);
		long x_n = -1;
		if(b == 0)	//Multiplicative LCG
		{
			x_n = g(x_n_1) + m*h(x_n_1);
		}
		else	//Mixed LCG
		{
			x_n = (a*x_n_1 + b) % m;
		}
		randomNumbersPool.add(x_n);	//save at pool
		return x_n;		
	}
	
	public List<Long> getNextRandoms(int n)
	{
		for (int i = 0; i < n; i++) 
		{
			getNextRandom();
		}
		return  randomNumbersPool.subList(randomNumbersPool.size()-n, randomNumbersPool.size());
	}
	
	private long g(long x)
	{
		long q = m / a;
		long r = m % a;
		return ( a*(x % q) ) - ( r*(x / q) );
	}
	
	private long h(long x)
	{
		long q = m / a;
		return (x / q) - ( (a*x) / m);
	}



	public static void main(String[] args) {
		LCG rnd = new LCG(16807,0,2147483647,1);
		/*for (int i = 0; i < 20; i++) {
			System.out.print(rnd.getNextRandom() +", "); 
		}
		System.out.println();
		*/
		System.out.println(rnd.getNextRandoms(100000));

	}

}
