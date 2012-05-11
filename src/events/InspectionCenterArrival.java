package events;

import servers.InspectionCenter;
import simulation.Item;
import simulation.Simulator;

/**
 * @author Amr Sharaf
 * 
 *         This class represents an arrival event at the inspection center.
 * 
 */
public class InspectionCenterArrival implements Event, Comparable<Event> {

	/**
	 * Simulator controller object.
	 */
	private Simulator simulator;

	/**
	 * Event type.
	 */
	private static final EventType type = EventType.INSPECTION_CENTER_ARRIVAL;

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
	public InspectionCenterArrival(Simulator simulator) {
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
		int nInspectionCenter = simulator.getNinspectionCenter();
		nInspectionCenter += 1;
		simulator.setNinspectionCenter(nInspectionCenter);
		simulator.setMasterClock(eventTime);
		// log (MC , N)
		InspectionCenter inspectionCenter = simulator.getInspectionCenter();
		if (nInspectionCenter == 1) {
			inspectionCenter.startService(item);
		} else {
			// Queue current request
			inspectionCenter.enqueue(this);
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

}
