package fileControler.load;

import java.util.ArrayList;
import java.util.List;

import dataClass.javaFxTreeView.ItemInfo;

public class ItemInfoLoader {
  /**
   * CSVファイルを読み込み、ItemInfoのListを返却する。
   *
   * @return
   * @version 2021/01/05 1.0.0 新規作成
   * @since 1.0.0
   * @author wadamasaya
   */
  public static List<ItemInfo> itemInfoLoader() {

    // TODO ファイルパスはpropertieファイルで管理
    String file_name = "src/main/resources/Files/FileInfo.csv";
    // ItemInfoファイルからデータを取得
    List<String[]> data = CsvLoader.csvLoader(file_name);

    // ItemInfoファイルから取得したデータをItemInfoのリストに格納
    List<ItemInfo> a = new ArrayList<ItemInfo>();
    ItemInfo b;

    for (String[] line_data : data) {
      // 各行データを要素毎に処理
      b = new ItemInfo(line_data[0], line_data[1], line_data[2]);
      a.add(b);
    }

    // ItemInfoのListを返却
    return a;
  }
}