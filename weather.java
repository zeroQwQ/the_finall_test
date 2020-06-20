package test_1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class weather {
	public static void main(String[] args) {
		// ������ҳ�ṹ��д���򣬴���pattern����
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

		System.out.println("------------------��ʼ��ȡ������Ϣ-------------------");
		try {
			// ����ҳ���url����
			URL url = new URL("https://www.hao123.com");
			// ���������ȡ��
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					url.openStream(), "utf8"));
			// ��ȡ��������������BufferReader
			String str = null;
			while ((str = reader.readLine()) != null) {
				m_city = p_city.matcher(str.toString());
				m_info_today = p_info_today.matcher(str.toString());
				m_info_temperature_today = p_info_temperature_today.matcher(str
						.toString());
				m_info_tomorrow = p_info_tomorrow.matcher(str.toString());
				m_info_temperature_tomorrow = p_info_temperature_tomorrow
						.matcher(str.toString());

				// ��ȡ����
				Boolean isEx = m_city.find();
				if (isEx) {
					String city = m_city.group();
					// ��ϴ�õ�������
					city = city.replace("<span class=\"weather2-item\" data-hook=\"name\">","")
							.replace("</span>", "");
					System.out.println("���У�" + city);
				}

				// ��ȡ��������
				Boolean isEx1 = m_info_today.find();
				if (isEx1) {
					String info_today = m_info_today.group();
					// ��ϴ�õ�������
					info_today = info_today
							.replace("<div class=\"weather2__info-txt\" data-hook=\"weather\" slider-type=\"item\">","")
							.replace("</div>", "");
					System.out.println("����������" + info_today);
				}

				// ��ȡ�����¶�
				Boolean isEx2 = m_info_temperature_today.find();
				if (isEx2) {
					String temperature_today = m_info_temperature_today.group();
					// ��ϴ�õ�������
					temperature_today = temperature_today
							.replace("<div class=\"weather2__temperature\" data-hook=\"tempera\">","")
							.replace("</div>", "");
					System.out.println("�����¶ȣ�" + temperature_today);
				}

				// ��ȡ��������
				Boolean isEx3 = m_info_tomorrow.find();
				if (isEx3) {
					String info_tomorrow = m_info_tomorrow.group();
					// ��ϴ�õ�������
					info_tomorrow = info_tomorrow
							.replace("<div class=\"weather2__info-txt\" data-hook=\"weather-tomorrow\">","")
							.replace("</div>", "");
					System.out.println("����������" + info_tomorrow);
				}

				// ��ȡ�����¶�
				Boolean isEx4 = m_info_temperature_tomorrow.find();
				if (isEx4) {
					String temperature_tomorrow = m_info_temperature_tomorrow
							.group();
					// ��ϴ�õ�������
					temperature_tomorrow = temperature_tomorrow
							.replace("<div class=\"weather2__temperature\" data-hook=\"tempera-tomorrow\">","")
							.replace("</div>", "");
					System.out.println("�����¶ȣ�" + temperature_tomorrow);
				}
			}
			reader.close();
		} catch (Exception e) {
			System.out.println("����ʧ��");
			e.printStackTrace();
		}
		System.out.println("-----------------���������Ϣ��ȡ--------------------");
	}
}

