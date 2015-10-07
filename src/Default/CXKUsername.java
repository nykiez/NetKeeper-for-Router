package Default;

import java.util.Date;


public class CXKUsername {
		@SuppressWarnings("unused")
		private int m_ver;				//�ǿյİ汾��V12��V18����
		private long m_lasttimec;		//�ϴγɹ���ʱ�䴦��
		private String m_username;		//ԭʼ�û���
		private String m_realusername;	//�������û���
		private String RADIUS;
		private String LR;
		
		public static char trans(long n){
			if(n<=127)
				return (char) n;
			else
				return (char) (n-256);
		}
		
		public static byte intToByte(long n){
			if(n<=127)
				return  (byte) n;
			else
				return (byte)(n-256);
		}
		
		
		public CXKUsername(String username){
			this.m_ver = 18;
			this.m_lasttimec = 0;
			this.m_username=username;
			this.RADIUS="hubtxinli01";
			this.LR="\r\n";
		}
		
		public long GetLastTimeC(){
			return m_lasttimec;
		}
		
		public String Realusername(){					
			long time=(new Date()).getTime();//�õ�ϵͳʱ�䣬��1970.01.01.00:00:00 ��ʼ������
			//System.out.println(time);
			time /= 1000;
			long m_time1c;						//ʱ�������m_time1cΪ���,����ʱ�������ĵ�һ�μ���
			long m_time1convert;				//��ʱ�������Ľ������Ϊ��ʽ�ִ���ԭʼ����
			char ss[] ={0,0,0,0};		//Դ����1,��m_time1convert���м���õ���ʽ��Դ����
			byte[] by2=new byte[4];    //md5���ܲ�����һ����,m_time1c��byte��ʽ
			String m_formatsring = "";				//��m_timece������ַ���,һ��Ϊ�����ַ�
			String m_md5;						//�Գ�����(m_timec�ַ�����ʾ+m_username+radius)��MD5����
			String m_md5use;					//md5 Lowerģʽ��ǰ��λ
			
			long t;
			t = time;
			t *= 0x66666667;
			t >>= 0x20; //����32λ
			t >>= 0x01; //����1λ
			m_time1c = (long) t;  //ǿ��ת��
			
			m_lasttimec = m_time1c;
			
			t = m_time1c;
			by2[3] = intToByte(t & 0xFF);
			by2[2] = intToByte((t & 0xFF00) / 0x100) ;
			by2[1] = intToByte((t & 0xFF0000) / 0x10000);
			by2[0] = intToByte((t & 0xFF000000) / 0x1000000);
			
			//System.out.println(by2[3]+" "+by2[2]+" "+by2[1]+" "+by2[0]);

			/**
			 * ���ù���m_time1convertΪ���
			 */
			int t0=0, t1, t2, t3;
			t0 = (int) m_time1c;
			t1 = t0;
			t2 = t0;
			t3 = t0;
			t3 = t3 << 0x10;
			t1 = t1 & 0x0FF00;
			t1 = t1 | t3;
			t3 = t0;
			t3 = t3 & 0x0FF0000;
			t2 = t2 >> 0x10;
			t3 = t3 | t2;
			t1 = t1 << 0x08;
			t3 = t3 >> 0x08;
			t1 = t1 | t3;
			m_time1convert = t1;
			
			//System.out.println(m_time1convert);

			/**
			 * Դ����1,��m_time1convert���м���õ���ʽ��Դ����
			 */
			
			long tc=0;
			tc = m_time1convert;
			ss[3] = trans(tc & 0xFF);
			ss[2] = trans((tc & 0xFF00) / 0x100)  ;
			ss[1] = trans((tc & 0xFF0000) / 0x10000);
			ss[0] = trans((tc & 0xFF000000) / 0x1000000);
			
			//System.out.println(ss[3]+" "+ss[2]+ " "+ss[1]+ " "+ss[0]);
			/**
			 * ��ʽ��������
			 */
			char pp[] ={0,0,0,0};
			int i = 0, j = 0, k = 0;
			for (i = 0; i < 0x20; i++){
				j = i / 0x8;
				k = 3 - (i % 0x4);
				pp[k] *= 0x2;
				if (ss[j] % 2 == 1){
					pp[k]++;
				}
				ss[j] /= 2;
			}
			
			/**
			 * ��ʽ������,m_formatsringΪ���
			 */
			char pf[] ={0,0,0,0,0,0};
		    short st1,st2 ;
			st1 = (short) pp[3];
			st1 /= 0x4;
			pf[0] = trans(st1);
			st1 = (short) pp[3];
			st1 = (short) (st1 & 0x3);
			st1 *= 0x10;
			pf[1] = trans(st1);
			st2 = (short) pp[2];
			st2 /= 0x10;
			st2 = (short) (st2 | st1);
			pf[1] = trans(st2);
			st1 = (short) pp[2];
			st1 = (short) (st1 & 0x0F);
			st1 *= 0x04;
			pf[2] = trans(st1);
			st2 = (short) pp[1];
			st2 /= 0x40;
			st2 = (short) (st2 | st1);
			pf[2] = trans(st2);
			st1 = (short) pp[1];
			st1 = (short) (st1 & 0x3F);
			pf[3] = trans(st1);
			st2 = (short) pp[0];
			st2 /= 0x04;
			pf[4] = trans(st2);
			st1 = (short) pp[0];
			st1 = (short) (st1 & 0x03);
			st1 *= 0x10;
			pf[5] = trans(st1);
			
		/*	String arr="";
			for(int x=0;x<6;x++){
				arr+=(pf[x]+" ");
			}
			System.out.println(arr);*/
			
			for (int n = 0; n < 6; n++){
				pf[n] += 0x20;
				if ((pf[n]) >= 0x40){
					pf[n]++;
				}
			}
			
			//System.out.println("m_f"+m_formatsring);
			
			for (int m = 0; m < 6; m++){
				m_formatsring += pf[m];
			}
			
			//System.out.println("m_f"+m_formatsring);
			
			String strInput;
			String strtem;
			if(m_username.contains("@")){
				strtem=m_username.substring(0, m_username.indexOf("@"));
			}else{
				strtem=m_username;
			}
			strInput = strtem + RADIUS;
			byte[] temp=new byte[by2.length+strInput.getBytes().length];
			System.arraycopy(by2, 0, temp, 0, by2.length);
			System.arraycopy(strInput.getBytes(),0,temp,by2.length,strInput.getBytes().length);
			m_md5 = MD5.getMD5(temp);
			
			//System.out.println("m5:"+m_md5);
			m_md5use = m_md5.substring(0, 2);
			m_realusername = m_formatsring + m_md5use + m_username;
			m_realusername = LR + m_realusername;//ǰ����λΪ�س�����0D0A,�������Ǻ�����

			return m_realusername;
		}
}