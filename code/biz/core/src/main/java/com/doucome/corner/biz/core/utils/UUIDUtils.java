package com.doucome.corner.biz.core.utils;

import java.util.UUID;

public class UUIDUtils {

	private static final int LEN20 = 20;

	/* ������� */
	private static char X36S[] = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

	/**
	 * ������ս԰ר���㷨����20λ��дUUID
	 * 
	 * @return 20λUUID
	 */
	public static String random20() {
		char chs[] = new char[LEN20];

		/**
		 * ����ǰ8λ����ַ�(��ϵͳʱ��Ϊ�����, ��36λ����+Ӣ����ĸΪ�������)
		 */
		long v = (System.currentTimeMillis() - 936748800000L) >> 1; // 1999-9-9
		// ��ʼ������Ҫ���102��:)
		for (int i = 7; i > 0; i--) {
			chs[i] = X36S[(int) (v % 36)];
			v = v / 36;
		}
		chs[0] = X36S[(int) (v % 26) + 10]; // ȷ����һ������ַ���"��ĸ", �Է���һ���̵ı�ʶ������

		/**
		 * ���ɺ�12λ����ַ�(��UUIDΪ�����, ��36λ����+Ӣ����ĸΪ�������)
		 */
		UUID u = UUID.randomUUID();
		v = u.getLeastSignificantBits() ^ u.getMostSignificantBits();
		if (v < 0) {
			v = -v;
		}

		for (int i = 8; i < LEN20; i++) {
			chs[i] = X36S[(int) (v % 36)];
			v = v / 36;
		}

		return new String(chs).toLowerCase();
	}
}
