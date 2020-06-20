package test_1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class weather {
	public static void main(String[] args) {
		// 根据网页结构编写正则，创建pattern对象
		Pattern p_city = Pattern
				.compile("<span class=\"weather2-item\" data-hook=\"name\">(.*?)</span>");
		Pattern p_info_today = Pattern
				.compile("<div class=\"weather2__info-txt\" data-hook=\"weather\" slider-type=\"item\">(.*?)</div>");
		Pattern p_info_temperature_today = Pattern
				.compile("<div class=\"weather2__temperature\" data-hook=\"tempera\">(.*?)</div>");
		Pattern p_info_tomorrow = Pattern
				.compile("<div class=\"weather2__info-txt\" data-hook=\"weather-tomorrow\">(.*?)</div>");
		Pattern p_info_temperature_tomorrow = Pattern
				.compile("<div class=\"weather2__temperature\" data-hook=\"tempera-tomorrow\">(.*?)</div>");

		Matcher m_city;
		Matcher m_info_today;
		Matcher m_info_temperature_today;
		Matcher m_info_tomorrow;
		Matcher m_info_temperature_tomorrow;

		System.out.println("------------------开始获取天气信息-------------------");
		try {
			// 创建页面的url对象
			URL url = new URL("https://www.hao123.com");
			// 创建网络读取流
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					url.openStream(), "utf8"));
			// 读取网络内容网络流BufferReader
			String str = null;
			while ((str = reader.readLine()) != null) {
				m_city = p_city.matcher(str.toString());
				m_info_today = p_info_today.matcher(str.toString());
				m_info_temperature_today = p_info_temperature_today.matcher(str
						.toString());
				m_info_tomorrow = p_info_tomorrow.matcher(str.toString());
				m_info_temperature_tomorrow = p_info_temperature_tomorrow
						.matcher(str.toString());

				// 获取地区
				Boolean isEx = m_city.find();
				if (isEx) {
					String city = m_city.group();
					// 清洗得到的数据
					city = city.replace("<span class=\"weather2-item\" data-hook=\"name\">","")
							.replace("</span>", "");
					System.out.println("城市：" + city);
				}

				// 获取今日天气
				Boolean isEx1 = m_info_today.find();
				if (isEx1) {
					String info_today = m_info_today.group();
					// 清洗得到的数据
					info_today = info_today
							.replace("<div class=\"weather2__info-txt\" data-hook=\"weather\" slider-type=\"item\">","")
							.replace("</div>", "");
					System.out.println("今日天气：" + info_today);
				}

				// 获取今日温度
				Boolean isEx2 = m_info_temperature_today.find();
				if (isEx2) {
					String temperature_today = m_info_temperature_today.group();
					// 清洗得到的数据
					temperature_today = temperature_today
							.replace("<div class=\"weather2__temperature\" data-hook=\"tempera\">","")
							.replace("</div>", "");
					System.out.println("今日温度：" + temperature_today);
				}

				// 获取明天天气
				Boolean isEx3 = m_info_tomorrow.find();
				if (isEx3) {
					String info_tomorrow = m_info_tomorrow.group();
					// 清洗得到的数据
					info_tomorrow = info_tomorrow
							.replace("<div class=\"weather2__info-txt\" data-hook=\"weather-tomorrow\">","")
							.replace("</div>", "");
					System.out.println("明日天气：" + info_tomorrow);
				}

				// 获取明天温度
				Boolean isEx4 = m_info_temperature_tomorrow.find();
				if (isEx4) {
					String temperature_tomorrow = m_info_temperature_tomorrow
							.group();
					// 清洗得到的数据
					temperature_tomorrow = temperature_tomorrow
							.replace("<div class=\"weather2__temperature\" data-hook=\"tempera-tomorrow\">","")
							.replace("</div>", "");
					System.out.println("明日温度：" + temperature_tomorrow);
				}
			}
			reader.close();
		} catch (Exception e) {
			System.out.println("下载失败");
			e.printStackTrace();
		}
		System.out.println("-----------------完成天气信息获取--------------------");
	}
}

