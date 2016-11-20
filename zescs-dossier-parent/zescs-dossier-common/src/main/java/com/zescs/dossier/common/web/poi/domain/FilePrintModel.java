package com.zescs.dossier.common.web.poi.domain;

import java.io.InputStream;

public class FilePrintModel {
	private InputStream inputStream;
	private Long size;

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}

	public FilePrintModel(InputStream inputStream, Long size) {
		this.inputStream = inputStream;
		this.size = size;
	}
}
