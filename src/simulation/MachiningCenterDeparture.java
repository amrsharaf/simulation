package simulation;

public class MachiningCenterDeparture implements Event {

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
	 * Machining center departure event constructor.
	 * 
	 * @param simulator
	 *            simulator constructor.
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
		// N = N - 1
		int nMachiningCenter = simulator.getNmachiningCenter();
		nMachiningCenter -= 1;
		simulator.setNmachiningCenter(nMachiningCenter);
		simulator.setMasterClock(eventTime);
		// log (MC, N)
		if (nMachiningCenter > 0) {
			// IF (N>0) THEN
			// Select nearest time arrival of machine to start service
			// start_service(machine)			
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

}
