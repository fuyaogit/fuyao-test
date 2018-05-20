package com.linktrust.fuyao.utils;
import org.bson.types.ObjectId;

import java.util.Random;
import java.util.UUID;

/**
 * @ClassName UUIDGenerator
 * @Description: UUID生成器32位
 * @author: QIJJ
 * @since: 2014-9-19 下午3:45:26
 */
public class UUIDGenerator {

	private UUIDGenerator() {

	}

	public static final int NUM_EIGHT = 8;

	public static final int NUM_TENTHREE = 13;

	public static final int NUM_NINE = 9;

	public static final int NUM_TENFOUR = 14;

	public static final int NUM_TENEIGHT = 18;

	public static final int NUM_TENNINE = 19;

	public static final int NUM_TWOTHREE = 23;

	public static final int NUM_TWOFOUR = 24;

	public static String[] chars = new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3",
			"4", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "5", "6", "7", "8", "9", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };

	/********************************************************************************************/
	/**
	 * @Title: getUUID
	 * @Description: 获取UUID
	 * @return
	 * @return: String
	 * @throws:
	 * @author: QIJJ
	 * @date: 2013-8-10 上午8:07:31
	 */
	public static String getUUID() {
		UUID uuid = UUID.randomUUID();
		String str = uuid.toString();
		/**
		 * 去掉"-"符号
		 */
		String temp = str.substring(0, NUM_EIGHT) + str.substring(NUM_NINE, NUM_TENTHREE) + str.substring(NUM_TENFOUR, NUM_TENEIGHT) + str.substring(NUM_TENNINE, NUM_TWOTHREE)
				+ str.substring(NUM_TWOFOUR);

		return temp;
	}

	/**
	 * @Title: getUUID
	 * @Description: 获得指定数量的UUID
	 * @param number
	 * @return
	 * @return: String[]
	 * @throws:
	 * @author: QIJJ
	 * @date: 2013-8-10 上午8:07:46
	 */
	public static String[] getUUID(int number) {
		if (number < 1) {
			return null;
		}
		String[] ss = new String[number];
		for (int i = 0; i < number; i++) {
			ss[i] = getUUID();
		}
		return ss;
	}

	/**
	 * @Description： 获取nanoTime+两位随机数生成 UUID
	 * @author: QIJJ
	 * @since: 2015-5-12 下午11:53:24
	 */
	public static synchronized Long generaterLongKeyByNanoTime() {
		return System.nanoTime() + (new Random()).nextInt(99);
	}

	/**
	 * @Description： 获取nanoTime生成 UUID
	 * @author: QIJJ
	 * @since: 2014-11-3 上午11:43:51
	 */
	public static synchronized Long generaterKeyByNanoTime() {
		return System.nanoTime();
	}

	/**
	 * 
	 * @Description： 随机获取4位随机数
	 * @author: QIJJ
	 * @since: 2017年4月14日 上午10:03:44
	 */
	public static synchronized int getFourRanNum() {
		int num = (int) (Math.random() * 9000 + 1000);
		return num;
	}

	/**
	 * 
	 * @Description： 根据参数获取对应长度的UUID
	 * @author: QIJJ
	 * @since: 2017年4月26日 下午8:07:45
	 */
	public static String getRandomString(int length) { // length表示生成字符串的长度
		String base = "0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		int number = 0;
		for (int i = 0; i < length; i++) {
			number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}

		return sb.toString();
	}

	/**
	 * 
	 * @Description： 生成类似订单16位不重复ID
	 * @author: QIJJ
	 * @since: 2017年4月28日 上午11:41:29
	 */
	public static String getOrderIdByUUId() {
		int machineId = 1;
		int hashCodeV = UUID.randomUUID().toString().hashCode();
		if (hashCodeV < 0) {
			hashCodeV = -hashCodeV;
		}

		return machineId + String.format("%015d", hashCodeV);
	}

	/**
	 * 
	 * @Description： 生成短8位UUID
	 * @author: QIJJ
	 * @since: 2017年4月28日 上午11:40:10
	 */
	public static String generateShortUuid() {
		StringBuffer shortBuffer = new StringBuffer();
		String uuid = UUID.randomUUID().toString().replace("-", "");
		for (int i = 0; i < 8; i++) {
			String str = uuid.substring(i * 4, i * 4 + 4);
			int x = Integer.parseInt(str, 16);
			shortBuffer.append(chars[x % 0x3E]);
		}
		return shortBuffer.toString();

	}

	/**
	 * 
	 * @Description： 生成24位UUID
	 * @author: QIJJ
	 * @since: 2017年4月28日 上午11:43:29
	 */
	public static String getTwentyFourUuid() {
		return new ObjectId().toString();
	}

	/**
	 *
	 * @Description： 获取mongoObjectId
	 * @author: TAOSHUAIJIANG
	 * @since: 2017年5月2日 上午9:19:41
	 */
	public static String genMongoId(){
		return new ObjectId().toString();
	}

}
