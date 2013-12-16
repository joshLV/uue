package com.zihexin.business_interface.bank.bankRequest;

import java.io.*;
import java.net.*;
import java.util.*;

/**
 * SOCKETͨѶ������
 */
public class SocketRequest {

	/**
	 * ������������������
	 * 
	 * @return
	 */
	public String getRequestStr(String commandId, Map fieldMap,
			LinkedHashMap bankCommandMap) {
		try {
			// �����������
			XmlPacket xmlPkt = new XmlPacket(commandId, "����ֱ��ר�ü���1");
			LinkedHashMap mpPodInfo = new LinkedHashMap();
			Set keys = bankCommandMap.keySet();
			Object[] keyList = null;
			keyList = (Object[]) keys.toArray();
			for (int i = 0; i < 15; i++) {
				// System.out.println(bankCommandMap.get(keyList[i]).toString());
				mpPodInfo.put(bankCommandMap.get(keyList[i]).toString(),
						fieldMap.get(keyList[i]) == null ? "" : fieldMap.get(
								keyList[i]).toString());
			}
			xmlPkt.putProperty("SDKATSRQX", mpPodInfo);

			LinkedHashMap mpPayInfo = new LinkedHashMap();

			for (int i = 15; i < keyList.length; i++) {
				mpPayInfo.put(bankCommandMap.get(keyList[i]).toString(),
						fieldMap.get(keyList[i]) == null ? "" : fieldMap.get(
								keyList[i]).toString());
			}

			xmlPkt.putProperty("SDKATDRQX", mpPayInfo);
			return xmlPkt.toXmlString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * ���ɲ�ѯ���׸�Ҫ��Ϣ������
	 * 
	 * @return
	 */
	public String getRequestGetAgentInfo(String commandId, Map fieldMap,LinkedHashMap bankCommandMap) {

	// �����������
	try{
			XmlPacket xmlPkt = new XmlPacket(commandId, "����ֱ��ר�ü���1");
			LinkedHashMap mpPodInfo = new LinkedHashMap();
			Set keys = bankCommandMap.keySet();
			Object[] keyList = null;
			keyList = (Object[]) keys.toArray();
			for (int i = 0; i < keyList.length; i++) {
				// System.out.println(bankCommandMap.get(keyList[i]).toString());
				mpPodInfo.put(bankCommandMap.get(keyList[i]).toString(),
						fieldMap.get(keyList[i]) == null ? "" : fieldMap.get(
								keyList[i]).toString());
			}
			xmlPkt.putProperty("SDKATSQYX", mpPodInfo);
			return xmlPkt.toXmlString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * ���ɲ�ѯ������ϸ��Ϣ������
	 * 
	 * @return
	 */
	public String getRequestGetAgentDetail(String commandId, Map fieldMap,LinkedHashMap bankCommandMap) {

		// �����������
		try{
				XmlPacket xmlPkt = new XmlPacket(commandId, "����ֱ��ר�ü���1");
				LinkedHashMap mpPodInfo = new LinkedHashMap();
				Set keys = bankCommandMap.keySet();
				Object[] keyList = null;
				keyList = (Object[]) keys.toArray();
				for (int i = 0; i < keyList.length; i++) {
					// System.out.println(bankCommandMap.get(keyList[i]).toString());
					mpPodInfo.put(bankCommandMap.get(keyList[i]).toString(),
							fieldMap.get(keyList[i]) == null ? "" : fieldMap.get(
									keyList[i]).toString());
				}
				xmlPkt.putProperty("SDKATDQYX", mpPodInfo);
				return xmlPkt.toXmlString();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "";
	}

	
	/**
	 * ����֧����Ϣ������
	 * 
	 * @return
	 */
	public String getRequestPayment(String commandId, Map fieldMap,LinkedHashMap bankCommandMap) {

		try {
			// �����������
			XmlPacket xmlPkt = new XmlPacket(commandId, "����ֱ��ר�ü���1");
			LinkedHashMap mpPodInfo = new LinkedHashMap();
			Set keys = bankCommandMap.keySet();
			Object[] keyList = null;
			keyList = (Object[]) keys.toArray();
			
			// System.out.println(bankCommandMap.get(keyList[i]).toString());
			mpPodInfo.put(bankCommandMap.get(keyList[2]).toString(),
					fieldMap.get(keyList[2]) == null ? "" : fieldMap.get(
							keyList[2]).toString());
			mpPodInfo.put(bankCommandMap.get(keyList[3]).toString(),
					fieldMap.get(keyList[3]) == null ? "" : fieldMap.get(
							keyList[3]).toString());
			
			xmlPkt.putProperty("SDKPAYRQX", mpPodInfo);

			LinkedHashMap mpPayInfo = new LinkedHashMap();

			for (int i = 0; i < keyList.length; i++) {
				if(i==2||i==3){
					continue ;
				}
				mpPayInfo.put(bankCommandMap.get(keyList[i]).toString(),
						fieldMap.get(keyList[i]) == null ? "" : fieldMap.get(
								keyList[i]).toString());
			}

			xmlPkt.putProperty("SDKPAYDTX", mpPayInfo);
			return xmlPkt.toXmlString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	
	/**
	 * ���ɲ�ѯ�˻�������Ϣ������
	 * 
	 * @return
	 */
	public String getRequestGetTransInfo(String commandId, Map fieldMap,LinkedHashMap bankCommandMap) {

		try {
			// �����������
			XmlPacket xmlPkt = new XmlPacket(commandId, "����ֱ��ר�ü���1");
			LinkedHashMap mpPodInfo = new LinkedHashMap();
			Set keys = bankCommandMap.keySet();
			Object[] keyList = null;
			keyList = (Object[]) keys.toArray();
			for (int i = 0; i < keyList.length; i++) {
				// System.out.println(bankCommandMap.get(keyList[i]).toString());
				mpPodInfo.put(bankCommandMap.get(keyList[i]).toString(),
						fieldMap.get(keyList[i]) == null ? "" : fieldMap.get(
								keyList[i]).toString());
			}
			xmlPkt.putProperty("SDKTSINFX", mpPodInfo);
			return xmlPkt.toXmlString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	/**
	 * ����ǰ�û������������ģ���÷��ر���
	 * 
	 * @param data
	 * @return
	 */
	private String sendRequest(String data) {
		// ����ǰ�û���Ip + port
		String hostname = "localhost";
		int port = 8880;
		String result = null;
		try {
			InetAddress addr = InetAddress.getByName(hostname);
			Socket socket = new Socket(addr, port);

			// ����2����ͨѶ��ʱʱ��
			socket.setSoTimeout(120 * 1000);

			DataOutputStream wr = new DataOutputStream(socket.getOutputStream());

			// ͨѶͷΪ8λ���ȣ��Ҳ��ո��Ȳ���8λ�ո���ȡǰ8λ��Ϊ����ͷ
			String strLen = String.valueOf(data.getBytes().length) + "        ";
			wr.write((strLen.substring(0, 8) + data).getBytes());
			wr.flush();
			DataInputStream rd = new DataInputStream(socket.getInputStream());
			// ���շ��ر��ĵĳ���
			byte rcvLen[] = new byte[8];
			rd.read(rcvLen);
			String sLen = new String(rcvLen);
			int len = 0;
			try {
				len = Integer.valueOf(sLen.trim());
			} catch (NumberFormatException e) {
				System.out.println("����ͷ��ʽ����" + sLen);
			}
			if (len > 0) {
				System.out.println("��Ӧ���ĳ���:" + len);

				// ���շ��ر��ĵ�����
				byte rcvData[] = new byte[len];
				rd.read(rcvData);
				result = new String(rcvData);
				System.out.println("��Ӧ��������:" + result);
			}
			wr.close();
			rd.close();
		} catch (java.net.SocketTimeoutException e) {
			System.out.println("ͨѶ��ʱ��" + e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return result;
	}

	/**
	 * �����صĽ��
	 * 
	 * @param result
	 */
	public Map processResult(String result) {
		if (result != null && result.length() > 0) {
			XmlPacket pktRsp = XmlPacket.valueOf(result);
			if (pktRsp != null) {
				if (pktRsp.isError()) {
					System.out.println("ȡ�˻���Ϣʧ�ܣ�" + pktRsp.getERRMSG());
				} else {
					Map propAcc = pktRsp.getProperty("NTQACINFZ", 0);

					System.out.println("�˻�" + propAcc.get("ACCNBR") + "��������"
							+ propAcc.get("ONLBLV"));
					return propAcc;
				}
			} else {
				System.out.println("��Ӧ���Ľ���ʧ��");
				return null;
			}
		}
		return null;
	}

	public static void main(String[] args) {
		try {
			/*
			 * SocketRequest request = new SocketRequest();
			 * 
			 * // ���������� String data = request.getRequestStr();
			 * 
			 * // ����ǰ�û������������ģ���÷��ر��� String result = request.sendRequest(data);
			 * 
			 * // �����صĽ�� request.processResult(result);
			 */
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}