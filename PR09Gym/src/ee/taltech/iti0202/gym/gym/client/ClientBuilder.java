package ee.taltech.iti0202.gym.gym.client;

public class ClientBuilder {
    private String firstName;
    private String lastName;
    private String gender;
    private Integer age;
    private String email;
    private String mobileNumber;
    private String creditCardNumber;

    public ClientBuilder(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public ClientBuilder setGender(String gender) {
        this.gender = gender;
        return this;
    }

    public ClientBuilder setAge(Integer age) {
        this.age = age;
        return this;
    }

    public ClientBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public ClientBuilder setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
        return this;
    }

    public ClientBuilder setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
        return this;
    }

    public Client createClient() {
        return new Client(firstName, lastName, gender, age, email, mobileNumber, creditCardNumber);
    }
}
