package fileControler.exists;

import java.io.File;
import java.io.IOException;
// TODO コメント修正、プログラム正規化

/**
 * ファイル、フォルダ存在確認処理
 * @version 2021/01/05 1.0.0 新規作成
 * @since 1.0.0
 * @author wadamasaya
 */
public class FileExists {

  /**
   * 引数に基づきファイル、フォルダの存在確認を実施する
   *
   * @param filePath ファイルパス、フォルダパス
   * @return ファイルの存在の是非(ファイル有:true ファイル無し:false)
   * @version 2021/01/05 1.0.0 新規作成
   * @since 1.0.0
   * @author wadamasaya
   * @return
   * @throws IOException
   */
  public static boolean createFile(final String filePath) throws IOException {
    File file = new File(filePath);
    return file.exists();
  }

}