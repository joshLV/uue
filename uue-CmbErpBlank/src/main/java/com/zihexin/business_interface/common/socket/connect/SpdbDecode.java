package com.zihexin.business_interface.common.socket.connect;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

public class SpdbDecode extends CumulativeProtocolDecoder {

	@Override
	protected boolean doDecode(IoSession session, IoBuffer in,
			ProtocolDecoderOutput out) throws Exception {
		// TODO Auto-generated method stub
		
		if(in.remaining()<8){   //项目协议前八字节两个int类型定义id和长度
			   return false;       //如果传来不足8字节，等下个数据来了一起解析
		}
	   int start=in.position();
	   int id=in.getInt();
	   int lenght=in.getInt();
	   int remaining=in.remaining();
	   if(remaining==lenght-8){
		   try{
			    
		   }finally{
			    in.position(in.limit());           //设置已复制过的为消耗的空间，不然mima会认为数据还没处理
		   }
		   
		   return true;
			  
	   }else if(remaining<lenght-8){     //少包情况
			   in.position(start);
			   return false;
	   }else{                             //粘包的情况
		   int limit=in.limit();
		   in.limit(start+lenght);
		   try{
		  //  goDecoder(session, in.slice(), out, id);//处理属于自己的那部分数据
		   }finally{
			   in.position(in.limit());
			   in.limit(limit);
		   }
		   doDecode(session, in, out);
		   return true;
	  }
		
	}

}
