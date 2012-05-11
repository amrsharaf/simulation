package generators;

public class ExponentialRandomVariable {

	double rate = 0.0;
	
	LCG lcg;
	
	public ExponentialRandomVariable(double rate,LCG lcg){
		this.rate = rate;
		this.lcg = lcg;
	}
	
	public double generate(){
		double u = lcg.getNextRandom()/2147483647.0;
		return -1.0/rate * Math.log(u);
	}
	
	public static void main(String[] args){
		LCG lcg = LCG.getPrimaryLCG(1);
		ExponentialRandomVariable exp = new ExponentialRandomVariable(1/6.0,lcg);
		double sum = 0;
		for (int i = 0; i < 1000; i++) {
			sum+= exp.generate();
		}
		sum /= 1000;
		System.out.println(sum);
	}
}
