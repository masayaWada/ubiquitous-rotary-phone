package fileControler.builder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import constants.CsvConstants;
import dataClass.javaFxTreeView.CharInfo;
import fileControler.load.CharCssInfoLoader;
import fileControler.load.CsvLoader;
// TODO コメント修正、プログラム正規化

/**
 * HTMLファイル作成処理
 *
 * @version 2021/01/05 1.0.0 新規作成
 * @since 1.0.0
 * @author wadamasaya
 */
public class HtmlBuilder {

  public static void htmlBuilder(String filePath) throws IOException {
    // HTMLで表示したいファイルの内容を取得
    List<String[]> data = CsvLoader.csvLoader(filePath);

    // TODO ファイルパスはpropertieファイルで管理
    File htmlFile = new File("src/main/resources/Files/SentenceCheck/tmp.html");
    FileWriter filewriter = new FileWriter(htmlFile);

    filewriter.write("<!DOCTYPE html>" + CsvConstants.NEW_LINE);
    filewriter.write("<html lang=\"ja\">" + CsvConstants.NEW_LINE);
    filewriter.write("  <head>" + CsvConstants.NEW_LINE);
    filewriter.write("    <meta charset=\"UTF-8\">" + CsvConstants.NEW_LINE);
    // TODO リンクするCSSファイルのディレクトリを修正
    filewriter.write("    <link rel=\"stylesheet\" href=\"./css/styleSheet.css\">" + CsvConstants.NEW_LINE);
    filewriter.write("    <title>CSS</title>" + CsvConstants.NEW_LINE);
    filewriter.write("  </head>" + CsvConstants.NEW_LINE);
    filewriter.write("  <body>" + CsvConstants.NEW_LINE);
    filewriter.write("    <div>" + CsvConstants.NEW_LINE);

    // ファイルの内容をHTMLに表示
    for (String[] line_data : data) {
      if (line_data[0].matches(".+「.*")) {
        String cssClassName = applyCssChecker(line_data[0].charAt(0));
        filewriter.write("        <main class=\"" + cssClassName + "\">" + line_data[0] + "</main>");
        filewriter.write(CsvConstants.NEW_LINE);
      } else {
        filewriter.write("        <main class=\"textColor00\">" + line_data[0] + "</main>");
        filewriter.write(CsvConstants.NEW_LINE);
      }
    }
    filewriter.write("    </div>" + CsvConstants.NEW_LINE);
    filewriter.write("  </body>" + CsvConstants.NEW_LINE);
    filewriter.write("</html>" + CsvConstants.NEW_LINE);

    // 追記した内容を上書きし、ファイルを閉じる
    filewriter.flush();
    filewriter.close();
  }

  /**
   * キャラ頭文字を元に、反映させるCSSのClass名を返却。
   * 対象のClass名が見つからなかった場合は、デフォルトのClass名を返却。
   *
   * @param c キャラ頭文字
   * @return CSSのClass名
   * @version 2021/01/05 1.0.0 新規作成
   * @since 1.0.0
   * @author wadamasaya
   */
  private static String applyCssChecker(char charInitial) {
    // TODO アニメ名は動的にセットするよう修正
    List<CharInfo> a = CharCssInfoLoader.charCssInfoLoader("鬼滅の刃");
    for (CharInfo charCssInfo : a) {
      // キャラ頭文字確認
      if (String.valueOf(charInitial).equals(charCssInfo.getCharInitial())) {
        // CSSのClass名を返却
        return charCssInfo.getCssClassName();
      }
    }
    return "textColor00";
  }
}