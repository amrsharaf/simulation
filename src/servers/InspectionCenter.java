package servers;

import java.util.PriorityQueue;

import simulation.Item;
import simulation.Simulator;

import events.Event;
import events.InspectionCenterArrival;
import events.InspectionCenterDeparture;

/**
 * @author Amr Sharaf
 * 
 * This class is used to represent the inspection center.
 *
 */
public class InspectionCenter {
	
	/**
	 * Inspection center queue
	 */
	private PriorityQueue<InspectionCenterArrival> queue;
	
	/**
	 * Simulator controller.
	 */
	private Simulator simulator;

	/**
	 * Inspection center service time
	 */
	private double serviceTime;

	/**
	 * Machining center constructor.
	 * @param simulator simulator controller.
	 */
	public InspectionCenter(Simulator simulator) {
		this.simulator = simulator;
		queue = new PriorityQueue<InspectionCenterArrival>();
		// TODO: replace with a random generator
		serviceTime = 6;
	}
	
	public void startService(Item item) {
		// Schedule a new departure event from inspection center.
		Event departure = new InspectionCenterDeparture(simulator);
		departure.setEventItem(item);
		double masterClock = simulator.getMasterClock();
		// Set the departure time to be the current simulation time + service
		// time.
		departure.setEventTime(masterClock + serviceTime);
		simulator.addEvent(departure);
		// log(machine, AC[machine], MC, DC) // log entries : m, A, B, C
		// TODO: use random generation here
		serviceTime = 6;
	}
	
	/**
	 * Enqueue event at the waiting queue.
	 * @param event event to enqueue.
	 */
	public void enqueue(InspectionCenterArrival event) {
		queue.add(event);
	}
	
	/**
	 * Remove item from the waiting queue.
	 * @return nearest arrival item from waiting queue.
	 */
	public InspectionCenterArrival dequeue() {
		InspectionCenterArrival closestEvent = queue.poll();
		return closestEvent;
	}

}
