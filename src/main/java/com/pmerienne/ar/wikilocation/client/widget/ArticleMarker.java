package com.pmerienne.ar.wikilocation.client.widget;

import com.pmerienne.ar.wikilocation.client.model.Article;
import com.pmerienne.gwt.ar.widget.marker.InformationMarker;
import com.pmerienne.gwt.ar.widget.marker.MarkerInformation;

public class ArticleMarker extends InformationMarker {

	private Article article;

	public ArticleMarker(Article article) {
		super(article.getLocation(), article.getTitle());
		this.article = article;
	}

	@Override
	public void update(MarkerInformation markerInformation) {
		this.setTitle(this.article.getTitle());
		this.setInformation(markerInformation.getDistance().intValue() + " m");
	}

	public Article getArticle() {
		return this.article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

}
