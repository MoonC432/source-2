package au.ntcrs6.trackiffy.utilities;

import java.util.Random;

public class RandomStringGenerator {
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int LICENSE_NUMBER_LENGTH = 15;
    private static final int DOCUMENT_NUMBER_LENGTH = 9;
    private static final Random random = new Random();

    public static String generateLicenseNumber() {
        StringBuilder sb = new StringBuilder(LICENSE_NUMBER_LENGTH);
        for (int i = 0; i < LICENSE_NUMBER_LENGTH; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            sb.append(CHARACTERS.charAt(randomIndex));
        }
        return sb.toString();
    }

    public static String generateDocumentNumber() {
        StringBuilder sb = new StringBuilder(DOCUMENT_NUMBER_LENGTH);
        for (int i = 0; i < DOCUMENT_NUMBER_LENGTH; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            sb.append(CHARACTERS.charAt(randomIndex));
        }
        return sb.toString();
    }
}
