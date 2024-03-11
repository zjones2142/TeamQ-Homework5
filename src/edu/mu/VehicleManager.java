package edu.mu;

import java.util.*;

import edu.mu.vehicleEnums.FuelType;
import edu.mu.vehicleEnums.StartMechanism;
import edu.mu.vehicleEnums.VehicleColor;

import java.io.IOException;
import java.io.FileReader;
import java.io.*;


public class VehicleManager {
	private final static double distance = 300;
	private final static double fuelPrice = 3.25;
	public final static String vehicleListFile = "vehicleList.csv";
	public ArrayList <Vehicle> vehicleList = new ArrayList<Vehicle>();
	
	//Todo: rb vvvvvvvvvvvvvv
	public boolean initializeStock() {
		/*
		 * o Reads the data from a CSV file located at vehicleFilePath. Initialize each
		 * of the Vehicle objects (Hint: Consider using the split() method for
		 * tokenization. Check the type of each object and instantiate the exact class.
		 * Store the objects into vehicleList). o Return true if the read file and
		 * initialization are successful. o Return false if cannot read/find the file.
		 */
		try(BufferedReader br = new BufferedReader(new FileReader(vehicleListFile))){
    		String line;
    		while((line = br.readLine()) != null) { //iterate through all lines in the file 
	          if(line.startsWith("Type")) { //skips headers
	            continue;
	          }
	          
	          //These initialize the vehicle based on csv location in file
	          //set string called token to split line at ","
	          String[] token = line.split(",");
	          //Type is what we use for deciding case 
	          String Type = token[0];
	          //setting each variable to there respective spots in the token array
	          String model = token[1];
	          String make = token[2];
	          long modelYear = Integer.parseInt(token[3]);
	          double price = Double.parseDouble(token[4]);
	          VehicleColor color = VehicleColor.valueOf(token[5]);
	          FuelType fuelType = FuelType.valueOf(token[6]);
	          double mileage = Double.parseDouble(token[7]);
	          double mass = Double.parseDouble(token[8]);
	          int cylinders = Integer.parseInt(token[9]);
	          double gasTankCapacity = Double.parseDouble(token[10]);
	          StartMechanism startType = StartMechanism.valueOf(token[11]);
	          Vehicle vehicle;
	          
	          //Switch cases for Type of vehicle and passes initialized variables into constructor for each type
	          switch(Type) {
	          //Based on type case set vehicle to constructor type
	            	case "Car":
	            		vehicle = new Car(model, make, modelYear, price, color, fuelType, mileage, cylinders, gasTankCapacity, startType, mass);
	                    break;
	                case "MotorBike":
	                	vehicle = new MotorBike(model, make, modelYear, price, color, fuelType, mileage, cylinders, gasTankCapacity, startType, mass);
	                    break;
	                case "SUV":
	                	vehicle = new SUV(model, make, modelYear, price, color, fuelType, mileage, cylinders, gasTankCapacity, startType, mass);
	                    break;
	                case "Truck":
	                	vehicle = new Truck(model, make, modelYear, price, color, fuelType, mileage, cylinders, gasTankCapacity, startType, mass);
	                	break;
	                default:
	                	//if no case applies cont
	                	continue;
	          }
	          //if vehicle is not initialized then add vehicle to vehicleList
	          if(vehicle != null) {
	        	  vehicleList.add(vehicle);
	          }
	       }
    		//return statement
    		return true;
		} catch (IOException e) {
	    	e.printStackTrace();
	    	//return statement
	        return false;
		}
	}
	
	//Todo: zj (all "display-Information" methods) vvvvvvvvvvvvvv   //XXX: All display methods for different vehicle classes are built with the same logic so comments are only included for this method
	public void displayAllCarInformation() {
		/*
		 * o This will display the information, including maintenance cost, fuel
		 * efficiency, and how the vehicle starts, of all the cars present in the
		 * vehicleList. o If the vehicle is not found, then print an appropriate error
		 * message.
		 */
		int numNOTCar = 0; //number of vehicles that are not cars will be counted in the case that cars are not found
		int carCount = 1;
		for(int i=0;i<vehicleList.size();i++) {
			if(isVehicleType(vehicleList.get(i), Car.class)) {
				System.out.println("Car "+carCount+":\n"+vehicleList.get(i).toString()); //if vehicle is car, vehicle is printed using vehicle toString()
				carCount++;
			}
			else {
				numNOTCar++;
			}
		}
		if(numNOTCar == vehicleList.size()) {
			System.out.println("No Cars Found\n");
		}
	}
	
	public void displayAllTruckInformation() {
		/*
		 * o This will display the information, including maintenance cost, fuel
		 * efficiency, and how the vehicle starts, of all the trucks present in the
		 * vehicleList. o If the vehicle is not found, then print an appropriate error
		 * message.
		 */
		int numNOTTruck = 0;
		int truckCount = 1;
		for(int i=0;i<vehicleList.size();i++) {
			if(isVehicleType(vehicleList.get(i), Truck.class)) {
				System.out.println("Truck "+truckCount+":\n"+vehicleList.get(i).toString());
				truckCount++;
			}
			else {
				numNOTTruck++;
			}
		}
		if(numNOTTruck == vehicleList.size()) {
			System.out.println("No Trucks Found\n");
		}
	}
	
	public void displayAllSUVInformation() {
		/*
		 * o This will display the information, including maintenance cost, fuel
		 * efficiency, and how the vehicle starts, of all the SUVs present in the
		 * vehicleList. o If the vehicle is not found, then print an appropriate error
		 * message.
		 */
		int numNOTSUV = 0;
		int SUVCount = 1;
		for(int i=0;i<vehicleList.size();i++) {
			if(isVehicleType(vehicleList.get(i), SUV.class)) {
				System.out.println("SUV "+SUVCount+":\n"+vehicleList.get(i).toString());
				SUVCount++;
			}
			else {
				numNOTSUV++;
			}
		}
		if(numNOTSUV == vehicleList.size()) {
			System.out.println("No SUVs Found\n");
		}
	}
	
	public void displayAllMotorBikeInformation() {
		/*
		 * o This will display the information, including maintenance cost, fuel
		 * efficiency, and how the vehicle starts, of all the motor bikes present in the
		 * vehicleList. o If the vehicle is not found, then print an appropriate error
		 * message.
		 */
		int numNOTBike = 0;
		int bikeCount = 1;
		for(int i=0;i<vehicleList.size();i++) {
			if(isVehicleType(vehicleList.get(i), MotorBike.class)) {
				System.out.println("MotorBike "+bikeCount+":\n"+vehicleList.get(i).toString());
				bikeCount++;
			}
			else {
				numNOTBike++;
			}
		}
		if(numNOTBike == vehicleList.size()) {
			System.out.println("No MotorBikes Found\n");
		}
	}
	
	public void displayVehicleInformation(Vehicle v) { //uses vehicle class toString() to print info, calculated values are appended to the bottom
		/*
		 * o This will display the vehicle information, including maintenance cost, fuel
		 * efficiency, and how the vehicle starts, of a Vehicle v which is present in
		 * the vehicleList. o If the vehicle is not found, then print an appropriate
		 * error message.
		 */
		if (vehicleList.contains(v)) {
			System.out.println(v.toString());//vehicle class default vars
			System.out.println(" Maintenance Cost: $"+v.calculateMaintenaceCost(distance)+"\n"+
								" Fuel Efficiency: "+v.calculateFuelEfficiency(distance, fuelPrice)+"\n");
		}
		else {
			System.out.println("Vehicle not found\n");
		}
	}
	
	public void displayAllVehicleInformation() { //prints "toString-ed" vehicles from list to console
		for(int i=0;i<vehicleList.size();i++) {
			System.out.println("Vehicle "+(i+1)+":\n"+vehicleList.get(i).toString());
			System.out.println(" Maintenance Cost: $"+vehicleList.get(i).calculateMaintenaceCost(distance)+"\n"+
					" Fuel Efficiency: "+vehicleList.get(i).calculateFuelEfficiency(distance, fuelPrice)+"\n");
		}
	}

	public boolean removeVehicle(Vehicle vehicle) {
		/*Removes the given vehicle from the vehicleList.
		o Returns true if the removal is successful, false otherwise.*/
	    if (vehicle != null && vehicleList.contains(vehicle)) {
	        vehicleList.remove(vehicle);
	        return true; // Vehicle successfully removed
	    }
	    return false; // Vehicle not removed, possibly not found or null
	}
	
	public boolean addVehicle(Vehicle vehicle) {
		/*o Adds the given vehicle into the vehicleList.
		o Returns true if the addition is successful, false otherwise.*/
	    if (vehicle != null) {
	        vehicleList.add(vehicle);
	        return true; // Vehicle successfully added
	    }
	    return false; // Vehicle not added, possibly null
	}
	
	//Todo: rb vvvvvvvvvvvvvv
	public boolean saveVehicleList() {
		/*
		 * o Saves the updated vehicleList back to the CSV file located at
		 * vehicleFilePath. o Overwrites the existing file with the updated data. o
		 * Returns true if the saving is successful, false otherwise (file does not
		 * exist, or file empty).
		 */
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(vehicleListFile))) {
    		//string constructing
			String[] str = new String[vehicleList.size()];
			
    		for(int i=0;i<vehicleList.size();i++) {
    			str[i] = vehicleList.get(i).getModel()+","
    					+vehicleList.get(i).getMake()+"," 							//builds comma list for each vehicle (does not include types)
    					+Long.toString(vehicleList.get(i).getModelYear())+","
    					+Double.toString(vehicleList.get(i).getPrice())+","
    					+VehicleColor.toString(vehicleList.get(i).getColor())+","
    					+FuelType.toString(vehicleList.get(i).getFuelType())+","
    					+Double.toString(vehicleList.get(i).getMileage())+","
    					+Double.toString(vehicleList.get(i).getMass())+","
    					+Integer.toString(vehicleList.get(i).getCylinders())+","
    					+Double.toString(vehicleList.get(i).getGasTankCapacity())+","
    					+StartMechanism.toString(vehicleList.get(i).getStartType())+",";
    		}
    		//file writing                //adds header line:    
    		bw.write("Type,Model,Make,ModelYear,Price,Color,FuelType,Mileage,Mass,Cylinders,GasTankCapacity,StartType,"+System.getProperty("line.separator"));
    		for(int i=0;i<vehicleList.size();i++) {   //following header, write vehicles line by line using previously constructed strings and adds appends type to beginning of line
    			if(vehicleList.get(i) instanceof Truck) {
    				bw.write("Truck,"+str[i]+System.getProperty("line.separator"));
    			}
    			else if (vehicleList.get(i) instanceof Car) {
    				bw.write("Car,"+str[i]+System.getProperty("line.separator"));
    			}
    			else if (vehicleList.get(i) instanceof SUV) {
    				bw.write("SUV,"+str[i]+System.getProperty("line.separator"));
    			}
    			else if (vehicleList.get(i) instanceof MotorBike) {
    				bw.write("MotorBike,"+str[i]+System.getProperty("line.separator"));
    			}
    		}
    		bw.close();
    		return true;
    	} catch(IOException e) {
    		e.printStackTrace();
    		return false;
    	}
	}
	
	//Todo: jm vvvvvvvvvvvvvv
	private boolean isVehicleType(Vehicle v, Class clazz) {
		/*o Checks if the given vehicle is a specific type of Vehicle subclass.
		o If the given vehicle object is the object type, then return true, otherwise return false.
		o Use instanceof or getClass() to count the number.
		o Call example: isVehicleType(vehicleObj, Truck.class);*/
	    if (v == null) return false; // Check if the vehicle is null to avoid NullPointerException.
	    return v.getClass().equals(clazz); // Check if v's class matches the specified class exactly.
	}

	
	//Todo: mj (all "get___" methods {should be pretty similar}) vvvvvvvvvvvvvv
	public int getNumberOfVehiclesByType(Class clazz) {
		
		/*
		 * o Returns the number of objects in the vehicle list based on the object
		 * vehicle type o Use the isVehicleType(Vehicle v, Class clazz) method. o Call
		 * example: getNumberOfVehichlesByType(SUV.class);
		 */
		
		// set our counter equal to 0
		int counter = 0;
		//for every vehicle in vehicleList
		for(Vehicle vehicle : vehicleList) {
			//if the vehicle is of one of types base on parameters vehicle and class
			if(isVehicleType(vehicle, clazz)) {
				//iterate up on counter
				counter++;
			}
		}
		//return counter
		return counter;
	}
	public Vehicle getVehicleWithHighestMaintenanceCost(double distance) {
	
			/*
			 * o Calculate the maintenance cost for each vehicle in the vehicle list and
			 * return the vehicle with the highest maintenance cost. o If multiple vehicles
			 * have the same maintenance cost, randomly return one of the vehicles (Use the
			 * Random class for random selection).
			 */
	    //Error handling for when the vehicle list is empty
		if(vehicleList.isEmpty()) {
			return null;
		}
		
		//These two statements initalize the vehicle maximumCostVehicle to the first vehicle in the the vehicle list and
		Vehicle maximumCostVehicle = vehicleList.get(0);
		//double to the calculated maintenance cost of the current evaluated vehicle
		double maximumMaintenanceCost = maximumCostVehicle.calculateMaintenaceCost(distance);
		//Next we iterate through the for loop base on if i is less than vehicle size
		for(int i = 1; i < vehicleList.size(); i++) {
			//Set current evaluated vehicle to vehicle list at the ith location in index
			Vehicle currentVehicle = vehicleList.get(i);
			//calculate maintenance cost of the current vehicle and store it in maintenceCost double
			double maintenanceCost = currentVehicle.calculateMaintenaceCost(distance);
			//if the maintenanceCost is less than the maximum maintenance cost execute
			if(maintenanceCost > maximumMaintenanceCost) {	
				
			maximumMaintenanceCost = maintenanceCost;
			
			maximumCostVehicle= currentVehicle;
			}
		}
		//return vehicle maximum maintenance cost vehicle
		return maximumCostVehicle;
}
		
	public Vehicle getVehicleWithLowestMaintenanceCost(double distance) {
		
			/*
			 * o Calculate the maintenance cost for each vehicle in the vehicle list and
			 * return the vehicle with the lowest maintenance cost. o If multiple vehicles
			 * have the same maintenance cost, randomly return one of the vehicles (Use the
			 * Random class for random selection).
			 */
		
		//Error handling for when the vehicle list is empty
		if(vehicleList.isEmpty()) {
			return null;
		}
		
		//These two statements initalize the vehicle minimumCostVehicle to the first vehicle in the the vehicle list and 
		Vehicle minimumCostVehicle = vehicleList.get(0);
		//double to the calculated maintenance cost of the current evaluated vehicle
		double minimumMaintenanceCost = minimumCostVehicle.calculateMaintenaceCost(distance);
		//Next we iterate through the for loop base on if i is less than vehicle size
		for(int i = 1; i < vehicleList.size(); i++) {
			//Set current evaluated vehicle to vehicle list at the ith location in index
			Vehicle currentVehicle = vehicleList.get(i);
			//calculate maintenance cost of the current vehicle and store it in maintenceCost double
			double maintenanceCost = currentVehicle.calculateMaintenaceCost(distance);
			//if the maintenanceCost is less than the minimum maintenance cost execute
			if(maintenanceCost < minimumMaintenanceCost)
			{
				//
				minimumMaintenanceCost = maintenanceCost;
				minimumCostVehicle = currentVehicle;	
				
			}
		}
		//return vehicle minimum maintenance cost vehicle
		return minimumCostVehicle;
	}

	public ArrayList<Vehicle> getVehicleWithHighestFuelEfficiency(double distance, double fuelPrice) {
		
			/*
			 * o Calculate the fuel efficiencies for each vehicle in the vehicle list and
			 * return the vehicle with the highest fuel efficiency. o If multiple vehicles
			 * have the same highest fuel efficiency, return vehicles with the same highest
			 * fuel efficiency in an ArrayList.
			 */
		
		
		//Error handling for when the vehicleList is empty return new array list//
		if(vehicleList.isEmpty()) {
			return new ArrayList<>();
		}
		
		//Create new array list for vehicle for highest fuel efficiency and initalize maxEfficeiency to double 0.0
		ArrayList<Vehicle> highestFuel = new ArrayList<>();
		double maxEfficiency = 0.0;
		
		//For vehicle in vehicleList execute
		for(Vehicle vehicle : vehicleList) {
			double fuelEfficency = vehicle.calculateFuelEfficiency(distance, fuelPrice);
			//first if the fuel efficiency of the vehicle is less than the current maxEfficiency than execute
			if(fuelEfficency > maxEfficiency) {
				
				maxEfficiency = fuelEfficency;
				//Clear highest fuel array list 
				highestFuel.clear();
				//Add the current highest efficiency vehicle to the list
				highestFuel.add(vehicle);
			//else if fuel efficiency and max efficiency are equal execute
			} else if (fuelEfficency == maxEfficiency){
				//add the current vehicle to array list
				highestFuel.add(vehicle);
			}
		}
		//Return the array list highest Fuel vehicle
		return highestFuel;
	}
	
		
	public ArrayList<Vehicle> getVehicleWithLowestFuelEfficiency(double distance, double fuelPrice) {
		
			/*
			 * Calculate the fuel efficiencies for each vehicle in the vehicle list and
			 * return the vehicle with the lowest fuel efficiency. If multiple vehicles have
			 * the same lowest fuel efficiency, return vehicles with the same lowest fuel
			 * efficiency in an ArrayList.
			 */
		//Error handling for when the vehicleList is empty return new array list//
		if(vehicleList.isEmpty()) {
			return new ArrayList<>();
		}
		
	    //Create new array list for vehicle for lowest fuel efficiency and initalize min Efficeiency to double 0.0
		ArrayList<Vehicle> lowestFuel = new ArrayList<>();
		double minEfficiency = 0.0;
		
		
		for(Vehicle vehicle : vehicleList) {
			double fuelEfficency = vehicle.calculateFuelEfficiency(distance, fuelPrice);
			//first if the fuel efficiency of the vehicle is greater than the current minEfficiency than execute
			if(fuelEfficency > minEfficiency) {
				minEfficiency = fuelEfficency;
				lowestFuel.clear();
				lowestFuel.add(vehicle);
			//else if fuel efficiency and min efficiency are equal execute
			} else if (fuelEfficency == minEfficiency){
				//add the current vehicle to array list
				lowestFuel.add(vehicle);
			}
		}
		//Return the array list lowest Fuel vehicle
		return lowestFuel;
		
		//make sure to switch to low instead of high
	}
	

	public double getAverageFuelEfficiencyOfSUVs(double distance,double fuelPrice) {
		
			/*
			 * Calculate the average/mean of the fuel efficiency of SUVs in the vehicle
			 * list. Use the isVehicleType(Vehicle v, Class clazz) method. If no SUVs exist
			 * in the list return -1.0 as an error code that indicates there is no SUVs in
			 * the list to calculate the average fuel efficiency
			 */
		
		//setting our temporary number equal number of vehicle of type SUV
		int suvTemp = getNumberOfVehiclesByType(SUV.class);
		//if there are 0 suvs in the file error handle
		if (suvTemp == 0) {
		//Error handling for when no suvs in file//
			return -1.0;
		}
		//setting our efficiency equal to 0
		double totalEfficiency = 0;
		//For every vehicle in the vehicle list execute
		for(Vehicle vehicle : vehicleList) {
			//if a vehicle is an SUV
			//the efficiency of fuel = the fuel efficiency of the vehicle + the efficiency of the fuel
			if(vehicle instanceof SUV){
				totalEfficiency += vehicle.calculateFuelEfficiency(distance, fuelPrice);
			}
		}
		//setting average equal to the total efficiency of all suvs in the file divided by the number of suv to find average
		double average = totalEfficiency / suvTemp;
		//Then we return double average;
		return average;
	}
}
