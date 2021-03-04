package fileControler.builder;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import pathControler.GetPath;
// TODO コメント修正、プログラム正規化

/**
 * Propertiesファイル作成クラス
 * @version 2021/01/05 1.0.0 新規作成
 * @since 1.0.0
 * @author wadamasaya
 */
public class PropertiesBuilder {

  /**
   * TmpPathDataファイル作成処理
   *
   * @param targetFilePath
   * @param targetFileName
   * @throws IOException
   * @version 2021/01/05 1.0.0 新規作成
   * @since 1.0.0
   * @author wadamasaya
   */
  public static void tmpPathDataBuilder(String targetFilePath, String targetFileName) throws IOException {
    // Propertiesに各設定をセットする
    Properties settings = new Properties();
    settings.setProperty("TargetFilePath", targetFilePath);
    settings.setProperty("TargetFileName", targetFileName);

    // TODO フォルダパスはpropertieファイルで管理
    FileOutputStream out = new FileOutputStream(GetPath.getConfigPath() + "/TmpPathData.properties");

    // セットした設定を上書き
    settings.store(out, "遷移先一時保存ファイル");
    if (out != null) {
      out.close();
    }
  }

  /**
  * TmpDataファイル作成処理
  *
  * @param nowEditProjectName 現編集プロジェクト名
  * @throws IOException
  * @version 2021/01/05 1.0.0 新規作成
  * @since 1.0.0
  * @author wadamasaya
  */
  public static void tmpDataBuilder(String nowEditProjectName) throws IOException {
    // Propertiesに各設定をセットする
    Properties settings = new Properties();
    settings.setProperty("NowEditProjectName", nowEditProjectName);

    // TODO フォルダパスはpropertieファイルで管理
    FileOutputStream out = new FileOutputStream(GetPath.getConfigPath() + "/TmpData.properties");

    // セットした設定を上書き
    settings.store(out, "各種情報一時保存ファイル");
    if (out != null) {
      out.close();
    }
  }
}