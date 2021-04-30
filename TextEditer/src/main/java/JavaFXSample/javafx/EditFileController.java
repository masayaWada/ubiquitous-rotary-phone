package JavaFXSample.javafx;

import java.io.IOException;
import java.util.List;

import count.ByteCounter;
import fileControler.builder.ContentTextBuilder;
import fileControler.load.CsvLoader;
import fileControler.load.PropertiesLoader;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import pathControler.GetPath;

/**
* EditFile画面のコントローラー
* @version 2021/01/05 1.0.0 新規作成
* @since 1.0.0
* @author wadamasaya
*/
public class EditFileController {
  // 改行
  private static final String NEW_LINE = "\r\n";

  /**
   * プロジェクト画面
   * @version 2021/01/05 1.0.0 新規作成
   * @since 1.0.0
   * @author wadamasaya
   */
  @FXML
  private VBox screen;
  @FXML
  private Label pathLabel;
  @FXML
  private Label strQuantityLabel;
  @FXML
  private TextArea textArea01;

  /**
   * TOP画面表示処理
   *
   * @throws IOException
   * @version 2021/01/05 1.0.0 新規作成
   * @since 1.0.0
   * @author wadamasaya
   */
  @FXML
  private void switchToSecondary() throws IOException {

    boolean closeFlg = true;

    if (changeConfirmation()) {
      // 保存されていない場合アラートを表示する
      Alert alert = new Alert(AlertType.NONE, "", ButtonType.OK, ButtonType.CANCEL);
      alert.setTitle("変更が保存されておりません！");
      alert.getDialogPane().setContentText("変更した内容が保存されておりません。\nこのままTOP画面に戻ると、変更した内容が失われてしまいますが\nそれでもよろしいですか。");
      ButtonType button = alert.showAndWait().orElse(ButtonType.CANCEL);

      if (!"OK_DONE".equals(button.getButtonData().toString())) {
        closeFlg = false;
      }
    }

    if (closeFlg) {
      App.setRoot("top");
    }

  }

  /**
   * 文章確認画面表示処理
   * @throws IOException
   * @version 2021/01/05 1.0.0 新規作成
   * @since 1.0.0
   * @author wadamasaya
   */
  @FXML
  private void switchToSentenceCheck() throws IOException {
    App.setRoot("sentenceCheck");
  }

  /**
   * 画面の初期化処理
   * @throws IOException
   * @version 2021/01/05 1.0.0 新規作成
   * @since 1.0.0
   * @author wadamasaya
   */
  public void initialize() throws IOException {
    fileOpener();
    strByteCount();
  }

  /**
   * 元ファイルと一時保存ファイルに差異があるか判断する
   *
   * @return 差異の有無(無し:true 有:false)
   * @version 2021/01/05 1.0.0 新規作成
   * @since 1.0.0
   * @author wadamasaya
   */
  private Boolean changeConfirmation() throws IOException {
    // 編集対象のファイルパスを取得
    String[] targetProperties = PropertiesLoader.tmpPathDataLoader();

    // ファイルの内容を取得する
    String fileContentValue = textLoader(targetProperties[0]);
    String tmpFileContentValue = textLoader("tmp.csv");
    return !fileContentValue.equals(tmpFileContentValue);
  }

  /**
   * 引数のファイルの内容を取得する
   *
   * @param filePath ファイルパス
   * @return 取得した内容
   * @version 2021/01/05 1.0.0 新規作成
   * @since 1.0.0
   * @author wadamasaya
   */
  private String textLoader(String filePath) throws IOException {
    // 編集対象のファイル内容を読込
    List<String[]> data = CsvLoader.csvLoader(GetPath.getConfigPath() + "/TextFolder/" + filePath);
    // 読み込んだ内容を整形
    String contectValue = "";
    for (String[] line_data : data) {
      contectValue = contectValue + line_data[0] + NEW_LINE;
    }
    return contectValue;
  }

  /**
   * ファイルを読込、テキストエリアに設定する
   * @throws IOException
   * @version 2021/01/05 1.0.0 新規作成
   * @since 1.0.0
   * @author wadamasaya
   */
  private void fileOpener() throws IOException {
    // 編集対象のファイルパスを取得
    String[] targetProperties = PropertiesLoader.tmpPathDataLoader();

    // 編集対象のファイル内容を読込
    String contectValue = textLoader(targetProperties[0]);

    // テキストエリアに読込んだ内容を設定
    textArea01.setText(contectValue);
    pathLabel.setText(targetProperties[1]);
  }

  /**
   * 入力内容の上書きを実行
   * @throws IOException
   * @version 2021/01/05 1.0.0 新規作成
   * @since 1.0.0
   * @author wadamasaya
   */
  @FXML
  private void fileSaveStart() throws IOException {
    // テキストエリアから値を取得
    String inputData = textArea01.getText();

    // lineを改行で分割し、配列リストdataに追加
    String[] data = inputData.split("\n");

    // 保存対象のファイルパスを取得
    String targetFilePath = PropertiesLoader.tmpPathDataLoader()[0];

    ContentTextBuilder.itemInfoBuilder(data, targetFilePath);
  }

  /**
   * 入力内容を一時的に別ファイルに保持する。
   * @throws IOException
   * @version 2021/01/05 1.0.0 新規作成
   * @since 1.0.0
   * @author wadamasaya
   */
  private void tmpfileSave() throws IOException {
    // テキストエリアから値を取得
    String inputData = textArea01.getText();

    // lineを改行で分割し、配列リストdataに追加
    String[] data = inputData.split("\n");

    ContentTextBuilder.itemInfoBuilder(data, "tmp.csv");
  }

  /**
   * 入力内容のByte表示
   * @throws IOException
   * @version 2021/01/05 1.0.0 新規作成
   * @since 1.0.0
   * @author wadamasaya
   */
  @FXML
  private void strByteCount() throws IOException {
    // テキストエリアから値を取得
    String inputData = textArea01.getText();

    // 入力文字数を取得
    int strQuantity = ByteCounter.getByte(inputData, "Shift_JIS");

    // 入力文字数を元に文字色を設定
    if (strQuantity >= 2048) {
      strQuantityLabel.setTextFill(Color.RED);
    } else if (strQuantity >= 1638) {
      strQuantityLabel.setTextFill(Color.ORANGE);
    } else {
      strQuantityLabel.setTextFill(Color.LIME);
    }

    // 入力文字数を表示
    strQuantityLabel.setText(strQuantity + "Byte");

    // 現在の内容を一時ファイルに保持
    tmpfileSave();
  }

  /**
   * TreeViewの大きさをリサイズする
   * @throws IOException
   * @version 2021/01/05 1.0.0 新規作成
   * @since 1.0.0
   * @author wadamasaya
   */
  @FXML
  private void resizeScreen() throws IOException {
    double x = (double) 15 / 16;
    textArea01.setPrefWidth(screen.getHeight() * x);
    double y = (double) 37 / 48;
    textArea01.setPrefHeight(screen.getWidth() * y);
  }
}