package simulation;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.PriorityQueue;
import servers.InspectionCenter;
import servers.MachiningCenter;
import servers.RepairCenter;
import events.BreakDownEvent;
import events.Event;
import events.MachiningCenterArrival;
import generators.ExponentialRandomVariable;
import generators.SeedGenerator;
import generators.UniformRandomVariable;

/**
 * @author Amr Sharaf
 * 
 *         This class is used to run the simulation
 * 
 */
public class Simulator {

	/**
	 * Generates uniform random numbers from 0 to 1
	 */
	UniformRandomVariable uniformRandom;

	/**
	 * Exponential random variable with mean = 1 minute.
	 */
	ExponentialRandomVariable expArrival;

	/**
	 * Exponential random variable with mean = 6 hours.
	 */
	ExponentialRandomVariable expBreakDown;

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
	 * Seed generator, generates non-overlapping streams
	 */
	private SeedGenerator seedGenerator;

	/**
	 * Used for printing service time at machining center.
	 */
	private PrintWriter machiningCenterWriter;

	/**
	 * Used for printing service time at inspection center.
	 */
	private PrintWriter inspectionCenterWriter;

	/**
	 * Used for printing hourly throughput
	 */
	private PrintWriter throughputWriter;

	/**
	 * Used for printing hourly throughput
	 */
	private PrintWriter machiningQueueWriter;

	/**
	 * Used for printing hourly throughput
	 */
	private PrintWriter inspectionQueueWriter;

	/**
	 * Used for printing inter-arrival time in machining cetner.
	 */
	private PrintWriter machiningInterarrival;
	
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
	 * 
	 * @param item
	 */
	public void addItem(Item item) {
		double throughput = shipment.size()
				/ item.getInspectionCenterDepartureTime();
		throughput *= 60;
		throughputWriter.println(item.getInspectionCenterDepartureTime() + " "
				+ throughput);
		shipment.add(item);
	}

	/**
	 * Return uniform random generator
	 * 
	 * @return
	 */
	public UniformRandomVariable getUniformRandom() {
		return uniformRandom;
	}

	/**
	 * Returns exponential random variable with mean = 1 minute.
	 * 
	 * @return random variable with mean = 1 minute.
	 */
	public ExponentialRandomVariable getExpArrival() {
		return expArrival;
	}

	/**
	 * Returns exponential random variable with mean = 6 hours.
	 * 
	 * @return random variable with mean = 6 hours.
	 */
	public ExponentialRandomVariable getExpBreakDown() {
		return expBreakDown;
	}

	/**
	 * Returns the seed generator.
	 * 
	 * @return the seed generator.
	 */
	public SeedGenerator getSeedGenerator() {
		return seedGenerator;
	}

	/**
	 * Sets the total number of jobs in machining center.
	 * 
	 * @param nMachiningCenter
	 */
	public void setNmachiningCenter(int nMachiningCenter) {
		this.nMachiningCenter = nMachiningCenter;
		int queueLength = Math.max(0, nMachiningCenter - 1);
		machiningQueueWriter.println(masterClock + " " + queueLength);
	}

	/**
	 * Sets the total number of jobs in inspection center.
	 * 
	 * @param nMachiningCenter
	 */
	public void setNinspectionCenter(int nInspectionCenter) {
		this.nInspectionCenter = nInspectionCenter;
		int queueLength = Math.max(0, nInspectionCenter - 1);
		inspectionQueueWriter.println(masterClock + " " + queueLength);
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
	 * Initialize print writers using for printing data
	 */
	private void initWriters() {
		try {
			machiningCenterWriter = new PrintWriter(new FileWriter(
					"machining-service.txt"));
			inspectionCenterWriter = new PrintWriter(new FileWriter(
					"inspection-service.txt"));
			throughputWriter = new PrintWriter(new FileWriter(
					"hourly-throughput.txt"));
			machiningQueueWriter = new PrintWriter(new FileWriter(
					"machining-queue.txt"));
			inspectionQueueWriter = new PrintWriter(new FileWriter(
					"inspection-queue.txt"));
			machiningInterarrival = new PrintWriter(new FileWriter(
					"machining-interarrival.txt"));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Returns the machining center service time writer.
	 * 
	 * @return machining center service time writer.
	 */
	public PrintWriter getMachiningCenterWriter() {
		return machiningCenterWriter;
	}
	
	/**
	 * Returns the machining center inter-arrival time writer. 
	 * 
	 * @return inspection center service time writer.
	 */
	public PrintWriter getMachiningInterarrival() {
		return machiningInterarrival;
	}

	/**
	 * Returns the inspection center service time writer.
	 * 
	 * @return inspection center service time writer.
	 */
	public PrintWriter getInspectionCenterWriter() {
		return inspectionCenterWriter;
	}

	/**
	 * This function is used to initialize the simulation attributes.
	 */
	public void init() {
		initWriters();
		masterClock = 0.0;
		shipment = new ArrayList<Item>();
		seedGenerator = new SeedGenerator();
		uniformRandom = new UniformRandomVariable(0, 1,
				seedGenerator.getNextSeed());
		expArrival = new ExponentialRandomVariable(1.0,
				seedGenerator.getNextSeed());
		expBreakDown = new ExponentialRandomVariable(1.0 / (6.0 * 60.0),
				seedGenerator.getNextSeed());
		Event machiningCenterArrival = new MachiningCenterArrival(this);
		machiningCenterArrival.setEventItem(new Item());
		machiningCenterArrival.setEventTime(expArrival.generate());
		events.add(machiningCenterArrival);
		Event breakDown = new BreakDownEvent(this);
		breakDown.setEventTime(expBreakDown.generate());
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
	public boolean simulationDone(Event event) {
		return event.getEventTime() > 30 * 60;
	}

	/**
	 * This method is the main method used for running the simulation.
	 */
	public void runSimulation() {
		Event nearestEvent = events.poll();
		nearestEvent.handleEvent();
		while (!simulationDone(nearestEvent)) {
			// Select nearest event to execute
			System.out.println(nearestEvent);
			// Handle the event
			nearestEvent = events.poll();
			nearestEvent.handleEvent();
		}
		printData();
		terminateSimulation();
	}

	/**
	 * Print the inter-arrival times at both servers and the total response time
	 * for each item
	 */
	private void printData() {
		try {
			PrintWriter responseTimeWriter = new PrintWriter(new FileWriter(
					"response.txt"));
			PrintWriter inspectionInterarrival = new PrintWriter(
					new FileWriter("inspection-interarrival.txt"));
			// Print response time for the first item
			Item item = shipment.get(0);
			responseTimeWriter.println(item.getInspectionCenterDepartureTime()
					- item.getMachiningCenterArrivalTime());
			double inspectionArrival = 0.0;
			for (int i = 1; i < shipment.size(); i++) {
				inspectionArrival = shipment.get(i)
						.getMachiningCenterDepartureTime()
						- shipment.get(i - 1).getMachiningCenterDepartureTime();
				responseTimeWriter.println(item
						.getInspectionCenterDepartureTime()
						- item.getMachiningCenterArrivalTime());
				inspectionInterarrival.println(inspectionArrival);
			}
			responseTimeWriter.close();
			inspectionInterarrival.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void terminateSimulation() {
		machiningCenterWriter.close();
		inspectionCenterWriter.close();
		throughputWriter.close();
		machiningQueueWriter.close();
		inspectionQueueWriter.close();
		machiningInterarrival.close();
	}

	/**
	 * Adds a new event to the events list
	 * 
	 * @param event
	 *            event to be added
	 */
	public void addEvent(Event event) {
		events.add(event);
	}

	/**
	 * Adds a new event to the events list
	 * 
	 * @param event
	 *            event to be added
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
