package com.djax.ffmpeg.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.lang3.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.djax.ffmpeg.dto.AttributeDTO;
import com.djax.ffmpeg.dto.NodeDTO;

public class XmlParserUtil {

	private static List<NodeDTO> nodeList = new ArrayList<>();
	private static List<AttributeDTO> attributeList = new ArrayList<>();

	public static void main1(String[] args) {
		try {
			File inputFile = new File("D://a.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);
			Node rootNode = doc.getDocumentElement();
			System.out.println("Root element :" + rootNode.getNodeName());
			NodeList nList = doc.getDocumentElement().getChildNodes();
			XmlParserUtil util = new XmlParserUtil();
			util.printAttributes(rootNode.getAttributes());
			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					System.out.println("\nCurrent Element :" + nNode.getNodeName());
					Element eElement = (Element) nNode;
					NodeList nodeList = eElement.getChildNodes();
					for (int i = 0; i < nodeList.getLength(); i++) {
						Node node = nodeList.item(i);
						if (node.getNodeType() == Node.ELEMENT_NODE) {
							util.printAttributes(node.getAttributes());
							System.out.println(node.getNodeName() + ": " + node.getTextContent());
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		try {
			File inputFile = new File("D://a.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);
			Node rootNode = doc.getDocumentElement();
			System.out.println("Root element :" + rootNode.getNodeName());
			NodeList nList = doc.getDocumentElement().getChildNodes();
			XmlParserUtil util = new XmlParserUtil();
			System.out.println("Root element attributes: ");
			util.printAttributes(rootNode.getAttributes());
			util.printNodesAndChild(nList, 0, 0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void printNodesAndChild(NodeList nList, long i, long parentId) {

		XmlParserUtil util = new XmlParserUtil();
		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				NodeDTO nodeDto = null;
				i++;
				System.out.println("\nCurrent Element :" + nNode.getNodeName());
				if (StringUtils.isNotBlank(nNode.getTextContent()) && nNode.getChildNodes().getLength() <= 1) {
					System.out.println("\nCurrent Element value :" + nNode.getTextContent());
					nodeDto = new NodeDTO(i, nNode.getNodeName(), nNode.getTextContent(), parentId, (short) 0);
				} else {
					System.out.println("++++++++++++PARENT++++++++++");
					nodeDto = new NodeDTO(i, nNode.getNodeName(), "", parentId, (short) 1);
				}
				if (nodeDto != null) {
                    nodeList.add(nodeDto);
				}
				util.printAttributes(nNode.getAttributes());
				NodeList nodeList = nNode.getChildNodes();
				if (nodeList.getLength() != 0) {
					printNodesAndChild(nodeList, i, i);
				}
			}
		}

	}

	public void printAttributes(NamedNodeMap map) {
		int attrLength = map.getLength();
		if (attrLength > 0) {
			for (int i = 0; i < attrLength; i++) {
				System.out.println("=====ATTR START======");
				System.out.println(map.item(i).getNodeName() + ":" + map.item(i).getNodeValue());
				System.out.println("=====ATTR END=======");

			}
		}

	}

}
