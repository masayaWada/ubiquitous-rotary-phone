package fileControler.create;

import java.io.File;
import java.io.IOException;

/**
 * ファイル作成処理
 * @version 2021/01/05 1.0.0 新規作成
 * @since 1.0.0
 * @author wadamasaya
 */
public class FileCreate {

  /**
   * 引数に基づきファイルを作成する
   *
   * @param filePath ファイルパス
   * @version 2021/01/05 1.0.0 新規作成
   * @since 1.0.0
   * @author wadamasaya
   * @throws IOException
   */
  public static void createFile(final String filePath) throws IOException {
    File newfile = new File(filePath);
    newfile.createNewFile();
  }

  /**
   * 引数に基づきフォルダを作成する
   *
   * @param folderPath フォルダパス
   * @version 2021/01/05 1.0.0 新規作成
   * @since 1.0.0
   * @author wadamasaya
   * @throws IOException
   */
  public static void createFolder(final String folderPath) throws IOException {
    File newdir = new File(folderPath);
    newdir.mkdir();
  }
}