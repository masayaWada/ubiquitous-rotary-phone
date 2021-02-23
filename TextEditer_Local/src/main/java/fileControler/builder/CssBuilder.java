package fileControler.builder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import constants.CsvConstants;
// TODO コメント修正、プログラム正規化
import dataClass.javaFxTreeView.CharInfo;

/**
 * CSSファイル作成処理
 * @version 2021/01/05 1.0.0 新規作成
 * @since 1.0.0
 * @author wadamasaya
 */
public class CssBuilder {

  public static void cssBuilder(List<CharInfo> a) throws IOException {
    // TODO ファイルパスはpropertieファイルで管理
    File cssFile = new File("src/main/resources/Files/SentenceCheck/css/styleSheet.css");
    FileWriter filewriter = new FileWriter(cssFile);

    // body,div,各キャラのCSSを作成
    bodyBuilder(filewriter);
    divBuilder(filewriter);
    for (CharInfo c : a) {
      classBuilder(filewriter, c);
    }

    // 追記した内容を上書きし、ファイルを閉じる
    filewriter.flush();
    filewriter.close();
  }

  /**
   * body部分のCSSを作成
   * @param filewriter ファイルライター
   * @throws IOException
   * @version 2021/01/05 1.0.0 新規作成
   * @since 1.0.0
   * @author wadamasaya
   */
  private static void bodyBuilder(FileWriter filewriter) throws IOException {
    filewriter.write("body {" + CsvConstants.NEW_LINE);
    filewriter.write("    background: #eeeeee;" + CsvConstants.NEW_LINE);
    filewriter.write("    font-family: Meiryo;" + CsvConstants.NEW_LINE);
    filewriter.write("}" + CsvConstants.NEW_LINE);
  }

  /**
   * div部分のCSSを作成
   * @param filewriter ファイルライター
   * @throws IOException
   * @version 2021/01/05 1.0.0 新規作成
   * @since 1.0.0
   * @author wadamasaya
   */
  private static void divBuilder(FileWriter filewriter) throws IOException {
    filewriter.write("div {" + CsvConstants.NEW_LINE);
    filewriter.write("    background: #eeeeee;" + CsvConstants.NEW_LINE);
    filewriter.write("    padding: 5px;" + CsvConstants.NEW_LINE);
    filewriter.write("    text-align: left;" + CsvConstants.NEW_LINE);
    filewriter.write("    border: 1px solid #a9a9a9" + CsvConstants.NEW_LINE);
    filewriter.write("    font-size: 10px;" + CsvConstants.NEW_LINE);
    filewriter.write("}" + CsvConstants.NEW_LINE);
  }

  /**
   * 各キャラのCSSを生成
   * @param filewriter ファイルライター
   * @throws IOException
   * @version 2021/01/05 1.0.0 新規作成
   * @since 1.0.0
   * @author wadamasaya
   */
  public static void classBuilder(FileWriter filewriter, CharInfo charCssInfo) throws IOException {
    filewriter.write("." + charCssInfo.getCssClassName() + " {" + CsvConstants.NEW_LINE);
    filewriter.write("  	color: " + charCssInfo.getCharColor() + ";" + CsvConstants.NEW_LINE);
    filewriter.write("}" + CsvConstants.NEW_LINE);
  }
}