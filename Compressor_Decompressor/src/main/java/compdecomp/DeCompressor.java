 package compdecomp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;

public class DeCompressor  {
	public static void method(File file)throws IOException {
		String path=file.getParent();
		
		FileInputStream fis=new FileInputStream(file);
		GZIPInputStream zip=new GZIPInputStream(fis);
		FileOutputStream fos=new FileOutputStream(path+"/decompresed_"+file.getName());
		
		byte[] buffer=new byte[1024];
		int n;
		while((n=zip.read(buffer))!=-1) {
			fos.write(buffer, 0, n);
		}
		fos.close();
		zip.close();
		fis.close();
	}

}
