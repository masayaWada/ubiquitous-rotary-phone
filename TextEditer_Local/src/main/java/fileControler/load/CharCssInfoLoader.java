package fileControler.load;

import java.util.ArrayList;
import java.util.List;

import dataClass.javaFxTreeView.CharInfo;

public class CharCssInfoLoader {
  /**
   * titleNameを元にCSVファイルを読み込み、CharCssInfoのListを返却する。
   * @param titleName
   * @return
   * @version 2021/01/05 1.0.0 新規作成
   * @since 1.0.0
   * @author wadamasaya
   */
  public static List<CharInfo> charCssInfoLoader(String titleName) {

    // TODO ファイルパスはpropertieファイルで管理
    String file_name = "src/main/resources/Files/CharacterInfo/" + titleName + ".csv";
    // CharCssInfoファイルからデータを取得
    List<String[]> data = CsvLoader.csvLoader(file_name);

    // ItemInfoファイルから取得したデータをItemInfoのリストに格納
    List<CharInfo> a = new ArrayList<CharInfo>();
    CharInfo b;

    for (String[] line_data : data) {
      // 各行データを要素毎に処理
      b = new CharInfo(line_data[0], line_data[1], line_data[2], line_data[3]);
      a.add(b);
    }

    // CharCssInfoのListを返却
    return a;
  }
}