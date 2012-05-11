package servers;

import simulation.Simulator;
import events.MachiningCenterDeparture;
import events.RepairEvent;

/**
 * @author Amr Sharaf
 * 
 * This class represents the repairing center for the machine.
 *
 */
public class RepairCenter {
	
	/**
	 * Simulator controller.
	 */
	private Simulator simulator;

	/**
	 * Machining center service time
	 */
	private double serviceTime;
	
	/**
	 * Machining center constructor.
	 * @param simulator simulator controller.
	 */
	public RepairCenter(Simulator simulator) {
		this.simulator = simulator;
		// TODO: replace with a random generator
		serviceTime = 5;
	}
	
	public void startService() {
		MachiningCenter machiningCenter = simulator.getMachiningCenter();
		machiningCenter.setInRepair(true);
		MachiningCenterDeparture nearestDeparture = machiningCenter
				.getNearestDeparture();		
		if(nearestDeparture != null) {
			simulator.removeEvent(nearestDeparture);
			nearestDeparture.setEventTime(nearestDeparture.getEventTime() + serviceTime);
			simulator.addEvent(nearestDeparture);
		}
		// Schedule a new repair event.
		RepairEvent repairEvent = new RepairEvent(simulator);
		double masterClock = simulator.getMasterClock();
		// Set the repair time to be the current simulation time + service
		// time.
		repairEvent.setEventTime(masterClock + serviceTime);
		simulator.addEvent(repairEvent);
		// log(machine, AC[machine], MC, DC) // log entries : m, A, B, C
		// TODO: use random generation here
		serviceTime = 5;
	}
}
