package JavaFXSample.javafx;

import java.io.File;
import java.io.IOException;
import java.util.List;

import dataClass.javaFxTreeView.CharInfo;
import dataClass.javaFxTreeView.ItemInfo;
import dataClass.javaFxTreeView.ProjectInfo;
import dataClass.javaFxTreeView.ProjectProperty;
import fileControler.builder.CssBuilder;
import fileControler.builder.HtmlBuilder;
import fileControler.load.CharCssInfoLoader;
import fileControler.load.FolderInfoLoader;
import fileControler.load.ItemInfoLoader;
import fileControler.load.ProjectInfoLoader;
import fileControler.load.PropertiesLoader;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import pathControler.GetPath;

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
    String filePath = GetPath.getConfigPath() + "/TextFolder/" + targetProperties[0];
    HtmlBuilder.htmlBuilder(filePath);

    // 編集対象のファイル情報を取得
    List<ItemInfo> itemInfoList = ItemInfoLoader.itemInfoLoader();
    String projectName = null;
    for (Integer i = 0; i < itemInfoList.size(); i++) {
      if (targetProperties[0].equals(itemInfoList.get(i).getFilePath())) {
        // TreeViewの選択箇所のデータを取得
        projectName = itemInfoList.get(i).getParentFolderPath();
      }
    }

    // 編集対象のプロジェクト情報を取得
    List<ProjectInfo> projectInfoList = ProjectInfoLoader.projectInfoLoader();
    String projectInfoPath = null;
    for (ProjectInfo projectInfo : projectInfoList) {
      if (projectName.equals(projectInfo.getProjectName())) {
        projectInfoPath = projectInfo.getProjectInfoPath();
      }
    }

    // CSVファイルを読込、FileInfoのリストを返却する。
    ProjectProperty projectProperty = FolderInfoLoader.folderInfoLoader(projectInfoPath);

    List<CharInfo> a = CharCssInfoLoader.charCssInfoLoader(projectProperty.getAnimeTitle());
    CssBuilder.cssBuilder(a);

    // 作成したHTMLのファイルオブジェクトを作成
    String search = GetPath.getConfigPath() + "/SentenceCheck/tmp.html";
    File htmlFile = new File(search);

    // ファイルオブジェクトからhtmlのURIを取得
    WebEngine engine = webView.getEngine();
    engine.load(htmlFile.toURI().toString());
    pathLabel.setText(targetProperties[1]);
  }
}