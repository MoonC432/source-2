package au.ntcrs6.trackiffy.driver;

import java.util.Date;

public class LicenceEntity {

    private Long id; // auto gen
    private String firstName;
    private String lastName;
    private String address;
    private String licenceNumber; // auto gen
    private Date issudeDate; // auto gen
    private Date expireDate;// auto gen
    private Date dateOfBirth;
    private int age; // auto gen
    private String documentNumber; // auto gen
    private int heightInCm;
    private char sex;
    private String licenceClass;
    private String province;

    public LicenceEntity(String firstName, String lastName, String address, String licenceNumber, Date issudeDate,
            Date expireDate, Date dateOfBirth, String documentNumber, int heightInCm, char sex, String licenceClass,
            String province) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.licenceNumber = licenceNumber;
        this.issudeDate = issudeDate;
        this.expireDate = expireDate;
        this.dateOfBirth = dateOfBirth;
        this.documentNumber = documentNumber;
        this.heightInCm = heightInCm;
        this.sex = sex;
        this.licenceClass = licenceClass;
        this.province = province;
    }

}
