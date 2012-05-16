package events;

import servers.MachiningCenter;
import simulation.Item;
import simulation.Simulator;

public class MachiningCenterArrival implements Event, Comparable<Event> {

	/**
	 * Simulator controller object.
	 */
	private Simulator simulator;

	/**
	 * Event type.
	 */
	private static final EventType type = EventType.MACHINING_CENTER_ARRIVAL;

	/**
	 * The time at which this event occurs.
	 */
	private double eventTime;

	/**
	 * The item that arrives at the machining center.
	 */
	private Item item;

	/**
	 * Machining center arrival event constructor
	 * 
	 * @param simulator
	 */
	public MachiningCenterArrival(Simulator simulator) {
		this.simulator = simulator;
	}

	@Override
	public double getEventTime() {
		return eventTime;
	}

	@Override
	public Item getEventItem() {
		return item;
	}

	@Override
	public void handleEvent() {
		simulator.setMasterClock(eventTime);
		double lastArrival = simulator.getMachiningCenter().getLastArrival();
		if(eventTime >= simulator.getDebugTime()) {
			simulator.getMachiningInterarrival().println(eventTime - lastArrival);	
		}
		simulator.getMachiningCenter().setLastArrival(eventTime);
		int nMachiningCenter = simulator.getNmachiningCenter();
		if (item.getMachiningCenterArrivalTime() == 0.0) {
			item.setMachiningCenterArrivalTime(eventTime);
			// Schedule new arrival event
			Event arrival = new MachiningCenterArrival(simulator);
			arrival.setEventItem(new Item());
			double interarrival = simulator.getExpArrival().generate();
			arrival.setEventTime(eventTime + interarrival);
			simulator.addEvent(arrival);
		}
		nMachiningCenter += 1;
		simulator.setNmachiningCenter(nMachiningCenter);
		MachiningCenter machiningCenter = simulator.getMachiningCenter();
		if (nMachiningCenter == 1 && !machiningCenter.isInRepair()) {
			machiningCenter.startService(item);
		} else {
			// Queue current request
			machiningCenter.enqueue(this);
		}
	}

	@Override
	public EventType getEventType() {
		return type;
	}

	@Override
	public void setEventTime(double time) {
		this.eventTime = time;
	}

	@Override
	public int compareTo(Event other) {
		return new Double(eventTime).compareTo(other.getEventTime());
	}

	@Override
	public void setEventItem(Item item) {
		this.item = item;
	}
	
	@Override
	public String toString() {
		return eventTime + " " + type + " " + item;
	}

}
