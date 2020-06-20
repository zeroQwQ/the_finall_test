package test_1;

import org.apache.commons.lang.StringUtils;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
 
import javax.servlet.http.HttpServletRequest;
 
/**
 * 
 * @author HuaZai
 * @contact who.seek.me@java98k.vip
 *          <ul>
 * @description TODO
 *              </ul>
 * @className IpUtil
 * @package com.b2c.microservice.common.utils
 * @createdTime 2017��12��30�� ����6:35:00
 *
 * @version V1.0.0
 */

public class ipsearch 
{
 
	/**
	 * Ĭ��IP��ַ
	 */
	public final static String ERROR_IP = "127.0.0.1";
 
	/**
	 * IP��ַ������ʽ
	 */
	public final static Pattern pattern = Pattern.compile(
			"(2[5][0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})\\.(25[0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})\\.(25[0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})\\.(25[0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})");
 
	/**
	 * 
	 * @author HuaZai
	 * @contact who.seek.me@java98k.vip
	 * @title isValidIP
	 *        <ul>
	 * @description ����������֤IP��ַ
	 *              </ul>
	 * @createdTime 2017��12��30�� ����7:38:19
	 * @param request
	 * @return boolean
	 *
	 * @version : V1.0.0
	 */
	public static boolean isValidIP(HttpServletRequest request)
	{
		// ��ȡ�û���ʵ��IP��ַ
		String ip = getUserIP(request);
		// ��֤IP��ַ�Ƿ���Ϲ���
		return isValidIP(ip);
	}
 
	/**
	 * 
	 * @author HuaZai
	 * @contact who.seek.me@java98k.vip
	 * @title getRemoteIp
	 *        <ul>
	 * @description ��ȡԶ��IP��ַ
	 *              </ul>
	 * @createdTime 2017��12��30�� ����6:39:22
	 * @param request
	 * @return String
	 *
	 * @version : V1.0.0
	 */
	public static String getRemoteIp(HttpServletRequest request)
	{
		String ip = request.getHeader("x-real-ip");
		if (ip == null)
		{
			ip = request.getRemoteAddr();
		}
		// ���˷�������ip
		String[] stemps = ip.split(",");
		if (stemps != null && stemps.length >= 1)
		{
			// �õ���һ��IP�����ͻ�����ʵIP
			ip = stemps[0];
		}
 
		ip = ip.trim();
		if (ip.length() > 23)
		{
			ip = ip.substring(0, 23);
		}
 
		return ip;
	}
 
	/**
	 * 
	 * @author HuaZai
	 * @contact who.seek.me@java98k.vip
	 * @title getUserIP
	 *        <ul>
	 * @description ��ȡ�û���ʵ��IP��ַ
	 *              </ul>
	 * @createdTime 2017��12��30�� ����6:42:17
	 * @param request
	 * @return String
	 *
	 * @version : V1.0.0
	 */
	public static String getUserIP(HttpServletRequest request)
	{
		// ����ȡ X-Real-IP
		String ip = request.getHeader("X-Real-IP");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
		{
			ip = request.getHeader("x-forwarded-for");
		}
 
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
		{
			ip = request.getRemoteAddr();
			if ("0:0:0:0:0:0:0:1".equals(ip))
			{
				ip = ERROR_IP;
			}
		}
		if ("unknown".equalsIgnoreCase(ip))
		{
			ip = ERROR_IP;
			return ip;
		}
		int index = ip.indexOf(',');
		if (index >= 0)
		{
			ip = ip.substring(0, index);
		}
 
		return ip;
	}
 
	/**
	 * 
	 * @author HuaZai
	 * @contact who.seek.me@java98k.vip
	 * @title getLastIpSegment
	 *        <ul>
	 * @description ��ȡĩβIP��ַ�Σ�{@linkplain IP��ַ���}
	 *              </ul>
	 * @createdTime 2017��12��30�� ����6:44:09
	 * @param request
	 * @return String
	 *
	 * @version : V1.0.0
	 */
	public static String getLastIpSegment(HttpServletRequest request)
	{
		// ��ȡ�û���ʵ��IP��ַ
		String ip = getUserIP(request);
		if (ip != null)
		{
			ip = ip.substring(ip.lastIndexOf('.') + 1);
		} else
		{
			ip = "0";
		}
		return ip;
	}
 
	/**
	 * 
	 * @author HuaZai
	 * @contact who.seek.me@java98k.vip
	 * @title isValidIP
	 *        <ul>
	 * @description ��֤IP��ַ�Ƿ���Ϲ���
	 *              </ul>
	 * @createdTime 2017��12��30�� ����6:49:21
	 * @param ip
	 * @return boolean
	 *
	 * @version : V1.0.0
	 */
	public static boolean isValidIP(String ip)
	{
		if (StringUtils.isEmpty(ip))
		{
			return false;
		}
		Matcher matcher = pattern.matcher(ip);
		boolean isValid = matcher.matches();
		return isValid;
	}
 
	/**
	 * 
	 * @author HuaZai
	 * @contact who.seek.me@java98k.vip
	 * @title getLastServerIpSegment
	 *        <ul>
	 * @description ��ȡ�������Ļ��һ����ַ��
	 *              </ul>
	 * @createdTime 2017��12��30�� ����7:44:21
	 * @return String
	 *
	 * @version : V1.0.0
	 */
	public static String getLastServerIpSegment()
	{
		String ip = getServerIP();
		if (ip != null)
		{
			ip = ip.substring(ip.lastIndexOf('.') + 1);
		} else
		{
			ip = "0";
		}
		return ip;
	}
 
	/**
	 * 
	 * @author HuaZai
	 * @contact who.seek.me@java98k.vip
	 * @title getServerIP
	 *        <ul>
	 * @description ��ȡ������IP��ַ
	 *              </ul>
	 * @createdTime 2017��12��30�� ����7:44:16
	 * @return String
	 *
	 * @version : V1.0.0
	 */
	public static String getServerIP()
	{
		InetAddress inetAddress;
		try
		{
			inetAddress = InetAddress.getLocalHost();
			String hostAddress = inetAddress.getHostAddress();
			return hostAddress;
		} catch (UnknownHostException e)
		{
			e.printStackTrace();
		}
		return ERROR_IP;
	}
 
	/**
	 * 
	 * @author HuaZai
	 * @contact who.seek.me@java98k.vip
	 * @title getIpAddress
	 *        <ul>
	 * @description
	 *              <li>��ȡIP��ַ
	 *              <li><strong>ע�⣺</strong>IP��ַ������η���������ж��IPֵ��
	 *              <li>���е�һ��IP������ʵIP�����Բ���ͨ��request.getRemoteAddr()��ȡIP��ַ��
	 *              <li>���ʹ���˶༶�������Ļ���X-Forwarded-For��ֵ����ֹһ��������һ��IP��ַ��
	 *              <li>X-Forwarded-For�е�һ����unknown����ЧIP�����û���ʵIP��ַ��
	 *              </ul>
	 * @createdTime 2017��12��30�� ����7:52:35
	 * @param request
	 * @return String
	 *
	 * @version : V1.0.0
	 */
	public static String getIpAddress(HttpServletRequest request)
	{
		String ip = null;
		try
		{
			// ��ȡ�û����ǵĵ�ַ
			String Xip = request.getHeader("X-Real-IP");
			// ��ȡ��δ�����IP�ַ���ֵ
			String XFor = request.getHeader("X-Forwarded-For");
			if (StringUtils.isNotEmpty(XFor) && !"unKnown".equalsIgnoreCase(XFor))
			{
				// ��η���������ж��IPֵ����һ���û���ʵ��IP��ַ
				int index = XFor.indexOf(",");
				if (index >= 0)
				{
					return XFor.substring(0, index);
				} else
				{
					return XFor;
				}
			}
			ip = Xip;
			if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip))
			{
				ip = request.getHeader("Proxy-Client-IP");
			}
			if (StringUtils.isEmpty(ip) || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
			{
				ip = request.getHeader("WL-Proxy-Client-IP");
			}
			if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip))
			{
				ip = request.getHeader("HTTP_CLIENT_IP");
			}
			if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip))
			{
				ip = request.getHeader("HTTP_X_FORWARDED_FOR");
			}
			if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip))
			{
				ip = request.getRemoteAddr();
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return ip;
	}
}