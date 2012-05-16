package events;

import servers.MachiningCenter;
import simulation.Item;
import simulation.Simulator;

public class RepairEvent implements Event, Comparable<Event> {

	/**
	 * Event type.
	 */
	private static final EventType type = EventType.REPAIR_EVENT;

	/**
	 * The time at which this event occurs.
	 */
	private double eventTime;

	/**
	 * Simulator manager
	 */
	private Simulator simulator;

	/**
	 * Item associated with this event.
	 */
	private Item item;

	/**
	 * Machining center departure event constructor.
	 * 
	 * @param simulator
	 *            simulator controller.
	 */
	public RepairEvent(Simulator simulator) {
		this.simulator = simulator;
	}

	@Override
	public int compareTo(Event other) {
		return new Double(eventTime).compareTo(other.getEventTime());
	}

	@Override
	public double getEventTime() {
		return eventTime;
	}

	@Override
	public void setEventTime(double time) {
		this.eventTime = time;
	}

	@Override
	public void handleEvent() {
		simulator.setMasterClock(eventTime);
		int nMachiningCenter = simulator.getNmachiningCenter();
		MachiningCenter machiningCenter = simulator.getMachiningCenter();
		machiningCenter.setInRepair(false);
		if (nMachiningCenter > 0
				&& machiningCenter.getNearestDeparture() == null) {
			// Select closest arrival event at machining center to service.
			MachiningCenterArrival closestArrival = machiningCenter.dequeue();
			machiningCenter.startService(closestArrival.getEventItem());
		}
	}

	@Override
	public EventType getEventType() {
		return type;
	}

	@Override
	public Item getEventItem() {
		return item;
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
