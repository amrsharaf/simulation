package simulation;

/**
 * @author Amr Sharaf
 *  
 * This class is used to represent the machining center system (Queue + Service
 * Unit).
 * 
 */
public class MachiningCenter {

	/**
	 * Simulator controller.
	 */
	private Simulator simulator;
	
	/**
	 * Machining center service time
	 */
	private double serviceTime;

	public void startService(Item item) {
		Event departure = new MachiningCenterDeparture();
		//double masterClock = 
		//departure.setEventTime()
		// DC = MC + ST[machine]
		// log(machine, AC[machine], MC, DC) // log entries : m, A, B, C
		// AC[machine] = DC + ArrivalRNG[machine].generateRN()
		// ST[machine]=ServiceRNG[machine].generateRN()
	}
}
