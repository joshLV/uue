package com.zihexin.business_interface.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.zihexin.business_interface.domain.BankCommand;
import com.zihexin.business_interface.domain.Command;
import com.zihexin.business_interface.domain.POSTransRecode;
import com.zihexin.business_interface.domain.TransRecode;

/**
 * xshtest数据库 sql
 */
public class XshTestBaseDao extends SqlMapClientDaoSupport {
	private final static Logger logger = LoggerFactory
			.getLogger(XshTestBaseDao.class);

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
	 * 获取银行指令列表
	 * 
	 * @return
	 */
	public List getBankCommandList(String command_code) {

		List bankCommandList = new ArrayList();

		List list = (List) getSqlMapClientTemplate().queryForList(
				"sql.queryBankCommandList", command_code);
		for (int i = 0; i < list.size(); i++) {
			HashMap map = (HashMap) list.get(i);
			BankCommand bankCommand = new BankCommand();
			bankCommand.setCommandCode(map.get("COMMAND_CODE").toString());
			bankCommand.setFieldName(map.get("FIELD_NAME").toString());
			bankCommand.setFilePath(map.get("FILE_PATH").toString());

			bankCommandList.add(bankCommand);
		}
		return bankCommandList;
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
	 * 获取网络支付状态
	 * @param r_type
	 * @param medium_id
	 * @return
	 */
	public HashMap getAcctStatus(String r_type , String medium_id) {
		HashMap returnMap = new HashMap();
		
		HashMap condition = new HashMap();
		condition.put("r_type", r_type);
		condition.put("medium_id", medium_id);
		
		List list = (List) getSqlMapClientTemplate().queryForList(
				"financeSql.queryNtacctMediumByMedId", condition); 
		if (list == null || list.size() == 0) {
			return null;
		} else {
			for (int i = 0; i < list.size(); i++) {
				 returnMap = (HashMap) list.get(i);
			}
		}

		return returnMap;
	}
	

	/**
	 * 获取网络支付状态
	 * @param r_type
	 * @param acct_id
	 * @return
	 */
	public HashMap queryNtacctMediumByAcctId(String r_type ,String acct_id) {
		HashMap returnMap = new HashMap();
		
		HashMap condition = new HashMap();
		condition.put("r_type", r_type);
		condition.put("acct_id", acct_id);
		
		List list = (List) getSqlMapClientTemplate().queryForList(
				"financeSql.queryNtacctMediumByAcctId", condition); 
		if (list == null || list.size() == 0) {
			return null;
		} else {
			for (int i = 0; i < list.size(); i++) {
				 returnMap = (HashMap) list.get(i);
			}
		}

		return returnMap;
	}
	
	/**
	 * 获取网络支付状态
	 * 
	 * @param commandId
	 * @return
	 */
	public HashMap queryCardNoByMedId(String medium_id) {
		HashMap returnMap = new HashMap();
		List list = (List) getSqlMapClientTemplate().queryForList(
				"financeSql.queryCardNoByMedId",medium_id); 
		if (list == null || list.size() == 0) {
			return null;
		} else {
			for (int i = 0; i < list.size(); i++) {
				 returnMap = (HashMap) list.get(i);
			}
		}

		return returnMap;
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
	
	
	/**
	 * POS交易流水信息
	 * 
	 * @param map
	 *            指令对象
	 * @return
	 */
	public boolean savePosTransRecode(POSTransRecode posTransRecode) {
		boolean ret = false;
		HashMap insertMap = new HashMap();
		
		String  id  = getPosTransRecodeSeq();
		insertMap.put("id", id);
		insertMap.put("pos_code", posTransRecode.getPos_code());
		insertMap.put("pos_water_num", posTransRecode.getPos_water_num());
		insertMap.put("market_id", posTransRecode.getMarket_id());
		insertMap.put("card_no", posTransRecode.getCard_no());
		
		insertMap.put("loan_amount", posTransRecode.getLoan_amount());
		
		insertMap.put("trace_code", posTransRecode.getTrace_code());
		
		insertMap.put("swap_type", posTransRecode.getSwap_type());
	
		insertMap.put("swap_date", posTransRecode.getSwap_date());
		
		insertMap.put("auth_code", posTransRecode.getAuth_code());
		
		insertMap.put("return_code", posTransRecode.getReturn_code());
		
		insertMap.put("log_date", posTransRecode.getLog_date());
		
		//银行编码
		insertMap.put("bankCode", posTransRecode.getBankCode());
        
        //第一张绑定银行编码
		insertMap.put("bankType1", posTransRecode.getBankType1());
      
        //第一张绑定银行卡号
		insertMap.put("bankAccountNumber1", posTransRecode.getBankAccountNumber1());

		 //第二张绑定银行编码
		insertMap.put("bankType2", posTransRecode.getBankType2());
      
        //第二张绑定银行卡号
		insertMap.put("bankAccountNumber2", posTransRecode.getBankAccountNumber2());
        
		 //第三张绑定银行编码
		insertMap.put("bankType3", posTransRecode.getBankType3());
      
        //第三张绑定银行卡号
		insertMap.put("bankAccountNumber3", posTransRecode.getBankAccountNumber3());
         
	     //扣费银行标示
		insertMap.put("feeBankFlag", posTransRecode.getFeeBankFlag());
		
		try {
			getSqlMapClientTemplate().insert("sql.posTransRecode", insertMap);
			ret = true;
			logger.info("数据库层插入POS交易流水信息成功！");
		} catch (Exception e) {
			logger.error("数据库层插入POS交易流水信息失败！");
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
	 * 获取系统参数列表
	 * 
	 * @return
	 */
	public List getQueryPAramList() {

		List paramList = new ArrayList();

		List list = (List) getSqlMapClientTemplate().queryForList(
				"financeSql.queryPAramList");
		for (int i = 0; i < list.size(); i++) {
			HashMap map = (HashMap) list.get(i);
			paramList.add(map);
		}
		return paramList;
	}
	
	
	/**
	 * 执行充值函数
	 * @param hashMap
	 * @return
	 */
	public String exeRechargeFun(HashMap hashMap) {
		
		String returnResult = "";
		try{
			List list = (List) getSqlMapClientTemplate().queryForList(
					"xshtestSql.exeRechargeFun", hashMap);
			 Map map = new HashMap();
	        if(list.size() > 0 ){
	        	map = (HashMap)list.get(0);
	        	returnResult = String.valueOf(map.get("RETURNRESULT"));
	        }
		 } catch (Exception e) {
	         e.printStackTrace();
	         logger.error("执行充值函数异常！");
	     } 
        return returnResult;
	}
	

}
