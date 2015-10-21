package models;

public class HealthProfile {
	private double weight; // in kg
	private double height; // in m
	private double bmi;
	private String uDate;

	public HealthProfile(double weight, double height) {
		this.weight = weight;
		this.height = height;
	}

	public HealthProfile(double weight, double height, double bmi, String uDate) {
		this.weight = weight;
		this.height = height;
		this.bmi = bmi;
		this.uDate = uDate;
	}


	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}
	public String toString() {
		return "Height="+height+", Weight="+weight + ", BMI="+bmi+", Update Date="+uDate;
	}
	
	public double getBMI(){
		return this.getWeight()/Math.pow(this.getHeight(), 2);
	}

	// add accessor for the newly created BMI
	// the getter can respond with the calculation (weight divided the height in meters elevated to the power of 2) 
}
