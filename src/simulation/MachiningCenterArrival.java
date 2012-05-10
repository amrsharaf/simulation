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
	
	public MachiningCenterArrival(Simulator simulator) {
		this.simulator = this.simulator;
	}
	
	@Override
	public double getEventTime() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void handleEvent() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public EventType getEventType() {
		return type;
	}

}
