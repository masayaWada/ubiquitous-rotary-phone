package fileControler.load;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
// TODO コメント修正、プログラム正規化

public class CsvLoader {
  /**
   * CSVファイルを読み込み、ItemInfoのListを返却する
   *
   * @param filePath
   * @return
   * @version 2021/01/05 1.0.0 新規作成
   * @since 1.0.0
   * @author wadamasaya
   */
  public static List<String[]> csvLoader(String filePath) {
    BufferedReader br = null;

    // データを格納するリスト
    List<String[]> data = new ArrayList<String[]>();
    try {
      File file = new File(filePath);
      br = new BufferedReader(new FileReader(file));

      // readLineで一行ずつ読み込む
      String line;
      while ((line = br.readLine()) != null) {
        // lineをカンマで分割し、配列リストdataに追加
        data.add(line.split(","));
      }

      // catch-finally部分は同様なので省略
    } catch (FileNotFoundException e) {
      // TODO 自動生成された catch ブロック
      e.printStackTrace();
    } catch (IOException e) {
      // TODO 自動生成された catch ブロック
      e.printStackTrace();
    }

    // CSVファイルの内容を返却
    return data;
  }
}