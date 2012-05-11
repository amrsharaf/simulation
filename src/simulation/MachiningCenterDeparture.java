package simulation;

public class MachiningCenterDeparture implements Event {

	/**
	 * The time at which this event occurs.
	 */
	private double eventTime;

	@Override
	public double getEventTime() {
		return eventTime;
	}

	@Override
	public void handleEvent() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public EventType getEventType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setEventTime(double time) {
		this.eventTime = time;
	}

}
