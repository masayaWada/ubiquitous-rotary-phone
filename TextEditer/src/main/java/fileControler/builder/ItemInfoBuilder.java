package fileControler.builder;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import constants.CsvConstants;
import dataClass.ItemInfo;
import pathControler.GetPath;

/**
 * FileInfoファイル作成処理
 * @version 2021/01/05 1.0.0 新規作成
 * @since 1.0.0
 * @author wadamasaya
 */
public class ItemInfoBuilder {

  public static void itemInfoBuilder(List<ItemInfo> dataInfoList) throws IOException {
    // TODO ファイルパスはpropertieファイルで管理
    FileWriter fileWriter = new FileWriter(GetPath.getConfigPath() + "/FileInfo.csv");

    // リストの内容を順に処理
    for (ItemInfo dataInfo : dataInfoList) {
      fileWriter.append(dataInfo.getFileName());
      fileWriter.append(CsvConstants.COMMA);
      fileWriter.append(dataInfo.getParentFolderPath());
      fileWriter.append(CsvConstants.COMMA);
      fileWriter.append(dataInfo.getFilePath());
      fileWriter.append(CsvConstants.NEW_LINE);
    }

    // 追記した内容を上書きし、ファイルを閉じる
    fileWriter.flush();
    fileWriter.close();
  }
}