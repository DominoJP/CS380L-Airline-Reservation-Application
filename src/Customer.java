/**
 
 
 @author 
 @version 
 */

import java.util.Date;

public class Customer {
	
	private String name;  /**  Represents Customer's name */
	private String email;    /**  Represents Customer's email */
	private Date DOB;       /**  Represents Customer's Date of Birth */
	private String passportNo;  /**  Represents Customer's passport number */
	
/**
 * 
 * @param name1
 * @param email1
 * @param DOB
 * @param passportNo1
 */
 public Customer(String name1, String email1, Date DOB, String passportNo1) {
	this.name = name1;
	this.DOB = DOB;
	this.passportNo = passportNo1;
	this.email = email1;
	
   }

  
 /**
  * Getter method 
  * @return
  */
  public String getname() {
	 return name;
 }

  /**
   * Getter method
   * @return
   */
  public Date getDOB() {
	return DOB;
 }

  /**
   * Getter method
   * @return
   */
  public String getpassportNo() {
	return passportNo;
 }

  /**
   * Getter method 
   * @return
   */
  public String getEmail() {
  	return email;
 }
  


}

