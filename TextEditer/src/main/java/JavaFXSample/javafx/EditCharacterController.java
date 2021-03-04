package JavaFXSample.javafx;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import dataClass.javaFxTreeView.CharInfo;
import dataClass.javaFxTreeView.TitleInfo;
import fileControler.builder.CharCssInfoBuilder;
import fileControler.load.CharCssInfoLoader;
import fileControler.load.TitleInfoLoader;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.paint.Color;
// TODO コメント修正、プログラム正規化

/**
* EditCharacter画面のコントローラー
* @version 2021/01/05 1.0.0 新規作成
* @since 1.0.0
* @author wadamasaya
*/
public class EditCharacterController {

  @FXML
  private ComboBox<String> comboBox1;
  @FXML
  private TreeView<String> treeView01;
  @FXML
  private TextField charName;
  @FXML
  private ColorPicker colorpicker;

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
    loadTitle();
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
   * CharCssInfoの読込を行う
   * @throws IOException
   * @version 2021/01/05 1.0.0 新規作成
   * @since 1.0.0
   * @author wadamasaya
   */
  @SuppressWarnings("unchecked")
  @FXML
  private void reloadTreeView() throws IOException {
    String titleName = comboBox1.getValue();
    // CSVファイルを読込、CharCssInfoのリストを返却する。
    List<CharInfo> a = CharCssInfoLoader.charCssInfoLoader(titleName);

    // rootの宣言
    TreeItem<String> rootItem = new TreeItem<>("キャラ一覧");
    // TreeViewを更新
    for (CharInfo b : a) {
      // rootにアイテムを追加していく
      rootItem.getChildren().addAll(new TreeItem<>(b.getCharName()));
    }
    // rootにアイテムをセット
    treeView01.setRoot(rootItem);
  }

  /**
   * キャラクター情報を追加する
   * @throws IOException
   * @version 2021/01/05 1.0.0 新規作成
   * @since 1.0.0
   * @author wadamasaya
   */
  @FXML
  private void addCharacter() throws IOException {
    // タイトルを取得
    String titleName = comboBox1.getValue();

    // キャラ名を取得
    String name = charName.getText().toString();

    // キャラ色を取得
    String colorStr = toHexString(colorpicker.getValue());

    // 入力済みかの確認
    if (!(name == "")) {
      // CSVファイルを読込、CharCssInfoのリストを返却する
      List<CharInfo> a = CharCssInfoLoader.charCssInfoLoader(titleName);

      // 同名の確認
      boolean charNameSameFlg = false;
      for (CharInfo c : a) {
        if (name == c.getCharName()) {
          charNameSameFlg = true;
        }
      }

      if (!charNameSameFlg) {
        // TreeViewの選択箇所のデータを変更
        CharInfo b = new CharInfo(name, name.substring(0, 1), "TextColor" + checkClassNum(a),
            colorStr.substring(0, 7));
        a.add(b);

        // CharCssInfoをCSVに上書きする
        CharCssInfoBuilder.charCssInfoBuilder(a, titleName);

        // TreeViewを再読込する
        reloadTreeView();
      }
    }
  }

  /**
   * キャラクター情報を編集する
   * @throws IOException
   * @version 2021/01/05 1.0.0 新規作成
   * @since 1.0.0
   * @author wadamasaya
   */
  @FXML
  private void editCharacter() throws IOException {
    // タイトルを取得
    String titleName = comboBox1.getValue();

    // キャラ色を取得
    String colorStr = toHexString(colorpicker.getValue());

    // TreeViewの選択している行番号を取得。(0は対象外のため-1)
    int focusedIndex = treeView01.getFocusModel().getFocusedIndex() - 1;
    // CSVファイルを読込、ItemInfoのリストを返却する
    List<CharInfo> a = CharCssInfoLoader.charCssInfoLoader(titleName);

    // TreeView未選択の場合は処理を実施しない。
    if (focusedIndex >= 0) {
      Alert dialog = new Alert(AlertType.CONFIRMATION);
      dialog.setTitle("変更確認ダイアログ");
      dialog.setContentText(a.get(focusedIndex).getCharName() + "のキャラ文字色を変更しますか？");
      Optional<ButtonType> result = dialog.showAndWait();

      // OKボタンがクリックされたら処理実行
      if (result.get() == ButtonType.OK) {

        // 対象のCharCssInfoを更新
        CharInfo b = a.get(focusedIndex);
        b = new CharInfo(b.getCharName(), b.getCharInitial(), b.getCssClassName(), colorStr.substring(0, 7));
        a.set(focusedIndex, b);

        // CharCssInfoをCSVに上書きする
        CharCssInfoBuilder.charCssInfoBuilder(a, titleName);

        // TreeViewを再読込する
        reloadTreeView();
      }
    }
  }

  /**
   * キャラクター情報を削除する
   * @throws IOException
   * @version 2021/01/05 1.0.0 新規作成
   * @since 1.0.0
   * @author wadamasaya
   */
  @FXML
  private void deleteCharacter() throws IOException {
    // タイトルを取得
    String titleName = comboBox1.getValue();
    // TreeViewの選択している行番号を取得。(0は対象外のため-1)
    int focusedIndex = treeView01.getFocusModel().getFocusedIndex() - 1;
    // CSVファイルを読込、ItemInfoのリストを返却する
    List<CharInfo> a = CharCssInfoLoader.charCssInfoLoader(titleName);

    // TreeView未選択の場合は処理を実施しない。
    if (focusedIndex >= 0) {
      Alert dialog = new Alert(AlertType.CONFIRMATION);
      dialog.setTitle("削除確認ダイアログ");
      dialog.setContentText(a.get(focusedIndex).getCharName() + "を削除しますか？");
      Optional<ButtonType> result = dialog.showAndWait();

      // OKボタンがクリックされたら処理実行
      if (result.get() == ButtonType.OK) {

        // ファイル削除、TitleInfoから削除
        a.remove(focusedIndex);

        // CharCssInfoをCSVに上書きする
        CharCssInfoBuilder.charCssInfoBuilder(a, titleName);

        // TreeViewを再読込する
        reloadTreeView();
      }
    }
  }

  /**
   * 情報を取得し、入力フォームに反映させる。
   * @throws IOException
   * @version 2021/01/05 1.0.0 新規作成
   * @since 1.0.0
   * @author wadamasaya
   */
  @FXML
  private void getContent() throws IOException {
    String titleName = comboBox1.getValue();

    // TreeViewの選択している行番号を取得。(0は対象外のため-1)
    int focusedIndex = treeView01.getFocusModel().getFocusedIndex() - 1;
    // TreeView未選択の場合は処理を実施しない。
    if (focusedIndex >= 0) {

      // CSVファイルを読込、ItemInfoのリストを返却する
      List<CharInfo> a = CharCssInfoLoader.charCssInfoLoader(titleName);

      // キャラ名をセット
      charName.setText(a.get(focusedIndex).getCharName());
    }
  }

  /**
   * カラーピッカーで取得した値を16進数に変換し返却。
   * @param color カラーピッカーで取得した値
   * @return 16進数
   * @version 2021/01/05 1.0.0 新規作成
   * @since 1.0.0
   * @author wadamasaya
   */
  private static String toHexString(Color color) {
    int r = ((int) Math.round(color.getRed() * 255)) << 24;
    int g = ((int) Math.round(color.getGreen() * 255)) << 16;
    int b = ((int) Math.round(color.getBlue() * 255)) << 8;
    int a = ((int) Math.round(color.getOpacity() * 255));

    return String.format("#%08X", (r + g + b + a));
  }

  /**
   * 使用されていない最小値のCSSクラス名を取得
   * @param charCssInfoList CharCssInfoのList
   * @return 最小値のCSSクラス名
   * @version 2021/01/05 1.0.0 新規作成
   * @since 1.0.0
   * @author wadamasaya
   */
  private static String checkClassNum(List<CharInfo> charCssInfoList) {
    ArrayList<Integer> usedNumList = new ArrayList<Integer>();

    // 使用済みの数値を配列に格納する
    for (CharInfo a : charCssInfoList) {
      Integer num = Integer.parseInt(a.getCssClassName().substring(10, 11));
      usedNumList.add(num);
    }

    // 配列の内容をソートする
    Collections.sort(usedNumList);

    // 未使用の最小値を取得
    Integer noUsedNum = 0;
    Integer i = 1;
    for (Integer a : usedNumList) {
      if (a == i) {
        if (a < i) {
          noUsedNum = i;
        }
      }
      i++;
    }

    // 最小値が見つからなかった場合
    if (noUsedNum.equals(0)) {
      if (usedNumList.isEmpty()) {
        noUsedNum = 1;
      } else {
        noUsedNum = usedNumList.get(usedNumList.size() - 1) + 1;
      }
    }

    // 未使用の最小値をString型に変換
    if (noUsedNum < 10) {
      return "0" + noUsedNum.toString();
    } else {
      return noUsedNum.toString();
    }
  }
}