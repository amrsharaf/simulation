package simulation;

/**
 * @author Amr Sharaf
 * This interface is used to represent a simulation event (arrivals and departures)
 *
 */
public interface Event {

	
	/**
	 * Returns the event occurrence time
	 * @return event occurrence time.
	 */
	public double getEventTime();

	/**
	 * Sets the event occurrence time.
	 * @param time event occurrence time.
	 */
	public void setEventTime(double time);
	
	/**
	 * Handle the event and update the simulator state.
	 */
	public void handleEvent();
	
	/**
	 * Returns the event type
	 * @return event type
	 */
	public EventType getEventType();
}
