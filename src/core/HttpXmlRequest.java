package core;

import io.netty.handler.codec.http.FullHttpRequest;

public class HttpXmlRequest {
	private FullHttpRequest request;
	private Object body;
	
	public HttpXmlRequest(FullHttpRequest request,Object body){
		this.setRequest(request);
		this.setBody(body);
	}

	public FullHttpRequest getRequest() {
		return request;
	}

	public void setRequest(FullHttpRequest request) {
		this.request = request;
	}

	public Object getBody() {
		return body;
	}

	public void setBody(Object body) {
		this.body = body;
	}
	
	
}
