package JavaFXSample.javafx;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import dataClass.ItemInfo;
import dataClass.ProjectInfo;
import dataClass.ProjectProperty;
import dataClass.TitleInfo;
import fileControler.builder.FolderInfoBuilder;
import fileControler.builder.ItemInfoBuilder;
import fileControler.builder.ProjectInfoBuilder;
import fileControler.load.FolderInfoLoader;
import fileControler.load.ItemInfoLoader;
import fileControler.load.ProjectInfoLoader;
import fileControler.load.PropertiesLoader;
import fileControler.load.TitleInfoLoader;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import pathControler.GetPath;

/**
* EditInfo画面のコントローラー
* @version 2021/01/05 1.0.0 新規作成
* @since 1.0.0
* @author wadamasaya
*/
public class EditInfoController {

  @FXML
  private ComboBox<String> comboBox1;
  @FXML
  private TextField upPassTextField;
  @FXML
  private TextField contentPassTextField;
  @FXML
  private ImageView hiddenImage;
  @FXML
  private TextField hiddenFlgTextField;
  @FXML
  private ImageView lockImage;
  @FXML
  private TextField lockFlgTextField;

  @FXML
  private void switchToSecondary() throws IOException {
    App.setRoot("top");
  }

  // 表示アイコンを取得
  private final Image hiddenIcon = new Image(getClass().getResourceAsStream("Icons/eye-line.png"));
  // 非表示アイコンを取得
  private final Image unhiddenIcon = new Image(getClass().getResourceAsStream("Icons/eye-off-line.png"));
  // 解除アイコンを取得
  private final Image unlockIcon = new Image(getClass().getResourceAsStream("Icons/lock-unlock-line.png"));
  // 保護アイコンを取得
  private final Image lockIcon = new Image(getClass().getResourceAsStream("Icons/lock-line.png"));

  /**
   * 画面の初期化処理
   * @throws IOException
   * @version 2021/01/05 1.0.0 新規作成
   * @since 1.0.0
   * @author wadamasaya
   */
  public void initialize() throws IOException {
    loadTitle();
    loadPropertie();
    reloadImage();
  }

  /**
   * タイトルを読込、コンボボックスに設定。
   * @throws IOException
   * @version 2021/01/05 1.0.0 新規作成
   * @since 1.0.0
   * @author wadamasaya
   */
  private void loadTitle() throws IOException {
    // CSVファイルを読込、ItemInfoのリストを返却する。
    List<TitleInfo> a = TitleInfoLoader.titleInfoLoader();

    ArrayList<String> titleList = new ArrayList<>();
    for (TitleInfo b : a) {
      // リストにタイトルを追加していく
      titleList.add(b.getTitle().toString());
    }
    comboBox1.getItems().addAll(titleList);
  }

  /**
   * プロパティを読込、各種入力項目に設定。
   * @throws IOException
   * @version 2021/01/05 1.0.0 新規作成
   * @since 1.0.0
   * @author wadamasaya
   */
  private void loadPropertie() throws IOException {
    // プロパティのパスを取得
    String[] targetProperties = PropertiesLoader.tmpPathDataLoader();
    // CSVファイルを読込、FileInfoのリストを返却する。
    ProjectProperty a = FolderInfoLoader.folderInfoLoader(targetProperties[0]);

    // プロパティの内容を初期値として設定する
    comboBox1.setValue(a.getAnimeTitle());
    upPassTextField.setText(a.getUploadPassword());
    contentPassTextField.setText(a.getContentPassword());
    hiddenFlgTextField.setText(a.getHiddenFlg().toString());
    lockFlgTextField.setText(a.getLockFlg().toString());
  }

  /**
   * フラグを元に画像の変更を行う
   * @throws IOException
   * @version 2021/01/05 1.0.0 新規作成
   * @since 1.0.0
   * @author wadamasaya
   */
  private void reloadImage() throws IOException {
    // 非表示フラグの値を元に、ボタンの画像を変更
    if ("true".equals(hiddenFlgTextField.getText())) {
      hiddenImage.setImage(unhiddenIcon);
    } else {
      hiddenImage.setImage(hiddenIcon);
    }

    // 保護フラグの値を元に、ボタンの画像を変更
    if ("true".equals(lockFlgTextField.getText())) {
      lockImage.setImage(lockIcon);
    } else {
      lockImage.setImage(unlockIcon);
    }
  }

  /**
   * 表示フラグを変更する
   * @throws IOException
   * @version 2021/01/05 1.0.0 新規作成
   * @since 1.0.0
   * @author wadamasaya
   */
  @FXML
  private void changeHiddenFlg() throws IOException {
    // 非表示フラグの値を元に、ボタンの画像を変更
    if ("true".equals(hiddenFlgTextField.getText())) {
      hiddenFlgTextField.setText("false");
    } else {
      hiddenFlgTextField.setText("true");
    }

    // ボタン画像を更新する
    reloadImage();
  }

  /**
   * 保護フラグを変更する
   * @throws IOException
   * @version 2021/01/05 1.0.0 新規作成
   * @since 1.0.0
   * @author wadamasaya
   */
  @FXML
  private void changeLockFlg() throws IOException {
    // 保護フラグの値を元に、ボタンの画像を変更
    if ("true".equals(lockFlgTextField.getText())) {
      lockFlgTextField.setText("false");
    } else {
      lockFlgTextField.setText("true");
    }

    // ボタン画像を更新する
    reloadImage();
  }

  /**
   * プロジェクトを削除します
   * @throws IOException
   * @version 2021/01/05 1.0.0 新規作成
   * @since 1.0.0
   * @author wadamasaya
   */
  @FXML
  private void deleteProject() throws IOException {
    // 編集対象のファイルパスを取得
    String[] targetProperties = PropertiesLoader.tmpPathDataLoader();
    // CSVファイルを読込、ItemInfo,ProjectInfoのリストを宣言
    List<ItemInfo> a = ItemInfoLoader.itemInfoLoader();
    List<ProjectInfo> b = ProjectInfoLoader.projectInfoLoader();

    Alert dialog = new Alert(AlertType.CONFIRMATION);
    dialog.setTitle("削除確認ダイアログ");
    dialog.setContentText(targetProperties[1] + "を削除します。本当に削除しますか？");
    Optional<ButtonType> result = dialog.showAndWait();

    // OKボタンがクリックされたら処理実行
    if (result.get() == ButtonType.OK) {
      // ItemInfoを宣言する
      for (Integer i = 0; i < a.size(); i++) {
        // プロジェクト内のファイルを全て削除する
        if (targetProperties[1].equals(a.get(i).getParentFolderPath())) {
          // ファイルオブジェクトを作成し、ファイルを削除する
          File file = new File(GetPath.getConfigPath() + "/TextFolder/" + a.get(i).getFilePath());
          file.delete();
          // ItemInfoから削除
          a.remove(a.indexOf(a.get(i)));
          // ItemInfoをCSVに上書きする
          ItemInfoBuilder.itemInfoBuilder(a);
        }
      }

      // ProjectInfoを宣言する
      for (Integer i = 0; i < b.size(); i++) {
        // プロジェクト内のファイルを全て削除する
        if (targetProperties[0].equals(b.get(i).getProjectInfoPath()) &&
            targetProperties[1].equals(b.get(i).getProjectName())) {
          // ファイルオブジェクトを作成し、プロジェクト情報ファイルを削除する
          File file = new File(GetPath.getConfigPath() + "/FolderInfo/" + b.get(i).getProjectInfoPath());
          file.delete();
          // ファイルオブジェクトを作成し、フォルダを削除する
          File folder = new File(GetPath.getConfigPath() + "/TextFolder/" + b.get(i).getFolderPath());
          folder.delete();
          // ProjectInfoから削除
          b.remove(b.indexOf(b.get(i)));
          // ProjectInfoをCSVに上書きする
          ProjectInfoBuilder.projectInfoBuilder(b);
        }
      }

      // 前画面に遷移する
      switchToSecondary();
    }
  }

  /**
   * 入力した設定をファイルに保存します
   * @throws IOException
   * @version 2021/01/05 1.0.0 新規作成
   * @since 1.0.0
   * @author wadamasaya
   */
  @FXML
  private void saveProperties() throws IOException {
    String animeTitle = comboBox1.getValue();
    String uploadPass = upPassTextField.getText();
    String contentPass = contentPassTextField.getText();

    // hiddenFlgがtrueの場合、trueをセット
    Boolean hiddenFlg = false;
    if (hiddenFlgTextField.getText().equals("true")) {
      hiddenFlg = true;
    }

    // lockFlgがtrueの場合、trueをセット
    Boolean lockFlg = false;
    if (lockFlgTextField.getText().equals("true")) {
      lockFlg = true;
    }

    // プロパティのパスを取得
    String[] targetProperties = PropertiesLoader.tmpPathDataLoader();

    // FileInfoを作成し、プロパティを上書きする
    ProjectProperty a = new ProjectProperty(animeTitle, uploadPass, contentPass, hiddenFlg, lockFlg);
    FolderInfoBuilder.folderInfoBuilder(a, targetProperties[0]);
  }
}