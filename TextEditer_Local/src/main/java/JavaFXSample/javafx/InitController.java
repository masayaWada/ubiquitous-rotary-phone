package JavaFXSample.javafx;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.CodeSource;
import java.security.ProtectionDomain;

import fileControler.builder.PropertiesBuilder;
import fileControler.create.FileCreate;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
* 初期処理画面のコントローラー
* @version 2021/01/05 1.0.0 新規作成
* @since 1.0.0
* @author wadamasaya
*/
public class InitController {

  /**
   * 開始ボタン
   * @version 2021/01/05 1.0.0 新規作成
   * @since 1.0.0
   * @author wadamasaya
   */
  @FXML
  private Button startButton;

  /**
   * TOP画面遷移処理
   * @throws IOException
   * @version 2021/01/05 1.0.0 新規作成
   * @since 1.0.0
   * @author wadamasaya
   */
  @FXML
  private void switchToTop() throws IOException {
    App.setRoot("top");
  }

  /**
   * 初期化処理
   *
   * @throws IOException
   * @version 2021/01/05 1.0.0 新規作成
   * @since 1.0.0
   * @author wadamasaya
   * @throws IOException,URISyntaxException
   */
  public void initialize() throws IOException, URISyntaxException {
    // 各種ファイル・フォルダ作成
    initBuilder();
    // 一時ファイルの初期化
    PropertiesBuilder.tmpDataBuilder("");
    PropertiesBuilder.tmpPathDataBuilder("", "");
  }

  /**
   * 各種ファイル・フォルダを作成する。
   * ファイル・フォルダが存在する場合は、処理を実施しない。
   *
   * @version 2021/01/05 1.0.0 新規作成
   * @since 1.0.0
   * @author wadamasaya
   * @throws URISyntaxException
   * @throws IOException
   */
  private void initBuilder() throws URISyntaxException, IOException {
    String rootPath = getApplicationPath(App.class).getParent().toString();

    // ルートフォルダを作成
    FileCreate.createFolder(rootPath + "/resources");
  }

  /**
   * 引数に渡した、クラスのファイルパスを取得。
   *
   * @throws IOException
   * @version 2021/01/05 1.0.0 新規作成
   * @since 1.0.0
   * @author wadamasaya
   * @throws URISyntaxException
   */
  public static Path getApplicationPath(Class<?> cls) throws URISyntaxException {
    ProtectionDomain pd = cls.getProtectionDomain();
    CodeSource cs = pd.getCodeSource();
    URL location = cs.getLocation();
    URI uri = location.toURI();
    Path path = Paths.get(uri);
    return path;
  }
}