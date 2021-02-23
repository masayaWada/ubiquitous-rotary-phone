package fileControler.load;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
// TODO コメント修正、プログラム正規化

public class PropertiesLoader {

  /**
   *
   * @return
   * @throws IOException
   * @version 2021/01/05 1.0.0 新規作成
   * @since 1.0.0
   * @author wadamasaya
   */
  public static String[] tmpPathDataLoader() throws IOException {
    // Propertiesの読み込み
    Properties settings = new Properties();
    FileInputStream in = null;
    try {
      in = new FileInputStream("src/main/resources/Files/TmpPathData.properties");
      settings.load(in);
    } finally {
      if (in != null) {
        in.close();
      }
    }

    // 設定ファイルのTargetFilePathを読み込む
    String targetFilePath = settings.getProperty("TargetFilePath");
    String targetFileName = settings.getProperty("TargetFileName");
    String[] getProperty = { targetFilePath, targetFileName };

    return getProperty;
  }

  /**
  *
  * @return
  * @throws IOException
  * @version 2021/01/05 1.0.0 新規作成
  * @since 1.0.0
  * @author wadamasaya
  */
  public static String[] tmpDataLoader() throws IOException {
    // Propertiesの読み込み
    Properties settings = new Properties();
    FileInputStream in = null;
    try {
      in = new FileInputStream("src/main/resources/Files/TmpData.properties");
      settings.load(in);
    } finally {
      if (in != null) {
        in.close();
      }
    }

    // 設定ファイルのTargetFilePathを読み込む
    String nowEditProjectName = settings.getProperty("NowEditProjectName");
    String[] getProperty = { nowEditProjectName };

    return getProperty;
  }
}