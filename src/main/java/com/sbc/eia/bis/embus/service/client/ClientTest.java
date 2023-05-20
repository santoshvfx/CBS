package com.sbc.eia.bis.embus.service.client;

import java.util.Properties;

import com.sbc.eia.bis.RmNam.utilities.TranBase;
import com.sbc.eia.bis.embus.service.client.access.ClientHelper;
/**
 * @author jn1936
 *
 */
public class ClientTest
{
	public ClientTest()
	{
		super();
		System.setProperty("bis.platform", "pc");
	}
	public static void main(String[] arg)
	{
		ClientTest client = new ClientTest();
		client.publisRGActivation();
		System.exit(0);
	}
	public void publisRGActivation()
	{
		try
		{
			Properties p = new Properties();
			p.setProperty("BBNMS_CLIENT_CONFIG_FILE_NAME", "test-client-config.xml");
			p.setProperty("BBNMS_ENVIRONMENT_NAME", "BBNMS");
			p.setProperty("STDOUT", "FALSE");
			p.setProperty("BIS_NAME", "RM12");
			p.setProperty("BIS_VERSION", "12.0.0");
			p.setProperty("LOG_POLICY_PATH", "");
			p.setProperty("OSS_PUBLIC_KEY","bossApplData1");
			
			TranBase dummy = new TranBase(p);
			ClientService service = new ClientService(p, dummy);
			
			String inputXml = "<response>true</response>";
			
			//reset jms/embus properties
			Properties pros = new Properties();
			
			//pros.put(ClientHelper.JMS_CORRELATION_ID,"yyyyyyyyyy");
			//pros.put(ClientHelper.EMBUS_MESSAGE_TAG, "xxxxxxxx");

			Properties override = new Properties();
			
			//override.put(ClientHelper.JMS_DESTINATION_NAME,"cn=resourceManagement_jn1936,ou=11_0,ou=rm,o=bis");
						
			//service.rmBisRequests(inputXml, pros, override);
			
			System.out.println("send with default config testing caching==================");
			
			//service.rmBisRequests(inputXml);
		
			System.out.println("message sent");
		}		
		catch (Exception e)
		{
			System.out.println("---cacth a exception------");
			e.printStackTrace();
		}
	}
}
