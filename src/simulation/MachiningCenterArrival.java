package simulation;

public class MachiningCenterArrival implements Event {

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

	public MachiningCenterArrival(Simulator simulator) {
		this.simulator = simulator;
		item = new Item();
	}

	@Override
	public double getEventTime() {
		return eventTime;
	}

	@Override
	public void handleEvent() {
		int nMachiningCenter = simulator.getNmachiningCenter();
		nMachiningCenter += 1;
		simulator.setNmachiningCenter(nMachiningCenter);
		simulator.setMasterClock(eventTime);
		// log (MC , N)
		if (nMachiningCenter == 1) {
			MachiningCenter machiningCenter = simulator.getMachiningCenter();
			machiningCenter.startService(item);
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

}
