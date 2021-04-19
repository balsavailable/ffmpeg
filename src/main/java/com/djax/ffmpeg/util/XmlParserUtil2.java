package com.djax.ffmpeg.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.lang3.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.djax.ffmpeg.builder.AdBuilder;
import com.djax.ffmpeg.builder.InLineBuilder;
import com.djax.ffmpeg.dto.AttributeDTO;
import com.djax.ffmpeg.dto.NodeDTO;
import com.djax.ffmpeg.wrapper.Ad;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

public class XmlParserUtil2 {

	private static List<NodeDTO> nodeList = new ArrayList<>();
	private static List<AttributeDTO> attributeList = new ArrayList<>();

	public static void main(String[] args) {
		try {
			Ad ad = null;
			AdBuilder adBuilder = new AdBuilder(new Ad());
			InLineBuilder inlineBuilder = null;
			XmlParserUtil2 thisInstance = new XmlParserUtil2();
			File inputFile = new File("D://a.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);

			// get ad tag
			Optional<NodeList> adNodeList = Optional.ofNullable(doc.getElementsByTagName("Ad"));
			Optional<Node> adElement = Optional.ofNullable(adNodeList.isPresent() ? adNodeList.get().item(0) : null);
			Optional<Node> adElemIdAttr = Optional
					.ofNullable(adElement.isPresent() ? adElement.get().getAttributes().getNamedItem("id") : null);
			if (adElemIdAttr.isPresent()) {
				String adId = adElemIdAttr.get().getNodeValue();
				if (StringUtils.isNotBlank(adId))
					adBuilder.setId(adId);
			}

			// get inline tag
			Optional<NodeList> inLineNodeList = Optional.ofNullable(doc.getElementsByTagName("InLine"));

			Optional<Node> inLineElement = Optional
					.ofNullable(inLineNodeList.isPresent() ? inLineNodeList.get().item(0) : null);

			// get adSystem tag
			Optional<Node> adSystemElement = Optional.ofNullable(thisInstance.getChildNodeFromNList(
					inLineElement.isPresent() ? inLineElement.get().getChildNodes() : null, "AdSystem"));

			// set adsystem value
			if (adSystemElement.isPresent()) {
				String adSystem = adSystemElement.get().getTextContent();

				if (StringUtils.isNotBlank(adSystem)) {
					inlineBuilder = adBuilder.getInlineBuilder();
					inlineBuilder.setAdSystem(adSystem);
				}
			}

			// get errror element from inline childs
			Optional<Node> errorElement = Optional.ofNullable(thisInstance.getChildNodeFromNList(
					inLineElement.isPresent() ? inLineElement.get().getChildNodes() : null, "Error"));

			if (errorElement.isPresent()) {
				String error = errorElement.get().getTextContent();
				if (StringUtils.isNotBlank(error)) {
					inlineBuilder.setError(error);
				}
			}

			// get description element from inline childs
			Optional<Node> descriptionElement = Optional.ofNullable(thisInstance.getChildNodeFromNList(
					inLineElement.isPresent() ? inLineElement.get().getChildNodes() : null, "Description"));

			if (descriptionElement.isPresent()) {
				String description = descriptionElement.get().getTextContent();
				if (StringUtils.isNotBlank(description)) {
					inlineBuilder.setDescription(description);
				}
			}

			// get impression element from inline childs
			Optional<Node> impressionElement = Optional.ofNullable(thisInstance.getChildNodeFromNList(
					inLineElement.isPresent() ? inLineElement.get().getChildNodes() : null, "Impression"));

			if (impressionElement.isPresent()) {
				String impression = impressionElement.get().getTextContent();
				if (StringUtils.isNotBlank(impression)) {
					inlineBuilder.setImpression(impression);
				}
			}

			// get adtitle element from inline childs
			Optional<Node> adtitleElement = Optional.ofNullable(thisInstance.getChildNodeFromNList(
					inLineElement.isPresent() ? inLineElement.get().getChildNodes() : null, "AdTitle"));

			if (adtitleElement.isPresent()) {
				String adtitle = adtitleElement.get().getTextContent();
				if (StringUtils.isNotBlank(adtitle)) {
					inlineBuilder.setAdTitle(adtitle);
				}
			}

			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			ad = adBuilder.build();
			String json = ow.writeValueAsString(ad);

			System.out.println(json);
			// System.out.println(node.getAttributes().getNamedItem("id").getTextContent());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Node getChildNodeFromNList(NodeList nodeList, String key) {
		if (nodeList == null || nodeList.getLength() == 0) {
			return null;
		}
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node node = nodeList.item(i);
			if (node.getNodeName().equals(key)) {
				return node;
			}
		}
		return null;
	}

}
