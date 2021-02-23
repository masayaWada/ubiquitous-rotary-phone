module JavaFXSample.javafx {
  requires javafx.controls;
  requires javafx.fxml;
  requires javafx.web;
  requires javafx.base;
  requires javafx.graphics;

  opens JavaFXSample.javafx to javafx.fxml;

  exports JavaFXSample.javafx;
}