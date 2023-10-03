import java.util.List;

//Define Account class 
class Account{
	 private String name;
	 private String email;
	 private String password;
	 private int accountnumber;
	 private List<Reservation> reservationHistory;
	 
 public void reserve(Flight flight, List<String> passenger, Customer customer1) {
	 //Implementation to make a reservation flight 
	
	 
 }
 
 public void cancelReservation(Reservation reservation) {
	 //Implementation to cancel a reservation 

	 
	 
 }
 
 public void changeReservation(Reservation reservation, Flight newflight){
	 //Implementation to change a reservation 
 
   }
 
 public void reviewFlightDetails(Flight flight) {
	 //Implementation to review flight details
	 
 }
 
 //Constructor - Account
 public Account(String name, String email, String password, int accountnumber) {
	 this.name = name;
	 this.email = email;
	 this.setPassword(password);
	 this.accountnumber = accountnumber;
 }
 
 //Getters - Account
 public String getname() {
	 return name;
	 
 }
 
 public String getemail() {
	 return email;
 }
 
 public int accountnumber() {
	 return accountnumber;
 }
 
 public boolean checkpassword(String customer2) {
	
	 
	 return false;
	 
   }

 public List<Reservation> getReservationHistory() {
	return reservationHistory;
 }

 public void setReservationHistory(List<Reservation> reservationHistory) {
	this.reservationHistory = reservationHistory;
 }

 public String getPassword() {
	return password;
 }

 public void setPassword(String password) {
	this.password = password;
 }
 
}
 