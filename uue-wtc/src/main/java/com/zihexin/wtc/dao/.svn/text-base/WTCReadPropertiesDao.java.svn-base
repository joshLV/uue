package com.zihexin.wtc.dao;

import com.zihexin.wtc.common.Loader;

import com.zihexin.wtc.domain.Command;
import com.zihexin.wtc.domain.Field;
import com.zihexin.wtc.domain.TransRecode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

/**
 * 
 */
public class WTCReadPropertiesDao extends SqlMapClientDaoSupport {
	private final static Logger logger = LoggerFactory
			.getLogger(WTCReadPropertiesDao.class);

	/**
	 * 获取指令列表
	 * 
	 * @return
	 */
	public Hashtable getCommandList() {
		Hashtable commandTable = new Hashtable();
		List list = (List) getSqlMapClientTemplate().queryForList(
				"sql.queryCommandList");
		for (int i = 0; i < list.size(); i++) {
			HashMap hashMap = (HashMap) list.get(i);
			String commandId = (String) hashMap.get("COMMAND_ID");
			commandTable.put(commandId, getCommandSet(commandId, "DB"));
		}
		return commandTable;
	}

	/**
	 * 获取交易流水表的下一个序列
	 * 
	 * @return
	 */
	public String getTransRecodeSeq() {
		List list = (List) getSqlMapClientTemplate().queryForList(
				"sql.getTransRecodeSeq");
		String getTransRecodeSeq = (String) list.get(0);
		logger.info("下一个序列为：" + getTransRecodeSeq);
		return getTransRecodeSeq;
	}

	/**
	 * g 获取日志表的下一个序列
	 * 
	 * @return
	 */
	public String getLogSeq() {
		try {
			List list = (List) getSqlMapClientTemplate().queryForList(
					"sql.getLogSeq");
			String getLogSeq = (String) list.get(0);
			logger.info("下一个序列为：" + getLogSeq);
			return getLogSeq;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 *  获取交易日志表的下一个序列
	 * 
	 * @return
	 */
	public String getTransLogSeq() {
		try {
			List list = (List) getSqlMapClientTemplate().queryForList(
					"sql.getTransLogSeq");
			String getLogSeq = (String) list.get(0);
			logger.info("下一个序列为：" + getLogSeq);
			return getLogSeq;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	
	/**
	 *  获取POS交易日志表的下一个序列
	 * 
	 * @return
	 */
	public String getPOSTransLogSeq() {
		try {
			List list = (List) getSqlMapClientTemplate().queryForList(
					"sql.getPOSTransLogSeq");
			String getLogSeq = (String) list.get(0);
			logger.info("下一个序列为：" + getLogSeq);
			return getLogSeq;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	
	/**
	 *  获取POS交易流水表的下一个序列
	 * 
	 * @return
	 */
	public String getPosTransRecodeSeq() {
		try {
			List list = (List) getSqlMapClientTemplate().queryForList(
					"sql.getPosTransRecodeSeq");
			String getLogSeq = (String) list.get(0);
			logger.info("下一个序列为：" + getLogSeq);
			return getLogSeq;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	

	/**
	 * 获取公共信息
	 * 
	 * @return
	 */
	public HashMap getCommon() {
		HashMap commonMap = new HashMap();
		List list = (List) getSqlMapClientTemplate().queryForList(
				"sql.queryCommonSet");
		for (int i = 0; i < list.size(); i++) {
			HashMap map = (HashMap) list.get(i);
			commonMap.put(map.get("ITEM"), map.get("VAL"));
		}
		return commonMap;
	}

	/**
	 * 获取指令业务码配置
	 * 
	 * @param commandId
	 * @return
	 */
	public Command getCommandSet(String commandId, String src) {
		if ("CACHE".equals(src)) {
			return Loader.getCommandSet(commandId);
		}
		Command command = new Command();
		HashMap baseMap = (HashMap) getSqlMapClientTemplate().queryForObject(
				"sql.queryCommandById", commandId); // 指令基本信息
		if (baseMap == null) {
			return null;
		} else {
			command.setCommandId(commandId);
			command.setCommandName((String) baseMap.get("NAME"));
			command.setCommandFieldsCnt(Long.valueOf(baseMap.get("FIELD_CNT")
					.toString()));
			command.setCommandStatus((String) baseMap.get("STATUS"));
			command.setCommandClass((String) baseMap.get("CLASS"));
			command.setCommondLog((String) baseMap.get("LOG"));
		}

		List commandOtherParameter = new ArrayList();
		List otherMapList = (List) getSqlMapClientTemplate().queryForList(
				"sql.queryCommandOtherSetById", commandId); // 指令其它报文配置信息
		if (otherMapList == null || otherMapList.size() == 0) {
			return null;
		} else {
			for (int i = 0; i < otherMapList.size(); i++) {
				HashMap m = (HashMap) otherMapList.get(i);
				Field field = new Field();
				field.setFieldOrder(Long.valueOf(m.get("FIELD_ORDER")
						.toString()));
				field.setFieldName((String) m.get("FIELD_NAME"));
				field.setFieldMaxLength(Long.valueOf(m.get("FIELD_MAX_LENGTH")
						.toString()));
				field.setBusiness((String) m.get("IS_BUSINESS"));
				field.setLogField((String) m.get("LOG_FIELD"));
				commandOtherParameter.add(i, field);
			}
			command.setCommandOtherParameter(commandOtherParameter);

			for (int i = 0; i < otherMapList.size(); i++) {
				HashMap m = (HashMap) otherMapList.get(i);
				Field field = new Field();
				field.setFieldOrder(Long.valueOf(m.get("FIELD_ORDER")
						.toString()));
				field.setFieldName((String) m.get("FIELD_NAME"));
				field.setFieldMaxLength(Long.valueOf(m.get("FIELD_MAX_LENGTH")
						.toString()));
				field.setBusiness((String) m.get("IS_BUSINESS"));
				field.setLogField((String) m.get("LOG_FIELD"));
				commandOtherParameter.add(i, field);
			}
			command.setCommandOtherParameter(commandOtherParameter);
		}

		List commandBusinessParameter = new ArrayList();
		List businessMapList = (List) getSqlMapClientTemplate().queryForList(
				"sql.queryCommandBusinessSetById", commandId); // 指令业务报文配置信息
		if (businessMapList == null || businessMapList.size() == 0) {
			return null;
		} else {
			for (int i = 0; i < businessMapList.size(); i++) {
				HashMap m = (HashMap) businessMapList.get(i);
				Field field = new Field();
				field.setFieldOrder(Long.valueOf(m.get("FIELD_ORDER")
						.toString()));
				field.setFieldName((String) m.get("FIELD_NAME"));
				field.setFieldMaxLength(Long.valueOf(m.get("FIELD_MAX_LENGTH")
						.toString()));
				field.setDataType((String) m.get("DATA_TYPE"));
				field.setBusiness((String) m.get("IS_BUSINESS"));
				field.setLogField((String) m.get("LOG_FIELD"));
				commandBusinessParameter.add(i, field);
			}
			command.setCommandBusinessParameter(commandBusinessParameter);
		}

		return command;
	}

	/**
	 * 记录日志
	 * 
	 * @param command
	 *            指令对象
	 * @return
	 */
	public boolean log(Command command) {
		boolean ret = false;
		HashMap insertMap = new HashMap();

		// 设置处理日志表的序列
		String logSeq = getLogSeq();
		insertMap.put("logId", logSeq);
		insertMap.put("logTable", command.getCommandLog());
		insertMap.put("commandId", command.getCommandId());
		insertMap.put("ip", command.getMessage().getIp());
		insertMap.put("src", command.getCommandSrc());
		insertMap.put("businessMes", command.getMessage().getMsg());
		insertMap.put("requestDt", command.getRequestDt());
		insertMap.put("responseDt", command.getResponseDt());
		insertMap.put("status", command.getFinalStatus());
		insertMap.put("bodyType", command.getBodyType());

		try {
			getSqlMapClientTemplate().insert("sql.log", insertMap);
			ret = true;

		} catch (Exception e) {
			e.printStackTrace();
			ret = false;
		}
		return ret;
	}

	/**
	 * 保存交易日志信息
	 * 
	 * @param command
	 *            指令对象
	 * @return
	 */
	public boolean saveTransLog(Command command) {
		boolean ret = false;
		HashMap insertMap = new HashMap();

		String logSeq = getTransLogSeq();
		insertMap.put("logId", logSeq);
		insertMap.put("businessMes", command.getMessage().getMsg());
		insertMap.put("requestDt", command.getRequestDt());
		insertMap.put("bodyType", command.getBodyType());
		insertMap.put("market_code", command.getCommandSrc());
		
		try {
			getSqlMapClientTemplate().insert("sql.transLog", insertMap);
			ret = true;

		} catch (Exception e) {
			e.printStackTrace();
			ret = false;
		}
		return ret;
	}
	
	
	/**
	 * 保存POS交易日志信息
	 * 
	 * @param command
	 *            指令对象
	 * @return
	 */
	public boolean savePOSTransLog(Command command) {
		boolean ret = false;
		HashMap insertMap = new HashMap();

		String logSeq = getPOSTransLogSeq();
		insertMap.put("logId", logSeq);
		insertMap.put("businessMes", command.getMessage().getMsg());
		insertMap.put("requestDt", command.getRequestDt());
		insertMap.put("bodyType", command.getBodyType());
		insertMap.put("market_code", command.getCommandSrc());
		
		try {
			getSqlMapClientTemplate().insert("sql.POSTransLog", insertMap);
			ret = true;

		} catch (Exception e) {
			e.printStackTrace();
			ret = false;
		}
		return ret;
	}
	
	
	/**
	 * 交易流水信息
	 * 
	 * @param transRecode
	 *            指令对象
	 * @return
	 */
	public boolean saveTransRecode(TransRecode transRecode) {
		boolean ret = false;
		HashMap insertMap = new HashMap();

		// 交易流水号
		insertMap.put("transRecodeNo", transRecode.getTransRecodeNo());

		// 银行编码
		insertMap.put("bankCode", transRecode.getBankCode());

		// 账号
		insertMap.put("accountNo", transRecode.getAccountNo());

		// 币种
		insertMap.put("dbCur", transRecode.getDbCur());

		// 金额
		insertMap.put("amt", transRecode.getAmt());

		// 请求时间
		insertMap.put("reqDate", transRecode.getReqDate());

		// 响应时间
		insertMap.put("respDate", transRecode.getRespDate());

		// 响应码
		insertMap.put("respCode", transRecode.getRespCode());

		// 响应信息
		insertMap.put("respInfo", transRecode.getRespInfo());

		// 来源系统
		insertMap.put("sourceSys", transRecode.getSourceSys());

		// ICS流水号
		insertMap.put("cmeSeqNo", transRecode.getCmeSeqNo());

		// ICS流水号
		insertMap.put("transType", transRecode.getTransType());

		try {
			getSqlMapClientTemplate().insert("sql.transRecodeSql", insertMap);
			ret = true;
			logger.info("数据库层插入交易流水信息成功！");
		} catch (Exception e) {
			logger.error("数据库层插入交易流水信息失败！");
			e.printStackTrace();
			ret = false;
		}
		return ret;
	}
	
	
	
	public boolean getSrc(Command _command) {
		boolean ret = false;
		HashMap condition = new HashMap();
		condition.put("commandId", _command.getCommandId());
		condition.put("commandSrc", _command.getCommandSrc());
		HashMap src = (HashMap) getSqlMapClientTemplate().queryForObject(
				"sql.querySysCommandById", condition); // 指令基本信息
		if (src != null) {
			ret = true;
		}
		return ret;
	}

	public List getLog() {
		return getSqlMapClientTemplate().queryForList("sql.queryLog");
	}

	/**
	 * 获取T_AIMS指令列表
	 * 
	 * @return
	 */
	public List getT_AIMSCommandList() {

		List bankCommandList = new ArrayList();

		List list = (List) getSqlMapClientTemplate().queryForList(
				"sql.queryT_AIMSCommandList");
		for (int i = 0; i < list.size(); i++) {
			HashMap map = (HashMap) list.get(i);
			bankCommandList.add(map);
		}
		return bankCommandList;
	}
	
	
	/**
	 * 查询商户编码
	 * @param marketCode
	 * @return
	 */
	public Map  queryMarketCodeMap(String marketCode) {
		
		 Map map = new HashMap();
		try{
			List list = (List) getSqlMapClientTemplate().queryForList(
					"sql.queryMarketCode", marketCode);
			
	        if(list.size() > 0 ){
	        	map = (HashMap)list.get(0);
	        }
		 } catch (Exception e) {
	         e.printStackTrace();
	         logger.error("查询商户编码异常！");
	     } 
        return map;
	}
	

}
