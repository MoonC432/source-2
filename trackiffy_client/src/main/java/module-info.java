module au.ntcrs6 {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.base;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;

    opens au.ntcrs6 to javafx.fxml;

    exports au.ntcrs6;

    opens au.ntcrs6.controllers to javafx.fxml, com.fasterxml.jackson.databind, javafx.base;

    exports au.ntcrs6.controllers to javafx.fxml;

}
