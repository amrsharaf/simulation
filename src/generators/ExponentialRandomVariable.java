package generators;

public class ExponentialRandomVariable {

	double rate = 0.0;
	
	public ExponentialRandomVariable(double rate){
		this.rate = rate;
	}
	
	public double generate(){
		double u = Math.random();
		return -1.0/rate * Math.log(u);
	}
}
