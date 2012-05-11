package simulation;

/**
 * @author Amr Sharaf
 *
 * This class is used to represent an item arriving at the machining center.
 */
public class Item {

	/**
	 * Arrival time at the machining center.
	 */
	private double machiningCenterArrivalTime;
	/** 
	 * Departure time from the machining center.
	 */
	private double machiningCenterDepartureTime;
	/**
	 * Departure time from the inspection center.
	 */ 
	private double inspectionCenterDepartureTime;
	
	/**
	 * Returns the machining center arrival time
	 */
	public double getMachiningCenterArrivalTime() {
		return machiningCenterArrivalTime;
	}

	/**
	 * Returns the machining center departure time
	 */
	public double getMachiningCenterDepartureTime() {
		return machiningCenterDepartureTime;
	}

	/**
	 * Returns the inspection center departure time
	 */
	public double getInspectionCenterDepartureTime() {
		return inspectionCenterDepartureTime;
	}
}
