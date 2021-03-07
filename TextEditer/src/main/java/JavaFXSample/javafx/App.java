package JavaFXSample.javafx;

import java.io.IOException;
import java.net.URISyntaxException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * JavaFX App
 * @version 2021/01/05 1.0.0 新規作成
 * @since 1.0.0
 * @author wadamasaya
 */
public class App extends Application {

  private static Scene scene;

  /**
   * TOP画面を表示する
   * @throws IOException
   * @version 2021/01/05 1.0.0 新規作成
   * @since 1.0.0
   * @author wadamasaya
   */
  @SuppressWarnings("exports")
  @Override
  public void start(Stage stage) throws IOException {
    scene = new Scene(loadFXML("init"), 640, 480);
    stage.setScene(scene);
    stage.setTitle("TextEditer");
    stage.show();
  }

  /**
   * 引数のfxmlを画面にセットする
   * @param fxml 表示対象のfxmlファイル名(拡張子なし)
   * @version 2021/01/05 1.0.0 新規作成
   * @since 1.0.0
   * @author wadamasaya
   * @throws IOException
   */
  static void setRoot(String fxml) throws IOException {
    scene.setRoot(loadFXML(fxml));
  }

  /**
   * 引数をもとにfxmlファイルの内容を取得する
   * @param fxml 表示対象のfxmlファイル名(拡張子なし)
   * @return fxmlファイルの内容
   * @version 2021/01/05 1.0.0 新規作成
   * @since 1.0.0
   * @author wadamasaya
   * @throws IOException
   */
  private static Parent loadFXML(String fxml) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("fxml/" + fxml + ".fxml"));
    return fxmlLoader.load();
  }

  /**
   * メインクラス
   * @throws IOException
   * @version 2021/01/05 1.0.0 新規作成
   * @since 1.0.0
   * @author wadamasaya
   * @throws URISyntaxException
   */
  public static void main(String[] args) {
    launch();
  }
}