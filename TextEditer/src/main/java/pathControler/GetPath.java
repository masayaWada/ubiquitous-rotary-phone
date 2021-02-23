package pathControler;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.CodeSource;
import java.security.ProtectionDomain;

import JavaFXSample.javafx.App;

/**
 * Propertiesファイル作成クラス
 * @version 2021/01/05 1.0.0 新規作成
 * @since 1.0.0
 * @author wadamasaya
 */
public class GetPath {

  /**
   * rootのフォルダパスを取得。
   *
   * @param targetFilePath
   * @param targetFileName
   * @return rootのフォルダパス
   * @version 2021/01/05 1.0.0 新規作成
   * @since 1.0.0
   * @author wadamasaya
   * @throws IOException
   * @throws URISyntaxException
   */
  public static String getResourceStr() throws URISyntaxException {
    // Propertiesに各設定をセットする
    return getApplicationPath(App.class).getParent().toString();
  }

  /**
   * 引数に渡した、クラスのファイルパスを取得。
   *
   * @throws IOException
   * @version 2021/01/05 1.0.0 新規作成
   * @since 1.0.0
   * @author wadamasaya
   * @throws URISyntaxException
   */
  public static Path getApplicationPath(Class<?> cls) throws URISyntaxException {
    ProtectionDomain pd = cls.getProtectionDomain();
    CodeSource cs = pd.getCodeSource();
    URL location = cs.getLocation();
    URI uri = location.toURI();
    Path path = Paths.get(uri);
    return path;
  }

}