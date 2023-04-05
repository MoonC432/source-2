package au.ntcrs6.trackiffy.driver;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.data.annotation.PersistenceCreator;

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
    private Date issueDate;
    private Date expireDate;
    private String status;
    private int record; // to store accrued points

    public DriverEntity() {
    }

    @PersistenceCreator
    public DriverEntity(String startDate, String firstName, String lastName,
            String address, String dateOfBirth,
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

    public void setIssueDate(Date issudeDate) {
        this.issueDate = issudeDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setStartDate(String startDate) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/mm/dd");

        this.startDate = dateFormat.parse(startDate);
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/mm/dd");

        this.dateOfBirth = dateFormat.parse(dateOfBirth);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLicenceClass() {
        return licenceClass;
    }

    public void setLicenceClass(String licenceClass) {
        this.licenceClass = licenceClass;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public int getHeightInCm() {
        return heightInCm;
    }

    public void setHeightInCm(int heightInCm) {
        this.heightInCm = heightInCm;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public String getLicenceNumber() {
        return licenceNumber;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public String getStatus() {
        return status;
    }

    public int getRecord() {
        return record;
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
                + licenceNumber + ", documentNumber=" + documentNumber + ", issudeDate=" + issueDate + ", expireDate="
                + expireDate + ", status=" + status + ", record=" + record + "]" + "\u001B[0m");
        return null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
