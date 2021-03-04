package pathControler;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * {@link URL#getFile()}は使用できない。
 * URLでは漢字等がエンコーディングされているが、getFile()はエンコーディング状態のまま取得してしまう。
 * @author ysugimura
 */
public class URLUtil {

  /** fileの{@link URL}から{@link Path}を取得する */
  public static Path getPath(URL url) {
    try {
      return Paths.get(url.toURI());
    } catch (URISyntaxException ex) {
      throw new RuntimeException(ex);
    }
  }

  /** fileの{@link URL}から{@link File}を取得する */
  public static File getFile(URL url) {
    return getPath(url).toFile();
  }
}