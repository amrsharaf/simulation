package events;

import servers.MachiningCenter;
import simulation.Item;
import simulation.Simulator;

/**
 * @author Amr Sharaf
 * 
 * This class is used to represent a departure event from the machining center.
 *
 */
public class MachiningCenterDeparture implements Event, Comparable<Event> {

	/**
	 * Event type.
	 */
	private static final EventType type = EventType.MACHINING_CENTER_DEPARTURE;

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
	public MachiningCenterDeparture(Simulator simulator) {
		this.simulator = simulator;
	}

	@Override
	public double getEventTime() {
		return eventTime;
	}

	@Override
	public void handleEvent() {
		int nMachiningCenter = simulator.getNmachiningCenter();
		nMachiningCenter -= 1;
		simulator.setNmachiningCenter(nMachiningCenter);
		simulator.setMasterClock(eventTime);
		// log (MC, N)
		// Schedule new arrival at inspection center
		Event inspectionArrival = new InspectionCenterArrival(simulator);
		inspectionArrival.setEventTime(eventTime);
		item.setMachiningCenterDepartureTime(eventTime);
		inspectionArrival.setEventItem(item);
		simulator.addEvent(inspectionArrival);
		if (nMachiningCenter > 0) {
			// Select closest arrival event at machining center to service.
			MachiningCenter machiningCenter = simulator.getMachiningCenter();
			MachiningCenterArrival closestArrival = machiningCenter.dequeue();
			machiningCenter.startService(closestArrival.getEventItem());			
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

}
