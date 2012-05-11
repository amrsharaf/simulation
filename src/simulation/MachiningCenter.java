package simulation;

import java.util.PriorityQueue;

/**
 * @author Amr Sharaf
 * 
 *         This class is used to represent the machining center system (Queue +
 *         Service Unit).
 * 
 */
public class MachiningCenter {

	/**
	 * Machining center queue
	 */
	private PriorityQueue<MachiningCenterArrival> queue;
	
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
		queue = new PriorityQueue<MachiningCenterArrival>();
		// TODO: replace with a random generator
		serviceTime = 5;
	}
	
	public void startService(Item item) {
		// Schedule a new departure event from machining center.
		Event departure = new MachiningCenterDeparture(simulator);
		double masterClock = simulator.getMasterClock();
		// Set the departure time to be the current simulation time + service
		// time.
		departure.setEventTime(masterClock + serviceTime);
		simulator.addEvent(departure);
		item.setMachiningCenterDepartureTime(departure.getEventTime());
		// log(machine, AC[machine], MC, DC) // log entries : m, A, B, C
		// TODO: use random generation here
		serviceTime = 5;
	}
	
	/**
	 * Enqueue event at the waiting queue.
	 * @param event event to enqueue.
	 */
	public void enqueue(MachiningCenterArrival event) {
		queue.add(event);
	}
	
	public MachiningCenterArrival dequeue() {
		MachiningCenterArrival closestEvent = queue.poll();
		return closestEvent;
	}
}
