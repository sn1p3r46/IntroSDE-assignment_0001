package models;


//health profile class

public class HealthProfile {
	private double weight; // in kg
	private double height; // in m
	private double bmi;
	private String uDate;

	// constructor method

	public HealthProfile(double weight, double height, double bmi, String uDate) {
		this.weight = weight;
		this.height = height;
		this.bmi = bmi;
		this.uDate = uDate;
	}

	// some helper methods
	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public String toString() {
		return "Height="+height+", Weight="+weight + ", BMI="+bmi+", Update Date="+uDate;
	}
}
