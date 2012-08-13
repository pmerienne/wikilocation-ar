package com.pmerienne.ar.wikilocation.client.service;

import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import org.fusesource.restygwt.client.JSONP;
import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.RestService;

import com.pmerienne.ar.wikilocation.client.model.WikiLocationResponse;

@Path("http://api.wikilocation.org/")
public interface WikiLocationService extends RestService {

	/**
	 * Find nearby Wikipedia articles based on a specific latitude and
	 * longitude.
	 * 
	 * @param latitude
	 *            latitude in decimal degree format.
	 * @param longitude
	 *            longitude in decimal degree format.
	 * @param radius
	 *            the radius (in metres) to search within. There is a maximum
	 *            radius of 20km and it will default to 250m if no radius is
	 *            supplied.
	 * @param limit
	 *            the number of results you want to return. There is a maximum
	 *            limit of 50 results although you can paginate (see below).
	 *            This will default to 50 if no lower figure is sent.
	 * @param offset
	 *            the offset from the first result returned. E.g. if you wanted
	 *            to view results 51-100 you would supply an offset of 50. There
	 *            is no maximum for this parameter. The default is 0.
	 * @param type
	 *            the type of article you are interested in. There are various
	 *            options for this so best to look at some results and then
	 *            filter from there (e.g. you could filter by a type of "river"
	 *            or "landmark"). The default is no filter.
	 * @return a {@link WikiLocationResponse} containing nerby articles
	 */
	@Path("articles")
	@JSONP(callbackParam = "jsonp")
	void search(@QueryParam("lat") double latitude, @QueryParam("lng") double longitude, @QueryParam("radius") double radius,
			@QueryParam("limit") double limit, @QueryParam("offset") double offset, @QueryParam("type") String type,
			MethodCallback<WikiLocationResponse> callback);
}
