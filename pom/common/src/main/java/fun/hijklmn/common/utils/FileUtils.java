package fun.hijklmn.common.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 文件操作工具类<br>
 * tips : 下面的代码用于练习<br>
 * description :
 *
 * @author : guosong
 * @date : 2017年12月29日
 * @time : 下午2:57:25
 * @project_name : util
 * @package_name : com.store.util
 *
 */
public class FileUtils {

	// 系统分隔符
	private static final String FILESEPARATOR = File.separator;

	private static final String SEPARATOR1 = "\\";

	private static final String SEPARATOR2 = "/";

	/**
	 * 读取文件<br>
	 * 
	 * @param file
	 * @param buffer
	 * @throws IOException
	 */
	public static void write(String filePath, String fileName, ByteBuffer buffer) throws IOException {
		File tempFile = new File(filePath);
		if (!tempFile.exists()) {
			tempFile.mkdirs();
		}
		String path = null;
		if (filePath.endsWith(FILESEPARATOR) || filePath.endsWith(SEPARATOR1) || filePath.endsWith(SEPARATOR2))
			path = filePath + fileName;
		else
			path = filePath + FILESEPARATOR + fileName;
		File file = new File(path);
		if (!file.exists()) {
			file.createNewFile();
		}
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(file, false);
		} catch (FileNotFoundException e) {
			throw new FileNotFoundException("[ " + file.getName() + " ]文件未找到。");
		}
		FileChannel channel = out.getChannel();
		try {
			channel.write(buffer);
		} catch (IOException e) {
			throw new IOException(e);
		} finally {
			if (channel != null) {
				try {
					channel.close();
				} catch (IOException e) {
				}
			}
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
				}
			}
		}

	}

	/**
	 * 读取文件<br>
	 * 
	 * @param file
	 * @param buffer
	 * @throws IOException
	 */
	public static void write(File file, ByteBuffer buffer) throws IOException {
		FileOutputStream out = null;
		try {
			if (!file.exists())
				file.createNewFile();
			out = new FileOutputStream(file, false);
		} catch (FileNotFoundException e) {
			throw new FileNotFoundException("[ " + file.getName() + " ]文件未找到。");
		}
		FileChannel channel = out.getChannel();

		try {
			channel.write(buffer);
		} catch (IOException e) {
			throw new IOException(e);
		} finally {
			if (channel != null) {
				try {
					channel.close();
				} catch (IOException e) {
				}
			}
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
				}
			}
		}

	}

	/**
	 * 删除文件<br>
	 * 
	 * @param filePathName
	 */
	public static boolean delete(String filePathName) {
		File file = new File(filePathName);
		if (file.exists()) {
			file.delete();
			if (file.exists()) {
				return false;
			}
		}
		return true;
	}

}
