// Name: Tyler Gauntlett
// NID: ty340586
// Course: COP3503C-16Spring 0001
// Assignment: Lottery
// Date: 1/19/2016

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Scanner;

public class Lottery {

	static int Count;
	static int Size = 6;

	static void LoadData() throws Exception {
		// Create a scanner to read in the file name from user input.
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("Enter the name of the file with the ticket data.");
		String fileName = in.readLine();

		// Create a scanner used to take in file information.
		Scanner winningLotto = new Scanner(System.in);

		int[] winningArray = new int[Size];
		int[] personArray = new int[Size];

		System.out.println("Enter the winning Lottery numbers");
		// Fills an array with user entered lotto numbers.
		for (int i = 0; i < Size; i++) {
			winningArray[i] = winningLotto.nextInt();
		}

		// Create a scanner based on a user input above.
		URL openFile = Lottery.class.getResource(fileName);
		Scanner input = new Scanner(new File(openFile.toURI()));

		// Number if input cases, decided by the first integer in the input
		// file.
		Count = input.nextInt();

		// Dynamically allocated array of objects.
		Person[] contestantArray = new Person[Count];

		// Loop through all data in the input file.
		for (int currentCount = 0; currentCount < Count; currentCount++) {
			
			// Initialize count to 0 after every pass.
			int compareCount = 0;

			// Creates a person object using a default constructor.
			Person storePerson = new Person();

			// Fills the object array with default objects. Index is
			// given by current loop value.
			contestantArray[currentCount] = storePerson;

			// Set first and last name from file input to
			// an object.
			contestantArray[currentCount].setLastName(input.next());
			contestantArray[currentCount].setFirstName(input.next());

			// Fills the array with file input lotto numbers.
			for (int i = 0; i < Size; i++) {
				personArray[i] = input.nextInt();
			}

			// Compares the user entered numbers to the numbers found
			// in the file. If they are equal, a counter is increased.
			for (int i = 0; i < Size; i++) {
				for (int j = 0; j < Size; j++) {
					if (winningArray[i] == personArray[j]) {
						compareCount++;
					}
				}
			}

			// Store the number of matching numbers into a person object.
			contestantArray[currentCount].setCompareCount(compareCount);

		}

		// Loop through object array.
		for (int i = 0; i < Count; i++) {
			int amount = 0;
			
			// Switch statement to decide amount won based on numbers
			// matched.
			switch (contestantArray[i].getCompareCount()) {
			case 3:
				amount = 10;
				break;
			case 4:
				amount = 100;
				break;
			case 5:
				amount = 10000;
				break;
			case 6:
				amount = 1000000;
				break;
			default:
				amount = 0;
			}

			// Output to console if they won some value of money.
			if (amount != 0) {
				System.out.println(contestantArray[i].getFirstName() + " " + contestantArray[i].getLastName()
						+ " matched " + contestantArray[i].getCompareCount() + " numbers and won $" + amount + ".");
			}
		}

		winningLotto.close();
		input.close();
	}

	
	// Person class used to create an object holding all
	// relevant information.
	static class Person {

		private String firstName;
		private String lastName;
		private int compareCount;

		// Initialize default constructor.
		public Person() {
			firstName = "";
			lastName = "";
			compareCount = 0;
		}

		public String getFirstName() {
			return firstName;
		}

		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}

		public String getLastName() {
			return lastName;
		}

		public void setLastName(String lastName) {
			this.lastName = lastName;
		}

		public int getCompareCount() {
			return compareCount;
		}

		public void setCompareCount(int compareCount) {
			this.compareCount = compareCount;
		}
		
	}

	

	public static void main(String[] args) {
		
		// Exception handler increase the input fails.
		try {
			LoadData();
		}

		catch (Exception ex) {
		}

	}
}