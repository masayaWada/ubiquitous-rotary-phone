package fileControler.builder;

import java.io.FileWriter;
import java.io.IOException;

import constants.CsvConstants;
// TODO コメント修正、プログラム正規化

/**
 * TextFolderフォルダ内のファイル作成処理
 * @version 2021/01/05 1.0.0 新規作成
 * @since 1.0.0
 * @author wadamasaya
 */
public class ContentTextBuilder {

  public static void itemInfoBuilder(String[] data, String filePath) throws IOException {
    // TODO ファイルパスはpropertieファイルで管理
    FileWriter fileWriter = new FileWriter("src/main/resources/Files/TextFolder/" + filePath);

    // リストの内容を順に処理
    for (String line_data : data) {
      fileWriter.append(line_data);
      fileWriter.append(CsvConstants.NEW_LINE);
    }

    // 追記した内容を上書きし、ファイルを閉じる
    fileWriter.flush();
    fileWriter.close();
  }
}