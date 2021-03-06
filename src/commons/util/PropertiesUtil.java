package commons.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

import commons.entity.Constant;
import commons.entity.NativeCp;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fgoScript.entity.Gudazi;

public class PropertiesUtil {
	private static final Logger LOGGER = LogManager.getLogger(PropertiesUtil.class);
	/**
	 * 取出值
	 * 
	 * @param key
	 * @param filepath
	 * @return
	 */
	private static String getValue(String filepath, String key) {
		Properties prop = new Properties();
		InputStream in = null;
		try {
			File file = new File(filepath);
			if (!file.exists()) {
				file.createNewFile();
			}
			FileInputStream fis = new FileInputStream(file);
			in = new BufferedInputStream(fis);
			prop.load(in); /// 加载属性列表
			Iterator<String> it = prop.stringPropertyNames().iterator();
			while (it.hasNext()) {
				String temp = it.next();
				if (temp.equals(key)) {
					return prop.getProperty(temp);
				}
			}
		} catch (Exception e) {
			LOGGER.error("读取配置信息出错！", e);
		} finally {
			try {
				if (in!=null) {
					in.close();
				}
			} catch (IOException e) {
				LOGGER.error(GameUtil.getStackMsg(e));
			}
		}
		return "";
	}
	public static String getValueFromConfig(String key) {
		String filepath = System.getProperty("user.dir") + "/config/"+ Constant.FGO +"/init.properties";
		return getValue(filepath, key);
	}
	public static String getValueFromTempConfig(String key) {
		String filepath = System.getProperty("user.dir") + "/config/"+ Constant.FGO +"/initTemp.properties";
		return getValue(filepath, key);
	}
	public static String getValueFromColorFile(String key) {
		String filepath = System.getProperty("user.dir") + "/config/"+ Constant.FGO +"/colors.properties";
		return getValue(filepath, key);
	}
	public static String getValueFromCommandCardFile(String key) {
		String filepath = System.getProperty("user.dir") + "/config/"+ Constant.FGO +"/commandCard.properties";
		return getValue(filepath, key);
	}
	public static String getValueFromOpenFile(String key) {
		String filepath = System.getProperty("user.dir") + "/config/"+ Constant.FGO +"/open_"+ NativeCp.getUserName() +".properties";
		String value = getValue(filepath, key);
		if (StringUtils.isBlank(value)) {
			Map<String,String> tempMap = new HashMap<>();
			tempMap.put("openAccount", "0");
			setValueForOpen(tempMap);
		}
		return getValue(filepath, key);
	}
	public static String getValueFromSkillsFile(String key) {
		String filepath = System.getProperty("user.dir") + "/config/"+ Constant.FGO +"/skills.properties";
		return getValue(filepath, key);
	}
	public static String getValueFromSystemFile(String key) {
		String filepath = System.getProperty("user.dir") + "/config/system"+"_"+ NativeCp.getUserName() +".properties";
		return getValue(filepath, key);
	}
	public static String getValueFromHasDoFile(String key) {
		String filepath = System.getProperty("user.dir") + "/config/"+ Constant.FGO +"/hasDo.properties";
		return getValue(filepath, key);
	}
	public static String getValueFromAutoClickFile(String key, String fileName, String relativePath) {
		String filepath = System.getProperty("user.dir") + "/config/"+ relativePath + fileName +"_"+ NativeCp.getUserName() +".properties";
		return getValue(filepath, key);
	}
	public static String getValueFromAutoClickFile(String key, String relativePath) {
		return getValueFromAutoClickFile(key,"clicks", relativePath);
	}
	public static String getValueFromspecialHasDo(String key) {
		String filepath = System.getProperty("user.dir") + "/config/"+ Constant.FGO +"/specialHasDo.properties";
		return getValue(filepath, key);
	}
	public static String getValueFromFileNameAndKey(String key, String fileName, String relativePath){
		String filepath = System.getProperty("user.dir") + "/config/"+ relativePath + fileName + ".properties";
		return getValue(filepath, key);
	}
	/**
	 *
	 * @param map
	 */
	public static void setValue( Map<String, String> map) {
		Properties prop = new Properties();
		String filepath = System.getProperty("user.dir") + "/config/"+ Constant.FGO +"/colors.properties";
		/// 保存属性到b.properties文件
		FileOutputStream oFile = null;
		try {
			LOGGER.info("写入新属性");
			oFile = new FileOutputStream(filepath, false);
			Set<Map.Entry<String, String>> entrySet = map.entrySet();
			for (Map.Entry<String, String> entry : entrySet) {
				prop.put(entry.getKey(), entry.getValue());
			}
			prop.store(oFile, "The New properties file");
		} catch (Exception e) {
			LOGGER.error("写入配置信息出错！", e);
			e.printStackTrace();
		} finally {
			try {
				if (oFile!=null) {
					oFile.close();
				}
			} catch (IOException e) {
				LOGGER.error(GameUtil.getStackMsg(e));
			}
		}
	}
	/**
	 *
	 * @param map
	 */
	public static void setValueForInitTemp( Map<String, String> map) {
		String filepath = System.getProperty("user.dir") + "/config/"+ Constant.FGO +"/initTemp.properties";
		setValueForUpdateAndAdd(map, filepath);
	}
	/**
	 *
	 * @param map
	 */
	public static void setValueForSkills( Map<String, String> map) {
		String filepath = System.getProperty("user.dir") + "/config/"+ Constant.FGO +"/skills.properties";
		setValueForUpdateAndAdd(map, filepath);
	}
	/**
	 *
	 * @param map
	 */
	public static void setValueForSystem( Map<String, String> map) {
		String filepath = System.getProperty("user.dir") + "/config/system"+"_"+ NativeCp.getUserName() +".properties";
		setValueForUpdateAndAdd(map, filepath);
	}
	/**
	 *
	 * @param map
	 */
	public static void setValueForOpen( Map<String, String> map) {
		String filepath = System.getProperty("user.dir") + "/config/"+ Constant.FGO +"/open_"+ NativeCp.getUserName() +".properties";
		setValueForUpdateAndAdd(map, filepath);
	}
	/**
	 *
	 * @param map
	 */
	public static void setValueForAutoClick( Map<String, String> map) {
		String filepath = System.getProperty("user.dir") + "/config/clicks_"+ NativeCp.getUserName() +".properties";
		setValueForUpdateAndAdd(map, filepath);
	}
	public static void setValueForspecialHasDo( Map<String, String> map) {
		String filepath = System.getProperty("user.dir") + "/config/"+ Constant.FGO +"/specialHasDo.properties";
		setValueForUpdateAndAdd(map, filepath);
	}
	public static void setValueByFileName( Map<String, String> map, String saveFileString, String relativePath) {
		String filepath = System.getProperty("user.dir") + "/config/"+ relativePath +saveFileString+".properties";
		setValueForUpdateAndAdd(map, filepath);
	}
	public static void deleteAutoClickFile() {
		String filepath = System.getProperty("user.dir") + "/config/clicks_"+ NativeCp.getUserName() +".properties";
		File file = new File(filepath);
		if (file.exists()) {
			file.delete();
		}
	}
	/**
	 *
	 * @param map
	 */
	public static void setValueForUpdateAndAdd( Map<String, String> map, String filepath) {
		Properties prop = new Properties();
		/// 保存属性到b.properties文件
		FileOutputStream oFile = null;
		try {
			LOGGER.info("从 "+filepath+" 里，加载属性列表");
			FileInputStream fis = new FileInputStream(filepath);
			BufferedInputStream in = new BufferedInputStream(fis);
			prop.load(in); /// 加载属性列表
			LOGGER.info("写入新属性");
			oFile = new FileOutputStream(filepath, false);
			Set<Map.Entry<String, String>> entrySet = map.entrySet();
			for (Map.Entry<String, String> entry : entrySet) {
				if (prop.containsKey(entry.getKey())) {
					prop.setProperty(entry.getKey(), entry.getValue());
				}else {
					prop.put(entry.getKey(), entry.getValue());
				}
			}
			prop.store(oFile, "The New properties file");
		} catch (Exception e) {
			LOGGER.error("写入配置信息出错！", e);
			e.printStackTrace();
		} finally {
			try {
				if (oFile!=null) {
					oFile.close();
				}
			} catch (IOException e) {
				LOGGER.error(GameUtil.getStackMsg(e));
			}
		}
	}
	/**
	 *
	 * @param map
	 */
	public static void setValueForHasDo( Map<String, String> map) {
		Properties prop = new Properties();
		String filepath = System.getProperty("user.dir") + "/config/"+ Constant.FGO +"/hasDo.properties";
		/// 保存属性到b.properties文件
		FileOutputStream oFile = null;
		try {
			LOGGER.info("写入新属性");
			oFile = new FileOutputStream(filepath, false);
			Set<Map.Entry<String, String>> entrySet = map.entrySet();
			for (Map.Entry<String, String> entry : entrySet) {
				prop.put(entry.getKey(), entry.getValue());
			}
			prop.store(oFile, "The New properties file");
		} catch (Exception e) {
			LOGGER.error("写入配置信息出错！", e);
			e.printStackTrace();
		} finally {
			try {
				if (oFile!=null) {
					oFile.close();
				}
			} catch (IOException e) {
				LOGGER.error(GameUtil.getStackMsg(e));
			}
		}
	}
}
