/**
 * 
 */
package developer;

import java.awt.image.BufferedImage;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

/**
* @Description: ����ʶ�𹤾�
* @author Miao
* @date 2015��12��14�� ����5:16:15
*
*/
public class Ocr {
	static {
		tesseract = new Tesseract();
	}

	private static ITesseract tesseract;

	/**
	 * ����Ĭ���ֿ�ʶ��
	 * @param img
	 * @return
	 * @throws TesseractException
	 */
	public static String doOcr(BufferedImage img) throws TesseractException {
		return tesseract.doOCR(img);
	}

	/**
	 * ���������ֿ�
	 * @param img
	 * @return
	 * @throws TesseractException
	 */
	public static String odOcr(BufferedImage img) throws TesseractException {
		tesseract.setLanguage("chi_sim");
		return tesseract.doOCR(img);
	}
}
