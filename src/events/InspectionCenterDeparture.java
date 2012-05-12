package events;

import servers.InspectionCenter;
import simulation.Item;
import simulation.Simulator;

/**
 * @author Amr Sharaf
 * 
 *         This class is used to represent a departure event from inspection
 *         center.
 * 
 */
public class InspectionCenterDeparture implements Event, Comparable<Event> {

	/**
	 * Event type.
	 */
	private static final EventType type = EventType.INSPECTION_CENTER_DEPARTURE;

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
	 * Inspection center departure event constructor.
	 * 
	 * @param simulator
	 *            simulator controller.
	 */
	public InspectionCenterDeparture(Simulator simulator) {
		this.simulator = simulator;
	}

	@Override
	public double getEventTime() {
		return eventTime;
	}

	@Override
	public void handleEvent() {
		simulator.setMasterClock(eventTime);
		int nInspectionCenter = simulator.getNinspectionCenter();
		nInspectionCenter -= 1;
		simulator.setNinspectionCenter(nInspectionCenter);
		item.setInspectionCenterDepartureTime(eventTime);
		// check if item is valid for shipment
		double rnd = simulator.getUniformRandom().generate();
		if(rnd < 0.9) {
			simulator.addItem(item);
		} else {
			// return back to inspection center
			Event arrival = new MachiningCenterArrival(simulator);
			arrival.setEventItem(item);
			arrival.setEventTime(eventTime);
			simulator.addEvent(arrival);			
		}
		// log (MC, N)
		if (nInspectionCenter > 0) {
			// Select closest arrival event at inspection center to service.
			InspectionCenter inspectionCenter = simulator.getInspectionCenter();
			InspectionCenterArrival closestArrival = inspectionCenter.dequeue();
			inspectionCenter.startService(closestArrival.getEventItem());
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
