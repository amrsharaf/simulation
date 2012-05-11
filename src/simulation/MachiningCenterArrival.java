package simulation;

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
	 * @param simulator
	 */
	public MachiningCenterArrival(Simulator simulator) {
		this.simulator = simulator;
		item = new Item();
	}

	@Override
	public double getEventTime() {
		return eventTime;
	}
	
	/**
	 * Return the arrival item
	 * @return arrival item
	 */
	public Item getItem() {
		return item;
	}

	@Override
	public void handleEvent() {
		int nMachiningCenter = simulator.getNmachiningCenter();
		nMachiningCenter += 1;
		simulator.setNmachiningCenter(nMachiningCenter);
		simulator.setMasterClock(eventTime);
		// log (MC , N)
		// Schedule new arrival event
		Event arrival = new MachiningCenterArrival(simulator);
		// TODO: replace this part with a random generator
		arrival.setEventTime(eventTime + 1);
		simulator.addEvent(arrival);
		MachiningCenter machiningCenter = simulator.getMachiningCenter();
		if (nMachiningCenter == 1) {
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
		this.item.setMachiningCenterArrivalTime(eventTime);
	}

	@Override
	public int compareTo(Event other) {
		return new Double(eventTime).compareTo(other.getEventTime());
	}

}
