package servers;

import java.util.PriorityQueue;

import simulation.Item;
import simulation.Simulator;

import events.Event;
import events.InspectionCenterArrival;
import events.InspectionCenterDeparture;
import generators.UniformRandomVariable;

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
	 * Used to generate uniform random variable for service time
	 */
	private UniformRandomVariable random;
	
	private double lastArrival;
	
	public double getLastArrival() {
		return lastArrival;
	}
	
	public void setLastArrival(double arrivalTime){
		lastArrival = arrivalTime;
	}
	
	/**
	 * Machining center constructor.
	 * @param simulator simulator controller.
	 */
	public InspectionCenter(Simulator simulator) {
		this.simulator = simulator;
		queue = new PriorityQueue<InspectionCenterArrival>();
		long seed = simulator.getSeedGenerator().getNextSeed();
		random = new UniformRandomVariable(0.75, 0.80, seed);
		serviceTime = random.generate();
		lastArrival = 0.0;
	}
	
	public void startService(Item item) {
		// Schedule a new departure event from inspection center.
		Event departure = new InspectionCenterDeparture(simulator);
		departure.setEventItem(item);
		double masterClock = simulator.getMasterClock();
		// Print service time
		if(masterClock >= simulator.getDebugTime()) {
			simulator.getInspectionCenterWriter().println(serviceTime);	
		}
		// Set the departure time to be the current simulation time + service
		// time.
		departure.setEventTime(masterClock + serviceTime);
		simulator.addEvent(departure);
		serviceTime = random.generate();
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
