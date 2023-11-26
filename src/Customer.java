

import java.util.Date;

/**
 *The customer class represents a customer on a system. It stores infomation.
 *such as the customer's name, email, date of birth and password number.  
 @author Sayra Reyes
 @version 1.0
 */

public class Customer {
	
	private String name;      /**  Represents Customer's name */
	private String email;       /**  Represents Customer's email */
	private Date DOB;             /**  Represents Customer's Date of Birth */
	private String passportNo;      /**  Represents Customer's passport number */
	
/**
 * Customer for the customer class 
 * @param name1 : name of the customer class
 * @param email1 : email of the customer 
 * @param DOB : the date of birth of the customer.
 * @param passportNo1 : the passport number of the customer. 
 */
 public Customer(String name1, String email1, Date DOB, String passportNo1) {
	this.name = name1;
	this.DOB = DOB;
	this.passportNo = passportNo1;
	this.email = email1;
	
   }

  
 /**
  * Getter method to return the name of the customer
  * @return
  */
  public String getname() {
	 return name;
 }

  /**
   * Getter method to returns the date of birth of the customer. 
   * @return
   */
  public Date getDOB() {
	return DOB;
 }

  /**
   * Getter method returns the passport number of the customer. 
   * @return
   */
  public String getpassportNo() {
	return passportNo;
 }

  /**
   * Getter method returns the email of the customer. 
   * @return
   */
  public String getEmail() {
  	return email;
 }
  


}

