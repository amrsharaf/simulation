package simulation;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Random;

import servers.InspectionCenter;
import servers.MachiningCenter;
import servers.RepairCenter;

import events.BreakDownEvent;
import events.Event;
import events.MachiningCenterArrival;

/**
 * @author Amr Sharaf
 * 
 *         This class is used to run the simulation
 * 
 */
public class Simulator {

	/**
	 * Random number generator with seed = 10
	 */
	Random random = new Random(10);

	/**
	 * Priority queue used for storing the simulation events.
	 */
	private PriorityQueue<Event> events = new PriorityQueue<Event>();

	/**
	 * Total number of jobs in the machining center, including the current job
	 * being served.
	 */
	private int nMachiningCenter;

	/**
	 * Total number of jobs in the inspection center, including the current job
	 * being served.
	 */
	private int nInspectionCenter;

	/**
	 * Represents the factory machining center.
	 */
	private MachiningCenter machiningCenter;

	/**
	 * Represents the factory inspection center.
	 */
	private InspectionCenter inspectionCenter;

	/**
	 * Represents the machine repair center.
	 */
	private RepairCenter repairCenter;

	/**
	 * Simulation master clock.
	 */
	private double masterClock;

	/**
	 * Items ready for shipment
	 */
	private ArrayList<Item> shipment;
	/**
	 * Set the simulation master clock.
	 * 
	 * @param masterClock
	 *            the master clock to set.
	 */
	public void setMasterClock(double masterClock) {
		this.masterClock = masterClock;
	}
	
	/**
	 * Add item to shipment list
	 * @param item
	 */
	public void addItem(Item item) {
		shipment.add(item);
	}
	
	/**
	 * Return random generator
	 * @return
	 */
	public Random getRandom() {
		return random;
	}
	
	/**
	 * Sets the total number of jobs in machining center.
	 * @param nMachiningCenter
	 */
	public void setNmachiningCenter(int nMachiningCenter) {
		this.nMachiningCenter = nMachiningCenter;
	}

	/**
	 * Sets the total number of jobs in inspection center.
	 * @param nMachiningCenter
	 */
	public void setNinspectionCenter(int nInspectionCenter) {
		this.nInspectionCenter = nInspectionCenter;
	}

	/**
	 * Returns the simulation master clock.
	 * 
	 * @return master clock.
	 */
	public double getMasterClock() {
		return masterClock;
	}

	/**
	 * Returns the total number of jobs in the machining center.
	 * 
	 * @return total number of jobs in machining center.
	 */
	public int getNmachiningCenter() {
		return nMachiningCenter;
	}
	
	/**
	 * Returns the total number of jobs in the inspection center.
	 * 
	 * @return total number of jobs in inspection center.
	 */
	public int getNinspectionCenter() {
		return nInspectionCenter;
	}


	/**
	 * This function is used to initialize the simulation attributes.
	 */
	public void init() {
		masterClock = 0.0;
		shipment = new ArrayList<Item>();
		Event machiningCenterArrival = new MachiningCenterArrival(this);
		// TODO: replace with a random generator
		machiningCenterArrival.setEventItem(new Item());
		machiningCenterArrival.setEventTime(1);
		events.add(machiningCenterArrival);
		
		Event breakDown = new BreakDownEvent(this);
		// TODO: replace this part with a random generator
		breakDown.setEventTime(1.5);
		events.add(breakDown);

		machiningCenter = new MachiningCenter(this);
		inspectionCenter = new InspectionCenter(this);
		repairCenter = new RepairCenter(this);
	}

	/**
	 * Returns the factory machining center.
	 * 
	 * @return factory machining center.
	 */
	public MachiningCenter getMachiningCenter() {
		return machiningCenter;
	}
	
	/**
	 * Returns the factory inspection center.
	 * 
	 * @return factory inspection center.
	 */
	public InspectionCenter getInspectionCenter() {
		return inspectionCenter;
	}

	/**
	 * Returns the machine repair center.
	 * 
	 * @return machine repair center.
	 */
	public RepairCenter getRepairCenter() {
		return repairCenter;
	}

	/**
	 * Used to detect if the simulation has ended.
	 * 
	 * @return true when the simulation has ended.
	 */
	public boolean simulationDone() {
		return false;
	}

	/**
	 * This method is the main method used for running the simulation.
	 */
	public void runSimulation() {
		while (!simulationDone()) {
			// Select nearest event to execute
			Event nearestEvent = events.poll();
			System.out.println(nearestEvent.getEventTime() +" "+nearestEvent.getEventType());
			// Handle the event
			nearestEvent.handleEvent();
		}
	}

	/**
	 * Adds a new event to the events list
	 * @param event event to be added
	 */
	public void addEvent(Event event) {
		events.add(event);
	}

	/**
	 * Adds a new event to the events list
	 * @param event event to be added
	 */
	public void removeEvent(Event event) {
		events.remove(event);
	}
	/**
	 * Application main entry point. Used to run the simulation
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Simulator simulator = new Simulator();
		simulator.init();
		// start the simulation
		simulator.runSimulation();
	}
}
