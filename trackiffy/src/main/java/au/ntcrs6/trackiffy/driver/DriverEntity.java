package au.ntcrs6.trackiffy.driver;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import jakarta.persistence.Id;

public class DriverEntity {
    @Id
    private Long id;
    private Date startDate; // to store experience
    private Date dateOfBirth;
    private String firstName;
    private String lastName;
    private String address;
    private String licenceClass;
    private String province;
    private int heightInCm;
    private char sex;

    // Saved as default values
    private String licenceNumber;
    private String documentNumber;
    private Date issudeDate;
    private Date expireDate;
    private String status;
    private int record; // to store accrued points

    public DriverEntity(String startDate, String firstName, String lastName, String address, String dateOfBirth,
            int heightInCm, String province, char sex, String licenceClass) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/mm/dd");
        this.startDate = dateFormat.parse(startDate);
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.dateOfBirth = dateFormat.parse(dateOfBirth);
        this.heightInCm = heightInCm;
        this.province = province;
        this.sex = sex;
        this.licenceClass = licenceClass;
    }

    @Override
    public String toString() {
        return "DriverEntity [startDate=" + startDate + ", firstName=" + firstName + ", lastName=" + lastName
                + ", address=" + address + ", dateOfBirth=" + dateOfBirth + ", heightInCm=" + heightInCm + ", sex="
                + sex + ", licenceClass=" + licenceClass + ", province=" + province + "]";
    }

}
