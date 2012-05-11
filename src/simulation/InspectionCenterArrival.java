package simulation;

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
		// TODO Auto-generated method stub

	}

	@Override
	public EventType getEventType() {
		return type;
	}

}
