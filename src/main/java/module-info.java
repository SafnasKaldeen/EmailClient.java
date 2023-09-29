module com.cyberflash.emailclient {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.mail;


    opens com.cyberflash.emailclient to javafx.fxml;
    exports com.cyberflash.emailclient;
}