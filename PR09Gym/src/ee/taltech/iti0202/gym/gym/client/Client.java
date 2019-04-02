package ee.taltech.iti0202.gym.gym.client;

public class Client {
    private final String firstName;
    private final String lastName;
    private String gender;
    private Integer age;
    private String email;
    private String mobileNumber;
    private String creditCardNumber;

    public Client(String firstName, String lastName, String gender, Integer age, String email,
                  String mobileNumber, String creditCardNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.age = age;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.creditCardNumber = creditCardNumber;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getGender() {
        return gender;
    }

    public Integer getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}
