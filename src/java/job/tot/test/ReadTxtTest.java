package job.tot.test;

import java.io.IOException;
import java.util.List;

import job.tot.util.FileUtil;

public class ReadTxtTest {
	public static void main(String[] args) {
		try {
			List<String> strList = FileUtil.readTxtFile("E:\\temp\\1.txt");
			for (String str : strList) {
				// System.out.println(str);
				String[] t = str.split("#");
				if (t != null && t.length == 2) {
					String code = t[0];
					String name = t[1];
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
