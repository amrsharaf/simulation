package simulation;

/**
 * @author Amr Sharaf
 * 
 *         This class is used to represent an item arriving at the machining
 *         center.
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
	 * Returns the machining center arrival time.
	 */
	public double getMachiningCenterArrivalTime() {
		return machiningCenterArrivalTime;
	}

	/**
	 * Returns the machining center departure time.
	 */
	public double getMachiningCenterDepartureTime() {
		return machiningCenterDepartureTime;
	}

	/**
	 * Returns the inspection center departure time.
	 */
	public double getInspectionCenterDepartureTime() {
		return inspectionCenterDepartureTime;
	}

	/**
	 * Sets the machining center arrival time.
	 * 
	 * @param machiningCenterArrivalTime
	 */
	public void setMachiningCenterArrivalTime(double machiningCenterArrivalTime) {
		this.machiningCenterArrivalTime = machiningCenterArrivalTime;
	}

	/**
	 * Sets the machining center departure time.
	 * 
	 * @param machiningCenterDepartureTime
	 */
	public void setMachiningCenterDepartureTime(
			double machiningCenterDepartureTime) {
		this.machiningCenterDepartureTime = machiningCenterDepartureTime;
	}

	/**
	 * Sets the inspection center departure time.
	 * 
	 * @param inspectionCenterDepartureTime
	 */
	public void setInspectionCenterDepartureTime(
			double inspectionCenterDepartureTime) {
		this.inspectionCenterDepartureTime = inspectionCenterDepartureTime;
	}

}
