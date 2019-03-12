package fun.hijklmn.common.utils;

import java.io.File;
import java.io.FileFilter;

import org.apache.commons.lang.StringUtils;

/**
 * 文件夹操作工具<br>
 * tips : 下面的代码用于练习<br>
 * description :
 *
 * @author : guosong
 * @date : 2017年12月29日
 * @time : 下午12:35:31
 * @project_name : util
 * @package_name : com.store.util
 *
 */
public class DirUtils {

	private static final String SEPARATOR = File.separator;

	private static boolean isWindows = false;

	private static final String WINDOWSNAME = "Windows";

	static {
		String osName = System.getProperty("os.name");
		if (StringUtils.isEmpty(osName)) {
			System.exit(0);
		}
		if (osName.contains(WINDOWSNAME)) {
			isWindows = true;
		}

	}

	/**
	 * 创建子文件夹
	 * 
	 * @return
	 */
	public static boolean createChildDir(String parentPath, String dirName) {
		File file = new File(parentPath);
		if (!file.isDirectory()) {
			if (!createDir(parentPath)) {
				return false;
			}
		}
		File[] files = file.listFiles(new FileFilter() {
			@Override
			public boolean accept(File pathname) {
				if (pathname.isDirectory() && pathname.getName().contains(dirName)) {
					return true;
				}
				return false;
			}
		});

		if (files == null || files.length == 0) {
			if (!createDir(file.getPath() + SEPARATOR + dirName)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 创建文件夹<br>
	 * 
	 * @param path
	 * @return
	 */
	public static boolean createDir(String path) {
		File pathfile = new File(path);
		if (!pathfile.getAbsolutePath().equals(pathfile.getPath())) {
			return false;
		}
		boolean isCreate = true;
		path = pathfile.getPath();
		String[] strs = null;
		if (path.contains(SEPARATOR)) {
			strs = path.split("\\" + SEPARATOR);
		} else {
			strs = new String[1];
			strs[0] = path;
		}
		StringBuilder stringBuilder = new StringBuilder();
		if (!isWindows) {
			stringBuilder.append(SEPARATOR);
		}
		for (int i = 0; i < strs.length; i++) {
			if (!StringUtils.isBlank(strs[i])) {
				stringBuilder.append(strs[i] + SEPARATOR);
				File file = new File(stringBuilder.toString());
				if (file.exists()) {
					if (file.isFile()) {
						isCreate = false;
					}
				} else {
					if (!file.mkdirs()) {
						isCreate = false;
						break;
					}
				}
			}
		}
		return isCreate;
	}

	/**
	 * 在绝对路径下创建文件夹<br>
	 * 
	 * @param path
	 */
	public static boolean createAbsoluteDir(String path) {
		File pathfile = new File(path);
		boolean isCreate = true;
		path = pathfile.getPath();
		String[] strs = null;
		if (path.contains(SEPARATOR)) {
			strs = path.split("\\" + SEPARATOR);
		} else {
			strs = new String[1];
			strs[0] = path;
		}
		StringBuilder stringBuilder = new StringBuilder();
		if (!isWindows) {
			stringBuilder.append(SEPARATOR);
		}
		for (int i = 0; i < strs.length; i++) {
			if (!StringUtils.isBlank(strs[i])) {
				stringBuilder.append(strs[i] + SEPARATOR);
				File file = new File(stringBuilder.toString());
				if (file.exists()) {
					if (file.isFile()) {
						isCreate = false;
					}
				} else {
					if (!file.mkdirs()) {
						isCreate = false;
						break;
					}
				}
			}
		}
		return isCreate;
	}

}
