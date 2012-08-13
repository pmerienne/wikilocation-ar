package com.pmerienne.ar.wikilocation.client;

import java.util.List;

import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.GWT.UncaughtExceptionHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.pmerienne.ar.wikilocation.client.model.Article;
import com.pmerienne.ar.wikilocation.client.model.WikiLocationResponse;
import com.pmerienne.ar.wikilocation.client.service.WikiLocationService;
import com.pmerienne.ar.wikilocation.client.util.Notification;
import com.pmerienne.ar.wikilocation.client.widget.ArticleMarker;
import com.pmerienne.gwt.ar.device.DeviceInformationProvider;
import com.pmerienne.gwt.ar.device.Location;
import com.pmerienne.gwt.ar.event.GeolocationChangeEvent;
import com.pmerienne.gwt.ar.event.GeolocationChangeHandler;
import com.pmerienne.gwt.ar.event.OrientationChangeEvent;
import com.pmerienne.gwt.ar.event.OrientationChangeHandler;
import com.pmerienne.gwt.ar.widget.ARPanel;

public class Application implements EntryPoint {

	private WikiLocationService wikiLocationService = GWT.create(WikiLocationService.class);

	private ARPanel arPanel;

	private Label cameraOrientationLabel;
	private Label deviceOrientationLabel;

	@Override
	public void onModuleLoad() {
		GWT.setUncaughtExceptionHandler(new UncaughtExceptionHandler() {
			@Override
			public void onUncaughtException(Throwable e) {
				// Window.alert("uncaught: " + e.getMessage());
				e.printStackTrace();
			}
		});

		new Timer() {
			@Override
			public void run() {
				createApplication();
			}

		}.schedule(1);

	}

	private void createApplication() {
		this.initUI();
		this.initHandlers();
		this.showSupport();
	}

	private void initUI() {
		RootPanel.get().setSize("100%", "100%");
		// Add augmented reality panel
		this.arPanel = new ARPanel();
		this.arPanel.setSize("100%", "100%");
		RootPanel.get().add(this.arPanel);

		this.cameraOrientationLabel = new Label();
		RootPanel.get().add(this.cameraOrientationLabel);

		this.deviceOrientationLabel = new Label();
		RootPanel.get().add(this.deviceOrientationLabel);
	}

	private void initHandlers() {
		// Update markers when geolocation change
		DeviceInformationProvider.get().addHandler(GeolocationChangeEvent.getType(), new GeolocationChangeHandler() {
			@Override
			public void onGeolocationChange(GeolocationChangeEvent event) {
				reloadArticles(event.getLocation());
			}
		});

		// Update orientation label when orientation change
		DeviceInformationProvider.get().addHandler(OrientationChangeEvent.getType(), new OrientationChangeHandler() {
			@Override
			public void onOrientationChange(OrientationChangeEvent event) {
				cameraOrientationLabel.setText(event.getCameraOrientation().toString() + ", " + event.getGlobalOrientation());
				deviceOrientationLabel.setText(event.getDeviceOrientation().toString());
			}
		});
	}

	private void showSupport() {
		DeviceInformationProvider dip = DeviceInformationProvider.get();

		if (!dip.isCameraAPISupported()) {
			Notification.warning("You're browser doesn't support camera API");
		}

		if (!dip.isGeolocationSupported()) {
			Notification.error("You're browser doesn't support geolocation");
		}

		if (!dip.isOrientationSupported()) {
			Notification.error("You're browser doesn't support device orientation");
		}
	}

	private void reloadArticles(Location location) {
		this.wikiLocationService.search(location.latitude, location.longitude, 2000, 15, 0, "", new MethodCallback<WikiLocationResponse>() {
			@Override
			public void onFailure(Method method, Throwable exception) {
				exception.printStackTrace();
			}

			@Override
			public void onSuccess(Method method, WikiLocationResponse response) {
				setArticles(response.getArticles());
			}
		});
	}

	public void setArticles(List<Article> articles) {
		this.arPanel.clearMarkers();
		for (final Article article : articles) {
			ArticleMarker articleMarker = new ArticleMarker(article);
			this.arPanel.addMarker(articleMarker);
		}
	}

}
