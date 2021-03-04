package JavaFXSample.javafx;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import fileControler.builder.PropertiesBuilder;
import fileControler.create.FileCreate;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import pathControler.ClassPathLocator;

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
    // 実行可能jarファイルパス取得
    File rootFile = ClassPathLocator.getLocation();
    String rootPath = rootFile.getParent();

    // 設定フォルダ作成
    String configPath = rootPath + "/config";
    FileCreate.createFolder(configPath);

    // 各種フォルダ・ファイル作成
    FileCreate.createFolder(configPath + "/CharacterInfo");
    FileCreate.createFolder(configPath + "/FolderInfo");

    FileCreate.createFolder(configPath + "/SentenceCheck");
    FileCreate.createFolder(configPath + "/SentenceCheck/css");
    FileCreate.createFile(configPath + "/SentenceCheck/tmp.html");
    FileCreate.createFile(configPath + "/SentenceCheck/css/styleSheet.css");

    FileCreate.createFolder(configPath + "/TextFolder");
    FileCreate.createFile(configPath + "/TextFolder/tmp.csv");

    FileCreate.createFile(configPath + "/FileInfo.csv");
    FileCreate.createFile(configPath + "/FilePath.properties");
    FileCreate.createFile(configPath + "/ProjectInfo.csv");
    FileCreate.createFile(configPath + "/TitleInfo.csv");
    FileCreate.createFile(configPath + "/TmpData.properties");
    FileCreate.createFile(configPath + "/TmpPathData.properties");
  }
}