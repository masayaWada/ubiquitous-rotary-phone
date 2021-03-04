package JavaFXSample.javafx;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import dataClass.javaFxTreeView.TitleInfo;
import fileControler.builder.TitleInfoBuilder;
import fileControler.load.TitleInfoLoader;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import pathControler.GetPath;

/**
* EditTitle画面のコントローラー
* @version 2021/01/05 1.0.0 新規作成
* @since 1.0.0
* @author wadamasaya
*/
public class EditTitleController {

  @FXML
  private TreeView<String> treeView01;

  @FXML
  private void switchToSecondary() throws IOException {
    App.setRoot("top");
  }

  /**
   * 画面の初期化処理
   * @throws IOException
   * @version 2021/01/05 1.0.0 新規作成
   * @since 1.0.0
   * @author wadamasaya
   */
  public void initialize() throws IOException {
    reloadScreen();
  }

  /**
   * TitleInfoの読込を行う
   * @throws IOException
   * @version 2021/01/05 1.0.0 新規作成
   * @since 1.0.0
   * @author wadamasaya
   */
  @SuppressWarnings("unchecked")
  private void reloadScreen() throws IOException {
    // CSVファイルを読込、ItemInfoのリストを返却する。
    List<TitleInfo> a = TitleInfoLoader.titleInfoLoader();

    // rootの宣言
    TreeItem<String> rootItem = new TreeItem<>("タイトル一覧");
    // TreeViewを更新
    for (TitleInfo b : a) {
      // rootにアイテムを追加していく
      rootItem.getChildren().addAll(new TreeItem<>(b.getTitle()));
    }
    // rootにアイテムをセット
    treeView01.setRoot(rootItem);
  }

  /**
   * ファイル名を追加する
   * @throws IOException
   * @version 2021/01/05 1.0.0 新規作成
   * @since 1.0.0
   * @author wadamasaya
   */
  @SuppressWarnings("rawtypes")
  @FXML
  private void addTitle() throws IOException {
    // 入力ダイアログを作成
    TextInputDialog dialog = new TextInputDialog("");
    dialog.setHeaderText("こちらにタイトルを入力してください。");
    Optional inputFileName = dialog.showAndWait();

    // 入力済みかの確認
    if (inputFileName.isPresent()) {
      // CSVファイルを読込、ItemInfoのリストを返却する
      List<TitleInfo> a = TitleInfoLoader.titleInfoLoader();
      // TreeViewの選択箇所のデータを変更
      TitleInfo b = new TitleInfo(inputFileName.get().toString(), inputFileName.get().toString() + ".csv");
      a.add(b);

      // ファイル作成を行う
      File file = new File(GetPath.getConfigPath() + "/CharacterInfo/" + b.getFilePath());
      file.createNewFile();

      // TitleInfoをCSVに上書きする
      TitleInfoBuilder.itemInfoBuilder(a);

      // 画面を再読込する
      reloadScreen();
    }

  }

  /**
   * コンテンツを削除する
   * @throws IOException
   * @version 2021/01/05 1.0.0 新規作成
   * @since 1.0.0
   * @author wadamasaya
   */
  @FXML
  private void deleteTitle() throws IOException {
    // TreeViewの選択している行番号を取得。(0は対象外のため-1)
    int focusedIndex = treeView01.getFocusModel().getFocusedIndex() - 1;
    // CSVファイルを読込、ItemInfoのリストを返却する。
    List<TitleInfo> a = TitleInfoLoader.titleInfoLoader();

    // TreeView未選択の場合は処理を実施しない。
    if (focusedIndex >= 0) {
      Alert dialog = new Alert(AlertType.CONFIRMATION);
      dialog.setTitle("削除確認ダイアログ");
      dialog.setContentText(a.get(focusedIndex).getTitle() + "を削除しますか？");
      Optional<ButtonType> result = dialog.showAndWait();

      // OKボタンがクリックされたら処理実行
      if (result.get() == ButtonType.OK) {

        // ファイルオブジェクトの宣言
        File file = new File(GetPath.getConfigPath() + "/CharacterInfo/" + a.get(focusedIndex).getFilePath());

        // ファイル削除、TitleInfoから削除
        file.delete();
        a.remove(focusedIndex);

        // TitleInfoをCSVに上書きする
        TitleInfoBuilder.itemInfoBuilder(a);

        // 画面を再読込する
        reloadScreen();
      }
    }
  }
}