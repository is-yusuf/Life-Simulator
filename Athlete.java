public class Athlete extends Person {
	protected double athleticPerformance;
	/**
	* Constructs the Athlete object and adds its special attributes to the map and its options to the options ArrayList.
	*/
	public Athlete(){
		athleticPerformance = getRandomNumber(40,60);
		valuesMap.put("athleticPerformance", athleticPerformance);
		options.add("Massage: 6");
	}
	/**
	* Peforms the eating and asks the user to input the amount to eat and the degree of healthiness of food. Overrides the eat method of super class person.
	*/
	@Override
	public void eat (){
		System.out.print("Amount of food: ");
		double amount = getInputDouble();
		System.out.print("\n Degree of healthiness 0-10: ");
		double healthiness = getInputDouble();

		fullness+= amount;
		wealth -= ((amount/2) + healthiness/10);
		if (fullness < 60 ) physicalHealth -= (fullness-60)/5;
		if (physicalHealth <35) mentalHealth -= (35-physicalHealth)/5;
		useTime(1);
		if (healthiness <5) athleticPerformance += amount/10;
		else athleticPerformance += amount/4 + effiencyFactor();
		mentalHealth += Math.abs (5-healthiness)/5 + effiencyFactor();
		useTime(1);
	}
	/**
	* Simulates going to the gym for the number of hours inputted by the user.
	*/
	@Override
	public void gym (){
		System.out.print("\n Hours to workout: ");

		double hours = getInputDouble();
		if (hours > 2.5 || hours < 1 ){
			athleticPerformance -= Math.abs(2-hours)/2;
			if (hours > 2.5){
				fullness -=5;
			}
			else {
				fullness -=3;
			}
			physicalHealth -= 1;
		}
		//healthy timing
		else {
			athleticPerformance+= Math.abs(2-hours)/2 + effiencyFactor();
			fullness -=4;
			physicalHealth += 1;
		}
		//healthiness comes at a cost.
		wealth -=0.25;
		useTime(hours);
	}

	/**
	* Simulates the passage of time by decreasing the amount of time provided and making the person feel more hungry and taking into account the muscle recovery process.
	* Also updates the values in the Map.
	* Overrides the super class mathod useTime.
	* @param double amount the hours simulated to pass.
	*/
	@Override
	public void useTime(double amount){
		timeLeft= timeLeft-amount;
		fullness -= amount/3;
		if (fullness > 75){
			athleticPerformance -= (0.1 - effiencyFactor()) * amount /2;		
			physicalHealth -= (0.1 - effiencyFactor()) * amount /2;	
		}
		else {
			athleticPerformance += (0.1 - effiencyFactor()) *amount/2;
			physicalHealth += (0.1 - effiencyFactor()) *amount/2;
		}

		valuesMap.replace ("fullness", fullness);
		valuesMap.replace("physicalHealth", physicalHealth);
		valuesMap.replace("mentalHealth", mentalHealth);
		valuesMap.replace("wealth", wealth);
		valuesMap.replace("athleticPerformance", athleticPerformance);
	}
	/**
	* Simulates going to take a message and changes the corresponding attributes.
	*/
	public void massage(){
		physicalHealth +=1;
		wealth -= 0.5;
		useTime(1);
	}

	/**
	* calculates the score of the player and returns it.
	* Different attributes have Different weigths and special attributes for student are accounted for.
	* @returns double number corresponding to the current score of the player. 
	*/
	@Override
	public double getScore(){
		return (physicalHealth * 1.2 + mentalHealth * 1.4 + fullness *0.9 + wealth * 1.3 + athleticPerformance * 1.8) / 5;
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
		if (option == 6) massage();
		
	}
}