module JavaFXSample.javafx {
    requires javafx.controls;
    requires javafx.fxml;

    opens JavaFXSample.javafx to javafx.fxml;
    exports JavaFXSample.javafx;
}