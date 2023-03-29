package au.ntcrs6.trackiffy.driver;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class DriverEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

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

    // Will have default values
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

    public void setLicenceNumber(String licenceNumber) {
        this.licenceNumber = licenceNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public void setIssudeDate(Date issudeDate) {
        this.issudeDate = issudeDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setRecord(int record) {
        this.record = record;
    }

    @Override
    public String toString() {
        System.out.println("\u001B[32m" + "DriverEntity [id=" + id + ", startDate=" + startDate + ", dateOfBirth="
                + dateOfBirth + ", firstName="
                + firstName + ", lastName=" + lastName + ", address=" + address + ", licenceClass=" + licenceClass
                + ", province=" + province + ", heightInCm=" + heightInCm + ", sex=" + sex + ", licenceNumber="
                + licenceNumber + ", documentNumber=" + documentNumber + ", issudeDate=" + issudeDate + ", expireDate="
                + expireDate + ", status=" + status + ", record=" + record + "]" + "\u001B[0m");
        return null;
    }

}
