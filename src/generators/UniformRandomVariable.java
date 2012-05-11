package generators;

public class UniformRandomVariable {

	LCG lcg;
	double a,b;
	double n;
	
	public UniformRandomVariable(double a,double b,long uniformSeed) {
		this.lcg = LCG.getPrimaryLCG(uniformSeed);
		this.a = a;
		this.b = b;
		this.n = b-a;
	}
	
	public double generate(){
		double u = ((double)lcg.getNextRandom())/LCG.getPrimaryPeriod();
		return u*n + a;
		
	}
	
	public static void main(String[] args) {
		UniformRandomVariable u = new UniformRandomVariable(1.0, 1.5, 1);
		double sum = 0;
		for (int i = 0; i < 10000; i++) {
			sum += u.generate();
		}
		//mean should be 1.25
		System.out.println("mean = "  + sum/10000.0);
	}

}
