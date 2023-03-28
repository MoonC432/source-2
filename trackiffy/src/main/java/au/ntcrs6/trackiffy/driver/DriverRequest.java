package au.ntcrs6.trackiffy.driver;

import java.text.ParseException;
import java.util.Date;

// Driver details
// Date format : yyyy-mm-dd
public class DriverRequest {
    private final String firstName;
    private final String lastName;
    private final int experience;
    private final String address;
    private final String status;
    private final int record;
    private final Date dateOfBirth;
    private final char sex;
    private final String licenceClass;
    private final String province;
    private final int height;

    public DriverRequest(String firstName, String lastName, int experience, String address, String status,
            int record, String dateOfBirth, char sex, String licenceClass, String province, int height)
            throws ParseException {
        this.firstName = firstName;
        this.lastName = lastName;
        this.experience = experience;
        this.address = address;
        this.status = status;
        this.record = record;
        this.dateOfBirth = new DateSerializer(dateOfBirth).getParsedDate();
        this.sex = sex;
        this.licenceClass = licenceClass;
        this.province = province;
        this.height = height;
    }

    public String getName() {
        return firstName + " " + lastName;
    }

    @Override
    public String toString() {
        return "DriverRequest{" +
                "name='" + firstName + " " + lastName + '\'' +
                ", address='" + address + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                '}';
    }

}
