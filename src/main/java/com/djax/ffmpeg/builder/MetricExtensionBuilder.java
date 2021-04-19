package com.djax.ffmpeg.builder;

import com.djax.ffmpeg.wrapper.Extension;
import com.djax.ffmpeg.wrapper.extension.GeoExtension;
import com.djax.ffmpeg.wrapper.extension.MetricExtension;

public class MetricExtensionBuilder extends ExtensionBuilder {

	private MetricExtension metricExtension;

	public MetricExtensionBuilder(Extension extension) {
		this.metricExtension = new MetricExtension();
		extension.setMetricExtension(metricExtension);
	}

	public MetricExtensionBuilder setType(String type) {
		this.metricExtension.setType(type);
		return this;
	}

	public MetricExtensionBuilder setFeEventId(String feEventId) {
		this.metricExtension.setFeEventId(feEventId);
		return this;
	}

	public MetricExtensionBuilder setAdEventId(String adEventId) {
		this.metricExtension.setAdEventId(adEventId);
		return this;
	}


}
