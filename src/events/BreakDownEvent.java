package events;

import servers.RepairCenter;
import simulation.Item;
import simulation.Simulator;

public class BreakDownEvent implements Event, Comparable<Event> {

	/**
	 * Simulator controller object.
	 */
	private Simulator simulator;

	/**
	 * Event type.
	 */
	private static final EventType type = EventType.BREAKDOWN;

	/**
	 * The time at which this event occurs.
	 */
	private double eventTime;

	/**
	 * Item associated with this event
	 */
	private Item item;

	/**
	 * Inspection center arrival event constructor.
	 * 
	 * @param simulator
	 *            Simulation Controller.
	 */
	public BreakDownEvent(Simulator simulator) {
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
		// Schedule new breakdown event
		Event breakDown = new BreakDownEvent(simulator);
		breakDown.setEventTime(eventTime + simulator.getExpBreakDown().generate());
		simulator.addEvent(breakDown);
		RepairCenter repairCenter = simulator.getRepairCenter();
		repairCenter.startService();
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

}
