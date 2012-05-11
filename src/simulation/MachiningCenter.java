package simulation;

/**
 * @author Amr Sharaf
 * 
 *         This class is used to represent the machining center system (Queue +
 *         Service Unit).
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

	/**
	 * Machining center constructor.
	 * @param simulator simulator controller.
	 */
	public MachiningCenter(Simulator simulator) {
		this.simulator = simulator;
	}
	
	public void startService(Item item) {
		// Schedule a new departure event from machining center.
		Event departure = new MachiningCenterDeparture(simulator);
		double masterClock = simulator.getMasterClock();
		// Set the departure time to be the current simulation time + service
		// time.
		departure.setEventTime(masterClock + serviceTime);
		item.setInspectionCenterDepartureTime(departure.getEventTime());
		// log(machine, AC[machine], MC, DC) // log entries : m, A, B, C
		// TODO: use random generation here
		// Schedule new arrival event
		Event arrival = new MachiningCenterArrival(simulator);
		arrival.setEventTime(departure.getEventTime() + 10);
		serviceTime = 5;
	}
}
