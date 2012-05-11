package generators;

import java.util.ArrayList;
import java.util.HashSet;

public class SeedGenerator {

	LCG lcg;
	ArrayList<Long> seeds;
	final int LCG_INTERVAL = 100000;
	final int SEEDS_COUNT = 10;
	int index = 0;
	
	public SeedGenerator() {
		lcg = new LCG(16807, 0, 2147483647, 1);
		init();
	}
	
	public void init(){
		seeds = new ArrayList<Long>();
		seeds.add(1L);
		int count = 1;
		long num;
		for (int i = 0; i < 2147483647; i++) {
			num = lcg.getNextRandom();
			if (count == 0)
				seeds.add(num);
			
			count +=1;
			count %= LCG_INTERVAL;
			if (seeds.size() == SEEDS_COUNT) break;
		}
		
		System.out.println(seeds.size());
		System.out.println(seeds);
	}
	
	public long getNextSeed(){
		if (index == SEEDS_COUNT)
			index = 0;
		return seeds.get(index++);
	}
	
	public static void main(String[] args){
		SeedGenerator s =  new SeedGenerator();
		long s1 = s.getNextSeed();
		long s2 = s.getNextSeed();
		System.out.println("A " + s1);
		LCG lcg1 = LCG.getPrimaryLCG(s1);
		LCG lcg2 = LCG.getPrimaryLCG(s2);
		HashSet<Long> set = new HashSet<Long>();
		for (int i = 0; i < 100000; i++) {
			set.add(lcg1.getNextRandom());
			set.add(lcg2.getNextRandom());
		}
		//size should be 20000 as numbers shouldn't overlab
		System.out.println(set.size());
	}
}
