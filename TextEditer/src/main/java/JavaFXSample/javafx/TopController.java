package JavaFXSample.javafx;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import converter.CharConverter;
import dataClass.javaFxTreeView.ItemInfo;
import dataClass.javaFxTreeView.ProjectInfo;
import dataClass.javaFxTreeView.ProjectProperty;
import dataClass.javaFxTreeView.TreeItemData.Type;
import date.GetDate;
import fileControler.builder.ItemInfoBuilder;
import fileControler.builder.ProjectInfoBuilder;
import fileControler.builder.PropertiesBuilder;
import fileControler.load.FolderInfoLoader;
import fileControler.load.ItemInfoLoader;
import fileControler.load.ProjectInfoLoader;
import fileControler.load.PropertiesLoader;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import pathControler.GetPath;

/**
 * Secondary画面のコントローラー
 * @version 2021/01/05 1.0.0 新規作成
 * @since 1.0.0
 * @author wadamasaya
 */
public class TopController {

  /**
   * プロジェクト作成ボタン
   * @version 2021/01/05 1.0.0 新規作成
   * @since 1.0.0
   * @author wadamasaya
   */
  @FXML
  private Button createProjectButton;
  /**
   * ファイル作成ボタン
   * @version 2021/01/05 1.0.0 新規作成
   * @since 1.0.0
   * @author wadamasaya
   */
  @FXML
  private Button createFileButton;
  /**
   * ファイル編集ボタン
   * @version 2021/01/05 1.0.0 新規作成
   * @since 1.0.0
   * @author wadamasaya
   */
  @FXML
  private Button editFileButton;
  /**
   * ファイル名編集ボタン
   * @version 2021/01/05 1.0.0 新規作成
   * @since 1.0.0
   * @author wadamasaya
   */
  @FXML
  private Button editFileNameButton;
  /**
   * プロジェクト情報ボタン
   * @version 2021/01/05 1.0.0 新規作成
   * @since 1.0.0
   * @author wadamasaya
   */
  @FXML
  private Button projectInfoButton;
  /**
   * ファイル削除ボタン
   * @version 2021/01/05 1.0.0 新規作成
   * @since 1.0.0
   * @author wadamasaya
   */
  @FXML
  private Button deleteFileButton;
  /**
   * タイトル編集ボタン
   * @version 2021/01/05 1.0.0 新規作成
   * @since 1.0.0
   * @author wadamasaya
   */
  @FXML
  private Button titleEditButton;
  /**
   * キャラ編集ボタン
   * @version 2021/01/05 1.0.0 新規作成
   * @since 1.0.0
   * @author wadamasaya
   */
  @FXML
  private Button characterEditButton;
  /**
   * プロジェクト選択コンボボックス
   * @version 2021/01/05 1.0.0 新規作成
   * @since 1.0.0
   * @author wadamasaya
   */
  @FXML
  private ComboBox<String> projectNameComboBox;
  /**
   * ファイル一覧表示用ツリービュー
   * @version 2021/01/05 1.0.0 新規作成
   * @since 1.0.0
   * @author wadamasaya
   */
  @FXML
  private TreeView<String> fileNameTreeView;

  /**
   * フォルダアイコン宣言
   * @version 2021/01/05 1.0.0 新規作成
   * @since 1.0.0
   * @author wadamasaya
   */
  private final Image folderIcon = new Image(getClass().getResourceAsStream("Icons/folder_white.png"));
  /**
   * ファイルアイコン宣言
   * @version 2021/01/05 1.0.0 新規作成
   * @since 1.0.0
   * @author wadamasaya
   */
  private final Image fileIcon = new Image(getClass().getResourceAsStream("Icons/file_white.png"));

  /**
   * 画面の初期化処理
   * @throws IOException
   * @version 2021/01/05 1.0.0 新規作成
   * @since 1.0.0
   * @author wadamasaya
   */
  public void initialize() throws IOException {
    loadProjectName();
    setTip();

    // 一時ファイルから情報を取得
    String[] tmpProperties = PropertiesLoader.tmpDataLoader();
    if (!"".equals(tmpProperties[0])) {
      loadScreen();
    }
  }

  /**
   * 全プロジェクト名を取得し、コンボボックスに設定
   * @throws IOException
   * @version 2021/01/05 1.0.0 新規作成
   * @since 1.0.0
   * @author wadamasaya
   */
  private void loadProjectName() throws IOException {
    // ProjectInfoの読込
    List<ProjectInfo> projectInfoList = ProjectInfoLoader.projectInfoLoader();

    // コンボボックスに設定されている内容をクリア
    projectNameComboBox.getItems().clear();

    for (ProjectInfo projectInfo : projectInfoList) {
      // プロジェクトごとのProjectPropertyを取得
      ProjectProperty f = FolderInfoLoader.folderInfoLoader(projectInfo.getProjectInfoPath());
      // ProjectPropertyの非表示フラグがtrueの場合は、マスクをかけてコンボボックスに追加
      if (f.getHiddenFlg()) {
        projectNameComboBox.getItems().add(CharConverter.stringMask(projectInfo.getProjectName()));
      } else {
        projectNameComboBox.getItems().add(projectInfo.getProjectName());
      }
    }
  }

  /**
   * ツールチップを設定
   * @version 2021/01/05 1.0.0 新規作成
   * @since 1.0.0
   * @author wadamasaya
   */
  private void setTip() {
    // ToolTip作成用のインスタンス宣言
    CreateTooltip a = new CreateTooltip();

    // ToolTipを各種ボタンに設定
    createProjectButton.setTooltip(a.createToolTip("新規プロジェクト作成"));
    createFileButton.setTooltip(a.createToolTip("新規ファイル作成"));
    editFileButton.setTooltip(a.createToolTip("ファイル編集"));
    editFileNameButton.setTooltip(a.createToolTip("ファイル名編集"));
    projectInfoButton.setTooltip(a.createToolTip("プロジェクト情報"));
    deleteFileButton.setTooltip(a.createToolTip("ファイル削除"));
    titleEditButton.setTooltip(a.createToolTip("タイトル編集"));
    characterEditButton.setTooltip(a.createToolTip("キャラクター編集"));
  }

  /**
   * 対象プロジェクトの設定を取得する
   *
   * @param projectName 取得対象プロジェクト名
   * @return プロジェクト設定　該当プロジェクトがない場合はNULL
   * @version 2021/01/05 1.0.0 新規作成
   * @since 1.0.0
   * @author wadamasaya
   * @throws IOException
   */
  private ProjectProperty getProjectProperty(String projectName) throws IOException {
    // ProjectInfoの読込
    List<ProjectInfo> projectInfoList = ProjectInfoLoader.projectInfoLoader();

    ProjectProperty projectProperty = null;
    for (ProjectInfo projectInfo : projectInfoList) {
      // プロジェクト名をもとに該当プロジェクトを判断
      if (projectName.equals(projectInfo.getProjectName())) {
        // 当プロジェクトのProjectProperty読込
        projectProperty = FolderInfoLoader.folderInfoLoader(projectInfo.getProjectInfoPath());
      }
    }

    return projectProperty;
  }

  /**
   * TreeViewの再読込
   * @throws IOException
   * @version 2021/01/05 1.0.0 新規作成
   * @since 1.0.0
   * @author wadamasaya
   */
  @SuppressWarnings("unchecked")
  private void loadScreen() throws IOException {

    // ProjectNameを取得
    String projectName = "";
    // 一時ファイルから情報を取得
    String[] tmpProperties = PropertiesLoader.tmpDataLoader();
    if ("".equals(tmpProperties[0])) {
      projectName = conversionProjectName();
    } else {
      projectName = tmpProperties[0];
      // コンボボックスに値をセットする
      projectNameComboBox.setValue(projectName);
    }

    // TreeViewにセットするTreeItemを宣言
    TreeItem<String> rootItem = null;

    // ItemInfoの読込
    List<ItemInfo> itemInfoList = ItemInfoLoader.itemInfoLoader();
    // TreeItemにプロジェクト名をセット
    rootItem = new TreeItem<>(projectName, new ImageView(GetIcon(Type.GROUP)));
    for (ItemInfo b : itemInfoList) {
      // 選択したプロジェクトに該当するファイルの判断
      if (projectName.equals(b.getParentFolderPath())) {
        // TreeItemにアイテムを追加していく
        rootItem.getChildren().addAll(
            new TreeItem<>(b.getFileName(), new ImageView(GetIcon(Type.ITEM))));
      }
    }

    // TreeViewにTreeItemをセット
    fileNameTreeView.setRoot(rootItem);
  }

  /**
   * アイテムのタイプをもとにアイコンを取得
   * @param type アイテムのタイプ(GROUPもしくはITEM)
   * @return Image typeに対応するアイコン
   * @version 2021/01/05 1.0.0 新規作成
   * @since 1.0.0
   * @author wadamasaya
   */
  @SuppressWarnings("exports")
  public Image GetIcon(Type type) {
    if (type.equals(Type.GROUP)) {
      // フォルダアイコンを取得
      return folderIcon;
    } else {
      // ファイルアイコンを取得
      return fileIcon;
    }
  }

  /**
   * プロジェクトを選択
   * @throws IOException
   * @version 2021/01/05 1.0.0 新規作成
   * @since 1.0.0
   * @author wadamasaya
   */
  @FXML
  private void selectProject() throws IOException {
    if (!"".equals(conversionProjectName())) {
      // ProjectNameを取得
      String projectName = conversionProjectName();

      // 対象プロジェクトの設定を取得
      ProjectProperty projectProperty = getProjectProperty(projectName);

      // プロジェクトロック確認
      boolean passFlg = true;
      if (projectProperty.getLockFlg()) {
        // パスワードを入力する
        TextInputDialog passDialog = new TextInputDialog("");
        passDialog.setHeaderText("パスワードを入力してください。");
        String inputPass = null;
        try {
          inputPass = passDialog.showAndWait().get().toString();
        } catch (NoSuchElementException e) {
          // パスワードを入力せず、入力フォームを閉じた際はここでキャッチする
          inputPass = "brank";
        }
        // パスワードの正否を判断
        passFlg = inputPass.equals(projectProperty.getContentPassword());
      }

      // パスワードが正しかった場合、ファイル一覧をTreeViewに表示
      // パスワードが誤っていた場合、TreeItemにエラーメッセージをセット
      if (passFlg) {
        // コンボボックスに値をセットする
        projectNameComboBox.setValue(projectName);
        // 現在、編集プロジェクトを一時ファイルに保持
        // 一時ファイルに対象のファイルパスとファイル名を保持
        PropertiesBuilder.tmpDataBuilder(projectName);
        // TreeViewを更新する
        loadScreen();
      } else {
        // コンボボックスの選択をクリア
        loadProjectName();
        // TreeViewにセットするTreeItemを宣言
        TreeItem<String> rootItem = new TreeItem<>("パスワードが正しくありません。TOP画面に戻り再度プロジェクトを選択してください。");
        // TreeViewにTreeItemをセット
        fileNameTreeView.setRoot(rootItem);
        // 一時ファイルに対象のファイルパスとファイル名を保持
        PropertiesBuilder.tmpDataBuilder("");
      }
    }
  }

  /**
   * 新規プロジェクトを作成
   * @throws IOException
   * @version 2021/01/05 1.0.0 新規作成
   * @since 1.0.0
   * @author wadamasaya
   */
  @FXML
  private void createFolder() throws IOException {
    // 新規作成するプロジェクト名の入力
    TextInputDialog dialog = new TextInputDialog("");
    dialog.setHeaderText("こちらにプロジェクト名を入力してください。");
    Optional<String> inputProjectName = dialog.showAndWait();

    // プロジェクト名が入力済みかの確認
    if (inputProjectName.isPresent()) {
      // ProjectInfoの読込
      List<ProjectInfo> projectInfoList = ProjectInfoLoader.projectInfoLoader();

      // 新規作成するプロジェクトの各種ファイル、フォルダのパスを定義
      String newProjectName = inputProjectName.get().toString();
      String folderPath = GetDate.getNowDate();
      String propertiePath = GetDate.getNowDate() + ".properties";

      // 新規作成するプロジェクトのProjectInfoを宣言
      ProjectInfo projectInfo = new ProjectInfo(newProjectName, folderPath, propertiePath);
      // ProjectInfoのリストに新規作成したProjectInfoを追加
      projectInfoList.add(projectInfo);

      // フォルダ作成を行う
      File folder = new File(GetPath.getConfigPath() + "/TextFolder/" + folderPath);
      folder.mkdir();

      // propertieファイル作成を行う
      File propertieFile = new File(GetPath.getConfigPath() + "/FolderInfo/" + propertiePath);
      propertieFile.createNewFile();

      // ItemInfoをCSVに上書きする
      ProjectInfoBuilder.projectInfoBuilder(projectInfoList);

      // コンボボックスを再読込する
      loadProjectName();
    }
  }

  /**
   * 新規ファイルを作成
   * @throws IOException
   * @version 2021/01/05 1.0.0 新規作成
   * @since 1.0.0
   * @author wadamasaya
   */
  @FXML
  private void createFile() throws IOException {
    // 現在、選択しているプロジェクト名を取得
    String projectName = conversionProjectName();
    // ProjectInfoの読込
    List<ProjectInfo> projectInfoList = ProjectInfoLoader.projectInfoLoader();

    // 新規ファイル追加対象プロジェクトの情報を取得
    ProjectInfo targetProjectInfo = null;
    for (ProjectInfo projectInfo : projectInfoList) {
      if (projectName.equals(projectInfo.getProjectName())) {
        targetProjectInfo = projectInfo;
      }
    }

    // 新規作成するファイル名の入力
    TextInputDialog dialog = new TextInputDialog("");
    dialog.setHeaderText("こちらにファイル名を入力してください。");
    Optional<String> inputFileName = dialog.showAndWait();

    // ファイル名が入力済みかの確認
    if (inputFileName.isPresent()) {
      // ItemInfoの読込
      List<ItemInfo> itemInfoList = ItemInfoLoader.itemInfoLoader();

      // 新規作成するファイルのファイル名、ファイルパスを定義
      String newFileName = inputFileName.get().toString();
      String newFilePath = targetProjectInfo.getFolderPath() + "/" + GetDate.getNowDate() + ".csv";

      // 新規作成するプロジェクトのItemInfoを宣言
      ItemInfo itemInfo = new ItemInfo(newFileName, projectName, newFilePath);
      // ItemInfoのリストに新規作成したItemInfoを追加
      itemInfoList.add(itemInfo);

      // ファイル作成を行う
      File file = new File(GetPath.getConfigPath() + "/TextFolder/" + newFilePath);
      file.createNewFile();

      // ItemInfoをCSVに上書きする
      ItemInfoBuilder.itemInfoBuilder(itemInfoList);

      // 画面を再読込する
      loadScreen();
    }
  }

  /**
   * 選択ファイルの編集
   * @throws IOException
   * @version 2021/01/05 1.0.0 新規作成
   * @since 1.0.0
   * @author wadamasaya
   */
  @FXML
  private void fileEdit() throws IOException {
    // 現在、選択しているプロジェクト名、TreeItemを取得
    String projectName = conversionProjectName();
    TreeItem<?> focusedTreeItem = fileNameTreeView.getFocusModel().getFocusedItem();
    // TreeViewの選択している行番号を取得。(0始まり,-1が未選択)
    int focusedIndex = fileNameTreeView.getFocusModel().getFocusedIndex();

    // TreeView未選択の場合は処理を実施しない。
    if (focusedIndex > 0) {
      // ItemInfoの読込
      List<ItemInfo> itemInfoList = ItemInfoLoader.itemInfoLoader();
      ItemInfo itemInfo = null;
      for (Integer i = 0; i < itemInfoList.size(); i++) {
        if (projectName.equals(itemInfoList.get(i).getParentFolderPath()) &&
            focusedTreeItem.getValue().equals(itemInfoList.get(i).getFileName())) {
          // TreeViewの選択箇所のデータを取得
          itemInfo = itemInfoList.get(i);
        }
      }

      // 一時ファイルに対象のファイルパスとファイル名を保持
      PropertiesBuilder.tmpPathDataBuilder(itemInfo.getFilePath(), itemInfo.getFileName());

      // テキスト編集画面に遷移
      App.setRoot("editFile");
    }
  }

  /**
   * ファイル名を編集
   * @throws IOException
   * @version 2021/01/05 1.0.0 新規作成
   * @since 1.0.0
   * @author wadamasaya
   */
  @SuppressWarnings("rawtypes")
  @FXML
  private void fileNameEdit() throws IOException {
    // 現在、選択しているプロジェクト名、TreeItemを取得
    String projectName = conversionProjectName();
    TreeItem<?> focusedTreeItem = fileNameTreeView.getFocusModel().getFocusedItem();
    // TreeViewの選択している行番号を取得。(0始まり,-1が未選択)
    int focusedIndex = fileNameTreeView.getFocusModel().getFocusedIndex();

    // TreeView未選択の場合は処理を実施しない
    if (focusedIndex >= 0) {
      // 変更後のファイル名の入力
      TextInputDialog dialog = new TextInputDialog("");
      dialog.setHeaderText("こちらにファイル名を入力してください。");
      Optional inputFileName = dialog.showAndWait();

      // ファイル名が入力済みかの確認
      if (inputFileName.isPresent()) {
        // ItemInfoの読込
        List<ItemInfo> itemInfoList = ItemInfoLoader.itemInfoLoader();
        ItemInfo itemInfo = null;
        for (Integer i = 0; i < itemInfoList.size(); i++) {
          if (projectName.equals(itemInfoList.get(i).getParentFolderPath()) &&
              focusedTreeItem.getValue().equals(itemInfoList.get(i).getFileName())) {
            // TreeViewの選択箇所のデータを取得
            itemInfo = new ItemInfo(inputFileName.get().toString(), itemInfoList.get(i).getParentFolderPath(),
                itemInfoList.get(i).getFilePath());
            itemInfoList.set(i, itemInfo);
          }
        }

        // ItemInfoをCSVに上書きする
        ItemInfoBuilder.itemInfoBuilder(itemInfoList);
        // 画面を再読込する
        loadScreen();
      }
    }
  }

  /**
   * ファイルを削除
   * @throws IOException
   * @version 2021/01/05 1.0.0 新規作成
   * @since 1.0.0
   * @author wadamasaya
   */
  @FXML
  private void deleteContent() throws IOException {
    // 現在、選択しているプロジェクト名、TreeItemを取得
    String projectName = conversionProjectName();
    TreeItem<?> focusedTreeItem = fileNameTreeView.getFocusModel().getFocusedItem();
    // TreeViewの選択している行番号を取得。(0始まり,-1が未選択)
    int focusedIndex = fileNameTreeView.getFocusModel().getFocusedIndex();
    // ItemInfoの読込
    List<ItemInfo> itemInfoList = ItemInfoLoader.itemInfoLoader();

    // TreeView未選択の場合は処理を実施しない。
    if (focusedIndex > 0) {
      // 削除確認ダイアログを表示
      Alert dialog = new Alert(AlertType.CONFIRMATION);
      dialog.setTitle("削除確認ダイアログ");
      dialog.setContentText(focusedTreeItem.getValue() + "を削除しますか？");
      Optional<ButtonType> result = dialog.showAndWait();

      // OKボタンがクリックされたら処理実行
      if (result.get() == ButtonType.OK) {
        for (Integer i = 0; i < itemInfoList.size(); i++) {
          if (projectName.equals(itemInfoList.get(i).getParentFolderPath()) &&
              focusedTreeItem.getValue().equals(itemInfoList.get(i).getFileName())) {
            // ファイルを削除
            File file = new File(GetPath.getConfigPath() + "/TextFolder/" + itemInfoList.get(i).getFilePath());
            file.delete();
            // ItemInfoから削除
            itemInfoList.remove(itemInfoList.indexOf(itemInfoList.get(i)));
            // ItemInfoをCSVに上書きする
            ItemInfoBuilder.itemInfoBuilder(itemInfoList);
          }
        }
        // 画面を再読込する
        loadScreen();
      }
    }
  }

  /**
   * フォルダ情報設定画面に遷移
   * @throws IOException
   * @version 2021/01/05 1.0.0 新規作成
   * @since 1.0.0
   * @author wadamasaya
   */
  @FXML
  private void switchToEditInfo() throws IOException {
    // 現在、選択しているプロジェクト名を取得
    String projectName = conversionProjectName();
    // CSVファイルを読込、ItemInfoのリストを返却
    List<ProjectInfo> projectInfoList = ProjectInfoLoader.projectInfoLoader();

    for (Integer i = 0; i < projectInfoList.size(); i++) {
      // プロジェクト内のファイルを全て削除
      if (projectName.equals(projectInfoList.get(i).getProjectName())) {
        // 対象プロジェクトの情報を取得
        ProjectInfo projectInfo = projectInfoList.get(projectInfoList.indexOf(projectInfoList.get(i)));
        // 一時ファイルに対象のファイルパスとファイル名を保持
        PropertiesBuilder.tmpPathDataBuilder(projectInfo.getProjectInfoPath(), projectInfo.getProjectName());
      }
    }
    // フォルダ情報設定画面に遷移
    App.setRoot("editInfo");
  }

  /**
   *タイトル設定画面に遷移
   * @throws IOException
   * @version 2021/01/05 1.0.0 新規作成
   * @since 1.0.0
   * @author wadamasaya
   */
  @FXML
  private void switchToEditTitle() throws IOException {
    App.setRoot("editTitle");
  }

  /**
   * キャラクター設定画面に遷移
   * @throws IOException
   * @version 2021/01/05 1.0.0 新規作成
   * @since 1.0.0
   * @author wadamasaya
   */
  @FXML
  private void switchToEditCharacter() throws IOException {
    App.setRoot("editCharacter");
  }

  /**
   * マスクされたプロジェクト名を取得し、マスク前に変換する
   * @return 変換元に戻したプロジェクト名
   * @throws IOException
   * @version 2021/01/05 1.0.0 新規作成
   * @since 1.0.0
   * @author wadamasaya
   */
  private String conversionProjectName() throws IOException {
    // CSVファイルを読込、ItemInfoのリストを返却
    List<ProjectInfo> c = ProjectInfoLoader.projectInfoLoader();

    // プロジェクト名保持用変数
    String projectName = "";
    // 変換後プロジェクト名保持用変数
    String compareStr = "";

    // コンボボックスの内容を作成
    for (ProjectInfo f : c) {
      // ProjectPropertyの読込
      ProjectProperty projectProperty = FolderInfoLoader.folderInfoLoader(f.getProjectInfoPath());

      // 非表示フラグがtrueの場合
      if (projectProperty.getHiddenFlg()) {
        compareStr = CharConverter.stringMask(f.getProjectName());
      } else {
        compareStr = f.getProjectName();
      }

      // 選択したComboBoxのアイテムのマスクしていないプロジェクト名を取得
      if (compareStr.equals(projectNameComboBox.getValue())) {
        projectName = f.getProjectName();
      }
    }
    return projectName;
  }
}