package fileControler.load;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import dataClass.ProjectProperty;
import pathControler.GetPath;

public class FolderInfoLoader {
  /**
   *
   * @param propertieFileName
   * @return
   * @throws IOException
   * @version 2021/01/05 1.0.0 新規作成
   * @since 1.0.0
   * @author wadamasaya
   */
  public static ProjectProperty folderInfoLoader(String propertieFileName) throws IOException {
    // Properties の読み込み
    Properties settings = new Properties();
    FileInputStream in = null;
    try {
      in = new FileInputStream(GetPath.getConfigPath() + "/FolderInfo/" + propertieFileName);
      settings.load(in);
    } finally {
      if (in != null) {
        in.close();
      }
    }

    // 設定ファイルのTargetFilePathを読み込む
    String animeTitle = settings.getProperty("AnimeTitle");
    String uploadPassword = settings.getProperty("UploadPassword");
    String contentPassword = settings.getProperty("ContentPassword");
    Boolean hiddenFlg = false;
    // HiddenFlgを文字列からBoolean型に変換
    if ("true".equals(settings.getProperty("HiddenFlg"))) {
      hiddenFlg = true;
    }

    Boolean lockFlg = false;
    // lockFlgを文字列からBoolean型に変換
    if ("true".equals(settings.getProperty("LockFlg"))) {
      lockFlg = true;
    }

    ProjectProperty getProperty = new ProjectProperty(animeTitle, uploadPassword, contentPassword, hiddenFlg, lockFlg);

    return getProperty;
  }
}