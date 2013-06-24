package de.YonasCode.AdminHelper.UpdateSystem;

import de.YonasCode.AdminHelper.Main;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class UpdateAlert {
	
	private URL filesFeed;
	private String version;
	private String link;
	private boolean needUpdate = false;
	
	public UpdateAlert(String url) {
		try {
			this.filesFeed = new URL(url);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateInformations() {
		boolean ret = true;
		try {
			InputStream input = this.filesFeed.openConnection().getInputStream();
			Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(input);
			
			Node latestFile = document.getElementsByTagName("item").item(0);
			NodeList children = latestFile.getChildNodes();
			
			this.version = children.item(1).getTextContent().replaceAll("[a-zA-Z]", "");
			this.link = children.item(3).getTextContent();
			
			ret = !(this.version.replaceAll(" ", "").equals(Main.INSTANCE.getDescription().getVersion().replaceAll(" ", "")));
			
			
		} catch(SAXException|ParserConfigurationException|IOException e) {
			Main.LOG.warning(e.toString());
		}
		this.needUpdate = ret;
	}
	
	public boolean updateNeeded() {
		return this.needUpdate;
	}
	
	public String getVersion() {
		return this.version;
	}
	
	public String getLink() {
		return this.link;
	}
	
}
