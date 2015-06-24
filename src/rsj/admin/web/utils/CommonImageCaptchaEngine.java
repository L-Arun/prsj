package rsj.admin.web.utils;

import java.awt.Color;
import java.awt.Font;

import com.octo.captcha.component.image.backgroundgenerator.BackgroundGenerator;
import com.octo.captcha.component.image.backgroundgenerator.UniColorBackgroundGenerator;
import com.octo.captcha.component.image.color.RandomRangeColorGenerator;
import com.octo.captcha.component.image.fontgenerator.FontGenerator;
import com.octo.captcha.component.image.fontgenerator.RandomFontGenerator;
import com.octo.captcha.component.image.textpaster.RandomTextPaster;
import com.octo.captcha.component.image.textpaster.TextPaster;
import com.octo.captcha.component.image.wordtoimage.ComposedWordToImage;
import com.octo.captcha.component.image.wordtoimage.WordToImage;
import com.octo.captcha.component.word.wordgenerator.RandomWordGenerator;
import com.octo.captcha.component.word.wordgenerator.WordGenerator;
import com.octo.captcha.engine.image.fisheye.SimpleFishEyeEngine;
import com.octo.captcha.image.gimpy.GimpyFactory;

public class CommonImageCaptchaEngine extends SimpleFishEyeEngine {

	@Override
	protected void buildInitialFactories() {
		// �����ɵ��ַ�
		WordGenerator wgen = new RandomWordGenerator(
				"1235689");
		RandomRangeColorGenerator cgen = new RandomRangeColorGenerator(
				new int[]{255, 255}, new int[]{255, 255}, new int[]{255, 255});
		// ������ʾ�ĸ���
		TextPaster textPaster = new RandomTextPaster(new Integer(4),
				new Integer(4), cgen, false);
		// ͼƬ�Ĵ�С
		BackgroundGenerator backgroundGenerator = new UniColorBackgroundGenerator(
				new Integer(60), new Integer(30),Color.GRAY);
		// �����ʽ
		Font[] fontsList = new Font[]{new Font("Arial", 0, 18),
				new Font("Tahoma", 0, 18), new Font("Verdana", 0, 18),};
		// ���ֵĴ�С
		FontGenerator fontGenerator = new RandomFontGenerator(new Integer(18),
				new Integer(18), fontsList);
		WordToImage wordToImage = new ComposedWordToImage(fontGenerator,
				backgroundGenerator, textPaster);
		this.addFactory(new GimpyFactory(wgen, wordToImage));
	}

}
