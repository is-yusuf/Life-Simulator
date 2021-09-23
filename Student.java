import java.util.*;

public class Student extends Person{
	protected double GPA;
	protected double sociability;
	
	/**
	* Constructs the student objects and adds its special attributes to the map and its options to the options ArrayList.
	*/
	public Student(){
		GPA = getRandomNumber(40,60);
		sociability = getRandomNumber(40,60);
		valuesMap.put("GPA", GPA);
		valuesMap.put("sociability", sociability);
		options.add("Study: 6");
		options.add("playGames: 7");
		options.add("doActivities: 8");
	}
	/**
	* Calls the function corresponding to the input of the user. Overrides the call function of the super class person.
	*/
	@Override
	public void callFunction(int option){
		if (option == 1) eat();
		if (option == 2) meditate();
		if (option == 3) gym();
		if (option == 4) sleep();
		if (option == 5) work();
		if (option == 6) study();
		if (option == 7) playGames();
		if (option == 8) doActivities();
	}
	/**
	* Simulates the passage of time by decreasing the amount of time provided and making the person feel more hungry.
	* Also updates the values in the Map.
	* Overrides the super class.
	* @param double amount the hours simulated to pass.
	*/
	@Override
	public void useTime(double amount){
		timeLeft= timeLeft-amount;
		fullness -= amount/4;
		GPA -= amount/12;
		valuesMap.replace ("fullness", fullness);
		valuesMap.replace("physicalHealth", physicalHealth);
		valuesMap.replace("mentalHealth", mentalHealth);
		valuesMap.replace("wealth", wealth);
		valuesMap.replace("GPA", GPA);
		valuesMap.replace("sociability",sociability);
	}
	/**
	* Calls the method studyHelper with the number of hours inputted by the user.
	*/
	public void study (){
		System.out.print("Hours to study: ");
		studyHelper(getInputDouble());
	}
	/**
	* Makes the student study for the inputted hours, and changes corresponding attributes.
	* @param double hours the amount of hours to study.
	*/
	public void studyHelper(double hours){
		GPA += (hours/3 + effiencyFactor());
		mentalHealth-= hours/4;
		useTime(hours);
	}
	/**
	* Makes the student do activities for the amount of hours inputted by the user and changes the corresponding attributes.
	*/
	public void doActivities(){
		System.out.print("Hours to do activities: ");
		double hours = getInputDouble();
		sociability += hours/2;
		mentalHealth +=(hours/4 - effiencyFactor()) ;
		useTime(hours);
	}
	/**
	* Plays games with the amount of hours provided by the user and changes the corresponding attributes.
	*/
	public void playGames(){		
		System.out.print("hours to play: ");
		double hours = getInputDouble();
		mentalHealth += (hours/4 - effiencyFactor());
		useTime(hours);
	}
	/**
	* Makes the student work for the amount of hours provided. Thus,increasing wealth and decreasing mentalHealth. Takes into account the lower wage of students.
	* Overrides the workHelp of the super class person.
	* @param double hours the amount of hours to work.
	*/
	@Override
	public void work(){ 
		System.out.print("Hours to work: ");
		double hours = getInputDouble();
		wealth += hours/6;
		mentalHealth -= hours/10;
		useTime(hours);
	}
	/**
	* calculates the score of the player and returns it.
	* Different attributes have Different weigths and special attributes for student are accounted for.
	* @returns double number corresponding to the current score of the player. 
	*/
	@Override
	public double getScore(){
		return (physicalHealth * 1.2 + mentalHealth * 1.4 + fullness *0.9 + wealth * 1.3 + GPA * 1.8 + sociability *1.3) / 6;
	}
}
