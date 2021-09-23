public class Parent extends Person{
	protected double familyLove;
	protected int tier;
	protected int workPerformance;
	
	/**
	* Constructs the student objects and adds its special attributes to the map and its options to the options ArrayList.
	*/
	public Parent(){
		familyLove = getRandomNumber(40,60);
		valuesMap.put("familyLove", familyLove);
		options.add("spendTimeWithFamily: 6");
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
		if (option == 6) spendTimeWithFamily();
	}
	
	/**
	* Makes the parent spend time with family thus changing the attributes.
	* Increases mental health, but decreases health.
	* Takes an input from the user the hours to spend with family. 
	*/
	public void spendTimeWithFamily(){
		System.out.print("Hours to spend with family: ");
		double hours = getInputDouble();
		wealth -= 1/12;
		familyLove+= hours;
		mentalHealth += (hours/2 + effiencyFactor());
		useTime(hours);
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
		fullness -= amount/3;
		familyLove-=1/8;
		valuesMap.replace ("fullness", fullness);
		valuesMap.replace("physicalHealth", physicalHealth);
		valuesMap.replace("mentalHealth", mentalHealth);
		valuesMap.replace("wealth", wealth);
		valuesMap.replace("familyLove", familyLove);
	}

	/**
	* calculates the score of the player and returns it.
	* Different attributes have Different weigths and special attributes for student are accounted for.
	* @returns double number corresponding to the current score of the player. 
	*/
	@Override
	public double getScore(){
		return (physicalHealth * 1.2 + mentalHealth * 1.4 + fullness *0.9 + wealth * 1.3 + familyLove * 1.8 ) / 5;
	}
}