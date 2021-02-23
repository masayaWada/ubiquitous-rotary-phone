package JavaFXSample.javafx;

import java.io.File;
import java.io.IOException;
import java.util.List;

import dataClass.javaFxTreeView.CharInfo;
import fileControler.builder.CssBuilder;
import fileControler.builder.HtmlBuilder;
import fileControler.load.CharCssInfoLoader;
import fileControler.load.PropertiesLoader;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
// TODO コメント修正、プログラム正規化

/**
* SentenceCheck画面のコントローラー
* @version 2021/01/05 1.0.0 新規作成
* @since 1.0.0
* @author wadamasaya
*/
public class SentenceCheckController {

  @FXML
  private Label pathLabel;
  @FXML
  private WebView webView;

  @FXML
  private void switchToEditFile() throws IOException {
    App.setRoot("editFile");
  }

  /**
   * 画面の初期化処理
   * @throws IOException
   * @version 2021/01/05 1.0.0 新規作成
   * @since 1.0.0
   * @author wadamasaya
   */
  public void initialize() throws IOException {
    pageOpener();
  }

  /**
   * ファイルを読込、テキストエリアに設定する
   * @throws IOException
   * @version 2021/01/05 1.0.0 新規作成
   * @since 1.0.0
   * @author wadamasaya
   */
  private void pageOpener() throws IOException {
    // 編集対象のファイルパスを取得
    String[] targetProperties = PropertiesLoader.tmpPathDataLoader();

    // 編集対象のファイル内容を読込、HTMLファイルを作成する
    String filePath = "src/main/resources/Files/TextFolder/" + targetProperties[0];
    HtmlBuilder.htmlBuilder(filePath);

    List<CharInfo> a = CharCssInfoLoader.charCssInfoLoader("鬼滅の刃");
    CssBuilder.cssBuilder(a);

    // 作成したHTMLのファイルオブジェクトを作成
    String search = "src/main/resources/Files/SentenceCheck/tmp.html";
    File htmlFile = new File(search);

    // ファイルオブジェクトからhtmlのURIを取得
    WebEngine engine = webView.getEngine();
    engine.load(htmlFile.toURI().toString());
    pathLabel.setText(targetProperties[1]);
  }
}