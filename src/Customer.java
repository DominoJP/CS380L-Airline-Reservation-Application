
import java.util.Date;

//Define Customer class
public class Customer {
	
	private String name;  //name of customer 
	private String email; 
	private Date DOB;
	private String passportNo;
	

//Constructor - Customer 
public Customer(String name1, String email1, Date DOB1, String passportNo1) {
	this.name = name1;
	this.DOB = DOB1;
	this.passportNo = passportNo1;
	this.email = email1;
	
   }

  //Getters - Customer 
  public String getname() {
	 return name;
 }

  public Date getDOB() {
	return DOB;
 }

  public String getpassportNo() {
	return passportNo;
 }

  public String getEmail() {
  	return email;
 }
  
  }

