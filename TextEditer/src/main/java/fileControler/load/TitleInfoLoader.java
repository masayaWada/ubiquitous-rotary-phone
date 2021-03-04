package fileControler.load;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import dataClass.javaFxTreeView.TitleInfo;
import pathControler.GetPath;

public class TitleInfoLoader {

  /**
   * CSVファイルを読み込み、ItemInfoのListを返却する。
   *
   * @return
   * @version 2021/01/05 1.0.0 新規作成
   * @since 1.0.0
   * @author wadamasaya
   * @throws IOException
   */
  public static List<TitleInfo> titleInfoLoader() throws IOException {

    // TODO ファイルパスはpropertieファイルで管理
    String file_name = GetPath.getConfigPath() + "/TitleInfo.csv";
    // ItemInfoファイルからデータを取得
    List<String[]> data = CsvLoader.csvLoader(file_name);

    // ItemInfoファイルから取得したデータをItemInfoのリストに格納
    List<TitleInfo> a = new ArrayList<TitleInfo>();
    TitleInfo b;

    for (String[] line_data : data) {
      // 各行データを要素毎に処理
      b = new TitleInfo(line_data[0], line_data[１]);
      a.add(b);
    }

    // ItemInfoのListを返却
    return a;
  }
}