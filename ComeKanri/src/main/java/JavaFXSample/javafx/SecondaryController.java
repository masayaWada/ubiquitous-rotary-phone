package JavaFXSample.javafx;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class SecondaryController {

	@FXML
	private TextField TextField01;
	@FXML
	private TextField TextField02;
	@FXML
	private TextField TextField03;
	@FXML
	private TextField TextField04;
	@FXML
	private Label ResultSetField;
	@FXML
	private Button ColcCutton;

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }

    /**
     * 企業価値算出を行う。
     * @throws IOException
     */
    @FXML
    private void colcCorporateValue() throws IOException {
    	// 企業価値に入力した値
    	Integer value01 = Integer.parseInt(TextField01.getText());
    	// 財産価値に入力した値
    	Integer value02 = Integer.parseInt(TextField02.getText());
    	// 負債に入力した値
    	Integer value03 = Integer.parseInt(TextField03.getText());
    	// 発行済株式数に入力した値
    	Integer value04 = Integer.parseInt(TextField04.getText());

    	//入力値を元に計算を実施
    	Integer calcResult = (value01 + value02 - value03) / value04;
    	// 計算結果をフィールドに出力
    	ResultSetField.setText(String.valueOf(calcResult));
    }
}