package compdecomp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;

public class Compressor {

	public static void method(File file)throws IOException {
		String path=file.getParent();
		
		FileInputStream fis=new FileInputStream(file);
		FileOutputStream fos=new FileOutputStream(path+"/compressed_"+file.getName());
		GZIPOutputStream zop=new GZIPOutputStream(fos);
		
		byte[] buffer=new byte[1024];
		int n;
		while((n=fis.read(buffer))!=-1) {
			zop.write(buffer, 0, n);
		}
		zop.close();
		fos.close();
		fis.close();
		
	}
}
