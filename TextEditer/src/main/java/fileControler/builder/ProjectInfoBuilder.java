package fileControler.builder;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import constants.CsvConstants;
import dataClass.ProjectInfo;
import pathControler.GetPath;

/**
 * ProjectInfoファイル作成処理
 * @version 2021/01/05 1.0.0 新規作成
 * @since 1.0.0
 * @author wadamasaya
 */
public class ProjectInfoBuilder {

  public static void projectInfoBuilder(List<ProjectInfo> dataInfoList) throws IOException {
    // TODO ファイルパスはpropertieファイルで管理
    FileWriter fileWriter = new FileWriter(GetPath.getConfigPath() + "/ProjectInfo.csv");

    // リストの内容を順に処理
    for (ProjectInfo dataInfo : dataInfoList) {
      fileWriter.append(dataInfo.getProjectName());
      fileWriter.append(CsvConstants.COMMA);
      fileWriter.append(dataInfo.getFolderPath());
      fileWriter.append(CsvConstants.COMMA);
      fileWriter.append(dataInfo.getProjectInfoPath());
      fileWriter.append(CsvConstants.NEW_LINE);
    }

    // 追記した内容を上書きし、ファイルを閉じる
    fileWriter.flush();
    fileWriter.close();
  }
}