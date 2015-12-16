/**
 * 
 */
package developer;

import java.awt.image.BufferedImage;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

/**
* @Description: 文字识别工具
* @author Miao
* @date 2015年12月14日 下午5:16:15
*
*/
public class Ocr {
	static {
		tesseract = new Tesseract();
	}

	private static ITesseract tesseract;

	/**
	 * 测试默认字库识别
	 * @param img
	 * @return
	 * @throws TesseractException
	 */
	public static String doOcr(BufferedImage img) throws TesseractException {
		return tesseract.doOCR(img);
	}

	/**
	 * 测试设置字库
	 * @param img
	 * @return
	 * @throws TesseractException
	 */
	public static String odOcr(BufferedImage img) throws TesseractException {
		tesseract.setLanguage("chi_sim");
		return tesseract.doOCR(img);
	}
}
