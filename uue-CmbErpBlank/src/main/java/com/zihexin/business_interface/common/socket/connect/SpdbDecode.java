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
		
		if(in.remaining()<8){   //��ĿЭ��ǰ���ֽ�����int���Ͷ���id�ͳ���
			   return false;       //�����������8�ֽڣ����¸���������һ�����
		}
	   int start=in.position();
	   int id=in.getInt();
	   int lenght=in.getInt();
	   int remaining=in.remaining();
	   if(remaining==lenght-8){
		   try{
			    
		   }finally{
			    in.position(in.limit());           //�����Ѹ��ƹ���Ϊ���ĵĿռ䣬��Ȼmima����Ϊ���ݻ�û����
		   }
		   
		   return true;
			  
	   }else if(remaining<lenght-8){     //�ٰ����
			   in.position(start);
			   return false;
	   }else{                             //ճ�������
		   int limit=in.limit();
		   in.limit(start+lenght);
		   try{
		  //  goDecoder(session, in.slice(), out, id);//���������Լ����ǲ�������
		   }finally{
			   in.position(in.limit());
			   in.limit(limit);
		   }
		   doDecode(session, in, out);
		   return true;
	  }
		
	}

}
