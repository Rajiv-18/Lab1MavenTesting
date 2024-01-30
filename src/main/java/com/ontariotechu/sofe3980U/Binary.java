package com.ontariotechu.sofe3980U;

/**
 * Unsigned integer Binary variable
 *
 */
public class Binary
{
	private String number="0";  // string containing the binary value '0' or '1'
	/**
	* A constructor that generates a binary object.
	*
	* @param number a String of the binary values. It should conatins only zeros or ones with any length and order. otherwise, the value of "0" will be stored.   Trailing zeros will be excluded and empty string will be considered as zero.
	*/
    public Binary(String number) {
		for (int i = 0; i < number.length(); i++) {
			// check each character if it's not 0 or 1
			char ch=number.charAt(i);
			if(ch!='0' && ch!='1') {
				number="0"; // if not store "0" and end the function
				return;
			}
		}
		// remove any trailing zeros
		int beg;
		for (beg = 0; beg < number.length(); beg++) {
			if (number.charAt(beg)!='0')
				break;
		}
		//beg has the index of the first non zero digit in the number
		this.number=number.substring(beg); // exclude the trailing zeros if any
		// uncomment the following code

		if(this.number=="") { // replace empty strings with a single zero
			this.number="0";
		}
		
    }
	/**
	* Return the binary value of the variable
	*
	* @return the binary value in a string format.
	*/
	public String getValue()
	{
		return this.number;
	}
	/**
	* Adding two binary variables. For more information, visit <a href="https://www.wikihow.com/Add-Binary-Numbers"> Add-Binary-Numbers </a>.
	*
	* @param num1 The first addend object
	* @param num2 The second addend object
	* @return A binary variable with a value of <i>num1+num2</i>.
	*/
	public static Binary add(Binary num1,Binary num2)
	{
		// the index of the first digit of each number
		int ind1=num1.number.length()-1;
		int ind2=num2.number.length()-1;
		//initial variable
		int carry=0;
		String num3="";  // the binary value of the sum
		while(ind1>=0 ||  ind2>=0 || carry!=0) // loop until all digits are processed
		{
			int sum=carry; // previous carry
			if(ind1>=0){ // if num1 has a digit to add
				sum += (num1.number.charAt(ind1)=='1')? 1:0; // convert the digit to int and add it to sum
				ind1--; // update ind1
			}
			if(ind2>=0){ // if num2 has a digit to add
				sum += (num2.number.charAt(ind2)=='1')? 1:0; // convert the digit to int and add it to sum
				ind2--; //update ind2
			}
			carry=sum/2; // the new carry
			sum=sum%2;  // the resultant digit
			num3 =( (sum==0)? "0":"1")+num3; //convert sum to string and append it to num3
		}
		Binary result=new Binary(num3);  // create a binary object with the calculated value.
		return result;
		
	}

	public static Binary or(Binary num1, Binary num2)
	{
		// Variables to store the length of given two binary variables
		int ind1 = num1.number.length();
		int ind2 = num2.number.length();
		int carry = 0;// Used to store our carry while adding
		StringBuilder result = new StringBuilder();

		// Using a ternary operator to find out the maximum length to ensure that it iterates over each digit of the given two binary variables used in our for loop
		int variableLength = (ind1 > ind2) ? ind1 : ind2;

		for (int i = 0; i < variableLength || carry != 0; i++){
			
			// Getting each bit from the two binary variables
			int firstBit = (i<ind1) ? Character.getNumericValue(num1.number.charAt(ind1 - 1 - i)) : 0;
			int secondBit = (i<ind2) ? Character.getNumericValue(num2.number.charAt(ind2 - 1 - i)) : 0;

			int addition = firstBit | secondBit | carry;
			carry = (firstBit + secondBit + carry) > 1 ? 1 : 0;

			// Ensure to add the carry to the sum into the string
			result.insert(0, addition);
		}

		return new Binary(result.toString());
		
	}

	public static Binary and(Binary num1, Binary num2)
	{
		// Variables to store the length of given two binary variables
		int ind1 = num1.number.length();
		int ind2 = num2.number.length();
		StringBuilder result = new StringBuilder();

		// Using a ternary operator to find out the minimum length to ensure both binary variables don't have different lengths and to ensure and is performed on the common bits.
		int variableLength = (ind1 < ind2) ? ind1 : ind2;

		for (int i = 0; i < variableLength; i++){
			
			// Getting each character bit in the binary variable and storing into variables
			char firstBit = num1.number.charAt(i);
			char secondBit = num2.number.charAt(i);
			// If both bits from both binary variables are 1 then it should print out 1 for the following binary variable position, and if not print out 0

			result.append((firstBit == '1' && secondBit == '1') ? '1' : '0');
		}

		return new Binary(result.toString());

	}

	public static Binary multiply(Binary num1, Binary num2)
	{
		// Initialize both the first multiplicand and the result
		Binary result = new Binary("0");
		Binary firstMultiplicand = num1;

		// Making sure each digit is multiplied until num2 is 0
		while (!num2.getValue().equals("0")){
			if (num2.getValue().charAt(num2.getValue().length() - 1) == '1'){
				result = Binary.add(result, firstMultiplicand);
			}
			firstMultiplicand = Binary.add(firstMultiplicand, firstMultiplicand);
			num2 = new Binary(num2.getValue().substring(0, num2.getValue().length() - 1));
		}
		
		// return final result
		return result;

	}

}	
