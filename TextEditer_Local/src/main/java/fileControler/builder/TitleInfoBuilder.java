package fileControler.builder;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import constants.CsvConstants;
// TODO コメント修正、プログラム正規化
import dataClass.javaFxTreeView.TitleInfo;

/**
 *
 *
 * @version 2021/01/05 1.0.0 新規作成
 * @since 1.0.0
 * @author wadamasaya
 */
public class TitleInfoBuilder {

  public static void itemInfoBuilder(List<TitleInfo> titleInfoList) throws IOException {
    // TODO ファイルパスはpropertieファイルで管理
    FileWriter fileWriter = new FileWriter("src/main/resources/Files/TitleInfo.csv");

    // リストの内容を順に処理
    for (TitleInfo titleInfo : titleInfoList) {
      fileWriter.append(titleInfo.getTitle());
      fileWriter.append(CsvConstants.COMMA);
      fileWriter.append(titleInfo.getFilePath());
      fileWriter.append(CsvConstants.NEW_LINE);
    }

    // 追記した内容を上書きし、ファイルを閉じる
    fileWriter.flush();
    fileWriter.close();
  }
}