package com.zihexin.business_interface.bank.bankRequest;

import java.io.*;
import java.net.*;
import java.util.*;

/**
 * SOCKET通讯请求报文
 */
public class SocketRequest {

	/**
	 * 代发工资生成请求报文
	 * 
	 * @return
	 */
	public String getRequestStr(String commandId, Map fieldMap,
			LinkedHashMap bankCommandMap) {
		try {
			// 构造的请求报文
			XmlPacket xmlPkt = new XmlPacket(commandId, "银企直连专用集团1");
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
	 * 生成查询交易概要信息请求报文
	 * 
	 * @return
	 */
	public String getRequestGetAgentInfo(String commandId, Map fieldMap,LinkedHashMap bankCommandMap) {

	// 构造的请求报文
	try{
			XmlPacket xmlPkt = new XmlPacket(commandId, "银企直连专用集团1");
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
	 * 生成查询交易明细信息请求报文
	 * 
	 * @return
	 */
	public String getRequestGetAgentDetail(String commandId, Map fieldMap,LinkedHashMap bankCommandMap) {

		// 构造的请求报文
		try{
				XmlPacket xmlPkt = new XmlPacket(commandId, "银企直连专用集团1");
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
	 * 生成支付信息请求报文
	 * 
	 * @return
	 */
	public String getRequestPayment(String commandId, Map fieldMap,LinkedHashMap bankCommandMap) {

		try {
			// 构造的请求报文
			XmlPacket xmlPkt = new XmlPacket(commandId, "银企直连专用集团1");
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
	 * 生成查询账户交易信息请求报文
	 * 
	 * @return
	 */
	public String getRequestGetTransInfo(String commandId, Map fieldMap,LinkedHashMap bankCommandMap) {

		try {
			// 构造的请求报文
			XmlPacket xmlPkt = new XmlPacket(commandId, "银企直连专用集团1");
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
	 * 连接前置机，发送请求报文，获得返回报文
	 * 
	 * @param data
	 * @return
	 */
	private String sendRequest(String data) {
		// 连接前置机：Ip + port
		String hostname = "localhost";
		int port = 8880;
		String result = null;
		try {
			InetAddress addr = InetAddress.getByName(hostname);
			Socket socket = new Socket(addr, port);

			// 设置2分钟通讯超时时间
			socket.setSoTimeout(120 * 1000);

			DataOutputStream wr = new DataOutputStream(socket.getOutputStream());

			// 通讯头为8位长度，右补空格：先补充8位空格，再取前8位作为报文头
			String strLen = String.valueOf(data.getBytes().length) + "        ";
			wr.write((strLen.substring(0, 8) + data).getBytes());
			wr.flush();
			DataInputStream rd = new DataInputStream(socket.getInputStream());
			// 接收返回报文的长度
			byte rcvLen[] = new byte[8];
			rd.read(rcvLen);
			String sLen = new String(rcvLen);
			int len = 0;
			try {
				len = Integer.valueOf(sLen.trim());
			} catch (NumberFormatException e) {
				System.out.println("报文头格式错误：" + sLen);
			}
			if (len > 0) {
				System.out.println("响应报文长度:" + len);

				// 接收返回报文的内容
				byte rcvData[] = new byte[len];
				rd.read(rcvData);
				result = new String(rcvData);
				System.out.println("响应报文内容:" + result);
			}
			wr.close();
			rd.close();
		} catch (java.net.SocketTimeoutException e) {
			System.out.println("通讯超时：" + e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return result;
	}

	/**
	 * 处理返回的结果
	 * 
	 * @param result
	 */
	public Map processResult(String result) {
		if (result != null && result.length() > 0) {
			XmlPacket pktRsp = XmlPacket.valueOf(result);
			if (pktRsp != null) {
				if (pktRsp.isError()) {
					System.out.println("取账户信息失败：" + pktRsp.getERRMSG());
				} else {
					Map propAcc = pktRsp.getProperty("NTQACINFZ", 0);

					System.out.println("账户" + propAcc.get("ACCNBR") + "的联机余额："
							+ propAcc.get("ONLBLV"));
					return propAcc;
				}
			} else {
				System.out.println("响应报文解析失败");
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
			 * // 生成请求报文 String data = request.getRequestStr();
			 * 
			 * // 连接前置机，发送请求报文，获得返回报文 String result = request.sendRequest(data);
			 * 
			 * // 处理返回的结果 request.processResult(result);
			 */
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}