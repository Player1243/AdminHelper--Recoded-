/*
* Copyright 2011-2013 Jan Krüger. All rights reserved.
*
* Redistribution and use in source and binary forms, with or without modification, are
* permitted provided that the following conditions are met:
*
* 1. Redistributions of source code must retain the above copyright notice, this list of
* conditions and the following disclaimer.
*
* 2. Redistributions in binary form must reproduce the above copyright notice, this list
* of conditions and the following disclaimer in the documentation and/or other materials
* provided with the distribution.
*
* THIS SOFTWARE IS PROVIDED BY THE AUTHOR ''AS IS'' AND ANY EXPRESS OR IMPLIED
* WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
* FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE AUTHOR OR
* CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
* CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
* SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
* ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
* NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF
* ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*
* The views and conclusions contained in the software and documentation are those of the
* authors and contributors and should not be interpreted as representing official policies,
* either expressed or implied, of anybody else.
*/
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
	private NodeList children;
	private String version;
	private String link;
	private String[] changelog = {""};
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
			//Version and link
			InputStream input = this.filesFeed.openConnection().getInputStream();
			Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(input);
			
			Node latestFile = document.getElementsByTagName("item").item(0);
			this.children = latestFile.getChildNodes();
			
			this.version = children.item(1).getTextContent().replaceAll("[a-zA-Z]", "");
			this.link = children.item(3).getTextContent();
			
			ret = !(this.version.replaceAll(" ", "").equals(Main.INSTANCE.getDescription().getVersion().replaceAll(" ", "")));
			
			//changelog
			String cg = children.item(17).getTextContent().replaceAll("(\r\n|\n)", "").replaceAll("<br/>", "<br />").replaceAll("<br />", "<br />").replaceAll("<br>", "<br />").replaceAll("<p>", "").replaceAll("</p>", "");
			this.changelog = cg.split("<br />");
			
		} catch(SAXException|ParserConfigurationException|IOException e) {
			Main.LOG.warning(e.toString());
		}
		this.needUpdate = ret;
	}
	
	public String[] getChangelog() {
		return this.changelog;
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
