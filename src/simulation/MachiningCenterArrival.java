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

	public MachiningCenterArrival(Simulator simulator) {
		this.simulator = this.simulator;
	}

	@Override
	public double getEventTime() {
		return eventTime;
	}

	@Override
	public void handleEvent() {
		// TODO Auto-generated method stub
		// N = N + 1
		simulator.setMasterClock(eventTime);
		// log (MC , N)
		int nMachiningCenter = simulator.getNmachiningCenter();
		if (nMachiningCenter == 0) {
			// THEN start_service(machine)
			// DC = MC + ST[machine]
			// log(machine, AC[machine], MC, DC) // log entries : m, A, B, C
			// AC[machine] = DC + ArrivalRNG[machine].generateRN()
			// ST[machine]=ServiceRNG[machine].generateRN()
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
