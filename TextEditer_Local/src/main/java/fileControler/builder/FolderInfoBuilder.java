package fileControler.builder;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import dataClass.javaFxTreeView.ProjectProperty;

/**
 * FolderInfoフォルダ内のファイル作成処理
   * @version 2021/01/05 1.0.0 新規作成
   * @since 1.0.0
   * @author wadamasaya
 */
public class FolderInfoBuilder {
  public static void folderInfoBuilder(ProjectProperty a, String propertieFileName) throws IOException {
    // Propertiesに各設定をセットする
    Properties settings = new Properties();
    settings.setProperty("AnimeTitle", a.getAnimeTitle());
    settings.setProperty("UploadPassword", a.getUploadPassword());
    settings.setProperty("ContentPassword", a.getContentPassword());
    settings.setProperty("HiddenFlg", a.getHiddenFlg().toString());
    settings.setProperty("LockFlg", a.getLockFlg().toString());

    // TODO フォルダパスはpropertieファイルで管理
    FileOutputStream out = new FileOutputStream("src/main/resources/Files/FolderInfo/" + propertieFileName);
    // セットした設定を上書き
    settings.store(out, "遷移先一時保存ファイル");
    if (out != null) {
      out.close();
    }
  }
}