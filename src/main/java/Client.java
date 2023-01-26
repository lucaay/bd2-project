public class Client {
    private int id;
    private String name;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String state;
    private String city;
    private String address;

    MysqlCon mysqlCon = new MysqlCon();

    public Client(int id, String name, String lastName, String email, String phoneNumber, String state, String city, String address) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.state = state;
        this.city = city;
        this.address = address;
    }

    public Object[] clientObject(){
        return new Object[]{id, name, lastName, email, phoneNumber, state, city, address};
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }



}
