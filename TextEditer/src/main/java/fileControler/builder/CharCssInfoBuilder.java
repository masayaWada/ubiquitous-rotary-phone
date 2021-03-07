package fileControler.builder;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import constants.CsvConstants;
import dataClass.CharInfo;
import pathControler.GetPath;

/**
 * CharacterInfoフォルダ内のファイル作成処理
 * @version 2021/01/05 1.0.0 新規作成
 * @since 1.0.0
 * @author wadamasaya
 */
public class CharCssInfoBuilder {

  /**
   * CharacterInfoフォルダ内の各タイトルファイルを、渡したリストの内容で更新
   * @param data
   * @param titleName
   * @version 2021/01/05 1.0.0 新規作成
   * @since 1.0.0
   * @author wadamasaya
   * @throws IOException
   */
  public static void charCssInfoBuilder(final List<CharInfo> charInfoList, final String titleName) throws IOException {
    FileWriter fileWriter = new FileWriter(GetPath.getConfigPath() + "/CharacterInfo/" + titleName + ".csv");

    // リストの内容を順に処理
    for (CharInfo charInfo : charInfoList) {
      fileWriter.append(charInfo.getCharName());
      fileWriter.append(CsvConstants.COMMA);
      fileWriter.append(charInfo.getCharInitial());
      fileWriter.append(CsvConstants.COMMA);
      fileWriter.append(charInfo.getCssClassName());
      fileWriter.append(CsvConstants.COMMA);
      fileWriter.append(charInfo.getCharColor());
      fileWriter.append(CsvConstants.NEW_LINE);
    }

    // 追記した内容を上書きし、ファイルを閉じる
    fileWriter.flush();
    fileWriter.close();
  }
}