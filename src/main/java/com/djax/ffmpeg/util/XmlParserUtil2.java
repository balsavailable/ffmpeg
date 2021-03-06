package com.djax.ffmpeg.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.djax.ffmpeg.builder.AdBuilder;
import com.djax.ffmpeg.builder.CompanionAdsBuilder;
import com.djax.ffmpeg.builder.CreativeBuilder;
import com.djax.ffmpeg.builder.ExtensionBuilder;
import com.djax.ffmpeg.builder.GeoExtensionBuilder;
import com.djax.ffmpeg.builder.InLineBuilder;
import com.djax.ffmpeg.builder.LinearBuilder;
import com.djax.ffmpeg.builder.MediaFileBuilder;
import com.djax.ffmpeg.builder.MetricExtensionBuilder;
import com.djax.ffmpeg.builder.TrackingBuilder;
import com.djax.ffmpeg.builder.TrackingExtensionBuilder;
import com.djax.ffmpeg.builder.WaterFallExtensionBuilder;
import com.djax.ffmpeg.data.PersistXmlData;
import com.djax.ffmpeg.dto.AttributeDTO;
import com.djax.ffmpeg.dto.NodeDTO;
import com.djax.ffmpeg.dto.XmlData;
import com.djax.ffmpeg.wrapper.Ad;
import com.djax.ffmpeg.wrapper.Creative;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

@Component
public class XmlParserUtil2 {

	private static List<NodeDTO> nodeList = new ArrayList<>();
	private static List<AttributeDTO> attributeList = new ArrayList<>();

	@Autowired
	PersistXmlData persistXmlData;

	public void main(String[] args) {
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

			Optional<Node> creativesNode = Optional.ofNullable(thisInstance.getChildNodeFromNList(
					inLineElement.isPresent() ? inLineElement.get().getChildNodes() : null, "creatives"));

			Optional<List<Node>> creativeEleList = Optional.ofNullable(thisInstance.getChildNodesFromNList(
					creativesNode.isPresent() ? creativesNode.get().getChildNodes() : null, "creative"));

			if (creativeEleList.isPresent()) {

				for (Node creativeElement : creativeEleList.get()) {
					CreativeBuilder creativeBuilder = new CreativeBuilder(new Creative());

					// create linear builder for linear child
					LinearBuilder linearBuilder = creativeBuilder.getLinearBuilder();
					CompanionAdsBuilder companionBuilder = creativeBuilder.getCompanionBuilder();
					TrackingBuilder trackingBuilder = new TrackingBuilder();

					// set creative id
					Optional<NamedNodeMap> namedNodeMap = Optional.ofNullable(creativeElement.getAttributes());
					Optional<Node> creativeIdAttr = Optional
							.ofNullable(namedNodeMap.isPresent() ? namedNodeMap.get().getNamedItem("id") : null);
					String creativeId = creativeIdAttr.isPresent() ? creativeIdAttr.get().getNodeValue() : null;
					if (StringUtils.isNotBlank(creativeId)) {
						creativeBuilder.setId(creativeId);
					}

					// set sequence
					Optional<Node> sequenceAtrr = Optional
							.ofNullable(namedNodeMap.isPresent() ? namedNodeMap.get().getNamedItem("sequence") : null);
					String sequence = sequenceAtrr.isPresent() ? sequenceAtrr.get().getNodeValue() : null;
					if (StringUtils.isNotBlank(sequence)) {
						creativeBuilder.setSequence(sequence);
					}

					Optional<Node> linearNode = Optional
							.ofNullable(thisInstance.getChildNodeFromNList(creativeElement.getChildNodes(), "linear"));
					Optional<Node> durationNode = Optional.ofNullable(thisInstance.getChildNodeFromNList(
							linearNode.isPresent() ? linearNode.get().getChildNodes() : null, "duration"));
					String duration = durationNode.isPresent() ? durationNode.get().getTextContent() : null;
					if (StringUtils.isNotBlank(duration)) {
						linearBuilder.setDuration(duration);
					}

					Optional<Node> trackingEventNode = Optional.ofNullable(thisInstance.getChildNodeFromNList(
							linearNode.isPresent() ? linearNode.get().getChildNodes() : null, "TrackingEvents"));
					Optional<List<Node>> trackingEvents = Optional.ofNullable(thisInstance.getChildNodesFromNList(
							trackingEventNode.isPresent() ? trackingEventNode.get().getChildNodes() : null,
							"Tracking"));
					/*
					 * int size=trackingEvents.get().size(); System.out.println(size);
					 */
					for (Node node : (trackingEvents.isPresent() ? trackingEvents.get() : new ArrayList<Node>())) {
						Optional<NamedNodeMap> attributes = Optional.ofNullable(node.getAttributes());
						Optional<Node> attribute = Optional
								.ofNullable(attributes.isPresent() ? attributes.get().item(0) : null);
						String trackingType = attribute.isPresent() ? attribute.get().getNodeValue() : null;
						String trackingValue = node.getTextContent();

						if (StringUtils.isNotBlank(trackingType)) {
							switch (trackingType) {
							case "start":
								trackingBuilder.setStart(trackingValue);
								break;
							case "firstQuartile":
								trackingBuilder.setFirstQuartile(trackingValue);
								break;
							case "midpoint":
								trackingBuilder.setMidpoint(trackingValue);
								break;
							case "thirdQuartile":
								trackingBuilder.setThirdQuartile(trackingValue);
								break;
							case "complete":
								trackingBuilder.setComplete(trackingValue);
								break;
							case "mute":
								trackingBuilder.setMute(trackingValue);
								break;
							case "unmute":
								trackingBuilder.setUnmute(trackingValue);
								break;
							case "rewind":
								trackingBuilder.setRewind(trackingValue);
								break;
							case "pause":
								trackingBuilder.setPause(trackingValue);
								break;
							case "resume":
								trackingBuilder.setResume(trackingValue);
								break;
							case "creativeView":
								trackingBuilder.setCreativeView(trackingValue);
								break;
							case "fullscreen":
								trackingBuilder.setFullscreen(trackingValue);
								break;
							case "acceptInvitationLinear":
								trackingBuilder.setAcceptInvitationLinear(trackingValue);
								break;
							case "closeLinear":
								trackingBuilder.setCloseLinear(trackingValue);
								break;
							case "exitFullscreen":
								trackingBuilder.setExitFullscreen(trackingValue);
								break;
							}
						}
					}

					// video clicks parsingl
					Optional<Node> videoClicksNode = Optional.ofNullable(thisInstance.getChildNodeFromNList(
							linearNode.isPresent() ? linearNode.get().getChildNodes() : null, "VideoClicks"));
					Optional<Node> clickThroughNode = Optional.ofNullable(thisInstance.getChildNodeFromNList(
							videoClicksNode.isPresent() ? videoClicksNode.get().getChildNodes() : null,
							"ClickThrough"));

					NamedNodeMap clickThroughAttrs = clickThroughNode.isPresent()
							? clickThroughNode.get().getAttributes()
							: null;
					Node idNode = clickThroughAttrs != null ? clickThroughAttrs.getNamedItem("id") : null;
					String clickThroughId = idNode != null ? idNode.getNodeValue() : null;
					String clickThroughText = clickThroughNode.isPresent() ? clickThroughNode.get().getTextContent()
							: null;
					if (StringUtils.isNotBlank(clickThroughId))
						linearBuilder.getVideoClickBuilder().setId(clickThroughId);

					if (StringUtils.isNotBlank(clickThroughText))
						linearBuilder.getVideoClickBuilder().setText(clickThroughText);

					// Media File parsing
					Optional<Node> mediaFilesNode = Optional.ofNullable(thisInstance.getChildNodeFromNList(
							linearNode.isPresent() ? linearNode.get().getChildNodes() : null, "MediaFiles"));
					Optional<List<Node>> mediaFileNodeList = Optional.ofNullable(thisInstance.getChildNodesFromNList(
							mediaFilesNode.isPresent() ? mediaFilesNode.get().getChildNodes() : null, "MediaFile"));

					for (Node mediaFile : mediaFileNodeList.isPresent() ? mediaFileNodeList.get()
							: new ArrayList<Node>()) {
						MediaFileBuilder mediaFileBuilder = new MediaFileBuilder();
						NamedNodeMap mediaFileAttributes = mediaFile.getAttributes();
						Node idAttributeAttr = mediaFileAttributes != null ? mediaFileAttributes.getNamedItem("id")
								: null;
						Node deliveryAttr = mediaFileAttributes != null ? mediaFileAttributes.getNamedItem("delivery")
								: null;
						Node widthAttr = mediaFileAttributes != null ? mediaFileAttributes.getNamedItem("width") : null;
						Node heightAttr = mediaFileAttributes != null ? mediaFileAttributes.getNamedItem("height")
								: null;
						Node typeAttr = mediaFileAttributes != null ? mediaFileAttributes.getNamedItem("type") : null;
						Node bitrateAttr = mediaFileAttributes != null ? mediaFileAttributes.getNamedItem("bitrate")
								: null;
						Node scalableAttr = mediaFileAttributes != null ? mediaFileAttributes.getNamedItem("scalable")
								: null;
						Node maintainAspectRatioAttr = mediaFileAttributes != null
								? mediaFileAttributes.getNamedItem("maintainAspectRatio")
								: null;
						String mediaFileText = mediaFile != null ? mediaFile.getTextContent() : null;

						String mediaFileId = idAttributeAttr != null ? idAttributeAttr.getNodeValue() : null;
						String delivery = deliveryAttr != null ? deliveryAttr.getNodeValue() : null;
						String width = widthAttr != null ? widthAttr.getNodeValue() : null;
						String height = heightAttr != null ? heightAttr.getNodeValue() : null;
						String type = typeAttr != null ? typeAttr.getNodeValue() : null;
						String bitrate = bitrateAttr != null ? bitrateAttr.getNodeValue() : null;
						String scalable = scalableAttr != null ? scalableAttr.getNodeValue() : null;
						String maintainAspectRation = maintainAspectRatioAttr != null
								? maintainAspectRatioAttr.getNodeValue()
								: null;
						if (StringUtils.isNotBlank(mediaFileId))
							mediaFileBuilder.setId(mediaFileId);
						if (StringUtils.isNotBlank(mediaFileText))
							mediaFileBuilder.setMediaFile(mediaFileText);
						if (StringUtils.isNotBlank(delivery))
							mediaFileBuilder.setDelivery(delivery);
						if (StringUtils.isNotBlank(delivery))
							mediaFileBuilder.setWidth(width);
						if (StringUtils.isNotBlank(width))
							mediaFileBuilder.setWidth(width);
						if (StringUtils.isNotBlank(height))
							mediaFileBuilder.setHeight(height);
						if (StringUtils.isNotBlank(type))
							mediaFileBuilder.setType(type);
						if (StringUtils.isNotBlank(bitrate))
							mediaFileBuilder.setBitrate(bitrate);
						if (StringUtils.isNotBlank(scalable))
							mediaFileBuilder.setScalable(scalable);
						if (StringUtils.isNotBlank(mediaFileText))
							mediaFileBuilder.setMediaFile(mediaFileText);
						linearBuilder.addMediaFile(mediaFileBuilder.build());
					}

					linearBuilder.addTrackingEvent(trackingBuilder.build());

					// start companion object building
					CompanionAdsBuilder companionAdsBuilder = creativeBuilder.getCompanionBuilder();
					Node companionAdsElement = thisInstance.getChildNodeFromNList(creativeElement.getChildNodes(),
							"CompanionAds");
					Node companionElement = thisInstance.getChildNodeFromNList(
							companionAdsElement != null ? companionAdsElement.getChildNodes() : null, "Companion");
					NamedNodeMap companionAttributes = companionElement != null ? companionElement.getAttributes()
							: null;

					if (companionAttributes != null) {
						for (int i = 0; i < companionAttributes.getLength(); i++) {
							Node nodeAttr = companionAttributes.item(i);
							String key = nodeAttr != null ? nodeAttr.getNodeName() : null;
							switch (key != null ? key : "") {
							case "id":
								companionAdsBuilder.setId(nodeAttr != null ? nodeAttr.getNodeValue() : null);
								break;
							case "width":
								companionAdsBuilder.setWidth(nodeAttr != null ? nodeAttr.getNodeValue() : null);
								break;
							case "height":
								companionAdsBuilder.setHeight(nodeAttr != null ? nodeAttr.getNodeValue() : null);
								break;
							default:
								break;
							}
						}
					}

					Node trackingEventsElement = thisInstance.getChildNodeFromNList(
							companionElement != null ? companionElement.getChildNodes() : null, "TrackingEvents");
					Node trackingElement = thisInstance.getChildNodeFromNList(
							trackingEventsElement != null ? trackingEventsElement.getChildNodes() : null, "Tracking");

					NamedNodeMap attributes = trackingElement != null ? trackingElement.getAttributes() : null;
					Node eventAttr = attributes != null ? attributes.getNamedItem("event") : null;
					String trackingEventType = eventAttr != null ? eventAttr.getNodeValue() : null;
					String trackingValue = trackingElement != null ? trackingElement.getTextContent() : null;
					TrackingBuilder trackingEventBuilder = new TrackingBuilder();
					if (StringUtils.isNotBlank(trackingEventType)) {
						switch (trackingEventType) {
						case "creativeView":
							trackingEventBuilder.setCreativeView(trackingValue);
							break;
						default:
							break;
						}
						companionAdsBuilder.setTrackingEvents(trackingEventBuilder.build());
					}

					// static resource object set

					Node staticResourceElement = thisInstance.getChildNodeFromNList(
							companionElement != null ? companionElement.getChildNodes() : null, "StaticResource");
					NamedNodeMap staticResourAttr = staticResourceElement != null
							? staticResourceElement.getAttributes()
							: null;
					Node creativeTypeAttr = staticResourAttr != null ? staticResourAttr.getNamedItem("creativeType")
							: null;
					String creativeTypeAttrVal = creativeTypeAttr != null ? creativeTypeAttr.getNodeValue() : null;
					companionAdsBuilder.getStaticResourceBuilder().setCreativeType(creativeTypeAttrVal);

					String staticResourceText = staticResourceElement != null ? staticResourceElement.getTextContent()
							: null;
					companionAdsBuilder.getStaticResourceBuilder().setText(staticResourceText);

					Node companionClickThrough = thisInstance.getChildNodeFromNList(
							companionElement != null ? companionElement.getChildNodes() : null,
							"CompanionClickThrough");
					String companionClickThroghText = companionClickThrough != null
							? companionClickThrough.getTextContent()
							: null;
					companionAdsBuilder.setCompanionClickThrough(companionClickThroghText);

					inlineBuilder.addCreatives(creativeBuilder.build());
				}
			}

			// building extension
			ExtensionBuilder extensionBuilder = inlineBuilder.getExtensionBuilder();
			Node extensionNode = thisInstance.getChildNodeFromNList(
					inLineElement.isPresent() ? inLineElement.get().getChildNodes() : null, "extensions");
			List<Node> extensionNodes = thisInstance
					.getChildNodesFromNList(extensionNode != null ? extensionNode.getChildNodes() : null, "extension");

			// int size = extensionNodes.size();

			for (Node extNode : extensionNodes != null ? extensionNodes : new ArrayList<Node>()) {
				NamedNodeMap extNodeAttr = extNode != null ? extNode.getAttributes() : null;
				Node typeAttr = extNodeAttr != null ? extNodeAttr.getNamedItem("type") : null;
				String type = typeAttr != null ? typeAttr.getNodeValue() : null;
				switch (type) {
				case "waterfall":
					thisInstance.buildWaterfallExtension(extensionBuilder.getWaterFallExtensionBuilder(), extNode);
					break;
				case "geo":
					thisInstance.buildGeoExtension(extensionBuilder.getGeoExtensionBuilder(), extNode);
					break;
				case "metrics":
					thisInstance.buildMetricsExtension(extensionBuilder.getMetricExtensionBuilder(), extNode);
					break;
				case "ShowAdTracking":
					thisInstance.buildTrackingExtension(extensionBuilder.getShowAdTrackingExtensionBuilder(), extNode,
							"showAd");
					break;
				case "video_ad_loaded":
					thisInstance.buildTrackingExtension(extensionBuilder.getVideoAdTrackingExtensionBuilder(), extNode,
							"videoAd");
					break;
				default:
					break;
				}
			}

			ObjectWriter ow = new ObjectMapper().setSerializationInclusion(Include.NON_NULL).writer().withDefaultPrettyPrinter();
			ad = adBuilder.build();
			String json = ow.writeValueAsString(ad);
			XmlData xmlData = new XmlData();
			xmlData.setJson(json);
			xmlData.setType("vast");
			this.persistXmlData.persistXmlData(xmlData);
			System.out.println(json);

			// System.out.println(node.getAttributes().getNamedItem("id").getTextContent());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void buildWaterfallExtension(WaterFallExtensionBuilder waterFallExtensionBuilder, Node extensionElement) {

		NamedNodeMap waterFallExtensionAttr = extensionElement != null ? extensionElement.getAttributes() : null;
		Node typeAttribute = waterFallExtensionAttr != null ? waterFallExtensionAttr.getNamedItem("type") : null;
		Node fallbackAttribute = waterFallExtensionAttr != null ? waterFallExtensionAttr.getNamedItem("fallback_index")
				: null;
		waterFallExtensionBuilder.setType(typeAttribute != null ? typeAttribute.getNodeValue() : null);
		waterFallExtensionBuilder.setFallbackIndex(fallbackAttribute != null ? fallbackAttribute.getNodeValue() : null);
	}

	public void buildGeoExtension(GeoExtensionBuilder geoExtensionBuilder, Node extensionElement) {
		NamedNodeMap geoExtensionAttr = extensionElement != null ? extensionElement.getAttributes() : null;
		Node typeAttribute = geoExtensionAttr != null ? geoExtensionAttr.getNamedItem("type") : null;
		geoExtensionBuilder.setType(typeAttribute != null ? typeAttribute.getNodeValue() : null);
		NodeList geoExtensionChildList = extensionElement != null ? extensionElement.getChildNodes() : null;
		for (int i = 0; i < (geoExtensionChildList != null ? geoExtensionChildList.getLength() : 0); i++) {
			Node node = geoExtensionChildList.item(i);
			String nodeName = node != null ? node.getNodeName() : null;
			String nodeValue = node != null ? node.getTextContent() : null;
			switch (nodeName != null ? nodeName : "") {
			case "Country":
				geoExtensionBuilder.setCountry(nodeValue);
				break;
			case "Bandwidth":
				geoExtensionBuilder.setBandwidth(nodeValue);
				break;
			case "BandwidthKbps":
				geoExtensionBuilder.setBandwidthKbps(nodeValue);
				break;
			default:
				break;
			}
		}
	}

	public void buildMetricsExtension(MetricExtensionBuilder metricExtensionBuilder, Node extensionElement) {

		NamedNodeMap metricsExtensionAttr = extensionElement != null ? extensionElement.getAttributes() : null;
		Node typeAttribute = metricsExtensionAttr != null ? metricsExtensionAttr.getNamedItem("type") : null;
		metricExtensionBuilder.setType(typeAttribute != null ? typeAttribute.getNodeValue() : null);
		NodeList metricsExtensionChildList = extensionElement != null ? extensionElement.getChildNodes() : null;
		for (int i = 0; i < (metricsExtensionChildList != null ? metricsExtensionChildList.getLength() : 0); i++) {
			Node node = metricsExtensionChildList.item(i);
			String nodeName = node != null ? node.getNodeName() : null;
			String nodeValue = node != null ? node.getTextContent() : null;
			switch (nodeName != null ? nodeName : "") {
			case "FeEventId":
				metricExtensionBuilder.setFeEventId(nodeValue);
				break;
			case "AdEventId":
				metricExtensionBuilder.setAdEventId(nodeValue);
				break;
			default:
				break;
			}
		}

	}

	public void buildTrackingExtension(TrackingExtensionBuilder trackingExtensionBuider, Node extensionElement,
			String trackingType) {
		NamedNodeMap metricsExtensionAttr = extensionElement != null ? extensionElement.getAttributes() : null;
		Node typeAttribute = metricsExtensionAttr != null ? metricsExtensionAttr.getNamedItem("type") : null;
		trackingExtensionBuider.setType(typeAttribute != null ? typeAttribute.getNodeValue() : null);
		Node customTrackingNode = getChildNodeFromNList(
				extensionElement != null ? extensionElement.getChildNodes() : null, "customtracking");
		Node trackingNode = getChildNodeFromNList(
				customTrackingNode != null ? customTrackingNode.getChildNodes() : null, "tracking");
		// NamedNodeMap attrs = trackingNode != null ? trackingNode.getAttributes() :
		// null;
		// Node eventAttr = attrs!=null?attrs.getNamedItem("event"):null;
		String eventVal = trackingNode != null ? trackingNode.getTextContent() : null;
		if (trackingType.equals("showAd")) {
			trackingExtensionBuider.getCustomTrackingBuilder().setShowAd(eventVal);

		} else if (trackingType.equals("videoAd")) {
			trackingExtensionBuider.getCustomTrackingBuilder().setLoaded(eventVal);
		}

	}

	public Node getChildNodeFromNList(NodeList nodeList, String key) {
		if (nodeList == null || nodeList.getLength() == 0) {
			return null;
		}
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node node = nodeList.item(i);
			if (node.getNodeName().equalsIgnoreCase(key)) {
				return node;
			}
		}
		return null;
	}

	public List<Node> getChildNodesFromNList(NodeList nodeList, String key) {
		List<Node> resultNodes = new ArrayList<>();

		if (nodeList == null || nodeList.getLength() == 0) {
			return null;
		}
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node node = nodeList.item(i);
			if (node.getNodeName().equalsIgnoreCase(key)) {
				resultNodes.add(node);
			}
		}
		return resultNodes;
	}

}
