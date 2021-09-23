import java.util.*;

public class Person{

	protected HashMap<String, Double> valuesMap =new HashMap<String, Double>();
	protected ArrayList<String> options = new ArrayList<String>();
	protected double physicalHealth;
	protected double mentalHealth;
	protected double wealth;
	protected double fullness;
	protected double timeLeft;
	protected double initialScore;
	protected int tier =1;
	protected int workPerformance;
	
	/**
	* Initiates the basic functions of the persion and puts them into the valuesMap.
	* Puts the basic options into the ArrayList options.
	*/
	public Person(){
		setRandomAttributes();
		valuesMap.put("physicalHealth", physicalHealth);
		valuesMap.put("mentalHealth", mentalHealth);
		valuesMap.put("wealth", wealth);
		valuesMap.put("fullness", fullness);
		options.add("Eat: 1");
		options.add("Meditate: 2");
		options.add("Gym: 3");
		options.add("Sleep: 4");
		options.add("Work: 5");
	}
	/**
	* Takes an input from the user as a double and returns it. 
	* @returns double object corresponding to the value entered.
	*/	
	public double getInputDouble(){
		Scanner scanner= new Scanner (System.in);
		return scanner.nextDouble();
	}
	/**
	* Sets random attributes to all the variables.
	*
	*/
	public void setRandomAttributes(){
		physicalHealth = getRandomNumber(60,80);
		mentalHealth = getRandomNumber(60,80);
		wealth = getRandomNumber(60,80);
		fullness = getRandomNumber(60,80);
		
	}
	/**
	* Returns a random double number with 2 decimals only.
	* @param double min the minimum number of the range.
	* @param double max the maximum number of the range.
	* @returns a random number between a minimum number and maximum numer with 2 decimals only.
	*/
	protected double getRandomNumber(double min, double max) {
		//return 0;
		int a = (int) min *100;
		int b = (int) max * 100;
		int c = ((int) ((Math.random() * (b - a)) + a));
    	return (double) c/100 ;
	}
	
	/**
	* Performs eat operation by calling the eatHelper method with the amount to eat entered by the user.
	*/
	public void eat (){
		System.out.print("Amount to eat : ");
		Scanner scanner = new Scanner(System.in);
		eatHelper(scanner.nextDouble());
	}
	/**
	* performs the actual eating and changes the attributes corresponding to the amount provided.
	* @param double amount the amount of food to eat.
	*/
	public void eatHelper(double amount){
		
		fullness+= amount;
		wealth -= amount/2;
		if (fullness < 60 ) physicalHealth-= (fullness-60)/5;
		if (physicalHealth <35) mentalHealth -= (35-physicalHealth)/5;
		useTime(1);
	}
	/**
	* Performs meditation for 1 hour only and changes the corresponding attributes.
	*/
	public void meditate(){
		mentalHealth += 1 - effiencyFactor();
		useTime(1);
	}
	/**
	* Used to calculate the deviaton from the perfect performance of the person.
	* An example would be going to the gym while not eating enough, so the gains would be lower. 
	* Must use effiencyFactor after a negative sign becasue it returns negative as good and positive as bad.
	* @returns double effiencyFactor negative if he is in a good mode and positive if in bad mode.
	*/
	public double effiencyFactor(){
		double mentalFactor = (75-mentalHealth)/100;
		double physicalFactor = 0;
		if (fullness > 75 && fullness < 90) physicalFactor = -Math.abs(82-fullness)/82;
		else physicalFactor = (Math.abs(fullness-100)/100);

		return mentalFactor + physicalFactor;
	}
	/**
	* Simulates the passage of time by decreasing the amount of time provided and making the person feel more hungry.
	* Also updates the values in the Map.
	* @param double amount the hours simulated to pass.
	*/
	public void useTime(double amount){
		timeLeft= timeLeft-amount;
		fullness -= amount/4;
		valuesMap.replace ("fullness", fullness);
		valuesMap.replace("physicalHealth", physicalHealth);
		valuesMap.replace("mentalHealth", mentalHealth);
		valuesMap.replace("wealth", wealth);
	}
	/**
	* Simulates going to the gym for one hour only and changes the corresponding attributes.
	*/
	public void gym(){
		physicalHealth ++;
		wealth -= 1/10;
		fullness-=2;
		useTime(1);
	}
	/**
	* Calls the method sleepHelper with the number of hours provided by the user.
	*/
	public void sleep(){
		System.out.println("Hours to sleep : ");
		Scanner scanner = new Scanner(System.in);
		sleepHelper(scanner.nextDouble());
	}
	/**
	* Makes the person sleep for the amount of hours provided. Thus, retaining good mental and physical health.
	* @param double hours the amount of hours to sleep.
	*/
	public void sleepHelper(double hours){
		mentalHealth++;
		physicalHealth++;
		useTime(hours);
	}

	/**
	* Makes the person work for the amount of hours inputted by the user. Thus,increasing wealth and decreasing mentalHealth. Accounts for promotions as the tier of the person.
	*/
	 
	public void work(){
		System.out.print("Amount of hours to work: ");
		double hours = getInputDouble();
		if (tier ==1){
			wealth += (hours/6 + effiencyFactor());
			
			mentalHealth -= (hours/6 - effiencyFactor());
		}
		if (tier ==2){
			wealth += (hours/4 + effiencyFactor());
			
			mentalHealth -= (hours/8 - effiencyFactor());
		}
		if (tier ==3){
			wealth += (hours/2 + effiencyFactor());
			mentalHealth -= (hours/10 - effiencyFactor());
		}
		workPerformance += hours/2;

		if (workPerformance <40) tier = 1;
		else if (workPerformance <70 && 40 < workPerformance) tier = 2;
		else tier = 3;
		useTime(hours);
	}
	/**
	* Prints the current state of the person to the user.
	*/
	public void printState(){
		Iterator it = valuesMap.entrySet().iterator();
		while (it.hasNext()){
				Map.Entry pair = (Map.Entry)it.next();
				System.out.println(pair.getKey() + " = " + pair.getValue());
				//it.remove(); // avoids a ConcurrentModificationException
			}
	}
	/**
	* Prints the available options to the user.
	*/
	public void printOptions(){
		for (String item : options){
			System.out.println(item);
		}
	}
	/**
	* Calls the function corresponding to the input of the user.
	*/
	public void callFunction(int option){
		if (option == 1) eat();
		if (option == 2) meditate();
		if (option == 3) gym();
		if (option == 4) sleep();
		if (option == 5) work(); 
	}
	/**
	* calculates the score of the player and returns it.
	* Different attributes have Different weigths
	* @returns double number corresponding to the current score of the player. 
	*/
	public double getScore(){
		return (physicalHealth * 1.2 + mentalHealth * 1.4 + fullness *0.9 + wealth * 1.3) / 4;
	}
	/**
	* Performs one round of playing. One round is 1 week by default.
	*/

	public void play(){
		int i = 0;
		System.out.print("How many hours do you want to play? ");
		timeLeft = getInputDouble();
		initialScore = getScore();
		while (timeLeft > 0 ){
			System.out.println ("*** Round " + i +" ***");
			System.out.println("TimeLeft = " + timeLeft);
			printState();
			System.out.println ("What action do you want to take: ");
			System.out.println ("///Your options///");
			printOptions();
			Scanner scanner = new Scanner(System.in);
			callFunction ( scanner.nextInt()) ;
			System.out.println("________________");
			if (timeLeft > 0)
			System.out.println("Score until now is: " + (getScore()- initialScore));
			
			i++;
		}
		System.out.println("Your score is: " + " "  +  (getScore() - initialScore) );
		
	}

	public static void main (String [] arg){
		System.out.print("What personality do you want to play? \n 1- Person \n 2- Student \n 3-parent \n 4-Athlete \n Please enter a number: ");
		Scanner scanner = new Scanner(System.in);
		int player = scanner.nextInt();
		if (player == 1) {
			Person tester = new Person();
			tester.play();
		}
		else if (player == 2){
			Student tester = new Student();
			tester.play();
		}
		else if (player ==3){
			Parent tester = new Parent();
			tester.play();
		}
		else if (player == 4){
			Athlete tester = new Athlete();
			tester.play();
		}
		else System.out.println("Please enter a valid number!");
		
	}
}