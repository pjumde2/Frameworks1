package commonclasses;

import java.io.File;

public class GetLatestFilefromDir {
	
	public static File GetLatestFilefromDirMethod(String path)
	{
		File dir = new File(path);
		File[] files = dir.listFiles();
		if (files == null || files.length == 0) {
			return null;
		}

		File lastModifiedFile = files[0];
		for (int i = 1; i < files.length; i++) {
			if (lastModifiedFile.lastModified() < files[i].lastModified()) {
				lastModifiedFile = files[i];
			}
		}
		return lastModifiedFile;
	}

}
