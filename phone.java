package test_1;

import java.util.Locale;

import com.csg.statistics.bean.PhoneModel;
import com.google.i18n.phonenumbers.PhoneNumberToCarrierMapper;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;
import com.google.i18n.phonenumbers.geocoding.PhoneNumberOfflineGeocoder;
 
/**
 * �ֻ��Ź����ز�ѯ
 * jar������com.googlecode.libphonenumber(Libphonenumber��Geocoder��Prefixmapper
 * ��Carrier) pom������http://mvnrepository.com/search?q=libphonenumber
 * ��Ŀ��ַ��https://github.com/googlei18n/libphonenumber
 *
 */
public class phone {
 
	/** ֱϽ�� */
	private final static String[] MUNICIPALITY = { "������", "�����", "�Ϻ���", "������" };
 
	/** ������ */
	private final static String[] AUTONOMOUS_REGION = { "�½�", "���ɹ�", "����",
			"����", "����" };
 
	private static PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil
			.getInstance();
 
	/** �ṩ��绰������ص���Ӫ����Ϣ */
	private static PhoneNumberToCarrierMapper carrierMapper = PhoneNumberToCarrierMapper
			.getInstance();
 
	/** �ṩ��绰�����йصĵ�����Ϣ */
	private static PhoneNumberOfflineGeocoder geocoder = PhoneNumberOfflineGeocoder
			.getInstance();
 
	/** �й���½������ */
	private final static int COUNTRY_CODE = 86;
 
	/**
	 * �����ֻ��� �ж��ֻ����Ƿ���Ч
	 * 
	 * @param phoneNumber
	 *            �ֻ�����
	 * @return true-��Ч false-��Ч
	 */
	public static boolean checkPhoneNumber(String phoneNumber) {
		long phone = Long.parseLong(phoneNumber);
 
		PhoneNumber pn = new PhoneNumber();
		pn.setCountryCode(COUNTRY_CODE);
		pn.setNationalNumber(phone);
 
		return phoneNumberUtil.isValidNumber(pn);
 
	}
 
	/**
	 * �����ֻ��� �ж��ֻ���Ӫ��
	 * 
	 * @param phoneNumber
	 *            �ֻ�����
	 * @return �磺�㶫ʡ�������ƶ�
	 */
	public static String getCarrier(String phoneNumber) {
 
		long phone = Long.parseLong(phoneNumber);
 
		PhoneNumber pn = new PhoneNumber();
		pn.setCountryCode(COUNTRY_CODE);
		pn.setNationalNumber(phone);
		// ���ؽ��ֻ��Ӣ�ģ��Լ�ת�ɳ�����
		String carrierEn = carrierMapper.getNameForNumber(pn, Locale.ENGLISH);
		String carrierZh = "";
		switch (carrierEn) {
		case "China Mobile":
			carrierZh += "�ƶ�";
			break;
		case "China Unicom":
			carrierZh += "��ͨ";
			break;
		case "China Telecom":
			carrierZh += "����";
			break;
		default:
			break;
		}
		return carrierZh;
	}
 
	/**
	 * �����ֻ��� ��ȡ�ֻ�������
	 * 
	 * @param phoneNumber
	 *            �ֻ�����
	 * @return �磺�㶫ʡ������
	 */
	public static String getGeo(String phoneNumber) {
		long phone = Long.parseLong(phoneNumber);
 
		PhoneNumber pn = new PhoneNumber();
		pn.setCountryCode(COUNTRY_CODE);
		pn.setNationalNumber(phone);
		return geocoder.getDescriptionForNumber(pn, Locale.CHINESE);
	}
 
	/**
	 * �����ֻ��� ��ȡ�ֻ���Ϣģ��
	 * 
	 * <pre>
	 * ������ֵΪnull����˵���ú�����Ч
	 * </pre>
	 * 
	 * @param phoneNumber
	 *            �ֻ�����
	 * @return �ֻ���Ϣģ��PhoneModel
	 */
	public static PhoneModel getPhoneModel(String phoneNumber) {
		if (checkPhoneNumber(phoneNumber)) {
			String geo = getGeo(phoneNumber);
			PhoneModel phoneModel = new PhoneModel();
			String carrier = getCarrier(phoneNumber);
			phoneModel.setCarrier(carrier);
			// ֱϽ��
			for (String val : MUNICIPALITY) {
				if (geo.equals(val)) {
					phoneModel.setProvinceName(val.replace("��", ""));
					phoneModel.setCityName(val);
					return phoneModel;
				}
			}
			// ������
			for (String val : AUTONOMOUS_REGION) {
				if (geo.startsWith(val)) {
					phoneModel.setProvinceName(val);
					phoneModel.setCityName(geo.replace(val, ""));
					return phoneModel;
				}
			}
 
			// ����
			String[] splitArr = geo.split("ʡ");
			if (splitArr != null && splitArr.length == 2) {
				phoneModel.setProvinceName(splitArr[0]);
				phoneModel.setCityName(splitArr[1]);
				return phoneModel;
			}
		}
		return null;
	}
	
	public static void main(String[] args) {
		PhoneModel phoneModel = PhoneUtil.getPhoneModel("13456555555");
		if(phoneModel != null){
			System.out.println(phoneModel.getProvinceName());
			System.out.println(phoneModel.getCityName());
			System.out.println(phoneModel.getCarrier());
		}else{
			System.err.println("�ú�����Ч");
		}
	}
 
}